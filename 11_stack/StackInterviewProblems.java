package stack;

/*
 * =============================================================================
 * Stack Interview Problems
 * =============================================================================
 *
 * ALGORITHMS IN THIS FILE:
 *
 * 1. isValidParentheses — stack matches opening/closing brackets
 *    Example: "()[]{}" → true,  "([)]" → false
 *    Time O(n), Space O(n)
 *
 * 2. nextGreaterElements — monotonic stack (decreasing indices)
 *    Example: [4,5,2,25] → [5,25,-1,-1]
 *    For each element, find next element to its right that is greater.
 *
 * 3. evaluatePostfix — stack evaluates "2 3 1 * + 9 -" → -4
 *    Operands pushed; operator pops two, pushes result.
 *
 * 4. sortStack — use temp stack to insert each element in sorted order
 *
 * =============================================================================
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class StackInterviewProblems {
    public static void main(String[] args) {
        System.out.println("=== Balanced parentheses ===");
        System.out.println("()[]{} => " + isValidParentheses("()[]{}"));
        System.out.println("([)] => " + isValidParentheses("([)]"));

        System.out.println("\n=== Next greater element ===");
        int[] values = {4, 5, 2, 25};
        System.out.println("Input: " + Arrays.toString(values));
        System.out.println("Next greater: " + Arrays.toString(nextGreaterElements(values)));

        System.out.println("\n=== Evaluate postfix expression ===");
        String expression = "2 3 1 * + 9 -";
        System.out.println(expression + " = " + evaluatePostfix(expression));

        System.out.println("\n=== Sort stack using another stack ===");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        sortStack(stack);
        System.out.println("Sorted stack: " + stack);
    }

    public static boolean isValidParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');

        for (char ch : s.toCharArray()) {
            if (pairs.containsValue(ch)) {
                stack.push(ch);
            } else if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(ch)) {
                    return false;
                }
            } else {
                // ignore other characters or add handling for invalid input
            }
        }
        return stack.isEmpty();
    }

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        return result;
    }

    public static int evaluatePostfix(String expression) {
        Deque<Integer> stack = new ArrayDeque<>();
        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int right = stack.pop();
                int left = stack.pop();
                stack.push(applyOperator(left, right, token));
            }
        }
        return stack.pop();
    }

    private static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int applyOperator(int left, int right, String operator) {
        return switch (operator) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    public static void sortStack(Deque<Integer> stack) {
        Deque<Integer> temp = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            int current = stack.pop();
            while (!temp.isEmpty() && temp.peek() > current) {
                stack.push(temp.pop());
            }
            temp.push(current);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
}
