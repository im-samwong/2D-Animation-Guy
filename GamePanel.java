import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;




public class GamePanel extends JPanel implements Runnable{
	Image image;
	Image background3;
	Image background2;
	Image background1;
	Graphics graphics;
	Player player;
	Thread gameThread;
	int t = 0;
	int i = 0;
	int k = 2;
	int frame = 2;
	boolean wentR = false;
	boolean wentL = false;
	boolean taskDone = false;
	boolean halfWay = false;
	Timer timer = new Timer();
	
	
	GamePanel() {
		this.setSize(500, 500);
		this.setPreferredSize(getSize());
		this.setFocusable(true);
		this.setLocation(0, 0);
		this.setBackground(Color.WHITE);
		this.addKeyListener(new AL());
		player = new Player(200,350,50,50);
		gameThread = new Thread(this);
		gameThread.start();
		

		
	}
	
	
	public void check() {
		
		if(player.runningR && k % 2 == 0 )
		{
			k++;
			timer.cancel();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					if(player.runningR)
					{
					if (i == 0)
						player.n = 0;
					else if (i == 1)
							player.n = 1;
						else if (i == 2)
								player.n = 2;
							else if (i == 3)
									player.n = 3;
								else if (i == 4)
										player.n = 4;
									else if (i == 5)
											player.n = 5;
					player.speed = 1;
					i++;
					k++;
					if (player.n == 5)
						i = 0;
					} else {player.n = 12; i = 0; k = 0;}
				}
				
			};
			timer = new Timer();
			timer.schedule(task, 180);
		}
		
		if(player.runningL && k % 2 == 0 )
		{
			k++;
			timer.cancel();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					if(player.runningL)
					{
					if (i == 0)
						player.n = 13;
					else if (i == 1)
							player.n = 6;
						else if (i == 2)
								player.n = 7;
							else if (i == 3)
									player.n = 8;
								else if (i == 4)
										player.n = 9;
									else if (i == 5)
											player.n = 10;
										else if (i == 6)
											player.n = 11;
					player.speed = 1;
					i++;
					k++;
					if (player.n == 11)
						i = 2;
					} else {player.n = 13; i = 0; k = 0;}
				}
				
			};
			timer = new Timer();
			timer.schedule(task, 180);
		}
		
		if (player.x >= 510)
		{
			newPlayerL();
		}
		
		if (player.x <= -40)
		{
			newPlayerR();
		}
		
		if (frame == 1)
		{
			if (player.x <= 0)
				player.x = 0;
		}
		
		if (frame == 3)
		{
			
			if(player.x == 250)
				halfWay = true;
			if(halfWay)
				if (player.x >= (500-85))
					player.x = (500-85);
		}
		
		
		
	}	
	
	public void newPlayerL() {
		player = new Player(-20,350,50,50);
		++frame;
		halfWay = false;
	}
	
	public void newPlayerR() {
		player = new Player(500,350,50,50);
		--frame;
		halfWay = false;
	}
		
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g2D.drawImage(image, 0, 0, this);
	}
	
	public void move() {
		player.move();
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		background1 = new ImageIcon(getClass().getClassLoader().getResource("trail.png")).getImage();
		background2 = new ImageIcon(getClass().getClassLoader().getResource("trail2.png")).getImage();
		background3 = new ImageIcon(getClass().getClassLoader().getResource("trail3.png")).getImage();
		if (frame == 3)
		{
			g2D.drawImage(background3, 0, 0, null);
		} else if (frame == 2)
				g2D.drawImage(background2, 0, 0, null);
				else if (frame == 1)
					g2D.drawImage(background1, 0, 0, null);
		player.draw(g2D);
		Toolkit.getDefaultToolkit().sync();
		
	
	}
	
	public class AL extends KeyAdapter {
			
			public void keyPressed(KeyEvent e) {
				player.keyPressed(e);
			}
			public void keyReleased(KeyEvent e) {
				player.keyReleased(e);
			}
			

	}
			
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				check();
				repaint();
				delta--;
			}
		}
		
	}
}

