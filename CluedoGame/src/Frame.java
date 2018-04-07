import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame {
	
	private static final long serialVersionUID = 1L;
	JFrame frame;
	Canvas canvas;
	GameMechanics game;
	Dimensions dim;
	int change = 0;
	public Frame(int width, int height) {
		frame = new JFrame("Cluedo");
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setFocusable(true);
		
		
		frame.getContentPane().setLayout(new BorderLayout());
		
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
	}
	
	public void setPlayerInput(PlayerInput playerInput) {
		frame.add(playerInput, BorderLayout.LINE_END);
		frame.pack();
	}
    
    public void checkPos(double x, double y) {
    	x = Math.floor(x);
		y = Math.floor(y);
		if(x >= 15 && x <= 135 && y >= 50 && y <= 530) {
			game.setPassword(1);
		}
    	
    }
	public void setDimensionsAndGame(Dimensions dim, GameMechanics game) {
		this.dim = dim;
		this.game = game;
	}
   
	
	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}
}