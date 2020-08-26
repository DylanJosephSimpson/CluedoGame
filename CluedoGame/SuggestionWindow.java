import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    private Suggestion createdSuggestion; //= new Suggestion(new Character("dummy", 0, 0), new Weapon("dummy"), new Room("dummy"));
    private Character suggestedCharacter;
    private Weapon suggestedWeapon;
    private Room enteredRoom;

    /**
     * @param title Title of the window
     * @param enteredRoom Room the suggestion was being made in
     */
    public SuggestionWindow(String title, Room enteredRoom){
        this.enteredRoom = enteredRoom;

        this.container.setLayout(cl);

        addFirstPanel(container);

        this.setTitle(title);
        this.add(container);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    /**
     * Adds the suspect panel and results of the suggestions panel to the main container
     * @param container
     */
    public void addFirstPanel(JPanel container){
        //Default values
        suggestedCharacter = Board.characterArrayList.get(0); // Default is Miss Scarlett
        suggestedWeapon = CluedoGUI.allWeapons.get(0); // Default is Candlestick


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // --------- Suggesting a suspect ---------
        JLabel suspectLabel = new JLabel("I suggest the crime was committed in the " + enteredRoom.toString() + " by...");

        String[] listOfSuspects = {"Miss. Scarlett", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
        JComboBox<String> suspectsComboBox = new JComboBox<>(listOfSuspects);
        suspectsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Character c : Board.characterArrayList){
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
                createdSuggestion = new Suggestion(suggestedCharacter, suggestedWeapon, enteredRoom);
                createdSuggestion.moveItems();
                addSecondPanel(container, createdSuggestion);
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
     * Aftermath of the suggestion
     * @param container
     */
    public void addSecondPanel(JPanel container, Suggestion suggestion){
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        List<JLabel> results = new ArrayList<>();

        Character currentPlayer = enteredRoom.getCharactersInRoom().get(enteredRoom.getCharactersInRoom().size() - 2);
        for (Player player : Player.playerList) {
            List<Card> cardMatches = new ArrayList<>(); //list of cards that match the suggestion that was made
            //cycle through all players except for the player that made the suggestion
            if (!player.getAssignedCharacter().toString().equals(currentPlayer.toString())) {
                for (Card card : player.getHand()) {
                    //Counting how many cards in this player's hand matches the suggestion made
                    if (card.toString().equals(suggestion.getCharacter().toString()) ||
                            card.toString().equals(suggestion.getWeapon().toString()) ||
                            card.toString().equals(suggestion.getRoom().toString())) {
                        cardMatches.add(card);
                    }
                }

                //Now add all the results to the main pile
                if (cardMatches.size() == 0) {
                    results.add(new JLabel(player.getAssignedCharacter() + " has 0 cards that match your suggestion"));
                } else {
                    //chooose one card to show them
                    Card cardToShow;
                    String[] cardsToPick = new String[cardMatches.size()];
                    //populating the options
                    for (int i = 0; i < cardMatches.size(); i++){
                        cardsToPick[i] = cardMatches.get(i).toString();
                    }
                    Object selected = JOptionPane.showInputDialog(null,
                            "If you are not " + player.getAssignedCharacter() + " please look away! " + player.getAssignedCharacter() + " choose one of these cards to show to the suggestee:",
                            "Select a card",
                            JOptionPane.DEFAULT_OPTION,
                            null,
                            cardsToPick,
                            "0");
                    if ( selected != null ){//null if the user cancels
                        String selectedString = selected.toString();
                        System.out.println("verify ====" + selectedString);
                        results.add(new JLabel(player.getAssignedCharacter() + " rejects your suggestion with the card: \"" + selectedString + "\""));
                    }else{
                        System.out.println("User cancelled");
                    }
                }
            }
        }
        //Add all of the results found
        for (JLabel label : results) {
            resultsPanel.add(label);
        }
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        resultsPanel.add(okButton);
        resultsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(resultsPanel, "2");
    }

}
