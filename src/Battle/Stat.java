package Battle;

import Game.MainGame;

/**
 * A class for creating the Stats of an Enemy or Player
 * @author Ian King
 */
public class Stat implements java.io.Serializable
{
    private static double cheerBuffValue = 1.2;
    private String stat;
    private int value;
    private Character character;
    private int originalValue;
    private int turnBuffEnds;
    private int turnDebuffEnds;
    private int cheerBuffCounter;
    private double buffModifier;
    private double debuffModifier;
    private boolean buffActive;
    private boolean debuffActive;
    
    public Stat(String stat, int value, Character c)
    {
        this.stat = stat;
        this.value = value;
        this.originalValue = value;
        character = c;
    }
    
    public Stat(String stat, Character c)
    {
        this.stat = stat;
        this.originalValue = value;
        character = c;
    }
    
    public String getStat() {return stat;}
    public int getValue() {return value;}
    
    public void setOriginalValue(int newValue) {originalValue = newValue;}
    public int getOriginalValue() {return originalValue;}

    public Character getCharacter() {return character;}
    
    public boolean hasCheerBuff() {return cheerBuffCounter > 0;} //  if >0, the buff is active. If 0, no buff is applied
    
    public static double get_cheer_buff_value() {return cheerBuffValue;}

    public double getCheerBuffCounter() {return cheerBuffCounter;}
    // public void resetCheerBuff() {cheerBuffCounter = 0;}
    public void increaseCheerBuff(double amount) 
    {
        cheerBuffCounter += 1; // increment how many cheer buff this stat has
        value *= amount; // increase the amount
    }

    public void reapplyCheerBuff()
    {
        for(int i = 0; i < cheerBuffCounter; i++)
        {
            value *= cheerBuffValue;
        }
    }

    public void removeCheerBuff()
    {
        value = originalValue;
        // for(int i = 0; i < cheerBuffCounter; i++)
        // {
            // value = (int)Math.round(value / cheerBuffValue);
        // }
    }
    
    public int getTurnBuffEnds() {return turnBuffEnds;}
    public void setTurnBuffEnds(int modifierTimer) {turnBuffEnds = Battle.getCurrentTurn() + modifierTimer + 1;}
    
    public int getTurnDebuffEnds() {return turnDebuffEnds;}
    public void setTurnDebuffEnds(int modifierTimer) {turnDebuffEnds = Battle.getCurrentTurn() + modifierTimer + 1;}
    
    public void setBuffActive(boolean condition) {buffActive = condition;}
    public void setDebuffActive(boolean condition) {debuffActive = condition;}
    
    public boolean getIsBuffActive() {return buffActive;}
    public boolean getIsDebuffActive() {return debuffActive;}
    
    public boolean isBuffActive() {return buffModifier > 1;}
    public boolean isDebuffActive() {return debuffModifier < 1;}
    
    public void setValue(int newValue) {value = newValue;}
    public void increaseValue(int valueToAdd) {value += valueToAdd;}
    
    public double getBuffModifier() {return buffModifier;}
    public void setBuffModifier(double modifier) {buffModifier = modifier;}
    
    public double getDebuffModifier() {return debuffModifier;}
    public void setDebuffModifier(double modifier) {debuffModifier = modifier;}
    
    /**
     * Returns true if a buff or debuff ends on the given turn.
     * @param currentTurn
     * @return true if a buff or debuff ends this turn
     */
    public boolean isTurnChangeEnds(int currentTurn)
    {
        return currentTurn == turnBuffEnds || currentTurn == turnDebuffEnds;
    }
    
    public boolean isTurnBuffEnds(int currentTurn)
    {
        return currentTurn == turnBuffEnds && buffActive;
    }
    
    public boolean isTurnDebuffEnds(int currentTurn)
    {
        return currentTurn == turnDebuffEnds && debuffActive;
    }
    
    public void activateStatBuff(int turnsToBeActive, int currentTurn, double buffModifier)
    {
        turnBuffEnds = turnsToBeActive + currentTurn;
        buffActive = true;
        this.buffModifier = buffModifier;
    }
    
    public boolean activateStatBuff(double buffModifier)
    {
        if(this.buffModifier > 1)
        {
            MainGame.printlnlnWait("\nThere's still a buff active!", 25, 2000);
            return false;
        }
        else
        {
            buffActive = true;
            this.buffModifier = buffModifier;
            value *= buffModifier;
            return true;
        }
    }
    
//    public void deactivateBuff(int currentTurn, Character character)
//    {
//        if(currentTurn == turnBuffEnds)
//        {
//            buffActive = false;
//            MainGame.printlnln("\n" + character.getName() + "'s " + stat + " buff is no longer active!", 5);
//            removeBuff();
////            resetValue(character);
//        }
//    }
    
    public void deactivateBuff()
    {
        buffActive = false;
        MainGame.printlnln(character.getName() + "'s " + stat + " buff is no longer active!");
        removeBuff();
//            resetValue(character);
    }

    public void deactivateBuffNoPrint()
    {
        buffActive = false;
        removeBuff();
    }
    
    public void activateStatDebuff(int turnsToBeActive, double debuffModifier)
    {
        turnDebuffEnds = turnsToBeActive;
        debuffActive = true;
        this.debuffModifier = debuffModifier;
    }
    
    public void deactivateDebuff()
    {
        debuffActive = false;
        MainGame.printlnln(character.getName() + "'s " + stat + " debuff is no longer active!");
        removeDebuff();
    }

    public void deactivateDebuffNoPrint()
    {
        debuffActive = false;
        removeDebuff();
    }
    
//    public void deactivateDebuff(int currentTurn, Character character)
//    {
//        if(currentTurn == turnDebuffEnds)
//        {
//            debuffActive = false;
//            
////            if(stat.equals("All"))
////            {
////                MainGame.printlnln(character.getName() + "'s stat debuffs are no longer active!", 5);
////            }
////            else
////            {
//                MainGame.printlnln(character.getName() + "'s " + stat + " debuff is no longer active!", 5);
////            }
//            
////            resetValue(character);
//            removeDebuff();
//        }
//    }
    
    public void resetValue(Character character)
    {
        if(buffActive)
        {
            deactivateBuffNoPrint();
        }
        
        if(debuffActive)
        {
            deactivateDebuffNoPrint();
        }
        
//        switch(stat)
//        {
//            case "Attack":
//            case "Defense":
//            case "R. Attack":
//            case "R. Defense":
//            case "Speed":
//                value = originalValue;
//                break;
////            case "Defense":
////                value = character.getDefense().getValue();
////                break;
////            case "R. Attack":
////                value = character.getRangedAttack().getValue();
////                break;
////            case "R. Defense": 
////                value = character.getRangedDefense().getValue();
////                break;
////            case "Speed":
////                value = character.getSpeed().getValue();
////                break;
//            default:
//                character.setAttack(character.getAttack().getOriginalValue());
//                character.setDefense(character.getDefense().getOriginalValue());
//                character.setRangedAttack(character.getRangedAttack().getOriginalValue());
//                character.setRangedDefense(character.getRangedDefense().getOriginalValue());
//                character.setSpeed(character.getSpeed().getOriginalValue());
//                break;
//        }
    }
    
    private void removeBuff()
    {
        value = (int)Math.round(value / buffModifier);
        buffModifier = 1;
    }
    
    private void removeDebuff()
    {
        value = (int)Math.round(value / debuffModifier);
        debuffModifier = 1;
    }
    
    @Override
    public String toString()
    {
        return stat + ": "+ value;
    }
    
    public String toStringOriginalValue()
    {
        return stat + ": "+ originalValue;
    }
}
