import java.io.*;

/**
 * Represents a single hotel room.
 * Implements Serializable so Room objects can be saved to and loaded from a file.
 */
public class Room implements Serializable{
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isBooked;

    // Creates a new room; every new room starts as not booked
    public Room(int roomNumber, String roomType, double pricePerNight){
        this.roomNumber=roomNumber;
        this.roomType=roomType;
        this.pricePerNight=pricePerNight;
        this.isBooked=false;
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public String getRoomType(){
        return roomType;
    }
    public double getPricePerNight(){
        return pricePerNight;
    }
    public boolean isBooked(){
        return isBooked;
    }
    // Sets the booking status (true = booked, false = available)
    public void setBooked(boolean booked){
        isBooked=booked;
    }
    // Prints all details of the room to the console
    public void display(){
        System.out.println("Room number: "+roomNumber);
        System.out.println("Room Type: "+roomType);
        System.out.println("Price per Night: "+pricePerNight);
        System.out.println("Status: "+(isBooked? "Booked":"Available"));
    }

}