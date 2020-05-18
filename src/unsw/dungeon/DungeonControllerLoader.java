package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader{

    private List<ImageView> entityImages;
    private Goal goal;
    
    //Images
    private Image playerImage;
    private Image playerAImage;
    private Image playerBImage;
    private Image bulletImage;
    private Image pistolImage;
    private Image wallImage;
    private Image lockedDoorImage;
    private Image openDoorImage;
    private Image keyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image swordImage;
    private Image unLitBombImage;
    private Image LitBombImageA;
    private Image litBombImageB;
    private Image litBombImageC;
    private Image litBombImageD;
    private Image enemyImage;
    private Image potionImage;
    private Image exitImage;
    private Image treasureImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        try {
        entityImages = new ArrayList<ImageView>();
        playerAImage = new Image("/human_new.png");
        playerBImage = new Image("/human_black.png");
        playerImage = playerAImage;
        bulletImage = new Image("/bullet.png");
        pistolImage = new Image("/pistol.png");
        wallImage = new Image("/brick_brown_0.png");
        lockedDoorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        keyImage = new Image("/key.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        swordImage = new Image("/greatsword_1_new.png");
        unLitBombImage = new Image("/bomb_unlit.png");
        LitBombImageA = new Image("/bomb_lit_1.png");
        litBombImageB = new Image("/bomb_lit_2.png");
        litBombImageC = new Image("/bomb_lit_3.png");
        litBombImageD = new Image("/bomb_lit_4.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        potionImage = new Image("/bubbly.png");
        exitImage = new Image("/exit.png");
        treasureImage = new Image("gold_pile.png");
        
        } catch (Exception e) {}
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        playerImage = playerBImage;
        addEntity(player, view);
    }
    
    @Override
    public void onLoad(Pistol pistol) {
        ImageView view = new ImageView(pistolImage);
        addEntity(pistol, view);
    }
    
    @Override
    public void onLoad(Bullet bullet) {
        ImageView view = new ImageView();
        detectShootBullet(bullet, view);
        addEntity(bullet, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(lockedDoorImage);
    	addEntity(door, view);
    	detectDoorOpen(door, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	detectDropKey(key, view);
    	addEntity(key, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }

    @Override
    public void onLoad(Switch _switch) {
    	ImageView view = new ImageView(switchImage);
    	addEntity(_switch, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }
    
    @Override
    public void onLoad(UnLitBomb unLitBomb) {
    	ImageView view = new ImageView(unLitBombImage);
    	addEntity(unLitBomb, view);
    }
    
    @Override
    public void onLoad(LitBomb litBomb) {
    	ImageView view = new ImageView();
    	detectDropBomb(litBomb, view);
    	detectBombState(litBomb, view);
    	addEntity(litBomb, view);
    }
    
    @Override
    public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    public void onLoad(Goal goal) {
    	this.goal = goal;
    }

    private void addEntity(Entity entity, ImageView view) {
    	detectDelete(entity, view);
        trackPosition(entity, view);
        entityImages.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }
    
    private void detectDoorOpen(Door door, ImageView view) {
        door.getLockedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue == false)
					view.setImage(openDoorImage);
			}
        });
    }
    
    private void detectDelete(Entity e, ImageView view) {
    	e.getExistProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(newValue == false) {
					view.setImage(null);
				}
			}} );
    }
    
    private void detectDropKey(Key k, ImageView view) {
    	k.getExistProperty().addListener(new ChangeListener<Boolean>() {
    		
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(newValue == true) {
					view.setImage(keyImage);
				}
			}} );
    }
    
    private void detectDropBomb(LitBomb b, ImageView view) {
    	b.getExistProperty().addListener(new ChangeListener<Boolean>() {
    		
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(newValue == true) {
					view.setImage(LitBombImageA);
				}
			}} );
    }
    
    private void detectShootBullet(Bullet b, ImageView view) {
    	b.getExistProperty().addListener(new ChangeListener<Boolean>() {
    		
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(newValue == true) {
					view.setImage(bulletImage);
				}
			}} );
    }
    
    private void detectBombState(LitBomb bomb, ImageView view) {
    	bomb.getState().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				switch(bomb.getState().get()) {
				case 0:
					view.setImage(LitBombImageA);
					break;
				case 1:
					view.setImage(litBombImageB);
					break;
				case 2:
					view.setImage(litBombImageC);
					break;
				case 3:
					view.setImage(litBombImageD);
					break;
				}
			}});
    }
    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entityImages, goal);
    }


}
