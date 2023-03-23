package Battle;

import java.util.ArrayList;

/**
 * A class that takes the player through a normal battle sequence.
 * @author Ian King
 */
public class NormalBattle extends Battle
{
    public NormalBattle(ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
    {
        super(enemyTeam, playerTeam);
        startingText = "Battle: S T A R T";
    }
}
