package Utilites;

/**
 * A comparator for comparing something between a Player and Enemy Object
 * @author ianth
 */
public interface DuelComparator<E, T>
{
    public int compare(E object1, T object2);
}
