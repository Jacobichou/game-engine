package level;

import gfx.Colors;
import gfx.Screen;

public abstract class Tile {

	public static Tile[] tiles = new Tile[256];
	
	//BaseTile(byteId, x-coord, y-coord, Colors.get(---,---,---,---)) 
	public static final Tile VOID = new BaseTile(0, 0, 0, Colors.get(000, -1, -1, -1, -1));
	public static final Tile STONE = new BaseTile(1, 1, 0, Colors.get(-1, 333, -1, -1, 123));
	public static final Tile GRASS = new BaseTile(2, 2, 0, Colors.get(-1, 131, 141,555, -1));
	public static final Tile LINE = new BaseTile(3, 3, 0, Colors.get(-1, 131, 141, 555, 222));
																	//-1, 131, 141, 555, 222
	
	
	protected byte id;
	protected boolean solid; //for collision detection
	protected boolean emitter; //for light
	
	public Tile(int id, boolean isSolid, boolean isEmitter){
		this.id = (byte) id;
		if(tiles[id] != null) { throw new RuntimeException("Duplicate tile id: " + id); }
		this.solid = isSolid;
		this.emitter = isEmitter;
		tiles[id] = this;
	}
	
	public byte getId(){ return id; }
	public boolean isSolid() { return solid; }
	public boolean isEmitter() { return emitter; }

	public abstract void render(Screen screen, Level level, int x, int y);
}
