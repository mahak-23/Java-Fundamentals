# Concurrency and Multithreading

## What is this module?

This section introduces Java concurrency and multithreading. It covers the core concepts engineers need to write safe, parallel code and manage threads in real applications.

## Why it matters

Concurrency is important for responsiveness, throughput, and scaling in backend systems, UIs, and data processing. Understanding thread safety, race conditions, and synchronization helps you avoid bugs in production.

## How to use this module

Read the concepts, study the example code, and practice identifying race conditions. Try running the sample with and without synchronization to see why shared state needs protection.

## About

Modern Java applications often need to perform multiple tasks at the same time. Concurrency and multithreading help with responsiveness, throughput, and parallel processing.

## What this example teaches

This example shows how multiple threads can safely update a shared counter.

## Why this matters

Shared mutable state is a common source of bugs in concurrent programs. Without synchronization, two or more threads can update the same variable at once and produce incorrect results.

## How it works

1. `ExecutorService` creates a fixed thread pool of 4 worker threads.
2. Ten tasks are submitted to the pool, each calling `increment()`.
3. The `increment()` method is `synchronized`, so only one thread may execute it at a time.
4. The main thread waits for all tasks to complete with `shutdown()` and `awaitTermination()`.
5. The final counter value is printed.

## Core ideas

- A thread is a lightweight execution unit.
- `synchronized` protects shared state from race conditions.
- `ExecutorService` manages thread pools cleanly.
- `Future` and `Callable` help retrieve results from asynchronous work.

## Common patterns

### Synchronized method

```java
public synchronized void increment() {
    counter++;
}
```

### Thread pool

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(() -> System.out.println("task"));
```

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Thread creation | O(1) | O(1) |
| Synchronized access | O(1) average | O(1) |
| Thread pool task execution | Depends on task | O(pool size) |

## Engineer checklist

- Understand race conditions, deadlocks, and thread safety
- Know when to use `synchronized`, `volatile`, and `ExecutorService`
- Be comfortable with `Future`, `Callable`, and concurrent collections
