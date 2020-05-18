package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class treasureGoal implements Goal {
	private Dungeon dungeon;
	private BooleanProperty complete;

	
	public treasureGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		complete = new SimpleBooleanProperty();
		complete.set(false);
	}
	
	@Override
	public boolean isComplete(boolean isExit) {
		for(Entity entity:dungeon.getEntities()) {
			if(entity instanceof Treasure) {
				complete.set(false);
				return false;
			}
		}
		complete.set(true);
		return true;
	}

	@Override
	public String toString() {
		return "Collect all treasures";
	}
	
	public BooleanProperty getProperty() {
		return this.complete;
	}
}
