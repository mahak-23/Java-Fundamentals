/*
Stream processing over n items is typically O(n) time and may use O(n) space for intermediate results.
*/

package streams_and_lambdas;

import java.util.*;
import java.util.stream.*;

public class StreamsExample {
    public static void main(String[] args) {
        System.out.println("=== Stream API ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Example 1: Filter
        System.out.println("Example 1: Filter (even numbers)");
        System.out.print("Result: ");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // Example 2: Map
        System.out.println("Example 2: Map (square each number)");
        System.out.print("Result: ");
        numbers.stream()
               .map(n -> n * n)
               .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // Example 3: Filter and Map
        System.out.println("Example 3: Filter and Map (square of even numbers)");
        System.out.print("Result: ");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .map(n -> n * n)
               .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // Example 4: Reduce (sum)
        System.out.println("Example 4: Reduce (sum all numbers)");
        int sum = numbers.stream()
                         .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum + "\n");

        // Example 5: Collect to List
        System.out.println("Example 5: Collect (create new list of even numbers)");
        List<Integer> evens = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
        System.out.println("Even numbers: " + evens + "\n");

        // Example 6: Distinct
        System.out.println("Example 6: Distinct (remove duplicates)");
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        System.out.print("Distinct: ");
        withDuplicates.stream()
                      .distinct()
                      .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // Example 7: Sorted
        System.out.println("Example 7: Sorted");
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.print("Sorted: ");
        unsorted.stream()
                .sorted()
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // Example 8: Limit
        System.out.println("Example 8: Limit (first 3 elements)");
        System.out.print("Result: ");
        numbers.stream()
               .limit(3)
               .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // Example 9: Count
        System.out.println("Example 9: Count");
        long count = numbers.stream()
                            .filter(n -> n > 5)
                            .count();
        System.out.println("Numbers greater than 5: " + count + "\n");

        // Example 10: Any match, all match, none match
        System.out.println("Example 10: Match operations");
        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
        boolean noNegative = numbers.stream().noneMatch(n -> n < 0);

        System.out.println("Has even number: " + hasEven);
        System.out.println("All even: " + allEven);
        System.out.println("No negative: " + noNegative);
    }
}
