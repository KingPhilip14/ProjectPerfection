package Battle;

import Game.MainGame;
import Utilites.MenuHelper;
import java.util.ArrayList;

/**
 * A class for creating a tutorial battle
 * @author Ian King
 */
public abstract class TutorialBattle extends Battle
{
    Player player;
    
    public TutorialBattle(Enemy enemy, Player player)
    {
        super(enemy, player);
        this.player = player;
        startingText = "Tutorial Battle: S T A R T";
    }

    public TutorialBattle(ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
    {
        super(enemyTeam, playerTeam);
    }
    
    @Override
    public void start(int gold, boolean result) 
    {
        intro();
        
        super.start(gold, result);
    }
    
    /**
     * This method prints out an introduction to player before starting the tutorial.
     */
    protected abstract void intro();
    
    protected void promptForTutorial(String message)
    {
        int response = MenuHelper.displayMenu(message, 1, 2);
        
        switch(response)
        {
            case 1:
                MainGame.clearScreen();
                explainBattles();
                MainGame.printlnln("Alright! Win so you can move on!");
                break;
            case 2:
                MainGame.printlnln("\nAlright! Win so you can move on!");
                break;
        }
        
        MainGame.promptToEnter();
    }
    
    /*
        interfaceTutorial();
        
        matchupTutorial();
        
        targetingTutorial();
        
        classTutorial();
        
        aggroTutorial();
        
        cooldownTutorial();
    */
    protected abstract void explainBattles();
}
