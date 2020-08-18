import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The character class is responsible for keeping track
 * of the player's character as it traverses the board.
 */
public class Character implements Item {

    /**
     * Name of the character
     */
    private String characterName;

    /**
     * x-coordinate of where to draw on the board
     */
    private int x;

    /**
     * y-coordinate of where to draw on the board
     */
    private int y;

    /**
     * Constructor for character
     * @param characterName Name of the weapon
     */
    public Character(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        this.x = x;
        this.y = y;

        //todo - Caleb - draw this properly - will do this in next commit
        String path = "CharacterPieces/" + characterName + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error: Image drawing for " + characterName + " failed.");
        }
    }

    @Override
    public void erase(Graphics g) {
        //todo - Caleb - erase this properly - will do this in next commit
    }

    @Override
    public String toString() {
        return characterName;
    }
}

