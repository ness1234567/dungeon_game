package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Bullet extends Entity{

	private int direction;
	private final Timeline clock;
	
	public Bullet(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		direction = 0;
		clock = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(getExists()) { 
					System.out.println("Bullet has travelled!");
					moveBullet();
					hitDetect();
				}
			}
			
		}));
		clock.setCycleCount(Integer.MAX_VALUE);
		clock.pause();
	}
	
	public void shootBullet(Player player) {
		System.out.println("A bullet was shot!");
		x().set(player.getX());
		y().set(player.getY());
		moveBullet();
		dungeon.addEntity(this);
		clock.playFromStart();
	}

	public void moveBullet() {
		if(direction == 0) {
			x().set(getX() + 1);
		} else if (direction == 1) {
			y().set(getY() + 1);
		} else if (direction == 2) {
			x().set(getX() - 1);
		} else if (direction == 3) {
			y().set(getY() - 1);
		}
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void hitDetect() {
		ArrayList<Entity> eList = dungeon.entitiesOnXY(getX(), getY());
		if(eList.isEmpty()) {
			return;
		}
		for(Entity e : eList) {
			if(e instanceof Player) {
				Player p = (Player)e;
				if(!p.isInvincible()) {
					dungeon.removeEntity(p);
				}
			} else if(e instanceof Enemy) {
				dungeon.removeEntity(e);
				if(dungeon.isComplete(false)) {
					dungeon.setComplete(true);
					System.out.println("You have defeated all enemies! Level Complete!");
					return;
				}
			}
			if(!e.equals(this)) {
				System.out.println("Collided with entity");
				clock.pause();
				dungeon.removeEntity(this);
				break;
			}
		}
	}
	
	@Override
	public boolean interact(Player player) {
		//If player walks into enemy, kill them
		if(player.isInvincible()) {
			dungeon.removeEntity(this);
		} else {
			dungeon.removeEntity(player);
			System.out.println("A player has Walked into a Bullet!");
		}
		return true;
	}
}
