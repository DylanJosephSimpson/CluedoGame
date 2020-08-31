package Model;

import java.awt.*;
import java.util.regex.Pattern;

/**
 * The tile class is responsible for keeping track of the individual
 * tiles which make up the board, as well as loading the information
 * on the tiles from the given txt file.
 */
public class Tile implements Item {

    /**
     * The type of tile the object represents e.g a Kitchen Tile or A Character tile
     */
    private String tileType;
    /**
     * The x position of the tile
     */
    int col;
    /**
     * The y position of the tile
     */
    int row;

    public Tile(String tileType, int col, int row) {
        this.tileType = tileType;
        this.col = col;
        this.row = row;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        //Character pattern
        Pattern pattern = Pattern.compile("[SMWGPC]");

        Graphics2D g2 = (Graphics2D) g;

        //Setting Room tiles to pink
        if (Board.getRoomNames().contains(tileType)) {
            g2.setColor(new Color(255,192,203));

        }
        //Setting door tiles to light tealish colour
        else if(tileType.equals("Door")){
            g2.setColor(new Color(100,229,180));

        }
        //If it is a wall so set it to purple!
        else if(tileType.equals("Wall")){
            g2.setColor(new Color(100,2,180));
        }
        //If the player is on the room tile, set it to tealish colour
        else if(Board.getOriginalBoardLayoutArray()[y/30][x/30].equals("@") && Board.getBoardLayoutArray()[y/30][x/30].matches(String.valueOf(pattern))){
            g2.setColor(new Color(100,229,180));
        }
        //Set it to white representing the fact that it is a free tile to move onto.
        else {
            g2.setColor(Color.WHITE);
        }

        //Draw the tile Rectangle
        int TILE_SIZE = 30;
        g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);

        //Draw a black border around the rect unless it is a room tile
        if (!Board.getRoomNames().contains(tileType)) {
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, TILE_SIZE, TILE_SIZE);
        }

    }

    @Override
    public void erase(Graphics g) { }

    /**
     * Get the tile type
     * @return the String that the tile represents
     */
    public String getTileType() {
        return tileType;
    }

    /**
     * The column the tile represents
     * @return the column
     */
    public int getCol() {
        return col;
    }

    /**
     * Setting the column
     * @param col the int to be set to the column
     */

    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Return the row
     * @return the row
     */

    public int getRow() {
        return row;
    }

    /**
     * Set the row
     * @param row the number to assign to the row
     */

    public void setRow(int row) {
        this.row = row;
    }
}
