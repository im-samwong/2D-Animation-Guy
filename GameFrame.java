import java.awt.Color;

import javax.swing.*;

public class GameFrame extends JFrame{
	
	GamePanel panel;
	
	
	GameFrame() {
		
		panel = new GamePanel();
		this.add(panel);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(true);
		
		
	}

}
