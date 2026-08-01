# Greedy Algorithms

## What are greedy algorithms?

Greedy algorithms make the locally optimal choice at each step with the hope of finding a global optimum. They are fast and easy to implement when the problem has a greedy-choice property.

## Why they matter

Many interview and engineering problems can be solved efficiently with greedy methods when the right ordering or selection rule is known. They are often the first optimization strategy to attempt.

## How to use them

1. Sort items or candidates by a key property.
2. Choose the best option available at that moment.
3. Update the state and repeat until done.

## Example problems

### Activity selection

Choose the maximum number of non-overlapping intervals by sorting by end time.

### Fractional knapsack

Fill the knapsack with the highest value-to-weight items first.

## Time and space complexity

| Algorithm | Time | Space |
|-----------|------|-------|
| Activity selection | O(n log n) | O(n) |
| Fractional knapsack | O(n log n) | O(n) |

## Files

| File | Concept |
|------|---------|
| `GreedyAlgorithmsExample.java` | Activity selection, fractional knapsack |
