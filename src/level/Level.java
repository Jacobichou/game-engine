package level;

import entities.Entity;
import gfx.Screen;

import java.util.ArrayList;
import java.util.List;

public class Level {
	private byte[] tiles;
	public int width;
	public int height;
	public List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height) {
		tiles = new byte[width*height];
		this.width = width;
		this.height = height;
		this.generateLevel();
	}

	private void generateLevel() {
		for(int y = 0; y < height; y++){
    		for(int x = 0; x < width; x++){
    			//if(x*y%7 < 5){
    				tiles[x+y*width] = Tile.GRASS.getId();//gets coordinate of map
    			//} //else {
//    				tiles[x+y*width] = Tile.LINE.getId();//gets coordinate of map
//    			}
    			if(x+2*y < 256 && x % 8 == 0) {
    				tiles[x+y*width] = Tile.LINE.getId();//gets coordinate of map
    			}
    		}
    	}
	}
	
	public void tick(){
		for(Entity e : entities){
			e.tick();
		}
	}
	
	public void renderTiles(Screen screen, int xOffset, int yOffset){
		if(xOffset < 0){ xOffset = 0; }
		if(xOffset > ((width << 3) - screen.width)){ xOffset = ((width << 3) - screen.width); }
		if(yOffset < 0){ yOffset = 0; }
		if(yOffset > ((height << 3) - screen.height)){ yOffset = ((height << 3) - screen.height); }
		
		screen.setOffset(xOffset, yOffset); //this is essentially the camera
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getTile(x,y).render(screen, this, x << 3, y << 3);
			}
		}
	}
	
	public void renderEntities(Screen screen){
		for(Entity e : entities){
			e.render(screen);
		}
	}
	
	public Tile getTile(int x, int y){
		if(x< 0 || x>width || y < 0 || y > height) { return Tile.VOID; }
		return Tile.tiles[tiles[x+y*width]];
	}
	
	public void addEntity(Entity entity){
		this.entities.add(entity);
	}
}
