package bots;

import java.util.ArrayList;
import java.util.Random;

import gameengine.*;

public class Bot3 implements BotAPI {

	ArrayList<String> knownPlayers = new ArrayList<>();
	ArrayList<String> suspectPlayers = new ArrayList<>();
	ArrayList<String> knownWeapons = new ArrayList<>();
	ArrayList<String> suspectWeapons = new ArrayList<>();
	ArrayList<String> knownRooms = new ArrayList<>();
	ArrayList<String> suspectRooms = new ArrayList<>();
	
	int lastIndex = 0;
	
	private int turnsDone = 0;
	private String target = "Lounge";

	ArrayList<Coordinates> bestPath = new ArrayList<Coordinates>();
	Coordinates checkedCords;
	
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
	
	String plumToStudy = "lllllldd";
	String plumToHall = "llllllllddl";
	String plumToLounge = "lllllllluulllllllllldd";
	String plumToDiningRoom = "lllllllluulllllllllluu";
	String plumToLibrary = "llllllluuur";
	String plumToBillardRoom = "llllllluuuuuuuuuur";
	String plumToConservatory = "llllllluuuuuuuuuuuuuurru";
	String plumToBallRoom = "llllllluuuuuuuuuuuuuul";
	String plumToKitchen = "lllllllluulllllllluuuuuuuuulllluu";
	
	String whiteToBallRoom = "dllddddr";
	String whiteToKitchen= "dllddddddlllu";
	String whiteToConsevatory = "dlldddddddrrrrrrrrrruuuru";
	String whiteToDiningRoom = "dllddddddddrdddl";
	String whiteToBillardRoom = "dlldddddddrrrrrrrrrrdl";
	String whiteToLibrary = "dlldddddddrrrrrrrrrddddddddl";
	String whiteToLounge = "dllddddddddrdddddddddlld";
	String whiteToHall = "dllddddddddrddddddddrrrd";
	String whiteToStudy= "dlldddddddrrrrrrrrrddddddddddddrd";
	
	//Green
	String greenToBallRoom = "drrdddl";
	String greenToConsevatory = "drrdrdddru";
	String greenToBillardRoom = "drrddddddddrr";
	String greenToLibrary = "drrdddddddddddddddr";
	String greenToStudy = "drrdddddddddddddddddddrd";
	String greenToHall = "drrdddddddddddddddddddll";
	String greenToKitchen = "drrddddddddllllllllllluluu";
	String greenToDiningroom = "drrddddddddlllllllldddl";
	String greenToLounge = "drrddddddddlllllllldddddddddlld";
	
	//Scarlet
	String scarlettToLounge = "uuuuuuuld";
	String scarlettToDiningRoom = "uuuuuuuluuu";
	String scarlettToHall = "uuuuuuuurrrrd";
	String scarlettToStudy = "uuuuuuuurrrrrrrrrdddrd";
	String scarlettToLibrary = "uuuuuuuurrrrrrrrrur";
	String scarlettToBR = "uuuuuuuuruuuuuuuuuuru";
	String scarlettToKitchen = "uuuuuuuuruuuuuuuuuulllluu";
	String scarlettToConsevatory = "uuuuuuuuruuuuuuuuuurrrrrrrrruuulu";
	String scarlettToBillardRoom = "uuuuuuuurrrrrrrrruuuurrrrrru";

	public Bot3(Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
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

		System.out.println(player.getToken().getName());

		if(player.getToken().getName().equals("Mustard")){
			if(target.equals("Lounge")) {
				System.out.println("ML");
				char toReturn =  mustardToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("Scarlett")) {
			if(target.equals("Lounge")) {
				System.out.println("SL");
				char toReturn = scarlettToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("White")) {
			if(target.equals("Lounge")) {
				System.out.println("WL");
				char toReturn = whiteToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("Green")) {
			if(target.equals("Lounge")) {
				System.out.println("GL");
				char toReturn =  greenToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("Plum")) {
			if(target.equals("Lounge")) {
				System.out.println("PL");
				char toReturn =  plumToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("Peacock")) {
			if(target.equals("Lounge")) {
				System.out.println("PeaL");
				char toReturn =  peacockToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}
		
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