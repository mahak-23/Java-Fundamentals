## Rate limiting and abuse protection

### Rate limiting need

Rate limiting is used to control how many requests a client or user can make in a period of time.

It is needed to protect systems from:

- brute-force attacks
- bot traffic
- abuse and scraping
- malicious API usage
- sudden traffic spikes
- resource exhaustion

Good rate limiting helps maintain:

- availability
- fairness across users
- cost control
- stability for backend systems

### Token bucket algorithm

The token bucket algorithm allows a certain number of requests to be processed in bursts while still enforcing an overall average rate.

Concept:

- bucket has a fixed capacity
- tokens are added at a steady refill rate
- each request consumes one token
- if the bucket is empty, the request is rejected or delayed

Example:

- capacity = 100 tokens
- refill rate = 10 tokens/second
- a burst of 50 requests can be served immediately
- after that, requests are allowed only as tokens refill

Pros:

- handles bursts well
- common for APIs and user quotas
- easier to tune than strict fixed windows
- good for balancing fairness and elasticity

Cons:

- requires careful token refill tuning
- can still reject legitimate traffic when the bucket is empty
- implementation must be thread-safe in distributed systems

Use cases:

- public APIs
- login endpoints
- search and recommendation APIs
- AI model token or request throttling

### Leaky bucket algorithm

The leaky bucket algorithm enforces a steady, consistent output rate.

Concept:

- requests enter a queue/bucket
- the system leaks them out at a fixed rate
- excess requests are dropped or queued

This produces smoother traffic and prevents sudden spikes from overwhelming downstream services.

Pros:

- smooths request flow
- prevents bursts from overwhelming infrastructure
- works well for steady processing pipelines
- easy to reason about in fixed throughput systems

Cons:

- less flexible for burst traffic than token bucket
- queueing can add latency if too much backlog builds up
- can be too strict for systems that need occasional spikes

Use cases:

- network traffic shaping
- stable worker pipelines
- systems needing controlled constant throughput

### Fixed window counter algorithm

The fixed window counter algorithm divides time into equal-sized windows and counts requests within each window.

Concept:

- choose a time window such as 1 minute
- track the number of requests seen in the current window
- if the count exceeds the limit, reject additional requests until the window resets

Example:

- limit = 100 requests per minute
- window = 60 seconds
- if 100 requests arrive in the first minute, the next request is rejected until the next window begins

Pros:

- very simple to implement
- easy to reason about
- fast and memory-light
- suitable for quick protection in gateways or proxies

Cons:

- bursty traffic at the edges of windows can be unfair
- a client can send a large burst right before a reset and another large burst right after, effectively bypassing the intended smooth limits
- less accurate than sliding window methods
- poor fit for systems needing smoother rate shaping

Use cases:

- simple API gateway protections
- startup throttles
- low-complexity quota enforcement

### Sliding window counter algorithm

The sliding window counter algorithm smooths the fixed window problem by looking at request counts across a rolling time period rather than a single fixed interval.

Concept:

- track request timestamps for the last N seconds
- calculate the number of requests in the current rolling window
- reject when the count exceeds the allowed limit

Example:

- limit = 100 requests per minute
- rolling window = last 60 seconds
- if a client made 80 requests in the last 30 seconds and 30 more arrive now, the total may exceed the limit and be rejected

Pros:

- more fair than fixed window counters
- better for burst control near window boundaries
- smoother enforcement for real traffic patterns

Cons:

- more memory usage because timestamps must be tracked
- slightly more complex than fixed windows
- still requires careful cleanup of expired entries

Use cases:

- APIs with moderate traffic and fairness requirements
- auth endpoints with burst protection
- systems where fixed windows feel too coarse

### IP-based limiting

IP-based limiting restricts requests by source address.

Examples:

- a single IP cannot exceed 100 requests/minute
- block suspicious IPs after repeated failures
- combine with firewall or CDN protections

Pros:

- simple to implement
- good for anonymous traffic or basic abuse prevention
- effective for blocking suspicious sources quickly

Cons:

- behind NATs or proxies many users may share one IP
- not fair for distributed users
- can block legitimate clients when multiple users are behind the same gateway
- weak for user-specific policies

Use cases:

- public web apps
- DDoS protection
- anti-scraping rules

### User-based limiting

User-based limiting applies quotas to a specific user account, API key, or session.

Examples:

- each authenticated user gets 1000 requests/hour
- each premium user gets higher limits than free users
- API key is throttled based on plan level

Pros:

- fairer than IP-based limiting
- aligns with account or subscription limits
- better for authenticated services
- supports different quotas by plan or role

Cons:

- needs identity and tracking per user
- more complex than IP-based checks
- requires secure storage of user quota keys
- can become expensive if done naively at scale

