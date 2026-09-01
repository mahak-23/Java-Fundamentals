/*
Stream processing over n items is typically O(n) time and may use O(n) space for intermediate results.
*/

package streams_and_lambdas;

import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    String department;
    int score;

    public Student(String name, String department, int score) {
        this.name = name;
        this.department = department;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " (" + department + ": " + score + ")";
    }
}

public class AdvancedStreamsExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", "CSE", 95),
            new Student("Bob", "ECE", 88),
            new Student("Charlie", "CSE", 92),
            new Student("Diana", "MECH", 85),
            new Student("Eve", "ECE", 90),
            new Student("Frank", "CSE", 87)
        );

        System.out.println("=== Advanced Stream Operations ===");

        // Example 1: Sorting by score (descending)
        System.out.println("Example 1: Sort by score (descending)");
        students.stream()
                .sorted((s1, s2) -> Integer.compare(s2.score, s1.score))
                .forEach(System.out::println);
        System.out.println();

        // Example 2: Grouping by department
        System.out.println("Example 2: Group by department");
        Map<String, List<Student>> byDept = students.stream()
                .collect(Collectors.groupingBy(s -> s.department));
        byDept.forEach((dept, stds) -> {
            System.out.println(dept + ":");
            stds.forEach(s -> System.out.println("  - " + s.name + ": " + s.score));
        });
        System.out.println();

        // Example 3: Average score by department
        System.out.println("Example 3: Average score by department");
        Map<String, Double> avgScore = students.stream()
                .collect(Collectors.groupingBy(
                    s -> s.department,
                    Collectors.averagingDouble(s -> s.score)
                ));
        avgScore.forEach((dept, avg) -> System.out.println(dept + ": " + String.format("%.2f", avg)));
        System.out.println();

        // Example 4: Filter and map
        System.out.println("Example 4: CSE students with score > 90");
        students.stream()
                .filter(s -> s.department.equals("CSE") && s.score > 90)
                .map(s -> s.name + " (" + s.score + ")")
                .forEach(System.out::println);
        System.out.println();

        // Example 5: Partition into two groups (CSE vs Others)
        System.out.println("Example 5: Partition by department (CSE vs Others)");
        Map<Boolean, List<Student>> partitioned = students.stream()
                .collect(Collectors.partitioningBy(s -> s.department.equals("CSE")));
        System.out.println("CSE Students:");
        partitioned.get(true).forEach(s -> System.out.println("  - " + s.name));
        System.out.println("Other Students:");
        partitioned.get(false).forEach(s -> System.out.println("  - " + s.name));
        System.out.println();

        // Example 6: Get names of top scorers
        System.out.println("Example 6: Names of top 2 scorers");
        students.stream()
                .sorted((s1, s2) -> Integer.compare(s2.score, s1.score))
                .limit(2)
                .map(s -> s.name)
                .forEach(System.out::println);
        System.out.println();

        // Example 7: Collect to custom string
        System.out.println("Example 7: Join names with comma");
        String names = students.stream()
                .map(s -> s.name)
                .collect(Collectors.joining(", "));
        System.out.println("All students: " + names);
        System.out.println();

        // Example 8: Method reference
        System.out.println("Example 8: Using method references");
        System.out.print("All students: ");
        students.forEach(System.out::println);
    }
}
