package Game;

import Battle.Player;
import Exploration.Location;
import Exploration.NPC;
import Exploration.Town;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class for managing boolean values and keeping track of the player's progress. It will also force the player to 
 * complete the given objective before they can move on.
 * @author Ian King
 */
public class Objective implements Serializable
{
    private Task currentTask;
//    private Task nextObjective;
    private ArrayList<Task> unfinsihedTasks = new ArrayList<>();
    
    // Objective booleans
    private boolean atOpiconForest;
    private boolean atAquammoda;
    private boolean talkedToMerda;
    private boolean atDegon;
    private boolean talkedToFleur;
    private boolean atZoniCity;
    private boolean talkedToEveryone;
    private boolean atAerogan;
    private boolean talkedToElderNu;
    private boolean atTempestTower;
    private boolean foundNinlil;
    private boolean atInfol;
    private boolean talkedToElderVulca;
    private boolean atMountVolcan;
    private boolean talkedToLyra;
    private boolean atMountZoni;
    private boolean atSolice;
    private boolean talkedToElderZeno;
    private boolean atSummit;
    private boolean atForlornCave;
    private boolean atElerric;
    private boolean talkedToElderClairdra;
    private boolean returnedZoniCity;
    
    // Battle booleans
    private boolean opiconTutorialDone;
    private boolean ninlilBossDefeated;
    private boolean omegaBossDefeated;
    private boolean frgisBossDefeated;
    private boolean fultraBossDefeated;
    private boolean irwinBossDefeated;
    private boolean finalBossDefeated;
    
    public Objective()
    {
        populateTasks();
        currentTask = unfinsihedTasks.remove(0);
    }
    
    private void populateTasks()
    {
        unfinsihedTasks.add(new LevelTask("Unlock Opicon Forest (Required Level: 6)", false, 6, true));
        unfinsihedTasks.add(new TravelTask("Travel to Opicon Forest", atOpiconForest, "Opicon Forest", false));
        unfinsihedTasks.add(new BattleTask("Complete the battle tutorial in Opicon Forest", opiconTutorialDone, true));
        unfinsihedTasks.add(new TravelTask("Travel to Aquammoda", atAquammoda, "Aquammoda", false));
        unfinsihedTasks.add(new NpcTask("Talk to Merda", talkedToMerda, "Merda", false));
        unfinsihedTasks.add(new LevelTask("Unlock Degon (Required Level: 9)", atDegon, 9, true));
        unfinsihedTasks.add(new TravelTask("Travel to Degon", atDegon, "Degon", false));
        unfinsihedTasks.add(new NpcTask("Talk to Fleur", talkedToFleur, "Fleur", false));
        unfinsihedTasks.add(new LevelTask("Unlock Zoni City (Required Level: 10)", false, 10, true));
        unfinsihedTasks.add(new TravelTask("Travel to Zoni City", atZoniCity, "Zoni City", false));
        unfinsihedTasks.add(new NpcTask("Enjoy the festival and talk to everyone!", talkedToEveryone, "", false));

        // Second phase 
        unfinsihedTasks.add(new LevelTask("Unlock Aerogan (Required Level: 12)", false, 12, true));
        unfinsihedTasks.add(new TravelTask("Travel to Aerogan", atAerogan, "Aerogan", false));
        unfinsihedTasks.add(new NpcTask("Talk to Elder Nu", talkedToElderNu, "Elder Nu", true));
        unfinsihedTasks.add(new TravelTask("Travel to Tempest Tower", atTempestTower, "Tempest Tower", false));
        unfinsihedTasks.add(new LevelTask("Find Ninlil in Tempest Tower (Required Level: 14)", foundNinlil, 14, false));
        unfinsihedTasks.add(new BattleTask("Battle Ninlil", ninlilBossDefeated, true));
        unfinsihedTasks.add(new LevelTask("Unlock Infol (Required Level: 16)", false, 16, true));
        unfinsihedTasks.add(new TravelTask("Travel to Infol", atInfol, "Infol", false));
        unfinsihedTasks.add(new NpcTask("Talk to Elder Vulca", talkedToElderVulca, "Elder Vulca", true));
        unfinsihedTasks.add(new TravelTask("Travel to Mount Volcan", atMountVolcan, "Mount Volcan", false));
        unfinsihedTasks.add(new LevelTask("Find the mineral for Elder Vulca (Required Level: 17)", false, 17, false));
        unfinsihedTasks.add(new BattleTask("Defeat the boss to mine the mineral", omegaBossDefeated, false));
        unfinsihedTasks.add(new NpcTask("Talk to Lyra", talkedToLyra, "Lyra", true));
        unfinsihedTasks.add(new TravelTask("Travel to Mount Zoni", atMountZoni, "Mount Zoni", false));
        unfinsihedTasks.add(new LevelTask("Unlock Solice (Required Level: 20)", false, 20, true));
        unfinsihedTasks.add(new TravelTask("Travel to Solice", atSolice, "Solice", false));
        unfinsihedTasks.add(new NpcTask("Talk to Elder Zeno", talkedToElderZeno, "Elder Zeno", true));
        unfinsihedTasks.add(new TravelTask("Travel to Mount Zoni Summit", atSummit, "Mount Zoni Summit", false));
        unfinsihedTasks.add(new LevelTask("Find Frigs (Required Level: 21)", false, 21, false));
        unfinsihedTasks.add(new BattleTask("Battle Frigs", frgisBossDefeated, false));
        unfinsihedTasks.add(new LevelTask("Unlock Forlorn Cave (Required Level: 23)", false, 23, true));
        unfinsihedTasks.add(new TravelTask("Travel to Forlorn Cave", atForlornCave, "Forlorn Cave", false));
        unfinsihedTasks.add(new LevelTask("Unlock Elerric (Required Level: 25)", false, 25, true));
        unfinsihedTasks.add(new TravelTask("Travel to Elerric", atElerric, "Elerric", false));
        unfinsihedTasks.add(new NpcTask("Talk to Elder Clairdra", talkedToElderClairdra, "Elder Clairdra", true));
        unfinsihedTasks.add(new TravelTask("Return to Zoni City", returnedZoniCity, "Zoni City", false));
        unfinsihedTasks.add(new LevelTask("Search for Irwin (Required Level: 27)", false, 27, false));
        unfinsihedTasks.add(new BattleTask("Defeat the incoming boss", fultraBossDefeated, false));
        unfinsihedTasks.add(new LevelTask("Search for Irwin again (Required Level: 28)", false, 28, false));
        unfinsihedTasks.add(new BattleTask("Defeat Irwin", irwinBossDefeated, false));
        unfinsihedTasks.add(new LevelTask("Find where Irwin fled to (Required Level: 30)", false, 30, false));
        unfinsihedTasks.add(new BattleTask("Defeat Irwin one last time", finalBossDefeated, false));
        unfinsihedTasks.add(new Task("Replay the game! There's no more content.", false, false));
    }
    
