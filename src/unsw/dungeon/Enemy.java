package unsw.dungeon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Enemy extends Entity{

	private final Timeline clock;
	
	public Enemy(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		clock = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(getExists() && !dungeon.getPaused()) { 
					//moveEnemy();
				}
			}
			
		}));
		clock.setCycleCount(Integer.MAX_VALUE);

		clock.play();

	}

	@Override
	public boolean interact(Player player) {
		//If player walks into enemy, kill them
		if(player.isInvincible()) {
			dungeon.removeEntity(this);
			if(dungeon.isComplete(false)) {
				System.out.println("You have defeated all enemies! Level Complete!");
			}
		} else {
			dungeon.removeEntity(player);
			System.out.println("A player has Walked into an enemy!");
		}
		return true;
	}
	
	public void moveEnemy() {
		int newX = getX();
		int newY = getY();
		Coord enemyPos = new Coord(newX,newY);
		Player player = getClosestPlayer();
		if(player == null) {
			return;
		}
		/*if(player.equals(dungeon.getPlayerA())) {
			System.out.println("Chasing PlayerA");
		} else {
			System.out.println("Chasing PlayerB");
		}*/
		
		Coord playerPos = new Coord(player.getX(), player.getY());
		List<Coord> visited = new ArrayList<Coord>();
		Coord newPos = getNextMove(playerPos,enemyPos, visited);
		
		int distFromEnemy = 0;
		distFromEnemy += Math.abs(getX() - newPos.getX());
		distFromEnemy += Math.abs(getY() - newPos.getY());
		if(distFromEnemy > 1) {
			return;
		}

		if(player.isInvincible()) {
			Coord up = new Coord(enemyPos.getX(),enemyPos.getY()+1);
			Coord down = new Coord(enemyPos.getX(),enemyPos.getY()-1);
			Coord left = new Coord(enemyPos.getX()-1,enemyPos.getY());
			Coord right = new Coord(enemyPos.getX()+1,enemyPos.getY());
			for(int i = 0; i<4; i++) {
				if(!up.equals(newPos)) {
					if(isPassable(up)) {
						newPos = up;
						break;
					}
				}
				if(!down.equals(newPos)) {
					if(isPassable(down)) {
						newPos = down;
						break;
					}
				}
				if(!right.equals(newPos)) {
					if(isPassable(right)) {
						newPos = right;
						break;
					}
				}
				if(!left.equals(newPos)) {
					if(isPassable(left)) {
						newPos = left;
						break;
					}
				}
			}
		}
		x().set(newPos.getX());
		y().set(newPos.getY());
		
		ArrayList<Entity> eList = dungeon.entitiesOnXY(getX(), getY());
		for(Entity e : eList) {
			if(e instanceof Bullet) {
				dungeon.removeEntity(this);
				if(dungeon.isComplete(false)) {
					System.out.println("You have defeated all enemies! Level Complete!");
				}
				break;
			}
		}
		
		if((player.getX() == x().get())&&(player.getY() == y().get())) {
			if(!player.isInvincible()) {
				dungeon.removeEntity(player);
				System.out.println("A player has been hunted down!");
			} else {
				dungeon.removeEntity(this);
				if(dungeon.isComplete(false)) {
					System.out.println("You have defeated all enemies! Level Complete!");
				}
			}
		}
	}
	
	public Coord getNextMove(Coord currPos, Coord destination, List<Coord> visited){
			
		Queue<Coord> toVisit = new LinkedList<Coord>();
		//visited.add(currPos);
		toVisit.add(currPos);
		
		while(!toVisit.isEmpty()) {
			Coord curr = toVisit.remove();
			if (!contains(visited, curr)) {
				visited.add(curr);
				Coord up = new Coord(curr.getX(),curr.getY()+1);
				Coord down = new Coord(curr.getX(),curr.getY()-1);
				Coord left = new Coord(curr.getX()-1,curr.getY());
				Coord right = new Coord(curr.getX()+1,curr.getY());

				if(!contains(visited,up)) {
					if(isPassable(up)) {
						toVisit.add(up);
					}
				}
				if(!contains(visited,down)) {
					if(isPassable(down)) {
						toVisit.add(down);
					}
				}
				if(!contains(visited,left)) {
					if(isPassable(left)) {
						toVisit.add(left);
					}
				}
				if(!contains(visited,right)) {
					if(isPassable(right)) {
						toVisit.add(right);
					}
				}

				if(destination.equals(up)||destination.equals(down)||destination.equals(left)||destination.equals(right)) {
					return getNextMove(curr, destination, visited);
				}			
			}
			
		}
		
		return currPos;
	}
	
	public boolean contains(List<Coord> coord, Coord toCheck) {
		for (Coord coo: coord) {
			if ((coo.getX() == toCheck.getX())&&(coo.getY()==toCheck.getY())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPassable(Coord coord) {
		ArrayList<Entity> eList = dungeon.entitiesOnXY(coord.getX(), coord.getY());
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
		return true;
	}
	
 public Player getClosestPlayer() {
	 int distanceA = -1;
	 int distanceB = -1;
	 if(dungeon.getPlayerA() != null) {
		int xA = dungeon.getPlayerA().getX();
	 	int yA = dungeon.getPlayerA().getY();
	 	distanceA = Math.abs(xA - getX()) + Math.abs(yA - getY());
	 }
	 
	 if(dungeon.getPlayerB() != null) {
		 int xB = dungeon.getPlayerB().getX();
	 	int yB = dungeon.getPlayerB().getY();
	 	distanceB = Math.abs(xB - getX()) + Math.abs(yB - getY());
	 }
	 
	 if((distanceA == -1) && (distanceB == -1)){
		return null; 
	 } else if ((distanceA == -1) && (distanceB != -1)) {
		 return dungeon.getPlayerB();
	 } else if ((distanceA != -1) && (distanceB == -1)) {
		 return dungeon.getPlayerA();
	 } else if(distanceA > distanceB) {
		 return dungeon.getPlayerB();
	 } else {
		 return dungeon.getPlayerA();
	 }
 }
}
