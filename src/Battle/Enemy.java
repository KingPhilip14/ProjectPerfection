package Battle;

import Exploration.Wilderness;
import Utilites.DamageComparator;
import Utilites.Sort;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A class for instantiating any type of enemy.
 * @author Ian King
 */
public abstract class Enemy extends Character
{
    private static ArrayList<OffensiveAttack> allOffensiveAttacks; 
    private static ArrayList<BuffAttack> allBuffAttacks; 
    private static ArrayList<DebuffAttack> allDebuffAttacks; 
    private static ArrayList<HealingAttack> allHealingAttacks;
    protected int totalStatPoints;
    protected boolean isOffensive;
    protected String statSpreadDescription;
    protected String elementDescriptor;
    protected String species;
    protected int xpYield = 15;
    protected abstract String createElementDescriptor();
    protected abstract String createDescription();
    
    public Enemy(Wilderness currentLocation)
    {
        createLevel(currentLocation);
        totalStatPoints = level * 65;
        generateStats();
        
        // RESI enemies will not get their moveset from this classes list of attacks
        if(!(this instanceof RESIEnemy))
        {
            populateCurrentAttacks();
        }
            
        populateListsOfStats();
    }
    
    public Enemy(int level)
    {
        this.level = level;
        totalStatPoints = level * 65;
        generateStats();
        
        // RESI enemies will not get their moveset from this classes list of attacks
        if(!(this instanceof RESIEnemy))
        {
            populateCurrentAttacks();
        }
        
        populateListsOfStats();
    }
    
    public Enemy(Wilderness currentLocation, int level)
    {
        this.level = level;
        totalStatPoints = level * 65;
        generateStats();
        
        // RESI enemies will not get their moveset from this classes list of attacks
        if(!(this instanceof RESIEnemy))
        {
            populateCurrentAttacks();
        }
        
        populateListsOfStats();
    }
    
    public Enemy(String name, String description, int level, ArrayList<Attack> knownAttacks)
    {
        this.name = name;
        this.description = description;
        this.level = level;
        totalStatPoints = level * 65;
        this.currentAttacks = knownAttacks;
        generateStats();
        populateCurrentAttacks();
    }
    
    public Enemy(String name, String description, int level)
    {
        this.name = name;
        this.description = description;
        this.level = level;
        totalStatPoints = level * 65;
        generateStats();
        populateCurrentAttacks();
    }
    
    public String getStatSpreadDesc() {return statSpreadDescription;}
    
    public int getXpYield() {return xpYield;}
    public void setXpYield(int yield) {xpYield = yield;}
    
    private void createLevel(Wilderness currentLocation)
    {
        Random rand = new Random();
        int max = currentLocation.getMAX_ENEMY_LV();
        int min = currentLocation.getMIN_ENEMY_LV();
        
        level = rand.nextInt(max - min) + min;
        
        if(level < 1)
        {
            level = 1;
        }
    }
    
    /**
     * Generates the stats of the enemy from one of 5 spread types (Offensive, defensive, 
     * physically oriented, range oriented, speed oriented).
     */
    private void generateStats()
    {
        Random rand = new Random();
        
        switch(rand.nextInt(5))
        {
            case 0:
                offensiveStatSpread();
                break;
            case 1: 
                defensiveStatSpread();
                break;
            case 2:
                physicalStatSpread();
                break;
            case 3:
                rangedStatSpread();
                break;
            case 4: 
                speedStatSpread();
                break;
        }
        
        this.getAttack().setOriginalValue(this.getAttack().getValue());
        this.getDefense().setOriginalValue(this.getDefense().getValue());
        this.getRangedAttack().setOriginalValue(this.getRangedAttack().getValue());
        this.getRangedDefense().setOriginalValue(this.getRangedDefense().getValue());
        this.getSpeed().setOriginalValue(this.getSpeed().getValue());
    }
    
