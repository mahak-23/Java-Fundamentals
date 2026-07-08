package arrays;

/*
 * =============================================================================
 * Matrix Transpose — Swap Rows and Columns
 * =============================================================================
 *
 * PROBLEM
 *   Convert m×n matrix to n×m by swapping element [i][j] with [j][i].
 *
 * EXAMPLE
 *   Input:          Output:
 *   1  2  3          1  4
 *   4  5  6    →     2  5
 *                    3  6
 *
 * RUN FLOW
 *   main() → transpose(matrix) → print result
 *
 * COMPLEXITY:  Time O(m×n), Space O(m×n) for new matrix
 * =============================================================================
 */

class TransposeMatrix {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        TransposeMatrix t = new TransposeMatrix();
        int[][] transposed = t.transpose(matrix);

        System.out.println("Original:");
        print(matrix);
        System.out.println("Transposed:");
        print(transposed);
    }

    static void print(int[][] m) {
        for (int[] row : m) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }
    }
}
