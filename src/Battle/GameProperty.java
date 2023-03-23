package Battle;

/**
 * An abstract class for Game Properties.
 * @author Ian King
 */
public abstract class GameProperty implements Entity
{
    protected String name;
    protected String description;
    
    @Override
    public String getName() {return this.name;}
    
    @Override
    public void setName(String newName) {this.name = newName;}
    
    @Override
    public String getDescription() {return description;}
    
    @Override
    public void setDescription(String newDescription) {this.description = newDescription;}
}
