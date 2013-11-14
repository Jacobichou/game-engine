package entities;

import gfx.Screen;
import gfx.SpriteSheet;
import level.Level;

public abstract class Mascot extends Mob {
	
	/*
	 * Inherits these from Entity<-Mob:
	 * int x
	 * int y
	 * protected String name;
	 * protected int speed;
	 * protected int numSteps = 0;
	 * protected boolean isMoving;
	 * protected int movingDir = 1;
	 * protected int scale = 1;
	 */
	protected String school;
	protected SpriteSheet spriteSheet;  	//each mascot has their own individual spritesheet containing all sprites needed to animate them
	protected boolean hasModifier; 			//does this mascot modify the game?
 	protected int modifierType;    			//corresponds to a number 0 - 3; each number modifies game play in a unique way
 	protected int pointWorth;				//how much is this mascot worth

	public Mascot(Level level, String name, String school, int x, int y, int speed) {
		super(level, name, x, y, speed);
		this.school = school;
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
