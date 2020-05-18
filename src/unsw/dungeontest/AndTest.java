package unsw.dungeontest;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Enemy;
import unsw.dungeon.LitBomb;

public class AndTest {
		
	@Test
	void testAndOr() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testAnd.json");
        Dungeon dungeon = dungeonLoader.load();
        
        assertTrue(dungeon.getPlayerA().getX() == 1);
        assertTrue(dungeon.getPlayerA().getY() == 1);
        dungeon.getPlayerA().setInvincible();
        dungeon.getPlayerA().moveRight();      //moved to an exit. Shouldn't finish
        assertTrue(!dungeon.isComplete(true));
        dungeon.getPlayerA().moveRight();      //collected the treasure. Shouldn't finish
        assertTrue(!dungeon.isComplete(false));
        dungeon.getPlayerA().moveRight();      //killed the enemy. Shouldn't finish
        assertTrue(!dungeon.isComplete(false));
        for(int i = 0;i < 2; i++) {//move back to exit
            dungeon.getPlayerA().moveLeft();      
        }    
        assertTrue(dungeon.isComplete(true));//should finish
	}
}
