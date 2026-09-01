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

### Client-server (CS)

The client-server model separates:

- the client that sends requests
- the server that processes them and returns responses

This is the basic model behind web apps, APIs, mobile apps, and backend services.

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

| File                                                                       | Purpose                                                                  |
| -------------------------------------------------------------------------- | ------------------------------------------------------------------------ |
| [DesignPatternsExample.java](DesignPatternsExample.java)                   | Strategy pattern example                                                 |
| [FactoryPatternExample.java](FactoryPatternExample.java)                   | Factory pattern example                                                  |
| [ParkingLotLLDExample.java](ParkingLotLLDExample.java)                     | LLD example for parking lot flow and slot allocation                     |
| [HotelBookingLLDExample.java](HotelBookingLLDExample.java)                 | LLD example for room booking, validation, and overlap protection         |
| [EcommerceSystemHLDExample.java](EcommerceSystemHLDExample.java)           | HLD example covering gateway, services, payment, and async notifications |
| [ChatSystemHLDExample.java](ChatSystemHLDExample.java)                     | HLD example for real-time messaging, presence, and offline delivery      |
| [NotificationPipelineHLDExample.java](NotificationPipelineHLDExample.java) | HLD example for async notification pipelines and retry safety            |
| [URLShortenerHLDExample.java](URLShortenerHLDExample.java)                 | HLD example showing redirect service design and cache bottlenecks        |

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
