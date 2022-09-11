package src.tests;

import src.modele.*;

public class IntegrationTest{

	//if the test is a success then true will be displayed
	
	public Player player, ennemy;
	public Modele mod;

	public IntegrationTest(Player player, Player ennemy, Modele mod){
		this.player = player;
		this.ennemy = ennemy;
		this.mod = mod;
	}

	public void aiTurn(){
		int[] target;

		System.out.println("===Test of a ai turn===");
		System.out.println("statement before the turn :");
		String statement = this.player.mapToStringAlly();
		System.out.println(statement);
		System.out.println("");
		
		target = this.ennemy.selectTarget();
		this.ennemy.shoot(target[0], target[1]);

		System.out.println("new statement (random shoot) :");
		System.out.println(this.player.mapToStringAlly());
		System.out.println("test past : " + !(statement == this.player.mapToStringAlly()));
	}

	public void playerTurn(){
		int[] target;

		System.out.println("===Test of a player turn===");
		System.out.println("statement before the turn :");
		String statement = this.player.mapToStringEnnemy();
		System.out.println(statement);
		System.out.println("");
		
		target = this.player.selectTarget();
		this.player.shoot(target[0], target[1]);

		System.out.println("new statement (random shoot) :");
		System.out.println(this.player.mapToStringEnnemy());
		System.out.println("test past : " + !(statement == this.player.mapToStringEnnemy()));
	}

	public void swapTurn(){
		System.out.println("Test Player then AI then Player");
		int turn = 0;
		int[] target;
		String allyStatement = this.player.mapToStringAlly();
		String ennemyStatement = this.player.mapToStringEnnemy();
		Player currentPlayer = this.mod.getCurrentPlayer();
		do{
			System.out.println("human's map statement :");
			System.out.println(this.player.mapToStringAlly());

			System.out.println("it's " + currentPlayer.getName() + " turn");
			target = currentPlayer.selectTarget();
			currentPlayer.shoot(target[0], target[1]);
			turn ++;
			this.mod.nextPlayer();
		} while(turn < 3);
		System.out.println("Test past : " + (allyStatement != this.player.mapToStringAlly() && ennemyStatement != this.player.mapToStringEnnemy()));
	}

}