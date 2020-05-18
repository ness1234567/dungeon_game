package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Enemy;
import unsw.dungeon.LitBomb;
import unsw.dungeon.Potion;

public class CollectPotionTest {
		@Test
		void testCollectKey() throws FileNotFoundException {
			DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
	        Dungeon dungeon = dungeonLoader.load();
	                      
	        assertTrue(dungeon.getPlayerA().getX() == 10);
	        assertTrue(dungeon.getPlayerA().getY() == 10);
	        
		    Potion potion = new Potion(11, 10, dungeon);
		    dungeon.addEntity(potion);
		    
		    dungeon.getPlayerA().moveRight();
		    
		    assertTrue(dungeon.getPlayerA().isInvincible());
		    assertTrue(potion.getExists() == false);
		}
		
		@Test
		void testInvincibleVsEnemy() throws FileNotFoundException {
			DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
	        Dungeon dungeon = dungeonLoader.load();
	                      
	        assertTrue(dungeon.getPlayerA().getX() == 10);
	        assertTrue(dungeon.getPlayerA().getY() == 10);
	        
		    Potion potion = new Potion(11, 10, dungeon);
		    dungeon.addEntity(potion);
		    
		    dungeon.getPlayerA().moveRight();
		    
		    assertTrue(dungeon.getPlayerA().isInvincible());
		    assertTrue(potion.getExists() == false);
		    
		    Enemy enemy = new Enemy(12, 10, dungeon);
		    dungeon.addEntity(enemy);
		    
		    assertTrue(enemy.getX() == 12);
	        assertTrue(enemy.getY() == 10);
		    
		    dungeon.getPlayerA().moveRight();
		    
	        assertTrue(dungeon.getPlayerA().getX() == 12);
	        assertTrue(dungeon.getPlayerA().getY() == 10);
	        assertTrue(enemy.getExists() == false);
		}
		
		@Test
		void testInvincibleVsBomb() throws FileNotFoundException {
			DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
	        Dungeon dungeon = dungeonLoader.load();
	                      
	        assertTrue(dungeon.getPlayerA().getX() == 10);
	        assertTrue(dungeon.getPlayerA().getY() == 10);
	        
		    Potion potion = new Potion(11, 10, dungeon);
		    dungeon.addEntity(potion);
		    
		    dungeon.getPlayerA().moveRight();
		    
		    assertTrue(dungeon.getPlayerA().isInvincible());
		    assertTrue(potion.getExists() == false);
		    
		    LitBomb bomb = new LitBomb(12, 10, dungeon);
		    dungeon.addEntity(bomb);
		    
		    bomb.blow();
		    dungeon.removeEntity(bomb);
		    		    
	        assertTrue(dungeon.getPlayerA().getExists());
	        assertTrue(bomb.getExists() == false);
		}
		
		@Test
		void testEnemyRuns() throws FileNotFoundException {
			DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testD.json");
	        Dungeon dungeon = dungeonLoader.load();
	                      
	        dungeon.getPlayerA().setInvincible();
	        
	        assertTrue(dungeon.getPlayerA().getX() == 1);
	        assertTrue(dungeon.getPlayerA().getY() == 1);		    
		    assertTrue(dungeon.getPlayerA().isInvincible());
		    
		    Enemy enemy = new Enemy(2, 1, dungeon);
		    dungeon.addEntity(enemy);

	        assertTrue(enemy.getX() == 2);
	        assertTrue(enemy.getY() == 1);		    

		    enemy.moveEnemy();
		    
	        assertTrue(enemy.getX() == 3);
	        assertTrue(enemy.getY() == 1);		
	        
		    enemy.moveEnemy();

	        assertTrue(enemy.getX() == 4);
	        assertTrue(enemy.getY() == 1);
	        
	        assertTrue(dungeon.getPlayerA().getExists());
		}
}
