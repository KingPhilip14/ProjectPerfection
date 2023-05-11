package Battle;

import Game.MainGame;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating a Player object. 
 * @author Ian King
 */
public class Player extends Character
{
    private ArrayList<Attack> listOfOtherAttacks;
    private ArrayList<ComboAttack> comboAttacks = new ArrayList<>();
    private int currentXP;
    private int xpToLevelUp;
    private int aggro;
    private static final int BASE_XP = 75;
    private PlayerClass playerClass;
    private String deathMessage;
    private String battleReadyMessage;
    private String cheerReadyMessage;
    private Player cheerPartner;
    private Player playerToCheer;
    
    public Player(String name, String description, String element, PlayerClass playerClass, int level)
    {
        super.name = name;
        super.description = description;
        super.element = element;
        this.playerClass = playerClass;
        super.level = level;
        this.listOfOtherAttacks = new ArrayList<>();
        super.currentAttacks = new ArrayList<>();
        currentXP = (int)Math.round((Math.pow((level + 1) * 10, 2)) / 4);
//        currentXP = 0;
        xpToLevelUp = currentXP;
        aggro = 0;
        
        populateListsOfStats();
    }
    
    public Player() {}
    
    public void addAttack(Attack newAttack)
    {
        this.listOfOtherAttacks.add(newAttack);
    }
    
    public void addComboAttack(ComboAttack newAttack)
    {
        this.comboAttacks.add(newAttack);
    }
    
    public ArrayList<ComboAttack> getComboAttacks() {return comboAttacks;}
    
    public int getCurrentXP() {return currentXP;}
    public int getXPToNextLV() {return xpToLevelUp;}
    
    public int getAggro() {return aggro;}
    public void setAggro(int newAggro) {aggro = newAggro;}
    
    public PlayerClass getPlayerClass() {return playerClass;}
    
    public ArrayList<Attack> getOtherAttacks() {return listOfOtherAttacks;}
    public void setListOfOtherAttacks(ArrayList<Attack> newList) {listOfOtherAttacks = newList;}
    
    public String getDeathMessage() {return deathMessage;}
    public void setDeathMessage(String message) {deathMessage = message;}
    
    public String getBattleReadyMessage() {return battleReadyMessage;}
    public void setBattleReadyMessage(String message) {battleReadyMessage = message;}
    public void printBattleReadyMessage() {MainGame.printlnln("\n" + name + ": " + battleReadyMessage, 25);}
    
    public String getCheerReadyMessage() {return cheerReadyMessage;}
    public void setCheerReadyMessage(String message) {cheerReadyMessage = message;}
    public void printCheerReadyMessage() {MainGame.printlnln("\n" + name + ": " + cheerReadyMessage, 5);}
    
    public String getClassRole() {return playerClass.getPrimaryRole();}
    public void setClassRole(String roleType) {playerClass.setPrimaryRole(roleType);}
    
    public Player getCheerPartner() {return cheerPartner;}
    public void setCheerPartner(Player partner) {cheerPartner = partner;}
    public void resetCheerPartner() {cheerPartner = null;}
    
    public Player getPlayerToCheer() {return playerToCheer;}
    public void setPlayerToCheer(Player player) {playerToCheer = player;}
    public void resetPlayerToCheer() {playerToCheer = null;}
    
    public void resetStats()
    {
        for(Stat s : listOfStats)
        {
            if(s.getValue() != s.getOriginalValue())
            {
                s.resetValue(this);
            }   
        }
    }
    
    public boolean isHealthy() {return currentHealth == maxHealth;}
    
    public ComboAttack getComboAttack()
    {
        String element1 = element;
        String element2 = cheerPartner.getElement();
        
        for(ComboAttack ca : comboAttacks)
        {
            if((ca.getElement1().equals(element1) || ca.getElement2().equals(element1)) && (ca.getElement1().equals(element2) || ca.getElement2().equals(element2)))
            {
                return ca;
            }
        }
        
        return null;
    }
    
