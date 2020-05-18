package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        
        createGoal(dungeon,jsonGoals);
        
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private Goal createGoal(Dungeon dungeon, JSONObject jsonGoals) {
        String goal = jsonGoals.getString("goal");
    	Goal newGoal = null;
    	List<Goal> newArray = null;
    	JSONArray subgoals;
        switch(goal) {
        case "exit":
        	newGoal = new exitGoal(dungeon);
        	onLoad(newGoal);
        	dungeon.setGoal(newGoal);
        	break;
        case "enemies":
        	newGoal = new enemyGoal(dungeon);
        	dungeon.setGoal(newGoal); 
        	onLoad(newGoal);
        	break;
        case "boulders":
        	newGoal = new switchGoal(dungeon);	
        	dungeon.setGoal(newGoal); 
        	onLoad(newGoal);
        	break;
        case "treasure":
        	newGoal = new treasureGoal(dungeon);
        	dungeon.setGoal(newGoal); 
        	onLoad(newGoal);
        	break;
        case "AND":
        	subgoals = jsonGoals.getJSONArray("subgoals");
        	newArray = new ArrayList<Goal>();
        	for(int i = 0;i < subgoals.length();i++) {
        		newArray.add(createGoal(dungeon,subgoals.getJSONObject(i)));
        	}
        	newGoal = new andGoal(dungeon,newArray);
        	onLoad(newGoal);
        	dungeon.setGoal(newGoal);
        	break;
        case "OR":
        	subgoals = jsonGoals.getJSONArray("subgoals");
        	newArray = new ArrayList<Goal>();
        	for(int i = 0;i < subgoals.length();i++) {
        		newArray.add(createGoal(dungeon,subgoals.getJSONObject(i)));
        	}
        	newGoal = new orGoal(dungeon,newArray);
        	onLoad(newGoal);
        	dungeon.setGoal(newGoal);
        	break;
        }
        
        
        
        return newGoal;       	       
    }
    
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            if(dungeon.getPlayerA() == null) {
            	dungeon.setPlayerA(player);
            } else {
            	dungeon.setPlayerB(player);
            }
            dungeon.setPlayerCount(dungeon.getPlayerCount() + 1);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y, dungeon);
            onLoad(wall);
            entity = wall;
            break;
        case "door":
        	int doorID = json.getInt("id");
            Door door = new Door(x, y, dungeon, doorID);
            onLoad(door);
            entity = door;
            break;
        case "key":
        	int keyID = json.getInt("id");
            Key key = new Key(x, y, dungeon, keyID);
            onLoad(key);
            entity = key;
            break;
        case "boulder":
            Boulder boulder = new Boulder(x, y, dungeon);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            Switch _switch = new Switch(x, y, dungeon);
            onLoad(_switch);
            entity = _switch;
            break;
        case "enemy":
            Enemy enemy = new Enemy(x, y, dungeon);
            onLoad(enemy);
            entity = enemy;
            break;
        case "sword":
            Sword sword = new Sword(x, y, dungeon);
            onLoad(sword);
            entity = sword;
            break;
        case "pistol":
        	for(int i = 0; i < 5; i++) {
        		Bullet bullet = new Bullet(x, y, dungeon);
        		onLoad(bullet);
        		dungeon.addDeadEntity(bullet);
        	}
        	
            Pistol pistol = new Pistol(x, y, dungeon);
            onLoad(pistol);
            entity = pistol;
            break;
        case "bomb":
            LitBomb bombA = new LitBomb(x, y, dungeon);
            onLoad(bombA);
            dungeon.addDeadEntity(bombA);
            
            UnLitBomb bombB = new UnLitBomb(x, y, dungeon);
            onLoad(bombB);
            entity = bombB;
            break;
        case "invincibility":
            Potion potion = new Potion(x, y, dungeon);
            onLoad(potion);
            entity = potion;
            break;
        // TODO Handle other possible entities
            
        case "treasure":
           Treasure treasure = new Treasure(x, y, dungeon);
            onLoad(treasure);
            entity = treasure;
            break;
        case "exit":
            Exit exit = new Exit(x, y, dungeon);
            onLoad(exit);
            entity = exit;
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Door door);
	public abstract void onLoad(Key key);
	public abstract void onLoad(Boulder boulder);
	public abstract void onLoad(Switch _switch);
	public abstract void onLoad(Enemy enemy);
	public abstract void onLoad(Sword sword);
	public abstract void onLoad(UnLitBomb bomb);
    public abstract void onLoad(Potion potion);
    public abstract void onLoad(LitBomb litBomb);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Pistol pistol);
    public abstract void onLoad(Bullet bullet);
    
    public abstract void onLoad(Goal goal);


    // TODO Create additional abstract methods for the other entities

}
