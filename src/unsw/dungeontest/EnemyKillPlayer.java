package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Enemy;

public class EnemyKillPlayer {
	@Test
	void testEnemyTouchPlayer() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        Enemy mob = new Enemy(10, 11, dungeon);
        dungeon.addEntity(mob);
        
        mob.moveEnemy();
        
        assertTrue(mob.getX() == 10);
        assertTrue(mob.getY() == 10);
        
        assertTrue(mob.getExists() == true);
        assertTrue(dungeon.getPlayerA().getExists() == false);
	}
	
	@Test
	void testShortestPath() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testShortestPath.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 1);
        assertTrue(dungeon.getPlayerA().getY() == 1);
        
        Enemy mob = new Enemy(2, 4, dungeon);
        dungeon.addEntity(mob);
        
        mob.moveEnemy();
        
        assertTrue(mob.getX() == 2);
        assertTrue(mob.getY() == 3);
        
        mob.moveEnemy();
        
        assertTrue(mob.getX() == 2);
        assertTrue(mob.getY() == 2);
        
        mob.moveEnemy();
        
        assertTrue(mob.getX() == 2);
        assertTrue(mob.getY() == 1);
        
        mob.moveEnemy();
        
        assertTrue(mob.getX() == 1);
        assertTrue(mob.getY() == 1);
        
        assertTrue(mob.getExists() == true);
        assertTrue(dungeon.getPlayerA().getExists() == false);
	}
	
	@Test
	void testNoPathMovement() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testD.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 1);
        assertTrue(dungeon.getPlayerA().getY() == 1);
        
        Enemy mob = new Enemy(4, 1, dungeon);
        dungeon.addEntity(mob);
        
        //spawn boulder to block only path to player
        Boulder boulder = new Boulder(2, 1, dungeon);
        dungeon.addEntity(boulder);
        
        mob.moveEnemy();
        
        System.out.println("X = "+ mob.getX());
        System.out.println("Y = "+ mob.getY());

        assertTrue(mob.getX() == 4);
        assertTrue(mob.getY() == 1);
        
        assertTrue(mob.getExists() == true);
	}
}
