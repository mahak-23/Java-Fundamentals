package interfaces;

interface Animal {
    void makeSound();
    void eat();
}

interface Swimmer {
    void swim();
}

class Dog implements Animal, Swimmer {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating food...");
    }

    @Override
    public void swim() {
        System.out.println("Dog is swimming in water...");
    }

    public void play() {
        System.out.println("Dog is playing...");
    }
}

class Bird implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Tweet! Tweet!");
    }

    @Override
    public void eat() {
        System.out.println("Bird is eating seeds...");
    }
}

class Fish implements Animal, Swimmer {
    @Override
    public void makeSound() {
        System.out.println("Fish makes bubbling sounds...");
    }

    @Override
    public void eat() {
        System.out.println("Fish is eating plankton...");
    }

    @Override
    public void swim() {
        System.out.println("Fish is swimming in water...");
    }
}

public class InterfacesExample {
    public static void main(String[] args) {
        System.out.println("=== Polymorphism with Interfaces ===");

        // Create objects implementing the Animal interface
        Animal dog = new Dog();
        Animal bird = new Bird();
        Animal fish = new Fish();

        // Call methods using interface reference
        System.out.println("Dog:");
        dog.makeSound();
        dog.eat();

        System.out.println("\nBird:");
        bird.makeSound();
        bird.eat();

        System.out.println("\nFish:");
        fish.makeSound();
        fish.eat();

        // Multiple interface implementation
        System.out.println("\n=== Multiple Interfaces ===");

        Swimmer dogSwimmer = new Dog();
        Swimmer fishSwimmer = new Fish();

        System.out.println("Dog swimming:");
        dogSwimmer.swim();

        System.out.println("\nFish swimming:");
        fishSwimmer.swim();

        // Array of interfaces
        System.out.println("\n=== Array of Interface References ===");
        Animal[] animals = {dog, bird, fish};
        for (Animal animal : animals) {
            animal.makeSound();
            animal.eat();
            System.out.println();
        }

        // Access Dog-specific method
        System.out.println("=== Dog-specific method ===");
        Dog myDog = (Dog) dog;
        myDog.play();
    }
}
