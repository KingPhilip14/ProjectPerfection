package Battle;

import Game.MainGame;
import Utilites.ElementComparator;
import java.util.ArrayList;

/**
 * A class for creating combo attacks.
 * @author Ian King
 */
public class ComboAttack extends Attack
{
    // Water Fire Ice Wind Earth Electric
    //   3    3    3    3    1      3
    
    /*
    Fire + Water = Exploding Geyser
    Fire + Wind = Infinite Inferno
    Fire + Electric = Flaming Bolt
    
    Water + Wind = Final Storm
    Water + Electric = Electro Wave
    Water + Fire = Exploding Geyser
    
    Ice + Wind = Greater Fimbulvetr
    Ice + Earth = Permafrost Pummel
    Ice + Electric =  Frozen Surge
    
    Earth + Fire = Burning Quake
    Earth + Wind = Precipice Gale
    Earth + Ice = Permafrost Pummel
    
    Wind + Fire = Infinite Inferno
    Wind + Water = Final Storm
    Wind + Ice = Greater Fimbulvetr
    
    Electric + Fire = Flaming Bolt
    Electric + Water = Electro Wave
    Electric + Ice = Frozen Surge
    
    */
    
    private static ArrayList<ComboAttack> comboAttackLibrary = new ArrayList<>();
    private static boolean availableToUse;
    private int baseDamage;
    private String element1;
    private String element2;
    private double elementEffectiveness1;
    private double elementEffectiveness2;
    
//    private static ComboAttack explodingGeyser = new ComboAttack("Exploding Geyser", "Combining the power of water and fire creates scalding-hot water to erupt and hurt the target.", 315, "Fire", "Water");
//    private static ComboAttack infiniteInferno = new ComboAttack("Infinite Inferno", "Intense heat and violent winds combine to create what feels like a never ending inferno.", 330, "Fire", "Wind");
//    private static ComboAttack flamingBolt = new ComboAttack("Flaming Bolt", "The deadly combination of a massive thunderbolt and searing flames creates the Flaming Bolt.", 320, "Fire", "Electric");
//    private static ComboAttack finalStorm = new ComboAttack("Final Storm", "Extreme winds and endless amounts of water combine to create what some consider the \"Storm of Storms,\" the most destructive storm of all.", 330, "Water", "Wind");
//    private static ComboAttack electroWave = new ComboAttack("Electro Wave", "A large tsunami combined with electricity creates this dangerous attack.", 340, "Water", "Electric");
//    private static ComboAttack greaterFimbulvetr = new ComboAttack("Greater Fimbulvetr", "With a bitter and nasty combination of ice, snow, hail, and wind, this creates what's known as the \"Greater Fimbulvetr.\"", 350, "Ice", "Wind");
//    private static ComboAttack permafrostPummel = new ComboAttack("Permafrost Pummel", "Chunks of earth are combined with a thick frozen layer to then rain down on the target.", 335, "Ice", "Earth");
//    private static ComboAttack frozenSurge = new ComboAttack("Frozen Surge", "A massive shock wave is combined with frigid air and ice to inflict massive damage.", 325, "Ice", "Electric");
//    private static ComboAttack burningQuake = new ComboAttack("Burning Quake", "An earthquake is combined with flames that burst from the ground to cause devistating damage.", 330, "Earth", "Fire");
//    private static ComboAttack precipiceGale = new ComboAttack("Precipice Gale", "Giants boulders and land masses are launched at the target with great winds and tornados.", 330, "Earth", "Wind");
    
//    public ComboAttack(String name, String description, int baseDamage, Player player1, Player player2)
//    {
//        super.name = name;
//        super.description = description;
//        this.baseDamage = baseDamage;
//    }
//    
//    public ComboAttack(String name, String description, int baseDamage)
//    {
//        super.name = name;
//        super.description = description;
//        this.baseDamage = baseDamage;
//    }
    
