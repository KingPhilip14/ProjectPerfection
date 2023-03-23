package Utilites;

/**
 * An abstract base class to assist implementations of the Priority Queue interface.
 * @author Ian King
 * @param <K>
 * @param <V>
 */
public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V>
{
    //-------------- nested PQEntry class --------------
    protected static class PQEntry<K,V> implements Entry<K,V>
    {
        private K key; // key
        private V value; // value
        public PQEntry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
        
        // methods of the Entry interface
        @Override
        public K getKey() {return key;}
        @Override
        public V getValue() {return value;}
        
        // utilities not exposed as part of the Entry interface
        protected void setKey(K key){this.key = key;}
        protected void setValue(V value){this.value = value;}
    }
    // -------------- end of nested PQEntry class --------------
    
    // instance variable for an AbstractPriorityQueue
    
    // The comparator defining the ordering of keys in the priority queue
    private Comparator<K> comp;
    
    /**
     * Creates an empty priority queue using the given comparator to order keys
     */
    protected AbstractPriorityQueue(Comparator<K> c) {comp = c;}
    
//    protected AbstractPriorityQueue() {this(new DefaultComparator<K>());}
    
    /**
     * Method for comparing two entries according to key.
     * @param a
     * @param b
     * @return 
     */
    protected int compare(Entry<K,V> a, Entry<K,V> b)
    {
        return comp.compare(a.getKey(), b.getKey());
    }
    
    /**
     * Determines whether a key is valid.
     * @param key
     * @return true or false
     * @throws IllegalArgumentException 
     */
    protected boolean checkKey(K key) throws IllegalArgumentException
    {
        try
        {
            // see if key can be compared to itself
            return (comp.compare(key, key) == 0);
        }
        catch(ClassCastException e)
        {
            throw new IllegalArgumentException("Incompatible key");
        }
    }
    
    /**
     * Tests whether the priority queue is empty.
     * @return true or false
     */
    @Override
    public boolean isEmpty() {return size() == 0;}
    
    
}
