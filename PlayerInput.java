import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class PlayerInput extends JPanel implements ActionListener {
	 private static JTextField textField;
    private static JTextArea textArea;
  
    int CurrX = GameObject.getx();
    int CurrY = GameObject.gety();
    
    
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
       
        
        int CurrX = GameObject.getx();
        int CurrY = GameObject.gety();
        
       
        	if(text.equals("u")){
        		textArea.append("up" + "\n");
        		
                int PosUp = Moving.moveUp(CurrY);
                
                GameObject.Sety(PosUp);
                textArea.append(PosUp + "\n");
        	}
        
        	else if(text.equals("d")){
        		textArea.append("down" + "\n");
        		
               int PosDown = Moving.moveDown(CurrY);
               GameObject.Sety(PosDown);
               textArea.append(PosDown + "\n");
        	}
        	
        	else if(text.equals("r")){
        		textArea.append("right" + "\n");
        		
               int PosRight = Moving.moveRight(CurrX);
               GameObject.Setx(PosRight);
               textArea.append(PosRight + "\n");
        	}
        	else if(text.equals("l")){
        		textArea.append("left" + "\n");
        	
               int PosLeft = Moving.moveLeft(CurrX);
               GameObject.Setx(PosLeft);
               textArea.append(PosLeft + "\n");
        	}
        	else{
        		textArea.append(text + "\n");
        	}
     
        	int Xcord = GameObject.getx();
        	int Ycord = GameObject.gety();
        	
        	textArea.append("("+Xcord +","+ Ycord+")" + "\n");
        	
        textField.selectAll();
 
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PlayerInput");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new PlayerInput());
       
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
                	createAndShowGUI();
                
            }
        
    
}