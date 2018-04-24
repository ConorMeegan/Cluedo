package bots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import gameengine.*;

public class Sherlock implements BotAPI {

	String[] allCards = {"Plum", "White", "Scarlett", "Green", "Mustard", "Peacock"
			,"Rope", "Dagger", "Wrench", "Pistol", "Candlestick", "Lead Pipe","Kitchen", "Ballroom", "Conservatory", "Billiard Room", "Library",
            "Study", "Hall", "Lounge", "Dining Room", "Cellar"};
	ArrayList<String> AllCards = new ArrayList<String>();
	
	ArrayList<String> knownPlayers = new ArrayList<>();
	ArrayList<String> suspectPlayers = new ArrayList<>();
	
	ArrayList<String> knownWeapons = new ArrayList<>();
	ArrayList<String> suspectWeapons = new ArrayList<>();
	
	ArrayList<String> knownRooms = new ArrayList<>();
	ArrayList<String> suspectRooms = new ArrayList<>();
	
	int lastIndex = 0;
	int cardsEach;
	
	
	private int turnsDone = 0;

	private String target = "Lounge";
	
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
	String peacockToBilliardRoom = "lllllldddr";
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
	String plumToHall = "lllllllldl";
	String plumToLounge = "lllllllluullllllllldd";
	String plumToDiningRoom = "lllllllluullllllllluu";
	String plumToLibrary = "llllllluuur";
	String plumToBilliardRoom = "llllllluuuuuuuuuurr";
	String plumToConservatory = "llllllluuuuuuuuuuuuuurru";
	String plumToBallRoom = "llllllluuuuuuuuuuuuuul";
	String plumToKitchen = "lllllllluullllllluuuuuuuuulllluu";
	
	String whiteToBallRoom = "dllddddr";
	String whiteToKitchen= "dllddddddlllu";
	String whiteToConservatory = "dlldddddddrrrrrrrrrruuuru";
	String whiteToDiningRoom = "dllddddddddrdddl";
	String whiteToBilliardRoom = "dllddddddddrrrrrrrrrrr";
	String whiteToLibrary = "dllddddddddrrrrrrrrddddrrrrrd";
	String whiteToLounge = "dllddddddddrdddddddddlld";
	String whiteToHall = "dllddddddddrddddddddrrrd";
	String whiteToStudy= "dlldddddddrrrrrrrrrddddddddddddrd";
	
	//Green
	String greenToBallRoom = "drrddddl";
	String greenToConservatory = "drrdrdddru";
	String greenToBilliardRoom = "drrddddddddrr";
	String greenToLibrary = "drrdddddddddddddddr";
	String greenToStudy = "drrdddddddddddddddddddrd";
	String greenToHall = "drrdddddddddddddddddddll";
	String greenToKitchen = "drrddddddddllllllllllluluu";
	String greenToDiningRoom = "drrddddddddlllllllldddl";
	String greenToLounge = "drrddddddddlllllllldddddddddlld";
	
	//Scarlet
	String scarlettToLounge = "uuuuuuld";
	String scarlettToDiningRoom = "uuuuuuuluuu";
	String scarlettToHall = "uuuuuuurrrrd";
	String scarlettToStudy = "uuuuuuurrrrrrrrrdddrd";
	String scarlettToLibrary = "uuuuuuurrrrrrrrrur";
	String scarlettToBallRoom = "uuuuuuuuruuuuuuuuuuru";
	String scarlettToKitchen = "uuuuuuuuruuuuuuuulllluu";
	String scarlettToConservatory = "uuuuuuuuruuuuuuuurrrrrrrrruuuru";
	String scarlettToBilliardRoom = "uuuuuuurrrrrrrrruuuuuuuurr";

