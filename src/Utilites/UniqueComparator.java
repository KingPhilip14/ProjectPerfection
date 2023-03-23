package Utilites;

/**
 * The comparator interface.
 * @author Ian King
 * @version 4/14/2022
 * @param <E>
 * @param <T>
 */
public interface UniqueComparator<E,T>
{
    public int comparePToE(E player, T enemy);
    public int compareEToP(E enemy, T player);
}
