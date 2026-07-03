package oops;

public class ClassVehicle {
    protected String brand;
    protected double price;

    public ClassVehicle(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }
}

class Bike extends ClassVehicle {
    private String type;

    public Bike(String brand, double price, String type) {
        super(brand, price);
        this.type = type;
    }

    public void displayInfo() {
        System.out.println("Brand: " + super.brand);
        System.out.println("Price: Rs" + super.price);
        System.out.println("Type: " + type);
    }
}

class Car extends ClassVehicle {
    private int numberOfDoors;

    public Car(String brand, double price, int numberOfDoors) {
        super(brand, price);
        this.numberOfDoors = numberOfDoors;
    }

    public void displayInfo() {
        System.out.println("Brand: " + super.brand);
        System.out.println("Price: Rs" + super.price);
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}

class Truck extends ClassVehicle {
    private double loadCapacity;

    public Truck(String brand, double price, double loadCapacity) {
        super(brand, price);
        this.loadCapacity = loadCapacity;
    }

    public void displayInfo() {
        System.out.println("Brand: " + super.brand);
        System.out.println("Price: Rs" + super.price);
        System.out.println("Load Capacity: " + loadCapacity + " tons");
    }
}

class Main {
    public static void main(String[] args) {
        Bike bike = new Bike("Yamaha", 150000, "Sport");
        Car car = new Car("Toyota", 800000, 4);
        Truck truck = new Truck("Volvo", 2000000, 10);

        System.out.println("--------------Bike Information--------------");
        bike.displayInfo();

        System.out.println("\n--------------Car Information--------------");
        car.displayInfo();

        System.out.println("\n--------------Truck Information--------------");
        truck.displayInfo();
    }
}