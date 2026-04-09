import java.io.*;
import java.util.*;

class HotelStateUC12 implements Serializable {
    private static final long serialVersionUID = 1L;
    public Map<String, Integer> inventory;
    public List<String> bookingHistory;

    public HotelStateUC12(Map<String, Integer> inventory, List<String> bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }
}

class PersistenceServiceUC12 {
    private final String filename = "hotel_data.ser";

    public void saveState(Map<String, Integer> inventory, List<String> history) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            HotelStateUC12 state = new HotelStateUC12(inventory, history);
            out.writeObject(state);
            System.out.println("System state persisted to " + filename);
        } catch (IOException e) {
            System.err.println("Save Error: " + e.getMessage());
        }
    }

    public HotelStateUC12 loadState() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No persistence file found. Starting with fresh state.");
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("Recovering system state from " + filename + "...");
            return (HotelStateUC12) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Recovery Error: " + e.getMessage());
            return null;
        }
    }
}

public class UC12 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v12.0 (Persistence Update)");
        System.out.println("-------------------------------------------------");

        PersistenceServiceUC12 persistence = new PersistenceServiceUC12();

        // Step 1: Attempt Recovery
        HotelStateUC12 recoveredData = persistence.loadState();

        Map<String, Integer> currentInventory;
        List<String> currentHistory;

        if (recoveredData != null) {
            currentInventory = recoveredData.inventory;
            currentHistory = recoveredData.bookingHistory;
        } else {
            currentInventory = new HashMap<>();
            currentInventory.put("Single Room", 10);
            currentHistory = new ArrayList<>();
        }

        System.out.println("Active Inventory: " + currentInventory);
        System.out.println("History Records : " + currentHistory.size());

        // Step 2: Simulate a new transaction
        System.out.println("\nProcessing new booking for 'Guest_99'...");
        currentInventory.put("Single Room", currentInventory.get("Single Room") - 1);
        currentHistory.add("RES_G99_SingleRoom");

        // Step 3: Save state before "Shutdown"
        persistence.saveState(currentInventory, currentHistory);

        System.out.println("-------------------------------------------------");
        System.out.println("System Shutdown complete.");
        System.out.println("-------------------------------------------------");
    }
}
