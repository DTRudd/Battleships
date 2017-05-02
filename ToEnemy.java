package battleships;

public enum ToEnemy {
	HIT, MISS, UNTOUCHED;

	private char shortStatus;
	
	static {
		HIT.shortStatus = 'X';
		MISS.shortStatus = 'O';
		UNTOUCHED.shortStatus = '~';
	}
	
	public char getShortStatus(){
		return shortStatus;
	}
}
