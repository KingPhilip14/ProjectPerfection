package Battle;

import Game.MainGame;
import java.util.Scanner;

/**
 * A class for making attacks that solely grant buffs.
 * @author Ian King
 */
public class BuffAttack extends Attack
{
    private final String STAT_TO_BUFF;
    private final double BUFF_MODIFIER;
    private final int BUFF_MODIFIER_TIMER;
    
    public BuffAttack(String name, String description, String statToBuff, double buffModifier, int cooldown, int buffModifierTimer)
    {
        super.name = name;
        super.description = description;
        this.STAT_TO_BUFF = statToBuff;
        this.BUFF_MODIFIER = buffModifier;
        super.cooldown = cooldown;
        this.BUFF_MODIFIER_TIMER = buffModifierTimer;
    }
    
    public BuffAttack(String name, String description, String statToBuff, int cooldown, int buffModifierTimer)
    {
        super.name = name;
        super.description = description;
        this.STAT_TO_BUFF = statToBuff;
        super.cooldown = cooldown;
        BUFF_MODIFIER = 1.5;
        BUFF_MODIFIER_TIMER = buffModifierTimer;
    }
    
    public BuffAttack(String name, String description, String statToBuff, int buffModifierTimer)
    {
        super.name = name;
        super.description = description;
        this.STAT_TO_BUFF = statToBuff;
        super.cooldown = 0;
        BUFF_MODIFIER = 1.5;
        BUFF_MODIFIER_TIMER = buffModifierTimer;
    }
    
    public int getBuffModifierTimer() {return BUFF_MODIFIER_TIMER;}
    
    public String getStatToBuff() {return STAT_TO_BUFF;}
    public double getBuffModifier() {return BUFF_MODIFIER;}
    
