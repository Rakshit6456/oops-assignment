import java.util.*;
import java.io.*;

public class HotelBookingSystem {
    static ArrayList<Room> rooms= new ArrayList<>();
    static Scanner sc= new Scanner(System.in);

    static void loadRooms(){
        try(ObjectInputStream in= new ObjectInputStream(new FileInputStream("hotelrooms.dat"))){
            rooms= (ArrayList<Room>) in.readObject();
            System.out.println("Room data loaded successfully");
        }
        catch(FileNotFoundException e){
            System.out.println("No previous data found");
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Error loading room data");
        }
    }
    static void saveRooms(){
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("hotelrooms.dat"))){
            out.writeObject(rooms);
            System.out.println("Rooms data saved successfully");
        }
        catch(IOException e){
            System.out.println("Error saving the data of room");
        }
    }
    static void addRoom(){
        try{
            System.out.println("Enter room number: ");
            int num= Integer.parseInt(sc.nextLine());
            for(Room room:rooms){
                if(room.getRoomNumber() == num){
                    System.out.println("room number already exists");
                    return;
                }
            }
            System.out.println("Enter room type: ");
            String type= sc.nextLine();

            System.out.println("Enter price per night: ");
            double price= Double.parseDouble(sc.nextLine());

            if(price<=0){
                System.out.println("Price must be positive");
                return;
            }
            Room room = new Room(num, type, price);
            rooms.add(room);
            System.out.println("Room added successfully");
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input, enter numbers correctly");
        }
    }
    static void displayRooms(){
        if(rooms.isEmpty()){
            System.out.println("no rooms available");
            return;
        }
        for(Room room: rooms){
            room.display();
        }
    }
    static void bookRoom(){
        try{
            System.out.println("Enter room number to book: ");
            int num= Integer.parseInt(sc.nextLine());

            for(Room room: rooms){
                if(room.getRoomNumber()== num){
                    if(room.isBooked()){
                        throw new RoomNotAvailableException("Room is already booked");
                    }   
                }
                room.setBooked(true);
                System.out.println("Room booking successfull");
                return;
            }
            System.out.println("Room not found");
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input, enter a valid room number");
        }
        catch(RoomNotAvailableException e){
            System.out.println("Booking error: "+ e.getMessage());
        }
    }
    static void checkOut(){
        try{
            System.out.println("enter room number for checkout: ");
            int number= Integer.parseInt(sc.nextLine());
            for(Room room: rooms){
                if (room.getRoomNumber()== number) {
                    if(!room.isBooked()){
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
        catch(NumberFormatException e){
            System.out.println("invalid input, enter a vlid room number");
        }
    }
    public static void main(String[] args){
        loadRooms();
        int choice=0;

        do{
            System.out.println("\nHotel Room Booking System");
            System.out.println("1. Add Room");
            System.out.println("2. Display all rooms");
            System.out.println("2. Book a room");
            System.out.println("Check-out: ");
            System.out.println("exit");
            try{
                System.out.println("enter your choice: ");
                choice= Integer.parseInt(sc.nextLine());

                switch(choice){
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
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("invalid choice, try again");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a valid inout");
            }
        }while(choice!=5);
        sc.close();
    }
}