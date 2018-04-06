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
	Accusation acc;
	int spaces = 0;
	int firstRoll = 0;
	int i = 0;
	int number = 0;
	boolean player = true;
	boolean room = true;
	boolean weapon = true;

	DiceRoll roll = new DiceRoll();

	public PlayerInput(GameMechanics mech,Accusation accuse) {
		super(new GridBagLayout());
		this.mech = mech;
		this.acc = accuse;
		cTest = new CollisonTesting(mech);
		textField = new JTextField(20);
		textField.addActionListener(this);

		textArea = new JTextArea(5, 20);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);// adds a scroll bar on jtextarea

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

		if (text.equals("roll") && i == 0) {
			spaces = Roll();
			textArea.append("press u,d,l,r to move character or \nclick on the board and use the arrow keys" + "\n");
			i++;
			mech.setRoll(spaces);
			mech.setDone(0);
		}else if (text.equals("quit")) {
			System.exit(0);
		}else if (text.equals("notes")) {
			mech.table();
		}else if (text.equals("help")) {
			help();
		}else if (text.equals("rules")) {
			rules();
		}else if (text.equals("cheat")) {
			message("Error");
			mech.setCurrentGameState(4);
		}else if(text.equals("password") && mech.getGameState() == 2) {
			mech.setPassword(1);
		}else {
			playerMove(text);
		}

		// characterMove(text);

		textField.selectAll();

		textArea.setCaretPosition(textArea.getDocument().getLength());

		textField.selectAll();

		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	public int Roll() {
		roll.Diceroll();
		spaces = roll.getTotal();
		textArea.append("you got " + spaces + "\n");
		return spaces;
	}

	public void characterMove(String text) {
		if (spaces <= 0) {
			textArea.append("Type 'done' to end your turn" + "\n");
			if (text.equals("done") && i == 1) {
				mech.checkCurrent();
				textArea.append("Next player turn. Type 'roll' to roll the dice" + "\n");
				mech.setDone(1);
				i = 0;
			}
		} else {
			playerMove(text);
		}
	}

	public void playerMove(String text) {
		if(mech.getGameState() == 2) {
			if (text.equals("u")) {
				if (cTest.testMove("u", mech.getOb())) {
					mech.setInput("u");
					textArea.append("Moved player up" + "\n");
				}
			}
			else if (text.equals("d")) {
				if (cTest.testMove(text, mech.getOb())) {
					mech.setInput("d");
					textArea.append("Moved player down" + "\n");
				}
			}else if (text.equals("r")) {
				if (cTest.testMove("r", mech.getOb())) {
					mech.setInput("r");
					textArea.append("Moved player right" + "\n");
				}
			}else if (text.equals("done")) {
				textArea.append("Next player turn. Type 'roll' to roll the dice" + "\n");
				mech.setDone(1);
				i = 0;
				mech.setPassword(0);
			}else if (text.equals("l")) {
				if (cTest.testMove("l", mech.getOb())) {
					mech.setInput("l");
					textArea.append("Moved player left" + "\n");
				}
			}else if (text.equals("1")) {
				setExit(1);
				textArea.append("Player exited through exit 1" + "\n");
				spaces--;
			}else if (text.equals("2")) {
				setExit(2);
				textArea.append("Player exited through exit 2" + "\n");
			}else if (text.equals("3")) {
				setExit(3);
				textArea.append("Player exited through exit 3" + "\n");
			}else if (text.equals("4")) {
				setExit(4);
				textArea.append("Player exited through exit 4" + "\n");
			}else if (text.equals("passage")) {
				setExit(5);
				textArea.append("Player exited through exit secret passage way" + "\n");
			}else if (text.equals("n")) {
				mech.newMClass();
			}else {
				message("(" + text +"): is not a command" );
			}
		}else if(mech.getGameState() == 5 && acc.accuseFull() == false) {
			if(text.equals("burns mansion")) {
				if(checkValue(11) == false) {
					acc.accuseAddition(11);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
			}else if(text.equals("comic book store")) {
				if(checkValue(16) == false) {
					acc.accuseAddition(16);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("flanders house")) {
				if(checkValue(14) == false) {
					acc.accuseAddition(14);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("frying dutchman")) {
				if(checkValue(13) == false) {
					acc.accuseAddition(13);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("krusty burger")) {
				if(checkValue(15) == false) {
					acc.accuseAddition(15);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
			
			}else if(text.equals("moes tavern")) {
				if(checkValue(19) == false) {
					acc.accuseAddition(19);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("kwik-e-mart")) {
				if(checkValue(17) == false) {
					acc.accuseAddition(17);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("springfield elementry")) {
				if(checkValue(12) == false) {
					acc.accuseAddition(12);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("simpsons house")) {
				if(checkValue(18) == false) {
					acc.accuseAddition(18);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("homer")) {
				if(checkValue(4) == false) {
					acc.accuseAddition(4);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("moe")) {
				if(checkValue(6) == false) {
					acc.accuseAddition(6);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("hanz")) {
				if(checkValue(3) == false) {
					acc.accuseAddition(3);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("maggie")) {
				if(checkValue(5) == false) {
					acc.accuseAddition(5);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("fat tony")) {
				if(checkValue(2) == false) {
					acc.accuseAddition(2);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						moveCardWeapon();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("crazy cat lady")) {
				if(checkValue(1) == false) {
					acc.accuseAddition(1);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("axe")) {
				if(checkValue(21) == false) {
					acc.accuseAddition(21);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("atomic bomb")) {
				if(checkValue(22) == false) {
					acc.accuseAddition(22);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("chainsaw")) {
				if(checkValue(23) == false) {
					acc.accuseAddition(23);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("knife")) {
				if(checkValue(25) == false) {
					acc.accuseAddition(25);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
			}else if(text.equals("sling shot")) {
				if(checkValue(26) == false) {
					acc.accuseAddition(26);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
				
			}else if(text.equals("gun")) {
				if(checkValue(24) == false) {
					acc.accuseAddition(24);
					if(acc.accuseFull() == true){
						number = acc.checkMatch();
						acc.clearAll();
						mech.showAccused(number,acc.getPlayerWithCard());
					}
				}
			}else {
				message("(" + text +"): is not a command" );
			}
		}else if(acc.accuseFull() == true) {
			message("Accusation:" +mech.getAccuse().toString());
			mech.setCurrentGameState(2);
		}

	}

	public void setExit(int num) {
		mech.setExitNum(num);
	}

	public void errorMessages(int num) {
		if (num == 1) {
			textArea.append("Type 'quit' to end the game" + "\n");
			textArea.append("Player has a choice to exit by the door or to exit by \nthe secret passage way\n");
			textArea.append("To exit by the door enter the value of the door you \nwould like to exit out of\n");
			textArea.append("Or to exit by the secret passage way type 'passage'\n");
		} else if (num == 2) {
			textArea.append("Player has a choice to exit by the door\n");
			textArea.append("To exit by the door enter the value of the door you would like to exit out of\n");
		} else if (num == 3) {
			textArea.append("No more moves\n");
			textArea.append("Type 'done' to end your turn\n");
		} else if (num == 4) {
			// textArea.append("Move weapon to room" + "\n");
		}
	}

	public void Messages(String name) {
		textArea.append(name + ":please roll" + "\n");
	}

	public void message(String message) {
		textArea.append(message + "\n");
	}

	public int getSpaces() {
		return spaces;
	}

	public void setSpaces() {
		spaces = 0;
	}

	public void help() {
		textArea.append(
				"--------------------------------------------------------------------------------------------\n'DONE' ends your turn\n"
						+ "'QUIT' closes the game\n'ROLL' rolls the dice\n'RULES' shows game rules\n"
						+ "'U','D','L','R' moves player up down left or right(must ROLL first)\n"
						+ "'NUMBER e.g(1)' to leave by that door(must be in room)\n"
						+ "'PASSAGE' to leave through secret passage(must be in room)\n"
						+ "-----------------------------------------------------------------------------------------");
	}

	public void rules() {
		if (!java.awt.Desktop.isDesktopSupported()) {
			System.err.println("Desktop is not supported (fatal)");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			System.err.println("Desktop doesn't support the browse action (fatal)");
			System.exit(1);
		}

		try {
			java.net.URI uri = new java.net.URI("https://www.hasbro.com/common/instruct/Clue_(2002).pdf");
			desktop.browse(uri);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public boolean checkValue(int value) {
		if(value <= 6 && player) {
			player = false;
			return false;
		}else if(value >= 11 && value <= 19 && room) {
			room = false;
			return false;
		}else if(value >= 21 && value <= 26 && weapon) {
			weapon = false;
			return false;
		}else if(!(room)) {
			message("Accusation already contains a room");
			return true;
		}else if(!(weapon)) {
			message("Accusation already contains a weapon");
			return true;
		}else if(!(player)) {
			message("Accusation already contains a player");
			return true;
		}
		return false;
	}
	
	public void moveCardWeapon() {
		for(int i=0;i<2;i++) {
			for(int j=0;j<6;j++) {
				if(acc.getAccuseList().get(i) == mech.getWeapons()[j].getWeaponsID()) {
					mech.getWeapons()[j].setx(mech.getDimensions().getX());
					mech.getWeapons()[j].sety(mech.getDimensions().getY());
				}
			}
		}
	}
	
}