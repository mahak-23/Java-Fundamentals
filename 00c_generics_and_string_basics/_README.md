# Generics and String Basics

> **Prerequisites:** [00b_memory_model_and_references](../00b_memory_model_and_references/_README.md), [01_java_basics](../01_java_basics/_README.md)

This module explains two foundational Java ideas that beginners often misuse or misunderstand: generics and string comparison.

## Generics

Generics allow you to write reusable code while keeping types safe.

```java
List<String> names = new ArrayList<>();
names.add("Alice");
// names.add(10); // compile-time error
```

Without generics, a list could store anything and bugs would appear later.

## Why generics matter

```java
public static <T> T identity(T value) {
    return value;
}

String name = identity("Ada");
Integer count = identity(42);
```

This lets you create reusable methods for different types while preserving type safety.

## Common collection examples

```java
List<Integer> numbers = new ArrayList<>();
Set<String> uniqueWords = new HashSet<>();
Map<String, Integer> scoreMap = new HashMap<>();
```

## String comparison in practice

```java
String a = "Java";
String b = "Java";

System.out.println(a == b);       // true because of string pool
System.out.println(a.equals(b)); // true
```

When strings are created with `new`, the references are different even when the content matches:

```java
String x = new String("Java");
String y = new String("Java");
System.out.println(x == y); // false
System.out.println(x.equals(y)); // true
```

## `StringBuilder` and `StringBuffer`

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" world");
```

- `StringBuilder`: faster, not thread-safe
- `StringBuffer`: synchronized, thread-safe

## Engineer checklist

- Know when to use generics in collections
- Understand why `==` is not enough for strings
- Be able to compare objects by content using `equals()`
- Know when to use `String`, `StringBuilder`, or `StringBuffer`

## Files

| File | Concept |
|------|---------|
| `GenericsAndStringExample.java` | Generics, string equality, builder usage |

## Interview questions

1. What is the purpose of generics?
2. Why does `==` fail for string content comparison?
3. When would you prefer `StringBuilder` to `String`?

## Next module

→ [01_java_basics/_README.md](../01_java_basics/_README.md)
