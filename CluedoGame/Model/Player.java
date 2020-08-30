package Model;

import View.CluedoGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.CluedoGUI.addRoomTiles;

/**
 * The player class is responsible for allowing a user to interact with
 * the board and game.
 */
public class Player {

    public boolean madeSuggestion = false;

    public boolean isMadeSuggestion() {
        return madeSuggestion;
    }

    public void setMadeSuggestion(boolean madeSuggestion) {
        this.madeSuggestion = madeSuggestion;
    }

    public static ArrayList<Player> playerList = new ArrayList<>();

    private ArrayList<Card> hand = new ArrayList<>();

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

//    public boolean canLeaveRoom = false;

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
        this.madeSuggestion = false;
    }

    /**
     * Method for making an accusation
     **/

    public void makeAccusation(Card characterCard, Card weaponCard, Card roomCard) {
        System.out.println(Board.envelope);
        if (!canMakeActions || !isInARoom()) {
            return; //If the player is not active due to making a false accusation or not in a room there is no
            // point in making an accusation, they can still move.
        }

        //False accusation as the envelope does not contain all three of the cards within the envelope
        if (!Board.envelope.contains(characterCard) || !Board.envelope.contains(weaponCard) || !Board.envelope.contains(roomCard)) {
            JOptionPane.showMessageDialog(null,
                    "Your Accusation is WRONG!",
                    "Incorrect Accusation",
                    JOptionPane.PLAIN_MESSAGE);
            canMakeActions = false;
        }
        //A successful accusation has been made and the game has been completed
        else {
            JOptionPane.showMessageDialog(null,
                    "Your Accusation is CORRECT!",
                    "Correct Accusation",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void doorSymbolToRoomName(){

    }

    public void makeSuggestion(Card characterCard, Card weaponCard) throws IOException {
        if (!canMakeActions || !isInARoom()) {
            return; //If the player is not active due to making a false accusation or not in a room there is no
            // point in making an accusation, they can still move.
        }
        System.out.println("Suggestion is being made");


        Card roomCard = Board.getCardHashMap().get(findRoom(this.getAssignedCharacter().currentTile.getCol(), this.getAssignedCharacter().currentTile.getRow()).toString());

        //cycles through all other players to see if they have a card that matches the suggestion
        for (Player otherPs : Player.getPlayerList()) {
            if (otherPs != this) {

                System.out.println("The next player is " + otherPs.getName());
                //adds all the cards of the current players hand that match the suggestion to a list

                ArrayList<Card> hasSuggestedCards = new ArrayList<>();

                if (otherPs.getHand().contains(characterCard)) {
                    hasSuggestedCards.add(characterCard);
                }if (otherPs.getHand().contains(weaponCard)) {
                    hasSuggestedCards.add(weaponCard);
                }if (otherPs.getHand().contains(roomCard)) {
                    hasSuggestedCards.add(roomCard);
                }



                //if the next player has nothing to offer, continue to next turn
                if (hasSuggestedCards.size() == 0) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, otherPs.getName()+" has nothing that matches your suggestion", "Suggestee", JOptionPane.PLAIN_MESSAGE);

                } else {
                    String cardToShow;
                    //if the next player has only one card to show, show this to current player
                    if (hasSuggestedCards.size() == 1) {
                        cardToShow = hasSuggestedCards.get(0).toString();
                    }
                    //if the next player has more than one card to show, pass on to that player, show that player their hand and from this the player can select one of the cards
                    else {
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, this.getName() + " look away and pass on to " + otherPs.getName(), "Look away", JOptionPane.PLAIN_MESSAGE);


                        JPanel CardToShowWindow = new JPanel();
                        String[] cards;
                        if (hasSuggestedCards.size() > 2){
                            cards = new String[3];
                        }else{
                            cards = new String[2];
                        }
                        cards[0] = hasSuggestedCards.get(0).toString();
                        cards[1] = hasSuggestedCards.get(1).toString();
                        if(hasSuggestedCards.size()>2){
                            cards[2] = hasSuggestedCards.get(2).toString();
                        }
                        JLabel CardBoxDesc = new JLabel("Chose card to Show");
                        JComboBox<String> CardSelection = new JComboBox<>(cards);


                        GridLayout layout = new GridLayout(1, 1);

                        CardToShowWindow.setLayout(layout);

                        CardToShowWindow.add(CardBoxDesc);
                        CardToShowWindow.add(CardSelection);

                        JOptionPane.showConfirmDialog(null, CardToShowWindow, "Choose between the following to show to the suggester", JOptionPane.OK_CANCEL_OPTION);
                        cardToShow = (String) CardSelection.getSelectedItem();
                    }
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Pass back to "+this.getName(), "Pass Back to Suggester", JOptionPane.PLAIN_MESSAGE);

                    String[] parts = cardToShow.split(" ");
                    String output;
                    if(parts.length>1){
                        output = parts[1];
                    }else{
                        output = cardToShow;
                    }
                    System.out.println(output);
                    output.replaceAll(" ", "" );
                    BufferedImage image = ImageIO.read(new File("Cards/"+output+".png"));
                    JLabel picLabel = new JLabel(new ImageIcon(image));
                    JOptionPane.showMessageDialog(null, picLabel, "Card from Suggestion: "+ cardToShow, JOptionPane.PLAIN_MESSAGE, null);
                    return;
                }
            }
        }
    }


    /**
     * Method that checks if the player is in a room
     *
     * @return
     */
    public boolean isInARoom() {

        int xTile = this.assignedCharacter.currentTile.x / 30;
        int yTile = this.assignedCharacter.currentTile.y / 30;

        String tileType = Board.getOriginalBoardLayoutArray()[yTile][xTile];
        Pattern pattern = Pattern.compile("[kbcdlhsiy@]");
        Matcher matcher = pattern.matcher(tileType);

        return matcher.find();
    }



    /**
     * Locates a room if a room tile is present surrounding it. This should only be used for an @ tile.
     *
     * @param x x-coordinate of the tile (from the board array)
     * @param y y-coordinate of the tile (from the board array)
     * @return
     */
    public static Room findRoom(int x, int y) {
        int col = x/30;
        int row = y/30;
        String topLeftDiagonalTile = Board.getOriginalBoardLayoutArray()[row - 1][col - 1];
        String topMiddleTile = Board.getOriginalBoardLayoutArray()[row - 1][col];
        String topRightDiagonalTile = Board.getOriginalBoardLayoutArray()[row - 1][col + 1];
        String midLeftTile = Board.getOriginalBoardLayoutArray()[row][col - 1];
        String midRightTile = Board.getOriginalBoardLayoutArray()[row][col + 1];
        String botLeftDiagonalTile = Board.getOriginalBoardLayoutArray()[row + 1][col - 1];
        String botMiddleTile = Board.getOriginalBoardLayoutArray()[row + 1][col];
        String botRightDiagonalTile = Board.getOriginalBoardLayoutArray()[row + 1][col + 1];

        Pattern pattern = Pattern.compile("[kbcdlhsiy]"); //room symbols
        if (topLeftDiagonalTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topLeftDiagonalTile))) {
                    return r;
                }
            }
        } else if (topMiddleTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topMiddleTile))) {
                    return r;
                }
            }
        } else if (topRightDiagonalTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topRightDiagonalTile))) {
                    return r;
                }
            }
        } else if (midLeftTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(midLeftTile))) {
                    return r;
                }
            }
        } else if (midRightTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(midRightTile))) {
                    return r;
                }
            }
        } else if (botLeftDiagonalTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botLeftDiagonalTile))) {
                    return r;
                }
            }
        } else if (botMiddleTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botMiddleTile))) {
                    return r;
                }
            }
        } else if (botRightDiagonalTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botRightDiagonalTile))) {
                    return r;
                }
            }
        }
        throw new RuntimeException("findRoom: Room tile was not found");
    }

