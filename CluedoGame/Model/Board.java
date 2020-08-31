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

    //Card related collections and important fields (such as the Murder related cards)
    static ArrayList<Card> deckOfCards = new ArrayList<>();
    static HashSet<Card> envelope = new HashSet<>();
    static HashMap<String, Card> cardHashMap = new HashMap<>();
    private static Card murderer;
    private static Card murderRoom;
    private static Card murderWeapon;

    //Current player
    private static Player currentPlayer;


    static ArrayList<Character> characterArrayList = new ArrayList<Character>();
    static Tile[][] BoardTiles = new Tile[25][24];

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

    //Image icons used to represent players
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

    /**
     * Method for initialising the board for functionality
     */
    public static void setup() {
        characterArrayList.clear();
        //Initialising the board based on the setup of the original board
        for (int row = 0; row < boardLayoutArray.length; row++) {
            for (int col = 0; col < boardLayoutArray[row].length; col++) {
                boardLayoutArray[row][col] = boardLayoutArray[row][col];
            }
        }
        //Initialising the characters
        Scarlett = new Character("Miss. Scarlett", 210, 720);
        Mustard = new Character("Col. Mustard", 0, 510);
        White = new Character("Mrs. White", 270, 0);
        Green = new Character("Mr. Green", 420, 0);
        Peacock = new Character("Mrs. Peacock", 690, 180);
        Plum = new Character("Prof. Plum", 690, 570);

        //Initialising characters
        characterCards.add(new CharacterCard("Miss. Scarlett", ScarlettIcon, ImageLoader.GetImage("Miss. Scarlett")));
        characterCards.add(new CharacterCard("Col. Mustard", MustardIcon, ImageLoader.GetImage("Col. Mustard")));
        characterCards.add(new CharacterCard("Mrs. White", WhiteIcon, ImageLoader.GetImage("Mrs. White")));
        characterCards.add(new CharacterCard("Mr. Green", GreenIcon, ImageLoader.GetImage("Mr. Green")));
        characterCards.add(new CharacterCard("Mrs. Peacock", PeacockIcon, ImageLoader.GetImage("Mrs. Peacock")));
        characterCards.add(new CharacterCard("Prof. Plum", PlumIcon, ImageLoader.GetImage("Prof. Plum")));

        //Initialising weapon cards
        weaponCards.add(new WeaponCard("Candlestick", CandlestickIcon, ImageLoader.GetImage("Candlestick")));
        weaponCards.add(new WeaponCard("Rope", RopeIcon, ImageLoader.GetImage("Rope")));
        weaponCards.add(new WeaponCard("Revolver", RevolverIcon, ImageLoader.GetImage("Revolver")));
        weaponCards.add(new WeaponCard("LeadPipe", LeadPipeIcon, ImageLoader.GetImage("LeadPipe")));
        weaponCards.add(new WeaponCard("Dagger", DaggerIcon, ImageLoader.GetImage("Dagger")));
        weaponCards.add(new WeaponCard("Spanner", SpannerIcon, ImageLoader.GetImage("Spanner")));
        // Initialising room cards
        roomCards.add(new RoomCard("Library", LibraryIcon, ImageLoader.GetImage("Library")));
        roomCards.add(new RoomCard("Conservatory", ConservatoryIcon, ImageLoader.GetImage("Conservatory")));
        roomCards.add(new RoomCard("Kitchen", KitchenIcon, ImageLoader.GetImage("Kitchen")));
        roomCards.add(new RoomCard("Study", StudyIcon, ImageLoader.GetImage("Study")));
        roomCards.add(new RoomCard("Hall", HallIcon, ImageLoader.GetImage("Hall")));
        roomCards.add(new RoomCard("Ballroom", BallRoomIcon, ImageLoader.GetImage("BallRoom")));
        roomCards.add(new RoomCard("Dining Room", DiningRoomIcon, ImageLoader.GetImage("DiningRoom")));
        roomCards.add(new RoomCard("Lounge", LoungeIcon, ImageLoader.GetImage("Lounge")));
        roomCards.add(new RoomCard("Billiard Room", BilliardRoomIcon, ImageLoader.GetImage("Billiard")));

        //Initialising arraylist of characters
        characterArrayList.add(Scarlett);
        characterArrayList.add(Mustard);
        characterArrayList.add(White);
        characterArrayList.add(Green);
        characterArrayList.add(Peacock);
        characterArrayList.add(Plum);

       // Setting up deck of cards
        Board.getDeckOfCards().addAll(characterCards);
        Board.getDeckOfCards().addAll(weaponCards);
        Board.getDeckOfCards().addAll(roomCards);

        //generating Murder, Murder weapon and Murder room
        generateMurderer();
        generateMurderWeapon();
        generateMurderRoom();

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

        //Adding all weapons to the list
        allWeapons.add(Candlestick);
        allWeapons.add(Dagger);
        allWeapons.add(LeadPipe);
        allWeapons.add(Revolver);
        allWeapons.add(Rope);
        allWeapons.add(Spanner);

        //Adding all rooms to the list
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

        //Setting up the loading of images
        ImageLoader.LoadImages();

        //Initialising the icons
        CardPlaceholderIcon = new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        CandlestickIcon = new ImageIcon(ImageLoader.GetImage("Candlestick").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        DaggerIcon = new ImageIcon(ImageLoader.GetImage("Dagger").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        LeadPipeIcon = new ImageIcon(ImageLoader.GetImage("LeadPipe").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        RevolverIcon = new ImageIcon(ImageLoader.GetImage("Revolver").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        RopeIcon = new ImageIcon(ImageLoader.GetImage("Rope").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        SpannerIcon = new ImageIcon(ImageLoader.GetImage("Spanner").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        ScarlettIcon = new ImageIcon(ImageLoader.GetImage("Miss. Scarlett").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        MustardIcon = new ImageIcon(ImageLoader.GetImage("Col. Mustard").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        GreenIcon = new ImageIcon(ImageLoader.GetImage("Mr. Green").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        WhiteIcon = new ImageIcon(ImageLoader.GetImage("Mrs. White").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        PlumIcon = new ImageIcon(ImageLoader.GetImage("Prof. Plum").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        PeacockIcon = new ImageIcon(ImageLoader.GetImage("Mrs. Peacock").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        LibraryIcon = new ImageIcon(ImageLoader.GetImage("Library").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        BallRoomIcon = new ImageIcon(ImageLoader.GetImage("BallRoom").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        KitchenIcon = new ImageIcon(ImageLoader.GetImage("Kitchen").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        DiningRoomIcon = new ImageIcon(ImageLoader.GetImage("DiningRoom").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        LoungeIcon = new ImageIcon(ImageLoader.GetImage("Lounge").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        HallIcon = new ImageIcon(ImageLoader.GetImage("Hall").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        StudyIcon = new ImageIcon(ImageLoader.GetImage("Study").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        BilliardRoomIcon = new ImageIcon(ImageLoader.GetImage("Billiard").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        ConservatoryIcon = new ImageIcon(ImageLoader.GetImage("Conservatory").getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        //Putting Cards into a hashmap used for quick access
        for (Card card : deckOfCards) {
            cardHashMap.put(card.toString(), card);
        }
        //Adding tiles to the board
        populateBoard();
    }

    /**
     * Generate the Murder Character Card
     */
    private static void generateMurderer() {
        int index = new Random().nextInt(Board.getCharacterCards().size());
        murderer = Board.getCharacterCards().get(index);
        Board.getEnvelope().add((Card) murderer);
    }

    /**
     * Generate the murder weapon
     */
    private static void generateMurderWeapon() {
        int index = new Random().nextInt(Board.getWeaponCards().size());
        murderWeapon = Board.getWeaponCards().get(index);
        Board.getEnvelope().add((Card) murderWeapon);
    }

    /**
     * Generate the MurderRoom
     */
    private static void generateMurderRoom() {
        int index = new Random().nextInt(Board.getRoomCards().size());
        murderRoom = Board.getRoomCards().get(index);
        Board.getEnvelope().add((Card) murderRoom);
    }

    /**
     * Dealing the cards to players
     */
    public static void dealCards() {
        ArrayList<Card> dealableCards = Board.getDeckOfCards();
        Collections.shuffle(dealableCards);
        //Removing murder related cards
        dealableCards.remove(murderRoom);
        dealableCards.remove(murderWeapon);
        dealableCards.remove(murderer);

        //Adding cards to every player
        for (int i = 0; i < Board.getDeckOfCards().size(); i++) {
            Player.playerList.get(i % Player.playerList.size()).addHand(dealableCards.get(i));
        }
    }

    /**
     * Getter method for returning image icons
     * @param imageToGrab the String representing the Icon to grab
     * @return an Image icon
     */
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

    /**
     * Getting a character object from a provided String
     * @param characterName the character being accessed
     * @return the Character
     */
    public static Character getCharacterFromString(String characterName) {
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

    /**
     *Getting a Weapon object from a provided String
     * @param weaponName the weapon name from the provided String
     * @return the Weapon
     */
    public static Weapon getWeaponFromString(String weaponName) {
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

    /**
     * Get the Room from a String provided
     * @param roomName the provided Room name
     * @return the Room object
     */
    public static Room getRoomFromString(String roomName) {
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

    /**
     * Constructor for board
     */
    public Board() {}

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

    /**
     * The board layout array represented as String, like Assignment 1 used for updating movements and tile logic
     */
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
    /**
     * The original board layout array represented as String, like Assignment 1 used for updating movements and referring to original tile logic
     * for game logic
     */
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
     *
     * @return - boardLayoutArray which is the array that is updated throughout the game
     */
    public static String[][] getBoardLayoutArray() {
        return boardLayoutArray;
    }

    /**
     * Setter method which updates the boardLayoutArray
     */
    public void setBoardLayoutArray(String[][] boardLayoutArray) {
        this.boardLayoutArray = boardLayoutArray;
    }

    /**
     * Getter method which returns the originalBoardLayoutArray
     *
     * @return - originalBoardLayoutArray which is the array that is not updated throughout the game
     */
    public static String[][] getOriginalBoardLayoutArray() {
        return originalBoardLayoutArray;
    }

    /**
     * Getter method to get a player's hand.
     *
     * @return - deckOfCards which is an array that contains all the cards in a players hand.
     */
    public static ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    /**
     * Returns the envelope with all of the murder cards in it
     * @return the set of cards
     */
    public static HashSet<Card> getEnvelope() {
        return envelope;
    }

    /**
     * The player who is currently being accessed and moved
     * @return the current player
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * The hashmap which stores the Cards for ease of access
     * @return the cardHashMap collection
     */
    public static HashMap<String, Card> getCardHashMap() {
        return cardHashMap;
    }

    /**
     * Getter for the arraylist of characters
     * @return the arraylist of characters
     */
    public static ArrayList<Character> getCharacterArrayList() {
        return characterArrayList;
    }

    /**
     * Getter for the current character
     * @return the character
     */
    public static Character getCharacter(int index) {
        return characterArrayList.get(index);
    }

    /**
     * Sets the current player to the next players
     */
    public static void setCurrentPlayer(Player currentPlayer) {
        Board.currentPlayer = currentPlayer;
    }



    /**
     * Getter for returning all weapons
     * @return all of the weapons
     */
    public static ArrayList<Weapon> getAllWeapons() {
        return allWeapons;
    }

    /**
     * Getter for returning all Rooms
     * @return all of the rooms
     */
    public static ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    /**
     * Getter for returning all Room cards
     * @return all of the room cards
     */
    public static ArrayList<RoomCard> getRoomCards() {
        return roomCards;
    }

    /**
     * Method for getting all weapon cards
     * @return the weapon cards
     */
    public static ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }

    /**
     * Method for returning all the character cards
     * @return the character cards
     */
    public static ArrayList<CharacterCard> getCharacterCards() {
        return characterCards;
    }

    /**
     * Getting all the roomnames
     * @return the String
     */
    public static HashSet<String> getRoomNames() {
        return roomNames;
    }

    @Override
    public String toString() {
        String output = "";
        for (int row = 0; row < boardLayoutArray.length; row++) {
            for (int col = 0; col < boardLayoutArray[row].length; col++) {
                output += boardLayoutArray[row][col];
            }
            output += "\n";
        }
        return output;
    }

}
