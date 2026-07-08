package arrays;

/*
 * =============================================================================
 * 2D Prefix Sum — Range Sum on a Matrix
 * =============================================================================
 *
 * PROBLEM
 *   Answer sum of rectangle (row1,col1) to (row2,col2) in O(1) after preprocessing.
 *
 * IDEA
 *   prefix[i][j] = sum of all cells in rectangle from (0,0) to (i,j)
 *   Use inclusion-exclusion to subtract overlapping regions.
 *
 * EXAMPLE
 *   Matrix:        Prefix (conceptually):
 *   1  2           Build cumulative sums row + column wise
 *   3  4
 *
 *   sumRegion(0,0,1,1) = prefix[1][1] = 10  (1+2+3+4)
 *
 * RUN FLOW
 *   main() → build PrefixSumMatrix → query sumRegion → print
 *
 * COMPLEXITY:  Build O(m×n), Query O(1), Space O(m×n)
 * =============================================================================
 */

class PrefixSumMatrix {
    private final int[][] prefixSum;

    public PrefixSumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        prefixSum = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixSum[i][j] = matrix[i][j];
                if (i > 0) prefixSum[i][j] += prefixSum[i - 1][j];
                if (j > 0) prefixSum[i][j] += prefixSum[i][j - 1];
                if (i > 0 && j > 0) prefixSum[i][j] -= prefixSum[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = prefixSum[row2][col2];
        if (row1 > 0) sum -= prefixSum[row1 - 1][col2];
        if (col1 > 0) sum -= prefixSum[row2][col1 - 1];
        if (row1 > 0 && col1 > 0) sum += prefixSum[row1 - 1][col1 - 1];
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {3, 4}};
        PrefixSumMatrix ps = new PrefixSumMatrix(matrix);
        System.out.println("Sum of full matrix (0,0) to (1,1): " + ps.sumRegion(0, 0, 1, 1));
    }
}
