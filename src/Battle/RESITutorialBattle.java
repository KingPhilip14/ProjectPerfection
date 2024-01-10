package Battle;

import Game.MainGame;
import Utilites.MenuHelper;
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
        MainGame.printlnln("Pulchra is under attack! Anahita and the others are being challenged by a robot!");
        
        String message = "Would you like the final tutorial on fighting these robots?\n\t1) Yes\n\t2) No";
        
        super.promptForTutorial(message);
//        MainGame.wait(2000);
//        MainGame.clearScreen();
    }
    
    @Override
    protected void explainBattles()
    {
        prompt();
    }

    private void prompt()
    {
        String message = "Select the tutorial you want to view:"
                + "\n\t1) R.E.S.I. Battles\n\t2) Cheer Partners\n\t3) Combo Attacks\n\t4) Done";
        int input = MenuHelper.displayMenu(message, 1, 4);
        
        // If the input isn't the player wanting to quit, recursively call the method until it is.
        switch(input)
        {
            case 1:
                MainGame.resiTutorial();
                break;
            case 2:
                MainGame.cheerPartnerTutorial();
                break;
            case 3:
                MainGame.comboAttackTutorial();
                break;
            default:
                MainGame.clearScreen();
                return;
        }

        prompt();   
    }
    
    // @Override
    // /**
    //  * This overridden method changes the RESI Bot's element depending on the player's element.
    //  */
    // protected void attackWithOffense(Attack attack, Player player, Enemy target)
    // {
    //     super.attackWithOffense(attack, player, target);
        
    //     if(attack.getAttackHit() && !target.isDead())
    //     {
    //         RESIEnemy enemy = ((RESIEnemy)target);
        
    //         enemy.setElement(player.getElement());

    //         MainGame.printlnln(enemy.name + "'s element is now " + enemy.element + "!");

    //         MainGame.promptToEnter();
    //     }
    // }
}
