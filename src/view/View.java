package src.view;

import src.modele.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class View extends JFrame{

	public Modele modele;
	private Maps maps;
	private PlayerLabel playerLabel;
	private InfoBox info;

	public View(Modele modele){
		this.modele = modele;
		this.maps = new Maps(this.modele, this);
		this.playerLabel = new PlayerLabel(this);
		this.info = new InfoBox(this);
		this.setPreferredSize(new Dimension(1200, 900));
		this.add(this.playerLabel, BorderLayout.NORTH);
		this.add(this.maps, BorderLayout.CENTER);
		this.add(info, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(false);
		this.pack();
	}

	public void makeFleet(){
		this.modele.getPlayer(2).createFleet();
		this.maps.allyMap.allowMakeFleet();
		this.setLabel(this.modele.getPlayer(1).getFleet().get(0).toStringFR());
		this.info.setInfo("clic gauche pour placer en vertical \nclic droit pour placer en horizontal");
	}

	public void startGame(){
		this.maps.ennemyMap.startGame();
		this.setInfo("La partie debut !");
	}

	public void setInfo(String info){
		this.info.setInfo(info);
	}

	public void setLabel(String label){
		this.playerLabel.setLabel(label);
	}

}