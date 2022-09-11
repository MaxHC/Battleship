package src.tests;

import src.modele.*;

public class UnitaryTest{

	//if the test is a success then true will be displayed
	
	public Player player, ennemy;

	public UnitaryTest(Player player, Player ennemy){
		this.player = player;
		this.ennemy = ennemy;
	}

	public void insertBoatTest(){
		System.out.println("===Test of boat functions===");

		System.out.println("");
		System.out.println("==Test of insertBoat function==");
		System.out.println("");
		System.out.println("If there is 1 vertical boat and 1 horizontal of length 4 then the test is past successfully");
		System.out.println(this.player.mapToStringAlly());
		System.out.println("");

		System.out.println("==Test of isInsertable function==");
		System.out.println("test of out of map boat (from the origin, negative coord) : " + !this.player.isInsertable(5,1,-1,-1));
		System.out.println("test of out of map boat (from the origin, positive coord) : " + !this.player.isInsertable(5,1,15,15));
		System.out.println("test of out of map boat (the end of boat) : " + !this.player.isInsertable(5,1,7,2));
		System.out.println("test of boat in the map : " + this.player.isInsertable(5,1,5,0));
		System.out.println("test if stackable : " + !this.player.isInsertable(5,1,1,4));
	}

	public void fleetTest(){
		System.out.println("===Test of create fleet function===");

		System.out.println("==AI fleet==");
		this.ennemy.createFleet();
		System.out.println(this.ennemy.mapToStringAlly());

		System.out.println("==player fleet==");
		this.player.createFleet();
		System.out.println(this.player.mapToStringAlly());
	}

	public void shootTest(){
		System.out.println("===Test of shoots functions===");

		System.out.println("shoot out of the map (negative coord) : " + !this.ennemy.isValidShoot(-1,0));
		System.out.println("shoot out of the map (positive coord) : " + !this.ennemy.isValidShoot(0,15));
		System.out.println("first shoot in ocean : " + this.ennemy.isValidShoot(0,0));
		System.out.println("first shoot in a boat : " + this.ennemy.isValidShoot(2,4));
		this.ennemy.shoot(0,0);
		this.ennemy.shoot(2,4);
		System.out.println("");
		System.out.println("If there is a shoot in the ocean at (0,0) (2) and one in a boat at (2,4) (3) then the test is past successfully");
		System.out.println(player.mapToStringAlly());
		System.out.println("");
		System.out.println("first shoot in shooted ocean : " + !this.ennemy.isValidShoot(0,0));
		System.out.println("first shoot in shooted boat : " + !this.ennemy.isValidShoot(2,4));
	}

	public void hasWinTest(){
		System.out.println("===Test of win function===");

		System.out.println("Boat intact : " + !this.player.hasWin());
		this.player.shoot(0, 0);
		System.out.println("Boat shooted but still alive : " + !this.player.hasWin());
		this.player.shoot(0, 1);
		System.out.println("Boat sunk : " + this.player.hasWin());
	}

}