# Java Basics

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
This section covers the foundational syntax and building blocks of Java.

## Program structure
```java
public class HelloJava {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

## Variables and data types
```java
int age = 21;
double price = 10.5;
boolean isActive = true;
char grade = 'A';
String name = "Alice";
```

## Operators
```java
int a = 10;
int b = 3;
System.out.println(a + b);
System.out.println(a % b);
System.out.println(a > b);
```

## Control flow
```java
if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

## Loops
```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

## Methods
```java
public static int add(int x, int y) {
    return x + y;
}
```

## User input
```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
int value = sc.nextInt();
```

## Interview notes
- `int` is a primitive type; `String` is a reference type.
- `==` compares references, while `.equals()` compares values.
- `for` is ideal when the number of iterations is known.
- `while` is better when the loop depends on a condition during execution.
