/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

// DELETE ME AFTER TESTS

import Battle.Enemy;
import Battle.Player;
import Game.MainGame;
import java.util.ArrayList;

public class BattleInterface
{
    private ArrayList<Enemy> enemyTeam;
    private ArrayList<Player> playerFightingTeam;
    private String[] battleInfo;
    private String[][] enemyInfo;
    private String[][] playerInfo;
    private int decimalPlaceAccuracy;
    private int cellPadding;
    private int tableWidth;
    private int col0Width;
    private int col1Width;
    private int col2Width;

    public BattleInterface(String[] battleInfo, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerFightingTeam)
    {
        this.battleInfo = battleInfo;
        this.enemyTeam = enemyTeam;
        this.playerFightingTeam = playerFightingTeam;
        populateEnemyInfo();
        populatePlayerInfo();
        cellPadding = 2;
        decimalPlaceAccuracy = 2;
    }

    private void populateEnemyInfo()
    {
        enemyInfo = new String[3][3];

        if(enemyTeam.size() >= 3)
        {
            Enemy enemy0 = enemyTeam.get(0);
            Enemy enemy1 = enemyTeam.get(1);
            Enemy enemy2 = enemyTeam.get(2);

            enemyInfo[0][0] = enemy0.getName();
            enemyInfo[0][1] = enemy1.getName();
            enemyInfo[0][2] = enemy2.getName();

            enemyInfo[1][0] = "HP: " + enemy0.getCurrentHealth() + "/" + enemy0.getMaxHealth();
            enemyInfo[1][1] = "HP: " + enemy1.getCurrentHealth() + "/" + enemy1.getMaxHealth();
            enemyInfo[1][2] = "HP: " + enemy2.getCurrentHealth() + "/" + enemy2.getMaxHealth();

            enemyInfo[2][0] = "Element: " + enemy0.getElement();
            enemyInfo[2][1] = "Element: " + enemy1.getElement();
            enemyInfo[2][2] = "Element: " + enemy2.getElement();
        }
        else if(enemyTeam.size() == 2)
        {
            Enemy enemy0 = enemyTeam.get(0);
            Enemy enemy1 = enemyTeam.get(1);
            
            enemyInfo[0][0] = enemy0.getName();
            enemyInfo[0][2] = enemy1.getName();
            
            enemyInfo[1][0] = "HP: " + enemy0.getCurrentHealth() + "/" + enemy0.getMaxHealth();
            enemyInfo[1][2] = "HP: " + enemy1.getCurrentHealth() + "/" + enemy1.getMaxHealth();
            
            enemyInfo[2][0] = "Element: " + enemy0.getElement();
            enemyInfo[2][2] = "Element: " + enemy1.getElement();
        }
        else if(enemyTeam.size() == 1)
        {
            Enemy enemy0 = enemyTeam.get(0);
            enemyInfo[0][1] = enemy0.getName();
            enemyInfo[1][1] = "HP: " + enemy0.getCurrentHealth() + "/" + enemy0.getMaxHealth();
            enemyInfo[2][1] = "Element: " + enemy0.getElement();
        }
            
    }

    private void populatePlayerInfo()
    {
        playerInfo = new String[3][3];

        switch (playerFightingTeam.size()) 
        {
            case 3:
                {
                    Player player0 = playerFightingTeam.get(0);
                    Player player1 = playerFightingTeam.get(1);
                    Player player2 = playerFightingTeam.get(2);
                    playerInfo[0][0] = player0.getName();
                    playerInfo[0][1] = player1.getName();
                    playerInfo[0][2] = player2.getName();
                    playerInfo[1][0] = "HP: " + player0.getCurrentHealth() + "/" + player0.getMaxHealth();
                    playerInfo[1][1] = "HP: " + player1.getCurrentHealth() + "/" + player1.getMaxHealth();
                    playerInfo[1][2] = "HP: " + player2.getCurrentHealth() + "/" + player2.getMaxHealth();
                    playerInfo[2][0] = "Element: " + player0.getElement();
                    playerInfo[2][1] = "Element: " + player1.getElement();
                    playerInfo[2][2] = "Element: " + player2.getElement();
                    break;
                }
            case 2:
                {
                    Player player0 = playerFightingTeam.get(0);
                    Player player1 = playerFightingTeam.get(1);
                    playerInfo[0][0] = player0.getName();
                    playerInfo[0][2] = player1.getName();
                    playerInfo[1][0] = "HP: " + player0.getCurrentHealth() + "/" + player0.getMaxHealth();
                    playerInfo[1][2] = "HP: " + player1.getCurrentHealth() + "/" + player1.getMaxHealth();
                    playerInfo[2][0] = "Element: " + player0.getElement();
                    playerInfo[2][2] = "Element: " + player1.getElement();
                    break;
                }
            case 1:
                {
                    Player player0 = playerFightingTeam.get(0);
                    playerInfo[0][1] = player0.getName();
                    playerInfo[1][1] = "HP: " + player0.getCurrentHealth() + "/" + player0.getMaxHealth();
                    playerInfo[2][1] = "Element: " + player0.getElement();
                    break;
                }
            default:
                break;
        }
    }

    public void setCellPadding(int newCellPadding)
    {
        cellPadding = newCellPadding;
    }

    public void setDecimalPlaceAccuracy(int newDecimalPlaceAccuracy)
    {
        decimalPlaceAccuracy = newDecimalPlaceAccuracy;
    }

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
        int separatorLineLength = separatorLineLength();
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
            System.out.printf("+" + "-".repeat(columnWidth + (cellPadding * 2)));
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
     * Prints all column titles.
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
     * Prints the 2D array of data.
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

                        System.out.printf(MainGame.ANSI_BLUE + " ".repeat(numOfSpaces / 2) + title + MainGame.ANSI_RESET + " ".repeat(numOfSpaces / 2) + "|");

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
     * Prints the entire ASCII Table.
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
}