    public void updateByTest()
    {
        completeTask();
    }
    
    private void completeTask() 
    {
//        currentTask.complete();
        currentTask = unfinsihedTasks.remove(0);
    }
    
    public boolean completedTask(Game game)
    {
        // The Game class will manually update for Battles
        if(currentTask instanceof LevelTask)
        {
            return metLevelReq(Player.highestPlayerLV(game.getTeam()));
        }
        else if(currentTask instanceof NpcTask && game.getCurrentLocation() instanceof Town)
        {
            return metNpcReq((Town)game.getCurrentLocation());
        }
        else if(currentTask instanceof TravelTask)
        {
            return metTravelReq(game.getCurrentLocation());
        }
        
        return false;
    }
    
    public void update(Game game)
    {
        if(currentTask.unlocksLocation && currentTask.isAccomplished)
        {
            MainGame.promptToEnter();
            game.unlockNextLocation();
        }
            
        completeTask();
        
            
//        if(currentTask instanceof LevelTask)
//        {
//            updateByLevel(Player.highestPlayerLV(game.getTeam()));
//            return true;
//        }
//        else if(currentTask instanceof NpcTask)
//        {
//            updateByNpc((Town)game.getCurrentLocation());
//            return true;
//        }
//        else if(currentTask instanceof PrereqTask)
//        {
//            updateByPrereq();
//            return true;
//        }
//        else if(currentTask instanceof BattleTask)
//        {
//            updateByBattleResult(game.getRecentBattleResult());
//            return true;
//        }
//        else if(currentTask instanceof TravelTask)
//        {
//            updateByTravel(game.getCurrentLocation());
//                return true;
//        }
//        
//        return false;
    }
    
    public void updateByBattleResult(boolean wonBattle)
    {
        // If the current task isn't to win a battle, don't do anything
//        if(!(currentTask instanceof BattleTask)) {return;}
        
        if(wonBattle)
        {
            completeTask();
        }
    }
    
    public void updateByTravel(Location place)
    {
        // If the current task isn't to travel, don't do anything
//        if(!(currentTask instanceof TravelTask)) {return;}
        
        // If the player arrived at the new place and it's still a new place, update the objective
//        if(place.getName().equals(((TravelTask)currentTask).getPlaceName()) && !place.isExplored())
//        {
//            completeTask();
//        }
        if(metTravelReq(place))
        {
            completeTask();
        }
    }
    
