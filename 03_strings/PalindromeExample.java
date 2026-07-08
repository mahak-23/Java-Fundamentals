package strings;

/*
 * =============================================================================
 * Palindrome Check — Two Pointer
 * =============================================================================
 *
 * PROBLEM
 *   Check if a string reads the same forward and backward.
 *
 * IDEA
 *   Two pointers: start at both ends, move inward while chars match.
 *
 * EXAMPLE
 *   "racecar" → start=0/end=6 match ... → palindrome
 *   "hello"   → 'h' vs 'o' mismatch at start → not palindrome
 *
 * RUN FLOW
 *   main() → normalize (optional) → isPalindrome() for each test case
 *
 * COMPLEXITY:  Time O(n), Space O(1)
 * =============================================================================
 */

public class PalindromeExample {
    public static void main(String[] args) {
        String[] testCases = {"madam", "racecar", "hello", "A man a plan a canal Panama"};

        System.out.println("=== Palindrome Check ===");
        for (String text : testCases) {
            boolean result = isPalindrome(normalize(text));
            System.out.println("'" + text + "' -> " + (result ? "palindrome" : "not a palindrome"));
        }

        System.out.println("\n=== Reverse String ===");
        String word = "Java";
        System.out.println("Original: " + word);
        System.out.println("Reversed: " + reverseString(word));
    }

    static String normalize(String str) {
        return str.replaceAll("\\s+", "").toLowerCase();
    }

    static boolean isPalindrome(String str) {
        int start = 0, end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    static String reverseString(String str) {
        char[] chars = str.toCharArray();
        int start = 0, end = chars.length - 1;
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
