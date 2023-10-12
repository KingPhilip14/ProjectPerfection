package Exploration;

import Battle.GameProperty;
import Battle.Player;
import Game.MainGame;
import java.util.ArrayList;
import java.util.Random;

/**
 * An abstract class that helps create different locations.
 * @author Ian King
 */
public abstract class Location extends GameProperty
{
    protected ArrayList<String> speciesNames = new ArrayList<>();
    protected boolean explored;
    protected boolean unlocked;
    protected TreasureChest chest;
    protected int requiredLevel;
    protected Coordinate coordinate;
    
    public Location(String name, String description, int requiredLevel, Coordinate coordinate)
    {
        this.name = name;
        this.description = description;
        this.requiredLevel = requiredLevel;
        this.coordinate = coordinate;
        makeChest();
    }
    
    public ArrayList<String> getSpeciesNames() {return speciesNames;}
    public void setSpeciesNames(ArrayList<String> listOfNames) {speciesNames = listOfNames;}
    
    public boolean isExplored() {return explored;}
    public int getRequiredLevel() {return requiredLevel;}
    
    public int getXCoordinate() {return coordinate.getXCoordinate();}
    
    public int getYCoordinate() {return coordinate.getYCoordinate();}
    
    /**
     * Automatically sets explored to be true.
     */
    public void setIsExplored() 
    {
        if(!explored)
        {
            explored = true;
        }
    }
    
    public TreasureChest getChest() {return chest;}
    public void setChest(TreasureChest chest) {this.chest = chest;}
    
    public boolean isUnlocked() {return unlocked;}
    public void setUnlocked(boolean value) {unlocked = value;}
    
    private void makeChest()
    {
        Random rand = new Random();
        
        int chance = rand.nextInt(100);
        
        // 70% to have a chest with 1 item
        if(chance >= 0 && chance <= 69)
        {
            chest = new TreasureChest(1);
        }
        // 29% chance to have a chest with 2 items
        else if(chance >= 70 && chance <= 98)
        {
            chest = new TreasureChest(2);
        }
        // 1% chance to have a chest with 3 items
        else
        {
            chest = new TreasureChest(3);
        }
    }
    
    private boolean hasFullChest()
    {
        return !chest.isEmpty();
    }
    
    private void openChest()
    {
        MainGame.printlnln("\nYou found a chest! What does it have?");
        chest.open();
    }
    
    public void findChest()
    {
        if(hasFullChest() && !chest.isAlreadyFound())
        {
            openChest();
        }
        else if(hasFullChest() && chest.isAlreadyFound())
        {
            int chance = new Random().nextInt(2);
            
            switch(chance)
            {
                case 0:
                    openChest();
                    break;
                case 1:
                    MainGame.printlnlnWait("You couldn't find a chest...", 25, 500);
                    break;
            }
        }
        else
        {
            MainGame.printlnlnWait("\nYou couldn't find a chest...", 25, 500);
        }
        
//        MainGame.waitForEnter();
    }
    
    public void replenishChest()
    {
        int randNum = new Random().nextInt(5);
                
        if(randNum == 0)
        {
            chest = new TreasureChest(new Random().nextInt(3) + 1);
        }
    }
    
    public void travelDescription(Location location1, Location location2, ArrayList<Player> team)
    {
        int size = team.size();
        
        if(size == 1)
        {
            Player p = team.get(0);
            MainGame.printlnln("\n" + p.getName() + " traveled from " + location1.getName() + 
                " to " + location2.getName() + ".");
            MainGame.dialoguelnln(p, "Time to explore " + location2.getName() + "! Let's see how this goes." );
            MainGame.clearScreen();
            return;
        }
        else
        {
            MainGame.printlnln("\nAnahita and the others traveled from " + location1.getName() + 
                " to " + location2.getName() + ".");
        }    
        
        MainGame.wait(2000);
        Player player = team.get(new Random().nextInt(team.size()));
        
        int randomNum = new Random().nextInt(4);
        switch(randomNum)
        {
            case 0:
                MainGame.dialoguelnln(player, "Time to explore " + location2.getName() + "! Let's see how this goes.");
                break;
            case 1:
                MainGame.dialoguelnln(player, location2.getName() + " huh? This'll be interesting...");
                break;
            case 2:
                MainGame.dialoguelnln(player, "Let's make sure to stay together!");
                break;
            case 3:
                MainGame.dialoguelnln(player, "I think it'll be fun to explore " + location2.getName() + "..."
                    + " What do you guys think?");
                break;
        }
        
        MainGame.clearScreen();
    }
}
