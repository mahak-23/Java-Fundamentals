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

### Java example and dry run

See [SystemDesignFundamentalsExample.java](SystemDesignFundamentalsExample.java) for a runnable example of cache-aside reads and role-based access checks.

Representative output:

```text
=== Customer reads a product ===
Loaded product P-101 from database and stored in cache
Result: Laptop - $999.99

=== Same product requested again ===
Cache hit for P-101
Result: Laptop - $999.99

=== Admin creates order ===
Loaded product P-102 from database and stored in cache
Order created for Nisha on Phone

=== Viewer tries to create an order ===
Blocked: User cannot create an order
```
