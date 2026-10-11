import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A small, runnable model of checkout. All data is in memory so the example
 * can focus on the order of operations instead of database or framework setup.
 */
public class ECommerceCheckoutDemo {

  private ECommerceCheckoutDemo() {}

  public static void main(String[] args) {
    // A product is the catalog item; its SKU identifies the exact sellable
    // variant and is therefore the key used by inventory.
    Product product = new Product(
      "PRODUCT-1",
      "SKU-RED-M",
      "Trail Shirt, red, medium",
      "Outdoor clothing",
      new BigDecimal("49.95")
    );

    ShoppingCart firstCart = new ShoppingCart("USER-1");
    firstCart.add(product, 1);

    // Only one unit exists. The first customer should buy it; the next should
    // be rejected by InventoryService rather than overselling it.
    InventoryService inventory = new InventoryService();
    inventory.addStock(product.getSkuId(), 1);

    // OrderService depends on an interface, so a real HTTP gateway can replace
    // this predictable in-memory implementation.
    DemoPaymentGateway gateway = new DemoPaymentGateway();
    OrderService orders = new OrderService(inventory, gateway);

    // The same key represents the same logical checkout if the client retries.
    Order firstOrder = orders.placeOrder(
      "USER-1",
      firstCart,
      "checkout-key-001"
    );
    System.out.println(
      "Order result: " +
        firstOrder.getOrderId() +
        " / " +
        firstOrder.getStatus()
    );

    Order retriedOrder = orders.placeOrder(
      "USER-1",
      firstCart,
      "checkout-key-001"
    );
    System.out.println(
      "Retry returned same order: " +
        firstOrder.getOrderId().equals(retriedOrder.getOrderId())
    );
    System.out.println("Gateway captures: " + gateway.getCaptureCount());

    // A different customer and key represents a separate purchase attempt.
    ShoppingCart secondCart = new ShoppingCart("USER-2");
    secondCart.add(product, 1);
    Order secondOrder = orders.placeOrder(
      "USER-2",
      secondCart,
      "checkout-key-002"
    );
    System.out.println("Second buyer result: " + secondOrder.getStatus());
    System.out.println(
      "Remaining available stock: " + inventory.getAvailable(product.getSkuId())
    );
  }

  /** Catalog data for one exact sellable SKU. */
  static final class Product {

    private final String productId;
    private final String skuId;
    private final String name;
    private final String category;
    // BigDecimal avoids binary floating-point rounding errors for money.
    private final BigDecimal unitPrice;

    Product(
      String productId,
      String skuId,
      String name,
      String category,
      BigDecimal unitPrice
    ) {
      if (unitPrice.signum() < 0) {
        throw new IllegalArgumentException("Price cannot be negative");
      }
      this.productId = productId;
      this.skuId = skuId;
      this.name = name;
      this.category = category;
      this.unitPrice = unitPrice;
    }

    String getProductId() {
      return productId;
    }

    String getSkuId() {
      return skuId;
    }

    String getName() {
      return name;
    }

    String getCategory() {
      return category;
    }

    BigDecimal getUnitPrice() {
      return unitPrice;
    }
  }

  /** One SKU and quantity selected in a customer's cart. */
  static final class CartLine {

    private final Product product;
    private final int quantity;

    CartLine(Product product, int quantity) {
      this.product = product;
      this.quantity = quantity;
    }

    Product getProduct() {
      return product;
    }

    int getQuantity() {
      return quantity;
    }

    BigDecimal getLineTotal() {
      return product.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }
  }

  /** A mutable cart. Synchronization protects this in-memory map per process. */
  static final class ShoppingCart {

    private final String userId;
    private final Map<String, CartLine> lines = new LinkedHashMap<>();

    ShoppingCart(String userId) {
      this.userId = userId;
    }

    synchronized void add(Product product, int quantity) {
      if (quantity <= 0) {
        throw new IllegalArgumentException("Quantity must be positive");
      }

      // Adding the same SKU increases its quantity instead of making a second
      // line. A real system would also enforce purchase limits here.
      CartLine existing = lines.get(product.getSkuId());
      int nextQuantity = quantity;
      if (existing != null) {
        nextQuantity += existing.getQuantity();
      }
      lines.put(product.getSkuId(), new CartLine(product, nextQuantity));
    }

    synchronized List<CartLine> getLines() {
      return Collections.unmodifiableList(new ArrayList<>(lines.values()));
    }

    String getUserId() {
      return userId;
    }

    synchronized BigDecimal getTotal() {
      BigDecimal total = BigDecimal.ZERO;
      for (CartLine line : lines.values()) {
        total = total.add(line.getLineTotal());
      }
      return total;
    }

