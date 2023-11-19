package Battle;

import Game.MainGame;

/**
 * A class for creating items that grant buffs.
 * @author Ian King
 */
public class BuffItem extends Item
{
    private final String STAT_TO_BUFF;
    private final double BUFF_MODIFIER;
    
    public BuffItem()
    {
        super.name = "Red Bean";
        super.description = "A magical red bean that boosts the attack stat.";
        super.price = 1750;
        STAT_TO_BUFF = "Attack";
        BUFF_MODIFIER = 1.25;
        salePrice = (int)Math.round(price * 0.75);
    }
    
    public BuffItem(String name, String description, String statToBoost)
    {
        super.name = name;
        super.description = description;
        super.price = 1750;
        this.STAT_TO_BUFF = statToBoost;
        this.BUFF_MODIFIER = 1.25;
        salePrice = (int)Math.round(price * 0.75);
    }
    
    @Override
    public void use(Character character)
    {
        boolean activatedBuff = false;
        
        switch (this.STAT_TO_BUFF) 
        {
            case "Attack":
                activatedBuff = character.getAttack().activateStatBuff(BUFF_MODIFIER);
                break;
            case "Defense":
                activatedBuff = character.getDefense().activateStatBuff(BUFF_MODIFIER);
                break;
            case "R. Attack":
                activatedBuff = character.getRangedAttack().activateStatBuff(BUFF_MODIFIER);
                break;
            case "R. Defense":
                activatedBuff = character.getRangedDefense().activateStatBuff(BUFF_MODIFIER);
                break;
            case "Speed":
                activatedBuff = character.getSpeed().activateStatBuff(BUFF_MODIFIER);
                break;
            default:
                break;
        }
        
        if(activatedBuff)
        {
            MainGame.printlnlnWait("\n" + character.getName() + " ate the " + name + " and increased their " + 
                STAT_TO_BUFF + "!", 5, 2000);
            
            decreaseQuantity();
            remove();
        }
    }
    
    @Override
    public String toInventoryString()
    {
        return super.toInventoryString() + "\n\tStat Affected: " + STAT_TO_BUFF;
    }
}
