
public class CollisonTesting {
	
	GameMechanics mech;
	Dimensions dim;
	
	public CollisonTesting(GameMechanics mech){
		this.mech = mech;
		dim = mech.getDimensions();
	}
	
	public boolean testMove(String s, GameObject ob) {
		if(s.equals("u") && (dim.getVal(ob.getx(), (ob.gety())-1)) == 0 ) {
			dim.setVal(ob.getx(), ob.gety());
			//dim.printDimensions();
			return true;
		}else if(s == "d" && (dim.getVal(ob.getx(), (ob.gety())+1)) == 0 ) {
			dim.setVal(ob.getx(), ob.gety());
			//dim.printDimensions();
			return true;
		}else if(s == "l" && (dim.getVal((ob.getx()) - 1, ob.gety())) == 0 ) {
			dim.setVal(ob.getx(), ob.gety());
			//dim.printDimensions();
			return true;
		}else if(s == "r" && (dim.getVal((ob.getx()) + 1, ob.gety())) == 0 ) {
			dim.setVal(ob.getx(), ob.gety());
			//dim.printDimensions();
			return true;
		}
		return false;
		
	}
	
	public String testDoor(String s, GameObject ob) {
		//System.out.println("got here");
		//System.out.println(dim.getVal(ob.getx(), (ob.gety())-1));
		if(s.equals("u") && ((dim.getVal(ob.getx(), (ob.gety())-1)) == 60) ) {
			//System.out.println("burnsmansion");
			return "burnsmansion";
		}
		/*else if(s == "u" && (dim.getVal(ob.getx(), (ob.gety())+1)) == 50 ) {
			return "comicbookstore";
		}else if(s == "u" && (dim.getVal((ob.getx()) - 1, ob.gety())) == 70 ) {
			return "kwikemart";
		}else if(s == "u" && (dim.getVal((ob.getx()) + 1, ob.gety())) == 40 ) {
			return "school";
		}else if(s == "u" && (dim.getVal((ob.getx()) + 1, ob.gety())) == 80 ) {
			return "flandershouse";
		}
		*/
		return "not moe";
		
	}
}
