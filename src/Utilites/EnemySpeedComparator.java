package Utilites;

import Battle.Enemy;
import Battle.Player;

/**
 * A class for comparing Enemy speeds
 * @author Ian King
 */
public class EnemySpeedComparator implements Comparator<Enemy>
{
    @Override
    public int compare(Enemy enemy1, Enemy enemy2)
    {
        if(enemy1 == null || enemy2 == null)
        {
            throw new NullPointerException("A Enemy is null.");
        }
        
        int e1Speed = enemy1.getSpeed().getValue();
        int e2Speed = enemy2.getSpeed().getValue();
        
        if(e1Speed < e2Speed)
        {
            return 1;
        }
        else if(e1Speed == e2Speed)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
