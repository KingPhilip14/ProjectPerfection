package Utilites;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class containing static sorting methods
 * Code Fragments 12.1, 12.2, and 12.5 from 
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Transcribed by
 * @author Ian King
 * @version 4/13/2022
 */
public class Sort 
{
    /**
     * A simple sorting method.
     * @param <E>
     * @param array
     * @param comparator 
     */
    public static <E> void simpleBubbleSort(E[] array, Comparator<E> comparator)
    {
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array.length - 1; j++)
            {
                if(comparator.compare(array[j], array[j + 1]) > 0)
                {
                    E temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    
    /**
     * An enhanced version of the bubble sort to not sort unnecessary elements.
     * @param <E>
     * @param array
     * @param comparator 
     */
    public static <E> void efficientBubbleSort(E[] array, Comparator<E> comparator)
    {
        //int count = 1;
        int arrayLength = array.length;
        int numOfSorts = 0;
        //boolean hasSorted = false;
        
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < arrayLength - 1; j++)
            {
                if(comparator.compare(array[j], array[j + 1]) > 0)
                {
                    E temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    numOfSorts++;
                }
            }

            if(numOfSorts == 0)
            {
                break;
            }
            
            numOfSorts = 0;
            arrayLength--;
        }   
    }
    
    /**
     * Sorts an array by selection.
     * @param <E>
     * @param array
     * @param comparator 
     */
    public static <E> void selectionSort(E[] array, Comparator<E> comparator)
    {
        // Temporary location for swaps
        E temp;
        // the object with the highest value
        int max;
        
        for(int i = 0; i < array.length; i++)
        {
            // find index of largest value in subarray
            max = indexOfLargestElement(array, comparator, array.length - i);
            
            // swap array[max] and array[array.length - i - 1]
            temp = array[max];
            array[max] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }
    
    /**
     * Sorts an array by insertion.
     * @param <E>
     * @param array
     * @param comparator 
     */
    public static <E> void insertionSort(E[] array, Comparator<E> comparator)
    {
        E temp;
        int j;
        
        for(int i = 0; i < array.length; i++)
        {
            j = i;
            temp = array[i];
            
            while(j != 0 && comparator.compare(array[j - 1], temp) == 1)
            {
                array[j] = array[j - 1];
                j--;
            }
            
            array[j] = temp;
        }
    }
    
    /**
     * Merge contents of arrays S1 and S2 into properly size array S.
     * @param <E>
     * @param S1
     * @param S2
     * @param S
     * @param comp 
     */
    public static <E> void merge(E[] S1, E[] S2, E[] S, Comparator<E> comp)
    {
        int i = 0, j = 0;
        while (i + j < S.length)
        {
            if(j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
            {
                // copy ith element of S1 and increment i
                S[i + j] = S1[i++];
            }
            else
            {
                // copy jth element of S2 and increment
                S[i + j] = S2[j++];
            }
        }
    }
    
    /**
     * Merge-sort contents of array S.
     * @param <E>
     * @param S
     * @param comparator 
     */
    public static <E> void mergeSort(E[] S, Comparator<E> comparator)
    {
        // O(nlogn) because it's log n to divide everything, and n to merge it all back together
        
        int n = S.length;
        // array is trivially sorted
        if(n < 2) return;
        
        // divide
        
        int mid = n / 2;
        // copy of first half
        E[] S1 = Arrays.copyOfRange(S, 0, mid);
        // copy of second half
        E[] S2 = Arrays.copyOfRange(S, mid, n);
        
        //conquer (with recursion)
        
        // sort copy of first half
        mergeSort(S1, comparator);
        // sort copy of second half
        mergeSort(S2, comparator);
        
        // merge results
        
        // merge sorted halves back into original
        merge(S1, S2, S, comparator);
    }
    
    /**
     * Quick-sort contents of a queue.
     * @param <E>
     * @param S
     * @param comparator 
     */
    public static <E> void quickSort(Queue<E> S, Comparator<E> comparator)
    {   
        /*
        best case: nlogn
        expecteed csae: nlogn
        worst case: n^2
        */
        
        int n = S.size();
        
        // queue is trivially sorted
        if(n < 2) return;
        
        // divide
        
        // using first as arbitrary pivot
        E pivot = S.first();
        
        Queue<E> L = new LinkedQueue<>();
        Queue<E> E = new LinkedQueue<>();
        Queue<E> G = new LinkedQueue<>();
        
        // divide original into L, E, and G
        while(!S.isEmpty())
        {
            E element = S.dequeue();
            int c = comparator.compare(element, pivot);
            
            // element is less than pivot
            if(c < 0)
            {
               L.enqueue(element);
            }
            // element is equal to pivot
            else if(c == 0)
            {
                E.enqueue(element);
            }
            // element is greater than pivot
            else
            {
                G.enqueue(element);
            }
        }
        
        // conquer
        
        // sort elements less than pivot
        quickSort(L, comparator);
        //sort elements greater than pivot
        quickSort(G, comparator);
        
        // concatenate results
        while(!L.isEmpty())
        {
            S.enqueue(L.dequeue());
        }
        while(!E.isEmpty())
        {
            S.enqueue(E.dequeue());
        }
        while(!G.isEmpty())
        {
            S.enqueue(G.dequeue());
        }
    }
    
    /**
     * Helper method to find the largest value of an array.
     * @param <E>
     * @param array
     * @param comparator
     * @param size
     * @return 
     */
    private static <E> int indexOfLargestElement(E[] array, Comparator<E> comparator, int size)
    {
        int index = 0;
        
        for(int i = 1; i < size; i++)
        {
            if(comparator.compare(array[i], array[index]) == 1)
            {
                index = i;
            }
        }
        
        return index;
    }
    
    /**
     * A version of the quickSort method that takes an array.
     * @param <E>
     * @param array
     * @param comparator 
     */
    public static <E> void quickSort(E[] array, Comparator<E> comparator)
    {
        LinkedQueue<E> queueData = arrayToQueue(array);
        quickSort(queueData, comparator);
        
        for(int i = 0; i < array.length; i++)
        {
            array[i] = queueData.dequeue();
        }
    }
    
    public static <E> void mergeSort(ArrayList<E> list, Comparator<E> comparator)
    {
        E[] array = listToArray(list);
        mergeSort(array, comparator);
        
        for(int i = 0; i < list.size(); i++)
        {
            list.set(i, array[i]);
        }
    }
    
    /**
     * Turns an array into a Queue
     * @param <E>
     * @param array
     * @return 
     */
    public static <E> LinkedQueue arrayToQueue(E[] array)
    {
        LinkedQueue<E> result = new LinkedQueue<>();
        
        for(E item : array)
        {
            result.enqueue(item);
        }
        
        return result;
    }
    
    /**
     * Turns a Queue into an array
     * @param <E>
     * @param queue
     * @return 
     */
    public static <E> E[] queueToArray(LinkedQueue<E> queue)
    {
        E[] result = (E[]) new Object[queue.size()];
        
        for(int i = 0; i < queue.size(); i++)
        {
            result[i] = queue.dequeue();
        }
        
        return result;
    }
    
    public static <E> E[] listToArray(ArrayList<E> list)
    {
        E[] result = (E[]) new Object[list.size()];
        
        for(int i = 0; i < list.size(); i++)
        {
            result[i] = list.get(i);
        }
        
        return result;
    }
    
    public static <E> ArrayList<E> arrayToList(E[] list)
    {
        ArrayList<E> result = new ArrayList<>();
        
        for(int i = 0; i < list.length; i++)
        {
            result.add(list[i]);
        }
        
        return result;
    }
    
    /**
     * Sorts an array based on 2-5 different comparators and throws an exception if the number of comparators is less than 2.
     * @param <E>
     * @param array
     * @param comparator1
     * @param comparator2
     * @param comparator3
     * @param comparator4
     * @param comparator5
     * @param numOfCompares
     * @throws IllegalArgumentException 
     */
    public static <E> void radixSort(E[] array, Comparator<E> comparator1, Comparator<E> comparator2, Comparator<E> comparator3,
            Comparator<E> comparator4, Comparator<E> comparator5, int numOfCompares) throws IllegalArgumentException
    {
        if(numOfCompares == 2)
        {
            quickSort(array, comparator2);
            quickSort(array, comparator1);
        }
        else if(numOfCompares == 3)
        {
            quickSort(array, comparator3);
            quickSort(array, comparator2);
            quickSort(array, comparator1);
        }
        else if(numOfCompares == 4)
        {
            quickSort(array, comparator4);
            quickSort(array, comparator3);
            quickSort(array, comparator2);
            quickSort(array, comparator1);
        }
        else if(numOfCompares == 5)
        {
            quickSort(array, comparator5);
            quickSort(array, comparator4);
            quickSort(array, comparator3);
            quickSort(array, comparator2);
            quickSort(array, comparator1);
        }
        else
        {
            throw new IllegalArgumentException("Can only use between 2-5 comparators");
        }
    }
    
    public static <E> ArrayList<E> radixSort(ArrayList<E> list, Comparator<E> comparator1, Comparator<E> comparator2, Comparator<E> comparator3,
            Comparator<E> comparator4, Comparator<E> comparator5, int numOfCompares) throws IllegalArgumentException
    {
        E[] array = listToArray(list);
        
        radixSort(array, comparator1, comparator2, comparator3, comparator4, comparator5, numOfCompares);
        
        list = arrayToList(array);
        
        return list;
    }
}
