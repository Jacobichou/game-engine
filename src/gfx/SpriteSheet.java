package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public String path;
	public int width;
	public int height;
	
	public int[] pixels;
	
	public SpriteSheet(String path) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(img == null){ return; }
		
		this.path = path;
		this.width = img.getWidth();
		this.height = img.getHeight();
		
		pixels = img.getRGB(0, 0, width, height, null, 0, width);
		
		for(int i = 0; i < pixels.length; i++){ //0xffRRGGBB
			pixels[i] = (pixels[i] & 0xff)/80; //removing the alpha channel(ff), then dividing by 64 for four colors
		}
		
		for(int i = 0; i < 8; i++){
			System.out.println(pixels[i]);
		}
	}
}
