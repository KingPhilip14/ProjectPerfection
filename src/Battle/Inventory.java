package Battle;

import Game.Game;
import Game.MainGame;
import Utilites.ItemNameComparator;
import Utilites.ItemTypeComparator;
import Utilites.Sort;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class for maintaining and managing the player's inventory
 * @author Ian King
 */
public class Inventory implements Serializable
{
    ArrayList<Item> inventory;
    
    public Inventory()
    {
        inventory = new ArrayList<>();
    }
    
    public void addTo(Item item, int quantity)
    {
        boolean hasItem = false;
        
        // Check if the item is already in the inventory. If so, increase the quantity
        for(Item i : inventory)
        {
            if(i.getName().equals(item.getName()))
            {
                hasItem = true;
                i.increaseQuantity(quantity);
            }
        }
        
        // If the inventory doesn't have it already, set it's quantity and add it to the inventory
        if(!hasItem)
        {
            item.setQuantity(quantity);
            inventory.add(item);
            
            if(inventory.size() != 1)
            {
                sort();
            }
        }
    }
    
    public void showInventory()
    {
        if(!inventory.isEmpty())
        {
            for(Item i : inventory)
            {
                MainGame.printlnln(i.toInventoryString(), 25);
            }
        }
        
        MainGame.wait(1000);
    }
    
    public String inventoryListForMenus()
    {
        String message = "";
        
        for(int i = 0; i < inventory.size(); i++)
        {
            message += "\n\t" + (i + 1) + ") " + inventory.get(i).getName() + " - x" + inventory.get(i).getQuantity();
        }
        
        return message;
    }
    
    public String inventoryListForSelling()
    {
        String message = "";
        
        for(int i = 0; i < inventory.size(); i++)
        {
            Item item = inventory.get(i);
            message += "\n\t" + (i + 1) + ") " + item.getName() + " x" + String.format("%,d", item.getQuantity()) + 
                    " -- Sell Price: " + item.getSalePriceString();
        }
        
        return message;
    }
    
    public boolean isEmpty() {return inventory.isEmpty();}
    
    public int size() {return inventory.size();}
    
    public void remove(Item item)
    {
        inventory.remove(item);
    }
    
    public Item get(int index)
    {
        return inventory.get(index);
    }
    
    public void sellItem(Item item, int quantity)
    {
        Item soldItem = null;
        
        for(Item i : inventory)
        {
            if(i.getName().equals(item.getName()))
            {
                soldItem = i;
                break;
            }
        }
        
        // The player can't choose an item to sell that they don't have, so this isn't a concern
        soldItem.decreaseQuantity(quantity);
        
        // If there are no more of the item, remove it
        if(soldItem.getQuantity() == 0)
        {
            inventory.remove(item);
        }
        
        // Increase gold
        Game.increaseGold(item.getSalePrice() * quantity);
    }
    
    public void sort()
    {
        ItemNameComparator inc = new ItemNameComparator();
        ItemTypeComparator itc = new ItemTypeComparator();
        
        inventory = Sort.radixSort(inventory, itc, inc, null, null, null, 2);
    }
}
