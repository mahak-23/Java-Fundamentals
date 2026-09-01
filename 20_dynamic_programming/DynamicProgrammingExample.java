package dynamic_programming;

/*
 * =============================================================================
 * Dynamic Programming — Core Patterns You Should Know
 * =============================================================================
 *
 * WHAT IS DP?
 *   Break a problem into overlapping subproblems and cache the answers so they
 *   are not recomputed repeatedly.
 *
 * MUST-KNOW PATTERNS:
 *   1) Fibonacci / staircase counting
 *   2) Unbounded / 0/1 knapsack
 *   3) Coin change
 *   4) LIS / longest increasing subsequence
 *   5) Edit distance
 *   6) House robber / non-adjacent choice
 *   7) Subset sum / partition
 *
 * TWO STYLES:
 *   Top-down (memoization): recursive + cache
 *   Bottom-up (tabulation): build table from small to large states
 *
 * COMPLEXITY:
 *   Fibonacci: O(n)
 *   Knapsack: O(n * W)
 *   LIS: O(n^2)
 *   Edit Distance: O(m * n)
 * =============================================================================
 */

public class DynamicProgrammingExample {
    // Bottom-up (tabulation) Fibonacci
    // State: dp[i] = Fibonacci(i)
    // Recurrence: dp[i] = dp[i-1] + dp[i-2]
    // Base cases: dp[0] = 0, dp[1] = 1
    // Complexity: O(n) time, O(n) space
    public static int fibonacciTab(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Top-down memoized Fibonacci
    // Recursion caches answers for subproblems so each state is solved once.
    // Complexity: O(n) time, O(n) space
    public static int fibonacciMemo(int n) {
        int[] memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        return fibMemoRec(n, memo);
    }

    private static int fibMemoRec(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = fibMemoRec(n - 1, memo) + fibMemoRec(n - 2, memo);
        return memo[n];
    }

    // Climbing Stairs — same recurrence as Fibonacci
    // ways[i] = ways[i-1] + ways[i-2]
    // Complexity: O(n) time, O(n) space
    public static int climbingStairs(int n) {
        if (n <= 1) return 1;
        int[] ways = new int[n + 1];
        ways[0] = 1;
        ways[1] = 1;
        for (int i = 2; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n];
    }

    // Minimum Coins for a target amount (coin change)
    // dp[x] = minimum coins needed to make amount x
    // Recurrence: dp[x] = min(dp[x - coin] + 1) for all valid coins
    // Complexity: O(n * amount)
    public static int minCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int x = 1; x <= amount; x++) {
            for (int coin : coins) {
                if (coin <= x && dp[x - coin] != Integer.MAX_VALUE) {
                    dp[x] = Math.min(dp[x], dp[x - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    // 0/1 Knapsack — bottom-up tabulation
    // State: dp[i][w] = max value using first i items and capacity w
    // Recurrence:
    //   skip item i -> dp[i - 1][w]
    //   take item i -> value[i - 1] + dp[i - 1][w - weight[i - 1]]
    // Complexity: O(n * W) time and space
    public static int knapsackTab(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w];
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                }
            }
        }
        return dp[n][capacity];
    }

    // 0/1 Knapsack — top-down memoization
    public static int knapsackMemo(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] memo = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) java.util.Arrays.fill(memo[i], -1);
        return knapRec(n, capacity, weights, values, memo);
    }

    private static int knapRec(int i, int w, int[] weights, int[] values, int[][] memo) {
        if (i == 0 || w == 0) return 0;
        if (memo[i][w] != -1) return memo[i][w];

        int without = knapRec(i - 1, w, weights, values, memo);
        int with = 0;
        if (weights[i - 1] <= w) {
            with = values[i - 1] + knapRec(i - 1, w - weights[i - 1], weights, values, memo);
        }

        memo[i][w] = Math.max(without, with);
        return memo[i][w];
    }

    // LIS — longest increasing subsequence
    // dp[i] = LIS length ending at index i
    // Recurrence: dp[i] = 1 + max(dp[j]) for j < i and nums[j] < nums[i]
    // Complexity: O(n^2)
    public static int lis(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int best = 1;

        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            best = Math.max(best, dp[i]);
        }

        return best;
    }

    // House Robber
    // dp[i] = max money from first i houses
    // Recurrence: dp[i] = max(dp[i-1], nums[i] + dp[i-2])
    // Complexity: O(n)
    public static int houseRobber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[nums.length - 1];
    }

    // Edit Distance — minimum operations to convert one string to another
    // dp[i][j] = minimum edits to convert first i chars of s1 to first j chars of s2
    // Operations: insert, delete, replace
    // Complexity: O(m * n)
    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("Fibonacci tab (7) = " + fibonacciTab(7));
        System.out.println("Fibonacci memo (7) = " + fibonacciMemo(7));
        System.out.println("Climbing stairs (5) = " + climbingStairs(5));
        System.out.println("Min coins for 11 = " + minCoins(new int[]{1, 3, 4}, 11));

        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        System.out.println("Knapsack tab = " + knapsackTab(weights, values, 7));
        System.out.println("Knapsack memo = " + knapsackMemo(weights, values, 7));

        System.out.println("LIS = " + lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("House robber = " + houseRobber(new int[]{2, 7, 9, 3, 1}));
        System.out.println("Edit distance = " + editDistance("kitten", "sitting"));
    }
}
