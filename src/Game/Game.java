package Game;

import Battle.Attack;
import Battle.BeachTutorialBattle;
import Battle.BossBattle;
import Battle.BuffAttack;
import Battle.DebuffAttack;
import Battle.EarthEnemy;
import Battle.ElectricEnemy;
import Battle.FireEnemy;
import Battle.IceEnemy;
import Battle.Inventory;
import Battle.Item;
import Battle.NormalBattle;
import Battle.OffensiveAttack;
import Battle.OpiconTutorialBattle;
import Battle.Player;
import Battle.PlayerClass;
import Battle.RESIBattle;
import Battle.RESITutorialBattle;
import Battle.SingleHealingAttack;
import Battle.TeamHealingAttack;
import Battle.WaterEnemy;
import Battle.WindEnemy;
import Exploration.Coordinate;
import Exploration.Location;
import Exploration.Map;
import Exploration.NPC;
import Exploration.Shop;
import Exploration.Town;
import Exploration.Wilderness;
import Utilites.MenuHelper;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A class for creating Game objects to start the game.
 * @author Ian King
 */
public class Game implements java.io.Serializable
{
    private boolean gameStarted;

    private boolean finalBossDefeated;
    private boolean beachTutorialDone;
    private boolean forestTutorialDone;
    private boolean resiTutorialDone;
    private boolean recentBattleWon;
    
    private static boolean testing;
    
    private boolean towerBossAttempted;
    private boolean volcanBossAttempted;
    private boolean summitBossAttempted;
    
    // Endgame boss battle booleans  
    private boolean fultraBossAttempted;
    private boolean fultraBossDefeated;
    private boolean irwinBossAttempted;
    private boolean irwinBossDefeated;
    private boolean finalBossAttempted;
    private boolean resiTutorialAttempted;
    
    private Objective objective;
    private Location currentLocation;
    private Location nextLocation;
    private ArrayList<Location> knownLocations = new ArrayList<>();
    private ArrayList<Location> remainingLocations = new ArrayList<>();
    private ArrayList<Player> team = new ArrayList<>(6);
    private static int gold;
    private static boolean inSecondPhase;
    private static boolean defeatedOmegaBoss;
    private int pulchraPopulation = 201704;
    private Map map = new Map();
    
    private static Inventory inventory = new Inventory();
    
    public Game(boolean isTesting)
    {
        /*
        Town names:
            Aquammoda (Aqua + accomodating [people pleasing])
            Degon (Dirt + egocentric)
            Aerogan (Aero + arrogant)
            Infol (Inferno + colossus)
            Solice (Solus [alone or unaccompanied] + Ice)
            Elerric  (Electric + terror)
        */
        
        
        testing = isTesting;
        populateAllLocations();
        knownLocations.add(remainingLocations.remove(0));
        
        objective = new Objective();
        
        
        team.add(MainGame.makeAnahita());
        currentLocation = knownLocations.get(0);
        nextLocation = remainingLocations.remove(0); 
        gold = 0; 
        gameStarted = false;
        

        map = new Map();
    }

    public boolean isFinalBossDefeated() {
        return finalBossDefeated;
    }

    public boolean getGameStarted() {return gameStarted;}

    public void setGameStarted(boolean value) {gameStarted = value;}

    public void setFinalBossDefeated(boolean finalBossDefeated) {
        this.finalBossDefeated = finalBossDefeated;
    }

    public boolean isBeachTutorialDone() {
        return beachTutorialDone;
    }

    public void setBeachTutorialDone(boolean beachTutorialDone) {
        this.beachTutorialDone = beachTutorialDone;
    }

    public boolean isForestTutorialDone() {
        return forestTutorialDone;
    }

    public void setForestTutorialDone(boolean forestTutorialDone) {
        this.forestTutorialDone = forestTutorialDone;
    }

    public boolean isRecentBattleWon() {
        return recentBattleWon;
    }

    public void setRecentBattleWon(boolean recentBattleWon) {
        this.recentBattleWon = recentBattleWon;
    }

    public boolean isTowerBossAttempted() {
        return towerBossAttempted;
    }

    public void setTowerBossAttempted(boolean towerBossAttempted) {
        this.towerBossAttempted = towerBossAttempted;
    }

    public boolean isVolcanBossAttempted() {
        return volcanBossAttempted;
    }

    public void setVolcanBossAttempted(boolean volcanBossAttempted) {
        this.volcanBossAttempted = volcanBossAttempted;
    }

    public boolean isSummitBossAttempted() {
        return summitBossAttempted;
    }

    public void setSummitBossAttempted(boolean summitBossAttempted) {
        this.summitBossAttempted = summitBossAttempted;
    }

    public boolean isFultraBossAttempted() {
        return fultraBossAttempted;
    }

    public void setFultraBossAttempted(boolean fultraBossAttempted) {
        this.fultraBossAttempted = fultraBossAttempted;
    }

    public boolean isFultraBossDefeated() {
        return fultraBossDefeated;
    }

    public void setFultraBossDefeated(boolean fultraBossDefeated) {
        this.fultraBossDefeated = fultraBossDefeated;
    }

    public boolean isIrwinBossAttempted() {
        return irwinBossAttempted;
    }

    public void setIrwinBossAttempted(boolean irwinBossAttempted) {
        this.irwinBossAttempted = irwinBossAttempted;
    }

    public boolean isIrwinBossDefeated() {
        return irwinBossDefeated;
    }

    public void setIrwinBossDefeated(boolean irwinBossDefeated) {
        this.irwinBossDefeated = irwinBossDefeated;
    }

    public boolean isFinalBossAttempted() {
        return finalBossAttempted;
    }

    public void setFinalBossAttempted(boolean finalBossAttempted) {
        this.finalBossAttempted = finalBossAttempted;
    }

    public boolean isResiTutorialAttempted() {
        return resiTutorialAttempted;
    }

