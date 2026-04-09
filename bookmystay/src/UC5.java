import java.util.LinkedList;
import java.util.Queue;

class ReservationUC5 {
    private String guestName;
    private String roomType;

    public ReservationUC5(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + " | Requested: " + roomType;
    }
}

class BookingRequestQueueUC5 {
    private Queue<ReservationUC5> requestQueue;

    public BookingRequestQueueUC5() {
        this.requestQueue = new LinkedList<>();
    }

    public void addRequest(ReservationUC5 reservation) {
        requestQueue.add(reservation);
        System.out.println("Enqueued: " + reservation);
    }

    public void displayQueue() {
        System.out.println("\n--- Current Booking Request Queue ---");
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            for (ReservationUC5 res : requestQueue) {
                System.out.println(res);
            }
        }
        System.out.println("Total requests waiting: " + requestQueue.size());
    }
}

public class UC5 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v5.0");
        System.out.println("-------------------------------------------------");

        BookingRequestQueueUC5 bookingQueue = new BookingRequestQueueUC5();

        bookingQueue.addRequest(new ReservationUC5("Alice", "Suite Room"));
        bookingQueue.addRequest(new ReservationUC5("Bob", "Single Room"));
        bookingQueue.addRequest(new ReservationUC5("Charlie", "Double Room"));

        bookingQueue.displayQueue();

        System.out.println("-------------------------------------------------");
        System.out.println("Status: Intake complete. Ready for processing.");
        System.out.println("-------------------------------------------------");
    }
}
