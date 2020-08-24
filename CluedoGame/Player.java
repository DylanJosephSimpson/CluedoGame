/**
 * The player class is responsible for allowing a user to interact with
 * the board and game.
 */
public class Player {

    /**
     * Name of the player
     */
    private String name;

    /**
     * Corresponds to one of the 6 playable characters in the game
     */
    private String assignedCharacter;

    /**
     * Boolean which represents if the player is in game
     */
    private boolean activePlayer;

    private Tile currentTile;

    public Player(String name, String assignedCharacter) {
        this.name = name;
        this.assignedCharacter = assignedCharacter;
    }

    /**
     *Method for making an accusation
     **/

    public void makeAccusation(CharacterCard characterCard, WeaponCard weaponCard, RoomCard roomCard) {
        if (!activePlayer || !isInARoom()) {
            return; //If the player is not active due to making a false accusation or not in a room there is no
            // point in making an accusation
        }
        System.out.println("Accusation is being made");


        //False accusation as the envelope does not contain all three of the cards within the envelope
        if (!Board.envelope.contains(characterCard) || !Board.envelope.contains(weaponCard) || !Board.envelope.contains(roomCard)) {
            System.out.println("This is a false accusation ");
            activePlayer = false;

        }
        //A successful accusation has been made and the game has been completed
        else {
            System.out.println("Game is completed, a successful accusation has card ");
        }

    }


    /**
     * Method that checks if the player is in a room
     *
     * @return
     */
    private boolean isInARoom() {
        return true;
    }

    @Override
    public String toString() {
        return "Player: " + name + " Character: " + assignedCharacter.toString();
    }
}
