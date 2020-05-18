package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Enemy;

public class SwordUsageTest {

	//US1.2
	@Test
	void testSwingUp() throws FileNotFoundException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();

        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);

        Enemy mob = new Enemy(10, 9, dungeon);
        dungeon.addEntity(mob);
        dungeon.getPlayerA().setWeaponUses(5);
        dungeon.getPlayerA().setSwordAttack();
        dungeon.getPlayerA().attackUp();
        
        //test that enemy dies
        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	assertTrue(!(e.equals(mob)));
        }
        
        boolean dead = false;
        for(unsw.dungeon.Entity e : dungeon.getDeadEntities()) {
        	if(e.equals(mob)) {dead = true;}
        }
        assertTrue(dead);
  	}
	
	@Test
	void testSwingDown() throws FileNotFoundException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();

        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);

        Enemy mob = new Enemy(10, 11, dungeon);
        dungeon.addEntity(mob);
        dungeon.getPlayerA().setWeaponUses(5);
        dungeon.getPlayerA().setSwordAttack();
        dungeon.getPlayerA().attackDown();
        
        //test that enemy dies
        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	assertTrue(!(e.equals(mob)));
        }
        
        boolean dead = false;
        for(unsw.dungeon.Entity e : dungeon.getDeadEntities()) {
        	if(e.equals(mob)) {dead = true;}
        }
        assertTrue(dead);
	}
	
	@Test
	void testSwingLeft() throws FileNotFoundException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();

        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);

        Enemy mob = new Enemy(9, 10, dungeon);
        dungeon.addEntity(mob);
        dungeon.getPlayerA().setWeaponUses(5);
        dungeon.getPlayerA().setSwordAttack();
        dungeon.getPlayerA().attackLeft();
        
        //test that enemy dies
        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	assertTrue(!(e.equals(mob)));
        }
        
        boolean dead = false;
        for(unsw.dungeon.Entity e : dungeon.getDeadEntities()) {
        	if(e.equals(mob)) {dead = true;}
        }
        assertTrue(dead);
	}
	
	@Test
	void testSwingRight() throws FileNotFoundException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();

        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);

        Enemy mob = new Enemy(11, 10, dungeon);
        dungeon.addEntity(mob);
        dungeon.getPlayerA().setWeaponUses(5);
        dungeon.getPlayerA().setSwordAttack();
        dungeon.getPlayerA().attackRight();
        
        //test that enemy dies
        for(unsw.dungeon.Entity e : dungeon.getEntities()) {
        	assertTrue(!(e.equals(mob)));
        }
        
        boolean dead = false;
        for(unsw.dungeon.Entity e : dungeon.getDeadEntities()) {
        	if(e.equals(mob)) {dead = true;}
        }
        assertTrue(dead);
	}
	
}
