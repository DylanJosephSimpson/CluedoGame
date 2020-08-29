package Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import Model.Board;
import View.CluedoGUI;
import org.junit.Test;

public class CluedoTests {

    /*
     *
     */
    @Test public void valid_player_movement() {
        String input =
                "UUU\n" +
                        "";
        String output =
                "--------- ---- ---------\n"+
                        "-------   ----   -------\n"+
                        "-kkkk-  ---bb---  -cccc-\n"+
                        "-kkkk-  -bbbbbb-  -cccc-\n"+
                        "-kkkk-  -bbbbbb-  @cccc-\n"+
                        "-kkkk-  @bbbbbb@  ------\n"+
                        "----@-  -bbbbbb-        \n"+
                        "-       -@----@-       -\n"+
                        "-                 ------\n"+
                        "-----             @iiii-\n"+
                        "-ddd----  -----   -iiii-\n"+
                        "-dddddd-  -eee-   -iiii-\n"+
                        "-dddddd@  -eee-   ----@-\n"+
                        "-dddddd-  -eee-        -\n"+
                        "-dddddd-  -eee-   --@---\n"+
                        "------@-  -eee-  --yyyy-\n"+
                        "-         -----  @yyyyy-\n"+
                        "                 --yyyy-\n"+
                        "-        --@@--   ------\n"+
                        "-----@-  -hhhh-         \n"+
                        "-lllll-  -hhhh@        -\n"+
                        "-lllll-  -hhhh-  -@-----\n"+
                        "-lllll-  -hhhh-  -sssss-\n"+
                        "-lllll-  -hhhh-  -sssss-\n"+
                        "------- ----------------\n";

                        //check(input,output);
    }

//    public static String drawBoard() {
//        StringBuilder output = null;
//        for(String[] s : b.getBoardLayoutArray()){
//            for (String a : s){
//                output.append(a);
//            }
//            output.append("\n");
//        }
//        return output.toString();
//    }

    // The following provides a simple helper method for all tests.
//    public static void check(String input, String expectedOutput) {
//        try {
//            Board b = new Board();
//            CluedoGUI game = new CluedoGUI("Cluedo Game",b);
//            if (boards.isEmpty()) {
//                fail("test failed with insufficient boards on input: " + input);
//            }
//            String actualOutput = boards.get(boards.size() - 1).toString();
//            assertEquals(expectedOutput, actualOutput);
//        } catch (Exception e) {
//            fail("test failed because of exception on input: " + input);
//        }
//    }
}