    public void setResiTutorialAttempted(boolean resiTutorialAttempted) {
        this.resiTutorialAttempted = resiTutorialAttempted;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

    public ArrayList<Location> getRemainingLocations() {
        return remainingLocations;
    }

    public void setRemainingLocations(ArrayList<Location> remainingLocations) {
        this.remainingLocations = remainingLocations;
    }

    public static boolean isInSecondPhase() {
        return inSecondPhase;
    }

    public static void setInSecondPhase(boolean inSecondPhase) {
        Game.inSecondPhase = inSecondPhase;
    }

    public static boolean isDefeatedOmegaBoss() {
        return defeatedOmegaBoss;
    }

    public static void setDefeatedOmegaBoss(boolean defeatedOmegaBoss) {
        Game.defeatedOmegaBoss = defeatedOmegaBoss;
    }

    public int getPulchraPopulation() {
        return pulchraPopulation;
    }

    public void setPulchraPopulation(int pulchraPopulation) {
        this.pulchraPopulation = pulchraPopulation;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    
    public boolean inSecondPhase() {return inSecondPhase;}
    
    public boolean getRecentBattleResult() {return recentBattleWon;}
    
    public Location getCurrentLocation() {return currentLocation;}
    
    public void setCurrentLocation(Location l) {currentLocation = l;}

    public void setKnownLocations(ArrayList<Location> knownLocations) {
        this.knownLocations = knownLocations;
    }
    
    public static boolean isTesting() {return testing;}
    
    public static boolean getSecondPhase() {return inSecondPhase;}
    
    public static boolean getDefeatedOmega() {return defeatedOmegaBoss;}
    
    public ArrayList<Player> getTeam() {return team;}

    public void setTeam(ArrayList<Player> team) {
        this.team = team;
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static void setInventory(Inventory inventory) {
        Game.inventory = inventory;
    }
    
    public static int getGold() {return gold;}

    public static void setGold(int gold) {
        Game.gold = gold;
    }
    
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
        MainGame.clearScreen();

        if(!gameStarted)
        {
            gameOpening();
        }
        
        instatiations();
//        currentObjective = "Get to Opicon Forest (Required level: " + nextLocation.getRequiredLevel() + ")";
        
        while(true)
        {
            processInput();
        }
    }
    
    private void populateAllLocations()
    {
        remainingLocations.add(createPurityBeach());
        remainingLocations.add(createOpiconForest());
        remainingLocations.add(createAquammoda());
        remainingLocations.add(createDegon());
        remainingLocations.add(createZoniCity());
        remainingLocations.add(createAerogan());
        remainingLocations.add(createTempestTower());
        remainingLocations.add(createInfol());
        remainingLocations.add(createMountVolcan());
        remainingLocations.add(createMountZoni());
        remainingLocations.add(createSolice());
        remainingLocations.add(createMountZoniSummit());
        remainingLocations.add(createForlornCave());
        remainingLocations.add(createElerric());
        remainingLocations.add(createZoniCity2());
        
//        if(testing)
//        {
//            remainingLocations.get(0).setIsExplored();
//            remainingLocations.get(1).setIsExplored();
//            remainingLocations.get(2).setIsExplored();
//            remainingLocations.get(3).setIsExplored();
//        }
    }
    
    private void gameOpening()
    {
        gameStarted = true;
        Cutscene.opening();
        introduceNewLocation(); // introduces Purity Beach
        MainGame.promptToEnter();
    }
    
    private void displayInfo()
    {
        objective.printCurrentObjective();
        MainGame.println("Current Location: " + currentLocation.getName());
        MainGame.println("Current Gold: " + String.format("%,d", getGold()) + " G");
        MainGame.printlnln("Pulchra Population: " + String.format("%,d", pulchraPopulation));
    }
    
    public void processInput()
    {
        displayInfo();
        
        MainGame.println("What would you like to do?");
        String message = "\t1) Travel\n\t2) ";
        int input = 0;
        
        if(currentLocation instanceof Town)
        {
            message += "Shop\n\t3) Talk to Townsfolk\n\t4) Search for Chest\n\t5) View Inventory\n\t6) More Options";
            input = MenuHelper.displayMenu(message, 1, 6);
            MainGame.clearScreen();

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
                    findCityChest();
                    break;
                case 5:
                    viewInventory();
                    break;
                case 6:
                    optionsMenu();
                    break;
            }
//            MainGame.waitForEnter();
        }
        // If the current location is of type Wildnerness
        else
        {
            wildernessOptions(message, input);
        }
        
        MainGame.promptToEnter();
    }
    
    /**
     * Helper method to help display the options for the Wilderness areas.
     * @param message 
     */
    private void wildernessOptions(String message, int input)
    {
        // If the requirements to start the boss battle aren't met, don't give the option.
        if(!((Wilderness)currentLocation).canDoBossBattle(team))
        {
            message += "Battle\n\t3) Search for Chest\n\t4) View Inventory\n\t5) More Options";
            input = MenuHelper.displayMenu(message, 1, 5);
            MainGame.clearScreen();

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
        }
        else
        {
            message += "Battle\n\t3) Boss Battle\n\t4) Search for Chest\n\t5) View Inventory\n\t6) More Options";
            input = MenuHelper.displayMenu(message, 1, 6);
            MainGame.clearScreen();
            
            switch(input)
            {
                case 1:
                    askForLocation();
                    break;
                case 2:
                    battle();
                    break;
                case 3:
                    bossBattle();
                    break;
                case 4:
                    findWildnernessChest();
                    break;
                case 5:
                    viewInventory();
                    break;
                case 6:
                    optionsMenu();
                    break;
            }
        }
    }
    
    private void introduceNewLocation()
    {
        MainGame.promptToEnter();
        MainGame.printWithRandomLetters("Welcome to " + currentLocation.getName() + ":");
        MainGame.printlnln("\n" + currentLocation.getDescription());
    }
    
    private void transition(Location newLocation)
    {
        currentLocation.travelDescription(currentLocation, newLocation, team);
        
        updateAndPrintMap(newLocation);
        
        currentLocation = newLocation;
        
        // Now using currentLocation as the newly arrived to Location
        if(!currentLocation.isExplored())
        {
            introduceNewLocation();
            if(objective.completedTask(this))
            {
                objective.update(this);
            }
//            objective.update(this);
//            objective.updateByLevel(Player.highestPlayerLV(team));
//            currentLocation.setIsExplored();
        }
        
        switch (currentLocation.getName()) 
        {
            case "Tempest Tower":
                {
                    BossBattle battle = new BossBattle(((Wilderness)currentLocation).makeNinlilBoss(), makePlayerTeam("Anahita"));
                    ((Wilderness)currentLocation).setBossBattle(battle, 14);
                }
                break;
            case "Mount Volcan":
                {
                    BossBattle battle = new BossBattle(((Wilderness)currentLocation).makeOmegaBoss(), team);
                    ((Wilderness)currentLocation).setBossBattle(battle, 17);
                }
                break;
            case "Mount Zoni Summit":
                {
                    BossBattle battle = new BossBattle(((Wilderness)currentLocation).makeFrigsBoss(), makePlayerTeam("Ninlil"));
                    ((Wilderness)currentLocation).setBossBattle(battle, 21);
                }
                break;
            case "Zoni City": // Zoni City in second phase
                if(inSecondPhase && !fultraBossDefeated)
                {
                    // Only make the boss battle when in second phase
                    BossBattle battle = new BossBattle(((Wilderness)currentLocation).makeFultraBoss((Wilderness)currentLocation), team);
                    ((Wilderness)currentLocation).setBossBattle(battle, 27);
                }
                break;
        }
        
        // Check if the next objective of getting to a certain level is met immediately
        if(objective.completedTask(this))
        {
            objective.update(this);
        }

        checkForCutscene();
    }
    
    private void updateAndPrintMap(Location newLocation)
    {
        map.updateMap(currentLocation, newLocation);
        map.printMap();
        // MainGame.promptToEnter();
        // MainGame.waitForEnter();
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
        
        // if(input == numOfOptions)
        // {
        //     MainGame.clearScreen();
        //     processInput();
        // }
        if(knownLocations.get(input - 1) == currentLocation)
        {
            System.out.println("");
            MainGame.printlnln("Anahita: Stop messing around! We're already at " + currentLocation + "!");
        }
        else if(input != numOfOptions)
        {
            Location newLocation = knownLocations.get(input - 1);
            transition(newLocation);
        }
    }
    
    private void activateShop()
    {
        MainGame.clearScreen();
        
        if(inSecondPhase && currentLocation.getName().equals("Zoni City"))
        {
            MainGame.printlnlnWait("The shop was destroyed during the invasion...", 25, 2000);
            return;
        }
        
        Shop shop = ((Town)currentLocation).getShop();
        shop.startShopping();
    }
    
    private void talkToPeople()
    {
        Town town = ((Town)currentLocation);
        
//        if(testing && town.getName().equals("Zoni City"))
//        {
//            town.getVillagePeople().forEach(p -> {
//                p.setTalkedTo(true);
//            });
//        }
        
        town.talkToPeople();
        
        /*
        Since the player sometimes unlocks a new location by talking to certain people, a check will occur here
        for if they talked to that person. This is to ensure that if the player is already at a specific level and
        hasn't talked to the specific person, after doing so, the next location is unlocked immediately.
        */
        
        // The first phase will only update the objective and not unlock the next location
        if(!inSecondPhase && talkedToSpecificPerson() && objective.completedTask(this))
        {
            // MainGame.clearScreen();
            objective.update(this);
            
            // After the objective updates, check to see if the next one was completed preemptively
            if(objective.completedTask(this))
            {
                objective.update(this);
            }
        }
        // If npc has been talked to and the objective successfully updated, unlock the next location. Only in second phase
        else if(talkedToSpecificPerson() && objective.completedTask(this))
        {   
            // MainGame.promptToEnter();
//            unlockNextLocation();
            objective.update(this);
//            objective.updateByNpc(town);
//            checkForNextLocation();
        }
        // This check is specifically for when the player talks to every NPC in the Zoni City in the first phase.
        else if(!inSecondPhase && town.getName().equals("Zoni City") && town.hasTalkedToEveryone())
        {           
            // Play the cutscene and add the new characters
            Cutscene.invasion();        
            team.add(MainGame.makeCalmus());
            team.add(MainGame.makeFrigs());
            team.add(MainGame.makeNinlil());

            /*
             * If the player loses the fight, they are forced to do it until they win. 
             * If the player loses, the game will give them a 2 free Apple Pies.
             */
            while(!recentBattleWon)
            {
                // If the player loses the tutorial, skip the cutscene. Otherwise, play it
                // if(!resiTutorialAttempted)
                // {
                // Start tutorial RESI Battle here
                RESITutorialBattle rtb = town.makeRESITutorial(team);
                rtb.start(gold, recentBattleWon);
            
                // If the battle is won, the player can move on, and the next cutscene plays. The second phase starts here
                if(rtb.isWon())
                {
                    recentBattleWon = true;
                    if(objective.completedTask(this))
                    {
                        objective.update(this);
                    }
//                    objective.update(this);
//                    objective.updateByNpc(town);
                    startSecondPhase();
                }
                else
                {
                    MainGame.promptToEnter();

                    MainGame.printlnln("You may have lost, but 2 Apple Pies appeared in your inventory!");
                    inventory.addTo(Item.getHealingItem("Apple Pie"), 2);
                    MainGame.printlnln("Try again and defend Pulchra!");

                    MainGame.promptToEnter();
                }
            }
        }
    }
    
    private void startSecondPhase()
    {
        // if(!testing)
        // {
            Cutscene.invasion2();
        // }   
        
        inSecondPhase = true;
        
        // Reduces the population number by an arbitrary amount to show that the world has changed
        pulchraPopulation = (pulchraPopulation / 3) - 777;
        
        // Adds new Aquammoda and Degon with less NPCs and adds it to the known locations.
        knownLocations.set(2, createAquammoda2());
        knownLocations.set(3, createDegon2());

        // Update the map to remove the 'current location' marker from Zoni City
        map.updateMap(currentLocation, knownLocations.get(2));
        
        // Removes Zoni City from the known locations. The player can no longer go there until unlocked again.
        knownLocations.remove(knownLocations.size() - 1);

        // Sets Aquammoda as current location
        currentLocation = knownLocations.get(2);

        // Removes Frigs and Ninlil from the player's team of characters
        removePlayer("Fultra");
        removePlayer("Frigs");
        removePlayer("Ninlil");
        
        // if(!testing)
        // {
            Cutscene.postInvasion();
        // }

        // Check if the next objective of getting to a certain level is met immediately
        if(objective.completedTask(this))
        {
            objective.update(this);
        }
    }
    
    /**
     * Creates a battle sequence for the player to follow.
     */
    private void battle()
    {
        MainGame.clearScreen();
        
        // If the player is in Purity Beach and they haven't done its tutorial, do it
        if(!beachTutorialDone && currentLocation.getName().equals("Purity Beach"))
        {
            BeachTutorialBattle battle = ((Wilderness)currentLocation).makeBeachTutorial(team.get(0));
            battle.start(gold, recentBattleWon);
            
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
            battle.start(gold, recentBattleWon);
            
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
            battle.start(gold, recentBattleWon);
        }
        // Second phase - 60% normal battles, 40% RESI battles
        else
        {
            chooseBattle(new Random().nextInt(100));
        }
        
        if(objective.completedTask(this))
        {
            objective.update(this);
        }
            
//        checkForNextLocation();
    }
    
    private void chooseBattle(int chance)
    {
        if(chance >= 0 && chance < 59)
        {
            NormalBattle battle = ((Wilderness)currentLocation).makeNormalBattle(team);
            battle.start(gold, recentBattleWon);
        }
        else
        {
            RESIBattle battle = ((Wilderness)currentLocation).makeRESIBattle(team);
            battle.start(gold, recentBattleWon);
        }
    }
    
    private void bossBattle()
    {   
        MainGame.clearScreen();
        
        switch (currentLocation.getName()) 
        {
            case "Tempest Tower":
                // If the Ninlil boss hasn't been attempted, play the cutscene. If it has already, don't.
                if(!towerBossAttempted)
                {
                    Cutscene.foundNinlil();
                }   
                
                Wilderness tempestTower = ((Wilderness)currentLocation);
                tempestTower.getBossBattle().start(gold, recentBattleWon);
                towerBossAttempted = true;
                
                // Update objective if battle is won
                // objective.updateByBattleResult(recentBattleWon);
               objective.updateByBattleResult(tempestTower.getBossBattle().isWon());
                
                // If the player wins the boss fight, remove it from Tempest Tower.
                if(tempestTower.getBossBattle().isWon())
                {
                    Cutscene.defeatedNinlil();
                    tempestTower.removeBossBattle();
                    team.add(MainGame.makeNinlil());
                }   
                break;
            case "Mount Volcan":
                // If the R.E.S.I. Omega boss hasn't been attempted, play the cutscene. If it has already, don't.
                if(!volcanBossAttempted)
                {
                    Cutscene.foundOmega();
                }   
                
                Wilderness volcan = ((Wilderness)currentLocation);
                volcan.getBossBattle().start(gold, recentBattleWon);
                volcanBossAttempted = true;
                
                // Update objective if battle is won
                // objective.update(this);
               objective.updateByBattleResult(volcan.getBossBattle().isWon());
                
                // If the player wins the boss fight, remove it from Mount Volcan.
                if(volcan.getBossBattle().isWon())
                {
                    defeatedOmegaBoss = true;
                    Cutscene.defeatedOmega();
                    volcan.removeBossBattle();
                    
                    NPC lyra = ((Town)getLocation("Infol")).getNPC("Lyra");
                    lyra.setDialogue("Thank you so much for your help again! Good luck on your journey!");
                    lyra.setTalkedTo(false);
                    lyra.setHasCutscene(true);
                    
                    NPC vulca = ((Town)getLocation("Infol")).getNPC("Elder Vulca");
                    vulca.setDialogue("Bless you, grandson. And the rest of you too. Be careful on your journey, okay?");
                    vulca.setTalkedTo(false);
                }   
                break;
            case "Mount Zoni Summit":
                // If the Frigs boss hasn't been attempted, play the cutscene. If it has already, don't.
                if(!summitBossAttempted)
                {
                    Cutscene.foundFrigs();
                }   
                
                Wilderness summit = ((Wilderness)currentLocation);
                summit.getBossBattle().start(gold, recentBattleWon);
                summitBossAttempted = true;
                
                // Update objective if battle is won
                // objective.update(this);
               objective.updateByBattleResult(summit.getBossBattle().isWon());
                
                // If the player wins the boss fight, remove it from Mount Zoni Summit.
                if(summit.getBossBattle().isWon())
                {
                    Cutscene.defeatedFrigs();
                    summit.removeBossBattle();
                    team.add(MainGame.makeFrigs());
                }   
                
                map.updateMap(currentLocation, getLocation("Mount Zoni"));
                currentLocation = getLocation("Mount Zoni");
                break;
            case "Zoni City":
                cityBossFights();
                break;
            default:
                break;
        }
        
        if(objective.completedTask(this))
        {
            objective.update(this);
        }
    }
    
    private void cityBossFights()
    {
        Wilderness city = ((Wilderness)currentLocation);
        
        if(!fultraBossDefeated) // Fultra boss fight
        {   
            if(!fultraBossAttempted)
            {
                Cutscene.foundResiFultra();
            }
            
            city.getBossBattle().start(gold, recentBattleWon);
            fultraBossAttempted = true;

            // Update objective if battle is won
            // objective.update(this);
           objective.updateByBattleResult(city.getBossBattle().isWon());
            
            if(city.getBossBattle().isWon())
            {
                Cutscene.defeatedResiFultra();
                fultraBossDefeated = true;
                city.removeBossBattle();
                BossBattle battle = new BossBattle(((Wilderness)currentLocation).makeIrwinBoss(), team);
                ((Wilderness)currentLocation).setBossBattle(battle, 28);
            }
        }
        else if(fultraBossDefeated && !irwinBossDefeated) // Irwin Fight
        {   
            if(!irwinBossAttempted)
            {
                Cutscene.foundIrwin();
            }

            city.getBossBattle().start(gold, recentBattleWon);
            
            // Update objective if battle is won
            // objective.update(this);
           objective.updateByBattleResult(city.getBossBattle().isWon());
            
            if(city.getBossBattle().isWon())
            {
                Cutscene.defeatedIrwin();
                irwinBossDefeated = true;
                city.removeBossBattle();
                BossBattle battle = new BossBattle(((Wilderness)currentLocation).makeFinalBoss(), team);
                ((Wilderness)currentLocation).setBossBattle(battle, 30);
            }
        }
        else if(fultraBossDefeated && irwinBossDefeated) // Final boss fight
        {
            if(!finalBossAttempted)
            {
                Cutscene.foundFinalBoss();
                team.add(MainGame.makeResiFultra());
            }

            city.getBossBattle().start(gold, recentBattleWon);
            
            // Update objective if battle is won
            // objective.update(this);
            objective.updateByBattleResult(city.getBossBattle().isWon());
            
            if(city.getBossBattle().isWon())
            {
                Cutscene.defeatedFinalBoss();
                finalBossDefeated = true;
                city.removeBossBattle();
                Cutscene.credits();
            }
        }
        
        if(objective.completedTask(this))
        {
            objective.update(this);
        }
    }
    
    /**
     * Returns the String representing the NPCs name from the objective.
     * @return String
     */
    private String getNpcNameFromObjective()
    {
        Scanner scan = new Scanner(objective.toString());
        scan.useDelimiter("Talk to ");
        return scan.next();
    }
    
    private boolean talkedToSpecificPerson()
    {
        if(currentLocation instanceof Town)
        {
            Town town = (Town)currentLocation;
        
            // If in Zoni City, check if everyone was talked to
//            if(town.getName().equals("Zoni City"))
//            {
//                return town.hasTalkedToEveryone();
//            }
            
            // Otherwise, check if the specific NPC was talked to 
            if(town.getNPC(getNpcNameFromObjective()) == null)
            {
                return false;
            }

            return town.getNPC(getNpcNameFromObjective()).hasBeenTalkedTo();
        }
        
        return false;
        
        // Chain of if statements look for if a specific NPC was talked to at the location
//        if(town.getName().equals("Aquammoda") && town.getNPC("Merda").hasBeenTalkedTo() )
//        {
//            talkedToMerda = true;
//            return true;
//        }
//        else if(town.getName().equals("Degon") && town.getNPC("Fleur").hasBeenTalkedTo())
//        {
//            talkedToFleur = true;
//            return true;
//        }
//        else if(town.getName().equals("Aerogan") && town.getNPC("Elder Nu").hasBeenTalkedTo())
//        {
//            talkedToElderNu = true;
//            
//            // Unlock Tempest Tower
//            MainGame.clearScreen();
//            nextLocation.setUnlocked(true);
//            locationUnlocked();
//            
//            return true;
//        }
//        else if(town.getName().equals("Infol") && town.getNPC("Elder Vulca").hasBeenTalkedTo())
//        {
//            talkedToElderVulca = true;
//            
//            // Unlock Mount Volcan
//            MainGame.clearScreen();
//            nextLocation.setUnlocked(true);
//            locationUnlocked();
//            
//            return true;
//        }
//        else if(town.getName().equals("Infol") && town.getNPC("Lyra").hasBeenTalkedTo() && defeatedOmegaBoss)
//        {
//            talkedToLyra = true;
//            
//            MainGame.clearScreen();
//            nextLocation.setUnlocked(true);
//            locationUnlocked();
//            
//            return true;
//        }
//        else if(town.getName().equals("Solice") && town.getNPC("Elder Zeno").hasBeenTalkedTo())
//        {
//            talkedToElderZeno = true;
//            
//            // Unlock Mount Zoni Summit
//            MainGame.clearScreen();
//            nextLocation.setUnlocked(true);
//            locationUnlocked();
//            
//            return true;
//        }
//        else if(town.getName().equals("Elerric") && town.getNPC("Elder Clairdra").hasBeenTalkedTo())
//        {
//            talkedToElderClairdra = true;
//            
//            MainGame.clearScreen();
//            nextLocation.setUnlocked(true);
//            locationUnlocked();
//            
//            return true;
//        }
//        
//        
//        return false;
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
        
        
        
        
        Town town = (Town)currentLocation;
        
        /*
        If the player is at Aquammoda, and Merda's cutscene has been completed (which counts as talking to her), 
        the player can progress 
        */
        if(town.getName().equals("Aquammoda") && town.getNPC("Merda").hasBeenTalkedTo())
        {
            return true;
        }
        
        return false;
    }
    
    public void unlockNextLocation()
    {
        nextLocation.setUnlocked(true);
        locationUnlocked();
    }
    
    private void checkForNextLocation()
    {
        // If a specific objective hasn't been met, don't allow the player to get to the next area
//        if(!specificObjectiveMet())
//        {
//            return;
//        }
        
        // If the specified person is talked to in the objective, then check if the level is the minimum to unlock the next place
//        if(talkedToSpecificPerson())
//        {
//            int level = Player.highestPlayerLV(team);
            Location latestLocation = knownLocations.get(knownLocations.size() - 1);

            if(latestLocation.isExplored() && objective.completedTask(this))
            {
                if(objective.completedTask(this))
                {
                    objective.update(this);
                }
//                objective.update(this);
//                unlockNextLocation();
            }
            // If the level requirement is met and the objective updates, unlock next location. The latest location must be explored too
//            if(level >= nextLocation.getRequiredLevel() && latestLocation.isExplored())
//            {
//                unlockNextLocation();
//            }
//        }
        
            
        
//        if(nextLocation.isUnlocked())
//        {
//            locationUnlocked();
//        }
    }
    
    private void locationUnlocked()
    {
//        MainGame.clearScreen();
        // MainGame.promptToEnter();
        MainGame.printlnln("Congratulations! You can now travel to " + nextLocation.getName() + "!");
        
        // Removes the location from the overall ArrayList to the known ArrayList
        knownLocations.add(nextLocation);
        
        // While there are remaining locations, take the next one and assign it as the next location
        if(!remainingLocations.isEmpty())
        {
            nextLocation = remainingLocations.remove(0);
        }
            
//        nextLocation.setIsExplored();
//        levelUpOccurred = false;
    }
    
    private void findCityChest()
    {
        Town town = ((Town)currentLocation);
        town.findChest();
        // processInput();
    }
    
    private void findWildnernessChest()
    {
        Wilderness wildnerness = ((Wilderness)currentLocation);
        wildnerness.findChest();
//        processInput();
    }
    
    private void optionsMenu()
    {
        MainGame.println("What would you like to do?");
        String message = "\t1) View Team\n\t2) View Tutorials\n\t3) View World Map\n\t4) Save\n\t5) Set Text Speed\n\t6) Back";
        int input = MenuHelper.displayMenu(message, 1, 6);
        
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
                MainGame.selectTextSpeed(this);
                break;
            default:
                // MainGame.clearScreen();
                // processInput();
                break;
            case 1:
                viewTeam();
                break;
        }
    }
    
