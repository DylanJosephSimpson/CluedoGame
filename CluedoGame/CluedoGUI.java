import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CluedoGUI extends JFrame {

    //variables be grid board
    public static final int GRID_SIZE = 30;


    //initialisation of characters
    private Character Scarlett = new Character("Miss. Scarlett");
    private Character Mustard = new Character("Col. Mustard");
    private Character White = new Character("Mrs. White");
    private Character Green = new Character("Mr. Green");
    private Character Peacock = new Character("Mrs. Peacock");
    private Character Plum = new Character("Prof. Plum");

    // JPanels and JLabels
    private JPanel InfoPanel;
    private JPanel GameControlPanel;
    private JPanel BoardPanel;
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
    private JLabel HandCardVI;
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

    private int movesLeft;
    private Character currentCharacter;
    private ArrayList<Character> allCharacters;

    private Tile[][] board = new Tile[25][30];
    private Board b;
    //Utility collections used for setup and quick checks
    private static HashMap<String,String> tileTypeToNameMap = new HashMap<>();
    static HashSet<String> roomNames = new HashSet<>();

    //


    public CluedoGUI(String title, Board b) {

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
        //Implementing a setup method which initialises required variables
        setup();
        this.b  =b;

    }

    public void setup(){
        //Adding all of mappings to a Hashmap
        tileTypeToNameMap.put("k","Kitchen");
        tileTypeToNameMap.put("b","Ballroom");
        tileTypeToNameMap.put("c","Conservatory");
        tileTypeToNameMap.put("d","Dining Room");
        tileTypeToNameMap.put("l","Lounge");
        tileTypeToNameMap.put("h","Hall");
        tileTypeToNameMap.put("s","Study");
        tileTypeToNameMap.put("i","Billiard Room");
        tileTypeToNameMap.put("y","Library");
        tileTypeToNameMap.put("-","Wall");
        tileTypeToNameMap.put("@","Door");
        tileTypeToNameMap.put(" ","SPACE");
        tileTypeToNameMap.put("G","Green");
        tileTypeToNameMap.put("W","White");
        tileTypeToNameMap.put("P","Plum");
        tileTypeToNameMap.put("C","Peacock");
        tileTypeToNameMap.put("S","Scarlett");
        tileTypeToNameMap.put("M","Mustard");
        tileTypeToNameMap.put("e","Cellar");




        //Room names being added to arraylist
        roomNames.add("Kitchen");
        roomNames.add("Ballroom");
        roomNames.add("Conservatory");
        roomNames.add("Dining Room");
        roomNames.add("Lounge");
        roomNames.add("Hall");
        roomNames.add("Study");
        roomNames.add("Billiard Room");
        roomNames.add("Library");
        roomNames.add("Cellar");

        //initialise starting pos of characters

//        allCharacters.add(Scarlett);
//        allCharacters.add(Mustard);
//        allCharacters.add(White);
//        allCharacters.add(Green);
//        allCharacters.add(Peacock);
//        allCharacters.add(Plum);

        //look into setting these as coordinates see if it's requried or not --> todo
        Scarlett.setX(210);
        Scarlett.setY(720);

        Mustard.setX(0);
        Mustard.setY(510);

        White.setX(270);
        White.setY(0);

        Green.setX(420);
        Green.setY(0);

        Peacock.setX(690);
        Peacock.setY(180);

        Plum.setX(690);
        Plum.setY(570);




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

    /**
     * Draws the board of the game
     *
     * @return
     */
    private JPanel GenerateBoardPanel()  {
        BoardPanel = new JPanel();
        //calls drawPane and this draws the main section of the board
        BoardPanel = new DrawPane();


        return BoardPanel;
    }

    /**
     * draws the board grid and the starting positions of the characters
     */
    class DrawPane extends JPanel {
        public void paintComponent(Graphics graphics) {
            Graphics2D g2d = (Graphics2D) graphics;

            //draws the 24x25 grid, hline = horizontal line, vline = vertial line
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,720,885);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));
            drawBoard(graphics);



            Scarlett.draw(g2d,Scarlett.getX(),Scarlett.getY());
            Mustard.draw(g2d,Mustard.getX(),Mustard.getY());
            Green.draw(g2d,Green.getX(),Green.getY());
            White.draw(g2d,White.getX(),White.getY());
            Plum.draw(g2d,Plum.getX(),Plum.getY());
            Peacock.draw(g2d,Peacock.getX(),Peacock.getY());




        }
    }



    /**
     *
     * Method which iterates through the board array and
     *
     */

    public void drawBoard(Graphics graphics) {


        for(int row = 0; row < 25; ++row) {
            for(int col = 0; col < 24; ++col) {
                //System.out.println(roomCharacterToRoomName.get(originalBoardLayoutArray[row][col]));
                board[row][col] = new Tile(tileTypeToNameMap.get(b.getBoardLayoutArray()[row][col]),col*GRID_SIZE,row*GRID_SIZE);
                board[row][col].draw(graphics,board[row][col].x,board[row][col].y);
            }
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
        // Set the HandCardV label to the SpannerImage Scaled Image.
        HandCardVI = new JLabel(new ImageIcon(SpannerImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        HandCardVI.setToolTipText("Spanner Card");
        // Add the Hand of Cards to the JPanel
        InfoPanelRight.add(HandCardI);
        InfoPanelRight.add(HandCardII);
        InfoPanelRight.add(HandCardIII);
        InfoPanelRight.add(HandCardIV);
        InfoPanelRight.add(HandCardV);
        InfoPanelRight.add(HandCardVI);
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
        movesLeft = firstDieRoll + secondDieRoll;
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
        RollDice.addActionListener(e -> { GenerateRandomDice(); });
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

                if(e.getKeyCode() == KeyEvent.VK_UP){
                    RollDice.doClick();
                }


            }
            @Override
            public void keyPressed(KeyEvent e) {
                int tileX = Scarlett.getX()/30;
                int tileY =Scarlett.getY()/30;


                Pattern pattern = Pattern.compile("(Scarlett|Mustard|Green|White|Plum|Peacock|Wall)",Pattern.CASE_INSENSITIVE);

                if(e.getKeyCode() == KeyEvent.VK_UP){
                    Matcher matcher = pattern.matcher(board[tileY-1][tileX].getTileType());
                    if(Scarlett.getY()>0 && !matcher.find()) {
                        Scarlett.move("NORTH");
                        movesLeft-=1;
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    Matcher matcher = pattern.matcher(board[tileY+1][tileX].getTileType());
                    if(Scarlett.getY()<720 && !matcher.find()) {
                        Scarlett.move("SOUTH");
                        movesLeft-=1;

                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    Matcher matcher = pattern.matcher(board[tileY][tileX-1].getTileType());
                    if(Scarlett.getX()>0 && !matcher.find()) {
                        Scarlett.move("WEST");
                        movesLeft-=1;
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    Matcher matcher = pattern.matcher(board[tileY][tileX+1].getTileType());
                    if(Scarlett.getX()<690 && !matcher.find()) {
                        Scarlett.move("EAST");
                        movesLeft-=1;
                    }
                }
                repaint();
                if(movesLeft==0){
                    System.out.println("NEXT PLAYER");
                }

            }
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
