import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Frame extends JFrame implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	Canvas canvas;
	
	public Frame(int width, int height) {
		frame = new JFrame("Cluedo");
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setFocusable(false);
		canvas.addMouseListener(this);
		
		//MouseHandling.MouseHandling(frame);
		frame.add(canvas);
		frame.pack();
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse Clicked at X: " + x + " - Y: " + y);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse Entered frame at X: " + x + " - Y: " + y);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse Exited frame at X: " + x + " - Y: " + y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse Pressed at X: " + x + " - Y: " + y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse Released at X: " + x + " - Y: " + y);
    } 
   
	
	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}
}