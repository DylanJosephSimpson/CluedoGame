/**
 * The player class is responsible for allowing a user to interact with
 * the board and game.
 *
 */
public class Player {

    /**
     * Corresponds to one of the 6 playable characters in the game
     */
    private Character assignedCharacter;

    /**
     * Name of the player
     */
    private String name;

    public Player(Character assignedCharacter, String name) {
        this.assignedCharacter = assignedCharacter;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player: " + name + " Character: " + assignedCharacter.toString();
    }
}
