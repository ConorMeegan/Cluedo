import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GameMechanics {
	
	Random rand = new Random();
	
	ArrayList<Integer> startList = new ArrayList<Integer>();
	ArrayList<Integer> murderEnvelope = new ArrayList<Integer>();
	ArrayList<Integer> loser = new ArrayList<Integer>();
	ArrayList<Integer> CList = new ArrayList<Integer>();
	
	int gameStateCurrent = 2;
	int numOfCards = 21;
	
	int accuseCurrent;
	int current = 0;
	int exitNum = 0;
	int secretExitNum = 0;
	int password = 0;
	int x=0,xOne = 852/2;
	int numOfPlayers;
	int diff;
	int frames = 0 ;
	
	int rollNum = 0;
	int done = 0;
	
	Frame frame;
	BufferStrategy buffer;
	Graphics g;
	int[] gameState = {1,2,3,4,5,6,7};
	int width, height;
	
	Images images = new Images();
	
	Players[] Players = new Players[6];
	Card[] Rooms = new Card[9];
	weapons[] Weapons = new weapons[6];

	Accusation accuse = new Accusation(Players,this);
	
	BufferedImage background;
	BufferedImage murder[] = new BufferedImage[3];
	
	Dimensions dimensions = new Dimensions();
	PlayerInput playerInput = new PlayerInput(this,accuse);
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
		frame.getCanvas().addKeyListener(keyManager);
		try {
			background = ImageIO.read(getClass().getResource("map2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.setPlayerInput(playerInput);
		
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
		//Start screen
		if(gameState[0] == gameStateCurrent) {
			//gameStateCurrent += 1;
			g.drawImage(images.getImage(2, "screens"), 0, 0, null);
			g.drawImage(images.getImage(3, "screens"), 480, 78, null);
			//main game
		}else if(gameState[1] == gameStateCurrent) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			g.drawImage(background,180, 0, null);
			if(Players[current].getDoor() != 0) {
				exit();
			}
			checkDone();
			Draw();
			if(CList.size() != 0) {
				for(int i=0;i<CList.size();i++) {
					if(CList.get(i) <= 6) {
						g.drawImage(images.getImage(CList.get(i), "cards"), 160, 632, null);
						
					}else if(CList.get(i) >= 11 && CList.get(i) < 20) {
						g.drawImage(images.getImage(CList.get(i), "room"), 260, 632, null);
						
					}else if(CList.get(i) >= 21 && CList.get(i) < 27) {
						g.drawImage(images.getImage(CList.get(i), "weaponsCard"), 360, 632, null);
						
					}
				}
			}
			//murder (cheat)
		}else if(gameState[3] == gameStateCurrent) {
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			//g.drawImage(images.getImage(0, "screens"),45, 0, null);
			
			g.drawImage(images.getImage(5, "screens"), 0, 0, null);
			g.drawImage(images.getImage(murderEnvelope.get(0), "cards"), 231, 255, null);
			g.drawImage(images.getImage(murderEnvelope.get(1), "room"), 368, 255, null);
			g.drawImage(images.getImage(murderEnvelope.get(2), "weaponsCard"), 505, 255, null);
			g.drawImage(images.getImage(7, "screens"), 0, 0, null);
			//Accusation board
		}else if(gameState[4] == gameStateCurrent) {
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			int x = 430; int y = 5;
			for(int i=1;i<=26;i++) {
				if(x > width-20) {
					x = 10;
					y += 167;
				}
				g.drawImage(images.getImage(1, "screens"), 0, 0, null);
				
				if(i<=6) {
					if(accuse.getAccuseList().contains(i)) {
						g.drawImage(images.getImage(7, "cards"), x, y, null);
						x+=140;
					}else {
						g.drawImage(images.getImage(i, "cards"), x, y, null);
						x+=140;
					}
				}else if(i >=11 && i<=19) {
					if(accuse.getAccuseList().contains(i)) {
						g.drawImage(images.getImage(7, "cards"), x, y, null);
						x+=140;
					}else {
						g.drawImage(images.getImage(i, "room"), x, y, null);
						x+=140;
					}
				}else if(i >=21 && i<=26) {
					if(accuse.getAccuseList().contains(i)) {
						g.drawImage(images.getImage(7, "cards"), x, y, null);
						x+=140;
					}else {
						g.drawImage(images.getImage(i, "weaponsCard"), x, y, null);
						x+=140;
					}
				}
			}
		}else if(gameState[6] == gameStateCurrent) {
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			int x = 430; int y = 5;
			for(int i=1;i<=26;i++) {
				if(x > width-20) {
					x = 10;
					y += 167;
				}
				g.drawImage(images.getImage(11, "screens"), 0, 0, null);
				
				if(i<=6) {
					if(accuse.getAccuseList().contains(i)) {
						g.drawImage(images.getImage(7, "cards"), x, y, null);
						x+=140;
					}else {
						g.drawImage(images.getImage(i, "cards"), x, y, null);
						x+=140;
					}
				}else if(i >=11 && i<=19) {
					if(accuse.getAccuseList().contains(i)) {
						g.drawImage(images.getImage(7, "cards"), x, y, null);
						x+=140;
					}else {
						g.drawImage(images.getImage(i, "room"), x, y, null);
						x+=140;
					}
				}else if(i >=21 && i<=26) {
					if(accuse.getAccuseList().contains(i)) {
						g.drawImage(images.getImage(7, "cards"), x, y, null);
						x+=140;
					}else {
						g.drawImage(images.getImage(i, "weaponsCard"), x, y, null);
						x+=140;
					}
				}
			}
		}else if(gameState[5] == gameStateCurrent) {
			draw(accuseCurrent);
		}
		buffer.show();
		g.dispose();
	}
	
	//handles all drawing of sprites
	public void Draw() {
		
		for(int i=0;i<numOfPlayers;i++) {
			g.drawImage(Players[i].getImage(), (Players[i].getx()*24) +180, Players[i].gety()*24, null);
		}
		for(int i=0;i<6;i++){
			g.drawImage(Weapons[i].getImage(), (Weapons[i].getx()*24) +170, (Weapons[i].gety()*24), null);
		}
		
		int y = 190;
		for(int i=0;i<startList.size();i++){
			g.drawImage(images.getImage(startList.get(i), "bigToken"), 157, y, null);
			if(i == current) {
				g.drawImage(images.getImage(12, "screens"), 148, y-8, null);
			}
			y+=70;
		}
		
		
		
		
		if(exit() || secretExit()) {
			if(secretExit() && rollNum > 0) {
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
							gameStateCurrent = 5;
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
						rollNum = 0;
						
					}else if(Players[current].getDoor() == 5) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						
						if(dimensions.checkPosAvailable(24,22,10)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
							gameStateCurrent = 5;
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
						rollNum = 0;
						
					}else if(Players[current].getDoor() == 3) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						
						if(dimensions.checkPosAvailable(4,22,7)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
							gameStateCurrent = 5;
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
						rollNum = 0;
						
					}else if(Players[current].getDoor() == 7) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						;
						if(dimensions.checkPosAvailable(23,5,3)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
							gameStateCurrent = 5;
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
					
						setExitNum(0);
						rollNum = 0;
					}
				}				
			}
			if(exit() && rollNum > 0) {
			
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
						}
					}
				}
				setExitNum(0);
			}
		}
		
		int yValue = 15;
		if(password == 1) {
			for(int i=0;i<diff;i++) {
				if(Players[current].cards.get(i) <= 6) {
					g.drawImage(images.getImage(Players[current].cards.get(i), "cards"), 15, yValue, null);
					yValue += 30*numOfPlayers;
				}else if(Players[current].cards.get(i) >= 11 && Players[current].cards.get(i) < 20) {
					g.drawImage(images.getImage(Players[current].cards.get(i), "room"), 15, yValue, null);
					yValue += 30*numOfPlayers;
				}else if(Players[current].cards.get(i) >= 21 && Players[current].cards.get(i) < 27) {
					g.drawImage(images.getImage(Players[current].cards.get(i), "weaponsCard"), 15, yValue, null);
					yValue += 30*numOfPlayers;
				}
			}
		}else {
			for(int i=0;i<diff;i++) {
				g.drawImage(images.getImage(7, "cards"), 15, yValue, null);
				yValue += 30*numOfPlayers;
			}
		}
		
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
		diff = 18 / numOfPlayers;
		
		CList.add(1);
		CList.add(2);
		CList.add(3);
		CList.add(4);
		CList.add(5);
		CList.add(6);
		
		CList.add(11);
		CList.add(12);
		CList.add(13);
		CList.add(14);
		CList.add(15);
		CList.add(16);
		CList.add(17);
		CList.add(18);
		CList.add(19);
		
		CList.add(21);
		CList.add(22);
		CList.add(23);
		CList.add(24);
		CList.add(25);
		CList.add(26);
		
		for(int j=0;j<numOfPlayers;j++) {
			if(start.getPlayerNames(j) == "Crazy Cat Lady") {
				Players[j] = new Players(1,images.getImage(1, "tokens"), 11, 1);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
				startList.add(1);
			}else if(start.getPlayerNames(j) == "Hanz Moleman") {
				Players[j] = new Players(3,images.getImage(3, "tokens"), 2, 18);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
				startList.add(3);
			}else if(start.getPlayerNames(j) == "Fat Tony") {
				Players[j] = new Players(2,images.getImage(2, "tokens"), 25, 20);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
				startList.add(2);
			}else if(start.getPlayerNames(j) == "Moe Syzlack") {
				Players[j] = new Players(6,images.getImage(6, "tokens"), 16, 1);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
				startList.add(6);
			}else if(start.getPlayerNames(j) == "Maggie Simpson") {
				Players[j] = new Players(5,images.getImage(5, "tokens"), 9, 25);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
				startList.add(5);
			}else if(start.getPlayerNames(j) == "Homer Simpson") {
				Players[j] = new Players(4,images.getImage(4, "tokens"), 25, 7);
				Players[j].setName(start.getNames(j));
				Players[j].setPlayerName(start.getPlayerNames(j));
				startList.add(4);
			}
		}
			
		//beginning dice roll here
		//for loop to roll the dice
		//check who gets the highest
		//put the player id value into an array of the player who rolled the highest number
		//if equal we roll again
		
		DiceRoll roll = new DiceRoll();
		
		//the starting roll for all players	
		for(int i=0; i<numOfPlayers; i++)
		{
			roll.Diceroll();
			int num = roll.getTotal();
			Players[i].setstartingRoll(num);
			playerInput.message(Players[i].getName() + " rolled a " +Players[i].getstartingRoll());
		}
		
		//checking for duplicate starting rolls
		for(int i=0; i<numOfPlayers; i++)
		{
			for(int j=i+1; j<numOfPlayers; j++)
			{
				if(Players[i].getstartingRoll() == Players[j].getstartingRoll())
				{
					//players keep rolling until they roll different totals
					while(Players[i].getduplicateRoll() == Players[j].getduplicateRoll())
					{
						roll.Diceroll();
						int num1 = roll.getTotal();
						Players[i].setduplicateRoll(num1);
						playerInput.message(Players[i].getName() + " must roll again to decide their tie. "+
								Players[i].getName()+" rolled a " +Players[i].getduplicateRoll());
						
						roll.Diceroll();
						int num2 = roll.getTotal();
						Players[j].setduplicateRoll(num2);
						playerInput.message(Players[j].getName() + " must roll again to decide their tie. "+
								Players[j].getName()+" rolled a " +Players[j].getduplicateRoll());
					}
				}
			}
		}
		
		//Players temp;
		
		for(int i=0; i<numOfPlayers; i++)
		{
			for(int j=1; j<(numOfPlayers-i); j++) 
			{
	            if(Players[j - 1].getstartingRoll() < Players[j].getstartingRoll()) 
	            {
	            	Players[j].setbiggestRoll(true);
	            	Players[j - 1].setbiggestRoll(false);
	            }
	            //checking the case in which players rolled the same number to start
	            else if(Players[j - 1].getstartingRoll() == Players[j].getstartingRoll()) 
	            {
	            	//checks the duplicate roll total to see who'd go first between the players
	            	if(Players[j - 1].getduplicateRoll() < Players[j].getduplicateRoll()) 
	            	{
	            		Players[j].setbiggestRoll(true);
		            	Players[j - 1].setbiggestRoll(false);
	            	}
	            }
	        }
		}
		
		for(int i=0; i<numOfPlayers; i++)
		{
			if(Players[i].getbiggestRoll() == true)
			{
				current = i;
			}
		}
		/*
		//ordering players by their starting dice roll
		for(int i=0; i<numOfPlayers; i++)
		{
			for(int j=1; j<(numOfPlayers-i); j++) 
			{
	            if(Players[j - 1].getstartingRoll() < Players[j].getstartingRoll()) 
	            {
	            	//swapping player's positions if one has a higher starting roll
	                temp = Players[j - 1];
	                Players[j - 1] = Players[j];
	                Players[j] = temp;
	            }
	            //checking the case in which players rolled the same number to start
	            else if(Players[j - 1].getstartingRoll() == Players[j].getstartingRoll()) 
	            {
	            	//checks the duplicate roll total to see who'd go first between the players
	            	if(Players[j - 1].getduplicateRoll() < Players[j].getduplicateRoll()) 
	            	{
	            		temp = Players[j - 1];
		                Players[j - 1] = Players[j];
		                Players[j] = temp;
	            	}
	            }
	        }
		}
		
		//printing out the order of the players by their starting roll(s)
		for(int i=0; i<numOfPlayers; i++)
		{
			playerInput.message(Players[i].getName()+": "+Players[i].getstartingRoll());
		}
		*/
		int suspect = rand.nextInt(6)+1;
		int room = rand.nextInt(9)+11;
		int weapon = rand.nextInt(6)+21;
		
		murderEnvelope.add(suspect);
		murderEnvelope.add(room);
		murderEnvelope.add(weapon);

		CList.remove(CList.indexOf(suspect));
		CList.remove(CList.indexOf(room));
		CList.remove(CList.indexOf(weapon));

		setPlayerCards();

		Weapons[0] = new weapons(21,images.getImage(1, "weapons"),0,1);
		Weapons[1] = new weapons(22,images.getImage(2, "weapons"),0,2);
		Weapons[2] = new weapons(23,images.getImage(3, "weapons"),0,3);
		Weapons[3] = new weapons(24,images.getImage(4, "weapons"),0,4);
		Weapons[4] = new weapons(25,images.getImage(5, "weapons"),0,5);
		Weapons[5] = new weapons(26,images.getImage(6, "weapons"),0,6);
		
		Rooms[0] = new Card(60,images.getImage(11, "room"),0,0,11);
		Rooms[0] = new Card(50,images.getImage(16, "room"),0,0,16);
		Rooms[0] = new Card(40,images.getImage(12, "room"),0,0,12);
		Rooms[0] = new Card(30,images.getImage(19, "room"),0,0,19);
		Rooms[0] = new Card(20,images.getImage(15, "room"),0,0,15);
		Rooms[0] = new Card(70,images.getImage(17, "room"),0,0,17);
		Rooms[0] = new Card(80,images.getImage(14, "room"),0,0,14);
		Rooms[0] = new Card(90,images.getImage(18, "room"),0,0,18);
		Rooms[0] = new Card(100,images.getImage(13, "room"),0,0,13);
		playerInput.errorMessages(1);
		playerInput.errorMessages(2);
		
		for(int i=0;i<numOfPlayers;i++) {
			Players[i].getCards();
		}
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
				rollNum = 0;
				
				gameStateCurrent = 5;
			}
			
		}else if(val == 30) {
			if(dimensions.checkPosAvailable(23,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 5;
			}
		}else if(val == 40) {
			if(dimensions.checkPosAvailable(13,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 5;
			}
		}else if(val == 50){
			if(dimensions.checkPosAvailable(4,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 5;
			}
		}else if(val == 60){
			if(dimensions.checkPosAvailable(5,14,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 5;
			}
		}else if(val == 70){
			if(dimensions.checkPosAvailable(4,22,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 5;
			}
		}else if(val == 80){
			if(dimensions.checkPosAvailable(11,24,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 5;
			}
		}else if(val == 90){
			if(dimensions.checkPosAvailable(17,24,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 5;
			}
		}else if(val == 100){
			if(dimensions.checkPosAvailable(24,22,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
	
				gameStateCurrent = 5;
			}
		}else if(val == 110){
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(14);
				Players[current].setx(14);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 7;
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
	
	public void setCurrentGameState(int num) {
		gameStateCurrent = num;
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
	
	public Players getcurrent(){
		return Players[current];
	}
	public void newMClass() {
		murderEnvelope.add(rand.nextInt(6)+1);
		murderEnvelope.add(rand.nextInt(9)+11);
		murderEnvelope.add(rand.nextInt(6)+21);
	}
	
	public void checkDone() {
		if(done == 1) {
			checkCurrent();
			setDone(0);
		}
	}
	
	public void setPlayerCards() {
		for(int i=0;i<numOfPlayers;i++){
			for(int j=0;j<diff;j++) {
				int ran = rand.nextInt(CList.size());
				Players[i].cards.add(CList.get(ran));
				CList.remove(ran);
			}
		}
	}
	
	public String getCards(int num){
		for(int i = 0; i<Players[current].cards.size(); i++){
			if( Players[current].cards.get(i) == num){
				return "X";
			}
			else if(CList.size() > 0){
				for(int j = 0; j<CList.size(); j++){
					if(CList.get(j) == num){
						return "A";
					}
				}
			}
		}
		return "";
	}
	
	public void table() {
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    Object rows[] = {"carzyCatLady", "fatTony", "hanzMoleMan", "homer", "maggie", "moe", "axe",
	    		"PlutoniumRod","chainsaw", "gun", "knife", "slingShot", "Burn's Mansion", "Springfield elementary",
	    		"Frying Dutchman", "Flander's house", "krusty burger", "comic book store", "kwik-e-mart", "simpson's house",
	    		"moe's tavern"};
	    Object columnNames[] = { "All Cards", "Your Cards"};
	   
	    Object rowData[][] = { { rows[0], getCards(1) },
	    		{ rows[1], getCards(2) },
	    		{ rows[2], getCards(3) },
	    		{ rows[3], getCards(4) },
	    		{ rows[4], getCards(5) },
	    		{ rows[5], getCards(6) },
	    		{ rows[6], getCards(21) },
	    		{ rows[7], getCards(22) },
	    		{ rows[8], getCards(23) },
	    		{ rows[9], getCards(24) },
	    		{ rows[10], getCards(25) },
	    		{ rows[11], getCards(26) },
	    		{ rows[12], getCards(11) },
	    		{ rows[13], getCards(12) },
	    		{ rows[14], getCards(13) },
	    		{ rows[15], getCards(14) },
	    		{ rows[16], getCards(15) },
	    		{ rows[17], getCards(16) },
	    		{ rows[18], getCards(17) },
	    		{ rows[19], getCards(18) },
	    		{ rows[20], getCards(19) },
	    		};
	    
	      
	    JTable table = new JTable(rowData, columnNames);
	    table.setEnabled(false);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(700, 398);
	    frame.setVisible(true);

	  }
	
	public Accusation getAccuse() {
		return accuse;
	}
	public void setPassword(int t) {
		password = t;
	}
	public void setAccuseCurrent(int number) {
		accuseCurrent = number;
	}
	
	public void draw(int number) {
		g.setColor(Color.BLACK);
		g.drawImage(images.getImage(8, "screens"), 0, 0, null);
		for(int i=0;i<6;i++) {
			if(i == accuseCurrent) {
				g.drawImage(images.getImage(10, "screens"), 340, 150, null);
				g.drawImage(images.getImage(Players[accuseCurrent].getPlayerId(), "bigToken"), 420,153, null);
			}
		}
		for(int i=0;i<accuse.getAccuseList().size();i++) {
			if(accuse.getAccuseList().get(i)<=6) {
				g.drawImage(images.getImage(accuse.getAccuseList().get(i), "cards"), 231, 255, null);
			
			}else if(accuse.getAccuseList().get(i) >=11 && accuse.getAccuseList().get(i)<=19) {
				g.drawImage(images.getImage(accuse.getAccuseList().get(i), "room"), 368, 255, null);
				
			}else if(accuse.getAccuseList().get(i) >=21 && accuse.getAccuseList().get(i)<=26) {
				g.drawImage(images.getImage(accuse.getAccuseList().get(i), "weaponsCard"), 505, 255, null);
			
			}
		}
		
		int xValue = 20;
		for(int i=0;i<diff;i++) {
			if(Players[number].cards.get(i) <= 6) {
				g.drawImage(images.getImage(Players[number].cards.get(i), "cards"), xValue, 450, null);
				xValue += 43*numOfPlayers;
			}else if(Players[number].cards.get(i) >= 11 && Players[number].cards.get(i) < 20) {
				g.drawImage(images.getImage(Players[number].cards.get(i), "room"), xValue, 450, null);
				xValue += 43*numOfPlayers;
			}else if(Players[number].cards.get(i) >= 21 && Players[number].cards.get(i) < 27) {
				g.drawImage(images.getImage(Players[number].cards.get(i), "weaponsCard"), xValue, 450, null);
				xValue += 43*numOfPlayers;
			}
		}
		
	}
	public void showAccused(int number, int player) {
		System.out.println("player:" +player);
		System.out.println("number:" +number);
		if(number == 0 || player == 0) {
			playerInput.message("No player had any cards");
		}else {
			playerInput.message(Players[player].getName()+" has the card:" + number);
		}
		setCurrentGameState(2);
	}
	
	public void checkWin() {
		int totalMurder = 0;
		int totalAccuse = 0;
		for(int i = 0;i<3;i++) {
			totalMurder += murderEnvelope.get(i);
			totalAccuse += accuse.getAccuseList().get(i);
		}
		
		if(totalMurder == totalAccuse) {
			playerInput.message("Winner");
		}
	}
	
	public weapons[] getWeapons() {
		return Weapons;
	}
	
	public Players[] getPlayers() {
		return Players;
	}
	
	public Card[] getRooms() {
		return Rooms;
	}
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
}