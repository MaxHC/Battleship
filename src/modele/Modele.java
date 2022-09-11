package src.modele;

import java.util.Scanner;

public class Modele{

	private Player player1, player2, currentPlayer;
	
	public Modele(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = this.player1;
		this.player1.setEnnemy(this.player2);
		this.player2.setEnnemy(this.player1);
	}

	public Player getPlayer(int playerNumber){
		return playerNumber == 1?this.player1:this.player2;
	}

	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}

	//change current player
	public void nextPlayer(){
		this.currentPlayer = (this.currentPlayer == this.player1)?this.player2:this.player1;
	}

	public void selectBoat(){
		Scanner scan = new Scanner(System.in);
		String addBoat;
		int length;
		do{
			System.out.println("Wanna add boat ? (y/n)");
			addBoat = scan.next();
			if(addBoat.equals("y")){
				System.out.println("Which length ?");
				length = Integer.parseInt(scan.next());
				this.addBoat(length);
			}
		} while(addBoat.equals("y"));
	}

	public void addBoat(int length){
		this.player1.addBoat(new Boat(length));
		this.player2.addBoat(new Boat(length));
	}

	// /!\ CMD ONLY /!\
	public void createFleets(){
		this.player1.createFleet();
		this.player2.createFleet();
	}
	
	//play in the cmd
	public void cmdGame(){
		int[] target;
		this.nextPlayer();

		do{ //while there is no winner
			this.nextPlayer(); //change player

			System.out.println("it's " + currentPlayer.getName() + " turn !");
			System.out.println(this.player1.getName() + " statement :");
			System.out.println(this.player1.mapToStringAlly());

			target = this.currentPlayer.selectTarget(); //get the target
			if(this.currentPlayer.shoot(target[0], target[1])){ //if it hit a boat
				System.out.println("Boat hited !");
				if(this.currentPlayer.isSunk(target[0], target[1])){ //if a boat sunk
					System.out.println("Boat sunk !");
				}
			} else {
				System.out.println("Missed !");
			}

		}while(!this.currentPlayer.hasWin());

		//result print
		System.out.println("");
		System.out.println("");
		System.out.println("=====END=====");
		System.out.println(this.currentPlayer.getName() + " has won !!");
		System.out.println("");
		System.out.println(this.player1.getName() + " statement :");
		System.out.println(this.player1.mapToStringAlly());
		System.out.println("");
		System.out.println(this.player2.getName() + " statement :");
		System.out.println(this.player2.mapToStringAlly());
	}

}