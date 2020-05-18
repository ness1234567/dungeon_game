package unsw.dungeon;

public class Exit extends Entity {
	
	public Exit(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
	}

	@Override
	public boolean interact(Player player) {
		if (dungeon.isComplete(true)) {
			System.out.println("Level is complete!");
			return false;
		}
		return true;
	}

}
