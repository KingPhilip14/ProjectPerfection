package Utilites;

import Battle.OffensiveAttack;

/**
 * A class to compare the damage between different OffensiveAttacks
 * @author Ian King
 */
public class DamageComparator implements Comparator<OffensiveAttack>
{
    @Override
    public int compare(OffensiveAttack attack1, OffensiveAttack attack2)
    {
        if(attack1 == null || attack2 == null)
        {
            throw new NullPointerException("A Enemy is null.");
        }
        
        int damage1 = attack1.getBaseDamage();
        int damage2 = attack2.getBaseDamage();
        
        if(damage1 < damage2)
        {
            return 1;
        }
        else if(damage1 == damage2)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
