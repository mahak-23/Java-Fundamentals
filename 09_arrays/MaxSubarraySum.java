package arrays;

/*
 * =============================================================================
 * Kadane's Algorithm — Maximum Subarray Sum
 * =============================================================================
 *
 * PROBLEM
 *   Find contiguous subarray with the largest sum.
 *
 * IDEA
 *   Track currentSum ending at each index.
 *   If currentSum becomes negative, reset to 0 (start fresh subarray).
 *   Update maxSum whenever currentSum improves.
 *
 * EXAMPLE
 *   nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *
 *   Walk:
 *     -2 → cur=-2, max=-2
 *      1 → cur=1,  max=1   (reset after negative)
 *     -3 → cur=-2, max=1
 *      4 → cur=4,  max=4
 *     -1 → cur=3,  max=4
 *      2 → cur=5,  max=5
 *      1 → cur=6,  max=6   ← answer
 *
 * RUN FLOW
 *   main() → maxSubArray(nums) → prints 6
 *
 * COMPLEXITY:  Time O(n), Space O(1)
 * =============================================================================
 */

class MaxSubarraySum {
    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int num : nums) {
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Max subarray sum: " + maxSubArray(nums));
    }
}
