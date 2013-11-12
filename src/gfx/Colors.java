package gfx;

public class Colors {

	/**
	 * This is used to get a long single number which is parsed for color determination.
	 * There are 2^8 bits for each different color.
	 * @param color1
	 * @param color2
	 * @param color3
	 * @param color4
	 * @param color5
	 * @return
	 */
	public static int get(int color1, int color2, int color3, int color4, int color5) {
		return (get(color5) << 32) + (get(color4) << 24) + (get(color3) << 16) + (get(color2) << 8) + get(color1);
	}
	
	private static int get(int color){
		if(color < 0) { return 255; }
		
		int r = color/100 % 10;
		int g = color/10 % 10;
		int b = color % 10;
		return r*36+g*6+b; //r*(6*6) + g*(6*1) + b(6*0); bcz there are size parts of data
	}
	
	/*
	 * These should be the colors on the sprite sheet:
	 * (000000, 5555555, aaaaaa, cccccc, ffffff)
	 * (000, 222, 333, 555, 444)
	 */
	static{
		Colors.get(000, 555 ,543,542,123);
	}
}
