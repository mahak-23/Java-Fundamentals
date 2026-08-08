# Heaps

## About

A heap is a complete binary tree that satisfies the heap property. In a min-heap, the smallest element is at the root. In a max-heap, the largest element is at the root. Heaps are commonly used for priority queues, scheduling, and top-k problems.

## How to create a heap in Java

### Min-heap

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
```

### Max-heap

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

## Common operations

### Insert / offer

```java
minHeap.offer(10);
minHeap.offer(5);
```

### Peek

```java
int smallest = minHeap.peek();
```

### Poll / remove root

```java
int removed = minHeap.poll();
```

### Remove arbitrary element

```java
minHeap.remove(5);
```

## CRUD-style heap workflow

```java
minHeap.offer(7);      // create
int top = minHeap.peek(); // read
minHeap.poll();        // delete
```

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Offer / insert | O(log n) | O(1) |
| Peek | O(1) | O(1) |
| Poll / remove root | O(log n) | O(1) |
| Remove arbitrary element | O(n) | O(1) |

## Heaps vs balanced trees

- A heap is optimized for finding the minimum or maximum element quickly.
- A balanced tree is optimized for ordered lookup, insertion, and deletion.
- Use a heap for priority scheduling and top-k problems.
- Use a balanced tree when you need sorted traversal and fast ordered operations.

## Engineer checklist

- Know the difference between min-heap and max-heap
- Understand `offer`, `poll`, `peek`, and `remove`
- Be able to explain why heaps are useful for priority queues and top-k problems
- Know when a balanced tree is preferable to a heap
