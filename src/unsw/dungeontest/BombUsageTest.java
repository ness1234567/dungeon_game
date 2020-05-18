package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.LitBomb;

public class BombUsageTest {

	@Test
	void testPlaceBomb() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();
        
        LitBomb bomb = new LitBomb(0, 0, dungeon);
        dungeon.addDeadEntity(bomb);
                
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	assertTrue(!(e instanceof LitBomb));
        }
        
        dungeon.getPlayerA().setNumBomb(1);
        dungeon.getPlayerA().dropBomb();
        
        boolean bombExists = false;
        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	if(e instanceof LitBomb) {
        		bombExists = true;
        		LitBomb b = (LitBomb)e;
        		assertTrue(b.getX() == dungeon.getPlayerA().getX());
        		assertTrue(b.getY() == dungeon.getPlayerA().getY());
        	}
        }
    	assertTrue(bombExists);
	}
	
	
	@Test
	void testBombStates() throws FileNotFoundException, InterruptedException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA2.json");
        Dungeon dungeon = dungeonLoader.load();
        
        LitBomb bomb = new LitBomb(0, 0, dungeon);
        dungeon.addDeadEntity(bomb);
                
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	assertTrue(!(e instanceof LitBomb));
        }
        
        dungeon.getPlayerA().setNumBomb(1);
        dungeon.getPlayerA().dropBomb();
        
        dungeon.getPlayerA().moveLeft();
        dungeon.getPlayerA().moveLeft();
        dungeon.getPlayerA().moveLeft();
        dungeon.getPlayerA().moveLeft();

		assertTrue(bomb.getState().get() == 0);
		bomb.nextState();
		assertTrue(bomb.getState().get() == 1);
		bomb.nextState();
		assertTrue(bomb.getState().get() == 2);
		bomb.nextState();
		assertTrue(bomb.getState().get() == 3);
		bomb.nextState();
		assertTrue(bomb.getState().get() == 4);

		for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	assertTrue(!(e instanceof LitBomb));
        }
	}
}
