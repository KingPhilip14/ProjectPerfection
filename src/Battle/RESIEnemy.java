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
    
    /*
    IMPORTANT: The enemy AI looks for a healing move for RESI bots, which can cause a null pointer reference. Either
    change the enemyAI in the RESIBattle class, or fix it in the abstract battle class
    */
    
    
    
    private static ArrayList<OffensiveAttack> allOffensiveResiAttacks; 
    private static ArrayList<BuffAttack> allBuffResiAttacks; 
    private static ArrayList<DebuffAttack> allDebuffResiAttacks;
    
    // R.E.S.I. Bot: Research Extraction Surveillance Investigator Bot
    public RESIEnemy(Wilderness currentLocation)
    {
        super(currentLocation);
        createElement(currentLocation);
        name = createName();
        setDescription();
        improveOffense();
        populateCurrentAttacks();
    }
    
    public RESIEnemy(int level, Wilderness currentLocation)
    {
        super(level);
        createElement(currentLocation);
        name = createName();
        setDescription();
        improveOffense();
        populateCurrentAttacks();
    }
    
    public RESIEnemy(int level, String element)
    {
        super(level);
        this.element = element;
        name = createName();
        setDescription();
        improveOffense();
        populateCurrentAttacks();
    }
    
    private String createName()
    {
        String result;
        
        switch(new Random().nextInt(10))
        {
            case 0:
                result = "Alpha";
                break;
            case 1:
                result = "Beta";
                break;
            case 2:
                result = "Gamma";
                break;
            case 3:
                result = "Delta";
                break;
            case 4:
                result = "Zeta";
                break;
            case 5:
                result = "Mu";
                break;
            case 6:
                result = "Phi";
                break;
            case 7:
                result = "Chi";
                break;
            case 8:
                result = "Psi";
                break;
            default:
                result = "Omicron";
                break;
        }
        
        return "R.E.S.I. Bot " + result;
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
    
    /**
     * Populates respective ArrayLists with all possible attacks a RESI Enemy can learn.
     */
    public static void populateAllAttacks()
    {
        allOffensiveResiAttacks = new ArrayList<>(7);
        allDebuffResiAttacks = new ArrayList<>(4);
        allBuffResiAttacks = new ArrayList<>(3);
        
        // Indexes 0-6
        allOffensiveResiAttacks.add(new OffensiveAttack("Laser Blast", "The user charges a laser and fires it at the target.", 90, "R. Attack"));
        allOffensiveResiAttacks.add(new OffensiveAttack("Metal Grip", "The user grabs the target with a metal claw and crushes them.", 105, "Attack"));
        allOffensiveResiAttacks.get(1).setAccuracy(90);
        allOffensiveResiAttacks.add(new OffensiveAttack("Engine Rush", "The user rushes into the target with immense force by using it's engines to fly at high speeds.", 110, "Attack"));
        allOffensiveResiAttacks.get(2).setAccuracy(85);
        allOffensiveResiAttacks.add(new OffensiveAttack("Metal Spike", "The user shoots a sharp, metal spike at the target to inflict damage.", 95, "R. Attack"));
        allOffensiveResiAttacks.add(new OffensiveAttack("RESI Protocol I: Barrage", "The user throws a barrage of attacks at the target to overwhelm them.", 100, "Attack"));
        allOffensiveResiAttacks.add(new OffensiveAttack("RESI Protocol II: Blast", "The user flies high in the sky to rain a myriad of lasers on the target.", 115, "R. Attack"));
        allOffensiveResiAttacks.get(5).setAccuracy(70);
        allOffensiveResiAttacks.add(new OffensiveAttack("RESI Protocol III: Arsenal", "The user uses a combination of missles, lasers, and metal projectiles to inflict damage.", 120, "R. Attack"));
        allOffensiveResiAttacks.get(6).setAccuracy(70);
        
        // Indexes 7-10
        allDebuffResiAttacks.add(new DebuffAttack("Extraction", "The user uses a needle-like apparatus to drain the target of their strength, weakening their offenses.", "Attack,R. Attack", 0.5, 3, 2));
        allDebuffResiAttacks.get(0).setAccuracy(95);
        allDebuffResiAttacks.add(new DebuffAttack("Metalic Screech", "By using its metatl to make a screeching sound, the user lowers the target's ranged defense.", "R. Defense", 0.5, 5, 2));
        allDebuffResiAttacks.get(1).setAccuracy(90);
        allDebuffResiAttacks.add(new DebuffAttack("Blinding Light", "The user blinds the target with a bright laser to lower their defense and speed.", "Defense,Speed", 0.5, 4, 3));
        allDebuffResiAttacks.add(new DebuffAttack("Cable Wire", "The user wraps the target's feet in thick cable wire, lowering their speed.", "Speed", 0.5, 3, 2));
        
        // Indexes 11-13
        allBuffResiAttacks.add(new BuffAttack("Light Metal", "The user sheds a layer of metal, increasing their speed for 3 turns.", "Speed", 1.5, 2, 3));
        allBuffResiAttacks.add(new BuffAttack("Heavy Shield", "The user creates a heavy shield that doubles their defense for 3 turns", "Defense", 2, 4, 3));
        allBuffResiAttacks.add(new BuffAttack("Sharpen", "The user sharpens their weapons to increase their attack for 2 turns.", "Speed", 1.5, 1, 2));
    }
    
    private void populateCurrentAttacks()
    {
        Random rand = new Random();
        int numOfOffensive = rand.nextInt(3) + 1;
        
        if(isOffensive)
        {
            /*
            Offensive RESI Bots will have 3 attacks and one buff attack at all times
            */
            for(int i = 0; i < 3; i++)
            {
                currentAttacks.add(allOffensiveResiAttacks.remove(rand.nextInt(allOffensiveResiAttacks.size())));
            }
            
            currentAttacks.add(allBuffResiAttacks.remove(rand.nextInt(allBuffResiAttacks.size())));
        }
        else
        {
            /*
            Defensive RESI will always have 2 attacks, one buff, and one debuff attack
            */
            for(int i = 0; i < numOfOffensive; i++)
            {
                currentAttacks.add(allOffensiveResiAttacks.remove(rand.nextInt(allOffensiveResiAttacks.size())));
            }
            
            currentAttacks.add(allBuffResiAttacks.remove(rand.nextInt(allBuffResiAttacks.size())));
            
            currentAttacks.add(allDebuffResiAttacks.remove(rand.nextInt(allDebuffResiAttacks.size())));
        }
        
        populateAllAttacks();
    }
}
