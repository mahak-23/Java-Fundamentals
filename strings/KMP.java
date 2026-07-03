/*
KMP pattern matching runs in O(n + m) time and O(m) space.
*/

public class KMP {
    public static void main(String[] args) {
        String text = "ababcababcabc";
        String pattern = "abc";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        
        int[] lps = computeLPSArray(pattern);
        System.out.println("LPS Array: ");
        for (int i : lps) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        int index = KMPSearch(text, pattern, lps);
        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found in the text.");
        }
    }

    private static int[] computeLPSArray(String pattern) {
        int length = 0;
        int i = 1;
        int[] lps = new int[pattern.length()];
        
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }

    private static int KMPSearch(String text, String pattern, int[] lps) {
        int i = 0; // index for text
        int j = 0; // index for pattern
        
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                
                if (j == pattern.length()) {
                    return i - j; // Pattern found at index
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return -1; // Pattern not found
    }
}