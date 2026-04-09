import java.util.HashMap;
import java.util.Map;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidatorUC9 {
    private Map<String, Integer> inventory;

    public BookingValidatorUC9(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void validateRequest(String roomType, int requestedQuantity) throws InvalidBookingException {
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Validation Error: Room type cannot be empty.");
        }

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Validation Error: Room type '" + roomType + "' does not exist.");
        }

        int available = inventory.get(roomType);
        if (requestedQuantity > available) {
            throw new InvalidBookingException("Validation Error: Insufficient inventory for " + roomType +
                    ". Requested: " + requestedQuantity + ", Available: " + available);
        }

        if (requestedQuantity <= 0) {
            throw new InvalidBookingException("Validation Error: Requested quantity must be greater than zero.");
        }
    }
}

public class UC9 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v9.0 (Reliability Update)");
        System.out.println("-------------------------------------------------");

        Map<String, Integer> hotelInventory = new HashMap<>();
        hotelInventory.put("Single Room", 2);
        hotelInventory.put("Suite Room", 1);

        BookingValidatorUC9 validator = new BookingValidatorUC9(hotelInventory);

        String[] testRooms = {"Deluxe Room", "Suite Room", ""};
        int[] testQuantities = {1, 5, 1};

        for (int i = 0; i < testRooms.length; i++) {
            try {
                System.out.println("Processing: " + (testRooms[i].isEmpty() ? "Empty String" : testRooms[i]));
                validator.validateRequest(testRooms[i], testQuantities[i]);
                System.out.println("Success: Request is valid.");
            } catch (InvalidBookingException e) {
                System.err.println("Caught Exception: " + e.getMessage());
            }
            System.out.println("---");
        }

        System.out.println("System remains stable and ready for next operations.");
        System.out.println("-------------------------------------------------");
    }
}