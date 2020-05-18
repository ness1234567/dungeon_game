package unsw.dungeon;

public class UnLitBomb extends Entity{
	
	public UnLitBomb(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
	}

	@Override
	public boolean interact(Player player) {
		//grant 5 sword uses to user upon pickup
		int currBombs = dungeon.getPlayerA().getNumBomb();
		player.setNumBomb(currBombs+1);
		System.out.println("Obtained a Bomb! Currently have " + player.getNumBomb());
		dungeon.removeEntity(this);
		return true;
	}
}
