package unsw.dungeon;

import java.util.ArrayList;

public class Pistol extends Entity{

	public Pistol(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
	}

	@Override
	public boolean interact(Player player) {
		//grant 5 sword uses to user upon pickup
		player.setWeaponUses(3);
		player.setPistolAttack();
		dungeon.removeEntity(this);
		System.out.println("Obtained Pistol!");
		return true;
	}
	
	

}
