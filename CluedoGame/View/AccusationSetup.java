package View;

import Model.*;
import Model.Board;
import javax.swing.*;
import java.awt.*;

public class AccusationSetup {

    private JPanel MakeAccusationWindow;

    //The three combo boxes needed for accusations
    String[] Weapon = new String[]{"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
    private JLabel WeaponBoxDesc = new JLabel("Weapon Choice");
    private JComboBox<String> WeaponSelection = new JComboBox<>(Weapon);

    String[] Character =  new String[]{"Miss. Scarlett", "Col. Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Prof. Plum"};
    private JLabel CharacterBoxDesc = new JLabel("Character Choice");
    private JComboBox<String> CharacterSelection  = new JComboBox<>(Character);

    String[] Room = new String[]{"Kitchen", "Ballroom", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};
    private JLabel RoomBoxDesc = new JLabel("Room Choice");
    private JComboBox<String> RoomSelection  = new JComboBox<>(Room);

    /**
     * Constructor for accusation setup
     * @param player the player making the accusation
     */
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

        //MVC implementation to shift control
        int result = JOptionPane.showConfirmDialog(null, MakeAccusationWindow,
                "Make an Accusation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            //Making an accusation using the selected Cards from the window
            player.makeAccusation( Board.getCardHashMap().get(CharacterSelection.getSelectedItem())
                    ,Board.getCardHashMap().get(WeaponSelection.getSelectedItem()), Board.getCardHashMap().get(RoomSelection.getSelectedItem()));
        }
    }
}
