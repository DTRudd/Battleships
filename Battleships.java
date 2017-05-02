package battleships;

public class Battleships {

	public static void main(String[] Args){
		Game g = new Game(3);
		TestShip p1Ship1 = new TestShip();
		TestShip p2Ship1 = new TestShip();
		TestShip p1Ship2 = new TestShip();
		TestShip p2Ship2 = new TestShip();
		g.getPlayer1().place(p1Ship1, 0, 0, Ship.Orientation.UP);
		g.getPlayer2().place(p2Ship1, 2, 1, Ship.Orientation.DOWN);
		g.getPlayer1().place(p1Ship2, 2, 1, Ship.Orientation.LEFT);
		g.getPlayer2().place(p2Ship2, 1, 2, Ship.Orientation.LEFT);
		g.turnSchedule(true);
	}

}