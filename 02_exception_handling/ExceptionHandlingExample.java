package exception_handling;

public class ExceptionHandlingExample {
    public static void main(String[] args) {
        System.out.println("=== Try-Catch-Finally ===");

        // Example 1: Arithmetic Exception
        System.out.println("Example 1: Division by zero");
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed\n");
        }

        // Example 2: Array Index Out of Bounds
        System.out.println("Example 2: Array index out of bounds");
        try {
            int[] numbers = {1, 2, 3};
            int value = numbers[5];
            System.out.println("Value: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed\n");
        }

        // Example 3: Multiple catch clauses
        System.out.println("Example 3: Multiple catch clauses");
        try {
            String number = "abc";
            int value = Integer.parseInt(number);
            System.out.println("Value: " + value);
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught generic Exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed\n");
        }

        // Example 4: NullPointerException
        System.out.println("Example 4: Null pointer exception");
        try {
            String name = null;
            int length = name.length();
            System.out.println("Length: " + length);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: cannot call method on null");
        } finally {
            System.out.println("Finally block executed\n");
        }

        // Example 5: Exception with custom message
        System.out.println("Example 5: Custom exception handling");
        try {
            int age = -5;
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed\n");
        }
    }
}