    public void activateBuff(Character target)
    {
        Scanner scan = new Scanner(STAT_TO_BUFF);
        scan.useDelimiter(",");
        
        MainGame.printlnln("\n" + target.getName() + " used " + name + "!", 5);
        
        while(scan.hasNext())
        {   
            switch (scan.next()) 
            {   
                case "Attack":
                    if(target.getAttack().getIsBuffActive() == false)
                    {
                        target.setAttack((int)Math.round(target.getAttack().getValue() * BUFF_MODIFIER));
                        target.getAttack().setBuffActive(true);
                        target.getAttack().setBuffModifier(BUFF_MODIFIER);
                        target.getAttack().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s attack increased!", 5);
                        
                        Battle.getChangedStats().enqueue(target.getAttack());
                    }
                    else 
                    {
                        MainGame.printlnln(target.getName() + " still has an attack buff active!", 5);
                    }
                    break;
                case "Defense":
                    if(target.getDefense().getIsBuffActive() == false)
                    {
                        target.setDefense((int)Math.round(target.getDefense().getValue() * BUFF_MODIFIER));
                        target.getDefense().setBuffActive(true);
                        target.getDefense().setBuffModifier(BUFF_MODIFIER);
                        target.getDefense().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s defense increased!", 5);
                        
                        Battle.getChangedStats().enqueue(target.getDefense());
                    }
                    else 
                    {
                        MainGame.printlnln(target.getName() + " still has a defense buff active!", 5);
                    }  
                    break;
                case "R. Attack":
                    if(target.getRangedAttack().getIsBuffActive() == false)
                    {
                        target.setRangedAttack((int)Math.round(target.getRangedAttack().getValue() * BUFF_MODIFIER));
                        target.getRangedAttack().setBuffActive(true);
                        target.getRangedAttack().setBuffModifier(BUFF_MODIFIER);
                        target.getRangedAttack().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s ranged attack increased!", 5);
                        
                        Battle.getChangedStats().enqueue(target.getRangedAttack());
                    }
                    else 
                    {
                        MainGame.printlnln(target.getName() + " still has a ranged attack buff active!", 5);
                    }  
                    break;
                case "R. Defense":
                    if(target.getRangedDefense().getIsBuffActive() == false)
                    {
                        target.setRangedDefense((int)Math.round(target.getRangedDefense().getValue() * BUFF_MODIFIER));
                        target.getRangedDefense().setBuffActive(true);
                        target.getRangedDefense().setBuffModifier(BUFF_MODIFIER);
                        target.getRangedDefense().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s ranged defense increased!" , 5);
                        
                        Battle.getChangedStats().enqueue(target.getRangedDefense());
                    }
                    else 
                    {
                        MainGame.printlnln(target.getName() + " still has a ranged defense buff active!", 5);
                    }
                    break;
                case "All":
                    if(target.getAttack().getIsBuffActive() == false)
                    {
                        target.setAttack((int)Math.round(target.getAttack().getValue() * BUFF_MODIFIER));
                        target.getAttack().setBuffActive(true);
                        target.getAttack().setBuffModifier(BUFF_MODIFIER);
                        target.getAttack().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s attack increased!" , 5);
                        
                        Battle.getChangedStats().enqueue(target.getAttack());
                    }
                    else 
                    {
                        MainGame.printlnln(target.getName() + " still has an attack buff active!", 5);
                    }
                    
                    if(target.getDefense().getIsBuffActive() == false)
                    {
                        target.setDefense((int)Math.round(target.getDefense().getValue() * BUFF_MODIFIER));
                        target.getDefense().setBuffActive(true);
                        target.getDefense().setBuffModifier(BUFF_MODIFIER);
                        target.getDefense().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s defense increased!", 5);
                        
                        Battle.getChangedStats().enqueue(target.getDefense());
                    }
                    else 
                    {
                        MainGame.printlnln(target.getName() + " still has a defense buff active!", 5);
                    }  
                    
                    if(target.getRangedAttack().getIsBuffActive() == false)
                    {
                        target.setRangedAttack((int)Math.round(target.getRangedAttack().getValue() * BUFF_MODIFIER));
                        target.getRangedAttack().setBuffActive(true);
                        target.getRangedAttack().setBuffModifier(BUFF_MODIFIER);
                        target.getRangedAttack().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s ranged attack increased!", 5);
                        
                        Battle.getChangedStats().enqueue(target.getRangedAttack());
                    }
                    else 
                    {
                        MainGame.printlnln(target.getName() + " still has a ranged attack buff active!", 5);
                    }
                    
                    if(target.getRangedDefense().getIsBuffActive() == false)
                    {
                        target.setRangedDefense((int)Math.round(target.getRangedDefense().getValue() * BUFF_MODIFIER));
                        target.getRangedDefense().setBuffActive(true);
                        target.getRangedDefense().setBuffModifier(BUFF_MODIFIER);
                        target.getRangedDefense().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s ranged defense increased!" , 5);
                        
                        Battle.getChangedStats().enqueue(target.getRangedDefense());
                    }
                    else 
                    {
                        MainGame.printlnln(target.getName() + " still has a ranged defense buff active!", 5);
                    }
                    
                    if(target.getSpeed().getIsBuffActive() == false)
                    {
                        target.setSpeed((int)Math.round(target.getSpeed().getValue() * BUFF_MODIFIER));
                        target.getSpeed().setBuffActive(true);
                        target.getSpeed().setBuffModifier(BUFF_MODIFIER);
                        target.getSpeed().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s speed increased!", 5);
                        
                        Battle.getChangedStats().enqueue(target.getSpeed());
                    }
                    else
                    {
                        MainGame.printlnln(target.getName() + " still has a speed buff active!", 5);
                    } 
                    
//                    MainGame.printlnln(target.getName() + "'s stats increased!", 5);
                    break;
                default:
                    if(target.getSpeed().getIsBuffActive() == false)
                    {
                        target.setSpeed((int)Math.round(target.getSpeed().getValue() * BUFF_MODIFIER));
                        target.getSpeed().setBuffActive(true);
                        target.getSpeed().setBuffModifier(BUFF_MODIFIER);
                        target.getSpeed().setTurnBuffEnds(BUFF_MODIFIER_TIMER);
                        MainGame.printlnln(target.getName() + "'s speed increased!", 5);
                        
                        Battle.getChangedStats().enqueue(target.getSpeed());
                    }
                    else
                    {
                        MainGame.printlnln(target.getName() + " still has a speed buff active!", 5);
                    } 
                    break;
            }
        }
        
        canUse = false;
        setNextAvailableTurn(Battle.getCurrentTurn());
    }
    
    @Override
    public String toString()
    {
        Scanner scan = new Scanner(STAT_TO_BUFF);
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
                
        return "\t" + this.name + ":\n\t\t" + this.description + "\n\t\tAccuracy: " + this.accuracy + "%\n\t\tCooldown: " + cooldown
                + "\n\t\tStat(s) affected: " + stats;
    }
}
