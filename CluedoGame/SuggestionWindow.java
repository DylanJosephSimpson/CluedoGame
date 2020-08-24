import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A suggestion window is prompted the first time a player enters a room.
 * The player is given an option on whether or not they would like to make a suggestion.
 */
public class SuggestionWindow extends JDialog {

    // Constants
    private int WINDOW_WIDTH = 600;
    private int WINDOW_HEIGHT = 300;

    // GUI fields
    private CardLayout cl = new CardLayout();
    private JPanel container = new JPanel();

    // Game fields
    private Suggestion createdSuggestion;
    private Character suggestedCharacter = CluedoGUI.allCharacters.get(0); // Default is Miss Scarlett
    private Weapon suggestedWeapon = CluedoGUI.allWeapons.get(0); // Default is Candlestick

    public SuggestionWindow(CluedoGUI mainClient){
        container.setLayout(cl);

        addFirstPanel(container);
        addSecondPanel(container);

        this.setTitle("You have entered a room, make a suggestion?");
        this.add(container);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        //this.setLocationRelativeTo(mainClient); //centres the dialog to the middle of CluedoGUI
    }

    /**
     * Adds the suspect panel to the main container
     * @param container
     */
    public void addFirstPanel(JPanel container){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Room currentRoom = new Room("Lounge"); //FIXME: Praveen link this to the actual room the suggestion was made in. Currently a dummy room.
        // --------- Suggesting a suspect ---------
        JLabel suspectLabel = new JLabel("I suggest the crime was committed in the " + currentRoom.toString() + " by...");

        String[] listOfSuspects = {"Miss. Scarlett", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
        JComboBox<String> suspectsComboBox = new JComboBox<>(listOfSuspects);
        suspectsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Character c : CluedoGUI.allCharacters){
                    if (c.toString().equals(suspectsComboBox.getSelectedItem())){
                        suggestedCharacter = c;
                    }
                }
            }
        });
        suspectsComboBox.setMaximumSize(suspectsComboBox.getPreferredSize());
        JPanel suspectPanel = new JPanel();
        suspectPanel.setLayout(new BoxLayout(suspectPanel, BoxLayout.X_AXIS));
        suspectPanel.add(suspectLabel);
        suspectPanel.add(suspectsComboBox);

        // --------- Suggesting a weapon ---------
        JLabel weaponLabel = new JLabel("With the use of a...");

        String[] listOfWeapons = {"Candlestick", "Dagger", "LeadPipe", "Revolver", "Rope", "Spanner"};
        JComboBox<String> weaponsComboBox = new JComboBox<>(listOfWeapons);
        weaponsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Weapon w : CluedoGUI.allWeapons){
                    if (w.toString().equals(weaponsComboBox.getSelectedItem())){
                        suggestedWeapon = w;
                    }
                }
            }
        });
        weaponsComboBox.setMaximumSize(weaponsComboBox.getPreferredSize());
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.X_AXIS));
        weaponPanel.add(weaponLabel);
        weaponPanel.add(weaponsComboBox);

        // Cancel and Next buttons
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //close the window
            }
        });
        JButton nextButton = new JButton("Submit Suggestion");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createdSuggestion = new Suggestion(suggestedCharacter, suggestedWeapon, currentRoom);
                createdSuggestion.moveItems();
                System.out.println(createdSuggestion.toString()); //TODO: remove this later
                cl.show(container, "2");
            }
        });

        // Add the buttons to their own panel to separate the line up
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(WINDOW_WIDTH/2, 20)));
        buttonPanel.add(nextButton);

        // Add all mini panels to the main panel
        mainPanel.add(Box.createRigidArea(new Dimension(0, WINDOW_HEIGHT/4))); // Adds vertical spacing before displaying the label
        mainPanel.add(suspectPanel);
        mainPanel.add(weaponPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, WINDOW_HEIGHT/3))); // Adds vertical spacing before displaying the buttons
        mainPanel.add(buttonPanel);

        container.add(mainPanel, "1");
    }

    /**
     * Adds the weapon panel to the main container
     * @param container
     */
    public void addSecondPanel(JPanel container){
        JPanel weaponPanel = new JPanel();


        container.add(weaponPanel, "2");
    }


    public static void main(String[] args) {
        SuggestionWindow test = new SuggestionWindow(new CluedoGUI("TEST", null));
    }
}
