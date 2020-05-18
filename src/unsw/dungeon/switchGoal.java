package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class switchGoal implements Goal {
	private Dungeon dungeon;
	private BooleanProperty complete;

	
	public switchGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		complete = new SimpleBooleanProperty();
		complete.set(false);
	}
	
	@Override
	public boolean isComplete(boolean isExit) {
		for(Entity entity:dungeon.getEntities()) {
			if (entity instanceof Switch) {
				if (!((Switch) entity).isActivated()) {
					complete.set(false);
					return false;
				}
			}
		}
		complete.set(true);
		return true;
	}

	@Override
	public String toString() {
		return "Activate all switches";
	}
	
	public BooleanProperty getProperty() {
		return this.complete;
	}
}
