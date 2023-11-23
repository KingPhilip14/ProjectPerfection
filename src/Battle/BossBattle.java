package Battle;

import java.util.ArrayList;

/**
 * A class for taking the player through a boss battle sequence.
 * @author Ian King
 */
public class BossBattle extends Battle
{
    public BossBattle(ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
    {
        super(enemyTeam, playerTeam);
        baseGoldAmt = 210;
        startingText = "The outcome of this battle could change everything...!\n\nBoss Battle: S T A R T";
    }   

    @Override
    protected void activateEnemyAI(Enemy enemy)
    {
        if((currentTurn == 1) || (currentTurn == enemy.getBuffAttack().nextAvailableTurn + 2 && 
            enemy.getBuffAttack().canUse()))
        {
            // Activate the buff on the first turn or if it's two turns after the buff could be used, use it
            enemy.getBuffAttack().activateBuff(enemy);
        }
        else
        {
            attackPlayer(enemy, getAdjacentPlayers(enemy));
        }
    }
}
