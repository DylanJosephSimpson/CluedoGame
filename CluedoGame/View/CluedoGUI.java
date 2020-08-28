package View;

import Model.*;
import Model.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CluedoGUI extends JFrame {

    //variables be grid board
    public static final int GRID_SIZE = 30;
    // Collection of key game objects
    public static ArrayList<Character> allCharacters = new ArrayList<>();

    //Utility collections used for setup and quick checks
    public static HashMap<String, String> tileTypeToNameMap = new HashMap<>();
    private final JFrame CluedoGame;

    // JButtons for the GameControlPanel
    public JButton EndTurn;
    public JButton OpenNotes;
    public JButton RollDice;
    public JButton MakeAccusation;
    public JButton MakeSuggestion;


    // JPanels and JLabels
    private JPanel InfoPanel;
    private JPanel GameControlPanel;
    private JPanel BoardPanel;
    private JLabel DiceOne;
    private JLabel DiceTwo;
    private JLabel HandCard1;
    private JLabel HandCard2;
    private JLabel HandCard3;
    private JLabel HandCard4;
    private JLabel HandCard5;
    private JLabel HandCard6;
    private JLabel HandCard7;
    private JLabel displayName;
    private JLabel CardPlaceholderCard;

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

    // Strings which are the File Locations for all the Model.Weapon Images.

    private boolean hasRolled = false;
    private ArrayList<int[]> previouslyTraversedTiles = new ArrayList<>();
    private Tile[][] board = new Tile[25][30];
    private Board b;
    private Player currentPlayer = Player.getPlayerList().get(0);
    private int currentPlayerPos;


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
        CluedoGame.setSize(720, 885);
        //
        CluedoGame.setVisible(true);
        //Implementing a setup method which initialises required variables
    }

    private void setup() {
        //Adding all of mappings to a Hashmap
        tileTypeToNameMap.put("k", "Kitchen");
        tileTypeToNameMap.put("b", "Ballroom");
        tileTypeToNameMap.put("c", "Conservatory");
        tileTypeToNameMap.put("d", "Dining Model.Room");
        tileTypeToNameMap.put("l", "Lounge");
        tileTypeToNameMap.put("h", "Hall");
        tileTypeToNameMap.put("s", "Study");
        tileTypeToNameMap.put("i", "Billiard Model.Room");
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
/*        generateMurderer();
        generateMurderRoom();
        generateMurderWeapon();*/
        allocateWeapons();
//        dealCards();
    }

    /**
     * Adds the room tiles to their respective rooms. Also adds the room's doorways to the Model.Room objects
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
                //Add the doorway tiles to the Model.Room
                if (tileKey.equals("@")) {
                    Player.findRoom(col, row).addDoorWay(new Tile("n/a", col * 30, row * 30));
                }
            }
        }

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
        CardPlaceholderCard.setToolTipText("Place Holder Image");
        // Set the HandCardI label to the CandlestickImage Scaled Image.
        CandlestickCard = new JLabel(Board.GetIcon("Candlestick"));
        CandlestickCard.setToolTipText("Candlestick Model.Card");
        Board.getAllCards().add(CandlestickCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        DaggerCard = new JLabel(Board.GetIcon("Dagger"));
        DaggerCard.setToolTipText("Dagger Model.Card");
        Board.getAllCards().add(DaggerCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        LeadPipeCard = new JLabel(Board.GetIcon("LeadPipe"));
        LeadPipeCard.setToolTipText("LeadPipe Model.Card");
        Board.getAllCards().add(LeadPipeCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        RevolverCard = new JLabel(Board.GetIcon("Revolver"));
        RevolverCard.setToolTipText("Revolver Model.Card");
        Board.getAllCards().add(RevolverCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        RopeCard = new JLabel(Board.GetIcon("Rope"));
        RopeCard.setToolTipText("Rope Model.Card");
        Board.getAllCards().add(RopeCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        SpannerCard = new JLabel(Board.GetIcon("Spanner"));
        SpannerCard.setToolTipText("Spanner Model.Card");
        Board.getAllCards().add(SpannerCard);
        // Add the Hand of Cards to the JPanel
        ScarlettCard = new JLabel(Board.GetIcon("Scarlett"));
        ScarlettCard.setToolTipText("Scarlett Model.Card");
        Board.getAllCards().add(ScarlettCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        MustardCard = new JLabel(Board.GetIcon("Mustard"));
        MustardCard.setToolTipText("Mustard Model.Card");
        Board.getAllCards().add(MustardCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        GreenCard = new JLabel(Board.GetIcon("Green"));
        GreenCard.setToolTipText("Green Model.Card");
        Board.getAllCards().add(GreenCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        WhiteCard = new JLabel(Board.GetIcon("White"));
        WhiteCard.setToolTipText("White Model.Card");
        Board.getAllCards().add(WhiteCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        PlumCard = new JLabel(Board.GetIcon("Plum"));
        PlumCard.setToolTipText("Plum Model.Card");
        Board.getAllCards().add(PlumCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        PeacockCard = new JLabel(Board.GetIcon("Peacock"));
        PeacockCard.setToolTipText("Peacock Model.Card");
        Board.getAllCards().add(PeacockCard);
        // Add the Hand of Cards to the JPanel
        LibraryCard = new JLabel(Board.GetIcon("Library"));
        LibraryCard.setToolTipText("Library Model.Card");
        Board.getAllCards().add(LibraryCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BallRoomCard = new JLabel(Board.GetIcon("BallRoom"));
        BallRoomCard.setToolTipText("Ball Model.Room Model.Card");
        Board.getAllCards().add(BallRoomCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        KitchenCard = new JLabel(Board.GetIcon("Kitchen"));
        KitchenCard.setToolTipText("Kitchen Model.Card");
        Board.getAllCards().add(KitchenCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        DiningRoomCard = new JLabel(Board.GetIcon("DiningRoom"));
        DiningRoomCard.setToolTipText("Dining Model.Room Model.Card");
        Board.getAllCards().add(DiningRoomCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        LoungeCard = new JLabel(Board.GetIcon("Lounge"));
        LoungeCard.setToolTipText("Lounge Model.Card");
        Board.getAllCards().add(LoungeCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        HallCard = new JLabel(Board.GetIcon("Hall"));
        HallCard.setToolTipText("Hall Model.Card");
        Board.getAllCards().add(HallCard);
        // Add the Hand of Cards to the JPanel
        StudyCard = new JLabel(Board.GetIcon("Study"));
        StudyCard.setToolTipText("Study Model.Card");
        Board.getAllCards().add(StudyCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BilliardRoomCard = new JLabel(Board.GetIcon("BilliardRoom"));
        BilliardRoomCard.setToolTipText("Billard Model.Room Model.Card");
        Board.getAllCards().add(BilliardRoomCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        ConservatoryCard = new JLabel(Board.GetIcon("Conservatory"));
        ConservatoryCard.setToolTipText("Conservatory Model.Card");
        Board.getAllCards().add(ConservatoryCard);
    }


    private JMenuBar GenerateMenu(String menuName, String optName, String optNameTwo) {

        // Create a new JMenuBar
        JMenuBar mainMenu = new JMenuBar();
        // Create a new JMenu
        JMenu MenuImplementation = new JMenu(menuName);
        // Create the JMenuItems for the JMenu
        JMenuItem ExitOption = new JMenuItem(optName);
        JMenuItem RestartGame = new JMenuItem(optNameTwo);
        // Create the ActionListener for the JMenuItems
        ExitOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        RestartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("World\n");
            }
        });
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
        BoardPanel = new JPanel();
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
                board[row][col] = new Tile(tileTypeToNameMap.get(b.getBoardLayoutArray()[row][col]), col * GRID_SIZE, row * GRID_SIZE);
                board[row][col].draw(graphics, board[row][col].getX(), board[row][col].getY());
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

        displayName = new JLabel("\t\t\t\t\t" + currentPlayer.getName() + "'s Turn");
        displayName.setFont(new Font("Georgia", Font.PLAIN, 30));
        displayName.setForeground(Color.white);
        displayName.setHorizontalAlignment(SwingConstants.CENTER);
        InfoPanelLeft.add(displayName);
        // Todo : Make sure to init the hand before this point
        Board.dealCards();

        HandCard1 = new JLabel();
        HandCard2 = new JLabel();
        HandCard3 = new JLabel();
        HandCard4 = new JLabel();
        HandCard5 = new JLabel();
        HandCard6 = new JLabel();

//        HandCard1.setIcon(currentPlayer.getHand().get(0).getCardIcon());
//        HandCard2.setIcon(currentPlayer.getHand().get(1).getCardIcon());
//        HandCard3.setIcon(currentPlayer.getHand().get(2).getCardIcon());
        HandCard1.setIcon(Board.GetIcon(currentPlayer.getHand().get(0).toString()));
        HandCard2.setIcon(Board.GetIcon(currentPlayer.getHand().get(1).toString()));
        HandCard3.setIcon(Board.GetIcon(currentPlayer.getHand().get(2).toString()));


        if (currentPlayer.getHand().size() > 3) {
            HandCard4.setIcon(Board.GetIcon(currentPlayer.getHand().get(3).toString()));
        } else {
            HandCard4 = CardPlaceholderCard;
        }
        if (currentPlayer.getHand().size() > 4) {
            HandCard5.setIcon(Board.GetIcon(currentPlayer.getHand().get(4).toString()));
        } else {
            HandCard5 = CardPlaceholderCard;
        }
        if (currentPlayer.getHand().size() > 5) {
            HandCard6.setIcon(Board.GetIcon(currentPlayer.getHand().get(5).toString()));
        } else {
            HandCard6 = CardPlaceholderCard;
        }
        if (currentPlayer.getHand().size() > 6) {
            HandCard7.setIcon(Board.GetIcon(currentPlayer.getHand().get(6).toString()));
        } else {
            HandCard7 = CardPlaceholderCard;
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

    private void GenerateRandomDice() {
        hasRolled = true;

        int firstDieRoll = (int) (Math.random() * (6)) + 1;
        int secondDieRoll = (int) (Math.random() * (6)) + 1;
        currentPlayer = Player.getPlayerList().get(currentPlayerPos);
        currentPlayer.setRemainingMoves(firstDieRoll + secondDieRoll);

        DiceOne.setIcon(((new ImageIcon(ImageLoader.getDiceImages().get(firstDieRoll - 1).getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        DiceTwo.setIcon(((new ImageIcon(ImageLoader.getDiceImages().get(secondDieRoll - 1).getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));

    }


    private boolean validMove(Tile tileInFrontOfPlayer) {
        if (currentPlayer.getRemainingMoves() <= 0) {
            return false;
        }

        //checks if the next tile is a character or not
        for (Character c : Board.getCharacterArrayList()) {
            if (c != currentPlayer.getAssignedCharacter()) {
                if (c.getX() == tileInFrontOfPlayer.getX() && c.getY() == tileInFrontOfPlayer.getY()) {
                    return false;
                }
            }
        }

        //checks if the next tile has been visited by checking the list of tiles that the character has visited in their turn
        for (int[] previousTile : previouslyTraversedTiles) {
            if (previousTile[0] == tileInFrontOfPlayer.getX() / 30 && previousTile[1] == tileInFrontOfPlayer.getY() / 30) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "You can not visit a space that you have already been in your turn.", "Keep Moving Forward", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void endTurn() {

        hasRolled = false;
        previouslyTraversedTiles.clear();
        currentPlayerPos++;
        if (currentPlayerPos == Player.playerList.size()) {
            currentPlayerPos = 0;
        }
        currentPlayer = Player.playerList.get(currentPlayerPos);

        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Your turn is now over, it is now " + currentPlayer.getName() + "'s turn.", "End your turn", JOptionPane.PLAIN_MESSAGE);

        displayName.setText("\t\t\t\t\t" + currentPlayer.getName() + "'s Turn");


        System.out.println("CURRENT PLAYERS IS" + currentPlayer.getName() + " HAND IS " + currentPlayer.getHand());

        HandCard1.setIcon(Board.GetIcon(currentPlayer.getHand().get(0).toString()));
        HandCard2.setIcon(Board.GetIcon(currentPlayer.getHand().get(1).toString()));
        HandCard3.setIcon(Board.GetIcon(currentPlayer.getHand().get(2).toString()));
        if (currentPlayer.getHand().size() > 3) {
            HandCard4.setIcon(Board.GetIcon(currentPlayer.getHand().get(3).toString()));
        } else {
            HandCard4.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (currentPlayer.getHand().size() > 4) {
            HandCard5.setIcon(Board.GetIcon(currentPlayer.getHand().get(4).toString()));
        } else {
            HandCard5.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (currentPlayer.getHand().size() > 5) {
            HandCard6.setIcon(Board.GetIcon(currentPlayer.getHand().get(5).toString()));
        } else {
            HandCard6.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (currentPlayer.getHand().size() > 6) {
            HandCard7.setIcon(Board.GetIcon(currentPlayer.getHand().get(6).toString()));
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
        // Add buttonListener to the GameControlPanel's JButtons.
        // TODO : ADD PROPER FUNCTIONALITY
        EndTurn.addActionListener(e -> {
            GameControlPanel.requestFocus();
            endTurn();
            currentPlayer.setRemainingMoves(0);
        });
//        OpenNotes.addActionListener(e -> {
//            GameControlPanel.requestFocus();
//        });
        RollDice.addActionListener(e -> {
            if (!hasRolled) {
                GenerateRandomDice();
            }
            GameControlPanel.requestFocus();
        });
//        MakeSuggestion.addActionListener(e -> {
//            GameControlPanel.requestFocus();
//        });
        MakeAccusation.addActionListener(e -> {
            GameControlPanel.requestFocus();
            if (currentPlayer.canMakeActions()) {
                new AccusationSetup(currentPlayer);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Cannot make an accusation as the player has made a false accusation.",
                        "Model.Player Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            endTurn();
        });
        // Add A KeyListener to the GameControlPanel
        GameControlPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //function keys
                if (e.getKeyChar() == '1') {
                    EndTurn.doClick();
                    GameControlPanel.requestFocus();
                }
//                if (e.getKeyChar() == '2') {
//                    OpenNotes.doClick();
//                    GameControlPanel.requestFocus();
//                }
                if (e.getKeyChar() == '2') {
                    if (!hasRolled) {
                        RollDice.doClick();
                        GameControlPanel.requestFocus();
                    }
                }
                if (e.getKeyChar() == '3') {
                    MakeAccusation.doClick();
                    new AccusationSetup(currentPlayer);
                    GameControlPanel.requestFocus();
                }
//                if (e.getKeyChar() == '5') {
//                    MakeSuggestion.doClick();
//                    GameControlPanel.requestFocus();
//                }
//                currentPlayer = Player.getPlayerList().get(currentPlayerPos);
                //if the player has rolled then they can move
                if (hasRolled) {
                    currentPlayer = Player.getPlayerList().get(currentPlayerPos);
                    //if the current player has no moves left, prompt the player that their turn has ended and return the settings to their defult
                    //convert pixel pos to tile pos
                    int tileX = currentPlayer.getAssignedCharacter().getX() / 30;
                    int tileY = currentPlayer.getAssignedCharacter().getY() / 30;

                    previouslyTraversedTiles.add(new int[]{tileX, tileY});

                    //Pattern pattern = Pattern.compile("(Scarlett|Mustard|Green|White|Plum|Peacock|Wall)",Pattern.CASE_INSENSITIVE); //todo update board each time player is moved and then uncomment this(Caleb)
                    Pattern pattern = Pattern.compile("(Wall)", Pattern.CASE_INSENSITIVE);
                    //ensures the player can move into the position that they want to, if they are not able to then do not decrese their moves left
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (currentPlayer.getAssignedCharacter().getY() > 0 && validMove(board[tileY - 1][tileX])) {
                            Matcher matcher = pattern.matcher(board[tileY - 1][tileX].getTileType());
                            if (!matcher.find()) {
                                currentPlayer.move("NORTH");
                                currentPlayer.setRemainingMoves(currentPlayer.getRemainingMoves() - 1);
                                CluedoGame.repaint();
                                //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (currentPlayer.getAssignedCharacter().getY() < 720 && validMove(board[tileY + 1][tileX])) {
                            Matcher matcher = pattern.matcher(board[tileY + 1][tileX].getTileType());
                            if (!matcher.find()) {
                                //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                currentPlayer.move("SOUTH");
                                currentPlayer.setRemainingMoves(currentPlayer.getRemainingMoves() - 1);
                                CluedoGame.repaint();
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (currentPlayer.getAssignedCharacter().getX() > 0 && validMove(board[tileY][tileX - 1])) {
                            Matcher matcher = pattern.matcher(board[tileY][tileX - 1].getTileType());
                            if (!matcher.find()) {
                                // previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                currentPlayer.move("WEST");
                                currentPlayer.setRemainingMoves(currentPlayer.getRemainingMoves() - 1);
                                CluedoGame.repaint();
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (currentPlayer.getAssignedCharacter().getX() < 690 && validMove(board[tileY][tileX + 1])) {
                            Matcher matcher = pattern.matcher(board[tileY][tileX + 1].getTileType());
                            if (!matcher.find()) {
                                //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                currentPlayer.move("EAST");
                                currentPlayer.setRemainingMoves(currentPlayer.getRemainingMoves() - 1);
                                CluedoGame.repaint();
                            }
                        }
                    }
                    //redraw the frame
                    //CluedoGame.repaint();
                    if (currentPlayer.getRemainingMoves() <= 0) {
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "You now have no more moves", "No more moves", JOptionPane.PLAIN_MESSAGE);
                        endTurn();

                    }

                } else {
                    //prompts the player to roll if they have not already
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "You need to roll the dice before you can move", "You have not rolled", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // Add the JButtons to the GameControlPanel.
        GameControlPanel.add(EndTurn);
        //GameControlPanel.add(OpenNotes);
        GameControlPanel.add(RollDice);
        GameControlPanel.add(MakeAccusation);
        //GameControlPanel.add(MakeSuggestion);
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
                c.draw(g2d, c.getX(), c.getY());
            }
            // Draw all the weapons and characters in a room if it has any
            for (Room r : Board.getAllRooms()) {
                for (int i = 0; i < r.getWeaponsInRoom().size(); i++) {
                    int x = r.getRoomTiles().get(i).getX();
                    int y = r.getRoomTiles().get(i).getY();
                    r.getWeaponsInRoom().get(i).draw(g2d, x, y);
                }
                int count = r.getRoomTiles().size() - 1; //draw from the end of the room tiles
                for (int i = 0; i < r.getCharactersInRoom().size(); i++) {
                    int x = r.getRoomTiles().get(count).getX();
                    int y = r.getRoomTiles().get(count).getY();

                    //move the player into the room
                    r.getCharactersInRoom().get(i).setX(x);
                    r.getCharactersInRoom().get(i).setY(y);

                    CluedoGame.repaint();
                    count--;
                }
            }
        }
    }
}
