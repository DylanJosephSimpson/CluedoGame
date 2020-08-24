import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerCreationMenu extends JFrame {

    private String playerName;
    private String assignedCharacter;
    private List<Player> players = new ArrayList<>();

    public PlayerCreationMenu(String title) {
        int count = 1; // Player number
        JPanel playerSelection = new JPanel();

        // 6 character options
        String charOne = "Miss Scarlett";
        String charTwo = "Colonel Mustard";
        String charThree = "Mrs. White";
        String charFour = "Mr. Green";
        String charFive = "Mrs. Peacock";
        String charSix = "Professor Plum";
        JRadioButton optOne = new JRadioButton(charOne); //TODO: add listeners to each button
        JRadioButton optTwo = new JRadioButton(charTwo);
        JRadioButton optThree = new JRadioButton(charThree);
        JRadioButton optFour = new JRadioButton(charFour);
        JRadioButton optFive = new JRadioButton(charFive);
        JRadioButton optSix = new JRadioButton(charSix);
        //todo the following will need to be assigning to characters rather than strings. I have changed this because characters were being initialised when they already have been. The initialisation of character can not happen here because not all characters are being used by players.
        optOne.addActionListener(e -> {
            if (optOne.isSelected()){
                assignedCharacter = charOne;
            }
        });
        optTwo.addActionListener(e -> {
            if (optTwo.isSelected()){
                assignedCharacter = charTwo;
            }
        });
        optThree.addActionListener(e -> {
            if (optThree.isSelected()){
                assignedCharacter = charThree;
            }
        });
        optFour.addActionListener(e -> {
            if (optFour.isSelected()){
                assignedCharacter = charFour;
            }
        });
        optFive.addActionListener(e -> {
            if (optFive.isSelected()){
                assignedCharacter = charFive;
            }
        });
        optSix.addActionListener(e -> {
            if (optSix.isSelected()){
                assignedCharacter = charSix;
            }
        });


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


        JTextField enterPlayerName = new JTextField();
        enterPlayerName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               playerName = enterPlayerName.getText();
            }
        });
        // this forces an action event to fire on every key press, so the
        // user doesn't need to hit enter for results.
        enterPlayerName.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                // don't fire an event on backspace or delete
                if (e.getKeyCode() == 8 || e.getKeyCode() == 127)
                    return;
                enterPlayerName.postActionEvent();
            }
        });
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
        nextPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //players.add(new Player(playerName, assignedCharacter));
            }
        });
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
