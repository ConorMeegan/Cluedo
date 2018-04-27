package bots;

import java.util.ArrayList;
import java.util.Random;

import gameengine.*;


/*
 * Sherlock bot by Mason Smith, Conor Meegan, Sam Bates
 * Student Numbers:
 * 			Mason Smith:16312606
 * 			Conor Meegan:16347531
 * 			Sam Bates:16391673
*/
public class Sherlock implements BotAPI {

	int everyThingReady = 1;
	ArrayList<String> PlayerCards = new ArrayList<String>();
	ArrayList<String> WeaponCards = new ArrayList<String>();
	ArrayList<String> RoomCards = new ArrayList<String>();
	ArrayList<String> AllCards = new ArrayList<String>();

	
	int lastIndex = 0;
	int cardsEach;
	Boolean accuseNumber = false;
	
	String pathToTravel;
	
	String suspectRoom = "";
	String suspectPlayer = "";
	String suspectWeapon = "";
	
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
	
	int size = 0;
	
	private Boolean release = false;
	
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
	
	String kitchenToBallRoom = "drrruur"; //done
	String ballroomToConservatory = "ldddrrrrrrrrrruuuru"; // done
	String conservatoryToBilliardRoom = "dddlddr";  //done
	String billardroomToLibrary = "ldddddlddr"; // done
	String libraryToStudy = "lddddrd";  //done
	String studyToHall = "luuululllld";  //done
	String hallToLounge = "ullllldd"; //done
	String loungeToDiningRoom = "uuuu"; //done
	String diningRoomToKitchen = "drruuuuuuullluluu";  //done
	
	//pathToAccusations
	String conservatoryToAccusation = "dldddddddddldddllllu";
	String ballroomToAccusation = "lddddrddddddddrrrru";
	String kitchenToAccusation = "ddrdrrrddddddddrrrru";
	String diningRoomToAccusation = "ddrrrrrru";
	String loungeToAccusation = "uurrrrrru";
	String hallToAccusation = "uru";
	String studyToAccusation = "uuullulllu";
	String libraryToAccusation = "lldlllu";
	String billiardRoomToAccusation = "lllddddddddlllu";
	
	
	private int turnsDone = 0;

	private String room = "";
	private String target = "Dining Room";
	private String lastCommand = "roll";
	private int routeIndex = 0;
	//private String[] masterRoute = new String[] {"lddddrddddddddrrrru"};
	
	private String[] masterRoute = new String[] {"drruuuuuuullluluu","drrruur","ldddrrrrrrrrrruuuru","dddlddr","ldddddlddr","lddddrd",
			"luuululllld","ullllldd","uuuu","ddrrrrrru"};
	
	private String[] masterRouteToAccuse = new String[] {"ddrrrrrru","ddrdrrrddddddddrrrru","lddddrddddddddrrrru","dldddddddddldddllllu","lllddddddddlllu",
			"lldlllu",
			"uuullulllu","uru","uurrrrrru"};

	/*
	diningRoomToKitchen,
	kitchenToBallRoom, ballroomToConservatory, conservatoryToBilliardRoom,
	billardroomToLibrary, libraryToStudy, studyToHall, hallToLounge, loungeToDiningRoom];
	 */
	private int sizeSeen = 0;
	
