package arrays;

/*
 * =============================================================================
 * Wave Matrix Traversal
 * =============================================================================
 *
 * PROBLEM
 *   Print matrix column by column, alternating down and up.
 *
 * EXAMPLE
 *   1 2 3
 *   4 5 6     →  1 4 7 8 5 2 3 6 9
 *   7 8 9
 *
 *   col 0 down: 1 4 7 | col 1 up: 8 5 2 | col 2 down: 3 6 9
 *
 * RUN FLOW
 *   main() → printWave(matrix) → prints wave order
 *
 * COMPLEXITY:  Time O(m×n), Space O(1)
 * =============================================================================
 */

class WaveMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.print("Wave order: ");
        printWave(matrix);
        System.out.println();
    }

    public static void printWave(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int col = 0; col < cols; col++) {
            if (col % 2 == 0) {
                for (int row = 0; row < rows; row++) {
                    System.out.print(matrix[row][col] + " ");
                }
            } else {
                for (int row = rows - 1; row >= 0; row--) {
                    System.out.print(matrix[row][col] + " ");
                }
            }
        }
    }
}