    synchronized String fingerprint() {
      // The fingerprint binds an idempotency key to the request contents. The
      // same key with different cart contents must not reuse the old order.
      StringBuilder value = new StringBuilder(userId);
      for (CartLine line : lines.values()) {
        value
          .append('|')
          .append(line.getProduct().getSkuId())
          .append(':')
          .append(line.getQuantity())
          .append(':')
          .append(line.getProduct().getUnitPrice().toPlainString());
      }
      return value.toString();
    }
  }

  /**
   * Tracks available, reserved, and sold stock. synchronized makes each
   * operation atomic only inside this one JVM, not across multiple servers.
   */
  static final class InventoryService {

    private final Map<String, Stock> stockBySku = new LinkedHashMap<>();
    private final Map<String, Reservation> reservations = new LinkedHashMap<>();

    synchronized void addStock(String skuId, int quantity) {
      if (quantity < 0) {
        throw new IllegalArgumentException("Stock cannot be negative");
      }
      stockBySku.put(skuId, new Stock(quantity));
    }

    synchronized boolean reserve(
      String reservationId,
      String skuId,
      int quantity
    ) {
      // A retry with the same reservation ID returns the original decision.
      Reservation existing = reservations.get(reservationId);
      if (existing != null) {
        return (
          existing.skuId.equals(skuId) &&
          existing.quantity == quantity &&
          existing.status != ReservationStatus.RELEASED
        );
      }

      // Check and decrement happen under the same lock. This prevents two
      // threads in this process from both claiming the last unit.
      Stock stock = stockBySku.get(skuId);
      if (stock == null || quantity <= 0 || stock.available < quantity) {
        return false;
      }

      stock.available -= quantity;
      stock.reserved += quantity;
      reservations.put(
        reservationId,
        new Reservation(skuId, quantity, ReservationStatus.HELD)
      );
      return true;
    }

    synchronized boolean confirm(String reservationId) {
      Reservation reservation = reservations.get(reservationId);
      if (reservation == null) {
        return false;
      }
      if (reservation.status == ReservationStatus.CONFIRMED) {
        return true;
      }
      if (reservation.status != ReservationStatus.HELD) {
        return false;
      }

      Stock stock = stockBySku.get(reservation.skuId);
      stock.reserved -= reservation.quantity;
      stock.sold += reservation.quantity;
      reservation.status = ReservationStatus.CONFIRMED;
      return true;
    }

    synchronized boolean release(String reservationId) {
      Reservation reservation = reservations.get(reservationId);
      if (
        reservation == null || reservation.status == ReservationStatus.RELEASED
      ) {
        return reservation != null;
      }
      if (reservation.status != ReservationStatus.HELD) {
        return false;
      }

      Stock stock = stockBySku.get(reservation.skuId);
      stock.available += reservation.quantity;
      stock.reserved -= reservation.quantity;
      reservation.status = ReservationStatus.RELEASED;
      return true;
    }

    synchronized int getAvailable(String skuId) {
      Stock stock = stockBySku.get(skuId);
      return stock == null ? 0 : stock.available;
    }

    private static final class Stock {

      private int available;
      private int reserved;
      private int sold;

      Stock(int available) {
        this.available = available;
      }
    }

    private static final class Reservation {

      private final String skuId;
      private final int quantity;
      private ReservationStatus status;

      Reservation(String skuId, int quantity, ReservationStatus status) {
        this.skuId = skuId;
        this.quantity = quantity;
        this.status = status;
      }
    }

    private enum ReservationStatus {
      HELD,
      CONFIRMED,
      RELEASED,
    }
  }

  /** Boundary around a payment provider such as Stripe or PayPal. */
  interface PaymentGateway {
    PaymentResult capture(
      String orderId,
      BigDecimal amount,
      String idempotencyKey
    );
  }

  /** Fake provider: stores a result by key so retrying cannot charge twice. */
  static final class DemoPaymentGateway implements PaymentGateway {

    private final Map<String, PaymentResult> resultsByKey =
      new LinkedHashMap<>();
    private int captureCount;

    @Override
    public synchronized PaymentResult capture(
      String orderId,
      BigDecimal amount,
      String idempotencyKey
    ) {
      // A real provider should offer this guarantee for its idempotency key.
      PaymentResult previous = resultsByKey.get(idempotencyKey);
      if (previous != null) {
        return previous;
      }

      captureCount++;
      PaymentResult result = new PaymentResult(true, "PAY-" + orderId, amount);
      resultsByKey.put(idempotencyKey, result);
      return result;
    }

    synchronized int getCaptureCount() {
      return captureCount;
    }
  }

  static final class PaymentResult {

    private final boolean captured;
    private final String providerReference;
    private final BigDecimal amount;

    PaymentResult(
      boolean captured,
      String providerReference,
      BigDecimal amount
    ) {
      this.captured = captured;
      this.providerReference = providerReference;
      this.amount = amount;
    }

