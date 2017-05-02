package battleships;

public class Game {

	private Player player1, player2;
	private int size;
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
	}

	public Game(int size){
		player1 = new HumanPlayer(size);
		player2 = new HumanPlayer(size);
		this.size = size;
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
					if (result != ToEnemy.HIT){
						p1Starts = false;
						System.out.println("\n\n\nPlayer 2's turn");
					}
					if (player2.getShips().size() == 0){
						System.out.println("Player 1 wins!");
						break;
					}
				} else {
					result = turn(player2,player1);
					printState();
					if (result != ToEnemy.HIT){
						p1Starts = true;
						System.out.println("\n\n\nPlayer 1's turn");
					}
					if (player1.getShips().size() == 0){
						System.out.println("Player 1 wins!");
						break;
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
