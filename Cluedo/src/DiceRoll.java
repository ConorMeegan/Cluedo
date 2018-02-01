import java.util.Random;


public class DiceRoll {
	
	int dice1;
	int dice2;
	int total;
	Random rand = new Random();
	
	public DiceRoll()
	{
		dice1 = rand.nextInt((6-1)+1)+1;
		dice2 = rand.nextInt((6-1)+1)+1;
		
		System.out.println("You rolled a "+dice1+" and a "+ dice2+"!");
		total = dice1+dice2;
		System.out.println("\nYou can move "+total+" places");
	}
	
	
	public int gettotal()
	{
		return total;
		
	}
	

}