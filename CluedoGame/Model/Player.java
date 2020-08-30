package Model;

import View.CluedoGUI;

import javax.swing.*;
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

    public static boolean enteredARoomOnThisTurn = false;

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

    public void makeSuggestion(Card characterCard, Card weaponCard) {
        if (!canMakeActions || !isInARoom()) {
            return; //If the player is not active due to making a false accusation or not in a room there is no
            // point in making an accusation, they can still move.
        }
        System.out.println("Suggestion is being made");

        Card roomCard = Board.getCardHashMap().get(findPlayerRoom(this.getAssignedCharacter().currentTile.getX(), this.getAssignedCharacter().currentTile.getRow()).toString());

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
                    Card cardToShow;
                    //if the next player has only one card to show, show this to current player
                    if (hasSuggestedCards.size() == 1) {
                        cardToShow = hasSuggestedCards.get(0);
                    }
                    //if the next player has more than one card to show, pass on to that player, show that player their hand and from this the player can select one of the cards
                    else {
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, this.getName() + " look away and pass on to " + otherPs.getName(), "Look away", JOptionPane.PLAIN_MESSAGE);


                        JPanel CardToShowWindow = new JPanel();
                        String[] cards = new String[3];
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

                        int result = JOptionPane.showConfirmDialog(null, CardToShowWindow,
                                "Choose between the following to show to the suggester", JOptionPane.OK_CANCEL_OPTION);



//                        boolean validCard = false;
//                        String stringCard = "";
//                        while (!validCard) {
//                            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                            System.out.println("Select card for Suggester:");
//                            stringCard = reader.readLine();
//                            if (stringCard.matches("[1-3]")) {
//                                validCard = true;
//                            } else {
//                                System.out.println("Invalid number");
//                            }
//                        }
//                        int cardNo = Integer.parseInt(stringCard);
//                        cardToShow = hasSuggestedCards.get(cardNo - 1);
//                        System.out.println(otherPs.getPlayerName()+" look away and pass on to "+this.getPlayerName()+" (5 sec delay).");
//
//                        for (int i = 0; i < 15; ++i) System.out.println();
//                    }
//                    System.out.println(otherPs.getPlayerName()+" has the following card that matches your suggestion. Take note of this (5 sec delay)");
//                    System.out.println(cardToShow.toString());
//                    for (int i = 0; i < 15; ++i) System.out.println();
//                    break;
                    }
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

        int xTile = this.assignedCharacter.currentTile.x/30;
        int yTile = this.assignedCharacter.currentTile.y/30;

        String tileType = Board.getOriginalBoardLayoutArray()[yTile][xTile];
        Pattern pattern = Pattern.compile("[kbcdlhsiy@]");
        Matcher matcher = pattern.matcher(tileType);

        return matcher.find();
    }

    public static Room findPlayerRoom(int x, int y){
        return Board.getRoomFromString(Board.getOriginalBoardLayoutArray()[y/30][x/30]);
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



    public void move(String dir){

        System.out.println(dir);
//        if(isInARoom()){
//            System.out.println("WE ARE IN A ROOM");
//            return;
//        }
        //If the player is moving onto a player/wall/outof bounds
        //Move the playerY coordinate up one
        switch (dir) {
            case "NORTH":
                System.out.println("NORTH"+this.assignedCharacter.characterName + "X POS " +this.assignedCharacter.getX() + "Y POS" + this.assignedCharacter.getY());
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() - 30) / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setY((this.assignedCharacter.currentTile.getRow() - 30));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                if(Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow()/30][this.assignedCharacter.currentTile.getCol()/30].equals("@")){
                enteredARoomOnThisTurn = true;
                Board.getCurrentPlayer().setRemainingMoves(0);
                 }
//                endMovement();
                System.out.println("NORTH"+this.assignedCharacter.characterName + "X POS " +this.assignedCharacter.getX() + "Y POS" + this.assignedCharacter.getY());
                break;
            case "SOUTH":
                System.out.println("SOUTH"+this.assignedCharacter.characterName + "X POS " +this.assignedCharacter.getX() + "Y POS" + this.assignedCharacter.getY());
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() + 30) / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setY((this.assignedCharacter.currentTile.getRow() + 30));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                if(Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow()/30][this.assignedCharacter.currentTile.getCol()/30].equals("@")){
                    enteredARoomOnThisTurn = true;
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }                break;
            case "EAST":
                System.out.println("EAST"+this.assignedCharacter.characterName + "X POS " +this.assignedCharacter.getX() + "Y POS" + this.assignedCharacter.getY());
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow()) / 30][(30 + this.assignedCharacter.currentTile.getCol()) / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setX((this.assignedCharacter.currentTile.getCol() + 30));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                if(Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow()/30][this.assignedCharacter.currentTile.getCol()/30].equals("@")){
                    enteredARoomOnThisTurn = true;
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }
                break;
            case "WEST":
                System.out.println("WEST"+this.assignedCharacter.characterName + "X POS " +this.assignedCharacter.getX() + "Y POS" + this.assignedCharacter.getY());
                Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow()) / 30][(this.assignedCharacter.currentTile.getCol() - 30) / 30] = Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                Board.getBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30] = Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow() / 30][this.assignedCharacter.currentTile.getCol() / 30];
                this.assignedCharacter.currentTile.setX((this.assignedCharacter.currentTile.getCol() - 30));
                this.assignedCharacter.setX(this.assignedCharacter.currentTile.getCol());
                this.assignedCharacter.setY(this.assignedCharacter.currentTile.getRow());
                if(Board.getOriginalBoardLayoutArray()[this.assignedCharacter.currentTile.getRow()/30][this.assignedCharacter.currentTile.getCol()/30].equals("@")){
                    enteredARoomOnThisTurn = true;
                    Board.getCurrentPlayer().setRemainingMoves(0);
                }                break;
            default: throw new RuntimeException("EPIC FAIL");
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

        //checks if the next tile is a character or not
        for (Character c : Board.getCharacterArrayList()) {
            if (c != Board.getCurrentPlayer().getAssignedCharacter()) {
                if (c.getX() == tileInFrontOfPlayer.getCol() && c.getY() == tileInFrontOfPlayer.getRow()) {
                    return false;
                }
            }
        }

        //checks if the next tile has been visited by checking the list of tiles that the character has visited in their turn
        for (Tile t: CluedoGUI.getPreviouslyTraversedTiles() ) {
            if(t.getCol()==tileInFrontOfPlayer.getCol() && t.getRow()==tileInFrontOfPlayer.getRow()){
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

    public void leaveRoom() {
        System.out.println(Board.getCurrentPlayer().RemainingMoves + "REMAINING MOVES");
        if(Board.getCurrentPlayer().RemainingMoves>=0) {
            System.out.println("WE ARE HERE");
            if (Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() / 30) - 1][this.assignedCharacter.getX() / 30].equals(" ")) {
                move("NORTH");
                System.out.println("WE ARE NORTH");

            } else if (Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() / 30) + 1][this.assignedCharacter.getX() / 30].equals(" ")) {
                move("SOUTH");
            } else if (Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() / 30)][this.assignedCharacter.getX() / 30 + 1].equals(" ")) {
                move("EAST");
            } else if (Board.getBoardLayoutArray()[(this.assignedCharacter.currentTile.getRow() / 30)][this.assignedCharacter.getX() / 30 - 1].equals(" ")) {
                move("WEST");
            }
        }
    }


    public enum Movement {
        NORTH("North", -1, 0, -2, 0),
        EAST("East", 0, +1, 0, +2),
        SOUTH("South", +1, 0, +2, 0),
        WEST("West", 0, -1, 0, -2),
        ;

        String enumName;
        int VerticalMovementValue;
        int HorizontalMovementValue;
        int DoorVerticalMovementValue;
        int DoorHorizontalMovementValue;

        /**
         * Constructor for a Movement enum
         * @param matcher this is what matches the direction that we are moving into
         * @param vertValue this is the direction that the character moves vertically when moving in the specified direction
         * @param horzValue this is the direction that the character moves horizontally when moving in the specified direction
         * @param doorvertValue this is the direction that the character moves horizontally when moving through a door in the specified direction
         * @param doorhorzValue this is the direction that the character moves vertically when moving through a door in the specified direction
         */
        Movement(String matcher, int vertValue, int horzValue, int doorvertValue, int doorhorzValue) {
            this.enumName = matcher;
            this.VerticalMovementValue = vertValue;
            this.HorizontalMovementValue = horzValue;
            this.DoorVerticalMovementValue = doorvertValue;
            this.DoorHorizontalMovementValue = doorhorzValue;
        }

        /**
         * getEnumName() method :
         *
         * @return getter method to return the enumName as a String
         */
        public String getEnumName() {
            return enumName;
        }

        /**
         * getVerticalMovementValue() method :
         *
         * @return getter method to return the VerticalMovementValue as an int
         */
        public int getVerticalMovementValue() {
            return VerticalMovementValue;
        }

        /**
         * getHorizontalMovementValue() method :
         *
         * @return getter method to return the HorizontalMovementValue as an int
         */
        public int getHorizontalMovementValue() {
            return HorizontalMovementValue;
        }

        /**
         * getDoorVerticalMovementValue() method :
         *
         * @return getter method to return the DoorVerticalMovementValue as an int
         */
        public int getDoorVerticalMovementValue() {
            return DoorVerticalMovementValue;
        }

        /**
         * getDoorHorizontalMovementValue() method :
         *
         * @return getter method to return the DoorHorizontalMovementValue as an int
         */
        public int getDoorHorizontalMovementValue() {
            return DoorHorizontalMovementValue;
        }

    }

    /**
     * Checks if tile is in bounds of the board
     *
     * @param tile being checked
     * @return if it is in bounds
     */
    private boolean tileInBounds(Tile tile) {
        return tile.getRow() > 0 && tile.getRow() <25 && tile.getCol() > 0 && tile.getCol() < 24;
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
