# Greedy Algorithms

## What are greedy algorithms?

Greedy algorithms make the locally optimal choice at each step, hoping that this leads to a globally optimal answer. They are usually fast and simple when the problem has a greedy-choice property.

The key question is not just “can I choose the best thing now?” but also “is this choice safe for the final solution?”

## When greedy works

Greedy is a good fit when:

- There is a clear ordering key, such as earliest end time, highest ratio, or lowest cost.
- Choosing a local optimum does not block a better global answer.
- The problem has a matroid-like or exchange argument that proves the greedy choice is safe.

Greedy is not always correct. For many optimization problems, dynamic programming or backtracking is required.

## Core greedy patterns to know

### 1. Activity selection

Sort intervals by end time and always pick the one that finishes earliest.

- Why it works: finishing earlier leaves more room for future intervals.
- Use case: scheduling meetings, jobs, or tasks.
- Complexity: O(n log n)

### 2. Interval merging

Sort intervals by start time and merge overlapping intervals.

- Why it works: if intervals overlap, they can be merged because they all share a connected coverage range.
- Use case: room booking, event merging, range consolidation.
- Complexity: O(n log n)

### 3. Minimum number of platforms

Track train arrivals and departures with two pointers.

- Why it works: for each arrival, if a platform is already occupied, we need another one.
- Use case: railway platform planning, resource scheduling.
- Complexity: O(n log n)

### 4. Coin change (greedy version)

Use the largest denomination first when the coin system is canonical, such as Indian or US coins.

- Why it works only in some systems: not every denomination set is greedy-safe.
- Use case: typical currency problems where a canonical coin set is guaranteed.
- Complexity: O(n log n) if sorting, or O(n) if already sorted.

### 5. Fractional knapsack

Sort items by value/weight ratio and take as much as possible from the best ratio items.

- Why it works: because fractions are allowed, the highest ratio item is always a safe choice.
- Use case: resource allocation with divisible items.
- Complexity: O(n log n)

### 6. Huffman coding (advanced)

Combine the two lowest-frequency symbols repeatedly.

- Why it works: smaller frequencies should be merged first to minimize total cost.
- Use case: data compression.
- Complexity: O(n log n)

## Greedy interview checklist

1. Can you define the ordering rule clearly?
2. Is the local choice provably safe?
3. Have you checked whether a greedy strategy can fail on a counterexample?
4. Can you state the complexity after sorting or scanning?

## Greedy vs DP

Use greedy when:

- a local decision is obviously safe
- the objective can be optimized by ordering or choosing the best current item

Use DP when:

- the problem depends on multiple overlapping subproblems
- skipping a local choice may be required to reach the best overall result

## Time and space complexity

| Pattern | Time | Space |
|---|---:|---:|
| Activity selection | O(n log n) | O(n) |
| Interval merge | O(n log n) | O(n) |
| Minimum platforms | O(n log n) | O(1) extra beyond sorted arrays |
| Greedy coin change | O(n log n) | O(1) |
| Fractional knapsack | O(n log n) | O(n) |
| Huffman coding | O(n log n) | O(n) |

## Files

| File | Concept |
|------|---------|
| `GreedyAlgorithmsExample.java` | Activity selection, interval merge, minimum platforms, greedy coin change, fractional knapsack |

## Key takeaway

Greedy is powerful because it often turns a complex optimization problem into a sorting-and-scan process. The most important skill is recognizing when the greedy choice is valid and when you must switch to DP instead.
