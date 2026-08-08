interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    public void drive() {
        System.out.println("Driving a car");
    }
}

class Bike implements Vehicle {
    public void drive() {
        System.out.println("Riding a bike");
    }
}

class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        if ("car".equalsIgnoreCase(type)) {
            return new Car();
        } else if ("bike".equalsIgnoreCase(type)) {
            return new Bike();
        }
        throw new IllegalArgumentException("Unknown vehicle type: " + type);
    }
}

public class FactoryPatternExample {
    public static void main(String[] args) {
        // What: demonstrates the Factory pattern for object creation.
        // Why: it centralizes construction code and makes it easier to add new types.
        // How: call VehicleFactory.createVehicle(type) instead of using new directly.
        Vehicle car = VehicleFactory.createVehicle("car");
        car.drive();

        Vehicle bike = VehicleFactory.createVehicle("bike");
        bike.drive();
    }
}
