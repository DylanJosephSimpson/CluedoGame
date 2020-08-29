package View;

import Model.*;
import Model.Board;
import javax.swing.*;
import java.awt.*;

public class AccusationSetup {

    private JPanel MakeAccusationWindow;

    String[] Weapon = new String[]{"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
    private JLabel WeaponBoxDesc = new JLabel("Weapon Choice");
    private JComboBox<String> WeaponSelection = new JComboBox<>(Weapon);

    String[] Character =  new String[]{"Miss Scarlett", "Colonel Mustard", "Mr. Green", "Ms. White", "Mrs. Peacock", "Professor Plum"};
    private JLabel CharacterBoxDesc = new JLabel("Character Choice");
    private JComboBox<String> CharacterSelection  = new JComboBox<>(Character);

    String[] Room = new String[]{"Kitchen", "Ballroom", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};
    private JLabel RoomBoxDesc = new JLabel("Room Choice");
    private JComboBox<String> RoomSelection  = new JComboBox<>(Room);

    public AccusationSetup(Player player){

        MakeAccusationWindow = new JPanel();

        GridLayout layout = new GridLayout(3, 2);

        MakeAccusationWindow.setLayout(layout);

        MakeAccusationWindow.add(WeaponBoxDesc);
        MakeAccusationWindow.add(WeaponSelection);
        MakeAccusationWindow.add(CharacterBoxDesc);
        MakeAccusationWindow.add(CharacterSelection);
        MakeAccusationWindow.add(RoomBoxDesc);
        MakeAccusationWindow.add(RoomSelection);

        int result = JOptionPane.showConfirmDialog(null, MakeAccusationWindow,
                "Make an Accusation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            //Making an accusation using the selected Cards from the window
            System.out.println("THE SIZE OF THIS MAP IS " + Board.getCardHashMap().size());
            System.out.println("Character" + CharacterSelection.getSelectedItem());
            System.out.println("WEapon" + WeaponSelection.getSelectedItem());

            System.out.println("Room" + RoomSelection.getSelectedItem());

            player.makeAccusation((CharacterCard)Board.getCardHashMap().get(CharacterSelection.getSelectedItem())
                    ,(WeaponCard)Board.getCardHashMap().get(WeaponSelection.getSelectedItem()), (RoomCard) Board.getCardHashMap().get(RoomSelection.getSelectedItem()));

        }
    }



}
