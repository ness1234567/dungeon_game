package unsw.dungeon;

public class unarmedAttack implements AttackBehavior{
	
	private Dungeon dungeon;
	private Player player;

	public unarmedAttack(Dungeon dungeon, Player player) {
		this.dungeon = dungeon;
		this.player = player;
	}
	
	@Override
	public void attackLeft() {
		return;
	}

	@Override
	public void attackRight() {
		return;
	}

	@Override
	public void attackUp() {
		return;
	}

	@Override
	public void attackDown() {
		return;		
	}
}
