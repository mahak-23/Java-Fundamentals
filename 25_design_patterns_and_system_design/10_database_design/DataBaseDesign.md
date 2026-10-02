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
