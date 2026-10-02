# System Design Interview Q&A Sheet

This file is meant to help you practice answering system design questions in a structured and interview-friendly way.

## 1. How do you start a system design interview?

A strong answer starts with a brief summary of the problem, key requirements, and system boundaries.

Example answer:
- We need to design a notification system for user updates.
- Functional requirements: send messages, track delivery status, support retries.
- Non-functional requirements: low latency, durability, retry safety, scale for large traffic.
- We will assume the system is asynchronous and must handle provider outages gracefully.

## 2. What are the most important questions to ask first?

- What is the expected scale?
- Is the system read-heavy or write-heavy?
- Do we need strong consistency or eventual consistency?
- Are there latency requirements?
- What are the critical APIs?
- Does the system need retries, deduplication, or audit logs?

## 3. How would you answer a URL shortener design question?

Model answer:
- Use a public API for creating and resolving short links.
- Store original URL and short code in a database.
- Use a hash or base62 generator to create short codes.
- Use Redis for hot link lookups.
- Separately send analytics events to a queue for tracking.
- Apply rate limiting and retry logic.
- Scale by sharding the mapping table and caching hot links.

## 4. How would you answer a chat system design question?

Model answer:
- Break the system into clients, gateway, chat services, message store, presence service, notification service, and event bus.
- Persist messages in a database or message store with conversation indexing.
- Use WebSockets for active users and push notifications for offline users.
- Treat delivery and notification as asynchronous work.
- Use caching for recent messages and conversation list.
- Handle ordering, read receipts, and message retries.

## 5. How would you answer an e-commerce system design question?

Model answer:
- Identify the major services: catalog, cart, order, payment, inventory, and notifications.
- Use synchronous calls for critical flows like payment authorization and stock reservation.
- Use async events for notifications and analytics.
- Use strong consistency for stock and payment decisions, but eventual consistency is acceptable for recommendations.
- Add idempotency keys for payment and order requests.
- Use queues to handle spikes without blocking user requests.

## 6. How would you answer a hotel booking design question?

Model answer:
- Define `Guest`, `Room`, and `Booking` as entities.
- Validate check-in and check-out dates before booking.
- Search for rooms of the requested type.
- Reject bookings that overlap with already confirmed bookings.
- Keep booking status transitions clear: confirmed, cancelled, completed.
- Use a repository or store for bookings and a service for logic and conflict detection.

## 7. What is the best way to explain tradeoffs?

Use this structure:
- State the requirement.
- Explain the tradeoff.
- Give a real-world example.
- Explain the chosen decision.

Example:
- For payment systems, we prefer strong consistency over availability because a duplicate or failed charge is more damaging than short downtime.

## 8. What should you say about CAP theorem?

A good answer:
- In a distributed system, we cannot optimize for all three simultaneously.
- For a payment system, we may prioritize consistency and partition tolerance while accepting limited availability during partial outages.
- For a social feed or notification system, we may prioritize availability and partition tolerance while accepting eventual consistency.

## 9. How do you talk about failure handling?

Mention:
- retries with backoff
- timeouts
- idempotent APIs
- circuit breakers
- dead-letter queues
- monitoring and alerts
- fallback strategies

## 10. What is a good closing statement in an interview?

A strong finish:
- I would start with the smallest viable design and identify the critical path first.
- Then I would add caches, queue-based workers, and scaling techniques only where the bottleneck requires them.
- My design choice depends on the product’s latency, consistency, and failure-tolerance requirements.

## Quick interview framework

Use this 6-step structure in almost every design answer:
1. Clarify requirements.
2. State assumptions.
3. Define major components.
4. Explain the data flow.
5. Discuss bottlenecks and scaling.
6. Describe failures, tradeoffs, and follow-up improvements.

## Common mistakes to avoid

- jumping into a complex design without clarifying requirements
- ignoring data model and state transitions
- not identifying the bottleneck
- treating every system as strongly consistent
- forgetting failure handling and retries
- not explaining tradeoffs clearly
