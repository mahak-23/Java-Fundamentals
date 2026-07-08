package arrays;

public class ArraysExample {
    public static void main(String[] args) {
        System.out.println("=== Single-Dimensional Arrays ===");
        
        // Array initialization with values
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.println("Array: " + arrayToString(numbers));
        System.out.println("Length: " + numbers.length);
        System.out.println("First element: " + numbers[0]);
        System.out.println("Last element: " + numbers[numbers.length - 1]);
        
        // Array initialization with new
        String[] fruits = new String[3];
        fruits[0] = "Apple";
        fruits[1] = "Banana";
        fruits[2] = "Orange";
        System.out.println("\nFruits: " + arrayToString(fruits));
        
        // Iteration with for loop
        System.out.println("\nIterating with for loop:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("  numbers[" + i + "] = " + numbers[i]);
        }
        
        // Iteration with for-each loop
        System.out.println("\nIterating with for-each loop:");
        for (int num : numbers) {
            System.out.println("  " + num);
        }
        
        System.out.println("\n=== Multi-Dimensional Arrays ===");
        
        // 2D array
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Matrix (3x3):");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("Element at [1][2]: " + matrix[1][2]);
        
        System.out.println("\n=== Default Values ===");
        int[] defaultInts = new int[3];
        boolean[] defaultBools = new boolean[3];
        String[] defaultStrings = new String[3];
        
        System.out.println("Default int values: " + arrayToString(defaultInts));
        System.out.println("Default boolean values: " + arrayToString(defaultBools));
        System.out.println("Default String values: " + arrayToString(defaultStrings));
    }
    
    static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    static String arrayToString(String[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    static String arrayToString(boolean[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
