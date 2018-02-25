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
	Players[] names;
	
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
		
		
		/*
		for(int x=0; x<numberPlayer; x++)
		{
			names[x].setName("");
		}
		*/
		
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Homer Simpson");
		playerNames.add("Maggie Simpson");
		playerNames.add("Moe Syzlack");
		playerNames.add("Hanz Moleman");
		playerNames.add("Fat Tony");
		playerNames.add("Crazy Cat Lady");
		
		
		/*
		icon1 = new ImageIcon(GameStart.class.getResource("homerCard.png"));
		icon2 = new ImageIcon(GameStart.class.getResource("maggieCardTest.png"));
		icon3 = new ImageIcon(GameStart.class.getResource("moeCardTest.png"));
		icon4 = new ImageIcon(GameStart.class.getResource("hanzMoleManCard.png"));
		icon5 = new ImageIcon(GameStart.class.getResource("fatTonyCardTest.png"));
		icon6 = new ImageIcon(GameStart.class.getResource("carzyCatLady.png"));
		*/
		String player = "";
		int j = 4;
		int PlayerNumber = 1;
		
		while(numberPlayer>0)
		{
			player = (String) JOptionPane.showInputDialog(null, new ImageIcon(image.getImage(j, "cards")),
							"Character Selection for Player "+PlayerNumber, JOptionPane.QUESTION_MESSAGE, 
							null, playerNames.toArray(), playerNames.get(0));
			
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
			System.out.println("\nYou have selected Homer Simpson as your character.");
			return 4;
		}
		else if(playerName.equals("Maggie Simpson"))
		{
			playerName = "maggieCardTest";
			System.out.println("\nYou have selected Maggie Simpson as your character.");
			return 5;
		}
		else if(playerName.equals("Moe Syzlack"))
		{
			playerName = "moeCardTest";
			System.out.println("\nYou have selected Moe Szyslack as your character.");
			return 6;
		}
		else if(playerName.equals("Hanz Moleman"))
		{
			playerName = "hanzMoleManCard";
			System.out.println("\nYou have selected Hanz Moleman as your character.");
			return 3;
		}
		else if(playerName.equals("Fat Tony"))
		{
			playerName = "fatTonyCardTest";
			System.out.println("\nYou have selected Fat Tony as your character.");
			return 2;
		}
		else if(playerName.equals("Crazy Cat Lady"))
		{
			playerName = "carzyCatLady";
			System.out.println("\nYou have selected Crazy Cat Lady as your character.");
			
			return 1;
		}
		return 1;
	}
	/*
	public int index()
	{
		
	}
	*/
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
