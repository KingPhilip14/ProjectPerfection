package Battle;

import java.util.ArrayList;

/**
 * A class for creating playable Characters
 * @author Ian King
 */
public abstract class Character extends GameProperty implements Combatable
{
    protected ArrayList<Attack> currentAttacks = new ArrayList<>(4);
    protected ArrayList<Stat> listOfStats = new ArrayList<>(5);
    protected String element;
    protected int level;
    protected int maxHealth;
    protected int currentHealth;
    protected Stat attack = new Stat("Attack", this);
    protected Stat defense = new Stat("Defense", this);
    protected Stat rangedAttack = new Stat("R. Attack", this);
    protected Stat rangedDefense = new Stat("R. Defense", this);
    protected Stat speed = new Stat("Speed", this);
    
    public ArrayList<Stat> getStats() {return listOfStats;}
    
    @Override
    public ArrayList<Attack> getCurrentAttacks() {return currentAttacks;}
    
    @Override
    public void setCurrentAttacks(ArrayList<Attack> newList) {currentAttacks = newList;}
    
    @Override
    public String getElement() {return element;}
    
    @Override
    public void setElement(String newElement) {element = newElement;}
    
    @Override
    public int getLevel() {return level;}
    
    @Override
    public void setLevel(int newLevel) {level = newLevel;}
    
    @Override
    public int getMaxHealth() {return maxHealth;}
    
    @Override
    public void setMaxHealth(int newMaxHealth) {maxHealth = newMaxHealth;}
    
    @Override
    public int getCurrentHealth() {return currentHealth;}
    
    @Override
    public void setCurrentHealth(int newHealth) {currentHealth = newHealth;}
    
    @Override 
    public Stat getAttack() {return attack;}
    
    @Override
    public void setAttack(int newAttack) {attack.setValue(newAttack);}
    
    @Override
    public Stat getDefense() {return defense;}
    
    @Override
    public void setDefense(int newDefense) {defense.setValue(newDefense);}
    
    @Override
    public Stat getRangedAttack() {return rangedAttack;}
    
    @Override
    public void setRangedAttack(int newRangedAttack) {rangedAttack.setValue(newRangedAttack);}
    
    @Override
    public Stat getRangedDefense() {return rangedDefense;}
    
    @Override
    public void setRangedDefense(int newRangedDefense) {rangedDefense.setValue(newRangedDefense);}
    
    @Override
    public Stat getSpeed() {return speed;}
    
    @Override
    public void setSpeed(int newSpeed) {speed.setValue(newSpeed);}
    
    @Override
    public void displayHealth()
    {
        String currentHP = String.format("%,d", currentHealth);
        String maxHP = String.format("%,d", maxHealth);
        double healthPercent = (((double)currentHealth / (double)maxHealth)) * 100;
        String healthPercentString = String.format("%,d", (int)healthPercent);
        System.out.println(this.getName() + "'s HP: " + currentHP + "/" + maxHP + " (" + 
                healthPercentString + "%)");
    }
    
    public static int highestPlayerLV(ArrayList<Player> playerTeam)
    {
        int highestLevel = playerTeam.get(0).getLevel();
        
        for(int i = 1; i < playerTeam.size(); i++)
        {
            if(playerTeam.get(i).getLevel() > highestLevel)
            {
                highestLevel = playerTeam.get(i).getLevel();
            }
        }
        
        return highestLevel;
    }
    
    public static int lowestPlayerLV(ArrayList<Player> playerTeam)
    {
        int lowestLevel = playerTeam.get(0).getLevel();
        
        for(int i = 1; i < playerTeam.size(); i++)
        {
            if(playerTeam.get(i).getLevel() < lowestLevel)
            {
                lowestLevel = playerTeam.get(i).getLevel();
            }
        }
        
        return lowestLevel;
    }
    
    public boolean isDead()
    {
        return currentHealth == 0;
    }
    
    public boolean hasDebuff()
    {
        for(Stat s : listOfStats)
        {
            if(s.getIsDebuffActive())
            {
                return true;
            }
        }
        
        return false;
    }
    
    public int getHighestAttackStat()
    {
        if(attack.getValue() > rangedAttack.getValue())
        {
            return attack.getValue();
        }
        else if(rangedAttack.getValue() > attack.getValue())
        {
            return rangedAttack.getValue();
        }
        else
        {
            return attack.getValue();
        }
    }
    
    public int getHighestDefenseStat()
    {
        if(defense.getValue() > rangedDefense.getValue())
        {
            return defense.getValue();
        }
        else if(rangedDefense.getValue() > defense.getValue())
        {
            return rangedDefense.getValue();
        }
        else
        {
            return defense.getValue();
        }
    }
    
    protected final void populateListsOfStats()
    {
        listOfStats.add(attack);
        listOfStats.add(defense);
        listOfStats.add(rangedAttack);
        listOfStats.add(rangedDefense);
        listOfStats.add(speed);
    }
    
    public String toBattleStats()
    {
        return "\n\t" + this.name + "'s level: " + this.level + "\n\tStats: \n\t\tHealth: " + this.currentHealth + "/" + this.maxHealth +
                "\n\t\t" + this.attack.toString() + "\n\t\t" + this.defense.toString() + 
                "\n\t\t" + this.rangedAttack.toString() + "\n\t\t" + 
                this.rangedDefense.toString() + "\n\t\t" + this.speed.toString();
    }
}
