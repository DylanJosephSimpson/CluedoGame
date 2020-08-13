import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CluedoGUI extends JFrame {

    private JPanel InfoPanel;
    private JPanel GameControlPanel;
    private JLabel DiceOne;
    private JLabel DiceTwo;
    private JLabel HandCardI;
    private JLabel HandCardII;
    private JLabel HandCardIII;
    private JLabel HandCardIV;
    private JLabel HandCardV;
    // JLabel's For all the Dice Images
    private BufferedImage FaceOne;
    private BufferedImage FaceTwo;
    private BufferedImage FaceThree;
    private BufferedImage FaceFour;
    private BufferedImage FaceFive;
    private BufferedImage FaceSix;
    // JLabel's For all the Dice Images
    private BufferedImage CandlestickImage;
    private BufferedImage DaggerImage;
    private BufferedImage LeadPipeImage;
    private BufferedImage RevolverImage;
    private BufferedImage RopeImage;
    private BufferedImage SpannerImage;
    // Strings which are the File Locations for all the Dice Images.
    private String DiceFaceOne = "DiceFace/DiceFaceOne.png";
    private String DiceFaceTwo = "DiceFace/DiceFaceTwo.png";
    private String DiceFaceThree = "DiceFace/DiceFaceThree.png";
    private String DiceFaceFour = "DiceFace/DiceFaceFour.png";
    private String DiceFaceFive = "DiceFace/DiceFaceFive.png";
    private String DiceFaceSix = "DiceFace/DiceFaceSix.png";
    // Strings which are the File Locations for all the Weapon Images.
    private String Candlestick = "WeaponIcon/Candlestick.png";
    private String Dagger = "WeaponIcon/Dagger.png";
    private String LeadPipe = "WeaponIcon/LeadPipe.png";
    private String Revolver = "WeaponIcon/Revolver.png";
    private String Rope = "WeaponIcon/Rope.png";
    private String Spanner = "WeaponIcon/Spanner.png";

    public CluedoGUI(String title) {
        super(title);
        // Set GUI to terminate the program when exited.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the JMenuBar to the return value of the GenerateMenu method.
        this.setJMenuBar(GenerateMenu("My Menu", "Option One", "Option Two"));
        // Set the layout of the GUI to GridLayout, this way BoardPanel and InfoPanel can be easily separated.
        this.setLayout(new BorderLayout(2, 1));
        // Add the GameControlPanel to the JFrame
        this.add(GenerateGameControlPanel(), BorderLayout.NORTH);
        // Add the BoardPanel to the JFrame.
        this.add((new MyPanel()));
        // Add the InfoPanel to the JFrame.
        this.add(GenerateInfoPanel(), BorderLayout.SOUTH);
        // Pack the JFrame so that all its contents are at or above their preferred sizes
        this.pack();
    }

    private JMenuBar GenerateMenu(String menuName, String optName, String optNameTwo) {

        // Create a new JMenuBar
        JMenuBar mainMenu = new JMenuBar();
        // Create a new JMenu
        JMenu MenuImplementation = new JMenu(menuName);
        // Create the JMenuItems for the JMenu
        JMenuItem ExitOption = new JMenuItem(optName);
        JMenuItem RestartGame = new JMenuItem(optNameTwo);
        // Create the ActionListener for the JMenuItems
        ExitOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Hello ");
            }
        });
        RestartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("World\n");
            }
        });
        // Add the JMenuItems to the JMenu
        MenuImplementation.add(ExitOption);
        MenuImplementation.add(RestartGame);
        // Add the JMenu to the MenuBar
        mainMenu.add(MenuImplementation);

        return mainMenu;
    }


    class MyPanel extends JPanel {
        public void paint(Graphics g) {
        }
    }

    private JPanel GenerateInfoPanel() {
        // Set the GameControlPanel to be a new JPanel.
        InfoPanel = new JPanel();
        // Set the background of the InfoPanel to blue.
        InfoPanel.setBackground(Color.blue);
        // TODO COMMENT
        InfoPanel.setLayout(new BorderLayout(10, 10));
        // Set the InfoPanelLeft to be a new JPanel.
        JPanel InfoPanelLeft = new JPanel();
        // Set the background of the InfoPanelLeft to white.
        InfoPanelLeft.setBackground(Color.blue);
        // TODO COMMENT
        InfoPanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        // Set the InfoPanelRight to be a new JPanel.
        JPanel InfoPanelRight = new JPanel();
        // Set the background of the InfoPanelRight to white.
        InfoPanelRight.setBackground(Color.blue);
        // TODO COMMENT
        InfoPanelRight.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        // Call the LoadDiceImages method to ensure that all the Dice Images have been loaded.
        LoadDiceImages();
        // Set the Dice One label to the FaceOne Scaled Image.
        DiceOne = new JLabel(new ImageIcon(FaceOne.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        // Set the Dice One label to the FaceOne Scaled Image.
        DiceTwo = new JLabel(new ImageIcon(FaceOne.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        // Add the Dice Labels to the JPanel
        InfoPanelLeft.add(DiceOne);
        InfoPanelLeft.add(DiceTwo);
        // Call the LoadWeaponImages method to ensure that all the Weapon Images have been loaded.
        LoadWeaponImages();
        // Set the HandCardI label to the CandlestickImage Scaled Image.
        HandCardI = new JLabel(new ImageIcon(CandlestickImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        HandCardI.setToolTipText("Candlestick Card");
        // Set the HandCardII label to the DaggerImage Scaled Image.
        HandCardII = new JLabel(new ImageIcon(DaggerImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        HandCardII.setToolTipText("Dagger Card");
        // Set the HandCardIII label to the LeadPipeImage Scaled Image.
        HandCardIII = new JLabel(new ImageIcon(LeadPipeImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        HandCardIII.setToolTipText("LeadPipe Card");
        // Set the HandCardIV label to the RevolverImage Scaled Image.
        HandCardIV = new JLabel(new ImageIcon(RevolverImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        HandCardIV.setToolTipText("Revolver Card");
        // Set the HandCardV label to the RopeImage Scaled Image.
        HandCardV = new JLabel(new ImageIcon(RopeImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        HandCardV.setToolTipText("Rope Card");
        // Add the Hand of Cards to the JPanel
        InfoPanelRight.add(HandCardI);
        InfoPanelRight.add(HandCardII);
        InfoPanelRight.add(HandCardIII);
        InfoPanelRight.add(HandCardIV);
        InfoPanelRight.add(HandCardV);
        // Add the InfoPanelRight and InfoPanelLeft to the
        InfoPanel.add(InfoPanelLeft, BorderLayout.LINE_START);
        InfoPanel.add(InfoPanelRight, BorderLayout.LINE_END);
        // Return the InfoPanel which should now be fully configured.
        return InfoPanel;
    }

    private void LoadDiceImages() {
        // Try to load the
        try {
            FaceOne = ImageIO.read(new File(DiceFaceOne));
            FaceTwo = ImageIO.read(new File(DiceFaceTwo));
            FaceThree = ImageIO.read(new File(DiceFaceThree));
            FaceFour = ImageIO.read(new File(DiceFaceFour));
            FaceFive = ImageIO.read(new File(DiceFaceFive));
            FaceSix = ImageIO.read(new File(DiceFaceSix));
        } catch (IOException ex) {
            System.out.println("INVALID FILE NAME");
        }
    }

    private void LoadWeaponImages() {
        // Try to load the
        try {
            CandlestickImage = ImageIO.read(new File(Candlestick));
            DaggerImage = ImageIO.read(new File(Dagger));
            LeadPipeImage = ImageIO.read(new File(LeadPipe));
            RevolverImage = ImageIO.read(new File(Revolver));
            RopeImage = ImageIO.read(new File(Rope));
            SpannerImage = ImageIO.read(new File(Spanner));
        } catch (IOException ex) {
            System.out.println("INVALID FILE NAME");
        }
    }

    private void GenerateRandomDice() {

        for(int runThrough = 0; runThrough < 15; runThrough++) {
            int firstDieRoll = (int) (Math.random() * (6)) + 1;
            int secondDieRoll = (int) (Math.random() * (6)) + 1;
            switch (firstDieRoll) {
                case 1:
                    DiceOne.setIcon(((new ImageIcon(FaceOne.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 2:
                    DiceOne.setIcon(((new ImageIcon(FaceTwo.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 3:
                    DiceOne.setIcon(((new ImageIcon(FaceThree.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 4:
                    DiceOne.setIcon(((new ImageIcon(FaceFour.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 5:
                    DiceOne.setIcon(((new ImageIcon(FaceFive.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 6:
                    DiceOne.setIcon(((new ImageIcon(FaceSix.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
            }

            switch (secondDieRoll) {
                case 1:
                    DiceTwo.setIcon(((new ImageIcon(FaceOne.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 2:
                    DiceTwo.setIcon(((new ImageIcon(FaceTwo.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 3:
                    DiceTwo.setIcon(((new ImageIcon(FaceThree.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 4:
                    DiceTwo.setIcon(((new ImageIcon(FaceFour.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 5:
                    DiceTwo.setIcon(((new ImageIcon(FaceFive.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
                case 6:
                    DiceTwo.setIcon(((new ImageIcon(FaceSix.getScaledInstance(40, 40, Image.SCALE_SMOOTH)))));
                    break;
            }

        }

    }

    private JPanel GenerateGameControlPanel() {
        // Set the GameControlPanel to be a new JPanel.
        GameControlPanel = new JPanel();
        // Set the background of the GameControlPanel to white.
        GameControlPanel.setBackground(Color.WHITE);
        // Set the Layout of the Background to a GridLayout, with rows, and column. Format : ButtonA, ButtonB, ButtonC, ButtonD
        GameControlPanel.setLayout(new GridLayout(1, 3));
        // Create the JButtons for the GameControlPanel
        JButton EndTurn = new JButton("End Turn");
        JButton OpenNotes = new JButton("Open Notes");
        JButton RollDice = new JButton("Roll Dice");
        JButton MakeSuggestion = new JButton("Suggest");
        JButton MakeAccusation = new JButton("Accusation");
        // Add buttonListener to the GameControlPanel's JButtons.
        // TODO : ADD PROPER FUNCTIONALITY
        EndTurn.addActionListener(e -> System.out.println("End Turn"));
        OpenNotes.addActionListener(e -> System.out.println("Open Notes"));
        RollDice.addActionListener(e -> GenerateRandomDice());
        MakeSuggestion.addActionListener(e -> System.out.println("Make Suggestion"));
        MakeAccusation.addActionListener(e -> System.out.println("Make Accusation"));
        // Add the JButtons to the GameControlPanel.
        GameControlPanel.add(EndTurn);
        GameControlPanel.add(OpenNotes);
        GameControlPanel.add(RollDice);
        GameControlPanel.add(MakeAccusation);
        GameControlPanel.add(MakeSuggestion);
        // Return the GameControlPanel which should now be fully configured.
        return GameControlPanel;
    }



}