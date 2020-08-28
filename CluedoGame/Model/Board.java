package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The board class is responsible for drawing and keeping track of
 * the components that make up the board.
 */
public class Board {

    public static ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public static HashSet<Card> getEnvelope() {
        return envelope;
    }

    static ArrayList<Card> deckOfCards = new ArrayList<>();
    static HashSet<Card> envelope = new HashSet<>();
    static HashMap<String, Card> cardHashMap = new HashMap<>();

    public static HashMap<String, Card> getCardHashMap() {
        return cardHashMap;
    }

    public static ArrayList<Character> getCharacterArrayList() {
        return characterArrayList;
    }

    public static Character getCharacter(int index) {
        return characterArrayList.get(index);
    }

    static ArrayList<Character> characterArrayList = new ArrayList<Character>();

    public static ArrayList<JLabel> getAllCards() {
        return allCards;
    }

    private static ArrayList<JLabel> allCards = new ArrayList<>();

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

    public static ArrayList<Weapon> getAllWeapons() {
        return allWeapons;
    }

    public static ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    public static ArrayList<RoomCard> getRoomCards() {
        return roomCards;
    }

    public static ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }

    public static ArrayList<CharacterCard> getCharacterCards() {
        return characterCards;
    }

    public static HashSet<String> getRoomNames() {
        return roomNames;
    }

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

        CardPlaceholderIcon = new ImageIcon(ImageLoader.GetImage("Placeholder").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardI label to the CandlestickImage Scaled Image.
        CandlestickIcon  = new ImageIcon(ImageLoader.GetImage("Candlestick").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
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
        ScarlettIcon = new ImageIcon(ImageLoader.GetImage("Scarlett").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        MustardIcon = new ImageIcon(ImageLoader.GetImage("Mustard").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        GreenIcon = new ImageIcon(ImageLoader.GetImage("White").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        WhiteIcon = new ImageIcon(ImageLoader.GetImage("Green").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the RopeImage Scaled Image.
        PlumIcon = new ImageIcon(ImageLoader.GetImage("Peacock").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the SpannerImage Scaled Image.
        PeacockIcon = new ImageIcon(ImageLoader.GetImage("Plum").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Add the Hand of Cards to the JPanel
        LibraryIcon = new ImageIcon(ImageLoader.GetImage("Library").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BallRoomIcon = new ImageIcon(ImageLoader.GetImage("Study").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        KitchenIcon = new ImageIcon(ImageLoader.GetImage("Conservatory").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        DiningRoomIcon = new ImageIcon(ImageLoader.GetImage("Hall").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the RopeImage Scaled Image.
        LoungeIcon = new ImageIcon(ImageLoader.GetImage("Lounge").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardV label to the SpannerImage Scaled Image.
        HallIcon = new ImageIcon(ImageLoader.GetImage("Billiard").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Add the Hand of Cards to the JPanel
        StudyIcon = new ImageIcon(ImageLoader.GetImage("Kitchen").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardII label to the DaggerImage Scaled Image.
        BilliardRoomIcon = new ImageIcon(ImageLoader.GetImage("BallRoom").getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        ConservatoryIcon = new ImageIcon(ImageLoader.GetImage("DiningRoom").getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        Board.getDeckOfCards().addAll(characterCards);
        Board.getDeckOfCards().addAll(weaponCards);
        Board.getDeckOfCards().addAll(roomCards);

        characterCards.add(new CharacterCard("Miss. Scarlett", ScarlettIcon, ImageLoader.GetImage("Scarlett")));
        characterCards.add(new CharacterCard("Col. Mustard", MustardIcon, ImageLoader.GetImage("Mustard")));
        characterCards.add(new CharacterCard("Mrs. White", WhiteIcon, ImageLoader.GetImage("White")));
        characterCards.add(new CharacterCard("Mr. Green", GreenIcon, ImageLoader.GetImage("Green")));
        characterCards.add(new CharacterCard("Mrs. Peacock", PeacockIcon, ImageLoader.GetImage("Peacock")));
        characterCards.add(new CharacterCard("Prof. Plum", PlumIcon, ImageLoader.GetImage("Plum")));

        // Generate weapon cards
        weaponCards.add(new WeaponCard("Candlestick", CandlestickIcon, ImageLoader.GetImage("Candlestick")));
        weaponCards.add(new WeaponCard("Rope", RopeIcon, ImageLoader.GetImage("Rope")));
        weaponCards.add(new WeaponCard("Revolver", RevolverIcon, ImageLoader.GetImage("Revolver")));
        weaponCards.add(new WeaponCard("LeadPipe", LeadPipeIcon, ImageLoader.GetImage("LeadPipe")));
        weaponCards.add(new WeaponCard("Dagger", DaggerIcon, ImageLoader.GetImage("Dagger")));
        weaponCards.add(new WeaponCard("Spanner", SpannerIcon, ImageLoader.GetImage("Spanner")));

        // Generate room cards
        roomCards.add(new RoomCard("Library", LibraryIcon, ImageLoader.GetImage("Library")));
        roomCards.add(new RoomCard("Conservatory", ConservatoryIcon, ImageLoader.GetImage("Conservatory")));
        roomCards.add(new RoomCard("Kitchen", KitchenIcon, ImageLoader.GetImage("Kitchen")));
        roomCards.add(new RoomCard("Study", StudyIcon, ImageLoader.GetImage("Study")));
        roomCards.add(new RoomCard("Hall", HallIcon, ImageLoader.GetImage("Hall")));
        roomCards.add(new RoomCard("BallRoom", BallRoomIcon, ImageLoader.GetImage("BallRoom")));
        roomCards.add(new RoomCard("DiningRoom", DiningRoomIcon, ImageLoader.GetImage("DiningRoom")));
        roomCards.add(new RoomCard("Lounge", LoungeIcon, ImageLoader.GetImage("Lounge")));
        roomCards.add(new RoomCard("Billiard Model.Room", BilliardRoomIcon, ImageLoader.GetImage("Billiard")));

        characterArrayList.add(Scarlett);
        characterArrayList.add(Mustard);
        characterArrayList.add(White);
        characterArrayList.add(Green);
        characterArrayList.add(Peacock);
        characterArrayList.add(Plum);

        for (Card card : deckOfCards) {
            cardHashMap.put(card.toString(), card);
        }

    }

    public static ImageIcon GetIcon(String imageToGrab){
        switch (imageToGrab){
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
            case "Scarlett":
                return ScarlettIcon;
            case "Mustard":
                return MustardIcon;
            case "White":
                return WhiteIcon;
            case "Green":
                return GreenIcon;
            case "Peacock":
                return PeacockIcon;
            case "Plum":
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
            case "Billiard":
                return BilliardRoomIcon;
            case "Kitchen":
                return KitchenIcon;
            case "BallRoom":
                return BallRoomIcon;
            case "DiningRoom":
                return DiningRoomIcon;
            default:
                return CardPlaceholderIcon;
        }
    }

    public Board() {
    }

    private String[][] boardLayoutArray = new String[][]

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


    public String[][] getBoardLayoutArray() {
        return boardLayoutArray;
    }

    public void setBoardLayoutArray(String[][] boardLayoutArray) {
        this.boardLayoutArray = boardLayoutArray;
    }

    public static String[][] getOriginalBoardLayoutArray() {
        return originalBoardLayoutArray;
    }


}
