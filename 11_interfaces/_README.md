# Interfaces

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
Interfaces define a contract that classes can implement. They are used to achieve abstraction and loose coupling.

## Interface example
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

## Key characteristics
- Interfaces contain abstract methods by default.
- A class can implement multiple interfaces.
- Interfaces can include default methods and static methods.

## Default methods
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

## Polymorphism through interfaces
```java
Animal animal = new Dog();
animal.makeSound();
```

## Interface vs abstract class
- Interface: contract, multiple inheritance possible
- Abstract class: can have fields and concrete methods, single inheritance only

## Interview notes
- Use interfaces when behavior should be shared across unrelated classes.
- A functional interface is ideal for lambda expressions.
- `implements` is used for interfaces; `extends` is used for classes.
