# Interfaces

Interfaces define a contract that classes can implement. They help you achieve abstraction, reduce coupling, and support polymorphism.

## How to create and use an interface

```java
interface Animal {
    void makeSound();
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof");
    }
}
```

## Common operations

### Implement the interface

```java
class Cat implements Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}
```

### Use polymorphism

```java
Animal animal = new Dog();
animal.makeSound();
```

### Add a default method

```java
interface Vehicle {
    void start();

    default void stop() {
        System.out.println("Stopping");
    }
}
```

## Functional interfaces
A functional interface has exactly one abstract method and works well with lambdas.
```java
@FunctionalInterface
interface MathOperation {
    int apply(int a, int b);
}
```

## Key characteristics
- Interfaces contain abstract methods by default.
- A class can implement multiple interfaces.
- Interfaces can include default methods and static methods.

## Interface vs abstract class
- Interface: contract, multiple inheritance possible
- Abstract class: can have fields and concrete methods, single inheritance only

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Interface method call | O(1) | O(1) |
| Lambda execution | O(1) | O(1) |
| Polymorphic dispatch | O(1) average | O(1) |

## Interview notes
- Use interfaces when behavior should be shared across unrelated classes.
- A functional interface is ideal for lambda expressions.
- `implements` is used for interfaces; `extends` is used for classes.