    private void offensiveStatSpread()
    {
        isOffensive = true;
        statSpreadDescription = "It looks like it'll pack a punch!";
        
        this.setMaxHealth(totalStatPoints - 50);
        this.setCurrentHealth(maxHealth);

        // Assigns 50% of total stat points to offensive stats
        this.setAttack((int)Math.round(totalStatPoints * 0.5 / 2));
        this.setRangedAttack((int)Math.round(totalStatPoints * 0.5 / 2));

        // Assigns 30% of total stat points to defensive stats
        this.setDefense((int)Math.round(totalStatPoints * 0.3 / 2));
        this.setRangedDefense((int)Math.round(totalStatPoints * 0.3 / 2));

        // Assigns 20% of total stat points to speed
        this.setSpeed((int)Math.round(totalStatPoints * 0.2));
    }
    
    private void defensiveStatSpread()
    {
        isOffensive = false;
        statSpreadDescription = "It seems to be on careful guard...";
        
        // Sets the HP based on the total points plus a random number
        this.setMaxHealth(totalStatPoints - 50);
        this.setCurrentHealth(maxHealth);

        // Assigns 50% of total stat points to defensive stats
        this.setDefense((int)Math.round(totalStatPoints * 0.5 / 2));
        this.setRangedDefense((int)Math.round(totalStatPoints * 0.5 / 2));

        // Assigns 35% of total stat points to offensive stats
        this.setAttack((int)Math.round(totalStatPoints * 0.35 / 2));
        this.setRangedAttack((int)Math.round(totalStatPoints * 0.35 / 2));

        // Assigns 15% of total stat points to speed
        this.setSpeed((int)Math.round(totalStatPoints * 0.15));
    }
    
    private void physicalStatSpread()
    {
        isOffensive = new Random().nextBoolean();
        statSpreadDescription = "It appears to have a strong stature!";
        
        // Sets the HP based on the total points plus a random number
        this.setMaxHealth(totalStatPoints - 30);
        this.setCurrentHealth(maxHealth);
        
        // Assigns 50% of total stat points to physical stats
        this.setAttack((int)Math.round(totalStatPoints * 0.5 / 2));
        this.setDefense((int)Math.round(totalStatPoints * 0.5 / 2));
        
        // Assigns 30% of total stat points to ranged stats
        this.setRangedAttack((int)Math.round(totalStatPoints * 0.3 / 2));
        this.setRangedDefense((int)Math.round(totalStatPoints * 0.3 / 2));
        
        // Assigns 20% of total stat points to speed
        this.setSpeed((int)Math.round(totalStatPoints * 0.2));
    }
    
    private void rangedStatSpread()
    {
        isOffensive = new Random().nextBoolean();
        statSpreadDescription = "It seems like it prefers to keep its distance.";
        
        // Sets the HP based on the total points plus a random number
        this.setMaxHealth((totalStatPoints - 60));
        this.setCurrentHealth(maxHealth);
        
        // Assigns 60% of total stat points to ranged stats
        this.setRangedAttack((int)Math.round(totalStatPoints * 0.60 / 2));
        this.setRangedDefense((int)Math.round(totalStatPoints * 0.60 / 2));
        
        // Assigns 18% of total stat points to physical stats
        this.setAttack((int)Math.round(totalStatPoints * 0.18 / 2));
        this.setDefense((int)Math.round(totalStatPoints * 0.18 / 2));
        
        // Assigns 22% of total stat points to speed
        this.setSpeed((int)Math.round(totalStatPoints * 0.22));
    }
    
    private void speedStatSpread()
    {
        isOffensive = new Random().nextBoolean();
        statSpreadDescription = "It looks quite nimble!";
        
        // Sets the HP based on the total points plus a random number
        this.setMaxHealth(totalStatPoints - 10);
        this.setCurrentHealth(maxHealth);
        
        // Assigns 25% of total stat points to speed
        this.setSpeed((int)Math.round(totalStatPoints * 0.25));
        
        // Assigns 37% of total stat points to physical stats
        this.setAttack((int)Math.round(totalStatPoints * 0.37 / 2));
        this.setDefense((int)Math.round(totalStatPoints * 0.37 / 2));
        
        // Assigns 38% of total stat points to ranged stats
        this.setRangedAttack((int)Math.round(totalStatPoints * 0.38 / 2));
        this.setRangedDefense((int)Math.round(totalStatPoints * 0.38 / 2));
    }
    
