package Battle;

/**
 * A class to create attacks that heal single targets.
 * @author Ian King
 */
public class SingleHealingAttack extends HealingAttack
{
    public SingleHealingAttack(String name, String description, double healRate)
    {
        super.name = name;
        super.description = description;
        super.healRate = healRate;
        super.cooldown = 0;
    }
    
    public SingleHealingAttack(String name, String description, double healRate, int cooldown)
    {
        super.name = name;
        super.description = description;
        super.healRate = healRate;
        super.cooldown = cooldown;
    }
    
    public void activateHealing(Character target)
    {
        int amountToHeal = (int)Math.round(target.getMaxHealth() * healRate);
        if(target.getCurrentHealth() + amountToHeal > target.getMaxHealth())
        {
            target.setCurrentHealth(target.getMaxHealth());
        }
        else
        {
            target.setCurrentHealth(target.getCurrentHealth() + amountToHeal);
        }
        
        canUse = false;
        setNextAvailableTurn(Battle.getCurrentTurn());
    }
    
    @Override
    public String toString()
    {
        return "\t" + this.name + ":\n\t\t" + this.description + "\n\t\tCooldown: " + cooldown + "\n\t\tHeals 1 Target" + "\n\t\tHealing Rate: " + (int)(this.healRate * 100) + "%";
    }
}
