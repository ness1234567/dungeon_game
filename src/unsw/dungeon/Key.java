package unsw.dungeon;

public class Key extends Entity{

	private int id;
	
	public Key(int x, int y, Dungeon dungeon, int id) {
		super(x, y, dungeon);
		this.id = id;
	}

	@Override
	public boolean interact(Player player) {
		//if player already has key, don't pick up key
		if(player.hasKey())
			return true;
		player.addKey(this);
		dungeon.removeEntity(this);
		return true;
	}
	
	public void drop(Player player) {
		
		x().set(player.getX());
		y().set(player.getY());
		dungeon.addEntity(this);
	}

	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
