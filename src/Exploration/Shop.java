package Exploration;

import Battle.GameProperty;
import Battle.Item;
import Game.Game;
import Game.MainGame;
import Utilites.MenuHelper;
import java.util.ArrayList;

/**
 * A class for creating Shops for the player to buy or sell Items.
 * @author Ian King
 */
public class Shop extends GameProperty
{
    private ArrayList<Item> soldItems;
    private final String MOTTO = "\'We're at the right place at the right time with the right items!\'";
    private boolean alreadyEntered;
    
    public Shop()
    {
        name = "Berry's Shop";
        description = "Welcome to Berry's Shop, where " + MOTTO;
    }
    
    public Shop(ArrayList<Item> carriedItems)
    {
        name = "Berry's Shop";
        description = "Welcome to Berry's Shop, where " + MOTTO;
        this.soldItems = carriedItems;
    }
    
    public ArrayList<Item> getSoldItems() {return soldItems;}
    public void setSoldItems(ArrayList<Item> items) {soldItems = items;}
    
    public void activateGreeting()
    {   
        // If in the first phase, greet the player like this
        if(!Game.getSecondPhase())
        {
            MainGame.dialoguelnln("Berry", description);
            MainGame.dialoguelnln("Berry", "Take a look at our inventory and lemme know what ya wanna do!");
        }
        // Otherwise, greet the player like this.
        else
        {
            MainGame.dialoguelnln("Berry", "... Ah! A customer! Welcome to my shop. Please, let me know if there's anything "
                    + "I can do for ya.");
        }
    }
    
    public void startShopping()
    {
        // If the player ever comes back to this inital menu, the greeting won't be displayed again
        if(!alreadyEntered)
        {
            activateGreeting();
        }
        
        alreadyEntered = true;
        
        String message = "What would you like to do?\n\t1) Buy\n\t2) Sell\n\t3) Exit";
        
        int input = MenuHelper.displayMenu(message, 1, 3);
        
        shopOptions(input);
    }
    
    private void shopOptions(int input)
    {
        switch(input)
        {
            case 2:
                sellItems();
                break;
            case 3:
                exit();
                break;
            default:
                selectItem();
                break;
        }
    }
    
    private void sellItems()
    {
        int inventorySize = Game.getInventory().size();
        
        System.out.println("");
        MainGame.dialoguelnln("Berry", "What would ya like to sell? I'll take anything!");
        
        MainGame.print("(Your Inventory\t Current Gold: " + Game.getGoldString() + ")", 5);
        String message = Game.getInventory().inventoryListForSelling() + 
                "\n\t" + ++inventorySize + ") Back";
        
        int input = MenuHelper.displayMenu(message, 1, inventorySize);
        
        if(input == inventorySize)
        {
            startShopping();
        }
        else
        {
            Item item = Game.getInventory().get(--input);
            promptToSell(item);
        }
    }
    
    private void promptToSell(Item item)
    {
        MainGame.dialoguelnln("Berry", "And how many would ya like to sell?");
        String message = "(Type the number you'd like to sell)";
        
        int quantity = MenuHelper.displayMenu(message, 1, item.getQuantity());
        
        completeSale(item, quantity);
    }
    
    private void completeSale(Item item, int quantity)
    {
        int amt = item.getSalePrice()* quantity;
        
        if(quantity == 1)
        {
            MainGame.dialoguelnln("Berry", "Okay! I'll take that off your hands and give you " + String.format("%,d", amt) + "!");
        }
        else
        {
            MainGame.dialoguelnln("Berry", "Okay! I'll take " + quantity + " " + item.getName() + "s from ya, and I'll give ya "
                    +  String.format("%,d", amt) + "!");
        }
        
        Game.getInventory().sellItem(item, quantity);
//        int goldAmt = item.getSalePrice() * quantity;
//        
//        Game.increaseGold(goldAmt);
        MainGame.printlnlnWait("Current Gold: " + Game.getGoldString(), 25, 1000);
        MainGame.promptToEnter();
        
        promptToShopAgain();
    }
    
    private void selectItem()
    {
        String message = "\n(What would you like to buy?\tCurrent Gold: ";
        message += String.format("%,d", Game.getGold()) + " G)\n\t";
        Item item;
        
        // Stores max amount of options player has
        int max = 0; 
        
        for(int i = 0; i < soldItems.size(); i++)
        {
            item = soldItems.get(i);
            message += (i + 1) + ") " + item.getName() + ": " + item.getDescription() + " (" + item.getPrice() + " G)\n\t";
            max++;
        }
        
        message += (soldItems.size() + 1) + ") Exit";
        max++;
        
        int input = MenuHelper.displayMenu(message, 1, max);
        
        if(input == max)
        {
            exit();
        }
        else
        {
            promptForPurchase(input);
        }
    }
    
    private void promptForPurchase(int input)
    {
        Item item = soldItems.get(input - 1);
        
        System.out.println("");

        MainGame.dialogueln("Berry", "Do you want the " + item.getName() + "?");
        input = MenuHelper.displayMenu("\t1) Yes\n\t2) No", 1, 2);

        switch(input)
        {
            case 1:
                askForQuantity(item);
                break;
            case 2:
                System.out.println("");
                selectItem();
                break;
        }
    }
    
    private void askForQuantity(Item item)
    {
        int maxAmt = calculateMaxQuantity(item);
                
        if(maxAmt == 0)
        {
            System.out.println("");
            
            MainGame.dialoguelnln("Berry", "Oh no! You can't afford any of those... Let's look at some other items, yeah?");
            selectItem();
        }
        else
        {
            System.out.println("");
            
            MainGame.dialoguelnln("Berry", "And how many do ya want? You can afford " + maxAmt + " of that item.");

            String message = "(Type the amount you want to purchase)";

            int quantity = MenuHelper.displayMenu(message, 1, maxAmt);

            makePurchase(item, quantity);
        }
    }
    
    private void makePurchase(Item item, int quantity)
    {
        Game.addToInventory(item, quantity);
        
        int amt = item.getPrice() * quantity;
        
        Game.decreaseGold(amt);
        
        if(quantity > 1)
        {
            MainGame.dialoguelnln("Berry", "Okay! Here are " + quantity + " " + item.getName() + "s!");
        }
        else
        {
            MainGame.dialoguelnln("Berry", "Okay! Here's 1 " + item.getName() + "!");
        }
        
        MainGame.dialoguelnln("Berry", "Your total comes to " + String.format("%,d", amt) + "!");
        
        MainGame.printlnlnWait("Current Gold: " + String.format("%,d", Game.getGold()) + " G", 25, 500);
        MainGame.promptToEnter();
        
        promptToShopAgain();
    }
    
    private int calculateMaxQuantity(Item item)
    {
        int gold = Game.getGold();
        int price = item.getPrice();
        
        return Math.floorDiv(gold, price);
    }
    
    private void promptToShopAgain()
    {
        MainGame.dialoguelnln("Berry", "Would ya like to do something else?");
        
        String message = "\t1) Buy\n\t2) Sell\n\t3) Exit";
        int input = MenuHelper.displayMenu(message, 1, 3);
        
        shopOptions(input);
    }
    
    private void exit()
    {
        System.out.println("");
        
        // If in first phase, say goodbye like this
        if(!Game.getSecondPhase())
        {
            MainGame.dialogueln("Berry", "Sounds good! Thanks for stopping by at Berry's Shop! We can't wait to see you again!");
        }
        else
        {
            MainGame.dialogueln("Berry", "A-alright! Please come back again soon...! It's good to see some nice faces.");
        }
    }
}
