import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up, down, left, right;
	GameMechanics mech;
	
	public KeyManager(GameMechanics mech)
	{
		this.mech = mech;
		keys = new boolean[256];
	}
	
	public void tick() 
	{
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true;
		System.out.println("\nPressed");
		
		if(keys[KeyEvent.VK_UP] == true)
		{
			mech.setInput("u");
		}
		else if(keys[KeyEvent.VK_DOWN] == true)
		{
			mech.setInput("d");
		}
		else if(keys[KeyEvent.VK_LEFT] == true)
		{
			mech.setInput("l");
		}
		else if(keys[KeyEvent.VK_RIGHT] == true)
		{
			mech.setInput("r");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
