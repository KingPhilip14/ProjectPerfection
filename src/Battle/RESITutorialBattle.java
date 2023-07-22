package Battle;

import Game.MainGame;
import Utilites.MenuHelper;
import java.util.ArrayList;

/**
 * A class for making the RESI tutorial battle sequence.
 * @author ianth
 */
public class ResiTutorialBattle  extends TutorialBattle
{
    public ResiTutorialBattle(ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
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
        String message = "Select the tutorial you want to view:"
                + "\n\t1) R.E.S.I. Battles\n\t2) Cheer Partners\n\t3) Combo Attacks\n\t4) Done";
        int input = MenuHelper.displayMenu(message, 1, 4);
        
        // If the input isn't the player wanting to quit, recursively call the method until it is.
        switch(input)
        {
            case 1:
                MainGame.resiTutorial();
                explainBattles();
                break;
            case 2:
                MainGame.cheerPartnerTutorial();
                explainBattles();
                break;
            case 3:
                MainGame.comboAttackTutorial();
                explainBattles();
                break;
            default:
                MainGame.clearScreen();
                break;
        }
    }
    
    @Override
    /**
     * This overridden method changes the RESI Bot's element depending on the player's element.
     */
    protected void attackWithOffense(Attack attack, Player player, Enemy target)
    {
        super.attackWithOffense(attack, player, target);
        
        if(attack.getAttackHit() && !target.isDead())
        {
            ResiEnemy enemy = ((ResiEnemy)target);
        
            enemy.setElement(player.getElement());

            MainGame.printlnln(enemy.name + "'s element is now " + enemy.element + "!", 25);

            MainGame.promptToEnter();
        }
    }
}
