# Product-Based Company Interview Preparation

This guide targets engineers with roughly 3-4 years of experience. Interview loops vary by company and role; use the stages below as a preparation framework, not a guarantee of a specific process.

## Quick links

- [System design interview Q&A](InterviewQnA.md)
- [System design diagrams](../09_system_design_examples/SystemDesignDiagrams.md)
- [System design cheat sheet](../reference_system_design_cheat_sheet/SystemDesignCheatSheet.md)
- [Browse by purpose](../BrowseByPurpose.md)

## What interviewers look for

At this level, interviewers generally expect you to solve problems independently, explain tradeoffs, write maintainable code, and demonstrate ownership beyond completing assigned tickets.

- Problem solving: clarify requirements, form an approach, analyze complexity, and test edge cases.
- Coding quality: use clear names, cohesive methods, appropriate data structures, and useful tests.
- Java depth: understand collections, equality, exceptions, generics, concurrency fundamentals, and JVM basics.
- Design judgment: explain data ownership, API boundaries, consistency, failure handling, and operational tradeoffs.
- Ownership: describe a production change from problem discovery through rollout and measurement.
- Communication: narrate decisions and respond constructively when requirements change.

## Common interview stages

| Stage | What it tends to assess | Preparation |
| --- | --- | --- |
| Recruiter or hiring manager | Role fit, experience, communication, motivation | Prepare a concise career summary and reasons for the role |
| Online assessment | DSA fundamentals, correctness, time management | Practice timed problems and test edge cases |
| Coding interviews | Algorithms, implementation, complexity, testing | Solve aloud; compare a simple approach with an improved one |
| Java or backend round | Language knowledge, APIs, persistence, concurrency | Review Java fundamentals and production failure cases |
| LLD or machine coding | Modeling, extensibility, clean code, working behavior | Implement a small domain with tests and sensible boundaries |
| HLD or system design | Requirements, scale, data, reliability, tradeoffs | Practice end-to-end designs with estimates and bottlenecks |
| Project deep dive | Ownership, technical choices, impact, learning | Prepare two projects with metrics and difficult decisions |
| Behavioral or bar raiser | Collaboration, judgment, conflict, accountability | Use concise STAR stories with specific outcomes |

## Coding and DSA preparation

Focus on recognizing patterns and explaining why they apply. Practice in Java under interview conditions, including writing a few tests without relying on an IDE.

| Area | Patterns to practice | Example problem types |
| --- | --- | --- |
| Arrays and hash maps | Counting, prefix sums, two pointers | Pair sums, subarray counts, longest unique range |
| Strings | Frequency maps, parsing, sliding window | Anagrams, minimum window, palindrome checks |
| Linked lists | Pointer manipulation, fast/slow pointers | Cycle detection, reverse list, merge lists |
| Stacks and queues | Monotonic stack, deque, BFS queue | Next greater element, valid brackets, sliding maximum |
| Trees and BSTs | DFS, BFS, recursion, invariants | Traversals, lowest common ancestor, validate BST |
| Heaps | Top-k, merge k sorted streams | Kth largest, top frequent items |
| Binary search | Search space, lower/upper bound | Rotated arrays, first feasible capacity |
| Graphs | BFS/DFS, topological order, union-find | Components, shortest paths, dependency ordering |
| Intervals and greedy | Sorting, sweep line, exchange reasoning | Merge intervals, meeting rooms, minimum resources |
| Dynamic programming | State definition, transitions, base cases | Coin change, grid paths, longest subsequence |

For every problem, state the input constraints, propose a baseline, improve it, give time and space complexity, then test empty, minimal, duplicate, boundary, and large cases.

Practice material already in this repository:

- [Arrays](../../09_arrays/_README.md)
- [Strings](../../10_strings/_README.md)
- [Stacks](../../11_stack/_README.md) and [queues](../../12_queue/_README.md)
- [Linked lists](../../13_linkedlist/_README.md)
- [Trees](../../15_trees_and_traversals/_README.md) and [heaps](../../16_heaps/_README.md)
- [Graphs](../../18_graphs/_README.md)
- [Greedy algorithms](../../19_greedy_algorithms/_README.md) and [dynamic programming](../../20_dynamic_programming/_README.md)
- [Algorithms and patterns](../../22_algorithms_and_patterns/README.md)

## Java and backend fundamentals

Be ready to explain concepts with a short code-level example and a production consequence.

