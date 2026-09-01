# stack

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [07_collections](../07_collections/_README.md)

A stack is a LIFO (last in, first out) data structure. It is commonly used for function calls, undo operations, expression evaluation, and balancing symbols.

## How to create a stack in Java

In Java, the easiest way is usually `ArrayDeque`:

```java
import java.util.ArrayDeque;

ArrayDeque<Integer> stack = new ArrayDeque<>();
```

## Common operations

### 1. Push / add

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

### 2. Pop / remove

```java
int value = stack.pop();
```

### 3. Peek / top

```java
int top = stack.peek();
```

### 4. Check size and emptiness

```java
boolean empty = stack.isEmpty();
int size = stack.size();
```

### 5. CRUD-style stack workflow

```java
stack.push(1);      // create
stack.push(2);
int topValue = stack.peek(); // read
int removed = stack.pop();   // delete
```

## Example workflow

```java
stack.push(1);
stack.push(2);
stack.push(3);

System.out.println(stack.peek()); // 3
System.out.println(stack.pop());  // 3
System.out.println(stack.peek()); // 2
```

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Push | O(1) | O(1) |
| Pop | O(1) | O(1) |
| Peek | O(1) | O(1) |
| Search | O(n) | O(1) |

## Files

| File | Content |
|------|---------|
| `StackOperations.java` | push, pop, peek basics |
| `StackInterviewProblems.java` | Brackets, next greater, postfix, sort stack |

## Algorithms in StackInterviewProblems

| Method | Pattern | Example |
|--------|---------|---------|
| `isValidParentheses` | Stack matching | `"()[]{}"` → true |
| `nextGreaterElements` | Monotonic stack | `[4,5,2,25]` → `[5,25,-1,-1]` |
| `evaluatePostfix` | Operand stack | `"2 3 1 * + 9 -"` → -4 |
| `sortStack` | Temp stack insertion | Sort without array |

## Interview questions

1. Stack vs recursion call stack?
2. Why `ArrayDeque` over legacy `Stack`?
3. What is monotonic stack used for?

## Next module

→ [07_queue/_README.md](../07_queue/_README.md)
