import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame {
	
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
		
		frame.add(canvas);
		frame.pack();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}
}