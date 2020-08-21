import javax.swing.*;
import java.awt.*;

public class PlayerCreationMenu extends JFrame {

    public PlayerCreationMenu(String title) {
        int count = 1; // Player number
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

        JLabel playerNoText = new JLabel();
        playerNoText.setFont(new Font("Trebuchet", Font.PLAIN, 16));
        playerNoText.setText("PLAYER " + count); //TODO: increase count after each player is done
        playerSelection.add(playerNoText);

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

        GridBagConstraints buttonPos = new GridBagConstraints();
        JButton nextPlayerButton = new JButton("Next");
        buttonPos.gridx = 0;
        buttonPos.gridy = 0;
        buttonPos.fill = GridBagConstraints.BOTH;
        buttonPos.weightx = 1.0;
        buttonPos.weighty = 1.0;
        playerSelection.add(Box.createGlue(), buttonPos);

        buttonPos = new GridBagConstraints(); //reset it
        buttonPos.gridx = 1;
        buttonPos.gridy = 1;
        buttonPos.fill = GridBagConstraints.NONE;
        buttonPos.weightx = 0.0;
        buttonPos.weighty = 0.0;
        playerSelection.add(nextPlayerButton, buttonPos);


    setVisible(true);
    }
}
