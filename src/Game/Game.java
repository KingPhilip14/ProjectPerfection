package Game;

import Battle.Attack;
import Battle.BeachTutorialBattle;
import Battle.BuffAttack;
import Battle.DebuffAttack;
import Battle.EarthEnemy;
import Battle.ElectricEnemy;
import Battle.FireEnemy;
import Battle.IceEnemy;
import Battle.Item;
import Battle.NormalBattle;
import Battle.OffensiveAttack;
import Battle.OpiconTutorialBattle;
import Battle.Player;
import Battle.RESIBattle;
import Battle.SingleHealingAttack;
import Battle.TeamHealingAttack;
import Battle.WaterEnemy;
import Battle.WindEnemy;
import Exploration.Coordinate;
import Exploration.Location;
import Exploration.Map;
import Exploration.NPC;
import Exploration.Shop;
import Exploration.Village;
import Exploration.Wilderness;
import Utilites.MenuHelper;
import java.util.ArrayList;

/**
 * A class for creating Game objects to start the game.
 * @author Ian King
 */
public class Game 
{
    private boolean finalBossDefeated;
    private boolean inSecondPhase;
    private boolean beachTutorialDone = false;
    private boolean forestTutorialDone;
    private boolean talkedToMerda;
    private boolean isTesting;
    private Cutscene startingCutscene;
    private String endingCutscene;
    private Objective objective;
//    private String currentObjective;
    private Location currentLocation;
    private Location nextLocation;
    private ArrayList<Location> knownLocations = new ArrayList<>();
    private ArrayList<Location> remainingLocations = new ArrayList<>();
    private ArrayList<Player> team = new ArrayList<>(6);
//    private static boolean levelUpOccurred;
    private static int gold;
    private int pulchraPopulation = 43452;
    private Map map = new Map();
    
    public Game(boolean isTesting)
    {
        this.isTesting = isTesting;
//        startingCutscene =  new Cutscene("Amidst the ocean, there is an island inhabited by a special people able to control the elements./"
//                + "This island is called Pulchra./It's a small island full of beauty, vast creatures, and a peaceful people./"
//                + "A bright, young girl named Anahita is found at Purity Beach, located to the south of the island./She's "
//                + "peacefully absorbing the beach's scenery and enjoying the gentle breeze./Little does she know that her "
//                + "life is about to take a drastic turn.");
        populateAllLocations();
        knownLocations.add(remainingLocations.remove(0));
        nextLocation = remainingLocations.remove(0);
        
        
        
        if(!isTesting)
        {
            team.add(MainGame.makeAnahita());
            currentLocation = knownLocations.get(0);
            gold = 0;
        }
            
        
        
        // DELETE AFTER SECOND TUTORIAL TESTS
//        knownLocations.add(allLocations.remove(0));
        
        
        
        currentLocation = knownLocations.get(0);
        nextLocation = remainingLocations.remove(0);
        team.add(MainGame.makeAnahita());
        map = new Map();
        objective = new Objective();
        gold = 0;
//        team.add(MainGame.makeAnahita());
        
        if(isTesting)
        {
            // Puts the player at Opicon Forest
            addToKnownLocations();
//            knownLocations.add(remainingLocations.remove(0));
            
            // Puts the player in Water Village
            addToKnownLocations();
//            knownLocations.add(remainingLocations.remove(0));

            // Puts the player in Earth Village
            addToKnownLocations();
            currentLocation = knownLocations.get(knownLocations.size() - 1);
            
//            nextLocation = remainingLocations.remove(0);
            
            beachTutorialDone = true;
            forestTutorialDone = true;
            Player anahita = MainGame.makeAnahita();
            Player gaea = MainGame.makeGaea();
            Player fultra = MainGame.makeFultra();
            
            anahita.setMaxHealth(450);
            anahita.setAttack(200);
            anahita.setDefense(200);
            anahita.setRangedAttack(200);
            anahita.setRangedDefense(200);
            anahita.setSpeed(200);
            
            gaea.setMaxHealth(450);
            gaea.setAttack(200);
            gaea.setDefense(200);
            gaea.setRangedAttack(200);
            gaea.setRangedDefense(200);
            gaea.setSpeed(200);
            
            fultra.setMaxHealth(450);
            fultra.setAttack(200);
            fultra.setDefense(200);
            fultra.setRangedAttack(200);
            fultra.setRangedDefense(200);
            fultra.setSpeed(200);
            
            team.add(anahita);
            team.add(gaea);
            team.add(fultra);
            
            objective.update();
            objective.update();
            objective.update();
            objective.update();
            
            gold = 10000;
        }
        
        map = new Map();
    }
    
    public boolean inSecondPhase() {return inSecondPhase;}
    
//    public static void setLevelUpOccurred() {levelUpOccurred = true;}
    
    public ArrayList<Player> getTeam() {return team;}
    
    public static int getGold() {return gold;}
    public static String getGoldString() {return String.format("%,d", gold) + " G";}
    
    public static void increaseGold(int amt) {gold += amt;}
    
    public static void decreaseGold(int amt) 
    {
        gold -= amt;
        
        if(gold < 0)
        {
            gold = 0;
        }
    }
    
