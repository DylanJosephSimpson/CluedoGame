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
    private BufferedImage CardPlaceholder;
    private BufferedImage CandlestickImage;
    private BufferedImage DaggerImage;
    private BufferedImage LeadPipeImage;
    private BufferedImage RevolverImage;
    private BufferedImage RopeImage;
    private BufferedImage SpannerImage;
    private BufferedImage ScarlettImage;
    private BufferedImage MustardImage;
    private BufferedImage WhiteImage;
    private BufferedImage GreenImage;
    private BufferedImage PeacockImage;
    private BufferedImage PlumImage;
    private BufferedImage LibraryImage;
    private BufferedImage StudyImage;
    private BufferedImage ConservatoryImage;
    private BufferedImage HallImage;
    private BufferedImage LoungeImage;
    private BufferedImage BilliardRoomImage;
    private BufferedImage KitchenImage;
    private BufferedImage BallRoomImage;
    private BufferedImage DiningRoomImage;
    // Labels for each Card in a Players Hand

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
    // Strings which are the File Locations for all the Dice Images.
    private String DiceFaceStart = "DiceFace/DiceFace";
    private ArrayList<Image> DiceImages = new ArrayList<>();
    // Strings which are the File Locations for all the Weapon Images.
    private String CandlestickPath = "WeaponIcon/Candlestick.png";
    private String DaggerPath = "WeaponIcon/Dagger.png";
    private String LeadPipePath = "WeaponIcon/LeadPipe.png";
    private String RevolverPath = "WeaponIcon/Revolver.png";
    private String RopePath = "WeaponIcon/Rope.png";
    private String SpannerPath = "WeaponIcon/Spanner.png";
    private String ScarlettPath = "Cards/Scarlett.png";
    private String MustardPath = "Cards/Mustard.png";
    private String WhitePath = "Cards/White.png";
    private String GreenPath = "Cards/Green.png";
    private String PeacockPath = "Cards/Peacock.png";
    private String PlumPath = "Cards/Plum.png";
    private String LibraryPath = "Cards/Library.png";
    private String BallRoomPath = "Cards/BallRoom.png";
    private String KitchenPath = "Cards/Kitchen.png";
    private String DiningRoomPath = "Cards/DiningRoom.png";
    private String LoungePath = "Cards/Lounge.png";
    private String HallPath = "Cards/Hall.png";
    private String StudyPath = "Cards/Study.png";
    private String BillardRoomPath = "Cards/BillardRoom.png";
    private String ConservatoryPath = "Cards/Conservatory.png";
    private String CardPlaceholderPath = "Cards/CardPlaceholder.png";
    private Character currentCharacter;
    private boolean hasRolled = false;
    private ArrayList<int[]> previouslyTraversedTiles = new ArrayList<>();
    private Tile[][] board = new Tile[25][30];
    private Board b;
    private Player currentPlayer = Player.getPlayerList().get(0);
    private int currentPlayerPos;

    private Card murderer;
    private Card murderWeapon;
    private Card murderRoom;

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

    public void setup() {
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

        //Room names being added to arraylist
        roomNames.add("Kitchen");
        roomNames.add("Ballroom");
        roomNames.add("Conservatory");
        roomNames.add("Dining Room");
        roomNames.add("Lounge");
        roomNames.add("Hall");
        roomNames.add("Study");
        roomNames.add("Billiard Room");
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
        allRooms.add(new Room("Dining Room"));
        allRooms.add(new Room("Lounge"));
        allRooms.add(new Room("Hall"));
        allRooms.add(new Room("Study"));
        allRooms.add(new Room("Billiard Room"));
        allRooms.add(new Room("Library"));
        allRooms.add(new Room("Cellar"));
        LoadImages();
        
        setupRooms();
        generateCards();
        generateMurderer();
        generateMurderRoom();
        generateMurderWeapon();
        allocateWeapons();
        dealCards();
    }
    
     /**
     * Adds the room tiles to their respective rooms. Also adds the room's doorways to the Room objects
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
                //Add the doorway tiles to the Room
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

        CardPlaceholderCard = new JLabel(new ImageIcon(CardPlaceholder.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        CardPlaceholderCard.setToolTipText("Card Place Holder");

        // Set the HandCardI label to the CandlestickImage Scaled Image.
        CandlestickCard = new JLabel(new ImageIcon(CandlestickImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        CandlestickCard.setToolTipText("Candlestick Card");
        allCards.add(CandlestickCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        DaggerCard = new JLabel(new ImageIcon(DaggerImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        DaggerCard.setToolTipText("Dagger Card");
        allCards.add(DaggerCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        LeadPipeCard = new JLabel(new ImageIcon(LeadPipeImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        LeadPipeCard.setToolTipText("LeadPipe Card");
        allCards.add(LeadPipeCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        RevolverCard = new JLabel(new ImageIcon(RevolverImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        RevolverCard.setToolTipText("Revolver Card");
        allCards.add(RevolverCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        RopeCard = new JLabel(new ImageIcon(RopeImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        RopeCard.setToolTipText("Rope Card");
        allCards.add(RopeCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        SpannerCard = new JLabel(new ImageIcon(SpannerImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        SpannerCard.setToolTipText("Spanner Card");
        allCards.add(SpannerCard);
        // Add the Hand of Cards to the JPanel


        ScarlettCard = new JLabel(new ImageIcon(ScarlettImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        ScarlettCard.setToolTipText("Scarlett Card");
        allCards.add(ScarlettCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        MustardCard = new JLabel(new ImageIcon(MustardImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        MustardCard.setToolTipText("Mustard Card");
        allCards.add(MustardCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        GreenCard = new JLabel(new ImageIcon(GreenImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        GreenCard.setToolTipText("Green Card");
        allCards.add(GreenCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        WhiteCard = new JLabel(new ImageIcon(WhiteImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        WhiteCard.setToolTipText("White Card");
        allCards.add(WhiteCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        PlumCard = new JLabel(new ImageIcon(PlumImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        PlumCard.setToolTipText("Plum Card");
        allCards.add(PlumCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        PeacockCard = new JLabel(new ImageIcon(PeacockImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        PeacockCard.setToolTipText("Peacock Card");
        allCards.add(PeacockCard);
        // Add the Hand of Cards to the JPanel


        LibraryCard = new JLabel(new ImageIcon(LibraryImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        LibraryCard.setToolTipText("Library Card");
        allCards.add(LibraryCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BallRoomCard = new JLabel(new ImageIcon(BallRoomImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        BallRoomCard.setToolTipText("Ball Room Card");
        allCards.add(BallRoomCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        KitchenCard = new JLabel(new ImageIcon(KitchenImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        KitchenCard.setToolTipText("Kitchen Card");
        allCards.add(KitchenCard);
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        DiningRoomCard = new JLabel(new ImageIcon(DiningRoomImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        DiningRoomCard.setToolTipText("Dining Room Card");
        allCards.add(DiningRoomCard);
        // Set the HandCardV label to the RopeImage Scaled Image.
        LoungeCard = new JLabel(new ImageIcon(LoungeImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        LoungeCard.setToolTipText("Lounge Card");
        allCards.add(LoungeCard);
        // Set the HandCardV label to the SpannerImage Scaled Image.
        HallCard = new JLabel(new ImageIcon(HallImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        HallCard.setToolTipText("Hall Card");
        allCards.add(HallCard);
        // Add the Hand of Cards to the JPanel
        StudyCard = new JLabel(new ImageIcon(StudyImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        StudyCard.setToolTipText("Study Card");
        allCards.add(StudyCard);
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BilliardRoomCard = new JLabel(new ImageIcon(BilliardRoomImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        BilliardRoomCard.setToolTipText("Billard Room Card");
        allCards.add(BilliardRoomCard);
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        ConservatoryCard = new JLabel(new ImageIcon(ConservatoryImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        ConservatoryCard.setToolTipText("Conservatory Card");
        allCards.add(ConservatoryCard);

        characterCards.add(new CharacterCard("Miss. Scarlett", ScarlettCard, ScarlettImage));
        characterCards.add(new CharacterCard("Col. Mustard", MustardCard, MustardImage));
        characterCards.add(new CharacterCard("Mrs. White", WhiteCard, WhiteImage));
        characterCards.add(new CharacterCard("Mr. Green", GreenCard, GreenImage));
        characterCards.add(new CharacterCard("Mrs. Peacock", PeacockCard, PeacockImage));
        characterCards.add(new CharacterCard("Prof. Plum", PlumCard, PlumImage));

        // Generate weapon cards
        weaponCards.add(new WeaponCard("Candlestick", CandlestickCard, CandlestickImage));
        weaponCards.add(new WeaponCard("Rope", RopeCard, RopeImage));
        weaponCards.add(new WeaponCard("Revolver", RevolverCard, RevolverImage));
        weaponCards.add(new WeaponCard("LeadPipe", LeadPipeCard, LeadPipeImage));
        weaponCards.add(new WeaponCard("Dagger", DaggerCard, DaggerImage));
        weaponCards.add(new WeaponCard("Spanner", SpannerCard, SpannerImage));

        // Generate room cards
        roomCards.add(new RoomCard("Library", LibraryCard, LibraryImage));
        roomCards.add(new RoomCard("Conservatory", ConservatoryCard, ConservatoryImage));
        roomCards.add(new RoomCard("Kitchen", KitchenCard, KitchenImage));
        roomCards.add(new RoomCard("Study", StudyCard, StudyImage));
        roomCards.add(new RoomCard("Hall", HallCard, HallImage));
        roomCards.add(new RoomCard("BallRoom", BallRoomCard, BallRoomImage));
        roomCards.add(new RoomCard("DiningRoom", DiningRoomCard, DiningRoomImage));
        roomCards.add(new RoomCard("Lounge", LoungeCard, LoungeImage));
        roomCards.add(new RoomCard("Billiard Room", BilliardRoomCard, BilliardRoomImage));


        Board.deckOfCards.addAll(characterCards);
        Board.deckOfCards.addAll(weaponCards);
        Board.deckOfCards.addAll(roomCards);
    }

    private void generateMurderer() {
        int index = new Random().nextInt(characterCards.size());
        murderer = characterCards.get(index);
        Board.deckOfCards.remove(murderer);
    }

    private void generateMurderWeapon() {
        int index = new Random().nextInt(weaponCards.size());
        murderWeapon = weaponCards.get(index);
        Board.deckOfCards.remove(murderWeapon);
    }

    private void generateMurderRoom() {
        int index = new Random().nextInt(roomCards.size());
        murderRoom = roomCards.get(index);
        Board.deckOfCards.remove(murderRoom);
    }

    private void dealCards() {
        for (int i = 0; i < Board.deckOfCards.size(); i++) {
            //if (!Board.deckOfCards.get(i).equals(murderer) && !Board.deckOfCards.get(i).equals(murderRoom) && !Board.deckOfCards.get(i).equals(murderWeapon)) {
                Player.playerList.get(i % Player.playerList.size()).addHand(Board.deckOfCards.get(i));
            //}
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
                board[row][col].draw(graphics, board[row][col].x, board[row][col].y);
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
        // Call the LoadImages method to ensure that all the Dice and Weapon Images have been loaded.

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
        DiceOne = new JLabel(new ImageIcon(DiceImages.get(0).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        // Set the Dice One label to the FaceOne Scaled Image.
        DiceTwo = new JLabel(new ImageIcon(DiceImages.get(0).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
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

    private void LoadImages() {

        try {
            for (int i = 1; i < 7; i++) {
                DiceImages.add(ImageIO.read(new File(DiceFaceStart + i + ".png")));
            }

            CardPlaceholder = ImageIO.read(new File(CardPlaceholderPath));

            CandlestickImage = ImageIO.read(new File(CandlestickPath));
            DaggerImage = ImageIO.read(new File(DaggerPath));
            LeadPipeImage = ImageIO.read(new File(LeadPipePath));
            RevolverImage = ImageIO.read(new File(RevolverPath));
            RopeImage = ImageIO.read(new File(RopePath));
            SpannerImage = ImageIO.read(new File(SpannerPath));

            ScarlettImage = ImageIO.read(new File(ScarlettPath));
            MustardImage = ImageIO.read(new File(MustardPath));
            WhiteImage = ImageIO.read(new File(WhitePath));
            GreenImage = ImageIO.read(new File(GreenPath));
            PlumImage = ImageIO.read(new File(PlumPath));
            PeacockImage = ImageIO.read(new File(PeacockPath));

            ConservatoryImage = ImageIO.read(new File(ConservatoryPath));
            BilliardRoomImage = ImageIO.read(new File(BillardRoomPath));
            StudyImage = ImageIO.read(new File(StudyPath));
            HallImage = ImageIO.read(new File(HallPath));
            LoungeImage = ImageIO.read(new File(LoungePath));
            DiningRoomImage = ImageIO.read(new File(DiningRoomPath));
            KitchenImage = ImageIO.read(new File(KitchenPath));
            LibraryImage = ImageIO.read(new File(LibraryPath));
            BallRoomImage = ImageIO.read(new File(BallRoomPath));

        } catch (IOException ex) {
            System.out.println("INVALID FILE NAME");
        }
    }

    private void GenerateRandomDice() {
        hasRolled = true;

        int firstDieRoll = (int) (Math.random() * (6)) + 1;
        int secondDieRoll = (int) (Math.random() * (6)) + 1;
        currentPlayer = Player.getPlayerList().get(currentPlayerPos);
        currentPlayer.setRemainingMoves(firstDieRoll + secondDieRoll);

        DiceOne.setIcon(((new ImageIcon(DiceImages.get(firstDieRoll - 1).getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
        DiceTwo.setIcon(((new ImageIcon(DiceImages.get(secondDieRoll - 1).getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));

    }


    private boolean validMove(Tile tileInFrontOfPlayer) {

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
        OpenNotes = new JButton("Open Notes");
        RollDice = new JButton("Roll Dice");
        MakeSuggestion = new JButton("Suggest");
        MakeAccusation = new JButton("Accusation");
        // Add buttonListener to the GameControlPanel's JButtons.
        // TODO : ADD PROPER FUNCTIONALITY
        EndTurn.addActionListener(e -> {
            GameControlPanel.requestFocus();
            currentPlayer.setRemainingMoves(0);
        });
        OpenNotes.addActionListener(e -> {
            GameControlPanel.requestFocus();
        });
        RollDice.addActionListener(e -> {
            if(!hasRolled) {
                GenerateRandomDice();
            }
            GameControlPanel.requestFocus();
        });
        MakeSuggestion.addActionListener(e -> {
            GameControlPanel.requestFocus();
        });
        MakeAccusation.addActionListener(e -> {
            GameControlPanel.requestFocus();
            if(currentPlayer.canMakeActions()) {
                new AccusationSetup(currentPlayer);
            }
            else{
                JOptionPane.showMessageDialog(this,
                        "Cannot make an accusation as the player has made a false accusation.",
                        "Player Error",
                        JOptionPane.ERROR_MESSAGE);
            }
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
                if (e.getKeyChar() == '2') {
                    OpenNotes.doClick();
                    GameControlPanel.requestFocus();
                }
                if (e.getKeyChar() == '3') {
                    if (!hasRolled) {
                        RollDice.doClick();
                        GameControlPanel.requestFocus();
                    }
                }
                if (e.getKeyChar() == '4') {
                    MakeAccusation.doClick();
                    new AccusationSetup(currentPlayer);
                    GameControlPanel.requestFocus();
                }
                if (e.getKeyChar() == '5') {
                    MakeSuggestion.doClick();
                    GameControlPanel.requestFocus();
                }
                currentPlayer = Player.getPlayerList().get(currentPlayerPos);
                //if the player has rolled then they can move
                if (hasRolled) {
                    currentPlayer = Player.getPlayerList().get(currentPlayerPos);
                    System.out.println("The current Player is " + currentPlayer);
                    //if the current player has no moves left, prompt the player that their turn has ended and return the settings to their defult
                    if (currentPlayer.getRemainingMoves() <= 0) {
                        hasRolled = false;
                        previouslyTraversedTiles.clear();

                        currentPlayerPos++;
                        if (currentPlayerPos == Player.playerList.size()) {
                            currentPlayerPos = 0;
                        }
                        System.out.println("----------------");
                        currentPlayer = Player.playerList.get(currentPlayerPos);

                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "You have run out of moves, it is now "+currentPlayer.getName()+"'s turn.", "End your turn", JOptionPane.PLAIN_MESSAGE);

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
                            HandCard4.setIcon(new ImageIcon(CardPlaceholder.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        }
                        if (currentPlayer.getHand().size() > 4) {
                            HandCard5.setIcon(((new ImageIcon(currentPlayer.getHand().get(4).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                        } else {
                            HandCard5.setIcon(new ImageIcon(CardPlaceholder.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        }
                        if (currentPlayer.getHand().size() > 5) {
                            HandCard6.setIcon(((new ImageIcon(currentPlayer.getHand().get(5).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                        } else {
                            HandCard6.setIcon(new ImageIcon(CardPlaceholder.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        }
                        if (currentPlayer.getHand().size() > 6) {
                            HandCard7.setIcon(((new ImageIcon(currentPlayer.getHand().get(6).getCardImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                        } else {
                            HandCard7.setIcon(new ImageIcon(CardPlaceholder.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        }

                        return;
                    }

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
                    CluedoGame.repaint();
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
        GameControlPanel.add(OpenNotes);
        GameControlPanel.add(RollDice);
        GameControlPanel.add(MakeAccusation);
        GameControlPanel.add(MakeSuggestion);
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
