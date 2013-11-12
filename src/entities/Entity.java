package entities;

import gfx.Screen;
import level.Level;

public abstract class Entity {

	public int x;
	public int y;
	
	public Level level;
	
	public Entity(Level level){
		init(level);
	}
	
	public final void init(Level leve){
		this.level = level;
	}
	
	public abstract void tick();
	public abstract void render(Screen screen);
}
