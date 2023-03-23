package Battle;

import Exploration.Wilderness;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating RESI Bot enemies.
 * @author Ian King
 */
public class RESIEnemy extends Enemy
{
    // R.E.S.I. Bot: Research Extraction Surveillance Investigator Bot
    public RESIEnemy(Wilderness currentLocation)
    {
        super(currentLocation);
        createElement(currentLocation);
        name = createName();
        improveOffense();
    }
    
    public RESIEnemy(int level, Wilderness currentLocation)
    {
        super(level);
        createElement(currentLocation);
        name = createName();
        improveOffense();
    }
    
    private String createName()
    {
        return "R.E.S.I. Bot: " + createElementDescriptor();
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
    /**
     * This overridden method changes the RESI Bot's element and name.
     */
    public void setElement(String newElement) 
    {
        super.setElement(newElement);
        createElementDescriptor();
    }
    
    
    
    @Override
    protected String createElementDescriptor()
    {
        String descriptor = "";
        
        switch(element)
        {
            case "Fire":
                descriptor = "Kai";
                break;
            case "Water":
                descriptor = "Drowned";
                break;
            case "Earth":
                descriptor = "Rocky";
                break;
            case "Ice":
                descriptor = "Frosted";
                break;
            case "Wind":
                descriptor = "Twister";
                break;
            case "Electric":
                descriptor = "Galvanic";
                break;
        }
        
        return descriptor;
    }
    
    private void createElement(Wilderness currentLocation)
    {
        ArrayList<String> elements = currentLocation.getLocalElements();
        int randomNum = new Random().nextInt(elements.size());
        
        String randomElement = elements.get(randomNum);
        element = randomElement;
    }
    
    /**
     * Adds extra points to offensive stats to make RESI enemies tougher
     */
    private void improveOffense()
    {
        this.attack.setValue(attack.getValue() + 10);
        this.rangedAttack.setValue(rangedAttack.getValue() + 10);
        this.speed.setValue(speed.getValue() + 10);
    }
}
