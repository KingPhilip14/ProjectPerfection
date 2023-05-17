package Battle;

import Game.MainGame;
import java.util.ArrayList;

/**
 * A class for making the RESI tutorial battle sequence.
 * @author ianth
 */
public class RESITutorialBattle  extends TutorialBattle
{
    public RESITutorialBattle(ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
    {
        super(enemyTeam, playerTeam);
        startingText = "Tutorial Battle: S T A R T";
    }
    
    @Override
    protected void intro()
    {
        MainGame.printlnlnWait("Pulchra is under attack! Anahita and the others are being challenged by a robot!", 25, 1000);
        
        String message = "Would you like the final tutorial on fighting these robots?\n\t1) Yes\n\t2) No";
        
        super.promptForTutorial(message);
//        MainGame.wait(2000);
//        MainGame.clearScreen();
    }
    
    @Override
    protected void explainBattles()
    {
        resiTutorial();
    }
    
    private void resiTutorial()
    {
        MainGame.resiTutorial();
    }
}
