package Utilites;

import Battle.Item;

/**
 * A class that compares the values of the names of two different Items.
 * @author Ian King
 */
public class ItemNameComparator implements Comparator<Item>
{
    @Override
    public int compare(Item item1, Item item2)
    {
        if(item1 == null || item2 == null)
        {
            throw new NullPointerException("An Item is null.");
        }
        
        int result = 0;
        
        String i1Name = item1.getName();
        String i2Name = item2.getName();
        int i1NameLength = i1Name.length();
        int i2NameLength = i2Name.length();
        int longestNameLength;
        int shortestNameLength = 0;
        
        if(i1NameLength > i2NameLength)
        {
            longestNameLength = i1NameLength;
            shortestNameLength = i2NameLength;
        }
        else if(i2NameLength > i1NameLength)
        {
            longestNameLength = i2NameLength;
            shortestNameLength = i1NameLength;
        }
        else
        {
            longestNameLength = i1NameLength;
            shortestNameLength = i1NameLength;
        }
        
        for(int i = 0; i < longestNameLength; i++)
        {
            /*
            If statement is used only if the two names are not the same length. The shorter name would come before the longer
            */
            if(i == shortestNameLength)
            {
                if(shortestNameLength == i1Name.length())
                {
                    result = -1;
                    break;
                }
                else if(shortestNameLength == i2Name.length())
                {
                    result = 1;
                    break;
                }
            }
            
            if(i1Name.charAt(i) != i2Name.charAt(i))
            {
                /* 
                Checks if the first item's letter at charAt(i) is less than the letter in the second item's name.
                If so, then it has higher alphabetical value
                */
                if(i1Name.charAt(i) < i2Name.charAt(i))
                {
                    result = -1;
                    break;
                }
                else if(i1Name.charAt(i) > i2Name.charAt(i))
                {
                    result = 1;
                    break;
                }
            }
            else
            {
                result = 0;
            }
        }
        
        return result;
    }
}
