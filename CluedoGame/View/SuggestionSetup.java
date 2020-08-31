package View;

import Model.*;
import Model.Board;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SuggestionSetup {

    private JPanel MakeSuggestionWindow;

    //The two Combo Boxes needed for suggestions
    String[] Weapon = new String[]{"Candlestick", "Dagger", "LeadPipe", "Revolver", "Rope", "Spanner"};
    private JLabel WeaponBoxDesc = new JLabel("Weapon Choice");
    private JComboBox<String> WeaponSelection = new JComboBox<>(Weapon);

    String[] Character =  new String[]{"Miss. Scarlett", "Col. Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Prof. Plum"};
    private JLabel CharacterBoxDesc = new JLabel("Character Choice");
    private JComboBox<String> CharacterSelection  = new JComboBox<>(Character);

    /**
     * Constructor for the Suggestion setup
     * @param player the player who is making the suggestion
     * @throws IOException if there are errors pertaining to input or output
     */
    public SuggestionSetup(Player player) throws IOException {

        MakeSuggestionWindow = new JPanel();
        GridLayout layout = new GridLayout(3, 2);
        MakeSuggestionWindow.setLayout(layout);
        MakeSuggestionWindow.add(WeaponBoxDesc);
        MakeSuggestionWindow.add(WeaponSelection);
        MakeSuggestionWindow.add(CharacterBoxDesc);
        MakeSuggestionWindow.add(CharacterSelection);

        //Getting the input from the player
        int result = JOptionPane.showConfirmDialog(null, MakeSuggestionWindow,
                "Make a Suggestion", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            //Making an accusation using the selected Cards from the window
            Suggestion currentSuggetion = new Suggestion(
                    Board.getCharacterFromString((String) CharacterSelection.getSelectedItem()),
                    Board.getWeaponFromString( (String) WeaponSelection.getSelectedItem()),
                    Player.findRoom(player.getAssignedCharacter().currentTile.getCol(), player.getAssignedCharacter().currentTile.getRow())
            );

            player.makeSuggestion(Board.getCardHashMap().get(CharacterSelection.getSelectedItem().toString())
                    ,Board.getCardHashMap().get(WeaponSelection.getSelectedItem().toString()));

            currentSuggetion.moveItems();
        }
    }
}
