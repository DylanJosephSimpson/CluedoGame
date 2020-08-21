import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {


    public static void main(String[] args) {
        int GAME_WIDTH = 720;
        int GAME_HEIGHT = 885;

        boolean setupIsDone = true; //FIXME: set this false once player creation is done

        JFrame setupFrame = new PlayerCreationMenu("Cluedo: How many players?");
        setupFrame.setSize(GAME_WIDTH, GAME_HEIGHT/2);
        //Actual game starts here

        if (setupIsDone) {
            // Create the Frame by making a new CluedoGui
            Board b = new Board();
            JFrame frame = new CluedoGUI("Cluedo Game",b);
            frame.setSize(GAME_WIDTH, GAME_HEIGHT);
            frame.setVisible(true);
        }

    }

}
