abstract class Room {
    protected String type;
    protected int capacity;
    protected double pricePerNight;

    public Room(String type, int capacity, double pricePerNight) {
        this.type = type;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
    }

    public abstract void displayDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Capacity: " + capacity + " | Price: " + pricePerNight);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 1800.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Capacity: " + capacity + " | Price: " + pricePerNight);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 4, 3500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Capacity: " + capacity + " | Price: " + pricePerNight);
    }
}

public class UC2 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("       Welcome to Book My Stay App!              ");
        System.out.println("-------------------------------------------------");
        System.out.println("Application Name: Hotel Booking Management System");
        System.out.println("Version         : v2.0");
        System.out.println("-------------------------------------------------");

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("Available Room Types:");
        single.displayDetails();
        System.out.println("Availability: " + singleAvailability);
        System.out.println();

        dbl.displayDetails();
        System.out.println("Availability: " + doubleAvailability);
        System.out.println();

        suite.displayDetails();
        System.out.println("Availability: " + suiteAvailability);
        System.out.println("-------------------------------------------------");
    }
}