/*
Matcher-based scanning is O(n) for simple patterns and may use O(n) space for captured groups.
*/

package regex;

import java.util.regex.*;

public class MatcherExample {
    public static void main(String[] args) {
        System.out.println("=== Matcher Class ===");

        // Example 1: Finding all digits
        System.out.println("Example 1: Find All Digits");
        String text1 = "Price: 100, Quantity: 50, Discount: 10";
        Pattern pattern1 = Pattern.compile("\\d+");
        Matcher matcher1 = pattern1.matcher(text1);

        System.out.println("Text: " + text1);
        System.out.println("All numbers found:");
        while (matcher1.find()) {
            System.out.println("  - " + matcher1.group() + " at position " + matcher1.start());
        }
        System.out.println();

        // Example 2: Finding words
        System.out.println("Example 2: Find All Words");
        String text2 = "The quick brown fox jumps over the lazy dog";
        Pattern pattern2 = Pattern.compile("\\b\\w+\\b");
        Matcher matcher2 = pattern2.matcher(text2);

        System.out.println("Text: " + text2);
        System.out.println("All words found:");
        int count = 0;
        while (matcher2.find()) {
            System.out.println("  - " + matcher2.group());
            count++;
        }
        System.out.println("Total words: " + count + "\n");

        // Example 3: Finding email addresses
        System.out.println("Example 3: Find All Email Addresses");
        String text3 = "Contact us at support@example.com or sales@company.org";
        Pattern pattern3 = Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcher3 = pattern3.matcher(text3);

        System.out.println("Text: " + text3);
        System.out.println("Emails found:");
        while (matcher3.find()) {
            System.out.println("  - " + matcher3.group());
        }
        System.out.println();

        // Example 4: Check if pattern matches
        System.out.println("Example 4: Check Pattern Match");
        Pattern pattern4 = Pattern.compile("^[A-Z][a-z]+$"); // Starts with capital, rest lowercase
        String[] names = {"Alice", "alice", "ALICE", "Bob123", "Charlie"};

        System.out.println("Pattern: First letter uppercase, rest lowercase");
        for (String name : names) {
            Matcher matcher4 = pattern4.matcher(name);
            System.out.println(name + " -> " + (matcher4.matches() ? "matches" : "does not match"));
        }
        System.out.println();

        // Example 5: Replace using Matcher
        System.out.println("Example 5: Replace Using Matcher");
        String text5 = "Item 1 costs 50, Item 2 costs 75, Item 3 costs 100";
        Pattern pattern5 = Pattern.compile("\\d+");
        Matcher matcher5 = pattern5.matcher(text5);
        String replaced = matcher5.replaceAll("[X]");

        System.out.println("Original: " + text5);
        System.out.println("Replaced: " + replaced);
    }
}
