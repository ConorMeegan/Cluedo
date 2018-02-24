public class Dimensions {
	
	//corresponds to the map, this allows for collision detection in future updates and allows 
	//for players to move
	private int[][] dimensions = {
			
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,5,5,5,5,5,1,0,1,4,4,4,4,4,4,4,1,0,1,17,3,3,3,3,3,1,1},
			{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,5,5,5,5,5,50,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,15,5,5,5,5,5,0,0,0,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,4,4,4,4,4,40,0,0,0,30,3,3,3,3,1,1,1},
			{1,1,0,0,0,0,0,0,0,0,0,4,4,40,4,4,4,0,0,0,0,0,0,0,0,0,1,1},
			{1,1,0,6,6,6,60,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,6,6,6,6,6,60,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,1,1},
			{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,2,2,2,2,2,2,2,1,1},
			{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,20,2,2,2,2,2,2,1,1},
			{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,110,0,20,2,2,2,2,2,2,1,1},
			{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,2,2,2,2,2,2,2,1,1},
			{1,0,0,6,6,6,6,6,60,0,0,11,11,11,11,11,11,11,0,2,2,20,2,2,2,2,1,1},
			{1,1,0,6,6,6,60,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,9,90,9,0,0,0,100,10,10,1,1,1},
			{1,1,7,7,7,70,0,0,0,8,80,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,17,0,0,8,8,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,7,0,0,8,8,8,8,8,0,90,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,7,0,0,8,8,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,7,0,0,8,8,8,8,80,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,1,0,1,8,8,8,8,8,1,1,9,9,9,1,0,1,15,10,10,10,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	
	
private int[][] dimensionsDraw = {
			
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	
	public void printDimensions() {
		for(int i=0;i<dimensionsDraw.length;i++) {
			for(int j=0;j<dimensionsDraw.length;j++) {
				System.out.printf("%d",dimensionsDraw[i][j]);
			}
			System.out.println();
		}
		System.out.println(dimensions.length);
	}
	
	public int getVal(int i, int j) {
		return dimensions[i][j];
	}
	
	public void setVal(int i, int j) {
		dimensionsDraw[i][j] = 55;
	}
}