package entities;

import game.InputHandler;
import gfx.Colors;
import gfx.Screen;
import level.Level;

public class Player extends Mob {
	
	private InputHandler input;
								// 555, -1, 111, 521, 543
	private int color = Colors.get(000, 222, 333, 555, 444);
	private int scale = 1;

	public Player(Level level, int x, int y, InputHandler input) {
		super(level, "A dude", x, y, 1);
		this.input = input;
	}

	@Override
	public boolean hasCollided(int xa, int ya) {
		return false;
	}

	@Override
	public void tick() {
		int xa = 0;
		int ya = 0;
		
		if(input.up.isPressed()) { ya--;  }//System.out.println("y:" + y + " x: "+ x); }
    	if(input.down.isPressed()) { ya++; }//System.out.println("y:" + y + " x: "+ x); }
    	if(input.left.isPressed()) { xa--; }//System.out.println("y:" + y + " x: "+ x); }
    	if(input.right.isPressed()) { xa++; }//System.out.println("y:" + y + " x: "+ x); }
    	
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
