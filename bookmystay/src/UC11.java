import java.util.*;

class ReservationUC11 {
    private String guestName;
    private String roomType;

    public ReservationUC11(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

class ConcurrentBookingProcessorUC11 {
    private Map<String, Integer> inventory = new HashMap<>();
    private Queue<ReservationUC11> requestQueue = new LinkedList<>();

    public void setupInventory(String type, int count) {
        inventory.put(type, count);
    }

    public void addRequest(ReservationUC11 res) {
        synchronized (requestQueue) {
            requestQueue.add(res);
        }
    }

    public void processBookings() {
        while (true) {
            ReservationUC11 request;
            synchronized (requestQueue) {
                if (requestQueue.isEmpty()) break;
                request = requestQueue.poll();
            }

            synchronized (inventory) {
                String type = request.getRoomType();
                int count = inventory.getOrDefault(type, 0);

                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + " CONFIRMED: " +
                            request.getGuestName() + " for " + type);
                    inventory.put(type, count - 1);
                } else {
                    System.out.println(Thread.currentThread().getName() + " FAILED: No rooms for " +
                            request.getGuestName());
                }
            }

            try { Thread.sleep(50); } catch (InterruptedException e) { }
        }
    }

    public void displayFinalInventory() {
        System.out.println("\nFinal Inventory: " + inventory);
    }
}

public class UC11 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v11.0 (Thread Safety)");
        System.out.println("-------------------------------------------------");

        ConcurrentBookingProcessorUC11 processor = new ConcurrentBookingProcessorUC11();
        processor.setupInventory("Suite Room", 2);

        processor.addRequest(new ReservationUC11("Guest_1", "Suite Room"));
        processor.addRequest(new ReservationUC11("Guest_2", "Suite Room"));
        processor.addRequest(new ReservationUC11("Guest_3", "Suite Room"));
        processor.addRequest(new ReservationUC11("Guest_4", "Suite Room"));

        Thread thread1 = new Thread(processor::processBookings, "Server-Thread-A");
        Thread thread2 = new Thread(processor::processBookings, "Server-Thread-B");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        processor.displayFinalInventory();
        System.out.println("-------------------------------------------------");
    }
}