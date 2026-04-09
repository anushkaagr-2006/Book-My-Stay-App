import java.util.*;

class ReservationUC10 {
    private String id;
    private String type;

    public ReservationUC10(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() { return id; }
    public String getType() { return type; }
}

class CancellationServiceUC10 {
    private Map<String, Integer> inventory;
    private Map<String, ReservationUC10> activeBookings;
    private Stack<String> releasedRooms;

    public CancellationServiceUC10(Map<String, Integer> inventory) {
        this.inventory = inventory;
        this.activeBookings = new HashMap<>();
        this.releasedRooms = new Stack<>();
    }

    public void addBooking(ReservationUC10 res) {
        activeBookings.put(res.getId(), res);
    }

    public void cancelBooking(String reservationId) {
        System.out.println("Processing cancellation for: " + reservationId);

        if (!activeBookings.containsKey(reservationId)) {
            System.out.println("Error: Reservation ID " + reservationId + " not found.");
            return;
        }

        ReservationUC10 res = activeBookings.remove(reservationId);
        String roomType = res.getType();

        inventory.put(roomType, inventory.get(roomType) + 1);

        String dummyRoomId = roomType.substring(0, 1).toUpperCase() + "101";
        releasedRooms.push(dummyRoomId);

        System.out.println("Success: " + roomType + " inventory incremented. Room " + dummyRoomId + " released to stack.");
    }

    public void displayState() {
        System.out.println("\n--- Current System State ---");
        System.out.println("Inventory: " + inventory);
        System.out.println("Active Bookings: " + activeBookings.size());
        System.out.println("Recently Released Rooms (LIFO): " + releasedRooms);
    }
}

public class UC10 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v10.0 (Rollback Support)");
        System.out.println("-------------------------------------------------");

        Map<String, Integer> hotelInventory = new HashMap<>();
        hotelInventory.put("Single Room", 0);
        hotelInventory.put("Suite Room", 2);

        CancellationServiceUC10 service = new CancellationServiceUC10(hotelInventory);

        service.addBooking(new ReservationUC10("RES001", "Single Room"));
        service.addBooking(new ReservationUC10("RES002", "Suite Room"));

        service.cancelBooking("RES001");
        service.cancelBooking("RES999"); // Non-existent case

        service.displayState();
        System.out.println("-------------------------------------------------");
    }
}