	private String[] masterRouteNames = new String[] {"Dining Room","Kitchen","Ballroom","Conservatory","Billiard Room","Library",
			"Study","Hall","Lounge","Accusation"};
	
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
		if(room.equals("Cellar") && player.getToken().isInRoom()){
			return "accuse";
		}
		if(lastCommand.equals("roll") && !(player.getToken().isInRoom())){
			
			lastCommand = "done";
			return "done";
		}
		if(lastCommand.equals("question") && turnsDone == dice.getTotal()){
			
			release = false;
			lastCommand = "done";
			return "done";
		}
		if(lastCommand.equals("roll") && player.getToken().isInRoom()){
			
			lastCommand = "question";
			turnsDone = dice.getTotal();
			return "question";
		}
		if(lastCommand.equals("done") && player.getToken().isInRoom()){
			release = true;
			
			if(!(masterRouteNames[routeIndex].equals(player.getToken().getRoom().toString()))) {
				for(int i=0;i<masterRouteNames.length;i++) {
					if(masterRouteNames[i].equals(player.getToken().getRoom().toString())) {
						routeIndex = i;
					}
				}
			}
			removeViewedCards(PlayerCards);
			removeViewedCards(WeaponCards);
			removeViewedCards(RoomCards);
			
			if(RoomCards.size() <= 2 && PlayerCards.size() <=2 && WeaponCards.size() <= 2) {
				switch(player.getToken().getRoom().toString()) {
					
					case "Dining Room":
						System.out.println("Dining to accuse");
						pathToTravel = masterRouteToAccuse[0];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					case "Kitchen":
						System.out.println("Kitchen to accuse");
						pathToTravel = masterRouteToAccuse[1];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					case "Ballroom":
						System.out.println("Ballroom to accuse");
						pathToTravel = masterRouteToAccuse[2];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					case "Conservatory":
						System.out.println("Conservatory to accuse");
						pathToTravel = masterRouteToAccuse[3];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					case "Billiard Room":
						System.out.println("Billiard Room to accuse");
						pathToTravel = masterRouteToAccuse[4];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					case "Library":
						System.out.println("Library to accuse");
						pathToTravel = masterRouteToAccuse[5];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					case "Study":
						System.out.println("Study to accuse");
						pathToTravel = masterRouteToAccuse[6];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					case "Hall":
						System.out.println("Hall to accuse");
						pathToTravel = masterRouteToAccuse[7];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					case "Lounge":
						System.out.println("Lounge to accuse");
						pathToTravel = masterRouteToAccuse[8];
						lastIndex = 0;
						room = "Cellar";
						return "roll";
					
				}
			}
			pathToTravel = masterRoute[routeIndex];
			//routeIndex++;
			lastIndex = 0;
			lastCommand = "roll";
			return "roll";
		}
		else{
			lastCommand ="roll";
			return "roll";
		}

	}

	public String getMove() {

		if(everyThingReady == 1) {
			PlayerCards.add("Plum");
			PlayerCards.add("White");
			PlayerCards.add("Scarlett");
			PlayerCards.add("Mustard");
			PlayerCards.add("Peacock");
			PlayerCards.add("Green");	
			
			WeaponCards.add("Rope");
			WeaponCards.add("Dagger");
			WeaponCards.add("Wrench");
			WeaponCards.add("Pistol");
			WeaponCards.add("Candlestick");
			WeaponCards.add("Lead Pipe");
			
			RoomCards.add("Kitchen");
			RoomCards.add("Ballroom");
			RoomCards.add("Conservatory");
			RoomCards.add("Billiard Room");
			RoomCards.add("Library");
			RoomCards.add("Study");
			RoomCards.add("Hall");
			RoomCards.add("Lounge");
			RoomCards.add("Dining Room");
			
			
			//Allcards
			AllCards.add("Plum");
			AllCards.add("White");
			AllCards.add("Scarlett");
			AllCards.add("Mustard");
			AllCards.add("Peacock");
			AllCards.add("Green");	
			AllCards.add("Rope");
			AllCards.add("Dagger");
			AllCards.add("Wrench");
			AllCards.add("Pistol");
			AllCards.add("Candlestick");
			AllCards.add("Lead Pipe");
			AllCards.add("Kitchen");
			AllCards.add("Ballroom");
			AllCards.add("Conservatory");
			AllCards.add("Billiard Room");
			AllCards.add("Library");
			AllCards.add("Study");
			AllCards.add("Hall");
			AllCards.add("Lounge");
			AllCards.add("Dining Room");
			
			removeSharedCards(PlayerCards);
			removeSharedCards(WeaponCards);
			removeSharedCards(RoomCards);
			
			removeOwnCards(PlayerCards);
			removeOwnCards(WeaponCards);
			removeOwnCards(RoomCards);
			
			everyThingReady = 0;
		}
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
			}else if(target.equals("Ballroom")) {
				
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
			}else if(target.equals("Ballroom")) {
				
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
			}else if(target.equals("Ballroom")) {
				
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
			}else if(target.equals("Ballroom")) {
				
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
			}else if(target.equals("Ballroom")) {
				
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
			}else if(target.equals("Ballroom")) {
				
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
		if(PlayerCards.size() == 1){
			System.out.println("Accuse Player:" + PlayerCards.get(0));
			suspectPlayer = PlayerCards.get(0);
			return PlayerCards.get(0);
		}else {
			Random rand = new Random();
			int number = rand.nextInt(PlayerCards.size());
			suspectPlayer = PlayerCards.get(number);
			return PlayerCards.get(number);
		}
	}

	public String getWeapon() {
		if(WeaponCards.size() == 1){
			System.out.println("Accuse Weapon:" + WeaponCards.get(0));
			suspectWeapon = WeaponCards.get(0);
			suspectRoom = player.getToken().getRoom().toString();
			return WeaponCards.get(0);
		}else {
			Random rand = new Random();
			int number = rand.nextInt(WeaponCards.size());
			suspectPlayer = WeaponCards.get(number);
			suspectRoom = player.getToken().getRoom().toString();
			return WeaponCards.get(number);
		}
	}

	public String getRoom() {
		System.out.println("Accuse Room:" + RoomCards.get(0));
		return RoomCards.get(0);
	}

	public String getDoor() {
		return "1";
	}

	public String getCard(Cards matchingCards) {
		

		return matchingCards.get().toString();
	}

	public void notifyResponse(Log response) {
		// Add your code here

	}
	
	private void removeSharedCards(ArrayList<String> arrayL) {
		for(int i=0;i<arrayL.size();i++) {
			if(deck.isSharedCard(arrayL.get(i))) {
				arrayL.remove(i);
			}
		}
	}
	
	private void removeOwnCards(ArrayList<String> arrayL) {
		for(int i=0;i<arrayL.size();i++) {
			if(player.getCards().contains(arrayL.get(i))) {
				arrayL.remove(i);
			}
		}
	}
	
	private void removeViewedCards(ArrayList<String> arrayL) {
		for(int i=0;i<AllCards.size();i++) {
			if(player.hasSeen(AllCards.get(i))) {
				if(arrayL.contains(AllCards.get(i))){
					arrayL.remove(AllCards.get(i));
					System.out.println(player.getToken().getName());
					System.out.println("Weapons:" + WeaponCards.toString());
					System.out.println("Rooms:" + RoomCards.toString());
					System.out.println("Players:" + PlayerCards.toString());
					size++;
					
				}
			}
		}
		
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyPlayerName(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTurnOver(String playerName, String position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyQuery(String playerName, String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyReply(String playerName, boolean cardShown) {
		// TODO Auto-generated method stub
		
	}
	
}