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

## Memoization vs Tabulation (Top-down vs Bottom-up)

- Memoization (Top-down):
    - Implement a recursive solution and cache results for subproblems (usually in an array or map).
    - Good for problems where the recursion tree is natural and you want to avoid computing unreachable states.
    - Easier to write quickly from a recursive formulation.

- Tabulation (Bottom-up):
    - Build a DP table iteratively from base cases up to the desired answer.
    - Often has slightly better constant factors and avoids recursion overhead.
    - Easier to reason about space optimization (rolling arrays) in many cases.

## How to approach a DP problem (practical checklist)

1. Identify if the problem has overlapping subproblems and optimal substructure.
2. Choose a state representation: what parameters define a subproblem? (e.g., index and remaining capacity)
3. Write a recurrence relating a state to smaller states.
4. Decide between memoization (recursive) or tabulation (iterative).
5. Initialize base cases.
6. (Optional) Optimize space by keeping only necessary previous states.
7. Verify with small examples and edge cases (zero, single element, large values).

## Common DP problems (study list)

- Fibonacci (tabulation + memoization)
- 0/1 Knapsack (tabulation + memoization)
- Coin change (min coins / ways)
- Longest Increasing Subsequence (DP / patience sorting)
- Edit distance (Levenshtein)
- Partition / subset sum
- Matrix chain multiplication

## Tips & pitfalls

- Prefer tabulation when stack depth or recursion overhead is a concern.
- When memoizing using arrays, initialize with a sentinel (e.g., -1) for "unknown".
- Be careful with state size — sometimes you can compress dimensions (e.g., iterate capacity backwards for 0/1 knapsack to get O(W) space).
- Always reason about base cases before implementation.

## Example snippets

Top-down memoization (Fibonacci):

```java
int[] memo = new int[n + 1];
Arrays.fill(memo, -1);
int fib(int k) {
    if (k <= 1) return k;
    if (memo[k] != -1) return memo[k];
    return memo[k] = fib(k-1) + fib(k-2);
}
```

Bottom-up tabulation (Fibonacci):

```java
int[] dp = new int[n + 1];
dp[0] = 0; dp[1] = 1;
for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
```

