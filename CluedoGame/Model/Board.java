package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * The board class is responsible for drawing and keeping track of
 * the components that make up the board.
 */
public class Board {

    static ArrayList<Card> deckOfCards = new ArrayList<>();
    static HashSet<Card> envelope = new HashSet<>();
    static HashMap<String, Card> cardHashMap = new HashMap<>();
    private static Card murderer;
    private static Card murderRoom;
    private static Card murderWeapon;
    private static Player currentPlayer;
    static ArrayList<Character> characterArrayList = new ArrayList<Character>();
    private static ArrayList<JLabel> allCards = new ArrayList<>();
    static Tile [][] BoardTiles = new Tile[25][24];

    // Initialization of characters
    public static ArrayList<Weapon> allWeapons = new ArrayList<>();
    public static ArrayList<Room> allRooms = new ArrayList<>();
    public static ArrayList<RoomCard> roomCards = new ArrayList<>();
    public static ArrayList<WeaponCard> weaponCards = new ArrayList<>();
    public static ArrayList<CharacterCard> characterCards = new ArrayList<>();
    public static HashSet<String> roomNames = new HashSet<>();

    // Initialization of Weapons
    private static Weapon Candlestick = new Weapon("Candlestick");
    private static Weapon Dagger = new Weapon("Dagger");
    private static Weapon LeadPipe = new Weapon("LeadPipe");
    private static Weapon Revolver = new Weapon("Revolver");
    private static Weapon Rope = new Weapon("Rope");
    private static Weapon Spanner = new Weapon("Spanner");

    private static Room Kitchen = new Room("Kitchen");
    private static Room Ballroom = new Room("Ballroom");
    private static Room Conservatory = new Room("Conservatory");
    private static Room DiningRoom = new Room("Dining Room");
    private static Room Lounge = new Room("Lounge");
    private static Room Hall = new Room("Hall");
    private static Room Study = new Room("Study");
    private static Room BilliardRoom = new Room("Billiard Room");
    private static Room Library = new Room("Library");
    private static Room Cellar = new Room("Cellar");

    // Initialization of Characters
    private static Character Scarlett = new Character("Miss. Scarlett", 210, 720);
    private static Character Mustard = new Character("Col. Mustard", 0, 510);
    private static Character White = new Character("Mrs. White", 270, 0);
    private static Character Green = new Character("Mr. Green", 420, 0);
    private static Character Peacock = new Character("Mrs. Peacock", 690, 180);
    private static Character Plum = new Character("Prof. Plum", 690, 570);

    private static ImageIcon CardPlaceholderIcon;
    private static ImageIcon CandlestickIcon;
    private static ImageIcon DaggerIcon;
    private static ImageIcon LeadPipeIcon;
    private static ImageIcon RevolverIcon;
    private static ImageIcon RopeIcon;
    private static ImageIcon SpannerIcon;
    private static ImageIcon ScarlettIcon;
    private static ImageIcon MustardIcon;
    private static ImageIcon GreenIcon;
    private static ImageIcon WhiteIcon;
    private static ImageIcon PlumIcon;
    private static ImageIcon PeacockIcon;
    private static ImageIcon LibraryIcon;
    private static ImageIcon BallRoomIcon;
    private static ImageIcon KitchenIcon;
    private static ImageIcon DiningRoomIcon;
    private static ImageIcon LoungeIcon;
    private static ImageIcon HallIcon;
    private static ImageIcon StudyIcon;
    private static ImageIcon BilliardRoomIcon;
    private static ImageIcon ConservatoryIcon;

