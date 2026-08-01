# Algorithms and Interview Patterns

## What is this module?

This module collects the core algorithms every Java engineer should understand for data structure and interview preparation. It explains what each algorithm does, why it matters, and how to approach it in code.

## Why it matters

These algorithms are the practical patterns you will see in real work and interview problems. They help you solve common tasks such as search, optimization, string matching, and graph traversal efficiently.

## How to use this module

Study each section in order, run the sample code, and practice explaining the time and space complexity for each pattern. Use the examples to connect the algorithm to the underlying data structure.

## 1. Arrays and strings

### Binary search
- Problem: find a target in a sorted array quickly.
- Idea: repeatedly halve the search space.
- Complexity: O(log n) time, O(1) space.

### Two sum
- Problem: find two numbers that add up to a target.
- Idea: store previously seen values in a hash map.
- Complexity: O(n) time, O(n) space.

### Kadane's algorithm
- Problem: maximum subarray sum.
- Idea: keep a running best and a running current sum.
- Complexity: O(n) time, O(1) space.

### Sliding window for unique substrings
- Problem: longest substring with unique characters.
- Idea: maintain a window and shrink it when a duplicate appears.
- Complexity: O(n) time, O(n) space.

## 2. Linked lists, stacks, and queues

### Reverse a linked list
- Problem: reverse the order of nodes.
- Idea: use three pointers: prev, current, next.
- Complexity: O(n) time, O(1) space.

### Detect a cycle
- Problem: determine whether a list has a loop.
- Idea: use slow and fast pointers.
- Complexity: O(n) time, O(1) space.

### Valid parentheses
- Problem: verify balanced brackets.
- Idea: push opening symbols and pop on matching closers.
- Complexity: O(n) time, O(n) space.

### Min stack
- Problem: retrieve the minimum element in O(1).
- Idea: maintain a second stack for minima.
- Complexity: O(1) push/pop/top/min.

## 3. Hashing and heaps

### Hash-map two sum
- Problem: find pairs efficiently.
- Idea: use a hash map for constant-time lookup.
- Complexity: O(n) time, O(n) space.

### Group anagrams
- Problem: cluster words that are permutations of each other.
- Idea: sort each word and use the sorted version as the key.
- Complexity: O(n × k log k) time, O(n) space.

### Top K frequent elements
- Problem: find the most common values.
- Idea: use a frequency map and a min-heap.
- Complexity: O(n log k) time, O(n) space.

## 4. Trees and graphs

### Tree depth and diameter
- Problem: compute height and diameter of a binary tree.
- Idea: use recursion and return height/diameter together.
- Complexity: O(n) time, O(h) space.

### Level-order traversal
- Problem: traverse a tree breadth-first.
- Idea: use a queue.
- Complexity: O(n) time, O(w) space.

### Graph BFS and DFS
- Problem: explore connected components and paths.
- Idea: use a queue for BFS and recursion/stack for DFS.
- Complexity: O(V + E) time, O(V) space.

## 5. Dynamic programming

### Fibonacci
- Problem: compute Fibonacci numbers.
- Idea: build results from smaller subproblems.
- Complexity: O(n) time, O(n) space.

### Climbing stairs
- Problem: count paths to the top.
- Idea: use the recurrence $dp[i] = dp[i-1] + dp[i-2]$.
- Complexity: O(n) time, O(1) space.

### Coin change
- Problem: find the minimum coins for an amount.
- Idea: fill a DP table from smaller amounts upward.
- Complexity: O(n × amount) time, O(amount) space.

### Longest common subsequence
- Problem: find the longest shared subsequence between two strings.
- Idea: fill a 2D table with match and skip decisions.
- Complexity: O(m × n) time, O(m × n) space.

## Suggested study order

1. Arrays and strings
2. Linked lists, stacks, and queues
3. Hashing and heaps
4. Trees and graphs
5. Dynamic programming

## Files in this module

- ArraysAndStringsAlgorithms.java
- LinkedListAndStackQueueAlgorithms.java
- HashingAndHeapAlgorithms.java
- TreeAndGraphAlgorithms.java
- DynamicProgrammingAlgorithms.java
