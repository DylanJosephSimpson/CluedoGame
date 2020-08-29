package View;

import Controller.SuggestionWindowController;
import Model.*;
import Model.Board;
import Model.Card;
import Model.Character;
import Model.Suggestion;
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
    private static CardLayout cl = new CardLayout();
    private static JPanel container = new JPanel();

    // Game fields
    private static JComboBox<String> weaponsComboBox;
    private static JComboBox<String> suspectsComboBox;

    private static JButton cancelButton;
    private static JButton nextButton;
    private static JButton okButton;
    private static Character suggestedCharacter;
    private static Weapon suggestedWeapon;
    private static Room enteredRoom;

    /**
     * @param title Title of the window
     * @param enteredRoom Model.Room the suggestion was being made in
     */
    public SuggestionWindow(String title, Room enteredRoom){
        this.enteredRoom = enteredRoom;
        this.container.setLayout(cl);
        addFirstPanel(container);
        this.setTitle(title);
        this.add(container);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        new SuggestionWindowController();
    }

    /**
     * Adds the suspect panel and results of the suggestions panel to the main container
     * @param container
     */
    public void addFirstPanel(JPanel container){
        //Default values
        suggestedCharacter = Board.getCharacterArrayList().get(0); // Default is Miss Scarlett
        suggestedWeapon = Board.getAllWeapons().get(0); // Default is Candlestick
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // --------- Suggesting a suspect ---------
        JLabel suspectLabel = new JLabel("I suggest the crime was committed in the " + enteredRoom.toString() + " by...");
        String[] listOfSuspects = {"Miss. Scarlett", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
        suspectsComboBox = new JComboBox<>(listOfSuspects);
        suspectsComboBox.setMaximumSize(suspectsComboBox.getPreferredSize());

        JPanel suspectPanel = new JPanel();
        suspectPanel.setLayout(new BoxLayout(suspectPanel, BoxLayout.X_AXIS));
        suspectPanel.add(suspectLabel);
        suspectPanel.add(suspectsComboBox);

        // --------- Suggesting a weapon ---------
        JLabel weaponLabel = new JLabel("With the use of a...");
        String[] listOfWeapons = {"Candlestick", "Dagger", "LeadPipe", "Revolver", "Rope", "Spanner"};
        weaponsComboBox = new JComboBox<>(listOfWeapons);
        weaponsComboBox.setMaximumSize(weaponsComboBox.getPreferredSize());
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.X_AXIS));
        weaponPanel.add(weaponLabel);
        weaponPanel.add(weaponsComboBox);

        // Cancel and Next buttons
        cancelButton = new JButton("Cancel");

        JButton nextButton = new JButton("Submit Suggestion");

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
    public static void addSecondPanel(JPanel container, Suggestion suggestion){

        JPanel resultsPanel = new JPanel();

        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        List<JLabel> results = new ArrayList<>();

        Character currentPlayer;

        if (enteredRoom.getCharactersInRoom().size() == 1){
            currentPlayer = enteredRoom.getCharactersInRoom().get(enteredRoom.getCharactersInRoom().size() - 1);
        }

        else {
            currentPlayer = enteredRoom.getCharactersInRoom().get(enteredRoom.getCharactersInRoom().size() - 2);
        }

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
                }
                else if (cardMatches.size() == 1){
                    results.add(new JLabel(player.getAssignedCharacter() + " rejects your suggestion with the card: \"" + cardMatches.get(0) + "\""));
                }
                else {
                    //chooose one card to show them
                    String[] cardsToPick = new String[cardMatches.size()];
                    //populating the options
                    for (int i = 0; i < cardMatches.size(); i++){
                        cardsToPick[i] = cardMatches.get(i).toString();
                    }
                    Object selected = JOptionPane.showInputDialog(null,
                            "If you are not " + player.getName() + " please look away! " + player.getName() + " choose one of these cards to show to the suggestee:",
                            "Select a card",
                            JOptionPane.PLAIN_MESSAGE,
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
        okButton = new JButton("Ok");
        resultsPanel.add(okButton);
        resultsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(resultsPanel, "2");
    }

    public static JComboBox<String> getWeaponsComboBox() {
        return weaponsComboBox;
    }

    public static void setSuggestedCharacter(Character suggestedCharacter) {
        suggestedCharacter = suggestedCharacter;
    }

    public static void setSuggestedWeapon(Weapon suggestedWeapon) {
        suggestedWeapon = suggestedWeapon;
    }

    public static JComboBox<String> getSuspectsComboBox() {
        return suspectsComboBox;
    }

    public static JButton getCancelButton() {
        return cancelButton;
    }

    public static JButton getNextButton() {
        return nextButton;
    }

    public static Character getSuggestedCharacter() {
        return suggestedCharacter;
    }

    public static Weapon getSuggestedWeapon() {
        return suggestedWeapon;
    }

    public static Room getEnteredRoom() {
        return enteredRoom;
    }

    public static CardLayout getCl() {
        return cl;
    }

    public static JPanel getContainer() {
        return container;
    }

    public static JButton getOkButton() {
        return okButton;
    }

}
