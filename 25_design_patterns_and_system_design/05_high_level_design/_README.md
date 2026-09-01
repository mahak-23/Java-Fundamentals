# High-Level Design (HLD)

High-level design focuses on the architecture of a whole system.

It answers questions like:
- what are the major components?
- how do services communicate?
- how is data stored and scaled?
- what are the bottlenecks, tradeoffs, and failure modes?
- what happens under peak traffic or partial outages?

## HLD concerns

### 1. Functional requirements (FR)

What is the system expected to do?

Examples:
- create orders
- push notifications
- search documents
- serve user profile data
- reserve a hotel room
- process a payment

### 2. Non-functional requirements (NFR)

What matters beyond correctness?

Examples:
- scalability
- availability
- latency
- consistency
- fault tolerance
- security
- throughput
- observability

### 3. CAP and distributed tradeoffs

In distributed systems, CAP is a key design principle:

- Consistency: every read sees the latest committed value
- Availability: every request receives a response
- Partition tolerance: the system works despite network failures

A system usually chooses two of the three, and the decision depends on the product requirement.

Examples:
- banking: favor consistency and availability over partition tolerance in a controlled environment
- social feed: often favor availability and partition tolerance with eventual consistency
- payment system: choose strong consistency for critical stock or account updates

### 4. Load balancer

A load balancer distributes traffic across multiple instances to reduce overload and improve uptime.

Common use cases:
- web servers
- application servers
- APIs behind a gateway

Typical algorithms:
- round robin
- least connections
- weighted balancing
- IP-based hashing

### 5. Client-server (CS) model

A client sends a request and the server responds with data or actions.

This is the foundation of most backend systems, APIs, and web applications.

Examples:
- mobile app -> backend API -> database
- browser -> web server -> application service -> cache

### 6. System components

A typical HLD includes:
- clients
- API gateway
- application services
- databases
- caches
- message brokers
- workers
- observability and monitoring
- auth and security services

## Example: URL Shortener HLD

### Problem
Create a service like TinyURL that converts long URLs into short links and redirects users.

### Requirements
- generate a unique short code
- redirect quickly
- support high read traffic
- keep analytics for clicks

### Components
- API layer for create/read requests
- URL service to generate and validate codes
- database for original URL + metadata
- cache for hot short links
- analytics pipeline for click tracking

### Storage choice
- primary DB: relational DB or key-value store for link mapping
- cache: Redis for hot links and redirect lookups
- sharding: split by hash or ID range if traffic grows large

### Tradeoffs
- read-heavy workload -> strong caching helps
- unique ID generation may use sequence IDs or base62 encoding
- storing analytics in a separate stream or event-based collector avoids slowing redirects

### Failure handling
- DB failover and retries
- cache misses handled by DB fallback
- rate limiting to stop abuse

### How to answer in an interview

1. Clarify the product requirement.
   - short link creation, redirect, analytics, and scale expectations
2. Define the traffic profile.
   - redirects are much more frequent than link creation
3. Explain component boundaries.
   - API, service, storage, cache, analytics stream
4. Discuss bottlenecks.
   - hot links, DB load, cache misses, analytics overhead
5. Close with scale plan.
   - sharding, cache warming, rate limiting, idempotent short-code generation

### Questions to ask the interviewer
- Is the model centralized or global across regions?
- Do we need custom short URLs or only generated codes?
- How much analytics detail is required?
- Is expiration or link validity a requirement?

## Example: Chat System HLD

### Problem
Design a messaging platform where users send and receive messages in near real time.

### Components
- user auth and presence service
- message API
- chat service
- notification service
- message store
- WebSocket or polling layer
- cache for recent conversations
- queue for async processing

### Data flow
- user sends message via client
- app service writes to DB or message queue
- event is pushed to target user via WebSocket
- notification system sends push/email if offline

### Important concerns
- single chat room vs conversation list
- read receipts and delivery status
- message ordering
- offline message sync
- indexing by conversation and timestamps

### Scaling strategy
- shard conversations by user or chat ID
- use cache for recent messages
- use async workers for notifications and analytics
- use message brokers to decouple write and delivery pipeline

## Example: E-commerce HLD

### Problem
Design an online shopping platform with product catalog, cart, order placement, and payment.

### Components
- product service
- cart service
- order service
- payment service
- inventory service
- recommendation service
- search and catalog layer
- notification service

### Core flows
- user browses catalog
- adds items to cart
- places order
- inventory is reserved
- payment is authorized
- order is created and notifications are sent

### Critical concerns
- inventory consistency
- payment rollback and retries
- concurrent stock updates
- order idempotency
- heavy catalog reads and search indexing

### Design tradeoffs
- strong consistency for inventory and payment may reduce system availability under failures
- eventual consistency may be acceptable for recommendations or analytics
- asynchronous workflows are important for order events and notifications

