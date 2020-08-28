import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The board class is responsible for drawing and keeping track of
 * the components that make up the board.
 */
public class Board {
    static ArrayList<Card> deckOfCards = new ArrayList<>();
    static HashSet<Card> envelope = new HashSet<>();
    static HashMap<String, Card> cardHashMap = new HashMap<>();


    public static ArrayList<Character> getCharacterArrayList() {
        return characterArrayList;
    }

    public static Character getCharacter(int index) {
        return characterArrayList.get(index);
    }

    static ArrayList<Character> characterArrayList = new ArrayList<Character>();


    private static Character Scarlett = new Character("Miss. Scarlett", 210, 720);
    private static Character Mustard = new Character("Col. Mustard", 0, 510);
    private static Character White = new Character("Mrs. White", 270, 0);
    private static Character Green = new Character("Mr. Green", 420, 0);
    private static Character Peacock = new Character("Mrs. Peacock", 690, 180);
    private static Character Plum = new Character("Prof. Plum", 690, 570);

    public static void setup() {
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