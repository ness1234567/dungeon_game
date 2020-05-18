package unsw.dungeon;

public class Treasure extends Entity{

	public Treasure(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
	}

	@Override
	public boolean interact(Player player) {
		dungeon.removeEntity(this);
		System.out.println("Picked up Treasure!");
		if(dungeon.isComplete(false)) {
			System.out.println("You have picked up all treasures! Level Complete!");
		} else {
			int counter = 0;
			for(Entity entity:dungeon.getEntities()) {
				if(entity instanceof Treasure) {
					counter++;
				}
			}
			System.out.println(counter + " treasures remaining!");
		}
		return true;
	}
	
}