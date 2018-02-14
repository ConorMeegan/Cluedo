import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class PlayerInput extends JPanel implements ActionListener {
	private static JTextField textField;
    private static JTextArea textArea;
  
    GameMechanics mech;
    String string = null;

    
    public PlayerInput(GameMechanics mech) {
        super(new GridBagLayout());
 
        this.mech = mech;
        textField = new JTextField(20);
        textField.addActionListener(this);
 
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);//adds a scroll bar on jtextarea
 
       
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField, gbc);
    }
 
    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        text = text.toLowerCase();
       
     
        weaponMove(text);
        
      	playerMove(text);
    	
      	
        textField.selectAll();
 
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    
    public void weaponMove(String text){
    	if(text.equals("burns mansion")){
        	
    		textArea.append("burns mansion" + "\n");
    		string = "burnsMansion";
    		sendString(mech);
    		textArea.append("moved weapon to burns mansion" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    		}
    	
    	else if(text.equals("comic book store")){
    	
    		textArea.append("comic book store" + "\n");
    		string = "comicBookStore";
    		sendString(mech);
    		textArea.append("moved weapon to comic book store" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    	}
    	
    	else if(text.equals("kwik-e-mart")){
    	
    		textArea.append("kwik-e-mart" + "\n");
    		string = "kwikEMart";
    		sendString(mech);
    		textArea.append("moved weapon to kwik-e-mart" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    	}
    	
    	else if(text.equals("school")){
    	
    		textArea.append("school" + "\n");
    		string = "school";
    		sendString(mech);
    		textArea.append("moved weapon to school" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    	}
    	
    	else if(text.equals("flanders house")){
    	
    		textArea.append("flanders house" + "\n");
    		string = "flandersHouse";
    		sendString(mech);
    		textArea.append("moved weapon to flanders house" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    	}
    	
    	else if(text.equals("simpsons house")){
    	
    		textArea.append("simpsons house" + "\n");
    		string = "simpsonsHouse";
    		sendString(mech);
    		textArea.append("moved weapon to simpsons house" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    	}
    	
    	else if(text.equals("frying dutchman")){
    	
    		textArea.append("frying dutchman" + "\n");
    		string = "fryingDutchman";
    		sendString(mech);
    		textArea.append("moved weapon to frying dutchman" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    	}
    	else if(text.equals("krusty burger")){
    	
    		textArea.append("krusty burger" + "\n");
    		string = "krustyBurger";
    		sendString(mech);
    		textArea.append("moved weapon to krusty burger" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    	}
    	else if(text.equals("moes tavern")){
    	
    		textArea.append("moes tavern" + "\n");
    		string = "moesTavern";
    		sendString(mech);
    		textArea.append("moved weapon to moes tavern" + "\n");
    		textArea.append("move player by pressing (u, d, l ,r)" + "\n");
    	}
    	
    }
    
    public void playerMove(String text){
    	if(text.equals("u")){
    		textArea.append("Moved player up" + "\n");
    		
    		string = "u";
    		sendString(mech);
           
    	}
    
    	else if(text.equals("d")){
    		textArea.append("Moved player down" + "\n");
    		
    		string = "d";
    		sendString(mech);
           
    	}
    	
    	else if(text.equals("r")){
    		textArea.append("Moved player right" + "\n");
    		
    		string = "r";
    		sendString(mech);
          
    	}
    	else if(text.equals("l")){
    		textArea.append("Moved player left" + "\n");
    	
    		string = "l";
    		sendString(mech);
           
    	}
    	
    }
    
    public void sendString(GameMechanics mech){
    	mech.setInput(string);
    	string = "works";
    }
    public void setString(){
    	string = "works";
    }
    
    
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PlayerInput");
        
        //setting size of the player input panel
        frame.setPreferredSize(new Dimension(400, 710));
        
        //setting the player input screen to be beside the board
        frame.setLocation(853, 0);  //set y co-ordinate to be 568 if you want it to appear at the bottom
        
        //Add contents to the window.
        frame.add(new PlayerInput(mech));
       
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea.append("Move weapon to room" + "\n");
    }
         
    
}