
import java.util.*;
import java.io.*;

/**
 * Menu-driven hotel room booking system.
 * Keeps rooms in an ArrayList and saves/loads them using serialization.
 */
public class HotelBookingSystem {

    static ArrayList<Room> rooms = new ArrayList<>();   // all rooms in the hotel
    static Scanner sc = new Scanner(System.in);         // shared console input

    // Load rooms from file at program start (if the file exists)
    @SuppressWarnings("unchecked")
    static void loadRooms() {

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("hotelrooms.dat"))) {

            rooms = (ArrayList<Room>) in.readObject();

            System.out.println("Room data loaded successfully");

        }
        catch (FileNotFoundException e) {
            System.out.println("No previous data found");
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading room data");
        }
    }

    // Save rooms to file when the user exits
    static void saveRooms() {

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("hotelrooms.dat"))) {

            out.writeObject(rooms);

            System.out.println("Room data saved successfully");

        }
        catch (IOException e) {
            System.out.println("Error saving room data");
        }
    }

    // Add a new room (rejects duplicate numbers and non-positive prices)
    static void addRoom() {

        try {
            System.out.println("Enter room number: ");
            int num = Integer.parseInt(sc.nextLine());

            for (Room room : rooms) {

                if (room.getRoomNumber() == num) {
                    System.out.println("Room number already exists");
                    return;
                }
            }

            System.out.println("Enter room type: ");
            String type = sc.nextLine();

            System.out.println("Enter price per night: ");
            double price = Double.parseDouble(sc.nextLine());

            if (price <= 0) {
                System.out.println("Price must be positive");
                return;
            }

            Room room = new Room(num, type, price);
            rooms.add(room);

            System.out.println("Room added successfully");

        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input, enter numbers correctly");
        }
    }

    // Display all rooms
    static void displayRooms() {

        if (rooms.isEmpty()) {
            System.out.println("No rooms available");
            return;
        }

        for (Room room : rooms) {
            room.display();
        }
    }

    // Book a room; throws RoomNotAvailableException if it is already booked
    static void bookRoom() {

        try {
            System.out.println("Enter room number to book: ");
            int num = Integer.parseInt(sc.nextLine());

            for (Room room : rooms) {

                if (room.getRoomNumber() == num) {

                    if (room.isBooked()) {
                        throw new RoomNotAvailableException(
                                "Room is already booked");
                    }

                    room.setBooked(true);

                    System.out.println("Room booking successful");
                    return;
                }
            }

            System.out.println("Room not found");

        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input, enter a valid room number");
        }
        catch (RoomNotAvailableException e) {
            System.out.println("Booking error: " + e.getMessage());
        }
    }

    // Check-out a room
    static void checkOut() {

        try {
            System.out.println("Enter room number for checkout: ");
            int number = Integer.parseInt(sc.nextLine());

            for (Room room : rooms) {

                if (room.getRoomNumber() == number) {

                    if (!room.isBooked()) {
                        System.out.println("Room is not currently booked");
                        return;
                    }

                    room.setBooked(false);

                    System.out.println("Checkout successful");
                    return;
                }
            }

            System.out.println("Room not found");

        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input, enter a valid room number");
        }
    }

    // Entry point: loads saved data, then shows the menu until the user exits
    public static void main(String[] args) {

        loadRooms();

        int choice = 0;

        do {
            System.out.println("\nHOTEL ROOM BOOKING SYSTEM");
            System.out.println("1. Add Room");
            System.out.println("2. Display all rooms");
            System.out.println("3. Book a room");
            System.out.println("4. Check-out");
            System.out.println("5. Exit");

            try {
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        addRoom();
                        break;

                    case 2:
                        displayRooms();
                        break;

                    case 3:
                        bookRoom();
                        break;

                    case 4:
                        checkOut();
                        break;

                    case 5:
                        saveRooms();
                        System.out.println("Exiting program");
                        break;

                    default:
                        System.out.println("Invalid choice, try again");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid input");
            }

        } while (choice != 5);

        sc.close();
    }
}
