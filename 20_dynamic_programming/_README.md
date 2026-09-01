# Dynamic Programming

## About

Dynamic programming solves problems by breaking them into overlapping subproblems and storing previously computed results so they are reused instead of recomputed.

This is one of the most important algorithmic patterns for interview preparation, optimization problems, and sequence-based tasks.

## Core idea

A DP problem usually has two key properties:

- Overlapping subproblems: the same smaller problem is solved many times.
- Optimal substructure: the best solution for a larger problem can be built from best solutions of smaller subproblems.

If a problem does not have these properties, greedy or brute force is usually a better approach.

## Two standard styles

### 1. Memoization (top-down)

- Start from the full problem.
- Recursively break it into smaller states.
- Cache results in a memo table.
- Best when the recursion structure is natural and easy to express.

```java
int[] memo = new int[n + 1];
Arrays.fill(memo, -1);

int fib(int k) {
    if (k <= 1) return k;
    if (memo[k] != -1) return memo[k];
    return memo[k] = fib(k - 1) + fib(k - 2);
}
```

### 2. Tabulation (bottom-up)

- Start from base cases.
- Build the answer step by step from small to large states.
- Best when a direct iterative order is easy to define.

```java
int[] dp = new int[n + 1];
dp[0] = 0;
dp[1] = 1;
for (int i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}
```

## Must-know DP algorithms

### Fibonacci

- State: `dp[i] = fib(i)`
- Recurrence: `dp[i] = dp[i - 1] + dp[i - 2]`
- Base cases: `dp[0] = 0`, `dp[1] = 1`
- Use when: counting ways, sequential growth, recursive structure with repeated subproblems

### Climbing Stairs

- State: number of ways to reach stair `i`
- Recurrence: `ways[i] = ways[i - 1] + ways[i - 2]`
- This is the same pattern as Fibonacci, but in a different problem context.

### Coin Change

- State: minimum coins needed for amount `x`
- Recurrence: try each coin and update the best answer for the remaining amount
- Example: minimum number of coins to make 11 with `[1, 3, 4]` is 3 (`4 + 4 + 3`)

### 0/1 Knapsack

- State: `dp[i][w] = max value using first i items with capacity w`
- Recurrence:
  - skip item i: `dp[i - 1][w]`
  - take item i: `value[i] + dp[i - 1][w - weight[i]]`
- Use when: each item can be selected at most once

### Unbounded Knapsack

- Similar to 0/1 knapsack, but an item can be used multiple times.
- Recurrence usually uses the same row or same index instead of moving backwards.

### Longest Increasing Subsequence (LIS)

- State: `dp[i] = length of longest increasing subsequence ending at i`
- Recurrence: check all earlier positions `j < i` and update when `nums[j] < nums[i]`
- Use when: you need longest ordered subsequence in a sequence.

### House Robber

- State: maximum money for first `i` houses
- Recurrence:
  - skip house `i`: `dp[i - 1]`
  - rob house `i`: `nums[i] + dp[i - 2]`
- Use when: choosing non-adjacent items with maximum score.

### Edit Distance

- State: minimum operations to convert string `a[0..i]` to `b[0..j]`
- Operations: insert, delete, replace
- Recurrence compares characters and tries the three operations
- Use when: similarity, approximate matching, or string transformation problems

### Subset Sum

- State: whether a target sum can be formed using a subset
- Recurrence: either skip an item or use it to reach the remaining sum
- Use when: checking feasibility of sum-based problems

## Interview checklist

- Can you explain the state and recurrence clearly?
- Can you identify whether the problem is memoization or tabulation?
- Can you mention the base cases?
- Can you say the time and space complexity?
- Can you optimize space if needed?

## Time and space complexity

| Problem | Time | Space |
| --- | --- | --- |
| Fibonacci | O(n) | O(n) |
| Climbing Stairs | O(n) | O(n) |
| Coin Change (min coins) | O(n × amount) | O(amount) |
| 0/1 Knapsack | O(n × W) | O(n × W) |
| LIS | O(n²) | O(n) |
| Edit Distance | O(m × n) | O(m × n) |
| House Robber | O(n) | O(n) |
| Subset Sum | O(n × target) | O(n × target) |

Where `n` is the number of elements, `W` is capacity, and `m`, `n` are string lengths.

## Tips and pitfalls

- Always define the state carefully before writing recurrence.
- Be explicit with base cases; most DP mistakes happen there.
- Choose the right orientation: index-based, capacity-based, or remaining-value-based.
- For optimization problems, DP usually stores the best value for each state.
- For counting problems, DP usually stores the number of valid ways.
- Use `-1` or `null` as an uncomputed sentinel in memoization.

## Standard DP patterns to remember

1. Counting paths / ways: Fibonacci, climbing stairs
2. Minimum cost: coin change, edit distance
3. Best value with constraints: knapsack, LIS
4. Reachability / feasibility: subset sum, partition
5. Sequence alignment: edit distance

## Very common interview phrase

> “This is a DP problem because the answer depends on smaller overlapping subproblems.”

That is the key observation interviewers usually want.

