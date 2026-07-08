package arrays;

public class JaggedArraysExample {
    public static void main(String[] args) {
        System.out.println("=== Jagged Arrays ===");
        
        // Jagged array: rows have different lengths
        int[][] jagged = {
            {1, 2, 3, 4},
            {5, 6, 7},
            {8, 9},
            {10, 11, 12, 13, 14}
        };
        
        System.out.println("Jagged array (rows with different lengths):");
        for (int i = 0; i < jagged.length; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < jagged[i].length; j++) {
                System.out.print(jagged[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nManual jagged array creation:");
        
        // Create jagged array row by row
        int[][] manual = new int[4][];
        manual[0] = new int[]{1, 2, 3};
        manual[1] = new int[]{4, 5};
        manual[2] = new int[]{6, 7, 8, 9};
        manual[3] = new int[]{10};
        
        System.out.println("Manual jagged array:");
        for (int i = 0; i < manual.length; i++) {
            System.out.print("Row " + i + " (length " + manual[i].length + "): ");
            for (int value : manual[i]) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nAccessing jagged array elements:");
        System.out.println("jagged[0][0] = " + jagged[0][0]);
        System.out.println("jagged[1][2] = " + jagged[1][2]);
        System.out.println("jagged[3][4] = " + jagged[3][4]);
    }
}
