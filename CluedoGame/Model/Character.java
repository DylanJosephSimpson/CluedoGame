package Model;

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
    public String characterName;

    /**
     * x-coordinate of where to draw on the board
     */
    private int x;

    /**
     * y-coordinate of where to draw on the board
     */
    private int y;

    /**
     * Was the murder committed by this character? (false by default)
     */
    private boolean isInvolvedInMurder;

    /**
     * Room that the player is currently in. By default it is null as it does not spawn in one.
     */
    private Room currentRoom = null;

    /**
     * Checks if the player was previously transported into a room before prior to their turn
     */
    private boolean transportedIntoRoom;
    /**
     * The current tile
     */
    public Tile currentTile;

    /**
     * Constructor for character
     *
     * @param characterName Name of the weapon
     */
    public Character(String characterName , int x, int y) {
        this.characterName = characterName;
        this.x = x ;
        this.y = y;
        currentTile = new Tile("s",this.x,this.y);
    }

    @Override
    public void draw(Graphics g, int x, int y) {

        Graphics2D g2d = (Graphics2D) g;

        //Getting the card path and attempts to draw the image
        String path = "CharacterPieces/" + characterName + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(path));
            Image scaledImage = image.getScaledInstance(28, 28, Image.SCALE_SMOOTH);
            g2d.drawImage(scaledImage,x+1,y+1,null);
        } catch (IOException e) {
            System.out.println("Error: Image drawing for " + characterName + " failed.");
        }
    }

    @Override
    public void erase(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x+1,y+1,28,28);
    }

    /**
     * Getter for currentRoom
     * @return the current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter for currentRoom
     * @param currentRoom the current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Check to see if the Character is involved in the murder
     * @return a boolean based on this field
     */
    public boolean isInvolvedInMurder() {
        return isInvolvedInMurder;
    }


    /**
     * This is used after a suggestion transports them into a room
     * @param transportedIntoRoom transported into a room prior to this player's turn?
     */
    public void setTransportedIntoRoom(boolean transportedIntoRoom) {
        this.transportedIntoRoom = transportedIntoRoom;
    }

    @Override
    public String toString() {
        return characterName;
    }

    /**
     * Getter for x position
     *
    * @return an int representing the x position
     */
  public int getX() {
        return x;
   }

    /**
     * Getter for y position
     *
     * @return an int Y position
     */
    public int getY() {
        return y;
    }


    /**
     * Setter for x position
     *
     * @return an int x position
     */
   public void setX(int x) {
        this.x = x;
    }


    /**
     * Setter for y position
     *
     * @return an int y position
     */
  public void setY(int y) {
       this.y = y;
    }
}
