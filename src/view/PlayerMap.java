package src.view;

import src.modele.*;
import javax.swing.JPanel;
import java.awt.Dimension;

public class PlayerMap extends JPanel{
	
	private Modele modele;
	private View view;
	public int c; //panel dim

	public PlayerMap(Modele modele, View view){
		this.modele = modele;
		this.view = view;
	}

	public Modele getModele(){
		return this.modele;
	}

	public int getC(){
		return this.c;
	}

	public View getView(){
		return this.view;
	}

	//set the panel dimention
	public void panel(){
		int width = this.view.getWidth()/2-100;
		int height = this.view.getHeight()*3/5;
		this.c = (width < height)?width:height;
		if(this.c <= 0){ //when jframe is initialitig itself
			this.c = 500;
		}
		this.setPreferredSize(new Dimension(c, c));
	}

}