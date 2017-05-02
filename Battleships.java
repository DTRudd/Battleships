package battleships;


public class Battleships {

	public static void main(String[] Args){
		Game g = new Game(12);
		g.printState();
		g.turnSchedule(true);
	}

}