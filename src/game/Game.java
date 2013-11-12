package game;

import entities.Player;
import gfx.Colors;
import gfx.Font;
import gfx.Screen;
import gfx.SpriteSheet;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import level.Level;

/**
 * PROJECT: Basic2
 * = = = = = = = = = = = =
 * User: jacob s pagano
 * Date: 11/5/13
 * Time: 8:48 PM
 * Package: game
 */
public class Game extends Canvas implements Runnable{

    public static final long serialVersionUID = 1L;

    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH/12*9;
    public static final int SCALE = 6;

    public static final String NAME = "Sweet Game";

    private JFrame frame;

    public boolean running;
    public int tickCount;
    
    private BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
    private int[] colors = new int[6*6*6]; //rgb
    
    private Screen screen;
    public InputHandler input;
    public Level level;
    public Player player;

    public Game() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void init(){
    	int index = 0;
    	for(int r = 0; r < 6; r++){ //looping through reds, greens, and blues. Hence, the int naming
    		for(int g = 0; g < 6; g++){
    			for(int b = 0; b < 6; b++){
    				//this will give us the shades of each color: 0 - 5; six shades total for each
    				int rr = (r*255/5); //dividing by 255 bcz 256 is used for an invisible color
    				int gg = (g*255/5);
    				int bb = (b*255/5);
    				
    				colors[index++] = rr << 16 | gg << 8 | bb; //2^8 for each 
    			}
    		}
    	}
    	
    	//initialize objects 
    	screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));
    	input = new InputHandler(this);
    	level = new Level(64,64);
    	player = new Player(level, 0, 0, input);
    	level.addEntity(player);
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }

    @Override
    public void run() {
    	long lastTime = System.nanoTime();
    	double nsPerTick = 1000000000.0/60.0;
    	
    	int ticks = 0;
    	int frames = 0;
    	
    	long lastTimer = System.currentTimeMillis();
    	double delta = 0;
    	
    	init();
    	
        while(running){
        	long now = System.nanoTime();
        	delta += (now - lastTime) / nsPerTick;
        	lastTime = now;
        	
        	boolean shouldRender = true;
        	
        	while(delta >= 1){
        		ticks++;
        		tick();
        		delta -= 1;
        		shouldRender = true;
        	}
        	
        	try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        	if(shouldRender){
        		frames++;
        		render();
        	}
            
            if(System.currentTimeMillis() - lastTimer >= 1000){
            	lastTimer += 1000;
            	System.out.println("frames: " + frames +"," + " ticks: " + ticks);
            	frames = 0;
            	ticks = 0;
            }
        }
    }
    
//    private int x = 0, y = 0;
    
    public void tick() { //game updates
    	tickCount++;
//    	screen.xOffset++;
//    	screen.yOffset++;
    	
//    	if(input.up.isPressed()) { y--;  }//System.out.println("y:" + y + " x: "+ x); }
//    	if(input.down.isPressed()) { y++; }//System.out.println("y:" + y + " x: "+ x); }
//    	if(input.left.isPressed()) { x--; }//System.out.println("y:" + y + " x: "+ x); }
//    	if(input.right.isPressed()) { x++; }//System.out.println("y:" + y + " x: "+ x); }
    	
    	//level tick to make Level update if anything is going on in Level.tick()
    	level.tick();
    	
    	System.out.println(colors.toString());
    }
    
    public void render() {
    	BufferStrategy bs = getBufferStrategy();
    	if(bs == null){
    		createBufferStrategy(3);
    		return;
    	}
    	
    	int xOffset = player.x - (screen.width/2);				//division by 2 in order to get middle of x axis
    	int yOffset = player.y - (screen.height/2);			//division by 2 in order to get middle of y axis
    	level.renderTiles(screen, xOffset, yOffset);
    	
//    	screen.render(pixels, 0, WIDTH);
//    	for(int y = 0; y < 32; y++){
//    		for(int x = 0; x < 32; x++){
//    			boolean flipX = x%2 == 1; //inverts x
//    			boolean flipY = y%2 == 1; //inverts y
//        		screen.render(x<<3, y<<3, 0, Colors.get(555, 505, 055, 550), flipX, flipY);
//        	}
//    	}
    	
//    	for(int x = 0; x < level.width; x++){
//    		int color = Colors.get(-1, -1, -1, 555);
//    		if(x%10 == 0 && x != 0) { color = Colors.get(-1, -1, -1, 500); }
//    		Font.render((x%10) +"", screen, 0+(x*8), 0, color);
//    	}
    	
    	//draw the font
//    	String msg = "This is our game!";
//    	Font.render(msg, screen, screen.xOffset + screen.width/2 - ((msg.length()*8)/2), 20, Colors.get(-1,-1,-1,000));
    	
    	//render entities
    	level.renderEntities(screen);
    	
    	//copy pixel data from the screen into the game
    	for(int y = 0; y < screen.height; y++){
    		for(int x = 0; x < screen.width; x++){
    			int colorCode = screen.pixels[x+y*screen.width];
    			
    			if(colorCode < 255) { pixels[x+y*WIDTH] = colors[colorCode]; }
    		}
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	
    	g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
//    	g.setColor(Color.black);
//    	g.fillRect(0, 0, getWidth(), getHeight());
    	
    	g.dispose();
    	bs.show();
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
