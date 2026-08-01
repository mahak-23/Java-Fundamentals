# Dynamic Programming

## About

Dynamic programming solves complex problems by breaking them into overlapping subproblems and storing results for reuse. It is commonly used for optimization, sequence, and path problems.

## Common patterns

### Fibonacci

```java
int[] dp = new int[n + 1];
dp[0] = 0;
dp[1] = 1;
for (int i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}
```

### 0/1 Knapsack

```java
int[][] dp = new int[n + 1][capacity + 1];
```

## Time and space complexity

| Problem | Time | Space |
|---------|------|-------|
| Fibonacci DP | O(n) | O(n) |
| 0/1 Knapsack | O(n × W) | O(n × W) |

Where $n$ is the number of items and $W$ is the capacity.

## Engineer checklist

- Know when a problem has overlapping subproblems
- Be comfortable with top-down memoization and bottom-up tabulation
- Be able to explain trade-offs between time and memory
