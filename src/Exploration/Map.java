package Exploration;

import Game.MainGame;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A class for managing the game map.
 * @author Ian King
 */
public class Map implements java.io.Serializable
{
    char[][] map;
    
    public Map()
    {
        map = new char[33][85];
        makeMap();
    }
    
    private void makeMap()
    {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("GameMap.txt");
        String string_map = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8)).lines().
                collect(Collectors.joining("\n"));
        
        Scanner scan = new Scanner(string_map);

        int row = 0;
        // While the file has another line
        while(scan.hasNextLine())
        {
            map[row] = scan.nextLine().toCharArray();
            row++;
        }
        
      
//        String gameMapPath = new File("").getAbsolutePath() + "\\src\\Exploration\\GameMap.txt";
//        File gameMap = new File(gameMapPath);
//        
//        try
//        {
//            Scanner scan = new Scanner(gameMap);
//            int row = 0;
//            
//            // While the file has another line
//            while(scan.hasNextLine())
//            {
//                map[row] = scan.nextLine().toCharArray();
//                
//                row++;
//            }
//        }
//        catch(FileNotFoundException e)
//        {
//            System.out.println("Map not found!");
//        }
    }
    
    public void printMap()
    {
        for(int row = 0; row < map.length; row++)
        {
            for(int col = 0; col < map[row].length; col++)
            {
                MainGame.print(String.valueOf(map[row][col]));
            }
            
            System.out.println("");
        }
    }
    
    public void printWithCurrentLocation(Location currentLocation)
    {
        int xCoordinate = currentLocation.getXCoordinate();
        int yCoordinate = currentLocation.getYCoordinate();
        
        map[xCoordinate][yCoordinate] = 'O';
        
        printMap();
    }
    
    private char getMapChar(Location currentLocation)
    {
        char result;
        
        if(currentLocation.getName().equals("Zoni Village"))
        {
            result = '*';
        }
        else if(currentLocation instanceof Town )
        {
            result = '+';
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
