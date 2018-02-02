import java.awt.image.BufferedImage;

public class locations {
	
	BufferedImage img1;
	BufferedImage img2;
	BufferedImage img3;
	BufferedImage img4;
	BufferedImage img5;
	BufferedImage img6;
	int num = 1;
	
	public locations(BufferedImage img1,BufferedImage img2,BufferedImage img3,BufferedImage img4,BufferedImage img5,BufferedImage img6) {
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
	}
	
	/*
	String string1 = "Assets/Images/catLadyToken2.png";
	String string2 = "Assets/Images/fatTonyToken2.png";
	String string3 = "Assets/Images/hanzToken2.png";
	String string4 = "Assets/Images/HomerToken2.png";
	String string5 = "Assets/Images/maggieToken2.png";
	String string6 = "Assets/Images/moeToken2.png";
	*/
	GameObject PlayerOne = new GameObject(num,false,11,2);
	GameObject PlayerTwo = new GameObject(++num,false,11,2);
	GameObject PlayerThree = new GameObject(++num,false,11,2);
	GameObject PlayerFour = new GameObject(++num,false,11,2);
	GameObject PlayerFive = new GameObject(++num,false,11,2);
	GameObject PlayerSix = new GameObject(++num,false,11,2);

	
}