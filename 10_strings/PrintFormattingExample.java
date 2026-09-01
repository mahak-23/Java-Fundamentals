/*
 * PrintStream, String.format, and toString() — pretty printing in Java.
 */

package strings;

public class PrintFormattingExample {
    public static void main(String[] args) {
        System.out.println("=== println vs printf ===");
        System.out.println("Hello using println");
        System.out.printf("Hello using printf%n"); // %n = platform newline

        System.out.println("\n=== Format specifiers ===");
        String name = "Alice";
        int age = 21;
        double score = 92.567;

        // %s = string, %d = int, %.2f = float with 2 decimal places
        System.out.printf("Name: %s, Age: %d, Score: %.2f%n", name, age, score);

        String formatted = String.format("Formatted: %s scored %.1f%%", name, score);
        System.out.println(formatted);

        System.out.println("\n=== toString() on objects ===");
        Student s1 = new Student("Bob", 88);
        Student s2 = new Student("Carol", 95);

        // println calls toString() automatically
        System.out.println(s1);
        System.out.println(s2);

        // Without toString(), you would see something like: Student@1b6d3586
        System.out.println("\nDefault toString (Object class): " + new Object());
    }

    static class Student {
        private final String name;
        private final int score;

        Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', score=" + score + "}";
        }
    }
}
