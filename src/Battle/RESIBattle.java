package Battle;

import Game.MainGame;
import java.util.ArrayList;

/**
 * A class for taking the player through a RESI Bot battle sequence.
 * @author Ian King
 */
public class RESIBattle extends Battle
{
    public RESIBattle(ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
    {
        super(enemyTeam, playerTeam);
        baseGoldAmt = 155;
        startingText = "A L E R T! Incoming R.E.S.I. Bots!\n\nBattle: S T A R T";
    }
    
    // @Override
    // /**
    //  * This overridden method changes the RESI Bot's element depending on the player's element.
    //  */
    // protected void attackWithOffense(Attack attack, Player player, Enemy target)
    // {
    //     super.attackWithOffense(attack, player, target);
        
    //     if(attack.getAttackHit() && !target.isDead() && !player.getElement().equals(target.getElement()))
    //     {
    //         RESIEnemy enemy = ((RESIEnemy)target);
        
    //         enemy.setElement(player.getElement());

    //         MainGame.printlnln(enemy.name + "'s element is now " + enemy.element + "!");

    //         MainGame.promptToEnter();
    //     }
    // }
}
