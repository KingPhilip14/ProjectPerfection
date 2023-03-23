package Battle;

import java.util.ArrayList;

/**
 * A class to create attacks that heal an entire party.
 * @author Ian King
 */
public class TeamHealingAttack extends HealingAttack
{   
    public TeamHealingAttack(String name, String description, double healRate, int cooldown)
    {
        super.name = name;
        super.description = description;
        this.healRate = healRate;
        super.cooldown = cooldown;
    }
    
    public void healEnemies(ArrayList<Enemy> team)
    {
        for(Enemy target : team)
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
        }
        
        canUse = false;
        setNextAvailableTurn(Battle.getCurrentTurn());
    }
    
    public void healPlayers(ArrayList<Player> team, int currentTurn)
    {
        for(Player target : team)
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
        }
        
        canUse = false;
        setNextAvailableTurn(Battle.getCurrentTurn());
    }
    
    @Override
    public String toString()
    {
        return "\t" + this.name + ":\n\t\t" + this.description + "\n\t\tCooldown: " + cooldown 
                + "\n\t\tHeals Entire Team" + "\n\t\tHealing Rate: " + (int)(this.healRate * 100) + "%";
    }
}
