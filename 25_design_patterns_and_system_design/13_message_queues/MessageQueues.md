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
