package Utilites;

import Battle.Player;

/**
 * A class for comparing Player speed
 * @author Ian King
 * @version 4/17/2022
 */
public class PlayerSpeedComparator implements Comparator<Player>
{
    @Override
    public int compare(Player player1, Player player2)
    {
        if(player1 == null || player2 == null)
        {
            throw new NullPointerException("A Player is null.");
        }
        
        int p1Speed = player1.getSpeed().getValue();
        int p2Speed = player2.getSpeed().getValue();
        
        if(p1Speed < p2Speed)
        {
            return 1;
        }
        else if(p1Speed == p2Speed)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
