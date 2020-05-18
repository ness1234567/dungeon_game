package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Enemy;
import unsw.dungeon.Key;
import unsw.dungeon.LitBomb;
import unsw.dungeon.Potion;
import unsw.dungeon.Switch;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.UnLitBomb;
import unsw.dungeon.Wall;

public class BoulderTest {
	@Test
	void testBoulderPush() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //test can push 1 boulder
	    Boulder boulderA = new Boulder(11, 10, dungeon);
	    dungeon.addEntity(boulderA);

	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(dungeon.getPlayerA().getX() == 11);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        assertTrue(boulderA.getX() == 12);
        assertTrue(boulderA.getY() == 10);

        //test can't push 1 boulder against wall
        Wall wall = new Wall(13, 10, dungeon);
        dungeon.addEntity(wall);
        
        dungeon.getPlayerA().moveRight();
        
        assertTrue(dungeon.getPlayerA().getX() == 11);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        assertTrue(boulderA.getX() == 12);
        assertTrue(boulderA.getY() == 10);
        assertTrue(wall.getX() == 13);
        assertTrue(wall.getY() == 10);

	    
	    //test can't push 2 boulders
	    Boulder boulderC = new Boulder(11, 11, dungeon);
	    Boulder boulderD = new Boulder(11, 12, dungeon);
        dungeon.addEntity(boulderC);
        dungeon.addEntity(boulderD);
	    
	    assertTrue(dungeon.hasEntity(boulderC));
	    assertTrue(dungeon.hasEntity(boulderD));
	    
	    dungeon.getPlayerA().moveDown();
	    
        assertTrue(dungeon.getPlayerA().getX() == 11);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        assertTrue(boulderC.getX() == 11);
        assertTrue(boulderC.getY() == 11);
        assertTrue(boulderD.getX() == 11);
        assertTrue(boulderD.getY() == 12);

	}
	
	@Test
	void testBoulderCollisionEnemy() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //try to push boulder into other crushable entities;
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    Enemy enemy = new Enemy(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(enemy);
	    
	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(enemy.getExists() == false);
	}
	
	@Test
	void testBoulderCollisionPotion() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //try to push boulder into other crushable entities;
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    Potion potion = new Potion(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(potion);
	    
	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(potion.getExists() == false);
	}
	
	@Test
	void testBoulderCollisionKey() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //try to push boulder into other crushable entities;
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    Key key = new Key(12, 10, dungeon, 0);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(key);
	    
	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(key.getExists() == false);
	}
	
	@Test
	void testBoulderCollisionTreasure() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //try to push boulder into other crushable entities;
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    Treasure treasure = new Treasure(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(treasure);
	    
	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(treasure.getExists() == false);
	}
	
	@Test
	void testBoulderCollisionSword() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //try to push boulder into other crushable entities;
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    Sword sword = new Sword(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(sword);
	    
	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(sword.getExists() == false);
	}
	
	@Test
	void testBoulderCollisionUnlitbomb() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //try to push boulder into other crushable entities;
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    UnLitBomb bomb = new UnLitBomb(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(bomb);
	    
	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(bomb.getExists() == false);
	}
	
	@Test
	void testBoulderCollisionLitbomb() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testB.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //try to push boulder into other crushable entities;
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    LitBomb bomb = new LitBomb(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(bomb);
	    
	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(bomb.getExists() == false);
	}
	
	@Test
	void testBoulderCollisionSwitch() throws FileNotFoundException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();
                      
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //try to push boulder into other crushable entities;
	    Boulder boulder = new Boulder(11, 10, dungeon);
	    Switch _switch = new Switch(12, 10, dungeon);
	    dungeon.addEntity(boulder);
	    dungeon.addEntity(_switch);
        assertTrue(_switch.isActivated() == false);

	    dungeon.getPlayerA().moveRight();
	    
        assertTrue(boulder.getX() == 12);
        assertTrue(boulder.getY() == 10);
        assertTrue(_switch.getExists() == true);
        assertTrue(_switch.isActivated() == true);
	}
}
