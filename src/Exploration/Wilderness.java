package Exploration;

import Battle.BeachTutorialBattle;
import Battle.BossBattle;
import Battle.BossEnemy;
import Battle.EarthEnemy;
import Battle.ElectricEnemy;
import Battle.Enemy;
import Battle.FireEnemy;
import Battle.IceEnemy;
import Battle.NormalBattle;
import Battle.OpiconTutorialBattle;
import Battle.Player;
import Battle.RESIBattle;
import Battle.RESIEnemy;
import Battle.WaterEnemy;
import Battle.WindEnemy;
import Game.MainGame;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for making areas the player can explore for chests and battle monsters
 * @author Ian King
 */
public class Wilderness extends Location
{
    private final ArrayList<String> LOCAL_ELEMENTS = new ArrayList<>(3);
    private final int MAX_ENEMY_LV;
    private final int MIN_ENEMY_LV;
    
    public Wilderness(String name, String description, int requiredLevel, Coordinate coordinate)
    {
        super(name, description, requiredLevel, coordinate);
        MAX_ENEMY_LV = requiredLevel + 4;
        MIN_ENEMY_LV = requiredLevel - 1;
        this.coordinate = coordinate;
    }
    
    public int getMAX_ENEMY_LV() {return MAX_ENEMY_LV;}
    public int getMIN_ENEMY_LV() {return MIN_ENEMY_LV;}
    
    public ArrayList getLocalElements() {return LOCAL_ELEMENTS;}
    
    public void addLocalElement(String element) {LOCAL_ELEMENTS.add(element);}
    
//    public void findChest()
//    {
//        if(hasFullChest() && !chest.isAlreadyFound())
//        {
//            openChest();
//        }
//        else if(hasFullChest() && chest.isAlreadyFound())
//        {
//            int chance = new Random().nextInt(2);
//            
//            switch(chance)
//            {
//                case 0:
//                    openChest();
//                    break;
//                case 1:
//                    MainGame.printlnlnWait("You couldn't find a chest...", 25, 2000);
//                    break;
//            }
//        }
//        else
//        {
//            System.out.println("");
//            MainGame.printlnlnWait("You couldn't find a chest...", 25, 2000);
//        }
//    }
//    
//    private boolean hasFullChest()
//    {
//        return !chest.isEmpty();
//    }
//    
//    private void openChest()
//    {
//        System.out.println("");
//        MainGame.printlnln("You found a chest! What does it have?", 25);
//        chest.open();
//        MainGame.wait(2500);
//    }
    
    /**
     * Returns a tutorial battle sequence to teach the player how to play.
     * @param player
     * @return a tutorial battle
     */
    public BeachTutorialBattle makeBeachTutorial(Player player)
    {
        EarthEnemy krobble = new EarthEnemy("Sandy Krobble", "A Krobble with a loving family that's threatening Anahita!", 5);
        krobble.setCurrentHealth(1);
        krobble.setSpeed(1);
        BeachTutorialBattle battle = new BeachTutorialBattle(krobble, player);
        return battle;
    }
    
    public OpiconTutorialBattle makeOpiconTutorial(ArrayList<Player> playerTeam)
    {
        WaterEnemy turkle = new WaterEnemy("Damp Turkle", "It appears to be helping the leader Krobble to get revenge!", 5);
        EarthEnemy krobble = new EarthEnemy("Sandy Krobble", "This Krobble is the sibling of the other one Anahita defeated!\n\tIt's come for revenge with it's gang!", 6);
        ElectricEnemy torped = new ElectricEnemy("Thundering Torped", "It appears to be helping the leader Krobble to get revenge!", 5);
        
        
        // Remove after tests are done *************************
        turkle.setCurrentHealth(1);
//        turkle.setSpeed(98);
        krobble.setCurrentHealth(1);
//        krobble.setSpeed(97);
        torped.setCurrentHealth(1);
//        torped.setSpeed(1);
        
//        turkle.getCurrentAttacks().remove(0);
//        krobble.getCurrentAttacks().remove(0);
//        torped.getCurrentAttacks().remove(0);
//        
//        turkle.getCurrentAttacks().add(Enemy.getHealingAttack("restful spirit"));
//        krobble.getCurrentAttacks().add(Enemy.getHealingAttack("soothing aura"));
//        torped.getCurrentAttacks().add(Enemy.getHealingAttack("soothing aura"));
        
        //*************************************************************
        
        
        ArrayList<Enemy> enemyTeam = new ArrayList<>(3);
        enemyTeam.add(turkle);
        enemyTeam.add(krobble);
        enemyTeam.add(torped);
        
        OpiconTutorialBattle battle = new OpiconTutorialBattle(enemyTeam, playerTeam);
        return battle;
    }
    
