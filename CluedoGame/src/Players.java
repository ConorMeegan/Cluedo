import java.awt.image.BufferedImage;

public class Players extends GameObject{

	int playerId;
	int door;
	
	public Players(int playerId,BufferedImage icon, int x, int y) {
		super(icon,x,y);
		this.playerId = playerId;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public void setDoor(int door) {
		this.door = door;
	}
	
	public int getDoor() {
		return door;
	}
}
