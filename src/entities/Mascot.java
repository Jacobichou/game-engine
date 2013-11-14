package entities;

import gfx.Screen;
import gfx.SpriteSheet;
import level.Level;

public abstract class Mascot extends Mob {
	
	protected String school;
	protected SpriteSheet sprite;  	//each mascot has their own individual spritesheet containing all sprites needed to animate them
	protected boolean hasModifier; 	//does this mascot modify the game?
 	protected int modifierType;    	//corresponds to a number 0 - 3; each number modifies game play in a unique way
 	protected int pointWorth;		//how much is this mascot worth

	public Mascot(Level level, String name, int x, int y, int speed) {
		super(level, name, x, y, speed);
	}

	@Override
	public boolean hasCollided(int xa, int ya) {
		return false;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Screen screen) {
		
	}

}