    public boolean metTravelReq(Location place)
    {
        String currentPlaceName = place.getName();
        String taskPlaceName = ((TravelTask)currentTask).getPlaceName();
        // currentTask.isAccomplished = place.getName().equals(((TravelTask)currentTask).getPlaceName()) && !place.isExplored();
        currentTask.isAccomplished = currentPlaceName.equals(taskPlaceName) && !place.isExplored();
        return currentTask.isAccomplished;
    }
    
    
    public void updateByNpc(Town town)
    {
        // If the current task isn't to talk, don't do anything
//        if(!(currentTask instanceof NpcTask)) {return;}
        
        // Specific check for talking to everyone at Zoni City
//        if(town.getName().equals("Zoni City") && town.hasTalkedToEveryone())
//        {
//            completeTask();
//            return;
//        }
//        
//        NPC npc = town.getNPC(((NpcTask)currentTask).getNpcName());
//        
//        // If the NPC is returned successfully and has been talked to, complete the task
//        if(npc != null && npc.hasBeenTalkedTo())
//        {
//            completeTask();
//        }
        if(metNpcReq(town))
        {
            completeTask();
        }
    }
    
    public boolean metNpcReq(Town town)
    {
        if(town.getName().equals("Zoni City") && town.hasTalkedToEveryone())
        {
            currentTask.isAccomplished = true;
        }
        else
        {
            NPC npc = town.getNPC(((NpcTask)currentTask).getNpcName());
            currentTask.isAccomplished = npc != null && npc.hasBeenTalkedTo();
        }
        
        return currentTask.isAccomplished;
    }
    
    public void updateByLevel(int level)
    {
        // If the current task isn't to level up, don't do anything
//        if(!(currentTask instanceof LevelTask)) {return;}
        
        if(metLevelReq(level))
        {
            completeTask();
        }
    }
    
    /**
     * Returns true if the level requirement is met.
     * @param level
     * @return boolean value
     */
    public boolean metLevelReq(int level)
    {
        currentTask.isAccomplished = level >= ((LevelTask)currentTask).getRequiredLevel();
        return currentTask.isAccomplished;
    }
    
    public void printCurrentObjective()
    {
        MainGame.println("Current Objective: " + currentTask.toString());
    }
    
    @Override
    public String toString()
    {
        return currentTask.toString();
    }
    
    /* 
    Make an instantiations method like in the game class to create all the Booleans that'll be checked for the game.
    Add them in order to the notCompleted ArrayList and when this Object is created, add the first not completed Boolean to 
    be the currentObjective. 
    */
    
    // Private classes -----------------------------------------------------------------------------------------------------
    private class BattleTask extends Task
    {
        private BattleTask(String taskDescription, boolean accomplished, boolean unlocksLocation)
        {
            super(taskDescription, accomplished, unlocksLocation);
        }
    }
    
    private class TravelTask extends Task
    {
        String placeName;
        
        private TravelTask(String taskDescription, boolean accomplished, String placeName, boolean unlocksLocation)
        {
            super(taskDescription, accomplished, unlocksLocation);
            this.placeName = placeName;
        }
        
        private String getPlaceName() {return placeName;}
    }
    
    private class NpcTask extends Task
    {
        String npcName;
        
        private NpcTask(String taskDescription, boolean accomplished, String npcName, boolean unlocksLocation)
        {
            super(taskDescription, accomplished, unlocksLocation);
            this.npcName = npcName;
        }
        
        private String getNpcName() {return npcName;}
    }
    
    private class LevelTask extends Task
    {
        int requiredLevel;
        
        private LevelTask(String taskDescription, boolean accomplished, int requiredLevel, boolean unlocksLocation)
        {
            super(taskDescription, accomplished, unlocksLocation);
            this.requiredLevel = requiredLevel;
        }
        
        private int getRequiredLevel() {return requiredLevel;}
    }
    
    private class Task implements Serializable
    {
        private boolean isAccomplished;
        private final String TASK_DESCRIPTION;
        private boolean unlocksLocation;
        
        private Task(String taskDescription, boolean accomplished, boolean unlocksLocation)
        {
            this.TASK_DESCRIPTION = taskDescription;
            this.isAccomplished = accomplished;
            this.unlocksLocation = unlocksLocation;
        }
        
        private void complete() {this.isAccomplished = true;}
        
        @Override
        public String toString()
        {
            return TASK_DESCRIPTION;
        }
    }
}