    boolean isCaptured() {
      return captured;
    }

    String getProviderReference() {
      return providerReference;
    }

    BigDecimal getAmount() {
      return amount;
    }
  }

  /** Simplified states for this synchronous teaching example. */
  enum OrderStatus {
    PENDING_PAYMENT,
    PAID,
    OUT_OF_STOCK,
    PAYMENT_FAILED,
  }

  /** A durable system would persist this order and every state transition. */
  static final class Order {

    private final String orderId;
    private final String userId;
    private final List<CartLine> lines;
    private final BigDecimal amount;
    private final String idempotencyKey;
    private OrderStatus status;
    private String paymentReference;

    Order(
      String orderId,
      String userId,
      List<CartLine> lines,
      BigDecimal amount,
      String idempotencyKey
    ) {
      this.orderId = orderId;
      this.userId = userId;
      this.lines = new ArrayList<>(lines);
      this.amount = amount;
      this.idempotencyKey = idempotencyKey;
      this.status = OrderStatus.PENDING_PAYMENT;
    }

    String getOrderId() {
      return orderId;
    }

    OrderStatus getStatus() {
      return status;
    }
  }

  /** Coordinates inventory reservation, payment, and order state. */
  static final class OrderService {

    private final InventoryService inventory;
    private final PaymentGateway paymentGateway;
    private final Map<String, String> fingerprintByKey = new LinkedHashMap<>();
    private final Map<String, Order> orderByKey = new LinkedHashMap<>();
    private final AtomicLong nextOrderId = new AtomicLong(1000);

    OrderService(InventoryService inventory, PaymentGateway paymentGateway) {
      this.inventory = inventory;
      this.paymentGateway = paymentGateway;
    }

    synchronized Order placeOrder(
      String userId,
      ShoppingCart cart,
      String idempotencyKey
    ) {
      // Authorization check: a caller cannot check out another user's cart.
      if (!cart.getUserId().equals(userId)) {
        throw new SecurityException("Cart does not belong to this user");
      }
      String fingerprint = cart.fingerprint();
      String previousFingerprint = fingerprintByKey.get(idempotencyKey);
      if (previousFingerprint != null) {
        if (!previousFingerprint.equals(fingerprint)) {
          throw new IllegalArgumentException(
            "Idempotency key was reused for a different request"
          );
        }
        return orderByKey.get(idempotencyKey);
      }
      if (cart.getLines().isEmpty()) {
        throw new IllegalArgumentException("Cannot place an empty cart");
      }

      // Step 1: create a pending order snapshot. Keeping the cart lines on the
      // order protects the purchase record from later cart edits.
      String orderId = "ORD-" + nextOrderId.incrementAndGet();
      Order order = new Order(
        orderId,
        userId,
        cart.getLines(),
        cart.getTotal(),
        idempotencyKey
      );
      List<String> heldReservations = new ArrayList<>();

      // Step 2: reserve every SKU before charging. If one line fails, release
      // earlier holds so a partial order does not strand stock.
      for (CartLine line : order.lines) {
        String reservationId = orderId + "-" + line.getProduct().getSkuId();
        boolean reserved = inventory.reserve(
          reservationId,
          line.getProduct().getSkuId(),
          line.getQuantity()
        );
        if (!reserved) {
          releaseAll(heldReservations);
          order.status = OrderStatus.OUT_OF_STOCK;
          remember(idempotencyKey, fingerprint, order);
          return order;
        }
        heldReservations.add(reservationId);
      }

      // Step 3: use a stable payment key. A retry of this call returns the
      // original result instead of creating another charge.
      PaymentResult payment = paymentGateway.capture(
        orderId,
        order.amount,
        idempotencyKey + "-capture"
      );
      if (!payment.isCaptured()) {
        releaseAll(heldReservations);
        order.status = OrderStatus.PAYMENT_FAILED;
        remember(idempotencyKey, fingerprint, order);
        return order;
      }

      // Step 4: payment succeeded, so convert each temporary hold into a sale.
      for (String reservationId : heldReservations) {
        if (!inventory.confirm(reservationId)) {
          throw new IllegalStateException(
            "Captured payment requires reservation reconciliation: " +
              reservationId
          );
        }
      }
      order.paymentReference = payment.getProviderReference();
      order.status = OrderStatus.PAID;
      remember(idempotencyKey, fingerprint, order);
      return order;
    }

    private void releaseAll(List<String> reservationIds) {
      for (String reservationId : reservationIds) {
        inventory.release(reservationId);
      }
    }

    private void remember(String key, String fingerprint, Order order) {
      fingerprintByKey.put(key, fingerprint);
      orderByKey.put(key, order);
    }
  }
}
