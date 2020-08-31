package View;

import Model.*;
import Model.Character;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.regex.Pattern;

public class CluedoGUI extends JFrame {

    //variables be grid board
    public static final int GRID_SIZE = 30;
    // Collection of key game objects
    public static ArrayList<Character> allCharacters = new ArrayList<>();

    //Utility collections used for setup and quick checks
    public static HashMap<String, String> tileTypeToNameMap = new HashMap<>();

    private static JFrame CluedoGame = new JFrame();

    // JButtons for the GameControlPanel
    public static JButton EndTurn;
    public static JButton OpenNotes;
    public static JButton RollDice;
    public static JButton MakeAccusation;
    public static JButton MakeSuggestion;
    public static JButton LeaveRoom;
    // JPanels and JLabels
    private static JPanel InfoPanel;
    private static JPanel GameControlPanel;


    private static JPanel BoardPanel;
    private static JLabel DiceOne;
    private static JLabel DiceTwo;
    private static JLabel HandCard1;
    private static JLabel HandCard2;
    private static JLabel HandCard3;
    private static JLabel HandCard4;
    private static JLabel HandCard5;
    private static JLabel HandCard6;
    private static JLabel HandCard7;
    private static JLabel displayName;
    private static JLabel CardPlaceholderCard;

    // Labels for each Model.Card in a Players Hand
    private JLabel CandlestickCard;
    private JLabel DaggerCard;
    private JLabel LeadPipeCard;
    private JLabel RevolverCard;
    private JLabel RopeCard;
    private JLabel SpannerCard;
    private JLabel ScarlettCard;
    private JLabel MustardCard;
    private JLabel WhiteCard;
    private JLabel GreenCard;
    private JLabel PeacockCard;
    private JLabel PlumCard;
    private JLabel LibraryCard;
    private JLabel StudyCard;
    private JLabel ConservatoryCard;
    private JLabel HallCard;
    private JLabel LoungeCard;
    private JLabel BilliardRoomCard;
    private JLabel KitchenCard;
    private JLabel BallRoomCard;
    private JLabel DiningRoomCard;

    public static JMenuItem ExitOption;
    public static JMenuItem RestartGame;

    public static Tile[][] getBoard() {
        return board;
    }

    private static Tile[][] board = new Tile[25][24];
    private Board b;

    private static boolean hasRolled = false;

    public static JPanel getBoardPanel() {
        return BoardPanel;
    }

    private static int currentPlayerPos;

    public static JMenuItem getExitOption() {
        return ExitOption;
    }

    public static JMenuItem getRestartGame() {
        return RestartGame;
    }

    public static JButton getEndTurn() { return EndTurn; }

    public static JButton getOpenNotes() {
        return OpenNotes;
    }

    public static JButton getRollDice() {
        return RollDice;
    }

    public static JButton getMakeAccusation() {
        return MakeAccusation;
    }

    public static JButton getMakeSuggestion() {
        return MakeSuggestion;
    }

    public static JButton getLeaveRoom() {
        return LeaveRoom;
    }

    public static JPanel getGameControlPanel() {
        return GameControlPanel;
    }

    public static JFrame getCluedoGame() {
        return CluedoGame;
    }

    public static boolean isHasRolled() {
        return hasRolled;
    }

    private static ArrayList<Tile> previouslyTraversedTiles = new ArrayList<>();

    public static int getCurrentPlayerPos() {
        return currentPlayerPos;
    }

    public static ArrayList<Tile> getPreviouslyTraversedTiles() {
        return previouslyTraversedTiles;
    }



    public CluedoGUI(String title, Board board) {
        CluedoGame = new JFrame(title);
        this.b = board;
        setup();
        // Set GUI to terminate the program when exited.
        CluedoGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the JMenuBar to the return value of the GenerateMenu method.
        CluedoGame.setJMenuBar(GenerateMenu("Game Menu", "Exit Game", "Restart Game"));
        // Set the layout of the GUI to GridLayout, this way BoardPanel and InfoPanel can be easily separated.
        CluedoGame.setLayout(new BorderLayout(2, 1));
        // Add the GameControlPanel to the JFrame
        CluedoGame.add(GenerateGameControlPanel(), BorderLayout.NORTH);
        // Add the BoardPanel to the JFrame.
        //this.add(GenerateBoardPanel());
        CluedoGame.add(GenerateBoardPanel(), BorderLayout.CENTER);
        // Add the InfoPanel to the JFrame.
        CluedoGame.add(GenerateInfoPanel(), BorderLayout.SOUTH);
        // Pack the JFrame so that all its contents are at or above their preferred sizes
        CluedoGame.setSize(740, 920);
        CluedoGame.setVisible(true);
        //Implementing a setup method which initialises required variables
    }

