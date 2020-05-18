package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {
	
	private IntegerProperty health;
	private BooleanProperty invincible;
	private IntegerProperty weaponUses;
	private IntegerProperty numBomb;
	private AttackBehavior attackBehaviour;
	private ObjectProperty<Key> key;
	
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.health = new SimpleIntegerProperty(); this.health.set(3);
        this.invincible = new SimpleBooleanProperty(); this.invincible.set(false);
        this.weaponUses = new SimpleIntegerProperty(); this.weaponUses.set(0);
        this.attackBehaviour = new unarmedAttack(dungeon, this);
        this.numBomb = new SimpleIntegerProperty(); this.numBomb.set(0);
        this.key = new SimpleObjectProperty<Key>();
    }

    public void moveUp() {
        if (getY() > 0) {
        	if(interactWithXY(getX(), getY()-1) == true) {
        		y().set(getY() - 1);
        	}
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
        	if(interactWithXY(getX(), getY() + 1) == true) {
                y().set(getY() + 1);
        	}
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
        	if(interactWithXY(getX() - 1, getY()) == true) {
                x().set(getX() - 1);
        	}
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
        	if(interactWithXY(getX() + 1, getY()) == true) {
                x().set(getX() + 1);
        	}
        }
    }
    
    //have the player interact with entities on location x, y
    public boolean interactWithXY(int x, int y) {
        ArrayList<Entity> eList = dungeon.entitiesOnXY(x, y);
        for(Entity e : eList) {
        	if(e.interact(this) == false) {
        		return false;
        	}
        }
        return true;
    }

	@Override
	public boolean interact(Player player) {
		return false;
	}

	public int getHealth() {
		return health.get();
	}

	public void setHealth(int health) {
		this.health.set(health);;
	}

	public boolean isInvincible() {
		return invincible.get();
	}

	public void setInvincible() {
		this.invincible.set(true);
		System.out.println("You are invincible!");

		//make invincible for 5 seconds
		Timeline clock = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Invincibility has run out!");
				invincible.set(false);
			}
			
		}));
		clock.setDelay(Duration.millis(5000));
		clock.setCycleCount(1);
		clock.play();
	}

	public void attackUp() {
		if(weaponUses.get() <= 0) {
			System.out.println("No Sword to Use!!");
			return;	
		}
		//remove killed enemies
		weaponUses.set(weaponUses.get()-1); 
		attackBehaviour.attackUp();
	}
	
	public void attackRight() {
		if(weaponUses.get() <= 0) {
			System.out.println("No Sword to Use!!");
			return;	
		}
		//remove killed enemies
		weaponUses.set(weaponUses.get()-1); 
		attackBehaviour.attackRight();
	}
	
	public void attackDown() {
		if(weaponUses.get() <= 0) {
			System.out.println("No Sword to Use!!");
			return;	
		}
		//remove killed enemies
		weaponUses.set(weaponUses.get()-1); 
		attackBehaviour.attackDown();
	}
	
	public void attackLeft() {
		if(weaponUses.get() <= 0) {
			System.out.println("No Sword to Use!!");
			return;	
		}
		//remove killed enemies
		weaponUses.set(weaponUses.get()-1); 
		attackBehaviour.attackLeft();
	}
	
	public int getWeaponUses() {
		return weaponUses.get();
	}

	public void setWeaponUses(int weaponUses) {
		this.weaponUses.set(weaponUses);
	}
	
	public void dropKey() {
		ArrayList<Entity> eList = dungeon.entitiesOnXY(getX(), getY());
		for(Entity e : eList) {
			if(e instanceof Key)
				return;
		}
		key.get().drop(this);
		key.set(null);
	}
	
	public ObjectProperty<Key> getKeyProperty() {
		return key;
	}
	
	public void removeKey() {
		key.set(null);
	}
	
	public void addKey(Key k) {
		key.set(k);
	}
	
	public boolean hasKey() {
		if(key.get() == null) {
			return false;
		}
		return true;
	}
	
	public boolean hasKeybyID(int id) {
		if(key.get() == null) {
			return false;
		} else if (key.get().getID() == id) {
			return true;
		}
		return false;
	}

	public int getNumBomb() {
		return numBomb.get();
	}

	public void setNumBomb(int numBomb) {
		this.numBomb.set(numBomb);
	}
	
	public void dropBomb() {
		if(numBomb.get() > 0) {
			numBomb.set(numBomb.get()-1);
			for(Entity e : dungeon.getDeadEntities()) {
				if(e instanceof LitBomb) {
					((LitBomb) e).startBomb(this);
					break;
				}
			}
		}
	}
	
	public void setSwordAttack() {
		this.attackBehaviour = new SwordAttack(dungeon, this);
	}
	
	public void setPistolAttack() {
		this.attackBehaviour = new PistolAttack(dungeon, this);
	}
	
	public BooleanProperty getStatusProperty() {
		return invincible;
	}
	
	public IntegerProperty getBombProperty() {
		return numBomb;
	}
	
	public IntegerProperty getWeaponProperty() {
		return weaponUses;
	}
}

