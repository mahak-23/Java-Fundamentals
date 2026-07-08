package oops;

/*
 * OOPConcepts.java
 * This file explains the four main Object-Oriented Programming concepts in Java.
 * Definitions are shown as comments, and the example demonstrates how classes,
 * objects, inheritance, and polymorphism work.
 *
 * Running flow:
 * 1. Java starts at main().
 * 2. The program prints the example output.
 * 3. It creates a Vehicle reference that points to a Car object.
 * 4. It calls startEngine() on the reference.
 * 5. Because Car overrides the method, Car's implementation runs.
 * 6. The program prints the vehicle type, showing the stored object data.
 */

/*
 * OOP Concept definitions:
 * Encapsulation: Hide internal data and provide access through methods.
 * Inheritance: One class extends another class to reuse code.
 * Polymorphism: Same method call can behave differently based on object type.
 * Abstraction: Show only essential features and hide complex details.
 */

public class OOPConcepts {

    public static void main(String[] args) {
        // Example object creation and method call
        System.out.println("Example output:");
        Vehicle demoCar = new DemoCar("Sedan");
        demoCar.startEngine(); // Polymorphism: DemoCar's method runs
        System.out.println("Vehicle type: " + demoCar.getType());
    }
}

// Vehicle is a base class that demonstrates inheritance and abstraction.
class Vehicle {
    private String type; // Encapsulated field: private data with public access method

    public Vehicle(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void startEngine() {
        System.out.println("Starting engine in Vehicle class.");
    }
}

// DemoCar extends Vehicle and overrides startEngine(), demonstrating polymorphism.
class DemoCar extends Vehicle {
    public DemoCar(String type) {
        super(type);
    }

    @Override
    public void startEngine() {
        System.out.println("Starting engine in Car class. Vroom!");
    }
}
