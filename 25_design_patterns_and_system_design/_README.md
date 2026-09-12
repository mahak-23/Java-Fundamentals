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
