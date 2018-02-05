import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class PlayerInput extends JPanel implements ActionListener {
<<<<<<< HEAD
	public JTextField textField;
    public JTextArea textArea;
    
    GameMechanics mech;
    String string = null;
=======
	private static JTextField textField;
    private static JTextArea textArea;
>>>>>>> 784379fd34c301b80fb4d5db5dec4451f68088cd
  
    GameMechanics mech;
    String string = null;
    
    
<<<<<<< HEAD
    public PlayerInput() {
        super(new GridBagLayout());
       
=======
    public PlayerInput(GameMechanics mech) {
        super(new GridBagLayout());
 
        this.mech = mech;
>>>>>>> 784379fd34c301b80fb4d5db5dec4451f68088cd
        textField = new JTextField(20);
        textField.addActionListener(this);
 
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
       
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
 
        gbc.gridx = 672;
        gbc.weightx = 10.0;
        gbc.weighty = 10.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField, gbc);
    }
 
    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
       
        	if(text.equals("u")){
        		textArea.append("up" + "\n");
        		
<<<<<<< HEAD
        		Moving Up = new Moving();
                int PosUp = Up.moveUp(CurrY);
                
                GameObject Y1 = new GameObject(1, true, CurrX, CurrY);
                Y1.sety(PosUp);
              
=======
        		string = "up";
        		sendString(mech);
               
>>>>>>> 784379fd34c301b80fb4d5db5dec4451f68088cd
        	}
        
        	else if(text.equals("d")){
        		textArea.append("down" + "\n");
        		
<<<<<<< HEAD
        	
        		Moving Down = new Moving();
                int PosDown = Down.moveUp(CurrY);
                
                GameObject Y2 = new GameObject(1, true, CurrX, CurrY);
                Y2.sety(PosDown);
                
=======
        		string = "down";
        		sendString(mech);
>>>>>>> 784379fd34c301b80fb4d5db5dec4451f68088cd
               
        	}
        	
        	else if(text.equals("r")){
        		textArea.append("right" + "\n");
        		
<<<<<<< HEAD
        		
        		Moving Right = new Moving();
                int PosRight = Right.moveUp(CurrX);
                
                GameObject X1 = new GameObject(1, true, CurrX, CurrY);
                X1.setx(PosRight);
                
=======
        		string = "right";
        		sendString(mech);
>>>>>>> 784379fd34c301b80fb4d5db5dec4451f68088cd
              
        	}
        	else if(text.equals("l")){
        		textArea.append("left" + "\n");
        	
<<<<<<< HEAD

        		Moving Left = new Moving();
                int PosLeft = Left.moveUp(CurrX);
              
                GameObject X2 = new GameObject(1, true, CurrX, CurrY);
                X2.setx(PosLeft);
=======
        		string = "left";
        		sendString(mech);
>>>>>>> 784379fd34c301b80fb4d5db5dec4451f68088cd
               
        	}
        	else{
        		textArea.append(text + "\n");
        	}
     
        textField.selectAll();
 
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
<<<<<<< HEAD
    public void createAndShowGUI(){
=======
    public void sendString(GameMechanics mech){
    	mech.setInput(string);
    	string = "works";
    }
    public void setString(){
    	string = "works";
    }
    
    
    public void createAndShowGUI() {
>>>>>>> 784379fd34c301b80fb4d5db5dec4451f68088cd
        //Create and set up the window.
        JFrame frame = new JFrame("PlayerInput");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new PlayerInput(mech));
       
        ////Display the window.
        frame.pack();
        frame.setVisible(true);
    }
         
    
}