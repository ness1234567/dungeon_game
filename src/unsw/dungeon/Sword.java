package unsw.dungeon;

public class Sword extends Entity{

	public Sword(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
	}

	@Override
	public boolean interact(Player player) {
		//grant 5 sword uses to user upon pickup
		player.setWeaponUses(5);
		player.setSwordAttack();
		dungeon.removeEntity(this);
		System.out.println("Obtained Sword of Destiny!");
		return true;
	}

}
