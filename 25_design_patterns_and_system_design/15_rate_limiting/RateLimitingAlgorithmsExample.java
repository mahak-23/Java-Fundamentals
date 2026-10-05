import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.Queue;

public class RateLimitingAlgorithmsExample {
    private static final class TokenBucket {
        private final int capacity;
        private final double refillPerSecond;
        private double tokens;
        private long lastRefillMillis;

        TokenBucket(int capacity, double refillPerSecond, long startMillis) {
            if (capacity <= 0 || refillPerSecond < 0) {
                throw new IllegalArgumentException("Capacity must be positive and refill rate non-negative");
            }
            this.capacity = capacity;
            this.refillPerSecond = refillPerSecond;
            this.tokens = capacity;
            this.lastRefillMillis = startMillis;
        }

        boolean allow(long nowMillis) {
            refill(nowMillis);
            if (tokens < 1) {
                return false;
            }
            tokens -= 1;
            return true;
        }

        double availableTokens() {
            return tokens;
        }

        private void refill(long nowMillis) {
            if (nowMillis < lastRefillMillis) {
                throw new IllegalArgumentException("Time must be monotonic");
            }
            long elapsedMillis = nowMillis - lastRefillMillis;
            tokens = Math.min(capacity, tokens + elapsedMillis * refillPerSecond / 1000.0);
            lastRefillMillis = nowMillis;
        }
    }

    private static final class FixedWindowCounter {
        private final int limit;
        private final long windowMillis;
        private long windowStart = Long.MIN_VALUE;
        private int count;

        FixedWindowCounter(int limit, long windowMillis) {
            if (limit <= 0 || windowMillis <= 0) {
                throw new IllegalArgumentException("Limit and window must be positive");
            }
            this.limit = limit;
            this.windowMillis = windowMillis;
        }

        boolean allow(long nowMillis) {
            long currentWindowStart = nowMillis - Math.floorMod(nowMillis, windowMillis);
            if (currentWindowStart != windowStart) {
                windowStart = currentWindowStart;
                count = 0;
            }
            if (count >= limit) {
                return false;
            }
            count++;
            return true;
        }

        int count() {
            return count;
        }
    }

    private static final class SlidingWindowLog {
        private final int limit;
        private final long windowMillis;
        private final Deque<Long> requestTimes = new ArrayDeque<>();

        SlidingWindowLog(int limit, long windowMillis) {
            if (limit <= 0 || windowMillis <= 0) {
                throw new IllegalArgumentException("Limit and window must be positive");
            }
            this.limit = limit;
            this.windowMillis = windowMillis;
        }

        boolean allow(long nowMillis) {
            while (!requestTimes.isEmpty() && requestTimes.peekFirst() <= nowMillis - windowMillis) {
                requestTimes.removeFirst();
            }
            if (requestTimes.size() >= limit) {
                return false;
            }
            requestTimes.addLast(nowMillis);
            return true;
        }

        int count() {
            return requestTimes.size();
        }
    }

    private static final class LeakyBucket {
        private final int capacity;
        private final Queue<String> queue = new ArrayDeque<>();

        LeakyBucket(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be positive");
            }
            this.capacity = capacity;
        }

        boolean offer(String request) {
            if (queue.size() == capacity) {
                return false;
            }
            return queue.offer(request);
        }

        String leakOne() {
            return queue.poll();
        }

        int size() {
            return queue.size();
        }
    }

    public static void main(String[] args) {
        runTokenBucket();
        runFixedWindow();
        runSlidingWindow();
        runLeakyBucket();
    }

    private static void runTokenBucket() {
        TokenBucket bucket = new TokenBucket(3, 1.0, 0);
        System.out.println("Token bucket (capacity=3, refill=1 token/sec):");
        printBucketAttempt(bucket, 0);
        printBucketAttempt(bucket, 0);
        printBucketAttempt(bucket, 0);
        printBucketAttempt(bucket, 0);
        printBucketAttempt(bucket, 1000);
    }

    private static void printBucketAttempt(TokenBucket bucket, long timeMillis) {
        boolean allowed = bucket.allow(timeMillis);
        System.out.printf(Locale.ROOT, "  t=%dms -> %s, tokens=%.1f%n",
            timeMillis, allowed ? "ALLOW" : "REJECT", bucket.availableTokens());
    }

    private static void runFixedWindow() {
        FixedWindowCounter limiter = new FixedWindowCounter(3, 1000);
        long[] times = {0, 100, 200, 500, 1000};
        System.out.println("Fixed window (limit=3, window=1000ms):");
        for (long time : times) {
            boolean allowed = limiter.allow(time);
            System.out.printf("  t=%dms -> %s, windowCount=%d%n",
                time, allowed ? "ALLOW" : "REJECT", limiter.count());
        }
    }

    private static void runSlidingWindow() {
        SlidingWindowLog limiter = new SlidingWindowLog(3, 1000);
        long[] times = {0, 100, 200, 500, 1000};
        System.out.println("Sliding window log (limit=3, rolling window=1000ms):");
        for (long time : times) {
            boolean allowed = limiter.allow(time);
            System.out.printf("  t=%dms -> %s, activeCount=%d%n",
                time, allowed ? "ALLOW" : "REJECT", limiter.count());
        }
    }

    private static void runLeakyBucket() {
        LeakyBucket bucket = new LeakyBucket(3);
        System.out.println("Leaky bucket (queue capacity=3):");
        System.out.println("  offer r1 -> " + bucket.offer("r1"));
        System.out.println("  offer r2 -> " + bucket.offer("r2"));
        System.out.println("  offer r3 -> " + bucket.offer("r3"));
        System.out.println("  offer r4 -> " + bucket.offer("r4"));
        System.out.println("  leak -> " + bucket.leakOne());
        System.out.println("  offer r4 -> " + bucket.offer("r4") + ", queueSize=" + bucket.size());
    }
}
