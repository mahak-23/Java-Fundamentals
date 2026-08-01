# java_basics

> **Prerequisites:** None

Java basics are the foundation for every program and data-structure example in this repository. This section teaches how to create simple Java programs and perform everyday operations such as storing values, branching, looping, and calling methods.

## How to create a Java program

Every Java program needs a class and a `main` method:

```java
public class HelloJava {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

## Variables and data types

Java has primitive and reference types. Common primitive types include:

```java
byte b = 10;
short s = 200;
int age = 25;
long id = 123456789L;
float price = 19.99f;
double salary = 50000.50;
char grade = 'A';
boolean active = true;
```

Reference types include `String`, arrays, and custom classes:

```java
String name = "Alice";
int[] numbers = {1, 2, 3};
```

## Type conversions in Java

### Widening conversion (automatic)

```java
int x = 10;
long y = x;
double z = y;
```

### Narrowing conversion (manual)

```java
double d = 9.87;
int n = (int) d;
```

### String to number conversion

```java
String value = "100";
int number = Integer.parseInt(value);
double decimal = Double.parseDouble("3.14");
```

### Number to String conversion

```java
int count = 42;
String text = String.valueOf(count);
```

## Common operations

### Arithmetic and comparison

```java
int a = 10;
int b = 3;
System.out.println(a + b);
System.out.println(a > b);
```

### Conditional logic

```java
if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

### Loops

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

### Methods

```java
public static int add(int x, int y) {
    return x + y;
}
```

## Engineer checklist

- Know primitive vs reference types
- Know when to use `int`, `long`, `double`, `float`, and `String`
- Be comfortable with implicit and explicit conversions
- Understand `parseInt`, `parseDouble`, and `String.valueOf()`

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Variable declaration | O(1) | O(1) |
| Arithmetic / comparison | O(1) | O(1) |
| Loop over n items | O(n) | O(1) |
| Method call | O(1) | O(1) |

## Files

| File | Concept |
|------|---------|
| `HelloJava.java` | Program structure, main method |
| `DataType.java` | Primitive and reference types |
| `Operators.java` | Arithmetic, comparison, logical |
| `ControlFlow.java` | if-else, switch |
| `Loops.java` | for, while, do-while |
| `Methods.java` | Parameters, return values |
| `UserInput.java` | Scanner |
| `Calculator.java`, `FizzBuzz.java` | Practice programs |

## Interview questions

1. Primitive vs reference type?
2. Pass-by-value in Java?
3. When use `for` vs `while`?

## Next module

→ [02_oops/_README.md](../02_oops/_README.md)
