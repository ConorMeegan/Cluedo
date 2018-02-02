import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class PlayerInput extends JPanel implements ActionListener {
	public JTextField textField;
    public JTextArea textArea;
  
    GameObject X = new GameObject(1, true, 0, 0);
    int CurrX = X.getx();
    
    GameObject Y = new GameObject(1, true, 0, 0);
    int CurrY = Y.getx();
    
    
    public PlayerInput() {
        super(new GridBagLayout());
 
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
        		
        		Moving Up = new Moving();
                int PosUp = Up.moveUp(CurrY);
                
                GameObject Y1 = new GameObject(1, true, CurrX, CurrY);
                Y1.sety(PosUp);
               
        	}
        
        	else if(text.equals("d")){
        		textArea.append("down" + "\n");
        		
        		Moving Down = new Moving();
                int PosDown = Down.moveUp(CurrY);
                
                GameObject Y2 = new GameObject(1, true, CurrX, CurrY);
                Y2.sety(PosDown);
               
        	}
        	
        	else if(text.equals("r")){
        		textArea.append("right" + "\n");
        		
        		Moving Right = new Moving();
                int PosRight = Right.moveUp(CurrX);
                
                GameObject X1 = new GameObject(1, true, CurrX, CurrY);
                X1.setx(PosRight);
              
        	}
        	else if(text.equals("l")){
        		textArea.append("left" + "\n");
        	
        		Moving Left = new Moving();
                int PosLeft = Left.moveUp(CurrX);
              
                GameObject X2 = new GameObject(1, true, CurrX, CurrY);
                X2.setx(PosLeft);
               
        	}
        	else{
        		textArea.append(text + "\n");
        	}
     
        textField.selectAll();
 
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PlayerInput");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new PlayerInput());
       
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
         
    
}