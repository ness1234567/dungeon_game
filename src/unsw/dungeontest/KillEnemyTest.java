package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Enemy;
import unsw.dungeon.LitBomb;

public class KillEnemyTest {
	@Test
	void testKillBySword() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        Enemy mob = new Enemy(10, 11, dungeon);
        dungeon.addEntity(mob);
        
        dungeon.getPlayerA().setWeaponUses(5);
        dungeon.getPlayerA().setSwordAttack();
        dungeon.getPlayerA().attackDown();
        
        assertTrue(mob.getExists() == false);

	}
	
	@Test
	void testKillByBomb() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
        
        LitBomb bomb = new LitBomb(10, 11, dungeon);
        dungeon.addDeadEntity(bomb);
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        Enemy mob = new Enemy(10, 11, dungeon);
        dungeon.addEntity(mob);
        
        dungeon.getPlayerA().setNumBomb(1);
        dungeon.getPlayerA().dropBomb();
		assertTrue(bomb.getState().get() == 0);
		bomb.nextState();
		assertTrue(bomb.getState().get() == 1);
		bomb.nextState();
		assertTrue(bomb.getState().get() == 2);
		bomb.nextState();
		assertTrue(bomb.getState().get() == 3);
		bomb.nextState();

        assertTrue(mob.getExists() == false);
	}
	
	@Test
	void testKillByInvincibility() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        Enemy mob = new Enemy(10, 11, dungeon);
        dungeon.addEntity(mob);
        
        dungeon.getPlayerA().setInvincible();
        dungeon.getPlayerA().moveDown();
        
        assertTrue(mob.getExists() == false);
	}
}