    public static void setup() {

        characterCards.add(new CharacterCard("Miss. Scarlett", ScarlettIcon, ImageLoader.GetImage("Miss. Scarlett")));
        characterCards.add(new CharacterCard("Col. Mustard", MustardIcon, ImageLoader.GetImage("Col. Mustard")));
        characterCards.add(new CharacterCard("Mrs. White", WhiteIcon, ImageLoader.GetImage("Mrs. White")));
        characterCards.add(new CharacterCard("Mr. Green", GreenIcon, ImageLoader.GetImage("Mr. Green")));
        characterCards.add(new CharacterCard("Mrs. Peacock", PeacockIcon, ImageLoader.GetImage("Mrs. Peacock")));
        characterCards.add(new CharacterCard("Prof. Plum", PlumIcon, ImageLoader.GetImage("Prof. Plum")));
        // Generate weapon cards
        weaponCards.add(new WeaponCard("Candlestick", CandlestickIcon, ImageLoader.GetImage("Candlestick")));
        weaponCards.add(new WeaponCard("Rope", RopeIcon, ImageLoader.GetImage("Rope")));
        weaponCards.add(new WeaponCard("Revolver", RevolverIcon, ImageLoader.GetImage("Revolver")));
        weaponCards.add(new WeaponCard("LeadPipe", LeadPipeIcon, ImageLoader.GetImage("LeadPipe")));
        weaponCards.add(new WeaponCard("Dagger", DaggerIcon, ImageLoader.GetImage("Dagger")));
        weaponCards.add(new WeaponCard("Spanner", SpannerIcon, ImageLoader.GetImage("Spanner")));
        System.out.println("Aztec");
        // Generate room cards
        roomCards.add(new RoomCard("Library", LibraryIcon, ImageLoader.GetImage("Library")));
        roomCards.add(new RoomCard("Conservatory", ConservatoryIcon, ImageLoader.GetImage("Conservatory")));
        roomCards.add(new RoomCard("Kitchen", KitchenIcon, ImageLoader.GetImage("Kitchen")));
        roomCards.add(new RoomCard("Study", StudyIcon, ImageLoader.GetImage("Study")));
        roomCards.add(new RoomCard("Hall", HallIcon, ImageLoader.GetImage("Hall")));
        roomCards.add(new RoomCard("Ballroom", BallRoomIcon, ImageLoader.GetImage("BallRoom")));
        roomCards.add(new RoomCard("Dining Room", DiningRoomIcon, ImageLoader.GetImage("DiningRoom")));
        roomCards.add(new RoomCard("Lounge", LoungeIcon, ImageLoader.GetImage("Lounge")));
        roomCards.add(new RoomCard("Billiard Room", BilliardRoomIcon, ImageLoader.GetImage("Billiard")));

        characterArrayList.add(Scarlett);
        characterArrayList.add(Mustard);
        characterArrayList.add(White);
        characterArrayList.add(Green);
        characterArrayList.add(Peacock);
        characterArrayList.add(Plum);

        Board.getDeckOfCards().addAll(characterCards);
        Board.getDeckOfCards().addAll(weaponCards);
        Board.getDeckOfCards().addAll(roomCards);

        generateMurderer();
        generateMurderWeapon();
        generateMurderRoom();

        //Model.Room names being added to arraylist
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
        allRooms.add(Kitchen);
        allRooms.add(Ballroom);
        allRooms.add(Conservatory);
        allRooms.add(DiningRoom);
        allRooms.add(Lounge);
        allRooms.add(Hall);
        allRooms.add(Study);
        allRooms.add(BilliardRoom);
        allRooms.add(Library);
        allRooms.add(Cellar);

        ImageLoader.LoadImages();

        CardPlaceholderIcon = new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardI label to the CandlestickImage Scaled Image.
        CandlestickIcon = new ImageIcon(ImageLoader.GetImage("Candlestick").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        DaggerIcon = new ImageIcon(ImageLoader.GetImage("Dagger").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        LeadPipeIcon = new ImageIcon(ImageLoader.GetImage("LeadPipe").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        RevolverIcon = new ImageIcon(ImageLoader.GetImage("Revolver").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the RopeImage Scaled Image.
        RopeIcon = new ImageIcon(ImageLoader.GetImage("Rope").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the SpannerImage Scaled Image.
        SpannerIcon = new ImageIcon(ImageLoader.GetImage("Spanner").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Add the Hand of Cards to the JPanel
        ScarlettIcon = new ImageIcon(ImageLoader.GetImage("Miss. Scarlett").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        MustardIcon = new ImageIcon(ImageLoader.GetImage("Col. Mustard").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        GreenIcon = new ImageIcon(ImageLoader.GetImage("Mr. Green").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        WhiteIcon = new ImageIcon(ImageLoader.GetImage("Mrs. White").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the RopeImage Scaled Image.
        PlumIcon = new ImageIcon(ImageLoader.GetImage("Prof. Plum").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the SpannerImage Scaled Image.
        PeacockIcon = new ImageIcon(ImageLoader.GetImage("Mrs. Peacock").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Add the Hand of Cards to the JPanel
        LibraryIcon = new ImageIcon(ImageLoader.GetImage("Library").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BallRoomIcon = new ImageIcon(ImageLoader.GetImage("BallRoom").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        KitchenIcon = new ImageIcon(ImageLoader.GetImage("Kitchen").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        DiningRoomIcon = new ImageIcon(ImageLoader.GetImage("DiningRoom").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the RopeImage Scaled Image.
        LoungeIcon = new ImageIcon(ImageLoader.GetImage("Lounge").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the SpannerImage Scaled Image.
        HallIcon = new ImageIcon(ImageLoader.GetImage("Hall").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Add the Hand of Cards to the JPanel
        StudyIcon = new ImageIcon(ImageLoader.GetImage("Study").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BilliardRoomIcon = new ImageIcon(ImageLoader.GetImage("Billiard").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        ConservatoryIcon = new ImageIcon(ImageLoader.GetImage("Conservatory").getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        for (Card card : deckOfCards) {
            cardHashMap.put(card.toString(), card);
        }

        populateBoard();

    }

    private static void generateMurderer() {
        int index = new Random().nextInt(Board.getCharacterCards().size());
        murderer = Board.getCharacterCards().get(index);
        Board.getEnvelope().add(murderer);
        Board.getDeckOfCards().remove(murderer);
    }

    private static void generateMurderWeapon() {
        int index = new Random().nextInt(Board.getWeaponCards().size());
        murderWeapon = Board.getWeaponCards().get(index);
        Board.getEnvelope().add(murderWeapon);
        Board.getDeckOfCards().remove(murderWeapon);
    }

    private static void generateMurderRoom() {
        int index = new Random().nextInt(Board.getRoomCards().size());
        murderRoom = Board.getRoomCards().get(index);
        Board.getEnvelope().add(murderRoom);
        Board.getDeckOfCards().remove(murderRoom);
    }

    public static void dealCards() {
        ArrayList<Card> dealableCards = Board.getDeckOfCards();
        Collections.shuffle(dealableCards);
        dealableCards.remove(murderRoom);
        dealableCards.remove(murderWeapon);
        dealableCards.remove(murderer);
        for (int i = 0; i < Board.getDeckOfCards().size(); i++) {
            Player.playerList.get(i % Player.playerList.size()).addHand(dealableCards.get(i));
        }
    }

    public static ImageIcon GetIcon(String imageToGrab) {
        switch (imageToGrab) {
            case "Candlestick":
                return CandlestickIcon;
            case "Dagger":
                return DaggerIcon;
            case "LeadPipe":
                return LeadPipeIcon;
            case "Revolver":
                return RevolverIcon;
            case "Rope":
                return RopeIcon;
            case "Spanner":
                return SpannerIcon;
            case "Miss. Scarlett":
                return ScarlettIcon;
            case "Col. Mustard":
                return MustardIcon;
            case "Mrs. White":
                return WhiteIcon;
            case "Mr. Green":
                return GreenIcon;
            case "Mrs. Peacock":
                return PeacockIcon;
            case "Prof. Plum":
                return PlumIcon;
            case "Library":
                return LibraryIcon;
            case "Study":
                return StudyIcon;
            case "Conservatory":
                return ConservatoryIcon;
            case "Hall":
                return HallIcon;
            case "Lounge":
                return LoungeIcon;
            case "Billiard Room":
                return BilliardRoomIcon;
            case "Kitchen":
                return KitchenIcon;
            case "Ballroom":
                return BallRoomIcon;
            case "Dining Room":
                return DiningRoomIcon;
            default:
                return CardPlaceholderIcon;
        }
    }

    public static Character getCharacterFromString(String characterName){
        switch (characterName) {
            case "Miss. Scarlett":
                return Scarlett;
            case "Col. Mustard":
                return Mustard;
            case "Mrs. White":
                return White;
            case "Mr. Green":
                return Green;
            case "Mrs. Peacock":
                return Peacock;
            case "Prof. Plum":
                return Plum;
        }
        return null;
    }

    public static Weapon getWeaponFromString(String weaponName){
        switch (weaponName) {
            case "Candlestick":
                return Candlestick;
            case "Dagger":
                return Dagger;
            case "LeadPipe":
                return LeadPipe;
            case "Revolver":
                return Revolver;
            case "Rope":
                return Rope;
            case "Spanner":
                return Spanner;
        }
        return null;
    }

    public static Room getRoomFromString(String roomName){
        System.out.println("PRAVEEN WAS HERE :" + roomName);
        switch (roomName) {
            case "Kitchen":
            case "k":
                return Kitchen;
            case "Ballroom":
            case "b":
                return Ballroom;
            case "Conservatory":
            case "c":
                return Conservatory;
            case "Dining Room":
            case "d":
                return DiningRoom;
            case "Lounge":
            case "l":
                return Lounge;
            case "Hall":
            case "h":
                return Hall;
            case "Study":
            case "s":
                return Study;
            case "Billiard Room":
            case "i":
                return BilliardRoom;
            case "Library":
            case "y":
                return Library;
            case "Cellar":
            case "e":
                return Cellar;
        }
        return null;
    }

    public Board() {
    }

    /**
     * Populates the 2d array board with tiles
     */
    private static void populateBoard() {
        for (int row = 0; row < 25; row++) {
            for (int col = 0; col < 24; col++) {
                BoardTiles[row][col] = new Tile(boardLayoutArray[row][col], row, col);
            }
        }
    }

    private static String[][] boardLayoutArray = new String[][]
            {{
                    "-", "-", "-", "-", "-", "-", "-", "-", "-", "G", "-", "-", "-", "-", "W", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"-", "-", "-", "-", "-", "-", "-", " ", " ", " ", "-", "-", "-", "-", " ", " ", " ", "-", "-", "-", "-", "-", "-", "-"},
                    {"-", "k", "k", "k", "k", "-", " ", " ", "-", "-", "-", "b", "b", "-", "-", "-", " ", " ", "-", "c", "c", "c", "c", "-"},
                    {"-", "k", "k", "k", "k", "-", " ", " ", "-", "b", "b", "b", "b", "b", "b", "-", " ", " ", "-", "c", "c", "c", "c", "-"},
                    {"-", "k", "k", "k", "k", "-", " ", " ", "-", "b", "b", "b", "b", "b", "b", "-", " ", " ", "@", "c", "c", "c", "c", "-"},
                    {"-", "k", "k", "k", "k", "-", " ", " ", "@", "b", "b", "b", "b", "b", "b", "@", " ", " ", "-", "-", "-", "-", "-", "-"},
                    {"-", "-", "-", "-", "@", "-", " ", " ", "-", "b", "b", "b", "b", "b", "b", "-", " ", " ", " ", " ", " ", " ", " ", "C"},
                    {"-", " ", " ", " ", " ", " ", " ", " ", "-", "@", "-", "-", "-", "-", "@", "-", " ", " ", " ", " ", " ", " ", " ", "-"},
                    {"-", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "-", "-", "-", "-", "-", "-"},
                    {"-", "-", "-", "-", "-", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "@", "i", "i", "i", "i", "-"},
                    {"-", "d", "d", "d", "-", "-", "-", "-", " ", " ", "-", "-", "-", "-", "-", " ", " ", " ", "-", "i", "i", "i", "i", "-"},
                    {"-", "d", "d", "d", "d", "d", "d", "-", " ", " ", "-", "e", "e", "e", "-", " ", " ", " ", "-", "i", "i", "i", "i", "-"},
                    {"-", "d", "d", "d", "d", "d", "d", "@", " ", " ", "-", "e", "e", "e", "-", " ", " ", " ", "-", "-", "-", "-", "@", "-"},
                    {"-", "d", "d", "d", "d", "d", "d", "-", " ", " ", "-", "e", "e", "e", "-", " ", " ", " ", " ", " ", " ", " ", " ", "-"},
                    {"-", "d", "d", "d", "d", "d", "d", "-", " ", " ", "-", "e", "e", "e", "-", " ", " ", " ", "-", "-", "@", "-", "-", "-"},
                    {"-", "-", "-", "-", "-", "-", "@", "-", " ", " ", "-", "e", "e", "e", "-", " ", " ", "-", "-", "y", "y", "y", "y", "-"},
                    {"-", " ", " ", " ", " ", " ", " ", " ", " ", " ", "-", "-", "-", "-", "-", " ", " ", "@", "y", "y", "y", "y", "y", "-"},
                    {"M", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "-", "-", "y", "y", "y", "y", "-"},
                    {"-", " ", " ", " ", " ", " ", " ", " ", " ", "-", "-", "@", "@", "-", "-", " ", " ", " ", "-", "-", "-", "-", "-", "-"},
                    {"-", "-", "-", "-", "-", "@", "-", " ", " ", "-", "h", "h", "h", "h", "-", " ", " ", " ", " ", " ", " ", " ", " ", "P"},
                    {"-", "l", "l", "l", "l", "l", "-", " ", " ", "-", "h", "h", "h", "h", "@", " ", " ", " ", " ", " ", " ", " ", " ", "-"},
                    {"-", "l", "l", "l", "l", "l", "-", " ", " ", "-", "h", "h", "h", "h", "-", " ", " ", "-", "@", "-", "-", "-", "-", "-"},
                    {"-", "l", "l", "l", "l", "l", "-", " ", " ", "-", "h", "h", "h", "h", "-", " ", " ", "-", "s", "s", "s", "s", "s", "-"},
                    {"-", "l", "l", "l", "l", "l", "-", " ", " ", "-", "h", "h", "h", "h", "-", " ", " ", "-", "s", "s", "s", "s", "s", "-"},
                    {"-", "-", "-", "-", "-", "-", "-", "S", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},

            };
    private static String[][] originalBoardLayoutArray = new String[][]
            {
                    {"-", "-", "-", "-", "-", "-", "-", "-", "-", " ", "-", "-", "-", "-", " ", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"-", "-", "-", "-", "-", "-", "-", " ", " ", " ", "-", "-", "-", "-", " ", " ", " ", "-", "-", "-", "-", "-", "-", "-"},
                    {"-", "k", "k", "k", "k", "-", " ", " ", "-", "-", "-", "b", "b", "-", "-", "-", " ", " ", "-", "c", "c", "c", "c", "-"},
                    {"-", "k", "k", "k", "k", "-", " ", " ", "-", "b", "b", "b", "b", "b", "b", "-", " ", " ", "-", "c", "c", "c", "c", "-"},
                    {"-", "k", "k", "k", "k", "-", " ", " ", "-", "b", "b", "b", "b", "b", "b", "-", " ", " ", "@", "c", "c", "c", "c", "-"},
                    {"-", "k", "k", "k", "k", "-", " ", " ", "@", "b", "b", "b", "b", "b", "b", "@", " ", " ", "-", "-", "-", "-", "-", "-"},
                    {"-", "-", "-", "-", "@", "-", " ", " ", "-", "b", "b", "b", "b", "b", "b", "-", " ", " ", " ", " ", " ", " ", " ", " "},
                    {"-", " ", " ", " ", " ", " ", " ", " ", "-", "@", "-", "-", "-", "-", "@", "-", " ", " ", " ", " ", " ", " ", " ", "-"},
                    {"-", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "-", "-", "-", "-", "-", "-"},
                    {"-", "-", "-", "-", "-", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "@", "i", "i", "i", "i", "-"},
                    {"-", "d", "d", "d", "-", "-", "-", "-", " ", " ", "-", "-", "-", "-", "-", " ", " ", " ", "-", "i", "i", "i", "i", "-"},
                    {"-", "d", "d", "d", "d", "d", "d", "-", " ", " ", "-", "e", "e", "e", "-", " ", " ", " ", "-", "i", "i", "i", "i", "-"},
                    {"-", "d", "d", "d", "d", "d", "d", "@", " ", " ", "-", "e", "e", "e", "-", " ", " ", " ", "-", "-", "-", "-", "@", "-"},
                    {"-", "d", "d", "d", "d", "d", "d", "-", " ", " ", "-", "e", "e", "e", "-", " ", " ", " ", " ", " ", " ", " ", " ", "-"},
                    {"-", "d", "d", "d", "d", "d", "d", "-", " ", " ", "-", "e", "e", "e", "-", " ", " ", " ", "-", "-", "@", "-", "-", "-"},
                    {"-", "-", "-", "-", "-", "-", "@", "-", " ", " ", "-", "e", "e", "e", "-", " ", " ", "-", "-", "y", "y", "y", "y", "-"},
                    {"-", " ", " ", " ", " ", " ", " ", " ", " ", " ", "-", "-", "-", "-", "-", " ", " ", "@", "y", "y", "y", "y", "y", "-"},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "-", "-", "y", "y", "y", "y", "-"},
                    {"-", " ", " ", " ", " ", " ", " ", " ", " ", "-", "-", "@", "@", "-", "-", " ", " ", " ", "-", "-", "-", "-", "-", "-"},
                    {"-", "-", "-", "-", "-", "@", "-", " ", " ", "-", "h", "h", "h", "h", "-", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {"-", "l", "l", "l", "l", "l", "-", " ", " ", "-", "h", "h", "h", "h", "@", " ", " ", " ", " ", " ", " ", " ", " ", "-"},
                    {"-", "l", "l", "l", "l", "l", "-", " ", " ", "-", "h", "h", "h", "h", "-", " ", " ", "-", "@", "-", "-", "-", "-", "-"},
                    {"-", "l", "l", "l", "l", "l", "-", " ", " ", "-", "h", "h", "h", "h", "-", " ", " ", "-", "s", "s", "s", "s", "s", "-"},
                    {"-", "l", "l", "l", "l", "l", "-", " ", " ", "-", "h", "h", "h", "h", "-", " ", " ", "-", "s", "s", "s", "s", "s", "-"},
                    {"-", "-", "-", "-", "-", "-", "-", " ", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            };

    /**
     * Getter method which returns the BoardLayoutArray
     * @return - boardLayoutArray which is the array that is updated throughout the game
     */
    public static String[][] getBoardLayoutArray() {
        return boardLayoutArray;
    }

    /**
     * Setter method which updates the boardLayoutArray
     *
     */
    public void setBoardLayoutArray(String[][] boardLayoutArray) {
        this.boardLayoutArray = boardLayoutArray;
    }

    /**
     * Getter method which returns the originalBoardLayoutArray
     * @return - originalBoardLayoutArray which is the array that is not updated throughout the game
     */
    public static String[][] getOriginalBoardLayoutArray() {
        return originalBoardLayoutArray;
    }

    /**
     * Getter method to get a player's hand.
     * @return - deckOfCards which is an array that contains all the cards in a players hand.
     */
    public static ArrayList<Card> getDeckOfCards() { return deckOfCards; }

    /**
     *
     * @return
     */
    public static HashSet<Card> getEnvelope() { return envelope; }

    /**
     *
     * @return
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     *
     * @return
     */
    public static HashMap<String, Card> getCardHashMap() {
        return cardHashMap;
    }

    /**
     *
     * @return
     */
    public static ArrayList<Character> getCharacterArrayList() {
        return characterArrayList;
    }

    /**
     *
     * @return
     */
    public static Character getCharacter(int index) {
        return characterArrayList.get(index);
    }

    /**
     *
     * @return
     */
    public static void setCurrentPlayer(Player currentPlayer) { Board.currentPlayer = currentPlayer; }

    /**
     *
     * @return
     */
    public static ArrayList<JLabel> getAllCards() {
        return allCards;
    }

    /**
     *
     * @return
     */
    public static ArrayList<Weapon> getAllWeapons() {
        return allWeapons;
    }

    /**
     *
     * @return
     */
    public static ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    /**
     *
     * @return
     */
    public static ArrayList<RoomCard> getRoomCards() {
        return roomCards;
    }

    /**
     *
     * @return
     */
    public static ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }

    /**
     *
     * @return
     */
    public static ArrayList<CharacterCard> getCharacterCards() {
        return characterCards;
    }

    /**
     *
     * @return
     */
    public static HashSet<String> getRoomNames() {
        return roomNames;
    }

}
