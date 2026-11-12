# Simple Hotel Room Booking System

A simple console-based Java program that lets a hotel manage its rooms. You can add rooms, view them, book a room, and check a guest out. Room data is saved to a file when you exit, so it is still there the next time you run the program.

This project was made as a college assignment to practice core Java concepts.

## Features

- Add a new room (room number, room type, price per night)
- Prevents duplicate room numbers and non-positive prices
- Display all rooms with their details and status (Available / Booked)
- Book a room (a room that is already booked cannot be booked again)
- Check out a room
- Input validation for invalid numbers and menu choices
- Automatically saves room data on exit and loads it on startup

## Technologies Used

- Java
- Object-Oriented Programming (classes, objects, encapsulation)
- `ArrayList` to store rooms
- Exception Handling (including a custom exception)
- File Handling
- Serialization (`Serializable`, `ObjectOutputStream`, `ObjectInputStream`)

## Project Structure

```
HotelRoomBookSystem/
├── HotelBookingSystem.java        # Main class with the menu and program logic
├── Room.java                      # Room class (Serializable)
├── RoomNotAvailableException.java # Custom exception for already-booked rooms
├── README.md
└── .gitignore
```

## How to Compile and Run

Make sure the JDK is installed, then open a terminal in the project folder and run:

```bash
javac *.java
java HotelBookingSystem
```

## Menu Options

```
1. Add Room
2. Display all rooms
3. Book a room
4. Check-out
5. Exit
```

Choose **5. Exit** to save your data. If you close the program in any other way, your changes will not be saved.

## How Data Is Saved and Loaded (Serialization)

Serialization means converting a Java object into a stream of bytes so it can be stored in a file and rebuilt later.

- The `Room` class implements the `Serializable` interface, which allows `Room` objects to be written to a file.
- **Saving:** When you choose *Exit*, `saveRooms()` uses an `ObjectOutputStream` to write the whole `ArrayList<Room>` to a file named `hotelrooms.dat`.
- **Loading:** When the program starts, `loadRooms()` uses an `ObjectInputStream` to read the list back from `hotelrooms.dat`. If the file does not exist yet (first run), the program prints "No previous data found" and starts with an empty list.

The `hotelrooms.dat` file is created automatically in the folder where you run the program. It is not included in the repository (see `.gitignore`).
