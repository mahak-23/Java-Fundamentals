# Strings

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
Strings are one of the most common data types in Java. They are immutable sequences of characters and are heavily used in text processing.

## String basics
```java
String name = "Alice";
String greeting = "Hello " + name;
System.out.println(greeting);
```

## Common operations
### 1. Length
```java
String text = "Java";
System.out.println(text.length()); // Output: 4
```

### 2. Character access
```java
String text = "Java";
System.out.println(text.charAt(0)); // Output: J
System.out.println(text.charAt(3)); // Output: a
```

### 3. Substring
```java
String text = "Hello World";
System.out.println(text.substring(0, 5)); // Output: Hello
System.out.println(text.substring(6)); // Output: World
```

### 4. indexOf and lastIndexOf
```java
String text = "Hello World";
System.out.println(text.indexOf('o')); // Output: 4
System.out.println(text.lastIndexOf('o')); // Output: 7
System.out.println(text.indexOf("World")); // Output: 6
```

### 5. Contains, startsWith, endsWith
```java
String text = "Hello World";
System.out.println(text.contains("World")); // Output: true
System.out.println(text.startsWith("Hello")); // Output: true
System.out.println(text.endsWith("World")); // Output: true
```

### 6. toUpperCase, toLowerCase, trim
```java
String text = "  Hello World  ";
System.out.println(text.toUpperCase()); // Output: HELLO WORLD
System.out.println(text.toLowerCase()); // Output: hello world
System.out.println(text.trim()); // Output: Hello World (no spaces)
```

### 7. Replace and split
```java
String text = "Java is fun";
String updated = text.replace("fun", "awesome"); // Output: Java is awesome
String[] parts = text.split(" "); // Output: [Java, is, fun]
```

### 8. compareTo and equals
```java
String a = "apple";
String b = "banana";
System.out.println(a.equals(b)); // Output: false
System.out.println(a.compareTo(b)); // Output: -1 (a comes before b)
System.out.println(a.equalsIgnoreCase("APPLE")); // Output: true
```

## Immutability
Strings cannot be changed after creation. Any operation that appears to modify a string returns a new string object.

## String pool
```java
String a = "Hello";
String b = "Hello";
System.out.println(a == b);
```

## StringBuilder vs StringBuffer
- `String` - immutable and safe for fixed content
- `StringBuilder` - mutable and faster for frequent modifications
- `StringBuffer` - mutable and thread-safe, but slower

```java
StringBuilder builder = new StringBuilder();
builder.append("Hello"); // "Hello"
builder.append(" World"); // "Hello World"
builder.insert(5, " Beautiful"); // "Hello Beautiful World"
builder.replace(6, 15, "Java"); // "Hello Java World"
builder.deleteCharAt(4); // "HellJava World"
builder.reverse(); // "dlroW avaJlleH"
System.out.println(builder.toString());
System.out.println(builder.length()); // Output: length of reversed string
```

## CRUD, length, and iteration notes
- Strings are not traditional CRUD data structures, but you can still think in terms of create/read/update/delete style operations.
- Create: initialize a string or build one with `StringBuilder`.
- Read: use `charAt()` or `substring()`.
- Update: use `replace()` or `StringBuilder.append()`.
- Delete: use `replace()` or rebuild a new string.
- Use `length()` to get the size of a string.
- Iterate using `for` loops, `charAt()`, or `for-each` over a `char[]`.

```java
String text = "Java";
System.out.println(text.length());
for (int i = 0; i < text.length(); i++) {
    System.out.print(text.charAt(i));
}
```

## Interview notes
- Use `.equals()` to compare string content, not `==`.
- `StringBuilder` is preferred for many modifications in loops.
- Strings are immutable, which helps with safety and reuse.

## Quick reference: string operations

### Basic operations (immutable)
- Length: `length()`
- Access: `charAt(index)`
- Substring: `substring(start, end)`
- Search: `indexOf()`, `lastIndexOf()`, `contains()`
- Compare: `equals()`, `compareTo()`, `equalsIgnoreCase()`
- Case: `toUpperCase()`, `toLowerCase()`
- Trim: `trim()`, `strip()`

### String manipulation
- Replace: `replace()`, `replaceAll()`
- Split: `split()`
- Concatenation: `+` operator or `concat()`
- Check prefix/suffix: `startsWith()`, `endsWith()`
- Reverse: use `StringBuilder` or char array

### StringBuilder operations (mutable)
- Append: `append()`
- Insert: `insert()`
- Delete: `delete()`, `deleteCharAt()`
- Replace: `replace()`
- Reverse: `reverse()`
- Get: `toString()`, `length()`, `charAt()`
