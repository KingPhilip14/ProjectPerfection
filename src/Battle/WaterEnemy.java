package Battle;

import Exploration.Wilderness;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating Water enemies.
 * @author Ian King
 */
public class WaterEnemy extends Enemy
{
    private static ArrayList<String> speciesNames = new ArrayList<>();
    
    public WaterEnemy(Wilderness currentLocation)
    {
        super(currentLocation);
        element = "Water";
        name = createName();
        setDescription();
    }
    
    public WaterEnemy(int level)
    {
        super(level);
        element = "Water";
        name = createName();
        setDescription();
    }
    
    public WaterEnemy(Wilderness currentLocation, int level)
    {
        super(currentLocation, level);
        element = "Water";
        name = createName();
        setDescription();
    }
    
    public WaterEnemy(String name, String description, int level)
    {
        super(name, description, level);
        element = "Water";
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
    
    public static void createSpeciesNames()
    {
        speciesNames.add("Krobble"); // Crab-like
        speciesNames.add("Poss"); // Fish-like
        speciesNames.add("Turkle"); // Turtle-like
        speciesNames.add("Phini"); // Dolphin-like
        speciesNames.add("Chimla"); // Shark-like
        speciesNames.add("Puco"); // Octopus-like
    }
    
    private String createName()
    {
        return createElementDescriptor() + getSpecies();
    }
    
    @Override
    protected String createElementDescriptor()
    {
        String descriptor = "";
        
        switch(new Random().nextInt(4))
        {
            case 0:
                descriptor = "Water ";
                break;
            case 1:
                descriptor = "Aquatic ";
                break;
            case 2:
                descriptor = "Moist ";
                break;
            case 3:
                descriptor = "Damp ";
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
