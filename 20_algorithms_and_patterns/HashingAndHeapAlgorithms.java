import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HashingAndHeapAlgorithms {
    public static int[] twoSumHash(int[] nums, int target) {
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

    public static List<List<String>> groupAnagrams(List<String> words) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            java.util.Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("twoSumHash([2,7,11,15], 9) => [" + twoSumHash(new int[]{2, 7, 11, 15}, 9)[0] + ", " + twoSumHash(new int[]{2, 7, 11, 15}, 9)[1] + "]");
        System.out.println("groupAnagrams([eat, tea, tan, ate, nat, bat]) => " + groupAnagrams(java.util.Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat")));
        System.out.println("topKFrequent([1,1,1,2,2,3], 2) => " + topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
    }
}
