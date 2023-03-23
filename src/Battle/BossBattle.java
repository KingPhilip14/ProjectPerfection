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
        baseGoldAmt = 250;
        startingText = "The outcome of this battle could change everything...!\n\n Battle: S T A R T";
    }   
}
