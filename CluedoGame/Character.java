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
     * Was the murder committed by this character? (false by default)
     */
    private boolean isInvolvedInMurder;

    /**
     * Room that the player is currently in. By default it is null as it does not spawn in one.
     */
    private Room currentRoom = null;

    /**
     * Constructor for character
     *
     * @param characterName Name of the weapon
     */
    public Character(String characterName , int x, int y) {
        this.characterName = characterName;
        this.x = x ;
        this.y = y;
    } //todo add prams for starting pos

    /**
     * When a player moves in a certain direction the x or y position changes for their character and then board is redrawn
     *
     * @param dir
     */
    public void move(String dir){
        if(dir.equals("NORTH")){
            y-=30;
        }else if(dir.equals("EAST")){
            x+=30;
        } else if(dir.equals("SOUTH")){
            y+=30;
        } else if(dir.equals("WEST")){
            x-=30;
        }
    }

    @Override
    public void draw(Graphics g, int x, int y) {

        Graphics2D g2d = (Graphics2D) g;
        this.x = x;
        this.y = y;

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
        //todo - Caleb - erase this properly
    }

    /**
     * When a suggestion is made this character moves to the designated room
     * @param room Room the character is moving to
     */
    public void moveToRoom(Room room){
        //TODO: Write this method once the Room class is implemented (Iqbal)
    }

    /**
     * Getter for currentRoom
     * @return
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter for currentRoom
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean isInvolvedInMurder() {
        return isInvolvedInMurder;
    }

    public void setInvolvedInMurder(boolean involvedInMurder) {
        isInvolvedInMurder = involvedInMurder;
    }


    @Override
    public String toString() {
        return characterName;
    }

    /**
     * Getter for x position
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y position
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for x position
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for y position
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

}
