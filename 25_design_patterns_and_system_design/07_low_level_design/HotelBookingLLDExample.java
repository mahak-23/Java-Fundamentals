import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum RoomType {
    STANDARD,
    DELUXE,
    SUITE
}

enum BookingStatus {
    CONFIRMED,
    CANCELLED,
    COMPLETED
}

class Guest {
    private final String guestId;
    private final String name;

    public Guest(String guestId, String name) {
        this.guestId = guestId;
        this.name = name;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }
}

class Room {
    private final String roomId;
    private final RoomType roomType;
    private final double pricePerNight;

    public Room(String roomId, RoomType roomType, double pricePerNight) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomId() {
        return roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}

class Booking {
    private final String bookingId;
    private final Guest guest;
    private final Room room;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private BookingStatus status;

    public Booking(String bookingId, Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = BookingStatus.CONFIRMED;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }
}

class HotelBookingService {
    private final List<Room> rooms;
    private final Map<String, Booking> bookingsById;

    public HotelBookingService(List<Room> rooms) {
        this.rooms = rooms;
        this.bookingsById = new HashMap<>();
    }

    public Booking createBooking(Guest guest, LocalDate checkIn, LocalDate checkOut, RoomType preferredType) {
        if (!checkIn.isBefore(checkOut)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        Room availableRoom = findAvailableRoom(checkIn, checkOut, preferredType);
        if (availableRoom == null) {
            throw new IllegalStateException("No room available for the requested dates and type");
        }

        String bookingId = "BK-" + guest.getGuestId() + "-" + System.currentTimeMillis();
        Booking booking = new Booking(bookingId, guest, availableRoom, checkIn, checkOut);
        bookingsById.put(bookingId, booking);

        return booking;
    }

    private Room findAvailableRoom(LocalDate checkIn, LocalDate checkOut, RoomType preferredType) {
        for (Room room : rooms) {
            if (room.getRoomType() != preferredType) {
                continue;
            }
            if (isRoomAvailable(room, checkIn, checkOut)) {
                return room;
            }
        }
        return null;
    }

    private boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : bookingsById.values()) {
            if (!booking.getRoom().getRoomId().equals(room.getRoomId())) {
                continue;
            }
            if (booking.getStatus() == BookingStatus.CANCELLED) {
                continue;
            }
            boolean overlaps = !(checkOut.isBefore(booking.getCheckIn()) || checkIn.isAfter(booking.getCheckOut()));
            if (overlaps) {
                return false;
            }
        }
        return true;
    }

    public void printBookings() {
        for (Booking booking : bookingsById.values()) {
            System.out.println("Booking " + booking.getBookingId() + " -> " +
                    booking.getGuest().getName() + " / " + booking.getRoom().getRoomId() +
                    " / " + booking.getStatus());
        }
    }
}

public class HotelBookingLLDExample {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("R101", RoomType.STANDARD, 120.0));
        rooms.add(new Room("R102", RoomType.STANDARD, 120.0));
        rooms.add(new Room("R201", RoomType.DELUXE, 220.0));

        HotelBookingService service = new HotelBookingService(rooms);

        Guest guest1 = new Guest("G1", "Ava");
        Guest guest2 = new Guest("G2", "Ishaan");

        Booking booking1 = service.createBooking(guest1, LocalDate.of(2026, 9, 10), LocalDate.of(2026, 9, 12), RoomType.STANDARD);
        System.out.println("Booked room: " + booking1.getRoom().getRoomId() + " for " + booking1.getGuest().getName());

        try {
            service.createBooking(guest2, LocalDate.of(2026, 9, 11), LocalDate.of(2026, 9, 13), RoomType.STANDARD);
        } catch (IllegalStateException e) {
            System.out.println("Conflict handled: " + e.getMessage());
        }

        service.printBookings();

        // LLD explanation:
        // - Guest, Room, Booking are the key entities.
        // - HotelBookingService handles business rules: validation, room search, and date overlap checks.
        // - Booking status tracks lifecycle, and the system must prevent double-booking.
    }
}
