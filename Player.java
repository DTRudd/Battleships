package battleships;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player {
	
	ArrayList<Ship> fleet;
	private int size;
	private Board playerSquare;
	public Player(int size) {
		fleet = new ArrayList<Ship>();
		this.size = size;
		playerSquare = new Board(size);
	}
	
	public void place(Ship s, int xCoord, int yCoord, Orientation orientation){
		try { 
			s.place(playerSquare, xCoord, xCoord, orientation);
		} catch (ImproperPlacementException ipe){
			ipe.printStackTrace();
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public ArrayList<Ship> getFleet(){
		return fleet;
	}
	
	public ToEnemy[][] getHitMissSquare(){
		ToEnemy[][] outp = new ToEnemy[size][size];
		for(int ii = 0; ii < size; ii++){
			for(int jj = 0; jj < size; jj++){
				outp[ii][jj] = playerSquare.getPoint(ii, jj).getPublicStatus();
			}
		}
		return outp;
	}
	
	public ToPlayer[][] getShipSquare(){
		ToPlayer[][] outp = new ToPlayer[size][size];
		for (int ii = 0; ii < size; ii++){
			for (int jj = 0; jj < size; jj++){
				outp[ii][jj] = playerSquare.getPoint(ii, jj).getPrivateStatus();
			}
		}
		return outp;
	}
	
	public ToEnemy attack(Player defPlayer, int xCoord, int yCoord) throws ArrayIndexOutOfBoundsException, AttackNotPermittedException{
		return defPlayer.defend(xCoord, yCoord);
	}

	public ToEnemy defend(int xCoord, int yCoord) throws ArrayIndexOutOfBoundsException, AttackNotPermittedException{
		return playerSquare.getPoint(xCoord, yCoord).defend();
	}
	
	public abstract void placeAll();
	
	public abstract Tuple<Integer,Integer> getAttackVector(Scanner sc);
}
