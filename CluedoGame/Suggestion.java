public class Suggestion {

    /**
     * Suspected murderer that is suggested
     */
    private Character character;

    /**
     * Suspected murder weapon that is suggested
     */
    private Weapon weapon;

    /**
     * The room that the suggestion was made in
     */
    private Room room;

    /**
     * Suggestion constructor
     * @param character
     * @param weapon
     * @param room
     */
    public Suggestion(Character character, Weapon weapon, Room room) {
        this.character = character;
        this.weapon = weapon;
        this.room = room;
    }

    /**
     * When a suggestion is made, the suggested character and weapon are moved into the room where the suggestion occurred.
     */
    public void moveItems(){
        // *** (1) Move the suggested character into the room ***
        //remove the character from its current room if they were in one
        if (character.getCurrentRoom() != null) {
            character.getCurrentRoom().removeCharacterFromRoom(character);
        }

        //add the character to the room where the suggestion occurred in
        room.addCharacterToRoom(character);
        character.setCurrentRoom(room);

        // *** (2) Move the suggested weapon into the new room ***
        //remove the weapon from its current room
        weapon.getCurrentRoom().removeWeaponFromRoom(weapon);

        //add the weapon to the room where the suggestion occurred in
        room.addWeaponToRoom(weapon);
        weapon.setCurrentRoom(room);
    }

    @Override
    public String toString() {
        return "Suggestion: " + character.toString() + ", " + weapon.toString() + ", " + room.toString();
    }
}
