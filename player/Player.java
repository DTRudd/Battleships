package battleships.player;

import java.util.ArrayList;
import java.util.Scanner;

import battleships.AttackNotPermittedException;
import battleships.Board;
import battleships.Game;
import battleships.ImproperPlacementException;
import battleships.Orientation;
import battleships.ToEnemy;
import battleships.Tuple;
import battleships.message.*;
import battleships.ship.Ship;

public abstract class Player {
	
	protected ArrayList<Ship> fleet;
	protected int size;
	protected Board playerSquare;
	protected Game g;
	int turns;
	
	public Player(Game g,int size) {
		fleet = new ArrayList<Ship>();
		this.size = size;
		playerSquare = new Board(size);
		this.g = g;
		turns = 0;
	}
	
	public Game getGame(){
		return g;
	}
	
	public int getTurns(){
		return turns;
	}
	
	public void incrementTurns(){
		turns++;
	}
	
	public Player findOpponent(){
		if (g.getPlayer1().equals(this)){
			return g.getPlayer2();
		} else {
			return g.getPlayer1();
		}
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
	
	public abstract void message(Message m);
}
