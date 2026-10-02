# Design Patterns and System Design

This module helps you think beyond coding syntax and into software architecture.

It covers:

- common design patterns
- low-level design (LLD)
- high-level design (HLD)
- tradeoffs in scalability, performance, and reliability

## Subtopics

- [01_creational_patterns](01_creational_patterns/)
- [02_structural_patterns](02_structural_patterns/)
- [03_behavioral_patterns](03_behavioral_patterns/)
- [04_low_level_design](04_low_level_design/)
- [05_high_level_design](05_high_level_design/)

## LLD vs HLD

### Low-level design (LLD)

LLD focuses on a feature or module.

Questions it answers:

- What classes are needed?
- What interfaces and methods exist?
- How do entities and services interact?
- What edge cases and validations are required?

Examples:

- Parking Lot
- Library Management System
- Ride Sharing System
- Splitwise clone

### High-level design (HLD)

HLD focuses on the overall system.

Questions it answers:

- What are the main components?
- What database and cache choices are needed?
- How do services communicate?
- How do we handle scale, load, and failure?

Examples:

- URL shortener
- Notification service
- Chat system
- E-commerce platform

## Core system-design concepts

### Functional requirements (FR)

Functional requirements describe what the system must do.

Examples:

- create an order
- send a message
- fetch user profile
- process payment

### Non-functional requirements (NFR)

Non-functional requirements describe quality attributes of the system.

Examples:

- availability
- latency
- scalability
- reliability
- security
- consistency
- fault tolerance

### CAP theorem

CAP says that in a distributed system, you can usually guarantee only two of the following three at the same time:

- Consistency
- Availability
- Partition tolerance

Typical tradeoff examples:

- strong consistency usually reduces availability under partitions
- eventual consistency improves availability and partition tolerance
- choosing the right tradeoff depends on business needs

### Load balancer

A load balancer distributes requests across multiple servers to improve:

- availability
- throughput
- fault tolerance
- scalability

Common patterns:

- round-robin
- least-connections
- IP hash
- weighted balancing

## Load balancing and infrastructure basics

### Load balancing basics

Load balancing is the practice of sending incoming traffic across multiple backend servers so that no single machine becomes a bottleneck.

A good load balancer helps with:

- better utilization of servers
- higher availability during failures
- smoother scaling under traffic spikes
- easier deployment and maintenance

Typical setup:

- users call a domain or public IP
- load balancer receives the request
- it forwards the request to one of the healthy application instances
- the response is returned to the user

In real systems, the load balancer can also handle TLS termination, routing, rate limiting, session stickiness, and health verification.

### Layer 4 vs Layer 7 load balancers

#### Layer 4 (L4) load balancer

L4 load balancers work at the transport/network layer.

They route based on:

- IP address
- TCP/UDP port
- connection metadata

Examples:

- TCP load balancing
- UDP load balancing
- simple packet or connection-level routing

Advantages:

- very fast
- low overhead
- good for general traffic distribution

Disadvantages:

- cannot inspect HTTP path, headers, or cookies
- limited application-aware routing

Use cases:

- game servers
- TCP-based services
- simple service routing where content awareness is not required

#### Layer 7 (L7) load balancer

L7 load balancers work at the application layer.

They can inspect:

- HTTP method
- URL path
- headers
- cookies
- request body (in some cases)

Examples:

- routing /api to one service and /admin to another
- sending mobile traffic to a different backend
- SSL termination and request-based rules

Advantages:

- smarter routing decisions
- better content-based traffic control
- easier security and caching control

Disadvantages:

- more CPU and complexity
- more latency than a raw L4 balancer

Use cases:

- web apps
- APIs
- microservices with path-based routing

### Common load balancing algorithms

#### Round robin

Requests are distributed in a circular order.

Good when:

- all servers have similar capacity
- workloads are evenly distributed

Example:

- server 1 receives request 1
- server 2 receives request 2
- server 3 receives request 3
- then repeat

#### Least connections

The balancer sends the new request to the server with the fewest active connections.

Good when:

- server workloads vary by request intensity
- some backend services are slower than others

This is often better than round robin when traffic is uneven.

#### Weighted load balancing

Some servers get more traffic because they are stronger or have more capacity.

Example:

- server A weight = 3
- server B weight = 1
- server A gets roughly three times the traffic

#### IP hash

The balancer computes a hash from the client's IP and consistently maps the client to the same backend.

Useful for:

- sticky sessions
- caching efficiency
- stateful or session-based services

### Reverse proxy concept

A reverse proxy sits in front of one or more backend servers and represents them to the outside world.

It is different from a forward proxy, which is used by clients to access external networks.

A reverse proxy can:

- hide backend server details
- terminate SSL
- cache static content
- compress responses
- apply rate limiting
- protect against abuse
- route to multiple app servers

In many deployments, the reverse proxy and load balancer are combined in one system such as Nginx or HAProxy.

## Message queues and event-driven architecture

### Message queues concept

A message queue is a communication mechanism that allows different parts of a system to exchange data asynchronously.

Instead of service A calling service B directly and waiting for the result, A can place a message in a queue and return immediately. Service B processes the message later.

This helps with:

- decoupling services
- improving resilience
- handling bursts of requests
- protecting downstream systems from overload

Examples:

- order created event
- payment success event
- user signup event
- email notification job

### Synchronous vs asynchronous communication

#### Synchronous communication

In synchronous communication, the caller waits for the callee to respond before continuing.

Examples:

- HTTP request to an API
- RPC call
- direct database transaction in the same request flow

Pros:

- simpler to reason about
- immediate feedback
- good for user-facing interactive flows

Cons:

- tight coupling
- slower if downstream is slow or unavailable
- can amplify failures under load

#### Asynchronous communication

In asynchronous communication, the caller sends a message and continues without waiting for an immediate response.

Examples:

- queue-based processing
- event streaming
- background jobs

Pros:

- more resilient
- decoupled services
- better for spikes and retries
- allows background processing

Cons:

- harder to debug
- eventual consistency
- needs retry and monitoring logic

### Kafka basics

Kafka is a distributed event streaming platform designed for high-throughput, durable, and scalable messaging.

It is often used for:

- event logs
- real-time data pipelines
- async service communication
- analytics and monitoring pipelines

Core Kafka ideas:

- events are written to topics
- topics are split into partitions
- producers publish to topics
- consumers read from partitions
- Kafka stores records durably and can replay them

Kafka is commonly used when you need:

- large-scale event processing
- decoupled microservices
- change data capture or stream processing
- real-time analytics

### Producers, consumers, topics

#### Producer

A producer sends data to Kafka by writing events to a topic.

Examples:

- order service sends OrderCreated
- inventory service emits StockUpdated
- payment service publishes PaymentSucceeded

#### Consumer

