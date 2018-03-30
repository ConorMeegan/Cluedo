import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up, down, left, right;
	GameMechanics mech;
	CollisonTesting cTest;
	
	public KeyManager(GameMechanics mech,CollisonTesting testing)
	{
		this.mech = mech;
		cTest = testing;
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
		
		if(keys[KeyEvent.VK_UP] == true)
		{
			
			if(cTest.testMove("u", mech.getOb())){
				mech.setInput("u");
			}
		}
		else if(keys[KeyEvent.VK_DOWN] == true)
		{
			if(cTest.testMove("d", mech.getOb())){
				mech.setInput("d");
			}
		}
		else if(keys[KeyEvent.VK_LEFT] == true)
		{
			if(cTest.testMove("l", mech.getOb())){
				mech.setInput("l");
			}
		}
		else if(keys[KeyEvent.VK_RIGHT] == true)
		{
			if(cTest.testMove("r", mech.getOb())){
				mech.setInput("r");
			}
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
