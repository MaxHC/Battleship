package src.modele;

import java.util.ArrayList;

public class Boat{
	
	public int length, orientation, xOrigin, yOrigin, lives;
	public ArrayList<int[]> coord;

	public Boat(int length){
		this.length = length;
		this.lives = length;
	}

	public String toString(){
		return "boat of length " + this.length + " " + ((this.orientation == 0)?"vertical":"horizontal") + " at origin (" + this.xOrigin + "," + this.yOrigin + ")"; 
	}

	public String toStringFR(){
		return "bateau de longueur " + this.length + " " + ((this.orientation == 0)?"verticale":"horizontale") + " d'origine (" + this.xOrigin + "," + this.yOrigin + ")"; 
	}

	//return true if the boat sunk
	public boolean isSunk(){
		return this.lives == 0;
	}

	//set selected value for the boat : orientation, and origin
	public void setBoat(int orientation, int xOrigin, int yOrigin){
		this.orientation = orientation;
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.setCoord();
	}

	//create the arraylist of the coord of the boat
	public void setCoord(){
		int x, y;

		this.coord = new ArrayList<int[]>();

		//if it's vertical boat then increase the y coord at every iteration, else increase the x one
		x = (this.orientation == 0)?0:1;
		y = (this.orientation == 0)?1:0;

		//iteration number equals to length value to have the corresponding boat
		for(int i=0;i<this.length;i++){
			int[] coord = new int[2];
			coord[0] = this.xOrigin + x*i;
			coord[1] = this.yOrigin + y*i;
			this.coord.add(coord);
		}
	}

}