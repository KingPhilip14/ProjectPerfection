package Battle;

import java.util.ArrayList;

/**
 * An interface containing methods for Game entities that can fight in combat.
 * @author Ian King
 */
public interface Combatable extends Entity
{
//    public ArrayList<Attack> getListOfAllAttacks();
//    public void setListOfAllAttacks(ArrayList<Attack> newList);
    
    public ArrayList<Attack> getCurrentAttacks();
    public void setCurrentAttacks(ArrayList<Attack> newList);
    
    public String getElement();
    public void setElement(String newElement);
    
    public int getLevel();
    public void setLevel(int newLevel);
    
    public int getMaxHealth();
    public void setMaxHealth(int newMaxHealth);
    
    public int getCurrentHealth();
    public void setCurrentHealth(int newHealth);
    
    public Stat getAttack();
    public void setAttack(int newAttack);
    
    public Stat getDefense();
    public void setDefense(int newDefense);
    
    public Stat getRangedAttack();
    public void setRangedAttack(int newRangedAttack);
    
    public Stat getRangedDefense();
    public void setRangedDefense(int newRangedDefense);
    
    public Stat getSpeed();
    public void setSpeed(int newSpeed);
    
//    public Stat getBattleAttack();
//    public void setBattleAttack(int newAttack);
//    
//    public Stat getBattleDefense();
//    public void setBattleDefense(int newDefense);
//    
//    public Stat getBattleRAttack();
//    public void setBattleRAttack(int newRAttack);
//    
//    public Stat getBattleRDefense();
//    public void setBattleRDefense(int newRDefense);
//    
//    public Stat getBattleSpeed();
//    public void setBattleSpeed(int newSpeed);
    
//    public boolean getAttackDebuffed();
//    public void setAttackDebuffed(boolean condition);
//    
//    public boolean getDefenseDebuffed();
//    public void setDefenseDebuffed(boolean condition);
//    
//    public boolean getRAttackDebuffed();
//    public void setRAttackDebuffed(boolean condition);
//    
//    public boolean getRDefenseDebuffed();
//    public void setRDefenseDebuffed(boolean condition);
//    
//    public boolean getSpeedDebuffed();
//    public void setSpeedDebuffed(boolean condition);
//    
//    public boolean getAttackBuffed();
//    public void setAttackBuffed(boolean condition);
//    
//    public boolean getDefenseBuffed();
//    public void setDefenseBuffed(boolean condition);
//    
//    public boolean getRAttackBuffed();
//    public void setRAttackBuffed(boolean condition);
//    
//    public boolean getRDefenseBuffed();
//    public void setRDefenseBuffed(boolean condition);
//    
//    public boolean getSpeedBuffed();
//    public void setSpeedBuffed(boolean condition);
    
    public void displayHealth();
}
