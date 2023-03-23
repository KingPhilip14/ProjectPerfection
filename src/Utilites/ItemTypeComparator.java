package Utilites;

import Battle.BuffItem;
import Battle.HealingItem;
import Battle.Item;

/**
 * A class for comparing the types of Items.
 * @author Ian King
 */
public class ItemTypeComparator implements Comparator<Item>
{
    @Override
    public int compare(Item item1, Item item2)
    {
        if(item1 == null || item2 == null)
        {
            throw new NullPointerException("An Item is null.");
        }
        
        int result = 0;
        
        if(item1 instanceof HealingItem)
        {
            if(item2 instanceof HealingItem)
            {
                result = 0;
            }
            else
            {
                result = -1;
            }
        }
        else if(item1 instanceof BuffItem)
        {
            if(item2 instanceof BuffItem)
            {
                result = 0;
            }
            else
            {
                // Healing Items are listed before Buff Items
                result = 1;
            }
        }
        
        return result;
    }
}
