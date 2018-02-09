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
        JScrollPane scrollPane = new JScrollPane(textArea);
 
       
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
       
             
        if(text.equals("burns mansion")){
        	
        	string = "burnsMansion";
    		sendString(mech);
        }
        else if(text.equals("comic book store")){
        	
        	string = "comicBookStore";
    		sendString(mech);
        }
        else if(text.equals("kwik-e-mart")){
        	
        	string = "kwikEMart";
    		sendString(mech);
        }
        else if(text.equals("school")){
        	
        	string = "school";
    		sendString(mech);
        }
        else if(text.equals("flanders house")){
        	
        	string = "flandersHouse";
    		sendString(mech);
        }
        else if(text.equals("simpsons house")){
        	
        	string = "simpsonsHouse";
    		sendString(mech);
        }
        else if(text.equals("frying dutchman")){
        	
        	string = "fryingDutchman";
    		sendString(mech);
        }
        else if(text.equals("krusty burger")){
        	
        	string = "krustyBurger";
    		sendString(mech);
        }
        else if(text.equals("moes tavern")){
        	
        	string = "moesTavern";
    		sendString(mech);
        }
       
        	if(text.equals("u")){
        		textArea.append("up" + "\n");
        		
        		string = "u";
        		sendString(mech);
               
        	}
        
        	else if(text.equals("d")){
        		textArea.append("down" + "\n");
        		
        		string = "d";
        		sendString(mech);
               
        	}
        	
        	else if(text.equals("r")){
        		textArea.append("right" + "\n");
        		
        		string = "r";
        		sendString(mech);
              
        	}
        	else if(text.equals("l")){
        		textArea.append("left" + "\n");
        	
        		string = "l";
        		sendString(mech);
               
        	}
        	else{
        		textArea.append(text + "\n");
        	}
     
        textField.selectAll();
 
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
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
        textArea.append("move weapon to ______");
    }
         
    
}