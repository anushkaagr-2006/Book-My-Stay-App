import java.util.*;

class AddOnUC7 {
    private String name;
    private double price;

    public AddOnUC7(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

class AddOnManagerUC7 {
    private Map<String, List<AddOnUC7>> reservationAddOns;

    public AddOnManagerUC7() {
        this.reservationAddOns = new HashMap<>();
    }

    public void addService(String reservationId, AddOnUC7 service) {
        reservationAddOns.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Added " + service.getName() + " to Reservation: " + reservationId);
    }

    public double calculateTotalCost(String reservationId, double basePrice) {
        double total = basePrice;
        List<AddOnUC7> services = reservationAddOns.get(reservationId);
        if (services != null) {
            for (AddOnUC7 service : services) {
                total += service.getPrice();
            }
        }
        return total;
    }

    public void displaySummary(String reservationId, double basePrice) {
        System.out.println("\n--- Billing Summary for " + reservationId + " ---");
        System.out.println("Base Room Price: $" + basePrice);
        List<AddOnUC7> services = reservationAddOns.get(reservationId);
        if (services != null && !services.isEmpty()) {
            System.out.println("Add-On Services:");
            for (AddOnUC7 s : services) {
                System.out.println(" - " + s);
            }
        }
        System.out.println("Total Amount Due: $" + calculateTotalCost(reservationId, basePrice));
    }
}

public class UC7 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v7.0");
        System.out.println("-------------------------------------------------");

        AddOnManagerUC7 manager = new AddOnManagerUC7();

        String resId = "RES1001";
        double roomPrice = 1800.0;

        AddOnUC7 breakfast = new AddOnUC7("Buffet Breakfast", 50.0);
        AddOnUC7 wifi = new AddOnUC7("Premium WiFi", 20.0);
        AddOnUC7 spa = new AddOnUC7("Spa Treatment", 250.0);

        manager.addService(resId, breakfast);
        manager.addService(resId, wifi);
        manager.addService(resId, spa);

        manager.displaySummary(resId, roomPrice);
        System.out.println("-------------------------------------------------");
    }
}