//    public void leaveFromSuggestion() {
//        addRoomTiles();
//        String roomCard = findRoom(this.getAssignedCharacter().currentTile.getCol(), this.getAssignedCharacter().currentTile.getRow()).toString();
//        System.out.println(roomCard + " WE ARE IN THIS ROOM");
//        Room r = Board.getRoomFromString(roomCard);
//        System.out.println(r + "ROOM IS ");
//        assert r != null;
//        for(Tile t: r.getDoorwayTiles()){
//            System.out.println(Board.getBoardLayoutArray()[t.getRow()/30][t.getCol()/30] + "TILE TYPE");
//            if(Board.getBoardLayoutArray()[t.getRow()/30][t.getCol()/30].equals("@")){
//                System.out.println("WE CAN MOVE A PLAYER");
//
//                Pattern pattern = Pattern.compile("[SMWGPC]");
//                if(!t.getTileType().matches(String.valueOf(pattern))) {
//
//                    //Board.getBoardLayoutArray()[(t.getRow()) / 30][t.getCol() / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
//                    Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
//                    this.assignedCharacter.currentTile.setY((t.getRow()));
//                    this.assignedCharacter.currentTile.setX((t.getCol()));
//                    this.assignedCharacter.setX(t.getCol());
//                    this.assignedCharacter.setY(t.getRow());
//                    r.removeCharacterFromRoom(this.assignedCharacter);
//                }
//            }
//        }
//    }




    public void leaveFromSuggestion() {
        addRoomTiles();
        String roomCard = findRoom(this.getAssignedCharacter().currentTile.getCol(), this.getAssignedCharacter().currentTile.getRow()).toString();
        System.out.println(roomCard + " WE ARE IN THIS ROOM");
        Room r = Board.getRoomFromString(roomCard);
        System.out.println(r + "ROOM IS ");
        int OGx = this.getAssignedCharacter().currentTile.getCol();
        int OGy = this.getAssignedCharacter().currentTile.getRow();
        assert r != null;
        for(Tile t: r.getDoorwayTiles()){
            System.out.println(Board.getBoardLayoutArray()[t.getRow()/30][t.getCol()/30] + "TILE TYPE");
            if(Board.getBoardLayoutArray()[t.getRow()/30][t.getCol()/30].equals("@")){
                System.out.println("WE CAN MOVE A PLAYER");
                //Set the old tile to what it previously was
                Board.getBoardLayoutArray()[t.getRow() / 30][t.getCol() / 30] = "S";
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[t.getRow() / 30][t.getCol() / 30];
                this.assignedCharacter.currentTile.setY((t.getRow()));
                this.assignedCharacter.currentTile.setX((t.getCol()));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                Board.getBoardLayoutArray()[OGy/30][OGx/30] = Board.getOriginalBoardLayoutArray()[OGy / 30][OGx / 30];
                r.removeCharacterFromRoom(this.assignedCharacter);
            }
        }


    }

    public void move(String dir) {

        switch (dir) {
            case "NORTH":
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() - 30) / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setY((this.assignedCharacter.currentTile.getRow() - 30));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                if (Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30].equals("@")) {
//                    canLeaveRoom = false;
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
//                endMovement();
                break;
            case "SOUTH":
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() + 30) / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setY((this.assignedCharacter.currentTile.getRow() + 30));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                if (Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30].equals("@")) {
//                    canLeaveRoom = false;
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
                break;
            case "EAST":
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow()) / 30][(30 + this.assignedCharacter.currentTile.getCol()) / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setX((this.assignedCharacter.currentTile.getCol() + 30));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                if (Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30].equals("@")) {
//                    canLeaveRoom = false;
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
                break;
            case "WEST":
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow()) / 30][(this.assignedCharacter.currentTile.getCol() - 30) / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setX((this.assignedCharacter.currentTile.getCol() - 30));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                if (Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30].equals("@")) {
//                    canLeaveRoom = false;
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
                break;
            default:
                throw new RuntimeException("EPIC FAIL");
        }


    }

