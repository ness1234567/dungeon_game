package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class exitGoal implements Goal {
	private Dungeon dungeon;
	private BooleanProperty complete;
	
	
	public exitGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		complete = new SimpleBooleanProperty();
		complete.set(false);
	}
	
	@Override
	public boolean isComplete(boolean isExit) {
		if(!isExit) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Reach the exit";
	}
	
	public BooleanProperty getProperty() {
		return this.complete;
	}

}
