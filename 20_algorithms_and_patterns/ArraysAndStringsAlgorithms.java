import java.util.HashMap;
import java.util.Map;

public class ArraysAndStringsAlgorithms {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static int maxSubarraySum(int[] nums) {
        int current = nums[0];
        int best = nums[0];

        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]);
            best = Math.max(best, current);
        }

        return best;
    }

    public static int longestUniqueSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (lastSeen.containsKey(ch)) {
                left = Math.max(left, lastSeen.get(ch) + 1);
            }
            lastSeen.put(ch, right);
            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        int[] counts = new int[26];
        for (int i = 0; i < a.length(); i++) {
            counts[a.charAt(i) - 'a']++;
            counts[b.charAt(i) - 'a']--;
        }

        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("binarySearch([1,3,5,7], 5) => " + binarySearch(new int[]{1, 3, 5, 7}, 5));
        System.out.println("twoSum([2,7,11,15], 9) => [" + twoSum(new int[]{2, 7, 11, 15}, 9)[0] + ", " + twoSum(new int[]{2, 7, 11, 15}, 9)[1] + "]");
        System.out.println("maxSubarraySum([-2,1,-3,4,-1,2,1,-5,4]) => " + maxSubarraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println("longestUniqueSubstring(abcabcbb) => " + longestUniqueSubstring("abcabcbb"));
        System.out.println("isAnagram( listen, silent ) => " + isAnagram("listen", "silent"));
    }
}
