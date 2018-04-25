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
	Boolean accuseNumber = false;
	
	String pathToTravel;
	
	private int turnsDone = 0;

	private String target = "Dining Room";
	private String lastCommand = "roll";
	private Boolean questionDone = false;
	
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
	
	private Boolean release = false;
	private Boolean enteredRoom = false;
	private Boolean leaveRoom = false;
	
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
	
	
	//room paths
	String kitchenToDiningRoom = "drrrrddddl";
	String kitchenToBallRoom = "drrruur"; //done
	String kitchenToConservatory = "drrrrrrrrrrrrruuuru";
	String kitchenToBilliardRoom = "drrrrrrrrrrrrrdr";
	String kitchenToLounge = "drrrrddddddddddlld";
	String kitchenToHall = "drrrrdddddddddrrrd";
	String kitchenToStudy = "drrrrdddddddddrrrrrrrdddrrd";
	String kitchenToLibrary = "drrrrrrrrrrrrddddddddr";

	String ballroomToConservatory = "ldddrrrrrrrrrruuuru"; // done
	String ballroomToBilliardRoom = "rrrrrrrrdr";
	String ballroomToLibrary = "rrrrrrrddddddddr";
	String ballroomToDiningRoom = "lddddl";
	String ballroomToLounge = "lddddddddddlld";
	String ballroomToHall = "ldddddddddrrrd";
	String ballroomToStudy = "ldddddddddrrrrrrrdddrrd";

	String conservatoryToBilliardRoom = "dddlddr";  //done
	String conservatoryToLibrary = "ddldddddddlddr";
	String conservatoryToStudy = "ddldddddddlddddddrd";
	String conservatoryToHall = "ddldddddddldddllllld";
	String conservatoryToLounge = "ddldddddddldddlllllllllldd";
	String conservatoryToDiningRoom = "ddlddllllllllldddl";

	String billardroomToLibrary = "ldddddlddr"; // done
	String billardroomToStudy = "dddddlddddddrd";
	String billardroomToHall = "dddddldddllllld";
	String billardroomToLounge = "dddddldddlllllllllldd";
	String billardroomToDiningRoom = "ullllllllldddl";

	String libraryToStudy = "lddddrd";  //done
	String libraryToHall = "dllllld";
	String libraryToLounge = "dlllllllllldd";
	String libraryToDiningRoom = "dlllllllluuuuul";

	String studyToHall = "luuululllld";  //done
	String studyToLounge = "uululllllllllldd";
	String studyToDiningRoom = "uululllllllluuuuul";

	String hallToLounge = "ullllldd"; //done
	String hallToDiningRoom = "llluuuuul";

	String loungeToDiningRoom = "uuuu"; //done

	String diningRoomToKitchen = "drruuuuuuullluluu";  //done

	private int routeIndex = 0;
	private String[] masterRoute = new String[] {"drruuuuuuullluluu","drrruur","ldddrrrrrrrrrruuuru","dddlddr","ldddddlddr","lddddrd",
			"luuululllld","ullllldd","uuuu","ddrrrrrru"};

	/*
	diningRoomToKitchen,
	kitchenToBallRoom, ballroomToConservatory, conservatoryToBilliardRoom,
	billardroomToLibrary, libraryToStudy, studyToHall, hallToLounge, loungeToDiningRoom];
	 */


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
		if(routeIndex == 10 && player.getToken().isInRoom()){
			return "accuse";
		}
		if(lastCommand.equals("roll") && !(player.getToken().isInRoom())){
			System.out.println("Done");
			lastCommand = "done";
			return "done";
		}
		if(lastCommand.equals("question") && turnsDone == dice.getTotal()){
			System.out.println("Done with question");
			release = false;
			lastCommand = "done";
			return "done";
		}
		if(lastCommand.equals("roll") && player.getToken().isInRoom()){
			System.out.println("asking question");
			lastCommand = "question";
			questionDone = true;
			turnsDone = dice.getTotal();
			return "question";
		}
		if(lastCommand.equals("done") && player.getToken().isInRoom()){
			System.out.println("perform exit");
			release = true;
			System.out.println(routeIndex);
			System.out.println(masterRoute.length);

			pathToTravel = masterRoute[routeIndex];
			routeIndex++;
			lastIndex = 0;
			lastCommand = "roll";
			return "roll";
		}
		else{
			System.out.println("roll not in room");
			lastCommand ="roll";
			return "roll";
		}

	}

	public String getMove() {


		if(release) {
			char toReturn = pathToTravel.charAt(lastIndex);
			lastIndex += 1;
			turnsDone += 1;
			return Character.toString(toReturn);
		}

	

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
		for(int i=0; i<6; i++){
			if(!(player.hasCard(Names.SUSPECT_NAMES[i]))) {
				suspectPlayers.add(Names.SUSPECT_NAMES[i]);
			}
		}

		if(player.hasCard("Green") || player.hasSeen("Green")){
			knownPlayers.add("Green");
			suspectPlayers.remove("Green");
		}
		if(player.hasCard("White") || player.hasSeen("White")){
			knownPlayers.add("White");
			suspectPlayers.remove("White");
		}
		if(player.hasCard("Peacock") || player.hasSeen("Peacock")){
			knownPlayers.add("Peacock");
			suspectPlayers.remove("Peacock");
		}
		if(player.hasCard("Scarlett") || player.hasSeen("Scarlett")){
			knownPlayers.add("Scarlett");
			suspectPlayers.remove("Scarlett");
		}
		if(player.hasCard("Plum") || player.hasSeen("Plum")){
			knownPlayers.add("Plum");
			suspectPlayers.remove("Plum");
		}
		if(player.hasCard("Mustard") || player.hasSeen("Mustard")){
			knownPlayers.add("Mustard");
			suspectPlayers.remove("Mustard");
		}
		/*
		for (String name : Names.WEAPON_NAMES) {
			if (player.hasCard(name)) {
				knownWeapons.add(name);
			}
		}*/

		//System.out.println(player.getCards());
		//System.out.println(knownPlayers);
		//System.out.println(suspectPlayers);


		return suspectPlayers.get(1);
	}

	public String getWeapon() {
		// Add your code here
		for(int i=0; i<6; i++){
			if(!(player.hasCard(Names.WEAPON_NAMES[i]))) {
				suspectWeapons.add(Names.WEAPON_NAMES[i]);
			}
		}
		if(player.hasCard("Pistol") || player.hasSeen("Pistol")){
			knownWeapons.add("Pistol");
			suspectWeapons.remove("Pistol");
		}
		if(player.hasCard("Rope") || player.hasSeen("Rope")){
			knownWeapons.add("Rope");
			suspectWeapons.remove("Rope");
		}
		if(player.hasCard("Dagger") || player.hasSeen("Dagger")){
			knownWeapons.add("Dagger");
			suspectWeapons.remove("Dagger");
		}
		if(player.hasCard("Wrench") || player.hasSeen("Wrench")){
			knownWeapons.add("Wrench");
			suspectWeapons.remove("Wrench");
		}
		if(player.hasCard("Candlestick") || player.hasSeen("Candlestick")){
			knownWeapons.add("Candlestick");
			suspectWeapons.remove("Candlestick");
		}
		if(player.hasCard("Lead Pipe") || player.hasSeen("Lead Pipe")){
			knownWeapons.add("Lead Pipe");
			suspectWeapons.remove("Lead Pipe");
		}
		/*
		for (String name : Names.WEAPON_NAMES) {
			if (player.hasCard(name)) {
				knownWeapons.add(name);
			}
		}*/

		//System.out.println(player.getCards());
		//System.out.println(knownWeapons);
		//System.out.println(suspectWeapons);

		return suspectWeapons.get(0);
	}

	public String getRoom() {
		// Add your code here

		for(int i=0; i<6; i++){
			if(!(player.hasCard(Names.ROOM_NAMES[i]))) {
				suspectRooms.add(Names.ROOM_NAMES[i]);
			}

		}
		if(player.hasCard("Kitchen") || player.hasSeen("Kitchen")){
			knownRooms.add("Kitchen");
			suspectRooms.remove("Kitchen");
		}
		if(player.hasCard("Ballroom") || player.hasSeen("Ballroom")){
			knownRooms.add("Ballroom");
			suspectRooms.remove("Ballroom");
		}
		if(player.hasCard("Conservatory") || player.hasSeen("Conservatory")){
			knownRooms.add("Conservatory");
			suspectRooms.remove("Conservatory");
		}
		if(player.hasCard("Billiard Room") || player.hasSeen("Billiard Room")){
			knownRooms.add("Billiard Room");
			suspectRooms.remove("Billiard Room");
		}
		if(player.hasCard("Library") || player.hasSeen("Library")){
			knownRooms.add("Library");
			suspectRooms.remove("Library");
		}
		if(player.hasCard("Study") || player.hasSeen("Study")){
			knownRooms.add("Study");
			suspectRooms.remove("Study");
		}
		if(player.hasCard("Hall") || player.hasSeen("Hall")){
			knownRooms.add("Hall");
			suspectRooms.remove("Hall");
		}
		if(player.hasCard("Lounge") || player.hasSeen("Lounge")){
			knownRooms.add("Lounge");
			suspectRooms.remove("Lounge");
		}
		if(player.hasCard("Dining Room") || player.hasSeen("Dining Room")){
			knownRooms.add("Dining Room");
			suspectRooms.remove("Dining Room");
		}
		/*
		for (String name : Names.ROOM_NAMES) {
			if (player.hasCard(name)) {
				knownRooms.add(name);
			}
		}*/

		//System.out.println(player.getCards());
		//System.out.println(knownRooms);
		//System.out.println(suspectRooms);

		return suspectRooms.get(suspectRooms.size()-1);
	}

	public String getDoor() {
		// Add your code here
		/*
		String door = "";

		if(player.getToken().isInRoom()){
			int numberOfDoors =  player.getToken().getRoom().getNumberOfDoors();
			Random rand = new Random();
			door = Integer.toString(rand.nextInt(numberOfDoors)+1);
		}
		*/
		return "1";
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
		
	
		if(cardsEach > 0) {
			
		}
	}
}