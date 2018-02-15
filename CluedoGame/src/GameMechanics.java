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
	int[] gameState = {1,2,3};
	int width, height;
	
	GameObject PlayerOne;
	GameObject PlayerTwo;
	GameObject PlayerThree;
	GameObject PlayerFour;
	GameObject PlayerFive;
	GameObject PlayerSix;
	GameObject weaponOne;
	GameObject weaponTwo;
	GameObject weaponThree;
	GameObject weaponFour;
	GameObject weaponFive;
	GameObject weaponSix;
	
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
	
	BufferedImage weaponImage1;
	BufferedImage weaponImage2;
	BufferedImage weaponImage3;
	BufferedImage weaponImage4;
	BufferedImage weaponImage5;
	BufferedImage weaponImage6;
	
	BufferedImage accusations;
	BufferedImage startArea;
	BufferedImage playButton;
	BufferedImage HTPButton;
	
	Dimensions dimensions = new Dimensions();
	PlayerInput playerInput = new PlayerInput(this);
	Moving moving = new Moving();
	
	//input key variable
	private KeyManager keyManager;
	
	//constructor to get everything ready
	public GameMechanics(int width,int height) {
		this.width = width;
		this.height = height;
		frame = new Frame(width,height);
		keyManager = new KeyManager(this);  //for arrow key movement
		frame.getFrame().addKeyListener(keyManager);
		
		try {
			background = ImageIO.read(getClass().getResource("NEWmap4.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		playerInput.createAndShowGUI();
		Initialise();
		loop();
	}
	//loop to continue the process of drawing the sprites and maps when updates happen 
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
		
		//futurecode for future game states
		if(gameState[0] == 0) {
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			g.drawImage(startArea, (width/2)-250, (height/2)-300, null);
			g.drawImage(playButton, 450, 100, null);
			g.drawImage(HTPButton, 450, 160, null);
		}else if(gameState[2] == 2) {
			//System.out.println("here");
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			g.drawImage(accusations,45, 0, null);
			
			g.drawImage(weaponImage1, 15, 230, null);
			g.drawImage(weaponImage2, 175, 230, null);
			g.drawImage(weaponImage3, 335, 230, null);
			g.drawImage(weaponImage4, 15, 440, null);
			g.drawImage(weaponImage5, 175, 440, null);
			g.drawImage(weaponImage6, 335, 440, null);
			
			g.drawImage(cardImage1, 500, 15, null);
			g.drawImage(cardImage2, 500, 230, null);
			g.drawImage(cardImage3, 500, 445, null);
			g.drawImage(cardImage4, 670, 15, null);
			g.drawImage(cardImage5, 670, 230, null);
			g.drawImage(cardImage6, 670, 445, null);
		}
		
		/*
		for(int i=0;i<28;i++) {
			for(int j=0;j<28;j++) {
				if(dimensions.getVal(i, j) == 0) {
					g.setColor(new Color(100,100,100,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 2) {
					g.setColor(new Color(111,222,55,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 3) {
					g.setColor(new Color(99,51,116,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 4) {
					g.setColor(new Color(248,54,56,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 5) {
					g.setColor(new Color(158,54,241,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 6) {
					g.setColor(new Color(120,34,116,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 7) {
					g.setColor(new Color(213,54,56,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 8) {
					g.setColor(new Color(213,22,33,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 9) {
					g.setColor(new Color(100,54,56,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 10) {
					g.setColor(new Color(213,54,56,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 11) {
					g.setColor(new Color(75,45,75,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 12) {
					g.setColor(new Color(150,54,46,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 1) {
					g.setColor(new Color(23,94,56,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 50) {
					g.setColor(new Color(88,55,122));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 60) {
					g.setColor(new Color(55,200,45));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 70) {
					g.setColor(new Color(55,20,245));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 80) {
					g.setColor(new Color(255,0,0));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 90) {
					g.setColor(new Color(0,0,255));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 1000) {
					g.setColor(new Color(255,255,0));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 20) {
					g.setColor(new Color(11,55,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 30) {
					g.setColor(new Color(75,75,75));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 40) {
					g.setColor(new Color(80,55,1));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 55) {
					g.setColor(new Color(0,200,100));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 17) {
					g.setColor(new Color(150,150,150));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 15) {
					g.setColor(new Color(50,50,50));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 50) {
					g.setColor(new Color(75,80,210));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}
			}
		}
		*/
		buffer.show();
		g.dispose();
	}
	
	//handles all drawing of sprites
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
		/*
		g.drawImage(weaponImage6, (weaponSix.getx()*24) +180, weaponSix.gety()*24, null);
		g.drawImage(weaponImage5, (weaponOne.getx()*24) +180, weaponOne.gety()*24, null);
		g.drawImage(weaponImage2, (weaponTwo.getx()*24) +180, weaponTwo.gety()*24, null);
		g.drawImage(weaponImage1, (weaponThree.getx()*24) +180, weaponThree.gety()*24, null);
		g.drawImage(weaponImage3, (weaponFour.getx()*24) +180, weaponFour.gety()*24, null);
		g.drawImage(weaponImage4, (weaponFive.getx()*24) +180, weaponFive.gety()*24, null);
		*/
	}
	
	public void movement(int num) {
		if(num == 1) {
			PlayerOne.sety(moving.moveUp(PlayerOne.gety()));
			playerInput.setString();
		}else if(num == 2) {
			PlayerOne.sety(moving.moveDown(PlayerOne.gety()));
			playerInput.setString();
		}else if(num == 3) {
			PlayerOne.setx(moving.moveRight(PlayerOne.getx()));
			playerInput.setString();
		}else if(num == 4){
			PlayerOne.setx(moving.moveLeft(PlayerOne.getx()));
			playerInput.setString();
		}else if(num == 5) {
			weaponOne.sety(8);
			weaponOne.setx(15);
			playerInput.setString();
			System.out.println("Burns Mansion");
		}else if(num == 6) {
			weaponOne.sety(7);
			weaponOne.setx(5);
			playerInput.setString();
			System.out.println("Comic Book Store");
		}else if(num == 7){
			weaponOne.sety(6);
			weaponOne.setx(23);
			playerInput.setString();
			System.out.println("Kwik-E Mart");
		}else if(num == 8) {
			weaponOne.sety(14);
			weaponOne.setx(9);
			playerInput.setString();
			System.out.println("School");
		}else if(num == 10){
			weaponOne.sety(13);
			weaponOne.setx(22);
			playerInput.setString();
			System.out.println("Flanders House");
		}else if(num == 11) {
			weaponOne.sety(19);
			weaponOne.setx(22);
			playerInput.setString();
			System.out.println("Simpsons House");
		}else if(num == 12) {
			weaponOne.sety(24);
			weaponOne.setx(20);
			playerInput.setString();
			System.out.println("Frying Dutchman");
		}else if(num == 13){
			weaponOne.sety(19);
			weaponOne.setx(12);
			playerInput.setString();
			System.out.println("Krusty Burger");
		}else if(num == 14){
			weaponOne.sety(21);
			weaponOne.setx(6);
			playerInput.setString();
			System.out.println("Moes Tavern");
		}
		
		
	}
	
	//receives all images and sets all object to positions
	public void Initialise() {
		try {
			img1 = ImageIO.read(getClass().getResource("catLadyToken2.png"));
			img2 = ImageIO.read(getClass().getResource("fatTonyToken2.png"));
			img3 = ImageIO.read(getClass().getResource("hanzToken2.png"));
			img4 = ImageIO.read(getClass().getResource("HomerToken2.png"));
			img5 = ImageIO.read(getClass().getResource("maggieToken2.png"));
			img6 = ImageIO.read(getClass().getResource("moeToken2.png"));
			cardImage1 = ImageIO.read(getClass().getResource("carzyCatLady.png"));
			cardImage2 = ImageIO.read(getClass().getResource("fatTonyCardTest.png"));
			cardImage3 = ImageIO.read(getClass().getResource("hanzMoleManCard.png"));
			cardImage4 = ImageIO.read(getClass().getResource("homerCard.png"));
			cardImage5 = ImageIO.read(getClass().getResource("maggieCardTest.png"));
			cardImage6 = ImageIO.read(getClass().getResource("moeCardTest.png"));
			weaponImage1 = ImageIO.read(getClass().getResource("axe.png"));
			weaponImage2 = ImageIO.read(getClass().getResource("PlutoniumRod.png"));
			weaponImage3 = ImageIO.read(getClass().getResource("chainsaw.png"));
			weaponImage4 = ImageIO.read(getClass().getResource("gun.png"));
			weaponImage5 = ImageIO.read(getClass().getResource("knife.png"));
			weaponImage6 = ImageIO.read(getClass().getResource("slingshot.png"));
			accusations = ImageIO.read(getClass().getResource("Accusations3.png"));
			startArea = ImageIO.read(getClass().getResource("startArea.png"));
			playButton = ImageIO.read(getClass().getResource("playButton.png"));
			HTPButton = ImageIO.read(getClass().getResource("howToPlayButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PlayerOne = new GameObject(15,false,11,1);
		PlayerTwo = new GameObject(16,false,25,20);
		PlayerThree = new GameObject(17,false,2,18);
		PlayerFour = new GameObject(18,false,25,7);
		PlayerFive = new GameObject(19,false,9,25);
		PlayerSix = new GameObject(20,false,16,1);
		
		weaponOne = new GameObject(20,false,0,1);
		weaponTwo = new GameObject(25,false,0,2);
		weaponThree = new GameObject(30,false,0,3);
		weaponFour = new GameObject(40,false,0,4);
		weaponFive = new GameObject(45,false,0,5);
		weaponSix = new GameObject(50,false,0,6);
	}
	
	//used to control players and weapons, will be updated in the future to work for different input styles
	public void setInput(String string) {
		if(string.equals("u")) {
			this.movement(1);
		}else if(string.equals("d")){
			this.movement(2);
		}else if(string.equals("l")){
			this.movement(3);
		}else if(string.equals("r")){
			this.movement(4);
		}else if(string.equals("burnsMansion")){
			this.movement(5);
		}else if(string.equals("comicBookStore")){
			this.movement(6);
		}else if(string.equals("kwikEMart")){
			this.movement(7);
		}else if(string.equals("school")){
			this.movement(8);
		}else if(string.equals("flandersHouse")){
			this.movement(10);
		}else if(string.equals("simpsonsHouse")){
			this.movement(11);
		}else if(string.equals("fryingDutchman")){
			this.movement(12);
		}else if(string.equals("krustyBurger")){
			this.movement(13);
		}else if(string.equals("moesTavern")){
			this.movement(14);
		}
	}
	
	public Dimensions getDimensions() {
		return dimensions;
	}
	
	public GameObject getOb() {
		return PlayerOne;
	}
}