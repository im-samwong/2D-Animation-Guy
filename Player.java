import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import java.util.Timer;


public class Player extends Rectangle{
	
	int xVelocity = 0;
	int yVelocity = 0;
	int speed = 0;
	int n = 12;
	boolean runningR = false;
	boolean runningL = false;
	Image boom;
	BufferedImage bboom;
	BufferedImage sprite;
	Timer timer = new Timer();
	ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	
	
	
	Player(int x, int y, int width, int height) {
		super(x,y,width,height);
	
		
	}
	
	
	
	public void move() {
		x += xVelocity;
		y += yVelocity;
		
	}
	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }
		BufferedImage buffered = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = buffered.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();
	    return buffered;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		boom = (new ImageIcon(getClass().getClassLoader().getResource("imag1.png")).getImage());
		bboom = toBufferedImage(boom);
		
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 6; x++)
			{
			sprites.add(bboom.getSubimage(1+(82*x),1+(125*y), 85, 125));	
			}
		}
		
			
		g2D.drawImage(sprites.get(n), x, y, null);
	}

	
	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode()==37) 
		{
			setXDirection(-speed);
			runningL = true;
		}
		
		if(e.getKeyCode()==39) 
		{
			setXDirection(speed);
			runningR = true;
			
		}
		
	}
	
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}



	public void keyReleased(KeyEvent e) {
		
		
		if(e.getKeyCode()==37) 
		{
			speed = 0;
			setXDirection(speed);
			runningL = false;
		}
		if(e.getKeyCode()==39) 
		{
			speed = 0;
			setXDirection(speed);
			runningR = false;
		}
		
	}




}
