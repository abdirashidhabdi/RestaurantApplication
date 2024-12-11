/*
* RestaurantApp.java
* Created by: Abdirashid Abdi
*/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RestaurantApp {
    public static void main(String[] args) {
        ReservationService service = new ReservationService();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // Available Tables To Book
        service.addTable(new Table(1, 2), new Table(2, 4), new Table(3, 6)); // Using varargs to create an array of tables
        service.addTable(new Table(4, 8)); // Using overloaded method addTable

        System.out.println("Welcome to our Restaurant Reservation Application!");

        Filters filters = new Filters(); // Create an instance of Filters which extends BaseFilter

        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease choose an option from the following:");
            System.out.println("1. Make a Booking");
            System.out.println("2. Cancel a Booking");
            System.out.println("3. View Reservations");
            System.out.println("4. View Available Tables");
            System.out.println("5. Exit");
            System.out.print("\nChoose an option: ");
            
            int choice = -1;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice >= 1 && choice <= 5) {
                        break; // Exit loop if input is valid
                    } else {
                        System.out.println("Invalid choice. Please select a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    scanner.nextLine(); // Prompt for valid input
                }
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your name: ");
                    String customerName = scanner.nextLine();
                    while (customerName.isEmpty()) {
                        System.out.print("Name cannot be empty. Please enter your name: ");
                        customerName = scanner.nextLine();
                    }

                    int partySize = -1;
                    while (true) {
                        System.out.print("Enter party size: ");
                        try {
                            partySize = scanner.nextInt();
                            scanner.nextLine();
                            if (partySize > 0) {
                                break; // Valid party size
                            } else {
                                System.out.println("Party size must be greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine(); // Prompt for valid input
                        }
                    }

                    System.out.print("Enter reservation time (DD-MM-YYYY HH:mm): ");
                    String timeInput = scanner.nextLine();
                    try {
                        LocalDateTime reservationTime = LocalDateTime.parse(timeInput, dateTimeFormatter);
                        service.makeReservation(customerName, partySize, reservationTime);
                    } catch (Exception e) {
                        System.out.println("Invalid date and time format. Please try again.");
                    }
                }
                case 2 -> {
                    int reservationId = -1;
                    while (true) {
                        System.out.print("Enter reservation ID to cancel: ");
                        try {
                            reservationId = scanner.nextInt();
                            scanner.nextLine();
                            if (reservationId > 0) {
                                break; // Valid reservation ID
                            } else {
                                System.out.println("Reservation ID must be a positive integer.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid integer.");
                            scanner.nextLine(); // Prompt for valid input
                        }
                    }
                    service.cancelReservation(reservationId);
                }
                case 3 -> {
                    System.out.println("Current Reservations:");
                    service.getReservations().forEach(reservation -> 
                    System.out.println(reservation + "\n- This booking is: " + reservation.status()));
                }
                case 4 -> {
                    System.out.println("\nTo Filter Available Tables by Capacity:");
                    System.out.print("Enter the number of people you want to book the table: ");
                    int minCapacity = scanner.nextInt();
                    scanner.nextLine();
    
                    // Use Filters.filterList to filter available tables based on capacity
                    List<Table> availableTables = filters.filterList(service.getTables(), table -> !table.isReserved() && table.getCapacity() >= minCapacity);
    
                    // If no tables match the filter criteria, print a message
                    if (availableTables.isEmpty()) {
                        System.out.println("No available tables meet the specified capacity.");
                    } else {
                    // Display filtered available tables
                        availableTables.forEach(System.out::println);
                    }
                }
                case 5 -> {
                    exit = true;
                    System.out.println("\nThank you for using the Restaurant Reservation Application!\n");
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }
}