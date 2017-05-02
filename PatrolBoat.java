package battleships;

public class PatrolBoat extends Ship {

	public PatrolBoat(Player p) {
		super(p);
		intactLength = length = 2;
	}
	
	public void sink(){
		System.out.println("You've sunk my patrol boat!");
	}

}