    public void increaseXP(Player player, ArrayList<Player> fighitngTeam, Enemy defeatedEnemy)
    {
        MainGame.clearScreen();
        
        // Calculates the XP gained by rounding the amount
        int amt = (int)Math.round(BASE_XP * ((15 * defeatedEnemy.getLevel()) / player.getLevel()));
        
        // If there is only one player, increase their XP and return
        if(fighitngTeam.size() == 1)
        {
            getXPAmount(player, amt);
            return;
        }
        
        // Calculates the XP gained by rounding the amount
        getXPAmount(player, amt);
        
        amt = (int)Math.round(BASE_XP * ((10 * defeatedEnemy.getLevel()) / player.getLevel()));
        
        // For loop applies new amount of XP to everyone that isn't the player that got the kill
        for(Player p : fighitngTeam)
        {
            if(!p.equals(player))
            {
                getXPAmount(p, amt);
                MainGame.promptToEnter();
            }
        }
    }
    
    private void getXPAmount(Player player, int amt)
    {
        // if the Player has a cheer partner
        if(player.getCheerPartner() != null)
        {
            MainGame.printlnln(player.getName() + " received " + amt + " XP!", 25);
            MainGame.wait(1000);
            player.currentXP += amt;
            player.updateXP(amt);
//            MainGame.printlnlnWait(player.getName() + "'s XP to next level: " + xpToLevelUp, 20, 2000);
            
//            player.getCheerPartner().currentXP += amt;
//            player.getCheerPartner().updateXPToLevelUp(amt);
            
            MainGame.printlnln(player.getCheerPartner().getName() + " received " +  amt + " XP!", 25);
            MainGame.wait(1000);
            player.getCheerPartner().currentXP += amt;
            player.getCheerPartner().updateXP(amt);
//            MainGame.printlnlnWait(player.getCheerPartner().getName() + "'s XP to next level: " + xpToLevelUp, 20, 2000);
//            player.getCheerPartner().currentXP += amt;
//            player.getCheerPartner().updateXPToLevelUp(amt);
        }
        // if there isn't a cheer partner
        else
        {
            MainGame.printlnln(player.getName() + " received " + amt + " XP!", 25);
            player.currentXP += amt;
            player.updateXP(amt);
//            MainGame.printlnlnWait(player.getName() + "'s XP to next level: " + xpToLevelUp, 20, 2000);
            
//            player.getCheerPartner().currentXP += amt;
//            player.getCheerPartner().updateXPToLevelUp(amt);
        }
    }
    
    private void updateXP(int xpAmt)
    {
        if(xpToLevelUp - xpAmt < 0)
        {
            int remaining = xpAmt - xpToLevelUp;
//            xpToLevelUp -= xpToLevelUp;

            // set this to 0 to properly manage it in levelUp()
            xpToLevelUp = 0;
            levelUp(remaining);
//            levelUp(remaining);
        }
        else
        {
            xpToLevelUp -= xpAmt;
            
            /*
            This is a specific check to prevent a logic error. The only time a character will be at
            level 5 is Anahita at the beginning before the tutorial, in which all characters afterwards
            are 6 and above
            May or may not be the best practice...?
            */
            if(level != 5)
            {
                MainGame.printlnlnWait(getName() + "'s XP to next level: " + xpToLevelUp, 20, 2000);
            }
        }
        
        MainGame.promptToEnter();
    }
    
//    public void levelUp(int remaining)
//    {
////        Game.setLevelUpOccurred();
//        MainGame.printlnln(name + " leveled up to level " + (level + 1) + "!", 25);
//        MainGame.wait(1250);
//        MainGame.printlnln("Stats before:", 5);
//        MainGame.wait(1500);
//        MainGame.printlnln(toStringOriginalStats(), 25);
//        MainGame.wait(3000);
//        
//        level++;
//        currentXP = (int)Math.round((Math.pow((level + 1) * 10, 2)) / 4);
//        
//        xpToLevelUp = currentXP;
//        currentXP += remaining;
//        xpToLevelUp -= remaining;
//        updateStats();
//        
//        MainGame.printlnln("Current stats:", 5);
//        MainGame.println(toStringOriginalStats(), 25);
//        MainGame.wait(3000);
//        MainGame.printlnlnWait(getName() + "'s XP to next level: " + xpToLevelUp, 20, 3000);
//    }
    
