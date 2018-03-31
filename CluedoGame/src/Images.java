import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	BufferedImage tokens[] = new BufferedImage[6];
	BufferedImage tokensH[] = new BufferedImage[6];
	BufferedImage cards[] = new BufferedImage[6];
	BufferedImage weapons[] = new BufferedImage[6];
	BufferedImage weaponsCard[] = new BufferedImage[6];
	BufferedImage screens[] = new BufferedImage[7];
	BufferedImage numbers[] = new BufferedImage[6];
	BufferedImage rooms[] = new BufferedImage[9];
	BufferedImage secret;
	
	public Images() {
		Initialise();
	}
	
	
	public void Initialise() {
		try {
			tokens[0] = ImageIO.read(getClass().getResource("catLadyToken2.png"));
			tokens[1] = ImageIO.read(getClass().getResource("fatTonyToken2.png"));
			tokens[2] = ImageIO.read(getClass().getResource("hanzToken2.png"));
			tokens[3] = ImageIO.read(getClass().getResource("HomerToken2.png"));
			tokens[4] = ImageIO.read(getClass().getResource("maggieToken2.png"));
			tokens[5] = ImageIO.read(getClass().getResource("moeToken2.png"));
			
			cards[0] = ImageIO.read(getClass().getResource("crazyCatLadyCard.png"));
			cards[1] = ImageIO.read(getClass().getResource("fatTonyCard.png"));
			cards[2] = ImageIO.read(getClass().getResource("hanzMolemanCard.png"));
			cards[3] = ImageIO.read(getClass().getResource("homerCard.png"));
			cards[4] = ImageIO.read(getClass().getResource("maggieCard.png"));
			cards[5] = ImageIO.read(getClass().getResource("moeCard.png"));
			
			weapons[0] = ImageIO.read(getClass().getResource("axeToken.png"));
			weapons[1] = ImageIO.read(getClass().getResource("bombToken.png"));
			weapons[2] = ImageIO.read(getClass().getResource("chainsawToken.png"));
			weapons[3] = ImageIO.read(getClass().getResource("gunToken.png"));
			weapons[4] = ImageIO.read(getClass().getResource("knifeToken.png"));
			weapons[5] = ImageIO.read(getClass().getResource("slingShotToken.png"));
			
			weaponsCard[0] = ImageIO.read(getClass().getResource("axeCard.png"));
			weaponsCard[1] = ImageIO.read(getClass().getResource("atomicBombCard.png"));
			weaponsCard[2] = ImageIO.read(getClass().getResource("chainsawCard.png"));
			weaponsCard[3] = ImageIO.read(getClass().getResource("gunCard.png"));
			weaponsCard[4] = ImageIO.read(getClass().getResource("knifeCard.png"));
			weaponsCard[5] = ImageIO.read(getClass().getResource("slingShotCard.png"));
			
			screens[0] = ImageIO.read(getClass().getResource("acc.png"));
			screens[1] = ImageIO.read(getClass().getResource("startArea.png"));
			screens[2] = ImageIO.read(getClass().getResource("playButton.png"));
			screens[3] = ImageIO.read(getClass().getResource("howToPlayButton.png"));
			screens[4] = ImageIO.read(getClass().getResource("murderEnvelope.png"));
			screens[5] = ImageIO.read(getClass().getResource("selectionButton.png"));
			screens[6] = ImageIO.read(getClass().getResource("murderEnvelope2.png"));
			
			numbers[0] = ImageIO.read(getClass().getResource("1.png"));
			numbers[1] = ImageIO.read(getClass().getResource("2.png"));
			numbers[2] = ImageIO.read(getClass().getResource("3.png"));
			numbers[3] = ImageIO.read(getClass().getResource("4.png"));
			numbers[4] = ImageIO.read(getClass().getResource("5.png"));
			numbers[5] = ImageIO.read(getClass().getResource("6.png"));
			
			secret = ImageIO.read(getClass().getResource("secret1.png"));
			
			rooms[0] = ImageIO.read(getClass().getResource("burnsMansionCard.png"));
			rooms[1] = ImageIO.read(getClass().getResource("springfieldElementryCard.png"));
			rooms[2] = ImageIO.read(getClass().getResource("fryingDutchmanCard.png"));
			rooms[3] = ImageIO.read(getClass().getResource("flandersHouseCard.png"));
			rooms[4] = ImageIO.read(getClass().getResource("krustyBurgerCard.png"));
			rooms[5] = ImageIO.read(getClass().getResource("comicBookStoreCard.png"));
			rooms[6] = ImageIO.read(getClass().getResource("kwikEMartCard.png"));
			rooms[7] = ImageIO.read(getClass().getResource("simpsonsHouseCard.png"));
			rooms[8] = ImageIO.read(getClass().getResource("moesTavernCard.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getImage(int val, String str) {
		if(str.equals("tokens")) {
			if(val == 1) {
				return tokens[0];
			}else if(val == 2) {
				return tokens[1];
			}else if(val == 3) {
				return tokens[2];
			}else if(val == 4) {
				return tokens[3];
			}else if(val == 5) {
				return tokens[4];
			}else if(val == 6) {
				return tokens[5];
			}
		}else if(str.equals("cards")) {
			if(val == 1) {
				return cards[0];
			}else if(val == 2) {
				return cards[1];
			}else if(val == 3) {
				return cards[2];
			}else if(val == 4) {
				return cards[3];
			}else if(val == 5) {
				return cards[4];
			}else if(val == 6) {
				return cards[5];
			}
		}else if(str.equals("weapons")) {
			if(val == 1) {
				return weapons[0];
			}else if(val == 2) {
				return weapons[1];
			}else if(val == 3) {
				return weapons[2];
			}else if(val == 4) {
				return weapons[3];
			}else if(val == 5) {
				return weapons[4];
			}else if(val == 6) {
				return weapons[5];
			}
		}else if(str.equals("weaponsCard")) {
			if(val == 21) {
				return weaponsCard[0];
			}else if(val == 22) {
				return weaponsCard[1];
			}else if(val == 23) {
				return weaponsCard[2];
			}else if(val == 24) {
				return weaponsCard[3];
			}else if(val == 25) {
				return weaponsCard[4];
			}else if(val == 26) {
				return weaponsCard[5];
			}
		}else if(str.equals("highlight")) {
			if(val == 1) {
				return tokensH[0];
			}else if(val == 2) {
				return tokensH[1];
			}else if(val == 3) {
				return tokensH[2];
			}else if(val == 4) {
				return tokensH[3];
			}else if(val == 5) {
				return tokensH[4];
			}else if(val == 6) {
				return tokensH[5];
			}
		}else if(str.equals("room")) {
			if(val == 11) {
				return rooms[0];
			}else if(val == 12) {
				return rooms[1];
			}else if(val == 13) {
				return rooms[2];
			}else if(val == 14) {
				return rooms[3];
			}else if(val == 15) {
				return rooms[4];
			}else if(val == 16) {
				return rooms[5];
			}else if(val == 17) {
				return rooms[6];
			}else if(val == 18) {
				return rooms[7];
			}else if(val == 19) {
				return rooms[8];
			}
		}else if(str.equals("screens")) {
			if(val == 1) {
				return screens[0];
			}else if(val == 2) {
				return screens[1];
			}else if(val == 3) {
				return screens[2];
			}else if(val == 4) {
				return screens[3];
			}else if(val == 5) {
				return screens[4];
			}else if(val == 6) {
				return screens[5];
			}else if(val == 7) {
				return screens[6];
			}
		}else if(str.equals("numbers")) {
			if(val == 1) {
				return numbers[0];
			}else if(val == 2) {
				return numbers[1];
			}else if(val == 3) {
				return numbers[2];
			}else if(val == 4) {
				return numbers[3];
			}else if(val == 5) {
				return numbers[4];
			}else if(val == 6) {
				return numbers[5];
			}
		}else if(str.equals("secret")) {
			if(val == 1) {
				return secret;
			}
		}
		return null;
	}
}