    private void instatiations()
    {
        WindEnemy.createSpeciesNames();
        WaterEnemy.createSpeciesNames();
        FireEnemy.createSpeciesNames();
        ElectricEnemy.createSpeciesNames();
        IceEnemy.createSpeciesNames();
        EarthEnemy.createSpeciesNames();
    }
    
    public void startGame()
    {
        // Comment out for testing
        MainGame.clearScreen();
        gameOpening();
        
        instatiations();
//        currentObjective = "Get to Opicon Forest (Required level: " + nextLocation.getRequiredLevel() + ")";
        
//        printNewAreaInfo();

        while(!MainGame.getFinalBossDefeated())
        {
            processInput();
        }
    }
    
    private void populateAllLocations()
    {
        remainingLocations.add(createPurityBeach());
        remainingLocations.add(createOpiconForest());
        remainingLocations.add(createWaterVillage());
        remainingLocations.add(createEarthVillage());
        remainingLocations.add(createZoniVillage());
        remainingLocations.add(createWindVillage());
        remainingLocations.add(createTempestTower());
        remainingLocations.add(createFireVillage());
        remainingLocations.add(createMountVolcan());
        remainingLocations.add(createMountZoni());
        remainingLocations.add(createIceVillage());
        remainingLocations.add(createForlornDesert());
        remainingLocations.add(createElectricVillage());
    }
    
    private void gameOpening()
    {
        Cutscene.startingCutscene(isTesting);
    }
    
    private void displayInfo()
    {
        objective.printCurrentObjective();
        MainGame.println("Current Location: " + currentLocation.getName(), 10);
        MainGame.println("Current Gold: " + String.format("%,d", getGold()) + " G", 10);
        MainGame.printlnln("Pulchra Population: " + String.format("%,d", pulchraPopulation), 10);
    }
    
    public void processInput()
    {
        MainGame.clearScreen();
        displayInfo();
        
        MainGame.println("What would you like to do?", 5);
        String message = "\t1) Travel\n\t2) ";
        int input;
        
        if(currentLocation instanceof Village)
        {
            message += "Shop\n\t3) Talk to Townsfolk\n\t4) Search for Chest\n\t5) View Inventory\n\t6) Options";
            input = MenuHelper.displayMenu(message, 1, 6);
            
            switch(input)
            {
                case 1:
                    askForLocation();
                    break;
                case 2:
                    activateShop();
                    break;
                case 3:
                    talkToPeople();
                    break;
                case 4:
                    findVillageChest();
                    break;
                case 5:
                    viewInventory();
                    break;
                case 6:
                    optionsMenu();
                    break;
            }
            
            MainGame.waitForEnter();
        }
        // If the current location is of type Wildnerness
        else
        {
            message += "Battle\n\t3) Search for Chest\n\t4) View Inventory\n\t5) Options";
            input = MenuHelper.displayMenu(message, 1, 5);
            
            switch(input)
            {
                case 1:
                    askForLocation();
                    break;
                case 2:
                    battle();
                    break;
                case 3:
                    findWildnernessChest();
                    break;
                case 4:
                    viewInventory();
                    break;
                case 5:
                    optionsMenu();
                    break;
            }
            
            MainGame.waitForEnter();
        }
        
//        MainGame.waitForEnter();
    }
    
    private void transition(Location newLocation)
    {
        currentLocation.travelDescription(currentLocation, newLocation);
        
        updateAndPrintMap(newLocation);
        
        currentLocation = newLocation;
        
        printNewAreaInfo();
        // Now using currentLocation as the newly arrived to Location
//        if(!currentLocation.isExplored())
//        {
//            MainGame.clearScreen();
//            MainGame.printWithRandomLetters("Welcome to " + currentLocation.getName() + ":");
//            MainGame.wait(2000);
//            MainGame.printlnlnWait("\n" + currentLocation.getDescription(), 25, 4000);
////            currentLocation.setIsExplored();
//        }
//        
//        checkForCutscene();
    }
    
    private void printNewAreaInfo()
    {
        if(!currentLocation.isExplored())
        {
            MainGame.clearScreen();
            MainGame.printWithRandomLetters("Welcome to " + currentLocation.getName() + ":");
            MainGame.wait(2000);
            MainGame.printlnlnWait("\n" + currentLocation.getDescription(), 25, 4000);
//            currentLocation.setIsExplored();
        }
        
        checkForCutscene();
    }
    
    private void updateAndPrintMap(Location newLocation)
    {
        map.updateMap(currentLocation, newLocation);
        map.printMap();
        
        MainGame.waitForEnter();
//        MainGame.clearScreen();
    }
    
    private void askForLocation()
    {
        String message = "\nWhere would you like to go?\n\t";
        
        map.printWithCurrentLocation(currentLocation);
        
        int numOfOptions = 0;
        
        for(Location location : knownLocations)
        {
            message += ++numOfOptions + ") " + location.getName() + "\n\t";
        }
        
        message += ++numOfOptions + ") Back";
        
        int input = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        if(input == numOfOptions)
        {
            processInput();
        }
        else if(knownLocations.get(input - 1) == currentLocation)
        {
            System.out.println("");
            MainGame.printWait("Anahita: Stop messing around! We're already at " + currentLocation + "!", 25, 1500);
        }
        else
        {
            Location newLocation = knownLocations.get(input - 1);
            transition(newLocation);
        }
    }
    