    private void levelUp(int remainingXP)
    {
        MainGame.printlnln(name + " leveled up to level " + (level + 1) + "!", 25);
        MainGame.wait(1250);
        MainGame.printlnln("Stats before:", 5);
        MainGame.printlnln(toStringOriginalStats(), 25);

        MainGame.waitForEnter();
        System.out.println("");
        
        level++;
        xpToLevelUp = (int)Math.round((Math.pow((level + 1) * 10, 2)) / 4); 
        updateStats();

        // Initiates a recursive call to give the rest of the experience
        if(xpToLevelUp - remainingXP < 0)
        {
            updateXP(remainingXP);
        }

        xpToLevelUp -= remainingXP;
        MainGame.printlnln("Current stats:", 5);
        MainGame.println(toStringOriginalStats(), 25);
        MainGame.printlnlnWait(getName() + "'s XP to next level: " + xpToLevelUp, 20, 3000);
    }
    
    public void forcedLevelUp()
    {
        MainGame.printlnln(name + " leveled up to level " + (level + 1) + "!", 25);
        MainGame.wait(1000);
        MainGame.printlnln("Stats before:", 5);
        MainGame.wait(1000);
        MainGame.printlnln(toStringOriginalStats(), 25);
        MainGame.wait(1000);
        
        level++;
        currentXP = (int)Math.round((Math.pow((level + 1) * 10, 2)) / 4);
        xpToLevelUp = currentXP;
        
        updateStats();
        
        MainGame.printlnln("Current stats:", 5);
        MainGame.println(toString(), 25);
        MainGame.printlnlnWait(getName() + "'s XP to next level: " + xpToLevelUp, 20, 3000);
        MainGame.promptToEnter();
    }
    
    public void updateStats()
    {
//        removeBuffOrDebuff();
//        removeCheerBuff();
        
        // Sets the value to work with to be its original
        for(Stat s : listOfStats)
        {
            s.setValue(s.getOriginalValue());
        }
        
        switch(playerClass.getPrimaryRole())
        {
            case "Clerk":
                updateClerkStats(60);
                break;
            case "Tank":
                updateTankStats(60);
                break;
            case "Striker":
                updateStrikerStats(60);
                break;
            case "All-Rounder":
                updateAllRounderStats(60);
                break;
        }
        
        // Updates the original value with the new, updated stats
        for(Stat s : listOfStats)
        {
            s.setOriginalValue(s.getValue());
        }
        
        reapplyBuffOrDebuff();
        reapplyCheerBuff();
    }
    
    public void updateClerkStats(int newStatPoints)
    {
        /*
                      HP Atk Def R Atk  R Def SPd
        Master Clerk: 40 30 30   80     80     40
        Hyper Clerk:  35 35 25   90     60     65
        Passive Clerk: 50 50 50  70     75     20
        */
        ArrayList<Integer> listOfPercentages = new ArrayList<>();
        
        int hpIncrease = 0;
        int attackIncrease = 0;
        int defenseIncrease = 0;
        int rAttackIncrease = 0;
        int rDefenseIncrease = 0;
        int speedIncrease = 0;
        
        ArrayList<Integer> listOfStatIncreases = new ArrayList<>();
        listOfStatIncreases.add(attackIncrease);
        listOfStatIncreases.add(defenseIncrease);
        listOfStatIncreases.add(rAttackIncrease);
        listOfStatIncreases.add(rDefenseIncrease);
        listOfStatIncreases.add(speedIncrease);
        
        switch(playerClass.getSpecificClassName())
        {
            case "Master Clerk":
                // Potential HP increase
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 40);
                
                listOfPercentages.add(30);
                listOfPercentages.add(30);
                listOfPercentages.add(80);
                listOfPercentages.add(80);
                listOfPercentages.add(40);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
                break;
            case "Hyper Clerk":
                // Potential HP increase
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 35);
                
