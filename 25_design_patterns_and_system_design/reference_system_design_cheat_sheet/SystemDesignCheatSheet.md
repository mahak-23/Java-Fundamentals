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
