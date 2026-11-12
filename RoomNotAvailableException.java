/**
 * User-defined checked exception.
 * Thrown when someone tries to book a room that is already booked.
 */
public class RoomNotAvailableException extends Exception{
    public RoomNotAvailableException(String message){
        super(message);
    }
}
