package View;

import Model.*;
import Model.Character;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CluedoGUI extends JFrame {

    //variables be grid board
    public static final int GRID_SIZE = 30;
    // Collection of key game objects
    public static ArrayList<Character> allCharacters = new ArrayList<>();

    // Initialization of characters
    public static ArrayList<Weapon> allWeapons = new ArrayList<>();
    public static ArrayList<Room> allRooms = new ArrayList<>();
    public static ArrayList<RoomCard> roomCards = new ArrayList<>();
    public static ArrayList<WeaponCard> weaponCards = new ArrayList<>();
    public static ArrayList<CharacterCard> characterCards = new ArrayList<>();
    public static HashSet<String> roomNames = new HashSet<>();

    //Utility collections used for setup and quick checks
    public static HashMap<String, String> tileTypeToNameMap = new HashMap<>();
    private final JFrame CluedoGame;

    // JButtons for the GameControlPanel
    public JButton EndTurn;
    public JButton OpenNotes;
    public JButton RollDice;
    public JButton MakeAccusation;
    public JButton MakeSuggestion;
    private ArrayList<JLabel> allCards = new ArrayList<>();

    // Initialization of weapons
    private Weapon Candlestick = new Weapon("Candlestick");
    private Weapon Dagger = new Weapon("Dagger");
    private Weapon LeadPipe = new Weapon("LeadPipe");
    private Weapon Revolver = new Weapon("Revolver");
    private Weapon Rope = new Weapon("Rope");
    private Weapon Spanner = new Weapon("Spanner");

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

    private Card murderer;
    private Card murderRoom;
    private Card murderWeapon;


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

        //Model.Room names being added to arraylist
        roomNames.add("Kitchen");
        roomNames.add("Ballroom");
        roomNames.add("Conservatory");
        roomNames.add("Dining Model.Room");
        roomNames.add("Lounge");
        roomNames.add("Hall");
        roomNames.add("Study");
        roomNames.add("Billiard Model.Room");
        roomNames.add("Library");
        roomNames.add("Cellar");


        allWeapons.add(Candlestick);
        allWeapons.add(Dagger);
        allWeapons.add(LeadPipe);
        allWeapons.add(Revolver);
        allWeapons.add(Rope);
        allWeapons.add(Spanner);

        allRooms.add(new Room("Kitchen"));
        allRooms.add(new Room("Ballroom"));
        allRooms.add(new Room("Conservatory"));
        allRooms.add(new Room("Dining Model.Room"));
        allRooms.add(new Room("Lounge"));
        allRooms.add(new Room("Hall"));
        allRooms.add(new Room("Study"));
        allRooms.add(new Room("Billiard Model.Room"));
        allRooms.add(new Room("Library"));
        allRooms.add(new Room("Cellar"));
        ImageLoader.LoadImages();
        
        setupRooms();
        generateCards();
        generateMurderer();
        generateMurderRoom();
        generateMurderWeapon();
        allocateWeapons();
        dealCards();
    }
    
     /**
     * Adds the room tiles to their respective rooms. Also adds the room's doorways to the Model.Room objects
     */
    private void setupRooms(){
        for (int row = 0; row < b.getBoardLayoutArray().length; row++) {
            for (int col = 0; col < b.getBoardLayoutArray()[row].length; col++) {
                String tileKey = b.getBoardLayoutArray()[row][col];
                for (Room r : allRooms) {
                    // Add the room tiles to the room's collection of tiles
                    if (r.getRoomName().equals(CluedoGUI.tileTypeToNameMap.get(tileKey))) {
                        r.addRoomTile(new Tile("n/a", col * 30, row * 30));
                    }
                }
                //Add the doorway tiles to the Model.Room
                if (tileKey.equals("@")){
                    Player.findRoom(col, row).addDoorWay(new Tile("n/a",col * 30, row * 30));
                }
            }
        }

    }

    /**
     * Chooses 6 rooms to allocate weapons at startup
     */
    private void allocateWeapons(){
        Collections.shuffle(allRooms);
        int count = 0;
        while (count < allWeapons.size()) {
            for (Room r : allRooms) {
                if (!r.getRoomName().equals("Cellar")) { //no weapons can go into the cellar
                    r.addWeaponToRoom(allWeapons.get(count));
                    allWeapons.get(count).setCurrentRoom(r);
                    count++;
                    if (count == 6) break;
                }
            }
        }
    }

    private void generateCards() {
        // Generate character cards

        CardPlaceholderCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        CardPlaceholderCard.setToolTipText("Model.Card Place Holder");

        // Set the HandCardI label to the CandlestickImage Scaled Image.
        CandlestickCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Candlestick").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        CandlestickCard.setToolTipText("Candlestick Model.Card");
        allCards.add(CandlestickCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        DaggerCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Dagger").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        DaggerCard.setToolTipText("Dagger Model.Card");
        allCards.add(DaggerCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        LeadPipeCard = new JLabel(new ImageIcon(ImageLoader.GetImage("LeadPipe").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        LeadPipeCard.setToolTipText("LeadPipe Model.Card");
        allCards.add(LeadPipeCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        RevolverCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Revolver").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        RevolverCard.setToolTipText("Revolver Model.Card");
        allCards.add(RevolverCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        RopeCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Rope").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        RopeCard.setToolTipText("Rope Model.Card");
        allCards.add(RopeCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        SpannerCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Spanner").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        SpannerCard.setToolTipText("Spanner Model.Card");
        allCards.add(SpannerCard);
        // Add the Hand of Cards to the JPanel


        ScarlettCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Scarlett").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        ScarlettCard.setToolTipText("Scarlett Model.Card");
        allCards.add(ScarlettCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        MustardCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Mustard").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        MustardCard.setToolTipText("Mustard Model.Card");
        allCards.add(MustardCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        GreenCard = new JLabel(new ImageIcon(ImageLoader.GetImage("White").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        GreenCard.setToolTipText("Green Model.Card");
        allCards.add(GreenCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        WhiteCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Green").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        WhiteCard.setToolTipText("White Model.Card");
        allCards.add(WhiteCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        PlumCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Peacock").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        PlumCard.setToolTipText("Plum Model.Card");
        allCards.add(PlumCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        PeacockCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Plum").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        PeacockCard.setToolTipText("Peacock Model.Card");
        allCards.add(PeacockCard);
        // Add the Hand of Cards to the JPanel


        LibraryCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Library").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        LibraryCard.setToolTipText("Library Model.Card");
        allCards.add(LibraryCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BallRoomCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Study").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        BallRoomCard.setToolTipText("Ball Model.Room Model.Card");
        allCards.add(BallRoomCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        KitchenCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Conservatory").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        KitchenCard.setToolTipText("Kitchen Model.Card");
        allCards.add(KitchenCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        DiningRoomCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Hall").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        DiningRoomCard.setToolTipText("Dining Model.Room Model.Card");
        allCards.add(DiningRoomCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        LoungeCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Lounge").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        LoungeCard.setToolTipText("Lounge Model.Card");
        allCards.add(LoungeCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        HallCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Billiard").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        HallCard.setToolTipText("Hall Model.Card");
        allCards.add(HallCard);
        // Add the Hand of Cards to the JPanel
        StudyCard = new JLabel(new ImageIcon(ImageLoader.GetImage("Kitchen").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        StudyCard.setToolTipText("Study Model.Card");
        allCards.add(StudyCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BilliardRoomCard = new JLabel(new ImageIcon(ImageLoader.GetImage("BallRoom").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        BilliardRoomCard.setToolTipText("Billard Model.Room Model.Card");
        allCards.add(BilliardRoomCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        ConservatoryCard = new JLabel(new ImageIcon(ImageLoader.GetImage("DiningRoom").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        ConservatoryCard.setToolTipText("Conservatory Model.Card");
        allCards.add(ConservatoryCard);

        characterCards.add(new CharacterCard("Miss. Scarlett", ScarlettCard, ImageLoader.GetImage("Scarlett")));
        characterCards.add(new CharacterCard("Col. Mustard", MustardCard, ImageLoader.GetImage("Mustard")));
        characterCards.add(new CharacterCard("Mrs. White", WhiteCard, ImageLoader.GetImage("White")));
        characterCards.add(new CharacterCard("Mr. Green", GreenCard, ImageLoader.GetImage("Green")));
        characterCards.add(new CharacterCard("Mrs. Peacock", PeacockCard, ImageLoader.GetImage("Peacock")));
        characterCards.add(new CharacterCard("Prof. Plum", PlumCard, ImageLoader.GetImage("Plum")));

        // Generate weapon cards
        weaponCards.add(new WeaponCard("Candlestick", CandlestickCard, ImageLoader.GetImage("Candlestick")));
        weaponCards.add(new WeaponCard("Rope", RopeCard, ImageLoader.GetImage("Rope")));
        weaponCards.add(new WeaponCard("Revolver", RevolverCard, ImageLoader.GetImage("Revolver")));
        weaponCards.add(new WeaponCard("LeadPipe", LeadPipeCard, ImageLoader.GetImage("LeadPipe")));
        weaponCards.add(new WeaponCard("Dagger", DaggerCard, ImageLoader.GetImage("Dagger")));
        weaponCards.add(new WeaponCard("Spanner", SpannerCard, ImageLoader.GetImage("Spanner")));

        // Generate room cards
        roomCards.add(new RoomCard("Library", LibraryCard, ImageLoader.GetImage("Library")));
        roomCards.add(new RoomCard("Conservatory", ConservatoryCard, ImageLoader.GetImage("Conservatory")));
        roomCards.add(new RoomCard("Kitchen", KitchenCard, ImageLoader.GetImage("Kitchen")));
        roomCards.add(new RoomCard("Study", StudyCard, ImageLoader.GetImage("Study")));
        roomCards.add(new RoomCard("Hall", HallCard, ImageLoader.GetImage("Hall")));
        roomCards.add(new RoomCard("BallRoom", BallRoomCard, ImageLoader.GetImage("BallRoom")));
        roomCards.add(new RoomCard("DiningRoom", DiningRoomCard, ImageLoader.GetImage("DiningRoom")));
        roomCards.add(new RoomCard("Lounge", LoungeCard, ImageLoader.GetImage("Lounge")));
        roomCards.add(new RoomCard("Billiard Model.Room", BilliardRoomCard, ImageLoader.GetImage("Billiard")));


        Board.getDeckOfCards().addAll(characterCards);
        Board.getDeckOfCards().addAll(weaponCards);
        Board.getDeckOfCards().addAll(roomCards);

    }

    private void generateMurderer() {
        int index = new Random().nextInt(characterCards.size());
        murderer = characterCards.get(index);
        Board.getEnvelope().add(murderer);
        Board.getDeckOfCards().remove(murderer);
        System.out.println(murderer.toString()+"----");
    }

    private void generateMurderWeapon() {
        int index = new Random().nextInt(weaponCards.size());
        murderWeapon = weaponCards.get(index);
        Board.getEnvelope().add(murderWeapon);
        Board.getDeckOfCards().remove(murderWeapon);
        System.out.println(murderWeapon.toString()+"----");
    }

    private void generateMurderRoom() {
        int index = new Random().nextInt(roomCards.size());
        murderRoom = roomCards.get(index);
        Board.getEnvelope().add(murderRoom);
        Board.getDeckOfCards().remove(murderRoom);
        System.out.println(murderRoom.toString()+"----");
    }

    private void dealCards() {
        ArrayList<Card> dealableCards = Board.getDeckOfCards();
        dealableCards.remove(murderRoom);
        dealableCards.remove(murderWeapon);
        dealableCards.remove(murderer);
        for (int i = 0; i < Board.getDeckOfCards().size(); i++) {
            Player.playerList.get(i % Player.playerList.size()).addHand(dealableCards.get(i));
        }
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

        displayName = new JLabel("\t\t\t\t\t"+currentPlayer.getName()+"'s Turn");
        displayName.setFont(new Font("Georgia", Font.PLAIN, 30));
        displayName.setForeground(Color.white);
        displayName.setHorizontalAlignment( SwingConstants.CENTER );
        InfoPanelLeft.add(displayName);

        HandCard1 = currentPlayer.getHand().get(0).getCardIcon();
        HandCard2 = currentPlayer.getHand().get(1).getCardIcon();
        HandCard3 = currentPlayer.getHand().get(2).getCardIcon();
        if (currentPlayer.getHand().size() > 3) {
            HandCard4 = currentPlayer.getHand().get(3).getCardIcon();
        } else {
            HandCard4 = CardPlaceholderCard;
        }
        if (currentPlayer.getHand().size() > 4) {
            HandCard5 = currentPlayer.getHand().get(4).getCardIcon();
        } else {
            HandCard5 = CardPlaceholderCard;
        }
        if (currentPlayer.getHand().size() > 5) {
            HandCard6 = currentPlayer.getHand().get(5).getCardIcon();
        } else {
            HandCard6 = CardPlaceholderCard;
        }
        if (currentPlayer.getHand().size() > 6) {
            HandCard7 = currentPlayer.getHand().get(6).getCardIcon();
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
        if(currentPlayer.getRemainingMoves() <= 0){
            return false;
        }

        //checks if the next tile is a character or not
        for(Character c : Board.getCharacterArrayList()){
            if(c != currentPlayer.getAssignedCharacter()){
                if(c.getX()==tileInFrontOfPlayer.getX() && c.getY()==tileInFrontOfPlayer.getY()){
                    return false;
                }
            }
        }

        //checks if the next tile has been visited by checking the list of tiles that the character has visited in their turn
        for (int[] previousTile : previouslyTraversedTiles) {
            if (previousTile[0] == tileInFrontOfPlayer.getX()/30 && previousTile[1] == tileInFrontOfPlayer.getY()/30) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "You can not visit a space that you have already been in your turn.", "Keep Moving Forward", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void endTurn(){
        hasRolled = false;
        previouslyTraversedTiles.clear();

        currentPlayerPos++;
        if (currentPlayerPos == Player.playerList.size()) {
            currentPlayerPos = 0;
        }
        currentPlayer = Player.playerList.get(currentPlayerPos);

        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Your turn is now over, it is now "+currentPlayer.getName()+"'s turn.", "End your turn", JOptionPane.PLAIN_MESSAGE);

        displayName.setText("\t\t\t\t\t"+currentPlayer.getName()+"'s Turn");

        for (int i =0; i<currentPlayer.getHand().size();i++){
            System.out.println(currentPlayer.getHand().get(i).toString());
        }

        HandCard1.setIcon(((new ImageIcon(currentPlayer.getHand().get(0).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        HandCard2.setIcon(((new ImageIcon(currentPlayer.getHand().get(1).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        HandCard3.setIcon(((new ImageIcon(currentPlayer.getHand().get(2).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        if (currentPlayer.getHand().size() > 3) {
            HandCard4.setIcon(((new ImageIcon(currentPlayer.getHand().get(3).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        } else {
            HandCard4.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (currentPlayer.getHand().size() > 4) {
            HandCard5.setIcon(((new ImageIcon(currentPlayer.getHand().get(4).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        } else {
            HandCard5.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (currentPlayer.getHand().size() > 5) {
            HandCard6.setIcon(((new ImageIcon(currentPlayer.getHand().get(5).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        } else {
            HandCard6.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        if (currentPlayer.getHand().size() > 6) {
            HandCard7.setIcon(((new ImageIcon(currentPlayer.getHand().get(6).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        } else {
            HandCard7.setIcon(new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
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
            if(!hasRolled) {
                GenerateRandomDice();
            }
            GameControlPanel.requestFocus();
        });
//        MakeSuggestion.addActionListener(e -> {
//            GameControlPanel.requestFocus();
//        });
        MakeAccusation.addActionListener(e -> {
            GameControlPanel.requestFocus();
            if(currentPlayer.canMakeActions()) {
                new AccusationSetup(currentPlayer);
            }
            else{
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
                currentPlayer = Player.getPlayerList().get(currentPlayerPos);
                //if the player has rolled then they can move
                if (hasRolled) {
                    currentPlayer = Player.getPlayerList().get(currentPlayerPos);
                    System.out.println("The current Model.Player is " + currentPlayer);
                    //if the current player has no moves left, prompt the player that their turn has ended and return the settings to their defult
                    System.out.println(currentPlayer);
                    //convert pixel pos to tile pos
                    System.out.println(currentPlayer.getAssignedCharacter());
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
            for (Room r : allRooms) {
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
