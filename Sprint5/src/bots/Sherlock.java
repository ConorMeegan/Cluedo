package bots;

import java.util.ArrayList;
import java.util.Random;

import gameengine.*;

public class Sherlock implements BotAPI {
	
	
	private class node {
		int H;
		int T;
		int D;
		int xCord;
		int yCord;
		
		public node(int x, int y) {
			xCord = x;
			yCord = y;
			this.T = -1;
		}
		
		public void setH(int H) {
			this.H = H;
		}
		
		public void setT(int T) {
			this.T= T;
		}
		
		public void setD(int D) {
			this.D = D;
		}
		
		public int getH() {
			return H;
		}
		
		public int getT() {
			return T;
		}
		
		public int getD() {
			return D;
		}
		
		/*
		private void setUpNodeCords(node nodes[][]) {
			for(int i=0;i<24;i++) {
				for(int j=0;j<24;j++) {
					nodes[i][j] =  new node(i,j);
				}
			}
		}
		*/
	}
	
	
	private int turnsDone = 0;
	private Coordinates target = new Coordinates(19,6);
	ArrayList<Coordinates> bestPath = new ArrayList<Coordinates>();
	Coordinates checkedCords;
	public node[][] mapNodes = new node[24][24];
	int lowest = 100;
	
	// The public API of Bot must not change
	// This is ONLY class that you can edit in the program
	// Rename Bot to the name of your team. Use camel case.
	// Bot may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects

	private Player player;
	private PlayersInfo playersInfo;
	private Map map;
	private Dice dice;
	private Log log;
	private Deck deck;

	public Sherlock(Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
		for(int i=0;i<24;i++) {
			for(int j=0;j<24;j++) {
				mapNodes[i][j] =  new node(i,j);
				
			}
		}
		
		
		this.player = player;
		this.playersInfo = playersInfo;
		this.map = map;
		this.dice = dice;
		this.log = log;
		this.deck = deck;

	}

	public String getName() {
		return "Sherlock"; // must match the class name
	}

	public String getCommand() {
		if(turnsDone == dice.getTotal()) {
			turnsDone = 0;
			return "done";
		}
		if(player.getToken().isInRoom()) {
			if(turnsDone == 0){
				return "done";
			}
			turnsDone = 0;
			return "question";
		}
		
		return "roll";
	}

	public String getMove() {
		String move;
		int targetX = target.getCol();
		int targetY = target.getRow();
		int playerX = player.getToken().getPosition().getCol();
		int playerY = player.getToken().getPosition().getRow();
		//int distOfNodeAndTarget = distance(playerX,playerY,targetX,targetY);
		
		int xDifference = playerX-targetX;
		int yDifference = playerY-targetY;
		
		System.out.println("xDifference:" +xDifference);
		System.out.println("yDifference:" +yDifference);
		
		if(yDifference < -1) {
			if(map.isCorridor(new Coordinates(playerX ,playerY+1))) {
				turnsDone += 1;
				System.out.println("true for D");
				return "d";
			}else {
				if(xDifference < -1 && map.isCorridor(new Coordinates(playerX +1,playerY))) {
					turnsDone += 1;
					System.out.println("true for D and R");
					return "r";
				}else if(xDifference > -1 && map.isCorridor(new Coordinates(playerX -1,playerY))) {
					turnsDone += 1;
					System.out.println("true for D and L");
					return "l";
				}
			}
		}else if(yDifference > -1) {
			if(map.isCorridor(new Coordinates(playerX ,playerY-1))) {
				turnsDone += 1;
				System.out.println("true for D");
				return "u";
			}else {
				if(xDifference < -1 && map.isCorridor(new Coordinates(playerX +1,playerY))) {
					turnsDone += 1;
					System.out.println("true for D and R");
					return "r";
				}else if(xDifference > -1 && map.isCorridor(new Coordinates(playerX -1,playerY))) {
					turnsDone += 1;
					System.out.println("true for D and L");
					return "l";
				}
			}
		}else {
			//left and right stuff
			if(xDifference < -1) {
				turnsDone += 1;
				return "r";
			}else if(xDifference > -1) {
				turnsDone += 1;
				return "l";
			}
		}
		turnsDone += 1;
		return "u";
	}

