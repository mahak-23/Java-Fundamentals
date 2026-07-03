/*
Matrix transpose runs in O(r*c) time and O(1) extra space for an in-place swap.
*/

class TransposeMatrix {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] transposed = new int[n][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        
        return transposed;
    }
}

/*
example input and output:
Input:
1 2 3
4 5 6

Output:
1 4
2 5
3 6
*/