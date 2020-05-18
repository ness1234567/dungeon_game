package unsw.dungeon;

public class SwordAttack implements AttackBehavior{
	
	private Dungeon dungeon;
	private Player player;

	public SwordAttack(Dungeon dungeon, Player player) {
		this.dungeon = dungeon;
		this.player = player;
	}
	
	@Override
	public void attackLeft() {
		dungeon.killEnemies(player.getX()-1, player.getY());
	}

	@Override
	public void attackRight() {
		dungeon.killEnemies(player.getX()+1, player.getY());
	}

	@Override
	public void attackUp() {
		dungeon.killEnemies(player.getX(), player.getY()-1);
	}

	@Override
	public void attackDown() {
		dungeon.killEnemies(player.getX(), player.getY()+1);		
	}

}
