
public class Moving {
	
	int total;
	
	public Moving()
	{
		DiceRoll roll = new DiceRoll();
		total = 0;
		
	}
	
	
	public int moveUp(int y)
	{
		
		return y+1;
	}
	
	public int moveDown(int y)
	{
		
		return y-1;
	}
	
	public int moveLeft(int x)
	{
		
		return x-1;
	}
	
	public int moveRight(int x)
	{
		
		return x+1;
	}
	
	public void setTotal(int total) 
	{
		this.total = total;
	}

}
