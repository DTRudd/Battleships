package battleships.player.agent;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import battleships.Game;
import battleships.ImproperPlacementException;
import battleships.Orientation;
import battleships.Tuple;
import battleships.message.BeenHitMessage;
import battleships.message.BeenMissedMessage;
import battleships.message.BeenSunkMessage;
import battleships.message.HitMessage;
import battleships.message.IllegalMessage;
import battleships.message.LossMessage;
import battleships.message.Message;
import battleships.message.MissMessage;
import battleships.message.SunkMessage;
import battleships.message.WinMessage;
import battleships.ship.Ship;

public class HunterKillerAgent extends Agent {

	protected int paritySize;
	protected Stack<Tuple<Integer,Integer>> killStack;
	protected ArrayList<Tuple<Integer,Integer>> avails;
	public HunterKillerAgent(Game g, int size) {
		super(g,size);
		paritySize = 2;
		killStack = new Stack<Tuple<Integer,Integer>>();
		for(int ii = 0; ii < size; ii++){
			for(int jj = 0; jj < size; jj++){
				avails.add(new Tuple<Integer,Integer>(ii,jj));
			}
		}
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
		if (killStack.isEmpty()){
			return huntAttackVector();
		} else {
			return killAttackVector();
		}
	}

	public Tuple<Integer, Integer> huntAttackVector(){
		int ii = (int) Math.ceil(Math.random() * avails.size())-1;
		return avails.remove(ii);
	}
	
	public Tuple<Integer,Integer> killAttackVector(){
		return new Tuple<Integer,Integer>(-1,-1);
	}
	
	public void message(Message m){
		if (m instanceof BeenHitMessage){
		} else if (m instanceof BeenMissedMessage){
		} else if (m instanceof BeenSunkMessage){
		} else if (m instanceof HitMessage){
		} else if (m instanceof IllegalMessage){
		} else if (m instanceof LossMessage){
		} else if (m instanceof MissMessage){
		} else if (m instanceof SunkMessage){
		} else if (m instanceof WinMessage){
		}
	}
	
}
