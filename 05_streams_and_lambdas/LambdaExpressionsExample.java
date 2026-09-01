package streams_and_lambdas;

import java.util.*;
import java.util.function.*;

public class LambdaExpressionsExample {
    public static void main(String[] args) {
        System.out.println("=== Lambda Expressions ===");

        // Example 1: Simple lambda with no parameters
        System.out.println("Example 1: No Parameters");
        Runnable task = () -> System.out.println("Task executed");
        task.run();
        System.out.println();

        // Example 2: Lambda with single parameter
        System.out.println("Example 2: Single Parameter");
        Consumer<String> greet = name -> System.out.println("Hello, " + name);
        greet.accept("Alice");
        greet.accept("Bob");
        System.out.println();

        // Example 3: Lambda with multiple parameters
        System.out.println("Example 3: Multiple Parameters");
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("Add 5 + 3 = " + add.apply(5, 3));
        System.out.println();

        // Example 4: Lambda with multiple statements
        System.out.println("Example 4: Multiple Statements");
        Consumer<Integer> printNumber = num -> {
            System.out.println("Number: " + num);
            System.out.println("Square: " + (num * num));
        };
        printNumber.accept(5);
        System.out.println();

        // Example 5: Lambda with Predicate (returns boolean)
        System.out.println("Example 5: Predicate");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));
        System.out.println();

        // Example 6: Lambda with Function (transform input to output)
        System.out.println("Example 6: Function");
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        System.out.println("Length of 'Java': " + stringLength.apply("Java"));
        System.out.println();

        // Example 7: Processing a list with lambda
        System.out.println("Example 7: Processing List");
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango");
        System.out.println("Fruits: " + fruits);
        fruits.forEach(fruit -> System.out.println("  - " + fruit));
    }
}
