package Game;

import Battle.Attack;
import Battle.BossEnemy;
import Battle.BuffAttack;
import Battle.ComboAttack;
import Battle.DebuffAttack;
import Battle.Enemy;
import Battle.Item;
import Battle.OffensiveAttack;
import Battle.Player;
import Battle.PlayerClass;
import Battle.RESIEnemy;
import Battle.SingleHealingAttack;
import Battle.TeamHealingAttack;
import Data.SaveLoad;
import Exploration.Wilderness;
import Utilites.MenuHelper;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A main class for running the game.
 * @author Ian King
 */
public class MainGame 
{
    private static int textSpeed = 0;
    private static boolean finalBossDefeated;
    private static ArrayList<String> startUpOptions = new ArrayList<>();
    private static ArrayList<Player> playerTeam = new ArrayList<>(6);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    private static Game game;
    private static SaveLoad saveLoad = new SaveLoad();
    
    public static void main(String[] args) 
    {        
        Enemy.populateAllAttacks();
        RESIEnemy.populateAllAttacks();
        ComboAttack.populateComboAttackLibrary();
        Item.populateAllBuffItems();
        Item.populateAllHealItems();
        PlayerClass.createClasses();
        startUpOptions.add("New Game");
        startUpOptions.add("Continue Game");
        startUpOptions.add("Set Text Speed");

        startUp();
    }
    
    public static void startUp()
    {
        clearScreen();
        
        String gameTitle = "Project Perfection";
        
        MainGame.printWithRandomLetters(gameTitle);
        
        for(int i = 0; i < startUpOptions.size(); i++)
        {
            System.out.println("");
            System.out.print("\t" + (i + 1) + ") ");
            MainGame.printWithRandomLetters(startUpOptions.get(i));
        }
        
        int response = MenuHelper.displayMenu("", 1, startUpOptions.size() + 1);
        
        System.out.println("");
        
        clearScreen();
        
        switch(response)
        {
            case 1:
                game = new Game(false);
                break;
            case 2:
                load();
                break;
            case 3:
                game = new Game(false);
                selectTextSpeed(game);
                break;
        }
        
        game.startGame();
    }
    
    public static void selectTextSpeed(Game game)
    {
        MainGame.clearScreen();
        promptToChangeTextSpeed();
        textSpeedExample();
        finalTextSpeedPrompt(game);
    }
    
    /**
     * Asks the player what they want to set the text speed to.
     */
    private static void promptToChangeTextSpeed()
    {
        String message = String.format("What would you like the text speed to be? It is currently set to %d.\n\n"
                + "0 is instant.\n"
                + "1 is the fastest.\n"
                + "25 is average.\n" 
                + "Anything greater than 40 is slow.\n\n"
                + "Please enter a number between 0-100", textSpeed);
        
        int input = MenuHelper.displayMenu(message, 0, 100);
        
        textSpeed = input;
        
        promptToEnter();
    }
    
    /**
     * Shows an example of how fast the text will be typed.
     */
    private static void textSpeedExample()
    {
        printlnln(String.format("You selected %d to be the text speed.", textSpeed));
        
        printlnln("The current speed is how the game will be displayed.");
        
        promptToEnter();
    }
    
    /**
     * Asks the player if the current speed is fine or if they need the example again.
     */
    private static void finalTextSpeedPrompt(Game game)
    {
        String message = String.format("Is a speed of %d what you want?\n\t1) Yes\n\t2) No\n\t3) Show example", textSpeed);
        
        int input = MenuHelper.displayMenu(message, 1, 3);
        
        switch(input)
        {
            case 1:
                clearScreen();
                printlnln("The speed can be changed by selecting 'More Options > Set Text Speed' while playing the game.");

                if(!game.getGameStarted()){
                    promptToEnter();
                    startUp();
                }
                
                break;
            case 2: 
                promptToEnter();
                selectTextSpeed(game);
                break;
            case 3: 
                promptToEnter();
                textSpeedExample();
                finalTextSpeedPrompt(game);
                break;
        }
    }
    
    public static void save()
    {
        clearScreen();
        saveLoad.save(game);
    }
    
    public static void load()
    {
        try
        {
            game = saveLoad.load();
        }
        catch(Exception e)
        {
            printlnln("There is no game data saved currently. Starting a new game instead.");
            promptToEnter();
            game = new Game(false);
        }

        
        game.startGame();
    }
        
    /**
     * Prints an ellipsis without adding new lines.
     */
    public static void ellipsis()
    {
        printWait("... ", 500, 1000);
    }
    
    /**
     * Prints an ellipsis with a new line.
     */
    public static void ellipsisln()
    {
        printlnWait("...", 500, 1000);
    }
    
    /**
     * Prints an ellipsis with 2 new lines.
     */
    public static void ellipsislnln()
    {
        printlnlnWait("...", 500, 1000);
    }
    
    public static void println(String string)
    {
        for(int i = 0; i < string.length(); i++)
        {
            System.out.print(string.charAt(i));
            
            if(string.charAt(i) == ' ') {continue;}

            try
            {
                Thread.sleep(textSpeed);
            }
            catch(InterruptedException ie)
            {
                
            }
        }
        
        System.out.println("");
    }
    
    public static void printlnln(String string)
    {
        for(int i = 0; i < string.length(); i++)
        {
            System.out.print(string.charAt(i));
            
            if(string.charAt(i) == ' ') {continue;}

            try
            {
                Thread.sleep(textSpeed);
            }
            catch(InterruptedException ie)
            {
                
            }
        }
        
        System.out.println("\n");
    }
    
    /**
     * Calls printlnln and waits for the corresponding wait time.
     * @param string
     * @param time
     * @param waitTime 
     */
    public static void printlnlnWait(String string, int time, int waitTime)
    {
        printlnln(string);
        wait(waitTime);
    }
    
    /**
     * Calls println and waits for the corresponding wait time.
     * @param string
     * @param time
     * @param waitTime 
     */
    public static void printlnWait(String string, int time, int waitTime)
    {
        println(string);
        wait(waitTime);
    }
    
