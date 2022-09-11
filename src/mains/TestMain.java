package src.mains;

import src.modele.*;
import src.tests.*;

public class TestMain{
	
	public static void main(String[] args){ //true signify that the test is past successfully
		Player player1, player2, temp;
		Boat boat;
		Modele mod;
		UnitaryTest test;

		player1 = new Player("Arthur");
		player2 = new WeakAI();
		mod = new Modele(player1, player2); //used to set the ennemy
		test = new UnitaryTest(player1, player2);
		
		boat = new Boat(4);
		boat.setBoat(0,2,3);
		player1.addBoat(boat);
		player1.insertBoat(boat);

		boat = new Boat(4);
		boat.setBoat(1,4,5);
		player1.addBoat(boat);
		player1.insertBoat(boat);

		System.out.println("======|!| if the test is a success then true will be displayed |!|=====");
		System.out.println("=====Unitary Tests=====");
		//
		System.out.println("");
		System.out.println("");

		Player currentPlayer = mod.getCurrentPlayer();

		System.out.println("=====Next Player Test=====");
		temp = currentPlayer;
		System.out.println("actual player : " + currentPlayer.getName());
		System.out.println("without any change : " + (temp == currentPlayer));
		
		mod.nextPlayer();
		currentPlayer = mod.getCurrentPlayer();

		System.out.println("new player : " + currentPlayer.getName());
		System.out.println("test past : " + !(temp == currentPlayer));
		System.out.println("");
		System.out.println("");

		test.insertBoatTest();
		System.out.println("");
		System.out.println("");

		test.shootTest();
		System.out.println("");
		System.out.println("");
		
		player1 = new Player("Maxence");
		player2 = new WeakAI();
		mod = new Modele(player1, player2);
		test = new UnitaryTest(player1, player2);

		player1.addBoat(new Boat(4));
		player1.addBoat(new Boat(3));

		player2.addBoat(new Boat(4));
		player2.addBoat(new Boat(3));
		player2.addBoat(new Boat(5));
		player2.addBoat(new Boat(2));

		test.fleetTest();
		System.out.println("");
		System.out.println("");

		player1 = new Player("Maxence");
		player2 = new WeakAI();
		mod = new Modele(player1, player2);
		test = new UnitaryTest(player1, player2);

		boat = new Boat(2);
		boat.setBoat(0, 0, 0);
		player2.addBoat(boat);
		player2.insertBoat(boat);
		
		test.hasWinTest();
		System.out.println("");
		System.out.println("");

		IntegrationTest iTest = new IntegrationTest(player1, player2, mod);
		System.out.println("=====Integration Tests=====");
		System.out.println("");
		System.out.println("");
		iTest.aiTurn();
		System.out.println("");
		System.out.println("");
		iTest.playerTurn();
		System.out.println("");
		System.out.println("");
		iTest.swapTurn();
	}

}