package src.view;

import src.modele.*;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.time.Duration;

public class Maps extends JPanel{

	public Modele modele;
	public AllyMap allyMap;
	public EnnemyMap ennemyMap;
	public View view;
	
	public Maps(Modele modele, View view){
		this.modele = modele;
		this.view = view;
		this.allyMap = new AllyMap(this.modele, this.view);
		this.ennemyMap = new EnnemyMap(this.modele, this.view, this);
		this.add(this.allyMap);
		this.add(this.ennemyMap);
		this.setLayout(new GridLayout(1,2,0,100));
	}

	public void gameAI(){
		int[] target;
		Player currentPlayer = this.modele.getCurrentPlayer();

		if(currentPlayer == this.modele.getPlayer(2)){
			target = currentPlayer.selectTarget(); //get the target
			this.performPlay(target); //perform the play
			this.allyMap.repaint(); //update the interface
		}

	}

	public void performPlay(int[] target){
		Player currentPlayer = this.modele.getCurrentPlayer();

		if(currentPlayer.shoot(target[0], target[1])){ //if it hit a boat
			this.view.setInfo(currentPlayer.getName() + " a touche un bateau");
			if(currentPlayer.isSunk(target[0], target[1])){ //if a boat sunk
				this.view.setInfo(currentPlayer.getName() + " a coule un bateau !");
			}
		} else {
			this.view.setInfo(currentPlayer.getName() + " a rate son tire");
		}
	}

}