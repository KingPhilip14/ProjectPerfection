package Battle;

import java.util.Random;

/**
 * A class for creating of Attacks for each Character and Enemy.
 * @author Ian King
 */
public abstract class Attack extends GameProperty
{
    protected int accuracy = 100;
    protected boolean attackHit;
    protected int cooldown;
//    protected int cooldownCounter;
    protected int nextAvailableTurn;
    protected boolean hasCooldown;
    protected boolean canUse = true;
    
    public void setAccuracy(int newAccuracy) {accuracy = newAccuracy;}
    
    public void setCooldown(int cooldown) {this.cooldown = cooldown;}
    public int getCooldown() {return cooldown;}
    
//    public int getCooldownCounter() {return cooldownCounter;}
    
    public int getNextAvailableTurn() {return nextAvailableTurn;}
    
    public boolean getHasCooldown() {return cooldown > 0;}
    
    public void setAttackHit(boolean value) {attackHit = value;}
    
    /**
     * Resets the next available turn to be turn 1 for the next battle.
     */
    public void resetNextAvailableTurn()
    {
        nextAvailableTurn = 1;
    }
    
    public boolean canUse()
    {
        return canUse;
    }
    
    /**
     * Takes the current turn and determines the next turn the attack can be used.
     * @param currentTurn 
     */
    public void setNextAvailableTurn(int currentTurn)
    {
        // Cooldown determines how many turns the attack is INACTIVE for
        // e.g. cooldown of 1 used on turn 1 means it's inactive on turn 2, available on turn 3
        if(cooldown != 0)
        {
            nextAvailableTurn = ++currentTurn + cooldown;
        }
    }
    
//    public void decreaseCooldownCounter()
//    {
//        if(cooldownCounter != 0)
//        {
//            cooldownCounter--;
//
//            if(cooldownCounter < 0)
//            {
//                cooldownCounter = cooldown;
//            }
//        }
//    }
    
//    public void resetCooldownCounter() {cooldownCounter = cooldown;}
    
    public boolean canUse(int currentTurn)
    {
        return currentTurn >= nextAvailableTurn;
    }
    
    public void attackHit()
    {
        Random rand = new Random();
        
        int randomAccuracy = rand.nextInt(100);
        
        attackHit = randomAccuracy >= 0 && randomAccuracy <= this.accuracy;
    }
    
    public boolean getAttackHit() {return attackHit;}
}
