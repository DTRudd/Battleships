package battleships;

public class Battleship extends Ship {

	public Battleship(Player p) {
		super(p);
		intactLength = length = 3;
	}

	public void sink(){
		System.out.println("You've sunk my battleship!");
	}
}
