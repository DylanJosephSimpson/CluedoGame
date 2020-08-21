/**
 * The player class is responsible for allowing a user to interact with
 * the board and game.
 *
 */
public class Player {

    /**
     * Name of the player
     */
    private String name;

    /**
     * Corresponds to one of the 6 playable characters in the game
     */
    private Character assignedCharacter;

    public Player(String name, Character assignedCharacter) {
        this.name = name;
        this.assignedCharacter = assignedCharacter;
    }

    @Override
    public String toString() {
        return "Player: " + name + " Character: " + assignedCharacter.toString();
    }
}