A consumer reads events from a topic and processes them.

Consumers may be:

- a single consumer app
- a consumer group with multiple instances
- a processor reading from many partitions

#### Topic

A topic is a category or stream of events.

Examples:

- user-signup-events
- payment-events
- recommendation-events
- billing-audit-events

Topics help organize event streams by domain or use case.

### At-least-once vs exactly-once delivery

#### At-least-once delivery

The message is delivered one or more times, but it may be retried if there was no acknowledgement.

This means:

- the message may be processed more than once
- duplicates are possible
- the system must be idempotent

Good for many systems where duplicate handling is acceptable or recoverable.

#### At-most-once delivery

The message is delivered zero or one time.

This is fast, but it can lose messages if a failure happens before acknowledgement.

#### Exactly-once delivery

Exactly-once means the message is processed once and only once, even in distributed systems with retries and failures.

In practice, exact-once is hard to achieve in distributed systems.

Many systems aim for:

- at-least-once delivery with idempotent processing
- deduplication keys
- transactional writes
- offset tracking

This is often the correct engineering tradeoff in real systems.

### Event-driven architecture

Event-driven architecture (EDA) is based on events that describe what happened, not commands that tell another component exactly what to do.

A producer emits an event such as:

- OrderPlaced
- UserRegistered
- PaymentFailed

Consumers react to those events.

Benefits:

- loose coupling
- easier scaling
- better support for background processing
- flexible reaction to new workflows

Common patterns:

- event producer -> message broker -> event consumer
- event-driven microservices
- async workflows with retry and dead-letter queues

Example:

- user registers
- auth service emits UserRegistered
- onboarding service sends welcome email
- analytics service records the action
- billing service triggers trial setup

### AI pipeline design

AI pipelines often use queues and event streams to decouple model inference, data ingestion, and workflow orchestration.

Typical flow:

- user submits request
- API service validates input
- message is queued for AI processing
- worker consumes the job
- model runs inference
- result is stored or published to another topic
- downstream service sends notification or UI update

Why queues matter in AI systems:

- inference workloads are bursty
- model calls may be slow
- retries are common
- background jobs avoid blocking user requests
- one failure should not bring down the entire system

Example architecture:

- API layer accepts requests
- message queue holds jobs
- worker pool runs model inference
- results go to Redis, database, or another topic
- monitoring tracks latency, queue depth, and failure rates

AI system design often includes:

- input validation
- retry policies
- model versioning
- asynchronous inference jobs
- observability and tracing
- dead-letter queues for failed jobs

### Interview perspective

For system design interviews, important points are:

- synchronous calls are simpler but tightly coupled
- asynchronous communication improves resilience and scalability
- Kafka is useful for high-throughput event streams
- topics, producers, and consumers form the core model
- at-least-once is common, while exactly-once is expensive and more complex
- event-driven architecture supports decoupling and growth

## System design examples

### 1. URL shortener design

Problem:

- given a long URL, generate a short URL
- redirect users to the original URL quickly
- handle high traffic efficiently

Key components:

- API layer for create/redirect requests
- database to store original URL and short code
- cache for hot URLs
- hash generation or base62 encoding
- load balancer in front of app instances

Main flow:

- user submits a long URL
- server generates a unique short key
- store key -> original URL mapping in DB
- cache the mapping for quick access
- redirect request goes to lookup service and returns 301/302 redirect

Important considerations:

- short keys must be unique
- database design should support fast lookup
- cache reduces repeated redirect latency
- rate limiting prevents abuse

Example flow:

```mermaid
flowchart LR
    U[User] -->|submit long URL| API[API Server]
    API -->|generate short key| SVC[URL Service]
    SVC --> DB[(Database)]
    SVC --> CACHE[(Cache)]
    U -->|visit /abc123| API
    API --> CACHE
    CACHE -->|hit| REDIRECT[Redirect Response]
    CACHE -->|miss| DB
    DB --> REDIRECT
```

### 2. Chat application design

Problem:

- users send and receive messages in real time
- support many concurrent users
- maintain message delivery and read status

Key components:

- web socket servers for real-time communication
- application servers for business logic
- message store for persistent chat history
- cache for active sessions and recent conversations
- notification system for offline users

Main flow:

- client opens websocket connection
- server keeps a connection mapping for each user
- message is sent to the target user or room
- app stores the message in DB
- receiver gets real-time update via websocket

Important considerations:

- connection management is critical at scale
- fan-out for group chats can be expensive
- message ordering may need per-room sequence rules
- presence and online status add complexity

Example flow:

```mermaid
flowchart LR
    U1[User A] --> WS[WebSocket Server]
    U2[User B] --> WS
    WS --> APP[Chat Service]
    APP --> DB[(Message Store)]
    APP --> CACHE[(Presence/Recent Cache)]
    APP -->|deliver message| U2
    APP -->|broadcast room update| U1
```

### 3. Notification service design

Problem:

- send email, SMS, and push notifications reliably
- handle large volumes without blocking user actions

Key components:

- API service receives notification requests
- message queue buffers work
- worker services process delivery
- email/SMS/push providers as downstream integrations
- retry and dead-letter infrastructure

Main flow:

- app emits a notification event
- message broker stores the event
- workers read and deliver it
- if provider fails, event is retried
- failures after retries go to a dead-letter queue for investigation

Important considerations:

- asynchronous delivery prevents user request delay
- idempotency avoids duplicate sends
- provider rate limits must be respected
- observability is essential for retries and failures

Example flow:

```mermaid
flowchart LR
    APP[App Service] --> Q[(Message Queue)]
    Q --> W[Worker Service]
    W --> EMAIL[Email Provider]
    W --> SMS[SMS Provider]
    W --> PUSH[Push Service]
    EMAIL --> RETRY[Retry / DLQ]
    SMS --> RETRY
    PUSH --> RETRY
```

### 4. E-commerce platform design

Problem:

- allow users to browse products, add to cart, place orders, and pay
- support large concurrent traffic
- maintain data consistency and reliability

Key components:

- web/API tier
- product catalog database
- inventory service
- cart service
- order service
- payment service
- search and recommendation systems
- cache layer
- message queue for async workflows

Main flow:

- user searches or views products
- product data is fetched from cache or DB
- cart updates are stored and validated
- order is created after stock checks
- payment service handles transaction processing
- inventory and notification events are fired asynchronously

Important considerations:

- payments require strong consistency and careful failure handling
- inventory updates should avoid overselling
- caching reduces read load for popular products
- queue-based processing decouples order confirmation and notifications

Example flow:

```mermaid
flowchart TD
    U[Customer] --> B[Browser/App]
    B --> API[API Layer]
    API --> C[Cart Service]
    API --> O[Order Service]
    O --> P[Payment Service]
    O --> Q[(Message Queue)]
    Q --> INV[Inventory Service]
    Q --> N[Notification Service]
    API --> CACHE[(Redis Cache)]
    API --> DB[(Product/Order DB)]
```

