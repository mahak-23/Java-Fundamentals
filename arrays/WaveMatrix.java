/*
Wave traversal is O(r*c) time and O(1) extra space.
*/

class WaveMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        printWave(matrix);
    }
    
    public static void printWave(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        for (int col = 0; col < cols; col++) {
            if (col % 2 == 0) {
                // Print downwards
                for (int row = 0; row < rows; row++) {
                    System.out.print(matrix[row][col] + " ");
                }
            } else {
                // Print upwards
                for (int row = rows - 1; row >= 0; row--) {
                    System.out.print(matrix[row][col] + " ");
                }
            }
        }
    }
}

/*
example input and output:
Input:
1 2 3
4 5 6
7 8 9
Output:
1 2 3 6 5 4 7 8 9
*/