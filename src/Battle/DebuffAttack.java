package Battle;

import Game.MainGame;
import java.util.Scanner;

/**
 * A class for making attacks that solely cause debuffs on enemies.
 * @author Ian King
 */
public class DebuffAttack extends Attack
{
    private final String STAT_TO_DEBUFF;
    private final double DEBUFF_MODIFIER;
    private final int DEBUFF_MODIFIER_TIMER;
    
    public DebuffAttack(String name, String description, String statToDebuff, double debuffModifier, int cooldown, int debuffModifierTimer)
    {
        super.name = name;
        super.description = description;
        this.STAT_TO_DEBUFF = statToDebuff;
        this.DEBUFF_MODIFIER = debuffModifier;
        super.cooldown = cooldown;
        this.DEBUFF_MODIFIER_TIMER = debuffModifierTimer;
    }
    
    public DebuffAttack(String name, String description, String statToDebuff, int cooldown, int debuffModifierTimer)
    {
        super.name = name;
        super.description = description;
        this.STAT_TO_DEBUFF = statToDebuff;
        this.DEBUFF_MODIFIER = 0.5;
        super.cooldown = cooldown;
        DEBUFF_MODIFIER_TIMER = debuffModifierTimer;
    }
    
    public DebuffAttack(String name, String description, String statToDebuff, int cooldown)
    {
        super.name = name;
        super.description = description;
        this.STAT_TO_DEBUFF = statToDebuff;
        super.cooldown = cooldown;
        this.DEBUFF_MODIFIER = 0.5;
        DEBUFF_MODIFIER_TIMER = 0;
    }
    
    public int getDebuffModifierTimer() {return DEBUFF_MODIFIER_TIMER;}
    
    public String getStatToDebuff() {return STAT_TO_DEBUFF;}
    public double getDebuffModifier() {return DEBUFF_MODIFIER;}
    
