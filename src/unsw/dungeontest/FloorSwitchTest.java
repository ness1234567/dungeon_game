package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Switch;

public class FloorSwitchTest {
	@Test
	void testSwitchActivate() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    Switch _switch = new Switch(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(_switch);
        assertTrue(_switch.isActivated() == false);

	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(_switch.getExists() == true);
        assertTrue(_switch.isActivated() == true);
	}
	
	@Test
	void testSwitchDeactivate() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    Switch _switch = new Switch(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(_switch);
        assertTrue(_switch.isActivated() == false);

	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(_switch.getExists() == true);
        assertTrue(_switch.isActivated() == true);
        
        dungeon.getPlayerA().moveRight();
        
        assertTrue(boulder.getX() == 13);
        assertTrue(boulder.getY() == 10);
        assertTrue(_switch.getExists() == true);
        assertTrue(_switch.isActivated() == false);
	}
}
