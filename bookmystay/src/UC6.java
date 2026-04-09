import java.util.*;

class ReservationUC6 {
    private String guestName;
    private String roomType;

    public ReservationUC6(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    @Override
    public String toString() {
        return "Guest: " + guestName + " | Room: " + roomType;
    }
}

class AllocationServiceUC6 {
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private Queue<ReservationUC6> queue = new LinkedList<>();

    public void setupInventory(String type, int count) {
        inventory.put(type, count);
        allocatedRooms.put(type, new HashSet<>());
    }

    public void receiveRequest(ReservationUC6 res) {
        queue.add(res);
    }

    public void processAllocations() {
        System.out.println("\n--- Processing Room Allocations ---");
        while (!queue.isEmpty()) {
            ReservationUC6 request = queue.poll();
            String type = request.getRoomType();

            if (inventory.getOrDefault(type, 0) > 0) {
                String roomId = type.substring(0, 1).toUpperCase() + (100 + allocatedRooms.get(type).size() + 1);

                allocatedRooms.get(type).add(roomId);
                inventory.put(type, inventory.get(type) - 1);

                System.out.println("CONFIRMED: " + request.getGuestName() + " assigned to " + roomId);
            } else {
                System.out.println("FAILED: No availability for " + request.getGuestName() + " (" + type + ")");
            }
        }
    }

    public void displayFinalState() {
        System.out.println("\n--- Final System State ---");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> Available: " + inventory.get(type) + " | Allocated IDs: " + allocatedRooms.get(type));
        }
    }
}

public class UC6 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v6.0");
        System.out.println("-------------------------------------------------");

        AllocationServiceUC6 service = new AllocationServiceUC6();

        service.setupInventory("Single Room", 2);
        service.setupInventory("Suite Room", 1);

        service.receiveRequest(new ReservationUC6("Alice", "Suite Room"));
        service.receiveRequest(new ReservationUC6("Bob", "Single Room"));
        service.receiveRequest(new ReservationUC6("Charlie", "Single Room"));
        service.receiveRequest(new ReservationUC6("David", "Single Room")); // Should fail

        service.processAllocations();
        service.displayFinalState();
        System.out.println("-------------------------------------------------");
    }
}