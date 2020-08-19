import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CluedoGUI extends JFrame {

    private JPanel InfoPanel;
    private JPanel GameControlPanel;
    private Panel BoardPanel;
    private JLabel DiceOne;
    private JLabel DiceTwo;

    // JButtons for the GameControlPanel
    public JButton EndTurn;
    public JButton OpenNotes;
    public JButton RollDice;
    public JButton MakeAccusation;
    public JButton MakeSuggestion;

    // BufferedImage's For all the Dice Images
    private BufferedImage FaceOne;
    private BufferedImage FaceTwo;
    private BufferedImage FaceThree;
    private BufferedImage FaceFour;
    private BufferedImage FaceFive;
    private BufferedImage FaceSix;
    // BufferedImage's For all the Dice Images
    private BufferedImage CandlestickImage;
    private BufferedImage DaggerImage;
    private BufferedImage LeadPipeImage;
    private BufferedImage RevolverImage;
    private BufferedImage RopeImage;
    private BufferedImage SpannerImage;
    // Labels for each Card in a Players Hand
    private JLabel HandCardI;
    private JLabel HandCardII;
    private JLabel HandCardIII;
    private JLabel HandCardIV;
    private JLabel HandCardV;
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
        this.setJMenuBar(GenerateMenu("Game Menu", "Exit Game", "Restart Game"));
        // Set the layout of the GUI to GridLayout, this way BoardPanel and InfoPanel can be easily separated.
        this.setLayout(new BorderLayout(2, 1));
        // Add the GameControlPanel to the JFrame
        this.add(GenerateGameControlPanel(), BorderLayout.NORTH);
        // Add the BoardPanel to the JFrame.
        //this.add(GenerateBoardPanel());
        this.add(GenerateBoardPanel(), BorderLayout.CENTER);
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
                System.exit(1);
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

    //TODO GET TILES AND CHARACTERS DRAWING
    //TODO GET TILES AND CHARACTERS DRAWING
    private JPanel GenerateBoardPanel(){
        BoardPanel = new JPanel();
        BoardPanel.setBackground(Color.WHITE);
        BoardPanel = new DrawPane();

        return BoardPanel;
    }

    static class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));

            for (int i = 0; i <= GRID_WIDTH; i++) {
                Line2D hline = new Line2D.Double(0, i * GRID_SIZE, 600, i * GRID_SIZE);
                Line2D vline = new Line2D.Double(i * GRID_SIZE, 0, i * GRID_SIZE, 625);
                g2d.draw(hline);
                g2d.draw(vline);
            }
            Line2D hline = new Line2D.Double(0, 25 * GRID_SIZE, 600, 25 * GRID_SIZE);
            g2d.draw(hline);
        }
    }

    private JPanel GenerateInfoPanel() {
        // Set the GameControlPanel to be a new JPanel.
        InfoPanel = new JPanel();
        // Set the background of the InfoPanel to blue.
        InfoPanel.setBackground(Color.blue);
        // TODO COMMENT
        InfoPanel.setLayout(new BorderLayout(10, 10));
        // Call the LoadImages method to ensure that all the Dice and Weapon Images have been loaded.
        LoadImages();
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
        // Set the Dice One label to the FaceOne Scaled Image.
        DiceOne = new JLabel(new ImageIcon(FaceOne.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        // Set the Dice One label to the FaceOne Scaled Image.
        DiceTwo = new JLabel(new ImageIcon(FaceOne.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        // Add the Dice Labels to the JPanel
        InfoPanelLeft.add(DiceOne);
        InfoPanelLeft.add(DiceTwo);
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

    private void LoadImages() {
        // Try to load the
        try {
            FaceOne = ImageIO.read(new File(DiceFaceOne));
            FaceTwo = ImageIO.read(new File(DiceFaceTwo));
            FaceThree = ImageIO.read(new File(DiceFaceThree));
            FaceFour = ImageIO.read(new File(DiceFaceFour));
            FaceFive = ImageIO.read(new File(DiceFaceFive));
            FaceSix = ImageIO.read(new File(DiceFaceSix));
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



    private JPanel GenerateGameControlPanel() {
        // Set the GameControlPanel to be a new JPanel.
        GameControlPanel = new JPanel();
        // Set the GameControlPanel to be focusable, allowing for our KeyListener to work.
        GameControlPanel.setFocusable(true);
        // Set the GameControlPanel to be able to request the program focus on this JPanel.
        GameControlPanel.requestFocusInWindow();
        // Set the background of the GameControlPanel to white.
        GameControlPanel.setBackground(Color.WHITE);
        // Set the Layout of the Background to a GridLayout, with rows, and column. Format : ButtonA, ButtonB, ButtonC, ButtonD
        GameControlPanel.setLayout(new GridLayout(1, 5));
        // Create the JButtons for the GameControlPanel
        EndTurn = new JButton("End Turn");
        OpenNotes = new JButton("Open Notes");
        RollDice = new JButton("Roll Dice");
        MakeSuggestion = new JButton("Suggest");
        MakeAccusation = new JButton("Accusation");
        // Add buttonListener to the GameControlPanel's JButtons.
        // TODO : ADD PROPER FUNCTIONALITY
        EndTurn.addActionListener(e -> System.out.println("End Turn"));
        OpenNotes.addActionListener(e -> System.out.println("Open Notes"));
        RollDice.addActionListener(e -> { for (int i = 0; i < 10; i++) { GenerateRandomDice(); } });
        MakeSuggestion.addActionListener(e -> System.out.println("Make Suggestion"));
        MakeAccusation.addActionListener(e -> System.out.println("Make Accusation"));
        // Add A KeyListener to the GameControlPanel
        GameControlPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '1'){
                    EndTurn.doClick();
                }
                if(e.getKeyChar() == '2'){
                    OpenNotes.doClick();
                }
                if(e.getKeyChar() == '3'){
                    RollDice.doClick();
                }
                if(e.getKeyChar() == '4'){
                    MakeAccusation.doClick();
                }
                if(e.getKeyChar() == '5'){
                    MakeSuggestion.doClick();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {         }
            @Override
            public void keyReleased(KeyEvent e) {           }
        });
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
