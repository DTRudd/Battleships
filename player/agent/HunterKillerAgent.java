package battleships.player.agent;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import battleships.Game;
import battleships.ImproperPlacementException;
import battleships.Orientation;
import battleships.ToEnemy;
import battleships.Tuple;
import battleships.message.HitMessage;
import battleships.message.Message;
import battleships.message.SunkMessage;
import battleships.ship.Ship;

public class HunterKillerAgent extends Agent {

	protected int paritySize;
	protected int[][] parityBoard;
	protected Stack<Tuple<Integer,Integer>> killStack;
	protected ArrayList<Tuple<Integer,Integer>> avails;
	protected ArrayList<Ship> enemyFleet;
	public HunterKillerAgent(Game g, int size) {
		super(g,size);
		paritySize = 2;
		killStack = new Stack<Tuple<Integer,Integer>>();
		avails = new ArrayList<Tuple<Integer,Integer>>();
		parityBoard = new int[size][size];
		for(int ii = 0; ii < size; ii++){
			for(int jj = 0; jj < size; jj++){
				avails.add(new Tuple<Integer,Integer>(ii,jj));
			}
		}
		updateParityBoard(2);
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
		if (enemyFleet == null){
			enemyFleet = findOpponent().getFleet();
		}
		int tmp = enemyFleet.stream().map(Ship::getLength).reduce(Math::min).get();
		if (tmp != paritySize){
			paritySize = tmp;
			updateParityBoard(paritySize);
		}
		if (killStack.isEmpty()){
			return huntAttackVector();
		} else {
			return killAttackVector();
		}
	}

	public void updateParityBoard(int inp){
		int startNo = 0;
		int pointNo;
		for(int ii = 0; ii < g.getSize(); ii++){
			if (startNo != inp){
				startNo++;
			} else {
				startNo = 1;
			}
			pointNo = startNo;
			for (int jj = 0; jj < g.getSize(); jj++){
				parityBoard[ii][jj] = pointNo;
				if (pointNo != inp){
					pointNo++;
				} else {
					pointNo = 1;
				}
			}
		}
	}
	
	public Tuple<Integer, Integer> huntAttackVector(){
		int ii = (int) Math.ceil(Math.random() * avails.size())-1;
		while (parityBoard[avails.get(ii).first()][avails.get(ii).second()] != 1){
			ii = (int) Math.ceil(Math.random() * avails.size())-1;
		}
		return avails.remove(ii);
	}
	
	public Tuple<Integer,Integer> killAttackVector(){
		Tuple<Integer,Integer> outp = killStack.pop();
		avails.remove(outp);
		return outp;
	}
	
	public void message(Message m){
		if (m instanceof HitMessage){
			Tuple<Integer,Integer> centrePoint = ((HitMessage) m).getAttackedSquare();
			addToKillStack(new Tuple<Integer,Integer>(centrePoint.first()+1,centrePoint.second()));
			addToKillStack(new Tuple<Integer,Integer>(centrePoint.first()-1,centrePoint.second()));
			addToKillStack(new Tuple<Integer,Integer>(centrePoint.first(),centrePoint.second()+1));
			addToKillStack(new Tuple<Integer,Integer>(centrePoint.first(),centrePoint.second()-1));
		} else if (m instanceof SunkMessage){
			for (Ship s : enemyFleet){
				if (s.getClass().equals(((SunkMessage) m).getShipClass())){
					enemyFleet.remove(s);
					break;
				}
			}
		}
	}
	
	public void addToKillStack(Tuple<Integer,Integer> point){
		if (point.first() >= g.getSize() || point.first() < 0 || point.second() >= g.getSize() || point.second() < 0){
			//Do nothing
		} else if (findOpponent().getHitMissSquare()[point.first()][point.second()].getShortStatus() != ToEnemy.UNTOUCHED.getShortStatus()){
			//Do nothing
		} else {
			killStack.push(point);
		}
		
	}
	
}