	public Sherlock(Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
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

		
		
		

		if(player.getToken().getName().equals("Mustard")){
			if(target.equals("Lounge")) {
				
				char toReturn =  mustardToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Dining Room")) {
				
				char toReturn =  mustardToDiningRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Library")) {
				
				char toReturn =  mustardToLibrary.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Ball Room")) {
				
				char toReturn =  mustardToBallRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Conservatory")) {
				
				char toReturn =  mustardToConservatory.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Billiard Room")) {
				
				char toReturn =  mustardToBilliardRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Study")) {
				
				char toReturn =  mustardToStudy.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Hall")) {
				
				char toReturn =  mustardToHall.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Kitchen")) {
				
				char toReturn =  mustardToKitchen.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("Scarlett")) {
			if(target.equals("Lounge")) {
				
				char toReturn =  scarlettToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Dining Room")) {
				
				char toReturn =  scarlettToDiningRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Library")) {
				
				char toReturn =  scarlettToLibrary.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Ball Room")) {
				
				char toReturn =  scarlettToBallRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Conservatory")) {
				
				char toReturn =  scarlettToConservatory.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Billiard Room")) {
				
				char toReturn =  scarlettToBilliardRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Study")) {
				
				char toReturn =  scarlettToStudy.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Hall")) {
				
				char toReturn =  scarlettToHall.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Kitchen")) {
				
				char toReturn =  scarlettToKitchen.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("White")) {
			if(target.equals("Lounge")) {
				
				char toReturn =  whiteToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Dining Room")) {
				
				char toReturn =  whiteToDiningRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Library")) {
				
				char toReturn =  whiteToLibrary.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Ball Room")) {
				
				char toReturn =  whiteToBallRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Conservatory")) {
				
				char toReturn =  whiteToConservatory.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Billiard Room")) {
				
				char toReturn =  whiteToBilliardRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Study")) {
				
				char toReturn =  whiteToStudy.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Hall")) {
				
				char toReturn =  whiteToHall.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Kitchen")) {
				
				char toReturn =  whiteToKitchen.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("Green")) {
			if(target.equals("Lounge")) {
				
				char toReturn =  greenToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Dining Room")) {
				
				char toReturn =  greenToDiningRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Library")) {
				
				char toReturn =  greenToLibrary.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Ball Room")) {
				
				char toReturn =  greenToBallRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Conservatory")) {
				
				char toReturn =  greenToConservatory.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Billiard Room")) {
				
				char toReturn =  greenToBilliardRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Study")) {
				
				char toReturn =  greenToStudy.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Hall")) {
				
				char toReturn =  greenToHall.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Kitchen")) {
				
				char toReturn =  greenToKitchen.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("Plum")) {
			if(target.equals("Lounge")) {
				
				char toReturn =  plumToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Dining Room")) {
				
				char toReturn =  plumToDiningRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Library")) {
				
				char toReturn =  plumToLibrary.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Ball Room")) {
				
				char toReturn =  plumToBallRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Conservatory")) {
				
				char toReturn =  plumToConservatory.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Billiard Room")) {
				
				char toReturn =  plumToBilliardRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Study")) {
				
				char toReturn =  plumToStudy.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Hall")) {
				
				char toReturn =  plumToHall.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Kitchen")) {
				
				char toReturn =  plumToKitchen.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}
		}else if(player.getToken().getName().equals("Peacock")) {
			if(target.equals("Lounge")) {
				
				char toReturn =  peacockToLounge.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Dining Room")) {
				
				char toReturn =  peacockToDiningRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Library")) {
				
				char toReturn =  peacockToLibrary.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Ball Room")) {
				
				char toReturn =  peacockToBallRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Conservatory")) {
				
				char toReturn =  peacockToConservatory.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Billiard Room")) {
				
				char toReturn =  peacockToBilliardRoom.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Study")) {
				
				char toReturn =  peacockToStudy.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Hall")) {
				
				char toReturn =  peacockToHall.charAt(lastIndex);
				lastIndex += 1;
				turnsDone += 1;
				return Character.toString(toReturn);
			}else if(target.equals("Kitchen")) {
				
				char toReturn =  peacockToKitchen.charAt(lastIndex);
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
		//System.out.println(player.getCards());
		//System.out.println(knownPlayers);
		//System.out.println(suspectPlayers);


		return suspectPlayers.get(1);
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
		//System.out.println(player.getCards());
		//System.out.println(knownWeapons);
		//System.out.println(suspectWeapons);

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
		//System.out.println(player.getCards());
		//System.out.println(knownRooms);
		//System.out.println(suspectRooms);

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
	
	public void Initialize() {
		
		for(int i =0;i<AllCards.size();i++) {
			AllCards.add(allCards[i]);
			System.out.println(allCards[i]);
		}
		System.out.println(AllCards.size());
		cardsEach = 18/playersInfo.numPlayers();
		
		
			//System.out.println(player.getCards().contains("Ball Room"));
			
		
		if(cardsEach > 0) {
			
		}
	}
}