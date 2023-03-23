package Battle;

import Exploration.Wilderness;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating Fire enemies.
 * @author Ian King
 */
public class FireEnemy extends Enemy
{
    private static ArrayList<String> speciesNames = new ArrayList<>();
    
    public FireEnemy(Wilderness currentLocation)
    {
        super(currentLocation);
        element = "Fire";
        name = createName();
        setDescription();
    }
    
    public FireEnemy(int level)
    {
        super(level);
        element = "Fire";
        name = createName();
        setDescription();
    }
    
    public FireEnemy(Wilderness currentLocation, int level)
    {
        super(currentLocation, level);
        element = "Fire";
        name = createName();
        setDescription();
    }
    
    public FireEnemy(String name, String description, int level)
    {
        super(name, description, level);
        element = "Fire";
    }
    
    public static void createSpeciesNames()
    {
        speciesNames.add("Antic"); // Ant-like
        speciesNames.add("Riteg"); // Tiger-like
        speciesNames.add("Drenar"); // Fox-like
        speciesNames.add("Gondra"); // Dragon-like
        speciesNames.add("Malas"); // Salamander-like
    }
    
    private String createName()
    {
        return createElementDescriptor() + getSpecies();
    }
    
    private void setDescription()
    {
        description = createDescription();
    }
    
    @Override
    protected String createDescription()
    {
        return "It's a " + name + "! " + statSpreadDescription;
    }
    
    @Override
    protected String createElementDescriptor()
    {
        String descriptor = "";
        
        switch(new Random().nextInt(4))
        {
            case 0:
                descriptor = "Fire ";
                break;
            case 1:
                descriptor = "Pyro ";
                break;
            case 2:
                descriptor = "Blazing ";
                break;
            case 3:
                descriptor = "Charred ";
                break;
        }
        
        return descriptor;
    }
    
    private String getSpecies()
    {
        int randNum = new Random().nextInt(speciesNames.size());
        return speciesNames.get(randNum);
    }
}
