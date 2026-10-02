import java.util.HashMap;
import java.util.Map;

class ShortUrlStore {
    private final Map<String, String> shortToLong = new HashMap<>();

    public String createShortUrl(String longUrl) {
        String shortCode = "abc" + (shortToLong.size() + 1);
        shortToLong.put(shortCode, longUrl);
        System.out.println("Created short code " + shortCode + " -> " + longUrl);
        return shortCode;
    }

    public String resolveShortUrl(String shortCode) {
        String longUrl = shortToLong.get(shortCode);
        if (longUrl == null) {
            throw new IllegalArgumentException("Short code not found");
        }
        System.out.println("Resolved " + shortCode + " to " + longUrl);
        return longUrl;
    }
}

public class URLShortenerHLDExample {
    public static void main(String[] args) {
        ShortUrlStore store = new ShortUrlStore();

        String shortCode = store.createShortUrl("https://www.example.com/very/long/path/with/many/segments");
        String resolved = store.resolveShortUrl(shortCode);

        System.out.println("Redirect target: " + resolved);

        // HLD explanation:
        // - This is the minimal core of a URL shortener: create and resolve mappings.
        // - In production, a cache (like Redis) and a database would be added for scale.
        // - The real bottleneck is read-heavy traffic for redirects, so caching is critical.
    }
}