    /**
     * Returns a battle with normal enemies.
     * @param playerTeam
     * @return a normal battle
     */
    public NormalBattle makeNormalBattle(ArrayList<Player> playerTeam)
    {
        ArrayList<Enemy> enemyTeam = createNormalEnemyTeam();
        NormalBattle battle = new NormalBattle(enemyTeam, playerTeam);
        return battle;
    }
    
    /**
     * Creates a randomly generated team of normal enemies.
     * @return a team of enemies
     */
    private ArrayList<Enemy> createNormalEnemyTeam()
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        ArrayList<Enemy> enemyTeam = new ArrayList<>();
        
        formNormalEnemyTeam(enemyTeam, randomNum);
//        switch(randomNum)
//        {
//            // create a team of 1 enemy
//            case 0:
//                formNormalEnemyTeam(enemyTeam, 1);
//                break;
//            case 1:
//                // create a team of 2 enemies
//                formNormalEnemyTeam(enemyTeam, 2);
//                break;
//            case 2:
//                // create a team of 3 enemies
//                formNormalEnemyTeam(enemyTeam, 3);
//                break;
//        }
        
        return enemyTeam;
    }
    
    /**
     * Adds to an ArrayList of the Enemy type using the given team size.
     */
    private void formNormalEnemyTeam(ArrayList<Enemy> enemyTeam, int enemyTeamSize)
    {   
        for(int i = 0; i < enemyTeamSize; i++)
        {
            /* 
            If team size is 1, make level the max
            If team size is >1, the level will be randomly generated
            */
            switch (enemyTeamSize) 
            {
                case 1:
                    enemyTeam.add(createNormalEnemy(this.MAX_ENEMY_LV));
                    break;
                case 2:
                default:
                    enemyTeam.add(createNormalEnemy());
                    break;
            }
        }
    }
    
    /**
     * Returns a generated Normal Enemy.
     * @return an Enemy
     */
    private Enemy createNormalEnemy()
    {
        String element = selectEnemyElement();
        Enemy e = null;
        
        switch(element)
        {
            case "Water":
                e = new WaterEnemy(this);
                break;
            case "Fire":
                e = new FireEnemy(this);
                break;
            case "Earth":
                e = new EarthEnemy(this);
                break;
            case "Wind":
                e = new WindEnemy(this);
                break;
            case "Ice":
                e = new IceEnemy(this);
                break;
            case "Electric":
                e = new ElectricEnemy(this);
                break;
        }
        
        return e;
    }
    
    /**
     * Creates a Normal enemy with the specified level.
     * @param level
     * @return an Enemy
     */
    private Enemy createNormalEnemy(int level)
    {
        String element = selectEnemyElement();
        Enemy e = null;
        
        switch(element)
        {
            case "Water":
                e = new WaterEnemy(this, level);
                break;
            case "Fire":
                e = new FireEnemy(this, level);
                break;
            case "Earth":
                e = new EarthEnemy(this, level);
                break;
            case "Wind":
                e = new WindEnemy(this, level);
                break;
            case "Ice":
                e = new IceEnemy(this, level);
                break;
            case "Electric":
                e = new ElectricEnemy(this, level);
                break;
        }
        
        return e;
    }
    
    /**
     * Randomly selects an element from this location's element list.
     * @return an element
     */
    private String selectEnemyElement()
    {
        String element;
        int randomNum = new Random().nextInt(LOCAL_ELEMENTS.size());
        element = LOCAL_ELEMENTS.get(randomNum);
        return element;
    }
    
    public BossBattle makeBossBattle(BossEnemy boss, ArrayList<Player> playerTeam)
    {
        ArrayList<Enemy> bossTeam = createBossEnemyTeam(boss);
        BossBattle battle = new BossBattle(bossTeam, playerTeam);
        return battle;
    }
    
    private ArrayList<Enemy> createBossEnemyTeam(BossEnemy boss)
    {
        ArrayList<Enemy> bossTeam = new ArrayList<>();
        
        formBossTeam(boss, bossTeam);
        
        return bossTeam;
    }
    
    /**
     * Randomly adds 0, 1, or 2 extra enemies to the boss team and positions them in a certain way.
     * @param boss
     * @param enemyTeam 
     */
    private void formBossTeam(BossEnemy boss, ArrayList<Enemy> enemyTeam)
    {
        int randomNum = new Random().nextInt(3);
        
        // Determines if there will be any weaker enemies with the boss
        switch(randomNum)
        {
            // No case 0 for no additional enemies
            
            // One extra enemy is added to the enemy team
            case 1:
                enemyTeam.add(boss);
                enemyTeam.add(createNormalEnemy(this.MIN_ENEMY_LV - 1));
                break;
            // Two extra enemies are added
            case 2: 
                enemyTeam.add(createNormalEnemy(this.MIN_ENEMY_LV - 3));
                // Boss is added second to ensure it's in the center for the fight
                enemyTeam.add(boss);
                enemyTeam.add(createNormalEnemy(this.MIN_ENEMY_LV - 3));
                break;
        }
    }
    
    public RESIBattle makeRESIBattle(ArrayList<Player> playerTeam)
    {
        ArrayList<Enemy> enemyTeam = createRESITeam();
        RESIBattle battle = new RESIBattle(enemyTeam, playerTeam);
        return battle;
    }
    
    private ArrayList<Enemy> createRESITeam()
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1;
        ArrayList<Enemy> enemyTeam = new ArrayList<>();
        
        // Use the random number for more concise code
        formRESIEnemyTeam(enemyTeam, randomNum);
        
