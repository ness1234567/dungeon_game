package unsw.dungeontest;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Enemy;
import unsw.dungeon.LitBomb;

public class TreasureTest {
		
	@Test
	void testTreasure() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testTreasure.json");
        Dungeon dungeon = dungeonLoader.load();
        
        assertTrue(dungeon.getPlayerA().getX() == 1);
        assertTrue(dungeon.getPlayerA().getY() == 1);
        for(int i = 0;i <4; i++) {
            dungeon.getPlayerA().moveRight();      
        } 
        assertTrue(!dungeon.isComplete(false));
        dungeon.getPlayerA().moveRight();      
        assertTrue(dungeon.isComplete(false));
	}
}
