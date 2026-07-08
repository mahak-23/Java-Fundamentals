# strings

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md)

Read each `.java` file — concepts, examples, and run flow are in the file header comments.

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

→ [04_arrays/_README.md](../04_arrays/_README.md)
