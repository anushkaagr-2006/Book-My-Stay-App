import java.util.HashMap;
import java.util.Map;

abstract class RoomUC4 {
    protected String type;
    protected double price;

    public RoomUC4(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() { return type; }
    public double getPrice() { return price; }
    public abstract void showDetails();
}

class SingleRoomUC4 extends RoomUC4 {
    public SingleRoomUC4() { super("Single Room", 1000.0); }
    public void showDetails() {
        System.out.println("Type: " + type + " | Price: " + price + " (Perfect for solo travelers)");
    }
}

class DoubleRoomUC4 extends RoomUC4 {
    public DoubleRoomUC4() { super("Double Room", 1800.0); }
    public void showDetails() {
        System.out.println("Type: " + type + " | Price: " + price + " (Spacious for couples)");
    }
}

class SuiteRoomUC4 extends RoomUC4 {
    public SuiteRoomUC4() { super("Suite Room", 3500.0); }
    public void showDetails() {
        System.out.println("Type: " + type + " | Price: " + price + " (Luxury experience)");
    }
}

class InventoryUC4 {
    private Map<String, Integer> counts = new HashMap<>();

    public void setRooms(String type, int count) {
        counts.put(type, count);
    }

    public int getCount(String type) {
        return counts.getOrDefault(type, 0);
    }
}

class SearchServiceUC4 {
    public void searchAvailableRooms(InventoryUC4 inventory, RoomUC4[] roomTypes) {
        System.out.println("--- Search Results (Available Rooms Only) ---");
        boolean found = false;
        for (RoomUC4 room : roomTypes) {
            int availableCount = inventory.getCount(room.getType());
            if (availableCount > 0) {
                room.showDetails();
                System.out.println("Rooms remaining: " + availableCount);
                found = true;
            }
        }
        if (!found) System.out.println("No rooms available currently.");
    }
}

public class UC4 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v4.0");
        System.out.println("-------------------------------------------------");

        InventoryUC4 hotelInventory = new InventoryUC4();
        hotelInventory.setRooms("Single Room", 5);
        hotelInventory.setRooms("Double Room", 0);
        hotelInventory.setRooms("Suite Room", 2);

        RoomUC4[] catalog = { new SingleRoomUC4(), new DoubleRoomUC4(), new SuiteRoomUC4() };
        SearchServiceUC4 searchService = new SearchServiceUC4();

        searchService.searchAvailableRooms(hotelInventory, catalog);
        System.out.println("-------------------------------------------------");
    }
}