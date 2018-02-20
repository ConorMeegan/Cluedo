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
	
	ImageIcon icon1;
	ImageIcon icon2;
	ImageIcon icon3;
	ImageIcon icon4;
	ImageIcon icon5;
	ImageIcon icon6;
	
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
		
		
		
		icon1 = new ImageIcon(GameStart.class.getResource("homerCard.png"));
		icon2 = new ImageIcon(GameStart.class.getResource("maggieCardTest.png"));
		icon3 = new ImageIcon(GameStart.class.getResource("moeCardTest.png"));
		icon4 = new ImageIcon(GameStart.class.getResource("hanzMoleManCard.png"));
		icon5 = new ImageIcon(GameStart.class.getResource("fatTonyCardTest.png"));
		icon6 = new ImageIcon(GameStart.class.getResource("carzyCatLady.png"));
		
		String player = "";
		
		while(numberPlayer>0)
		{
			player = (String) JOptionPane.showInputDialog(null, icon1, "Character Selection",
					JOptionPane.QUESTION_MESSAGE, null, playerNames, playerNames[0]);
			
			for(int i=0; i<playerNames.length; i++)
			{
				if(playerNames[i].equals(player))
				{
					updateLabel(player, i);
				}
			}
			
			numberPlayer--;
		}
		
		
	}
	
	public void updateLabel(String playerName, int index)
	{
		if(index == 0)
		{
			playerName = "homerCard";
			System.out.println("\nYou have selected Homer Simpson as your character.");
		}
		else if(index == 1)
		{
			playerName = "maggieCardTest";
			System.out.println("\nYou have selected Maggie Simpson as your character.");
		}
		else if(index == 2)
		{
			playerName = "moeCardTest";
			System.out.println("\nYou have selected Moe Szyslack as your character.");
		}
		else if(index == 3)
		{
			playerName = "hanzMoleManCard";
			System.out.println("\nYou have selected Hanz Moleman as your character.");
		}
		else if(index == 4)
		{
			playerName = "fatTonyCardTest";
			System.out.println("\nYou have selected Fat Tony as your character.");
		}
		else if(index == 5)
		{
			playerName = "carzyCatLady";
			System.out.println("\nYou have selected Crazy Cat Lady as your character.");
		}
		
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