    /**
     * Calls print and waits for the corresponding wait time.
     * @param string
     * @param time
     * @param waitTime 
     */
    public static void printWait(String string, int time, int waitTime)
    {
        print(string);
        wait(waitTime);
    }
    
    public static void print(String string)
    {
        for(int i = 0; i < string.length(); i++)
        {
            System.out.print(string.charAt(i));
            
            if(string.charAt(i) == ' ') {continue;}

            try
            {
                Thread.sleep(textSpeed);
            }
            catch(InterruptedException ie)
            {
                
            }
        }
        
    }
    
    public static void print(char c)
    {
        System.out.print(c);
        
        try
        {
            Thread.sleep(textSpeed);
        }
        catch(InterruptedException ie)
        {

        }
    }
    
    /**
     * Takes a String with sentences divided by / to add new line characters.
     * @param cutscene 
     */
    public static void printWithBreaks(String cutscene)
    {
        Scanner scan = new Scanner(cutscene);
        scan.useDelimiter("/");
        
        while(scan.hasNext())
        {
            println(scan.next());
        }
        
        System.out.println("");
    }
    
    public static void printWithRandomLetters(String string)
    {
        for(int i = 0; i < string.length(); i++)
        {
            char letter = string.charAt(i);

            if(Character.isUpperCase(letter))
            {
                for(int j = 0; j < 3; j++)
                {
                    int randNum = new Random().nextInt(90 - 65 + 1) + 65;
                    char c = (char)(randNum);
                    System.out.print(c);
                    wait(10);
                    System.out.print("\b");
                }
                System.out.print(letter);
            }
            else
            {
                for(int j = 0; j < 3; j++)
                {
                    int randNum = new Random().nextInt(122 - 97 + 1) + 97;
                    char c = (char)(randNum);
                    System.out.print(c);
                    wait(10);
                    System.out.print("\b");
                }
                System.out.print(letter);
            }
        }
    }
    
    public static void dialoguelnln(Player player, String dialogue)
    {
        printlnln("\t" + player.getName() + ": \"" + dialogue  + "\"");
    }
    
    public static void dialoguelnln(String name, String dialogue)
    {
        printlnln("\t" + name + ": \"" + dialogue + "\"");
    }
    
    public static void dialogueln(Player player, String dialogue)
    {
        println("\t" + player.getName() + ": \"" + dialogue + "\"");
    }
    
    public static void dialogueln(String name, String dialogue)
    {
        println("\t" + name + ": \"" + dialogue + "\"");
    }
    
    public static void dialogueInteract(Player player, String dialogue)
    {
        print("\t" + player.getName() + ": \"" + dialogue + "\"");
        waitForEnter();
    }
    
    public static void dialogueInteract(String name, String dialogue)
    {
        printlnln("\t" + name + ": \"" + dialogue + "\"");
        promptToEnter();
    }
    