### How to answer in an interview

1. Outline the user journey and service boundaries.
   - catalog, cart, order, payment, inventory
2. Rank critical workflows.
   - payment and inventory are the highest risk paths
3. Discuss consistency requirements.
   - strong consistency for stock and payment; eventual for analytics and recommendations
4. Explain failure handling.
   - rollback, retries, queue-based outbox pattern, retries for payment events
5. End with scale concerns.
   - catalog reads scale horizontally; stock and order writes are busiest critical points

### Questions to ask the interviewer
- Is this a marketplace or a single-seller platform?
- Do we need support for coupons, wallets, and refunds?
- Is inventory per SKU or per warehouse?
- Is the system read-heavy, write-heavy, or balanced?

## Example: Notification System HLD

### Problem
Send emails, SMS, and push notifications to users reliably.

### Components
- API to enqueue notifications
- queue for pending notifications
- worker pool for delivery
- provider adapters for email/SMS/push
- retry and dead-letter queue
- monitoring and retry metrics

### Why this architecture works
- decouples request handling from slow external delivery systems
- allows retries without blocking user requests
- protects the app from provider outages

### Design decisions
- use retry backoff for rate-limited providers
- deduplicate repeated requests
- prioritize critical user events

## Example: Social Feed HLD

### Problem
Serve personalized feeds with millions of posts and reads.

### Components
- user graph service
- feed generation service
- cache layer
- message queue
- storage for posts and relationships

### Typical strategy
- write fan-out to followers or generate feed on read depending on product needs
- use cache for hot feeds
- separate feed generation from post creation
- use index and precomputation for fast retrieval

### Tradeoff
- fan-out-on-write is faster for reads but more expensive for writes
- fan-out-on-read is cheaper for writes but slower and more expensive on read

### How to answer in an interview

1. Start with product usage pattern.
   - read-heavy feed, user graph, ranking, personalization
2. Choose fan-out strategy.
   - fan-out-on-write for popular accounts; fan-out-on-read for low-scale or sparse traffic
3. Explain storage and cache strategy.
   - post table, relationship graph, feed cache, ranking service
4. Discuss latency and scale.
   - caches, precomputation, scheduler, retries for feed updates
5. Mention failure modes.
   - inconsistent feed ordering, stale caches, delayed fan-out

### Questions to ask the interviewer
- Are there hot influencers or mostly ordinary traffic?
- Is feed ranking important for each user?
- Does the product require real-time or near-real-time updates?
- Are we optimizing for write or read performance?

## Data flow patterns

The system design usually includes flows like:
- client -> API gateway -> service -> DB
- client -> queue -> worker -> downstream service
- request -> cache -> DB fallback
- user event -> stream -> analytics + notification pipeline

## Bottleneck analysis

Also discuss:
- database read/write load
- hot partitions
- network bottlenecks
- timeouts and retries
- sharding, replication, and load balancing
- cache stampede and eviction issues
- queue backpressure

## Scaling strategies

### Vertical scaling
Increase resources on the same machine.

Good for:
- small systems
- early-stage products

### Horizontal scaling
Add more machines behind a load balancer.

Good for:
- high traffic systems
- stateless services
- read-heavy or write-split workloads

### Caching
- Redis or in-memory cache for hot data
- CDN for static content
- application cache for frequent reads

### Replication
- read replicas for read-heavy workloads
- leader/follower or primary-secondary setup

### Sharding
Split data across multiple DB instances to reduce hotspots and increase capacity.

## Reliability patterns

- retry with exponential backoff
- timeouts and circuit breakers
- queue-based async processing
- idempotent APIs
- dead-letter queues
- backups and restore procedures
- health checks and observability

## Typical HLD answer structure

1. Requirements and assumptions
2. High-level components
3. Data model and storage choice
4. APIs and communication patterns
5. Scaling strategy
6. Reliability and failure handling
7. Tradeoffs and estimation
8. Bottlenecks and monitoring

## Interview-level HLD checklist

- What are the FR and NFR?
- Which system components exist and why?
- Where is the data stored and why?
- Which parts are synchronous and which are asynchronous?
- Where are the bottlenecks?
- How does the design handle failures?
- What takes priority: consistency, availability, or partition tolerance?
- How will the system scale to 10x, 100x traffic?
- What is the key tradeoff in this design?

## Important concepts

- horizontal scaling vs vertical scaling
- load balancing
- caching strategies
- read replicas
- master-slave or leader-follower patterns
- queues and async workers
- CAP tradeoffs
- partitioning and sharding
- rate limiting, quotas, and circuit breakers
- observability and metrics

## Key takeaway

HLD is about choosing the right architectural shape for the system, not just implementing feature logic. A strong answer proves that you understand traffic patterns, bottlenecks, failure modes, and business constraints.
