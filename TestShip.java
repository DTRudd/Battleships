package battleships;

public class TestShip extends Ship {

	public TestShip(Player p) {
		super(p);
		length = 2;
		intactLength = length;
	}
	
	public void sink(){
		System.out.println("You've sunk my test ship!");
	}
	
}
