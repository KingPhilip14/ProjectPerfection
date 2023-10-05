package Data;

import Game.Game;
import Game.MainGame;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class used to write and read Game data from a file.
 * @author Ian King
 */
public class SaveLoad 
{
    public void save(Game game)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("ProjPerf.dat")));
            
            // Write a new DataManager object to the file. All information is stored once constructed.
            oos.writeObject(new DataManager(game));
            oos.close();
            
            MainGame.printlnln("Game successfully saved!", 25);
        }
        catch(Exception e)
        {
            MainGame.printlnln("Game data couldn't be saved. " + e.getMessage(), 25);
        }
    }
    
    public Game load() throws Exception
    {
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("ProjPerf.dat"))))
        {
            DataManager dm = (DataManager) ois.readObject();
            
            // Write the data from the file into a Game object and return it
            Game game = new Game(false);
            game.setBeachTutorialDone(dm.isBeachTutorialDone());
            game.setForestTutorialDone(dm.isForestTutorialDone());
            game.setRecentBattleWon(dm.isRecentBattleWon());
            game.setResiTutorialAttempted(dm.isResiTutorialAttempted());
            
            game.setTowerBossAttempted(dm.isTowerBossAttempted());
            game.setVolcanBossAttempted(dm.isVolcanBossAttempted());
            game.setSummitBossAttempted(dm.isSummitBossAttempted());
            
            game.setFultraBossAttempted(dm.isFultraBossAttempted());
            game.setFultraBossDefeated(dm.isFultraBossDefeated());
            game.setIrwinBossAttempted(dm.isIrwinBossAttempted());
            game.setIrwinBossDefeated(dm.isIrwinBossDefeated());
            
            game.setObjective(dm.getObjective());
            game.setCurrentLocation(dm.getCurrentLocation());
            game.setNextLocation(dm.getNextLocation());
            game.setKnownLocations(dm.getKnownLocations());
            game.setRemainingLocations(dm.getRemainingLocations());
            
            game.setTeam(dm.getTeam());
            Game.setGold(dm.getGold());
            Game.setInSecondPhase(dm.isInSecondPhase());
            
            Game.setDefeatedOmegaBoss(dm.isDefeatedOmegaBoss());
            game.setPulchraPopulation(dm.getPulchraPopulation());
            game.setMap(dm.getMap());
            
            Game.setInventory(dm.getInventory());
            
            ois.close();
            return game;
        }
    }
}
