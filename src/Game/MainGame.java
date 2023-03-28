package Game;

import Battle.Attack;
import Battle.BuffAttack;
import Battle.ComboAttack;
import Battle.DebuffAttack;
import Battle.Enemy;
import Battle.Inventory;
import Battle.Item;
import Battle.OffensiveAttack;
import Battle.Player;
import Battle.PlayerClass;
import Battle.SingleHealingAttack;
import Battle.TeamHealingAttack;
import Exploration.Wilderness;
import Utilites.MenuHelper;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A main class for running the game.
 *
 * @author Ian King
 */
public class MainGame {

    private static int gold = 10000;
    private static boolean finalBossDefeated;
    private static ArrayList<String> startUpOptions = new ArrayList<>();
    private static Inventory inventory = new Inventory();
    private static ArrayList<Player> playerTeam = new ArrayList<>(6);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
//        cd C:\Users\ianth\OneDrive\Desktop\ProjectPerfection\dist
//        java -jar ProjectPerfection.jar

        /*
        Healer classes:
            Master Clerk: heavily prioitizes healing; can heal allies no matter the distance; low hp, average attack and defense, 
                higher ranged defense and attack, speed can vary
                    Anahita's canon class
            Hyper Clerk: moderately prioitizes healing and attacks; can only heal adjacent allies; low hp, slightly higher 
                attack ^, average defense, equal ranged attack as attack, average ranged defense, higher speed
            Passive Clerk: moderately prioritizes healing and taking hits, can only heal adjacent allies; moderate hp, moderate
                attack, higher defense than ^ and ^^, slightly higher ranged attack than attack, slightly higher r. defense than
                defense, slow speed
                    Gaea's canon class
        
        Tank classes:
            Master Tank: heavily prioritizes taking hits; higher chances of being hit by adjacent enemies; high hp, moderate
                attack, low ranged attack, moderate/low ranged defense, speed low
            Wild Tank: moderately prioritizes taking hits and hits harder; lower chances to be hit; slightly lower hp ^, slightly
                higher attack ^, slightly lower defense, ranged attack and defense low, speed moderate
                    Calmus' canon class
            Holy Tank: moderately prioritizes taking hits and minor healing; can only heal adjacent allies; slightly lower hp ^^,
                same attack as ^^, same defense as ^, slightly higher ranged stats, speed slow/moderate
            
        Attacker classes:
            Master Striker: heavily prioritizes attacking; moderate chance of being hit; glass-cannon; moderate hp, one attack
                is high and the other is moderate, low defenses, high speed
                    Frigs' canon class
            Tranquil Striker: focuses on attacks and minor healing; only heals adjacent allies; slightly bulkier than ^ and doesn't
                hit as hard; slightly higher hp ^, slightly lower attack stats, slightly higher defenses, same speed ^
            Guardian Striker: fast defensive class with decent attacking power; moderate hp, has only one high attack stat,
                average defenses, average speed
                    Ninlil's canon class
        
        All-Rounder: Fultra exclusive class. All stats are relatively equal, with offensive stats and hp being the highest
         */
//        try
//        {
//            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//        }
//        catch(Exception e)
//        {
//            
//        }
        ComboAttack.populateComboAttackLibrary();
        Item.populateAllBuffItems();
        Item.populateAllHealItems();
        PlayerClass.createClasses();

//        ArrayList<Player> team = new ArrayList<>(6);
        // Instantiating Anahita and her moveset--------------------------
//        Player anahita = new Player("Anahita", "A master of water, and a kind, compassionate soul.", "Water", "Master Clerk", 5);
//        anahita.setDeathMessage("Everyone... I'm sorry...");
//        anahita.setCheerReadyMessage("I'm right behind you!");
//        anahita.setBattleReadyMessage("I have faith we'll succeed!");
//        anahita.setRoleType("Clerk");
//        anahita.setMaxHealth(300);
//        anahita.setCurrentHealth(300);
//        anahita.setAttack(45);
//        anahita.setDefense(55);
//        anahita.setRangedAttack(70);
//        anahita.setRangedDefense(75);
//        anahita.setSpeed(55);
//        
//        // All of Anahita's attacks
//        TeamHealingAttack blessedRain = new TeamHealingAttack("Blessed Rain", "The user heals each party member 30% of their total health.", 0.3, 2);
//        OffensiveAttack tsnunamiShot = new OffensiveAttack("Tsunami Shot", "The user shoots the target with pressurized water.", 75, "R. Attack");
//        SingleHealingAttack waterHalo = new SingleHealingAttack("Water Halo", "A ring of water surrounds the target and heals 10% of their total health for 3 turns.", 0.1);
//        OffensiveAttack torrent = new OffensiveAttack("Torrent", "The user blasts the target with a torrent of water.", 95, "R. Attack");
//        torrent.setAccuracy(85);
//        BuffAttack liquidArmor = new BuffAttack("Liquid Armor", "The user surrounds themself with a armor made of water, raises their Defense for 3 turns.", "Defense", 4, 3);
//        DebuffAttack sparklingMist = new DebuffAttack("Sparkling Mist", "The user sprays a light mist that sparkles and distracts the target to slightly lower their defenses for 3 turns.", "Defense,R. Defense", 0.75, 4, 3);
//        
//        ArrayList<Attack> anahitaCurrentAttacks = new ArrayList<>(4);
//        anahitaCurrentAttacks.add(blessedRain);
//        anahitaCurrentAttacks.add(tsnunamiShot);
//        anahitaCurrentAttacks.add(sparklingMist);
//        anahitaCurrentAttacks.add(torrent);
//        
//        
//        ComboAttack.getComboAttacks(anahita);
//        
//        ArrayList<Attack> anahitaOtherAttacks = new ArrayList<>(2);
//        anahitaOtherAttacks.add(waterHalo);
//        anahitaOtherAttacks.add(liquidArmor);
//        
//        anahita.setCurrentAttacks(anahitaCurrentAttacks);
//        anahita.setListOfOtherAttacks(anahitaOtherAttacks);
        //----------------------------------------------------------------
        // Instantiating Gaea and her moveset--------------------------
//        PlayerClass pc = PlayerClass.getPlayerClass("Passive Clerk");
//        Player gaea = new Player("Gaea", "A short and fiesty master of Earth.", "Earth", pc, 5);
//        gaea.setDeathMessage("Ana, Fultra, everyone... forgive me...");
//        gaea.setBattleReadyMessage("I'll help, but just don't mess up my hair!");
//        gaea.setCheerReadyMessage("I'm right here to support you!");
//        gaea.setClassRole("Clerk");
//        gaea.setAggro(5);
//        gaea.setMaxHealth(330);
//        gaea.setCurrentHealth(330);
//        gaea.setAttack(55);
//        gaea.setDefense(65);
//        gaea.setRangedAttack(65);
//        gaea.setRangedDefense(65);
//        gaea.setSpeed(50);
//        
//        ArrayList<Attack> gaeaAllAttacks = new ArrayList<>(6);
//        gaeaAllAttacks.add(new SingleHealingAttack("Floral Healing", "The user uses blessed plants to heal the target one third of their total health.", 0.333));
//        gaeaAllAttacks.add(new DebuffAttack("Trip Terrain", "The user plants thick vines to trip the target, lowering their speed for 3 turns.", "Speed", 4, 3));
//        gaeaAllAttacks.add(new DebuffAttack("Foul Aroma", "Using foul-smelling flowers, the user slightly lowers the target's attack and defense for 3 turns.", "Attack,Defense", 0.75, 4, 3));
//        gaeaAllAttacks.add(new BuffAttack("Stone Shield", "The user creates a shield made of stone to increase their ranged defense for 3 turns.", "R. Defense", 4, 3));
//        gaeaAllAttacks.add(new OffensiveAttack("Terra Force", "The user creates a massive boulder and launches it at the target.", 85, "Attack"));
//        gaeaAllAttacks.get(4).setAccuracy(90);
//        gaeaAllAttacks.add(new OffensiveAttack("Earth's Anger", "The user overwhelms and damages the target with viscious vines that emerge from the earth.", 80, "R. Attack"));
//        
//        ArrayList<Attack> gaeaCurrentAttacks = new ArrayList<>(4);
//        gaeaCurrentAttacks.add(gaeaAllAttacks.get(0));
//        gaeaCurrentAttacks.add(gaeaAllAttacks.get(2));
//        gaeaCurrentAttacks.add(gaeaAllAttacks.get(3));
//        gaeaCurrentAttacks.add(gaeaAllAttacks.get(4));
//        
//        ComboAttack.getComboAttacks(gaea);
//        
//        gaea.setCurrentAttacks(gaeaCurrentAttacks);
//        gaea.setListOfOtherAttacks(gaeaAllAttacks);
        //----------------------------------------------------------------
        // Instantiating Frigs and his moveset--------------------------
//        pc = PlayerClass.getPlayerClass("Master Striker");
//        Player frigs = new Player("Frigs", "A witty master of Ice with a cool personailty.", "Ice", pc, 5);
//        frigs.setDeathMessage("Tch... Why like this... Anything but this...");
//        frigs.setBattleReadyMessage("We've got this. Just stay cool.");
//        frigs.setCheerReadyMessage("You've got this!");
//        frigs.setClassRole("Striker");
//        frigs.setMaxHealth(310);
//        frigs.setCurrentHealth(310);
//        frigs.setAttack(75);
//        frigs.setDefense(40);
//        frigs.setRangedAttack(70);
//        frigs.setRangedDefense(40);
//        frigs.setSpeed(75);
//        
//        ArrayList<Attack> frigsAllAttacks = new ArrayList<>(6);
//        OffensiveAttack fimblevetr = new OffensiveAttack("Fimbulvetr", "The user blasts the target with what feels like an eternal blizzard. Has a higher chance to land a critical hit.", 70, "R. Attack");
//        fimblevetr.setCritRate(0.35);
//        frigsAllAttacks.add(fimblevetr);
//        frigsAllAttacks.get(0).setAccuracy(70);
//        frigsAllAttacks.add(new OffensiveAttack("Icicle Slash", "The user a sharp icicle to slash at the target.", 75, "Attack"));
//        frigsAllAttacks.add(new OffensiveAttack("Hailstorm", "The user damages the target with a harsh hailstorm.", 90, "R. Attack"));
//        frigsAllAttacks.get(2).setAccuracy(85);
//        frigsAllAttacks.add(new BuffAttack("Frosted Knuckles", "The user creates a layer of ice on their fists to increase their attack for 3 turns.", "Attack", 4, 3));
//        frigsAllAttacks.add(new DebuffAttack("Frostbite", "The user causes the target to have frostbite, lowering all stats for 2 turns.", "All", 0.75, 5, 2));
//        frigsAllAttacks.get(4).setAccuracy(60);
//        frigsAllAttacks.add(new OffensiveAttack("Avalanche", "The user attacks by causing an avalanche to fall on the target.", 70, "Attack"));
//        
//        ArrayList<Attack> frigsCurrentAttacks = new ArrayList<>(4);
//        frigsCurrentAttacks.add(frigsAllAttacks.get(0));
//        frigsCurrentAttacks.add(frigsAllAttacks.get(1));
//        frigsCurrentAttacks.add(frigsAllAttacks.get(3));
//        frigsCurrentAttacks.add(frigsAllAttacks.get(5));
//        
//        ComboAttack.getComboAttacks(frigs);
//        
//        frigs.setCurrentAttacks(frigsCurrentAttacks);
//        frigs.setListOfOtherAttacks(frigsAllAttacks);
        //--------------------------------------------------------------
        // Instantiating Calmus and his moveset--------------------------
//        pc = PlayerClass.getPlayerClass("Wild Tank");
//        Player calmus = new Player("Calmus", "A muscular yet gentle master of Fire.", "Fire", pc, 5);
//        calmus.setDeathMessage("Argh, no! Anahita, I have failed you...");
//        calmus.setBattleReadyMessage("I'm all fired up!");
//        calmus.setCheerReadyMessage("Just let me know what to do!");
//        calmus.setClassRole("Tank");
//        calmus.setAggro(10);
//        calmus.setMaxHealth(350);
//        calmus.setCurrentHealth(350);
//        calmus.setAttack(70);
//        calmus.setDefense(75);
//        calmus.setRangedAttack(40);
//        calmus.setRangedDefense(65);
//        calmus.setSpeed(50);
//        
//        ArrayList<Attack> calmusAllAttacks = new ArrayList<>(6);
//        calmusAllAttacks.add(new OffensiveAttack("Fiery Wrath", "The user goes berserk, ramming their flaming body into the target.", 95, "Attack"));
//        calmusAllAttacks.get(0).setAccuracy(80);
//        calmusAllAttacks.add(new DebuffAttack("Heat Wave", "The user causes an immense heat wave to surround the target, lowering its attack for 3 turns.", "Attack", 4, 3));
//        calmusAllAttacks.add(new BuffAttack("Flare Boost", "The user charges themselves with flames to double their speed for 2 turns.", "Speed", 2.0, 4, 2));
//        calmusAllAttacks.add(new OffensiveAttack("Searing Blow", "Using a flaming fist, the user lands a hard blow on the target.", 80, "Attack"));
//        calmusAllAttacks.add(new BuffAttack("Flaming Aura", "The user creates a hot aura that increases their ranged defense for 3 turns.", "R. Defense", 3));
//        calmusAllAttacks.add(new OffensiveAttack("Burning Dunk", "The user attacks by grabbing the target and slamming them into the ground with intense heat.", 100, "Attack"));
//        calmusAllAttacks.get(5).setAccuracy(80);
//        
//        ArrayList<Attack> calmusCurrentAttacks = new ArrayList<>(4);
//        calmusCurrentAttacks.add(calmusAllAttacks.get(0));
//        calmusCurrentAttacks.add(calmusAllAttacks.get(2));
//        calmusCurrentAttacks.add(calmusAllAttacks.get(3));
//        calmusCurrentAttacks.add(calmusAllAttacks.get(5));
//        
//        ComboAttack.getComboAttacks(calmus);
//        
//        calmus.setCurrentAttacks(calmusCurrentAttacks);
//        calmus.setListOfOtherAttacks(calmusAllAttacks);
        //--------------------------------------------------------------
        // Instantiating Ninlil and her moveset--------------------------
//        pc = PlayerClass.getPlayerClass("Guardian Striker");
//        Player ninlil = new Player("Ninlil", "A master of Wind with a (too) high esteem.", "Wind", pc, 5);
//        ninlil.setDeathMessage("I hope you can all forgive me... I thought I was strong enough...");
//        ninlil.setBattleReadyMessage("Tch! I'll show you all how it's done.");
//        ninlil.setCheerReadyMessage("Ugh, fine. I'll help you.");
//        ninlil.setClassRole("Striker");
//        ninlil.setAggro(7);
//        ninlil.setMaxHealth(335);
//        ninlil.setCurrentHealth(335);
//        ninlil.setAttack(35);
//        ninlil.setDefense(60);
//        ninlil.setRangedAttack(75);
//        ninlil.setRangedDefense(65);
//        ninlil.setSpeed(65);
//        
//        ArrayList<Attack> ninlilAllAttacks = new ArrayList<>(6);
//        ninlilAllAttacks.add(new OffensiveAttack("Hurricane", "The user creeates a massive hurricane to damage the target.", 100, "R. Attack"));
//        ninlilAllAttacks.get(0).setAccuracy(80);
//        ninlilAllAttacks.add(new OffensiveAttack("Tornado", "The user causes a tornado to cause damage.", 70, "R. Attack"));
//        ninlilAllAttacks.add(new BuffAttack("Soaring Spirit", "The user using their high spirits to double their attack for 2 turns.", "Attack", 2.0, 3, 2));
//        ninlilAllAttacks.add(new OffensiveAttack("Aerial Dance", "The user flies into the air and dances around the target while dealing a flurry of quick blows.", 85, "Attack"));
//        ninlilAllAttacks.add(new OffensiveAttack("Air Slash", "The user attacks by slashing the target with concentrated air.", 75, "R. Attack"));
//        ninlilAllAttacks.add(new OffensiveAttack("Sky Drop", "The user flies into the air with the target and drops them to deal damage.", 90, "Attack"));
//        ninlilAllAttacks.get(5).setAccuracy(90);
//        
//        ArrayList<Attack> ninlilCurrentAttacks = new ArrayList<>(4);
//        ninlilCurrentAttacks.add(ninlilAllAttacks.get(0));
//        ninlilCurrentAttacks.add(ninlilAllAttacks.get(1));
//        ninlilCurrentAttacks.add(ninlilAllAttacks.get(3));
//        ninlilCurrentAttacks.add(ninlilAllAttacks.get(4));
//        
//        ComboAttack.getComboAttacks(ninlil);
//        
//        ninlil.setCurrentAttacks(ninlilCurrentAttacks);
//        ninlil.setListOfOtherAttacks(ninlilAllAttacks);
        //---------------------------------------------------------------
        // Instantiating Fultra and his moveset--------------------------
//        pc = PlayerClass.getPlayerClass("All-Rounder");
//        Player fultra = new Player("Fultra", "\"Fearless Thunder.\" A well renowned master of Electricity.", "Electric", 
//                pc, 5);
//        fultra.setDeathMessage("What?! N-no! Gaea... I'm so sorry...");
//        fultra.setBattleReadyMessage("Time for Fearless Thunder to shine!");
//        fultra.setCheerReadyMessage("You're in good hands!");
//        fultra.setClassRole("All-Rounder");
//        fultra.setMaxHealth(335);
//        fultra.setCurrentHealth(335);
//        fultra.setAttack(60);
//        fultra.setDefense(60);
//        fultra.setRangedAttack(60);
//        fultra.setRangedDefense(60);
//        fultra.setSpeed(60);
//        
//        ArrayList<Attack> fultraAllAttacks = new ArrayList<>(6);
//        fultraAllAttacks.add(new OffensiveAttack("Thunderbolt", "The user shocks the target with a large thunderbolt.", 70, "R. Attack"));
//        fultraAllAttacks.add(new OffensiveAttack("Overdrive", "The user coats themselves in electricity and rams into the target.", 100, "Attack"));
//        fultraAllAttacks.get(1).setAccuracy(75);
//        fultraAllAttacks.add(new BuffAttack("Charge", "The user charges themselves with high amounts of electricity. Then, all stats are increased for 2 turns.", "All", 5, 2));
//        fultraAllAttacks.add(new DebuffAttack("Filter", "The user creates an electrical barrier that weakens the target's ranged attack for 3 turns.", "R. Attack", 4, 3));
//        fultraAllAttacks.add(new SingleHealingAttack("Blue Pulse", "The user emits a weak electrical pulse that heals an ally slightly.", 0.1));
//        fultraAllAttacks.add(new OffensiveAttack("Plasma Blast", "The user discharges a strong electrical shock wave.", 80, "R. Attack"));
//        
//        ArrayList<Attack> fultraCurrentAttacks = new ArrayList<>(4);
//        fultraCurrentAttacks.add(fultraAllAttacks.get(0));
//        fultraCurrentAttacks.add(fultraAllAttacks.get(1));
//        fultraCurrentAttacks.add(fultraAllAttacks.get(3));
//        fultraCurrentAttacks.add(fultraAllAttacks.get(2));
//        
//        ComboAttack.getComboAttacks(fultra);
//        
//        fultra.setCurrentAttacks(fultraCurrentAttacks);
//        fultra.setListOfOtherAttacks(fultraAllAttacks);
        //---------------------------------------------------------------
//        playerTeam.add(anahita);
//        playerTeam.add(gaea);
//        playerTeam.add(frigs);
//        playerTeam.add(calmus);
//        playerTeam.add(ninlil);
//        playerTeam.add(fultra);
//        playerFightingTeam.add(anahita);
//        playerFightingTeam.add(calmus);
//        playerFightingTeam.add(fultra);
        Enemy.populateAllAttacks();

//        Enemy e0 = new Enemy("Electric Elk", "An Elk that can control electricity.", "Electric", playerTeam, true);
//        Enemy e1 = new Enemy("Water Elk", "An Elk that can control water.", "Water", playerTeam, true);
//        Enemy e2 = new Enemy("Earth Elk", "An Elk that can control the earth.", "Earth", playerTeam, true);
//        e0.generateRandomStats();
//        e1.generateRandomStats();
//        e2.generateRandomStats();
//        ArrayList<Enemy> enemyTeam = new ArrayList<>(3);
//        enemyTeam.add(e0);
//        enemyTeam.add(e1);
//        enemyTeam.add(e2);
//        Battle battle = new Battle(enemyTeam, playerTeam);
//        battle.start(gold);
//        startUp();
        Game game = new Game(false);
        playerTeam = game.getTeam();
        game.startGame();

//        TypeChart tc = new TypeChart();
//        tc.printChart();
//        playerTeam.add(makeAnahita());
//        playerTeam.add(makeGaea());
//        playerTeam.add(makeFultra());
//        
//        Wilderness.makeOpiconTutorial(playerTeam).start(gold);
    }

    public static void startUp() {
        clearScreen();
        startUpOptions.add("New Game");

        String gameTitle = "Project Perfection";

        MainGame.printWithRandomLetters(gameTitle);
        System.out.println("\t");

        for (int i = 0; i < startUpOptions.size(); i++) {
            System.out.print("\t(" + (i + 1) + ") ");
            MainGame.printWithRandomLetters(startUpOptions.get(i));
        }

        int response = MenuHelper.displayMenu("", 1, startUpOptions.size() + 1);

        System.out.println("");

        clearScreen();

        switch (response) {
            case 1:
                Game game = new Game(false);
                game.startGame();
        }
    }

    /**
     * Prints an ellipsis without adding new lines.
     */
    public static void ellipsis() {
        printWait("... ", 500, 1000);
    }

    /**
     * Prints an ellipsis with a new line.
     */
    public static void ellipsisln() {
        printlnWait("...", 500, 1000);
    }

    /**
     * Prints an ellipsis with 2 new lines.
     */
    public static void ellipsislnln() {
        printlnlnWait("...", 500, 1000);
    }

    public static void println(String string, int time) {
        for (int i = 0; i < string.length(); i++) {
            System.out.print(string.charAt(i));

            try {
                Thread.sleep(time);
            } catch (InterruptedException ie) {

            }
        }

        System.out.println("");
    }

    public static void printlnln(String string, int time) {
        for (int i = 0; i < string.length(); i++) {
            System.out.print(string.charAt(i));

            try {
                Thread.sleep(time);
            } catch (InterruptedException ie) {

            }
        }

        System.out.println("\n");
    }

    /**
     * Calls printlnln and waits for the corresponding wait time.
     *
     * @param string
     * @param time
     * @param waitTime
     */
    public static void printlnlnWait(String string, int time, int waitTime) {
        printlnln(string, time);
        wait(waitTime);
    }

    /**
     * Calls println and waits for the corresponding wait time.
     *
     * @param string
     * @param time
     * @param waitTime
     */
    public static void printlnWait(String string, int time, int waitTime) {
        println(string, time);
        wait(waitTime);
    }

    /**
     * Calls print and waits for the corresponding wait time.
     *
     * @param string
     * @param time
     * @param waitTime
     */
    public static void printWait(String string, int time, int waitTime) {
        print(string, time);
        wait(waitTime);
    }

    public static void print(String string, int time) {
        for (int i = 0; i < string.length(); i++) {
            System.out.print(string.charAt(i));

            try {
                Thread.sleep(time);
            } catch (InterruptedException ie) {

            }
        }

//        System.out.print("");
    }

    public static void print(char c, int time) {
        System.out.print(c);

        try {
            Thread.sleep(time);
        } catch (InterruptedException ie) {

        }
    }

    /**
     * Takes a String with sentences divided by / to add new line characters.
     *
     * @param cutscene
     */
    public static void printWithBreaks(String cutscene) {
        Scanner scan = new Scanner(cutscene);
        scan.useDelimiter("/");

        while (scan.hasNext()) {
            printlnln(scan.next(), 25);
            wait(1750);
        }
    }

    public static void printWithRandomLetters(String string) {
        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);

            if (Character.isUpperCase(letter)) {
                for (int j = 0; j < 12; j++) {
                    int randNum = new Random().nextInt(90 - 65 + 1) + 65;
                    char c = (char) (randNum);
                    System.out.print(c);
                    wait(10);
                    System.out.print("\b");
                }
                System.out.print(letter);
            } else {
                for (int j = 0; j < 20; j++) {
                    int randNum = new Random().nextInt(122 - 97 + 1) + 97;
                    char c = (char) (randNum);
                    System.out.print(c);
                    wait(10);
                    System.out.print("\b");
                }
                System.out.print(letter);
            }
        }
    }

    public static void dialoguelnln(Player player, String dialogue) {
        printlnln("\t" + player.getName() + ": \"" + dialogue + "\"", 25);
        wait(1000);
    }

    public static void dialoguelnln(String name, String dialogue) {
        printlnln("\t" + name + ": \"" + dialogue + "\"", 25);
        wait(1000);
    }

    public static void dialogueln(Player player, String dialogue) {
        println("\t" + player.getName() + ": \"" + dialogue + "\"", 25);
        wait(1000);
    }

    public static void dialogueln(String name, String dialogue) {
        println("\t" + name + ": \"" + dialogue + "\"", 25);
        wait(2000);
    }

    public static void dialogueInteract(Player player, String dialogue) {
        print("\t" + player.getName() + ": \"" + dialogue + "\"", 25);
        waitForEnter();
    }

    public static void dialogueInteract(String name, String dialogue) {
        printlnln("\t" + name + ": \"" + dialogue + "\"", 25);
        promptToEnter();
    }

    public static void waitForEnter() {
        String prompt = "\n\tPress Enter to Continue >";
        print(prompt, 5);

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        if (input.isEmpty()) {
            return;
        }

        while (!input.equals("")) {
            for (int i = 0; i < input.length(); i++) {
                System.out.print("\b");
                System.out.print("");
            }

            input = "";
        }
    }

    public static void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ie) {

        }
    }

    public static boolean getFinalBossDefeated() {
        return finalBossDefeated;
    }

    public static void addToInventory(Item item, int quantity) {
        inventory.addTo(item, quantity);

    }

    public static Inventory getInventory() {
        return inventory;
    }

