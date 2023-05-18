package Exploration;

import Battle.GameProperty;
import Battle.Item;
import Game.Cutscene;
import Game.MainGame;
import java.util.Random;

/**
 * A class for NPCs the player can interact with.
 * @author Ian King
 */
public class NPC extends GameProperty
{
    private Item gift;
    private String dialogue;
    private String giveGiftMessage;
    private boolean talkedTo;
    private boolean hasCutscene;
    
    public NPC(String name, String dialogue, boolean hasCutscene)
    {
        super.name = name;
        this.dialogue = dialogue;
        this.hasCutscene = hasCutscene;
    }
    
    public NPC(String name, String dialogue, Item gift, boolean hasCutscene)
    {
        super.name = name;
        this.dialogue = dialogue;
        this.gift = gift;
        gift.setQuantity(1);
        this.hasCutscene = hasCutscene;
    }
    
    public NPC(String name, String dialogue, Item gift, int itemQuantity, boolean hasCutscene)
    {
        super.name = name;
        this.dialogue = dialogue;
        this.gift = gift;
        gift.setQuantity(itemQuantity);
        this.hasCutscene = hasCutscene;
    }
    
    public boolean getHasCutscene() {return hasCutscene;}
    public void setHasCutscene(boolean value) {hasCutscene = value;}
    
    public void setGiveGiftMessage(String message) {giveGiftMessage = message;}
    
    public String getDialogue() {return dialogue;}
    public void setDialogue(String message) {dialogue = message;}
    
    public boolean hasBeenTalkedTo() {return talkedTo;}
    public void setTalkedTo(boolean value) {talkedTo = value;}
    
    /**
     * After dialogue is complete, NPC gives player its item as a gift.
     */
    public void giveGift()
    {
        
        MainGame.dialoguelnln(name, giveGiftMessage());
        gift.printReceivedMessage();
        MainGame.addToInventory(gift, gift.getQuantity());
        MainGame.dialoguelnln(name, "I hope it helps you on your journey!");
        gift = null;
    }
    
    /**
     * After dialogue is complete, NPC gives player its item as a gift with specified message.
     * @param message
     */
    public void giveGift(String message)
    {
        
        MainGame.dialoguelnln(name, message);
        gift.printReceivedMessage();
        MainGame.addToInventory(gift, gift.getQuantity());
        MainGame.dialoguelnln(name, "I hope it helps you on your journey!");
        gift = null;
    }
    
    /**
     * Prints dialogue to the player; if the NPC has an Item, special dialogue is printed; if not, another piece of dialogue
     * is printed.
     */
    public void talk()
    {
        /*
        If an NPC has a specific cutscene, it'll play here and count as the player talking to them for the first time
        */
        if(hasCutscene)
        {
            MainGame.clearScreen();
            Cutscene.startNPCCutscene(this);
            hasCutscene = false;
            talkedTo = true;
            return;
        }
        
        talkedTo = true;
        
        if(gift == null)
        {
            MainGame.dialoguelnln(name, dialogue);
        }
        else if(giveGiftMessage == null)
        {
            MainGame.dialoguelnln(name, dialogue);
            giveGift();
        }
        else
        {
            MainGame.dialoguelnln(name, dialogue);
            giveGift(giveGiftMessage);
        }
        
        MainGame.promptToEnter();
    }
    
    private String giveGiftMessage()
    {
        String message = "";
        int chance = new Random().nextInt(5);
        
        switch(chance)
        {
            case 0: 
                message = "I actually have something for you. Take this.";
                break;
            case 1:
                message = "Here -- you should take this!";
                break;
            case 2:
                message = "Please, take this. You don't have to repay me.";
                break;
            case 3: 
                message = "I have a gift for you. Let's see... Here it is!";
                break;
            case 4:
                message = "Thank you for all you've done for us. Take this.";
        }
        
        return message;
    }
    
    @Override
    public String toString()
    {
        return "Name: " + this.name + "\tGift Item:" + this.gift.toString();
    }
}
