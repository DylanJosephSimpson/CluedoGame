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

    //TODO: PRAVEEN make sure when a character/player enters or leaves a room to use the addCharacterToRoom/removeCharacterFromRoom methods

    /**
     *  --- Model.Suggestion Method ---
     * Add a weapon to this room (if it is not already in it) when a suggestion is made.
     * @param weapon
     */
    public void addWeaponToRoom(Weapon weapon){
        weaponsInRoom.add(weapon);
    }

    /**
     * --- Model.Suggestion Method ---
     * Remove the weapon from this room when the weapon has to be moved into a different room
     * @param weapon
     */
    public void removeWeaponFromRoom(Weapon weapon){
        weaponsInRoom.remove(weapon);
    }

    /**
     *  --- Model.Suggestion Method ---
     * Add a character to this room (if it is not already in it) when a suggestion is made
     * Also remove the character from its previous room.
     * @param character
     */
    public void addCharacterToRoom(Character character){
        charactersInRoom.add(character);
    }

    /**
     * --- Model.Suggestion Method ---
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

    public String getRoomName() {
        return roomName;
    }

    public List<Tile> getRoomTiles() {
        return roomTiles;
    }

    public void addRoomTile(Tile tile){
        roomTiles.add(tile);
    }

    public Set<Tile> getDoorwayTiles() {
        return doorwayTiles;
    }

    public void setDoorwayTiles(Set<Tile> doorwayTiles) {
        this.doorwayTiles = doorwayTiles;
    }

    public void addDoorWay(Tile doorwayTile){
        doorwayTiles.add(doorwayTile);
    }

    public List<Weapon> getWeaponsInRoom() {
        return weaponsInRoom;
    }

    public List<Character> getCharactersInRoom() {
        return charactersInRoom;
    }

    @Override
    public String toString() {
        return roomName;
    }
}
