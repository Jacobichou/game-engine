package entities;

import gfx.Colors;
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
 	
 	private int color = Colors.get(000, 555, 333, 555, 444);
	private int scale = 1;

	public Mascot(Level level, int x, int y) {
		super(level, x, y, 1);
//		this.school = school;
	}

	@Override
	public boolean hasCollided(int xa, int ya) {
		return false;
	}

	@Override
	public void tick() {
		int xa = 0;
		int ya = 0;
		
		//move logic for mascot goes here
		//-------------------------------
		
		if(xa != 0 || ya != 0) {
    		move(xa, ya);
    		isMoving = true;
    	} else {
    		isMoving = false;
    	}
	}

	@Override
	public void render(Screen screen) {
		int xTile = 2;  //bcz the guy begins at column 0 on sprite sheet
		int yTile = 28; //bcz the guy begins at row 28 on sprite sheet
		
		int modifier = 8 * scale;
		int xOffset = x - modifier /2;
		int yOffset = y - modifier/2 - 4; //subtracting 4 to make center of player at about his waist
		
		//upper body
		screen.render(xOffset, yOffset, xTile + yTile * 32, color, 0x00,scale);
		screen.render(xOffset + modifier, yOffset, (xTile +1) + yTile * 32, color, 0x00, scale);
		
		//lower body
		screen.render(xOffset, yOffset + modifier, xTile + (yTile + 1) * 32, color, 0x00, scale);
		screen.render(xOffset + modifier, yOffset + modifier, (xTile  + 1) + (yTile + 1) * 32, color, 0x00, scale);
	}

}
