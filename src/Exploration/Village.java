 package Exploration;

import Battle.Enemy;
import Battle.Player;
import Battle.RESIEnemy;
import Battle.RESITutorialBattle;
import Game.MainGame;
import Utilites.MenuHelper;
import java.util.ArrayList;

/**
 * A class for creating Villages the player can briefly explore
 * @author Ian King
 */
public class Village extends Location
{
    private ArrayList<NPC> villagePeople;
    private Shop shop;
    private int population;
    private boolean hasTalkedToEveryone;
    
//    public Village(String name, String description, ArrayList<NPC> people, int requiredLevel)
//    {
//        this.name = name;
//        this.description = description;
//        super.requiredLevel = requiredLevel;
//        this.villagePeople = people;
//        super.chest = makeChest();
//        shop = new Shop();
//    }
    
    public Village(String name, String description, ArrayList<NPC> people, int requiredLevel, int population, Coordinate coordinate)
    {
        super(name, description, requiredLevel, coordinate);
        this.villagePeople = people;
        this.population = population;
    }
    
    public Village(String name, String description, ArrayList<NPC> people, int requiredLevel, int population, Coordinate coordinate, Shop shop)
    {
        super(name, description, requiredLevel, coordinate);
        this.villagePeople = people;
        this.population = population;
        this.shop = shop;
    }
    
    public ArrayList<NPC> getVillagePeople() {return villagePeople;}
    public void setVillagePeople(ArrayList<NPC> people) {villagePeople = people;}
    
    @Override
    public int getRequiredLevel() {return requiredLevel;}
    
    public void setRequiredLevel(int level) {requiredLevel = level;}
    
    public Shop getShop(){return shop;}
    
    public void setShop(Shop s){shop = s;}
    
    public int getPopulation() {return population;}
    
    public void setTalkedToEveryone(boolean value) {this.hasTalkedToEveryone = value;}
    
    public String getPopulationString()
    {
        return String.format("%,d", population);
    }
    
    public void setPopulation(int num) {population = num;}
    
    public void talkToPeople()
    {
        if(!villagePeople.isEmpty())
        {
            promptToTalk();
        }
        else
        {
            MainGame.printlnln("There's no one to talk to...", 5);
        }
    }
    
    public void promptToTalk()
    {
        MainGame.clearScreen();
        
        String message = "Who would you like to talk to?\n\t";
        int numOfOptions = 0; 
        
        for(NPC person : villagePeople)
        {
            message += ++numOfOptions + ") " + person.getName() + " (" + person.getDescription() + ")\n\t";
        }
        
        message += ++numOfOptions + ") Back";
        
        int input = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        talkTo(input, numOfOptions);
    }
    
    public void talkTo(int input, int numOfOptions)
    {
        if(input != numOfOptions)
        {
            System.out.println("");
            NPC person = villagePeople.get(input - 1);
            person.talk();
            promptToTalk();
        }
    }
    
//    public void findChest()
//    {
//        findChest();
////        if(hasFullChest() && !chest.isAlreadyFound())
////        {
////            openChest();
////        }
////        else if(hasFullChest() && chest.isAlreadyFound())
////        {
////            int chance = new Random().nextInt(2);
////            
////            switch(chance)
////            {
////                case 0:
////                    openChest();
////                    break;
////                case 1:
////                    MainGame.printlnlnWait("You couldn't find a chest...", 25, 2000);
////                    break;
////            }
////        }
////        else
////        {
////            MainGame.printlnlnWait("You couldn't find a chest...", 25, 2000);
////        }
//    }
    
//    private boolean hasFullChest()
//    {
//        return !chest.isEmpty();
//    }
//    
//    private void openChest()
//    {
//        MainGame.printlnln("You found a chest! What does it have?", 25);
//        chest.open();
//    }
    
    /**
     * Returns an NPC by searching for a certain name.
     * @param nameOfPersonWanted
     * @return null if NPC doesn't exist or if list of NPCs is empty
     */
    public NPC getPerson(String nameOfPersonWanted)
    {
        if(!villagePeople.isEmpty())
        {
            nameOfPersonWanted = nameOfPersonWanted.toLowerCase();
            String personName;

            for(NPC person : villagePeople)
            {
                personName = person.getName().toLowerCase();

                if(nameOfPersonWanted.equals(personName))
                {
                    return person;
                }
            }

            return null;
        }
        else
        {
            return null;
        }
    }
    
    public boolean hasTalkedToEveryone()
    {
        boolean result = false;
        
        for(NPC person : villagePeople)
        {
            if(!person.hasBeenTalkedTo())
            {
                result = false;
                break;
            }
            else
            {
                result = true;
            }
        }
        
        this.hasTalkedToEveryone = result;
        
        return result;
    }
    
    /**
     * Returns an NPC given a specific name (capitalization needed).
     * @param name
     * @return an NPC
     */
    public NPC getNPC(String name)
    {
        for(NPC person : villagePeople)
        {
            if(name.equals(person.getName()))
            {
                return person;
            }
        }
        
        return null;
    }
    
    /**
     * Returns a tutorial battle sequence to teach the player how to play.
     * @param player
     * @return a tutorial battle
     */
    public RESITutorialBattle makeRESITutorial(ArrayList<Player> playerTeam)
    {
        RESIEnemy resi = new RESIEnemy(12, "Fire");
//        resi.setCurrentHealth(1);
//        resi.setSpeed(1);
        ArrayList<Enemy> enemyTeam = new ArrayList<>(1);
        enemyTeam.add(resi);
        RESITutorialBattle battle = new RESITutorialBattle(enemyTeam, playerTeam);
        return battle;
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }
}
