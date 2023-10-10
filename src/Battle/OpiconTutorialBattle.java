package Battle;

import Game.MainGame;
import Utilites.MenuHelper;
import java.util.ArrayList;

/**
 * A class for making the Opicon Forest tutorial battle sequence.
 * @author Ian King
 */
public class OpiconTutorialBattle extends TutorialBattle
{
    public OpiconTutorialBattle(ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
    {
        super(enemyTeam, playerTeam);
        startingText = "Tutorial Battle: S T A R T";
    }
    
    @Override
    protected void intro()
    {
        MainGame.printlnlnWait("It's time to fight with more characters!", 25, 1000);
        
        String message = "Would you like the next, technical tutorial of how battles work?\n\t1) Yes\n\t2) No";
        
        super.promptForTutorial(message);
//        MainGame.wait(1000);
//        MainGame.clearScreen();
    }
    
    @Override
    protected void explainBattles()
    {
        String message = "Select the tutorial you want to view:"
                + "\n\t1) Attack Cooldown\n\t2) Aggro\n\t3) Classes\n\t4) Done";
        int input = MenuHelper.displayMenu(message, 1, 4);
        
        // If the input isn't the player wanting to quit, recursively call the method until it is.
        switch(input)
        {
            case 1:
                cooldownTutorial();
                explainBattles();
                break;
            case 2:
                aggroTutorial();
                explainBattles();
                break;
            case 3:
                classTutorial();
                explainBattles();
                break;
            default:
                MainGame.clearScreen();
                break;
        }
    }
    
    private void cooldownTutorial()
    {
        MainGame.printlnlnWait("First, let's go over how attacks work a little more in depth.", 25, 1500);
        MainGame.cooldownTutorial();
    }
    
    private void aggroTutorial()
    {
        MainGame.clearScreen();
        
        // Explains the aggro system
        MainGame.printlnlnWait("Next, Aggro:", 25, 1500);
        MainGame.println("You may wonder how the enemies will target you. Each of your characters have a value called "
                + "\"Aggro\".\nAfter every attack, their aggro will increase, forcing the enemy to target them.");
        
        MainGame.promptToEnter();
        
        MainGame.printlnln("Certain moves accrue more Aggro than others, and some classes affect this as well.\n"
                + "However, enemies will sometimes ignore this Aggro and target someone else, so be mindeful!");
        
        MainGame.promptToEnter();
    }
    
    private void classTutorial()
    {
        MainGame.printlnlnWait("Now that you have access to more characters, you have access to more classes! Let's discuss them.", 25, 1500);
        MainGame.classTutorial();
    }
}
