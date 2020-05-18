package unsw.dungeon;

public class Potion extends Entity{

	public Potion(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
	}

	@Override
	public boolean interact(Player player) {
		dungeon.removeEntity(this);
		player.setInvincible();
		return true;
	}
	
}
