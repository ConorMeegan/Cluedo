package bots;

import gameengine.*;

public class Bot2 implements BotAPI {

	private int turnsDone = 0;
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

    public Bot2 (Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
        this.player = player;
        this.playersInfo = playersInfo;
        this.map = map;
        this.dice = dice;
        this.log = log;
        this.deck = deck;
    }

    public String getName() {
        return "Bot2"; // must match the class name
    }

    public String getCommand() {
    	if(turnsDone == dice.getTotal()) {
			turnsDone = 0;
			return "done";
		}

        return "done";
    }

    public String getMove() {
    	if(map.isCorridor(new Coordinates(player.getToken().getPosition().getCol(),player.getToken().getPosition().getRow()-1))) {
			turnsDone += 1;
			return "u";
		}else if(map.isCorridor(new Coordinates(player.getToken().getPosition().getCol()+1,player.getToken().getPosition().getRow()))) {
			turnsDone += 1;
			return "r";
		}else if(map.isCorridor(new Coordinates(player.getToken().getPosition().getCol()-1,player.getToken().getPosition().getRow()))) {
			turnsDone += 1;
			return "l";
		}
		turnsDone += 1;
		return "d";
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
        return "1";
    }

    public String getCard(Cards matchingCards) {
        // Add your code here
        return matchingCards.get().toString();
    }

    public void notifyResponse(Log response) {
        // Add your code here
    }
}
