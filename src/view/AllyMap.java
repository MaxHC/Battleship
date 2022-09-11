package src.view;

import src.modele.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class AllyMap extends PlayerMap implements MouseListener{
	
	private int nbBoatInserted; //number of boat inserted used to find the next boat to insert

	public AllyMap(Modele modele, View view){
		super(modele, view);
		this.panel();
	}

	public void allowMakeFleet(){
		this.addMouseListener(this);
	}

	public void forbidMakeFleet(){
		this.removeMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		this.panel();

		int state, x, y, r; //state is the statement of a map's case, x and y the coords to place the circle
		r = this.getC()/30; //circle radius
		Graphics2D g2 = (Graphics2D) g;

		g.drawLine(0,0,0,this.getC()); //left line
		g.drawLine(0,0,this.getC(),0); //top line
		for(int i=0;i<10;i++){
			g.setColor(Color.black); //set the color toi black (for the lines)
			g.drawLine((i+1)*this.getC()/10,0,(i+1)*this.getC()/10,this.getC()); //vertical line to make the grid
			g.drawLine(0,(i+1)*this.getC()/10,this.getC(),(i+1)*this.getC()/10); //horizontal line to make the grid
			for(int j=0;j<10;j++){
				state = this.getModele().getPlayer(1).getMap()[i][j];
				if(state > 0){
					if(state == 1){ //ally boat
						g2.setColor(Color.blue); //display in blue
					} else if(state == 2){ //missed shot
						g2.setColor(Color.green); //display in green
					} else if(state == 3){ //boat hit
						g2.setColor(Color.red); //display in red
					}
					x = this.getC()/10*i+this.getC()/20; //circle center (coord X)
					y = this.getC()/10*j+this.getC()/20; //circle center (coord Y)
					g2.fillOval(x-r,y-r,2*r,2*r); //draw circle
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e){
		int[] target = new int[2]; //coord of the case selected
		Player player1 = this.getModele().getPlayer(1);
		Boat boat = player1.getFleet().get(this.nbBoatInserted); //get boat to insert
		View view = this.getView();
		int orientation = -1; //0 vertical, 1 horizontal

		target[0] = e.getX()/(this.getC()/10); //x coord clicked (in pixel) to map coord
		target[1] = e.getY()/(this.getC()/10); //y coord clicked (in pixel) to map coord
		
		//get orientation from the button clicked, left click : vertical, right click : horizontal
		if(e.getButton() == MouseEvent.BUTTON1){
			orientation = 0;
		} else if(e.getButton() == MouseEvent.BUTTON3){
			orientation = 1;
		} else {
			view.setInfo("Touche non valide");
		}

		if(orientation != -1){
			if(player1.isInsertable(boat.length, orientation, target[0], target[1])){
				boat.setBoat(orientation, target[0], target[1]);
				player1.insertBoat(boat);
				this.repaint();
				this.nbBoatInserted ++; //new boat inserted, increase to get the next one
				if(this.nbBoatInserted == player1.getFleet().size()){
					view.setInfo("Tous les bateaux ont ete insere, la partie va débuter");
					view.startGame();
					this.forbidMakeFleet(); //disable listener
				} else {
					view.setLabel(player1.getFleet().get(this.nbBoatInserted).toStringFR());
				}
			} else {
				view.setInfo("Emplacement non valide !");
			}
		}
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

}