package arrays;

/*
 * =============================================================================
 * Prefix Sum — Range Sum Queries in O(1)
 * =============================================================================
 *
 * PROBLEM
 *   Answer "sum of arr[l..r]" many times without re-summing each range.
 *
 * IDEA
 *   prefix[i] = arr[0] + arr[1] + ... + arr[i]
 *   sum(l, r) = prefix[r] - prefix[l-1]   (when l > 0)
 *
 * EXAMPLE
 *   arr    = [1, 2, 3, 4, 5]
 *   prefix = [1, 3, 6, 10, 15]
 *
 *   sum(1, 3) = prefix[3] - prefix[0] = 10 - 1 = 9  → arr[1]+arr[2]+arr[3] = 2+3+4
 *
 * RUN FLOW
 *   main() → build prefix array in one pass → print result
 *
 * COMPLEXITY:  Build O(n), Query O(1), Space O(n)
 * =============================================================================
 */

class PrefixSum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] prefix = buildPrefixSum(arr);

        System.out.println("Array:  " + java.util.Arrays.toString(arr));
        System.out.println("Prefix: " + java.util.Arrays.toString(prefix));

        int l = 1, r = 3;
        System.out.println("Sum [" + l + ".." + r + "] = " + rangeSum(prefix, l, r));
    }

    static int[] buildPrefixSum(int[] arr) {
        int[] prefix = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        return prefix;
    }

    static int rangeSum(int[] prefix, int l, int r) {
        if (l == 0) return prefix[r];
        return prefix[r] - prefix[l - 1];
    }
}
