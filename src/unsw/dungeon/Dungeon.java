/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> deadEntities;
    private Player playerA;
    private Player playerB;

    private List<Goal> currentGoals;
    private List<Goal> completedGoals;
    
    private Goal goal; 
    private boolean paused;
    private BooleanProperty complete;
    private IntegerProperty playerCount;
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.deadEntities = new ArrayList<>();
        this.playerA = null;
        this.playerB = null;
        this.currentGoals = new ArrayList<Goal>();
        this.completedGoals = new ArrayList<Goal>();
        this.paused = true;
        this.complete = new SimpleBooleanProperty(false);
        this.playerCount = new SimpleIntegerProperty(0);
    }

    public BooleanProperty getCompleteProperty() {
        return complete;
    }

    public boolean getComplete() {
        return complete.get();
    }
    
    public void setComplete(boolean value) {
        complete.set(value);
    }
    
    public IntegerProperty getPlayerCountProperty() {
        return playerCount;
    }
    
    public int getPlayerCount() {
        return playerCount.get();
    }
    
    public void setPlayerCount(int value) {
        playerCount.set(value);
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player player) {
        this.playerA = player;
    }
    
    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player player) {
        this.playerB = player;
    }
    
    public boolean hasEntity(Entity entity) {
    	for(Entity e : entities) {
    		if(e.equals(entity)) {
    			return true;
    		}
    	}
    	return false;
    }

    public boolean hasDeadEntity(Entity entity) {
    	for(Entity e : deadEntities) {
    		if(e.equals(entity)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public void addEntity(Entity entity) {
        entities.add(entity);
        if(hasDeadEntity(entity))
        	deadEntities.remove(entity);
        entity.getExistProperty().set(true);
    }
    
    public void removeEntity(Entity entity) {
    	if(entity instanceof Player) {
    		setPlayerCount(getPlayerCount() - 1);
    	}
    	System.out.println(getPlayerCount());
        entities.remove(entity);
        deadEntities.add(entity);
        entity.getExistProperty().set(false);
    }
    
    public void addDeadEntity(Entity entity) {
       	deadEntities.add(entity);
    }
    
    public void removeDeadEntity(Entity entity) {
        deadEntities.remove(entity);
    }
    
    public ArrayList<Entity> getEntities() {
        return entities;
    }
    
    public ArrayList<Entity> getDeadEntities() {
        return deadEntities;
    }
    
    //returns a list of entities at location x,y
    public ArrayList<Entity> entitiesOnXY(int x, int y){
    	ArrayList<Entity> entityList = new ArrayList<Entity>();
    	
    	for(Entity e : entities) {
    		if(e == null)
    			continue;
    		if((e.getX() == x) && (e.getY() == y)){
    			entityList.add(e);
    		}
    	}
    	
    	return entityList;
    }
    
    public void killEnemies(int x, int y) {
		ArrayList<Entity> eList = entitiesOnXY(x, y);
		for(Entity e : eList) {
			if(e instanceof Enemy) {
				removeEntity(e);
				if(this.isComplete(false)) {
					setComplete(true);
					System.out.println("You have defeated all enemies! Level Complete!");
					return;
				}
			}
			if(eList == null)
				break;
		}
	}
    
    public void killAll(int x, int y) {
		ArrayList<Entity> eList = entitiesOnXY(x, y);
		for(Entity e : eList) {
			if(e instanceof Enemy) {
				removeEntity(e);
				if(this.isComplete(false)) {
					setComplete(true);
					System.out.println("You have defeated all enemies! Level Complete!");
					return;
				}
			}
			if(e instanceof Player) {
				Player p = (Player)e;
				if(!p.isInvincible()) {
					removeEntity(e);
					System.out.println("A player has been blown up!");
				}
			}
			if(this.getPlayerB() != null) {
				if((this.getPlayerB().getExists()) || (this.getPlayerA().getExists())) {
					if(this.isComplete(false)) {
						System.out.println("You have defeated all enemies! Level Complete!");
						return;
					}
				}
			}

			
			if(eList == null)
				break;
		}
	}
    
    public void destroyBoulder(int x, int y) {
		ArrayList<Entity> eList = entitiesOnXY(x, y);
		for(Entity e : eList) {
			if(e instanceof Boulder)
				removeEntity(e);
			if(eList == null)
				break;
		}
	}


	public boolean isComplete(boolean exitComplete) {//exitComplete is whether the current check is for exit
		if(goal.isComplete(exitComplete)) {
			setComplete(true);
			return true;
		}
		return false;
	}
	
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	
	public void setPaused(boolean started) {
		this.paused = started;
	}
	
	public boolean getPaused() {
		return this.paused;
	}
}
