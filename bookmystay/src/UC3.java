import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    public void initializeRoom(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        }
    }

    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + " | Available: " + entry.getValue());
        }
    }
}

public class UC3 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v3.0");
        System.out.println("-------------------------------------------------");

        RoomInventory hotelInventory = new RoomInventory();

        hotelInventory.initializeRoom("Single Room", 10);
        hotelInventory.initializeRoom("Double Room", 5);
        hotelInventory.initializeRoom("Suite Room", 2);

        hotelInventory.displayInventory();

        System.out.println("\nUpdating Suite Room inventory...");
        hotelInventory.updateAvailability("Suite Room", 1);

        System.out.println("Checking specific availability for Double Room: " +
                hotelInventory.getAvailability("Double Room"));

        System.out.println("-------------------------------------------------");
        hotelInventory.displayInventory();
        System.out.println("-------------------------------------------------");
    }
}
