package Battle;

import Game.MainGame;
import java.util.ArrayList;

/**
 * An abstract class for Items that can be collected.
 * @author Ian King
 */
public abstract class Item implements Collectable
{
    private static ArrayList<HealingItem> allHealItems = new ArrayList();
    private static ArrayList<BuffItem> allBuffItems = new ArrayList();
    private static ArrayList<Item> allItems = new ArrayList();
    protected String name;
    protected String description;
    protected int quantity;
    protected int price;
    protected int salePrice;
    
    
    
    
    
    
    /*
    For buff items, they will permanently increase a Stat for the battle, but in turn increase it by a smaller amount
    */
    
    
    /*
    Look into deep copying the actual Item objects so that clones of them can be made but with the same information
    */
    
    
    
    
    
    @Override
    public String getName() {return name;}
    
    @Override
    public void setName(String newName) {name = newName;}
    
    @Override
    public String getDescription() {return description;}
    
    @Override
    public void setDescription(String newDesc) {this.description = newDesc;}
    
    public int getQuantity() {return quantity;}
    
    public void setQuantity(int amount)
    {
        quantity = amount;
    }
    
    // The next 4 methods are incorrect. They need to increase the quantity of the item in the inventory (if already there)
    
    public void increaseQuantity(int amount) {quantity += amount;}
    
    public void decreaseQuantity(int amount) {quantity -= amount;}
     
    public void increaseQuantity() {++quantity;}
    
    public void decreaseQuantity() {--quantity;}
    
    public int getPrice() {return price;}
    
    public void setPrice(int newPrice) {price = newPrice;}
    
    public String getSalePriceString() {return String.format("%,d", salePrice) + " G";}
    public int getSalePrice() {return salePrice;}
    
    public abstract void use(Character character);
    
    public void printDescription() {MainGame.printlnln(this.description, 5);}
    
    /**
     * Shows the player what item they received and how much.
     */
    public void printReceivedMessage() 
    {
        if(quantity > 1)
        {
            MainGame.printlnln("You received " + quantity + " " + name + "s!", 10);
        }
        else
        {
            MainGame.printlnln("You received " + quantity + " " + name + "!", 10);
        }
    }
    
    /**
     * Populates all healing items into a static ArrayList.
     */
    public static void populateAllHealItems()
    {   
        HealingItem cinnamonRoll = new HealingItem();
        HealingItem bigCinnamonRoll = new HealingItem("Big Cinnamon Roll", "A large cinnamon roll with extra sugar!", 125, 250);
        HealingItem megaRoll = new HealingItem("Mega Roll", "A cinnamon roll as big as Gaea's head! It's the largest cinnamon roll you can find.", 300, 430);
        HealingItem cakeSlice = new HealingItem("Cake Slice", "A small slice of cake that can make the saddest people smile.", 150, 275);
        HealingItem halfCake = new HealingItem("Half Cake", "Half of a cake. Where did the other half go? Who knows!", 350, 490);
        HealingItem wholeCake = new HealingItem("Whole Cake", "An entire, untampered cake that looks pretty good! It looks very filling.", 500, 700);
        HealingItem applePie = new HealingItem("Apple Pie", "An old town favorite dessert filled with cinnamon, apples, and lots of love.", 250, 360);
        HealingItem peachCobbler = new HealingItem("Peach Cobbler", "A soft, sweet dessert that makes any heart warm.", 225, 335);
        HealingItem chocoBar = new HealingItem("Choco Bar", "A bar made of high quality chocolate.", 200, 325);
        HealingItem unknown = new HealingItem("???", "Is... Is this even edible? Only one way to find out...");
        HealingItem lollipop = new HealingItem("Lollipop", "A small, delectable treat that all the children love.", 60, 45);
        HealingItem cheesecake = new HealingItem("Cheesecake", "It's cheesecake! Who doesn't like cheesecake?", 165, 285);
        HealingItem tcm = new HealingItem("Triple Chocolate Meltdown", "A chocolate-lover's dream: chocolate cake with molten chocolate inside!", 800, 2920);
        HealingItem snowbake = new HealingItem("Snowbake", "A nice, ice treat that's very popular everywhere in Pultra.", 605, 1025);

        ArrayList<HealingItem> items = new ArrayList<>();

        items.add(cinnamonRoll);
        items.add(bigCinnamonRoll);
        items.add(megaRoll);
        items.add(cakeSlice);
        items.add(halfCake);
        items.add(wholeCake);
        items.add(applePie);
        items.add(peachCobbler);
        items.add(chocoBar);
        items.add(unknown);
        items.add(lollipop);
        items.add(cheesecake);
        items.add(tcm);
        items.add(snowbake);

        allHealItems = items;

        for(Item i : allHealItems)
        {
            allItems.add(i);
        }
        
    }
    
