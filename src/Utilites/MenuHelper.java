/*
 * A class for creating and displaying menus to the player.
 */
package Utilites;

import Battle.Player;
import Game.MainGame;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ianth
 */
public class MenuHelper
{
    private static int input;
    private static String invalidResponse, stringInput;
    private static Scanner scan;
    
    // Displays the menu to prompt the player for their choices
    public static int displayMenu(String message, int min, int max)
    {
        MainGame.printlnln(message, 5);
//        System.out.println(message);
//        String invalidMessage = "Please enter a value between " + min + "-" + max + ":";
        scan = new Scanner(System.in);
        
        while(!scan.hasNextInt())
        {
            invalidResponse = scan.nextLine();
//            System.out.println(invalidMessage);
            MainGame.printlnln(message, 25);
//            System.out.println(message);
        }
        input = scan.nextInt();
        
        while(input < min || input > max)
        {
            invalidResponse = scan.nextLine();
            MainGame.printlnln(message, 25);
//            System.out.println(invalidMessage);
            
            while(!scan.hasNextInt())
            {
                invalidResponse = scan.nextLine();
                MainGame.printlnln(message, 25);
//                System.out.println(invalidMessage);
            }
            input = scan.nextInt();
        }
        return input;
    }
}