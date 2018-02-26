import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class PlayerInput extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField textField;
    private static JTextArea textArea;
  
    GameMechanics mech;
    CollisonTesting cTest;
    int spaces = 0;
    int i = 0;
    
    DiceRoll roll = new DiceRoll();

    
    public PlayerInput(GameMechanics mech) {
        super(new GridBagLayout());
        this.mech = mech;
        cTest = new CollisonTesting(mech);
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
        text = text.trim();
        
        	 weaponMove(text);
        	
             	if(text.equals("roll") && i == 0){
             		spaces = Roll();
             		textArea.append("press u,d,l,r to move character or \nclick on the board and use the arrow keys" + "\n");
             		i++;
             		mech.setRoll(spaces);
             		mech.setDone(0);
             	}
             	else if(text.equals("quit")){
             		System.exit(0);
             	}else {
             		playerMove(text);
             	}
        
        //characterMove(text);
      	
        	textField.selectAll();
 
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
    	
      	
        textField.selectAll();
 
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
	public int Roll(){
		roll.DiceRoll();
		spaces = roll.getTotal();
		textArea.append("you got " + spaces + "\n");
		return spaces;
	}
	public void characterMove(String text){
		if(spaces <= 0 ){
			textArea.append("Type 'done' to end your turn" + "\n");
			if(text.equals("done")&& i == 1){
				mech.checkCurrent();
				textArea.append("Next player turn. Type 'roll' to roll the dice" + "\n");
				mech.setDone(1);
				i = 0;
			}
		}
		else{
			playerMove(text);
			}
	}
    
    
    public void weaponMove(String text){
    	if(text.equals("burns mansion")){
        	
    		textArea.append("burns mansion" + "\n");
    		textArea.append("moved weapon to burns mansion" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    		}
    	
    	else if(text.equals("comic book store")){
    	
    		textArea.append("comic book store" + "\n");
    		textArea.append("moved weapon to comic book store" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    	}
    	
    	else if(text.equals("kwik-e-mart")){
    	
    		textArea.append("kwik-e-mart" + "\n");
    		textArea.append("moved weapon to kwik-e-mart" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    	}
    	
    	else if(text.equals("school")){
    	
    		textArea.append("school" + "\n");
    		textArea.append("moved weapon to school" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    	}
    	
    	else if(text.equals("flanders house")){
    	
    		textArea.append("flanders house" + "\n");
    		textArea.append("moved weapon to flanders house" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    	}
    	
    	else if(text.equals("simpsons house")){
    	
    		textArea.append("simpsons house" + "\n");
    		textArea.append("moved weapon to simpsons house" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    	}
    	
    	else if(text.equals("frying dutchman")){
    	
    		textArea.append("frying dutchman" + "\n");
    		textArea.append("moved weapon to frying dutchman" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    	}
    	else if(text.equals("krusty burger")){
    	
    		textArea.append("krusty burger" + "\n");
    		textArea.append("moved weapon to krusty burger" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    	}
    	else if(text.equals("moes tavern")){
    	
    		textArea.append("moes tavern" + "\n");
    		textArea.append("moved weapon to moes tavern" + "\n");
    		textArea.append("roll dice by typing roll" + "\n");
    	}
    	
    }
    
    public void playerMove(String text){
    	if(text.equals("u")){
    		if(cTest.testMove("u", mech.getOb())){
				mech.setInput("u");
				textArea.append("Moved player up" + "\n");

			}
    	}
    	
    	else if(text.equals("d")){
    		if(cTest.testMove(text, mech.getOb())){
				mech.setInput("d");
				textArea.append("Moved player down" + "\n");

			}
    	}
    	
    	else if(text.equals("r")){
    		if(cTest.testMove("r", mech.getOb())){
				mech.setInput("r");
				textArea.append("Moved player right" + "\n");

			}
    	}
    	
    	else if(text.equals("done")){
    		//mech.updateCurrent();
			textArea.append("Next player turn. Type 'roll' to roll the dice" + "\n");
			mech.setDone(1);
			i = 0;
    	}
    	else if(text.equals("l")){
    		if(cTest.testMove("l", mech.getOb())){
				mech.setInput("l");
				textArea.append("Moved player left" + "\n");

			}
    	}
    	
    	else if(text.equals("1")){
    		setExit(1);
    		textArea.append("Player exited through exit 1" + "\n");
    		spaces--;
    	}
    	
    	else if(text.equals("2")){
    		setExit(2);
    		textArea.append("Player exited through exit 2" + "\n");

    	}
    	
    	else if(text.equals("3")){
    		setExit(3);
    		textArea.append("Player exited through exit 3" + "\n");

    	}
    	
    	else if(text.equals("4")){
    		setExit(4);
    		textArea.append("Player exited through exit 4" + "\n");

    	}
    	
    	else if(text.equals("passage")) {
    		setExit(5);
    		textArea.append("Player exited through exit secret passage way" + "\n");
    
    	}
    	
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
      
    }
    
    public void setExit(int num) {
    	mech.setExitNum(num);
    }
    
    public void errorMessages(int num) {
    	if(num == 1) {
    		textArea.append("Type 'quit' to end the game" + "\n");
    		textArea.append("Player has a choice to exit by the door or to exit by \nthe secret passage way\n");
    		textArea.append("To exit by the door enter the value of the door you \nwould like to exit out of\n");
    		textArea.append("Or to exit by the secret passage way type 'passage'\n");
    	}else if(num == 2) {
    		textArea.append("Player has a choice to exit by the door\n");
    		textArea.append("To exit by the door enter the value of the door you would like to exit out of\n");
    	}else if(num == 3) {
    		textArea.append("No more moves\n");
    		textArea.append("Type 'done' to end your turn\n");
    	}else if(num == 4) {
    		 //textArea.append("Move weapon to room" + "\n");
    	}
    }
    
    public int getSpaces() {
    	return spaces;
    }
    public void setSpaces() {
    	spaces = 0;
    }
}