    public void activateDebuff(Character attacker, Character target)
    {
        attackHit();
        
        MainGame.printlnln("\n" + attacker.getName() + " used " + name + " on "+ target.getName() + "!");
        
        if(attackHit)
        {
            Scanner scan = new Scanner(STAT_TO_DEBUFF);
            scan.useDelimiter(",");

            while(scan.hasNext())
            {
                switch (scan.next()) 
                {
                    case "Attack":
                        if(target.getAttack().getIsDebuffActive() == false)
                        {
                            target.setAttack((int)Math.round(target.getAttack().getValue() * DEBUFF_MODIFIER));
                            target.getAttack().setDebuffActive(true);
                            target.getAttack().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getAttack().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s attack decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getAttack());
                        }
                        else 
                        {
                            MainGame.printlnln(target.getName() + " still has an attack debuff active!");
                        }
                        break;
                    case "Defense":
                        if(target.getDefense().getIsDebuffActive() == false)
                        {
                            target.setDefense((int)Math.round(target.getDefense().getValue() * DEBUFF_MODIFIER));
                            target.getDefense().setDebuffActive(true);
                            target.getDefense().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getDefense().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s defense decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getDefense());
                        }
                        else 
                        {
                            MainGame.printlnln(target.getName() + " still has a defense debuff active!");
                        }  
                        break;
                    case "R. Attack":
                        if(target.getRangedAttack().getIsDebuffActive() == false)
                        {
                            target.setRangedAttack((int)Math.round(target.getRangedAttack().getValue() * DEBUFF_MODIFIER));
                            target.getRangedAttack().setDebuffActive(true);
                            target.getRangedAttack().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getRangedAttack().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s ranged attack decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getRangedAttack());
                        }
                        else 
                        {
                            MainGame.printlnln(target.getName() + " still has a ranged attack debuff active!");
                        }  
                        break;
                    case "R. Defense":
                        if(target.getRangedDefense().getIsDebuffActive() == false)
                        {
                            target.setRangedDefense((int)Math.round(target.getRangedDefense().getValue() * DEBUFF_MODIFIER));
                            target.getRangedDefense().setDebuffActive(true);
                            target.getRangedDefense().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getRangedDefense().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s ranged defense decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getRangedDefense());
                        }
                        else 
                        {
                            MainGame.printlnln(target.getName() + " still has a ranged debuff active!");
                        }
                        break;
                    case "All":
                        if(target.getAttack().getIsDebuffActive() == false)
                        {
                            target.setAttack((int)Math.round(target.getAttack().getValue() * DEBUFF_MODIFIER));
                            target.getAttack().setDebuffActive(true);
                            target.getAttack().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getAttack().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s attack decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getAttack());
                        }
                        else 
                        {
                            MainGame.printlnln(target.getName() + " still has an attack buff active!");
                        }

                        if(target.getDefense().getIsDebuffActive() == false)
                        {
                            target.setDefense((int)Math.round(target.getDefense().getValue() * DEBUFF_MODIFIER));
                            target.getDefense().setDebuffActive(true);
                            target.getDefense().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getDefense().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s defense decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getDefense());
                        }
                        else 
                        {
                            MainGame.printlnln(target.getName() + " still has a defense buff active!");
                        }  

                        if(target.getRangedAttack().getIsDebuffActive() == false)
                        {
                            target.setRangedAttack((int)Math.round(target.getRangedAttack().getValue() * DEBUFF_MODIFIER));
                            target.getRangedAttack().setDebuffActive(true);
                            target.getRangedAttack().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getRangedAttack().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s ranged attack decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getRangedAttack());
                        }
                        else 
                        {
                            MainGame.printlnln(target.getName() + " still has a ranged attack buff active!");
                        }

                        if(target.getRangedDefense().getIsDebuffActive() == false)
                        {
                            target.setRangedDefense((int)Math.round(target.getRangedDefense().getValue() * DEBUFF_MODIFIER));
                            target.getRangedDefense().setDebuffActive(true);
                            target.getRangedDefense().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getRangedDefense().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s ranged defense decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getRangedDefense());
                        }
                        else 
                        {
                            MainGame.printlnln(target.getName() + " still has a ranged defense buff active!");
                        }

                        if(target.getSpeed().getIsDebuffActive() == false)
                        {
                            target.setSpeed((int)Math.round(target.getSpeed().getValue() * DEBUFF_MODIFIER));
                            target.getSpeed().setDebuffActive(true);
                            target.getSpeed().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getSpeed().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s speed decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getSpeed());
                        }
                        else
                        {
                            MainGame.printlnln(target.getName() + " still has a speed buff active!");
                        } 
                    
//                        MainGame.printlnln(target.getName() + "'s stats decreased!");
                        break;
                    default:
                        if(target.getSpeed().getIsDebuffActive() == false)
                        {
                            target.setSpeed((int)Math.round(target.getSpeed().getValue() * DEBUFF_MODIFIER));
                            target.getSpeed().setDebuffActive(true);
                            target.getSpeed().setDebuffModifier(DEBUFF_MODIFIER);
                            target.getSpeed().setTurnDebuffEnds(DEBUFF_MODIFIER_TIMER);
                            MainGame.printlnln(target.getName() + "'s speed decreased!");
                            
                            Battle.getChangedStats().enqueue(target.getSpeed());
                        }
                        else
                        {
                            MainGame.printlnln(target.getName() + " still has a speed debuff active!");
                        } 
                        break;
                }
            }
            
            canUse = false;
            setNextAvailableTurn(Battle.getCurrentTurn());
        }
        else
        {
            MainGame.printlnln(attacker.getName() + " targeted " + target.getName() + " and missed!");
        }
    }
    
    @Override
    public String toString()
    {
        Scanner scan = new Scanner(STAT_TO_DEBUFF);
        scan.useDelimiter(",");
        String stats = "";
        
        while(scan.hasNext())
        {
            if(scan.hasNext("All"))
            {
                stats = "All";
                break;
            }
            else
            {
                while(scan.hasNext())
                {   
                    stats += scan.next();
                    
                    if(scan.hasNext())
                    {
                        stats += ", ";
                    }
                }
            }
        }
        
        return "\t" + this.name + ":\n\t\t" + this.description + "\n\t\tAccuracy: " + this.accuracy + "%\n\t\tCooldown: " + 
                cooldown + "\n\t\tStat(s) affected: " + stats;
    }
}
