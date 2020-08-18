import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * The weapon class is responsible for keeping track of
 * the weapons which are on the board.
 *
 * @author Iqbal
 */
public class Weapon implements Item {

    /**
     * Name of the weapon
     */
    private String weaponName;

    /**
     * x-coordinate of where to draw on the board
     */
    private int x;

    /**
     * y-coordinate of where to draw on the board
     */
    private int y;

    /**
     * Constructor for weapon
     * @param weaponName Name of the weapon
     */
    public Weapon(String weaponName) {
        this.weaponName = weaponName;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        this.x = x;
        this.y = y;
        //TODO: will finish this - Iqbal
        //iterate through the tile array (will be known as the board)
        //and draw the image of the corresponding weapon onto the tile

        String path = "WeaponIcon/" + weaponName + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error: Image drawing for " + weaponName + " failed.");
        }
    }

    @Override
    public void erase(Graphics g) {
        //do something with x and y to erase it
    }

    @Override
    public String toString() {
        return weaponName;
    }
}

