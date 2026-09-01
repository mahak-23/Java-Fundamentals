/*
Simple regex matching is typically O(n); more complex patterns may be slower.
*/

package regex;

import java.util.regex.*;

public class RegexExample {
    public static void main(String[] args) {
        System.out.println("=== Regex Pattern Matching ===");

        // Example 1: Check if string contains digits
        System.out.println("Example 1: Contains digits");
        String text1 = "Hello123World";
        System.out.println("Text: " + text1);
        System.out.println("Contains digits: " + text1.matches(".*[0-9].*"));
        System.out.println("Matches only digits: " + text1.matches("[0-9]+"));
        System.out.println();

        // Example 2: Validate email address
        System.out.println("Example 2: Email Validation");
        String[] emails = {"user@example.com", "invalid.email", "test@domain.co.uk"};
        String emailRegex = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        for (String email : emails) {
            boolean isValid = email.matches(emailRegex);
            System.out.println(email + " -> Valid: " + isValid);
        }
        System.out.println();

        // Example 3: Validate phone number
        System.out.println("Example 3: Phone Number Validation");
        String[] phones = {"9876543210", "123456", "98-765-43210"};
        String phoneRegex = "[0-9]{10}";

        for (String phone : phones) {
            boolean isValid = phone.matches(phoneRegex);
            System.out.println(phone + " -> Valid: " + isValid);
        }
        System.out.println();

        // Example 4: Replace patterns
        System.out.println("Example 4: Replace Patterns");
        String text2 = "Price is 100 and quantity is 50";
        String replaced = text2.replaceAll("[0-9]+", "X");
        System.out.println("Original: " + text2);
        System.out.println("Replaced: " + replaced);
        System.out.println();

        // Example 5: Remove special characters
        System.out.println("Example 5: Remove Special Characters");
        String text3 = "Hello@World#123!";
        String cleaned = text3.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println("Original: " + text3);
        System.out.println("Cleaned: " + cleaned);
        System.out.println();

        // Example 6: Split using regex
        System.out.println("Example 6: Split Using Regex");
        String text4 = "apple,banana;orange:grape";
        String[] fruits = text4.split("[,;:]");
        System.out.println("Text: " + text4);
        System.out.println("Split result:");
        for (String fruit : fruits) {
            System.out.println("  - " + fruit);
        }
    }
}