    private void activateShop()
    {
        MainGame.clearScreen();
        
        if(inSecondPhase && currentLocation.getName().equals("Zoni Village"))
        {
            MainGame.printlnlnWait("The shop was destroyed during the invasion...", 25, 2000);
            return;
        }
        
        Shop shop = ((Village)currentLocation).getShop();
        shop.startShopping();
    }
    
    private void talkToPeople()
    {
        Village village = ((Village)currentLocation);
        village.talkToPeople();
        
        /*
        Since the player sometimes unlocks a new location by talking to certain people, a check will occur here
        for if they talked to that person. This is to ensure that if the player is already at a specific level and
        hasn't talked to the specific person, after doing so, the next location is unlocked immediately.
        */
        
        if(talkedToSpecificPerson())
        {   
            objective.update();
            checkForNextLocation();
        }
    }
    
    /**
     * Creates a battle sequence for the player to follow.
     */
    private void battle()
    {
        MainGame.clearScreen();
        
        // If the player is in Purity Beach and they haven't done it's tutorial, do it
        if(!beachTutorialDone && currentLocation.getName().equals("Purity Beach"))
        {
            BeachTutorialBattle battle = ((Wilderness)currentLocation).makeBeachTutorial(team.get(0));
            battle.start(gold);
            
            // If the player loses the tutorial, they can't move on
            if(battle.isWon())
            {
                beachTutorialDone = true;
            }   
        }
        // If the player is in Opicon Forest and haven't done the last tutorial
        else if(!forestTutorialDone && currentLocation.getName().equals("Opicon Forest"))
        {
            OpiconTutorialBattle battle = ((Wilderness)currentLocation).makeOpiconTutorial(team);
            battle.start(gold);
            
            // If the player loses the tutorial, they can't move on
            if(battle.isWon())
            {
                forestTutorialDone = true;
                
                // Activate cutscene here so it only happens right after the tutorial is done
//                Cutscene.opiconCutscene2();
            }
            
//            opiconForestCutscene2();
        }
        // First phase only has normal battles
        else if(!inSecondPhase)
        {
            NormalBattle battle = ((Wilderness)currentLocation).makeNormalBattle(team);
            battle.start(gold);
        }
        // Second phase - 65% normal battles, 35% RESI battles
        else if(inSecondPhase)
        {
            chooseBattle(gold);
        }
        
        checkForNextLocation();
    }
    
    private void chooseBattle(int chance)
    {
        if(chance >= 0 && chance < 65)
        {
            NormalBattle battle = ((Wilderness)currentLocation).makeNormalBattle(team);
            battle.start(gold);
        }
        else
        {
            RESIBattle battle = ((Wilderness)currentLocation).makeRESIBattle(team);
            battle.start(gold);
        }
    }
    