    /**
     * Populates all buff items into a static ArrayList.
     */
    public static void populateAllBuffItems()
    {   
        BuffItem redBean = new BuffItem();
        BuffItem orangeBean = new BuffItem("Orange Bean", "A magical orange bean that boosts the defense stat.", "Defense");
        BuffItem purpleBean = new BuffItem("Purple Bean", "A magical purple bean that boosts the ranged attack stat.", "R. Attack");
        BuffItem greenBean = new BuffItem("Green Bean", "A magical green bean that boosts the ranged defense stat.", "R. Defense");
        BuffItem blueBean = new BuffItem("Blue Bean", "A magical blue bean that boosts the speed stat.", "Speed");

        ArrayList<BuffItem> items = new ArrayList<>();

        items.add(redBean);
        items.add(orangeBean);
        items.add(purpleBean);
        items.add(greenBean);
        items.add(blueBean);

        allBuffItems = items;

        for(Item i : allBuffItems)
        {
            allItems.add(i);
        }
        
    }
    
    public static ArrayList<Item> getAllItems() {return allItems;}
    public static ArrayList<HealingItem> getAllHealItems() {return allHealItems;}
    public static ArrayList<BuffItem> getBuffAllItems() {return allBuffItems;}
    
    public static void refreshAllItems()
    {
        allItems = new ArrayList<>();
        
        for(Item i : allBuffItems)
        {
            allItems.add(i);
        }
        
        for(Item i: allHealItems)
        {
            allItems.add(i);
        }
    }
    
    /**
     * Returns a preexisting Healing Item by entering its name.
     * @param nameOfItem
     * @return null or a healing item
     */
    public static HealingItem getHealingItem(String nameOfItem)
    {
        HealingItem result = null;
        nameOfItem = nameOfItem.toLowerCase();
        String name;
        
        for(HealingItem item : allHealItems)
        {
            name = item.getName().toLowerCase();
            
            if(nameOfItem.equals(name))
            {
                result = item;
                break;
            }
        }
        
        return result;
    }
    
    /**
     * Returns a deep copied list of all Item objects.
     * @return ArrayList
     */
    public static ArrayList<Item> allItemsDeepCopy()
    {
        ArrayList<Item> items = new ArrayList<>();
        
        for(Item i : allItems)
        {
            items.add(i);
        }
        
        return items;
    }
    
    /**
     * Returns a preexisting Buff Item by entering its name.
     * @param wantedItem
     * @return null or a buff item
     */
    public static BuffItem getBuffItem(String wantedItem)
    {
        BuffItem result = null;
        wantedItem = wantedItem.toLowerCase();
        String name;
        
        for(BuffItem item : allBuffItems)
        {
            name = item.getName().toLowerCase();
            
            if(wantedItem.equals(name))
            {
                result = item;
                break;
            }
        }
        
        return result;
    }
    
    public void remove()
    {
        if(quantity == 0)
        {
            MainGame.getInventory().remove(this);
        }
    }
    
    public String toInventoryString()
    {
        return name + " - x" + quantity + "\n\t" + description;
    }
    
    
    @Override
    public String toString()
    {
        return this.name;
    }
}
