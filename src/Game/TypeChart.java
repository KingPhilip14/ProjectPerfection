package Game;

import Utilites.ElementStringComparator;
import java.util.ArrayList;

/**
 * A class for creating and displaying the type match-up chart.
 * @author Ian King
 */
public class TypeChart 
{
    private final String[] HEADER;
    private ArrayList<String> elements;
    private final ArrayList<Integer> COLUMN_SIZES;
    private String[][] chart;
    private final int CELL_PADDING;
    private int tableWidth;
    private int col0Width;
    private int col1Width;
    private int col2Width;
    private int col3Width;
    private int col4Width;
    private int col5Width;
    private int col6Width;
    
    // "Water", "Fire", "Wind", "Ice", "Earth", "Electric"
    
    public TypeChart()
    {
        HEADER = new String[]{"Element Match-up Chart", "Attacking Element: Left", "Targeted Element: Top"};
        COLUMN_SIZES = new ArrayList<>();
        CELL_PADDING = 2;
        initialPopulation();
    }
    
    private void initialPopulation()
    {   
        elements = new ArrayList<>();
        elements.add("Water");
        elements.add("Fire");
        elements.add("Wind");
        elements.add("Ice");
        elements.add("Earth");
        elements.add("Electric");
        
        chart = new String[7][7];
        chart[0][0] = "";
        
        // Start at index 1 and make the first row contain the elements
        for(int col = 1; col < chart.length; col++)
        {
            chart[0][col] = elements.get(col - 1);
        }
        
        // Start at index 1 and make the first column contain the elements
        for(int row = 1; row < chart.length; row++)
        {
            chart[row][0] = elements.get(row - 1);
        }
    }
    
    private void populateChart()
    {
        for(int row = 1; row < chart.length; row++)
        {
            for(int col = 1; col < chart[row].length; col++)
            {
                chart[row][col] = getEffectiveness(row, col);
            }
        }
    }
    
    private String getEffectiveness(int row, int col)
    {
        String attackerElement = elements.get(row - 1);
        String targetElement = elements.get(col - 1);
        
        int result = (new ElementStringComparator()).compare(attackerElement, targetElement);
        
        switch (result) 
        {
            case 1:
                return "x2";
            case 0:
                return "x1";
            default:
                return "x0.5";
        }
    }
    
    /**
    * Adds a space to the title if its length is odd.
    */
    public void formatHeader()
    {
        String info;
        // Ensures every title has an even length to aid with centering
        for(int i = 0; i < HEADER.length; i++)
        {
            info = HEADER[i];
            
            if(info.length() % 2 == 1)
            {
                info += " ";
                HEADER[i] = info;
            }
        }   
    }
   
    /**
     * Adds a space to every cell's info if its length is odd.
     */
    public void formatChartInfo()
    {
        for (String[] chart1 : chart) 
        {
            for (int col = 0; col < chart1.length; col++) 
            {
                if ((chart1[col] != null) && (chart1[col].length() % 2 == 1)) 
                {
                    chart1[col] += " ";
                }
            }
        }
    }
    
    /**
     * Finds the total width of the entire table.
     */
    private void determineTableWidth()
    {
        col0Width = findWidestWidth(0);
        col1Width = findWidestWidth(1);
        col2Width = findWidestWidth(2);
        col3Width = findWidestWidth(3);
        col4Width = findWidestWidth(4);
        col5Width = findWidestWidth(5);
        col6Width = findWidestWidth(6);
        
        COLUMN_SIZES.add(col0Width);
        COLUMN_SIZES.add(col1Width);
        COLUMN_SIZES.add(col2Width);
        COLUMN_SIZES.add(col3Width);
        COLUMN_SIZES.add(col4Width);
        COLUMN_SIZES.add(col5Width);
        COLUMN_SIZES.add(col6Width);
        
        int numOfColumns = chart[0].length;
        
        int result = (col0Width + col1Width + col2Width + col3Width + col4Width + col5Width + col6Width) + 
                (numOfColumns * (CELL_PADDING * 2)) + (numOfColumns -1);
        
        if(result % 2 == 1)
        {
            result++;
        }
        
        tableWidth = result;
    }
    
    /**
    * Helper method finds the widest element in a given column; method is used in compareColumnWidths.
    * @param array
    * @param column
    * @return widest element in a column
    */
    private int findWidestWidth(int column)
    {
       int widest = 0; 

        for(String[] chart1 : chart) 
        {
            if(chart1[column] != null) 
            {
                if(widest < chart1[column].length()) 
                {
                    widest = chart1[column].length();
                }
            }
        }

       return widest;
    }
    
    /**
    * Prints the array of table titles.
    */
    private void printTitle()
    {   
        printSeparatorLine();
        
        for(String info : HEADER)
        {
            int numOfSpaces = tableWidth - info.length();

            System.out.printf("%1s" + " ".repeat(numOfSpaces / 2) + "%" + info.length() + "s" + 
                    " ".repeat(numOfSpaces / 2) + "%1s", "|", info, "|\n");
        }
            

        printSeparatorLine();
    }
    
    /**
     * Prints the rest of the information of the chart.
     */
    private void printChartInfo()
    {
        int numOfSpaces;
        int i = -1;
        int columnWidth;

        for(String[] innerArray : chart)
        {
            System.out.printf("|");
            
            for(String info : innerArray)
            {
                columnWidth = COLUMN_SIZES.get(++i) + (CELL_PADDING * 2);
                
                numOfSpaces = columnWidth - info.length();
                
                MainGame.print(" ".repeat(numOfSpaces / 2) + info + " ".repeat(numOfSpaces / 2) + "|", 5);
            }
            
            System.out.println("");
            
            printSeparatorLine();
            i = -1;
        }
    }
    
    /**
    * Prints the separator line.
    */
    private void printSeparatorLine()
    {   
        for(Integer columnSize : COLUMN_SIZES) 
        {
            MainGame.print("+" + "-".repeat(columnSize + (CELL_PADDING * 2)), 1);
        }

        System.out.printf("+\n");
    }
   
    public void printChart()
    {
        formatHeader();
        populateChart();
        formatChartInfo();
        determineTableWidth();
        
        printTitle();
        printChartInfo();
    }
}
