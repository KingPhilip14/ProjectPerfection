package Exploration;

import Battle.GameProperty;
import Battle.Item;
import Game.Game;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for creating Treasure Chests to give the Player a variety of Items and Gold.
 * @author Ian King
 */
public class TreasureChest extends GameProperty
{
    private ArrayList<Item> contents;
    private boolean alreadyFound;
    
    public TreasureChest(int numOfItems) throws IllegalArgumentException
    {
        super.name = "Treasure Chest";
        super.description = "A chest probably full of good things.";
        alreadyFound = false;
        this.contents = new ArrayList();
        
        if(numOfItems != 0)
        {
            for(int i = 0; i < numOfItems; i++)
            {
                ArrayList<Item> allItems = Item.allItemsDeepCopy();
                Item item = allItems.remove(new Random().nextInt(allItems.size()));
                setItemQuantity(item);
                contents.add(item);
            }
            
            Item.refreshAllItems();
        }
        else if(numOfItems < 0 || numOfItems > 5)
        {
            throw new IllegalArgumentException("A chest cannot have less than 0 items nor more than 5 items.");
        }
    }
    
    /**
     * Sets the quantity of the given item based on a random chance.
     * @param item 
     */
    private void setItemQuantity(Item item)
    {
        int chance = new Random().nextInt(100);
        
        // 60% chance for quantity of 1
        if(chance >= 0 && chance <= 59)
        {
            item.setQuantity(1);
        }
        // 39% chance for quantity of 2
        else if(chance >= 60 && chance <= 98)
        {
            item.setQuantity(2);
        }
        else
        {
            item.setQuantity(3);
        }
    }
    
    public ArrayList<Item> getContents() {return contents;}
    
    public boolean isEmpty()
    {
        return contents.isEmpty();
    }
    
    public boolean isAlreadyFound() {return alreadyFound;}
    public void setAlreadyFound(boolean value) {alreadyFound = value;}
    
    public void open()
    {
        for(Item item : contents)
        {
            item.printReceivedMessage();
            Game.addToInventory(item, item.getQuantity());
        }
        
        int contentsSize = contents.size();
        
        for(int i = 0; i < contentsSize; i++)
        {
            contents.remove(0);
        }
        
        alreadyFound = true;
    }
}