- Collections: `HashMap` behavior, `equals`/`hashCode`, ordering, concurrent collections, and when to use lists, sets, or maps.
- Object design: interfaces, composition vs inheritance, immutability, SOLID principles, and common patterns.
- Exceptions: checked vs unchecked exceptions, error boundaries, and not swallowing failures.
- Concurrency: thread safety, race conditions, synchronization, executors, futures, and safe shared state.
- JVM: heap vs stack, garbage collection at a high level, memory leaks through retained references, and diagnosing resource pressure.
- Backend: REST semantics, validation, authentication/authorization, idempotency, pagination, timeouts, retries, and rate limits.
- Persistence: transactions, indexes, isolation, query plans, schema evolution, and cache invalidation.

## LLD and machine-coding preparation

Practice turning requirements into a small implementation before adding abstractions.

1. Clarify scope, actors, and core use cases.
2. Identify entities, value objects, states, and invariants.
3. Define interfaces and service responsibilities.
4. Implement one happy path plus important failure cases.
5. Add tests for state transitions, validation, and concurrency-sensitive rules.
6. Explain what would change for persistence, multiple instances, and higher traffic.

Good practice prompts include parking lot, hotel booking, payment processing, notification dispatch, expense sharing, and rate limiting. See the [LLD examples](../07_low_level_design/_README.md).

## HLD and system-design preparation

Use a consistent sequence so the discussion stays grounded in requirements:

1. Clarify users, use cases, scale, latency, availability, and data retention.
2. Estimate peak reads/writes and storage; state assumptions out loud.
3. Define APIs and the core data model.
4. Draw the request and event flows; identify synchronous and asynchronous work.
5. Choose storage, indexes, caches, queues, and partition keys with reasons.
6. Find bottlenecks and explain how the design scales.
7. Walk through timeouts, retries, duplicate delivery, partial outages, and recovery.
8. Cover security, observability, deployment, and cost where relevant.
9. Summarize tradeoffs and describe the next improvement you would make as load grows.

Practice designs:

- URL shortener, chat, notification pipeline, e-commerce, social feed, and AI inference pipeline in [worked system designs](../09_system_design_examples/SystemDesignExamples.md).
- Use the [HLD guide](../08_high_level_design/_README.md) and [diagram collection](../09_system_design_examples/SystemDesignDiagrams.md) to rehearse component and sequence flows.

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

Start by covering the basic terms:

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

## Project deep dive

Prepare two projects you can explain in depth. One should ideally include a meaningful production challenge, scale concern, reliability improvement, or cross-team decision.

For each project, be ready to describe:

- user or business problem and your specific responsibility
- baseline behavior and measurable constraints
- architecture and the main request/data flow
- one design choice you made and alternatives you rejected
- a difficult bug, incident, migration, or performance bottleneck
- tests, rollout strategy, monitoring, and rollback plan
- measurable result, what you learned, and what you would change now

Use accurate metrics. If a number is confidential or unavailable, describe the direction and measurement method without inventing a figure.

## Behavioral preparation

Prepare concise STAR stories (Situation, Task, Action, Result) for:

- an incident or difficult defect you owned
- a disagreement about a technical design
- a project with changing requirements
- a mistake and how you improved your process
- a performance or reliability improvement
- mentoring, unblocking, or collaborating across teams

Keep the focus on your decisions and actions, give credit to collaborators, and explain the result honestly.

## Six-week preparation plan

| Week | Focus | Evidence of readiness |
| --- | --- | --- |
| 1 | Arrays, strings, hash maps, complexity, Java collections | Explain and solve common patterns without hints |
| 2 | Linked lists, stacks, queues, trees, heaps | Complete timed problems and test edge cases |
| 3 | Graphs, binary search, intervals, greedy, DP basics | Choose a pattern and justify it clearly |
| 4 | Java/backend fundamentals and LLD | Build a small design with clear interfaces and tests |
| 5 | HLD, estimation, databases, cache, queues, reliability | Complete two mock designs in 45-60 minutes |
| 6 | Project stories, behavioral rounds, mixed mocks | Deliver concise answers and identify gaps from feedback |

For a shorter timeline, preserve the mock interviews and project review; reduce the number of practice problems rather than skipping explanation and testing practice.

## Mock interview routine

- Schedule at least two timed coding mocks and one design mock each week.
- After each mock, record one issue in problem solving, communication, correctness, or time management.
- Re-solve missed problems from a blank editor after a delay.
- For design mocks, ask the reviewer to challenge one assumption and one failure scenario.
- Review the [system design Q&A](InterviewQnA.md) and [cheat sheet](../reference_system_design_cheat_sheet/SystemDesignCheatSheet.md) only after attempting an answer yourself.

## Final checklist

- I clarify requirements before choosing an algorithm or architecture.
- I explain complexity and test boundary cases.
- I can discuss Java and backend decisions beyond definitions.
- I can draw a design, estimate load, and explain a bottleneck.
- I describe failures, retries, idempotency, and observability.
- I can explain my project contributions and impact precisely.
- I state assumptions and tradeoffs instead of presenting one design as universally correct.
