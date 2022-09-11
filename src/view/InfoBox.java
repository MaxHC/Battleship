package src.view;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class InfoBox extends JScrollPane{
	
	public JTextArea textArea;
	public View view;

	public InfoBox(View view){
		this.view = view;
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.setViewportView(this.textArea);
		this.repaint();
	}

	//set the panel dimention
	public void panel(){
		int width = this.view.getWidth();
		int height = this.view.getHeight()*1/4;
		if(width <= 0 || height <= 0){ //when jframe is initialitig itself
			this.setPreferredSize(new Dimension(1200, 300));
		} else {
			this.setPreferredSize(new Dimension(width, height));
		}
	}

	//add info to the infobox
	public void setInfo(String info){
		this.textArea.append("\n" + info);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		this.panel();
		this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
	}

}