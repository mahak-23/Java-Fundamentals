# Queue

> **Difficulty:** Intermediate | **Prerequisites:** [java basics](../java%20basics/_README.md), [collections](../collections/_README.md) | **Time:** 2-3 hours | **Topics:** FIFO, BFS, sliding window, deque

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
A queue is a linear data structure that follows the First-In-First-Out (FIFO) rule. The first element inserted is the first one removed.

## Why queues are useful
- They model real-world waiting lines.
- They are used in BFS, scheduling, and task processing.
- They help with request handling and buffering.
- They are essential for many sliding-window and level-order problems.

## Queue operations
- `offer(value)` adds an element to the rear.
- `poll()` removes and returns the front element.
- `peek()` returns the front element without removing it.
- `isEmpty()` checks whether the queue has no elements.
- `size()` returns the number of elements.

## Queue operations with examples
```java
Queue<String> queue = new LinkedList<>();

// Offer (enqueue): add to rear
queue.offer("Alice"); // Queue: [Alice]
queue.offer("Bob");   // Queue: [Alice, Bob]
queue.offer("Carol"); // Queue: [Alice, Bob, Carol]
System.out.println("After offers: " + queue); // Output: [Alice, Bob, Carol]

// Peek: view front without removing
System.out.println(queue.peek()); // Output: Alice
System.out.println("Queue unchanged: " + queue); // Output: [Alice, Bob, Carol]

// Poll (dequeue): remove from front
String polled = queue.poll();
System.out.println("Polled: " + polled); // Output: Alice
System.out.println("Queue after poll: " + queue); // Output: [Bob, Carol]

// Check properties
System.out.println(queue.size()); // Output: 2
System.out.println(queue.isEmpty()); // Output: false

// Clear
queue.clear();
System.out.println(queue.isEmpty()); // Output: true
```

## Time complexity
- `offer` : O(1)
- `poll` : O(1)
- `peek` : O(1)
- `isEmpty` : O(1)
- `size` : O(1)

## Common queue patterns
### 1. Breadth-first traversal
```java
import java.util.*;

public void bfs(int start, List<List<Integer>> graph) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[graph.size()];
    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.print(node + " ");
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
    }
}
```

### 2. Sliding window maximum
A monotonic deque keeps decreasing values so the front is always the largest candidate for the current window.

```java
public int[] slidingWindowMax(int[] nums, int k) {
    Deque<Integer> deque = new ArrayDeque<>();
    int[] result = new int[nums.length - k + 1];

    for (int i = 0; i < nums.length; i++) {
        while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
            deque.pollFirst();
        }
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
            deque.pollLast();
        }
        deque.offerLast(i);
        if (i >= k - 1) {
            result[i - k + 1] = nums[deque.peekFirst()];
        }
    }
    return result;
}
```

## Interview notes
- Queues follow FIFO: first in, first out.
- Use `Deque<T>` with `ArrayDeque` for better performance than `Queue<T>`.
- Common queue problems involve BFS, level-order traversal, and sliding window algorithms.

## Quick reference: queue operations

### Core operations (O(1))
- Enqueue: `offer()` or `add()` - add to rear
- Dequeue: `poll()` or `remove()` - remove and return front
- Peek: `peek()` or `element()` - view front without removing
- Check: `isEmpty()`, `size()`
- Clear: `clear()`

### Queue use cases
- Breadth-first search (BFS)
- Task scheduling and job queues
- Message passing systems
- Sliding window problems
- Resource allocation

### 3. Reverse first K elements of a queue
```java
public void reverseFirstK(Queue<Integer> queue, int k) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < k; i++) {
        stack.push(queue.poll());
    }
    while (!stack.isEmpty()) {
        queue.offer(stack.pop());
    }
    for (int i = 0; i < queue.size() - k; i++) {
        queue.offer(queue.poll());
    }
}
```

## CRUD, size, and iteration notes
- Create: `offer()` or `add()`.
- Read: `peek()`.
- Update: queue operations usually do not support direct update by index.
- Delete: `poll()` or `remove()`.
- Use `size()` to know how many elements are present.
- Iterate by polling elements or by converting to an array/list.

```java
Queue<Integer> q = new LinkedList<>();
q.offer(1);
q.offer(2);
System.out.println(q.peek());
System.out.println(q.size());
while (!q.isEmpty()) {
    System.out.println(q.poll());
}
```

## Interview notes
- A queue is FIFO: the earliest inserted element is processed first.
- Use `Queue` for general FIFO behavior and `PriorityQueue` when ordering by priority matters.
- Mention edge cases like empty queue, full queue, and size restrictions.
- For interview answers, explain the idea and show the step-by-step processing order.

## Common interview questions
- Implement a queue using two stacks
- Generate binary numbers using a queue
- Find the first non-repeating character in a stream
- Compute the sliding window maximum
- Reverse the first K elements of a queue
