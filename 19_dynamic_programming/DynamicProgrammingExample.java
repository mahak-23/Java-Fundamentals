public class DynamicProgrammingExample {
    // Bottom-up (tabulation) Fibonacci — O(n) time, O(n) space
    public static int fibonacciTab(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    // Top-down memoized Fibonacci — O(n) time, O(n) space (stack + memo)
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

    // 0/1 Knapsack — bottom-up tabulation
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
        if (weights[i - 1] <= w) with = values[i - 1] + knapRec(i - 1, w - weights[i - 1], weights, values, memo);
        memo[i][w] = Math.max(without, with);
        return memo[i][w];
    }

    public static void main(String[] args) {
        System.out.println("Fibonacci tab (7) = " + fibonacciTab(7));
        System.out.println("Fibonacci memo (7) = " + fibonacciMemo(7));

        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        System.out.println("Knapsack tab = " + knapsackTab(weights, values, 7));
        System.out.println("Knapsack memo = " + knapsackMemo(weights, values, 7));
    }
}
