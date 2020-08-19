import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
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
     *
     * @param characterName Name of the weapon
     */
    public Character(String characterName) {
        this.characterName = characterName;
    }

    /**
     * When a player moves in a certain direction the x or y position changes for their character and then board is redrawn
     *
     * @param dir
     */
    public void move(Graphics g, String dir){
        erase(g);
        if(dir.equals("NORTH")){
            y-=30;
        }else if(dir.equals("EAST")){
            x+=30;
        } else if(dir.equals("SOUTH")){
            y+=30;
        } else if(dir.equals("WEST")){
            x-=30;
        }
        draw(g,x,y);
        //todo (Caleb) - once a player has moved redraw the board
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

