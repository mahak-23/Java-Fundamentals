/*
Stack push, pop, peek, and size are O(1); iteration over the stack is O(n).
*/

package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.Stack;

public class StackOperations {
    public static void main(String[] args) {
        System.out.println("=== Built-in Stack example ===");
        Stack<String> legacyStack = new Stack<>();
        legacyStack.push("First");
        legacyStack.push("Second");
        legacyStack.push("Third");

        System.out.println("Stack contents: " + legacyStack);
        System.out.println("Peek top element: " + legacyStack.peek());
        System.out.println("Pop top element: " + legacyStack.pop());
        System.out.println("After pop: " + legacyStack);
        System.out.println("Search for 'First': " + legacyStack.search("First"));
        System.out.println("Is empty: " + legacyStack.empty());

        System.out.println("\n=== Deque as stack example ===");
        Deque<Integer> stack = new ArrayDeque<>();
        push(stack, 10);
        push(stack, 20);
        push(stack, 30);

        System.out.println("Stack content: " + stack);
        System.out.println("Top element: " + peek(stack));
        System.out.println("Popped element: " + pop(stack));
        System.out.println("Stack after pop: " + stack);
        System.out.println("Contains 20: " + stack.contains(20));
        System.out.println("Stack size: " + size(stack));
        System.out.println("Is empty: " + isEmpty(stack));

        System.out.println("\n=== Manual stack implementation example ===");
        ArrayStack<String> customStack = new ArrayStack<>(5);
        customStack.push("Alpha");
        customStack.push("Beta");
        customStack.push("Gamma");

        System.out.println("Custom stack size: " + customStack.size());
        System.out.println("Custom stack top: " + customStack.peek());
        System.out.println("Custom stack pop: " + customStack.pop());
        System.out.println("Custom stack empty: " + customStack.isEmpty());
    }

    private static void push(Deque<Integer> stack, int value) {
        stack.push(value);
    }

    private static int pop(Deque<Integer> stack) {
        return stack.pop();
    }

    private static int peek(Deque<Integer> stack) {
        return stack.peek();
    }

    private static int size(Deque<Integer> stack) {
        return stack.size();
    }

    private static boolean isEmpty(Deque<Integer> stack) {
        return stack.isEmpty();
    }

    private static class ArrayStack<T> {
        private final Object[] elements;
        private int top;

        ArrayStack(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be greater than 0");
            }
            this.elements = new Object[capacity];
            this.top = -1;
        }

        void push(T value) {
            if (top == elements.length - 1) {
                throw new StackOverflowError("Stack is full");
            }
            elements[++top] = value;
        }

        T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            @SuppressWarnings("unchecked")
            T value = (T) elements[top];
            elements[top--] = null;
            return value;
        }

        T peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            @SuppressWarnings("unchecked")
            T value = (T) elements[top];
            return value;
        }

        boolean isEmpty() {
            return top == -1;
        }

        int size() {
            return top + 1;
        }
    }
}
