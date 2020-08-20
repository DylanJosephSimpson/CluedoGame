import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The tile class is responsible for keeping track of the individual
 * tiles which make up the board, as well as loading the information
 * on the tiles from the given txt file.
 */
public class Tile implements Item {

    String roomName;
    int x;
    int y;

    public Tile(String roomName, int x, int y) {
        this.roomName = roomName;
        this.x = x;
        this.y = y;
    }

    public void drawLInes(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

    }


    @Override
    public void draw(Graphics g, int x, int y) {

        Graphics2D g2 = (Graphics2D) g;
        System.out.println(roomName);
        //Tidy up conditional
        if(roomName.equals("Kitchen") || roomName.equals("Dining Room") ||roomName.equals("Conservatory") || roomName.equals("Ballroom") ||roomName.equals("Lounge")
            ||roomName.equals("Hall") || roomName.equals("Study") || roomName.equals("Billard Room") || roomName.equals("Cellar")) {
            g2.setColor(Color.cyan);
        }
        //It is not a Room tile therefore it can be white
        else {
            g.setColor(Color.WHITE);
        }
        //Draw the tile Rect
        g2.fillRect(x, y, 60, 60);

        //Draw a border around the rect
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        g2.drawRect(x,y,60,60);

    }

    @Override
    public void erase(Graphics g) {

    }
}
