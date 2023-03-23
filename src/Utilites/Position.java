package Utilites;

/**
 * An implementation of the Position interface
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Code Fragment 7.7
 * Transcribed by
 * @author Ian King
 * @version 4/1/2022
 */
public interface Position<E>
{
    E getElement() throws IllegalStateException;
}
