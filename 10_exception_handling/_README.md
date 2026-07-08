# Exception Handling

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
Exception handling is the mechanism Java provides to respond to runtime errors without crashing the whole application.

## Core idea
A method can signal an error using an exception. Calling code can either handle it or pass it further up.

## Basic example
```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("Cleanup code");
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

## Custom exception example
```java
class InvalidAgeException extends Exception {
    InvalidAgeException(String message) {
        super(message);
    }
}
```

## Best practices
- Catch specific exceptions first.
- Avoid swallowing exceptions silently.
- Use exceptions for exceptional situations, not normal control flow.
- Clean up resources in `finally` or use try-with-resources.

## Interview notes
- Checked exceptions must be handled or declared; unchecked exceptions usually extend `RuntimeException`.
- `throw` triggers an exception immediately; `throws` announces the possibility.
- `finally` is useful for closing streams, files, and database resources.
