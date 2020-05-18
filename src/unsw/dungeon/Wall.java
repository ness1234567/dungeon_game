package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

	@Override
	public boolean interact(Player player) {
		return false;
	}

}
