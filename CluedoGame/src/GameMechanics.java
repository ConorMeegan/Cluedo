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
	
	int num = 0;
	
	Frame frame;
	BufferStrategy buffer;
	Graphics g;
	
	int width, height;
	
	GameObject PlayerOne;
	GameObject PlayerTwo;
	GameObject PlayerThree;
	GameObject PlayerFour;
	GameObject PlayerFive;
	GameObject PlayerSix;
	
	BufferedImage background;
	BufferedImage img1;
	BufferedImage img2;
	BufferedImage img3;
	BufferedImage img4;
	BufferedImage img5;
	BufferedImage img6;
	BufferedImage cardImage1;
	BufferedImage cardImage2;
	BufferedImage cardImage3;
	BufferedImage cardImage4;
	BufferedImage cardImage5;
	BufferedImage cardImage6;
	
	Dimensions dimensions = new Dimensions();
	PlayerInput playerInput = new PlayerInput(this);
	Moving moving = new Moving();
	
	public GameMechanics(int width,int height) {
		this.width = width;
		this.height = height;
		frame = new Frame(width,height);
		try {
			background = ImageIO.read(new File("Assets/Images/NEWmap4.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		playerInput.createAndShowGUI();
		Initialise();
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
		g.setColor(new Color(97,62,7));
		g.fillRect(0, 0, width, height);
		g.drawImage(background,180, 0, null);
		Draw();
		
		buffer.show();
		g.dispose();
	}
	
	public void Draw() {
		
		g.drawImage(img6, (PlayerSix.getx()*24) +180, PlayerSix.gety()*24, null);
		g.drawImage(img1, (PlayerOne.getx()*24)+180, PlayerOne.gety()*24, null);
		g.drawImage(img3, (PlayerThree.getx()*24)+180, PlayerThree.gety()*24, null);
		g.drawImage(img4, (PlayerFour.getx()*24)+180, PlayerFour.gety()*24, null);
		g.drawImage(img2, (PlayerTwo.getx()*24)+180, PlayerTwo.gety()*24, null);
		g.drawImage(img5, (PlayerFive.getx()*24)+180, PlayerFive.gety()*24, null);
		g.drawImage(cardImage6, 15, 15, null);
		g.drawImage(cardImage5, 15, 240, null);
		g.drawImage(cardImage2, 15, 460, null);
	}
	
	public void movement(int num) {
		if(num == 1) {
			PlayerOne.sety(moving.moveUp(PlayerOne.gety()));
			playerInput.setString();
			System.out.println("Up");
		}else if(num == 2) {
			PlayerOne.sety(moving.moveDown(PlayerOne.gety()));
			playerInput.setString();
			System.out.println("Down");
		}else if(num == 3) {
			PlayerOne.setx(moving.moveRight(PlayerOne.getx()));
			playerInput.setString();
			System.out.println("Right");
		}else if(num == 4){
			PlayerOne.setx(moving.moveLeft(PlayerOne.getx()));
			playerInput.setString();
			System.out.println("Left");
		}
		
		
	}
	
	public void Initialise() {
		
		try {
			img1 = ImageIO.read(new File("Assets/Images/catLadyToken2.png"));
			img2 = ImageIO.read(new File("Assets/Images/fatTonyToken2.png"));
			img3 = ImageIO.read(new File("Assets/Images/hanzToken2.png"));
			img4 = ImageIO.read(new File("Assets/Images/HomerToken2.png"));
			img5 = ImageIO.read(new File("Assets/Images/maggieToken2.png"));
			img6 = ImageIO.read(new File("Assets/Images/moeToken2.png"));
			cardImage1 = ImageIO.read(new File("Assets/Images/carzycatLady.png"));
			cardImage2 = ImageIO.read(new File("Assets/Images/fatTonyCardTest.png"));
			cardImage3 = ImageIO.read(new File("Assets/Images/hanzMoleManCard.png"));
			cardImage4 = ImageIO.read(new File("Assets/Images/HomerCard.png"));
			cardImage5 = ImageIO.read(new File("Assets/Images/maggieCardTest.png"));
			cardImage6 = ImageIO.read(new File("Assets/Images/moeCardTest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		PlayerOne = new GameObject(15,false,11,1);
		PlayerTwo = new GameObject(16,false,25,20);
		PlayerThree = new GameObject(17,false,2,18);
		PlayerFour = new GameObject(18,false,25,7);
		PlayerFive = new GameObject(19,false,9,25);
		PlayerSix = new GameObject(20,false,16,1);
	}
	
	public void setInput(String string) {
		if(string.equals("u")) {
			this.movement(1);
		}else if(string.equals("d")){
			this.movement(2);
		}else if(string.equals("l")){
			this.movement(3);
		}else if(string.equals("r")){
			this.movement(4);
		}
	}
}