package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
     * Was the murder committed using this weapon? (false by default)
     */
    private boolean isInvolvedInMurder;


    /**
     * Room that the weapon is currently in
     */
    private Room currentRoom;

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
        Graphics2D g2d = (Graphics2D) g;
        String path = "WeaponIcon/" + weaponName + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(path));
            Image scaledImage = image.getScaledInstance(28, 28, Image.SCALE_SMOOTH);
            g2d.drawImage(scaledImage,x+1,y+1,null);
        } catch (IOException e) {
            System.out.println("Error: Image drawing for " + weaponName + " failed.");
        }
    }

    @Override
    public void erase(Graphics g) {
        //do something with x and y to erase it
    }


    /**
     * Getter for currentRoom
     * @return currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter for currentRoom
     * @param room
     */
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    /**
     * Boolean to check if it is involved with the murder
     * @return a boolean which is based on the instance variable
     */

    public boolean isInvolvedInMurder() {
        return isInvolvedInMurder;
    }

    
    @Override
    public String toString() {
        return weaponName;
    }
}
