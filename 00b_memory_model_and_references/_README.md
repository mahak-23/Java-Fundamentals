# Memory Model and References

> **Prerequisites:** [00_java_setup_and_environment](../00_java_setup_and_environment/_README.md)

This module explains one of the most important beginner concepts in Java: how variables, objects, and references behave in memory.

## Why this matters

Many bugs come from misunderstanding memory:

- primitive values vs object references
- stack vs heap
- pass-by-value behavior in Java
- `==` vs `.equals()`
- why strings are immutable

## Stack vs heap

Java uses memory regions for different kinds of data:

- Stack: local variables and method call data
- Heap: objects created with `new`

```java
int number = 10;      // primitive, stored on stack
String text = "Java"; // reference on stack, object on heap
```

## Primitive types

Primitive values are stored directly in the variable:

```java
int a = 10;
int b = a;
b = 20;
System.out.println(a); // 10
```

Changing `b` does not affect `a` because they hold separate values.

## Reference types

Reference types point to objects in heap memory:

```java
String s1 = new String("Java");
String s2 = s1;
s2 = "Python";

System.out.println(s1); // Java
System.out.println(s2); // Python
```

Here, `s1` and `s2` are references, not the objects themselves. Reassigning `s2` changes only the reference variable.

## Pass-by-value in Java

Java passes arguments by value, even for objects.

```java
public static void changeValue(int x) {
    x = 100;
}

public static void changeObject(StringBuilder builder) {
    builder.append("!");
}
```

The primitive value is copied, but the object reference is also copied. That means you can mutate an object through the reference, but you cannot reassign the caller's original reference from inside a method.

## `==` vs `.equals()`

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a == b);       // false
System.out.println(a.equals(b)); // true
```

`==` compares references; `.equals()` compares actual content for most objects.

## String immutability

Strings are immutable in Java:

```java
String s = "Hello";
s = s + " Java";
```

This does not modify the original object; it creates a new string.

## Why `StringBuilder` exists

```java
StringBuilder builder = new StringBuilder();
builder.append("Hello");
builder.append(" Java");
System.out.println(builder.toString());
```

`StringBuilder` is mutable and efficient for repeated string modifications.

## Engineer checklist

- Know the difference between stack and heap
- Understand primitive vs reference types
- Know why Java uses pass-by-value semantics
- Understand `==`, `.equals()`, and immutability
- Be comfortable choosing `String` vs `StringBuilder`

## Files

| File | Concept |
|------|---------|
| `MemoryModelExample.java` | Stack, heap, references, immutability |

## Interview questions

1. What is the difference between stack and heap?
2. Why is Java pass-by-value even when objects are passed?
3. Why are strings immutable?

## Next module

→ [01_java_basics/_README.md](../01_java_basics/_README.md)
