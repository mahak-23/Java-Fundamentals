# OOP Concepts

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
Object-Oriented Programming organizes code around objects and classes.

## Four pillars of OOP
- Encapsulation - hide data and expose controlled access
- Inheritance - reuse behavior from a parent class
- Polymorphism - same method name with different behavior
- Abstraction - model essential behavior while hiding complexity

## Class and object example
```java
class Car {
    String model;

    void drive() {
        System.out.println("Driving " + model);
    }
}

Car c = new Car();
c.model = "BMW";
c.drive();
```

## Inheritance example
```java
class Vehicle {
    void start() {
        System.out.println("Starting");
    }
}

class Bike extends Vehicle {
    void ride() {
        System.out.println("Riding");
    }
}
```

## Constructor example
```java
class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}
```

## Interview notes
- A class is a blueprint; an object is an instance.
- `extends` is used for inheritance; `implements` is used for interfaces.
- Method overriding enables runtime polymorphism.
- Encapsulation improves safety and maintainability.
