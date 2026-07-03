/*
Prefix sum construction runs in O(n) time and O(n) extra space.
*/

class PrefixSum{
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] prefixSum = new int[arr.length];
        
        // Calculate prefix sum
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        
        // Print the prefix sum array
        System.out.println("Prefix Sum Array:");
        for (int i = 0; i < prefixSum.length; i++) {
            System.out.print(prefixSum[i] + " ");
        }
    }
}