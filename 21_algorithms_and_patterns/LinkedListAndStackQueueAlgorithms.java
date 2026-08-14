package algorithms_and_patterns;

/*
 * Linked list, stack, queue: reverse list, cycle detection, valid parentheses, min stack.
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class LinkedListAndStackQueueAlgorithms {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    public static boolean isValidParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') || (ch == ']' && top != '[') || (ch == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    static class MinStack {
        private final Deque<Integer> values = new ArrayDeque<>();
        private final Deque<Integer> mins = new ArrayDeque<>();

        public void push(int x) {
            values.push(x);
            if (mins.isEmpty() || x <= mins.peek()) {
                mins.push(x);
            }
        }

        public void pop() {
            if (values.isEmpty()) {
                return;
            }
            int removed = values.pop();
            if (!mins.isEmpty() && removed == mins.peek()) {
                mins.pop();
            }
        }

        public int top() {
            return values.peek();
        }

        public int getMin() {
            return mins.peek();
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println("reversed head val => " + reverseList(head).val);

        ListNode cycle = new ListNode(1);
        cycle.next = new ListNode(2);
        cycle.next.next = cycle;
        System.out.println("hasCycle => " + hasCycle(cycle));

        System.out.println("valid parentheses => " + isValidParentheses("()[]{}"));

        MinStack minStack = new MinStack();
        minStack.push(5);
        minStack.push(3);
        minStack.push(8);
        System.out.println("min stack minimum => " + minStack.getMin());
    }
}
