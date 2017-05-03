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
	
	public void place(Ship s, int xCoord, int yCoord, Orientation orientation) throws ImproperPlacementException, ArrayIndexOutOfBoundsException{
		s.place(playerSquare, xCoord, yCoord, orientation);
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
	
	public String[][] getLocationSquare(){
		String[][] outp = new String[size][size];
		for (int ii = 0; ii < size; ii++){
			for (int jj = 0; jj < size; jj++){
				if (playerSquare.getPoint(ii, jj).getPublicStatus() == ToEnemy.MISS){
					outp[ii][jj] = Character.toString('-');
				} else {
					outp[ii][jj] = Character.toString(playerSquare.getPoint(ii, jj).getPrivateStatus().getShortStatus());
				}
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
	
	public abstract void placeAll(Game g, Scanner sc);
	
	public abstract Tuple<Integer,Integer> getAttackVector(Game g, Scanner sc);
}
