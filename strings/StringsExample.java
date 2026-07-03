/*
Common string operations such as scanning, searching, and splitting are O(n) time.
Space is O(n) when new strings are created.
*/

package strings;

public class StringsExample {
    public static void main(String[] args) {
        System.out.println("=== String Creation ===");
        
        // String literal (in String pool)
        String s1 = "Hello";
        String s2 = "Hello";
        System.out.println("s1 == s2 (same reference): " + (s1 == s2)); // true
        System.out.println("s1.equals(s2) (same content): " + s1.equals(s2)); // true
        
        // String object (in heap)
        String s3 = new String("Hello");
        System.out.println("s1 == s3 (different objects): " + (s1 == s3)); // false
        System.out.println("s1.equals(s3) (same content): " + s1.equals(s3)); // true
        
        System.out.println("\n=== String Immutability ===");
        String original = "Java";
        String modified = original.toUpperCase();
        System.out.println("Original: " + original); // Java (unchanged)
        System.out.println("Modified: " + modified); // JAVA (new object)
        
        System.out.println("\n=== Common String Methods ===");
        String text = "Hello World";
        
        System.out.println("Text: " + text);
        System.out.println("Length: " + text.length());
        System.out.println("Character at index 0: " + text.charAt(0));
        System.out.println("Substring (0, 5): " + text.substring(0, 5));
        System.out.println("Index of 'World': " + text.indexOf("World"));
        System.out.println("Contains 'Java': " + text.contains("Java"));
        System.out.println("To uppercase: " + text.toUpperCase());
        System.out.println("To lowercase: " + text.toLowerCase());
        System.out.println("Replace 'World' with 'Java': " + text.replace("World", "Java"));
        System.out.println("Starts with 'Hello': " + text.startsWith("Hello"));
        System.out.println("Ends with 'World': " + text.endsWith("World"));
        
        System.out.println("\n=== String Split ===");
        String[] words = text.split(" ");
        System.out.println("Split by space:");
        for (String word : words) {
            System.out.println("  - " + word);
        }
        
        System.out.println("\n=== String Concatenation ===");
        String concat1 = "Hello" + " " + "World";
        System.out.println("String + String + String: " + concat1);
        
        String concat2 = "Number: " + 42;
        System.out.println("String + int: " + concat2);
        
        String concat3 = text.concat(" - ").concat("Java");
        System.out.println("Using concat method: " + concat3);
        
        System.out.println("\n=== Trim and Case ===");
        String withSpaces = "  Hello World  ";
        System.out.println("Original: '" + withSpaces + "'");
        System.out.println("Trimmed: '" + withSpaces.trim() + "'");
    }

    
    public void checkPalindrome(String str) {
        int start=0, end=str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                System.out.println(str + " is not a palindrome.");
                return;
            }
            start++;
            end--;
        }
        System.out.println(str + " is a palindrome.");
    }

    public String reverseString(String str) {
        int start = 0, end = str.length() - 1;
        char[] chars = str.toCharArray();
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }
    // Count vowels, consonants, digits, special characters
    
    public void analyzeString(String str) {
        int vowels = 0, consonants = 0, digits = 0, specialChars = 0;
        
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                if ("aeiouAEIOU".indexOf(c) != -1) {
                    vowels++;
                } else {
                    consonants++;
                }
            } else if (Character.isDigit(c)) {
                digits++;
            } else if (!Character.isWhitespace(c)) {
                specialChars++;
            }
        }
        
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Special Characters: " + specialChars);
    }

}