    private ComboAttack(String name, String description, int baseDamage, String element1, String element2)
    {
        super.name = name;
        super.description = description;
        this.baseDamage = baseDamage;
        this.element1 = element1;
        this.element2 = element2;
    }
    
    public int getBaseDamage() {return baseDamage;}
    public ArrayList<ComboAttack> getComboAttackLibrary() {return comboAttackLibrary;}
    
    public String getElement1() {return element1;}
    public String getElement2() {return element2;}
    public void setElement1(String element) {element1 = element;}
    public void setElement2(String element) {element2 = element;}
    
    public static void populateComboAttackLibrary()
    {
        ComboAttack explodingGeyser = new ComboAttack("Exploding Geyser", "Combining the power of water and fire creates scalding-hot water to erupt and hurt the target.", 325, "Fire", "Water");
        ComboAttack infiniteInferno = new ComboAttack("Infinite Inferno", "Intense heat and violent winds combine to create what feels like a never ending inferno.", 330, "Fire", "Wind");
        ComboAttack flamingBolt = new ComboAttack("Flaming Bolt", "The deadly combination of a massive thunderbolt and searing flames creates the Flaming Bolt.", 320, "Fire", "Electric");
        ComboAttack finalStorm = new ComboAttack("Final Storm", "Extreme winds and endless amounts of water combine to create what some consider the \"Storm of Storms,\" the most destructive storm of all.", 330, "Water", "Wind");
        ComboAttack electroWave = new ComboAttack("Electro Wave", "A large tsunami combined with electricity creates this dangerous attack.", 340, "Water", "Electric");
        ComboAttack greaterFimbulvetr = new ComboAttack("Greater Fimbulvetr", "With a bitter and nasty combination of ice, snow, hail, and wind, this creates what's known as the \"Greater Fimbulvetr.\"", 350, "Ice", "Wind");
        ComboAttack permafrostPummel = new ComboAttack("Permafrost Pummel", "Chunks of earth are combined with a thick frozen layer to then rain down on the target.", 335, "Ice", "Earth");
        ComboAttack frozenSurge = new ComboAttack("Frozen Surge", "A massive shock wave is combined with frigid air and ice to inflict massive damage.", 325, "Ice", "Electric");
        ComboAttack burningQuake = new ComboAttack("Burning Quake", "An earthquake is combined with flames that burst from the ground to cause devistating damage.", 330, "Earth", "Fire");
        ComboAttack precipiceGale = new ComboAttack("Precipice Gale", "Giants boulders and land masses are launched at the target with great winds and tornados.", 330, "Earth", "Wind");
        
        comboAttackLibrary.add(explodingGeyser);
        comboAttackLibrary.add(infiniteInferno);
        comboAttackLibrary.add(flamingBolt);
        comboAttackLibrary.add(finalStorm);
        comboAttackLibrary.add(electroWave);
        comboAttackLibrary.add(greaterFimbulvetr);
        comboAttackLibrary.add(permafrostPummel);
        comboAttackLibrary.add(frozenSurge);
        comboAttackLibrary.add(burningQuake);
        comboAttackLibrary.add(precipiceGale);
    }
    
    
    public int calculateDamage(Player attacker1, Player attacker2, Enemy target)
    {
        int attack1 = attacker1.getHighestAttackStat();
        int attack2 = attacker2.getHighestAttackStat();
        int defense = target.getHighestDefenseStat();
        
        elementEffectiveness1 = calculateElementEffectiveness(attacker1, target);
        elementEffectiveness2 = calculateElementEffectiveness(attacker2, target);
        
        int damage = (int)(Math.round((baseDamage * attack1 * attack2 * elementEffectiveness1 * elementEffectiveness2) / (Math.pow(defense, 2) + Math.pow(target.getLevel() * 10, 2))));
        
        return damage;
    }
    