//        switch(randomNum)
//        {
//            // create a team of 1 enemy
//            case 0:
//                formRESIEnemyTeam(enemyTeam, 1);
//                break;
//            case 1:
//                // create a team of 2 enemies
//                formRESIEnemyTeam(enemyTeam, 2);
//                break;
//        }
        
        return enemyTeam;
    }
    
    private void formRESIEnemyTeam(ArrayList<Enemy> enemyTeam, int enemyTeamSize)
    {
        Enemy e = null;
        
        for(int i = 0; i < enemyTeamSize; i++)
        {
            switch(enemyTeamSize)
            {
                case 1:
                    e = createRESIEnemy(this.MAX_ENEMY_LV - 1);
                    break;
                case 2:
                    e = createRESIEnemy(this.MAX_ENEMY_LV - 2);
                    break;
            }
            
//            // If team size is 1, make level the max - 1
//            if(enemyTeamSize == 1)
//            {
//                e = createRESIEnemy(this.MAX_ENEMY_LV - 1);
//            }
//            // Else, make the level the max - 2
//            else if(enemyTeamSize == 2)
//            {
//                e = createRESIEnemy(this.MAX_ENEMY_LV - 2);
//            }
            
            
            enemyTeam.add(e);
        }
    }
    
    private RESIEnemy createRESIEnemy(int level)
    {
        return new RESIEnemy(level, this);
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }
}
