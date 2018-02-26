import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.soap.Text;

public class GameStart {

	ImageIcon[] icons = new ImageIcon[6];
	Images image = new Images();
	
	Players[] arr = new Players[6];
	String[] names = new String[6];
	GameMechanics mech;
	
	public GameStart(GameMechanics mech)
	{
		this.mech = mech;

		String[] numberOfPlayers = {"Two", "Three", "Four", "Five", "Six"};
		
		String number = "";
		
		//getting the number of players that will be playing between 2-6
		number = (String) JOptionPane.showInputDialog(null, "Players:", "Number of Players",
				JOptionPane.QUESTION_MESSAGE, null, numberOfPlayers, numberOfPlayers[0]);
		
		int numberPlayer = 0;
		
		if(number.equals("Two"))
		{
			numberPlayer = 2;
		}
		else if(number.equals("Three"))
		{
			numberPlayer = 3;
		}
		else if(number.equals("Four"))
		{
			numberPlayer = 4;
		}
		else if(number.equals("Five"))
		{
			numberPlayer = 5;
		}
		else if(number.equals("Six"))
		{
			numberPlayer = 6;
		}
		mech.setMax(numberPlayer);
		
		//getting the player's names
		for(int x=0; x<numberPlayer; x++)
		{
			String PlayerInputNames = JOptionPane.showInputDialog(null, "Player "+(x+1) +" enter your name:");
			names[x] = PlayerInputNames;
		}
		
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Homer Simpson");
		playerNames.add("Maggie Simpson");
		playerNames.add("Moe Syzlack");
		playerNames.add("Hanz Moleman");
		playerNames.add("Fat Tony");
		playerNames.add("Crazy Cat Lady");
		
		String player = "";
		int j = 4;
		int PlayerNumber = 1;
		
		//getting the players to select their characters
		while(numberPlayer>0)
		{
			player = (String) JOptionPane.showInputDialog(null, //settingIcon(j),
							"Characters",""+names[PlayerNumber-1]+"'s Character Selection for Player "+PlayerNumber, JOptionPane.QUESTION_MESSAGE, 
							null, playerNames.toArray(), playerNames.get(0));
			//change the play to the names of the people themselves
			names[PlayerNumber-1] = player;
			j = updateLabel(player);
			playerNames.remove(player);
						
			numberPlayer--;
			PlayerNumber++;
		}
		
		
	}
	
	public int updateLabel(String playerName)
	{
		if(playerName.equals("Homer Simpson"))
		{
			playerName = "homerCard";
			//System.out.println("\nYou have selected Homer Simpson as your character.");
			return 4;
		}
		else if(playerName.equals("Maggie Simpson"))
		{
			playerName = "maggieCardTest";
			//System.out.println("\nYou have selected Maggie Simpson as your character.");
			return 5;
		}
		else if(playerName.equals("Moe Syzlack"))
		{
			playerName = "moeCardTest";
			//System.out.println("\nYou have selected Moe Szyslack as your character.");
			return 6;
		}
		else if(playerName.equals("Hanz Moleman"))
		{
			playerName = "hanzMoleManCard";
			//System.out.println("\nYou have selected Hanz Moleman as your character.");
			return 3;
		}
		else if(playerName.equals("Fat Tony"))
		{
			playerName = "fatTonyCardTest";
			//System.out.println("\nYou have selected Fat Tony as your character.");
			return 2;
		}
		else if(playerName.equals("Crazy Cat Lady"))
		{
			playerName = "carzyCatLady";
			//System.out.println("\nYou have selected Crazy Cat Lady as your character.");
			
			return 1;
		}
		return 1;
	}
	
	public String getName(int i)
	{
		return names[i];
	}
	
	public ImageIcon settingIcon(int j)
	{
		return new ImageIcon(image.getImage(j, "cards"));
	}
	
}
