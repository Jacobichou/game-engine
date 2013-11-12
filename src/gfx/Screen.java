package gfx;

public class Screen {
	
	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
	
//	public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
//	public int[] colors = new int[MAP_WIDTH * MAP_WIDTH * 4];
	
	public static final byte BIT_MIRROR_X = 0x01;
	public static final byte BIT_MIRROR_Y = 0x02;
	
	public int[] pixels;

	public int xOffset = 0;
	public int yOffset = 0;
	
	public int width;
	public int height;
	
	public SpriteSheet sheet;
	
	
	public Screen(int width, int height, SpriteSheet sheet){
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		
		pixels = new int[width*height];
		
//		for(int i = 0; i < MAP_WIDTH * MAP_WIDTH; i++){
//			colors[i*4+0] = 0xff00ff;
//			colors[i*4+1] = 0x00ffff;
//			colors[i*4+2] = 0xffff00;
//			colors[i*4+3] = 0xffffff;
//		}
	}
	
	/**
	 * used for the font rendering
	 * @param xPos
	 * @param yPos
	 * @param tile
	 * @param color
	 */
//	public void render(int xPos, int yPos, int tile, int color){
//		render(xPos, yPos, tile, color, 0x00);
//	}
	
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param tile - tile index; actual tile from the sprite sheet
	 * @param color - long integer from the color class
	 */
	public void render(int xPos, int yPos, int tile, int color, int mirrorDir, int scale){
		xPos -= xOffset;
		yPos -= yOffset;
		
		boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0;
		boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;
		
		int scaleMap = scale - 1;
		int xTile = tile % 32;
		int yTile = tile / 32;
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;
		
		for(int y = 0; y < 8; y++){
			int ySheet = y;
			if(mirrorY) { ySheet = 7 - y; }
			
			int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 3) /2);
			
			
			for(int x = 0; x < 8; x++){
				int xSheet = x;
				if(mirrorX) { xSheet = 7 - x; }
				
				int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3) /2);
				int col = (color >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255;
				
				if(col < 255) { 
					for(int yScale = 0; yScale < scale; yScale++){
						if(yPixel + yScale < 0 || yPixel + yScale >= height) { continue; }
						
						for(int xScale = 0; xScale < scale; xScale++){
							if(xPixel + xScale < 0 || xPixel + xScale >= width) { continue; }
							
							pixels[(xPixel + xScale) + (yPixel + yScale) * width] = col; 
						}
					}
				}
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
//	public void render(int[] pixels, int offset, int row){
//		for(int yTile = yOffset >> 3; yTile <= (yOffset + height) >> 3; yTile++){
//			int yMin = yTile * 8 - yOffset;
//			int yMax = yMin + 8;
//			
//			if(yMin < 0) { yMin = 0; }
//			if(yMax > height) { yMax = height; }
//			
//			for(int xTile = xOffset >> 3; xTile <= (xOffset + width) >> 3; xTile++){
//				int xMin = xTile * 8 - xOffset;
//				int xMax = xMin + 8;
//				
//				if(xMin < 0) { xMin = 0; }
//				if(xMax > width) { xMax = width; }
//				
//				int tileIndex = (xTile & (MAP_WIDTH_MASK)) + (yTile & (MAP_WIDTH_MASK)) * MAP_WIDTH;
//				
//				for(int y = yMin; y < yMax; y++){
//					int sheetPixel = ((y+yOffset) & 7) * sheet.width + ((xMin + xOffset) & 7);
//					int tilePixel = offset + xMin + y * row;
//					
//					for(int x = xMin; x < xMax; x++){
//						int color = tileIndex * 4 + sheet.pixels[sheetPixel++];
//						pixels[tilePixel++] = colors[color];
//					}
//				}
//			}
//		}
//	}
}
