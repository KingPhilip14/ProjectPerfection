/*
 * A class for creating and displaying menus to the player.
 */
package Utilites;

import Game.MainGame;
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
        MainGame.printlnln(message);
//        System.out.println(message);
//        String invalidMessage = "Please enter a value between " + min + "-" + max + ":";
        scan = new Scanner(System.in);
        
        while(!scan.hasNextInt())
        {
            invalidResponse = scan.nextLine();
//            System.out.println(invalidMessage);
            System.out.println();
            MainGame.printlnln(message);
//            System.out.println(message);
        }
        input = scan.nextInt();
        
        while(input < min || input > max)
        {
            invalidResponse = scan.nextLine();
            System.out.println();
            MainGame.printlnln(message);
//            System.out.println(invalidMessage);
            
            while(!scan.hasNextInt())
            {
                invalidResponse = scan.nextLine();
                System.out.println();
                MainGame.printlnln(message);
//                System.out.println(invalidMessage);
            }
            input = scan.nextInt();
        }
        return input;
    }
}