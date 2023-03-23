package Battle;

import Exploration.Wilderness;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating Wind enemies
 * @author Ian King
 */
public class WindEnemy extends Enemy
{
    private static ArrayList<String> speciesNames = new ArrayList<>();
    
    public WindEnemy(Wilderness currentLocation)
    {
        super(currentLocation);
        element = "Wind";
        name = createName();
        setDescription();
    }
    
    public WindEnemy(int level)
    {
        super(level);
        element = "Wind";
        name = createName();
        setDescription();
    }
    
    public WindEnemy(Wilderness currentLocation, int level)
    {
        super(currentLocation, level);
        element = "Wind";
        name = createName();
        setDescription();
    }
    
    public WindEnemy(String name, String description, int level)
    {
        super(name, description, level);
        element = "Wind";
    }
    
    public static void createSpeciesNames()
    {
        speciesNames.add("Airgle"); // Eagle-like
        speciesNames.add("Sino"); // Monkey-like
        speciesNames.add("Serpen"); // Serpent-like
        speciesNames.add("Sectin"); // Insect-like
        speciesNames.add("Fer"); // Fairy-like
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
                descriptor = "Wind ";
                break;
            case 1:
                descriptor = "Tempest ";
                break;
            case 2:
                descriptor = "Gale ";
                break;
            case 3:
                descriptor = "Zephyr ";
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
