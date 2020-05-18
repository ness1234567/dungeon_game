package unsw.dungeon;

public class Switch extends Entity{
	
	private boolean activated;

	public Switch(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.activated = false;
	}

	public boolean isActivated() {
		return activated;
	}
	
	public void setActivated(boolean status) {
		this.activated = status;
		if(isActivated())
			System.out.println("Activated Switch!!");
		else
			System.out.println("Deactivated Switch!!");
	}

	@Override
	public boolean interact(Player player) {
		return true;
	}
	
	
}
