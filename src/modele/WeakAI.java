package src.modele;

import java.util.Random;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.time.Duration;

public class WeakAI extends Player{

	public WeakAI(){
		super("bot");
	}

	@Override
	public void createFleet(){
		System.out.println("AI is making his fleet");

		int size, orientation, xOrigin, yOrigin;
		Random rand = new Random();

		for(Boat boat:this.getFleet()){
			do{
				orientation = rand.nextInt(2);
				xOrigin = rand.nextInt(this.getSize()[0]);
				yOrigin = rand.nextInt(this.getSize()[1]);
			} while(!this.isInsertable(boat.length, orientation, xOrigin, yOrigin));

			boat.setBoat(orientation, xOrigin, yOrigin);
			this.insertBoat(boat);

			System.out.println("AI inserted : " + boat); //disable to play in cmd because it's showing were boat are
		}

		System.out.println("AI finished it fleet");
	}

	@Override
	public int[] selectTarget(){
		System.out.println("AI is selecting target...");

		Random rand = new Random();
		int[] target = new int[2];
		int[] ennemySize = this.getEnnemy().getSize(); //to allow custom size map, not implemented yet

		do{
			target[0] = rand.nextInt(ennemySize[0]);
			target[1] = rand.nextInt(ennemySize[1]);
		}while(!this.isValidShoot(target[0], target[1]));

		System.out.println("AI selected " + "(" + target[0] + "," + target[1] + ")");

		return target;
	}

}