                listOfPercentages.add(35);
                listOfPercentages.add(25);
                listOfPercentages.add(90);
                listOfPercentages.add(60);
                listOfPercentages.add(65);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
                break;
            case "Passive Clerk":
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 50);
                
                listOfPercentages.add(50);
                listOfPercentages.add(50);
                listOfPercentages.add(70);
                listOfPercentages.add(75);
                listOfPercentages.add(20);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
        }
    }
    
    private void updateTankStats(int newStatPoints)
    {
        /*
                    HP Atk Def R Atk  R Def SPd
        Master Tank: 90 65 85   35     30     30
        Wild Tank:  80 75 75   35     30     40
        Holy Tank: 70 65 75  45     45     35
        */
        ArrayList<Integer> listOfPercentages = new ArrayList<>();
        
        int hpIncrease = 0;
        int attackIncrease = 0;
        int defenseIncrease = 0;
        int rAttackIncrease = 0;
        int rDefenseIncrease = 0;
        int speedIncrease = 0;
        
        ArrayList<Integer> listOfStatIncreases = new ArrayList<>();
        listOfStatIncreases.add(attackIncrease);
        listOfStatIncreases.add(defenseIncrease);
        listOfStatIncreases.add(rAttackIncrease);
        listOfStatIncreases.add(rDefenseIncrease);
        listOfStatIncreases.add(speedIncrease);
        
        
        switch(playerClass.getSpecificClassName())
        {
            case "Master Tank":
                // Potential HP increase
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 90);
                
                listOfPercentages.add(65);
                listOfPercentages.add(85);
                listOfPercentages.add(35);
                listOfPercentages.add(30);
                listOfPercentages.add(30);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
                break;
            case "Wild Tank":
                // Potential HP increase
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 80);
                
                listOfPercentages.add(75);
                listOfPercentages.add(75);
                listOfPercentages.add(45);
                listOfPercentages.add(45);
                listOfPercentages.add(35);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
                break;
            case "Holy Tank":
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 70);
                
                listOfPercentages.add(65);
                listOfPercentages.add(75);
                listOfPercentages.add(70);
                listOfPercentages.add(75);
                listOfPercentages.add(20);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
        }
    }
    
    private void updateStrikerStats(int newStatPoints)
    {
        /*
                        HP Atk Def R Atk  R Def SPd
        Master Striker: 50 90  30   50    30     80
        Tranquil Striker: 45 75 45  60    45     80
        Guardian Striker: 65 50 50  75    50     70
        */
        ArrayList<Integer> listOfPercentages = new ArrayList<>();
        
        int hpIncrease = 0;
        int attackIncrease = 0;
        int defenseIncrease = 0;
        int rAttackIncrease = 0;
        int rDefenseIncrease = 0;
        int speedIncrease = 0;
        
        ArrayList<Integer> listOfStatIncreases = new ArrayList<>();
        listOfStatIncreases.add(attackIncrease);
        listOfStatIncreases.add(defenseIncrease);
        listOfStatIncreases.add(rAttackIncrease);
        listOfStatIncreases.add(rDefenseIncrease);
        listOfStatIncreases.add(speedIncrease);
        
        switch(playerClass.getSpecificClassName())
        {
            case "Master Striker":
                // Potential HP increase
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 50);
                
                listOfPercentages.add(90);
                listOfPercentages.add(30);
                listOfPercentages.add(50);
                listOfPercentages.add(30);
                listOfPercentages.add(80);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
                break;
            case "Tranquil Striker":
                // Potential HP increase
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 45);
                
                listOfPercentages.add(75);
                listOfPercentages.add(45);
                listOfPercentages.add(60);
                listOfPercentages.add(45);
                listOfPercentages.add(80);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
                break;
            case "Guardian Striker":
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 65);
                
                listOfPercentages.add(50);
                listOfPercentages.add(50);
                listOfPercentages.add(75);
                listOfPercentages.add(50);
                listOfPercentages.add(75);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
        }
    }
    
    private void updateAllRounderStats(int newStatPoints)
    {
        /*
                        HP Atk Def R Atk  R Def SPd
        All-Rounder:    75 75  60   75    60     60
        */
        ArrayList<Integer> listOfPercentages = new ArrayList<>();
        
        int hpIncrease = 0;
        int attackIncrease = 0;
        int defenseIncrease = 0;
        int rAttackIncrease = 0;
        int rDefenseIncrease = 0;
        int speedIncrease = 0;
        
        ArrayList<Integer> listOfStatIncreases = new ArrayList<>();
        listOfStatIncreases.add(attackIncrease);
        listOfStatIncreases.add(defenseIncrease);
        listOfStatIncreases.add(rAttackIncrease);
        listOfStatIncreases.add(rDefenseIncrease);
        listOfStatIncreases.add(speedIncrease);
        
        switch(playerClass.getSpecificClassName())
        {
            case "All-Rounder":
                // Potential HP increase
                newStatPoints = increaseHealth(newStatPoints, hpIncrease, 75);
                
                listOfPercentages.add(75);
                listOfPercentages.add(60);
                listOfPercentages.add(75);
                listOfPercentages.add(60);
                listOfPercentages.add(60);
                
                // Goes through each stat and increases them accordingly
                while(newStatPoints != 0)
                {
                    for(int i = 0; i < listOfStats.size(); i++)
                    {
                        newStatPoints = increaseStat(newStatPoints, listOfStatIncreases.get(i), listOfStats.get(i), listOfPercentages.get(i));
                        
                        if(newStatPoints == 0)
                        {
                            break;
                        }
                    }
                }
                break;
        }
    }
    
    private void removeCheerBuff()
    {
        for(Stat s: listOfStats)
        {
            
            if(s.getCheerBuff() > 0)
            {
                s.setValue(s.getValue() - s.getCheerBuff());
            }
        }
    }
    
    private void reapplyCheerBuff()
    {
        for(Stat s : listOfStats)
        {
            if(s.getCheerBuff() > 0)
            {
                s.setValue(s.getValue() + s.getCheerBuff());
            }
        }
    }
    
    private void removeBuffOrDebuff()
    {
        for(Stat s : listOfStats)
        {
            if(s.getIsDebuffActive())
            {
//                s.setValue((int)(Math.round(s.getValue() / s.getDebuffModifier())));
                s.setValue(s.getOriginalValue());
            }
            
            if(s.getIsBuffActive())
            {
//                s.setValue((int)(Math.round(s.getValue() / s.getBuffModifier())));
                s.setValue(s.getOriginalValue());
            }
        }
    }
    
    private void reapplyBuffOrDebuff()
    {
        for(Stat s : listOfStats)
        {
            if(s.getIsDebuffActive())
            {   
                s.setValue((int)Math.round(s.getValue() * s.getDebuffModifier()));
            }
            
            if(s.getIsBuffActive())
            {   
                s.setValue((int)Math.round(s.getValue() * s.getBuffModifier()));
            }
        }
    }
    
    private int getDistributionAmount(int statDistribution, int newStatPoints)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(10);
        
        // 40% chance to increase stat by 5
        if(randomNum >= 0 && randomNum <= 3)
        {
            statDistribution = 5;
        }
        // 50% chance to increase stat by 10
        else if(randomNum >= 4 && randomNum <= 8)
        {
            statDistribution = 10;
        }
        // 10% chance to increase stat by 20
        else
        {
            statDistribution = 20;
        }
        
        // Makes sure that the statDistribution doesn't exceed how many points are left for leveling up
        if(newStatPoints - statDistribution < 0)
        {
            statDistribution = newStatPoints;
        }
        
        return statDistribution;
    }
    
    private int increaseStat(int newStatPoints, int increasePoints, Stat stat, int percentage)
    {
        int randomNum = (new Random().nextInt(100));
        
        if(randomNum >= 0 && randomNum <= percentage - 1)
        {
            increasePoints = getDistributionAmount(increasePoints, newStatPoints);
            stat.setValue(stat.getValue() + increasePoints);
            newStatPoints -= increasePoints;
            return newStatPoints;
        }
        else
        {
            return newStatPoints;
        }
    }
    
    private int increaseHealth(int newStatPoints, int increasePoints, int percentage)
    {
        int randomNum = (new Random().nextInt(100));
        
        if(randomNum >= 0 && randomNum <= percentage - 1)
        {
            increasePoints = getDistributionAmount(increasePoints, newStatPoints);
            maxHealth += increasePoints;
            newStatPoints -= increasePoints;
            return newStatPoints;
        }
        else
        {
            return newStatPoints;
        }
    }
    
    /**
     * Increases aggro based on the type of Attack (Offensive, Buff, etc.).
     * @param attack 
     */
    public void increaseAggro(Attack attack)
    {
        int amount = 0;
        
        if(attack instanceof OffensiveAttack)
        {
            amount = ((OffensiveAttack)attack).getBaseDamage();
            amount = (amount / 10);
        }
        else if(attack instanceof BuffAttack)
        {
            amount = (int)((BuffAttack)attack).getBuffModifier() * 10;
            amount += 15;
        }
        else if(attack instanceof DebuffAttack)
        {
            amount = (int)((DebuffAttack)attack).getDebuffModifier() * 10;
            amount += 20;
        }
        else if(attack instanceof SingleHealingAttack)
        {
            amount = (int)((SingleHealingAttack)attack).getHealingRate() * 10;
            amount += 25;
        }
        else if(attack instanceof TeamHealingAttack)
        {
            amount = (int)((TeamHealingAttack)attack).getHealingRate() * 10;
            amount += 35;
        }
        else if(attack instanceof ComboAttack)
        {
            amount = ((ComboAttack) attack).getBaseDamage();
            amount = (amount / 10) + 15;
        }
        
        amount = multiplyAggroAmount(amount);
        aggro += amount;
    }
    
    private int multiplyAggroAmount(int amount)
    {
        if(playerClass.isMasterTank())
        {
            amount = Math.round(amount * 2);
        }
        else if(playerClass.isPrimaryTank())
        {
            amount = (int)Math.round(amount * 1.75);
        }
        else if(playerClass.isSecondaryTank())
        {
            amount = (int)Math.round(amount * 1.5);
        }
        
        return amount;
    }
    
    public void resetAggro()
    {
        if(playerClass.isPrimaryTank())
        {
            aggro = 10;
        }
        else if(playerClass.isSecondaryTank())
        {
            aggro = 5;
        }
        else
        {
            aggro = 0;
        }
    }
    
    /**
     * Resets the next available turn for an attack so that it's usable for the next battle.
     */
    public void resetAttacks()
    {
        for(Attack a : currentAttacks)
        {
            a.resetNextAvailableTurn();
        }
    }
    
    public void printDeathMessage()
    {
        MainGame.dialoguelnln(name, deathMessage);
    }
    
    public void listKnownAttacks()
    {
        for(Attack attack : currentAttacks)
        {
            MainGame.printlnln(attack.toString(), 5);
        }
            
    }
    
    /**
     * Finds the player with the highest aggro and returns a String with the player's name with the "(!)" indicator.
     * @param adjacentPlayers
     * @return 
     */
    public String getNameWithAggroMarker(ArrayList<Player> adjacentPlayers)
    {
        // If the player has the highest aggro, add the indicator
        if(this.equals(getHighestAggro(adjacentPlayers)))
        {
            return name + " (!)";
        }
        // else return the name
        else
        {
            return name;
        }
    }
    
    /**
     * Checks the aggro of every character in the team and returns the character with the highest aggro.
     * @param team
     * @return the character with the highest aggro
     */
    public static Player getHighestAggro(ArrayList<Player> team)
    {
        if(team.size() == 1)
        {
            return team.get(0);
        }
        
        Player target = team.get(0);
        int highestAggro = target.getAggro();
        
        for(Player player : team)
        {
            if(player.getAggro() > highestAggro)
            {
                highestAggro = player.getAggro();
                target = player;
            }
        }
        
        return target;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Player))
        {
            return false;
        }
        
        Player p = (Player) o;
        
        return this.name.equals(p.name) && this.element.equals(p.element) && this.description.equals(p.description) && 
                this.level == p.level && this.attack == p.attack && this.defense == p.defense && 
                this.rangedAttack == p.rangedAttack && this.rangedDefense == p.rangedDefense && this.speed == p.speed;
    }
    
    @Override
    public String toString()
    {
        return this.name + "'s level: " + this.level + "\nRole: " + this.playerClass.getSpecificClassName() + "\nStats: \n\tHealth: " + this.currentHealth + "/" + this.maxHealth +
                "\n\t" + this.attack + "\n\t" + this.defense + "\n\t" + this.rangedAttack +
                "\n\t" + this.rangedDefense + "\n\t" + this.speed + "\n";
    }
    
    public String toStringOriginalStats()
    {
        return this.name + "'s level: " + this.level + "\nRole: " + this.playerClass.getSpecificClassName() + "\nStats: \n\tHealth: " + this.currentHealth + "/" + this.maxHealth +
                "\n\t" + this.attack.toStringOriginalValue() + "\n\t" + this.defense.toStringOriginalValue() + 
                "\n\t" + this.rangedAttack.toStringOriginalValue() + "\n\t" + this.rangedDefense.toStringOriginalValue() + 
                "\n\t" + this.speed.toStringOriginalValue() + "\n";
    }
    
    public String toOverallString()
    {
        return this.name + ": " + this.description + "\nLevel: " + this.level + "\nRole: " + this.playerClass.getSpecificClassName() + "\nStats: \n\t" + this.currentHealth + "/" + this.maxHealth +
                "\n\t" + this.attack + "\n\t" + this.defense + "\n\t" + this.rangedAttack +
                "\n\t" + this.rangedDefense + "\n\t" + this.speed + "\n\n\t" + "XP to level up: " + xpToLevelUp;
    }
}
