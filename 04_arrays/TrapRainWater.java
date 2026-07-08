package arrays;

/*
 * =============================================================================
 * Trapping Rain Water — Two Pointer
 * =============================================================================
 *
 * PROBLEM
 *   Given bar heights, compute total water trapped between bars.
 *
 * IDEA
 *   Water at index i = min(leftMax, rightMax) - height[i]  (if positive)
 *   Two pointers from both ends; process the shorter side first.
 *
 * EXAMPLE
 *   height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
 *
 *        |     |
 *    |   ||  | ||
 *   _|_||_||_|_||  → trapped = 6 units
 *
 * RUN FLOW
 *   main() → trap(height) → prints total trapped water
 *
 * COMPLEXITY:  Time O(n), Space O(1)
 * =============================================================================
 */

class TrapRainWater {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int trappedWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    trappedWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    trappedWater += rightMax - height[right];
                }
                right--;
            }
        }
        return trappedWater;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrapRainWater solver = new TrapRainWater();
        System.out.println("Heights: " + java.util.Arrays.toString(height));
        System.out.println("Trapped water: " + solver.trap(height));
    }
}
