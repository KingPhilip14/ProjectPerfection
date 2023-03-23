package Battle;

/**
 * An interface for the methods collectables have.
 * @author Ian King
 */
public interface Collectable extends Entity
{
    public String getDescription();
    public void setDescription(String newDesc);
}
