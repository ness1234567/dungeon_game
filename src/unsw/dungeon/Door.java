package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {

	private int id;
	private BooleanProperty locked;
	
	public Door(int x, int y, Dungeon dungeon, int id) {
		super(x, y, dungeon);
		this.locked = new SimpleBooleanProperty(true);
		this.id = id;
	}

	@Override
	public boolean interact(Player player) {
		
		if(player.hasKeybyID(this.id)) {
			locked.set(false);
			player.removeKey();
		}
		
		if(locked.get()) {
			return false;
		} else {
			return true;
		}
	}
	
	public BooleanProperty getLockedProperty() {
		return this.locked;
	}
	
	public boolean Locked() {
		return this.locked.get();
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

}
