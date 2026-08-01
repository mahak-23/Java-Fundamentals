# Design Patterns and System Design

## What is this module?

This section introduces common Java design patterns and high-level system design concepts. It helps you think like an engineer when designing maintainable, flexible systems.

## Why it matters

Design patterns provide reusable solutions to common software design problems. System design knowledge helps you evaluate architecture tradeoffs, scalability, and reliability.

## How to use this module

Read the pattern definitions, review the sample code, and practice describing when to use each pattern. Pair this module with the architecture concepts when thinking about larger applications.

## About

Real engineering work is not only about coding data structures. Java developers are expected to understand common design patterns and how to design scalable systems.

## What this example teaches

This example demonstrates the Strategy pattern, which separates the algorithm from the client that uses it.

## Why this matters

Using design patterns makes code more modular, easier to extend, and simpler to maintain. The Strategy pattern is particularly useful when behavior should be swappable at runtime.

## How it works

1. Define a `PaymentStrategy` interface with a `pay(int amount)` method.
2. Implement different concrete strategies like `CreditCardPayment` and `PayPalPayment`.
3. Create a `PaymentProcessor` that takes a `PaymentStrategy` in its constructor.
4. Call `processor.process(amount)` to execute the selected payment behavior.

## Common design patterns

### Strategy pattern

```java
interface PaymentStrategy {
    void pay(int amount);
}
```

### Factory pattern

```java
class VehicleFactory {
    Vehicle create(String type) { ... }
}
```

### What these examples teach

- `DesignPatternsExample.java` shows the Strategy pattern, where behavior is passed into a processor.
- `FactoryPatternExample.java` shows the Factory pattern, where object creation is centralized in one place.

### Why these patterns matter

- Strategy makes behavior interchangeable without changing the client.
- Factory keeps construction logic in one place and makes it easier to support new types.

### How to use these examples

1. Create a strategy or request an object from a factory.
2. Pass the strategy or factory-produced object to the client.
3. Execute the behavior without the client knowing the concrete implementation.

## Example files

| File | Pattern | Purpose |
|------|---------|---------|
| `DesignPatternsExample.java` | Strategy | Swap payment behavior without changing the processor |
| `FactoryPatternExample.java` | Factory | Create objects through a single factory method |

## System design topics

- Scalability and load balancing
- Caching and database sharding
- Messaging queues and async processing
- API design and rate limiting
- Availability, consistency, and CAP tradeoffs

## Interview checklist

- Explain tradeoffs clearly
- Discuss bottlenecks and scalability options
- Mention reliability, monitoring, and failover

## Time and space complexity

| Concept | Time | Space |
|---------|------|-------|
| Strategy dispatch | O(1) | O(1) |
| Simple factory creation | O(1) | O(1) |
| System design evaluation | Depends on architecture | Depends on architecture |
