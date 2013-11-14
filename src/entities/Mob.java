package entities;

import level.Level;

public abstract class Mob extends Entity {
	/*
	 * Inherits these from Entity:
	 * int x
	 * int y
	 */
	protected String name;			//name of mob
	protected int speed;			//modifies movement speed across the window
	protected int numSteps = 0;		//how many steps have been taken; in case we want to implement something which makes use of this
	protected boolean isMoving;		//if mob is moving or not
	protected int movingDir = 1;	//0 is up; 1 is down; 2 is left; 3 is right
	protected int scale = 1;		//size scale; 1 is normal size

	/**
	 * Constructor for Mob which extends the Entity class.
	 * It accepts the following: 
	 * @param level
	 * @param name
	 * @param x
	 * @param y
	 * @param speed
	 */
	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	/**
	 * This indicates direction. The numbers passed to xa, ya relate to
	 * the numbers used for the movingDir variable.
	 * @param xa
	 * @param ya
	 */
	public void move(int xa, int ya){
		//to correct movement situation of being capable of moving essentially 2 at a time
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			numSteps--; //decremented to compensate for the situation this conditional is correcting(moving in two directions at once)
			return;
		}
		
		numSteps++;
		if(!hasCollided(xa,ya)){
			if(ya < 0){ movingDir = 0; }
			if(ya > 0) { movingDir = 1; }
			if(xa < 0){ movingDir = 2; }
			if(xa > 0) { movingDir = 3; }
			
			x += xa * speed;
			y += ya * speed;
		}
	}
	
	public abstract boolean hasCollided(int xa, int ya);
	
	public String getName() { return name; }

}
