# exception_handling

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md)

In simple terms: when something goes wrong at runtime (bad input, division by zero, missing file), Java throws an **exception**. You use `try` / `catch` so the program can handle the error instead of stopping abruptly.

Exception handling is essential when reading input, parsing data, or dealing with user actions.

## How to create and use exceptions

### Basic try-catch-finally

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("Cleanup code");
}
```

### Throw your own exception

```java
public static void validateAge(int age) throws IllegalArgumentException {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be 18 or above");
    }
}
```

### Create a custom exception

```java
class InvalidAgeException extends Exception {
    InvalidAgeException(String message) {
        super(message);
    }
}
```

## Keywords
- `try` - contains code that may fail
- `catch` - handles the exception
- `finally` - always executes
- `throw` - raises an exception manually
- `throws` - declares that a method may throw an exception

## Common exceptions
- `ArithmeticException` - invalid arithmetic
- `NullPointerException` - null reference
- `ArrayIndexOutOfBoundsException` - invalid index
- `NumberFormatException` - invalid numeric parsing
- `IOException` - file and input/output problems

## Best practices
- Catch specific exceptions first.
- Avoid swallowing exceptions silently.
- Use exceptions for exceptional situations, not normal control flow.
- Clean up resources in `finally` or use try-with-resources.

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| `try` / `catch` block | O(1) | O(1) |
| Throwing an exception | O(1) | O(1) |
| Propagating exception stack | O(depth) | O(depth) |

## Files

| File | Concept |
|------|---------|
| `ExceptionHandlingExample.java` | try-catch-finally, common exceptions |
| `CustomExceptionExample.java` | Custom exceptions, `throw`, `throws` |

## Interview notes
- Checked exceptions must be handled or declared; unchecked exceptions usually extend `RuntimeException`.
- `throw` triggers an exception immediately; `throws` announces the possibility.
- `finally` is useful for closing streams, files, and database resources.

## Next module

→ [03_oops/_README.md](../03_oops/_README.md)
