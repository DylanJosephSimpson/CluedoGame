    import javax.imageio.ImageIO;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import java.util.*;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;

    public class CluedoGUI extends JFrame {
        //variables be grid board
        public static final int GRID_SIZE = 30;

        private final JFrame CluedoGame;

        public static Character getScarlett() {
            return Scarlett;
        }

        public static Character getMustard() {
            return Mustard;
        }

        public static Character getWhite() {
            return White;
        }

        public static Character getGreen() {
            return Green;
        }

        public static Character getPeacock() {
            return Peacock;
        }

        public static Character getPlum() {
            return Plum;
        }
        //TODO: Need to fix access so it's not static this means the players do not redraw correctly, but they do move I think

        // Initialization of characters
        private static Character Scarlett = new Character("Miss. Scarlett",210,720);
        private static Character Mustard = new Character("Col. Mustard",0,510);
        private static Character White = new Character("Mrs. White",270,0);
        private static Character Green = new Character("Mr. Green",420,0);
        private static Character Peacock = new Character("Mrs. Peacock",690,180);
        private static Character Plum = new Character("Prof. Plum",690,570);

        // Initialization of weapons
        private Weapon Candlestick = new Weapon("Candlestick");
        private Weapon Dagger = new Weapon("Dagger");
        private Weapon LeadPipe = new Weapon("LeadPipe");
        private Weapon Revolver = new Weapon("Revolver");
        private Weapon Rope = new Weapon("Rope");
        private Weapon Spanner = new Weapon("Spanner");

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

        private BufferedImage ScarlettImage;
        private BufferedImage MustardImage;
        private BufferedImage WhiteImage;
        private BufferedImage GreenImage;
        private BufferedImage PeacockImage;
        private BufferedImage PlumImage;

        private BufferedImage LibraryImage;
        private BufferedImage StudyImage;
        private BufferedImage ConservatoryImage;
        private BufferedImage HallImage;
        private BufferedImage LoungeImage;
        private BufferedImage BillardRoomImage;
        private BufferedImage KitchenImage;
        private BufferedImage BallRoomImage;
        private BufferedImage DiningRoomImage;

        // Labels for each Card in a Players Hand
        private JLabel CandlestickCard;
        private JLabel DaggerCard;
        private JLabel LeadPipeCard;
        private JLabel RevolverCard;
        private JLabel RopeCard;
        private JLabel SpannerCard;

        private JLabel ScarlettCard;
        private JLabel MustardCard;
        private JLabel WhiteCard;
        private JLabel GreenCard;
        private JLabel PeacockCard;
        private JLabel PlumCard;

        private JLabel LibraryCard;
        private JLabel StudyCard;
        private JLabel ConservatoryCard;
        private JLabel HallCard;
        private JLabel LoungeCard;
        private JLabel BillardRoomCard;
        private JLabel KitchenCard;
        private JLabel BallRoomCard;
        private JLabel DiningRoomCard;
        // Strings which are the File Locations for all the Dice Images.
        private String DiceFaceOne = "DiceFace/DiceFaceOne.png";
        private String DiceFaceTwo = "DiceFace/DiceFaceTwo.png";
        private String DiceFaceThree = "DiceFace/DiceFaceThree.png";
        private String DiceFaceFour = "DiceFace/DiceFaceFour.png";
        private String DiceFaceFive = "DiceFace/DiceFaceFive.png";
        private String DiceFaceSix = "DiceFace/DiceFaceSix.png";
        // Strings which are the File Locations for all the Weapon Images.


        //todo added this >>>>
        private String CandlestickPath = "Cards/Candlestick.png";
        private String DaggerPath = "Cards/Dagger.png";
        private String LeadPipePath = "Cards/LeadPipe.png";
        private String RevolverPath = "Cards/Revolver.png";
        private String RopePath = "Cards/Rope.png";
        private String SpannerPath = "Cards/Spanner.png";

        private String ScarlettPath = "Cards/Scarlett.png";
        private String MustardPath = "Cards/Mustard.png";
        private String WhitePath = "Cards/White.png";
        private String GreenPath = "Cards/Green.png";
        private String PeacockPath = "Cards/Peacock.png";
        private String PlumPath = "Cards/Plum.png";

        private String LibraryPath = "Cards/Library.png";
        private String BallRoomPath = "Cards/BallRoom.png";
        private String KitchenPath = "Cards/Kitchen.png";
        private String DiningRoomPath = "Cards/DiningRoom.png";
        private String LoungePath = "Cards/Lounge.png";
        private String HallPath = "Cards/Hall.png";
        private String StudyPath = "Cards/Study.png";
        private String BillardRoomPath = "Cards/BillardRoom.png";
        private String ConservatoryPath = "Cards/Conservatory.png";

        //todo added this <<<<<



        // Collection of key game objects
        public static ArrayList<Character> allCharacters = new ArrayList<>();
        public static ArrayList<Weapon> allWeapons = new ArrayList<>();
        public static ArrayList<Room> allRooms = new ArrayList<>();
        public static Set<RoomCard> roomCards = new HashSet<>();
        public static Set<WeaponCard> weaponCards = new HashSet<>();
        public static Set<CharacterCard> characterCards = new HashSet<>();

        private int movesLeft;
        private Character currentCharacter;
        private int currentCharacterPos = 0;
        private boolean hasRolled = false;
        private ArrayList<int[]> previouslyTraversedTiles = new ArrayList<>();

        private Tile[][] board = new Tile[25][30];
        private Board b;

        //Utility collections used for setup and quick checks
        private static HashMap<String,String> tileTypeToNameMap = new HashMap<>();
        public static HashSet<String> roomNames = new HashSet<>();

        public CluedoGUI(String title, Board b) {

            CluedoGame = new JFrame(title);
            this.b  =b;
            // Set GUI to terminate the program when exited.
            CluedoGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set the JMenuBar to the return value of the GenerateMenu method.
            CluedoGame.setJMenuBar(GenerateMenu("Game Menu", "Exit Game", "Restart Game"));
            // Set the layout of the GUI to GridLayout, this way BoardPanel and InfoPanel can be easily separated.
            CluedoGame.setLayout(new BorderLayout(2, 1));
            // Add the GameControlPanel to the JFrame
            CluedoGame.add(GenerateGameControlPanel(), BorderLayout.NORTH);
            // Add the BoardPanel to the JFrame.
            //this.add(GenerateBoardPanel());
            CluedoGame.add(GenerateBoardPanel(), BorderLayout.CENTER);
            // Add the InfoPanel to the JFrame.
            CluedoGame.add(GenerateInfoPanel(), BorderLayout.SOUTH);
            // Pack the JFrame so that all its contents are at or above their preferred sizes
            CluedoGame.pack();
            //
            CluedoGame.setVisible(true);
            //Implementing a setup method which initialises required variables
            setup();

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

            //todo these will need to be changed to players rather than characters
            //FIXME: I've used allCharacters in the SuggestionWindow class in order to create a new Suggestion - Iqbal
            allCharacters.add(Scarlett);
            allCharacters.add(Mustard);
            allCharacters.add(White);
            allCharacters.add(Green);
            allCharacters.add(Peacock);
            allCharacters.add(Plum);

            allWeapons.add(Candlestick);
            allWeapons.add(Dagger);
            allWeapons.add(LeadPipe);
            allWeapons.add(Revolver);
            allWeapons.add(Rope);
            allWeapons.add(Spanner);

            allRooms.add(new Room("Kitchen"));
            allRooms.add(new Room("Ballroom"));
            allRooms.add(new Room("Conservatory"));
            allRooms.add(new Room("Dining Room"));
            allRooms.add(new Room("Lounge"));
            allRooms.add(new Room("Hall"));
            allRooms.add(new Room("Study"));
            allRooms.add(new Room("Billiard Room"));
            allRooms.add(new Room("Library"));
            allRooms.add(new Room("Cellar"));

            currentCharacter = Scarlett;

        }


        private void generateCards(){
            // Generate character cards
            for (Character c : allCharacters){
                characterCards.add(new CharacterCard(c.toString()));
            }

            // Generate weapon cards
            for (Weapon w : allWeapons){
                weaponCards.add(new WeaponCard(w.toString()));
            }

            // Generate room cards
            for (Room r : allRooms){
                roomCards.add(new RoomCard(r.toString()));
            }

            Board.deckOfCards.addAll(characterCards);
            Board.deckOfCards.addAll(weaponCards);
            Board.deckOfCards.addAll(roomCards);
        }

        private void generateMurderer(){
            int index = new Random().nextInt(allCharacters.size());
            Iterator<Character> iter = allCharacters.iterator();
            for (int i = 0; i < index; i++) {
                iter.next().setInvolvedInMurder(true);
            }
            throw new RuntimeException("Error: generating murderer unsuccessful");
        }

        private void generateMurderWeapon(){
            int index = new Random().nextInt(allWeapons.size());
            Iterator<Weapon> iter = allWeapons.iterator();
            for (int i = 0; i < index; i++) {
                iter.next().setInvolvedInMurder(true);
            }
            throw new RuntimeException("Error: generating murder weapon unsuccessful");
        }

        private void generateMurderRoom(){
            int index = new Random().nextInt(allRooms.size());
            Iterator<Room> iter = allRooms.iterator();
            for (int i = 0; i < index; i++) {
                iter.next().setInvolvedInMurder(true);
            }
            throw new RuntimeException("Error: generating murder room unsuccessful");
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
            CandlestickCard = new JLabel(new ImageIcon(CandlestickImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            CandlestickCard.setToolTipText("Candlestick Card");
            // Set the HandCardII label to the DaggerImage Scaled Image.
            DaggerCard = new JLabel(new ImageIcon(DaggerImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            DaggerCard.setToolTipText("Dagger Card");
            // Set the HandCardIII label to the LeadPipeImage Scaled Image.
            LeadPipeCard = new JLabel(new ImageIcon(LeadPipeImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            LeadPipeCard.setToolTipText("LeadPipe Card");
            // Set the HandCardIV label to the RevolverImage Scaled Image.
            RevolverCard = new JLabel(new ImageIcon(RevolverImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            RevolverCard.setToolTipText("Revolver Card");
            // Set the HandCardV label to the RopeImage Scaled Image.
            RopeCard = new JLabel(new ImageIcon(RopeImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            RopeCard.setToolTipText("Rope Card");
            // Set the HandCardV label to the SpannerImage Scaled Image.
            SpannerCard = new JLabel(new ImageIcon(SpannerImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            SpannerCard.setToolTipText("Spanner Card");
            // Add the Hand of Cards to the JPanel


            ScarlettCard = new JLabel(new ImageIcon(ScarlettImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            ScarlettCard.setToolTipText("Candlestick Card");
            // Set the HandCardII label to the DaggerImage Scaled Image.
            MustardCard = new JLabel(new ImageIcon(MustardImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            MustardCard.setToolTipText("Dagger Card");
            // Set the HandCardIII label to the LeadPipeImage Scaled Image.
            GreenCard = new JLabel(new ImageIcon(GreenImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            GreenCard.setToolTipText("LeadPipe Card");
            // Set the HandCardIV label to the RevolverImage Scaled Image.
            WhiteCard = new JLabel(new ImageIcon(WhiteImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            WhiteCard.setToolTipText("Revolver Card");
            // Set the HandCardV label to the RopeImage Scaled Image.
            PlumCard = new JLabel(new ImageIcon(PlumImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            PlumCard.setToolTipText("Rope Card");
            // Set the HandCardV label to the SpannerImage Scaled Image.
            PeacockCard = new JLabel(new ImageIcon(PeacockImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            PeacockCard.setToolTipText("Spanner Card");
            // Add the Hand of Cards to the JPanel




            LibraryCard = new JLabel(new ImageIcon(LibraryImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            LibraryCard.setToolTipText("Library Card");
            // Set the HandCardII label to the DaggerImage Scaled Image.
            BallRoomCard = new JLabel(new ImageIcon(BallRoomImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            BallRoomCard.setToolTipText("Ball Room Card");
            // Set the HandCardIII label to the LeadPipeImage Scaled Image.
            KitchenCard = new JLabel(new ImageIcon(KitchenImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            KitchenCard.setToolTipText("Kitchen Card");
            // Set the HandCardIV label to the RevolverImage Scaled Image.
            DiningRoomCard = new JLabel(new ImageIcon(DiningRoomImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            DiningRoomCard.setToolTipText("Dining Room Card");
            // Set the HandCardV label to the RopeImage Scaled Image.
            LoungeCard = new JLabel(new ImageIcon(LoungeImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            LoungeCard.setToolTipText("Lounge Card");
            // Set the HandCardV label to the SpannerImage Scaled Image.
            HallCard = new JLabel(new ImageIcon(HallImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            HallCard.setToolTipText("Hall Card");
            // Add the Hand of Cards to the JPanel
            StudyCard = new JLabel(new ImageIcon(StudyImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            StudyCard.setToolTipText("Study Card");
            // Set the HandCardII label to the DaggerImage Scaled Image.
            BillardRoomCard = new JLabel(new ImageIcon(BillardRoomImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            BillardRoomCard.setToolTipText("Billard Room Card");
            // Set the HandCardIII label to the LeadPipeImage Scaled Image.
            ConservatoryCard = new JLabel(new ImageIcon(ConservatoryImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            ConservatoryCard.setToolTipText("Conservatory Card");
            // Set the HandCardIV label to the RevolverImage Scaled Image.




            InfoPanelRight.add(ConservatoryCard);
            InfoPanelRight.add(BillardRoomCard);
            InfoPanelRight.add(StudyCard);
            InfoPanelRight.add(HallCard);
            InfoPanelRight.add(LoungeCard);
            InfoPanelRight.add(KitchenCard);
            InfoPanelRight.add(BallRoomCard);
            InfoPanelRight.add(LibraryCard);
            InfoPanelRight.add(DiningRoomCard);
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
                //
                CandlestickImage = ImageIO.read(new File(CandlestickPath));
                DaggerImage = ImageIO.read(new File(DaggerPath));
                LeadPipeImage = ImageIO.read(new File(LeadPipePath));
                RevolverImage = ImageIO.read(new File(RevolverPath));
                RopeImage = ImageIO.read(new File(RopePath));
                SpannerImage = ImageIO.read(new File(SpannerPath));

                ScarlettImage = ImageIO.read(new File(ScarlettPath));
                MustardImage = ImageIO.read(new File(MustardPath));
                WhiteImage = ImageIO.read(new File(WhitePath));
                GreenImage = ImageIO.read(new File(GreenPath));
                PlumImage = ImageIO.read(new File(PlumPath));
                PeacockImage = ImageIO.read(new File(PeacockPath));

                ConservatoryImage = ImageIO.read(new File(ConservatoryPath));
                BillardRoomImage = ImageIO.read(new File(BillardRoomPath));
                StudyImage = ImageIO.read(new File(StudyPath));
                HallImage = ImageIO.read(new File(HallPath));
                LoungeImage = ImageIO.read(new File(LoungePath));
                DiningRoomImage = ImageIO.read(new File(DiningRoomPath));
                KitchenImage = ImageIO.read(new File(KitchenPath));
                LibraryImage = ImageIO.read(new File(LibraryPath));
                BallRoomImage = ImageIO.read(new File(BallRoomPath));
            } catch (IOException ex) {
                System.out.println("INVALID FILE NAME");
            }
        }

        private void GenerateRandomDice() {
            hasRolled = true;
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

        private boolean visitedTile(Tile tileInFrontOfPlayer) {
            //checks if the next tile has been visited by checking the list of tiles that the character has visited in their turn
            for (int[] previousTile : previouslyTraversedTiles) {
                if (previousTile[0] == tileInFrontOfPlayer.getX()/30 && previousTile[1] == tileInFrontOfPlayer.getY()/30) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "You can not visit a space that you have already been in your turn.", "Keep Moving Forward", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            return true;
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
                public void keyTyped(KeyEvent e) { }
                @Override
                public void keyPressed(KeyEvent e) {
                    //function keys
                    if(e.getKeyChar() == '1'){
                        EndTurn.doClick();
                    }
                    if(e.getKeyChar() == '2'){
                        OpenNotes.doClick();
                    }
                    if(e.getKeyChar() == '3'){
                        if(!hasRolled) {
                            RollDice.doClick();
                        }
                    }
                    if(e.getKeyChar() == '4'){
                        MakeAccusation.doClick();
                    }
                    if(e.getKeyChar() == '5'){
                        MakeSuggestion.doClick();
                    }
                    //if the player has rolled then they can move
                    if(hasRolled) {
                        //if the current player has no moves left, prompt the player that their turn has ended and return the settings to their defult
                        if (movesLeft == 0) {
                            hasRolled = false;
                            previouslyTraversedTiles.clear();
                            currentCharacterPos++;
                            if (currentCharacterPos == 6) {
                                currentCharacterPos = 0;
                            }
                            currentCharacter = allCharacters.get(currentCharacterPos);
                            JFrame frame = new JFrame();
                            JOptionPane.showMessageDialog(frame, "You have run out of moves", "End your turn", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }


                        //convert pixel pos to tile pos
                        int tileX = currentCharacter.getX() / 30;
                        int tileY = currentCharacter.getY() / 30;

                        previouslyTraversedTiles.add(new int[]{tileX, tileY});

                        //Pattern pattern = Pattern.compile("(Scarlett|Mustard|Green|White|Plum|Peacock|Wall)",Pattern.CASE_INSENSITIVE); //todo update board each time player is moved and then uncomment this(Caleb)
                        Pattern pattern = Pattern.compile("(Wall)", Pattern.CASE_INSENSITIVE);
                        //ensures the player can move into the position that they want to, if they are not able to then do not decrese their moves left
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            if (currentCharacter.getY() > 0 && visitedTile(board[tileY - 1][tileX])) {
                                Matcher matcher = pattern.matcher(board[tileY - 1][tileX].getTileType());
                                if (!matcher.find()) {
                                    currentCharacter.move("NORTH");
                                    movesLeft -= 1;
                                    repaint();
                                    //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                }
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            if (currentCharacter.getY() < 720 && visitedTile(board[tileY + 1][tileX])) {
                                Matcher matcher = pattern.matcher(board[tileY + 1][tileX].getTileType());
                                if (!matcher.find()) {
                                    //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                    currentCharacter.move("SOUTH");
                                    movesLeft -= 1;
                                    repaint();
                                }
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            if (currentCharacter.getX() > 0 && visitedTile(board[tileY][tileX - 1])) {
                                Matcher matcher = pattern.matcher(board[tileY][tileX - 1].getTileType());
                                if (!matcher.find()) {
                                    // previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                    currentCharacter.move("WEST");
                                    movesLeft -= 1;
                                    repaint();
                                }
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            if (currentCharacter.getX() < 690 && visitedTile(board[tileY][tileX + 1])) {
                                Matcher matcher = pattern.matcher(board[tileY][tileX + 1].getTileType());
                                if (!matcher.find()) {
                                    //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                    currentCharacter.move("EAST");
                                    movesLeft -= 1;
                                    repaint();
                                }
                            }
                        }
                        //redraw the frame
                        repaint();
                    } else{
                        //prompts the player to roll if they have not already
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "You need to roll the dice before you can move", "You have not rolled", JOptionPane.WARNING_MESSAGE);
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
