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
    private static ArrayList<OffensiveAttack> allOffensiveAttacks; 
    private static ArrayList<BuffAttack> allBuffAttacks; 
    private static ArrayList<DebuffAttack> allDebuffAttacks; 
    private static ArrayList<HealingAttack> allHealingAttacks;
    
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
    
    /**
     * Populates respective ArrayLists with all possible attacks a RESI Enemy can learn.
     */
    public static void populateAllAttacks()
    {
        allOffensiveAttacks = new ArrayList<>(10);
        allDebuffAttacks = new ArrayList<>(4);
        allBuffAttacks = new ArrayList<>(4);
        allHealingAttacks = new ArrayList<>(2);
        
        // Indexes 0-9
        allOffensiveAttacks.add(new OffensiveAttack("Rushdown", "The user rushes at the target and tackles them with impressive force.", 80, "Attack"));
        allOffensiveAttacks.add(new OffensiveAttack("Elemental Burst", "The user emits a powerful wave of energy based on their element.", 90, "R. Attack"));
        allOffensiveAttacks.get(1).setAccuracy(80);
        allOffensiveAttacks.add(new OffensiveAttack("Wild Fever", "The user uses its wild instinct to cause massive damage.", 110, "Attack"));
        allOffensiveAttacks.get(2).setAccuracy(65);
        allOffensiveAttacks.add(new OffensiveAttack("Elemental Pulse", "The user emits a shock wave of energy based on their element.", 80, "R. Attack"));
        allOffensiveAttacks.add(new OffensiveAttack("Elemental Blast", "The user emits an overwhelming wave of energy.", 100, "R. Attack"));
        allOffensiveAttacks.add(new OffensiveAttack("Fury Strikes", "The user strikes with many quick blows in succession.", 75, "Attack"));
        allOffensiveAttacks.add(new OffensiveAttack("Elemental Wrath", "The user uses raw, elemental energy in the area to inflict damage.", 85, "R. Attack"));
        allOffensiveAttacks.add(new OffensiveAttack("Outrage", "Using their raw strength, the user attacks with all its might.", 90, "Attack"));
        allOffensiveAttacks.get(7).setAccuracy(70);
        allOffensiveAttacks.add(new OffensiveAttack("Piercing Strike", "The user focuses where to land their attack, resulting in more critical hits.", 65, "Attack"));
        allOffensiveAttacks.get(8).setCritRate(0.35);
        allOffensiveAttacks.get(8).setAccuracy(85);
        allOffensiveAttacks.add(new OffensiveAttack("Twister", "The user creates a destructive twister with their elemental property.", 70, "R. Attack"));
        
        // Indexes 10-13
        allDebuffAttacks.add(new DebuffAttack("Shattered Spirit", "Emitting a negative aura, the user lowers the target's attack stats for 2 turns.", "Attack,R. Attack", 0.75, 3, 2));
        allDebuffAttacks.get(0).setAccuracy(95);
        allDebuffAttacks.add(new DebuffAttack("Elemental Purge", "The user strips the target of their elemental strengths, weakening all stats for 2 turns.", "All", 0.75, 5, 2));
        allDebuffAttacks.get(1).setAccuracy(85);
        allDebuffAttacks.add(new DebuffAttack("Trip Up", "The user creates an uneven terrain to lower the target's speed for 3 turns.", "Speed", 0.5, 4, 3));
        allDebuffAttacks.add(new DebuffAttack("Piercing Shout", "The user shouts loudly to lower the target's defenses for 2 turns.", "Defense,R. Defense", 0.75, 3, 2));
        
        // Indexes 14-17
        allBuffAttacks.add(new BuffAttack("Heightened Spirit", "The user hypes themselves up to increase their attack stats for 2 turns.", "Attack,R. Attack", 1.25, 3, 2));
        allBuffAttacks.add(new BuffAttack("Careful Guard", "Taking a precautious stance, the user increases their defenses for 2 turns.", "Defense,R. Defense", 1.25, 3, 2));
        allBuffAttacks.add(new BuffAttack("Agility", "The user moves more swiftly to increase their speed for 3 turns.", "Speed", 1.5, 4, 3));
        allBuffAttacks.add(new BuffAttack("Absorption", "The user gathers all excess energy around it to increase all its stats for 2 turns.", "All", 1.5, 5, 2));
        
        // Indexes 18-19
        allHealingAttacks.add(new SingleHealingAttack("Restful Spirit", "The user calms the target's spirits to restore 30% their total HP.", 0.3, 2));
        allHealingAttacks.add(new TeamHealingAttack("Soothing Aura", "The user emits a gentle aura to restore 30% HP to its entire team.", 0.3, 2));
    }
}
