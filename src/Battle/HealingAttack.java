package Battle;

import java.util.ArrayList;

/**
 * A class for creating healing attacks.
 * @author Ian King
 */
public abstract class HealingAttack extends Attack
{
    protected double healRate;
    
    public double getHealingRate() {return healRate;}
    public void setHealingRate(double newRate) {healRate = newRate;}
}
