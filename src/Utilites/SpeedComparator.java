package Utilites;

import Battle.Enemy;
import Battle.Player;

/**
 * An individual comparator class to compare the speed between a Player and an Enemy.
 * @author Ian King
 */
public class SpeedComparator implements DuelComparator<Player, Enemy>
{
    @Override
    public int compare(Player player, Enemy enemy)
    {
        if(player == null || enemy == null)
        {
            throw new NullPointerException("A Character is null.");
        }
        
        int playerSpeed = player.getSpeed().getValue();
        int enemySpeed = enemy.getSpeed().getValue();
        
        if(playerSpeed < enemySpeed)
        {
            return 1;
        }
        else if(playerSpeed == enemySpeed)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
