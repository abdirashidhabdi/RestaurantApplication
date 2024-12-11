## Overview of the Restaurant Reservation App
The main goal of this application is to allow customers to make and cancel reservations at a restaurant. It involves managing tables, reservations, and the status of these reservations.
The app uses multiple Java classes that handle different responsibilities. Let's look at each component of the system.

## 1. RestaurantApp.java - Main Application
The RestaurantApp.java is the entry point of the application. When you run this file, it starts the restaurant reservation system.
What it does:
•	The application presents a menu to the user with options to:
  o	Make a reservation.
  o	Cancel a reservation.
  o	View current reservations.
  o	View available tables.
  o	Exit the application.
•	The user interacts with the application via a console (command-line interface), entering their choices and details like their name, party size, and reservation time.
•	Making a Reservation: The user can provide their name, party size, and reservation time. The system checks if there is a table available that matches the party size and is not reserved. If so, the reservation is confirmed; if not, an error message is shown.
•	Canceling a Reservation: The user can enter the reservation ID of an existing reservation to cancel it. The system will update the reservation status to "CANCELED" and free up the table.
•	Viewing Reservations: The user can view all current reservations along with their status (e.g., "CONFIRMED", "CANCELED", etc.).
•	Viewing Available Tables: The user can specify the minimum number of people for a table, and the system will show all tables that are available for that party size.

## 2. ReservationService.java - Reservation Logic
The ReservationService.java class handles the core functionality of managing reservations and tables. This is where the business logic is stored, such as creating and canceling reservations.
What it does:
•	Stores Tables and Reservations: It keeps track of all the tables (tables) and reservations (reservations) in the system. Tables have attributes like ID, capacity, and whether they are reserved or not.
•	Adding Tables:
  o	The addTable method is used to add tables to the system. Tables are either added individually or in bulk using varargs (a feature that allows passing a variable number of arguments).
•	Making Reservations:
  o	The makeReservation method is responsible for reserving a table for a customer. It checks if there is an available table with enough capacity for the party size. If a table is available, it reserves the table and adds a new reservation to the list. The reservation's status is set to "CONFIRMED".
•	Canceling Reservations:
  o	The cancelReservation method allows the user to cancel an existing reservation by providing the reservation ID. It removes the reservation from the list and changes its status to "CANCELED". It also frees up the reserved table.
•	Saving and Loading Reservations:
  o	The system can save and load reservations to and from a file. This is done using the saveReservations and loadReservations methods, which use Java's serialization mechanism to write and read objects to a file.
•	Table Categorization:
  o	The categorizeTable method categorizes tables based on their capacity (e.g., small, medium, large). This helps in understanding the table sizes available in the restaurant.

## 3. Table.java - Table Representation
The Table.java class represents a table in the restaurant.
What it does:
•	Table Properties:
  o	Each table has an ID, capacity (how many people it can seat), and a reserved status (whether it's currently reserved or not).
•	Methods:
  o	reserve(): Marks the table as reserved.
  o	release(): Frees up the table, making it available for future reservations.
  o	The toString() method returns a string representation of the table, which includes its ID, capacity, and reserved status.
________________________________________
## 4. Reservation.java - Reservation Representation
The Reservation.java class represents a reservation made by a customer.
What it does:
•	Reservation Properties:
  o	The reservation has a unique ID, the customer’s name, the number of people in the party, the reservation time, the associated table, and the status of the reservation (e.g., "CONFIRMED", "CANCELED").
•	Methods:
  o	Provides getters for all the properties of the reservation (e.g., customerName(), partySize()).
  o	The toString() method returns a string representation of the reservation, which includes all the reservation details.

## 5. Filters.java - Filtering Utility
The Filters.java class provides a utility method to filter tables based on certain conditions.
What it does:
•	Generic Filtering:
  o	It uses lambda expressions and predicates to filter lists of tables. For example, it can filter out tables that are already reserved or find tables that can accommodate a minimum party size.
•	The filterList method takes a list and a condition (a predicate) and returns a list of items that satisfy that condition.
•	Lambda Expressions:
  o	In the RestaurantApp.java, this filtering is used to find tables that are available for a specific party size, demonstrating the use of lambdas to pass conditions as arguments.

## 6. ReservationStatus.java - Enum for Reservation Status
The ReservationStatus.java is an enum that defines the possible statuses a reservation can have.
What it does:
•	Defines Constants: It defines three possible statuses for a reservation: 
  o	CONFIRMED: The reservation is successfully made.
  o	CANCELED: The reservation was canceled by the user.
  o	COMPLETED: The reservation has been completed (though not currently used in the code).
________________________________________
## 7. BaseFilter.java - Base Class for Filtering
The BaseFilter.java class is the base class for any filtering logic.
What it does:
•	Provides a Filter Method: It defines a filterList method, which can be used by subclasses (like Filters) to implement specific filtering logic.

## How These Files Work Together
•	User Interaction: The user interacts with the RestaurantApp.java class, where they can choose different options like making a reservation or viewing available tables.
•	Managing Reservations: When a user makes a reservation, the RestaurantApp class communicates with the ReservationService.java class to check if a table is available and then creates a new reservation.
•	Filtering Tables: When the user asks to view available tables, the Filters.java class is used to filter tables based on user-specified conditions, like the minimum number of people.
•	Data Management: Reservations and tables are stored in memory and can be saved to or loaded from a file using serialization in ReservationService.java.
