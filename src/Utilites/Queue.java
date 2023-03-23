package Utilites;

/**
 * An implementation of the Queue interface
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Code Fragment 6.9
 * Transcribed by
 * @author Ian King
 * @revision 3/7/2022
 */

public interface Queue <E>
{
    /**
     * Returns the number of elements in the queue.
     * @return number of elements
     */
    int size();
    
    /**
     * Tests whether the queue is empty.
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();
    
    /**
     * Inserts an element at the rear of the queue.
     * @param e 
     */
    void enqueue(E e) throws IllegalStateException;
    
    /**
     * Returns, but does not remove, the first element of the queue (null if empty).
     * @return the first element in the queue
     */
    E first();
    
    /**
     * Removes and returns the first element of the queue (null if empty).
     * @return 
     */
    E dequeue();
}
