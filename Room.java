import java.io.*;

public class Room implements Serializable{
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isBooked;

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
    public void setBooked(boolean booked){
        isBooked=booked;
    }
    public void display(){
        System.out.println("Room number: "+roomNumber);
        System.out.println("Room Type: "+roomType);
        System.out.println("Price per Night: "+pricePerNight);
        System.out.println("Status: "+(isBooked? "Booked":"Avaiable "));
    }

}