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
import java.util.List;

import static View.CluedoGUI.checkDoorwayTiles;

/**
 * The player class is responsible for allowing a user to interact with
 * the board and game.
 */
public class Player {

    /**
     * Method for checking if the player has made a suggestion on the latest turn
     */
    public boolean madeSuggestion = false;
    /**
     * List used to refer to all players
     */
    public static List<Player> playerList = new ArrayList<>();
    /**
     * List of cards that the player has, represents their hand.
     */
    private ArrayList<Card> hand = new ArrayList<>();

    /**
     * The player's remaining moves
     */
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
     * Boolean which represents if the player can make suggestions and or accusations
     */
    private boolean canMakeActions = true;

    /**
     * Checks if the player is trying to exit the room
     */
    private boolean justExitedRoom;

    /**
     * The current tile the player is on
     */
    private Tile currentTile;

    /**
     * Constructor for the player
     *
     * @param name              the name of the player
     * @param assignedCharacter the assgined character for the player
     */
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
            //They cannot make suggestions or accusations anymore but can still move :)
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


    /**
     * The method for making a suggestion
     *
     * @param characterCard the card being suggested
     * @param weaponCard    the weapon being suggested
     * @throws IOException
     */
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
                }
                if (otherPs.getHand().contains(weaponCard)) {
                    hasSuggestedCards.add(weaponCard);
                }
                if (otherPs.getHand().contains(roomCard)) {
                    hasSuggestedCards.add(roomCard);
                }


                //if the next player has nothing to offer, continue to next turn
                if (hasSuggestedCards.size() == 0) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, otherPs.getName() + " has nothing that matches your suggestion", "Suggestee", JOptionPane.PLAIN_MESSAGE);

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
                        if (hasSuggestedCards.size() > 2) {
                            cards = new String[3];
                        } else {
                            cards = new String[2];
                        }
                        cards[0] = hasSuggestedCards.get(0).toString();
                        cards[1] = hasSuggestedCards.get(1).toString();
                        if (hasSuggestedCards.size() > 2) {
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
                    JOptionPane.showMessageDialog(frame, "Pass back to " + this.getName(), "Pass Back to Suggester", JOptionPane.PLAIN_MESSAGE);

                    String[] parts = cardToShow.split(" ");
                    String output;
                    System.out.println(cardToShow);
                    //Show card implementation
                    switch (cardToShow) {
                        case "Ball Room":
                            output = "BallRoom";
                            break;
                        case "Billiard Room":
                            output = "BilliardRoom";
                            break;
                        case "Dining Room":
                            output = "DiningRoom";
                            break;
                        default:
                            if (parts.length > 1) {
                                output = parts[1];
                            } else {
                                output = cardToShow;
                            }
                            break;
                    }
                    System.out.println(output);
                    BufferedImage image = ImageIO.read(new File("Cards/" + output + ".png"));
                    JLabel picLabel = new JLabel(new ImageIcon(image));
                    JOptionPane.showMessageDialog(null, picLabel, "Card from Suggestion: " + cardToShow, JOptionPane.PLAIN_MESSAGE, null);
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

        int xTile = this.assignedCharacter.currentTile.col / 30;
        int yTile = this.assignedCharacter.currentTile.row / 30;

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
     * @return if a player is in the room
     */
    public static Room findRoom(int x, int y) {
        int col = x / 30;
        int row = y / 30;
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

    /**
     * Method for changing the entrance of a player, assumed the player shall not abuse this but it prevents players from getting stuck
     */
    public void changeEntranceOfPlayer() {
        //Queries which tiles are currently free to access as doorway tiles
        checkDoorwayTiles();
        //Gets the players current room
        String roomCard = findRoom(this.getAssignedCharacter().currentTile.getCol(), this.getAssignedCharacter().currentTile.getRow()).toString();
        Room r = Board.getRoomFromString(roomCard);
        //Original X Position and Original Y position
        int originalX = this.getAssignedCharacter().currentTile.getCol();
        int originalY = this.getAssignedCharacter().currentTile.getRow();
        //Check to see if the room is not null
        assert r != null;
        //For each tile in the room
        for (Tile t : r.getDoorwayTiles()) {
            if (Board.getBoardLayoutArray()[t.getRow() / 30][t.getCol() / 30].equals("@")) {
                //Set the old tile to what it previously was
                Board.getBoardLayoutArray()[t.getRow() / 30][t.getCol() / 30] = "S";
                //Resetting based on movement
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[t.getRow() / 30][t.getCol() / 30];
                this.assignedCharacter.currentTile.setRow((t.getRow()));
                this.assignedCharacter.currentTile.setCol((t.getCol()));
                this.assignedCharacter.setCol(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setRow(this.assignedCharacter.currentTile.getRow());
                Board.getBoardLayoutArray()[originalY / 30][originalX / 30] = Board.getOriginalBoardLayoutArray()[originalY / 30][originalX / 30];
                r.removeCharacterFromRoom(this.assignedCharacter);
            }
        }


    }

    /**
     * Important method which implements move
     *
     * @param dir the direction in which the player moves
     */
    public void move(String dir) {
        //Switch based on which direction has been specified
        switch (dir) {
            case "NORTH":
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() - 30) / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setRow((this.assignedCharacter.currentTile.getRow() - 30));
                this.assignedCharacter.setCol(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setRow(this.assignedCharacter.currentTile.getRow());
                if (Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30].equals("@")) {
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
                break;
            case "SOUTH":
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() + 30) / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setRow((this.assignedCharacter.currentTile.getRow() + 30));
                this.assignedCharacter.setCol(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setRow(this.assignedCharacter.currentTile.getRow());
                if (Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30].equals("@")) {
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
                break;
            case "EAST":
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow()) / 30][(30 + this.assignedCharacter.currentTile.getCol()) / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setCol((this.assignedCharacter.currentTile.getCol() + 30));
                this.assignedCharacter.setCol(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setRow(this.assignedCharacter.currentTile.getRow());
                if (Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30].equals("@")) {
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
                break;
            case "WEST":
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow()) / 30][(this.assignedCharacter.currentTile.getCol() - 30) / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setCol((this.assignedCharacter.currentTile.getCol() - 30));
                this.assignedCharacter.setCol(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setRow(this.assignedCharacter.currentTile.getRow());
                if (Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30].equals("@")) {
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
                break;
            default:
                throw new RuntimeException("Error pertaining to move logic");
        }
    }

    /**
     * Check for valid move
     *
     * @param tileInFrontOfPlayer the tile in front of the player
     * @return a boolean which states if the room is true or false
     */

    public static boolean validMove(Tile tileInFrontOfPlayer) {
        if (Board.getCurrentPlayer().getRemainingMoves() <= 0) {
            return false;
        }
        String tileType = Board.getOriginalBoardLayoutArray()[tileInFrontOfPlayer.getRow() / 30][tileInFrontOfPlayer.getCol() / 30];
        Pattern pattern = Pattern.compile("[kbcdlhsiy]");
        Matcher matcher = pattern.matcher(tileType);
        if (matcher.find()) {

            return false;
        }

        //checks if the next tile is a character or not
        for (Character c : Board.getCharacterArrayList()) {
            if (c != Board.getCurrentPlayer().getAssignedCharacter()) {
                if (c.getCol() == tileInFrontOfPlayer.getCol() && c.getRow() == tileInFrontOfPlayer.getRow()) {
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

    /**
     * Adding a card to the players hand
     *
     * @param card
     */
    public void addHand(Card card) {
        this.hand.add(card);
    }

    /**
     * Accessing the player's hand
     *
     * @return
     */
    public ArrayList<Card> getHand() {
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


    /**
     * Check to see if the player has made a suggestion in this turn
     *
     * @return a boolean which represents if the player has a made a suggestion on this turn
     */
    public boolean hasMadeSuggestion() {
        return madeSuggestion;
    }

    /**
     * Setter for the made suggestion boolean
     *
     * @param madeSuggestion
     */
    public void setMadeSuggestion(boolean madeSuggestion) {
        this.madeSuggestion = madeSuggestion;
    }

    /**
     * Method for getting all of the players
     *
     * @return the player list
     */
    public static List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Adding a new player to the player list
     *
     * @param player
     */
    public static void addPlayerList(Player player) {
        playerList.add(player);
    }


    /**
     * Adding a player to the player list
     *
     * @param playerList
     */
    public static void setPlayerList(List<Player> playerList) {
        Player.playerList = playerList;
    }

    /**
     * Getter for the name of the player
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of a player object
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the character assigned to a player
     *
     * @return
     */
    public Character getAssignedCharacter() {
        return assignedCharacter;
    }

    /**
     * Set method for the assigned character
     *
     * @param assignedCharacter
     */
    public void setAssignedCharacter(Character assignedCharacter) {
        this.assignedCharacter = assignedCharacter;
    }

    /**
     * Getter for if the player can make suggestions/accusations
     *
     * @return
     */
    public boolean canMakeActions() {
        return canMakeActions;
    }

    /**
     * Setting the actions of the player
     *
     * @param canMakeActions if they can make an action
     */
    public void setCanMakeActions(boolean canMakeActions) {
        this.canMakeActions = canMakeActions;
    }

    /**
     * Get the current tile of the player
     *
     * @return
     */
    public Tile getCurrentTile() {
        return currentTile;
    }

    /**
     * Setting the player's current tile
     *
     * @param currentTile
     */
    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    /**
     * Getting the remaining moves of the player
     *
     * @return the amount of moves left
     */
    public int getRemainingMoves() {
        return RemainingMoves;
    }

    /**
     * Setting the remaining moves
     *
     * @param remainingMoves
     */
    public void setRemainingMoves(int remainingMoves) {
        RemainingMoves = remainingMoves;
    }


}
