package Model;

import View.CluedoGUI;
import View.SuggestionSetup;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The player class is responsible for allowing a user to interact with
 * the board and game.
 */
public class Player {

    public static ArrayList<Player> playerList = new ArrayList<>();

    private ArrayList<Card> hand = new ArrayList<>();

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public static void addPlayerList(Player player) {
        System.out.println(player.toString() + "NEW PLAYER");
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

    public boolean canMakeActions() {
        return canMakeActions;
    }

    public void setCanMakeActions(boolean canMakeActions) {
        this.canMakeActions = canMakeActions;
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
    private boolean canMakeActions = true;

    /**
     * Checks if the player is trying to exit the room
     */
    private boolean justExitedRoom;

    private int newX;
    private int newY;

    private Tile currentTile;

    public Player(String name, Character assignedCharacter) {
        this.name = name;
        this.assignedCharacter = assignedCharacter;
    }

    /**
     *Method for making an accusation
     **/

    public void makeAccusation(Card characterCard, Card weaponCard, Card roomCard) {
        System.out.println("-----");
        for(Card s : Board.envelope){
            System.out.println(s.toString());
        }
        System.out.println(characterCard.toString());
        System.out.println(weaponCard.toString());
        System.out.println(roomCard.toString());
        System.out.println("-----");

        if (!canMakeActions || !isInARoom()) {
            return; //If the player is not active due to making a false accusation or not in a room there is no
            // point in making an accusation, they can still move.
        }
        System.out.println("Accusation is being made");

        //False accusation as the envelope does not contain all three of the cards within the envelope
        if (!Board.envelope.contains(characterCard) || !Board.envelope.contains(weaponCard) || !Board.envelope.contains(roomCard)) {
            System.out.println("This is a false accusation ");
            canMakeActions = false;
        }
        //A successful accusation has been made and the game has been completed
        else {
            System.out.println("Game is completed, a successful accusation has card ");
        }

    }

    public void makeSuggestion(Card characterCard, Card weaponCard, Card roomCard) {
        if (!canMakeActions || !isInARoom()) {
            return; //If the player is not active due to making a false accusation or not in a room there is no
            // point in making an accusation, they can still move.
        }
        System.out.println("Accusation is being made");

        //False accusation as the envelope does not contain all three of the cards within the envelope
        if (!Board.envelope.contains(characterCard) || !Board.envelope.contains(weaponCard) || !Board.envelope.contains(roomCard)) {
            System.out.println("This is a false accusation ");
            canMakeActions = false;
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
    public boolean isInARoom() {

        int xTile = this.assignedCharacter.getX()/30;
        int yTile = this.assignedCharacter.getY()/30;

        String tileType = Board.getOriginalBoardLayoutArray()[yTile][xTile];
        Pattern pattern = Pattern.compile("[kbcdlhsiy@]");
        Matcher matcher = pattern.matcher(tileType);

        return matcher.find();
    }

    /**
     * Locates a room if a room tile is present surrounding it. This should only be used for an @ tile.
     * @param x x-coordinate of the tile (from the board array)
     * @param y y-coordinate of the tile (from the board array)
     * @return
     */
    public static Room findRoom(int x, int y){
        String topLeftDiagonalTile =  Board.getOriginalBoardLayoutArray()[y-1][x-1];
        String topMiddleTile = Board.getOriginalBoardLayoutArray()[y-1][x];
        String topRightDiagonalTile = Board.getOriginalBoardLayoutArray()[y-1][x+1];
        String midLeftTile = Board.getOriginalBoardLayoutArray()[y][x-1];
        String midRightTile = Board.getOriginalBoardLayoutArray()[y][x+1];
        String botLeftDiagonalTile = Board.getOriginalBoardLayoutArray()[y+1][x-1];
        String botMiddleTile = Board.getOriginalBoardLayoutArray()[y+1][x];
        String botRightDiagonalTile = Board.getOriginalBoardLayoutArray()[y+1][x+1];

        Pattern pattern = Pattern.compile("[kbcdlhsiy]"); //room symbols
        if (topLeftDiagonalTile.matches(String.valueOf(pattern))){
            for (Room r : Board.getAllRooms()){
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topLeftDiagonalTile))){
                    return r;
                }
            }
        }
        else if (topMiddleTile.matches(String.valueOf(pattern))){
            for (Room r : Board.getAllRooms()){
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topMiddleTile))){
                    return r;
                }
            }
        }
        else if (topRightDiagonalTile.matches(String.valueOf(pattern))){
            for (Room r : Board.getAllRooms()){
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topRightDiagonalTile))){
                    return r;
                }
            }
        }
        else if (midLeftTile.matches(String.valueOf(pattern))){
            for (Room r : Board.getAllRooms()){
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(midLeftTile))){
                    return r;
                }
            }
        }
        else if (midRightTile.matches(String.valueOf(pattern))){
            for (Room r : Board.getAllRooms()){
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(midRightTile))){
                    return r;
                }
            }
        }
        else if (botLeftDiagonalTile.matches(String.valueOf(pattern))){
            for (Room r : Board.getAllRooms()){
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botLeftDiagonalTile))){
                    return r;
                }
            }
        }
        else if (botMiddleTile.matches(String.valueOf(pattern))){
            for (Room r : Board.getAllRooms()){
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botMiddleTile))){
                    return r;
                }
            }
        }
        else if (botRightDiagonalTile.matches(String.valueOf(pattern))){
            for (Room r : Board.getAllRooms()){
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botRightDiagonalTile))){
                    return r;
                }
            }
        }
        throw new RuntimeException("findRoom: Room tile was not found");
    }

    /**
     * When a player moves in a certain direction the x or y position changes for their character and then board is redrawn
     *
     * @param dir
     */
    public void move(String dir){
        //First, let the player make a suggestion if they have just been transported into a room
        if (assignedCharacter.isTransportedIntoRoom()) {
            new SuggestionSetup(Board.getCurrentPlayer());

            //reset this
            assignedCharacter.setTransportedIntoRoom(false);
        }
        //Second, check if the player is currently in a room, if so, give the player the option to exit
        else if (isInARoom() && !justExitedRoom){
            Object[] options = {"No",
                    "Yes"};
            int optionSelected = JOptionPane.showOptionDialog(new JFrame(),
                    assignedCharacter.toString() + " would you like to exit the " + assignedCharacter.getCurrentRoom().toString() + "? You may only remain in this room if the doorways are blocked!",
                    assignedCharacter.getCurrentRoom().toString(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    null);
            switch (optionSelected){
                //option "no"
                case 0:
                    setRemainingMoves(0);
                    break;
                //option "yes"
                case 1:
                    //let the player choose which doorway to exit to
                    int exitNo = 1;

                    //GUI elements
                    JPanel exitRoomPanel = new JPanel();
                    ButtonGroup buttonGroup = new ButtonGroup();
                    for (Tile doorway : assignedCharacter.getCurrentRoom().getDoorwayTiles()){
                        JRadioButton exitButton = new JRadioButton("Doorway " + exitNo);
                        exitButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                newX = doorway.getX();
                                newY = doorway.getY();
                            }
                        });
                        buttonGroup.add(exitButton);
                        exitRoomPanel.add(exitButton);
                        exitNo++;
                    }
                    int result = JOptionPane.showConfirmDialog(null,
                            exitRoomPanel,
                            "Choose an exit",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    //When they press OK:
                    if (result == 0) {
                        //Remove them from the room
                        assignedCharacter.getCurrentRoom().removeCharacterFromRoom(assignedCharacter);
                        assignedCharacter.setCurrentRoom(null);
                        justExitedRoom = true;

                        //Actually move them on the board
                        assignedCharacter.setX(newX);
                        assignedCharacter.setY(newY);
                    }
                    break;
            }
        }
        //else let them move normally
        else {
            System.out.println(this.getRemainingMoves());
            boolean tempTest = isInARoom();

            if (dir.equals("NORTH")) {
                assignedCharacter.setY(assignedCharacter.getY() - 30);
            } else if (dir.equals("EAST")) {
                assignedCharacter.setX(assignedCharacter.getX() + 30);
            } else if (dir.equals("SOUTH")) {
                assignedCharacter.setY(assignedCharacter.getY() + 30);
            } else if (dir.equals("WEST")) {
                assignedCharacter.setX(assignedCharacter.getX() - 30);
            }

            if (!justExitedRoom && (!tempTest && isInARoom() || tempTest && !isInARoom())) {
                //current location
                int xTile = this.assignedCharacter.getX() / 30;
                int yTile = this.assignedCharacter.getY() / 30;

                //find the room the player is stepping into
                //add the character to the room and set the character's current room to be the room being entered
                Room enteredRoom = findRoom(xTile, yTile);
                enteredRoom.addCharacterToRoom(assignedCharacter);
                assignedCharacter.setCurrentRoom(enteredRoom);

                //player has entered a room. Give the player an option to make a suggestion.
                new SuggestionSetup(Board.getCurrentPlayer());

                //they should stop moving after entering a room
                this.setRemainingMoves(0);
            }

            //reset this once they have moved out of a room and are traversing normally
            justExitedRoom = false;
        }

    }

    public static boolean validMove(Tile tileInFrontOfPlayer) {
        if (Board.getCurrentPlayer().getRemainingMoves() <= 0) {
            return false;
        }

        //checks if the next tile is a character or not
        for (Character c : Board.getCharacterArrayList()) {
            if (c != Board.getCurrentPlayer().getAssignedCharacter()) {
                if (c.getX() == tileInFrontOfPlayer.getX() && c.getY() == tileInFrontOfPlayer.getY()) {
                    return false;
                }
            }
        }

        //checks if the next tile has been visited by checking the list of tiles that the character has visited in their turn
        for (int[] previousTile : CluedoGUI.getPreviouslyTraversedTiles() ) {
            if (previousTile[0] == tileInFrontOfPlayer.getX() / 30 && previousTile[1] == tileInFrontOfPlayer.getY() / 30) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "You can not visit a space that you have already been in your turn.", "Keep Moving Forward", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public void addHand(Card card) {
        System.out.println(hand);
        this.hand.add(card);
    }

    public ArrayList<Card> getHand() {
        System.out.println(hand.size());
        return hand;
    }

    /**
     * Custom string of all the players who have been created
     * @return a formatted String for the Menu GUI
     */
    public static String customToStringForPlayerList(){
        if(playerList.size()==0){
            return "No players have been added to the game yet!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current Players:");
        for(Player player : playerList){
            stringBuilder.append(player.getName()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Player: " + name + " Character: " + assignedCharacter.toString();
    }
}
