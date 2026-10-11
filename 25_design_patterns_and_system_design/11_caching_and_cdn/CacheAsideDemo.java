import java.util.HashMap;
import java.util.Map;

public class CacheAsideDemo {
    public static void main(String[] args) {
        ProductDatabase database = new ProductDatabase();
        ProductCache cache = new ProductCache();
        ProductService service = new ProductService(database, cache);

        System.out.println("First request:");
        System.out.println(service.getProductName("P-101"));

        System.out.println("\nSecond request:");
        System.out.println(service.getProductName("P-101"));

        System.out.println("\nDatabase reads: " + database.getReadCount());
    }

    static final class ProductService {
        private final ProductDatabase database;
        private final ProductCache cache;

        ProductService(ProductDatabase database, ProductCache cache) {
            this.database = database;
            this.cache = cache;
        }

        String getProductName(String productId) {
            String cachedName = cache.get(productId);
            if (cachedName != null) {
                System.out.println("Cache hit for " + productId);
                return cachedName;
            }

            System.out.println("Cache miss for " + productId);
            String productName = database.findName(productId);
            if (productName == null) {
                throw new IllegalArgumentException("Product not found: " + productId);
            }

            cache.put(productId, productName);
            return productName;
        }
    }

    static final class ProductCache {
        private final Map<String, String> values = new HashMap<>();

        String get(String productId) {
            return values.get(productId);
        }

        void put(String productId, String productName) {
            values.put(productId, productName);
        }
    }

    static final class ProductDatabase {
        private final Map<String, String> products = new HashMap<>();
        private int readCount;

        ProductDatabase() {
            products.put("P-101", "Trail Shoes");
        }

        String findName(String productId) {
            readCount++;
            System.out.println("Database lookup for " + productId);
            return products.get(productId);
        }

        int getReadCount() {
            return readCount;
        }
    }
}
