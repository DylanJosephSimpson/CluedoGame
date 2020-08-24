import java.util.HashSet;
import java.util.List;

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
     * Was the murder committed in this room? (false by default)
     */
    private boolean isInvolvedInMurder;


    /**
     * Originally allocated weapons and weapons that are suggested go into this room
     */
    private HashSet<Weapon> weaponsInRoom = new HashSet<>();

    /**
     * Originally allocated characters and characters that are suggested go into this room
     */
    private HashSet<Character> charactersInRoom = new HashSet<>();

    /**
     * Constructor for creating a room
     * @param roomName Name of the room
     */
    public Room(String roomName){
        this.roomName = roomName;
    }

    //TODO: PRAVEEN make sure when a character/player enters or leaves a room to use the addCharacterToRoom/removeCharacterFromRoom methods

    /**
     *  --- Suggestion Method ---
     * Add a weapon to this room (if it is not already in it) when a suggestion is made.
     * @param weapon
     */
    public void addWeaponToRoom(Weapon weapon){
        weaponsInRoom.add(weapon);
    }

    /**
     * --- Suggestion Method ---
     * Remove the weapon from this room when the weapon has to be moved into a different room
     * @param weapon
     */
    public void removeWeaponFromRoom(Weapon weapon){
        weaponsInRoom.remove(weapon);
    }

    /**
     *  --- Suggestion Method ---
     * Add a character to this room (if it is not already in it) when a suggestion is made
     * Also remove the character from its previous room.
     * @param character
     */
    public void addCharacterToRoom(Character character){
        charactersInRoom.add(character);
    }

    /**
     * --- Suggestion Method ---
     * Remove the weapon from this room when the weapon has to be moved into a different room
     * @param character
     */
    public void removeCharacterFromRoom(Character character){
        charactersInRoom.remove(character);
    }

    public boolean isInvolvedInMurder() {
        return isInvolvedInMurder;
    }

    public void setInvolvedInMurder(boolean involvedInMurder) {
        isInvolvedInMurder = involvedInMurder;
    }

    @Override
    public String toString() {
        return roomName;
    }
}
