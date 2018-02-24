import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameMechanics {
	
	int num = 0;
	int gameStateCurrent = 2;
	
	int current = 0;
	
	Frame frame;
	BufferStrategy buffer;
	Graphics g;
	int[] gameState = {1,2,3};
	int width, height;
	
	Images images = new Images();
	
	GameObject[] Players = new Players[6];
	GameObject[] Cards = new Cards[6];
	GameObject[] Weapons = new weapons[6];

	BufferedImage background;
	
	Dimensions dimensions = new Dimensions();
	PlayerInput playerInput = new PlayerInput(this);
	Moving moving = new Moving();
	CollisonTesting cTest = new CollisonTesting(this);
	
	//input key variable
	private KeyManager keyManager;
	
	//constructor to get everything ready
	public GameMechanics(int width,int height) {
		this.width = width;
		this.height = height;
		frame = new Frame(width,height);
		keyManager = new KeyManager(this,cTest);  //for arrow key movement
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

		
		//futurecode for future game states
		if(gameState[0] == gameStateCurrent) {
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			g.drawImage(images.getImage(1, "screens"), (width/2)-250, (height/2)-300, null);
			g.drawImage(images.getImage(2, "screens"), 450, 100, null);
			g.drawImage(images.getImage(3, "screens"), 450, 160, null);
		}else if(gameState[2] == gameStateCurrent) {
			//System.out.println("here");
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			g.drawImage(images.getImage(0, "screens"),45, 0, null);
			
			g.drawImage(images.getImage(1, "weapons"), 15, 230, null);
			g.drawImage(images.getImage(2, "weapons"), 175, 230, null);
			g.drawImage(images.getImage(3, "weapons"), 335, 230, null);
			g.drawImage(images.getImage(4, "weapons"), 15, 440, null);
			g.drawImage(images.getImage(5, "weapons"), 175, 440, null);
			g.drawImage(images.getImage(6, "weapons"), 335, 440, null);
			
			g.drawImage(images.getImage(1, "cards"), 500, 15, null);
			g.drawImage(images.getImage(2, "cards"), 500, 230, null);
			g.drawImage(images.getImage(3, "cards"), 500, 445, null);
			g.drawImage(images.getImage(4, "cards"), 670, 15, null);
			g.drawImage(images.getImage(5, "cards"), 670, 230, null);
			g.drawImage(images.getImage(6, "cards"), 670, 445, null);
		}else if(gameState[1] == gameStateCurrent) {
			g.setColor(new Color(97,62,7));
			g.fillRect(0, 0, width, height);
			g.drawImage(background,180, 0, null);
			Draw();
		}
		/*
		for(int i=0;i<28;i++) {
			for(int j=0;j<28;j++) {
				if(dimensions.getVal(i, j) == 0) {
					g.setColor(new Color(100,100,100));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 2) {
					g.setColor(new Color(111,222,55));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 3) {
					g.setColor(new Color(99,51,116));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 4) {
					g.setColor(new Color(248,54,56));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 5) {
					g.setColor(new Color(158,54,241));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 6) {
					g.setColor(new Color(120,34,116));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 7) {
					g.setColor(new Color(213,54,56));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 8) {
					g.setColor(new Color(213,22,33));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 9) {
					g.setColor(new Color(100,54,56));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 10) {
					g.setColor(new Color(213,54,56));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 12) {
					g.setColor(new Color(150,54,46));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 1) {
					g.setColor(new Color(23,94,56));
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
				}else if(dimensions.getVal(i, j) == 100) {
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
				}else if(dimensions.getVal(i, j) == 110) {
					g.setColor(new Color(75,0,10));
					g.fillRect((i*24)+180, j*24, 24, 24);
				}else if(dimensions.getVal(i, j) == 11) {
					g.setColor(new Color(225,0,0));
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
		
		for(int i=0;i<6;i++) {
			g.drawImage(Players[i].getImage(), (Players[i].getx()*24) +180, Players[i].gety()*24, null);
		}

		g.drawImage(images.getImage(6, "cards"), 15, 15, null);
		g.drawImage(images.getImage(5, "cards"), 15, 240, null);
		g.drawImage(images.getImage(2, "cards"), 15, 460, null);
	}
	
	public void movement(int num) {
		if(num == 1) {
			Players[current].sety(moving.moveUp(Players[current].gety()));
			//playerInput.setString();
		}else if(num == 2) {
			Players[current].sety(moving.moveDown(Players[current].gety()));
			//playerInput.setString();
		}else if(num == 3) {
			Players[current].setx(moving.moveRight(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 4){
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 20) {
			Players[current].setx(24);
			Players[current].sety(14);
			//playerInput.setString();
		}else if(num == 30) {
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 40){
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 50) {
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 60){
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 70) {
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 80) {
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 90){
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 100){
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}else if(num == 110){
			Players[current].setx(moving.moveLeft(Players[current].getx()));
			//playerInput.setString();
		}
	}
	
	//receives all images and sets all object to positions
	public void Initialise() {
		
		Players[0] = new Players(1,images.getImage(1, "tokens"),11,1);
		Players[1] = new Players(2,images.getImage(2, "tokens"),25,20);
		Players[2] = new Players(3,images.getImage(3, "tokens"),2,18);
		Players[3] = new Players(4,images.getImage(4, "tokens"),25,7);
		Players[4] = new Players(5,images.getImage(5, "tokens"),9,25);
		Players[5] = new Players(6,images.getImage(6, "tokens"),16,1);
		
		Weapons[0] = new weapons(1,images.getImage(1, "weapons"),0,1);
		Weapons[1] = new weapons(2,images.getImage(2, "weapons"),0,2);
		Weapons[2] = new weapons(3,images.getImage(3, "weapons"),0,3);
		Weapons[3] = new weapons(4,images.getImage(4, "weapons"),0,4);
		Weapons[4] = new weapons(5,images.getImage(5, "weapons"),0,5);
		Weapons[5] = new weapons(6,images.getImage(6, "weapons"),0,6);
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
		return Players[current];
	}
	
	public int getGameState() {
		return gameStateCurrent;
	}
}