    public static void setHasRolled(boolean hasRolled) {
        CluedoGUI.hasRolled = hasRolled;
    }

    private void setup() {
        //Adding all of mappings to a Hashmap
        tileTypeToNameMap.put("k", "Kitchen");
        tileTypeToNameMap.put("b", "Ballroom");
        tileTypeToNameMap.put("c", "Conservatory");
        tileTypeToNameMap.put("d", "Dining Room");
        tileTypeToNameMap.put("l", "Lounge");
        tileTypeToNameMap.put("h", "Hall");
        tileTypeToNameMap.put("s", "Study");
        tileTypeToNameMap.put("i", "Billiard Room");
        tileTypeToNameMap.put("y", "Library");
        tileTypeToNameMap.put("-", "Wall");
        tileTypeToNameMap.put("@", "Door");
        tileTypeToNameMap.put(" ", "SPACE");
        tileTypeToNameMap.put("G", "Green");
        tileTypeToNameMap.put("W", "White");
        tileTypeToNameMap.put("P", "Plum");
        tileTypeToNameMap.put("C", "Peacock");
        tileTypeToNameMap.put("S", "Scarlett");
        tileTypeToNameMap.put("M", "Mustard");
        tileTypeToNameMap.put("e", "Cellar");
        setupRooms();
        generateCards();
        allocateWeapons();
    }