### 5. AI-powered application design

Problem:

- accept user input, run AI inference, and return results quickly
- support many concurrent requests
- handle model failures and retry logic

Key components:

- API gateway or reverse proxy
- app server and validation layer
- job queue for asynchronous inference tasks
- model serving layer or inference workers
- vector database or retrieval system for RAG
- cache for frequent responses
- logs and metrics for latency and accuracy

Main flow:

- user submits request with prompt or document
- app validates and stores metadata
- request goes to queue for inference
- worker loads model and performs inference
- output is stored and returned to the user or next system

Important considerations:

- prompt injection safeguards are essential
- model latency can be much higher than normal API calls
- asynchronous design helps under traffic spikes
- versioning and monitoring protect production reliability

Example flow:

```mermaid
flowchart LR
    U[User] --> API[API Layer]
    API --> Q[(Inference Queue)]
    Q --> W[AI Worker]
    W --> M[Model Server]
    M --> RES[Result Store]
    RES --> UI[User Response]
    W --> LOG[Metrics / Logs]
```

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

#### Round robin load balancer implementation

```java
import java.util.ArrayList;
import java.util.List;

class RoundRobinLoadBalancer {
    private final List<String> servers;
    private int index = 0;

    public RoundRobinLoadBalancer(List<String> servers) {
        this.servers = new ArrayList<>(servers);
    }

    public synchronized String nextServer() {
        String server = servers.get(index);
        index = (index + 1) % servers.size();
        return server;
    }

    public static void main(String[] args) {
        RoundRobinLoadBalancer lb = new RoundRobinLoadBalancer(
            List.of("server-1", "server-2", "server-3")
        );

        for (int i = 0; i < 6; i++) {
            System.out.println("Request " + i + " -> " + lb.nextServer());
        }
    }
}
```

These examples show the common pattern behind production throttling and traffic distribution: a small stateful algorithm that decides whether a request is accepted and where it should be routed.

## Authentication and authorization basics

### Authentication vs authorization

#### Authentication

Authentication answers the question: "Who are you?"

It verifies the identity of a user, service, or device.

Examples:

- login with username and password
- OAuth login via Google or GitHub
- API key validation
- certificate-based client validation

#### Authorization

Authorization answers the question: "What are you allowed to do?"

It decides whether an authenticated principal can access a resource or action.

Examples:

- user can read their own profile
- admin can delete a product
- service can call internal billing API

Important distinction:

- authentication verifies identity
- authorization checks permissions

A user can be authenticated but still not authorized for a specific action.

### JWT structure

JWT stands for JSON Web Token.

A JWT is a compact, stateless token used to carry identity and claims.

A JWT consists of three parts:

- Header
- Payload
- Signature

Format:

```text
<base64(header)>.<base64(payload)>.<base64(signature)>
```

#### Header

Typically contains:

- alg: signing algorithm, such as HS256 or RS256
- typ: token type, usually JWT

Example:

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

#### Payload

Contains claims about the user or token.

Common claims:

- sub: subject or user ID
- iss: issuer
- aud: audience
- exp: expiration time
- iat: issued-at time
- role: user role or permissions

Example:

```json
{
  "sub": "user_123",
  "role": "ADMIN",
  "iss": "myapp",
  "aud": "api",
  "exp": 1730000000
}
```

#### Signature

The signature is computed using the header, payload, and a secret or private key.

This ensures:

- integrity of the token
- detection of tampering
- trust when the server verifies it

JWTs are stateless, so the server can validate them without storing session data on the server side.

### OAuth flow

OAuth is an authorization framework that lets a user grant an application limited access to data on another service without sharing the password.

Typical OAuth flow:

1. User clicks "Login with Google"
2. App redirects user to the provider
3. User signs in and approves permissions
4. Provider redirects back with an authorization code
5. App exchanges the code for tokens
6. App uses access token to call protected APIs

Example flow:

```mermaid
flowchart LR
    U[User] --> A[App]
    A --> P[Authorization Server]
    P -->|grant consent| U
    U -->|redirect with code| A
    A -->|exchange code for tokens| P
    P -->|return access + refresh tokens| A
    A --> API[Protected API]
    API -->|validate token| A
```

The flow often includes:

- client ID
- client secret
- authorization endpoint
- token endpoint
- redirect URI
- scopes

Common scopes:

- read:user
- write:profile
- email
- admin

OAuth is often used with OpenID Connect (OIDC) for identity and login information.

### Access token vs refresh token

#### Access token

An access token is used to access protected resources.

Properties:

- short-lived
- sent in requests
- usually included in Authorization header
- used for API access and authorization checks

Example:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Refresh token

A refresh token is used to get a new access token without logging the user in again.

Properties:

- longer-lived than access token
- stored securely on the client or server
- exchanged when access token expires
- sensitive and should be protected carefully

Typical pattern:

- login -> get access + refresh tokens
- access token expires -> use refresh token to renew access token
- rotate refresh tokens when possible for better security

### Session-based auth

Session-based authentication stores user authentication state on the server.

Typical flow:

1. user logs in
2. server creates a session record
3. server stores session ID in a cookie
4. client sends the cookie with each request
5. server checks session store to validate the user

Pros:

- easy to revoke sessions
- server has direct control over login state
- useful for web apps with server-side rendering

Cons:

- server memory or database overhead
- requires session storage and scaling strategy
- sticky sessions or distributed session storage may be needed

Session-based auth is common in traditional monolithic web apps.

### Role-based access control (RBAC)

RBAC assigns permissions based on roles rather than individual users.

Examples of roles:

- USER
- ADMIN
- MODERATOR
- SUPPORT

Role-based rules:

- user can view their profile
- admin can delete records
- moderator can review flagged content

This is easier to manage than assigning permissions one-by-one to every user.

More advanced patterns include:

- permission-based access control
- attribute-based access control (ABAC)
- policy-based authorization

### Securing APIs

APIs need strong security controls at multiple layers.

Common protections:

- use HTTPS everywhere
- validate and sanitize all input
- use authentication and authorization
- avoid leaking sensitive data in responses
- rotate keys and tokens regularly
- store secret keys in environment variables or secret managers
- use rate limiting and request throttling
- log failed requests and access events
- use CSRF protection for browser-based apps
- validate tokens and payloads carefully

Good API security design should also include:

- least privilege access
- strong password hashing
- session or token expiration
- API gateway protections
- monitoring for suspicious patterns

### AI threat modeling

AI systems introduce new security and trust concerns.

Examples of threats:

- prompt injection
- data leakage from model outputs
- model poisoning during training
- misuse of generated content
- insecure API integration with model providers
- prompt-based bypass of application controls

