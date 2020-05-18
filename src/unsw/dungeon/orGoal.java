package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class orGoal implements Goal {
	private Dungeon dungeon;
	private List<Goal> goals;
	private BooleanProperty complete;

	
	public orGoal(Dungeon dungeon,List<Goal> goals) {
		this.dungeon = dungeon;
		this.goals = goals;
		complete = new SimpleBooleanProperty();
		complete.set(false);
	}
	
	@Override
	public boolean isComplete(boolean isExit) {
		int flag = 0;
		for(Goal g: goals) {
			if(g.isComplete(isExit)) {
				flag = 1;
			}
		}
		if(flag == 1) {
			complete.set(true);
			return true;
		}
		complete.set(false);
		return false;
	}
	
	public void addGoal(Goal goal) {
		goals.add(goal);
	}
	
	public List<Goal> getSubGoals(){
		return this.goals;
	}
	
	@Override
	public String toString() {
		return "Complete one goal: ";
	}
	
	public BooleanProperty getProperty() {
		return this.complete;
	}
}