    public static void waitForEnter()
    {
        String prompt = "\n\tPress Enter to Continue >";
        print(prompt);
        
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
    
    public static void wait(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(InterruptedException ie)
        {
            
        }
    }
    
    public static boolean getFinalBossDefeated() {return finalBossDefeated;}
    
    public static void clearScreen()
    {
        try
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch(Exception e)
        {
            
        }
    }
    
    /**
     * Waits for the user to press the "enter" key and then clears the screen.
     */
    public static void promptToEnter()
    {
        MainGame.waitForEnter();
        MainGame.clearScreen();
    }
    
    public static void printElementMatchups()
    {
        clearScreen();
        new TypeChart().printChart();
        promptToEnter();
    }
    
    public static ArrayList<Player> getPlayerTeam() {return playerTeam;}
    public static void setPlayerTeam(ArrayList<Player> team) {playerTeam = team;}
    
    public static void targetingTutorial()
    {
        clearScreen();
        // Explains targeting with the board
        printlnln("Targeting:");
        printlnln("\tLeft slot: The left slot can only target and be targeted by the opposing left and center "
                + "slots.\n\tCenter slot: The center slot can target and be targeted by all opposing slots.\n\t"
                + "Right slot: The right slot can target adn be targeted by the opposing center and right slots.");
    }
    
    public static void aggroTutorial()
    {
        clearScreen();
        
        // Explains the aggro system
        printWithBreaks("Each of your characters has a value called "
                + "\"aggro\"./After every attack, their aggro will increase, forcing the enemy to target them./The character "
                + "with the most aggro will have a marker next to their name: (!)./"
                + "Certain moves accrue more aggro than others, and some classes affect this as well./");
    }
    
    public static void cheerPartnerTutorial()
    {
        clearScreen();
        
        // Explains cheer partner mechanic
        printWithBreaks("When you have more than 3 team members, you can assign a Cheer Partner to another character./"
                + "A Cheer Partner will be there to support the person fighting by using their Cheer Skill./"
                + "A Cheer Skill depends on the Cheer Partner's primary Class type.//\tStriker: Boosts Attack and Ranged Attack "
                + "by 10 points./\tTank: Boosts Defense and Ranged Defense by 10 points.\n\tClerk: Heals the fighter "
                + "slightly if they aren't at full HP./\tAll-Rounder: Boosts Speed by 10 points.");
        
        printlnln("A Cheer Skill has a chance of activating after every turn a character finishes. It may come in handy!");
    }
    
    public static void cooldownTutorial()
    {
        clearScreen();
        
        printWithBreaks("The more you play, you'll become more acquainted with your types of attacks: Buff, Debuff, Offensive, Single Heal, and Team Heal./"
                + "All of these attacks except Offensive have \"Cooldowns.\"/If you view your character's attacks, you can see the cooldown value./"
                + "This value means that after using this attack, it will be unavailable for that amount of turns./After the cooldown effect is over, the move is available again.");
    }
    
    public static void classTutorial()
    {
        clearScreen();
        
        // Part 1
        printWithBreaks("Each character has a \"Class.\" There are 10 unique classes in total:/Clerk classes:\n\tMaster Clerk: Focuses on healing.\n\tHyper Clerk: Focuses on healing and damage.\n\tPassive Clerk: Focuses on healing and takes hits better than the other Clerks./"
                + "Striker classes:\n\tMaster Striker: Focuses on physical and ranged damage.\n\tTranquil Striker: Focuses on physical damage and slight healing.\n\tGuardian Striker: Focuses on damage and slightly on aggro./"
                + "Tank classes:\n\tMaster Tank: Focuses on taking physical attacks and gaining aggro.\n\tHoly Tank: Focuses on taking hits in general with minor healing.\n\tWild Tank: Focuses on taking hits while dealing damage./"
                + "Special class:\n\tAll-Rounder: Relatively balanced with everything.");
        
        promptToEnter();
        
        // Part 2
        printWithBreaks("All classes except All-Rounder and the Master classes are made of two class roles./"
                + "For example, the Passive Clerk has the primary role of a Clerk class with the secondary role being the Tank.");
        
        promptToEnter();
        
        // Part 3
        printWithBreaks("Classes affect how your stats change when a level up occurs./Each class focuses on certain stats, and when a character levels up, their class determines the odds of a stat "
                + "increasing./For example, if Anahita levels up, you may not see her health increase, but it might increase if she levels up again.");
        
        promptToEnter();
        
        // Part 5
        printWithBreaks("Lastly, the Tank and Master Clerk classes have special properties in battle./Tank classes (primary or secondary) accrue more aggro than other classes normally would./"
                + "The Master Clerk class has a special property where they can heal anyone no matter the adjacency./Normally, a Clerk can only heal an adjacent ally. However, a Master Clerk ignores this.");
    }
    
    public static void resiTutorial()
    {
        clearScreen();
        printWithBreaks("These robots, known as R.E.S.I Bots, are very durable and are highly adaptable to their environments./"
                + "Every time a R.E.S.I is attacked, whatever element it was attacked by is what its element will become.");
        
        printWithBreaks("For example, say the R.E.S.I starts off with the Water element. If it's attacked by an Earth element,/it "
                + "will then have the Earth element.");
        
        printWithBreaks("If a R.E.S.I is hit with a Combo attack, the element will change to the character who is fighting's/"
                + "element, not their cheer partner.");
    }
    
    public static void comboAttackTutorial()
    {
        clearScreen();
        
        printWithBreaks("Combo Attacks are very strong in battle! When someone fighting has a cheer partner and their elements "
                + "are compatable,/the option of using a Combo attack is unlocked.");
        
        printWithBreaks("Combo Attacks work by taking both elements of both the fighter and their cheer partner. The "
                + "effectiveness of both will/be applied and deal massive damage! This can only be done once per battle, however, "
                + "so use it wisely!");
    }
    
    public static Player makeAnahita()
    {
        PlayerClass masterClerk = PlayerClass.getPlayerClass("Master Clerk");
        
        // Instantiating Anahita and her moveset--------------------------
        Player anahita = new Player("Anahita", "A master of water and a kind, compassionate soul.", "Water", masterClerk, 5);
        anahita.setDeathMessage("Everyone... I'm sorry...");
        anahita.setCheerReadyMessage("I'm right behind you!");
        anahita.setBattleReadyMessage("I have faith we'll succeed!");
        anahita.setMaxHealth(250);
        anahita.setCurrentHealth(250);
        anahita.setAttack(55);
        anahita.setDefense(45);
        anahita.setRangedAttack(60);
        anahita.setRangedDefense(90);
        anahita.setSpeed(50);
        
        // make this a method in the character class as a for loop
        anahita.getAttack().setOriginalValue(anahita.getAttack().getValue());
        anahita.getDefense().setOriginalValue(anahita.getDefense().getValue());
        anahita.getRangedAttack().setOriginalValue(anahita.getRangedAttack().getValue());
        anahita.getRangedDefense().setOriginalValue(anahita.getRangedDefense().getValue());
        anahita.getSpeed().setOriginalValue(anahita.getSpeed().getValue());
        
        // All of Anahita's attacks
        TeamHealingAttack blessedRain = new TeamHealingAttack("Blessed Rain", "The user heals each party member 20% of their total health.", 0.35, 2);
        OffensiveAttack tsnunamiShot = new OffensiveAttack("Tsunami Shot", "The user shoots the target with pressurized water.", 70, "R. Attack");
        tsnunamiShot.setCritRate(0.35);
        SingleHealingAttack waterHalo = new SingleHealingAttack("Water Halo", "A ring of water surrounds the target and heals 30% of their total health.", 0.3, 3);
        OffensiveAttack torrent = new OffensiveAttack("Torrent", "The user blasts the target with a torrent of water.", 95, "R. Attack");
        torrent.setAccuracy(90);
        BuffAttack liquidArmor = new BuffAttack("Liquid Armor", "The user surrounds themself with a armor made of water, raises their Defense for 3 turns.", "Defense", 4, 3);
        DebuffAttack sparklingMist = new DebuffAttack("Sparkling Mist", "The user sprays a light mist that sparkles and distracts the target to slightly lower their defenses for 3 turns.", "Defense,R. Defense", 0.67, 4, 3);
        OffensiveAttack tidalWave = new OffensiveAttack("Tidal Wave", "The user surrounds themselves in water and crashes into the target with the force of a tidal wave.", 95, "Attack");
        tidalWave.setAccuracy(95);
        DebuffAttack soak = new DebuffAttack("Soak", "The user soaks the target in so much water to weigh them down that their Speed decreases.", "Speed", 0.67, 3, 2);
        
        ArrayList<Attack> anahitaCurrentAttacks = new ArrayList<>(4);
        anahitaCurrentAttacks.add(blessedRain);
        anahitaCurrentAttacks.add(tsnunamiShot);
        anahitaCurrentAttacks.add(sparklingMist);
        anahitaCurrentAttacks.add(waterHalo);
        
        ComboAttack.getComboAttacks(anahita);
        
        ArrayList<Attack> anahitaOtherAttacks = new ArrayList<>(4);
        anahitaOtherAttacks.add(torrent);
        anahitaOtherAttacks.add(liquidArmor);
        anahitaOtherAttacks.add(tidalWave);
        anahitaOtherAttacks.add(soak);
        
        anahita.setCurrentAttacks(anahitaCurrentAttacks);
        anahita.setListOfOtherAttacks(anahitaOtherAttacks);
        
        if(Game.isTesting())
        {
            anahita.setMaxHealth(9999);
            anahita.setCurrentHealth(9999);
            anahita.setAttack(9999);
            anahita.setDefense(9999);
            anahita.setRangedAttack(9999);
            anahita.setRangedDefense(9999);
            anahita.setSpeed(9999);
            anahita.getAttack().setOriginalValue(anahita.getAttack().getValue());
            anahita.getDefense().setOriginalValue(anahita.getDefense().getValue());
            anahita.getRangedAttack().setOriginalValue(anahita.getRangedAttack().getValue());
            anahita.getRangedDefense().setOriginalValue(anahita.getRangedDefense().getValue());
            anahita.getSpeed().setOriginalValue(anahita.getSpeed().getValue());
        }

        ArrayList<PlayerClass> anahitaOtherClasses = new ArrayList<>(3);
        anahitaOtherClasses.add(PlayerClass.getPlayerClass("Holy Tank"));
        anahitaOtherClasses.add(PlayerClass.getPlayerClass("Master Striker"));
        anahitaOtherClasses.add(PlayerClass.getPlayerClass("Tranquil Striker"));
        
        anahita.setOtherClasses(anahitaOtherClasses);
        
        return anahita;
    }
    
    public static Player makeGaea()
    {
        PlayerClass passiveClerk = PlayerClass.getPlayerClass("Passive Clerk");
        
        // Instantiating Gaea and her moveset--------------------------
        Player gaea = new Player("Gaea", "A short and fiesty master of Earth.", "Earth", passiveClerk, 6);
        gaea.setDeathMessage("Ana, Fultra, everyone... forgive me...");
        gaea.setBattleReadyMessage("I'll help, but don't mess up my hair!");
        gaea.setCheerReadyMessage("I'm right here to support you!");
        gaea.setMaxHealth(280);
        gaea.setCurrentHealth(280);
        gaea.setAttack(65);
        gaea.setDefense(80);
        gaea.setRangedAttack(80);
        gaea.setRangedDefense(75);
        gaea.setSpeed(60);
        
        gaea.getAttack().setOriginalValue(gaea.getAttack().getValue());
        gaea.getDefense().setOriginalValue(gaea.getDefense().getValue());
        gaea.getRangedAttack().setOriginalValue(gaea.getRangedAttack().getValue());
        gaea.getRangedDefense().setOriginalValue(gaea.getRangedDefense().getValue());
        gaea.getSpeed().setOriginalValue(gaea.getSpeed().getValue());
        
        SingleHealingAttack floralHealing = new SingleHealingAttack("Floral Healing", "The user uses blessed plants to heal the target one third of their total health.", 0.30);
        DebuffAttack overgrowth = new DebuffAttack("Overgrowth", "The user grows giant, thick vines to trip the target, lowering their speed for 3 turns.", "Speed", 4, 3);
        DebuffAttack foulAroma = new DebuffAttack("Foul Aroma", "Using foul-smelling flowers, the user slightly lowers the target's attack and defense for 3 turns.", "Attack,Defense", 0.67, 4, 3);
        BuffAttack stoneShield = new BuffAttack("Stone Shield", "The user creates a shield made of stone to increase their ranged defense for 3 turns.", "R. Defense", 4, 3);
        OffensiveAttack terraForce = new OffensiveAttack("Terra Force", "The user creates a massive boulder and launches it at the target.", 95, "Attack");
        terraForce.setAccuracy(95);
        OffensiveAttack wrathOfGaea = new OffensiveAttack("Wrath of Gaea", "The user overwhelms and damages the target with sharp leaves.", 80, "R. Attack");
        SingleHealingAttack earthsLove = new SingleHealingAttack("Earth's Love", "The user heals the target by uses special soils that soothe any injury.", 0.5, 3);
        OffensiveAttack obsidianStorm = new OffensiveAttack("Obsidian Storm", "The user attacks by surrounding the target with a plethera of obsidian shards.", 105, "R. Attack");
        
        ArrayList<Attack> gaeaCurrentAttacks = new ArrayList<>(4);
        gaeaCurrentAttacks.add(floralHealing);
        gaeaCurrentAttacks.add(foulAroma);
        gaeaCurrentAttacks.add(stoneShield);
        gaeaCurrentAttacks.add(terraForce);
        
        ComboAttack.getComboAttacks(gaea);
        
        ArrayList<Attack> gaeaOtherAttacks = new ArrayList<>(4);
        gaeaOtherAttacks.add(overgrowth);
        gaeaOtherAttacks.add(wrathOfGaea);
        gaeaOtherAttacks.add(earthsLove);
        gaeaOtherAttacks.add(obsidianStorm);
        
        gaea.setCurrentAttacks(gaeaCurrentAttacks);
        gaea.setListOfOtherAttacks(gaeaOtherAttacks);
        
        if(Game.isTesting())
        {
            gaea.setMaxHealth(9999);
            gaea.setCurrentHealth(9999);
            gaea.setAttack(9999);
            gaea.setDefense(9999);
            gaea.setRangedAttack(9999);
            gaea.setRangedDefense(9999);
            gaea.setSpeed(9999);
            gaea.getAttack().setOriginalValue(gaea.getAttack().getValue());
            gaea.getDefense().setOriginalValue(gaea.getDefense().getValue());
            gaea.getRangedAttack().setOriginalValue(gaea.getRangedAttack().getValue());
            gaea.getRangedDefense().setOriginalValue(gaea.getRangedDefense().getValue());
            gaea.getSpeed().setOriginalValue(gaea.getSpeed().getValue());
        }

        ArrayList<PlayerClass> gaeaOtherClasses = new ArrayList<>(3);
        gaeaOtherClasses.add(PlayerClass.getPlayerClass("Master Tank"));
        gaeaOtherClasses.add(PlayerClass.getPlayerClass("Hyper Clerk"));
        gaeaOtherClasses.add(PlayerClass.getPlayerClass("Guardian Striker"));
        
        gaea.setOtherClasses(gaeaOtherClasses);
        
        return gaea;
    }
    
    public static Player makeFultra()
    {
        PlayerClass allRounder = PlayerClass.getPlayerClass("All-Rounder");
        
        // Instantiating Fultra and his moveset--------------------------
        Player fultra = new Player("Fultra", "\"Fearless Thunder.\" A well renowned master of Electricity and one of the strongest Pulchrians.", "Electric", 
                allRounder, 6);
        fultra.setDeathMessage("What?! N-no! Gaea... I'm so sorry...");
        fultra.setBattleReadyMessage("Time for Fearless Thunder to shine!");
        fultra.setCheerReadyMessage("You're in good hands!");
        fultra.setMaxHealth(300);
        fultra.setCurrentHealth(300);
        fultra.setAttack(77);
        fultra.setDefense(77);
        fultra.setRangedAttack(77);
        fultra.setRangedDefense(77);
        fultra.setSpeed(77);
        
        fultra.getAttack().setOriginalValue(fultra.getAttack().getValue());
        fultra.getDefense().setOriginalValue(fultra.getDefense().getValue());
        fultra.getRangedAttack().setOriginalValue(fultra.getRangedAttack().getValue());
        fultra.getRangedDefense().setOriginalValue(fultra.getRangedDefense().getValue());
        fultra.getSpeed().setOriginalValue(fultra.getSpeed().getValue());
        
        OffensiveAttack thunderbolt = new OffensiveAttack("Thunderbolt", "The user shocks the target with a large thunderbolt.", 70, "R. Attack");
        OffensiveAttack overdrive = new OffensiveAttack("Overdrive", "The user coats themselves in electricity and rams into the target.", 115, "Attack");
        overdrive.setAccuracy(85);
        BuffAttack charge = new BuffAttack("Charge", "The user charges themselves with high amounts of electricity. Then, all stats are doubled for 2 turns.", "All", 2.0, 4, 2);
        DebuffAttack filter = new DebuffAttack("Filter", "The user creates an electrical barrier that weakens the target's ranged attacks for 3 turns.", "R. Attack", 4, 3);
        SingleHealingAttack bluePulse = new SingleHealingAttack("Blue Pulse", "The user emits a healing, electrical pulse that heals an ally slightly.", 0.15);
        OffensiveAttack plasmaBlast = new OffensiveAttack("Plasma Blast", "The user discharges a overwhelming electrical wave. It has poor accuracy due to taking time to charge.", 250, "R. Attack");
        plasmaBlast.setAccuracy(50);
        
        ArrayList<Attack> fultraCurrentAttacks = new ArrayList<>(4);
        fultraCurrentAttacks.add(thunderbolt);
        fultraCurrentAttacks.add(overdrive);
        fultraCurrentAttacks.add(charge);
        fultraCurrentAttacks.add(filter);
        
        ComboAttack.getComboAttacks(fultra);
        
        if(Game.isTesting())
        {
            fultra.setMaxHealth(9999);
            fultra.setCurrentHealth(9999);
            fultra.setAttack(9999);
            fultra.setDefense(9999);
            fultra.setRangedAttack(9999);
            fultra.setRangedDefense(9999);
            fultra.setSpeed(9999);
            fultra.getAttack().setOriginalValue(fultra.getAttack().getValue());
            fultra.getDefense().setOriginalValue(fultra.getDefense().getValue());
            fultra.getRangedAttack().setOriginalValue(fultra.getRangedAttack().getValue());
            fultra.getRangedDefense().setOriginalValue(fultra.getRangedDefense().getValue());
            fultra.getSpeed().setOriginalValue(fultra.getSpeed().getValue());
        }

        ArrayList<Attack> fultraOtherAttacks = new ArrayList<>(2);
        fultraOtherAttacks.add(bluePulse);
        fultraOtherAttacks.add(plasmaBlast);
        
        fultra.setCurrentAttacks(fultraCurrentAttacks);
        fultra.setListOfOtherAttacks(fultraOtherAttacks);
        
        return fultra;
    }
    
    public static Player makeResiFultra()
    {
        PlayerClass pc = PlayerClass.getPlayerClass("All-Rounder");

        Player fultra = new Player("Fultra", "Fultra in his R.E.S.I form. He fights with Anahita and the others to redeem himself", 
        "Electric", pc, 27);
        fultra.setDeathMessage("I just wanted to redeem myself...");
        fultra.setBattleReadyMessage("Let's go!");
        fultra.setCheerReadyMessage("You can trust me!");

        fultra.setMaxHealth(777);
        fultra.setCurrentHealth(777);
        fultra.setAttack(324);
        fultra.setDefense(324);
        fultra.setRangedAttack(324);
        fultra.setRangedDefense(324);
        fultra.setSpeed(324);

        fultra.getAttack().setOriginalValue(fultra.getAttack().getValue());
        fultra.getDefense().setOriginalValue(fultra.getDefense().getValue());
        fultra.getRangedAttack().setOriginalValue(fultra.getRangedAttack().getValue());
        fultra.getRangedDefense().setOriginalValue(fultra.getRangedDefense().getValue());
        fultra.getSpeed().setOriginalValue(fultra.getSpeed().getValue());

        ArrayList<Attack> currentAttacks = new ArrayList<>(4);
        currentAttacks.add(new BuffAttack("Charge II", "The user charges themselves with high amounts of electricity. All stats are doubled for 3 turns. The cooldown is less than Charge.", "All", 2.0, 3, 3));
        currentAttacks.add(new OffensiveAttack("Blackbolt", "The user brings down a massive, destructive lightning bolt that is dark in color.", 115, "R. Attack"));
        currentAttacks.get(1).setAccuracy(85);
        currentAttacks.add(new OffensiveAttack("Plasma Blast II", "The user discharges a overwhelming electrical wave. Its accuracy and power are better than normal.", 180, "R. Attack"));
        currentAttacks.get(2).setAccuracy(51); 
        OffensiveAttack overdriveII = new OffensiveAttack("Overdrive II", "The user coats themselves in electricity and rams into the target. It now has a higher crit rate.", 115, "Attack");
        overdriveII.setCritRate(0.35);
        overdriveII.setAccuracy(75);
        currentAttacks.add(overdriveII);

        SingleHealingAttack bluePulseII = new SingleHealingAttack("Blue Pulse II", "The user emits a healing, electrical pulse that heals an ally more than normal.", 0.25);
        DebuffAttack filterII = new DebuffAttack("Filter II", "The user creates a thick, electrical barrier that weakens the target's ranged attacks for 3 turns.", "R. Attack", 3, 3);

        ArrayList<Attack> fultraOtherAttacks = new ArrayList<>(4);
        fultraOtherAttacks.add(bluePulseII);
        fultraOtherAttacks.add(filterII);

        fultra.setCurrentAttacks(currentAttacks);
        ComboAttack.getComboAttacks(fultra);
        fultra.setListOfOtherAttacks(fultraOtherAttacks);

        return fultra;
    }

    public static Player makeCalmus()
    {
        PlayerClass pc = PlayerClass.getPlayerClass("Master Tank");
        
        // Instantiating Calmus and his moveset--------------------------
        Player calmus = new Player("Calmus", "A muscular yet gentle master of Fire.", "Fire", pc, 10);
        calmus.setDeathMessage("Argh, no! Anahita, I have failed you...");
        calmus.setBattleReadyMessage("I'm all fired up!");
        calmus.setCheerReadyMessage("Just let me know what to do!");
        calmus.setMaxHealth(420);
        calmus.setCurrentHealth(420);
        calmus.setAttack(160);
        calmus.setDefense(170);
        calmus.setRangedAttack(45);
        calmus.setRangedDefense(150);
        calmus.setSpeed(75);

        calmus.getAttack().setOriginalValue(calmus.getAttack().getValue());
        calmus.getDefense().setOriginalValue(calmus.getDefense().getValue());
        calmus.getRangedAttack().setOriginalValue(calmus.getRangedAttack().getValue());
        calmus.getRangedDefense().setOriginalValue(calmus.getRangedDefense().getValue());
        calmus.getSpeed().setOriginalValue(calmus.getSpeed().getValue());
        
        OffensiveAttack fieryWrath = new OffensiveAttack("Fiery Wrath", "The user goes berserk, ramming their flaming body into the target.", 135, "Attack");
        fieryWrath.setAccuracy(80);
        DebuffAttack heatWave = new DebuffAttack("Heat Wave", "The user causes an immense heat wave to surround the target, lowering its attack for 3 turns.", "Attack", 4, 3);
        BuffAttack flareBoost = new BuffAttack("Flare Boost", "The user charges themselves with flames to double their speed for 2 turns.", "Speed", 2.0, 4, 2);
        OffensiveAttack searingBlow = new OffensiveAttack("Searing Blow", "Using a flaming fist, the user lands a hard blow on the target.", 80, "Attack");
        BuffAttack flamingAura = new BuffAttack("Flaming Aura", "The user creates a hot aura that increases their ranged defense for 3 turns.", "R. Defense", 3);
        OffensiveAttack burningDunk = new OffensiveAttack("Burning Dunk", "The user attacks by grabbing the target and slamming them into the ground with intense heat.", 160, "Attack");
        burningDunk.setAccuracy(70);
        SingleHealingAttack pureFlame = new SingleHealingAttack("Pure Flame", "The user creates an unsoiled flame that heals any injury.", 0.25);
        DebuffAttack singe = new DebuffAttack("Singe", "The user singes the target with heat to make them more sensitive. This lowers their Defense.", "Defense", 4, 3);
        
        ArrayList<Attack> calmusCurrentAttacks = new ArrayList<>(4);
        calmusCurrentAttacks.add(fieryWrath);
        calmusCurrentAttacks.add(flareBoost);
        calmusCurrentAttacks.add(searingBlow);
        calmusCurrentAttacks.add(burningDunk);
        
        ComboAttack.getComboAttacks(calmus);
        
        ArrayList<Attack> calmusOtherAttacks = new ArrayList<>(4);
        calmusOtherAttacks.add(heatWave);
        calmusOtherAttacks.add(flamingAura);
        calmusOtherAttacks.add(pureFlame);
        calmusOtherAttacks.add(singe);
        
        calmus.setCurrentAttacks(calmusCurrentAttacks);
        calmus.setListOfOtherAttacks(calmusOtherAttacks);
        
        if(Game.isTesting())
        {
            calmus.setMaxHealth(9999);
            calmus.setCurrentHealth(9999);
            calmus.setAttack(9999);
            calmus.setDefense(9999);
            calmus.setRangedAttack(9999);
            calmus.setRangedDefense(9999);
            calmus.setSpeed(9999);
            calmus.getAttack().setOriginalValue(calmus.getAttack().getValue());
            calmus.getDefense().setOriginalValue(calmus.getDefense().getValue());
            calmus.getRangedAttack().setOriginalValue(calmus.getRangedAttack().getValue());
            calmus.getRangedDefense().setOriginalValue(calmus.getRangedDefense().getValue());
            calmus.getSpeed().setOriginalValue(calmus.getSpeed().getValue());
        }

        ArrayList<PlayerClass> calmusOtherClasses = new ArrayList<>(3);
        calmusOtherClasses.add(PlayerClass.getPlayerClass("Wild Tank"));
        calmusOtherClasses.add(PlayerClass.getPlayerClass("Master Striker"));
        calmusOtherClasses.add(PlayerClass.getPlayerClass("Hyper Clerk"));
        
        calmus.setOtherClasses(calmusOtherClasses);
        
        return calmus;
    }
    
    public static Player makeFrigs()
    {
        PlayerClass pc = PlayerClass.getPlayerClass("Master Striker");
        Player frigs;
        
        if(Game.isInSecondPhase())
        {
            frigs = new Player("Frigs", "A master of Ice with a cool personailty.", "Ice", pc, 22);
            // Instantiating Frigs' stats for second phase
            frigs.setMaxHealth(350);
            frigs.setCurrentHealth(350);
            frigs.setAttack(390);
            frigs.setDefense(150);
            frigs.setRangedAttack(360);
            frigs.setRangedDefense(150);
            frigs.setSpeed(270);
        }
        else
        {
            frigs = new Player("Frigs", "A witty master of Ice with a cool personailty.", "Ice", pc, 10);
            // Instantiating Frigs' stats for first phase 
            frigs.setMaxHealth(300);  // 10 * 60 = 600
            frigs.setCurrentHealth(300);
            frigs.setAttack(180);
            frigs.setDefense(70);
            frigs.setRangedAttack(170);
            frigs.setRangedDefense(70);
            frigs.setSpeed(110);
        }
        
        frigs.getAttack().setOriginalValue(frigs.getAttack().getValue());
        frigs.getDefense().setOriginalValue(frigs.getDefense().getValue());
        frigs.getRangedAttack().setOriginalValue(frigs.getRangedAttack().getValue());
        frigs.getRangedDefense().setOriginalValue(frigs.getRangedDefense().getValue());
        frigs.getSpeed().setOriginalValue(frigs.getSpeed().getValue());

        // Player frigs = new Player("Frigs", "A witty master of Ice with a cool personailty.", "Ice", pc, 21);
        frigs.setDeathMessage("Tch. Why like this? Anything but this...");
        frigs.setBattleReadyMessage("We got this. Just stay cool.");
        frigs.setCheerReadyMessage("You've got this! I'll be right here.");

        OffensiveAttack fimblevetr = new OffensiveAttack("Fimbulvetr", "The user blasts the target with what feels like an eternal blizzard. Has a higher chance to land a critical hit.", 90, "R. Attack");
        fimblevetr.setCritRate(0.35);
        fimblevetr.setAccuracy(90);
        OffensiveAttack icicleSlash = new OffensiveAttack("Icicle Slash", "The user creates many sharp icicles to slash at the target.", 95, "Attack");
        icicleSlash.setAccuracy(95);
        OffensiveAttack hailstorm = new OffensiveAttack("Hailstorm", "The user damages the target with a harsh hailstorm.", 100, "R. Attack");
        hailstorm.setAccuracy(85);
        BuffAttack frostedKnuckles = new BuffAttack("Frosted Knuckles", "The user creates a layer of ice on their fists to increase their attack for 3 turns.", "Attack", 4, 3);
        DebuffAttack frostbite = new DebuffAttack("Frostbite", "The user causes the target to have frostbite, lowering all stats for 2 turns.", "All", 0.5, 5, 2);
        frostbite.setAccuracy(60);
        OffensiveAttack avalanche = new OffensiveAttack("Avalanche", "The user attacks by causing an avalanche to fall on the target.", 85, "Attack");
        TeamHealingAttack aurora = new TeamHealingAttack("Aurora", "The user summons an aurora in the sky that sends healing down across the entire team.", 0.333, 3);
        OffensiveAttack subzeroSlammer = new OffensiveAttack("Subzero Slammer", "The user surrounds the target with subzero temperatures and rushes them down for an attack.", 140, "Attack");
        subzeroSlammer.setAccuracy(50);
        
        ArrayList<Attack> frigsCurrentAttacks = new ArrayList<>(4);
        frigsCurrentAttacks.add(fimblevetr);
        frigsCurrentAttacks.add(icicleSlash);
        frigsCurrentAttacks.add(hailstorm);
        frigsCurrentAttacks.add(frostedKnuckles);
        
        ComboAttack.getComboAttacks(frigs);
        
        ArrayList<Attack> frigsOtherAttacks = new ArrayList<>(4);
        frigsOtherAttacks.add(frostbite);
        frigsOtherAttacks.add(avalanche);
        frigsOtherAttacks.add(aurora);
        frigsOtherAttacks.add(subzeroSlammer);
        
        frigs.setCurrentAttacks(frigsCurrentAttacks);
        frigs.setListOfOtherAttacks(frigsOtherAttacks);
        
        if(Game.isTesting())
        {
            frigs.setMaxHealth(9999);
            frigs.setCurrentHealth(9999);
            frigs.setAttack(9999);
            frigs.setDefense(9999);
            frigs.setRangedAttack(9999);
            frigs.setRangedDefense(9999);
            frigs.setSpeed(9999);
            frigs.getAttack().setOriginalValue(frigs.getAttack().getValue());
            frigs.getDefense().setOriginalValue(frigs.getDefense().getValue());
            frigs.getRangedAttack().setOriginalValue(frigs.getRangedAttack().getValue());
            frigs.getRangedDefense().setOriginalValue(frigs.getRangedDefense().getValue());
            frigs.getSpeed().setOriginalValue(frigs.getSpeed().getValue());
        }

        ArrayList<PlayerClass> frigsOtherClasses = new ArrayList<>(3);
        frigsOtherClasses.add(PlayerClass.getPlayerClass("Tranquil Striker"));
        frigsOtherClasses.add(PlayerClass.getPlayerClass("Guardian Striker"));
        frigsOtherClasses.add(PlayerClass.getPlayerClass("Wild Tank"));
        
        frigs.setOtherClasses(frigsOtherClasses);
        
        return frigs;
    }
    
    public static Player makeNinlil()
    {
        PlayerClass pc = PlayerClass.getPlayerClass("Guardian Striker");
        Player ninlil;

        if(Game.isInSecondPhase())
        {
            ninlil = new Player("Ninlil", "A master of Wind with a (too) high esteem.", "Wind", pc, 15);
            // Instantiating Ninlil's stats for second phase
            ninlil.setMaxHealth(345);
            ninlil.setCurrentHealth(345);
            ninlil.setAttack(80);
            ninlil.setDefense(145);
            ninlil.setRangedAttack(380);
            ninlil.setRangedDefense(150); 
            ninlil.setSpeed(145);
        }
        else
        {
            ninlil = new Player("Ninlil", "A master of Wind with a (too) high esteem.", "Wind", pc, 10);
            // Instantiating Ninlil's stats for first phase
            ninlil.setMaxHealth(370);
            ninlil.setCurrentHealth(370);
            ninlil.setAttack(75);
            ninlil.setDefense(100);
            ninlil.setRangedAttack(250);
            ninlil.setRangedDefense(100);
            ninlil.setSpeed(75);
        }

        ninlil.getAttack().setOriginalValue(ninlil.getAttack().getValue());
        ninlil.getDefense().setOriginalValue(ninlil.getDefense().getValue());
        ninlil.getRangedAttack().setOriginalValue(ninlil.getRangedAttack().getValue());
        ninlil.getRangedDefense().setOriginalValue(ninlil.getRangedDefense().getValue());
        ninlil.getSpeed().setOriginalValue(ninlil.getSpeed().getValue());

        ninlil.setDeathMessage("I hope you can all forgive me... I thought I was strong enough...");
        ninlil.setBattleReadyMessage("I'll show you all how it's done.");
        ninlil.setCheerReadyMessage("... Okay. I'll help you.");
        
        OffensiveAttack hurricane = new OffensiveAttack("Hurricane", "The user creates a massive hurricane to damage the target.", 110, "R. Attack");
        hurricane.setAccuracy(90);
        OffensiveAttack tornado = new OffensiveAttack("Tornado", "The user causes a tornado to cause damage. This will deal critical damage 50% of the time.", 70, "R. Attack");
        tornado.setCritRate(0.50);
        tornado.setAccuracy(90);
        BuffAttack soaringSpirit = new BuffAttack("Soaring Spirit", "The user using their high spirits to double their attack for 2 turns.", "Attack", 2.0, 3, 2);
        OffensiveAttack aerialDance = new OffensiveAttack("Aerial Dance", "The user flies into the air and dances around the target while dealing a flurry of quick blows.", 100, "Attack");
        OffensiveAttack airSlash = new OffensiveAttack("Air Slash", "The user attacks by slashing the target with concentrated air.", 90, "R. Attack");
        OffensiveAttack tempestBlade = new OffensiveAttack("Tempest Blade", "Using blades made of pressurized air, the user slashes at the target. This has a high critical hit rate.", 85, "Attack");
        tempestBlade.setAccuracy(85);
        tempestBlade.setCritRate(0.35);
        TeamHealingAttack soothingWinds = new TeamHealingAttack("Soothing Winds", "The user creates an updraft of wind with healing properties to heal the team.", 0.15, 2);
        SingleHealingAttack slipstream = new SingleHealingAttack("Slipstream", "The user creates a gentle breeze that heals the wounds of the target.", 0.25, 3);
        
        ArrayList<Attack> ninlilCurrentAttacks = new ArrayList<>(4);
        ninlilCurrentAttacks.add(tornado);
        ninlilCurrentAttacks.add(soaringSpirit);
        ninlilCurrentAttacks.add(hurricane);
        ninlilCurrentAttacks.add(aerialDance);
        
        ComboAttack.getComboAttacks(ninlil);
        
        ArrayList<Attack> ninlilOtherAttacks = new ArrayList<>(4);
        ninlilOtherAttacks.add(airSlash);
        ninlilOtherAttacks.add(tempestBlade);
        ninlilOtherAttacks.add(soothingWinds);
        ninlilOtherAttacks.add(slipstream);
        
        ninlil.setCurrentAttacks(ninlilCurrentAttacks);
        ninlil.setListOfOtherAttacks(ninlilOtherAttacks);
        
        if(Game.isTesting())
        {
            ninlil.setMaxHealth(9999);
            ninlil.setCurrentHealth(9999);
            ninlil.setAttack(9999);
            ninlil.setDefense(9999);
            ninlil.setRangedAttack(9999);
            ninlil.setRangedDefense(9999);
            ninlil.setSpeed(9999);
            ninlil.getAttack().setOriginalValue(ninlil.getAttack().getValue());
            ninlil.getDefense().setOriginalValue(ninlil.getDefense().getValue());
            ninlil.getRangedAttack().setOriginalValue(ninlil.getRangedAttack().getValue());
            ninlil.getRangedDefense().setOriginalValue(ninlil.getRangedDefense().getValue());
            ninlil.getSpeed().setOriginalValue(ninlil.getSpeed().getValue());
        }

        ArrayList<PlayerClass> ninlilOtherClasses = new ArrayList<>(3);
        ninlilOtherClasses.add(PlayerClass.getPlayerClass("Master Clerk"));
        ninlilOtherClasses.add(PlayerClass.getPlayerClass("Master Tank"));
        ninlilOtherClasses.add(PlayerClass.getPlayerClass("Master Striker"));
        
        ninlil.setOtherClasses(ninlilOtherClasses);
        
        return ninlil;
    }

    /**
     * Helper method that will take the list of characters and a name. The character that has the given name will be removed.
     * @param team
     * @param name
     */
    public static void removeFromTeam(ArrayList<Player> team, String name)
    {
        for(Player p: team)
        {
            if(p.getName().equals(name))
            {
                team.remove(p);
            }
        }
    }
}
