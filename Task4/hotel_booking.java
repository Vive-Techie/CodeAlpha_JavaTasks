import java.util.*;

class Reservation {
    String name;
    int roomNumber;
    int days;
    double pricePerDay;

    Reservation(String name, int roomNumber, int days, double pricePerDay) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.days = days;
        this.pricePerDay = pricePerDay;
    }

    double getTotalCost() {
        return days * pricePerDay;
    }

    void display() {
        System.out.println("Guest: " + name);
        System.out.println("Room: " + roomNumber);
        System.out.println("Days: " + days);
        System.out.println("Total Bill: Rs. " + getTotalCost());
    }
}

public class hotel_booking {
    static Scanner sc = new Scanner(System.in);
    static Map<Integer, Reservation> reservations = new HashMap<>();
    static Set<Integer> occupiedRooms = new HashSet<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Book Room");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View All Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bookRoom();
                    break;
                case 2:
                    cancelRoom();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using our system.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void bookRoom() {
        System.out.print("Enter guest name: ");
        sc.nextLine(); // consume newline
        String name = sc.nextLine();

        System.out.print("Enter room number (100-110): ");
        int room = sc.nextInt();

        if (occupiedRooms.contains(room)) {
            System.out.println("Room is already booked.");
            return;
        }

        System.out.print("Enter number of days: ");
        int days = sc.nextInt();

        System.out.print("Enter price per day: ");
        double price = sc.nextDouble();

        Reservation r = new Reservation(name, room, days, price);
        reservations.put(room, r);
        occupiedRooms.add(room);

        System.out.println("Room booked successfully for " + name);
    }

    static void cancelRoom() {
        System.out.print("Enter room number to cancel: ");
        int room = sc.nextInt();

        if (reservations.containsKey(room)) {
            reservations.remove(room);
            occupiedRooms.remove(room);
            System.out.println("Booking cancelled for room " + room);
        } else {
            System.out.println("No booking found for that room.");
        }
    }

    static void viewBookings() {
        if (reservations.isEmpty()) {
            System.out.println("No current bookings.");
            return;
        }

        for (Reservation r : reservations.values()) {
            System.out.println("------------------------");
            r.display();
        }
    }
}
