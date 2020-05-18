package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.LitBomb;
import unsw.dungeon.UnLitBomb;

public class CollectBombTest {
	@Test
	void testCollectBomb() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
               
        UnLitBomb bomb = null;
        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	if(e instanceof UnLitBomb) {
        		bomb = (UnLitBomb) e;
        		break;
        	}
        }
        
        assertTrue(bomb.getX() == 10);
        assertTrue(bomb.getY() == 11);
        
        assertTrue(dungeon.getPlayerA().getNumBomb() == 0);
        
        dungeon.getPlayerA().moveDown();
        
        assertTrue(dungeon.getPlayerA().getNumBomb() == 1);

        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	assertTrue(!(e instanceof LitBomb));
        }

	}
	
	void testBombDestroyBoulder() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
	    Dungeon dungeon = dungeonLoader.load();
	                  
	    LitBomb bomb = new LitBomb(0, 0, dungeon);
	    dungeon.addDeadEntity(bomb);
	    
	    assertTrue(dungeon.getPlayerA().getX() == 10);
	    assertTrue(dungeon.getPlayerA().getY() == 10);
	           
	    dungeon.getPlayerA().setNumBomb(1);
	    
	    Boulder boulderA = new Boulder(11, 10, dungeon);
	    Boulder boulderB = new Boulder(10, 11, dungeon);
	    Boulder boulderC = new Boulder(9, 10, dungeon);
	    Boulder boulderD = new Boulder(10, 9, dungeon);
	    
	    dungeon.addEntity(boulderA);
	    dungeon.addEntity(boulderB);
	    dungeon.addEntity(boulderC);
	    dungeon.addEntity(boulderD);
	    
	    assertTrue(dungeon.hasEntity(boulderA));
	    assertTrue(dungeon.hasEntity(boulderB));
	    assertTrue(dungeon.hasEntity(boulderC));
	    assertTrue(dungeon.hasEntity(boulderD));
	    		
	    dungeon.getPlayerA().dropBomb();
		bomb.nextState();
		bomb.nextState();
		bomb.nextState();
		bomb.nextState();  
	    
	    for(unsw.dungeon.Entity e : dungeon.getEntities()) {
	    	assertTrue(!(e instanceof Boulder));
	    }
	
	}
}
