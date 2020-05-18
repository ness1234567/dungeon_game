package unsw.dungeon;

public class PistolAttack implements AttackBehavior{
	
	private Dungeon dungeon;
	private Player player;

	public PistolAttack(Dungeon dungeon, Player player) {
		this.dungeon = dungeon;
		this.player = player;
	}
	
	@Override
	public void attackLeft() {
		System.out.println("shooting left!");
		for(Entity e : dungeon.getDeadEntities()) {
			if(e instanceof Bullet) {
				System.out.println("Found dead bullet!");
				Bullet b = (Bullet)e;
				b.setDirection(2);
				b.shootBullet(player);
				break;
			}
		}
	}

	@Override
	public void attackRight() {
		for(Entity e : dungeon.getDeadEntities()) {
			if(e instanceof Bullet) {
				Bullet b = (Bullet)e;
				b.setDirection(0);
				b.shootBullet(player);
				break;
			}
		}
	}

	@Override
	public void attackUp() {
		for(Entity e : dungeon.getDeadEntities()) {
			if(e instanceof Bullet) {
				Bullet b = (Bullet)e;
				b.setDirection(3);
				b.shootBullet(player);
				break;
			}
		}
	}

	@Override
	public void attackDown() {
		for(Entity e : dungeon.getDeadEntities()) {
			if(e instanceof Bullet) {
				Bullet b = (Bullet)e;
				b.setDirection(1);
				b.shootBullet(player);
				break;
			}
		}
	}

}
