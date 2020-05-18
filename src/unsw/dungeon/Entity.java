package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
	private BooleanProperty exists;
    private IntegerProperty x, y;
    Dungeon dungeon;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, Dungeon dungeon) {
    	this.exists = new SimpleBooleanProperty(false);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeon = dungeon;
    }

    public BooleanProperty getExistProperty() {
        return exists;
    }
    
    public boolean getExists() {
    	return exists.get();
    }
    
    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    /**
     * @param player TODO
     * @return Returns true if player can interact with entity in a given direction, false if not. 
     * Function performs said interaction.
     */
    public abstract boolean interact(Player player);
}
