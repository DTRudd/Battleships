package battleships;

public class Battleships {

	public static void main(String[] Args){
		Game g = new Game(3);
		g.getPlayer1().getShips().add(new TestShip(g.getPlayer1()));
		g.getPlayer1().getShips().add(new TestShip(g.getPlayer1()));
		g.getPlayer2().getShips().add(new TestShip(g.getPlayer2()));
		g.getPlayer2().getShips().add(new TestShip(g.getPlayer2()));
		g.getPlayer1().place(g.getPlayer1().getShips().get(0), 0, 0, Ship.Orientation.UP);
		g.getPlayer2().place(g.getPlayer2().getShips().get(0), 2, 1, Ship.Orientation.DOWN);
		g.getPlayer1().place(g.getPlayer1().getShips().get(1), 2, 1, Ship.Orientation.LEFT);
		g.getPlayer2().place(g.getPlayer2().getShips().get(1), 1, 2, Ship.Orientation.LEFT);
		g.printState();
		g.turnSchedule(true);
	}

}