import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Accusation {
	
	Players[] players;
	ArrayList<Integer> accuse = new ArrayList<Integer>();
	GameMechanics game;
	Frame frame;
	int playerWithCard = 0;
	
	public Accusation(Players[] players,GameMechanics game) {
		this.players = players;
		this.game = game;
	}
	
	public int checkMatch() {
		game.setCurrentGameState(6);
		int i = (game.current) + 1;
		int n;
		playerWithCard = i;
		String name;
		int match = 0;
		while(i != game.current) {
			if(i >= game.numOfPlayers) {
				i = 0;
			}
			
			game.draw(i);
			game.setAccuseCurrent(i);
			n = JOptionPane.showConfirmDialog(game.frame.getFrame(),"Do you have any matching cards",
				    "Accusation Question",JOptionPane.YES_NO_OPTION);
			if(n == 0) {
				name = JOptionPane.showInputDialog(null, "Enter matching card number",
					    "Accusation Question");
				if(accuse.contains(Integer.parseInt(name))) {
					playerWithCard = i;
					return Integer.parseInt(name);
				}
			}else if(n == 1) {
				for(int m=0;m<3;m++) {
					if(players[i].cards.contains(accuse.get(m))) {
						match += 1;
					}
				}
				if(!(match >= 1)) {
					 i++;
				}
			}
			if(i >= game.numOfPlayers) {
				i = 0;
			}
		}
		game.setCurrentGameState(2);
		return 0;
	}
	public ArrayList<Integer> getAccuseList() {
		return accuse;
	}
	public boolean accuseFull() {
		return accuse.size() == 3;
	}
	public void accuseAddition(int number) {
		accuse.add(number);
	}
	public int getPlayerWithCard() {
		return playerWithCard;
	}
	public void clearAll() {
		accuse.clear();
	}
}
