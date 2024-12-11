import java.io.Serializable;
import java.time.LocalDateTime;

public record Reservation(int id, String customerName, int partySize, LocalDateTime reservationTime, Table table, ReservationStatus status) implements Serializable {}
