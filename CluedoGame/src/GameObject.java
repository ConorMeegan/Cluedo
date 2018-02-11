
public class GameObject {
	
	//every sprite in the game is a GameObject
	//variables to be used
	int playerId;
	boolean Active = false;
	int x,y;  //co-ordinates (x, y)
	
	//constructor
	public GameObject(int playerId, boolean Active, int x, int y)
	{
		this.playerId = playerId;
		this.Active = Active;
		this.x = x;
		this.y = y;
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
	
	//mutators for the x and y co-ordinates
	public void setx(int xValue)
	{
		x = xValue;
	}
	
	public void sety(int yValue)
	{
		y = yValue;
	}

}