Use cases:

- SaaS apps
- paid API platforms
- personalized services

### Distributed rate limiting

Distributed rate limiting is required when multiple app servers or regions handle requests.

In a single-service deployment, local counters may be enough, but in distributed systems, each instance cannot safely count globally on its own.

Common approaches:

- Redis counters with atomic increments
- centralized token bucket stores
- per-key sharded counters
- distributed caches or in-memory stores for shared limits

Important issues:

- race conditions in concurrent requests
- synchronization across multiple application nodes
- adherence to a strict global quota
- avoiding a central limiter becoming a bottleneck

Patterns:

- per-user quota in Redis
- distributed sliding window counters
- edge-level enforcement near the load balancer or gateway

Pros:

- works across multiple app servers and regions
- enforces global quotas consistently
- supports enterprise-level fairness and billing controls

Cons:

- requires coordination between services
- depends on centralized state such as Redis or a gateway
- adds operational complexity and a single point of engineering concern if misconfigured

### Low-level design (LLD) for rate limiting

A typical rate limiter has the following internal pieces:

- key: identifies the client, such as user ID, IP, or API key
- limit: maximum allowed requests in the time window
- window size: duration of the quota window
- counter or token store: tracks current usage
- decision logic: accepts or rejects based on current counters
- response handling: returns 429 Too Many Requests when blocked

Example design:

```java
class RateLimitDecision {
    private final String key;
    private final int limit;
    private final long windowMs;
    private final long requestTimeMs;

    public RateLimitDecision(String key, int limit, long windowMs, long requestTimeMs) {
        this.key = key;
        this.limit = limit;
        this.windowMs = windowMs;
        this.requestTimeMs = requestTimeMs;
    }

    public boolean isAllowed(int currentCount) {
        return currentCount < limit;
    }
}
```

Pseudo-flow:

1. Extract user key or client IP.
2. Look up current request count in cache or store.
3. Compare against configured limit.
4. If allowed, increment count and proceed.
5. If blocked, return 429 response and optionally include retry-after header.
6. Clear or expire the count when the time window resets.

This is the basis for token bucket, fixed window, and sliding window implementations in production systems.

### Algorithm comparison table

| Algorithm                 | Best for                             | Pros                                  | Cons                                      | Typical use                          |
| ------------------------- | ------------------------------------ | ------------------------------------- | ----------------------------------------- | ------------------------------------ |
| Token bucket              | Bursty traffic, APIs, user quotas    | Allows spikes, flexible, easy to tune | Can reject valid requests when empty      | Public APIs, login throttling        |
| Leaky bucket              | Smooth steady throughput             | Stable output rate, prevents overload | Less burst-friendly, queue delay possible | Network shaping, worker pipelines    |
| Fixed window counter      | Simple quotas and early protections  | Easy, cheap, fast                     | Boundary bursts can bypass fairness       | Basic API limits, startup safeguards |
| Sliding window counter    | Fairer burst protection              | Better smoothing than fixed windows   | More state and memory                     | Balance fairness and stability       |
| IP-based limiting         | Anonymous or edge traffic control    | Simple, effective against abuse       | Shared IPs cause unfairness               | DDoS protection, anti-scraping       |
| User-based limiting       | Authenticated product limits         | Fairer and business-aware             | Requires identity + tracking              | SaaS, paid APIs                      |
| Distributed rate limiting | Multi-node systems and global quotas | Consistent across nodes               | More operational complexity               | Large-scale platforms                |

### AI strategy comparison

When using AI systems, rate limiting should match the workload type.

#### Token bucket for AI APIs

Best for:

- bursty AI feature requests
- user-generated prompts and chat sessions
- variable inference demand

Why:

- allows moderate spikes without rejecting all traffic
- easy to match user plan or request tier

#### Leaky bucket for AI throughput control

Best for:

- stable model serving pipelines
- guarding model backends from overload
- smooth queue processing

Why:

- keeps throughput steady and predictable
- prevents noisy bursts from overwhelming GPUs or model servers

#### IP-based limiting for AI surfaces

Best for:

- anonymous or public endpoints
- chatbot demo pages
- anti-abuse protection

Why:

- simple to implement at the edge
- helps block repeated abuse from suspicious sources

#### User-based limiting for AI products

Best for:

- subscriptions and paid AI usage plans
- enterprise customers with quotas
- preventing unfair use by single accounts

Why:

- ties limits to business value and plan boundaries
- easier to enforce tiered access policies

#### Distributed limiting for AI at scale

Best for:

- multiple app instances or regions
- large enterprise AI platforms
- multi-service inference pipelines

Why:

