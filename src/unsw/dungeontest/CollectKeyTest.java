package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Key;

public class CollectKeyTest {
	@Test
	void testCollectKey() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
	    Key key = new Key(11, 10, dungeon, 0);
	    dungeon.addEntity(key);
	    
	    assertTrue(dungeon.getPlayerA().hasKey() == false);
	    
	    dungeon.getPlayerA().moveRight();
	    
	    assertTrue(dungeon.getPlayerA().hasKey() == true);
	    assertTrue(dungeon.getPlayerA().getKeyProperty().get().getID() == 0);
	    
	}
	
	@Test
	void testOpenDoor() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
	    Key key = new Key(11, 10, dungeon, 0);
	    dungeon.addEntity(key);
	    
	    assertTrue(dungeon.getPlayerA().hasKey() == false);
	    
	    dungeon.getPlayerA().moveRight();
	    
	    assertTrue(dungeon.getPlayerA().hasKey() == true);
	    assertTrue(dungeon.getPlayerA().getKeyProperty().get().getID() == 0);

	    Door door = new Door(12, 10, dungeon, 0);
	    dungeon.addEntity(door);

	    assertTrue(door.getLockedProperty().get() == true);

	    dungeon.getPlayerA().moveRight();
	    
	    assertTrue(dungeon.getPlayerA().getX() == 12);
	    assertTrue(dungeon.getPlayerA().getY() == 10);
	    assertTrue(dungeon.getPlayerA().hasKey() == false);
	    assertTrue(door.getLockedProperty().get() == false);
	}
	
	@Test
	void testOneKeyOnly() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
	    Key key = new Key(11, 10, dungeon, 0);
	    Key key2 = new Key(12, 10, dungeon, 1);
	    dungeon.addEntity(key);
	    dungeon.addEntity(key2);
	    
	    assertTrue(dungeon.getPlayerA().hasKey() == false);
	    
	    dungeon.getPlayerA().moveRight();
	    
	    assertTrue(dungeon.getPlayerA().getX() == 11);
	    assertTrue(dungeon.getPlayerA().getY() == 10);
	    assertTrue(dungeon.getPlayerA().hasKey() == true);
	    assertTrue(dungeon.getPlayerA().getKeyProperty().get().getID() == 0);

	    dungeon.getPlayerA().moveRight();
	    
	    assertTrue(dungeon.getPlayerA().getX() == 12);
	    assertTrue(dungeon.getPlayerA().getY() == 10);
	    assertTrue(dungeon.getPlayerA().hasKey() == true);
	    assertTrue(dungeon.getPlayerA().getKeyProperty().get().getID() == 0);
    
	}
}
