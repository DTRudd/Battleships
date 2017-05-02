package battleships;

public class Carrier extends Ship {

	public Carrier(Player p) {
		super(p);
		intactLength = length = 5;
	}
	
	public void sink(){
		System.out.println("You've sunk my aircraft carrier!");
	}

}
