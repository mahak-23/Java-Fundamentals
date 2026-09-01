package queue;

/*
 * =============================================================================
 * Queue Interview Problems
 * =============================================================================
 *
 * ALGORITHMS IN THIS FILE:
 *
 * 1. reverseFirstK — stack reverses first k elements, rest rotated
 *    Example: [10,20,30,40,50], k=3 → [30,20,10,40,50]
 *
 * 2. generateBinaryNumbers — BFS-style: append 0 and 1 to build binary strings
 *    Example: n=5 → 1, 10, 11, 100, 101
 *
 * 3. firstNonRepeatingCharacter — queue + frequency array
 *    Example: "aabc" → 'a' (first char with count 1)
 *
 * 4. slidingWindowMaximum — deque stores indices of useful candidates
 *    Example: [1,3,-1,-3,5,3,6,7], k=3 → [3,3,5,6,7]
 *
 * 5. QueueUsingStacks — enqueue on input stack, dequeue from output stack
 *
 * =============================================================================
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class QueueInterviewProblems {
    public static void main(String[] args) {
        System.out.println("=== Reverse first K elements of a queue ===");
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Original: " + queue);
        reverseFirstK(queue, 3);
        System.out.println("After reversing first 3: " + queue);

        System.out.println("\n=== Generate binary numbers with a queue ===");
        System.out.println(generateBinaryNumbers(10));

        System.out.println("\n=== First non-repeating character in a stream ===");
        String stream = "aabc";
        System.out.println(stream + " => " + firstNonRepeatingCharacter(stream));

        System.out.println("\n=== Sliding window maximum ===");
        int[] values = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(values));
        System.out.println("Window size 3 max: " + Arrays.toString(slidingWindowMaximum(values, 3)));

        System.out.println("\n=== Queue using two stacks ===");
        QueueUsingStacks<Integer> twoStackQueue = new QueueUsingStacks<>();
        twoStackQueue.enqueue(1);
        twoStackQueue.enqueue(2);
        twoStackQueue.enqueue(3);
        System.out.println("Dequeued: " + twoStackQueue.dequeue());
        System.out.println("Front: " + twoStackQueue.peek());
    }

    public static void reverseFirstK(Queue<Integer> queue, int k) {
        if (queue == null || k <= 0 || k > queue.size()) {
            throw new IllegalArgumentException("Invalid queue or k value");
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            stack.push(queue.poll());
        }
        while (!stack.isEmpty()) {
            queue.offer(stack.pop());
        }
        int remaining = queue.size() - k;
        for (int i = 0; i < remaining; i++) {
            queue.offer(queue.poll());
        }
    }

    public static Queue<String> generateBinaryNumbers(int n) {
        Queue<String> result = new LinkedList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("1");

        for (int i = 0; i < n; i++) {
            String current = queue.poll();
            result.offer(current);
            queue.offer(current + "0");
            queue.offer(current + "1");
        }
        return result;
    }

    public static String firstNonRepeatingCharacter(String stream) {
        int[] frequency = new int[256];
        Queue<Character> queue = new LinkedList<>();

        for (char ch : stream.toCharArray()) {
            frequency[ch]++;
            queue.offer(ch);
            while (!queue.isEmpty() && frequency[queue.peek()] > 1) {
                queue.poll();
            }
        }
        return queue.isEmpty() ? "None" : String.valueOf(queue.peek());
    }

    public static int[] slidingWindowMaximum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    private static class QueueUsingStacks<T> {
        private final Deque<T> input;
        private final Deque<T> output;

        QueueUsingStacks() {
            input = new ArrayDeque<>();
            output = new ArrayDeque<>();
        }

        void enqueue(T value) {
            input.push(value);
        }

        T dequeue() {
            moveInputToOutput();
            return output.isEmpty() ? null : output.pop();
        }

        T peek() {
            moveInputToOutput();
            return output.isEmpty() ? null : output.peek();
        }

        boolean isEmpty() {
            return input.isEmpty() && output.isEmpty();
        }

        private void moveInputToOutput() {
            if (output.isEmpty()) {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }
        }
    }
}