    private void viewTutorials()
    {
        MainGame.clearScreen();

        MainGame.println("Which tutorial would you like to review?");
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
            // case 5: 
            //     MainGame.clearScreen();
            //     optionsMenu();
            //     break;
            case 1: 
                MainGame.clearScreen();
                new TypeChart().printChart();
                break;
        }
    }
    
    private void save()
    {
        MainGame.clearScreen();
        
        MainGame.println("Would you like to save the game?");
        
        String message = "\t1) Yes\n\t2) No";
        
        int input = MenuHelper.displayMenu(message, 1, 2);
        
        switch(input)
        {
            case 1:
                // Saves the game using a SaveLoad object. Game data is written to a file
                MainGame.save();
                break;
            // case 2: 
            //     processInput();
            //     break;
        }
    }
    
    private void viewWorldMap()
    {
        map.printWithCurrentLocation(currentLocation);
    }
    
    private void viewInventory()
    {
        if(inventory.isEmpty())
        {
            MainGame.dialoguelnln("Anahita", "Aww, dang it! We don't have anything.");
        }
        else
        {
            MainGame.println("Inventory:\n");
            inventory.showInventory();
        }
    }
    
    public static void addToInventory(Item item, int quantity)
    {
        inventory.addTo(item, quantity);   
    }
    
    private void viewTeam()
    {
        MainGame.clearScreen();
        MainGame.println("Who would you like to view?");
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
            MainGame.clearScreen();
            optionsMenu();
        }
        else
        {
            MainGame.clearScreen();
            Player p = team.get(--input);
            viewPlayer(p);
        }
    }
    
    private void viewPlayer(Player player)
    {
        MainGame.clearScreen();
        MainGame.println("What would you like to do with " + player.getName() + "?");
        
        // The player has access to changing classes when they're in the second phase
        if(inSecondPhase)
        {
            String message = "\t1) View Stats\n\t2) Change Moves\n\t3) Change Class\n\t4) Back";
            int input = MenuHelper.displayMenu(message, 1, 4);
            MainGame.clearScreen();

            switch(input)
            {
                case 2:
                    changeMoves(player);
                    break;
                case 3:
                    changeClass(player);
                    break;
                case 4:
                    MainGame.clearScreen();
                    viewTeam();
                    break;
                default:
                    MainGame.printlnln("\n" + player.toOverallString());
                    break;
            }
        }
        else
        {
            String message = "\t1) View Stats\n\t2) Change Moves\n\t3) Back";
            int input = MenuHelper.displayMenu(message, 1, 3);

            switch(input)
            {
                case 2:
                    changeMoves(player);
                    break;
                case 3:
                    viewTeam();
                    break;
                default:
                    MainGame.printlnln("\n" + player.toOverallString());
                    break;
            }
        }
    }
    
    private void changeMoves(Player player)
    {
        MainGame.clearScreen();
        MainGame.printlnWait(player.getName() + "'s current moves:", 25, 500);
        
        // Prints out the four moves the character can use
        for(Attack attack : player.getCurrentAttacks())
        {
            printMove(attack);
        }
        
        MainGame.printlnWait(player.getName() + "'s other moves:", 25, 500);
        
        // Prints out the other two moves the character can use
        for(Attack attack : player.getOtherAttacks())
        {
            printMove(attack);
        }
        
        promptToChangeMove(player);
    }
    
    private void changeClass(Player player)
    {
        MainGame.clearScreen();
        MainGame.printlnlnWait(player.getName() + "'s current class:\n" + player.getPlayerClass().detailedString(), 5, 500);
        promptToChangeClass(player);
    }
    
    private void promptToChangeClass(Player p)
    {
        MainGame.printlnln(p.getName() + "'s other classes:");
        
        // Print available classes and their info
        for(PlayerClass pc : p.getOtherClasses())
        {
            MainGame.printlnln(pc.detailedString());
        }
        
        MainGame.promptToEnter();
        
        // Prompt player to select a choice.
        MainGame.println("What would you like to change " + p.getName() + "'s class to?");
        String message = "";
        int numOfOptions = 0;
        
        for(PlayerClass pc : p.getOtherClasses())
        {
            message += "\t" + ++numOfOptions + ") " + pc.getClassName() + "\n";
        }
        
        message += "\t" + ++numOfOptions + ") Go Back to View Team";
        
        int input = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        if(input == numOfOptions)
        {
            viewTeam();
        }
        else
        {
            switchClasses(p, input);
        }
    }
    
    private void switchClasses(Player p, int input)
    {
        PlayerClass currentClass = p.getPlayerClass();
        PlayerClass otherClass = p.getOtherClasses().get(--input);
        
        checkClasses(p, currentClass, otherClass);
        
        p.setPlayerClass(otherClass);
        
        p.getOtherClasses().set(input, currentClass);
        
        MainGame.promptToEnter();
        MainGame.printlnln("Class Change: Successful!\n\t" + currentClass.toString() +
                " -----------> " + otherClass.toString());

        MainGame.promptToEnter();
        
        MainGame.println(p.getName() + "'s new info:");
        
        // Prints out the updated character's info
        MainGame.printlnln(p.toOverallString());
        
        MainGame.waitForEnter();
        viewTeam();
    }
    
    /**
     * Helper method that checks if the class to change to isn't a Clerk class. If so, it prompts the player to update the 
     * character's attack list first IF their current attacks contain any healing moves.
     * @param p
     * @param input
     * @param currentClass
     * @param otherClass 
     */
    private void checkClasses(Player p, PlayerClass currentClass, PlayerClass wantedClass)
    {
        // If switching classes from Clerk to Clerk subclasss, return. Else, check for a healing move on the player object.
        if(currentClass.isClerk() && wantedClass.isClerk())
        {
            return;
        }
        
        for(int index = 0; index < p.getCurrentAttacks().size(); index++)
        {
            Attack attack = p.getCurrentAttacks().get(index);
            
            if(attack instanceof SingleHealingAttack || attack instanceof TeamHealingAttack)
            {
                MainGame.promptToEnter();
                MainGame.printlnln("WARNING: Only Clerk classes can use healing attacks. " + p.getName() + " is currently "
                        + "using " + attack.getName() + ".\nTo change " + p.getName() + "'s class from " + 
                        currentClass.getClassName() + " to " + wantedClass.getClassName() + ", please change " +
                        attack.getName() + " to a different attack.");
                MainGame.promptToEnter();
                
                // If a is a form of healing, prompt player to change attacks first.
                changeAttackForClass(p, index, attack);
            }
        }
    }
    
    /**
     * Takes the player through a sequence to change their attack list to properly change classes.
     * @param p
     * @param input
     * @param currentClass
     * @param wantedClass 
     */
    private void changeAttackForClass(Player p, int indexOfHealAttack, Attack attackToChange)
    {
        MainGame.println("Which move would you like to change " + attackToChange.getName() + " with? Please choose an attack "
                + "that is not a form of healing.");

        String message = "";
        int numOfOptions = 0;
        
        for(Attack a : p.getOtherAttacks())
        {
            message += "\t" + ++numOfOptions + ") " + a.getName() + "\n";
        }
        
        message += "\t" + ++numOfOptions + ") Go Back to Change Class";
        
        int indexOfOtherAttack = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        if(indexOfOtherAttack == numOfOptions)
        {
            changeClass(p);
        }
        else
        {
            switchAttackForClass(indexOfHealAttack, indexOfOtherAttack, p, attackToChange);
        }
    }
    
    /**
     * Finishes the process of changing attacks for changing a character's class.
     * @param indexOfHealAttack
     * @param indexOfOtherAttack
     * @param p 
     */
    private void switchAttackForClass(int indexOfHealAttack, int indexOfOtherAttack, Player p, Attack attackToChange)
    {
        // decrement the index of indexOfOtherAttack to get the right attack from the list
        Attack otherAttack = p.getOtherAttacks().get(--indexOfOtherAttack);
        if(otherAttack instanceof SingleHealingAttack || otherAttack instanceof TeamHealingAttack)
        {
            MainGame.printlnln("\nPlease select an attack that isn't a form of healing.");
            MainGame.promptToEnter();
            changeAttackForClass(p, indexOfHealAttack, attackToChange);
        }
        else
        {
            switchAttacksProcess(indexOfHealAttack, indexOfOtherAttack, p);
        }
    }
    
    private void printMove(Attack attack)
    {
        if(attack instanceof OffensiveAttack)
        {
            MainGame.printlnlnWait(((OffensiveAttack)attack).toString(), 5, 500);
        }
        else if(attack instanceof BuffAttack)
        {
            MainGame.printlnlnWait(((BuffAttack)attack).toString(), 5, 500);
        }
        else if(attack instanceof DebuffAttack)
        {
            MainGame.printlnlnWait(((DebuffAttack)attack).toString(), 5, 500);
        }
        else if(attack instanceof SingleHealingAttack)
        {
            MainGame.printlnlnWait(((SingleHealingAttack)attack).toString(), 5, 500);
        }
        else if(attack instanceof TeamHealingAttack)
        {
            MainGame.printlnlnWait(((TeamHealingAttack)attack).toString(), 5, 500);
        }
    }
    
    private void promptToChangeMove(Player p)
    {
        MainGame.println("\nWhich move would you like to change?");
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
        MainGame.promptToEnter();
        Attack wantedAttack = p.getCurrentAttacks().get(inputForFirstAttack - 1);
        MainGame.println("Which attack would you like to change " + wantedAttack.getName() 
                + " with?\nNOTE: Only Clerk classes can use attacks that heal.");
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
    
    /**
     * Helper method to be used in promptForOtherAttack to guide player in changing attacks of a character.
     * @param currentAttackInput
     * @param otherAttackInput
     * @param p 
     */
    private void switchAttacks(int currentAttackInput, int otherAttackInput, Player p)
    {
//        // Decrement inputs for use in the ArrayLists
//        --currentAttackInput;
//        --otherAttackInput;
//        
//        Attack currentAttack = p.getCurrentAttacks().get(currentAttackInput);
//        Attack otherAttack = p.getOtherAttacks().get(otherAttackInput);
//        
//        p.getCurrentAttacks().set(currentAttackInput, otherAttack);
//        p.getOtherAttacks().set(otherAttackInput, currentAttack);
//        
//        MainGame.printlnlnWait("\nMove Change: Successful!\n\t" + currentAttack.getName() +
//                " <----------> " + otherAttack.getName(), 25, 1500);
        
        // Decrement inputs for use in the ArrayLists
        --currentAttackInput;
        --otherAttackInput;
        
        Attack otherAttack = p.getOtherAttacks().get(otherAttackInput);
        if((otherAttack instanceof SingleHealingAttack && !p.getPlayerClass().isClerk()) 
                || (otherAttack instanceof TeamHealingAttack && !p.getPlayerClass().isClerk()))
        {
            MainGame.printlnln("\nYou cannot change " + p.getCurrentAttacks().get(currentAttackInput).getName() + " to " 
                    + otherAttack.getName() + " because " + p.getName() + "'s curernt class is " 
                    + p.getPlayerClass().getClassName() + ".\nPlease choose a different attack to switch to.");
            
            // Take player back to select the other attack they want to change. Increment for the sake of implementation
            promptForOtherAttack(p, ++currentAttackInput);
        }
        
        switchAttacksProcess(currentAttackInput, otherAttackInput, p);
        
        MainGame.promptToEnter();
        MainGame.printlnWait(p.getName() + "'s new, current moves:", 25, 1500);
        
        // Prints out the four moves the player now knows
        for(Attack attack : p.getCurrentAttacks())
        {
            printMove(attack);
        }
        
        MainGame.waitForEnter();
        viewTeam();
    }
    
    /**
     * Helper method used to complete the bulk of the process of changing an attack.
     * @param currentAttackInput
     * @param otherAttackInput
     * @param p 
     */
    private void switchAttacksProcess(int currentAttackInput, int otherAttackInput, Player p)
    {
        // **************************!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // THE COMMENTED OUT CODE SHOULD NOT BE NECESSARY SINCE IT'S HANDLED IN THE METHOD THAT CALLS THIS ONE
        // Decrement inputs for use in the ArrayLists
//        --currentAttackInput;
//        --otherAttackInput;
        
        Attack currentAttack = p.getCurrentAttacks().get(currentAttackInput);
        Attack otherAttack = p.getOtherAttacks().get(otherAttackInput);
        
        p.getCurrentAttacks().set(currentAttackInput, otherAttack);
        p.getOtherAttacks().set(otherAttackInput, currentAttack);
        
        MainGame.printlnlnWait("\nMove Change: Successful!\n\t" + currentAttack.getName() +
                " -----------> " + otherAttack.getName() , 25, 500);
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
    
    private Town createAquammoda()
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
        lac.setDescription("Anahita's father | Aquammoda Elder");
        
        NPC buzi = new NPC("Buzi", "Hey guys! I hope you're ready for the annual festival tonight. It'll be a blast!", false);
        buzi.setDescription("Aquammoda resident");
        
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
        Town v = new Town("Aquammoda", "A town located above Opicon Forest. Its residents are known to be very altruistic and compassionate.", people, 7, 1020, c);
        v.setShop(s);
        return v;
    }
    
    private Town createAquammoda2()
    {
        NPC merda = new NPC("Merda", "Be careful out there. If you ever need something, we're here for you.", false);
        merda.setDescription("Anahita's mother");
        
        // Anahita's little sister
        NPC brinlee = new NPC("Brinlee", "Ana, please be careful when you leave! I don't want to lose you too...", false);
        brinlee.setDescription("Anahita's little sister");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(merda);
        people.add(brinlee);
        
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
        Town v = new Town("Aquammoda", "A town located above Opicon Forest. Its residents are known to be very altruistic and compassionate.", people, 7, 1020, c);
        v.setShop(s);
        v.setIsExplored();
        return v;
    }
    
    private Town createDegon()
    {
        NPC gord = new NPC("Gord", "The thing I love most about Pulchra is how we all live in harmony. Our powers make it easy to help each other out.", false);
        gord.setDescription("Degon resident");
        
        NPC caillou = new NPC("Caillou", "ugh... i'm so weak now... the effects of my beans wore off... remember what i said, okay?", true);
        caillou.setDescription("Degon resident | Bean Master"); 
       
        // Gaea's cousin
        NPC fleur = new NPC("Fleur", "Thank you guys for your help again! But why are you still here? Go to the festival!", true);
        fleur.setDescription("Gaea's older cousin | Degon Elder");
        
        NPC roxy = new NPC("Roxy", "Have you guys seen the flowers blooming in Opicon Forest? This is the best time of year to see them if you haven't!", false);
        roxy.setDescription("Degon resident");

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
        Town v = new Town("Degon", "A town located southwest of Aquammoda.\nTheir residents love to take care of themselves and help the island's vegetation to prosper.", people, 9, 1271, c);
        v.setShop(s);
        return v;
    }
    
    private Town createDegon2()
    {   
        NPC caillou = new NPC("Caillou", "i wish beans were good enough to prevent all the casualties that happened.", false);
        caillou.setDescription("Degon resident // Bean Master"); 
       
        // Gaea's cousin
        NPC fleur = new NPC("Fleur", "I wish you all the best on your journey. Be careful out there, please.", true);
        fleur.setDescription("Gaea's older cousin");
        
        Item gift = Item.getBuffItem("Purple Bean");
        NPC roxy = new NPC("Roxy", "You're all going to be known as heroes. I just know it. Do what you can for us all.", gift, false);
        roxy.setDescription("Degon Elder");
        
        ArrayList<NPC> people = new ArrayList<>();
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
        Town v = new Town("Degon", "A town located southwest of Aquammoda.\nTheir residents love to take care of themselves and help the island's vegetation to prosper.", people, 9, 1271, c);
        v.setShop(s);
        v.setIsExplored();
        return v;
    }
    
    private Town createZoniCity()
    {
        // Add a cutscene for Vitorem
        NPC vitorem = new NPC("Elder Vitorem", "We'll be starting the festival soon! You don't want miss it.", true);
        vitorem.setDescription("Zoni City Elder | Leader of Pulchra");
        
        NPC calmus = new NPC("Calmus", "We should catch up more! Let's meet up again after the festival. I'll see you all soon!", true);
        calmus.setDescription("Infol resident");
        
        NPC frigs = new NPC("Frigs", "Go enjoy the festival! I'll be here for a long while. But let's catch up later!", true);
        frigs.setDescription("Solice resident");
        
        NPC ninlil = new NPC("Ninlil", "Ugh, can't you see I'm busy? You've interrupted me enough. Go away.", true);
        ninlil.setDescription("Aerogan resident");
        
        Item gift = Item.getBuffItem("Green Bean");
        NPC pheu = new NPC("Pheu", "You know my nephew Calmus, right? Do me a favor and keep being good friends with him. He may seem okay, but\n\the's struggling "
                + "with the loss of his parents from a few years ago. The poor boy needs a break from all he's doing.", gift, 2, false);
        pheu.setGiveGiftMessage("Take this in advance as a thank you. I know you'll keep my word.");
        pheu.setDescription("Infol Resident | Calmus' aunt");
        
        NPC ilven = new NPC("Ilven", "Hey! I hope you're all ready for the festival! Also, I have a request: be patient with Ninlil.\n\tI know she can be pretentious at times, "
                + "but she's not always like that. I know it's hard to believe, but trust me.", false);
        ilven.setDescription("Aerogan resident | Ninlil's training partner");
        
        gift = Item.getBuffItem("Blue Bean");
        NPC clairdra = new NPC("Clairdra", "Look at you all -- two beautiful, young ladies and my wonderful grandson. Let's celebrate another year of peace\n\ttogether, yes?", gift, 1, false);
        clairdra.setGiveGiftMessage("And to celebrate, take a Blue Bean. It's good for you, you know.");
        clairdra.setDescription("Elerric resident | Fultra's grandmother");
        
        NPC verg = new NPC("Verg", "Oh, hey everyone! I want to say thank you for being there for my little brother. It's awesome to see him have "
                + "amazing\n\tpeople to back him up when I'm not there. I hope you enjoy the rest of the night!", false);
        verg.setDescription("Solice resident | Frigs' older brother");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(vitorem);
        people.add(calmus);
        people.add(frigs);
        people.add(ninlil);
        people.add(pheu);
        people.add(ilven);
        people.add(clairdra);
        people.add(verg);
        
        Coordinate c = new Coordinate(12, 31);
        Town v = new Town("Zoni City", "The captial of Pulchra. It's located at the center of the island and has the densest population with a variety of residents.", people, 10, 2473, c);
        
        //----------------------------------------------------------------------
        Shop s = new Shop(Item.allItemsDeepCopy());
        //----------------------------------------------------------------------
        
        v.setShop(s);
        return v;
    }
    
    private Wilderness createZoniCity2()
    {
        Coordinate c = new Coordinate(12, 31);
        Wilderness zoni = new Wilderness("Zoni City", "What was once a bustling, animated city is now a desolated area. No Pulchrians are here anymore...", 27, c);
        zoni.addLocalElement("Water");
        zoni.addLocalElement("Earth");
        zoni.addLocalElement("Wind");
        zoni.addLocalElement("Fire");
        zoni.addLocalElement("Ice");
        zoni.addLocalElement("Electric");
        return zoni;
    }    
    
    private Town createAerogan()
    {   
        NPC nu = new NPC("Elder Nu", "(*smack*) If you need anything, do come back. We will do what we can to help you.", true);
        nu.setDescription("Aerogan Elder");
        
        NPC oura = new NPC("Oura", "(*sniff*) why is this happening... oh goodness... why?", false);
        oura.setDescription("Aerogan resident | Newly widowed");
        
        Item gift = Item.getBuffItem("Purple Bean");
        NPC tem = new NPC("Tem", "If you guys are here to help, we appreciate it. Hopefully we can recover from everything.", gift, 3, false);
        tem.setDescription("Aerogan resident");
        tem.setGiveGiftMessage("We don't have much right now, but I think you could use this better than us.");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(nu);
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
        Town v = new Town("Aerogan", "Located on the most eastern area of Pulchra, the residents here are known for their high esteem and have the most prestige out of\nany other town.", people, 12, 312, c);
        v.setShop(s);
        return v;
    }
    
    private Town createInfol()
    {
        NPC lyra = new NPC("Lyra", "Calmus! Please do what you can to help!", false);
        lyra.setDescription("Infol resident | Calmus' little sister");
        
        NPC volca = new NPC("Elder Vulca", "(*cough*) Thank you for helping, grandson. (*cough cough*)", true);
        volca.setDescription("Infol Elder | Calmus' grandmother");
        
        NPC mimi = new NPC("Mimi", "I normally live in Aquammoda, but things seem worse here than back at home, so I'm here to help.\n\tAre you here to help too?", false);
        mimi.setDescription("Aquammoda resident");
        
        Item gift = Item.getBuffItem("Red Bean");
        NPC hitaka = new NPC("Hitaka", "That Irwin guy... why did he do all of this...? So many towns have been destroyed because of him.", gift, false);
        hitaka.setDescription("Infol resident");
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
        items.add(Item.getBuffItem("Red Bean"));
        items.add(Item.getBuffItem("Orange Bean"));
        items.add(Item.getBuffItem("Green Bean"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(5, 53);
        Town v = new Town("Infol", "Located in the northeast of Pulchra, the Infol has a group of people rich in culture, community, and humblness.", people, 16, 201, c);
        v.setShop(s);
        return v;
    }
    
    private Town createSolice()
    {
        // Once player enters town, can find summit of mountain
        
        NPC zeno = new NPC("Elder Zeno", "I'm impressed you all made it through the mountain! The weather is at its worst this time of the year. Like others, we don't have much left, but some of us are holding on to hope.", true);
        zeno.setDescription("Solice Elder");
        
        NPC ligian = new NPC("Ligan", "If you want Frigs, he's at the summit of the mountain. He's been grieving a lot lately.", false);
        ligian.setDescription("Solice Resident");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(zeno);
        people.add(ligian);
        
        //----------------------------------------------------------------------
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.getHealingItem("Choco Bar"));
        items.add(Item.getHealingItem("Apple Pie"));
        items.add(Item.getHealingItem("Cheesecake"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(3, 31);
        Town v = new Town("Solice", "Near the peak of Zoni Mountain, Solice hosts a group of nonchalant yet powerful and honorable people.", people, 20, 56, c);
        v.setShop(s);
        return v;
    }
    
    private Town createElerric()
    {
        NPC clairdra = new NPC("Elder Clairdra", "Oh. Hello everyone. No, I don't know where Fultra is. We beleive he died during the festival. No one has seen him since... Oh, my poor grandson...", true);
        clairdra.setDescription("Elerric Elder | Fultra's grandmother");
        
        NPC tonnerre = new NPC("Tonnerre", "\"Fearless Thunder...\" Our town hasn't been the same without him. Gaea... I'm so sorry for your loss.", false);
        tonnerre.setDescription("Elerric resident");
        
        Item gift = Item.getHealingItem("Half Cake");
        NPC san = new NPC("San", "Are you guys okay? How are your towns?", gift, false);
        san.setDescription("Elerric resident");
        san.setGiveGiftMessage("I hope this helps, even if just a little.");
        
        NPC pheu = new NPC("Pheu", "MAKE ME HAVE DEFAULT TEXT", true);
        pheu.setDescription("Infol Resident | Calmus' aunt");
        
        ArrayList<NPC> people = new ArrayList<>();
        people.add(clairdra);
        people.add(tonnerre);
        people.add(san);
        people.add(pheu);
        
        //----------------------------------------------------------------------
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.getHealingItem("Apple Pie"));
        items.add(Item.getBuffItem("Red Bean"));
        items.add(Item.getBuffItem("Orange Bean"));
        items.add(Item.getBuffItem("Blue Bean"));
        Shop s = new Shop(items);
        //----------------------------------------------------------------------
        
        Coordinate c = new Coordinate(10, 9);
        Town v = new Town("Elerric", "Located to the east, Elerric is known for having the strongest fighters on Pulchra.", people, 25, 101, c);
        v.setShop(s);
        return v;
    }
    
    private Wilderness createPurityBeach()
    {
        Coordinate c = new Coordinate(22, 36);
        Wilderness purityBeach = new Wilderness("Purity Beach", "A beach to the south of Aquammoda. Its calm waves and salty air help soothe the mind.", 5, c);
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
        Wilderness opiconForest = new Wilderness("Opicon Forest", "A luscious forest with towering trees, diverse wildlife, and a variety of vegetation.\nIt spans between Degon and Aquammoda, "
                + "separating the two.", 6, c);
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
        
//        BossBattle battle = new BossBattle(((Wilderness)currentLocation).makeNinlilBoss(), team);
//        tempestTower.setBossBattle(battle, 14);
        
        return tempestTower;
    }
    
    private Wilderness createMountVolcan()
    {
        Coordinate c = new Coordinate(4, 58);
        Wilderness mountVolcan = new Wilderness("Mount Volcan", "An inactive volcano. Infol residents come here frequently to train and hone their abilities.", 17, c);
        mountVolcan.addLocalElement("Fire");
        mountVolcan.addLocalElement("Earth");
        mountVolcan.addLocalElement("Wind");
        return mountVolcan;
    }
    
    private Wilderness createMountZoni()
    {
        Coordinate c = new Coordinate(4, 31);
        Wilderness mountZoni = new Wilderness("Mount Zoni", "A large mountain with a frigid summit. During certain times of the year, the mountain expereinces whiteout blizzards.", 19, c);
        mountZoni.addLocalElement("Ice");
        mountZoni.addLocalElement("Wind");
        mountZoni.addLocalElement("Earth");
        mountZoni.addLocalElement("Electric");
        return mountZoni;
    }
    
    private Wilderness createMountZoniSummit()
    {
        Coordinate c = new Coordinate(2, 31);
        Wilderness mountZoniSummit = new Wilderness("Mount Zoni Summit", "The summit of Mount Zoni. Thw winds and bitter cold are unforgiving here.", 20, c);
        mountZoniSummit.addLocalElement("Ice");
        mountZoniSummit.addLocalElement("Wind");
        return mountZoniSummit;
    }
    
    private Wilderness createForlornCave()
    {
        Coordinate c = new Coordinate(9, 15);
        Wilderness forlornDessert = new Wilderness("Forlorn Cave", "A dark and ominous cave. Some say that the cave feels sentient. Those that go in rarely come out...", 23, c);
        forlornDessert.addLocalElement("Electric");
        forlornDessert.addLocalElement("Earth");
        forlornDessert.addLocalElement("Wind");
        forlornDessert.addLocalElement("Fire");
        return forlornDessert;
    }
    
    /**
     * Plays the necessary cutscene when the player enters a new location and updates their objective.
     */
    private void checkForCutscene()
    {
        if(isNewLocation("Opicon Forest"))
        {
            Cutscene.opicon();
            team.add(MainGame.makeGaea());
            team.add(MainGame.makeFultra());
            MainGame.setPlayerTeam(team);
        }
        else if(isNewLocation("Aquammoda"))
        {
            Cutscene.aquammoda();
        }
        else if(isNewLocation("Degon"))
        {
            Cutscene.degon();
        }
        else if(isNewLocation("Zoni City") && (!inSecondPhase))
        {
            Cutscene.zoniCity();
        }
        else if(isNewLocation("Aerogan"))
        {
            Cutscene.aerogan();
        }
        else if(isNewLocation("Tempest Tower"))
        {
            Cutscene.tempestTower();
        }
        else if(isNewLocation("Infol"))
        {
            Cutscene.infol();
        }
        else if(isNewLocation("Mount Volcan"))
        {
            Cutscene.mountVulca();
        }
        else if(isNewLocation("Mount Zoni"))
        {
            Cutscene.mountZoni();
        }
        else if(isNewLocation("Solice"))
        {
            Cutscene.solice();
        }
        else if(isNewLocation("Mount Zoni Summit"))
        {
//            objective.update();  // No cutscene needed
        }
        else if(isNewLocation("Forlorn Cave"))
        {
            Cutscene.forlornCave();
        }
        else if(isNewLocation("Elerric"))
        {
            Cutscene.elerric();
        }
        else if(isNewLocation("Zoni City") && inSecondPhase)
        {
            Cutscene.returnToZoni();
        }
            
        currentLocation.setIsExplored();
    }
    
    /**
     * Helper method for checkForCutscene() that checks if the given town name hasn't been explored.
     * @param name
     * @return 
     */
    private boolean isNewLocation(String name)
    {
        return currentLocation.getName().equals(name) && (!currentLocation.isExplored());
    }
    
    /**
     * Helper method that removes a Player objects from the team ArrayList based on the given name.
     * @param name 
     */
    private void removePlayer(String name)
    {
        for(Player p : team)
        {
            if(name.equals(p.getName()))
            {
                team.remove(p);
                break;
            }
        }
    }
    
    /**
     * Helper method that changes each Player object's level from the team ArrayList.
     * @param name 
     */
    private void setPlayerLevels(int level)
    {
        for(Player p : team)
        {
            p.setLevel(level);
        }
    }
    
    /**
     * Helper method that returns an ArrayList containing the player the Player given.
     * @param name
     * @return 
     */
    private ArrayList<Player> makePlayerTeam(String name)
    {
        ArrayList<Player> list = new ArrayList<>();
        
        for(Player p : team)
        {
            if(p.getName().equals(name))
            {
                list.add(p);
                break;
            }
        }
        
        return list;
    }
    
    /**
     * Helper method to get the object of a known location given the name.
     * @param name
     * @return Location
     */
    private Location getLocation(String name)
    {
        for(Location l : knownLocations)
        {
            if(l.getName().equals(name))
            {
                return l;
            }
        }
        
        return null;
    }
}
