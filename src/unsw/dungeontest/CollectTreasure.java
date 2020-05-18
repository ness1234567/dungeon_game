package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Treasure;

public class CollectTreasure {
	@Test
	void testPickUpTreasure() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
	    Treasure treasure = new Treasure(11, 10, dungeon);
	    dungeon.addEntity(treasure);
	    
	    assertTrue(treasure.getExists() == true);
	    
	    dungeon.getPlayerA().moveRight();
	    
	    assertTrue(treasure.getExists() == false);
	}
}
