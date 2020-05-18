package unsw.dungeon;

import javafx.beans.value.ObservableValue;

public interface Goal {
	public boolean isComplete(boolean isExit);
	public String toString();
}
