package battleships;

public class Point {

	public static enum ToEnemy {HIT, MISS,UNTOUCHED};
	public static enum ToPlayer {EMPTY, SHIP_INTACT, SHIP_DESTROYED};

	private ToEnemy publicStatus;
	private ToPlayer privateStatus;
	
	public ToEnemy getPublicStatus() {
		return publicStatus;
	}
	public void setPublicStatus(ToEnemy publicStatus) {
		this.publicStatus = publicStatus;
	}
	public ToPlayer getPrivateStatus() {
		return privateStatus;
	}
	public void setPrivateStatus(ToPlayer privateStatus) {
		this.privateStatus = privateStatus;
	}
	
	public Point() {
		publicStatus = ToEnemy.UNTOUCHED;
		privateStatus = ToPlayer.EMPTY;
	}

}