- prevents each node from making isolated decisions
- maintains consistent quotas across the fleet

### Interview perspective

For interviews, explain rate limiting in terms of:

- token bucket allows bursts and is usually more user-friendly
- leaky bucket smooths traffic and is useful for stable rate control
- IP-based limiting is simple but imperfect behind NATs and proxies
- user-based limiting is fairer and more business-aware
- distributed rate limiting needs shared state and coordination across nodes
- AI systems often combine token bucket logic with queue-based model throttling to balance latency and cost

### Java implementations

For deterministic algorithm examples with fixed timestamps and printed traces, see [RateLimitingAlgorithmsExample.java](RateLimitingAlgorithmsExample.java).

The complete, deterministic Java demo for token bucket, fixed window, sliding window log, and bounded leaky bucket is [RateLimitingAlgorithmsExample.java](RateLimitingAlgorithmsExample.java). Run it with:

```powershell
javac RateLimitingAlgorithmsExample.java
java RateLimitingAlgorithmsExample
```

Dry run for the token bucket (capacity 3, refill 1 token/second):

|    Time | Request | Result | Tokens after request |
| ------: | ------: | ------ | -------------------: |
|    0 ms |       1 | ALLOW  |                    2 |
|    0 ms |       2 | ALLOW  |                    1 |
|    0 ms |       3 | ALLOW  |                    0 |
|    0 ms |       4 | REJECT |                    0 |
| 1000 ms |       5 | ALLOW  |                    0 |

At 1000 ms, one token has refilled, so the fifth request is accepted and consumes it.

Fixed and sliding window dry run (limit 3, window 1000 ms):

|    Time | Fixed window | Fixed count | Sliding window log | Active requests |
| ------: | ------------ | ----------: | ------------------ | --------------: |
|    0 ms | ALLOW        |           1 | ALLOW              |               1 |
|  100 ms | ALLOW        |           2 | ALLOW              |               2 |
|  200 ms | ALLOW        |           3 | ALLOW              |               3 |
|  500 ms | REJECT       |           3 | REJECT             |               3 |
| 1000 ms | ALLOW        |           1 | ALLOW              |               3 |

At 1000 ms, the fixed window resets at the boundary. The sliding log expires the request at 0 ms and retains the requests at 100, 200, and 1000 ms in its rolling window.

Leaky bucket dry run (queue capacity 3; each explicit leak represents one worker drain):

```text
offer r1 -> true
offer r2 -> true
offer r3 -> true
offer r4 -> false
leak -> r1
offer r4 -> true, queueSize=3
```

#### Token bucket implementation

```java
import java.time.Instant;

class TokenBucket {
    private final int capacity;
    private final double refillRatePerSecond;
    private double tokens;
    private long lastRefillTimeMillis;

    public TokenBucket(int capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillTimeMillis = System.currentTimeMillis();
    }

    public synchronized boolean tryConsume(int amount) {
        refill();
        if (tokens >= amount) {
            tokens -= amount;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        double elapsedSeconds = (now - lastRefillTimeMillis) / 1000.0;
        tokens = Math.min(capacity, tokens + elapsedSeconds * refillRatePerSecond);
        lastRefillTimeMillis = now;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket bucket = new TokenBucket(5, 1.0);

        System.out.println(bucket.tryConsume(1));
        System.out.println(bucket.tryConsume(1));
        System.out.println(bucket.tryConsume(1));
        System.out.println(bucket.tryConsume(1));
        System.out.println(bucket.tryConsume(1));
        System.out.println(bucket.tryConsume(1));
    }
}
```

#### Leaky bucket implementation

```java
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class LeakyBucket {
    private final BlockingQueue<String> queue;
    private final int leakRatePerSecond;
    private final Thread leakThread;

    public LeakyBucket(int capacity, int leakRatePerSecond) {
        this.queue = new ArrayBlockingQueue<>(capacity);
        this.leakRatePerSecond = leakRatePerSecond;

        this.leakThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000 / leakRatePerSecond);
                    if (!queue.isEmpty()) {
                        queue.poll();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        this.leakThread.start();
    }

    public boolean offer(String item) {
        return queue.offer(item);
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyBucket bucket = new LeakyBucket(3, 1);
        System.out.println(bucket.offer("req1"));
        System.out.println(bucket.offer("req2"));
        System.out.println(bucket.offer("req3"));
        System.out.println(bucket.offer("req4"));
        Thread.sleep(3000);
        System.out.println(bucket.size());
        bucket.leakThread.interrupt();
    }
}
```

These rate-limiting examples show how state and elapsed time determine whether a request is accepted. The round-robin load-balancer implementation is in the [load-balancing module](../12_load_balancing/LoadBalancing.md).
