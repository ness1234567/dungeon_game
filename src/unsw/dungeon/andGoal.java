package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class andGoal implements Goal {
	private Dungeon dungeon;
	private List<Goal> goals;
	private BooleanProperty complete;
	
	public andGoal(Dungeon dungeon,List<Goal> goals) {
		this.dungeon = dungeon;
		this.goals = goals;
		complete = new SimpleBooleanProperty();
		complete.set(false);
	}
	
	@Override
	public boolean isComplete(boolean isExit) {
		int flag = 1;
		for(Goal g: goals) {
			if(g.isComplete(isExit) == false) {
				flag = 0;
			}
		}
		if(flag == 0) {
			complete.set(false);
			return false;
		}
		complete.set(true);
		return true;
	}
	
	public void addGoal(Goal goal) {
		goals.add(goal);
	}
	
	public List<Goal> getSubGoals(){
		return this.goals;
	}
	
	@Override
	public String toString() {
		return "Complete both goals:";
	}
	
	public BooleanProperty getProperty() {
		return this.complete;
	}
}
