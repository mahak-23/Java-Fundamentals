# stack

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [05_collections](../05_collections/_README.md)

LIFO structure — see `StackInterviewProblems.java` header for each algorithm explained.

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
