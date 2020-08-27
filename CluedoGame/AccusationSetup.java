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

    String[] Room = new String[]{"Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};
    private JLabel RoomBoxDesc = new JLabel("Weapon Choice");
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
            player.makeAccusation( Board.cardHashMap.get(CharacterSelection.getSelectedItem().toString())
                    ,Board.cardHashMap.get(WeaponSelection.getSelectedItem().toString()), Board.cardHashMap.get(RoomSelection.getSelectedItem().toString()));


            
            System.out.println(WeaponSelection.getSelectedItem() + "\n" + CharacterSelection.getSelectedItem() + "\n"
            + RoomSelection.getSelectedItem() + "\n");
        }
    }



}
