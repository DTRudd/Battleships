package battleships;

public class TestShip extends Ship {

	public TestShip(Player p) {
		super(p);
		intactLength = length = 2;
	}
	
	public void sink(){
		System.out.println("You've sunk my test ship!");
	}
	
}
