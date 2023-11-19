package Utilites;

import Battle.Enemy;
import Battle.Player;

/**
 * A class for comparing Elements in an offensive standpoint.
 * @author Ian King
 * @version 4/17/2022
 */
public class ElementComparator implements UniqueComparator<Player, Enemy>
{
    @Override
    public int comparePToE(Player player, Enemy target)
    {
        if(player == null || target == null)
        {
            throw new NullPointerException("A Character is null.");
        }
        
        int result = 0;
        
        String playerElement = player.getElement();
        String targetElement = target.getElement();
        
        if(playerElement.equals("Water"))
        {
            switch (targetElement) 
            {
                case "Fire":
                case "Earth":
                    result = 1;
                    break;
                case "Wind":
                case "Ice":
                case "Electric":
                    result = 0;
                    break;
                case "Water":
                    result = -1;
                    break;
                default:
                    break;
            }
        }
        else if(playerElement.equals("Fire"))
        {
            switch (targetElement) 
            {
                case "Ice":
                case "Earth":
                    result = 1;
                    break;
                case "Electric":
                case "Wind":
                    result = 0;
                    break;
                case "Fire":
                case "Water":
                    result = -1;
                    break;
                default:
                    break;
            }
        }
        else if(playerElement.equals("Wind"))
        {
            switch (targetElement) 
            {
                case "Fire":
                    result = 1;
                    break;
                case "Water":
                case "Ice":
                case "Earth":
                case "Wind":
                    result = 0;
                    break;
                case "Electric":
                    result = -1;
                    break;
                default:
                    break;
            }
        }
        else if(playerElement.equals("Ice"))
        {
            switch (targetElement) 
            {
                case "Wind":
                case "Water":
                case "Earth":
                    result = 1;
                    break;
                case "Electric":
                    result = 0;
                    break;
                case "Ice":
                case "Fire":
                    result = -1;
                    break;
                default:
                    break;
            }
        }
        else if(playerElement.equals("Earth"))
        {
            switch (targetElement) 
            {
                case "Electric":
                case "Fire":
                    result = 1;
                    break;
                case "Wind":
                case "Ice":
                    result = 0;
                    break;
                case "Water":
                case "Earth":
                    result = -1;
                    break;
                default:
                    break;
            }
        }
        else if(playerElement.equals("Electric"))
        {
            switch (targetElement) 
            {
                case "Water":
                    result = 1;
                    break;
                case "Fire":
                case "Wind":
                case "Ice":
                    result = 0;
                    break;
                case "Earth":
                case "Electric":
                    result = -1;
                    break;
                default:
                    break;
            }
        }
        return result;
    }
    
    @Override
    public int compareEToP(Player target, Enemy enemy)
    {
        if(enemy == null || target == null)
        {
            throw new NullPointerException("A Character is null.");
        }
        
        int result = 0;
        
        String enemyElement = enemy.getElement();
        String targetElement = target.getElement();
        
        switch (enemyElement) 
        {
            case "Water":
                switch (targetElement)
                {
                    case "Fire":
                    case "Earth":
                        result = 1;
                        break;
                    case "Wind":
                    case "Ice":
                    case "Electric":
                        result = 0;
                        break;
                    case "Water":
                        result = -1;
                        break;
                    default:
                        break;
                }  
                break;
            case "Fire":
                switch (targetElement)
                {
                    case "Ice":
                    case "Earth":
                        result = 1;
                        break;
                    case "Electric":
                    case "Wind":
                        result = 0;
                        break;
                    case "Fire":
                    case "Water":
                        result = -1;
                        break;
                    default:
                        break;
                }  
                break;
            case "Wind":
                switch (targetElement)
                {
                    case "Fire":
                        result = 1;
                        break;
                    case "Water":
                    case "Ice":
                    case "Earth":
                    case "Wind":
                        result = 0;
                        break;
                    case "Electric":
                        result = -1;
                        break;
                    default:
                        break;
                }   
                break;
            case "Ice":
                switch (targetElement)
                {
                    case "Wind":
                    case "Water":
                    case "Earth":
                        result = 1;
                        break;
                    case "Electric":
                        result = 0;
                        break;
                    case "Ice":
                    case "Fire":
                        result = -1;
                        break;
                    default:
                        break;
                }  
                break;
            case "Earth":
                switch (targetElement)
                {
                    case "Electric":
                    case "Fire":
                        result = 1;
                        break;
                    case "Wind":
                    case "Ice":
                        result = 0;
                        break;
                    case "Water":
                    case "Earth":
                        result = -1;
                        break;
                    default:
                        break;
                }   
                break;
            case "Electric":
                switch (targetElement)
                {
                    case "Water":
                        result = 1;
                        break;
                    case "Fire":
                    case "Wind":
                    case "Ice":
                        result = 0;
                        break;
                    case "Earth":
                    case "Electric":
                        result = -1;
                        break;
                    default:
                        break;
                }   
                break;
            default:
                break;
        }
        return result;
    }
}
