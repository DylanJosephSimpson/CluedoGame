package View;

import Model.*;
import Model.Board;
import javax.swing.*;
import java.awt.*;

public class SuggestionSetup {

    private JPanel MakeSuggestionWindow;

    String[] Weapon = new String[]{"Candlestick", "Dagger", "LeadPipe", "Revolver", "Rope", "Spanner"};
    private JLabel WeaponBoxDesc = new JLabel("Weapon Choice");
    private JComboBox<String> WeaponSelection = new JComboBox<>(Weapon);

    String[] Character =  new String[]{"Miss. Scarlett", "Col. Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Prof. Plum"};
    private JLabel CharacterBoxDesc = new JLabel("Character Choice");
    private JComboBox<String> CharacterSelection  = new JComboBox<>(Character);

    public SuggestionSetup(Player player){

        MakeSuggestionWindow = new JPanel();
        GridLayout layout = new GridLayout(3, 2);
        MakeSuggestionWindow.setLayout(layout);
        MakeSuggestionWindow.add(WeaponBoxDesc);
        MakeSuggestionWindow.add(WeaponSelection);
        MakeSuggestionWindow.add(CharacterBoxDesc);
        MakeSuggestionWindow.add(CharacterSelection);

        int result = JOptionPane.showConfirmDialog(null, MakeSuggestionWindow,
                "Make an Accusation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            //Making an accusation using the selected Cards from the window
            Suggestion currentSuggetion = new Suggestion(
                    Board.getCharacterFromString((String) CharacterSelection.getSelectedItem()),
                    Board.getWeaponFromString( (String) WeaponSelection.getSelectedItem()),
                    Player.findPlayerRoom(player.getAssignedCharacter().currentTile.getX(), player.getAssignedCharacter().currentTile.getY())
            );

            currentSuggetion.moveItems();

            System.out.println(WeaponSelection.getSelectedItem() + "\n" + CharacterSelection.getSelectedItem() + "\n" + player.findPlayerRoom(player.getAssignedCharacter().currentTile.getX(), player.getAssignedCharacter().currentTile.getY() ) + "\n");
        }
    }
}
