/**
 * The room class is responsible for keeping track of
 * what is stored inside of rooms.
 */
public class Room {

    /**
     * Name of the room
     */
    private String roomName;

    /**
     * Constructor for creating a room
     * @param roomName Name of the room
     */
    public Room(String roomName){
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return roomName;
    }
}
