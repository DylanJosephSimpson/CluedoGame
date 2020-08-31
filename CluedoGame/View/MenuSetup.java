package View;

import Model.*;

import Controller.MenuSetupController;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class MenuSetup {
    private static JFrame MenuWindow;

    /**
     * Grouping of Components : Menu
     */
    private static Container MenuContainer;
    private static JPanel GameTitlePanel;
    private static JLabel GameTitleName;
    private static JButton StartButton;

    /**
     * Constructor for a MenuJFrame Object.
     * The MenuJFrame is the first part of the GUI that the player gets to see, it allows them to press the
     * start button to initialise the game.
     *
     * As of now the MenuJFrame is resizeable, and the content inside of the JFrame
     **
     * @param title
     */
    public MenuSetup(String title) {
        Board.setup();
        // Creates a new JFrame with a given title, which is displayed at the top left hand corner of the application.
        MenuWindow = new JFrame(title);
        // Set the MenuWindow to be disposed when exited, this will result in the JFrame closing, if there is any other JFrames still running the program will not stop.
        MenuWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Set the initial size of the Application to be 740 pixels wide and 500 pixels tall
        MenuWindow.setSize(new Dimension(740, 500));
        // Set the minimum size the minimum size to 720 pixels wide and 480 pixels tall. Anything smaller than this and the game becomes unplayable.
        MenuWindow.setMinimumSize(new Dimension(720, 480));
        // Set the minimum size the minimum size to 1920 pixels wide and 1080 pixels tall. This allows you to fit the Application to the size of modern screens when using the Maximise button.
        MenuWindow.setMaximumSize(new Dimension(1920, 1080));
        // Set's the Background colour to this nifty lavender / blue color. // TODO : Can be changed if anyone wants, we should have a color scheme, but that can be done later.
        MenuWindow.getContentPane().setBackground(new Color(84, 101, 215));
        // Set the layout to be null so that anything added to this JFrame can be placed exactly where it should be // TODO : If someone wants to play with the Layouts and get the resizing working that way, feel free to.
        MenuWindow.setLayout(null);
        // Set the JFrame to be visible
        MenuWindow.setVisible(true);
        // Run through the container setup, which is a container that is drawn in the contentPane, and holds all of the content inside of it.
        containerSetup(MenuWindow);
        MenuWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(MenuWindow,
                        "Do you want to Exit ?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    MenuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else if (result == JOptionPane.NO_OPTION)
                    MenuWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        MenuWindow.pack();
    }

    /**
     * Setting up the menu container
     * @param ParentFrame
     */
    public void containerSetup(JFrame ParentFrame){
        MenuContainer = ParentFrame.getContentPane();
        MenuContainer.add(gameTitlePanelSetup(ParentFrame));
        MenuContainer.add(StartButtonSetup(ParentFrame));
    }

    /**
     * Setting up the Game title  Panel
     * @param ParentFrame the overarching frame
     * @return the setup Game title panel
     */
    public JPanel gameTitlePanelSetup(JFrame ParentFrame){
        GameTitlePanel = new JPanel();
        GameTitlePanel.setBounds(ParentFrame.getSize().width / 4, ParentFrame.getSize().height / 4, ParentFrame.getSize().width / 2, ParentFrame.getSize().height / 4);
        GameTitlePanel.setBackground(new Color(84, 101, 215));
        GameTitlePanel.add(gameTitleNameSetup(ParentFrame, GameTitlePanel));
        return GameTitlePanel;
    }

    /**
     * Setting up the Game Title Name
     * @param ParentFrame the overarching frame
     * @param ParentPanel the gameTitle setup panel
     * @return the setup JLabel
     */
    public JLabel gameTitleNameSetup(JFrame ParentFrame, JPanel ParentPanel){
        GameTitleName = new JLabel("CLUEDO");
        GameTitleName.setBounds(ParentPanel.getBounds().x, ParentPanel.getBounds().y, ParentPanel.getBounds().x, ParentPanel.getBounds().y);
        GameTitleName.setForeground(new Color(240, 128, 8));
        GameTitleName.setFont(new Font("Times New Roman", Font.BOLD, (ParentFrame.getSize().width - ParentFrame.getSize().height) / 4));
        return GameTitleName;
    }

    /**
     * Setting up the Start button
     * @param ParentFrame the overarching frame
     * @return the setup start button
     */
    public JButton StartButtonSetup(JFrame ParentFrame){
        StartButton = new JButton("Start");
        StartButton.setBackground(new Color(240, 128, 8));
        StartButton.setBorderPainted(true);
        StartButton.setContentAreaFilled(false);
        StartButton.setForeground(new Color(240, 128, 8));
        StartButton.setFont(new Font("Times New Roman", Font.PLAIN, (ParentFrame.getSize().width - ParentFrame.getSize().height) / 4));
        StartButton.setBounds(MenuWindow.getSize().width / 4, ParentFrame.getSize().height / 2, ParentFrame.getSize().width / 2, ParentFrame.getSize().height / 8);
        StartButton.addActionListener(e-> ChangeScreen("Options") );
        return StartButton;
    }

    /**
     *  Method for chaning the screen back to game setup
     * @param title the title of the screen
     */
    public void ChangeScreen(String title){
        new GameSetup(title);
        MenuWindow.dispose();
    }

    /**
     * Getter for the menu container
     * @return the menu container
     */
    public static Container getMenuContainer() {
        return MenuContainer;
    }

    /**
     * The getter for the JLabel relating to the game title frame
     * @return the associated JLabel
     */
    public static JLabel getGameTitleName() {
        return GameTitleName;
    }
}
