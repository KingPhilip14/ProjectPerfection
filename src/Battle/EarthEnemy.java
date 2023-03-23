package Battle;

import Exploration.Wilderness;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating Earth enemies.
 * @author Ian King
 */
public class EarthEnemy extends Enemy
{
    private static ArrayList<String> speciesNames = new ArrayList<>();
    
    public EarthEnemy(Wilderness currentLocation)
    {
        super(currentLocation);
        element = "Earth";
        name = createName();
        setDescription();
    }
    
    public EarthEnemy(int level)
    {
        super(level);
        element = "Earth";
        name = createName();
        setDescription();
    }
    
    public EarthEnemy(Wilderness currentLocation, int level)
    {
        super(currentLocation, level);
        element = "Earth";
        name = createName();
        setDescription();
    }
    
    public EarthEnemy(String name, String description, int level)
    {
        super(name, description, level);
        element = "Earth";
    }
    
    public static void createSpeciesNames()
    {
        speciesNames.add("Rhik"); // Rhino-like
        speciesNames.add("Copig"); // Pig-like
        speciesNames.add("Scarab"); // Beetle-like
        speciesNames.add("Taur"); // Bull-like
        speciesNames.add("Phantel"); // Elephant-like
        speciesNames.add("Krobble"); // Crab-like
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
                descriptor = "Earth ";
                break;
            case 1:
                descriptor = "Flowery ";
                break;
            case 2:
                descriptor = "Sandy ";
                break;
            case 3:
                descriptor = "Rugged ";
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