//    public void endMovement() {
//        if(isInARoom() && enteredARoomOnThisTurn){
//        Board.getCurrentPlayer().setRemainingMoves(0);
//            System.out.println("DONE");
//        }
//    }

    public static boolean validMove(Tile tileInFrontOfPlayer) {
        if (Board.getCurrentPlayer().getRemainingMoves() <= 0) {
            return false;
        }
        String tileType = Board.getOriginalBoardLayoutArray()[tileInFrontOfPlayer.getRow()/30][tileInFrontOfPlayer.getCol()/30];
        Pattern pattern = Pattern.compile("[kbcdlhsiy]");
        Matcher matcher = pattern.matcher(tileType);
        if(matcher.find()){

            return false;
        }

        //checks if the next tile is a character or not
        for (Character c : Board.getCharacterArrayList()) {
            if (c != Board.getCurrentPlayer().getAssignedCharacter()) {
                if (c.getX() == tileInFrontOfPlayer.getCol() && c.getY() == tileInFrontOfPlayer.getRow()) {
                    return false;
                }
            }
        }


        //checks if the next tile has been visited by checking the list of tiles that the character has visited in their turn
        for (Tile t : CluedoGUI.getPreviouslyTraversedTiles()) {
            if (t.getCol() == tileInFrontOfPlayer.getCol() && t.getRow() == tileInFrontOfPlayer.getRow()) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "You can not visit a space that you have already been in your turn.", "Keep Moving Forward", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public void addHand(Card card) {
//        System.out.println(hand);
        this.hand.add(card);
    }

    public ArrayList<Card> getHand() {
//        System.out.println(hand.size());
        return hand;
    }

    /**
     * Custom string of all the players who have been created
     *
     * @return a formatted String for the Menu GUI
     */
    public static String customToStringForPlayerList() {
        if (playerList.size() == 0) {
            return "No players have been added to the game yet!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current Players:");
        for (Player player : playerList) {
            stringBuilder.append(player.getName()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Player: " + name + " Character: " + assignedCharacter.toString();
    }

   /* public void leaveRoom() {
        System.out.println(Board.getCurrentPlayer().RemainingMoves + "REMAINING MOVES");
        if (Board.getCurrentPlayer().RemainingMoves >= 0) {
//            System.out.println("WE ARE HERE");
            if (Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() / 30) - 1][this.assignedCharacter.getX() / 30].equals(" ")) {
                move("NORTH");
//                System.out.println("WE ARE NORTH");

            } else if (Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() / 30) + 1][this.assignedCharacter.getX() / 30].equals(" ")) {
                move("SOUTH");
            } else if (Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() / 30)][this.assignedCharacter.getX() / 30 + 1].equals(" ")) {
                move("EAST");
            } else if (Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() / 30)][this.assignedCharacter.getX() / 30 - 1].equals(" ")) {
                move("WEST");
            }
        }
    }*/



    /**
     * Checks if tile is in bounds of the board
     *
     * @param tile being checked
     * @return if it is in bounds
     */
    private boolean tileInBounds(Tile tile) {
        return tile.getRow() > 0 && tile.getRow() < 25 && tile.getCol() > 0 && tile.getCol() < 24;
    }

    /**
     * Helper method for checking if a tile's coordinates are in bounds of the board
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return if its in bounds
     */
    private boolean coordinateInBounds(int x, int y) {
        return y > 0 && y < 25 && x > 0 && x < 24;
    }
}
