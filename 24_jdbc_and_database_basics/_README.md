# JDBC and Database Basics

## What is this module?

This section explains the basics of connecting Java applications to relational databases using JDBC. It describes the main JDBC classes and the standard query workflow.

## Why it matters

Most Java backend systems interact with databases. Knowing JDBC lets you understand how data access works at a low level, which is useful even when using higher-level frameworks.

## How to use this module

Follow the JDBC workflow, write queries safely with `PreparedStatement`, and practice closing resources correctly. Use the example code as a template for connecting to a database.

## About

JDBC is the standard Java API for connecting to relational databases such as MySQL, PostgreSQL, and Oracle. It is essential for real-world Java backend development.

## What this example teaches

This example explains the standard JDBC workflow without requiring a live database connection.

## Why this matters

Understanding JDBC helps you write safer database access code and understand what higher-level frameworks like JPA and Spring JDBC do under the hood.

## How it works

1. Load the JDBC driver class.
2. Use `DriverManager.getConnection(...)` to open a `Connection`.
3. Create a `PreparedStatement` for SQL with placeholders (`?`).
4. Set parameter values and execute the statement.
5. Read results from the `ResultSet`.
6. Close `ResultSet`, `PreparedStatement`, and `Connection` in a finally block or use try-with-resources.

## Core concepts

- `DriverManager` creates a database connection.
- `Connection` represents the session with the database.
- `Statement` executes simple SQL.
- `PreparedStatement` prevents SQL injection and is preferred for parameters.
- `ResultSet` holds rows returned by a query.

## Typical workflow

```java
Connection connection = DriverManager.getConnection(url, user, password);
PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE active = ?");
ps.setBoolean(1, true);
ResultSet rs = ps.executeQuery();
```

## Common interview points

- Prefer `PreparedStatement` over `Statement`
- Use transactions for atomic multi-step updates
- Close resources in `finally` or with try-with-resources

## Example files

| File | What | Why |
|------|------|-----|
| `JDBCExample.java` | JDBC workflow steps | Teaches the standard lifecycle of database access |
| `JDBCPreparedStatementExample.java` | PreparedStatement usage | Shows safe parameter binding and result processing |

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Simple query | Depends on DB | O(rows) |
| Prepared statement execution | Depends on DB | O(rows) |
| Transaction commit | Depends on DB | O(1) |
