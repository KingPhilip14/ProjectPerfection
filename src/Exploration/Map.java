package Exploration;

import Game.MainGame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class for managing the game map.
 * @author Ian King
 */
public class Map 
{
    char[][] map;
    
    public Map()
    {
        map = new char[33][85];
        makeMap();
    }
    
    private void makeMap()
    {
        String gameMapPath = "C:\\Users\\ianth\\OneDrive\\Documents\\GameMap.txt";
        File gameMap = new File(gameMapPath);
        
        try
        {
            Scanner scan = new Scanner(gameMap);
            int row = 0;
            int col = 0;
            
            // While the file has another line
            while(scan.hasNextLine())
            {
                map[row] = scan.nextLine().toCharArray();
                
                row++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Map not found!");
        }
    }
    
    public void printMap()
    {
        for(int row = 0; row < map.length; row++)
        {
            for(int col = 0; col < map[row].length; col++)
            {
                
                MainGame.print(map[row][col], 1);
//                System.out.print(map[row][col]);
            }
            
            System.out.println("");
        }
    }
    
    public void printWithCurrentLocation(Location currentLocation)
    {
        int xCoordinate = currentLocation.getXCoordinate();
        int yCoordinate = currentLocation.getYCoordinate();
        
        char marker = getMapChar(currentLocation);
        
        map[xCoordinate][yCoordinate] = 'O';
        
        printMap();
    }
    
    private char getMapChar(Location currentLocation)
    {
        char result;
        
        if(currentLocation instanceof Village)
        {
            result = '+';
        }
        else if(currentLocation.getName().equals("Zoni Village"))
        {
            result = '*';
        }
        else
        {
            result = 'x';
        }
        
        return result;
    }
    
    public void updateMap(Location location1, Location location2)
    {
        // Replace the current location marker with the other marker
        int xCoordinate = location1.getXCoordinate();
        int yCoordinate = location1.getYCoordinate();
        
        char marker = getMapChar(location1);
        map[xCoordinate][yCoordinate] = marker;
        
        // Replace the new, current location with the current location marker
        xCoordinate = location2.getXCoordinate();
        yCoordinate = location2.getYCoordinate();
        
        map[xCoordinate][yCoordinate] = 'O';
    }
}
