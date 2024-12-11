import java.time.LocalDateTime;
import java.util.List;

public interface ReservationManager {
    void makeReservation(String customerName, int partySize, LocalDateTime time);

    void cancelReservation(int reservationId);

    // Default method display the reservations
    default void displayReservations(List<Reservation> reservations) {
        reservations.forEach(System.out::println);
    }

    // Static method to print the header
    static void printHeader(String header) {
        System.out.println("==== " + header + " ====");
    }

    // Private method to display the table details
    private static void printTableDetails(Table table) {
        System.out.println("Table ID: " + table.getId() + ", Capacity: " + table.getCapacity());
    }
}
