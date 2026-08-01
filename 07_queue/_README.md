# queue

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [05_collections](../05_collections/_README.md)

A queue is a FIFO (first in, first out) data structure. It is used in scheduling, breadth-first search, buffering, and task processing.

## How to create a queue in Java

You can create a queue with `LinkedList` or `ArrayDeque`:

```java
import java.util.LinkedList;

LinkedList<Integer> queue = new LinkedList<>();
```

## Common operations

### 1. Enqueue / add

```java
queue.add(10);
queue.add(20);
queue.add(30);
```

### 2. Dequeue / poll / remove

```java
int value = queue.remove();
int polled = queue.poll();
```

### 3. Peek / front

```java
int front = queue.peek();
```

### 4. Check size and emptiness

```java
int size = queue.size();
boolean empty = queue.isEmpty();
```

### 5. CRUD-style queue workflow

```java
queue.add(1);      // create
int first = queue.peek(); // read
queue.remove();    // delete
```

## Example workflow

```java
queue.add(1);
queue.add(2);
queue.add(3);

System.out.println(queue.peek()); // 1
System.out.println(queue.remove()); // 1
System.out.println(queue.peek()); // 2
```

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Enqueue | O(1) | O(1) |
| Dequeue | O(1) | O(1) |
| Peek | O(1) | O(1) |
| Search | O(n) | O(1) |

## Files

| File | Content |
|------|---------|
| `QueueOperations.java` | enqueue, dequeue, circular queue |
| `QueueWithCircularArray.java` | Array-based circular queue |
| `QueueInterviewProblems.java` | BFS patterns, sliding window max |

## Algorithms in QueueInterviewProblems

| Method | Pattern | Example |
|--------|---------|---------|
| `reverseFirstK` | Stack + queue | Reverse first k elements |
| `generateBinaryNumbers` | BFS generation | First n binary numbers |
| `firstNonRepeatingCharacter` | Queue + frequency | First unique char in stream |
| `slidingWindowMaximum` | Deque | Max in each window of size k |
| `QueueUsingStacks` | Two stacks | Implement queue using stacks |

## Interview questions

1. Queue vs stack — when use which?
2. How does circular queue work?
3. BFS — why queue and not stack?

## Next module

→ [08_linkedlist/_README.md](../08_linkedlist/_README.md)
