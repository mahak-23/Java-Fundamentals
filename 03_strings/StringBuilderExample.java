/*
StringBuilder appends are amortized O(1) per append and O(n) overall for many operations.
Space is O(n) for the built content.
*/

package strings;

public class StringBuilderExample {
    public static void main(String[] args) {
        System.out.println("=== StringBuilder Basics ===");
        
        // Create a StringBuilder
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("Initial: " + sb);
        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());
        
        System.out.println("\n=== Append ===");
        sb.append(" ");
        sb.append("World");
        System.out.println("After append: " + sb);
        
        System.out.println("\n=== Insert ===");
        sb.insert(5, "Beautiful");
        System.out.println("After insert at 5: " + sb);
        
        System.out.println("\n=== Delete and Replace ===");
        sb.delete(5, 14); // Remove "Beautiful"
        System.out.println("After delete: " + sb);
        
        sb.replace(6, 11, "Java");
        System.out.println("After replace: " + sb);
        
        System.out.println("\n=== Reverse ===");
        StringBuilder reverse = new StringBuilder("Hello");
        System.out.println("Original: " + reverse);
        System.out.println("Reversed: " + reverse.reverse());
        
        System.out.println("\n=== StringBuilder in a Loop ===");
        StringBuilder result = new StringBuilder();
        System.out.println("Building string in a loop:");
        for (int i = 1; i <= 5; i++) {
            result.append(i).append(" ");
        }
        System.out.println("Result: " + result);
        
        System.out.println("\n=== Performance Comparison ===");
        // Note: "Hello" + " World" at compile time becomes one literal (fast).
        // Concatenating inside a loop at runtime creates many new String objects (slow).

        // String concatenation (creates many intermediate objects)
        long startTime = System.currentTimeMillis();
        String concat = "";
        for (int i = 0; i < 10000; i++) {
            concat += i;
        }
        long concatTime = System.currentTimeMillis() - startTime;
        System.out.println("String concatenation time: " + concatTime + "ms");
        
        // StringBuilder (efficient)
        startTime = System.currentTimeMillis();
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            build.append(i);
        }
        long builderTime = System.currentTimeMillis() - startTime;
        System.out.println("StringBuilder time: " + builderTime + "ms");
        System.out.println("StringBuilder is faster by: " + (concatTime - builderTime) + "ms");
        
        System.out.println("\n=== Character Counting ===");
        StringBuilderExample analyzer = new StringBuilderExample();
        analyzer.analyzeString("Hello World!");
        analyzer.analyzeString("Java123@Programming");
    }

    private void analyzeString(String text) {
        int letters = 0;
        int digits = 0;
        int spaces = 0;
        int special = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                letters++;
            } else if (Character.isDigit(ch)) {
                digits++;
            } else if (Character.isWhitespace(ch)) {
                spaces++;
            } else {
                special++;
            }
        }

        System.out.println("Text: " + text);
        System.out.println("Letters: " + letters + ", Digits: " + digits + ", Spaces: " + spaces + ", Special: " + special);
    }
}
