package Battle;

import Game.MainGame;
import Utilites.MenuHelper;

/**
 * A class for taking the player through the first tutorial.
 * @author ianth
 */
public class BeachTutorialBattle extends TutorialBattle
{
    public BeachTutorialBattle(Enemy enemy, Player player)
    {
        super(enemy, player);
    }
    
    @Override
    public void start(int gold, boolean result)
    {
        super.start(gold, result);

        if(won)
        {
            player.forcedLevelUp();
        }
       
        player.setCurrentHealth(player.getMaxHealth());
    }
    
    @Override
    protected void intro()
    {
        MainGame.printlnln("Anahita is being threatened by a Sandy Krobble!");
        
        String message = "Would you like the tutorial of how battles work?\n\t1) Yes\n\t2) No";
        
        super.promptForTutorial(message);
//        int response = MenuHelper.displayMenu("Would you like the tutorial of how battles work?\n\t1) Yes\n\t2) No", 1, 2);
//        
//        switch(response)
//        {
//            case 1:
//                MainGame.clearScreen();
//                MainGame.printlnlnWait("Here's how battles work:", 25, 2000);
//                explainBattles();
//                MainGame.printlnln("Alright! Beat up that Krobble so you can move to Opicon Forest!");
//                break;
//            case 2:
//                MainGame.printlnln("\nAlright! Beat up that Krobble so you can move to Opicon Forest!");
//                break;
//        }
        
//        MainGame.wait(2000);
//        MainGame.clearScreen();
    }
    
    @Override
    protected void explainBattles()
    {
        String message = "Select the tutorial you want to view:"
                + "\n\t1) Battle Interface\n\t2) Matchup Chart\n\t3) Targeting\n\t4) Done";
        int input = MenuHelper.displayMenu(message, 1, 4);
        
        // If the input isn't the player wanting to quit, recursively call the method until it is.
        switch(input)
        {
            case 1:
                interfaceTutorial();
                explainBattles();
                break;
            case 2:
                matchupTutorial();
                explainBattles();
                break;
            case 3:
                targetingTutorial();
                explainBattles();
                break;
            default:
                MainGame.clearScreen();
                break;
        }
        
        MainGame.printlnlnWait("That's a little bit of how battles work. These basics should help you out!", 25, 2000);
    }
    
    private void interfaceTutorial()
    {
        super.printEnemyInfoTable();
        
        // Explains the interface
        MainGame.printlnln("\nHere is what the battle interface will look like.\n\nWhen there are more enemies and members "
                + "on your team, the slots fill accordingly:\n\t"
                + "If a team has 1 member, they will be in the middle slot.\n\t"
                + "If a team has 2 members, they will be in the left and right slots, leaving the middle empty.\n\t"
                + "And if a team has 3 members, they will fill the entire table.\n\t"
                + "(This will make more sense the more you play).");
        
        MainGame.promptToEnter();
    }
    
    private void matchupTutorial()
    {
        // Explains matchups
        MainGame.printlnlnWait("Next: Element matchups.", 25, 1500);
        MainGame.printElementMatchups();
        
        MainGame.clearScreen();
    }
    
    private void targetingTutorial()
    {
        // Explains targeting with the board
        MainGame.printlnlnWait("Next: Targeting.", 25, 1000);
        MainGame.println("\tLeft slot: The left slot can only target and be targeted by the opposing left and center slots.");
        MainGame.println("\tCenter slot: The center slot can target and be targeted by all opposing slots.");
        MainGame.printlnln("\tRight slot: The right slot can target and be targeted by the opposing center and right slots.");
        MainGame.promptToEnter();
    }
}
