package Utilites;

/**
 * Interface for a key-value pair
 * @author Ian King
 * @param <K>
 * @param <V>
 */
public interface Entry<K,V>
{
    // returns the key stored in this entry
    K getKey();
    
    // returns the value stored in this entry
    V getValue();
}
