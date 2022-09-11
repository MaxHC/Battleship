package src.mains;

import src.modele.*;
import java.util.Scanner;

public class MainCMD{
	
	// /!\ CMD ONLY ! /!\
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		System.out.println("Select a player name (without space) :");
		String name = scan.next();

		Player player1 = new Player(name);
		Player player2 = new WeakAI();
		Modele mod = new Modele(player1, player2);

		mod.selectBoat(); //select the number of boat and their length
		mod.createFleets(); //create the fleet for each player

		mod.cmdGame(); //start the game
	}

}