package Tests;

import Model.Board;
import Model.Player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * JUnit tests testing the player movement
 */
public class MovementTests {

    // ===========================================
    // Valid movements
    // ===========================================

    //Scarlett moving up 2 spaces
    @Test public void test01(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH"));
        List<PlayerMovement> input = Arrays.asList(scarlett);

        String expected =
                "---------G----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "M                --yyyy-\n" +
                        "-        --@@--   ------\n" +
                        "-----@-  -hhhh-        P\n" +
                        "-lllll-  -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-S -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    //Scarlett and mustard moving using north and east
    @Test public void test02(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "NORTH"));
        PlayerMovement mustard = new PlayerMovement("mustard", Arrays.asList("EAST", "EAST", "EAST", "NORTH"));
        List<PlayerMovement> input = Arrays.asList(scarlett, mustard);

        String expected =
                "---------G----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-  M      -----  @yyyyy-\n" +
                        "                 --yyyy-\n" +
                        "-        --@@--   ------\n" +
                        "-----@-  -hhhh-        P\n" +
                        "-lllll-S -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    //Scarlett and mustard moving using north, east, south
    @Test public void test03(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "NORTH", "NORTH", "NORTH", "EAST", "SOUTH"));
        PlayerMovement mustard = new PlayerMovement("mustard", Arrays.asList("EAST", "EAST", "EAST", "EAST", "SOUTH"));
        List<PlayerMovement> input = Arrays.asList(scarlett, mustard);

        String expected =
                "---------G----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "                 --yyyy-\n" +
                        "-   M    --@@--   ------\n" +
                        "-----@- S-hhhh-        P\n" +
                        "-lllll-  -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    //Scarlett, mustard and white moving using north, east, south, west
    @Test public void test04(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "NORTH", "NORTH", "NORTH", "EAST", "SOUTH"));
        PlayerMovement mustard = new PlayerMovement("mustard", Arrays.asList("EAST", "EAST", "EAST", "EAST", "SOUTH"));
        PlayerMovement white = new PlayerMovement("white", Arrays.asList("SOUTH", "WEST", "WEST", "SOUTH", "SOUTH"));
        List<PlayerMovement> input = Arrays.asList(scarlett, mustard, white);

        String expected =
                "--------- ----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk- G-bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "                 --yyyy-\n" +
                        "-   M    --@@--   ------\n" +
                        "-----@- S-hhhh-        P\n" +
                        "-lllll-  -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    //All players making a move
    @Test public void test05(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "NORTH", "NORTH", "NORTH", "EAST", "SOUTH"));
        PlayerMovement mustard = new PlayerMovement("mustard", Arrays.asList("EAST", "EAST", "EAST", "EAST", "SOUTH"));
        PlayerMovement white = new PlayerMovement("white", Arrays.asList("SOUTH", "WEST", "WEST", "SOUTH", "SOUTH"));
        PlayerMovement green = new PlayerMovement("green", Arrays.asList("SOUTH", "EAST", "EAST", "SOUTH", "SOUTH"));
        PlayerMovement peacock = new PlayerMovement("peacock", Arrays.asList("WEST", "WEST", "SOUTH", "WEST"));
        PlayerMovement plum = new PlayerMovement("plum", Arrays.asList("WEST", "WEST", "SOUTH", "WEST", "WEST", "NORTH"));
        List<PlayerMovement> input = Arrays.asList(scarlett, mustard, white, green, peacock, plum);

        String expected =
                "--------- ---- ---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk- G-bbbbbb-W -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-        \n" +
                        "-       -@----@-    C  -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "                 --yyyy-\n" +
                        "-   M    --@@--   ------\n" +
                        "-----@- S-hhhh-    P    \n" +
                        "-lllll-  -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    // ===========================================
    // Invalid movements
    // ===========================================

    //Scarlett walking into a wall
    @Test public void test06(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "NORTH", "EAST"));

        List<PlayerMovement> input = Arrays.asList(scarlett);

        String expected =
                "---------G----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "M                --yyyy-\n" +
                        "-        --@@--   ------\n" +
                        "-----@-  -hhhh-        P\n" +
                        "-lllll- S-hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    //Scarlett walking into a previously traversed space
    @Test public void test07(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "SOUTH"));

        List<PlayerMovement> input = Arrays.asList(scarlett);

        String expected =
                "---------G----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "M                --yyyy-\n" +
                        "-        --@@--   ------\n" +
                        "-----@-  -hhhh-        P\n" +
                        "-lllll-  -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-S -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    //Scarlett walking into a previously traversed space and mustard moving off the board
    @Test public void test08(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "SOUTH"));
        PlayerMovement mustard = new PlayerMovement("mustard", Arrays.asList("EAST", "EAST", "NORTH", "SOUTH"));

        List<PlayerMovement> input = Arrays.asList(scarlett, mustard);

        String expected =
                "---------G----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "  M              --yyyy-\n" +
                        "-        --@@--   ------\n" +
                        "-----@-  -hhhh-        P\n" +
                        "-lllll-  -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-S -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    //Scarlett walking into mustard
    @Test public void test09(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "SOUTH"));
        PlayerMovement mustard = new PlayerMovement("mustard", Arrays.asList("EAST", "EAST", "NORTH", "SOUTH"));
        PlayerMovement scarlett2 = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "NORTH", "NORTH" , "WEST", "WEST", "WEST"));

        List<PlayerMovement> input = Arrays.asList(scarlett, mustard, scarlett2);

        String expected =
                "---------G----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "  M S            --yyyy-\n" +
                        "-        --@@--   ------\n" +
                        "-----@-  -hhhh-        P\n" +
                        "-lllll-  -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    //Scarlett walking into mustard, then mustard walking into scarlett
    @Test public void test10(){

        PlayerMovement scarlett = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "SOUTH"));
        PlayerMovement mustard = new PlayerMovement("mustard", Arrays.asList("EAST", "EAST", "NORTH", "SOUTH"));
        PlayerMovement scarlett2 = new PlayerMovement("scarlett", Arrays.asList("NORTH", "NORTH", "NORTH", "NORTH", "NORTH" , "WEST", "WEST", "WEST"));
        PlayerMovement mustard2 = new PlayerMovement("mustard", Arrays.asList("SOUTH", "NORTH"));


        List<PlayerMovement> input = Arrays.asList(scarlett, mustard, scarlett2, mustard2);

        String expected =
                "---------G----W---------\n" +
                        "-------   ----   -------\n" +
                        "-kkkk-  ---bb---  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  -cccc-\n" +
                        "-kkkk-  -bbbbbb-  @cccc-\n" +
                        "-kkkk-  @bbbbbb@  ------\n" +
                        "----@-  -bbbbbb-       C\n" +
                        "-       -@----@-       -\n" +
                        "-                 ------\n" +
                        "-----             @iiii-\n" +
                        "-ddd----  -----   -iiii-\n" +
                        "-dddddd-  -eee-   -iiii-\n" +
                        "-dddddd@  -eee-   ----@-\n" +
                        "-dddddd-  -eee-        -\n" +
                        "-dddddd-  -eee-   --@---\n" +
                        "------@-  -eee-  --yyyy-\n" +
                        "-         -----  @yyyyy-\n" +
                        "  M S            --yyyy-\n" +
                        "-        --@@--   ------\n" +
                        "-----@-  -hhhh-        P\n" +
                        "-lllll-  -hhhh@        -\n" +
                        "-lllll-  -hhhh-  -@-----\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "-lllll-  -hhhh-  -sssss-\n" +
                        "------- ----------------\n";


        String actual = simulateMove(input);
        assertEquals(expected, actual);
    }

    /**
     * Creates a simulation of the game board applying the movements supplied
     * @param moves List of player movements
     * @return Board of the game after applying the moves
     */
    public String simulateMove(List<PlayerMovement> moves){
        Board b = new Board();
        b.setup();

        //Maximum amount of players in the game
        Player scarlett = new Player("flatbench", Board.getCharacter(0));
        Player mustard = new Player("comp102god", Board.getCharacter(1));
        Player white = new Player("shoulderpain", Board.getCharacter(2));
        Player green = new Player("iqballin", Board.getCharacter(3));
        Player peacock = new Player("mvp", Board.getCharacter(4));
        Player plum = new Player("10plates", Board.getCharacter(5));


        Player.setPlayerList(Arrays.asList(scarlett, mustard, white, green, peacock, plum));

        //Applying the movement
        for (PlayerMovement player : moves){
            if (player.getCharacterName().equals("scarlett")){
                for (String direction : player.getMovements()){
                    scarlett.move(direction);
                }
            }
            else if (player.getCharacterName().equals("mustard")){
                for (String direction : player.getMovements()){
                    mustard.move(direction);
                }
            }
            else if (player.getCharacterName().equals("white")){
                for (String direction : player.getMovements()){
                    white.move(direction);
                }
            }
            else if (player.getCharacterName().equals("green")){
                for (String direction : player.getMovements()){
                    green.move(direction);
                }
            }
            else if (player.getCharacterName().equals("peacock")){
                for (String direction : player.getMovements()){
                    peacock.move(direction);
                }
            }
            else if (player.getCharacterName().equals("plum")){
                for (String direction : player.getMovements()){
                    plum.move(direction);
                }
            }
            else {
                throw new IllegalArgumentException("Movement simulation failed: The character " + player.getCharacterName() + " was not found.");
            }
        }
        Player.setPlayerList(Collections.emptyList());
        return b.toString();
    }

}

/**
 * Helper class used to easily store information regarding a simulation of a player's movements
 */
class PlayerMovement {

    /**
     * scarlett, mustard, white, green, peacock or plum
     */
    private String characterName;

    /**
     * List of movements by this player
     */
    private List<String> movements;

    public PlayerMovement(String characterName, List<String> movements) {
        this.characterName = characterName;
        this.movements = movements;
    }

    /**
     * Getter for character name
     * @return this player's character name
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Getter for the player's movements
     * @return this player's movements
     */
    public List<String> getMovements() {
        return movements;
    }
}
