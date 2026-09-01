# strings

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md)

Strings are sequences of characters and are one of the most common Java data types. They are immutable, but you can build or transform them efficiently using `StringBuilder`.

## How to create strings in Java

```java
String name = "Java";
String message = new String("Hello");
```

## Common operations

### Create and initialize

```java
String name = "Java";
String message = new String("Hello");
```

### Concatenation

```java
String fullName = name + " Developer";
```

### Comparison

```java
System.out.println(name.equals("Java"));
System.out.println(name == "Java");
```

### CRUD-style string operations

```java
String text = "hello";
text = text + " world";      // create/update
String upper = text.toUpperCase(); // read/transform
text = text.replace("h", "H"); // update
text = text.substring(0, 5); // read slice
```

### StringBuilder for repeated changes

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" World");
String result = sb.toString();
```

### Useful methods

```java
name.length();
name.toUpperCase();
name.substring(0, 2);
name.indexOf("a");
name.trim();
```

## Engineer checklist

- Know how strings are immutable and how `StringBuilder` helps with repeated updates
- Be comfortable with `equals()`, `==`, `substring()`, `replace()`, `split()`, and `trim()`
- Understand when to use `StringBuilder` instead of repeated concatenation

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Length / index access | O(1) | O(1) |
| Concatenation with `+` | O(n + m) | O(n + m) |
| `StringBuilder.append()` | O(1) amortized | O(1) |
| Substring / split | O(n) | O(n) |
| Search with `contains` / `indexOf` | O(n) | O(1) |

## Files

| File | Topic | Key idea |
|------|-------|----------|
| `StringsExample.java` | String pool, immutability | `==` vs `.equals()`, `intern()` |
| `StringBuilderExample.java` | Mutable strings | Use in loops instead of `+` |
| `PrintFormattingExample.java` | printf, format specifiers | `%s`, `%d`, `%.2f`, `toString()` |
| `PalindromeExample.java` | Two-pointer | Start/end pointers move inward |
| `KMP.java` | **Advanced** pattern matching | LPS array avoids text backtracking |

## KMP (Knuth-Morris-Pratt) — summary

**Problem:** Find pattern in text faster than naive O(n×m).

**LPS array:** For each position in pattern, store length of longest proper prefix that is also a suffix.

```
pattern = "abc"  →  LPS = [0, 0, 0]
text    = "ababcababcabc"
```

**On mismatch:** Move pattern pointer using LPS instead of restarting text from scratch.

**Complexity:** O(n + m) time, O(m) space. See `KMP.java` for full walkthrough.

## Interview questions

1. Why are strings immutable? What happens with `toUpperCase()`?
2. When use `StringBuilder` instead of `+`?
3. `==` vs `.equals()` — when is each correct?
4. How does two-pointer check palindrome in O(n)?

## Next module

→ [08_arrays/_README.md](../08_arrays/_README.md)
