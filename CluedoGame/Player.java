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
           for (Room r : CluedoGUI.allRooms){
               if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topLeftDiagonalTile))){
                   return r;
               }
           }
        }
       else if (topMiddleTile.matches(String.valueOf(pattern))){
           for (Room r : CluedoGUI.allRooms){
               if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topMiddleTile))){
                   return r;
               }
           }
       }
       else if (topRightDiagonalTile.matches(String.valueOf(pattern))){
           for (Room r : CluedoGUI.allRooms){
               if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topRightDiagonalTile))){
                   return r;
               }
           }
       }
       else if (midLeftTile.matches(String.valueOf(pattern))){
           for (Room r : CluedoGUI.allRooms){
               if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(midLeftTile))){
                   return r;
               }
           }
       }
       else if (midRightTile.matches(String.valueOf(pattern))){
           for (Room r : CluedoGUI.allRooms){
               if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(midRightTile))){
                   return r;
               }
           }
       }
       else if (botLeftDiagonalTile.matches(String.valueOf(pattern))){
           for (Room r : CluedoGUI.allRooms){
               if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botLeftDiagonalTile))){
                   return r;
               }
           }
       }
       else if (botMiddleTile.matches(String.valueOf(pattern))){
           for (Room r : CluedoGUI.allRooms){
               if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botMiddleTile))){
                   return r;
               }
           }
       }
       else if (botRightDiagonalTile.matches(String.valueOf(pattern))){
           for (Room r : CluedoGUI.allRooms){
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
        //First, check if the player is currently in a room, if so, give the player the option to exit
        if (isInARoom()){
            Object[] options = {"No",
                    "Yes"};
            // optionSelected = 0 (yes), = 1 (no), = 2 (return to menu)
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
                    //player has chosen to stay in the room, they forfeit their turn but are given the option to make another suggestion
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
                                //add this
                            }
                        });
                        buttonGroup.add(exitButton);
                        exitRoomPanel.add(exitButton);
                        exitNo++;
                    }

                    //Remove them from the room
                    assignedCharacter.getCurrentRoom().removeCharacterFromRoom(assignedCharacter);
                    assignedCharacter.setCurrentRoom(null);
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

            if (!tempTest && isInARoom() || tempTest && !isInARoom()) {
                //current location
                int xTile = this.assignedCharacter.getX() / 30;
                int yTile = this.assignedCharacter.getY() / 30;


                //find the room the player is stepping into
                //add the character to the room and set the character's current room to be the room being entered
                Room enteredRoom = findRoom(xTile, yTile);
                enteredRoom.addCharacterToRoom(assignedCharacter);
                assignedCharacter.setCurrentRoom(enteredRoom);

                //they should stop moving after entering a room
                this.setRemainingMoves(0);

                //player has entered a room. Give the player an option to make a suggestion.
                new SuggestionWindow("You have entered a room, make a suggestion?", enteredRoom);
            }
        }

    }

    public void addHand(Card card) {
        this.hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player: " + name + " Character: " + assignedCharacter.toString();
    }
}
