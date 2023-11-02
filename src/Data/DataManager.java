package Data;

import Battle.Inventory;
import Battle.Player;
import Exploration.Location;
import Exploration.Map;
import Game.Game;
import Game.Objective;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class used to manage which Game data needs to be saved.
 * @author Ian King
 */
public class DataManager implements Serializable
{
    private boolean isTesting;

    // Game information
    private boolean beachTutorialDone;
    private boolean forestTutorialDone;
    private boolean recentBattleWon;
    private boolean resiTutorialAttempted;
    
    private boolean towerBossAttempted;
    private boolean volcanBossAttempted;
    private boolean summitBossAttempted;
    
    // Endgame boss battle booleans  
    private boolean fultraBossAttempted;
    private boolean fultraBossDefeated;
    private boolean irwinBossAttempted;
    private boolean irwinBossDefeated;
    private boolean finalBossAttempted;
    
    private Objective objective;    
    private Location currentLocation;
    private Location nextLocation;
    private ArrayList<Location> knownLocations;
    private ArrayList<Location> remainingLocations;
    private ArrayList<Player> team;    
    private int gold;    
    private boolean inSecondPhase;
    private boolean defeatedOmegaBoss;
    private int pulchraPopulation;
    private Map map;    
    private Inventory inventory = new Inventory();
    
    public DataManager(Game game)
    {
        isTesting = Game.isTesting();

        beachTutorialDone = game.isBeachTutorialDone();
        forestTutorialDone = game.isForestTutorialDone();
        recentBattleWon = game.isRecentBattleWon();
        resiTutorialAttempted = game.isResiTutorialAttempted();

        towerBossAttempted = game.isTowerBossAttempted();
        volcanBossAttempted = game.isVolcanBossAttempted();
        summitBossAttempted = game.isSummitBossAttempted();

        fultraBossAttempted = game.isFultraBossAttempted();
        fultraBossDefeated = game.isFultraBossDefeated();
        irwinBossAttempted = game.isIrwinBossAttempted();
        irwinBossDefeated = game.isIrwinBossDefeated();
        finalBossAttempted = game.isFinalBossAttempted();

        objective = game.getObjective();
        currentLocation = game.getCurrentLocation();
        nextLocation = game.getNextLocation();
        knownLocations = game.getKnownLocations();
        remainingLocations = game.getRemainingLocations();
        team = game.getTeam();
        gold = Game.getGold();
        inSecondPhase = Game.getSecondPhase();
        defeatedOmegaBoss = Game.getDefeatedOmega();
        pulchraPopulation = game.getPulchraPopulation();
        map = game.getMap();
        inventory = Game.getInventory();
    }

    public boolean isTesting() {
        return isTesting;
    }

    public boolean isBeachTutorialDone() {
        return beachTutorialDone;
    }

    public boolean isForestTutorialDone() {
        return forestTutorialDone;
    }

    public boolean isRecentBattleWon() {
        return recentBattleWon;
    }

    public boolean isResiTutorialAttempted() {
        return resiTutorialAttempted;
    }

    public boolean isTowerBossAttempted() {
        return towerBossAttempted;
    }

    public boolean isVolcanBossAttempted() {
        return volcanBossAttempted;
    }

    public boolean isSummitBossAttempted() {
        return summitBossAttempted;
    }

    public boolean isFultraBossAttempted() {
        return fultraBossAttempted;
    }

    public boolean isFultraBossDefeated() {
        return fultraBossDefeated;
    }

    public boolean isIrwinBossAttempted() {
        return irwinBossAttempted;
    }

    public boolean isIrwinBossDefeated() {
        return irwinBossDefeated;
    }

    public boolean isFinalBossAttempted() {
        return finalBossAttempted;
    }

    public Objective getObjective() {
        return objective;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public ArrayList<Location> getKnownLocations() {
        return knownLocations;
    }

    public ArrayList<Location> getRemainingLocations() {
        return remainingLocations;
    }

    public ArrayList<Player> getTeam() {
        return team;
    }

    public int getGold() {
        return gold;
    }

    public boolean isInSecondPhase() {
        return inSecondPhase;
    }

    public boolean isDefeatedOmegaBoss() {
        return defeatedOmegaBoss;
    }

    public int getPulchraPopulation() {
        return pulchraPopulation;
    }

    public Map getMap() {
        return map;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