Threat modeling for AI systems asks:

- what data does the model see?
- who can influence prompts or user input?
- what external systems are called by the model?
- how is sensitive data protected?
- what are the failure modes of generated outputs?

Examples of controls:

- validate model inputs and outputs
- restrict tool access for LLMs
- redact sensitive data before sending to external model APIs
- maintain audit logs of prompts and responses
- use a strict allowlist for allowed actions
- implement human review for critical decisions

### Interview perspective

For system design interviews, the key points are:

- authentication proves identity
- authorization decides allowed actions
- JWTs are compact, stateless, and signed tokens
- OAuth is a delegation protocol, not a user authentication mechanism by itself
- access tokens are short-lived and used for API calls
- refresh tokens are longer-lived and used to get new access tokens
- session auth is server-managed and common for web apps
- RBAC is a practical pattern for permission management
- API security requires defense in depth
- AI systems need specific threat analysis because model behavior can be manipulated or abused

### Nginx basics

Nginx is a popular reverse proxy and load balancer.

Common responsibilities:

- listen on port 80 or 443
- forward requests to app servers
- serve static files
- handle HTTPS certificates
- load balance between upstream backends

Basic upstream example:

```nginx
upstream app_servers {
    server 10.0.0.11:8080;
    server 10.0.0.12:8080;
    server 10.0.0.13:8080;
}

server {
    listen 80;
    server_name example.com;

    location / {
        proxy_pass http://app_servers;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

This configuration sends incoming traffic to backend app servers while keeping the application servers behind a single public entry point.

### Health checks

Health checks verify whether a backend instance is able to serve traffic.

A server is marked healthy only if it passes the check.

Common health check methods:

- HTTP GET to /health
- TCP connection check
- custom application-level verification

Examples:

- if /health returns 200, mark as healthy
- if the response is timeout or 500, route traffic away
- if a node fails repeatedly, remove it from the pool

Benefits:

- avoids sending requests to dead instances
- improves availability
- prevents cascading failures

Good health checks should include:

- timeout value
- retry count
- interval
- failure threshold
- graceful recovery behavior

### Auto scaling groups

An Auto Scaling Group (ASG) automatically adds or removes instances based on traffic and health.

Typical goals:

- handle traffic spikes automatically
- reduce cost during idle periods
- maintain enough healthy instances for availability

Key parts of scaling:

- minimum number of instances
- desired number of instances
- maximum number of instances
- scale-out policy when CPU, memory, or request rate rises
- scale-in policy when demand falls

Common triggers:

- CPU utilization > 70%
- application latency > threshold
- request rate > target
- queue length growing beyond a limit

ASGs are usually combined with:

- load balancers
- launch templates or machine images
- health checks
- alarms and metrics

This makes the system easier to scale without manual intervention.

### AI-assisted infrastructure planning

AI can help in early-stage architecture design by:

- generating possible system layouts
- suggesting scaling strategies
- identifying bottlenecks from traffic estimates
- comparing tradeoffs between SQL and NoSQL, caching, queues, and replication
- reviewing infrastructure-as-code templates
- assisting with incident response and troubleshooting steps

Example prompt:

- "Design a scalable e-commerce architecture for 1M daily users with 200ms p95 latency targets."

AI is strongest when used for:

- brainstorming architecture options
- summarizing tradeoffs
- producing initial drafts and checklists
- clarifying operational risk areas

AI is not a replacement for:

- capacity planning
- security review
- failure-mode analysis
- production operations judgment

The best approach is to use AI as a planning assistant, while engineers validate the architecture against real requirements, costs, and reliability constraints.

### Interview perspective

For interviews, explain these concepts clearly:

- L4 balancers are simpler and faster, but less aware of application logic.
- L7 balancers are smarter and more flexible, but heavier.
- Round robin is simple when servers are similar.
- Least connections works better when workloads differ.
- Reverse proxies protect and centralize traffic handling.
- Health checks are necessary for safe and automatic failover.
- Auto scaling is key for handling variable user load.
- AI helps draft architecture, but engineering judgment still decides the final design.

### What is system design?

System design is the process of turning a business requirement into a working architecture.

It answers questions like:

- What components do we need?
- How do they communicate?
- How does data flow?
- How is traffic handled at scale?
- How do we keep the system reliable and secure?

Example:

- For a ride-sharing app, system design includes user APIs, driver matching, payment, trip history, notifications, and real-time location updates.

### Why system design is needed

Without system design, developers can build code that works for a small demo but fails under real traffic or production issues.

System design is needed to make decisions about:

- performance under load
- fault tolerance
- data durability
- security
- cost and maintainability

Example:

- A single server can serve 10 users, but a system for 10 million users needs load balancers, caching, queueing, replication, and observability.

### Functional vs non-functional requirements

Functional requirements answer: what should the system do?

Examples:

- create order
- search products
- send message
- update profile

Non-functional requirements answer: how well should it do it?

Examples:

- low latency
- high availability
- strong security
- easy maintainability
- scalability

Example:

- A payment system must support payment creation (functional), but it must also be secure, idempotent, and available (non-functional).

### Scalability, availability, reliability, maintainability

#### Scalability

Scalability means the system can handle more users or more requests without a major redesign.

- vertical scaling: increase CPU/RAM on one machine
- horizontal scaling: add more machines behind a load balancer

#### Availability

Availability means the system is accessible when users need it.

It is often expressed as a percentage like 99.9% uptime.

#### Reliability

Reliability means the system works correctly and recovers from failures without data loss or broken behavior.

#### Maintainability

Maintainability means the system is easy to change, debug, and extend as requirements evolve.

Example:

- A chat system must be available and scalable, while a payment system must prioritize reliability and consistency.

### Vertical vs horizontal scaling

#### Vertical scaling

Add more power to the same machine.

Pros:

- simpler setup
- easier to manage initially

Cons:

- limited by machine capacity
- single point of failure
- expensive at large scale

#### Horizontal scaling

Add more machines and distribute traffic.

Pros:

- better scaling for large traffic
- higher availability
- more fault tolerance

Cons:

- complexity increases
- data consistency becomes harder
- load balancing and coordination are required

Example:

- A web app can start on one server, then scale horizontally by running 4 app instances behind a load balancer.

### CAP theorem

CAP theorem states that in a distributed system, you can usually guarantee only two of the three:

- Consistency
- Availability
- Partition tolerance

Meaning:

- if network partitions happen, you must choose between strong consistency and high availability
- many systems choose eventual consistency for internet-scale products

Example:

- A social feed can tolerate slightly stale data, so eventually consistent reads are acceptable.
- A payment system usually must be strongly consistent to avoid charging a user twice.

### High-level vs low-level design

#### High-level design (HLD)

HLD answers the architecture-level questions:

- what are the main components?
- how do services communicate?
- what databases and caches are used?
- how does traffic scale?

#### Low-level design (LLD)

LLD answers implementation-level questions:

- what classes or interfaces exist?
- how do entities relate?
- what workflows and validations are needed?

Example:

- For a URL shortener, HLD includes API gateway, service layer, database, and Redis cache. LLD includes URL entity, short-code generation logic, redirect logic, and validation rules.

### Estimating users, traffic, and storage

Good system design starts with rough capacity estimates.

Typical estimation flow:

1. estimate users and daily active users (DAU)
2. estimate requests per second (RPS)
3. estimate peak traffic
4. estimate storage needs based on payload size
5. plan retention, backups, and replicas

Example:

- If 2 million users each visit 5 times a day and each request is 2 KB, total read traffic can be estimated quickly and used to decide cache and database sizing.

### Using AI to convert requirements into architecture

AI can help by turning a plain requirement into:

- candidate architecture components
- service boundaries
- data models
- API design
- possible bottlenecks and tradeoffs

Example prompt:

- “Design a notification service for 50M users with email and SMS delivery, retries, and low latency.”

AI can suggest:

- API layer
- queue-based workers
- DB schema
- cache placement
- retry and deduplication strategy

### Drawing architecture diagrams with AI

AI can help generate architecture diagrams in text form, such as:

- component diagrams
- sequence diagrams
- deployment diagrams
- request-flow diagrams

Example:

- AI can draw the flow: client -> API Gateway -> Auth Service -> Order Service -> Payment Service -> DB -> Notification Queue

This is useful in interviews because the interviewer wants to see the system flow clearly and logically.

### Client-server architecture

Client-server architecture separates the responsibilities of:

- client: sends requests and renders results
- server: validates, processes, and responds

This is the standard pattern for web, mobile, and API systems.

Example:

- A browser sends an HTTP request to a backend server. The server checks the user, reads database data, and returns JSON.

### Monolithic vs microservices

#### Monolithic

All application logic is in one deployable unit.

Benefits:

- simple to build and deploy initially
- easier debugging for small systems

Drawbacks:

- harder to scale parts independently
- tightly coupled modules
- failures can affect the whole app

#### Microservices

An app is split into independent services.

Benefits:

- independent scaling
- team autonomy
- better fault isolation

Drawbacks:

- higher operational complexity
- network delays and failed calls
- more distributed system problems

Example:

- A monolith may have user, order, and payment code in one app; a microservice design splits them into separate services behind an API gateway.

### REST principles

REST stands for Representational State Transfer.

Core ideas:

- resources identified by URLs
- stateless communication
- standard HTTP methods
- representations such as JSON or XML

Example:

- `/users/123` is a resource representing a single user.

### HTTP methods and status codes

Common methods:

- GET: read data
- POST: create data
- PUT: full update
- PATCH: partial update
- DELETE: remove data

Common status codes:

- 200 OK
- 201 Created
- 204 No Content
- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found
- 409 Conflict
- 429 Too Many Requests
- 500 Internal Server Error

Example:

- POST `/orders` returns 201 Created when the order is successfully accepted.

### API versioning

API versioning helps maintain backward compatibility as the API evolves.

Common methods:

- URL versioning: `/v1/users`
- header versioning: `Accept: application/vnd.company.v1+json`
- query parameter versioning: `/users?version=1`

Example:

- When you add a new field or change behavior, versioning prevents breaking existing clients.

### Idempotency

Idempotency means repeating the same request has the same effect as doing it once.

Important for:

- payment retries
- network timeouts
- duplicate user clicks

Example:

- If a client retries `POST /payments` due to timeout, the server should not charge twice if the request already succeeded.

### Designing RESTful endpoints

A good endpoint design is:

- resource-oriented
- predictable
- consistent
- secure
- easy to document

Examples:

- `GET /users/123`
- `POST /users`
- `GET /orders/456/items`
- `DELETE /sessions/abc`

Example answer in interviews:

- “I would expose resource-based APIs and keep the business logic in services, not in the controller layer itself.”

### Request/response lifecycle

A typical API lifecycle is:

1. client sends HTTP request
2. load balancer routes it to an app instance
3. API layer validates input
4. service layer applies business rules
5. database or cache is accessed
6. response is returned to the client

Example:

- A user opens the profile page; the API validates token, loads user data, checks cache, then returns JSON.

### Using AI to generate API specs

AI can help create:

- OpenAPI/Swagger specs
- request/response payloads
- validation rules
- error payload structures
- endpoint naming conventions

Example:

- “Generate a REST API spec for a task management service with endpoints for creating tasks, listing tasks, and updating status.”

### Database fundamentals

Databases are where the application stores durable data.

#### Relational vs NoSQL databases

Relational databases use structured tables and relationships.

Best for:

- transactions
- strong consistency
- relational queries

Examples:

- PostgreSQL
- MySQL
- SQL Server

NoSQL databases focus on flexible schemas and scale.

Best for:

- unstructured data
- high write throughput
- large-scale analytics

Examples:

- MongoDB
- Cassandra
- Redis
- DynamoDB

Example:

- A banking app usually uses a relational database; a chat history system may use a NoSQL store or hybrid storage design.

#### Entity relationship modeling

This is the process of identifying entities and their relationships.

Example:

- `User` has many `Orders`
- `Order` contains many `OrderItems`
- `Product` belongs to a `Category`

#### Normalization and denormalization

Normalization reduces duplication and preserves correctness.

Denormalization intentionally duplicates data to improve read performance.

Example:

- In an e-commerce app, product details may be normalized into multiple tables, but search results may be denormalized for faster reads.

#### Primary and foreign keys

- primary key uniquely identifies each row
- foreign key references a row in another table

Example:

- `user_id` in `Orders` is a foreign key to `Users.id`.

#### Indexing concepts

Indexes improve read performance by speeding up lookup, sort, and join operations.

Tradeoff:

- faster reads
- slower writes
- more storage usage

Example:

- Adding an index on `email` helps look up users by email much faster.

#### Query optimization basics

Query optimization focuses on reducing expensive database work.

Common ideas:

- avoid full-table scans where possible
- add indexes on frequent query columns
- reduce unnecessary joins
- paginate large result sets
- use projections instead of selecting all columns

Example:

- Instead of selecting all columns from a large table, fetch only the fields required for the page.

#### Using AI to suggest schema improvements

AI can review a schema and suggest:

- missing indexes
- redundant columns
- better relationships
- table splitting
- denormalization opportunities
- query-level improvements

Example prompt:

- “Review this e-commerce schema and suggest improvements for high-read product search queries.”

### Caching fundamentals

Caching stores frequently accessed data closer to the user or service to reduce latency and database load.

#### Cache-aside pattern

The application checks cache first; if data is missing, it fetches from the database and stores it in cache.

Example:

- User profile data is read from the cache when hot, else loaded from the DB and cached.

#### Write-through vs write-back

##### Write-through

Data is written to cache and database together.

Pros:

- simpler consistency model
- cache stays up to date

Cons:

- slower writes

##### Write-back

Data is written to cache first and later flushed to the database.

Pros:

- faster writes
- good for bursty workloads

Cons:

- risk of data loss during failure
- more complex coordination

#### TTL and eviction policies

TTL (time-to-live) controls how long cached data stays valid.

Common eviction policies:

- LRU: least recently used
- LFU: least frequently used
- FIFO: oldest entries removed first

Example:

- A cache for top products may use a 5-minute TTL and LRU eviction.

### Redis caching

Redis is an in-memory data store commonly used for:

- session storage
- hot key caching
- rate limiting
- leaderboard data
- pub/sub messaging

Why Redis is useful:

- very fast reads/writes
- simple key-value model
- supports expiration and eviction

Example:

- A URL shortener can store the mapping `short_code -> long_url` in Redis so redirection is fast and the database is protected from hot-key reads.

### CDN working

A CDN caches static or semi-static content on geographically distributed edge servers closer to users.

It helps with:

- lower latency
- reduced origin server load
- better user experience for global traffic

Examples of CDN content:

- images
- JavaScript bundles
- CSS files
- videos
- static HTML pages

Example:

- A global e-commerce site uses a CDN to serve product images from edge locations instead of fetching them from the origin server every time.

### Role-based access control (RBAC)

RBAC is a security model where permissions are assigned to roles, and roles are assigned to users.

Example roles:

- admin
- editor
- viewer
- support

Benefits:

- easier permission management
- least-privilege enforcement
- cleaner auditing and governance

Example:

- An admin can delete users, while a viewer can only read dashboards.

### Using AI to place cache layers optimally

AI can help decide where to put caches based on access patterns.

Typical placement decisions:

- browser cache for static assets
- CDN for global static content
- Redis for hot application data
- DB-level cache for repeated queries

Example:

- If a product catalog is read heavily, put Redis in front of the database and a CDN in front of product images.

## System design fundamentals cheat sheet

### 1. System design basics

- System design turns requirements into scalable architecture.
- Start with scope, traffic assumptions, and bottlenecks.
- Design for failure, not only success.

Interview answer:

- “I would start by identifying the critical path, core entities, and major read/write flows before choosing the architecture.”

### 2. Functional vs non-functional requirements

- Functional = what the system does.
- Non-functional = how it behaves under load or failure.

Examples:

- Functional: create order, send message, update profile
- Non-functional: low latency, high availability, strong security, easy scaling

### 3. Scalability and capacity

- Vertical scaling = bigger machine
- Horizontal scaling = more machines
- Use horizontal scaling for user growth and redundancy

Rule of thumb:

- design for peak load, not only average load

### 4. CAP theorem

- You cannot get full consistency, availability, and partition tolerance at the same time.
- Tradeoff depends on business needs.

Examples:

- Payment system: prioritize consistency
- Social feed: prioritize availability and eventual consistency

### 5. HLD vs LLD

- HLD = architecture, services, data flow, scale
- LLD = classes, methods, validation, status transitions

Interview answer:

- “HLD focuses on the system as a whole; LLD focuses on the internal design of one feature.”

### 6. Client-server and REST

- Client sends request
- Server validates, processes, and returns response
- REST uses resources, standard HTTP methods, and stateless interactions

Common endpoints:

- `GET /users/123`
- `POST /users`
- `PUT /orders/456`
- `DELETE /sessions/abc`

### 7. HTTP status codes and API behavior

- 200 OK: success
- 201 Created: resource created
- 400 Bad Request: invalid request
- 401 Unauthorized: user not authenticated
- 403 Forbidden: forbidden action
- 404 Not Found: missing resource
- 409 Conflict: business conflict
- 429 Too Many Requests: rate limit hit
- 500 Server Error: internal failure

### 8. Idempotency and retries

- Idempotent APIs can safely be retried without duplicate side effects.
- Most critical in payments, search submissions, and event-driven flows.

Example:

- Payment retry should not double charge a user.

### 9. Databases

- SQL: structured, transactional, strongly consistent
- NoSQL: flexible, scalable, good for large writes or unstructured data

Examples:

- Use PostgreSQL for transactions and orders
- Use MongoDB or Cassandra for flexible and high-scale data access

### 10. Indexes and query optimization

- Indexes improve read speed
- Too many indexes hurt write performance
- Always optimize hot queries, not just the schema

Example:

- Add index on `email` for user lookup

### 11. Caching

- Cache hot reads and reduce database pressure
- Use cache-aside for web apps and APIs
- Common policies: TTL, LRU, LFU

Example:

- Cache top products for 5 minutes to reduce repeated reads.

### 12. Redis and CDN

- Redis: in-memory cache for hot data, rankings, sessions, and rate limiting
- CDN: caches static content closer to users

Example:

- Product images served via CDN, user profile data served from Redis, database as source of truth.

### 13. RBAC and security

- RBAC assigns permissions via roles
- Keeps access controlled and auditable

Example:

- Admin can manage users; viewer can only read reports

### 14. Reliability and failure handling

- Add retries, timeouts, queueing, and graceful degradation
- Use async processing for non-critical work

Example:

- Send email after order creation via message queue, not inline in the request path.

### 15. Interview formula

Use this structure in most interviews:

1. Clarify requirements and scope
2. State assumptions
3. List functional and non-functional requirements
4. Define main components
5. Describe request flow
6. Explain data model and storage choices
7. Add caching and scaling strategy
8. Discuss failure handling and tradeoffs
9. Mention optimizations and next steps

### 16. Common interview phrases

- “I would start by identifying the bottleneck.”
- “I would separate synchronous critical work from asynchronous follow-up work.”
- “This design is optimized for scale, but it introduces eventual consistency.”
- “I would add caching only after validating the actual access pattern.”
- “The database is the source of truth, while cache is for speed.”

### Consistency

Consistency means all readers see the same latest state after writes, depending on the chosen model.

Common models:

- strong consistency
- eventual consistency
- causal consistency

## Design pattern categories

### Creational

- Factory
- Abstract Factory
- Builder
- Singleton
- Prototype

### Structural

- Adapter
- Decorator
- Facade
- Proxy
- Composite
- Bridge

### Behavioral

- Strategy
- Observer
- Command
- State
- Template Method
- Chain of Responsibility

## Example files

| File                                                                         | Purpose                                                                  |
| ---------------------------------------------------------------------------- | ------------------------------------------------------------------------ |
| [DesignPatternsExample.java](DesignPatternsExample.java)                     | Strategy pattern example                                                 |
| [FactoryPatternExample.java](FactoryPatternExample.java)                     | Factory pattern example                                                  |
| [ParkingLotLLDExample.java](ParkingLotLLDExample.java)                       | LLD example for parking lot flow and slot allocation                     |
| [HotelBookingLLDExample.java](HotelBookingLLDExample.java)                   | LLD example for room booking, validation, and overlap protection         |
| [EcommerceSystemHLDExample.java](EcommerceSystemHLDExample.java)             | HLD example covering gateway, services, payment, and async notifications |
| [ChatSystemHLDExample.java](ChatSystemHLDExample.java)                       | HLD example for real-time messaging, presence, and offline delivery      |
| [NotificationPipelineHLDExample.java](NotificationPipelineHLDExample.java)   | HLD example for async notification pipelines and retry safety            |
| [URLShortenerHLDExample.java](URLShortenerHLDExample.java)                   | HLD example showing redirect service design and cache bottlenecks        |
| [SystemDesignFundamentalsExample.java](SystemDesignFundamentalsExample.java) | Demo for RBAC, caching, API flow, and database-source-of-truth design    |

## Interview prep docs

- [InterviewQnA.md](InterviewQnA.md) — common system design questions with model answers
- [SystemDesignDiagrams.md](SystemDesignDiagrams.md) — component and sequence diagram notes

## Interview checklist

- Explain when to use each pattern
- Compare factory vs builder vs singleton
- Distinguish LLD from HLD
- Discuss tradeoffs like availability, consistency, and scaling
- Mention assumptions, bottlenecks, and failure scenarios
- Explain class responsibilities, service boundaries, and repository design
- Discuss state transitions, validations, retries, and idempotency
- Describe how a system behaves under high load and partial failure

## Suggested learning path

1. Learn the common pattern groups.
2. Practice LLD for one feature at a time.
3. Learn HLD by designing full systems end-to-end.
4. Practice explaining tradeoffs clearly in interviews.

## System design roadmap for interviews

### Step 1: Get familiar with OOP basics

Why it matters:

- LLD and system design always start from modeling real-world entities and responsibilities.
- If your class boundaries are weak, the rest of the design will also be weak.

Topics to learn:

- Classes and objects
- Polymorphism
- Inheritance
- Abstraction
- Encapsulation

Example:

- In a `ParkingLot` design, a `Vehicle` is an object, a `ParkingSlot` is an entity, and a `ParkingLotService` is the business logic layer.

### Step 2: Learn design principles

Why it matters:

- Good design is not only about code working; it is about code being maintainable, extendable, and clean.

Focus on these principles:

- SOLID
  - Single Responsibility
  - Open to Extension and Closed to Modification
  - Liskov Substitution
  - Interface Segregation
  - Dependency Inversion
- DRY
- KISS
- YAGNI
- GRASP

Example:

- In a payment system, `PaymentService` should handle payment processing, while a separate `InvoiceService` should handle invoices. This follows Single Responsibility.

### Step 3: Understand UML and modeling

Why it matters:

- Interviewers often expect you to explain relationships and interaction flows clearly.

Core relationships to know:

- Generalization (Is-A)
- Association (Has-A)
- Aggregation
- Composition
- Dependency
- Multiplicity

Then learn:

- Class diagrams
- Sequence diagrams

Example:

- In a ride-sharing system, `Driver` has a `Vehicle`, and `Ride` is associated with both `User` and `Driver`. A sequence diagram would show: request ride -> match driver -> accept -> start trip -> complete -> charge.

### Step 4: Learn design patterns

Why it matters:

- Design patterns teach reusable solutions for recurring problems in code and architecture.

#### Creational patterns

- Factory
- Abstract Factory
- Singleton
- Builder

Example:

- `VehicleFactory` creates `Car`, `Bike`, or `Truck` depending on the input type.

#### Structural patterns

- Adapter
- Proxy
- Decorator
- Composite
- Bridge
- Facade
- Flyweight

Example:

- A `PaymentGatewayAdapter` can adapt a legacy gateway to a new interface without changing the app code.

#### Behavioral patterns

- Observer
- Strategy
- Chain of Responsibility
- Iterator
- State
- Command
- Template Method

Example:

- A `PaymentStrategy` interface can have `CreditCardPayment`, `UPIPayment`, and `WalletPayment` implementations. The choice can change at runtime.

### Step 5: Practice LLD problems

Why it matters:

- LLD trains you to design a feature deeply, not just name the classes.

#### Level I

- Design Parking Lot
- Design a Vending Machine
- Design Stack Overflow
- Design Logging Framework
- Design Coffee Vending Machine
- Design Traffic Signal Control System
- Design a Task Management System

Example:

- For `ParkingLot`, define `Vehicle`, `ParkingSlot`, `ParkingTicket`, and `ParkingLotService`; then explain how slots are allocated and how invalid requests are rejected.

#### Level II

- Design Pub/Sub System
- Design Tic-Tac-Toe Game
- Design Car Rental System
- Design an ATM
- Design Hotel Management System
- Design LinkedIn
- Design an Elevator
- Design an Airline Management System
- Design a Digital Wallet System
- Design an Online Auction System
- Design a Cache using LRU Eviction Policy
- Design a Concert Ticket Booking System

Example:

- For `HotelBookingSystem`, consider `Guest`, `Room`, and `Booking`; add date-overlap checks to prevent double booking.

#### Level III

- Design Movie Ticket Booking System
- Design Splitwise
- Design Snake and Ladder game
- Design Online Shopping System like Amazon
- Design Online Stock Brokerage System
- Design CricInfo
- Design Chess Game
- Design Ride-Sharing Service like Uber
- Design Online Food Delivery Service like Swiggy
- Design Music Streaming Service like Spotify
- Design University Course Registration System

Example:

- For `Splitwise`, define `User`, `Expense`, `Group`, and `Settlement`, and explain how balances are computed and settled.

### Step 6: Practice system design fundamentals

Why it matters:

- Before solving huge systems, you must understand the core vocabulary of distributed systems.

Start by covering the basics terms:

- Latency
- Throughput
- Horizontal vs vertical scaling
- Redundancy and replication
- Load balancer
- CDN
- CAP theorem
- Caching and cache eviction
- RDBMS vs NoSQL
- SQL vs NoSQL tradeoffs
- Indexing
- Synchronous vs asynchronous communication
- REST APIs
- Authentication vs authorization
- Forward proxy vs reverse proxy

Example:

- For a URL shortener, redirect traffic is read-heavy, so you use a cache like Redis in front of the database.

### Step 7: Deepen distributed systems knowledge

Why it matters:

- Once your basics are solid, you start thinking in terms of scale, consistency, and failure handling.

Topics to cover once basics are comfortable:

- Replication
- Active vs passive replication
- Single leader vs multi-leader
- Leaderless systems and quorum
- Transactions and isolation levels
- Partitioning and sharding
- Consistent hashing
- Zookeeper / coordination services
- MySQL vs PostgreSQL
- MongoDB, Cassandra, HBase basics
- Stream processing and message brokers
- Delivery guarantees
- Search systems and indexing
- Redis and Memcached
- HTTP, TCP, UDP, WebSockets, SSE
- Layer 3, Layer 4, Layer 7 concepts

Example:

- In a chat system, messages should be persisted and delivered asynchronously if the user is offline, while active users can receive messages in real time over WebSockets.

### Step 8: Practice high-level design questions

Why it matters:

- You need to design whole systems, not just modules.

Common system design interview problems:

- TicketMaster / BookMyShow
- Uber
- Dropbox / Google Drive
- Twitter
- URL Shortener
- Web Crawler
- Top K YouTube videos
- WhatsApp
- YouTube
- Ad Click Aggregator
- Tinder
- Facebook Live Comments
- Facebook News Feed
- Post Search
- Distributed Rate Limiter

Example:

- For `URL Shortener`, explain: API layer, service layer, mapping store, Redis cache, and analytics pipeline; then talk about hot keys, rate limiting, and redirect traffic.

### Interview answer structure

For both LLD and HLD, use this flow:

1. Clarify requirements
2. State assumptions
3. Define entities or components
4. Explain the core workflow
5. Discuss data models and storage choices
6. Describe scaling and bottlenecks
7. Talk about consistency, failover, retries, and tradeoffs
8. Close with improvement ideas

Example:

- For a notification system, you would say: “I would use an API to accept requests, enqueue jobs in a queue, and let workers deliver through email/SMS/push. This isolates slow providers and prevents the request path from being blocked.”

### Suggestions for practice

- Pick one design problem and answer it every day.
- Practice by speaking out loud instead of writing silently.
- Draw class diagrams and sequence diagrams.
- Focus on tradeoffs, not just the final architecture.
- Explain failure handling clearly: retries, timeouts, queues, caches, and redundancy.

Example:

- When asked about payment systems, say: “I would prioritize consistency and idempotency because duplicate charges are more damaging than a short outage.”

### Good resources to follow

- System design playlist resources mentioned in the learning plan
- Design patterns course content focusing on LLD and OOP
- Practice with real interview questions only after understanding the fundamentals

Example:

- Use one focused video/course for theory, then immediately design a system with your own words to test retention.

## Learning content to study deeply

### 1. OOP and design fundamentals

This is the base before patterns and architecture.

Study:

- classes, objects, and responsibilities
- abstraction and interfaces
- inheritance vs composition
- encapsulation and data hiding
- dependency injection and loose coupling
- cohesion vs coupling

Why it matters:

- strong object modeling makes LLD easier
- clean boundaries help you design scalable services later

### 2. Design principles

These are the rules that keep code maintainable.

Must know:

- SOLID
  - Single Responsibility
  - Open/Closed Principle
  - Liskov Substitution
  - Interface Segregation
  - Dependency Inversion
- DRY
- KISS
- YAGNI
- GRASP fundamentals

Interview angle:

- explain why a design is maintainable and extensible
- show that you can avoid over-engineering

### 3. UML and modeling skills

Before drawing full systems, be confident with diagrams.

Learn:

- class diagrams
- sequence diagrams
- inheritance relationships
- association, aggregation, composition
- dependency and multiplicity

What to say in interviews:

- “I would model the entities and their relationships first.”
- “Then I would show the interaction sequence between the caller and the services.”

### 4. Design patterns by category

#### Creational

- Factory
- Abstract Factory
- Builder
- Singleton
- Prototype

#### Structural

- Adapter
- Decorator
- Facade
- Proxy
- Composite
- Bridge
- Flyweight

#### Behavioral

- Strategy
- Observer
- Command
- State
- Template Method
- Chain of Responsibility
- Iterator

Mental model:

- Creational = how objects are made
- Structural = how objects fit together
- Behavioral = how objects interact and collaborate

### 5. LLD skill set

A good LLD answer is not just a list of classes. It should show good engineering judgment.

Always define:

- entities and their attributes
- services and responsibilities
- repositories or storage access
- validation rules and error handling
- status transitions and workflow
- concurrency and race conditions
- idempotency and retry logic

Common LLD design patterns:

- domain entity modeling
- service orchestration
- repository abstraction
- state-driven workflows
- validation and business rules

### 6. HLD skill set

At HLD level, you are designing the system as a whole.

Focus on:

- functional requirements vs non-functional requirements
- client-server architecture
- API gateways and service boundaries
- database choices and tradeoffs
- caching and cache invalidation
- load balancing and horizontal scaling
- message queues and async workers
- CAP tradeoffs and consistency models
- observability and operational concerns

Good HLD answers usually include:

- throughput assumptions
- read-heavy vs write-heavy workload
- latency concerns
- bottleneck identification
- failure handling strategy

### 7. Core system design building blocks

Learn these terms well:

- stateless vs stateful components
- vertical vs horizontal scaling
- load balancers and reverse proxies
- read replicas and leader-follower patterns
- sharding and partitioning
- cache aside, write-through, write-back
- message queues and event-driven systems
- rate limiting and throttling
- circuit breakers and retries
- timeouts and backoff
- dead-letter queues

### 8. Interview answer formula

Use this structure in almost every design interview:

1. Clarify the problem and scope
2. State assumptions
3. List functional and non-functional requirements
4. Define major components and services
5. Explain the flow of the request
6. Identify storage and caching choices
7. Discuss bottlenecks and scaling strategy
8. Explain failure handling and tradeoffs
9. End with what you would optimize next

### 9. Examples to master

Practice these repeatedly:

- Parking Lot
- Hotel Booking System
- Ride Sharing Service
- Splitwise
- Payment System
- Chat System
- Notification Pipeline
- URL Shortener
- E-commerce Platform
- Social Feed
- Online Food Delivery
- Movie Ticket Booking System

### 10. What an experienced engineer should sound like

A strong answer sounds like this:

- “I would first identify the critical path and the system bottleneck.”
- “The most important requirement here is consistency for payment, not raw speed.”
- “I would separate synchronous critical work from async follow-up work.”
- “This design is good for scale, but it introduces eventual consistency for non-critical operations.”
- “I would add caching only after validating the actual access pattern and hotspot.”

## Final takeaway

The real goal is to build intuition for:

- how systems are decomposed
- how components communicate
- where bottlenecks occur
- how tradeoffs shape the final design

When you can explain a design clearly, justify the tradeoffs, and handle failure scenarios, you are already thinking at a strong engineering level.