	public String getSuspect() {
		// Add your code here

		return Names.SUSPECT_NAMES[0];
	}

	public String getWeapon() {
		// Add your code here

		return Names.WEAPON_NAMES[0];
	}

	public String getRoom() {
		// Add your code here

		return Names.ROOM_NAMES[0];
	}

	public String getDoor() {
		// Add your code here
		String door = "";

		if(player.getToken().isInRoom()){
			int numberOfDoors =  player.getToken().getRoom().getNumberOfDoors();
			Random rand = new Random();
			door = Integer.toString(rand.nextInt(numberOfDoors)+1);
		}

		return door;
	}

	public String getCard(Cards matchingCards) {
		// Add your code here

		return matchingCards.get().toString();
	}

	public void notifyResponse(Log response) {
		// Add your code here

	}
	
	public int distance(int x,int y, int x2, int y2) {
		
		return (int) Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2));
	}
	
	public void getMove(int x, int y, int targetX, int targetY,int l) throws ArrayIndexOutOfBoundsException{


		int lowestX = 0;
		int lowestY = 0;
		
		
		if(x != targetX && y != targetY) {
			if(map.isCorridor(new Coordinates(x-1,y))) {
				//up
				mapNodes[x-1][y].setD(distance(x,y,x-1,y));
				System.out.println("up Node(D):" +distance(x,y,x-1,y));
				mapNodes[x-1][y].setH(distance(x-1,y,targetX,targetY));
				System.out.println("Up Node(H):" +distance(x-1,y,targetX,targetY));
				mapNodes[x-1][y].setT(mapNodes[x-1][y].getD() + mapNodes[x-1][y].getH());
				System.out.println("Up Node(T):"+mapNodes[x-1][y].getT());
				
			}
			if(map.isCorridor(new Coordinates(x+1,y))) {
				//down
				mapNodes[x+1][y].setD(distance(x,y,x+1,y));
				System.out.println("Down Node(D):" +distance(x,y,x+1,y));
				mapNodes[x+1][y].setH(distance(x+1,y,targetX,targetY));
				System.out.println("Down Node(H):" +distance(x+1,y,targetX,targetY));
				mapNodes[x+1][y].setT(mapNodes[x+1][y].getD() + mapNodes[x+1][y].getH());
				System.out.println("Down Node(T):" +mapNodes[x+1][y].getT());
				
			}
			if(map.isCorridor(new Coordinates(x,y+1))) {
				//right
				mapNodes[x][y+1].setD(distance(x,y,x,y+1));
				System.out.println("Right Node(D):" +distance(x,y,x,y+1));
				mapNodes[x][y+1].setH(distance(x,y+1,targetX,targetY));
				System.out.println("Right Node(H):" +distance(x,y+1,targetX,targetY));
				mapNodes[x][y+1].setT(mapNodes[x][y+1].getD() + mapNodes[x][y+1].getH());
				System.out.println("Right Node(T):" +mapNodes[x][y+1].getT());
				
			}
			if(map.isCorridor(new Coordinates(x,y-1))) {
				//left
				mapNodes[x][y-1].setD(distance(x,y,x,y-1));
				System.out.println("Left Node(D):" +distance(x,y,x,y-1));
				mapNodes[x][y-1].setH(distance(x,y-1,targetX,targetY));
				System.out.println("Left Node(H):" +distance(x,y-1,targetX,targetY));
				mapNodes[x][y-1].setT(mapNodes[x][y-1].getD() + mapNodes[x][y-1].getH());
				System.out.println("Left Node(T):" +mapNodes[x][y-1].getT());
			}
			
			
			for(int i=0;i<24;i++) {
				for(int j=0;j<24;j++) {
					if(mapNodes[i][j].getT() < l && mapNodes[i][j].getT() != -1) {
						lowest = mapNodes[i][j].getT();
						lowestX = i;
						lowestY = j;

					}
				}
			}
			checkedCords=new Coordinates(lowestX,lowestY);
			System.out.println();
			System.out.println(checkedCords.toString());
			for(int i=0;i<24;i++) {
				for(int j=0;j<24;j++) {
					System.out.printf("%d ",mapNodes[i][j].getT());
				}
				System.out.println();
			}
		}else return;
		
	}



}
