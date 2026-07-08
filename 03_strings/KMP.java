package strings;

/*
 * =============================================================================
 * KMP (Knuth-Morris-Pratt) — Pattern Matching in a String
 * =============================================================================
 *
 * PROBLEM
 *   Find the first index where pattern appears in text.
 *   Naive approach backtracks in text → O(n × m).
 *   KMP never backtracks text pointer → O(n + m).
 *
 * KEY IDEA — LPS (Longest Proper Prefix which is also Suffix)
 *   For pattern "abcab", when we mismatch at index 4 ('b' vs text char),
 *   we already matched "ab" — reuse that knowledge instead of restarting.
 *
 * EXAMPLE
 *   text    = "ababcababcabc"
 *   pattern = "abc"
 *
 *   LPS for "abc":
 *     index:  0  1  2
 *     char:   a  b  c
 *     lps:    0  0  0   (no proper prefix equals suffix)
 *
 *   Search walk (simplified):
 *     i=0,j=0  match a → i=1,j=1
 *     i=1,j=1  match b → i=2,j=2
 *     i=2,j=2  match c → full match → return i-j = 0? 
 *     (first match at index 2 for second occurrence — see output)
 *
 * RUN FLOW
 *   main()
 *     → computeLPSArray(pattern)  build failure table
 *     → KMPSearch(text, pattern, lps)  scan text once
 *     → print index or "not found"
 *
 * COMPLEXITY
 *   Time:  O(n + m)   n = text length, m = pattern length
 *   Space: O(m)      LPS array
 *
 * =============================================================================
 */

public class KMP {
    public static void main(String[] args) {
        String text = "ababcababcabc";
        String pattern = "abc";

        System.out.println("Text:    " + text);
        System.out.println("Pattern: " + pattern);

        int[] lps = computeLPSArray(pattern);
        System.out.print("LPS:     ");
        for (int val : lps) {
            System.out.print(val + " ");
        }
        System.out.println();

        int index = KMPSearch(text, pattern, lps);
        if (index != -1) {
            System.out.println("Found at index: " + index);
        } else {
            System.out.println("Pattern not found.");
        }
    }

    /*
     * Builds LPS array for pattern.
     * lps[i] = length of longest proper prefix of pattern[0..i] that is also a suffix.
     *
     * Example pattern "abab":
     *   i=0: lps[0]=0
     *   i=1: 'a'=='a' → lps[1]=1
     *   i=2: mismatch → fall back using lps, eventually lps[2]=1
     *   i=3: 'b'=='b' → lps[3]=2
     */
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
                    length = lps[length - 1]; // try shorter prefix
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /*
     * Scans text with pointer i, pattern with pointer j.
     * On match: advance both. When j == pattern.length → found at i-j.
     * On mismatch: if j > 0, j = lps[j-1]; else i++.
     */
    private static int KMPSearch(String text, String pattern, int[] lps) {
        int i = 0;
        int j = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    return i - j;
                }
            } else if (j != 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }
}
