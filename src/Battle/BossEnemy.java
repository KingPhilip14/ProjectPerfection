package Battle;

import java.util.ArrayList;

/**
 * A class for manually creating boss enemies for specific points in the game.
 * @author Ian King
 */
public class BossEnemy extends Enemy
{
    public BossEnemy(String name, String description, String element, 
            int level, ArrayList<Attack> knownAttacks, ArrayList<Integer> statValues)
    {
        super(name, description, level, knownAttacks);
        populateListsOfStats();
        setStats(statValues);
        this.element = element;
        addElementDescriptor();
        xpYield = 65;
    }
    
    public void setStatDescription(String desc)
    {
        this.statSpreadDescription = desc;
    }
    
    private void setStats(ArrayList<Integer> stats) throws IllegalArgumentException
    {
        if(stats.size() < 6 || stats.size() > 6)
        {
            throw new IllegalArgumentException("The ArrayList of stats needs to be of size 6.");
        }
        
        // Assigns the health value
        this.maxHealth = stats.remove(0);
        this.currentHealth = maxHealth;
        
        // Loops to add the stats to the Boss enemy
        for(int i = 0; i < stats.size(); i++)
        {
            this.listOfStats.get(i).setValue(stats.get(i));
        }
    }
    
    @Override
    protected String createElementDescriptor()
    {
        String desc = "They are capable of powerfully using " + element.toLowerCase() + "!";
        return desc;
    }
    
    @Override
    protected String createDescription()
    {
        return "It's a " + name + "! " + statSpreadDescription;
    }
    
    @Override
    public String toBattleString()
    {
        return name + "'s level: " + level + "\n\t" + description; 
    }
    
    private void addElementDescriptor()
    {
        elementDescriptor = createElementDescriptor();
        this.description += " " + elementDescriptor;
    }
}