    public void applyDamage(int damage, Player attacker1, Player attacker2, Enemy target)
    {
        target.setCurrentHealth(target.getCurrentHealth() - damage);
        
        if(target.getCurrentHealth() < 0)
        {
            target.setCurrentHealth(0);
        }
        
        MainGame.printlnln("\n" + attacker1.getName() + " and " + attacker2.getName() + " used the combo attack, " + name + "!", 5);
        MainGame.wait(500);
        
        if(elementEffectiveness1 > 1)
        {
            MainGame.printlnln(attacker1.getName() + "'s contribution to the combo was super effective!", 5);
        }
        else if(elementEffectiveness1 < 1)
        {
            MainGame.printlnln(attacker1.getName() + "'s contribution to the combo was not very effective.", 5);
        }
        
        MainGame.wait(1000);
        
        if(elementEffectiveness2 > 1)
        {
            MainGame.printlnln(attacker2.getName() + "'s contribution to the combo was super effective!", 5);
        }
        else if(elementEffectiveness2 < 1)
        {
            MainGame.printlnln(attacker2.getName() + "'s contribution to the combo was not very effective.", 5);
        }
        
        MainGame.wait(1000);
        
        MainGame.printlnln(attacker1.getName() + " and "  + attacker2.getName() + " dealt " + damage + " HP!", 5);
    }
    
    public void attack(Player attacker1, Player attacker2, Enemy target)
    {
        applyDamage(calculateDamage(attacker1, attacker2, target), attacker1, attacker2, target);
    }
    
    private double calculateElementEffectiveness(Player attacker, Enemy target)
    {    
        ElementComparator comparator = new ElementComparator();
        double result;
        
        switch (comparator.comparePToE(attacker, target)) 
        {
            case 1:
                result = 1.5;
                break;
            case 0:
                result = 1;
                break;
            default:
                result = 0.5;
        }
        
        return result;
    }
    
    public static boolean canUse(Player player)
    {
        if(player.getCheerPartner() != null)
        {
            checkElementCompatability(player);
        }
        else
        {
            availableToUse = false;
        }
        
        return availableToUse;
    }
    
    private static void checkElementCompatability(Player player)
    {
        String playerElement = player.getElement();
        String cheerElement = player.getCheerPartner().getElement();
        
        switch (playerElement) 
        {
            case "Fire":
                if(cheerElement.equals("Water") || cheerElement.equals("Wind") || cheerElement.equals("Electric"))
                {
                    availableToUse = true;
                }   
                break;
            case "Water":
                if(cheerElement.equals("Wind") || cheerElement.equals("Electric") || cheerElement.equals("Fire"))
                {
                    availableToUse = true;
                }   
                break;
            case "Ice":
                if(cheerElement.equals("Wind") || cheerElement.equals("Earth") || cheerElement.equals("Electric"))
                {
                    availableToUse = true;
                }   
                break;
            case "Earth":
                if(cheerElement.equals("Fire") || cheerElement.equals("Wind") || cheerElement.equals("Ice"))
                {
                    availableToUse = true;
                }   
                break;
            case "Wind":
                if(cheerElement.equals("Fire") || cheerElement.equals("Water") || cheerElement.equals("Ice"))
                {
                    availableToUse = true;
                }   
                break;
            case "Electric":
                if(cheerElement.equals("Fire") || cheerElement.equals("Water") || cheerElement.equals("Ice"))
                {
                    availableToUse = true;
                }   
                break;
            default:
                availableToUse = false;
                break;
        }
    }
    
    public static void getComboAttacks(Player player)
    {
        String element = player.getElement();
        
        for(ComboAttack ca : comboAttackLibrary)
        {
            if(ca.getElement1().equals(element) || ca.getElement2().equals(element))
            {
                player.addComboAttack(ca);
            }
        }
    }
    
    @Override
    public String toString()
    {
        return "\t" + name + ":\n\t\t" + description + "\n\t\tBase Damage: " + baseDamage + "\n\t\tFirst Element: " + element1 +
                "\n\t\tSecond Element: " + element2;           
    }
}
