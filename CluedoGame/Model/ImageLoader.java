package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageLoader {

    private static final String CandlestickPath = "Cards/Candlestick.png";
    private static final String DaggerPath = "Cards/Dagger.png";
    private static final String LeadPipePath = "Cards/LeadPipe.png";
    private static final String RevolverPath = "Cards/Revolver.png";
    private static final String RopePath = "Cards/Rope.png";
    private static final String SpannerPath = "Cards/Spanner.png";
    private static final String ScarlettPath = "Cards/Scarlett.png";
    private static final String MustardPath = "Cards/Mustard.png";
    private static final String WhitePath = "Cards/White.png";
    private static final String GreenPath = "Cards/Green.png";
    private static final String PeacockPath = "Cards/Peacock.png";
    private static final String PlumPath = "Cards/Plum.png";
    private static final String LibraryPath = "Cards/Library.png";
    private static final String BallRoomPath = "Cards/BallRoom.png";
    private static final String KitchenPath = "Cards/Kitchen.png";
    private static final String DiningRoomPath = "Cards/DiningRoom.png";
    private static final String LoungePath = "Cards/Lounge.png";
    private static final String HallPath = "Cards/Hall.png";
    private static final String StudyPath = "Cards/Study.png";
    private static final String BilliardRoomPath = "Cards/BillardRoom.png";
    private static final String ConservatoryPath = "Cards/Conservatory.png";
    private static final String CardPlaceholderPath = "Cards/CardPlaceholder.png";

    private static BufferedImage CardPlaceholder;
    private static BufferedImage CandlestickImage;
    private static BufferedImage DaggerImage;
    private static BufferedImage LeadPipeImage;
    private static BufferedImage RevolverImage;
    private static BufferedImage RopeImage;
    private static BufferedImage SpannerImage;
    private static BufferedImage ScarlettImage;
    private static BufferedImage MustardImage;
    private static BufferedImage WhiteImage;
    private static BufferedImage GreenImage;
    private static BufferedImage PeacockImage;
    private static BufferedImage PlumImage;
    private static BufferedImage LibraryImage;
    private static BufferedImage StudyImage;
    private static BufferedImage ConservatoryImage;
    private static BufferedImage HallImage;
    private static BufferedImage LoungeImage;
    private static BufferedImage BilliardRoomImage;
    private static BufferedImage KitchenImage;
    private static BufferedImage BallRoomImage;
    private static BufferedImage DiningRoomImage;

    // Strings which are the File Locations for all the Dice Images.
    private static String DiceFaceStart = "DiceFace/DiceFace";

    //List of image objects that are dice related!
    private static ArrayList<Image> DiceImages = new ArrayList<>();

    /**
     * Method for loading the images, will crash if file paths are incorrec
     */
    public static void LoadImages() {

        try {
            for (int i = 1; i < 7; i++) {
                DiceImages.add(ImageIO.read(new File(DiceFaceStart + i + ".png")));
            }

            CardPlaceholder = ImageIO.read(new File(CardPlaceholderPath));

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
            BilliardRoomImage = ImageIO.read(new File(BilliardRoomPath));
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

    /**
     * Method for getting a Buffered Image object for the GUI
     * @param imageToGrab the image wanted
     * @return the Buffered Image
     */
    public static BufferedImage GetImage(String imageToGrab){
        switch (imageToGrab){
            case "Candlestick":
                return CandlestickImage;
            case "Dagger":
                return DaggerImage;
            case "LeadPipe":
                return LeadPipeImage;
            case "Revolver":
                return RevolverImage;
            case "Rope":
                return RopeImage;
            case "Spanner":
                return SpannerImage;
            case "Miss. Scarlett":
                return ScarlettImage;
            case "Col. Mustard":
                return MustardImage;
            case "Mrs. White":
                return WhiteImage;
            case "Mr. Green":
                return GreenImage;
            case "Mrs. Peacock":
                return PeacockImage;
            case "Prof. Plum":
                return PlumImage;
            case "Library":
                return LibraryImage;
            case "Study":
                return StudyImage;
            case "Conservatory":
                return ConservatoryImage;
            case "Hall":
                return HallImage;
            case "Lounge":
                return LoungeImage;
            case "Billiard":
                return BilliardRoomImage;
            case "Kitchen":
                return KitchenImage;
            case "BallRoom":
                return BallRoomImage;
            case "DiningRoom":
                return DiningRoomImage;
            default:
                return CardPlaceholder;
        }
    }

    /**
     * Getter for the Dice Image list
     * @return the dice image list
     */

    public static ArrayList<Image> getDiceImages() {
        return DiceImages;
    }

}
