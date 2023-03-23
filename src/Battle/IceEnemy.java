package Battle;

import Exploration.Wilderness;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating Ice enemies.
 * @author Ian King
 */
public class IceEnemy extends Enemy
{
    private static ArrayList<String> speciesNames = new ArrayList<>();
    
    public IceEnemy(Wilderness currentLocation)
    {
        super(currentLocation);
        element = "Ice";
        name = createName();
        setDescription();
    }
    
    public IceEnemy(int level)
    {
        super(level);
        element = "Ice";
        name = createName();
        setDescription();
    }
    
    public IceEnemy(Wilderness currentLocation, int level)
    {
        super(currentLocation, level);
        element = "Ice";
        name = createName();
        setDescription();
    }
    
    public IceEnemy(String name, String description, int level)
    {
        super(name, description, level);
        element = "Ice";
    }
    
    public static void createSpeciesNames()
    {
        speciesNames.add("Abomity"); // Yeti-like
        speciesNames.add("Volv"); // Wolf-like
        speciesNames.add("Polors"); // Polar bear-like
        speciesNames.add("Pencho"); // Penguin-like
        speciesNames.add("Taran"); // Reindeer-like
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
                descriptor = "Ice ";
                break;
            case 1:
                descriptor = "Frostbitten ";
                break;
            case 2:
                descriptor = "Chilly ";
                break;
            case 3:
                descriptor = "Frozen ";
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
