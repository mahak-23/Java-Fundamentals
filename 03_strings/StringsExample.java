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

        System.out.println("\n=== String Pool and intern() ===");
        String heapString = new String("World");
        String pooledString = "World";
        System.out.println("heapString == pooledString: " + (heapString == pooledString)); // false
        String interned = heapString.intern(); // moves reference to pool if not already there
        System.out.println("interned == pooledString: " + (interned == pooledString)); // true
        System.out.println("Use intern() rarely; prefer .equals() for content comparison.");
        
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
        // Compile-time concatenation: compiler joins literals into one string in the pool
        String concat1 = "Hello" + " " + "World";
        System.out.println("Compile-time literal concat: " + concat1);

        // Runtime concatenation: creates new String objects (slow in loops — use StringBuilder)
        String name = "Java";
        String concat2 = "Hello " + name;
        System.out.println("Runtime concat with variable: " + concat2);

        String concat3 = "Number: " + 42;
        System.out.println("String + int: " + concat3);

        String concat4 = text.concat(" - ").concat("Java");
        System.out.println("Using concat method: " + concat4);
        
        System.out.println("\n=== Trim and Case ===");
        String withSpaces = "  Hello World  ";
        System.out.println("Original: '" + withSpaces + "'");
        System.out.println("Trimmed: '" + withSpaces.trim() + "'");
    }
}
