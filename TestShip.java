package battleships;

public class TestShip extends Ship {

	public TestShip() {
		length = 2;
		intactLength = length;
	}
	
	public void sink(){
		System.out.println("You've sunk my test ship!");
	}
	
}
