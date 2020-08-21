import javax.swing.*;
import java.awt.*;

public class PlayerCreationMenu extends JFrame {

    public PlayerCreationMenu(String title) {
        JPanel playerSelection = new JPanel();


        // 6 character options
        JRadioButton optOne = new JRadioButton("Miss Scarlett"); //TODO: add listeners to each button
        JRadioButton optTwo = new JRadioButton("Colonel Mustard");
        JRadioButton optThree = new JRadioButton("Mrs. White");
        JRadioButton optFour = new JRadioButton("Mr. Green");
        JRadioButton optFive = new JRadioButton("Mrs. Peacock");
        JRadioButton optSix = new JRadioButton("Professor Plum");

        // Making sure only 1 is selectable
        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(optOne);
        buttonGroup.add(optTwo);
        buttonGroup.add(optThree);
        buttonGroup.add(optFour);
        buttonGroup.add(optFive);
        buttonGroup.add(optSix);

        JTextField enterPlayerName = new JTextField(); //TODO: Add listener here - Iqbal
        playerSelection.add(enterPlayerName);
        playerSelection.add(optOne);
        playerSelection.add(optTwo);
        playerSelection.add(optThree);
        playerSelection.add(optFour);
        playerSelection.add(optFive);
        playerSelection.add(optSix);


        setLayout(new GridBagLayout());
        playerSelection.setLayout(new BoxLayout(playerSelection, BoxLayout.Y_AXIS));    // Each radiobutton occurring on a new line
        add(playerSelection, new GridBagConstraints());      // Ensures everything is vertically and horizontally centre on the page
        setVisible(true);
    }
}
