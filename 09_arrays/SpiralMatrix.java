package arrays;

/*
 * =============================================================================
 * Spiral Matrix Traversal
 * =============================================================================
 *
 * PROBLEM
 *   Print matrix elements in clockwise spiral order.
 *
 * EXAMPLE
 *   1 2 3
 *   4 5 6     →  1 2 3 6 9 8 7 4 5
 *   7 8 9
 *
 * IDEA
 *   Four boundaries: top, bottom, left, right.
 *   Print top row → right col → bottom row (reverse) → left col (reverse).
 *   Shrink boundaries after each side.
 *
 * RUN FLOW
 *   main() → printSpiral(matrix) → prints spiral order
 *
 * COMPLEXITY:  Time O(m×n), Space O(1)
 * =============================================================================
 */

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Spiral Order:");
        printSpiral(matrix);
    }
    
    public static void printSpiral(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // Traverse from left to right
            for (int i = left; i <= right; i++) {
                System.out.print(matrix[top][i] + " ");
            }
            top++;
            
            // Traverse from top to bottom
            for (int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right--;
            
            if (top <= bottom) {
                // Traverse from right to left
                for (int i = right; i >= left; i--) {
                    System.out.print(matrix[bottom][i] + " ");
                }
                bottom--;
            }
            
            if (left <= right) {
                // Traverse from bottom to top
                for (int i = bottom; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
                left++;
            }
        }
        System.out.println();
    }
}