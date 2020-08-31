package View;

import Controller.*;
import Model.*;
import javax.swing.*;
import java.awt.*;

public class GameSetup {

    private final JFrame GameSetupWindow;

    /**
     * The buttons used for selection during the game setup
     */
    private final JRadioButton ScarlettButton = new JRadioButton("Scarlett");
    private final JRadioButton MustardButton = new JRadioButton("Mustard");
    private final JRadioButton WhiteButton = new JRadioButton("White");
    private final JRadioButton GreenButton = new JRadioButton("Green");
    private final JRadioButton PeacockButton = new JRadioButton("Peacock");
    private final JRadioButton PlumButton = new JRadioButton("Plum");
    private  JLabel characterInformation;

    /**
     * The game setup constructor
     * @param title the title of the Game setup
     */
    public GameSetup(String title){

        GameSetupWindow = new JFrame(title);

        GameSetupWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GameSetupWindow.setSize(new Dimension(740, 500));

        GameSetupWindow.setMinimumSize(new Dimension(720, 480));

        GameSetupWindow.setMaximumSize(new Dimension(1920, 1080));

        GameSetupWindow.getContentPane().setBackground(new Color(84, 101, 215));

        GameSetupWindow.setLayout(new GridLayout(3, 0));

        GameSetupWindow.setVisible(true);
        GameSetupWindow.add(GameInputAreaSetup());
        GameSetupWindow.add(GameOptionAreaSetup());
        GameSetupWindow.add(GameCharactersSetup());

        GameSetupWindow.pack();

    }

    /**
     * Method for setting up a Container used for the game setup
     * @return the Container for the game input area
     */
    public Container GameInputAreaSetup(){
        Container gameInputArea = new JPanel();
        gameInputArea.setBackground(Color.red);

        BorderLayout GameInputAreaLayout = new BorderLayout();

        gameInputArea.setLayout(GameInputAreaLayout);

        JButton addPlayer = new JButton("Add Player");

        addPlayer.addActionListener(e -> RunAddCharacter() );

        gameInputArea.add(addPlayer, BorderLayout.CENTER);

        return gameInputArea;
    }

    /**
     * Method for setting up a Container used for the game setup
     * @return the Container for the game option area
     */
    public Container GameOptionAreaSetup(){
        Container gameOptionArea = new JPanel();
        gameOptionArea.setBackground(Color.green);

        BorderLayout GameInputAreaLayout = new BorderLayout();

        gameOptionArea.setLayout(GameInputAreaLayout);

        JButton startGame = new JButton("Start Game");

        startGame.addActionListener(e -> RunGameStart() );

        gameOptionArea.add(startGame, BorderLayout.CENTER);

        return gameOptionArea;
    }

    /**
     * Method for setting up a Container used for the game setup
     * @return the Container for the character game input area
     */

    public Container GameCharactersSetup(){
        Container gameCharacters = new JPanel();

        BorderLayout GameInputAreaLayout = new BorderLayout();

        gameCharacters.setLayout(GameInputAreaLayout);

        JButton returnToMenu = new JButton("Back To Menu");

        gameCharacters.add(returnToMenu, BorderLayout.CENTER);

        returnToMenu.addActionListener(e -> ReturnToMainMenu() );

        characterInformation = new JLabel("Current Players : " + Player.customToStringForPlayerList());

        gameCharacters.add(characterInformation, BorderLayout.PAGE_END);

        return gameCharacters;
    }

    /**
     * Implementation for the adding the characters to the game
     */
    private void RunAddCharacter(){

        JTextField PlayerName = new JTextField(10);

        ButtonGroup characterGroup = new ButtonGroup();
        characterGroup.add(ScarlettButton);
        characterGroup.add(MustardButton);
        characterGroup.add(WhiteButton);
        characterGroup.add(GreenButton);
        characterGroup.add(PeacockButton);
        characterGroup.add(PlumButton);

        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("Player Name : "));

        myPanel.add(PlayerName);

        myPanel.add(Box.createHorizontalStrut(15));

        myPanel.add(new JLabel("Character : "));

        myPanel.add(ScarlettButton);
        myPanel.add(MustardButton);
        myPanel.add(WhiteButton);
        myPanel.add(GreenButton);
        myPanel.add(PeacockButton);
        myPanel.add(PlumButton);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter your name and preferred character", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Player tempPlayer;
            if(ScarlettButton.isSelected() && PlayerName.getText().length() > 0){
                tempPlayer = new Player(PlayerName.getText(), Board.getCharacter(0) );
                Player.addPlayerList(tempPlayer);
                ScarlettButton.setEnabled(false);
            }
            else if(MustardButton.isSelected() && PlayerName.getText().length() > 0){
                tempPlayer = new Player(PlayerName.getText(), Board.getCharacter(1));
                Player.addPlayerList(tempPlayer);
                MustardButton.setEnabled(false);
            }
            else if(WhiteButton.isSelected() && PlayerName.getText().length() > 0){
                tempPlayer = new Player(PlayerName.getText(), Board.getCharacter(2));
                Player.addPlayerList(tempPlayer);
                WhiteButton.setEnabled(false);
            }
            else if(GreenButton.isSelected() && PlayerName.getText().length() > 0){
                tempPlayer = new Player(PlayerName.getText(), Board.getCharacter(3));
                Player.addPlayerList(tempPlayer);
                GreenButton.setEnabled(false);
            }
            else if(PeacockButton.isSelected() && PlayerName.getText().length() > 0){
                tempPlayer = new Player(PlayerName.getText(), Board.getCharacter(4));
                Player.addPlayerList(tempPlayer);
                PeacockButton.setEnabled(false);
            }
            else if(PlumButton.isSelected() && PlayerName.getText().length() > 0){
                tempPlayer = new Player(PlayerName.getText(), Board.getCharacter(5));
                Player.addPlayerList(tempPlayer);
                PlumButton.setEnabled(false);
            }
            characterInformation.setText("Current Players : " + Player.customToStringForPlayerList());
            characterGroup.clearSelection(); //Clears all selected players to avoid error where you can add Professor Plum
            //indefinitely which would balloon the list to over 6 players

        }
        for(Player player : Player.getPlayerList()) {
            System.out.println(player);
        }
        System.out.println("\n");
    }

    /**
     * Start the running of the game
     */
    public void RunGameStart(){

        if (Player.getPlayerList().size() >= 3) {
            Object[] options = {"Yes",
                    "No",
                    "Return To Menu"};
            // optionSelected = 0 (yes), = 1 (no), = 2 (return to menu)
            int optionSelected = JOptionPane.showOptionDialog(GameSetupWindow,
                    "Are you sure you want to start the game with " + Player.playerList.size() +" players? ",
                    "Confirm",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            switch (optionSelected){
                case 0:
                    StartGame();
                    break;
                case 1 :
                    // Close Window - > Do nothing
                    break;
                case 2 :
                    ReturnToMainMenu();
                    break;
            }
        }
        else{
            JOptionPane.showMessageDialog(GameSetupWindow,
                    "You currently have " + (Player.getPlayerList().size()) + " players, but require 3 to play" ,
                    "More Players Required",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Returning to the main menu
     */
    public void ReturnToMainMenu(){
        Player.getPlayerList().removeAll(Player.getPlayerList());
        GameSetupWindow.dispose();
        new MenuSetup("Cluedo");
    }

    /**
     * Starting the game
     */
    public void StartGame(){
        GameSetupWindow.dispose();
        Board b = new Board();
        new CluedoGUI("Cluedo Game",b);
        new CluedoGUIController();
    }
}
