import java.awt.*;

/**
 * The location class is responsible for representing
 * the location of objects on the board.
 */
public class Location {

    public double xPosition;
    public double yPosition;

    /**
     * Constructor for a location, made up of two doubles.
     * @param xPosition - The x position
     * @param yPosition - The y position
     */
    public Location(double xPosition, double yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Method to create a point from a Location
     * @param origin - The location to create the point from
     * @return a new point based on the information passed to the method.
     */
    public Point LocationAsPoint(Location origin){
        int x = (int) origin.xPosition;
        int y = (int) origin.yPosition;
        return new Point(x, y);
    }

    public boolean isNearby(Location otherPoint, double distance){
        return Math.abs(this.xPosition - otherPoint.xPosition) + Math.abs(this.yPosition - otherPoint.yPosition) <= distance;
    }

}
