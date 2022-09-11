package src.modele;

import java.util.Scanner;
import java.util.ArrayList;

public class Player{
	
	private String name;
	private int[][] map; //0 : no boat, 1 : boat, 2 : shoot in water, 3 : shooted boat
	private Boat[][] lifeMap; //used to set the statement of the boat (sunk or not)
	private int xSize, ySize;
	private Player ennemy;
	private Scanner scanner;
	private ArrayList<Boat> fleet;

	//default size and name
	public Player(){
		this("Joueur 1");		
	}

	//default size with name
	public Player(String name){
		this.name = name;
		this.xSize = 10;
		this.ySize = 10;
		this.map = new int[this.xSize][this.ySize];
		this.lifeMap = new Boat[this.xSize][this.ySize];
		this.fleet = new ArrayList<Boat>();
		this.scanner = new Scanner(System.in);
	}

	public String getName(){
		return this.name;
	}

	public Player getEnnemy(){
		return this.ennemy;
	}

	public int[][] getMap(){
		return this.map;
	}

	public Boat[][] getLifeMap(){
		return this.lifeMap;
	}

	public int[] getSize(){
		int[] size = new int[2];
		size[0] = this.xSize;
		size[1] = this.ySize;

		return size;
	}

	public ArrayList<Boat> getFleet(){
		return this.fleet;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setEnnemy(Player ennemy){
		this.ennemy = ennemy;
	}

	//stringify the player1's map /!\ CMD ONLY /!\
	public String mapToStringAlly(){
		String mapStringified = "     ";

		//style for the x indicator
		for(int i=0;i<this.xSize;i++){
			if(i < 10){
				mapStringified += Integer.toString(i) + "  ";
			} else {
				mapStringified += Integer.toString(i) + " ";
			}
		}

		mapStringified += System.lineSeparator() + "   ";

		for(int i=0;i<this.xSize;i++){
			mapStringified += "---";
		} 

		for(int j=0;j<this.ySize;j++){
			mapStringified += System.lineSeparator();
			//style for y indicator
			if(j < 10){
				mapStringified += Integer.toString(j) + " |";
			} else {
				mapStringified += Integer.toString(j) + "|";
			}
			//statement of the map
			for(int i=0;i<this.xSize;i++){
				if(this.map[i][j] != 0){
					mapStringified += "  " + Integer.toString(this.map[i][j]);
				} else {
					mapStringified += "   ";
				}				
			}
		}

		return mapStringified;
	}

	//stringify the player1's ennemy map with player1's vision /!\ CMD ONLY /!\
	public String mapToStringEnnemy(){
		String mapStringified = "     ";
		int[][] ennemyMap = this.ennemy.getMap();
		int[] ennemySize = this.ennemy.getSize();

		//style for x indicator
		for(int i=0;i<ennemySize[0];i++){
			if(i < 10){
				mapStringified += Integer.toString(i) + "  ";
			} else {
				mapStringified += Integer.toString(i) + " ";
			}
		}

		mapStringified += System.lineSeparator() + "   ";

		for(int i=0;i<this.xSize;i++){
			mapStringified += "---";
		} 

		for(int j=0;j<ennemySize[1];j++){
			mapStringified += System.lineSeparator();
			//style for y indicator
			if(j < 10){
				mapStringified += Integer.toString(j) + " |";
			} else {
				mapStringified += Integer.toString(j) + "|";
			}
			for(int i=0;i<ennemySize[0];i++){
				//statement of the map without non-hit boat part
				if(ennemyMap[i][j] < 2){
					mapStringified += "   ";
				} else {
					mapStringified += "  " + Integer.toString(ennemyMap[i][j]);
				}
			}
		}

		return mapStringified;
	}

	public void addBoat(Boat boat){
		this.fleet.add(boat);
	}

	//place the boat on the map /!\ CMD ONLY /!\
	public void createFleet(){
		int size, orientation, xOrigin, yOrigin;

		for(Boat boat:this.fleet){
			System.out.println("Current map : ");
			System.out.println(this.mapToStringAlly());

			System.out.println("Boat of length : " + Integer.toString(boat.length));

			System.out.println("Select boat orientation (0 vertical, 1 horizontal)");
			orientation = Integer.parseInt(scanner.next()); //get orientation

			System.out.println("Select boat origin X");
			xOrigin = Integer.parseInt(scanner.next()); //get the origin point (coord X)

			System.out.println("Select boat origin Y");
			yOrigin = Integer.parseInt(scanner.next()); //get the origin point (coord y)

			while(!this.isInsertable(boat.length, orientation, xOrigin, yOrigin)){ //while the selected value aren't valid redo
				System.out.println("Error, boat not insertable !");
				System.out.println("Select boat orientation (0 vertical, 1 horizontal)");

				orientation = Integer.parseInt(scanner.next());

				System.out.println("Select boat origin X");
				xOrigin = Integer.parseInt(scanner.next());

				System.out.println("Select boat origin Y");
				yOrigin = Integer.parseInt(scanner.next());
			}

			boat.setBoat(orientation, xOrigin, yOrigin); //set the coord on the boat
			this.insertBoat(boat); //insert the boat on the map
			System.out.println("boat inserted !");
		}
	}

	//insert the boat on the map(map + lifeMap)
	public void insertBoat(Boat boat){
		for(int[] coords:boat.coord){
			map[coords[0]][coords[1]] = 1;
			lifeMap[coords[0]][coords[1]] = boat;
		}
	}

	/*return true if the boat is insertable
	be sure to use it before insertBoat*/
	public boolean isInsertable(int size, int orientation, int xOrigin, int yOrigin){
		int x, y;

		//boat longer than the map or out of the map
		if(xOrigin < 0 || yOrigin < 0 || size >= this.xSize || size >= this.ySize){ 
			return false;
		}

		x = (orientation == 0)?0:1;
		y = (orientation == 0)?1:0;

		//boat will go out of map
		if((xOrigin + size)*x > this.xSize || (yOrigin + size)*y > this.ySize){ 
			return false;
		}

		//check if the boat don't stack with an already inserted boat
		for(int i=0;i<size;i++){
			if(map[xOrigin+x*i][yOrigin+y*i] == 1){
				return false;
			}
		}

		return true;
	}

	//return the target coord /!\ CMD ONLY /!\
	public int[] selectTarget(){
		int[] target = new int[2];

		System.out.println("Selectionnez une cible :");
		System.out.println(this.mapToStringEnnemy());
		System.out.println("2 is missed shoot, 3 is hit boat");
		System.out.println("");
		System.out.println("Selectionner la coordonnée X : ");
		target[0] = Integer.parseInt(scanner.next()); //get target coord (x)
		System.out.println("Selectionner la coordonnée Y : ");
		target[1] = Integer.parseInt(scanner.next()); //get target coord (y)

		//while the target is not a valid target redo
		while(!this.isValidShoot(target[0], target[1])){
			System.out.println("Cible non valide !");
			System.out.println("Selectionner la coordonnée X : ");
			target[0] = Integer.parseInt(scanner.next());
			System.out.println("Selectionner la coordonnée Y : ");
			target[1] = Integer.parseInt(scanner.next());
		}

		return target;
	}

	//return true if it's a valid shoot
	public boolean isValidShoot(int xCoordinate, int yCoordinate){
		int[][] ennemyMap = this.ennemy.getMap();
		int[] ennemySize = this.ennemy.getSize();
		
		//check if the target is out of the map
		if(xCoordinate < 0 || yCoordinate < 0 || xCoordinate >= ennemySize[0] || yCoordinate >= ennemySize[1]){
			return false;
		}

		//if the target is a non-hit target, then it's a valid one
		return ennemyMap[xCoordinate][yCoordinate] < 2;
	}

	//return true if a boat is hited
	public boolean shoot(int xCoordinate, int yCoordinate){
		return this.ennemy.shooted(xCoordinate, yCoordinate);
	}

	//return true if a boat is hited
	public boolean shooted(int xCoordinate, int yCoordinate){
		//if the target is the sea, set to "hit sea"
		if(this.map[xCoordinate][yCoordinate] == 0){
			this.map[xCoordinate][yCoordinate] = 2;
		//if the target is a boat, set to "hit boat"
		} else if(this.map[xCoordinate][yCoordinate] == 1){
			this.map[xCoordinate][yCoordinate] = 3;
			this.lifeMap[xCoordinate][yCoordinate].lives --; //descrease the boat live
			if(this.lifeMap[xCoordinate][yCoordinate].lives == 0){
				this.fleet.remove(this.lifeMap[xCoordinate][yCoordinate]); //if the boat's live is null, then it sunk so remove from the fleet
			}
			return true;
		} else {
			System.out.println("Error, not valid coordinate"); //TODO : throw error 
		}
		return false;
	}

	//return true if the boat sunk
	public boolean isSunk(int xCoordinate, int yCoordinate){
		return this.ennemy.getLifeMap()[xCoordinate][yCoordinate].lives == 0; //if the boat hasn't live anymore it's a sunk one
	}

	//return true if the player has won
	public boolean hasWin(){
		return this.ennemy.getFleet().size() == 0; //if the ennemy's fleet hasn't boat anymore, you won ! 
	}

}