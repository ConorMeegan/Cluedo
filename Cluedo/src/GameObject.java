import java.awt.image.BufferedImage;

public class GameObject {
	
	//variables to be used
	int playerId;
	boolean Active = false;
	int x,y;  //co-ordinates (x, y)
	BufferedImage representation;
	
	//constructor
	public GameObject(int playerId, boolean Active, int x, int y, BufferedImage representation)
	{
		this.playerId = playerId;
		this.Active = Active;
		this.x = x;
		this.y = y;
		this.representation = representation;
	}
	
	//accessors
	public int getplayerId()
	{
		return playerId;
	}
	
	public boolean getActive()
	{
		return Active;
	}
	
	public int getx()
	{
		return x;
	}
	
	public int gety()
	{
		return y;
	}
	
	public BufferedImage getrepresentation()
	{
		return representation;
	}
}
