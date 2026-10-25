import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Minimal in-process demo of structured logs, golden-signal style counters,
 * and trace/span IDs — no external Prometheus or Jaeger required.
 */
public class ObservabilityDemo {
    public static void main(String[] args) {
        String traceId = UUID.randomUUID().toString().substring(0, 6);
        GoldenSignals metrics = new GoldenSignals();

        log("INFO", traceId, "checkout started", Map.of("userId", 42));
        Span apiSpan = new Span(traceId, "s1", null, "api-gateway", 12);
        Span paymentSpan = new Span(traceId, "s2", "s1", "payment", 480);

        metrics.recordRequest();
        if (paymentSpan.durationMs() > 200) {
            metrics.recordSlowRequest();
            log("ERROR", traceId, "checkout failed after retries", Map.of(
                    "userId", 42,
                    "retries", 3,
                    "slowHop", "payment"));
        }

        System.out.println("--- spans (trace tree) ---");
        for (Span span : List.of(apiSpan, paymentSpan)) {
            System.out.println(span.toJsonLine());
        }
        System.out.println("--- metrics snapshot ---");
        System.out.println(metrics.snapshotJson());
    }

    static void log(String level, String traceId, String message, Map<String, Object> fields) {
        Map<String, Object> entry = new LinkedHashMap<>();
        entry.put("ts", Instant.now().toString());
        entry.put("level", level);
        entry.put("msg", message);
        entry.put("traceId", traceId);
        entry.putAll(fields);
        System.out.println(toJson(entry));
    }

    static String toJson(Map<String, Object> map) {
        List<String> parts = new ArrayList<>();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            Object v = e.getValue();
            String value = v instanceof Number ? v.toString() : "\"" + v + "\"";
            parts.add("\"" + e.getKey() + "\":" + value);
        }
        return "{" + String.join(",", parts) + "}";
    }

    static final class Span {
        private final String traceId;
        private final String spanId;
        private final String parentId;
        private final String service;
        private final long durationMs;

        Span(String traceId, String spanId, String parentId, String service, long durationMs) {
            this.traceId = traceId;
            this.spanId = spanId;
            this.parentId = parentId;
            this.service = service;
            this.durationMs = durationMs;
        }

        long durationMs() {
            return durationMs;
        }

        String toJsonLine() {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("traceId", traceId);
            m.put("spanId", spanId);
            if (parentId != null) {
                m.put("parentId", parentId);
            }
            m.put("service", service);
            m.put("durMs", durationMs);
            return toJson(m);
        }
    }

    /** Counters inspired by latency, traffic, errors, saturation (golden signals). */
    static final class GoldenSignals {
        private final AtomicLong requests = new AtomicLong();
        private final AtomicLong slowRequests = new AtomicLong();

        void recordRequest() {
            requests.incrementAndGet();
        }

        void recordSlowRequest() {
            slowRequests.incrementAndGet();
        }

        String snapshotJson() {
            long total = requests.get();
            long slow = slowRequests.get();
            double errorRate = total == 0 ? 0 : (double) slow / total;
            return "{\"traffic_rps_approx\":" + total
                    + ",\"slow_or_error_count\":" + slow
                    + ",\"error_rate_approx\":" + errorRate + "}";
        }
    }
}
