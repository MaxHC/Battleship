package src.view;

import src.modele.*;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class EnnemyMap extends PlayerMap implements MouseListener{

	private Maps maps;

	public EnnemyMap(Modele modele, View view, Maps maps){
		super(modele, view);
		this.maps = maps;
	}

	public void startGame(){
		this.addMouseListener(this);
		this.getView().setLabel("c'est au tour de : " + this.getModele().getCurrentPlayer().getName());
	}

	public void stopGame(){
		this.removeMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		this.panel();

		int state, x, y; //state is the statement of a map's case, x and y the coords to place the circle
		int r = this.getC()/30; //circle radius
		int[][] map;
		Graphics2D g2 = (Graphics2D) g;

		g.drawLine(0,0,0,this.getC()); //left line
		g.drawLine(0,0,this.getC(),0); //top line
		for(int i=0;i<10;i++){ 
			g.setColor(Color.black); //set the color toi black (for the lines)
			g.drawLine((i+1)*this.getC()/10,0,(i+1)*this.getC()/10,c); //vertical line to make the grid
			g.drawLine(0,(i+1)*this.getC()/10,this.getC(),(i+1)*this.getC()/10); //horizontal line to make the grid
			for(int j=0;j<10;j++){
				map = this.getModele().getPlayer(2).getMap();
				state = map[i][j];
				if(state > 1){ //if there is a shoot
					if(state == 2){ //missed shot
						g2.setColor(Color.green); //display in green
					} else if(state == 3){ //boat hit
						g2.setColor(Color.red); //display in red
					}
					x = this.getC()/10*i+this.getC()/20; //get center of the case (x coord)
					y = this.getC()/10*j+this.getC()/20; //get center of the case (y coord)
					g2.fillOval(x-r,y-r,2*r,2*r); //draw circle
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e){
		int[] target = new int[2]; //coord of the case selected
		Modele modele = this.getModele();
		View view = this.getView();
		Player currentPlayer = modele.getCurrentPlayer();
		Player player1 = modele.getPlayer(1);

		if(currentPlayer == player1){
			target[0] = e.getX()/(this.getC()/10); //x coord clicked (in pixel) to map coord
			target[1] = e.getY()/(this.getC()/10); //y coord clicked (in pixel) to map coord
			if(player1.isValidShoot(target[0], target[1])){
				this.maps.performPlay(target); //perform play is the target is a valid one 
				this.repaint(); //update the interface
				if(currentPlayer.hasWin()){
					view.setInfo("Vous avez gagne !"); //if win display it
					this.stopGame(); //disable listener, game is over
				} else {
					modele.nextPlayer();
					currentPlayer = modele.getCurrentPlayer();

					view.setLabel("C'est au tour de : " + currentPlayer.getName()); //display player changement
		 			this.maps.gameAI(); //AI play
					if(currentPlayer.hasWin()){
						view.setInfo("Vous avez perdu !"); //display if the AI win (you lose)
						this.stopGame(); //disable listener, game is over
					} else {
						modele.nextPlayer();
						currentPlayer = modele.getCurrentPlayer();

						view.setLabel("C'est au tour de : " + currentPlayer.getName()); //display player changement
					}
				}
			} else {
				view.setInfo("Cible non valide"); //display that the target is not valid
			}
		}
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

}