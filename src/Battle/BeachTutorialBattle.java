package Battle;

import Game.MainGame;

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
    public void start(int gold)
    {
        super.start(gold);
        player.forcedLevelUp();
        player.setCurrentHealth(player.getMaxHealth());
    }
    
    @Override
    protected void intro()
    {
        MainGame.printlnlnWait("Anahita is being threatened by a Sandy Krobble!", 25, 2000);
        
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
//                MainGame.printlnln("Alright! Beat up that Krobble so you can move to Opicon Forest!", 25);
//                break;
//            case 2:
//                MainGame.printlnln("\nAlright! Beat up that Krobble so you can move to Opicon Forest!", 25);
//                break;
//        }
        
        MainGame.wait(2000);
        MainGame.clearScreen();
    }
    
    @Override
    protected void explainBattles()
    {
        interfaceTutorial();
        
        matchupTutorial();
        
        targetingTutorial();
        
        MainGame.printlnlnWait("That's a little bit of how battles work. These basics should help you out!", 25, 2000);
    }
    
    private void interfaceTutorial()
    {
        super.printEnemyInfoTable();
        
        // Explains the interface
        MainGame.printlnlnWait("\nHere is what the battle interface will look like.\n\nWhen there are more enemies and members "
                + "on your team, the slots fill accordingly:\n\t"
                + "If a team has 1 member, they will be in the middle slot.\n\t"
                + "If a team has 2 members, they will be in the left and right slots, leaving the middle empty.\n\t"
                + "And if a team has 3 members, they will fill the entire table.\n\t"
                + "(This will make more sense the more you play).", 40, 5000);
        
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
        MainGame.printlnlnWait("Next: Targeting.", 25, 1500);
        MainGame.printlnWait("\tLeft slot: The left slot can only target and be targeted by the opposing left and center slots.", 40, 2000);
        MainGame.printlnWait("\tCenter slot: The center slot can target and be targeted by all opposing slots.", 40, 2000);
        MainGame.printlnlnWait("\tRight slot: The right slot can target and be targeted by the opposing center and right slots.", 40, 2000);
        MainGame.promptToEnter();
    }
}