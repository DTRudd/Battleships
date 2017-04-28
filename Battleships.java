package battleships;

public class Battleships {

	public static void main(String[] Args){
		Board player1Board = new Board(3);
		Board player2Board = new Board(3);
		TestShip p1Ship = new TestShip();
		TestShip p2Ship = new TestShip();
		try{
			p1Ship.place(player1Board, 0, 0, Ship.Orientation.UP);
			p2Ship.place(player2Board, 2, 1, Ship.Orientation.LEFT);
		} catch (improperPlacementException ipe){
			System.out.println(ipe.getMessage());
			ipe.printStackTrace();
		}
	}

}
