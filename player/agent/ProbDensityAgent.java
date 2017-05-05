package battleships.player.agent;

import java.util.ArrayList;
import java.util.Scanner;

import battleships.Game;
import battleships.ImproperPlacementException;
import battleships.Orientation;
import battleships.ToEnemy;
import battleships.Tuple;
import battleships.message.Message;
import battleships.message.SunkMessage;
import battleships.message.HitMessage;
import battleships.ship.Ship;

public class ProbDensityAgent extends Agent{

	protected ArrayList<Tuple<Integer,Integer>> avails;
	protected ArrayList<Ship> enemyFleet;
	protected int[][] probBoard;
	protected int unspentHits;
	protected boolean[][] sunkBoard;
	
	public ProbDensityAgent(Game g, int size) {
		super(g,size);
		avails = new ArrayList<Tuple<Integer,Integer>>();
		probBoard = new int[size][size];
		sunkBoard = new boolean[size][size];
		unspentHits = 0;
		for(int ii = 0; ii < size; ii++){
			for(int jj = 0; jj < size; jj++){
				avails.add(new Tuple<Integer,Integer>(ii,jj));
				sunkBoard[ii][jj] = false;
			}
		}
	}
	
	public void killUpdateProbBoard(){
		for(int ii = 0; ii < size; ii++){
			for(int jj = 0; jj < size; jj++){
				probBoard[ii][jj] = 0;
			}
		}
		enemyFleet.forEach(this::killRight);
		enemyFleet.forEach(this::killUp);
	}
	
	public void huntUpdateProbBoard(){
		for(int ii = 0; ii < size; ii++){
			for(int jj = 0; jj < size; jj++){
				probBoard[ii][jj] = 0;
			}
		}
		enemyFleet.forEach(this::huntRight);
		enemyFleet.forEach(this::huntUp);
	}
	
	/* STAYS IN TARGET MODE UNTIL ALL HITS HAVE BEEN ACCOUNTED FOR BY SINKING.*/

	public void killRight(Ship inp){
		for (int ii = 0; ii < size; ii++){
			for (int jj = 0; jj < size; jj++){
				boolean fits = true;
				boolean fitsHits = false;
				for (int kk = 0; kk < inp.getLength(); kk++){
					if (jj+kk >= size || findOpponent().getHitMissSquare()[ii][jj+kk] == ToEnemy.MISS || sunkBoard[ii][jj+kk]){
						fits = false;
					}
					if (jj+kk < size && findOpponent().getHitMissSquare()[ii][jj+kk] == ToEnemy.HIT){
						fitsHits = true;
					}
				}
				if (fits){
					for (int kk = 0; kk < inp.getLength(); kk++){
						probBoard[ii][jj+kk] += fitsHits ? 100 : 1;
					}
				}
				if (findOpponent().getHitMissSquare()[ii][jj] == ToEnemy.HIT){
					probBoard[ii][jj] = 0;
				}
			}
		}
	}
	
	public void killUp(Ship inp){
		for (int ii = 0; ii < size; ii++){
			for (int jj = 0; jj < size; jj++){
				boolean fits = true;
				boolean fitsHits = false;
				for (int kk = 0; kk < inp.getLength(); kk++){
					if (ii+kk >= size || findOpponent().getHitMissSquare()[ii+kk][jj] == ToEnemy.MISS || sunkBoard[ii+kk][jj]){
						fits = false;
					}
					if (ii+kk < size){
						if (findOpponent().getHitMissSquare()[ii+kk][jj] == ToEnemy.HIT && !sunkBoard[ii][jj]){
							fitsHits = true;
						}
					}
				}
				if (fits){
					for (int kk = 0; kk < inp.getLength(); kk++){
						probBoard[ii+kk][jj] += fitsHits ? 100 : 1;
					}
				}
				if (findOpponent().getHitMissSquare()[ii][jj] == ToEnemy.HIT && !sunkBoard[ii][jj]){
					probBoard[ii][jj] = 0;
				}
			}
		}
	}
	
	public void huntRight(Ship inp){
		for(int ii = 0; ii < size; ii++){
			for(int jj = 0; jj < size; jj++){
				boolean fits = true;
				for (int kk = 0; kk < inp.getIntactLength(); kk++){
					if (jj+kk >= size || findOpponent().getHitMissSquare()[ii][jj+kk] != ToEnemy.UNTOUCHED){
						fits = false;
					}
				}
				if (fits){
					for (int kk = 0; kk < inp.getIntactLength(); kk++){
						probBoard[ii][jj+kk]++;
					}
				}
			}
		}
	}
	
	public void huntUp(Ship inp){
		for(int ii = 0; ii < size; ii++){
			for(int jj = 0; jj < size; jj++){
				boolean fits = true;
				for (int kk = 0; kk < inp.getIntactLength(); kk++){
					if (ii+kk >= size || findOpponent().getHitMissSquare()[ii+kk][jj] != ToEnemy.UNTOUCHED){
						fits = false;
					}
				}
				if (fits){
					for (int kk = 0; kk < inp.getIntactLength(); kk++){
						probBoard[ii+kk][jj]++;
					}
				}
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
		if (enemyFleet == null){
			enemyFleet = findOpponent().getFleet();
		}
		if (unspentHits == 0){
			for(int ii = 0; ii < size; ii++){
				for (int jj = 0; jj < size; jj++){
					if (findOpponent().getHitMissSquare()[ii][jj] == ToEnemy.HIT){
						sunkBoard[ii][jj] = true;
					}
				}
			}
			huntUpdateProbBoard();
		} else {
			killUpdateProbBoard();
		}
		for(int ii = size-1; ii > -1; ii--){
			for (int jj = 0; jj < size; jj++){
				System.out.print(probBoard[ii][jj] + "\t");
			}
			System.out.println();
		}
		int xCoord = 0;
		int yCoord = 0;
		int max = 0;
		for(int ii = 0; ii < size; ii++){
			for (int jj = 0; jj < size; jj++){
				if (probBoard[ii][jj] > max){
					xCoord = ii;
					yCoord = jj;
					max = probBoard[ii][jj];
				}
			}
		}
		return new Tuple<Integer,Integer>(xCoord,yCoord);
	}

	@Override
	public void message(Message m) {
		if (m instanceof HitMessage){
			unspentHits++;
		} else if (m instanceof SunkMessage){
			unspentHits -= ((SunkMessage) m).getLength();
			for (Ship s : enemyFleet){
				if (s.getClass().equals(((SunkMessage) m).getShipClass())){
					enemyFleet.remove(s);
					break;
				}
			}
		}
	}
}