//    public static int getGold() {return gold;}
//    
//    public static void increaseGold(int amt) {gold += amt;}
//    public static void setCurrentObjective(String objective) {currentObjective= objective;}
//    
//    public static String getCurrentObjective() {return currentObjective;}
//    public static void decreaseGold(int amt) 
//    {
//        gold -= amt;
//        
//        if(gold < 0)
//        {
//            gold = 0;
//        }
//    }
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {

        }
    }

    /**
     * Waits for the user to press the "enter" key and then clears the screen.
     */
    public static void promptToEnter() {
        MainGame.waitForEnter();
        MainGame.clearScreen();
    }

    public static void printElementMatchups() {
        clearScreen();

        println("Water's Offensive Capabilites:", 25);
        printlnWait("\tWater is strong against: Fire and Earth", 25, 100);
        printlnWait("\tWater is not very effective against: Water", 25, 100);
        println("Water's Defensive Capabilites:", 25);
        printlnWait("\tWater is resistant to: Fire and Earth", 25, 100);
        printlnlnWait("\tWater is weak to: Electric", 25, 100);
        promptToEnter();

        println("Fire's Offensive Capabilites:", 25);
        printlnWait("\tFire is strong against: Ice and Earth", 25, 100);
        printlnWait("\tFire is not very effective against: Fire and Water", 25, 100);
        println("Fire's Defensive Capabilites:", 25);
        printlnWait("\tFire is resistant to: Ice", 25, 100);
        printlnlnWait("\tFire is weak to: Earth, Wind, and Water", 25, 100);
        promptToEnter();

        println("Wind's Offensive Capabilites:", 25);
        printlnWait("\tWind is strong against: Fire", 25, 100);
        printlnWait("\tWind is not very effective against: Electric", 25, 100);
        println("Wind's Defensive Capabilites:", 25);
        printlnlnWait("\tWind is weak to: Ice", 25, 100);
        promptToEnter();

        println("Ice's Offensive Capabilites:", 25);
        printlnWait("\tIce is strong against: Wind, Water and Earth", 25, 100);
        printlnWait("\tIce is not very effective against: Ice and Fire", 25, 100);
        println("Ice's Defensive Capabilites:", 25);
        printlnWait("\tIce is resistant to: Ice", 25, 100);
        printlnlnWait("\tIce is weak to: Fire", 25, 100);
        promptToEnter();

        println("Earth's Offensive Capabilites:", 25);
        printlnWait("\tEarth is strong against: Fire and Electric", 25, 100);
        printlnWait("\tEarth is not very effective against: Water", 25, 100);
        println("Earth's Defensive Capabilites:", 25);
        printlnWait("\tEarth is resistant to: Electric", 25, 100);
        printlnlnWait("\tEarth is weak to: Water, Fire, and Ice", 25, 100);
        promptToEnter();

        println("Electric's Offensive Capabilites:", 25);
        printlnWait("\tElectric is strong against: Water", 25, 100);
        printlnWait("\tElectric is not very effective against: Earth and Electric", 25, 100);
        println("Electric's Defensive Capabilites:", 25);
        printlnWait("\tElectric is resistant to: Wind and Electric", 25, 100);
        printlnlnWait("\tElectric is weak to: Earth", 25, 100);
        promptToEnter();

        new TypeChart().printChart();

        promptToEnter();
    }

    public static ArrayList<Player> getPlayerTeam() {
        return playerTeam;
    }

    public static void setPlayerTeam(ArrayList<Player> team) {
        playerTeam = team;
    }

    public static void targetingTutorial() {
        clearScreen();
        // Explains targeting with the board
        printlnlnWait("Targeting:", 25, 1500);
        printlnlnWait("\tLeft slot: The left slot can only target and be targeted by the opposing left and center "
                + "slots.\n\tCenter slot: The center slot can target and be targeted by all opposing slots.\n\t"
                + "Right slot: The right slot can target adn be targeted by the opposing center and right slots.", 40, 5000);

        waitForEnter();
    }

    public static void aggroTutorial() {
        clearScreen();

        // Explains the aggro system
        printlnlnWait("Aggro:", 25, 1500);
        printWithBreaks("Each of your characters has a value called "
                + "\"aggro\"./After every attack, their aggro will increase, forcing the enemy to target them./The character "
                + "with the most aggro will have a marker next to their name: (!)./"
                + "Certain moves accrue more aggro than others, and some classes affect this as well./"
                + "However, enemies will sometimes ignore this Aggro and target someone else, so be mindeful!");

        waitForEnter();
    }

    public static void cheerPartnerTutorial() {
        clearScreen();

        // Explains cheer partner mechanic
        printlnlnWait("Cheer Partners and Cheer Skills:", 25, 1500);
        printWithBreaks("When you have more than 3 team members, you can use a Cheer Partner on other characters./"
                + "A \"Cheer Partner\" will be there to support the person fighting by using their \"Cheer Skill.\"/"
                + "A Cheer Skill depends on the Cheer Partner's Class type./\tStriker: Boosts Attack and Ranged Attack "
                + "by 10 points./\tTank: Boosts Defense and Ranged Defense by 10 points.\n\tClerk: Heals the fighter "
                + "slightly if they aren't at full HP./\tAll-Rounder: Boosts Speed by 10 points.");
        waitForEnter();
        printlnlnWait("A Cheer Skill has a chance of activating after every turn a character finishes. It may come in handy!", 25, 3000);

        waitForEnter();
    }

    public static void cooldownTutorial() {
        clearScreen();

        printlnlnWait("Attack Cooldowns:", 25, 1500);
        printWithBreaks("The more you play, you'll become more acquainted with your types of attacks: Buff, Debuff, Offensive, Single Heal, and Team Heal./"
                + "All of these attacks except Offensive have \"Cooldowns.\"/If you view your character's attacks, you can see the cooldown value./"
                + "This value means that after using this attack, it will be unavailable for that amount of turns./After the cooldown effect is over, the move is available again.");

        waitForEnter();
    }

    public static void classTutorial() {
        clearScreen();

        // Part 1
        printlnlnWait("Classes:", 25, 1500);
        printWithBreaks("Each character has a \"Class.\" There are 10 unique classes in total:/Clerk classes:\n\tMaster Clerk: Focuses on healing.\n\tHyper Clerk: Focuses on healing and damage.\n\tPassive Clerk: Focuses on healing, debuffs, and aggro./"
                + "Striker classes:\n\tMaster Striker: Focuses on physical and ranged damage.\n\tTranquil Striker: Focuses on physical damage and slight healing.\n\tGuardian Striker: Focuses on ranged damage and slightly on aggro./"
                + "Tank classes:\n\tMaster Tank: Focuses on taking physical attacks and gaining aggro.\n\tHoly Tank: Focuses on taking hits in general with minor healing.\n\tWild Tank: Focuses on taking physical hits while dealing damage./"
                + "Special class:\n\tAll-Rounder: Relatively balanced with everything.");

        waitForEnter();
        clearScreen();

        // Part 2
        printWithBreaks("All classes except All-Rounder and the Master classes are made of two class roles./"
                + "For example, the Passive Clerk has the primary role of a Clerk class with the secondary role being the Tank./"
                + "This means that this role can heal while taking hits better than a Master Clerk.");

        waitForEnter();
        clearScreen();

        // Part 3
        printWithBreaks("Here are all the classes with their primary and secondary roles listed as Primary, Secondary:/\tMaster Clerk: Clerk, Clerk\n\tHyper Clerk: Clerk, Striker\n\tPassive Clerk: Clerk, Tank/"
                + "\tMaster Striker: Striker, Striker\n\tTranquil Striker: Striker, Clerk\n\tGuardian Striker: Striker, Tank/"
                + "\tMaster Tank: Tank, Tank\n\tHoly Tank: Tank, Clerk\n\tWild Tank: Tank, Striker");

        waitForEnter();
        clearScreen();

        // Part 4
        printWithBreaks("Classes mostly affect how your stats change when a level up occurs./Each class focuses on certain stats, and when a character levels up, their class determines the odds of a stat "
                + "increasing./For example, if Anahita levels up, you may not see her health increase, but it might increase if she levels up again.");

        waitForEnter();
        clearScreen();

        // Part 5
        printWithBreaks("Lastly, the Tank and Master Clerk classes have special properties in battle./Tank classes (primary or secondary) accrue more aggro than other classes normally would./"
                + "The Master Clerk class has a special property where they can heal anyone no matter the adjacency./Normally, a Clerk can only heal an adjacent ally. However, a Master Clerk ignores this.");

        waitForEnter();
        clearScreen();
    }

    public static Player makeAnahita() {
        PlayerClass masterClerk = PlayerClass.getPlayerClass("Master Clerk");

        // Instantiating Anahita and her moveset--------------------------
        Player anahita = new Player("Anahita", "A master of water and a kind, compassionate soul.", "Water", masterClerk, 5);
        anahita.setDeathMessage("Everyone... I'm sorry...");
        anahita.setCheerReadyMessage("I'm right behind you!");
        anahita.setBattleReadyMessage("I have faith we'll succeed!");
        anahita.setClassRole("Clerk");
        anahita.setMaxHealth(300);
        anahita.setCurrentHealth(300);
        anahita.setAttack(45);
        anahita.setDefense(55);
        anahita.setRangedAttack(70);
        anahita.setRangedDefense(75);
        anahita.setSpeed(55);

        // make this a method in the character class as a for loop
        anahita.getAttack().setOriginalValue(anahita.getAttack().getValue());
        anahita.getDefense().setOriginalValue(anahita.getDefense().getValue());
        anahita.getRangedAttack().setOriginalValue(anahita.getRangedAttack().getValue());
        anahita.getRangedDefense().setOriginalValue(anahita.getRangedDefense().getValue());
        anahita.getSpeed().setOriginalValue(anahita.getSpeed().getValue());

        // All of Anahita's attacks
        TeamHealingAttack blessedRain = new TeamHealingAttack("Blessed Rain", "The user heals each party member 20% of their total health.", 0.2, 2);
        OffensiveAttack tsnunamiShot = new OffensiveAttack("Tsunami Shot", "The user shoots the target with pressurized water.", 75, "R. Attack");
        SingleHealingAttack waterHalo = new SingleHealingAttack("Water Halo", "A ring of water surrounds the target and heals 30% of their total health.", 0.3, 3);
        OffensiveAttack torrent = new OffensiveAttack("Torrent", "The user blasts the target with a torrent of water.", 95, "R. Attack");
        torrent.setAccuracy(90);
        BuffAttack liquidArmor = new BuffAttack("Liquid Armor", "The user surrounds themself with a armor made of water, raises their Defense for 3 turns.", "Defense", 4, 3);
        DebuffAttack sparklingMist = new DebuffAttack("Sparkling Mist", "The user sprays a light mist that sparkles and distracts the target to slightly lower their defenses for 3 turns.", "Defense,R. Defense", 0.75, 4, 3);

        ArrayList<Attack> anahitaCurrentAttacks = new ArrayList<>(4);
        anahitaCurrentAttacks.add(blessedRain);
        anahitaCurrentAttacks.add(tsnunamiShot);
        anahitaCurrentAttacks.add(sparklingMist);
        anahitaCurrentAttacks.add(waterHalo);

        ComboAttack.getComboAttacks(anahita);

        ArrayList<Attack> anahitaOtherAttacks = new ArrayList<>(2);
        anahitaOtherAttacks.add(torrent);
        anahitaOtherAttacks.add(liquidArmor);

        anahita.setCurrentAttacks(anahitaCurrentAttacks);
        anahita.setListOfOtherAttacks(anahitaOtherAttacks);

        return anahita;
    }

    public static Player makeGaea() {
        PlayerClass passiveClerk = PlayerClass.getPlayerClass("Passive Clerk");

        // Instantiating Gaea and her moveset--------------------------
        Player gaea = new Player("Gaea", "A short and fiesty master of Earth.", "Earth", passiveClerk, 6);
        gaea.setDeathMessage("Ana, Fultra, everyone... forgive me...");
        gaea.setBattleReadyMessage("I'll help, but don't mess up my hair!");
        gaea.setCheerReadyMessage("I'm right here to support you!");
        gaea.setClassRole("Clerk");
        gaea.setAggro(5);
        gaea.setMaxHealth(315);
        gaea.setCurrentHealth(315);
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

        SingleHealingAttack floralHealing = new SingleHealingAttack("Floral Healing", "The user uses blessed plants to heal the target one third of their total health.", 0.333);
        DebuffAttack overgrowth = new DebuffAttack("Overgrowth", "The user grows giant, thick vines to trip the target, lowering their speed for 3 turns.", "Speed", 4, 3);
        DebuffAttack foulAroma = new DebuffAttack("Foul Aroma", "Using foul-smelling flowers, the user slightly lowers the target's attack and defense for 3 turns.", "Attack,Defense", 0.75, 4, 3);
        BuffAttack stoneShield = new BuffAttack("Stone Shield", "The user creates a shield made of stone to increase their ranged defense for 3 turns.", "R. Defense", 4, 3);
        OffensiveAttack terraForce = new OffensiveAttack("Terra Force", "The user creates a massive boulder and launches it at the target.", 85, "Attack");
        terraForce.setAccuracy(90);
        OffensiveAttack earthsWrath = new OffensiveAttack("Earth's Anger", "The user overwhelms and damages the target with viscious vines that emerge from the earth.", 80, "R. Attack");

        ArrayList<Attack> gaeaCurrentAttacks = new ArrayList<>(4);
        gaeaCurrentAttacks.add(floralHealing);
        gaeaCurrentAttacks.add(foulAroma);
        gaeaCurrentAttacks.add(stoneShield);
        gaeaCurrentAttacks.add(terraForce);

        ComboAttack.getComboAttacks(gaea);

        ArrayList<Attack> gaeaOtherAttacks = new ArrayList<>(2);
        gaeaOtherAttacks.add(overgrowth);
        gaeaOtherAttacks.add(earthsWrath);

        gaea.setCurrentAttacks(gaeaCurrentAttacks);
        gaea.setListOfOtherAttacks(gaeaOtherAttacks);

        return gaea;
    }

    public static Player makeFultra() {
        PlayerClass allRounder = PlayerClass.getPlayerClass("All-Rounder");

        // Instantiating Fultra and his moveset--------------------------
        Player fultra = new Player("Fultra", "\"Fearless Thunder.\" A well renowned master of Electricity and one of the strongest Pulchrians.", "Electric",
                allRounder, 6);
        fultra.setDeathMessage("What?! N-no! Gaea... I'm so sorry...");
        fultra.setBattleReadyMessage("Time for Fearless Thunder to shine!");
        fultra.setCheerReadyMessage("You're in good hands!");
        fultra.setClassRole("All-Rounder");
        fultra.setMaxHealth(335);
        fultra.setCurrentHealth(335);
        fultra.setAttack(72);
        fultra.setDefense(72);
        fultra.setRangedAttack(72);
        fultra.setRangedDefense(72);
        fultra.setSpeed(72);

        fultra.getAttack().setOriginalValue(fultra.getAttack().getValue());
        fultra.getDefense().setOriginalValue(fultra.getDefense().getValue());
        fultra.getRangedAttack().setOriginalValue(fultra.getRangedAttack().getValue());
        fultra.getRangedDefense().setOriginalValue(fultra.getRangedDefense().getValue());
        fultra.getSpeed().setOriginalValue(fultra.getSpeed().getValue());

        OffensiveAttack thunderbolt = new OffensiveAttack("Thunderbolt", "The user shocks the target with a large thunderbolt.", 70, "R. Attack");
        OffensiveAttack overdrive = new OffensiveAttack("Overdrive", "The user coats themselves in electricity and rams into the target.", 115, "Attack");
        overdrive.setAccuracy(75);

        BuffAttack charge = new BuffAttack("Charge", "The user charges themselves with high amounts of electricity. Then, all stats are increased for 2 turns.", "All", 5, 2);
        DebuffAttack filter = new DebuffAttack("Filter", "The user creates an electrical barrier that weakens the target's ranged attacks for 3 turns.", "R. Attack", 4, 3);
        SingleHealingAttack bluePulse = new SingleHealingAttack("Blue Pulse", "The user emits a healing, electrical pulse that heals an ally slightly.", 0.1);
        OffensiveAttack plasmaBlast = new OffensiveAttack("Plasma Blast", "The user discharges a overwhelming electrical wave. It has poor accuracy due to taking time to charge.", 150, "R. Attack");
        plasmaBlast.setAccuracy(50);

        ArrayList<Attack> fultraCurrentAttacks = new ArrayList<>(4);
        fultraCurrentAttacks.add(thunderbolt);
        fultraCurrentAttacks.add(overdrive);
        fultraCurrentAttacks.add(charge);
        fultraCurrentAttacks.add(filter);

        ComboAttack.getComboAttacks(fultra);

        ArrayList<Attack> fultraOtherAttacks = new ArrayList<>(2);
        fultraOtherAttacks.add(bluePulse);
        fultraOtherAttacks.add(plasmaBlast);

        fultra.setCurrentAttacks(fultraCurrentAttacks);
        fultra.setListOfOtherAttacks(fultraOtherAttacks);

        return fultra;
    }
}