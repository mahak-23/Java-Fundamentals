package oops;

public class ConstructorsExample {
    static class Person {
        String name;
        int age;
        String city;

        // Default constructor
        Person() {
            this("Unknown", 0, "Unknown");
        }

        // Parameterized constructor
        Person(String name, int age) {
            this(name, age, "Unknown");
        }

        // Parameterized constructor with all fields
        Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }

        // Copy constructor
        Person(Person other) {
            this(other.name, other.age, other.city);
        }

        void display() {
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("City: " + city);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Person defaultPerson = new Person();
        Person alice = new Person("Alice", 22);
        Person bob = new Person("Bob", 24, "Bangalore");
        Person copyOfBob = new Person(bob);

        System.out.println("Default constructor:");
        defaultPerson.display();

        System.out.println("Parameterized constructor:");
        alice.display();

        System.out.println("Constructor with all fields:");
        bob.display();

        System.out.println("Copy constructor:");
        copyOfBob.display();
    }
}
