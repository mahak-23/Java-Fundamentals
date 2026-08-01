# oops

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md)

Object-oriented programming organizes code into classes and objects. It helps you model real-world entities and manage complexity in larger programs.

## How to create a class and object

```java
class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    void display() {
        System.out.println(name);
    }
}

Student s = new Student("Ava");
s.display();
```

## Core OOP operations and concepts

### 1. Encapsulation

```java
class Account {
    private double balance;

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
```

### 2. Inheritance

```java
class Person {
    void speak() {
        System.out.println("Hello");
    }
}

class Teacher extends Person {
}
```

### 3. Polymorphism

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}
```

### 4. Abstraction

```java
abstract class Shape {
    abstract double area();
}
```

### 5. Constructor overloading

```java
class Employee {
    Employee() {}
    Employee(String name) {}
}
```

### 6. `this` and `super`

```java
class Base {
    int value = 10;
}

class Child extends Base {
    void print() {
        System.out.println(super.value);
    }
}
```

### 7. Composition

```java
class Engine {}

class Car {
    Engine engine = new Engine();
}
```

## Engineer checklist

- Know how to create classes, objects, and constructors
- Understand encapsulation, inheritance, polymorphism, and abstraction
- Be able to explain `this`, `super`, method overloading, and overriding
- Know when to prefer composition over inheritance

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Object creation | O(1) | O(1) |
| Method call | O(1) | O(1) |
| Inheritance lookup | O(1) average | O(1) |
| Polymorphic dispatch | O(1) average | O(1) |

## Files

| File | Concept |
|------|---------|
| `ClassAndObject.java` | Classes, objects, fields |
| `ConstructorsExample.java` | Constructors, `this` |
| `OOPConcepts.java` | Four pillars demo |
| `ClassVehicle.java` | Inheritance hierarchy |
| `LibraryMain.java` | Real-world OOP design |

## Four pillars

| Pillar | Meaning |
|--------|---------|
| Encapsulation | Hide data, expose via methods |
| Inheritance | Reuse parent class behavior |
| Polymorphism | Same method, different behavior at runtime |
| Abstraction | Show essentials, hide complexity |

## Interview questions

1. Class vs object?
2. Overloading vs overriding?
3. Inheritance vs composition?

## Next module

→ [03_strings/_README.md](../03_strings/_README.md)
