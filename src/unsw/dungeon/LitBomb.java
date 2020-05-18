package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class LitBomb extends Entity{

	private IntegerProperty state;
	private final Timeline clock;
	
	public LitBomb(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		state = new SimpleIntegerProperty(0);
		clock = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(getExists()) { 
					System.out.println("Bomb has ticked!");
					nextState();
				}
			}
			
		}));
		clock.setCycleCount(5);
		clock.pause();
	}

	public void startBomb(Player player) {
		System.out.println("Bomb is Starting!");
		x().set(player.getX());
		y().set(player.getY());
		dungeon.addEntity(this);
		if(getExists()) {
			clock.playFromStart();
		}
	}
	
	@Override
	public boolean interact(Player player) {
		return true;
	}
	
	public IntegerProperty getState() {
		return this.state;
	}

	public void nextState() {
		state.set(state.get() + 1);;
		if(state.get() == 3) {
			blow();
		}
		if(state.get() == 4) {
			System.out.println("Removing bomb");
			clock.pause();
		    dungeon.removeEntity(this);
		}
	}
	
   public void blow() {
	   System.out.println("Bomb has blown up");

	   dungeon.killAll(getX(), getY());
	   dungeon.killAll(getX()+1, getY());
	   dungeon.killAll(getX()-1, getY());
	   dungeon.killAll(getX(), getY()+1);
	   dungeon.killAll(getX(), getY()-1);
	   
	   dungeon.destroyBoulder(getX(), getY());
	   dungeon.destroyBoulder(getX()+1, getY());
	   dungeon.destroyBoulder(getX()-1, getY());
	   dungeon.destroyBoulder(getX(), getY()+1);
	   dungeon.destroyBoulder(getX(), getY()-1);
   }
	
}
