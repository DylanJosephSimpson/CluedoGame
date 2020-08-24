import java.awt.*;

/**
 * Interface class for the items in the game
 *
 * @author Iqbal
 */
public interface Item {

    /**
     * Draws the item on the board
     * @param g Graphics object
     * @param x x-coordinate of which tile it will be located on
     * @param y y-coordinate of which tile it will be located on
     */
    public void draw(Graphics g, int x, int y);

    /**
     * Erases the item from the board.
     * This method uses the x and y arguments from the draw method to locate where to erase the item.
     * @param g Graphics object
     */
    public void erase(Graphics g);
}
