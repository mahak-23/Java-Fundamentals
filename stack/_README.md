# Stack

> **Difficulty:** Intermediate | **Prerequisites:** [java basics](../java%20basics/_README.md), [collections](../collections/_README.md) | **Time:** 2-3 hours | **Topics:** LIFO, bracket matching, DFS, recursion

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
A stack is a linear data structure that follows the Last-In-First-Out (LIFO) rule. The last element pushed into the stack is the first one removed.

## Why stacks are useful
- They are ideal for undo/redo systems.
- They help with expression evaluation and parsing.
- They are commonly used in recursion, DFS, and browser history.
- They are helpful for balancing symbols and validating syntax.

## Stack operations
- `push(value)` adds an element to the top.
- `pop()` removes and returns the top element.
- `peek()` or `top()` returns the top element without removing it.
- `isEmpty()` checks whether the stack is empty.
- `size()` returns the number of elements.

## Stack operations with examples
```java
Deque<Integer> stack = new ArrayDeque<>();

// Push: add to top
stack.push(10); // Stack: [10]
stack.push(20); // Stack: [10, 20]
stack.push(30); // Stack: [10, 20, 30]
System.out.println("After pushes: " + stack); // Output: [30, 20, 10]

// Peek: view top without removing
System.out.println(stack.peek()); // Output: 30
System.out.println("Stack unchanged: " + stack); // Output: [30, 20, 10]

// Pop: remove from top
int popped = stack.pop();
System.out.println("Popped: " + popped); // Output: 30
System.out.println("Stack after pop: " + stack); // Output: [20, 10]

// Check properties
System.out.println(stack.size()); // Output: 2
System.out.println(stack.isEmpty()); // Output: false

// Clear
stack.clear();
System.out.println(stack.isEmpty()); // Output: true
```

## Time complexity
- `push` : O(1)
- `pop` : O(1)
- `peek` : O(1)
- `isEmpty` : O(1)
- `size` : O(1)

## Common stack problems
### 1. Balanced parentheses
Use a stack to push opening brackets and pop when a matching closing bracket arrives.

```java
public boolean isBalanced(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char ch : s.toCharArray()) {
        if (ch == '(' || ch == '{' || ch == '[') {
            stack.push(ch);
        } else {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if ((ch == ')' && top != '(') ||
                (ch == '}' && top != '{') ||
                (ch == ']' && top != '[')) {
                return false;
            }
        }
    }
    return stack.isEmpty();
}
```

### 2. Reverse a string using a stack
```java
public String reverse(String input) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char ch : input.toCharArray()) {
        stack.push(ch);
    }

    StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
        result.append(stack.pop());
    }
    return result.toString();
}
```

### 3. Postfix evaluation
```java
public int evaluatePostfix(String expression) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (String token : expression.split("\\s+")) {
        if (token.matches("\\d+")) {
            stack.push(Integer.parseInt(token));
        } else {
            int b = stack.pop();
            int a = stack.pop();
            switch (token) {
                case "+" -> stack.push(a + b);
                case "-" -> stack.push(a - b);
                case "*" -> stack.push(a * b);
                case "/" -> stack.push(a / b);
            }
        }
    }
    return stack.pop();
}
```

## CRUD, size, and iteration notes
- Create: `push()`.
- Read: `peek()`.
- Update: replace the top element by popping and pushing again.
- Delete: `pop()`.
- Use `size()` to check the number of elements.
- Iterate by popping elements or by using a temporary stack.

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(10);
stack.push(20);
System.out.println(stack.peek());
System.out.println(stack.size());
while (!stack.isEmpty()) {
    System.out.println(stack.pop());
}
```

## Interview notes
- A stack is LIFO: the most recently added item is processed first.
- In Java, prefer `Deque` and `ArrayDeque` over the older `Stack` class.
- Always mention edge cases: empty stack, single element, and invalid expressions.
- For coding interviews, explain the idea before writing the implementation.

## Common interview questions
- Validate balanced parentheses
- Evaluate postfix expressions
- Find the next greater element
- Implement a min stack
- Reverse a stack
- Use a stack in DFS and recursion problems

## Quick reference: stack operations

### Core operations (O(1))
- Push: `push(value)` - add to top
- Pop: `pop()` - remove and return top
- Peek: `peek()` - view top without removing
- Check: `isEmpty()`, `size()`
- Clear: `clear()`

### Stack use cases
- Expression evaluation (postfix, prefix)
- Function call stack (recursion)
- Undo/Redo functionality
- Depth-first search (DFS)
- Balanced parentheses validation
- Browser history (back button)