    /**
     * Adds the room tiles to their respective rooms. Also adds the room's doorways to the Room objects
     */
    /**
     * Adds the room tiles to their respective rooms. Also adds the room's doorways to the Room objects
     */
    private void setupRooms() {
        for (int row = 0; row < b.getBoardLayoutArray().length; row++) {
            for (int col = 0; col < b.getBoardLayoutArray()[row].length; col++) {
                String tileKey = b.getBoardLayoutArray()[row][col];
                for (Room r : Board.getAllRooms()) {
                    // Add the room tiles to the room's collection of tiles
                    if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(tileKey))) {
                        r.addRoomTile(new Tile("n/a", col * 30, row * 30));
                    }

                }
            }
        }

    }

    public static void addRoomTiles() {
        for (int row = 3; row < Board.getBoardLayoutArray().length; row++) {
            for (int col = 3; col < Board.getBoardLayoutArray()[row].length; col++) {
                String tileKey = Board.getBoardLayoutArray()[row][col];
                if (tileKey.equals("@")) {
                    utilityMethod(col, row).addDoorWay(new Tile("@", col * 30, row * 30));
                }
            }
        }
    }

    public static Room utilityMethod(int col, int row) {
        //Check the above tile
        String topMiddleTile = Board.getOriginalBoardLayoutArray()[row - 1][col];
        String midLeftTile = Board.getOriginalBoardLayoutArray()[row][col - 1];
        String midRightTile = Board.getOriginalBoardLayoutArray()[row][col + 1];
        String botMiddleTile = Board.getOriginalBoardLayoutArray()[row + 1][col];
        Pattern pattern = Pattern.compile("[kbcdlhsiy]"); //room symbols

        if (topMiddleTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(topMiddleTile))) {
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
        } else if (botMiddleTile.matches(String.valueOf(pattern))) {
            for (Room r : Board.getAllRooms()) {
                if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(botMiddleTile))) {
                    return r;
                }

            }
        }
        throw new RuntimeException("TEST");
    }

    /**
     * Chooses 6 rooms to allocate weapons at startup
     */
    private void allocateWeapons() {
        Collections.shuffle(Board.getAllRooms());
        int count = 0;
        while (count < Board.getAllWeapons().size()) {
            for (Room r : Board.getAllRooms()) {
                if (!r.getRoomName().equals("Cellar")) { //no weapons can go into the cellar
                    r.addWeaponToRoom(Board.getAllWeapons().get(count));
                    Board.getAllWeapons().get(count).setCurrentRoom(r);
                    count++;
                    if (count == 6) break;
                }
            }
        }
    }

    private void generateCards() {
        // Generate character cards
        CardPlaceholderCard = new JLabel(Board.GetIcon("Placeholder"));
        // Set the HandCardI label to the CandlestickImage Scaled Image.
        CandlestickCard = new JLabel(Board.GetIcon("Candlestick"));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        DaggerCard = new JLabel(Board.GetIcon("Dagger"));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        LeadPipeCard = new JLabel(Board.GetIcon("LeadPipe"));
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        RevolverCard = new JLabel(Board.GetIcon("Revolver"));
        // Set the HandCardV label to the RopeImage Scaled Image.
        RopeCard = new JLabel(Board.GetIcon("Rope"));
        // Set the HandCardV label to the SpannerImage Scaled Image.
        SpannerCard = new JLabel(Board.GetIcon("Spanner"));
        // Add the Hand of Cards to the JPanel
        ScarlettCard = new JLabel(Board.GetIcon("Scarlett"));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        MustardCard = new JLabel(Board.GetIcon("Mustard"));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        GreenCard = new JLabel(Board.GetIcon("Green"));
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        WhiteCard = new JLabel(Board.GetIcon("White"));
        // Set the HandCardV label to the RopeImage Scaled Image.
        PlumCard = new JLabel(Board.GetIcon("Plum"));
        // Set the HandCardV label to the SpannerImage Scaled Image.
        PeacockCard = new JLabel(Board.GetIcon("Peacock"));
        // Add the Hand of Cards to the JPanel
        LibraryCard = new JLabel(Board.GetIcon("Library"));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BallRoomCard = new JLabel(Board.GetIcon("BallRoom"));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        KitchenCard = new JLabel(Board.GetIcon("Kitchen"));
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        DiningRoomCard = new JLabel(Board.GetIcon("DiningRoom"));
        // Set the HandCardV label to the RopeImage Scaled Image.
        LoungeCard = new JLabel(Board.GetIcon("Lounge"));
        // Set the HandCardV label to the SpannerImage Scaled Image.
        HallCard = new JLabel(Board.GetIcon("Hall"));
        // Add the Hand of Cards to the JPanel.
        StudyCard = new JLabel(Board.GetIcon("Study"));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BilliardRoomCard = new JLabel(Board.GetIcon("BilliardRoom"));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        ConservatoryCard = new JLabel(Board.GetIcon("Conservatory"));
    }

    private JMenuBar GenerateMenu(String menuName, String optName, String optNameTwo) {
        // Create a new JMenuBar
        JMenuBar mainMenu = new JMenuBar();
        // Create a new JMenu
        JMenu MenuImplementation = new JMenu(menuName);
        // Create the JMenuItems for the JMenu
        ExitOption = new JMenuItem(optName);
        RestartGame = new JMenuItem(optNameTwo);
        // Add the JMenuItems to the JMenu
        MenuImplementation.add(ExitOption);
        MenuImplementation.add(RestartGame);
        // Add the JMenu to the MenuBar
        mainMenu.add(MenuImplementation);
        return mainMenu;
    }

    /**
     * Draws the board of the game
     *
     * @return
     */
    private JPanel GenerateBoardPanel() {
        //calls drawPane and this draws the main section of the board
        BoardPanel = new DrawPane();
        return BoardPanel;
    }

    /**
     * Method which iterates through the board array and
     */
    public void drawBoard(Graphics graphics) {
        for (int row = 0; row < 25; ++row) {
            for (int col = 0; col < 24; ++col) {
                board[row][col] = new Tile(tileTypeToNameMap.get(Board.getBoardLayoutArray()[row][col]), col * GRID_SIZE, row * GRID_SIZE);
                board[row][col].draw(graphics, board[row][col].getCol(), board[row][col].getRow());
            }
        }
    }

    private JPanel GenerateInfoPanel() {
        // Set the GameControlPanel to be a new JPanel.
        InfoPanel = new JPanel();
        // Set the background of the InfoPanel to blue.
        InfoPanel.setBackground(Color.blue);
        // TODO COMMENT
        InfoPanel.setLayout(new BorderLayout(10, 10));
        // Call the LoadImages method to ensure that all the Dice and Model.Weapon Images have been loaded.
        // Set the InfoPanelLeft to be a new JPanel.
        JPanel InfoPanelLeft = new JPanel();
        // Set the background of the InfoPanelLeft to white.
        InfoPanelLeft.setBackground(Color.blue);
        // TODO COMMENT
        InfoPanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        // Set the InfoPanelRight to be a new JPanel.
        JPanel InfoPanelRight = new JPanel();
        // Set the background of the InfoPanelRight to white.
        InfoPanelRight.setBackground(Color.blue);
        // TODO COMMENT
        InfoPanelRight.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        // Set the Dice One label to the FaceOne Scaled Image.
        DiceOne = new JLabel(new ImageIcon(ImageLoader.getDiceImages().get(0).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        // Set the Dice One label to the FaceOne Scaled Image.
        DiceTwo = new JLabel(new ImageIcon(ImageLoader.getDiceImages().get(0).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        // Add the Dice Labels to the JPanel
        InfoPanelLeft.add(DiceOne);
        InfoPanelLeft.add(DiceTwo);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        Board.setCurrentPlayer(Player.getPlayerList().get(0));
        displayName = new JLabel("\t\t\t\t\t" + Board.getCurrentPlayer().getName() + "'s Turn");
        displayName.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        displayName.setForeground(Color.white);
        displayName.setHorizontalAlignment(SwingConstants.CENTER);
        InfoPanelLeft.add(displayName);
        Board.dealCards();
        HandCard1 = new JLabel();
        HandCard2 = new JLabel();
        HandCard3 = new JLabel();
        HandCard4 = new JLabel();
        HandCard5 = new JLabel();
        HandCard6 = new JLabel();
        HandCard1.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(0).toString()));
        HandCard1.setToolTipText(Board.getCurrentPlayer().getHand().get(0).toString());
        HandCard2.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(1).toString()));
        HandCard2.setToolTipText(Board.getCurrentPlayer().getHand().get(1).toString());
        HandCard3.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(2).toString()));
        HandCard3.setToolTipText(Board.getCurrentPlayer().getHand().get(2).toString());
        if (Board.getCurrentPlayer().getHand().size() > 3) {
            HandCard4.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(3).toString()));
            HandCard4.setToolTipText(Board.getCurrentPlayer().getHand().get(3).toString());
        } else {
            HandCard4 = CardPlaceholderCard;
            HandCard4.setToolTipText("Placeholder");
        }
        if (Board.getCurrentPlayer().getHand().size() > 4) {
            HandCard5.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(4).toString()));
            HandCard5.setToolTipText(Board.getCurrentPlayer().getHand().get(4).toString());
        } else {
            HandCard5 = CardPlaceholderCard;
            HandCard5.setToolTipText("Placeholder");
        }
        if (Board.getCurrentPlayer().getHand().size() > 5) {
            HandCard6.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(5).toString()));
            HandCard6.setToolTipText(Board.getCurrentPlayer().getHand().get(5).toString());
        } else {
            HandCard6 = CardPlaceholderCard;
            HandCard6.setToolTipText("Placeholder");
        }
        if (Board.getCurrentPlayer().getHand().size() > 6) {
            HandCard7.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(6).toString()));
            HandCard7.setToolTipText(Board.getCurrentPlayer().getHand().get(6).toString());
        } else {
            HandCard7 = CardPlaceholderCard;
            HandCard7.setToolTipText("Placeholder");
        }
        InfoPanelRight.add(HandCard1);
        InfoPanelRight.add(HandCard2);
        InfoPanelRight.add(HandCard3);
        InfoPanelRight.add(HandCard4);
        InfoPanelRight.add(HandCard5);
        InfoPanelRight.add(HandCard6);
        InfoPanelRight.add(HandCard7);
        // Add the InfoPanelRight and InfoPanelLeft to the
        InfoPanel.add(InfoPanelLeft, BorderLayout.LINE_START);
        InfoPanel.add(InfoPanelRight, BorderLayout.LINE_END);
        // Return the InfoPanel which should now be fully configured.
        return InfoPanel;
    }

    public static void GenerateRandomDice() {
        hasRolled = true;
        int firstDieRoll = (int) (Math.random() * (6)) + 1;
        int secondDieRoll = (int) (Math.random() * (6)) + 1;
        Board.setCurrentPlayer(Player.getPlayerList().get(currentPlayerPos));
        Board.getCurrentPlayer().setRemainingMoves(firstDieRoll + secondDieRoll);
        DiceOne.setIcon(((new ImageIcon(ImageLoader.getDiceImages().get(firstDieRoll - 1).getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        DiceTwo.setIcon(((new ImageIcon(ImageLoader.getDiceImages().get(secondDieRoll - 1).getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
    }

    public static void endTurn() {
        hasRolled = false;
        previouslyTraversedTiles.clear();
        Board.getCurrentPlayer().setMadeSuggestion(false);
        currentPlayerPos++;
        if (currentPlayerPos == Player.playerList.size()) {
            currentPlayerPos = 0;
        }
        Board.setCurrentPlayer(Player.getPlayerList().get(currentPlayerPos));
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Your turn is now over, it is now " + Board.getCurrentPlayer().getName() + "'s turn.", "End your turn", JOptionPane.PLAIN_MESSAGE);
        displayName.setText("\t\t\t\t\t" + Board.getCurrentPlayer().getName() + "'s Turn");
        HandCard1.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(0).toString()));
        HandCard2.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(1).toString()));
        HandCard3.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(2).toString()));
        if (Board.getCurrentPlayer().getHand().size() > 3) {
            HandCard4.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(3).toString()));
        } else {
            HandCard4.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (Board.getCurrentPlayer().getHand().size() > 4) {
            HandCard5.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(4).toString()));
        } else {
            HandCard5.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (Board.getCurrentPlayer().getHand().size() > 5) {
            HandCard6.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(5).toString()));
        } else {
            HandCard6.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (Board.getCurrentPlayer().getHand().size() > 6) {
            HandCard7.setIcon(Board.GetIcon(Board.getCurrentPlayer().getHand().get(6).toString()));
        } else {
            HandCard7.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        InfoPanel.repaint();
    }

    private JPanel GenerateGameControlPanel() {
        // Set the GameControlPanel to be a new JPanel.
        GameControlPanel = new JPanel();
        // Set the GameControlPanel to be focusable, allowing for our KeyListener to work.
        GameControlPanel.setFocusable(true);
        // Set the GameControlPanel to be able to request the program focus on this JPanel.
        GameControlPanel.requestFocusInWindow();
        // Set the background of the GameControlPanel to white.
        GameControlPanel.setBackground(Color.WHITE);
        // Set the Layout of the Background to a GridLayout, with rows, and column. Format : ButtonA, ButtonB, ButtonC, ButtonD
        GameControlPanel.setLayout(new GridLayout(1, 5));
        // Create the JButtons for the GameControlPanel
        EndTurn = new JButton("End Turn");
        //OpenNotes = new JButton("Open Notes");
        RollDice = new JButton("Roll Dice");
        //MakeSuggestion = new JButton("Suggest");
        MakeAccusation = new JButton("Make Accusation");
        MakeSuggestion = new JButton("Make Suggestion");
        LeaveRoom = new JButton("Change Entrance");
        // Add buttonListener to the GameControlPanel's JButtons.
        // TODO : ADD PROPER FUNCTIONALITY
        // Add the JButtons to the GameControlPanel.
        GameControlPanel.add(EndTurn);
        //GameControlPanel.add(OpenNotes);
        GameControlPanel.add(RollDice);
        GameControlPanel.add(MakeAccusation);
        GameControlPanel.add(MakeSuggestion);
        GameControlPanel.add(LeaveRoom);
        // Return the GameControlPanel which should now be fully configured.
        return GameControlPanel;
    }

    /**
     * draws the board grid and the starting positions of the characters
     */
    class DrawPane extends JPanel {
        public void paintComponent(Graphics graphics) {
            Graphics2D g2d = (Graphics2D) graphics;
            //draws the 24x25 grid, hline = horizontal line, vline = vertial line
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, 720, 885);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));
            drawBoard(graphics);
            for (Character c : Board.getCharacterArrayList()) {
                c.draw(g2d, c.currentTile.getCol(), c.currentTile.getRow());
            }
            // Draw all the weapons and characters in a room if it has any
            for (Room r : Board.getAllRooms()) {
                for (int i = 0; i < r.getWeaponsInRoom().size(); i++) {
                    int x = r.getRoomTiles().get(i).getCol();
                    int y = r.getRoomTiles().get(i).getRow();
                    r.getWeaponsInRoom().get(i).draw(g2d, x, y);
                }
                int count = r.getRoomTiles().size() - 1; //draw from the end of the room tiles
                for (int i = 0; i < r.getCharactersInRoom().size(); i++) {
                    int x = r.getRoomTiles().get(count).getCol();
                    int y = r.getRoomTiles().get(count).getRow();
                    //move the player into the room
                    r.getCharactersInRoom().get(i).currentTile.setX(x);
                    r.getCharactersInRoom().get(i).currentTile.setY(y);
                    CluedoGame.repaint();
                    count--;
                }
            }
        }
    }


}
