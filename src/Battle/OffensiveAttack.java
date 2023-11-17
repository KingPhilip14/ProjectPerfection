package Battle;

import Game.MainGame;
import Utilites.ElementComparator;
import java.util.Random;

/**
 * A class for creating attacks that solely do damage.
 * @author Ian King
 */
public class OffensiveAttack extends Attack
{
    private int baseDamage;
    private double elementEffectiveness;
    private double critRate;
    private double critDamageModifier;
    private String statToUse;
    private boolean isCrit;
    
    public OffensiveAttack(String name, String description, int baseDamage, String statToUse)
    {
        super.name = name;
        super.description = description;
        this.baseDamage = baseDamage;
        this.elementEffectiveness = 1;
        super.accuracy = 100;
        this.critRate = 0.07;
        this.critDamageModifier = 2;
        this.statToUse = statToUse;
    }
    
    public OffensiveAttack() {}
    
    public void setCritRate(double newRate) {critRate = newRate;}
    public int getBaseDamage() {return baseDamage;}
    
    public boolean isCrit()
    {
        int rate = (int)(critRate * 100);
        
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        
        return randomNum >= 0 && randomNum <= rate;
    }
    
    private void calculateElementEffectiveness(Character attacker, Character target)
    {
        ElementComparator comparator = new ElementComparator();
        
        if(attacker instanceof Player)
        {
            switch (comparator.comparePToE((Player)attacker, (Enemy)target)) 
            {
                case 1:
                    elementEffectiveness = 1.5;
                    break;
                case 0:
                    elementEffectiveness = 1;
                    break;
                default:
                    elementEffectiveness = 0.5;
                    break;
            }
        }
        else if(attacker instanceof Enemy)
        {
            switch (comparator.compareEToP((Player)target, (Enemy)attacker))
            {
                case 1:
                    elementEffectiveness = 1.5;
                    break;
                case 0:
                    elementEffectiveness = 1;
                    break;
                default:
                    elementEffectiveness = 0.5;
                    break;
            }
        }
    }
    
    public int calculateDamage(Character attacker, Character target)
    {
        //((((((2 * attackerLevel) / 2 ) + 2) * attackPower * attackerAttackStat / targetDefenseStat) / 50) + 2) * critDamageMod * elementEff
        calculateElementEffectiveness(attacker, target);
        
        isCrit = isCrit();
        int damage; 
        
        if(isCrit)
        {
            if(statToUse.equals("Attack"))
            {
                damage = (int)(Math.round((((((2 * attacker.getLevel()) + 2) * baseDamage * attacker.getAttack().getValue() / target.getDefense().getValue()) / 50) + 50) * critDamageModifier * elementEffectiveness));
            }
            else
            {
                damage = (int)(Math.round((((((2 * attacker.getLevel()) + 2) * baseDamage * attacker.getRangedAttack().getValue() / target.getRangedDefense().getValue()) / 50) + 50) * critDamageModifier * elementEffectiveness));
            }
        }
        // if it's not a crit
        else
        {
            if(statToUse.equals("Attack"))
            {
                damage = (int)(Math.round((((((2 * attacker.getLevel()) + 5) * baseDamage * attacker.getAttack().getValue() / target.getDefense().getValue()) / 50) + 50) * elementEffectiveness));
            }
            else
            {
                damage = (int)(Math.round((((((2 * attacker.getLevel()) + 5) * baseDamage * attacker.getRangedAttack().getValue() / target.getRangedDefense().getValue()) / 50) + 50) * elementEffectiveness));
            }
        }
        
        // Adds a damage roll to the calculation
        damage += new Random().nextInt(50); 

        // Max damage possible
        if(damage > 9999)
        {
            damage = 9999;
        }
        
        return damage;
    }
    
    public void applyDamage(int damage, Character attacker, Character target)
    {
        attackHit();
        
        if(attackHit)
        {
            target.setCurrentHealth(target.getCurrentHealth() - damage);

            if(target.getCurrentHealth() < 0)
            {
                target.setCurrentHealth(0);
            }

            MainGame.printlnln("\n" + attacker.getName() + " used " + name + " and dealt " + damage + " to "+ target.getName() + "!");
            
            if(isCrit)
            {
    //            MainGame.println(MainGame.ANSI_RED + "It's a critical hit!" + MainGame.ANSI_RESET, 10);
                MainGame.printlnln("It's a critical hit!");
            }
            
            if(elementEffectiveness < 1)
            {
                MainGame.printlnln(attacker.getName() + "'s attack was not very effective.");
            }
            else if(elementEffectiveness > 1)
            {
                MainGame.printlnln(attacker.getName() + "'s attack was super effective!");
            }
        }
        else
        {
            MainGame.printlnln("\n" + attacker.getName() + " used " + name + " on " + target.getName() + " but missed!");
        }
    }
    
    public void attack(Character attacker, Character target)
    {
        attackHit();
        
        if(attackHit)
        {
            applyDamage(calculateDamage(attacker, target), attacker, target);
        }
        else
        {
            MainGame.printlnln("\n" + attacker.getName() + " used " + name + " on " + target.getName() + " but missed!");
        }
        
        setNextAvailableTurn(Battle.getCurrentTurn());
        
        MainGame.promptToEnter();
    }
    
    @Override
    public String toString()
    {
        int crit = (int)(critRate * 100);
        
        return "\t" + this.name + ":\n\t\t" + this.description + "\n\t\tBase Damage: " + this.baseDamage + "\n\t\tAccuracy: " + 
                this.accuracy + "%\n\t\tCritical Hit Rate: " + crit + "%";
    }
}
