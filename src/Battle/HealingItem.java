package Battle;

import Game.MainGame;
import java.util.Random;

/**
 * A class for creating healing items.
 * @author Ian King
 */
public class HealingItem extends Item
{
    private final int HEALING_AMT;
    
    public HealingItem()
    {
        super.name = "Cinnamon Roll";
        super.description = "A classic food that heals 85 HP.";
        super.price = 75;
        HEALING_AMT = 85;
        salePrice = (int)Math.round(price * 0.75);
    }
    
    public HealingItem(String name, String description, int healingAmt, int price)
    {
        super.name = name;
        super.description = description;
        super.price = price;
        this.HEALING_AMT = healingAmt;
        salePrice = (int)Math.round(price * 0.75);
    }
    
    public HealingItem(String name, String description, int price)
    {
        super.name = name;
        super.description = description;
        this.HEALING_AMT = new Random().nextInt(500);
        salePrice = (int)Math.round(price * 0.75);
    }
    
    @Override
    public void use(Character character)
    {
        if(character.currentHealth + this.HEALING_AMT > character.maxHealth)
        {
            int amountToHeal = character.maxHealth - character.currentHealth;
            character.setCurrentHealth(character.currentHealth + amountToHeal);
        }
        else
        {
            character.setCurrentHealth(this.HEALING_AMT + character.currentHealth);
        }
        
        MainGame.printlnlnWait("\n" + character.getName() + " ate the " + name + " and healed " + HEALING_AMT + " HP!", 5, 1500);
        
        decreaseQuantity();
        
        remove();
    }
    
    @Override
    public String toInventoryString()
    {
        return super.toInventoryString() + "\n\tHealing Amount: " + HEALING_AMT + " HP";
    }
}
