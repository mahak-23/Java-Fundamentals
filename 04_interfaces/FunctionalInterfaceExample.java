package interfaces;

@FunctionalInterface
interface Operation {
    int execute(int a, int b);
}

@FunctionalInterface
interface Formatter {
    String format(String text);
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        System.out.println("=== Functional Interfaces and Lambda Expressions ===");

        // Lambda expression for addition
        Operation add = (a, b) -> a + b;
        System.out.println("Add 5 + 3 = " + add.execute(5, 3));

        // Lambda expression for subtraction
        Operation subtract = (a, b) -> a - b;
        System.out.println("Subtract 5 - 3 = " + subtract.execute(5, 3));

        // Lambda expression for multiplication
        Operation multiply = (a, b) -> a * b;
        System.out.println("Multiply 5 * 3 = " + multiply.execute(5, 3));

        // Lambda expression for division
        Operation divide = (a, b) -> a / b;
        System.out.println("Divide 5 / 3 = " + divide.execute(5, 3));

        System.out.println("\n=== Formatter Functional Interface ===");

        // Convert to uppercase
        Formatter uppercase = text -> text.toUpperCase();
        System.out.println("Uppercase: " + uppercase.format("hello world"));

        // Convert to lowercase
        Formatter lowercase = text -> text.toLowerCase();
        System.out.println("Lowercase: " + lowercase.format("HELLO WORLD"));

        // Add prefix and suffix
        Formatter withBorder = text -> "*** " + text + " ***";
        System.out.println("With border: " + withBorder.format("Java"));

        // Reverse text
        Formatter reverse = text -> new StringBuilder(text).reverse().toString();
        System.out.println("Reversed: " + reverse.format("Hello"));

        System.out.println("\n=== Passing Lambda as Argument ===");

        int result1 = calculate(10, 5, add);
        int result2 = calculate(10, 5, subtract);
        int result3 = calculate(10, 5, multiply);

        System.out.println("Calculate with add: " + result1);
        System.out.println("Calculate with subtract: " + result2);
        System.out.println("Calculate with multiply: " + result3);
    }

    // Method that accepts a functional interface as parameter
    static int calculate(int a, int b, Operation op) {
        return op.execute(a, b);
    }
}
