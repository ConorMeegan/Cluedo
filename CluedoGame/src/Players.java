import java.awt.image.BufferedImage;

public class Players extends GameObject{

	int playerId;
	
	public Players(int playerId,BufferedImage icon, int x, int y) {
		super(icon,x,y);
		this.playerId = playerId;
	}
	
	public int getPlayerId() {
		return playerId;
	}

}
