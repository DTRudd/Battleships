package battleships;

public class Battleships {

	public static void main(String[] Args){
		Board player1Board = new Board(3);
		Board player2Board = new Board(3);
		player1Board.getCoOrds(1, 2);
		player2Board.getCoOrds(2, 1);
	}

}
