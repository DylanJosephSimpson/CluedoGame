import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.util.List;

/**
 * The character class is responsible for keeping track
 * of the player's character as it traverses the board.
 */
public class Character {

    public Location characterPosition;
    public Color characterColor;
    public String characterName;
    public boolean characterActive;
    public List<Character> characterList;

    /**
     * Constructor for the Character object
     * @param characterName
     * @param xPosition
     * @param yPosition
     * @param characterColor
     * @param isActive
     */
    public Character(String characterName, double xPosition, double yPosition, Color characterColor, boolean isActive){
        this.characterName = characterName;
        this.characterPosition = new Location(xPosition, yPosition);
        this.characterColor = characterColor;
        this.characterActive = isActive;
    }

    /**
     * Draw method to draw a character on the GUI.
     * @param graphics
     * @param area
     * @param origin
     */
    public void draw(Graphics graphics, Dimension area, Location origin){
        // Create a new point based on the character position
        Point characterPos = characterPosition.LocationAsPoint(origin);
        // Translate the character position
        characterPos.translate(area.width / 2, area.height / 2);
        // TODO: This is where scaling will have to take place
        int size = (int) 40.0;
        // Set the color of the character to their set color.
        graphics.setColor(characterColor);
        // Draw the character as an oval.
        graphics.fillOval(characterPos.x - size / 2, characterPos.y - size / 2, size, size);
    }
}
