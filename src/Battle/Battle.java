package Battle;

import Game.Game;
import Game.MainGame;
import java.io.Serializable;
import Utilites.MenuHelper;
import Utilites.EnemySpeedComparator;
import Utilites.LinkedQueue;
import Utilites.PlayerSpeedComparator;
import Utilites.Sort;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for starting, processing, and ending battles.
 * @author Ian King
 */
public abstract class Battle implements Serializable
{
    protected static int currentTurn;
    protected int baseGoldAmt = 140;
    protected BattleInterface BATTLE_INTERFACE;
    private static String[] battleInfo;
    private ArrayList<Enemy> enemyTeam;
    protected ArrayList<Enemy> originalEnemyPositions = new ArrayList<>();
    private ArrayList<Player> PLAYER_FIGHTING_TEAM = new ArrayList<>(3);
    private ArrayList<Player> PLAYER_TEAM;
    private ArrayList<Player> ORIGINAL_PLAYER_POSITIONS = new ArrayList<>();
    private ArrayList<Character> TURN_ORDER = new ArrayList<>(6);
    private static ArrayList<Character> statAffectedCharacters = new ArrayList<>();
    private boolean comboAttackUsed;
    protected String startingText;
    protected boolean won;
    protected boolean forfeit;
    
    public Battle(ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
    {
        String[] info = {"Turn " + currentTurn};
        battleInfo = info;
        this.enemyTeam = enemyTeam;
        this.PLAYER_TEAM = playerTeam;
        getOriginalEnemyPositions();
        BATTLE_INTERFACE = new BattleInterface(originalEnemyPositions, ORIGINAL_PLAYER_POSITIONS);
    }
    
    public Battle(Enemy enemy, Player player)
    {
        String[] info = {"Turn " + currentTurn};
        battleInfo = info;
        
        this.enemyTeam = new ArrayList<>();
        enemyTeam.add(enemy);
        this.PLAYER_TEAM = new ArrayList<>();
        PLAYER_TEAM.add(player);
        
        getOriginalEnemyPositions();
        BATTLE_INTERFACE = new BattleInterface(originalEnemyPositions, ORIGINAL_PLAYER_POSITIONS);
    }
    
    public static int getCurrentTurn() {return currentTurn;}
    public static ArrayList<Character> getStatAffectedCharacters() {return statAffectedCharacters;}
    
    private void getOriginalEnemyPositions()
    {
        for(Enemy e : enemyTeam)
        {
            originalEnemyPositions.add(e);
        }
    }
    
    /**
     * Starts the method and increases or decreases gold amount if player wins or loses.
     * @param gold 
     * @param result 
     */
    public void start(int gold, boolean result)
    {   
        MainGame.printlnln(startingText);
        turnSetup();
        beforeBattlePrompt();
        BATTLE_INTERFACE = new BattleInterface(originalEnemyPositions, ORIGINAL_PLAYER_POSITIONS);
        BATTLE_INTERFACE.setBattleInfo(battleInfo);
        MainGame.promptToEnter();
        
        while(!PLAYER_FIGHTING_TEAM.isEmpty() || !enemyTeam.isEmpty())
        {
            printTurnInfo();
            
            if(currentTurn == 1)
            {
                BATTLE_INTERFACE.printBattleInterfaceBNW();
            }
            else
            {
                BATTLE_INTERFACE.reprintBattleInterfaceBNW();
            }
            
            while(!TURN_ORDER.isEmpty())
            {
                // Activates the enemy or player's turn
                if(TURN_ORDER.get(0) instanceof Enemy)
                {
                    Enemy enemy = (Enemy)TURN_ORDER.remove(0);
                    MainGame.println("Starting " + enemy.getName() + "'s turn:");
                    activateEnemyAI(enemy);
                }
                else
                {
                    Player player = (Player)TURN_ORDER.remove(0);
                    MainGame.printlnln("Starting " + player.getName() + "'s turn:");
                    activatePlayerTurn(player);
                    
                    // Checks if player forfeit. If so, exit battle loop
                    if(forfeit) {break;}
                }
                
                // MainGame.promptToEnter();
                
                if(enemyTeam.isEmpty())
                {
                    won = true;
                    break;
                }
                else if(PLAYER_FIGHTING_TEAM.isEmpty())
                {
                    won = false;
                    break;
                }
                
                // Removes the Character that just completed their turn for the next one to go.
                if(!TURN_ORDER.isEmpty())
                {
                    MainGame.clearScreen();
                    completeTurn();
                }
            }

            // If the battle's final result is determined, escape loop
            if(forfeit || won)
            {
                break;
            }
            else if(!PLAYER_FIGHTING_TEAM.isEmpty() && !enemyTeam.isEmpty())
            {
                refreshBattle();
            }
            else if(!won) // must check if the battle is lost AFTER checking if a team was defeated.
            {
                break;
            }
        }
        
        if(won)
        {
            won();
        }
        else
        {
            lost();
        }
        
        result = won;
        
        currentTurn = 0;
        resetPlayers();
    }
    
    public boolean isWon() {return won;}
    
    private void printTurnInfo()
    {
        MainGame.printlnln("Start: Turn " + currentTurn);
        MainGame.printlnln("Turn " + currentTurn + " turn order:");
        
        for(Object o : TURN_ORDER)
        {
            if(o instanceof Player)
            {
                Player p = (Player)o;
                MainGame.println("\t" + p.getName());
            }
            else if(o instanceof Enemy)
            {
                Enemy e = (Enemy)o;
                MainGame.println("\t" + e.getName());
            }
            System.out.println("");
        }
    }
    
    private void beforeBattlePrompt()
    {
        positionPlayers();
        
        if(!PLAYER_TEAM.isEmpty())
        {
            positionCheerPlayers();
        }
        
        populateTurnOrder();
        
//        String message = "Would you like to view player and enemy stats before the battle starts?\n\t1) Yes\n\t2) No";
//        int response = MenuHelper.displayMenu(message, 1, 2);
//
//        switch(response)
//        {
//            case 1:
//                System.out.println("");
//                MainGame.println("Teammate's Stat Information:");
//                checkAllTeammatesStats();
//                MainGame.println("Enemies' Stat Information:");
//                checkAllEnemyStats();
//        }

        System.out.println("");
    }
    
    private void positionPlayers()
    {
        MainGame.printlnln("Incoming foe(s):");
        listEnemies();
        System.out.println("");
        
        // If the player only has one character, they will automatically be selected
        if(PLAYER_TEAM.size() == 1)
        {
            MainGame.println(PLAYER_TEAM.get(0).getName() + " will fight.");
            addPlayerTo(PLAYER_FIGHTING_TEAM, ORIGINAL_PLAYER_POSITIONS, 1);
        }
        // If the team can't have a cheer partner, this process allows to select the order without much else
        else if(PLAYER_TEAM.size() < 4)
        {
//            while(!PLAYER_TEAM.isEmpty() && PLAYER_TEAM.size() != 3)
            printEnemyInfoTable();
            System.out.println("");
            
            while(!PLAYER_TEAM.isEmpty())
            {
                if(PLAYER_TEAM.size() == 1)
                {
                    MainGame.println(PLAYER_TEAM.get(0).getName() + " will fight.");
                    addPlayerTo(PLAYER_FIGHTING_TEAM, ORIGINAL_PLAYER_POSITIONS, 1);
                    break;
                }
                
                positionPlayerProcess();
                // The 4 lines below are in the positionPlayerProcess method
//                message = "Who would you like to be positioned ";
//                message = loopThroughPlayerTeam(message);
//                int response = MenuHelper.displayMenu(message, 1, PLAYER_TEAM.size());
//                addPlayerTo(PLAYER_FIGHTING_TEAM, ORIGINAL_PLAYER_POSITIONS, response);
            }
            
            sortPlayerTeam();
        }
        // If the team size is 4 or greater, this allows for choosing who to fight with and assign cheer partners.
        else
        {
            printEnemyInfoTable();
            System.out.println("");
            
            while(ORIGINAL_PLAYER_POSITIONS.size() != 3)
            {
                positionPlayerProcess();
            }
            
            sortPlayerTeam();
        }
            
            
        
//        message = "Who would you like to be positioned second?";
//        message = loopThroughPlayerTeam(message);
//        
//        response = MenuHelper.displayMenu(message, 1, PLAYER_TEAM.size());
//        addPlayerTo(PLAYER_FIGHTING_TEAM, ORIGINAL_PLAYER_POSITIONS, response);
////        addPlayerTo(ORIGINAL_PLAYER_POSITIONS, response);
//        
//        message = "Who would you like to be positioned third?";
//        message = loopThroughPlayerTeam(message);
//        
//        response = MenuHelper.displayMenu(message, 1, PLAYER_TEAM.size());
//        addPlayerTo(PLAYER_FIGHTING_TEAM, ORIGINAL_PLAYER_POSITIONS, response);
////        addPlayerTo(ORIGINAL_PLAYER_POSITIONS, response);

//        if(PLAYER_FIGHTING_TEAM.size() > 1)
//        {
//            sortPlayerTeam();
//        }
    }
    
    /**
     * Completes the rest of the steps for positioning the characters the player chooses to fight with.
     */
    private void positionPlayerProcess()
    {
        String message;
        message = "Who would you like to be positioned ";
        message = loopThroughPlayerTeam(message);
        int response = MenuHelper.displayMenu(message, 1, PLAYER_TEAM.size());
        addPlayerTo(PLAYER_FIGHTING_TEAM, ORIGINAL_PLAYER_POSITIONS, response);
    }
        
    
    protected void printEnemyInfoTable()
    {
        BATTLE_INTERFACE.formatEnemyInfo();
        BATTLE_INTERFACE.printEnemyInfoBNW();
    }
    
    private void listEnemies()
    {
        Enemy enemy;
        
        for(int i = 0; i < enemyTeam.size(); i++)
        {
            enemy = enemyTeam.get(i);
            MainGame.println(enemy.getName() + ": " + enemy.getStatSpreadDesc());
        }
    }
    
    private void positionCheerPlayers()
    {
        if(ORIGINAL_PLAYER_POSITIONS.size() > 0)
        {
            selectPartners();
        }
    }
    
    /**
     * Takes the player through the process of assigning cheer partners to the characters that will fight.
     */
    private void selectPartners()
    {
        int response;
        
        for(Player p : ORIGINAL_PLAYER_POSITIONS)
        {   
            String message = "Who would you like to be " + p.getName() + "'s cheer partner?";
            message = loopForCheerSelection(p, message);

            message += "\n\t" + (PLAYER_TEAM.size() + 1)  + ") No one";

            response = MenuHelper.displayMenu(message, 1, PLAYER_TEAM.size() + 1);

            addPlayerToCheer(response, p);
            
            // If there are no more potential characters to assign as cheer partners, end the loop
            if(PLAYER_TEAM.isEmpty())
            {
                break;
            }
            
            // If size is greater than 1, prompt player to choose
//            if(PLAYER_TEAM.size() != 1)
//            {
//                String message = "Who would you like to be " + p.getName() + "'s cheer partner?";
//                message = loopThroughPlayerTeam(message);
//                response = MenuHelper.displayMenu(message, 1, PLAYER_TEAM.size());
//                addPlayerToCheer(response, p);
//            }
//            else
//            {
//                Player cheer = PLAYER_TEAM.remove(0);
//                MainGame.println(cheer.getName() + " will be " + p.getName() + "'s cheer partner.");
//                p.setCheerPartner(cheer);
//                cheer.setPlayerToCheer(p);
//                cheer.printCheerReadyMessage();
//            }
        }
    }
    
    private void addPlayerTo(ArrayList<Player> list1, ArrayList<Player> list2, int response)
    {
        Player player = PLAYER_TEAM.remove(response - 1);
        list1.add(player);
        list2.add(player);
        
        player.printBattleReadyMessage();
    }
    
    private void addPlayerToCheer(int response, Player player)
    {
        if(response == PLAYER_TEAM.size() + 1)
        {
            return;
        }
        
        Player cheer = PLAYER_TEAM.remove(response - 1);
        player.setCheerPartner(cheer);
        cheer.setPlayerToCheer(player);
        cheer.printCheerReadyMessage();
    }
    
    private String addPositionMessage(String message)
    {
        if(ORIGINAL_PLAYER_POSITIONS.isEmpty())
        {
            message += "first?";
        }
        else if(ORIGINAL_PLAYER_POSITIONS.size() == 1)
        {
            message += "second?";
        }
        else
        {
            message += "third?";
        }
        
        return message;
    }
    
    /**
     * Returns a string that completes the message variable by adding first, second, or third depending on PLAYER_TEAM size.
     * @param message
     * @return String
     */
    private String loopThroughPlayerTeam(String message)
    {
        message = addPositionMessage(message);
        
        int num = 0;
        
        for(Player p : PLAYER_TEAM)
        {
            message += "\n\t" + ++num  + ") " + p.getName() + ": " + p.getElement() + 
                        "   (" + p.getPlayerClass().toString() + ")";
        }
        
        return message;
    }
    
    /**
     * Returns a message showing the options the player has for who can be a cheer partner.
     * @param message
     * @return String
     */
    private String loopForCheerSelection(Player p, String message)
    {
        int num = 0;
        
        for(Player cheer : PLAYER_TEAM)
        {
            message += "\n\t" + ++num  + ") " + cheer.getName();
            message = addComboSymbol(p, cheer, message);
        }
        
        return message;
    }
    
    /**
     * Takes the fighting player and the potential cheer partner; if their elements allow for a combo, a star is added to the name.
     * @param fighting
     * @param cheer
     * @param message
     * @return String to complete the given message
     */
    private String addComboSymbol(Player fighting, Player cheer, String message)
    { 
        if(ComboAttack.hasElementCompatibility(fighting, cheer))
        {
            message += (" (!)");
            return message;
        }
        else
        {
            return message;
        }
    }
    
    private void turnSetup()
    {
        ++currentTurn;
        battleInfo = new String[]{"Turn " + currentTurn};
        BATTLE_INTERFACE.setBattleInfo(battleInfo);
        deactivateStatChanges();
        sortPlayerTeam();
        sortEnemyTeam();
    }
    
    private void refreshBattle()
    {
        MainGame.clearScreen();
        turnSetup();
        populateTurnOrder();
    }
    
    /**
     * Sorts the player team based on each player's speed stat.
     */
    private void sortPlayerTeam()
    {
        if(PLAYER_FIGHTING_TEAM.size() > 1)
        {
            Player[] temp = new Player[PLAYER_FIGHTING_TEAM.size()];

            for(int i = 0; i < PLAYER_FIGHTING_TEAM.size(); i++)
            {
                temp[i] = PLAYER_FIGHTING_TEAM.get(i);
            }

            PlayerSpeedComparator psc = new PlayerSpeedComparator();
            Sort.mergeSort(temp, psc);

            for(int i = 0; i < PLAYER_FIGHTING_TEAM.size(); i++)
            {
                PLAYER_FIGHTING_TEAM.set(i, temp[i]);
            }
        }
    }
    
    /**
     * Sorts the enemy team based on each enemy's speed stat.
     */
    private void sortEnemyTeam()
    {
        if(enemyTeam.size() > 1)
        {
            Enemy[] temp = new Enemy[enemyTeam.size()];

            for(int i = 0; i < enemyTeam.size(); i++)
            {
                temp[i] = enemyTeam.get(i);
            }

            EnemySpeedComparator esc = new EnemySpeedComparator();
            Sort.mergeSort(temp, esc);

            for(int i = 0; i < enemyTeam.size(); i++)
            {
                enemyTeam.set(i, temp[i]);
            }
        }
    }
    
    /**
     * Creates an ArrayList to store all enemies and players in the order of their speed stats.
     */
    private void populateTurnOrder()
    {   
        while(!PLAYER_FIGHTING_TEAM.isEmpty() || !enemyTeam.isEmpty())
        {
            if(enemyTeam.isEmpty())
            {
                TURN_ORDER.add(PLAYER_FIGHTING_TEAM.remove(0));
            }
            else if(PLAYER_FIGHTING_TEAM.isEmpty())
            {
                TURN_ORDER.add(enemyTeam.remove(0));
            }
            else if(!enemyTeam.isEmpty() && enemyTeam.get(0).getSpeed().getValue() > PLAYER_FIGHTING_TEAM.get(0).getSpeed().getValue())
            {
                TURN_ORDER.add(enemyTeam.remove(0));
            }
            else 
            {
                TURN_ORDER.add(PLAYER_FIGHTING_TEAM.remove(0));
            }
        }
        
        // Adds the Character objects back to their respective lists
        for(int i = 0; i < TURN_ORDER.size(); i++)
        {
            if(TURN_ORDER.get(i) instanceof Enemy)
            {
                enemyTeam.add((Enemy)(TURN_ORDER.get(i)));
            }
            else
            {
                PLAYER_FIGHTING_TEAM.add((Player)(TURN_ORDER.get(i)));
            }
        }
    }
    
    /**
     * Method starts a player's turn and prompts them for input.
     * @param player 
     */
    private void activatePlayerTurn(Player player)
    {
        int response;
        String message;
        
        if(ComboAttack.canUse(player) && comboAttackUsed == false)
        {
            message = "What would you like " + player.getName() + " to do?\n\t1) Attack\n\t2) Combo Attack\n\t3) Inventory"
                    + "\n\t4) Check " + player.getName() + "\n\t5) Check Enemy\n\t6) Forfeit";
            response = MenuHelper.displayMenu(message, 1, 6);
            
            switch (response) 
            {
                // If wanting to attack
                case 1:
                    wantToAttack(message, response, player);
                    break;
                //If wanting to combo attack
                case 2:
                    wantToCombo(message, response, player);
                    break;
                // If wanting to look at inventory
                case 3:
                    lookAtInventory(message, response, player);
                    break;
                // If wanting to look at the character being used
                case 4:
                    checkPlayer(player);
                    break;
                // If wanting to look at an enemy
                case 5:
                    checkEnemy(player);
                    break;
                // If wanting to forfeit the battle
                case 6:
                    forfeit(player);
            }
        }
        else
        {
            message = "What would you like " + player.getName() + " to do?\n\t1) Attack\n\t2) Inventory\n\t3) Check " + player.getName() + 
                    "\n\t4) Check Enemy\n\t5) Forfeit";
            response = MenuHelper.displayMenu(message, 1, 5);
            
            switch (response) 
            {
                // If wanting to attack
                case 1:
                    wantToAttack(message, response, player);
                    break;
                // If wanting to look at inventory
                case 2:
                    lookAtInventory(message, response, player);
                    break;
                // If wanting to look at the character being used
                case 3:
                    checkPlayer(player);
                    break;
                // If wanting to look at an enemy
                case 4:
                    checkEnemy(player);
                    break;
                case 5:
                    forfeit(player);
            }
        }    
        
        if(!player.isDead())
        {
            activateCheerPartner(player);
        }
    }
    
    /**
     * If the player wants to forfeit a battle, they will end the battle, but it will count as a loss.
     */
    private void forfeit(Player player)
    {
        MainGame.println("\nDo you want to forfeit? Doing so will count as a loss.");
        
        int input = MenuHelper.displayMenu("\t1) Yes\n\t2) No", 1, 2);
        
        switch(input)
        {
            case 1: 
                forfeit = true;
                break;
            case 2: 
                System.out.println(""); // formatting
                activatePlayerTurn(player); 
                break;
        }
    }
    
    private void activateCheerPartner(Player player)
    {
        if((!(enemyTeam.isEmpty())) && (player.getCheerPartner() != null))
        {
            Player cheer = player.getCheerPartner();
            activateCheerAbility(cheer);
        }
    }
    
    private void activateCheerAbility(Player cheer)
    {
        Random rand = new Random();
        int chance = rand.nextInt(20);
        
        if(chance == 0)
        {
            PlayerClass pc = cheer.getPlayerClass();
            Player playerToCheer = cheer.getPlayerToCheer();
            
            MainGame.printlnln(cheer.getName() + "'s cheer skill was activiated!");
            
            switch (pc.getPrimaryRole())
            {
                case "Clerk":
                    if(!playerToCheer.isHealthy())
                    {
                        int amt = (int)(playerToCheer.getMaxHealth() * Stat.get_cheer_buff_value());
                        playerToCheer.setCurrentHealth(amt);
                        MainGame.printlnln(cheer.getName() + " healed " + playerToCheer.getName() + " " + amt + " HP!");
                    }
                    else
                    {
                        MainGame.printlnln("But " + cheer.getName() + " couldn't do anything!");
                        MainGame.printlnln(cheer.getName() + ": Sorry about that!");
                    }
                break;
                case "Tank":
                    Stat defense = playerToCheer.getDefense();
                    // playerToCheer.setDefense((int)Math.round(defense.getValue() * Stat.get_cheer_buff_value()));
                    MainGame.printlnln(cheer.getName() + " increased " + playerToCheer.getName() + "'s defense by 20%!");
                    defense.increaseCheerBuff(Stat.get_cheer_buff_value());
                    
                    defense = playerToCheer.getRangedDefense();
                    // playerToCheer.setRangedDefense((int)Math.round(defense.getValue() * Stat.get_cheer_buff_value()));
                    MainGame.printlnln(cheer.getName() + " increased " + playerToCheer.getName() + "'s ranged defense by 20%!");
                    defense.increaseCheerBuff(Stat.get_cheer_buff_value());
                break;
                case "Striker":
                    Stat attack = playerToCheer.getAttack();
                    // playerToCheer.setAttack((int)Math.round(attack.getValue() * Stat.get_cheer_buff_value()));
                    MainGame.printlnln(cheer.getName() + " increased " + playerToCheer.getName() + "'s attack by 20%!");
                    attack.increaseCheerBuff(Stat.get_cheer_buff_value());
                    
                    attack = playerToCheer.getRangedAttack();
                    // playerToCheer.setRangedAttack((int)Math.round(attack.getValue() * Stat.get_cheer_buff_value()));
                    MainGame.printlnln(cheer.getName() + " increased " + playerToCheer.getName() + "'s ranged attack by 20%!");
                    attack.increaseCheerBuff(Stat.get_cheer_buff_value());
                break;
                default:
                    Stat speed = playerToCheer.getSpeed();
                    // playerToCheer.setSpeed((int)Math.round(speed.getValue() * Stat.get_cheer_buff_value()));
                    MainGame.printlnln(cheer.getName() + " increased " + playerToCheer.getName() + "'s speed by 20%!");
                    speed.increaseCheerBuff(Stat.get_cheer_buff_value());
                break;
            }
        }
    }
    
    /**
     * Asks the player to select an attack to use.
     * @param message
     * @param response
     * @param player 
     */
    private void wantToAttack(String message, int response, Player player)
    {
        message = "\nSelect an attack:\n\t1) " + player.getCurrentAttacks().get(0).getNameWithAttackType() + "\n\t2) " + 
                    player.getCurrentAttacks().get(1).getNameWithAttackType()+ "\n\t3) " + 
                    player.getCurrentAttacks().get(2).getNameWithAttackType()+ "\n\t4) " + 
                    player.getCurrentAttacks().get(3).getNameWithAttackType()+ "\n\t5) Back";
        response = MenuHelper.displayMenu(message, 1, 5);
        
        if(response != 5)
        {
            Attack attack = player.getCurrentAttacks().get(response - 1);
            
            if(attack.canUse(currentTurn))
            {
                if(attack instanceof OffensiveAttack || attack instanceof DebuffAttack)
                {
                    selectEnemyTarget(attack, message, response, player);
                }
                else if(attack instanceof BuffAttack)
                {
                    player.increaseAggro(attack);
                    BuffAttack buff = (BuffAttack)attack;
                    buff.activateBuff(player);
                }
                else if(attack instanceof SingleHealingAttack)
                {
                    healAlly(message, response, player, attack);
                }
                else if(attack instanceof TeamHealingAttack)
                {
                    MainGame.println("\n" + player.getName() + " used " + attack.getName() + " and healed the team!");
                    MainGame.promptToEnter();
                    player.increaseAggro(attack);
                    TeamHealingAttack heal = (TeamHealingAttack)attack;
                    heal.healPlayers(PLAYER_FIGHTING_TEAM, currentTurn);
                }
            }
//            else if(attack instanceof OffensiveAttack)
//            {
//                selectEnemyTarget(attack, message, response, player);
//            }
            else
            {
//                int leftoverCooldown = attack.getCooldown() - currentTurn;
                MainGame.println("\n" + attack.getName() + " can't used until turn " + attack.getNextAvailableTurn() + "!");
                wantToAttack(message, response, player);
            }
        }
        // Go back to previous menu
        else
        {
            System.out.println("");
            activatePlayerTurn(player);
        }
    }
    
    private void wantToCombo(String message, int response, Player player)
    {
        ComboAttack combo = player.getComboAttack();
        message = "\nWould you like " + player.getName() + " and " + player.getCheerPartner().getName() + " to use "
                + combo.getName() + "?\n\t1) Yes\n\t2) No";
        
        response = MenuHelper.displayMenu(message, 1, 2);
        
        switch (response)
        {
            case 1:
                selectEnemyTarget(combo, message, response, player);
                comboAttackUsed = true;
                break;
            case 2:
                activatePlayerTurn(player);
                break;
        }
    }
    
    /**
     * Asks the player to use the previously selected attack on a target.
     * @param attack
     * @param message
     * @param response
     * @param player 
     */
    protected void selectEnemyTarget(Attack attack, String message, int response, Player player)
    {
        Enemy target;
        ArrayList<Enemy> enemies = getAdjacentEnemies(player);
        
        // Selects which enemy to attack
        switch (enemies.size()) 
        {
            case 3:
                message = "\nWho would you like to attack?\n\t1) " + enemies.get(0).getName() + "\n\t2) " +
                        enemies.get(1).getName() + "\n\t3) " + enemies.get(2).getName() + "\n\t4) Back";
                response = MenuHelper.displayMenu(message, 1, 4);
                
                if(response != 4)
                {
                    target = enemies.get(response - 1);
                    attackEnemy(attack, player, target);
                }
                // Go back to previous menu
                else
                {
                    wantToAttack(message, response, player);
                }   
                break;
            case 2:
                message = "\nWho would you like to attack?\n\t1) " + enemies.get(0).getName() + "\n\t2) " +
                        enemies.get(1).getName() + "\n\t3) Back";
                response = MenuHelper.displayMenu(message, 1, 3);
                
                if(response != 3)
                {
                    target = enemies.get(response - 1);
                    attackEnemy(attack, player, target);
                }
                // Go back to previous menu
                else
                {
                    wantToAttack(message, response, player);
                }   
                break;
            default:
                target = enemies.get(0);
                attackEnemy(attack, player, target);
                break;
        }
        
//        // Attack the target
//        if(attack instanceof OffensiveAttack)
//        {
//            attackWithOffense(attack, player, target);
////            ((OffensiveAttack) attack).attack(player, target);
////            player.increaseAggro(attack);
//        }
//        else if(attack instanceof DebuffAttack)
//        {
//            DebuffAttack debuff = (DebuffAttack)attack;
//            debuff.activateDebuff(player, target);
//            player.increaseAggro(attack);
//        }
//        else if(attack instanceof ComboAttack)
//        {
//            ((ComboAttack) attack).attack(player, player.getCheerPartner(), target);
//            player.increaseAggro(attack);
//        }
//        
//        if(target.getCurrentHealth() == 0)
//        {
//            MainGame.printlnln(player.getName() + " defeated " + target.getName() + "!");
//            TURN_ORDER.remove(target);
//            enemyTeam.remove(target);
//            originalEnemyPositions.remove(target);
//            
//            player.increaseXP(player, ORIGINAL_PLAYER_POSITIONS, target);
//        }
    }
    
    private void attackEnemy(Attack attack, Player player, Enemy target)
    {
        // Attack the target
        if(attack instanceof OffensiveAttack || attack instanceof ComboAttack)
        {
            attackWithOffense(attack, player, target);
//            ((OffensiveAttack) attack).attack(player, target);
//            player.increaseAggro(attack);
        }
        else if(attack instanceof DebuffAttack)
        {
            attackWithDebuff(attack, player, target);
//            DebuffAttack debuff = (DebuffAttack)attack;
//            debuff.activateDebuff(player, target);
//            player.increaseAggro(attack);
        }
//        else if(attack instanceof ComboAttack)
//        {
//            ((ComboAttack) attack).attack(player, player.getCheerPartner(), target);
//            player.increaseAggro(attack);
//        }
        
//        if(target.getCurrentHealth() == 0)
//        {
//            MainGame.printlnln(player.getName() + " defeated " + target.getName() + "!");
//            TURN_ORDER.remove(target);
//            enemyTeam.remove(target);
//            originalEnemyPositions.remove(target);
//            
//            player.increaseXP(player, ORIGINAL_PLAYER_POSITIONS, target);
//        }
    }
    
    protected void attackWithOffense(Attack attack, Player player, Enemy target)
    {
        // Attack the target
        if(attack instanceof OffensiveAttack)
        {
            ((OffensiveAttack) attack).attack(player, target);
            player.increaseAggro(attack);
        }
        else if(attack instanceof ComboAttack)
        {
            ((ComboAttack) attack).attack(player, player.getCheerPartner(), target);
            player.increaseAggro(attack);
        }
        
        if(target.getCurrentHealth() == 0)
        {
            MainGame.printlnln(player.getName() + " defeated " + target.getName() + "!");
            MainGame.promptToEnter();
            TURN_ORDER.remove(target);
            enemyTeam.remove(target);
            originalEnemyPositions.remove(target);
            removeDeadEnemyStats(target);
            
            // The Beach Tutorial Battle forces a special type of level up, so the player doesn't need to see the xp gained
            if(!(this instanceof BeachTutorialBattle))
            {
                player.increaseXP(player, ORIGINAL_PLAYER_POSITIONS, target);
            }
        }
    }
    
    private void attackWithDebuff(Attack attack, Player player, Enemy target)
    {
        DebuffAttack debuff = (DebuffAttack)attack;
        debuff.activateDebuff(player, target);
        player.increaseAggro(attack);
    }
    
    private void checkPlayer(Player player)
    {
        MainGame.printlnln("\n" + player.toString());
        
        MainGame.println("Attacks:");
        player.listKnownAttacks();
        MainGame.waitForEnter();
        System.out.println("");
        
        activatePlayerTurn(player);
    }
    
    private void checkEnemy(Player player)
    {
        String message = "\nWho would you like to check?";
        int numOfOptions = 0;
        
        for(Enemy e : originalEnemyPositions)
        {
            message += "\n\t" + ++numOfOptions + ") " + e.getName();
        }
        
        message += "\n\t" + ++numOfOptions + ") Back";
        
        int input = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        if(input == numOfOptions)
        {
            activatePlayerTurn(player);
        }
        else
        {
            Enemy enemy = originalEnemyPositions.get(--input);
            
            displayEnemyInfo(enemy, player);
        }
    }
    
    private void displayEnemyInfo(Enemy enemy, Player player)
    {
        MainGame.printlnln("\n" + enemy.toBattleString());
        MainGame.waitForEnter();
        System.out.println("");
        
        activatePlayerTurn(player);
    }
    
    /**
     * Asks the user which item they want to use from their inventory.
     * @param message
     * @param response
     * @param player 
     */
    private void lookAtInventory(String message, int response, Player player)
    {
        message = "\nWhat item would you like to use?";
        
        int numOfOptions = 0;
        Item item;
        
        // Asks for which Item the player would like
        if(!Game.getInventory().isEmpty())
        {
            numOfOptions = Game.getInventory().size() + 1;
            
            message += Game.getInventory().inventoryListForMenus();
            
            message += "\n\t" + numOfOptions + ") Back";

            response = MenuHelper.displayMenu(message, 1, numOfOptions);
        
            // If the player chooses "Back," go back
            if(Game.getInventory().isEmpty() || response == numOfOptions)
            {
                System.out.println("");
                activatePlayerTurn(player);
            }
            else
            {
                item = Game.getInventory().get(--response);
                useOnPlayer(message, response, player, item);
    //            response = MenuHelper.displayMenu(message, 1, numOfOptions);
            }
        }
        else
        {
            // If there are no items, take them back to the main battle menu
            
            MainGame.printlnln("\nYou currently have no items.");
            MainGame.waitForEnter();
            System.out.println("");
            activatePlayerTurn(player);
        }
        
//        // Handles if the player chooses back or to use an item
//        if((response == 1 && numOfOptions == 1) || response == numOfOptions)
//        {
//            activatePlayerTurn(player);
//        }
//        else
//        {
//            useOnPlayer(message, response, player, item);
//        }
    }
    
    /**
     * Uses the previously selected item on a selected player.
     * @param message
     * @param response
     * @param player
     * @param item 
     */
    private void useOnPlayer(String message, int response, Player player, Item item)
    {
        message = "\nSelect a player you would like to use the " + item.getName() + " on:";
        int numOfOptions = 0;
        
        for(int i = 0; i < PLAYER_FIGHTING_TEAM.size(); i++)
        {
            message += "\n\t" + (i + 1) + ") " + PLAYER_FIGHTING_TEAM.get(i).getName();
            numOfOptions++;
        }
        
        message += "\n\t" + ++numOfOptions + ") Back";
        
        response = MenuHelper.displayMenu(message, 1, PLAYER_FIGHTING_TEAM.size() + 1);
        
        if(response != numOfOptions)
        {
            if(item instanceof HealingItem)
            {
                item = (HealingItem)item;
            }
            else
            {
                item = (BuffItem)item;
            }
            
            item.use(player);
        }
        // Go back to previous menu
        else
        {
            lookAtInventory(message, response, player);
        }
    }
    
    /**
     * Heals an adjacent ally (only applies for SingleHealAttacks).
     * @param message
     * @param response
     * @param player
     * @param attack 
     */
    private void healAlly(String message, int response, Player player, Attack attack)
    {
        message = "\nSelect who you would like to heal:";
        ArrayList<Player> adjacentAllies;
        
        if(player.getPlayerClass().toString().equals("Master Clerk"))
        {
            // Only Master Clerks can heal anyone from anywhere
            adjacentAllies = PLAYER_FIGHTING_TEAM;
        }
        else
        {
            // Otherwise, only adjacent allies can be healed.
            adjacentAllies = getAdjacentAllies(player);
        }
        
        int numOfOptions = 0;
        
        for(int i = 0; i < adjacentAllies.size(); i++)
        {
            message += "\n\t" + (i + 1) + ") " + adjacentAllies.get(i).getName();
            numOfOptions++;
        }
        
        message += "\n\t" + ++numOfOptions + ") Back";
        
        response = MenuHelper.displayMenu(message, 1, numOfOptions);
        
        if(response != numOfOptions)
        {
            Player ally = adjacentAllies.get(response - 1);
            
            if(ally.equals(player))
            {
                MainGame.printlnln("\n" + player.getName() + " healed themself!");
            }
            else
            {
                MainGame.printlnln("\n" + player.getName() + " healed " + ally.getName() + "!");
            }
            
            player.increaseAggro(attack);
            SingleHealingAttack heal = (SingleHealingAttack)attack;
            heal.activateHealing(ally);
        }
        // Go back to previous menu
        else
        {
            wantToAttack(message, response, player);
        }
    }
    
    /**
     * Allows an enemy to make a choice for what they want to do.
     * @param enemy 
     */
    protected void activateEnemyAI(Enemy enemy)
    {
        ArrayList<Player> adjacentPlayers = getAdjacentPlayers(enemy);
        
        // delete after testing the bug where the enemy does nothing
        boolean success = false;

        // Defeats player if possible. If possible, end enemy AI; else, move to the next statement
        if(defeatedPlayer(adjacentPlayers, enemy))
        {
            success = true;
            // System.out.println("Killed player: " + success);
        }
        // Prioritizes healing allies if applicable
        else if (canHealEnemyAlly(enemy))
        {
            success = true;
            healEnemyAlly(enemy);
            // System.out.println("Healed ally: " + success);
        }
        // Then decides to heal team if applicable
        else if(canHealEnemyTeam(enemy))
        {
            success = true;
            healEnemyTeam(enemy);
            // System.out.println("Healed team: " + success);
        }
        // 50% percent chance for the enemy to attack before considering to buff itself
        else if(new Random().nextBoolean())
        {
            success = true;
            attackPlayer(enemy, adjacentPlayers);
            // System.out.println("Attacked player before buff: " + success);
        }
        // Will buff itself if possible
        else if(enemy.hasBuffAttack() && enemy.getBuffAttack().canUse(currentTurn))
        {
            success = true;
            BuffAttack buff = enemy.getBuffAttack();
            buff.activateBuff(enemy);
            // System.out.println("Buffed self: " + success);
        }
        // 50% percent chance for the enemy to attack before considering to debuff a player's character
        else if(new Random().nextBoolean())
        {
            success = true;
            attackPlayer(enemy, adjacentPlayers);
            // System.out.println("Attacked player before debuff: " + success);
        }
        // Will debuff a target if enemy has a debuff attack and the highest aggroed player doesn't have a debuff
        else if(enemy.hasDebuffAttack() && canDebuffHighestAggro(enemy, adjacentPlayers))
        {
            success = true;
            DebuffAttack debuff = enemy.getDebuffAttack();
            Player target = Player.getHighestAggro(adjacentPlayers);
            debuff.activateDebuff(enemy, target);
            // System.out.println("Debuffed player: " + success);
        }
        // If nothing else, guarentee an attack
        else
        {
            success = true;
            attackPlayer(enemy, adjacentPlayers);
            // System.out.println("Attacked player on else: " + success);
        }

        if(!success)
        {
            System.out.println("No if statement reached");
            MainGame.promptToEnter();
        }
    }

   
    private boolean canDebuffHighestAggro(Enemy enemy, ArrayList<Player> adjacentPlayers)
    {
        Player target = Player.getHighestAggro(adjacentPlayers);
        return !target.hasActiveDebuff();
    }
    
    protected void attackPlayer(Enemy enemy, ArrayList<Player> adjacentPlayers)
    {
        /*
        The enemy will target the player with the highest aggro always. 
        If it's not the highest aggro within the entire team, it'll get the highest aggroed player from the adjacent 
            players.
        */ 
        Player target = Player.getHighestAggro(adjacentPlayers);

        OffensiveAttack attack = enemy.getOffensiveAttack();
        attack.attack(enemy, target);

        if(target.isDead())
        {
            removeDeadPlayer(enemy, target, attack);
        }
    }

    /**
     * Finds which Player object the enemy can defeat. Returns a boolean representing if defeating the player was successful.
     * @param adjacentPlayers
     * @param enemy
     * @return
     */
    private boolean defeatedPlayer(ArrayList<Player> adjacentPlayers, Enemy enemy)
    {
        // For each player and OffensiveAttack, check if the damage kills them. If so, attack
        for(Player player : adjacentPlayers)
        {
            Player target = player; 
            
            for(OffensiveAttack attack : enemy.getOffensiveAttackList())
            {
                int damageOutput = attack.calculateDamage(enemy, target);
                
                // If the enemy's attack can kill the player's character, it will attack
                if(target.getCurrentHealth() - damageOutput <= 0)
                {
                    /* 
                    Call overloaded attack() method by using the damage output calculated earlier.
                    This method is used instead because of the random damage roll that is used to calculate damage.
                    */ 
                    attack.attack(damageOutput, enemy, target);
                    removeDeadPlayer(enemy, target, attack);
                    return true;
                }
            }
        }

        return false;
    }
    
    // private boolean canGetKill(ArrayList<Player> adjacentPlayers, Enemy enemy)
    // {
    //     boolean canKill = false;
        
    //     for(Player player : adjacentPlayers)
    //     {            
    //         for(OffensiveAttack attack : enemy.getOffensiveAttackList())
    //         {
    //             int damageOutput = ((OffensiveAttack) attack).calculateDamage(enemy, player);

    //             if(player.getCurrentHealth() - damageOutput <= 0)
    //             {
    //                 canKill = true;
    //                 break;
    //             }
    //         }
            
    //         if(canKill) {break;}
    //     }
        
    //     return canKill;
    // }
    
    /**
     * Looks at each Player and Enemy OffensiveAttack to determine the Player to kill. If no player can be killed, returns
     * null.
     * @param enemy
     * @return a Player or null if no Player will die
     */
    // private Player getPlayerToKill(Enemy enemy, ArrayList<Player> adjacentPlayers)
    // {   
    //     // For each player and OffensiveAttack, check if the damage kills them. If so, return them. If not, return null
    //     for(Player player : adjacentPlayers)
    //     {
    //         Player target = player; 
            
    //         for(OffensiveAttack attack : enemy.getOffensiveAttackList())
    //         {
    //             int damageOutput = ((OffensiveAttack) attack).calculateDamage(enemy, target);

    //             if(target.getCurrentHealth() - damageOutput <= 0)
    //             {
    //                 return player;
    //             }
    //         }
    //     }
        
    //     return null;
    // }
    
    // private void killPlayer(Enemy enemy, Player target)
    // {
    //     for(OffensiveAttack attack : enemy.getOffensiveAttackList())
    //     {
    //         int damageOutput = attack.calculateDamage(enemy, target);
            
    //         // If the enemy's attack can kill the player's character, it will attack
    //         if(target.getCurrentHealth() - damageOutput <= 0)
    //         {
    //             attack.attack(enemy, target);
    //             removeDeadPlayer(enemy, target, attack);
    //         }
    //     }
    // }
    
    private void removeDeadPlayer(Enemy enemy, Player target, OffensiveAttack attack)
    {
        /*
            It's necessary to check if the attack hit in this if statement because 
            attack.getHit() might be true due to hitting previously. So doing it here means it's properly
            updated. If it does hit, remove the player accordingly. 
        */
        if(attack.getAttackHit())
        {
            MainGame.printlnln(enemy.getName() + " defeated " + target.getName() + "!");

            target.printDeathMessage();
            PLAYER_FIGHTING_TEAM.remove(target);
            ORIGINAL_PLAYER_POSITIONS.remove(target);
            TURN_ORDER.remove(target);

            // Places the character back in the team ArrayList to continue the game
            target.setCurrentHealth(target.getMaxHealth());
            PLAYER_TEAM.add(target);

            // The cheer partner covers the person that was defeated in battle
            if(target.getCheerPartner() != null)
            {
                Player cheer = target.getCheerPartner();
                cheer.resetPlayerToCheer();
                target.resetCheerPartner();
                PLAYER_TEAM.add(cheer);
                MainGame.printlnln(cheer.getName() + " took " + target.getName() + " to protect them from futher harm.");
            }
            // If there is only one person left to fight, they defend the last person defeated
            else if(PLAYER_FIGHTING_TEAM.size() == 1)
            {
                String name = PLAYER_FIGHTING_TEAM.get(0).getName();
                MainGame.printlnln(name + " started defending " + target.getName() + " to prevent them from further harm.");
                MainGame.dialoguelnln(name, "Hang in there, " + target.getName() + "! I've got you!");
            }
            // If there is more than 1 person alive when the target is defeated
            else if(PLAYER_FIGHTING_TEAM.size() > 1)
            {
                MainGame.printlnln("The remainding fighters started defending " + target.getName() + " to prevent them from further harm.");
            }
            
            removeDeadPlayerStats(target); // will remove player from affected list and reset their stats
            target.resetAttacks();
            MainGame.promptToEnter();
        }
    }

    /**
     * If a Player dies, this will remove them from the list that holds which Characters had their stats affected.
     * @param player
     */
    private void removeDeadPlayerStats(Player player)
    {
        for(int i = 0; i < statAffectedCharacters.size(); i++)
        {
            if(statAffectedCharacters.get(i).equals((Character)player))
            {
                statAffectedCharacters.remove(i);
                player.resetStats(); // Reset the stats since the player is dead
                i--; // If a character was removed, we need to now check that same index for the next one
            }
        }
    }

    /**
     * If an Enemy dies, this will remove them from the list that holds which Characters had their stats affected.
     * @param player
     */
    private void removeDeadEnemyStats(Enemy enemy)
    {
        for(int i = 0; i < statAffectedCharacters.size(); i++)
        {
            if(statAffectedCharacters.get(i).equals((Character)enemy))
            {
                statAffectedCharacters.remove(i);
                i--; // If a character was removed, we need to now check that same index for the next one
            }
        }
    }
    
    private boolean canHealEnemyAlly(Enemy enemy)
    {
        return isAllyAlmostDead() && enemy.hasSingleHeal() && enemy.getSingleHeal().canUse(currentTurn);
    }
    
    private boolean canHealEnemyTeam(Enemy enemy)
    {
        return isAllyAlmostDead() && enemy.hasTeamHeal() && enemy.getTeamHeal().canUse(currentTurn);
    }
    
    private void healEnemyAlly(Enemy enemy)
    {
        Enemy enemyToHeal = findAlmostDead(enemy);
        int before = enemyToHeal.getCurrentHealth();
        SingleHealingAttack healing = enemy.getSingleHeal();
        healing.activateHealing(enemyToHeal);
        int after = enemyToHeal.getCurrentHealth();
        int amt = after - before;

        if(enemyToHeal.equals(enemy))
        {
            MainGame.printlnln("\n" + enemy.getName() + " used " + healing.getName() + " and gained " + amt + " HP!");
        }
        else
        {
            MainGame.printlnln("\n" + enemy.getName() + " used " + healing.getName() + " on " + enemyToHeal.getName() + " and they gained " + amt + " HP!");
        }
    }
    
    private void healEnemyTeam(Enemy enemy)
    {
        TeamHealingAttack healing = enemy.getTeamHeal();
        healing.healEnemies(enemyTeam);
        MainGame.printlnln("\n" + enemy.getName() + " healed itself and its allies!");
    }
    
    /**
     * Checks if an enemy ally has less than 20% HP left.
     * @return true if an enemy ally has low HP
     */
    private boolean isAllyAlmostDead()
    {
        for(Enemy enemy: enemyTeam)
        {
            if(enemy.getCurrentHealth() < enemy.getMaxHealth() * 0.2)
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Returns an enemy ally that has than 20% HP left.
     * @return enemy that has low HP
     */
    private Enemy findAlmostDead(Enemy enemy)
    {
        ArrayList<Enemy> allies = getAdjacentEnemyAllies(enemy);
        
        if(allies.size() > 1)
        {
            Random rand = new Random();
            
            return allies.get(rand.nextInt(allies.size()));
        }
        else
        {
            return allies.get(0);
        }
    }
    
    /**
     * Returns an ArrayList of the adjacent players an enemy can target.
     * @param enemy
     * @return an ArrayList of players
     */
    protected ArrayList<Player> getAdjacentPlayers(Enemy enemy)
    {
        ArrayList<Player> result = new ArrayList<>();
        
        int position = getEnemyPosition(enemy);
        
        // Loop finds where the enemy parameter is located within the ArrayList
//        for(int i = 0; i < originalEnemyPositions.size(); i++)
//        {
//            if(originalEnemyPositions.size() == 1)
//            {
//                position = 1;
//                break;
//            }
//            else if(originalEnemyPositions.get(i).equals(enemy))
//            {
//                if(originalEnemyPositions.size() == 2)
//                {
//                    position = 2;
//                }
//                else
//                {
//                    position = i;
//                }
//                    
//                break;
//            }
//        }
        
        // Adds the correct adjacent players to the list depending on the team's size
        switch(ORIGINAL_PLAYER_POSITIONS.size())
        {
            case 1:
                result.add(ORIGINAL_PLAYER_POSITIONS.get(0));
                break;
            case 2:
                switch (position) 
                {
                    case 0:
                        result.add(ORIGINAL_PLAYER_POSITIONS.get(0));
                        break;
                    case 1:
                        for(Player player : ORIGINAL_PLAYER_POSITIONS)
                        {
                            result.add(player);
                        }
                        break;
                    default:
                        result.add(ORIGINAL_PLAYER_POSITIONS.get(1));
                        break;
                }
                break;

            default:
                switch(position) 
                {
                    case 0:
                        result.add(ORIGINAL_PLAYER_POSITIONS.get(0));
                        result.add(ORIGINAL_PLAYER_POSITIONS.get(1));
                        break;
                    case 1:
                        for(Player player : ORIGINAL_PLAYER_POSITIONS)
                        {
                            result.add(player);
                        }
                        break;
                    default:
                        result.add(ORIGINAL_PLAYER_POSITIONS.get(1));
                        result.add(ORIGINAL_PLAYER_POSITIONS.get(2));
                        break;
                }
                break;
        }
        
        return result;
    }
    
    /**
     * Returns an ArrayList of the adjacent enemies a player can target.
     * @param player
     * @return 
     */
    private ArrayList<Enemy> getAdjacentEnemies(Player player)
    {
        ArrayList<Enemy> result = new ArrayList<>();
        
        int position = getPlayerPosition(player);
        
//        for(int i = 0; i < ORIGINAL_PLAYER_POSITIONS.size(); i++)
//        {
//            if(ORIGINAL_PLAYER_POSITIONS.size() == 1)
//            {
//                position = 1;
//                break;
//            }
//            else if(ORIGINAL_PLAYER_POSITIONS.get(i).equals(player))
//            {
//                position = i;
//                break;
//            }
//        }
        
        // Adds the correct adjacent enemies to the list depending on the team's size
        switch (originalEnemyPositions.size()) 
        {
            case 1:
                result.add(originalEnemyPositions.get(0));
                break;
            case 2:
                switch (position) 
                {
                    case 0:
                        result.add(originalEnemyPositions.get(0));
                        break;
                    case 1:
                        for(Enemy enemy : originalEnemyPositions)
                        {
                            result.add(enemy);
                        }
                        break;
                    default:
                        result.add(originalEnemyPositions.get(1));
                        break;
                }
                break;

            default:
                switch (position) 
                {
                    case 0:
                        result.add(originalEnemyPositions.get(0));
                        result.add(originalEnemyPositions.get(1));
                        break;
                    case 1:
                        for(Enemy enemy : originalEnemyPositions)
                        {
                            result.add(enemy);
                        }
                        break;
                    default:
                        result.add(originalEnemyPositions.get(1));
                        result.add(originalEnemyPositions.get(2));
                        break;
                }
                break;
        }
        
        return result;
    }
    
    /**
     * Returns the position of the given player determined by the interface.
     * @param player
     * @return player's position
     */
    private int getPlayerPosition(Player player)
    {
        int position = 0;
        
        // When the team size is 1, their position is 1
        if(ORIGINAL_PLAYER_POSITIONS.size() == 1)
        {
            return 1;
        }
        else if(ORIGINAL_PLAYER_POSITIONS.size() == 2)
        {
            // When the size is 2, the position is either 0 or 2 since the Character is on either side of the interface
            for(int i = 0; i < ORIGINAL_PLAYER_POSITIONS.size();)
            {
                if((i == 0) && (ORIGINAL_PLAYER_POSITIONS.get(i).equals(player)))
                {
                    return 0;
                }
                else
                {
                    return 2;
                }
            }
        }
        
        // If the team size is 3, loop through and find the position
        for(int i = 0; i < ORIGINAL_PLAYER_POSITIONS.size(); i++)
        {
            if(ORIGINAL_PLAYER_POSITIONS.get(i).equals(player))
            {
                position = i;
                break;
            }
        }
        
        return position;
    }
    
    /**
     * Returns the position of the given enemy determined by the interface.
     * @param enemy
     * @return enemy's position
     */
    private int getEnemyPosition(Enemy enemy)
    {
        int position = 0;
        
        // When the team size is 1, their position is 1
        if(originalEnemyPositions.size() == 1)
        {
            return 1;
        }
        else if(originalEnemyPositions.size() == 2)
        {
            // When the size is 2, the position is either 0 or 2 since the Character is on either side of the interface
            for(int i = 0; i < originalEnemyPositions.size();)
            {
                if((i == 0) && (originalEnemyPositions.get(i).equals(enemy)))
                {
                    return 0;
                }
                else
                {
                    return 2;
                }
            }
        }
        
        // If the team size is 3, loop through and find the position
        for(int i = 0; i < originalEnemyPositions.size(); i++)
        {
            if(originalEnemyPositions.get(i).equals(enemy))
            {
                position = i;
                break;
            }
        }
        
        return position;
    }
    
    /**
     * Returns an ArrayList of the adjacent player allies a player can target.
     * @param player
     * @return 
     */
    private ArrayList<Player> getAdjacentAllies(Player player)
    {
        ArrayList<Player> result = new ArrayList<>();
        
        int position = getPlayerPosition(player);
        
        // Master Clerk's can heal anyone no matter the adjacency
        // if(player.getPlayerClass().isMasterClerk())
        // {
        //     for(Player p : ORIGINAL_PLAYER_POSITIONS)
        //     {
        //         result.add(p);
        //     }
        // }
        // else
        // {
//            for(int i = 0; i < ORIGINAL_PLAYER_POSITIONS.size(); i++)
//            {
//                if(ORIGINAL_PLAYER_POSITIONS.size() == 1)
//                {
//                    position = 1;
//                    break;
//                }
//                else if(ORIGINAL_PLAYER_POSITIONS.get(i).equals(player))
//                {
//                    if(ORIGINAL_PLAYER_POSITIONS.size() == 2)
//                    {
//                        position = 2;
//                    }
//                    else
//                    {
//                        position = i;
//                    }
//
//                    break;
//                }
//            }
            
        switch (position) 
        {
            case 0:
                result.add(ORIGINAL_PLAYER_POSITIONS.get(0));

                // Add the other player if the size is 3
                if(ORIGINAL_PLAYER_POSITIONS.size() == 3)
                {
                    result.add(ORIGINAL_PLAYER_POSITIONS.get(1));
                }
                break;
            case 1:
                for(Player p : ORIGINAL_PLAYER_POSITIONS)
                {
                    result.add(p);
                }   
                break;
            default:
                result.add(ORIGINAL_PLAYER_POSITIONS.get(1));

                // Add the other player if the size is 3
                if(ORIGINAL_PLAYER_POSITIONS.size() == 3)
                {
                    result.add(ORIGINAL_PLAYER_POSITIONS.get(2));
                }
                break;
        }
        // }

        return result;
    }
    
    private ArrayList<Enemy> getAdjacentEnemyAllies(Enemy enemy)
    {
        ArrayList<Enemy> result = new ArrayList<>();
        
        int position = 0;
        
        for(int i = 0; i < enemyTeam.size(); i++)
        {
            // Check if team size is 1, the position is 1
            if(enemyTeam.size() == 1)
            {
                position = 1;
                break;
            }
            else if(enemyTeam.get(i).equals(enemy))
            {
                position = i;
                break;
            }
        }
        
        switch(position) 
        {
            case 0:
                result.add(originalEnemyPositions.get(0));
                result.add(originalEnemyPositions.get(1));
                break;
            case 1:
                for(Enemy e : originalEnemyPositions)
                {
                    result.add(e);
                }   
                break;
            default:
                result.add(originalEnemyPositions.get(1));
                result.add(originalEnemyPositions.get(2));
                break;
        }
        
        return result;
    }
    
    private int calculateGold(int gold, boolean won)
    {
        if(won)
        {
            int teamBonus = PLAYER_FIGHTING_TEAM.size();
            int highestLevel = Player.highestPlayerLV(PLAYER_FIGHTING_TEAM);
            int lowestLevel = Player.lowestPlayerLV(PLAYER_FIGHTING_TEAM);
            gold += (baseGoldAmt * teamBonus) + (highestLevel * 2) + lowestLevel;
        }
        else
        {
            gold += baseGoldAmt + (int)(gold * 0.333);
        }
        
        return gold;
    }
    
    /**
     * Completes an individual Character's turn.
     */
    private void completeTurn()
    {
        BATTLE_INTERFACE.reprintBattleInterfaceBNW();
    }
    
    private void won()
    {
        MainGame.clearScreen();
        MainGame.printlnln("You won! You recieved " + calculateGold(baseGoldAmt, true) + " G.");
        Game.increaseGold(calculateGold(baseGoldAmt, true));
        won = true;
    }
    
    private void lost()
    {
        MainGame.clearScreen();
        MainGame.print("You've been defeated");
        MainGame.printlnln("...");
        MainGame.printlnln("You lost " + calculateGold(baseGoldAmt, false) + " gold.");
        Game.decreaseGold(calculateGold(baseGoldAmt, false));
        won = false;
//        MainGame.println("You lost " + MainGame.ANSI_YELLOW + calculateGold(BASE_GOLD, false) + " gold." +
//                MainGame.ANSI_RESET, BASE_GOLD);
    }
    
    /**
     * Removes the buff or debuff that was applied earlier in the battle from the characters in the 
     * statAffectedCharacters list.
     */
    private void deactivateStatChanges()
    {
        // while(changedStats.get(0) != null && changedStats.first().isTurnChangeEnds(currentTurn))

        boolean success = false;
        while(!statAffectedCharacters.isEmpty() && (statAffectedCharacters.get(0)).canRemoveStatChange(currentTurn))
        {
            success = true;
            Character c = statAffectedCharacters.remove(0);
            for(Stat s : c.getStats())
            {
                if(s.isTurnBuffEnds(currentTurn))
                {
                    s.deactivateBuff();
                }
                
                if(s.isTurnDebuffEnds(currentTurn))
                {
                    s.deactivateDebuff();
                }
            }
        }

        if(success) {MainGame.promptToEnter();}
    }

    /**
     * Removes all modifications to a stat. Used at the end of the battle.
     */
    private void deactivateStatModifications(Player p)
    {
        for(Stat s : p.getStats())
        {
            if(s.getIsBuffActive())
            {
                s.deactivateBuffNoPrint();
            }
            
            if(s.getIsDebuffActive())
            {
                s.deactivateDebuffNoPrint();
            }
        }
    }
    
    private void resetPlayers()
    {   
        for(Player p : PLAYER_FIGHTING_TEAM)
        {
            p.resetAggro();
            p.resetAttacks();
            // deactivateStatModifications(p);

            PLAYER_TEAM.add(p);
            
            if(p.getCheerPartner() != null)
            {
                Player cheer = p.getCheerPartner();
                PLAYER_TEAM.add(cheer);
                cheer.resetPlayerToCheer();
                p.resetCheerPartner();
            }    
            
            p.resetStats();
            p.currentHealth = p.maxHealth;
            
            // for(Stat s : p.getStats())
            // {
            //     if(s.hasCheerBuff())
            //     {
            //         s.removeCheerBuff();
            //     }
                
            //     s.resetValue(p);
            // }

            // for(Attack a : p.getCurrentAttacks())
            // {
            //     a.resetNextAvailableTurn();
            // }
        }
    }
    
    // private nested battle interface class------------------------------------
    private class BattleInterface implements Serializable
    {
        private ArrayList<Enemy> enemyTeam;
        private ArrayList<Player> playerFightingTeam;
        private String[] battleInfo;
        private String[][] enemyInfo;
        private String[][] playerInfo;
//        private int decimalPlaceAccuracy;
        private int cellPadding;
        private int tableWidth;
        private int col0Width;
        private int col1Width;
        private int col2Width;

        private BattleInterface(ArrayList<Enemy> enemyTeamPositions, ArrayList<Player> playerFightingTeamPositions)
        {
            this.battleInfo = Battle.battleInfo;
            this.enemyTeam = enemyTeamPositions;
            this.playerFightingTeam = playerFightingTeamPositions;
            populateEnemyInfo();
            populatePlayerInfo();
            adjustCellPadding();
//            cellPadding = 6;
//            decimalPlaceAccuracy = 2;
        }
        
        private void setBattleInfo(String[] battleInfo)
        {
            this.battleInfo = battleInfo;
        }

        private void populateEnemyInfo()
        {
            enemyInfo = new String[4][3];

            switch (enemyTeam.size()) 
            {
                case 3:
                    {
                        Enemy enemy0 = enemyTeam.get(0);
                        Enemy enemy1 = enemyTeam.get(1);
                        Enemy enemy2 = enemyTeam.get(2);
                        enemyInfo[0][0] = "LV " + enemy0.getLevel();
                        enemyInfo[0][1] = "LV " + enemy1.getLevel();
                        enemyInfo[0][2] = "LV " + enemy2.getLevel();
                        enemyInfo[1][0] = enemy0.getName();
                        enemyInfo[1][1] = enemy1.getName();
                        enemyInfo[1][2] = enemy2.getName();
                        enemyInfo[2][0] = "HP: " + enemy0.getCurrentHealth() + "/" + enemy0.getMaxHealth();
                        enemyInfo[2][1] = "HP: " + enemy1.getCurrentHealth() + "/" + enemy1.getMaxHealth();
                        enemyInfo[2][2] = "HP: " + enemy2.getCurrentHealth() + "/" + enemy2.getMaxHealth();
                        enemyInfo[3][0] = "Element: " + enemy0.getElement();
                        enemyInfo[3][1] = "Element: " + enemy1.getElement();
                        enemyInfo[3][2] = "Element: " + enemy2.getElement();
                        break;
                    }
                case 2:
                    {
                        Enemy enemy0 = enemyTeam.get(0);
                        Enemy enemy1 = enemyTeam.get(1);
                        enemyInfo[0][0] = "LV " + enemy0.getLevel();
                        enemyInfo[0][2] = "LV " + enemy1.getLevel();
                        enemyInfo[1][0] = enemy0.getName();
                        enemyInfo[1][2] = enemy1.getName();
                        enemyInfo[2][0] = "HP: " + enemy0.getCurrentHealth() + "/" + enemy0.getMaxHealth();
                        enemyInfo[2][2] = "HP: " + enemy1.getCurrentHealth() + "/" + enemy1.getMaxHealth();
                        enemyInfo[3][0] = "Element: " + enemy0.getElement();
                        enemyInfo[3][2] = "Element: " + enemy1.getElement();
                        break;
                    }
                case 1:
                    {
                        Enemy enemy0 = enemyTeam.get(0);
                        enemyInfo[0][1] = "LV " + enemy0.getLevel();
                        enemyInfo[1][1] = enemy0.getName();
                        enemyInfo[2][1] = "HP: " + enemy0.getCurrentHealth() + "/" + enemy0.getMaxHealth();
                        enemyInfo[3][1] = "Element: " + enemy0.getElement();
                        break;
                    }
                default:
                    break;
            }
        }

        private void populatePlayerInfo()
        {
            playerInfo = new String[5][3];
            
            switch (playerFightingTeam.size()) 
            {
                case 3:
                    {
                        Player player0 = playerFightingTeam.get(0);
                        Player player1 = playerFightingTeam.get(1);
                        Player player2 = playerFightingTeam.get(2);
                        playerInfo[0][0] = "LV " + player0.getLevel();
                        playerInfo[0][1] = "LV " + player1.getLevel();
                        playerInfo[0][2] = "LV " + player2.getLevel();
                        
                        playerInfo[1][0] = player0.getNameWithAggroMarker(playerFightingTeam);
                        playerInfo[1][1] = player1.getNameWithAggroMarker(playerFightingTeam);
                        playerInfo[1][2] = player2.getNameWithAggroMarker(playerFightingTeam);
                        
                        playerInfo[2][0] = "HP: " + player0.getCurrentHealth() + "/" + player0.getMaxHealth();
                        playerInfo[2][1] = "HP: " + player1.getCurrentHealth() + "/" + player1.getMaxHealth();
                        playerInfo[2][2] = "HP: " + player2.getCurrentHealth() + "/" + player2.getMaxHealth();
                        
                        playerInfo[3][0] = "Element: " + player0.getElement();
                        playerInfo[3][1] = "Element: " + player1.getElement();
                        playerInfo[3][2] = "Element: " + player2.getElement();
                        
                        playerInfo[4][0] = "Cheer Partner: " + cheerPartnerText(player0);
                        playerInfo[4][1] = "Cheer Partner: " + cheerPartnerText(player1);
                        playerInfo[4][2] = "Cheer Partner: " + cheerPartnerText(player2);
                        
                        for(int i = 0; i < playerFightingTeam.size(); i++)
                        {
                            if(playerFightingTeam.get(i).getCheerPartner() != null)
                            {
                                playerInfo[4][i] = "Cheer Partner: " + playerFightingTeam.get(i).getCheerPartner().getName();
                            }
                        }
                        break;
                    }
                case 2:
                    {
                        Player player0 = playerFightingTeam.get(0);
                        Player player1 = playerFightingTeam.get(1);
                        playerInfo[0][0] = "LV " + player0.getLevel();
                        playerInfo[0][2] = "LV " + player1.getLevel();
                        
                        playerInfo[1][0] = player0.getNameWithAggroMarker(playerFightingTeam);
                        playerInfo[1][2] = player1.getNameWithAggroMarker(playerFightingTeam);
                        
                        playerInfo[2][0] = "HP: " + player0.getCurrentHealth() + "/" + player0.getMaxHealth();
                        playerInfo[2][2] = "HP: " + player1.getCurrentHealth() + "/" + player1.getMaxHealth();
                        
                        playerInfo[3][0] = "Element: " + player0.getElement();
                        playerInfo[3][2] = "Element: " + player1.getElement();
                        
                        playerInfo[4][0] = "Cheer Partner: " + cheerPartnerText(player0);
                        playerInfo[4][2] = "Cheer Partner: " + cheerPartnerText(player1);
                        break;
                    }
                case 1:
                    {
                        Player player0 = playerFightingTeam.get(0);
                        playerInfo[0][1] = "LV " + player0.getLevel();
                        playerInfo[1][1] = player0.getNameWithAggroMarker(playerFightingTeam);
                        playerInfo[2][1] = "HP: " + player0.getCurrentHealth() + "/" + player0.getMaxHealth();
                        playerInfo[3][1] = "Element: " + player0.getElement();
                        playerInfo[4][1] = "Cheer Partner: " + cheerPartnerText(player0);
                        break;
                    }
                default:
                    break;
            }
        }

        private String cheerPartnerText(Player player)
        {
            if(player.getCheerPartner() == null)
            {
                return "None";
            }
            
            return player.getCheerPartner().getName();
        }
        
        public void setCellPadding(int newCellPadding)
        {
            cellPadding = newCellPadding;
        }

//        public void setDecimalPlaceAccuracy(int newDecimalPlaceAccuracy)
//        {
//            decimalPlaceAccuracy = newDecimalPlaceAccuracy;
//        }

        /**
         * Adds a space to every table title if its length is odd.
         */
        public void formatBattleInfo()
        {
            // Ensures every title has an even length to aid with centering
            for(int i = 0; i < battleInfo.length; i++)
            {
                if(battleInfo[i].length() % 2 == 1)
                {
                    battleInfo[i] += " ";
                }
            }
        }

        /**
         * Adds a space to every column title if its length is odd.
         */
        public void formatEnemyInfo()
        {
            String[][] result = new String[enemyInfo.length][enemyInfo[0].length];

            for(int row = 0; row < enemyInfo.length; row++)
            {
                for(int col = 0; col < enemyInfo[row].length; col++)
                {
                    if(enemyInfo[row][col] != null)
                    {
                        result[row][col] = enemyInfo[row][col];

                        if(result[row][col].length() % 2 == 1)
                        {
                            result[row][col] += " ";
                        }
                    }
                }
            }

            enemyInfo = result;
            result = null;
        }

        /**
         * Formats the 2D array of data depending on what its type is.
         */
        public void formatPlayerInfo()
        {   
//            Player.addAggroMarker(playerFightingTeam);
            
            String[][] result = new String[playerInfo.length][playerInfo[0].length];

            for(int row = 0; row < playerInfo.length; row++)
            {
                for(int col = 0; col < playerInfo[row].length; col++)
                {
                    if(playerInfo[row][col] != null)
                    {
                        result[row][col] = playerInfo[row][col];

                        if(result[row][col].length() % 2 == 1)
                        {
                            result[row][col] += " ";
                        }
                    }
                }
            }

            playerInfo = result;
            result = null;
        }

        /**
         * Finds the total width of the entire ASCII Table.
         * @return 
         */
        private int determineTableWidth()
        {
            int longestBattleInfoWidth = 0;
            int sumOfEnemyInfoWidths = 0;
            int sumOfPlayerInfoWidths = 0;
            int result = 0;
//            int separatorLineLength = separatorLineLength();
            int numOfColumns = playerInfo[0].length;

            // Finds the longest title in the array of titles
            for(String title : battleInfo)
            {
                if(title.length() > longestBattleInfoWidth)
                {
                    longestBattleInfoWidth = title.length();
                }
            }

            for(int col = 0; col < enemyInfo[0].length; col++)
            {
                sumOfPlayerInfoWidths += findWidestWidth(enemyInfo, col);
            }

            for(int col = 0; col < enemyInfo[0].length; col++)
            {
                sumOfEnemyInfoWidths += findWidestWidth(playerInfo, col);
            }

            if(longestBattleInfoWidth > sumOfPlayerInfoWidths && longestBattleInfoWidth > sumOfEnemyInfoWidths)
            {
                result = longestBattleInfoWidth;
            }
            else if(sumOfPlayerInfoWidths > longestBattleInfoWidth && sumOfPlayerInfoWidths > sumOfEnemyInfoWidths)
            {
                result = sumOfPlayerInfoWidths;
            }
            else if(sumOfEnemyInfoWidths > longestBattleInfoWidth && sumOfEnemyInfoWidths > sumOfPlayerInfoWidths)
            {
                result = sumOfEnemyInfoWidths;
            }

            /*
            Returns the total length of the table by adding how much cell padding and 
            how many + symbols there will be for each column

            If statement is a buffer that checks if the result is the correct length or not
            */

            result = (col0Width + col1Width + col2Width) + (numOfColumns * (cellPadding * 2)) + (numOfColumns -1);

            if(result % 2 == 1)
            {
                result++;
            }

            tableWidth = result;

            return result;
        }

        /**
         * Prints the array of table titles.
         */
        private void printBattleInfo()
        {
            if(battleInfo != null)
            {
                for(String info : battleInfo)
                {   
                    int numOfSpaces = tableWidth - info.length();

                    System.out.printf("%1s" + " ".repeat(numOfSpaces / 2) + "%" + info.length() + "s" + 
                            " ".repeat(numOfSpaces / 2) + "%1s", "|", info, "|\n");
                }

                printSeparatorLine();
            }
        }

        /**
         * Prints the separator line.
         */
        private void printSeparatorLine()
        {
            int columnWidth;

            for(int col = 0; col < playerInfo[0].length; col++)
            {
                columnWidth = compareColumnWidths(col);
                MainGame.print("+" + "-".repeat(columnWidth + (cellPadding * 2)));
//                System.out.printf("+" + "-".repeat(columnWidth + (cellPadding * 2)));
            }

            System.out.printf("+\n");
        }

        private int separatorLineLength()
        {
            int columnWidth;
            String separatorLine = "";

            for(int col = 0; col < playerInfo[0].length; col++)
            {
                if(playerInfo[0][col] != null && enemyInfo[0][col] != null)
                {
                    columnWidth = compareColumnWidths(col);

                    separatorLine += "+" + "-".repeat(columnWidth + (cellPadding * 2));
                }
            }

            /*
            I subtract 2 because I don't count the + or |'s at the edges of the table
            */
            return separatorLine.length() - 2;
        }

        /**
         * Prints all Enemy info in color.
         */
        private void printEnemyInfo()
        {
            printSeparatorLine();

            if(enemyInfo != null)
            {
                int numOfSpaces;
                int col = 0;

                for(String[] innerArray : enemyInfo)
                {
                    System.out.printf("|");

                    for(String title : innerArray)
                    {
                        if(title != null)
                        {
                            int columnWidth = compareColumnWidths(col) + (cellPadding * 2);

                            numOfSpaces = columnWidth - title.length();

                            System.out.printf(MainGame.ANSI_RED + " ".repeat(numOfSpaces / 2) + title + MainGame.ANSI_RESET + " ".repeat(numOfSpaces / 2) + "|");

                            col++;
                        }   
                        else
                        {
                            if(col == 0)
                            {
                                numOfSpaces = col0Width + (cellPadding * 2);
                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                            else if(col == 1)
                            {
                                numOfSpaces = col1Width + (cellPadding * 2);
                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                            else
                            {
                                numOfSpaces = col2Width + (cellPadding * 2);
                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                        }
                    }

                    System.out.printf("\n");
                    col = 0;
                }

                printSeparatorLine();
            }
        }
        
        /**
         * Prints all Enemy info in black and white.
         */
        private void printEnemyInfoBNW()
        {
            printSeparatorLine();

            if(enemyInfo != null)
            {
                int numOfSpaces;
                int col = 0;

                for(String[] innerArray : enemyInfo)
                {
                    System.out.printf("|");

                    for(String title : innerArray)
                    {
                        if(title != null)
                        {
                            int columnWidth = compareColumnWidths(col) + (cellPadding * 2);

                            numOfSpaces = columnWidth - title.length();

                            MainGame.print(" ".repeat(numOfSpaces / 2) + title + " ".repeat(numOfSpaces / 2) + "|");
                            
//                            System.out.printf(" ".repeat(numOfSpaces / 2) + title + " ".repeat(numOfSpaces / 2) + "|");

                            col++;
                        }   
                        else
                        {
                            if(col == 0)
                            {
                                numOfSpaces = col0Width + (cellPadding * 2);
                                
                                MainGame.print(" ".repeat(numOfSpaces) + "|");
//                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                            else if(col == 1)
                            {
                                numOfSpaces = col1Width + (cellPadding * 2);
                                MainGame.print(" ".repeat(numOfSpaces) + "|");
//                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                            else
                            {
                                numOfSpaces = col2Width + (cellPadding * 2);
                                MainGame.print(" ".repeat(numOfSpaces) + "|");
//                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                        }
                    }

                    System.out.printf("\n");
                    col = 0;
                }

                printSeparatorLine();
            }
        }

        /**
         * Prints all Player info in color.
         */
        private void printPlayerInfo()
        {
            printSeparatorLine();

            if(playerInfo != null)
            {
                int numOfSpaces;
                int col = 0;

                for(String[] innerArray : playerInfo)
                {
                    System.out.printf("|");

                    for(String title : innerArray)
                    {
                        if(title != null)
                        {
                            int columnWidth = compareColumnWidths(col) + (cellPadding * 2);

                            numOfSpaces = columnWidth - title.length();

                            System.out.printf(MainGame.ANSI_BLUE + " ".repeat(numOfSpaces / 2) + title + " ".repeat(numOfSpaces / 2) + MainGame.ANSI_RESET + "|");

                            col++;
                        }   
                        else
                        {
                            if(col == 0)
                            {
                                numOfSpaces = col0Width + (cellPadding * 2);
                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                            else if(col == 1)
                            {
                                numOfSpaces = col1Width + (cellPadding * 2);
                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                            else
                            {
                                numOfSpaces = col2Width + (cellPadding * 2);
                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                        }
                    }

                    System.out.printf("\n");
                    col = 0;
                }

                printSeparatorLine();
            }
        }

        /**
         * Prints all Player info in black and white.
         */
        private void printPlayerInfoBNW()
        {
            printSeparatorLine();

            if(playerInfo != null)
            {
                int numOfSpaces;
                int col = 0;

                for(String[] innerArray : playerInfo)
                {
                    System.out.printf("|");

                    for(String title : innerArray)
                    {
                        if(title != null)
                        {
                            int columnWidth = compareColumnWidths(col) + (cellPadding * 2);

                            numOfSpaces = columnWidth - title.length();
                            
                            MainGame.print(" ".repeat(numOfSpaces / 2) + title + " ".repeat(numOfSpaces / 2) + "|");

//                            System.out.printf(" ".repeat(numOfSpaces / 2) + title + " ".repeat(numOfSpaces / 2) + "|");

                            col++;
                        }   
                        else
                        {
                            if(col == 0)
                            {
                                numOfSpaces = col0Width + (cellPadding * 2);
                                MainGame.print(" ".repeat(numOfSpaces) + "|");
//                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                            else if(col == 1)
                            {
                                numOfSpaces = col1Width + (cellPadding * 2);
                                MainGame.print(" ".repeat(numOfSpaces) + "|");
//                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                            else
                            {
                                numOfSpaces = col2Width + (cellPadding * 2);
                                MainGame.print(" ".repeat(numOfSpaces) + "|");
//                                System.out.printf(" ".repeat(numOfSpaces) + "|");
                                col++;
                            }
                        }
                    }

                    System.out.printf("\n");
                    col = 0;
                }

                printSeparatorLine();
            }
        }
        
        /**
         * Prints the entire Battle Interface with color.
         */
        public void printBattleInterface()
        {
            formatPlayerInfo();
            formatBattleInfo();
            formatEnemyInfo();
            printSeparatorLine();
            determineTableWidth();
            printBattleInfo();
            System.out.println("\n");
            printEnemyInfo();
            System.out.println("\n");
            printPlayerInfo();
            System.out.printf("\n");
        }
        
        /**
         * Reprints the Battle Interface with updated information in color.
         */
        public void reprintBattleInterface()
        {
            populateEnemyInfo();
            populatePlayerInfo();
            formatPlayerInfo();
            formatBattleInfo();
            formatEnemyInfo();
            printSeparatorLine();
            determineTableWidth();
            printBattleInfo();
            System.out.println("\n");
            printEnemyInfo();
            System.out.println("\n");
            printPlayerInfo();
            System.out.printf("\n");
        }

        /**
         * Prints the entire Battle Interface in black and white.
         */
        public void printBattleInterfaceBNW()
        {
//            Player.addAggroMarker(playerFightingTeam);
//            System.out.println("\n");
            formatPlayerInfo();
            formatBattleInfo();
            formatEnemyInfo();
            printSeparatorLine();
            determineTableWidth();
            printBattleInfo();
            System.out.println("\n");
            printEnemyInfoBNW();
            System.out.println("\n");
            printPlayerInfoBNW();
            System.out.printf("\n");
        }
        
        /**
         * Reprints the Battle Interface with updated information in black and white.
         */
        public void reprintBattleInterfaceBNW()
        {
//            System.out.println("\n");
            adjustCellPadding();
            populateEnemyInfo();
            populatePlayerInfo();
            formatPlayerInfo();
            formatBattleInfo();
            formatEnemyInfo();
            printSeparatorLine();
            determineTableWidth();
            printBattleInfo();
            System.out.println("\n");
            printEnemyInfoBNW();
            System.out.println("\n");
            printPlayerInfoBNW();
            System.out.printf("\n");
        }
        
        /**
         * Helper method returns the widest length of a given column by comparing column titles and data.
         * @param column
         * @return the given column's width
         */
        private int compareColumnWidths(int column)
        {
            int columnWidth;

            int playerColWidth = findWidestWidth(playerInfo, column);
            int enemyColWidth = findWidestWidth(enemyInfo, column);

            if(playerColWidth > enemyColWidth)
            {
                columnWidth = playerColWidth;
            }
            else
            {
                columnWidth = enemyColWidth;
            }

            if(columnWidth % 2 == 1)
            {
                columnWidth++;
            }

            switch (column) 
            {
                case 0:
                    col0Width = columnWidth;
                    break;
                case 1:
                    col1Width = columnWidth;
                    break;
                default:
                    col2Width = columnWidth;
                    break;
            }

            return columnWidth;
        }

        /**
         * Helper method finds the widest element in a given column; method is used in compareColumnWidths.
         * @param array
         * @param column
         * @return widest element in a column
         */
        private int findWidestWidth(String[][] array, int column)
        {
            int widest = 0; 

            for(int row = 0; row < array.length; row++)
            {
                if(array[row][column] != null)
                {
                    if(widest < array[row][column].length())
                    {
                        widest = array[row][column].length();
                    }
                }   
            }

            return widest;
        }
        
        /**
         * Using the size of the enemy and player teams, this will either increase or reduce the cell padding.
         */
        private void adjustCellPadding()
        {
            // If they are both a size of 2, make cell padding 8 to better show the empty middle slot
            if((playerFightingTeam.size() == 2) && (enemyTeam.size() == 2))
            {
                cellPadding = 8;
            }
            // If the sizes are the same (3v3, 1v1), cell padding is 2
            else if(playerFightingTeam.size() == enemyTeam.size())
            {
                cellPadding = 2;
            }
            // Else (3v1, 2v1, etc.), make cell padding 4
            else
            {
                cellPadding = 4;
            }
        }
    }
}