    private boolean talkedToSpecificPerson()
    {
        Village village = (Village)currentLocation;
        
        // Chain of if statements look for if a specific NPC was talked to at the location
        if(village.getName().equals("Water Village") && village.getNPC("Merda").hasBeenTalkedTo())
        {
            return true;
        }
        else if(village.getName().equals("Earth Village") && village.getNPC("Fleur").hasBeenTalkedTo())
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns true if specific conditions have been met aside from reaching certain levels (used specifically with 
     * checkForNextLocation).
     * @return true or false
     */
    private boolean specificObjectiveMet()
    {
        /**********************************************************************
        find a way to check for talking to an NPC without disrupting the check for the levels to unlock
        * the next area
        */
        
        
        
        
        Village village = (Village)currentLocation;
        
        /*
        If the player is at Water Village, and Merda's cutscene has been completed (which counts as talking to her), 
        the player can progress 
        */
        if(village.getName().equals("Water Village") && village.getNPC("Merda").hasBeenTalkedTo())
        {
            return true;
        }
        
        return false;
    }
    
    private void checkForNextLocation()
    {
        // If a specific objective hasn't been met, don't allow the player to get to the next area
//        if(!specificObjectiveMet())
//        {
//            return;
//        }
        
        int level = Player.highestPlayerLV(team);
        Location latestLocation = knownLocations.get(knownLocations.size() - 1);
        
        // While the highest level is >= to the required level AND the newest location was explored, the player unlocks the next location 
        while(level >= nextLocation.getRequiredLevel() && latestLocation.isExplored())
        {
            nextLocation.setUnlocked(true);
            locationUnlocked();
        }
        
//        if(nextLocation.isUnlocked())
//        {
//            locationUnlocked();
//        }
    }
    
    private void locationUnlocked()
    {
//        MainGame.clearScreen();
        MainGame.printlnlnWait("Congratulations! You can now travel to " + nextLocation.getName() + "!", 25, 4000);
        addToKnownLocations();
        // Removes the location from the overall ArrayList to the known ArrayList
//        knownLocations.add(nextLocation);
//        nextLocation = remainingLocations.remove(0);
//        nextLocation.setIsExplored();
//        levelUpOccurred = false;
    }
    
    private void addToKnownLocations()
    {
        knownLocations.add(nextLocation);
        nextLocation = remainingLocations.remove(0);
    }
    
    private void findVillageChest()
    {
        Village village = ((Village)currentLocation);
        village.findChest();
        processInput();
    }
    
    private void findWildnernessChest()
    {
        Wilderness wildnerness = ((Wilderness)currentLocation);
        wildnerness.findChest();
        processInput();
    }
    
    private void optionsMenu()
    {
        MainGame.println("\nWhat would you like to do?", 25);
        String message = "\t1) View Team\n\t2) View Tutorials\n\t3) View World Map\n\t4) Save\n\t5) Back";
        int input = MenuHelper.displayMenu(message, 1, 5);
        
        switch(input)
        {
            case 2:
                viewTutorials();
                break;
            case 3:
                MainGame.clearScreen();
                viewWorldMap();
                break;
            case 4:
                save();
                break;
            case 5:
                processInput();
                break;
            default:
                viewTeam();
                break;
        }
    }
    
    private void viewTutorials()
    {
        MainGame.println("\nWhich tutorial would you like to review?", 25);
        String message = "\t1) Element Matchups\n\t2) Targeting\n\t3) Aggro\n\t4) Cheer Partner and Cheer Skills\n\t5) Back";
        int input = MenuHelper.displayMenu(message, 1, 5);
        
        switch(input)
        {
            case 2:
                MainGame.targetingTutorial();
                break;
            case 3:
                MainGame.aggroTutorial();
                break;
            case 4:
                MainGame.cheerPartnerTutorial();
                break;
            case 5: 
                optionsMenu();
                break;
            default: 
                MainGame.printElementMatchups();
                break;
        }
    }
    
    private void save()
    {
//        MainGame.save();
        
    }
    
    private void viewWorldMap()
    {
        map.printWithCurrentLocation(currentLocation);
    }
    
    private void viewInventory()
    {
        if(MainGame.getInventory().isEmpty())
        {
            System.out.println("");
            MainGame.dialoguelnln("Anahita", "Aww, dang it! We don't have anything!");
        }
        else
        {
            MainGame.println("\nInventory:\n", 25);
            MainGame.getInventory().showInventory();
        }
    }
    
    private void viewTeam()
    {
        MainGame.println("\nWho would you like to view?", 25);
        String message = "";
        int numOfOptions = 0;
        
        for(Player p : team)
        {
            message += "\t" + ++numOfOptions + ") " + p.getName() + "\n";
        }
        
        message += "\t" + ++numOfOptions + ") Back";
        
        int input = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        if(input == numOfOptions)
        {
            optionsMenu();
        }
        else
        {
            Player p = team.get(--input);
            viewPlayer(p);
        }
    }
    
    private void viewPlayer(Player player)
    {
        MainGame.println("\nWhat would you like to do with " + player.getName() + "?", 25);
        String message = "\t1) View Stats\n\t2) Change Moves\n\t3) Change Class\n\t4) Back";
        int input = MenuHelper.displayMenu(message, 1, 4);
        
        switch(input)
        {
            case 2:
                changeMoves(player);
                break;
            case 3:
                // MAKE MEEEEE **********------------------*************
//                changeClass();
                break;
            case 4:
                viewTeam();
                break;
            default:
                MainGame.printlnln("\n" + player.toOverallString(), 25);
                MainGame.waitForEnter();
                break;
        }
        
        viewTeam();
    }
    
    private void changeMoves(Player player)
    {
        MainGame.printlnWait("\n" + player.getName() + "'s current moves:", 25, 1500);
        
        // Prints out the four moves the character can use
        for(Attack attack : player.getCurrentAttacks())
        {
            printMove(attack);
        }
        
        MainGame.printlnWait(player.getName() + "'s other moves:", 25, 1500);
        
        // Prints out the other two moves the character can use
        for(Attack attack : player.getOtherAttacks())
        {
            printMove(attack);
        }
        
        promptToChangeMove(player);
    }
    
    private void printMove(Attack attack)
    {
        if(attack instanceof OffensiveAttack)
        {
            MainGame.printlnlnWait(((OffensiveAttack)attack).toString(), 25, 2000);
        }
        else if(attack instanceof BuffAttack)
        {
            MainGame.printlnlnWait(((BuffAttack)attack).toString(), 25, 2000);
        }
        else if(attack instanceof DebuffAttack)
        {
            MainGame.printlnlnWait(((DebuffAttack)attack).toString(), 25, 2000);
        }
        else if(attack instanceof SingleHealingAttack)
        {
            MainGame.printlnlnWait(((SingleHealingAttack)attack).toString(), 25, 2000);
        }
        else if(attack instanceof TeamHealingAttack)
        {
            MainGame.printlnlnWait(((TeamHealingAttack)attack).toString(), 25, 2000);
        }
    }
    
    private void promptToChangeMove(Player p)
    {
        MainGame.println("\nWhich move would you like to change?", 25);
        String message = "";
        int numOfOptions = 0;
        
        for(Attack a : p.getCurrentAttacks())
        {
            message += "\t" + ++numOfOptions + ") " + a.getName() + "\n";
        }
        
        message += "\t" + ++numOfOptions + ") Go Back to View Team";
        
        int input = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        if(input == numOfOptions)
        {
            viewTeam();
        }
        else
        {
            promptForOtherAttack(p, input);
        }
    }
    
    private void promptForOtherAttack(Player p, int inputForFirstAttack)
    {
        MainGame.println("\nWhich move would you like to change it with?", 25);
        String message = "";
        int numOfOptions = 0;
        
        for(Attack a : p.getOtherAttacks())
        {
            message += "\t" + ++numOfOptions + ") " + a.getName() + "\n";
        }
        
        message += "\t" + ++numOfOptions + ") Go Back to View Team";
        
        int inputForOtherAttack = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        if(inputForOtherAttack == numOfOptions)
        {
            viewTeam();
        }
        else
        {
            switchAttacks(inputForFirstAttack, inputForOtherAttack, p);
        }
    }
    
    private void switchAttacks(int currentAttackInput, int otherAttackInput, Player p)
    {
        // Decrement inputs for use in the ArrayLists
        --currentAttackInput;
        --otherAttackInput;
        
        Attack currentAttack = p.getCurrentAttacks().get(currentAttackInput);
        Attack otherAttack = p.getOtherAttacks().get(otherAttackInput);
        
        p.getCurrentAttacks().set(currentAttackInput, otherAttack);
        p.getOtherAttacks().set(otherAttackInput, currentAttack);
        
        MainGame.printlnlnWait("\nMove Change: Successful!\n\t" + currentAttack.getName() +
                " <----------> " + otherAttack.getName(), 25, 1500);
        
        MainGame.printlnWait(p.getName() + "'s new, current moves:", 25, 1500);
        
        // Prints out the four moves the player now knows
        for(Attack attack : p.getCurrentAttacks())
        {
            printMove(attack);
        }
        
        MainGame.waitForEnter();
        viewTeam();
    }
    
    public ArrayList<Location> getKnownLocations() {return knownLocations;}
    
//    public void addKnownLocation()
//    {
//        if(!currentLocation.isExplored())
//        {
//            currentLocation.setIsExplored();
//            knownLocations.add(currentLocation);
//        }
//    }
    
    private Village createWaterVillage()
    {
        // Anahita's mother
        NPC merda = new NPC("Merda", "I'll see you guys at the festival later!", true);
//        merda.setGiveGiftMessage("Luckily for you, I baked your favorite. You'll do great tonight sweetie!");
        merda.setDescription("Anahita's mother");
        
        // Anahita's little sister
        NPC brinlee = new NPC("Brinlee", "Ahh, Ana, Gaea! Are you going to the festival? Oh, oh! Can I braid Gaea's hair again? Can I-- what?\n\tYou don't have time today? Aww...", false);
        brinlee.setDescription("Anahita's little sister");
        
        Item gift = Item.getHealingItem("Lollipop");
        
        // Anahita's father
        NPC lac = new NPC("Lac", "It's the Drama Queen Duo and Fearless Thunder! Bahaha! It's a pleasure to see you all. Make sure you're ready\n\tfor the festival later!", gift, 2, false);
        lac.setGiveGiftMessage("I have something I'd like to give you guys. It isn't much, but it might help tame that sweet tooth!");
        lac.setDescription("Anahita's father");
        
        NPC buzi = new NPC("Buzi", "Hey guys! I hope you're ready for the annual festival tonight. It'll be a blast!", false);
        buzi.setDescription("Water Village resident");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(merda);
        people.add(brinlee);
        people.add(lac);
        people.add(buzi);
        
        //----------------------------------------------------------------------
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.getHealingItem("Cinnamon Roll"));
        items.add(Item.getHealingItem("Big Cinnamon Roll"));
        items.add(Item.getHealingItem("Lollipop"));
        items.add(Item.getBuffItem("Purple Bean"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        // X coordinate is 1 less, and Y coordinate is 2 less than what they actually are in the text file
        Coordinate c = new Coordinate(17, 24);
        Village v = new Village("Water Village", "A village located above Opicon Forest. Its residents are known to be very altruistic and compassionate.", people, 7, 1020, c);
        v.setShop(s);
        return v;
    }
    
    private Village createEarthVillage()
    {
        NPC gord = new NPC("Gord", "The thing I love most about Pulchra is how we all live in harmony. Our powers make it easy to help each other out.", false);
        gord.setDescription("Earth Village resident");
        
        NPC caillou = new NPC("Caillou", "ugh... i'm so weak now... the effects of my beans wore off... remember what i said, okay?", true);
        caillou.setDescription("Earth Village resident // Bean Master"); 
       
        // Gaea's cousin
        NPC fleur = new NPC("Fleur", "Thank you guys for your help again! But why are you still here? Go to the festival!", true);
        fleur.setDescription("Gaea's older cousin");
        
        NPC roxy = new NPC("Roxy", "Have you guys seen the flowers blooming in Opicon Forest? This is the best time of year to see them if you haven't!", false);
        roxy.setDescription("Earth Village resident");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(gord);
        people.add(caillou);
        people.add(fleur);
        people.add(roxy);
        
        //----------------------------------------------------------------------
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.getHealingItem("Big Cinnamon Roll"));
        items.add(Item.getHealingItem("Cheesecake"));
        items.add(Item.getBuffItem("Red Bean"));
        items.add(Item.getBuffItem("Orange Bean"));
        items.add(Item.getBuffItem("Purple Bean"));
        items.add(Item.getBuffItem("Green Bean"));
        items.add(Item.getBuffItem("Blue Bean"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(22, 13);
        Village v = new Village("Earth Village", "A village located southwest of the Water Village.\nTheir residents love to take care of themselves and help the island's vegetation to prosper.", people, 9, 1271, c);
        v.setShop(s);
        return v;
    }
    
    private Village createZoniVillage()
    {
        Item gift = Item.getHealingItem("Apple Pie");
        NPC calmus = new NPC("Calmus", "Oh wow! Ana, Gaea! How's it going? It's been a while since we've talked! I hope we can catch up after all of the celebration.", gift, true);
        calmus.setGiveGiftMessage("Before you go -- my grandma made a little to many pastries for the festival. I'd like you to take one.");
        calmus.setDescription("Fire Village resident");
        
        NPC frigs = new NPC("Frigs", "Hey girls. How's it going? I hope you're excited for the festival! I think we'll have a lot of fun tonight. I hope to see you guys later -- especially you Ana.", true);
        frigs.setDescription("Ice Village resident");
        
        NPC ninlil = new NPC("Ninlil", "(*mumbles* Not these two...) What do you guys want? Can't you see I'm busy? I'm trying to help with decorations.", true);
        ninlil.setDescription("Wind Village resident");
        
//        gift = Item.getHealingItem("Half Cake");
//        NPC fultra = new NPC("Fultra", "Gaea, Ana! It's great to see you guys. We've got plenty of food and cake here, so tuck in!", gift);
//        fultra.setGiveGiftMessage("I'll see you guys later. Love you Gaea!");
//        fultra.setDescription("Electric Village resident and Gaea's boyfriend");
        
        gift = Item.getBuffItem("Green Bean");
        NPC pheu = new NPC("Pheu", "You know my nephew Calmus, right? Do me a favor and keep being good friends with him. He may seem okay, but he's still struggling with the death of his parents.", gift, 2, false);
        pheu.setGiveGiftMessage("Take these in advance as a thank you.");
        pheu.setDescription("Fire Village Resident and Calmus' aunt");
        
        NPC ilvent = new NPC("Ilvent", "I hope you two are doing well! Tonight is going to be a great night. The music, the food, the people... Ahh, Pulchra is the place to be, huh?", true);
        ilvent.setDescription("Wind Village resident and Ninlil's training partner");
        
        gift = Item.getBuffItem("Blue Bean");
        NPC clairdra = new NPC("Clairdra", "I'm so proud of my grandson, Fultra. Thank you two for being there for him. And thank you, Gaea, for being a great partner for him too.", gift, 2, false);
        clairdra.setGiveGiftMessage("I have some Blue Beans for you. You know their Fultra's favorites.");
        clairdra.setDescription("Electric Village resident and Fultra's grandma");
        
        NPC verg = new NPC("Verg", "Hey Ana, Gaea. I just wanted to let you know that Frigs really appreciates you guys. Thanks for being there for my brother.", false);
        verg.setDescription("Ice Village resident and Frigs' older brother");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(calmus);
        people.add(frigs);
        people.add(ninlil);
//        people.add(fultra);
        people.add(pheu);
        people.add(ilvent);
        people.add(clairdra);
        people.add(verg);
        
        //----------------------------------------------------------------------
        Shop s = new Shop(Item.getAllItems());
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(12, 31);
        Village v = new Village("Zoni Village", "The \"captial\" of Pulchra. It's located at the center of the island and has the densest population with a variety of residents.", people, 10, 2473, c);
        v.setShop(s);
        return v;
    }
    
    private Village createZoniVillage2()
    {
        Coordinate c = new Coordinate(12, 31);
        return new Village("Zoni Village", "What was once a bustling, animated village is now a desolated area. No Pulchrians are here anymore...", new ArrayList<>(), 25, 0, c);
    }    
    
    private Village createWindVillage()
    {   
        NPC ilvent = new NPC("Ilvent", "It's good to see you guys. Things... things have been rough since the calamity... We lost so many people... You're looking for Ninlil? She's at the Tempest Tower.", true);
        ilvent.setDescription("Wind Village resident and Ninlil's training partner");
        
        NPC oura = new NPC("Oura", "(*sniff*) why is this happening... oh goodness... why?", false);
        oura.setDescription("Wind Village resident and newly Widowed");
        
        Item gift = Item.getBuffItem("Purple Bean");
        NPC tem = new NPC("Tem", "If you guys are here to help, we appreciate it, but I don't think there's much you can do for us.", gift, 3, false);
        tem.setGiveGiftMessage("We don't have much right now, but I think you could use this better than us.");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(ilvent);
        people.add(oura);
        people.add(tem);
        
        //----------------------------------------------------------------------
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.getHealingItem("Cake Slice"));
        items.add(Item.getHealingItem("Half Cake"));
        items.add(Item.getHealingItem("Apple Pie"));
        items.add(Item.getHealingItem("Choco Bar"));
        items.add(Item.getBuffItem("Red Bean"));
        items.add(Item.getBuffItem("Purple Bean"));
        items.add(Item.getBuffItem("Blue Bean"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(11, 71);
        Village v = new Village("Wind Village", "Located on the most eastern area of Pulchra, the residents here are known for their high esteem and have the most prestige out of any other village.", people, 12, 312, c);
        v.setShop(s);
        return v;
    }
    
    private Village createFireVillage()
    {
        NPC lyra = new NPC("Lyra", "Brother! Grandma needs your help. If you can defeat <Enemy Boss Name Here>, that should help. Please! She's not feeling well...", true);
        lyra.setDescription("Fire Village resident and Calmus' little sister");
        
        NPC volca = new NPC("Vulca", "(*cough*) Hello, grandson, Ana, Gaea, Ninlil... (*cough cough*)", true);
        volca.setDescription("Fire Village resident and Calmus' grandmother");
        
        NPC mimi = new NPC("Mimi", "I normally live in the Water Village, but things seem worse here than back at home, so I'm here to help. Are you here to help too?", false);
        mimi.setDescription("Fire Village resident");
        
        Item gift = Item.getBuffItem("Red Bean");
        NPC hitaka = new NPC("Hitaka", "That Irwin guy... why did he do all of this...?! So many villages have been destroyed because of him.", gift, false);
        hitaka.setDescription("Fire Village resident");
        hitaka.setGiveGiftMessage("I'm not much of a fighter, so please, take this. If you can do something about all this... do it.");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(lyra);
        people.add(volca);
        people.add(mimi);
        people.add(hitaka);
        
        //----------------------------------------------------------------------
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.getHealingItem("Triple Chocolate Meltdown"));
        items.add(Item.getHealingItem("Apple Pie"));
        items.add(Item.getHealingItem("Cheesecake"));
        items.add(Item.getHealingItem("Whole Cake"));
        items.add(Item.getHealingItem("Lasagna"));
        items.add(Item.getBuffItem("Red Bean"));
        items.add(Item.getBuffItem("Orange Bean"));
        items.add(Item.getBuffItem("Green Bean"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(5, 53);
        Village v = new Village("Fire Village", "Located in the northeast of Pulchra, the Fire Village has a group of people rich in culture, community, and humblness.", people, 15, 201, c);
        v.setShop(s);
        return v;
    }
    
    private Village createIceVillage()
    {
        // Once player enters village, can find summit of mountain
        
        NPC zeno = new NPC("Zeno", "Woah! I'm surprised you all made it through the mountain. The weather is at its worst this time of the year. Well, welcome. Like others, we don't have much left, but some of us are holding on to heope.", false);
        zeno.setDescription("Ice Village Resident");
        
        NPC ligian = new NPC("Ligian", "If you want Frigs, he's at the summit of the mountain. He's been grieving a lot lately.", false);
        ligian.setDescription("Ice Village Resident");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(zeno);
        people.add(ligian);
        
        //----------------------------------------------------------------------
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.getHealingItem("Choco Bar"));
        items.add(Item.getHealingItem("Apple Pie"));
        items.add(Item.getHealingItem("Cheesecake"));
        items.add(Item.getHealingItem("Lasagna"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(2, 31);
        Village v = new Village("Ice Village", "Near the peak of Zoni Mountain, the Ice Village hosts a group of nonchalant yet powerful and honorable people.", people, 19, 56, c);
        v.setShop(s);
        return v;
    }
    
    private Village createElectricVillage()
    {
        NPC clairdra = new NPC("Clairdra", "Oh. Hello everyone. No, I don't know where Fultra is. We beleive he died during the festival. No one has seen him since... Oh, my poor grandson...", true);
        
        NPC tonnerre = new NPC("Tonnerre", "\"Fearless Thunder...\" Our village hasn't been the same without him. Gaea... I'm so sorry for your loss.", false);
        
        Item gift = Item.getHealingItem("Half Cake");
        NPC san = new NPC("San", "Are you guys okay? How are your villages?", gift, false);
        san.setGiveGiftMessage("I hope this helps, even if just a little.");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(clairdra);
        people.add(tonnerre);
        people.add(san);
        
        //----------------------------------------------------------------------
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.getHealingItem("Apple Pie"));
        items.add(Item.getBuffItem("Red Bean"));
        items.add(Item.getBuffItem("Orange Bean"));
        items.add(Item.getBuffItem("Blue Bean"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(10, 9);
        Village v = new Village("Electric Village", "Located to the east, the Electric Village is known for having the strongest fighters on Pulchra.", people, 23, 101, c);
        v.setShop(s);
        return v;
    }
    
    private Wilderness createPurityBeach()
    {
        Coordinate c = new Coordinate(22, 36);
        Wilderness purityBeach = new Wilderness("Purity Beach", "A beach to the south of the Water Village. Its calm waves and salty air help soothe the mind.", 1, c);
        purityBeach.addLocalElement("Water");
        purityBeach.addLocalElement("Earth");
        purityBeach.addLocalElement("Wind");
        purityBeach.setUnlocked(true);
        purityBeach.setIsExplored();
        return purityBeach;
    }
    
    private Wilderness createOpiconForest()
    {
        Coordinate c = new Coordinate(19, 35);
        Wilderness opiconForest = new Wilderness("Opicon Forest", "A luscious forest with towering trees, diverse wildlife, and a variety of vegetation.\nIt spans between the Earth and Water villages.", 6, c);
        opiconForest.addLocalElement("Earth");
        opiconForest.addLocalElement("Water");
        opiconForest.addLocalElement("Wind");
        opiconForest.addLocalElement("Electric");
        return opiconForest;
    }
    
    private Wilderness createTempestTower()
    {
        Coordinate c = new Coordinate(18, 68);
        Wilderness tempestTower = new Wilderness("Tempest Tower", "An ancient tower the pierces the sky. The top is surrounded by clouds in a cresent shape.", 13, c);
        tempestTower.addLocalElement("Wind");
        tempestTower.addLocalElement("Ice");
        return tempestTower;
    }
    
    private Wilderness createMountVolcan()
    {
        Coordinate c = new Coordinate(4, 48);
        Wilderness mountVolcan = new Wilderness("Mount Volcan", "An inactive volcano. Fire Village residents come here frequently to train and hone their abilities,", 16, c);
        mountVolcan.addLocalElement("Fire");
        mountVolcan.addLocalElement("Earth");
        mountVolcan.addLocalElement("Wind");
        return mountVolcan;
    }
    
    private Wilderness createMountZoni()
    {
        Coordinate c = new Coordinate(4, 31);
        Wilderness mountZoni = new Wilderness("Mount Zoni", "A large mountain with a frigid summit. During certain times of the year, the mountain expereinces whiteout blizzards.", 18, c);
        mountZoni.addLocalElement("Ice");
        mountZoni.addLocalElement("Wind");
        mountZoni.addLocalElement("Earth");
        mountZoni.addLocalElement("Electric");
        return mountZoni;
    }
    
    private Wilderness createForlornDesert()
    {
        Coordinate c = new Coordinate(9, 15);
        Wilderness forlornDessert = new Wilderness("Forlorn Desert", "A dry and sandy area. It's covered in a dense fog that almost seems sentient. Those that go in rarely come out...", 22, c);
        forlornDessert.addLocalElement("Electric");
        forlornDessert.addLocalElement("Earth");
        forlornDessert.addLocalElement("Wind");
        forlornDessert.addLocalElement("Fire");
        return forlornDessert;
    }
    
    private void checkForCutscene()
    {
        if(currentLocation.getName().equals("Opicon Forest") && (!currentLocation.isExplored()))
        {
            objective.update();
            
            team.add(MainGame.makeGaea());
            team.add(MainGame.makeFultra());
            MainGame.setPlayerTeam(team);
        }
        else if(currentLocation.getName().equals("Water Village") && (!currentLocation.isExplored()))
        {
            objective.update();
        }
        else if(currentLocation.getName().equals("Earth Village") && (!currentLocation.isExplored()))
        {
            Cutscene.earthVillageCutscene(isTesting);
            objective.update();
        }
        
        currentLocation.setIsExplored();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    Create a new class called Objective in the Game package. It will have a lot of boolean field variables that 
    check and follow the player's progress. It will contain an ArrayList of these booleans similar to how
    this class contains an ArrayList of locations. The boolean will be listed in two different lists:
        completed and uncompleted
    
    For example: the first objective will be getting to Opicon Forest. A boolean will represent that as "arrivedOpiconForest"
    
    With each objective that's met, it will be updated here in the Game class
    
    For example: made it to Opicon Forest. Call updateObjective() which sets arriviedOpiconForest to be true and adds it to the
    completed list. Now the next objective is the first boolean in the uncompleted list (a reference to it can be held like
    "nextLocation" is an Object reference in this class)
    
    This allows for the player to not progress the story unless certain levels are attained AND NPCs are talked to since
    each boolean will be checking for a different condition
    
    For example: made it to water village is completed. Current objective: talk to merda. The class will now only check if Merda
    has been talked to. The talkToPeople() method in this class will check if she was talked to. If so, it'll update the 
    objective class from there. The next objective is to then get to level 9 to access the Earth Village. Since this is the case,
    an immediate check is necessary to see if the player is already at level 9. If so, unlock it like normal. If not, 
    the player will simply play until they're at that level and the Earth Village will be unlocked. 
    
    Essentially, the game does not progress unless the objective class does, providing a cleaner way of tracking
    the progress made. It also removes a lot of booleans from this class. 
    
    **If one objective is not complete, the objective class will not check for another until it's completed.**
    **Therefore, the game CANNOT go on until the player meets the requirements**
    */
    
    
    
    
    
}