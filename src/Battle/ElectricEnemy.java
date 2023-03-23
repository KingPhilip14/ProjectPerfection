package Battle;

import Exploration.Wilderness;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating Electric enemies.
 * @author Ian King
 */
public class ElectricEnemy extends Enemy
{
    private static ArrayList<String> speciesNames = new ArrayList<>();
    
    public ElectricEnemy(Wilderness currentLocation)
    {
        super(currentLocation);
        element = "Electric";
        name = createName();
        setDescription();
    }
    
    public ElectricEnemy(int level)
    {
        super(level);
        element = "Electric";
        name = createName();
        setDescription();
    }
    
    public ElectricEnemy(Wilderness currentLocation, int level)
    {
        super(currentLocation, level);
        element = "Electric";
        name = createName();
        setDescription();
    }
    
    public ElectricEnemy(String name, String description, int level)
    {
        super(name, description, level);
        element = "Electric";
    }
    
    public static void createSpeciesNames()
    {
        speciesNames.add("Hilabee"); // Bee-like
        speciesNames.add("Arich"); // Spider-like
        speciesNames.add("Ogeck"); // Gecko-like
        speciesNames.add("Bufo"); // Toad-like
        speciesNames.add("Phorus"); // Electric eel-like
        speciesNames.add("Torped"); // Electric ray-like
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
        
        switch(new Random().nextInt(3))
        {
            case 0:
                descriptor = "Electric ";
                break;
            case 1:
                descriptor = "Static ";
                break;
            case 2:
                descriptor = "Thundering ";
                break;
            case 3:
                descriptor = "Sparking ";
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
