import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The player class is responsible for allowing a user to interact with
 * the board and game.
 */
public class Player {

    public static ArrayList<Player> playerList = new ArrayList<>();

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public static void addPlayerList(Player player) {
        playerList.add(player);
    }

    public static void removePlayerList(int player) {
        playerList.remove(player);
    }

    public static void setPlayerList(ArrayList<Player> playerList) {
        Player.playerList = playerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getAssignedCharacter() {
        return assignedCharacter;
    }

    public void setAssignedCharacter(Character assignedCharacter) {
        this.assignedCharacter = assignedCharacter;
    }

    public boolean isActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(boolean activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public int getRemainingMoves() {
        return RemainingMoves;
    }

    public void setRemainingMoves(int remainingMoves) {
        RemainingMoves = remainingMoves;
    }

    public int RemainingMoves;

    /**
     * Name of the player
     */
    private String name;

    /**
     * Corresponds to one of the 6 playable characters in the game
     */
    private Character assignedCharacter;

    /**
     * Boolean which represents if the player is in game
     */
    private boolean activePlayer;

    private Tile currentTile;

    public Player(String name, Character assignedCharacter) {
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

        int xTile = this.assignedCharacter.getX()/30;
        int yTile = this.assignedCharacter.getY()/30;

        String tileType = Board.getOriginalBoardLayoutArray()[yTile][xTile];
        Pattern pattern = Pattern.compile("[kbcdlhsiy@]");
        Matcher matcher = pattern.matcher(tileType);

        return matcher.find();
    }

    /**
     * When a player moves in a certain direction the x or y position changes for their character and then board is redrawn
     *
     * @param dir
     */
    public void move(String dir){
        System.out.println(this.getRemainingMoves());
        boolean tempTest = isInARoom();

        if(dir.equals("NORTH")){
            assignedCharacter.setY(assignedCharacter.getY()-30);
        }else if(dir.equals("EAST")){
            assignedCharacter.setX(assignedCharacter.getX()+30);
        } else if(dir.equals("SOUTH")){
            assignedCharacter.setY(assignedCharacter.getY()+30);
        } else if(dir.equals("WEST")){
            assignedCharacter.setX(assignedCharacter.getX()-30);
        }

        if (!tempTest && isInARoom() || tempTest && !isInARoom()){
            this.setRemainingMoves(0);

        }

    }

    @Override
    public String toString() {
        return "Player: " + name + " Character: " + assignedCharacter.toString();
    }
}
