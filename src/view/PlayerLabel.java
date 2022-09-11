package src.view;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class PlayerLabel extends JLabel{

	public View view;

	public PlayerLabel(View view){
		this.view = view;
		this.setFont(new Font("Verdana", Font.BOLD, 30));
	}

	//set the label dimention
	public void label(){
		int width = this.view.getWidth();
		int height = this.view.getHeight()*1/10;
		if(width <= 0 || height <= 0){ //when jframe is initialitig itself
			this.setPreferredSize(new Dimension(1200, 120));
		} else {
			this.setPreferredSize(new Dimension(width, height));
		}
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.label();
	}

	public void setLabel(String label){
		this.setText(label);
	}

}