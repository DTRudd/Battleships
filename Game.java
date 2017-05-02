package battleships;

import java.util.HashMap;
import java.util.Map;

public class Game {

	private Player player1, player2;
	private int size;
	private HashMap<Ship,Integer> fleet = new HashMap<Ship,Integer>();
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		try {
			if (player1.getSize() != player2.getSize()){
				throw new Exception("Player board sizes not equal");
			}
			size = player1.getSize();
		} catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		fleet.put(new PatrolBoat(null), 2);
		fleet.put(new Battleship(null), 2);
		fleet.put(new Submarine(null), 1);
		fleet.put(new Destroyer(null), 1);
		fleet.put(new Carrier(null), 1);
		for(Map.Entry<Ship, Integer> e : fleet.entrySet()){
			for(int ii = 0; ii < e.getValue(); ii++){
				try{
					Class pC = player1.getClass().getSuperclass();
					player1.getFleet().add(e.getKey().getClass().getConstructor(pC).newInstance(player1));
					player2.getFleet().add(e.getKey().getClass().getConstructor(pC).newInstance(player2));
				} catch (Exception ex){
					System.out.println("BAD MOJO");
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		player1.placeAll();
		player2.placeAll();
	}

	public Game(int size){
		player1 = new HumanPlayer(size);
		player2 = new HumanPlayer(size);
		this.size = size;
		fleet.put(new PatrolBoat(null), 2);
		fleet.put(new Battleship(null), 2);
		fleet.put(new Submarine(null), 1);
		fleet.put(new Destroyer(null), 1);
		fleet.put(new Carrier(null), 1);
		for(Map.Entry<Ship, Integer> e : fleet.entrySet()){
			for(int ii = 0; ii < e.getValue(); ii++){
				try{
					Class pC = player1.getClass().getSuperclass();
					player1.getFleet().add(e.getKey().getClass().getConstructor(pC).newInstance(player1));
					player2.getFleet().add(e.getKey().getClass().getConstructor(pC).newInstance(player2));
				} catch (Exception ex){
					System.out.println("BAD MOJO");
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		player1.placeAll();
		player2.placeAll();
	}

	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}

	public int getSize(){
		return size;
	}
	
	public void turnSchedule(boolean p1Starts){
		ToEnemy result;
		while (true) {
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
		for (int ii = size-1; ii > -1; ii--){
			for (int jj = 0; jj < size; jj++){
				System.out.print(player1.getShipSquare()[ii][jj].getShortStatus());
			}
			System.out.print("\t");
			for (int jj = 0; jj < size; jj++){
				System.out.print(player2.getHitMissSquare()[ii][jj].getShortStatus());
			}
			System.out.println("");
		}
	}
	
	public ToEnemy turn(Player attPlayer, Player defPlayer) throws ArrayIndexOutOfBoundsException, AttackNotPermittedException{
		Tuple<Integer,Integer> coords = player1.getAttackVector();
		int xCoord = coords.first();
		int yCoord = coords.second();
		ToEnemy result;
		try{
			result = attPlayer.attack(defPlayer, xCoord, yCoord);
		} catch (ArrayIndexOutOfBoundsException | AttackNotPermittedException e){
			System.out.println("We can't attack there, commander.");
			throw e;
		}
		if (result == ToEnemy.HIT){
			System.out.println("We've scored a hit, commander!");
		} else {
			System.out.println("We've missed commander.");
		}
		return result;
	}
}
