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
       
        
       
       
        	if(text.equals("u")){
        		textArea.append("up" + "\n");
        		
        		string = "up";
        		sendString(mech);
               
        	}
        
        	else if(text.equals("d")){
        		textArea.append("down" + "\n");
        		
        		string = "down";
        		sendString(mech);
               
        	}
        	
        	else if(text.equals("r")){
        		textArea.append("right" + "\n");
        		
        		string = "right";
        		sendString(mech);
              
        	}
        	else if(text.equals("l")){
        		textArea.append("left" + "\n");
        	
        		string = "left";
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new PlayerInput(mech));
       
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
         
    
}