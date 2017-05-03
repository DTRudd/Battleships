package battleships;

import java.util.Scanner;

public class RandomAgent extends Agent {

	public RandomAgent(int size) {
		super(size);
	}

	@Override
	public void placeAll(Game g, Scanner sc) {
		for(Ship s : fleet){
			while(true){
				int xCoord = (int) Math.ceil(Math.random() * 12)-1;
				int yCoord = (int) Math.ceil(Math.random() * 12)-1;
				int dir = (int) Math.ceil(Math.random() * 4)-1;
				Orientation oDir;
				try{
					switch(dir){
					case 0:		oDir = Orientation.UP;
								break;
					case 1:		oDir = Orientation.DOWN;
								break;
					case 2: 	oDir = Orientation.LEFT;
								break;
					case 3: 	oDir = Orientation.RIGHT;
								break;
					default:	throw new ImproperPlacementException();
					}
					place(s, xCoord, yCoord, oDir);
					break;
				} catch (ImproperPlacementException | ArrayIndexOutOfBoundsException e){
					continue;
				}
			}
		}
	}

	@Override
	public Tuple<Integer, Integer> getAttackVector(Game g, Scanner sc) {
		int xCoord = (int) Math.ceil(Math.random() * 12)-1;
		int yCoord = (int) Math.ceil(Math.random() * 12)-1;
		return new Tuple<Integer,Integer>(xCoord,yCoord);
	}

}
