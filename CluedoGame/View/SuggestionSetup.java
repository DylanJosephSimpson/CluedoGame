package View;

import Model.*;
import Model.Board;
import javax.swing.*;
import java.awt.*;

public class SuggestionSetup {

    private JPanel MakeSuggestionWindow;

    String[] Weapon = new String[]{"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
    private JLabel WeaponBoxDesc = new JLabel("Weapon Choice");
    private JComboBox<String> WeaponSelection = new JComboBox<>(Weapon);

    String[] Character =  new String[]{"Miss Scarlett", "Colonel Mustard", "Mr. Green", "Ms. White", "Mrs. Peacock", "Professor Plum"};
    private JLabel CharacterBoxDesc = new JLabel("Character Choice");
    private JComboBox<String> CharacterSelection  = new JComboBox<>(Character);

    String[] Room = new String[]{"Kitchen", "Ballroom", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};
    private JLabel RoomBoxDesc = new JLabel("Weapon Choice");
    private JComboBox<String> RoomSelection  = new JComboBox<>(Room);

    public SuggestionSetup(Player player){

        MakeSuggestionWindow = new JPanel();
        GridLayout layout = new GridLayout(3, 2);
        MakeSuggestionWindow.setLayout(layout);
        MakeSuggestionWindow.add(WeaponBoxDesc);
        MakeSuggestionWindow.add(WeaponSelection);
        MakeSuggestionWindow.add(CharacterBoxDesc);
        MakeSuggestionWindow.add(CharacterSelection);
        MakeSuggestionWindow.add(RoomBoxDesc);
        MakeSuggestionWindow.add(RoomSelection);

        int result = JOptionPane.showConfirmDialog(null, MakeSuggestionWindow,
                "Make an Accusation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            //Making an accusation using the selected Cards from the window
            player.makeSuggestion(
                    (CharacterCard) Board.getCardHashMap().get(CharacterSelection.getSelectedItem()),
                    (WeaponCard) Board.getCardHashMap().get(WeaponSelection.getSelectedItem()),
                    (RoomCard) Board.getCardHashMap().get(RoomSelection.getSelectedItem()))
            ;
            System.out.println(WeaponSelection.getSelectedItem() + "\n" + CharacterSelection.getSelectedItem() + "\n" + RoomSelection.getSelectedItem() + "\n");
        }
    }
}
