import java.util.ArrayDeque;
import java.util.Queue;

class ApiGateway {
    public String handleRequest(String requestType, String entityId) {
        System.out.println("API Gateway received: " + requestType + " for " + entityId);
        return "Request accepted";
    }
}

class InventoryService {
    public boolean reserveStock(String productId, int quantity) {
        System.out.println("InventoryService: reserving " + quantity + " unit(s) for " + productId);
        return true;
    }
}

class PaymentService {
    public boolean chargeCustomer(String orderId, double amount) {
        System.out.println("PaymentService: charging " + amount + " for order " + orderId);
        return true;
    }
}

class NotificationQueue {
    private final Queue<String> queue = new ArrayDeque<>();

    public void enqueue(String message) {
        queue.offer(message);
        System.out.println("NotificationQueue: queued -> " + message);
    }

    public String poll() {
        return queue.poll();
    }
}

class OrderService {
    private final InventoryService inventoryService = new InventoryService();
    private final PaymentService paymentService = new PaymentService();
    private final NotificationQueue notificationQueue = new NotificationQueue();

    public String placeOrder(String userId, String productId, int quantity) {
        String orderId = "ORD-1001";

        System.out.println("OrderService: creating order " + orderId + " for user " + userId);

        // Synchronous critical steps
        boolean stockReserved = inventoryService.reserveStock(productId, quantity);
        boolean paymentDone = paymentService.chargeCustomer(orderId, 499.99);

        if (!stockReserved || !paymentDone) {
            System.out.println("OrderService: order failed; rollback may be required.");
            return "FAILED";
        }

        // Asynchronous follow-up work
        notificationQueue.enqueue("Order confirmation sent for " + orderId);

        System.out.println("OrderService: order " + orderId + " placed successfully");
        return orderId;
    }
}

public class EcommerceSystemHLDExample {
    public static void main(String[] args) {
        ApiGateway apiGateway = new ApiGateway();
        OrderService orderService = new OrderService();

        String response = apiGateway.handleRequest("CREATE_ORDER", "P-5001");
        String orderId = orderService.placeOrder("U-77", "P-5001", 2);

        System.out.println("Gateway response: " + response);
        System.out.println("Final order id: " + orderId);

        // HLD explanation:
        // - ApiGateway represents the public entry layer.
        // - OrderService is the application service coordinating core business flow.
        // - InventoryService and PaymentService are downstream dependencies.
        // - NotificationQueue demonstrates asynchronous processing to avoid blocking user requests.
        // - This is a simplified architecture showing decomposition, boundaries, and async behavior.
    }
}
