package Utilites;

/**
 * SinglyLinkedList Class
 * Code fragments 3.14, 3.15
 * from
 * Data Structures and Algorithms, 6th Edition
 * by Michael T. Goodrich, Roberto Tamassia & Michael H. Goldwasser
 * Wiley 2014
 * Transcribed by
 * @author Ian King
 * @version 1/29/2022
 */
public class SinglyLinkedList<E>
{
    // -------------- start of nested Node class --------------
    private static class Node<E>
    {
        // Reference to the element stores at this Node
        private E element;
        // Reference to the subsequent node in the list
        private Node<E> next;
        
        /**
         * Constructor method for the nested Node class
         * @param e
         * @param n 
         */
        public Node(E e, Node<E> n)
        {
            element = e;
            next = n;
        }
        
        public E getElement()
        {
            return element;
        }
        
        public Node<E> getNext() 
        {
            return next;
        }
        
        public void setNext(Node<E> n)
        {
            next = n;
        }
    }
    // -------------- end of nested Node class --------------
    
    // Head node of the list (or null if empty)
    private Node<E> head = null;
    // Last node of the list (or null if empty)
    private Node<E> tail = null;
    // Number of nodes in the list
    private int size = 0;
    
    /**
     * Constructs an initially empty list.
     */
    public SinglyLinkedList()
    {
        
    }
    
    // Access methods
    /**
     * Returns how many Nodes are in the list.
     * @return size
     */
    public int size()
    {
        return size;
    }
    
    /**
     * Checks if the list is empty.
     * @return True if size is 0
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    /**
     * Returns (but does not remove) the first element.
     * @return The first element
     */
    public E first()
    {
        if(isEmpty())
        {
            return null;
        }
        
        return head.getElement();
    }
    
    /**
     * Returns (but does not remove) the last element.
     * @return The last element
     */
    public E last()
    {
        if(isEmpty())
        {
            return null;
        }
        
        return tail.getElement();
    }
    
    // Update methods
    /**
     * Adds element e to the front of the list.
     * @param e 
     */
    public void addFirst(E e)
    {
        // Create and link a new Node
        head = new Node<>(e, head);
        
        // Special case: new node becomes tail also
        if(size == 0)
        {
            tail = head;
        }
        
        size++;
    }
    
    /**
     * Adds element e to the end of the list.
     * @param e 
     */
    public void addLast(E e)
    {
        // Node will eventaully be the tail
        Node<E> newest = new Node<>(e, null);
        
        // Special case: previously empty list
        if(isEmpty())
        {
            head = newest;
        }
        // New Node after existing tail
        else
        {
            tail.setNext(newest);
        }
        
        // New Node becomes the tail
        tail = newest;
        size++;
    }
    
    public E removeFirst()
    {
        // Nothing to remove
        if(isEmpty())
        {
            return null;
        }
        
        E answer = head.getElement();
        // Will become null if list had only one Node
        head = head.getNext();
        size--;
        
        // Special case as list is now empty
        if(size == 0)
        {
            tail = null;
        }
        
        return answer;
    }
}
