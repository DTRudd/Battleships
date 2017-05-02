package battleships;

public class Destroyer extends Ship {

	public Destroyer(Player p) {
		super(p);
		intactLength = length = 4;
	}

	public void sink(){
		System.out.println("You've sunk my destroyer!");
	}
}
