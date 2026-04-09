import java.util.ArrayList;
import java.util.List;

class ConfirmedBookingUC8 {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double totalAmount;

    public ConfirmedBookingUC8(String reservationId, String guestName, String roomType, double totalAmount) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Guest: %s | Room: %s | Paid: $%.2f",
                reservationId, guestName, roomType, totalAmount);
    }

    public double getTotalAmount() { return totalAmount; }
}

class HistoryServiceUC8 {
    private List<ConfirmedBookingUC8> history;

    public HistoryServiceUC8() {
        this.history = new ArrayList<>();
    }

    public void recordBooking(ConfirmedBookingUC8 booking) {
        history.add(booking);
    }

    public void generateReport() {
        System.out.println("\n--- Administrative Booking Report ---");
        if (history.isEmpty()) {
            System.out.println("No history recorded.");
            return;
        }

        double totalRevenue = 0;
        for (ConfirmedBookingUC8 record : history) {
            System.out.println(record);
            totalRevenue += record.getTotalAmount();
        }

        System.out.println("-------------------------------------");
        System.out.println("Total Transactions: " + history.size());
        System.out.println("Total Revenue     : $" + totalRevenue);
    }
}

public class UC8 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v8.0");
        System.out.println("-------------------------------------------------");

        HistoryServiceUC8 reporting = new HistoryServiceUC8();

        reporting.recordBooking(new ConfirmedBookingUC8("RES101", "Alice", "Suite Room", 3500.0));
        reporting.recordBooking(new ConfirmedBookingUC8("RES102", "Bob", "Single Room", 1000.0));
        reporting.recordBooking(new ConfirmedBookingUC8("RES103", "Charlie", "Double Room", 1800.0));

        reporting.generateReport();
        System.out.println("-------------------------------------------------");
    }
}
