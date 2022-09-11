package src.mains;

import src.modele.*;
import src.view.*;
import javax.swing.JFrame;
import java.awt.Dimension;

public class MainInterface{

	public static void main(String arg[]){
		//create a exemple map to test it
		Player player1 = new Player();
		Player player2 = new WeakAI();
		Modele game = new Modele(player1, player2);

		View view = new View(game);
		SelectBoat selectBoat = new SelectBoat(game, view);
		
	}

}