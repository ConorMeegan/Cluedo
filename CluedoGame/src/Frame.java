import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	JFrame frame;
	Canvas canvas;
	GameMechanics game;
	Dimensions dim;
	
	public Frame(int width, int height) {
		frame = new JFrame("Cluedo");
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setFocusable(true);
		canvas.addMouseListener(this);
		
		frame.getContentPane().setLayout(new BorderLayout());
		
		//MouseHandling.MouseHandling(frame);
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
	}
	
	public void setPlayerInput(PlayerInput playerInput) {
		frame.add(playerInput, BorderLayout.LINE_END);
		frame.pack();
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Entered frame at X: " + x + " - Y: " + y);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Exited frame at X: " + x + " - Y: " + y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Pressed at X: " + x + " - Y: " + y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Released at X: " + x + " - Y: " + y);
    } 
    
    public void checkPos(double x, double y) {
    	if(game.getGameState() == 3) {
    		x = Math.floor(x);
    		y = Math.floor(y);
    		if(x >= 15 && x <= 160 && y >= 230 && y <= 430) {
    			
    		}
    	}else {
    		if(x > 180) {
    			x = Math.floor(((x - 180)/24));
        		y = Math.floor((y/24));
        	}
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