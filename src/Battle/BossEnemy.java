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
        
        Stat stat;
        // Loops to add the stats to the Boss enemy
        for(int i = 0; i < stats.size(); i++)
        {
            stat = this.listOfStats.get(i);
            stat.setValue(stats.remove(i));
        }
    }
    
    @Override
    protected String createElementDescriptor()
    {
        String desc = "It's capable of powerfully using " + element.toLowerCase() + "!";
        return desc;
    }
    
    @Override
    protected String createDescription()
    {
        return "It's a " + name + "! " + statSpreadDescription;
    }
    
    private void addElementDescriptor()
    {
        elementDescriptor = createElementDescriptor();
        this.description += " " + elementDescriptor;
    }
}
