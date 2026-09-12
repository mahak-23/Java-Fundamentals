import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

enum Role {
    ADMIN,
    CUSTOMER,
    VIEWER
}

class User {
    private final String id;
    private final String name;
    private final List<Role> roles;

    public User(String id, String name, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = new ArrayList<>(roles);
    }

    public String getId() {
        return id;
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public String getName() {
        return name;
    }
}

class Product {
    private final String id;
    private final String name;
    private final double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class CacheStore {
    private final Map<String, Object> cache = new HashMap<>();

    public Object get(String key) {
        return cache.get(key);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }
}

class ProductDatabase {
    private final Map<String, Product> products = new HashMap<>();

    public ProductDatabase() {
        products.put("P-101", new Product("P-101", "Laptop", 999.99));
        products.put("P-102", new Product("P-102", "Phone", 699.99));
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }
}

class AuthorizationService {
    public boolean canAccess(User user, String action) {
        if (Objects.equals(action, "READ_PRODUCT")) {
            return user.hasRole(Role.CUSTOMER) || user.hasRole(Role.ADMIN) || user.hasRole(Role.VIEWER);
        }
        if (Objects.equals(action, "CREATE_ORDER")) {
            return user.hasRole(Role.CUSTOMER) || user.hasRole(Role.ADMIN);
        }
        if (Objects.equals(action, "MANAGE_USERS")) {
            return user.hasRole(Role.ADMIN);
        }
        return false;
    }
}

class ProductService {
    private final ProductDatabase database;
    private final CacheStore cacheStore;
    private final AuthorizationService authorizationService;

    public ProductService(ProductDatabase database, CacheStore cacheStore, AuthorizationService authorizationService) {
        this.database = database;
        this.cacheStore = cacheStore;
        this.authorizationService = authorizationService;
    }

    public Product getProduct(User user, String productId) {
        if (!authorizationService.canAccess(user, "READ_PRODUCT")) {
            throw new SecurityException("User is not allowed to read products");
        }

        String cacheKey = "product:" + productId;
        Product cached = (Product) cacheStore.get(cacheKey);
        if (cached != null) {
            System.out.println("Cache hit for " + productId);
            return cached;
        }

        Product product = database.getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found: " + productId);
        }

        cacheStore.put(cacheKey, product);
        System.out.println("Loaded product " + productId + " from database and stored in cache");
        return product;
    }

    public void createOrder(User user, String productId) {
        if (!authorizationService.canAccess(user, "CREATE_ORDER")) {
            throw new SecurityException("User cannot create an order");
        }

        Product product = getProduct(user, productId);
        System.out.println("Order created for " + user.getName() + " on " + product.getName());
    }
}

public class SystemDesignFundamentalsExample {
    public static void main(String[] args) {
        User customer = new User("U-1", "Aman", List.of(Role.CUSTOMER));
        User admin = new User("U-2", "Nisha", List.of(Role.ADMIN));
        User viewer = new User("U-3", "Rohan", List.of(Role.VIEWER));

        ProductDatabase database = new ProductDatabase();
        CacheStore cache = new CacheStore();
        AuthorizationService auth = new AuthorizationService();
        ProductService productService = new ProductService(database, cache, auth);

        System.out.println("=== Customer reads a product ===");
        Product p1 = productService.getProduct(customer, "P-101");
        System.out.println("Result: " + p1.getName() + " - $" + p1.getPrice());

        System.out.println("\n=== Same product requested again ===");
        Product p2 = productService.getProduct(customer, "P-101");
        System.out.println("Result: " + p2.getName() + " - $" + p2.getPrice());

        System.out.println("\n=== Admin creates order ===");
        productService.createOrder(admin, "P-102");

        System.out.println("\n=== Viewer tries to create an order ===");
        try {
            productService.createOrder(viewer, "P-102");
        } catch (SecurityException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        // Design explanation:
        // 1. Client sends a request to an API layer.
        // 2. Authorization is checked before business logic runs.
        // 3. A cache sits in front of the database for repeated reads.
        // 4. The database remains the source of truth.
        // 5. RBAC and caching are common system-design fundamentals in real production systems.
    }
}
