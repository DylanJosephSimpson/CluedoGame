import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {

    public static void main(String[] args) {

        // Create the Frame by making a new CluedoGui
        JFrame frame = new CluedoGUI("Cluedo Game");
        frame.setSize(720, 480);
        frame.setVisible(true);

    }

}
