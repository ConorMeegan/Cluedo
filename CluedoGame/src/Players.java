import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Players extends GameObject{

	int playerId;
	int door;
	int startingRoll;
	int duplicateRoll;
	String name;
	String playerName;
	boolean active = false;
	ArrayList<Integer> cards = new ArrayList<Integer>();
	
	public Players(int playerId,BufferedImage icon, int x, int y) {
		super(icon,x,y);
		this.playerId = playerId;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPlayerName(String name) {
		this.playerName = name;
	}
	
	public void setDoor(int door) {
		this.door = door;
	}
	
	public int getDoor() {
		return door;
	}
	
	public void setstartingRoll(int startingRoll) {
		this.startingRoll = startingRoll;
	}
	
	public int getstartingRoll(){
		return startingRoll;
	}
	
	public void setduplicateRoll(int duplicateRoll) {
		this.duplicateRoll = duplicateRoll;
	}
	
	public int getduplicateRoll(){
		return duplicateRoll;
	}
	
	public boolean getactive() {
		return active;
	}
	
	public int getCards(int num) {
		return cards.indexOf(num);
	}
	
	public void addCards(int num) {
		 cards.add(num);
	}
}
