import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService implements ReservationManager {
    private final List<Reservation> reservations = new ArrayList<>();
    private final List<Table> tables = new ArrayList<>();
    private int reservationCounter = 1;

    // This method processes a varargs parameter, internally handled as an array of Table objects
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public void addTable(Table... newTables) {
        for (Table table : newTables) {
            tables.add(table);
        }
    }

    // Overloading addTable method
    public void addTable(Table table) {
        tables.add(table);
    }    

    @Override
    public void makeReservation(String customerName, int partySize, LocalDateTime time) {
        Optional<Table> tableOpt = tables.stream()
            .filter(t -> !t.isReserved() && t.getCapacity() >= partySize)
            .findFirst();

        if (tableOpt.isPresent()) {
            Table table = tableOpt.get();
            table.reserve();
            reservations.add(new Reservation(reservationCounter++, customerName, partySize, time, table, ReservationStatus.CONFIRMED)); // Set status to CONFIRMED
            System.out.println("\nReservation successful!");
        } else {
        System.out.println("No tables available for the specified party size.");
        }
    }

    @Override
    public void cancelReservation(int reservationId) {
        var iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.id() == reservationId) {
                reservation.table().release();
                reservations.remove(reservation);
                reservations.add(new Reservation(reservation.id(), reservation.customerName(), reservation.partySize(), reservation.reservationTime(), reservation.table(), ReservationStatus.CANCELED)); // Update status to CANCELED
                System.out.println("\nReservation canceled.");
                return;
            }
        }
    System.out.println("\nReservation ID not found.");
    }


    public void saveReservations(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(reservations);
            System.out.println("Reservations saved to " + filename);
        }
    }

    public void loadReservations(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            reservations.clear();
            reservations.addAll((List<Reservation>) ois.readObject());
            System.out.println("Reservations loaded from " + filename);
        }
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations); // Defensive copy
    }

    public List<Table> getTables() {
        return new ArrayList<>(tables); // Defensive copy
    }
    

    public void categorizeTable(Table table) {
        String category = switch (table.getCapacity()) {
            case 2, 4 -> "Small Table";
            case 6, 8 -> "Medium Table";
            case 10 -> "Large Table";
            default -> "Unknown Size";
        };
        System.out.println("Table ID " + table.getId() + ": " + category);
    }

    public void processReservation(Object obj) {
        if (obj instanceof Reservation reservation) {
            System.out.println("Processing reservation for " + reservation.customerName());
        } else {
            System.out.println("Invalid object provided.");
        }
    }
    
}