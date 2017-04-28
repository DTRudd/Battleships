package battleships;

public class Battleships {

	public static void main(String[] Args){
		Board player1Board = new Board(3);
		Board player2Board = new Board(3);
		TestShip p1Ship1 = new TestShip();
		TestShip p2Ship1 = new TestShip();
		TestShip p1Ship2 = new TestShip();
		TestShip p2Ship2 = new TestShip();
		try{
			p1Ship1.place(player1Board, 0, 0, Ship.Orientation.UP);
			p2Ship1.place(player2Board, 2, 1, Ship.Orientation.LEFT);
			p1Ship2.place(player1Board, 1, 1, Ship.Orientation.UP);
			p2Ship2.place(player2Board, 1, 2, Ship.Orientation.LEFT);
		} catch (improperPlacementException ipe){
			System.out.println(ipe.getMessage());
			ipe.printStackTrace();
		}
		System.out.println(player1Board.toPlayerString());
		System.out.println(player2Board.toEnemyString());
	}

}

/*
XOX	OOX
OOX	XOO
OXX	XXX
*/