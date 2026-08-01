# Regular Expressions (Regex)

Regular expressions are patterns used to match, search, validate, and transform text. They are commonly used for email validation, password rules, and parsing user input.

## How to create and use regex in Java

### Create a pattern

```java
Pattern pattern = Pattern.compile("\\d+");
```

### Find matches

```java
Matcher matcher = pattern.matcher("Price is 100 and quantity is 50");
while (matcher.find()) {
    System.out.println(matcher.group());
}
```

### Validate a whole string

```java
String email = "user@example.com";
boolean valid = email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
```

### Replace matches

```java
String text = "Hello123World456";
String result = text.replaceAll("[0-9]+", "#");
```

## Common patterns
- `\d` - a digit
- `\w` - a word character
- `\s` - whitespace
- `.` - any character
- `*` - zero or more
- `+` - one or more
- `?` - zero or one
- `{n}` - exactly n times
- `[abc]` - one of a, b, c

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| `matches()` / `find()` | O(n) average | O(1) |
| `replaceAll()` | O(n) average | O(n) |
| Compiling a pattern | O(pattern length) | O(pattern length) |

## Interview notes
- Use `matches()` when the whole string must fit the pattern.
- Use `find()` to search for a pattern inside a larger string.
- Use `replaceAll()` when you want to transform matching text.
- Regex is especially useful for validation and parsing input.
