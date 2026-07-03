# Regular Expressions (Regex)

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
Regular expressions are patterns used to match, search, validate, and transform text.

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

## Example: validate an email
```java
String email = "user@example.com";
boolean valid = email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
System.out.println(valid);
```

## Example: find all numbers in text
```java
Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher("Price is 100 and quantity is 50");
while (matcher.find()) {
    System.out.println(matcher.group());
}
```

## Example: replace digits
```java
String text = "Hello123World456";
String result = text.replaceAll("[0-9]+", "#");
System.out.println(result);
```

## Interview notes
- Use `matches()` when the whole string must fit the pattern.
- Use `find()` to search for a pattern inside a larger string.
- Use `replaceAll()` when you want to transform matching text.
- Regex is especially useful for validation and parsing input.
