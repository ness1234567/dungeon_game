package unsw.dungeontest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Wall;

public class PlayerMovementTest {
	
	//US1.1
	@Test
	void testMoveUp() throws IOException{
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();
        
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        dungeon.getPlayerA().moveUp();

        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 9);
        
        //add door 2 tiles above
        Door door = new Door(10, 7, dungeon, 0);
        dungeon.addEntity(door);
        dungeon.getPlayerA().moveUp();
        dungeon.getPlayerA().moveUp();
        dungeon.getPlayerA().moveUp();
        dungeon.getPlayerA().moveUp();
        
        //test movement is blocked by locked door
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 8);

        dungeon.removeEntity(door);
        dungeon.addEntity(new Wall(10, 7, dungeon));
        dungeon.getPlayerA().moveUp();

        //test movement is blocked by wall
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 8);
	}
	
	@Test
	void testMoveDown() throws IOException{
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();
        
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        dungeon.getPlayerA().moveDown();

        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 11);
        
        //add door 2 tiles below
        Door door = new Door(10, 13, dungeon, 0);
        dungeon.addEntity(door);
        dungeon.getPlayerA().moveDown();
        dungeon.getPlayerA().moveDown();
        dungeon.getPlayerA().moveDown();
        dungeon.getPlayerA().moveDown();
        
        //test movement is blocked by locked door
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 12);

        dungeon.removeEntity(door);
        dungeon.addEntity(new Wall(10, 13, dungeon));
        dungeon.getPlayerA().moveDown();

        //test movement is blocked by wall
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 12);
	}
	
	@Test
	void testMoveLeft() throws IOException{
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();
        
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        dungeon.getPlayerA().moveLeft();

        assertTrue(dungeon.getPlayerA().getX() == 9);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //add door 2 tiles to left
        Door door = new Door(7, 10, dungeon, 0);
        dungeon.addEntity(door);
        dungeon.getPlayerA().moveLeft();
        dungeon.getPlayerA().moveLeft();
        dungeon.getPlayerA().moveLeft();
        dungeon.getPlayerA().moveLeft();
        
        //test movement is blocked by locked door
        assertTrue(dungeon.getPlayerA().getX() == 8);
        assertTrue(dungeon.getPlayerA().getY() == 10);

        dungeon.removeEntity(door);
        dungeon.addEntity(new Wall(7, 10, dungeon));
        dungeon.getPlayerA().moveLeft();

        //test movement is blocked by wall
        assertTrue(dungeon.getPlayerA().getX() == 8);
        assertTrue(dungeon.getPlayerA().getY() == 10);
	}
	
	@Test
	void testMoveRight() throws IOException{
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testA.json");
        Dungeon dungeon = dungeonLoader.load();
        
        assertTrue(dungeon.getPlayerA().getX() == 10);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        dungeon.getPlayerA().moveRight();

        assertTrue(dungeon.getPlayerA().getX() == 11);
        assertTrue(dungeon.getPlayerA().getY() == 10);
        
        //add door 2 tiles to left
        Door door = new Door(13, 10, dungeon, 0);
        dungeon.addEntity(door);
        dungeon.getPlayerA().moveRight();
        dungeon.getPlayerA().moveRight();
        dungeon.getPlayerA().moveRight();
        dungeon.getPlayerA().moveRight();
        
        //test movement is blocked by locked door
        assertTrue(dungeon.getPlayerA().getX() == 12);
        assertTrue(dungeon.getPlayerA().getY() == 10);

        dungeon.removeEntity(door);
        dungeon.addEntity(new Wall(13, 10, dungeon));
        dungeon.getPlayerA().moveRight();

        //test movement is blocked by wall
        assertTrue(dungeon.getPlayerA().getX() == 12);
        assertTrue(dungeon.getPlayerA().getY() == 10);
	}
}
