package battleships;

public class Submarine extends Ship {

	public Submarine(Player p) {
		super(p);
		intactLength = length = 3;
	}

	public void sink(){
		System.out.println("You've sunk my submarine!");
	}
}
