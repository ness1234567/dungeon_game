package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity{

	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
	}

	@Override
	public boolean interact(Player player) {
		//find the direction that player is pushing boulder
		int dX = getX()-player.getX();
		int dY = getY()-player.getY();
		int newX = getX();
		int newY = getY();
		
		if(dX == 1) {
			//player pushing boulder to right
			newX = getX()+1;
		} else if (dX == -1) {
			//player pushing boulder to left
			newX = getX()-1;
		} else if(dY == 1) {
			//player pushing boulder up
			newY = getY()+1;
		} else if (dY == -1) {
			//player pushing boulder down
			newY = getY()-1;
		}
		
		//don't push if blocked by wall, boulder or locked door
		ArrayList<Entity> eList = dungeon.entitiesOnXY(newX, newY);
		for(Entity e : eList) {
			if((e instanceof Wall) || (e instanceof Boulder)){
				return false;
			}
			if(e instanceof Door) {
				Door d = (Door)e;
				if(d.Locked())
					return false;
			}
		}
		
		//deactivate switches that boulder was on before push
		eList = dungeon.entitiesOnXY(getX(), getY());
		for(Entity e : eList) {
			if(e instanceof Switch) {
				Switch s = (Switch)e;
				s.setActivated(false);
				break;
			}
		}
				
		//activate switches boulder is on after push
		eList = dungeon.entitiesOnXY(newX, newY);
		for(Entity e : eList) {
			if(e instanceof Switch) {
				Switch s = (Switch)e;
				s.setActivated(true);
				if(dungeon.isComplete(false)) {
					System.out.println("You have activated all switches! Level Complete!");
				}
			} else if(e instanceof Enemy){
				dungeon.killEnemies(newX,newY);
			} else {
				dungeon.removeEntity(e);
			}
			
		}
		
		//Push boulder if unblocked
		x().set(newX);
		y().set(newY);
		
		return true;
	}
	
	

}
