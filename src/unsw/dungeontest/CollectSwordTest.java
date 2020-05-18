package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Sword;

public class CollectSwordTest {
	@Test
	void testPickUpSword() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
	    Sword sword = new Sword(11, 10, dungeon);
	    dungeon.addEntity(sword);
	    
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 0);
	    assertTrue(sword.getExists() == true);
	    
	    dungeon.getPlayerA().moveRight();
	    
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 5);
	    assertTrue(sword.getExists() == false);
	}
	
	void testSwordDurability() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
	    Sword sword = new Sword(11, 10, dungeon);
	    dungeon.addEntity(sword);
	    
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 0);
	    assertTrue(sword.getExists() == true);
	    
	    dungeon.getPlayerA().moveRight();
	    
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 5);
	    assertTrue(sword.getExists() == false);
	    
	    dungeon.getPlayerA().attackDown();
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 4);
	    dungeon.getPlayerA().attackDown();
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 3);
	    dungeon.getPlayerA().attackDown();
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 2);
	    dungeon.getPlayerA().attackDown();
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 1);
	    dungeon.getPlayerA().attackDown();
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 0);
	    dungeon.getPlayerA().attackDown();
	    assertTrue(dungeon.getPlayerA().getWeaponUses() == 0);
	}
}
