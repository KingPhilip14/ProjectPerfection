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
        MainGame.printlnln("It's time to fight with more characters!");
        
        String message = "Would you like the next, technical tutorial of how battles work?\n\t1) Yes\n\t2) No";
        
        super.promptForTutorial(message);
//        MainGame.wait(1000);
//        MainGame.clearScreen();
    }
    
    @Override
    protected void explainBattles()
    {
        prompt();
        MainGame.printlnln("That's a bit more on how battles work! Good luck!");
    }

    private void prompt()
    {
        String message = "Select the tutorial you want to view:"
                + "\n\t1) Attack Cooldown\n\t2) Aggro\n\t3) Classes\n\t4) Done";
        int input = MenuHelper.displayMenu(message, 1, 4);
        
        // If the input isn't the player wanting to quit, recursively call the method until it is.
        switch(input)
        {
            case 1:
                MainGame.cooldownTutorial();
                break;
            case 2:
                MainGame.aggroTutorial();
                break;
            case 3:
                MainGame.classTutorial();
                break;
            default:
                MainGame.clearScreen();
                return; // end the recursive call if the user is done
        }

        prompt();
    }
}
