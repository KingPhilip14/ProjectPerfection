package Exploration;

/**
 * A class for creating the coordinates of Locations.
 * @author Ian King
 */
public class Coordinate implements java.io.Serializable
{
    private int xCoordinate;
    private int yCoordinate;
    
    public Coordinate(int x, int y)
    {
        // X coordinate is 1 less, and Y coordinate is 1 less than what they actually are in the text file since it starts at 1,1 
        // and not 0,0 
        xCoordinate = x - 1;
        yCoordinate = y - 1;
    }
    
    public int getXCoordinate() {return xCoordinate;}
    
    public int getYCoordinate() {return yCoordinate;}
}
