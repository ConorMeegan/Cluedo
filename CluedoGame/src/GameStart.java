import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameStart {
	
	GameObject PlayerOne;
	GameObject PlayerTwo;
	GameObject PlayerThree;
	GameObject PlayerFour;
	GameObject PlayerFive;
	GameObject PlayerSix;
	
	Images images = new Images();
	
	ImageIcon[] icons = new ImageIcon[6];
	
	
	public GameStart()
	{
		String[] numberOfPlayers = {"Two", "Three", "Four", "Five", "Six"};
		
		String number = "";
		
		number = (String) JOptionPane.showInputDialog(null, "Players:", "Number of PLayers",
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
		
		
		String[] playerNames = {"Homer Simpson", "Maggie Simpson", "Moe Szyslack",
								"Hanz Moleman",	"Fat Tony", "Crazy Cat Lady" };
		
		
		
		for(int i=0;i<6;i++) {
			icons[i] = new ImageIcon(images.getImage(i+1, "cards"));
		}
		
		String player = "";
		
		while(numberPlayer>0)
		{
			int j = 1;
			player = (String) JOptionPane.showInputDialog(null, icons[j], "Character Selection",
					JOptionPane.QUESTION_MESSAGE, null, playerNames, playerNames[0]);
			
			for(int i=0; i<playerNames.length; i++)
			{
				if(playerNames[i].equals(player))
				{
					j = updateLabel(player, i);
				}
			}
			
			numberPlayer--;
		}
	}
	
	public int updateLabel(String playerName, int index)
	{
		if(index == 0)
		{
			playerName = "homerCard";
			System.out.println("\nYou have selected Homer Simpson as your character.");
			return 1;
		}
		else if(index == 1)
		{
			playerName = "maggieCardTest";
			System.out.println("\nYou have selected Maggie Simpson as your character.");
			return 2;
		}
		else if(index == 2)
		{
			playerName = "moeCardTest";
			System.out.println("\nYou have selected Moe Szyslack as your character.");
			return 3;
		}
		else if(index == 3)
		{
			playerName = "hanzMoleManCard";
			System.out.println("\nYou have selected Hanz Moleman as your character.");
			return 4;
		}
		else if(index == 4)
		{
			playerName = "fatTonyCardTest";
			System.out.println("\nYou have selected Fat Tony as your character.");
			return 5;
		}
		else if(index == 5)
		{
			playerName = "carzyCatLady";
			System.out.println("\nYou have selected Crazy Cat Lady as your character.");
			return 6;
		}
		return 1;
	}
	
	public void itemStateChanged(ItemEvent e) 
	{
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
	        //label.setVisible(true);
	    }
		else
		{
	        //label.setVisible(false);
	    }
	}
	
}
