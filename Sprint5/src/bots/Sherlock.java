package bots;

import java.util.ArrayList;
import java.util.Random;

import gameengine.*;

public class Sherlock implements BotAPI {

	ArrayList<String> knownPlayers = new ArrayList<>();
	ArrayList<String> suspectPlayers = new ArrayList<>();
	ArrayList<String> knownWeapons = new ArrayList<>();
	ArrayList<String> suspectWeapons = new ArrayList<>();
	ArrayList<String> knownRooms = new ArrayList<>();
	ArrayList<String> suspectRooms = new ArrayList<>();
	
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

		String peacockToConservatory = "llllluu";
		String peacockToBilliardRoom = "llllllddr";
		String peacockToLibrary = "lllllldddddddrrrd";
		String peacockToStudy = "lllllllddddddddddddddrd";
		String peacockToBallRoom = "lllllllul";
		String peacockToKitchen = "lllllllddlllllllllllluu";
		String peacockToDiningRoom = "llllllldddlllllllldddl";
		String peacockToHall = "llllllldddddddddddlllld";
		String peacockToLounge = "llllllldddddddddddlllllllllldd";

		String mustardToLounge = "rrrrrrdd";
		String mustardToDiningRoom = "rrrrrruu";
		String mustardToHall = "rrrrrrrrrrrd";
		String mustardToStudy = "rrrrrrrrrrrrrrrrdddrd";
		String mustardToLibrary = "rrrrrrrrrrrrrrrrur";
		String mustardToBilliardRoom = "rrrrrrrrrrrrrrrruuuuuuuurr";
		String mustardToConservatory = "rrrrrrrrrrrrrrrruuuuuuuuuuuurru";
		String mustardToBallRoom = "rrrrrrrruuuuuuuuuru";
		String mustardToKitchen = "rrrrrrrruuuuuuuuulllluu";

		//String conservatoryToBallRoom = "lll";


		return "u";
	}

	public String getSuspect() {
		// Add your code here


		if(player.hasCard("Green")){
			knownPlayers.add("Green");
		}
		if(player.hasCard("White")){
			knownPlayers.add("White");
		}
		if(player.hasCard("Peacock")){
			knownPlayers.add("Peacock");
		}
		if(player.hasCard("Scarlett")){
			knownPlayers.add("Scarlett");
		}
		if(player.hasCard("Plum")){
			knownPlayers.add("Plum");
		}
		if(player.hasCard("Mustard")){
			knownPlayers.add("Mustard");
		}
		/*
		for (String name : Names.WEAPON_NAMES) {
			if (player.hasCard(name)) {
				knownWeapons.add(name);
			}
		}*/
		for(int i=0; i<6; i++){
			if(!(player.hasCard(Names.SUSPECT_NAMES[i]))) {
				suspectPlayers.add(Names.SUSPECT_NAMES[i]);
			}
		}
		System.out.println(player.getCards());
		System.out.println(knownPlayers);
		System.out.println(suspectPlayers);


		return suspectPlayers.get(0);
	}

	public String getWeapon() {
		// Add your code here

		if(player.hasCard("Pistol")){
			knownWeapons.add("Pistol");
		}
		if(player.hasCard("Rope")){
			knownWeapons.add("Rope");
		}
		if(player.hasCard("Dagger")){
			knownWeapons.add("Dagger");
		}
		if(player.hasCard("Wrench")){
			knownWeapons.add("Wrench");
		}
		if(player.hasCard("Candlestick")){
			knownWeapons.add("Candlestick");
		}
		if(player.hasCard("Lead Pipe")){
			knownWeapons.add("Lead Pipe");
		}
		/*
		for (String name : Names.WEAPON_NAMES) {
			if (player.hasCard(name)) {
				knownWeapons.add(name);
			}
		}*/
		for(int i=0; i<6; i++){
			if(!(player.hasCard(Names.WEAPON_NAMES[i]))) {
				suspectWeapons.add(Names.WEAPON_NAMES[i]);
			}
		}
		System.out.println(player.getCards());
		System.out.println(knownWeapons);
		System.out.println(suspectWeapons);

		return suspectWeapons.get(0);
	}

	public String getRoom() {
		// Add your code here

		if(player.hasCard("Kitchen")){
			knownRooms.add("Kitchen");
		}
		if(player.hasCard("Ballroom")){
			knownRooms.add("Ballroom");
		}
		if(player.hasCard("Conservatory")){
			knownRooms.add("Conservatory");
		}
		if(player.hasCard("Billiard Room")){
			knownRooms.add("Billiard Room");
		}
		if(player.hasCard("Library")){
			knownRooms.add("Library");
		}
		if(player.hasCard("Study")){
			knownRooms.add("Study");
		}
		if(player.hasCard("Hall")){
			knownRooms.add("Hall");
		}
		if(player.hasCard("Lounge")){
			knownRooms.add("Lounge");
		}
		if(player.hasCard("Dining Room")){
			knownRooms.add("Dining Room");
		}
		/*
		for (String name : Names.ROOM_NAMES) {
			if (player.hasCard(name)) {
				knownRooms.add(name);
			}
		}*/
		for(int i=0; i<6; i++){
			if(!(player.hasCard(Names.ROOM_NAMES[i]))) {
				suspectPlayers.add(Names.ROOM_NAMES[i]);
			}

		}
		System.out.println(player.getCards());
		System.out.println(knownRooms);
		System.out.println(suspectRooms);

		return suspectRooms.get(0);
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

}
