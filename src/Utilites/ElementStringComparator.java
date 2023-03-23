package Utilites;

/**
 * Takes two String to compare elements
 * @author Ian King
 */
public class ElementStringComparator implements Comparator<String>
{
    @Override
    public int compare(String attackerElement, String targetElement)
    {
        if(attackerElement == null || targetElement == null)
        {
            throw new NullPointerException("An element for the type chart is null.");
        }
        
        int result = 0;
        
        switch (attackerElement) 
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
                    case "Earth":
                    case "Ice":
                        result = 0;
                        break;
                    case "Water":
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
