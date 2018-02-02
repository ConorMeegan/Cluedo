import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameMechanics {
	
	Frame frame;
	BufferStrategy buffer;
	Graphics g;
	
	int width, height;
	
	BufferedImage background;
	BufferedImage img1;
	BufferedImage img2;
	BufferedImage img3;
	BufferedImage img4;
	BufferedImage img5;
	BufferedImage img6;
	
	locations loc;
	
	Dimensions dimensions = new Dimensions();
	PlayerInput playerInput = new PlayerInput();
	
	public GameMechanics(int width,int height) {
		this.width = width;
		this.height = height;
		frame = new Frame(width,height);
		try {
			background = ImageIO.read(new File("Assets/Images/NEWmap.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		playerInput.createAndShowGUI();
		Image();
		//Draw();
		loop();
	}
	public void loop() {
		while(true) {
			start();
		}
	}
	
	public void start() {
		buffer = frame.getCanvas().getBufferStrategy();
		if(buffer == null) {
			frame.getCanvas().createBufferStrategy(2);
			return;
		}
		g = buffer.getDrawGraphics();
		
		g.drawImage(background, 0, 0, null);
		g.drawImage(img1, 16*24, 1*24, null);
		g.drawImage(img2, 11*24, 1*24, null);
		g.drawImage(img3,2*24, 18*24, null);
		g.drawImage(img4, 25*24, 7*24, null);
		g.drawImage(img5, 25*24, 20*24, null);
		g.drawImage(img6, 9*24, 25*24, null);
		
		Draw();
		
		buffer.show();
		g.dispose();
	}
	
	public void Draw() {
		for(int i=0;i<28;i++) {
			for(int j=0;j<28;j++) {
				System.out.printf("%d" ,dimensions.getVal(i, j));
			}
			
			System.out.println();
		}
	}
	
	public void Image() {
		
		try {
			img1 = ImageIO.read(new File("Assets/Images/catLadyToken2.png"));
			img2 = ImageIO.read(new File("Assets/Images/fatTonyToken2.png"));
			img3 = ImageIO.read(new File("Assets/Images/hanzToken2.png"));
			img4 = ImageIO.read(new File("Assets/Images/HomerToken2.png"));
			img5 = ImageIO.read(new File("Assets/Images/maggieToken2.png"));
			img6 = ImageIO.read(new File("Assets/Images/moeToken2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
