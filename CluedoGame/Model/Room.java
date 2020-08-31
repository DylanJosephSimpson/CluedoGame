package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<Weapon> weaponsInRoom = new ArrayList<>();

    /**
     * Originally allocated characters and characters that are suggested go into this room
     */
    private List<Character> charactersInRoom = new ArrayList<>();

    /**
     * Collection of room tiles
     */
    private List<Tile> roomTiles = new ArrayList<>();

    /**
     * Collection of doorways of this room (if it has any)
     */
    private Set<Tile> doorwayTiles = new HashSet<>();

    /**
     * Constructor for creating a room
     * @param roomName Name of the room
     */
    public Room(String roomName){
        this.roomName = roomName;
    }

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

    /** Check to see if the Room is involved with the murder
     */
    public boolean isInvolvedInMurder() {
        return isInvolvedInMurder;
    }

    /**
     * Getter for room nanme
     * @return the Room's name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Getting the room tiles
     * @return the list of room-only tiles
     */

    public List<Tile> getRoomTiles() {
        return roomTiles;
    }

    /**
     * Adding a tile to the room
     * @param tile the tile to be added
     */
    public void addRoomTile(Tile tile){
        roomTiles.add(tile);
    }

    /**
     * Getting the tiles associated with the doorway
     * @return the set of doorway tiles
     */
    public Set<Tile> getDoorwayTiles() {
        return doorwayTiles;
    }

    /**
     * Method for adding to a doorway
     * @param doorwayTile the doorway tiles
     */
    public void addDoorWay(Tile doorwayTile){
        doorwayTiles.add(doorwayTile);
    }

    /**
     * Getter for the weapons in a room
     * @return the weapons in the room
     */
    public List<Weapon> getWeaponsInRoom() {
        return weaponsInRoom;
    }

    /**
     * Method for getting the characters in a room
     * @return
     */
    public List<Character> getCharactersInRoom() {
        return charactersInRoom;
    }

    @Override
    public String toString() {
        return roomName;
    }
}
