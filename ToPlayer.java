package battleships;

public enum ToPlayer {

	EMPTY, SHIP_INTACT, SHIP_DESTROYED;
	
	private char shortStatus;
	
	static {
		SHIP_DESTROYED.shortStatus = 'X';
		SHIP_INTACT.shortStatus = 'O';
		EMPTY.shortStatus = '~';
	}
	
	public char getShortStatus(){
		return shortStatus;
	}
}
