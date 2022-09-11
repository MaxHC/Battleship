package src.view;

import src.modele.Modele;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SelectBoat extends JFrame{
	
	private JTextField name;
	private JFormattedTextField boat2, boat3, boat4, boat5;
	private Modele modele;
	private View view;

	public SelectBoat(Modele modele, View view){
		this.modele = modele;
		this.view = view;	

		this.setField();
		this.addComponent();

		this.setLayout(new GridLayout(6,2,0,50));
		this.setVisible(true);
		this.pack();
	}

	//create and set all textfield to defautl value
	public void setField(){
		NumberFormat format = NumberFormat.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true); //commit value on each keystroke

		this.boat2 = new JFormattedTextField(formatter);
		this.boat2.setValue(0);

		this.boat3 = new JFormattedTextField(formatter);
		this.boat3.setValue(0);

		this.boat4 = new JFormattedTextField(formatter);
		this.boat4.setValue(0);

		this.boat5 = new JFormattedTextField(formatter);
		this.boat5.setValue(0);
	}

	//add all component to the frame
	public void addComponent(){
		this.add(new JLabel("Nom du joueur :"));
		this.name = new JTextField();
		this.add(this.name);

		this.add(new JLabel("torpilleur (l=2) :"));
		this.add(this.boat2);

		this.add(new JLabel("Contre-torpilleur (l=3) :"));
		this.add(this.boat3);

		this.add(new JLabel("Croiseur (l=4) :"));
		this.add(this.boat4);

		this.add(new JLabel("Porte-avion (l=5) :"));
		this.add(this.boat5);

		JButton valid = new JButton("Valider");
		valid.addActionListener(new ActionListener(){ //using lambda function because needed to cast 1 function only. Button is used 1 time
			public void actionPerformed(ActionEvent e){
				buttonPressed();
			}
		});

		this.add(valid);

		JButton defaultB = new JButton("Valeurs par defaut");
		defaultB.addActionListener(new ActionListener(){ //using lambda function because needed to cast 2 functions only. Button is used 1 time
			public void actionPerformed(ActionEvent e){
				defaultValue();
				buttonPressed();
			}
		});

		this.add(defaultB);
	}

	//when a button is pressed, start the game with the parameter that user want
	public void buttonPressed(){
		setEntries();
		this.view.setVisible(true);
		this.view.makeFleet(); //start the game
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //close the window
	}

	//create the boat selected on each player instance
	public void setEntries(){
		if(!this.name.getText().equals("")){
			this.modele.getPlayer(1).setName(this.name.getText());
		}
		
		for(int i=0;i<(int) boat2.getValue();i++){
			this.modele.addBoat(2);
		}

		for(int i=0;i<(int) boat3.getValue();i++){
			this.modele.addBoat(3);
		}

		for(int i=0;i<(int) boat4.getValue();i++){
			this.modele.addBoat(4);
		}

		for(int i=0;i<(int) boat5.getValue();i++){
			this.modele.addBoat(5);
		}
	}

	//set default value for the game (took on https://fr.wikipedia.org/wiki/Bataille_navale_(jeu))
	public void defaultValue(){
		this.boat2.setValue(1);
		this.boat3.setValue(2);
		this.boat4.setValue(1);
		this.boat5.setValue(1);
	}

}