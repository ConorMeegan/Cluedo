import java.awt.image.BufferedImage;

public class Cards extends GameObject{
	
	int cardId;
	int choosenId;
	
	public Cards(int cardId,BufferedImage icon, int x, int y) {
		super(icon,x,y);
		this.cardId = cardId;
	}
	
	public int getCardId() {
		return cardId;
	}
	
	public void setChoosenId(int choosenId) {
		this.choosenId = choosenId;
	}
	
	public int getChoosenId() {
		return choosenId;
	}

}
