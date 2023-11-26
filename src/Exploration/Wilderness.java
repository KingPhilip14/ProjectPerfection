package Exploration;

import Battle.Attack;
import Battle.BeachTutorialBattle;
import Battle.BossBattle;
import Battle.BossEnemy;
import Battle.BuffAttack;
import Battle.EarthEnemy;
import Battle.ElectricEnemy;
import Battle.Enemy;
import Battle.FireEnemy;
import Battle.IceEnemy;
import Battle.NormalBattle;
import Battle.OffensiveAttack;
import Battle.OpiconTutorialBattle;
import Battle.Player;
import Battle.RESIBattle;
import Battle.RESIEnemy;
import Battle.SingleHealingAttack;
import Battle.WaterEnemy;
import Battle.WindEnemy;
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
    private BossBattle bossBattle;
    private int requiredBossLevel;
    
    public Wilderness(String name, String description, int averageLevel, Coordinate coordinate)
    {
        super(name, description, averageLevel, coordinate);
        MAX_ENEMY_LV = averageLevel + 3;
        MIN_ENEMY_LV = averageLevel;
        this.coordinate = coordinate;
        bossBattle = null;
    }
    
    public BossBattle getBossBattle() {return this.bossBattle;}
    
    /**
     * Sets the boss battle by giving a BossBattle Object and its required level.
     * @param battle
     * @param requiredLevel 
     */
    public void setBossBattle(BossBattle battle, int requiredLevel) 
    {
        this.bossBattle = battle;
        this.requiredBossLevel = requiredLevel;
    }
    
    public boolean hasBossBattle() {return this.bossBattle != null;}
    
    /**
     * Returns true if the boss battle is not null and if the player is at the required level.
     * @param playerTeam
     * @return true or false
     */
    public boolean canDoBossBattle(ArrayList<Player> playerTeam) 
    {
        return hasBossBattle() && Player.highestPlayerLV(playerTeam) >= requiredBossLevel;
    }
    public void removeBossBattle() {this.bossBattle = null;}
    
    
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
        krobble.setSpeed(1);
        BeachTutorialBattle battle = new BeachTutorialBattle(krobble, player);
        return battle;
    }
    
    public OpiconTutorialBattle makeOpiconTutorial(ArrayList<Player> playerTeam)
    {
        WaterEnemy turkle = new WaterEnemy("Damp Turkle", "The grunt of the group helping the leader Krobble to get revenge!", 5);
        EarthEnemy krobble = new EarthEnemy("Sandy Krobble", "This Krobble is the sibling of the other one Anahita defeated!\n\tIt's come for revenge with its gang!", 6);
        ElectricEnemy torped = new ElectricEnemy("Thundering Torped", "The second-in-command helping the leader Krobble to get revenge!", 5);
        
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
        
        switch(element)
        {
            case "Water":
                return new WaterEnemy(this);
            case "Fire":
                return new FireEnemy(this);
            case "Earth":
                return new EarthEnemy(this);
            case "Wind":
                return new WindEnemy(this);
            case "Ice":
                return new IceEnemy(this);
            default:
                return new ElectricEnemy(this);
        }
    }
    
    /**
     * Creates a Normal enemy with the specified level.
     * @param level
     * @return an Enemy
     */
    private Enemy createNormalEnemy(int level)
    {
        String element = selectEnemyElement();
        
        switch(element)
        {
            case "Water":
                return new WaterEnemy(this);
            case "Fire":
                return new FireEnemy(this);
            case "Earth":
                return new EarthEnemy(this);
            case "Wind":
                return new WindEnemy(this);
            case "Ice":
                return new IceEnemy(this);
            default:
                return new ElectricEnemy(this);
        }
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
        for(int i = 0; i < enemyTeamSize; i++)
        {
            switch(enemyTeamSize)
            {
                case 1:
                    enemyTeam.add(createRESIEnemy(this.MAX_ENEMY_LV - 1));
                    break;
                case 2:
                    enemyTeam.add(createRESIEnemy(this.MAX_ENEMY_LV - 2));
                    break;
            }
        }
    }
    
    private RESIEnemy createRESIEnemy(int level)
    {
        return new RESIEnemy(level, this);
    }
    
    public ArrayList<Enemy> makeNinlilBoss()
    {
        ArrayList<Attack> attacks = new ArrayList<>(4);
        attacks.add(new BuffAttack("Soaring Spirit", "The user using their high spirits to double their attack for 2 turns.", "Attack", 2.0, 3, 2));
        attacks.add(new OffensiveAttack("Aerial Dance", "The user flies into the air and dances around the target while dealing a flurry of quick blows.", 100, "Attack"));
        attacks.add(new OffensiveAttack("Hurricane", "The user creates a massive hurricane to damage the target.", 100, "R. Attack"));
        attacks.get(2).setAccuracy(90);
        OffensiveAttack tempestBlade = new OffensiveAttack("Tempest Blade", "Using blades made of pressurized air, the user slashes at the target. This has a high critical hit rate.", 85, "Attack");
        tempestBlade.setAccuracy(85);
        tempestBlade.setCritRate(0.35);
        attacks.add(tempestBlade);
        
        ArrayList<Integer> stats = new ArrayList<>(6);
        stats.add(355); // HP
        stats.add(80); // Attack
        stats.add(135); // Defense
        stats.add(350); // R Attack
        stats.add(140); // R Defense
        stats.add(135); // Speed
        
        BossEnemy ninlil = new BossEnemy("Ninlil", "Someone with a broken, grieving heart who needs a friend.", "Wind", 14, 
                                    attacks, stats);
        ninlil.setStatDescription("A currently grieving master of Wind.");
        
        ArrayList<Enemy> team = new ArrayList<>(1);
        team.add(ninlil);
        
        return team;
    }
    
    public ArrayList<Enemy> makeOmegaBoss()
    {
        ArrayList<Attack> attacks = new ArrayList<>(4);
        attacks.add(new BuffAttack("System Restart", "The user restarts its systems to refresh itself, raising all stats.", "All", 2.0, 5, 2));
        OffensiveAttack deathClaw = new OffensiveAttack("Death Claw", "The user flies into the air and launches a claw that captures the target and applies pressure to deal damage.", 130, "Attack");
        deathClaw.setAccuracy(90);
        attacks.add(deathClaw);
        attacks.add(new OffensiveAttack("Omega Laser", "The user blasts a highly concentrated laser that destroys everyting in its path.", 100, "R. Attack"));
        attacks.get(2).setAccuracy(90);
        OffensiveAttack protocal = new OffensiveAttack("RESI Protocal: Omega", "The user fires lasers, missles, claws and explosives in every direction. Those hit rarely live to tell the tale.", 150, "Attack");
        protocal.setAccuracy(55);
        attacks.add(protocal);
        
        ArrayList<Integer> stats = new ArrayList<>(6);
        stats.add(1550); // HP
        stats.add(204); // Attack
        stats.add(234); // Defense
        stats.add(204); // R Attack
        stats.add(204); // R Defense
        stats.add(174); // Speed
        
        BossEnemy omega = new BossEnemy("R.E.S.I. Bot Omega", "The first of its kind. Irwin's best R.E.S.I. Bot so far.", "Fire", 17, 
                                    attacks, stats);
        omega.setStatDescription("This R.E.S.I. Bot is only suited for hot areas, so its element won't change!");
        
        ArrayList<Enemy> team = new ArrayList<>(1);
        team.add(omega);
        
        return team;
    }
    
    public ArrayList<Enemy> makeFrigsBoss()
    {
        ArrayList<Attack> attacks = new ArrayList<>(4);
        attacks.add(new BuffAttack("Frosted Knuckles", "The user creates a layer of ice on their fists to increase their attack for 3 turns.", "Attack", 4, 3));
        attacks.add(new OffensiveAttack("Avalanche", "The user attacks by causing an avalanche to fall on the target.", 85, "Attack"));
        attacks.add(new OffensiveAttack("Icicle Slash", "The user creates many sharp icicles to slash at the target.", 95, "Attack"));
        attacks.get(2).setAccuracy(95);
        OffensiveAttack fimblevetr = new OffensiveAttack("Fimbulvetr", "The user blasts the target with what feels like an eternal blizzard. Has a higher chance to land a critical hit.", 90, "R. Attack");
        fimblevetr.setCritRate(0.35);
        fimblevetr.setAccuracy(90);
        attacks.add(fimblevetr);

        ArrayList<Integer> stats = new ArrayList<>(6);
        stats.add(350); // HP
        stats.add(390); // Attack
        stats.add(120); // Defense
        stats.add(360); // R Attack
        stats.add(120); // R Defense
        stats.add(270); // Speed
        
        BossEnemy frigs = new BossEnemy("Frigs", "A greiving friend who needs friends that'll stick closer than a lost brother.", "Ice", 21, 
                                    attacks, stats);
        frigs.setStatDescription("A currently grieving master of Ice.");
        
        ArrayList<Enemy> team = new ArrayList<>(1);
        team.add(frigs);
        
        return team;
    }
    
    public ArrayList<Enemy> makeFultraBoss(Wilderness currentLocation)
    {
        ArrayList<Attack> attacks = new ArrayList<>(4);
        attacks.add(new BuffAttack("Charge II", "The user charges themselves with high amounts of electricity. All stats are increased for 3 turns, and cooldown is less than Charge.", "All", 3, 3));
        attacks.add(new OffensiveAttack("Blackbolt", "The user brings down a massive, destructive lightning bolt that is dark in color.", 115, "R. Attack"));
        attacks.get(1).setAccuracy(85);
        attacks.add(new OffensiveAttack("Plasma Blast II", "The user discharges a overwhelming electrical wave. Its accuracy and power are better than normal.", 180, "R. Attack"));
        attacks.get(2).setAccuracy(51); 
        OffensiveAttack overdriveII = new OffensiveAttack("Overdrive II", "The user coats themselves in electricity and rams into the target. It now has a higher crit rate.", 115, "Attack");
        overdriveII.setCritRate(0.35);
        overdriveII.setAccuracy(75);
        attacks.add(overdriveII);
        
        ArrayList<Integer> stats = new ArrayList<>(6);
        stats.add(777); // HP
        stats.add(324); // Attack
        stats.add(324); // Defense
        stats.add(324); // R Attack
        stats.add(324); // R Defense
        stats.add(324); // Speed
        
        BossEnemy fultra = new BossEnemy("R.E.S.I. Fultra", "Fultra in a new form. Has he betrayed everyone?", "Electric", 27, 
                                    attacks, stats);
        fultra.setStatDescription("'Fearless Thunder' in a new form. Has he lived up to his name?");
        
        ArrayList<Enemy> team = new ArrayList<>(3);
        team.add(new RESIEnemy(currentLocation));
        team.add(fultra);
        team.add(new RESIEnemy(currentLocation));

        // Specify the R.E.S.I. enemies
        team.get(0).setMaxHealth(800);
        team.get(0).setCurrentHealth(800);
        team.get(0).setElement("Ice");
        team.get(0).setStatSpreadDesc("Its element won't change in this battle!");
        team.get(2).setMaxHealth(800);
        team.get(2).setCurrentHealth(800);
        team.get(2).setElement("Earth");
        team.get(0).setStatSpreadDesc("Its element won't change in this battle!");

        
        return team;
    }
    
    public ArrayList<Enemy> makeIrwinBoss()
    {
        // Creating R.E.S.I. Omega II ----------------------------------------------------------------------------------------
        ArrayList<Attack> omegaAttacks = new ArrayList<>(4);
        omegaAttacks.add(new BuffAttack("System Restart", "The user restarts its systems to refresh itself, raising all stats.", "All", 2.0, 5, 2));
        OffensiveAttack deathClaw = new OffensiveAttack("Death Claw", "The user flies into the air and launches a claw that captures the target and applies pressure to deal damage.", 130, "Attack");
        deathClaw.setAccuracy(70);
        omegaAttacks.add(deathClaw);
        omegaAttacks.add(new OffensiveAttack("Omega Laser", "The user blasts a highly concentrated laser that destroys everyting in its path.", 100, "R. Attack"));
        omegaAttacks.get(2).setAccuracy(90);
        OffensiveAttack protocal = new OffensiveAttack("RESI Protocal: Omega", "The user fires lasers, missles, claws and explosives in every direction. Those hit rarely live to tell the tale.", 150, "Attack");
        protocal.setAccuracy(55);
        omegaAttacks.add(protocal);
        
        ArrayList<Integer> omegaStats = new ArrayList<>(6);
        omegaStats.add(780); // HP
        omegaStats.add(340); // Attack
        omegaStats.add(340); // Defense
        omegaStats.add(340); // R Attack
        omegaStats.add(340); // R Defense
        omegaStats.add(200); // Speed
        
        BossEnemy omegaII = new BossEnemy("R.E.S.I. Bot Omega II", "The second of its kind. Irwin's attempt at redemption for the first one's failure.", "Fire", 26, 
                                    omegaAttacks, omegaStats);
        omegaII.setStatDescription("This R.E.S.I. Bot focuses on offense. Its element won't change!");
        // ----------------------------------------------------------------------------------------
        
        // Creating Irwin
        ArrayList<Attack> irwinAttacks = new ArrayList<>(4);
        irwinAttacks.add(new BuffAttack("Perfection Mindset", "The user thinks about their goals and fills themselves with determination, increasing their attack stats.", "Attack,R.Attack", 3, 2));
        irwinAttacks.add(new OffensiveAttack("Judgement", "The user judges the target for their imperfections.", 135, "R. Attack"));
        irwinAttacks.add(new OffensiveAttack("R.E.S.I. Blast", "Using R.E.S.I. technology, the user creates a concentrated blast to attack.", 100, "R. Attack"));
        irwinAttacks.add(new OffensiveAttack("R.E.S.I. Punch", "Using R.E.S.I. technology, the user punches with a metalic fist.", 100, "Attack"));
        
        irwinAttacks.get(1).setAccuracy(60);

        ArrayList<Integer> irwinStats = new ArrayList<>(6);
        irwinStats.add(777); // HP
        irwinStats.add(351); // Attack
        irwinStats.add(351); // Defense
        irwinStats.add(366); // R Attack
        irwinStats.add(351); // R Defense
        irwinStats.add(261); // Speed
        
        BossEnemy irwin = new BossEnemy("Irwin Krov", "A man trying to destroy the world to restart it in his image.", "Electric", 28, 
                                    irwinAttacks, irwinStats);
        irwin.setStatDescription("Perfection is his goal. He won't stop until he succeeds.");
        // ----------------------------------------------------------------------------------------
        
        // Creating R.E.S.I. Omega III
        ArrayList<Attack> omega2Attacks = new ArrayList<>(4);
        omega2Attacks.add(new BuffAttack("Reinforced Armor", "The user reinforces its armor to increase its defenses.", "Defense,R. Defense", 5, 2));
        omega2Attacks.add(deathClaw);
        omega2Attacks.add(new SingleHealingAttack("Ethrellium Injection", "The user injects their ally with pure ethrellium to heal them", 0.25, 3));
        omega2Attacks.add(protocal);
        
        ArrayList<Integer> omega2Stats = new ArrayList<>(6);
        omega2Stats.add(800); // HP
        omega2Stats.add(340); // Attack
        omega2Stats.add(340); // Defense
        omega2Stats.add(340); // R Attack
        omega2Stats.add(340); // R Defense
        omega2Stats.add(200); // Speed
        
        BossEnemy omegaIII = new BossEnemy("R.E.S.I. Bot Omega III", "The third -- and last -- of its kind. Irwin wishes it to crush you.", "Water", 26, 
                                    omega2Attacks, omega2Stats);
        omegaIII.setStatDescription("This R.E.S.I. Bot focuses on support. It's element won't change!");
        
        ArrayList<Enemy> team = new ArrayList<>(3);
        team.add(omegaII);
        team.add(irwin);
        team.add(omegaIII);
        
        return team;
    }
    
    public ArrayList<Enemy> makeFinalBoss()
    {
        ArrayList<Attack> attacks = new ArrayList<>(4);
        attacks.add(new BuffAttack("Perfectionist", "The user removes their imperfections to increase all stats.", "All", 5, 3));
        attacks.add(new OffensiveAttack("Perfect Strike", "The user attacks with a forceful strike. It cannot miss.", 100, "Attack"));
        attacks.add(new OffensiveAttack("Perfect Blast", "Using concentrated energy of the user's element, an energy blast is fired. It cannot miss.", 100, "R. Attack"));
        attacks.add(new OffensiveAttack("Divine Vision", "The vision of a new, divine world is what motivates the user. An onslaught of attacks is then launched at the target.", 125, "R. Attack"));
        attacks.get(3).setAccuracy(85);
        
        ArrayList<Integer> stats = new ArrayList<>(6);
        stats.add(888); // HP
        stats.add(420); // Attack
        stats.add(420); // Defense
        stats.add(420); // R Attack
        stats.add(420); // R Defense
        stats.add(420); // Speed
        
        BossEnemy perfectedIrwin = new BossEnemy("Perfected Irwin", "He's one step closer to accomplishing his goals.", "Electric", 35, 
                                    attacks, stats);
        perfectedIrwin.setStatDescription("He's one step closer to creating the perfect world.");
        
        ArrayList<Enemy> team = new ArrayList<>(1);
        team.add(perfectedIrwin);
        
        return team;
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }
}
