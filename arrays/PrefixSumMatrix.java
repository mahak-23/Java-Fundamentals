/*
Prefix sum matrix computation is O(r*c) time and O(r*c) extra space.
*/

class PrefixSumMatrix {
    private int[][] prefixSum;

    public PrefixSumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        prefixSum = new int[m][n];

        // Compute the prefix sum matrix
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
}