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
