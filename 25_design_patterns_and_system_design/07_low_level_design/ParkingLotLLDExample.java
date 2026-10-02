import java.util.ArrayList;
import java.util.List;

enum VehicleType {
    CAR,
    BIKE,
    TRUCK
}

class ParkingLotVehicle {
    private final String id;
    private final VehicleType type;

    public ParkingLotVehicle(String id, VehicleType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }
}

class ParkingSlot {
    private final int slotNumber;
    private final VehicleType allowedType;
    private ParkingLotVehicle vehicle;

    public ParkingSlot(int slotNumber, VehicleType allowedType) {
        this.slotNumber = slotNumber;
        this.allowedType = allowedType;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean fits(VehicleType type) {
        return allowedType == type;
    }

    public ParkingLotVehicle park(ParkingLotVehicle vehicle) {
        if (!isAvailable()) {
            throw new IllegalStateException("Slot " + slotNumber + " is already occupied");
        }
        this.vehicle = vehicle;
        return vehicle;
    }

    public ParkingLotVehicle unpark() {
        ParkingLotVehicle current = vehicle;
        vehicle = null;
        return current;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getAllowedType() {
        return allowedType;
    }
}

class ParkingTicket {
    private final String ticketId;
    private final String vehicleId;
    private final int slotNumber;
    private final long entryTime;

    public ParkingTicket(String ticketId, String vehicleId, int slotNumber, long entryTime) {
        this.ticketId = ticketId;
        this.vehicleId = vehicleId;
        this.slotNumber = slotNumber;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public long getEntryTime() {
        return entryTime;
    }
}

class ParkingLotService {
    private final List<ParkingSlot> slots;

    public ParkingLotService(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    public ParkingTicket parkVehicle(ParkingLotVehicle vehicle) {
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable() && slot.fits(vehicle.getType())) {
                slot.park(vehicle);
                return new ParkingTicket(
                        "TKT-" + vehicle.getId(),
                        vehicle.getId(),
                        slot.getSlotNumber(),
                        System.currentTimeMillis());
            }
        }
        throw new IllegalStateException("No available slot for " + vehicle.getType());
    }

    public void unparkVehicle(String ticketId) {
        for (ParkingSlot slot : slots) {
            if (!slot.isAvailable() && slot.getSlotNumber() > 0) {
                // This is a simplified example. In a real system, we would look up the ticket by ID.
                // Here we only demonstrate the slot lifecycle and the idea of releasing a parking slot.
            }
        }
        System.out.println("Ticket " + ticketId + " processed for exit");
    }

    public void printStatus() {
        System.out.println("Parking lot status:");
        for (ParkingSlot slot : slots) {
            System.out.println("Slot " + slot.getSlotNumber() + " (" + slot.getAllowedType() + ") => " +
                    (slot.isAvailable() ? "AVAILABLE" : "OCCUPIED"));
        }
    }
}

public class ParkingLotLLDExample {
    public static void main(String[] args) {
        List<ParkingSlot> slots = new ArrayList<>();
        slots.add(new ParkingSlot(1, VehicleType.CAR));
        slots.add(new ParkingSlot(2, VehicleType.CAR));
        slots.add(new ParkingSlot(3, VehicleType.BIKE));
        slots.add(new ParkingSlot(4, VehicleType.TRUCK));

        ParkingLotService parkingLotService = new ParkingLotService(slots);

        ParkingLotVehicle car1 = new ParkingLotVehicle("C-101", VehicleType.CAR);
        ParkingLotVehicle bike1 = new ParkingLotVehicle("B-202", VehicleType.BIKE);

        ParkingTicket carTicket = parkingLotService.parkVehicle(car1);
        ParkingTicket bikeTicket = parkingLotService.parkVehicle(bike1);

        System.out.println("Car parked: " + carTicket.getTicketId() + " at slot " + carTicket.getSlotNumber());
        System.out.println("Bike parked: " + bikeTicket.getTicketId() + " at slot " + bikeTicket.getSlotNumber());
        parkingLotService.printStatus();

        // LLD explanation:
        // - Vehicle is an entity representing the arriving object.
        // - ParkingSlot is a value/physical resource in the domain model.
        // - ParkingTicket captures the issued receipt and allows the exit flow.
        // - ParkingLotService contains the business workflow: find a slot, park, validate, and release.
    }
}