    protected int createLevel(boolean isRegular, ArrayList<Player> fightingTeam)
    {   
        int playerLevel = Character.highestPlayerLV(fightingTeam);
        int result;
        Random rand = new Random();
        
        // if boss enemy is to be instantiated
        if(isRegular == false)
        {
            result = playerLevel + rand.nextInt(3) + 5;
        }
        // if enemy is regular
        else
        {
            if(rand.nextInt() % 2 == 0)
            {
                result = playerLevel + rand.nextInt(3);
            }
            else 
            {
                result = playerLevel - rand.nextInt(3);
                
                if(result <= 1)
                {
                    result = 2;
                }
            }
        }
        
        return result;
    }
    
    public boolean hasBuffAttack()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof BuffAttack)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean hasAttackBuff()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof BuffAttack)
            {
                Scanner scan = new Scanner(((BuffAttack) anAttack).getStatToBuff());
                scan.useDelimiter(",");
                
                while(scan.hasNext())
                {
                    if(scan.next().equals("Attack") || scan.next().equals("R. Attack") || scan.next().equals("All"))
                    {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean hasBuff()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof BuffAttack)
            {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean hasDebuff()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof DebuffAttack)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public DebuffAttack getDebuffAttack()
    {
        DebuffAttack result = null; 
        
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof DebuffAttack)
            {
                result = (DebuffAttack)anAttack;
            }
        }
        
        return result;
    }
    
    public boolean hasActiveBuff()
    {
        return attack.getIsBuffActive() || defense.getIsBuffActive() || rangedAttack.getIsBuffActive() || 
                rangedDefense.getIsBuffActive() || speed.getIsBuffActive();
    }
    
    public boolean hasTeamHeal()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof TeamHealingAttack)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean hasSingleHeal()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof SingleHealingAttack)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public SingleHealingAttack getSingleHeal()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof SingleHealingAttack)
            {
                return (SingleHealingAttack)anAttack;
            }
        }
        
        return null;
    }
    
    public TeamHealingAttack getTeamHeal()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof TeamHealingAttack)
            {
                return (TeamHealingAttack)anAttack;
            }
        }
        
        return null;
    }
    
    public boolean hasHealingAttack()
    {
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof TeamHealingAttack || anAttack instanceof SingleHealingAttack)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public ArrayList<OffensiveAttack> getOffensiveAttackList()
    {
        ArrayList<OffensiveAttack> offensiveAttacks = new ArrayList<>();
        
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof OffensiveAttack)
            {
                offensiveAttacks.add((OffensiveAttack)anAttack);
            }
        }
        
        return offensiveAttacks;
    }
    
    /**
     * Randomly returns a known offensive attack with a higher chance to return the highest damaging one.
     * @return 
     */
    public OffensiveAttack getOffensiveAttack()
    {
        OffensiveAttack result;
        ArrayList<OffensiveAttack> offensiveAttacks = new ArrayList<>();
        
        // Gathers all offensive attacks and stores them in a temporary ArrayList
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof OffensiveAttack)
            {
                offensiveAttacks.add((OffensiveAttack)anAttack);
            }
        }
        
        DamageComparator dc = new DamageComparator();
        Sort.mergeSort(offensiveAttacks, dc);
        
        Random rand = new Random();
        
        int chance = rand.nextInt(10);
        
        // 70% chance to use the highest damaging attack; guaranteed to use that attack if only one offensive attack is known
        if((chance >= 0 && chance <= 6) || offensiveAttacks.size() == 1)
        {
            result = offensiveAttacks.get(0);
        }
        // 30% chance to use a random move if more than 1 offensive attack is known
        else
        {
            chance = rand.nextInt(offensiveAttacks.size());

            if(chance == 0)
            {
                chance++;
            }

            result = offensiveAttacks.get(chance);
        }
        
        return result;
    }
    
    public BuffAttack getAttackBuff()
    {
        BuffAttack result = null; 
        
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof BuffAttack)
            {
                Scanner scan = new Scanner(((BuffAttack) anAttack).getStatToBuff());
                scan.useDelimiter(",");
                
                while(scan.hasNext())
                {
                    if(scan.next().equals("Attack") || scan.next().equals("R. Attack") || scan.next().equals("All"))
                    {
                        result = (BuffAttack)anAttack;
                        break;
                    }
                }
            }
            
            if(result != null)
            {
                break;
            }
        }
        
        return result;
    }
    
    public BuffAttack getBuffAttack()
    {
        BuffAttack result = null; 
        
        for(Attack anAttack : currentAttacks)
        {
            if(anAttack instanceof BuffAttack)
            {
                result = (BuffAttack)anAttack;
            }
        }
        
        return result;
    }
    
    /**
     * Populates respective ArrayLists with all possible attacks an Enemy can learn.
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
        allOffensiveAttacks.add(new OffensiveAttack("Wild Fever", "The user uses its wild instinct to cause massive damage.", 140, "Attack"));
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
    
    public static HealingAttack getHealingAttack(String attackName)
    {
        for(HealingAttack ha : allHealingAttacks)
        {
            if(attackName.toLowerCase().equals(ha.getName().toLowerCase()))
            {
                return ha;
            }
        }
        
        return null;
    }
    
    private void populateCurrentAttacks()
    {
        Random rand = new Random();
        int numOfOffensive = rand.nextInt(3) + 1;
        
        if(isOffensive)
        {
            if(numOfOffensive == 1)
            {
                numOfOffensive++;
            }
            
            for(int i = 0; i < numOfOffensive; i++)
            {
                currentAttacks.add(allOffensiveAttacks.remove(rand.nextInt(allOffensiveAttacks.size())));
            }
            
            currentAttacks.add(allBuffAttacks.remove(rand.nextInt(allBuffAttacks.size())));
            
            if(currentAttacks.size() != 4)
            {
                currentAttacks.add(allDebuffAttacks.remove(rand.nextInt(allDebuffAttacks.size())));
            }
        }
        else
        {
            for(int i = 0; i < numOfOffensive; i++)
            {
                currentAttacks.add(allOffensiveAttacks.remove(rand.nextInt(allOffensiveAttacks.size())));
            }
            
            currentAttacks.add(allBuffAttacks.remove(rand.nextInt(allBuffAttacks.size())));
            
            if(currentAttacks.size() != 4)
            {
                currentAttacks.add(allDebuffAttacks.remove(rand.nextInt(allDebuffAttacks.size())));
            }
            
            if(currentAttacks.size() != 4)
            {
                currentAttacks.add(allHealingAttacks.remove(rand.nextInt(allHealingAttacks.size())));
            }
        }
        
        populateAllAttacks();
    }
    
    @Override
    public ArrayList<Attack> getCurrentAttacks()
    {
        return currentAttacks;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Enemy))
        {
            return false;
        }
        
        Enemy e = (Enemy) o;
        
        return this.name.equals(e.name) && this.description.equals(e.description) && this.element.equals(e.element) &&
                this.level == e.level && this.attack == e.attack && this.defense == e.defense && 
                this.rangedAttack == e.rangedAttack && this.rangedDefense == e.rangedDefense && this.speed == e.speed;
    }
    
    @Override
    public String toString()
    {
        return this.name + "'s level: " + this.level + "\nStats: \n\t Health: " + this.currentHealth + "/" + this.maxHealth +
                "\n\t Attack: " + this.attack + "\n\t Defense: " + this.defense + "\n\t Ranged Attack: " + this.rangedAttack +
                "\n\t Ranged Defense: " + this.rangedDefense + "\n\t Speed: " + this.speed + "\n";
    }
    
    public String toBattleString()
    {
        return name + "'s level: " + level + "\n\t" + description + "\n\t" + statSpreadDescription; 
    }
}
