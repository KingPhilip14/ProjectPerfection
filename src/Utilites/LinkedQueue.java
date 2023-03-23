package Utilites;

/**
 * An implementation of the LinkedQueue class
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Code Fragment 6.11.
 * Transcribed by
 * @author Ian King
 * @version 3/7/2022
 */

/*
Realization of a FIFO queue as an adaptation of a SinglyLinkedList.
*/
public class LinkedQueue<E> implements Queue<E>
{
    // an empty list
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    
    // new queue relies on the initially empty list
    public LinkedQueue()
    {
        
    }
    
    @Override
    public int size()
    {
        return list.size();
    }
    
    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    
    @Override
    public void enqueue(E element)
    {
        list.addLast(element);
    }
    
    @Override
    public E first()
    {
        return list.first();
    }
    
    @Override 
    public E dequeue()
    {
        return list.removeFirst();
    }
}
