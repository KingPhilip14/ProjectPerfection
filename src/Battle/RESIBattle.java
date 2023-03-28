package Battle;

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
        baseGoldAmt = 175;
        startingText = "A L E R T! Incoming R.E.S.I. Bots!\n\n Battle: S T A R T";
    }
    
    @Override
    /**
     * This overridden method changes the RESI Bot's element depending on the player's element.
     */
    protected void attackWithOffense(Attack attack, Player player, Enemy target)
    {
        super.attackWithOffense(attack, player, target);
        
        ((RESIEnemy)target).setElement(player.getElement());
    }
    
    /**
     * If the enemy team doesn't solely consist of RESI enemies, an exception is thrown.
     * @param enemyTeam
     * @throws IllegalArgumentException 
     */
//    protected void validateTeam(ArrayList<Enemy> enemyTeam) throws IllegalArgumentException
//    {
//        for(Enemy e : enemyTeam)
//        {
//            if(!(e instanceof RESIEnemy))
//            {
//                throw new IllegalArgumentException ("The enemy teams needs to only contain RESI enemies.");
//            }
//        }
//    }
}