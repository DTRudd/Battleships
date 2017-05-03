package battleships;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.reflect.Constructor;

public class Game extends Thread{

	private Player player1, player2;
	private int size;
	private HashMap<Ship,Integer> fleet = new HashMap<Ship,Integer>();
	private boolean p1Starts;
	private Scanner gScanner;

	public Game(int size, Scanner sc){
		player1 = new HumanPlayer(size);
		player2 = new RandomAgent(size);
		gScanner = sc;
		this.size = size;
		addFleet();
	}
	
	public Game(int size){
		player1 = new RandomAgent(size);
		player2 = new RandomAgent(size);
		this.size = size;
		gScanner = new Scanner(System.in);
		addFleet();
	}
	
	public void addFleet(){
		fleet.put(new PatrolBoat(null), 2);
		fleet.put(new Battleship(null), 2);
		fleet.put(new Submarine(null), 1);
		fleet.put(new Destroyer(null), 1);
		fleet.put(new Carrier(null), 1);
		for(Map.Entry<Ship, Integer> e : fleet.entrySet()){
			for(int ii = 0; ii < e.getValue(); ii++){
				try{
					Class<?> pC = player1.getClass().getSuperclass();
					Class<?> sC = e.getKey().getClass();
					Constructor<?> cC = sC.getConstructor(pC);
					player1.getFleet().add((Ship)cC.newInstance(player1));
					player2.getFleet().add((Ship)cC.newInstance(player2));
				} catch (Exception ex){
					System.out.println("BAD MOJO");
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		player1.placeAll(this,gScanner);
		player2.placeAll(this,gScanner);
	}

	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}

	public Player getOpponent(Player inp){
		if (inp.equals(player1)){
			return player2;
		} else{
			return player1;
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public void run(){
		ToEnemy result;
		while (true) {
			System.out.println();
			try{
				if (p1Starts){
					result = turn(player1,player2);
					printState();
					if (player2.getFleet().size() == 0){
						System.out.println("Player 1 wins!");
						break;
					}
					if (result != ToEnemy.HIT){
						p1Starts = false;
						System.out.println("\n\n\nPlayer 2's turn");
					}
				} else {
					result = turn(player2,player1);
					printState();
					if (player1.getFleet().size() == 0){
						System.out.println("Player 2 wins!");
						break;
					}
					if (result != ToEnemy.HIT){
						p1Starts = true;
						System.out.println("\n\n\nPlayer 1's turn");
					}
				}
			} catch (ArrayIndexOutOfBoundsException | AttackNotPermittedException e){
				continue;
			}
		}
	}
	
	public void printState(){
		System.out.print("  ");
		for(char ii = 'a'; ii < 'm'; ii++){
			System.out.format(" " + ii);
		}
		System.out.print("\t  ");
		for(char ii = 'a'; ii < 'm'; ii++){
			System.out.print(" " + ii );
		}
		System.out.println();
		for (int ii = size-1; ii > -1; ii--){
			System.out.format("%2d ",ii+1);
			for (int jj = 0; jj < size; jj++){
				System.out.print(player1.getLocationSquare()[ii][jj] + " ");
			}
			System.out.format("%2d",ii+1);
			System.out.print("\t");
			System.out.format("%2d ",ii+1);
			for (int jj = 0; jj < size; jj++){
				System.out.print(player2.getHitMissSquare()[ii][jj].getShortStatus() + " ");
			}
			System.out.format("%2d",ii+1);
			System.out.println();
		}
		System.out.print("   ");
		for(char ii = 'a'; ii < 'm'; ii++){
			System.out.print(ii + " ");
		}
		System.out.print("\t   ");
		for(char ii = 'a'; ii < 'm'; ii++){
			System.out.print(ii + " ");
		}
	}
	
	public ToEnemy turn(Player attPlayer, Player defPlayer) throws ArrayIndexOutOfBoundsException, AttackNotPermittedException{
		Tuple<Integer,Integer> coords = attPlayer.getAttackVector(this,gScanner);
		int xCoord = coords.first();
		int yCoord = coords.second();
		ToEnemy result;
		try{
			result = attPlayer.attack(defPlayer, xCoord, yCoord);
		} catch (ArrayIndexOutOfBoundsException | AttackNotPermittedException e){
			if (attPlayer instanceof HumanPlayer){
				System.out.println("We can't attack there, commander.");
			}
			throw e;
		}
		if (result == ToEnemy.HIT){
			if (attPlayer instanceof HumanPlayer){
				System.out.println("We've scored a hit, commander!");
			} else if (defPlayer instanceof HumanPlayer){
				System.out.println("We've been hit, commander!");
			}
		} else {

			if (attPlayer instanceof HumanPlayer){
				System.out.println("We've missed commander.");
			} else if (defPlayer instanceof HumanPlayer){
				System.out.println("Missed us by a mile, commander!");
			}
		}
		return result;
	}
}
