import java.awt.*;

/**
 * The tile class is responsible for keeping track of the individual
 * tiles which make up the board, as well as loading the information
 * on the tiles from the given txt file.
 */
public class Tile implements Item {
    
    private String tileType;
    int x;
    int y;

    Tile(String tileType, int x, int y) {
        this.tileType = tileType;
        this.x = x;
        this.y = y;
    }


    @Override
    public void draw(Graphics g, int x, int y) {

        Graphics2D g2 = (Graphics2D) g;
        //System.out.println(tileType);
        //Tidy up conditional
//        System.out.println(tileType);

        //Setting Roomtiles to pink
        if (CluedoGUI.roomNames.contains(tileType)) {
            g2.setColor(new Color(255,192,203));
        }
        //Setting door tiles to light tealish colour
        else if(tileType.equals("Door")){
            g2.setColor(new Color(100,229,180));
        }
        //It is a wall so set it to purple!
        else if(tileType.equals("Wall")){
            g2.setColor(new Color(100,2,180));
        }
        else {
            g.setColor(Color.WHITE);
        }
        //Draw the tile Rectangle
        int TILE_SIZE = 30;
        g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        
        //Draw a black border around the rect
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, TILE_SIZE, TILE_SIZE);

    }

    @Override
    public void erase(Graphics g) {

    }

    public String getTileType() {
        return tileType;
    }

    public void setTileType(String tileType) {
        this.tileType = tileType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
