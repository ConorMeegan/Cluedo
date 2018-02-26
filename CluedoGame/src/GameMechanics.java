import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameMechanics {
	
	int gameStateCurrent = 1;
	
	int current = 0;
	int exitNum = 0;
	int secretExitNum = 0;
	
	int x=0,xOne = 852/2;
	int numOfPlayers;
	int frames = 0 ;
	
	int rollNum = 0;
	int done = 0;
	
	Frame frame;
	BufferStrategy buffer;
	Graphics g;
	int[] gameState = {1,2,3};
	int width, height;
	
	Images images = new Images();
	
	Players[] Players = new Players[6];
	Cards[] Cards = new Cards[6];
	weapons[] Weapons = new weapons[6];

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
		frames++;
		buffer = frame.getCanvas().getBufferStrategy();
		if(buffer == null) {
			frame.getCanvas().createBufferStrategy(2);
			return;
		}
		g = buffer.getDrawGraphics();

		
		//future code for future game states
		if(gameState[0] == gameStateCurrent) {
			gameStateCurrent += 1;
		}else if(gameState[1] == gameStateCurrent) {
			g.setColor(new Color(97,62,7));
			g.fillRect(0, 0, width, height);
			g.drawImage(background,180, 0, null);
			if(Players[current].getDoor() != 0) {
				exit();
			}
			checkDone();
			Draw();
			
		}else if(gameState[2] == gameStateCurrent) {
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
		
		for(int i=0;i<numOfPlayers;i++) {
			g.drawImage(Players[i].getImage(), (Players[i].getx()*24) +180, Players[i].gety()*24, null);
		}
		if(exit() || secretExit()) {
			if(secretExit()) {
				if(Players[current].getDoor() == 10 || Players[current].getDoor() == 5) {
					g.drawImage(images.getImage(1, "secret"),(25*24) + 180, 22*24, null);
					g.drawImage(images.getImage(1, "secret"),(7*24) + 180, 2*24, null);
				}else if(Players[current].getDoor() == 3 || Players[current].getDoor() == 7){
					g.drawImage(images.getImage(1, "secret"),(2*24) + 180, 20*24, null);
					g.drawImage(images.getImage(1, "secret"),(21*24) + 180, 6*24, null);
				}
		
				if(getExitNum(5) == 5) {
					
					if(Players[current].getDoor() == 10) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						
						if(dimensions.checkPosAvailable(4,5,5)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
					}else if(Players[current].getDoor() == 5) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						
						if(dimensions.checkPosAvailable(24,22,10)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
					}else if(Players[current].getDoor() == 3) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						
						if(dimensions.checkPosAvailable(4,22,7)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
					}else if(Players[current].getDoor() == 7) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						;
						if(dimensions.checkPosAvailable(23,5,3)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
						};
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
					
						setExitNum(0);
					}
		
				}
				
			}
			if(exit()) {
			
				int count =1;
				for(int i=0;i<28;i++) {
					for(int j=0;j<28;j++) {
						if(dimensions.getVal(j,i) == (Players[current].getDoor())*10) {
							g.drawImage(images.getImage(count, "numbers"),(j*24) + 180, i*24, null);
							if(count == getExitNum(2)) {
								if(dimensions.checkPosAvailable(i,j,0)) {
									dimensions.setVal(i, j, Players[current].getDoor()/10);
									Players[current].setx(dimensions.getX());
									Players[current].sety(dimensions.getY());
									dimensions.setVal(i, j, 47);
									Players[current].setDoor(0);
								}
							}
							count += 1;
							//g.fillRect((j*24) + 180, i*24, 24, 24);
						}
					}
				}
				setExitNum(0);
			}
		}
		g.drawImage(images.getImage(6, "cards"), 15, 15, null);
		g.drawImage(images.getImage(5, "cards"), 15, 240, null);
		g.drawImage(images.getImage(2, "cards"), 15, 460, null);
	}
	
	public void movement(int num) {
		if(rollNum != 0 && done == 0) {
			if(num == 1) {
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(moving.moveUp(Players[current].gety()));
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum--;
			}else if(num == 2) {
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(moving.moveDown(Players[current].gety()));
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum--;
			}else if(num == 3) {
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].setx(moving.moveRight(Players[current].getx()));
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum--;
			}else if(num == 4){
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].setx(moving.moveLeft(Players[current].getx()));
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum--;
			}
		}else if(rollNum == 0) {
			setDone(0);
			playerInput.setSpaces();
			playerInput.errorMessages(3);
		}
	}
	
	//receives all images and sets all object to positions
	public void Initialise() {
		GameStart start = new GameStart(this);
		
		for(int j=0;j<numOfPlayers;j++) {
			if(start.getPlayerNames(j) == "Crazy Cat Lady") {
				Players[j] = new Players(j,images.getImage(1, "tokens"), 11, 1);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
			}else if(start.getPlayerNames(j) == "Hanz Moleman") {
				Players[j] = new Players(j,images.getImage(3, "tokens"), 2, 18);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
			}else if(start.getPlayerNames(j) == "Fat Tony") {
				Players[j] = new Players(j,images.getImage(2, "tokens"), 25, 20);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
			}else if(start.getPlayerNames(j) == "Moe Syzlack") {
				Players[j] = new Players(j,images.getImage(6, "tokens"), 16, 1);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
			}else if(start.getPlayerNames(j) == "Maggie Simpson") {
				Players[j] = new Players(j,images.getImage(5, "tokens"), 9, 25);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
			}else if(start.getPlayerNames(j) == "Homer Simpson") {
				Players[j] = new Players(j,images.getImage(4, "tokens"), 25, 7);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
			}
		}
		
		
		
		Weapons[0] = new weapons(1,images.getImage(1, "weapons"),0,1);
		Weapons[1] = new weapons(2,images.getImage(2, "weapons"),0,2);
		Weapons[2] = new weapons(3,images.getImage(3, "weapons"),0,3);
		Weapons[3] = new weapons(4,images.getImage(4, "weapons"),0,4);
		Weapons[4] = new weapons(5,images.getImage(5, "weapons"),0,5);
		Weapons[5] = new weapons(6,images.getImage(6, "weapons"),0,6);
		playerInput.errorMessages(1);
		playerInput.errorMessages(2);
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
		}
	}
	
	public void setDoor(int val) {
		if(val == 20) {
			if(dimensions.checkPosAvailable(23,15,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
			
		}else if(val == 30) {
			if(dimensions.checkPosAvailable(23,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
		}else if(val == 40) {
			if(dimensions.checkPosAvailable(13,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
		}else if(val == 50){
			if(dimensions.checkPosAvailable(4,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
		}else if(val == 60){
			if(dimensions.checkPosAvailable(5,14,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
		}else if(val == 70){
			if(dimensions.checkPosAvailable(4,22,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
		}else if(val == 80){
			if(dimensions.checkPosAvailable(11,24,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
		}else if(val == 90){
			if(dimensions.checkPosAvailable(17,24,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
		}else if(val == 100){
			if(dimensions.checkPosAvailable(24,22,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
			}
		}else if(val == 110){
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(14);
				Players[current].setx(14);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
		}
	}
	
	public Dimensions getDimensions() {
		return dimensions;
	}
	
	public Players getOb() {
		return Players[current];
	}
	
	public int getGameState() {
		return gameStateCurrent;
	}
	
	public void setExitNum(int val) {
		if(val == 0) {
			exitNum = 0;
			secretExitNum = 0;
		}else if (val > 0 && val < 5){
			exitNum = val;
		}else {
			secretExitNum = 5;
		}
	}
	public int getExitNum(int val) {
		if(val < 5) {
			return exitNum;
		}else {
			return secretExitNum;
		}
	}

	public void animation() {
		g.setColor(Color.BLACK);
		if(frames%15 == 0 && x < (852/2)+20) {
			x += 20;
			g.fillRect(0, 0, 852, 672);
			g.setColor(Color.white);
			g.fillRect((852/2)-20, x, 20, 20);
		}
	}
	
	public boolean secretExit() {
		if(Players[current].getDoor() == 10 || Players[current].getDoor() == 7 || Players[current].getDoor() == 5 || Players[current].getDoor() == 3) {
			return true;
		}
		return false;
	}
	public boolean exit() {
		if(Players[current].getDoor() > 0) {
			return true;
		}
		return false;
	}
	
	public void setMax(int max) {
		numOfPlayers= max;
	}
	
	public void setRoll(int val) {
		rollNum = val;
	}
	
	public void setDone(int val) {
		done = val;
	}
	
	public void checkCurrent() {
		if(current < (numOfPlayers-1)) {
			
			current++;
		}else {
			
			current = 0;
		}
	}
	
	public void checkDone() {
		if(done == 1) {
			checkCurrent();
			setDone(0);
		}
	}
}