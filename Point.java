package battleships;

public class Point {

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
	
	public ToEnemy defend() throws AttackNotPermittedException {
		if (this.publicStatus != ToEnemy.UNTOUCHED){
			throw new AttackNotPermittedException("Already attacked there.");
		} else {
			if (this.privateStatus == ToPlayer.EMPTY){
				this.publicStatus = ToEnemy.MISS;
				return ToEnemy.MISS;
			} else {
				this.privateStatus = ToPlayer.SHIP_DESTROYED;
				this.publicStatus = ToEnemy.HIT;
				return ToEnemy.HIT;
			}
		}
	}
	
	public char getPrivateCharStatus(){
		if (this.privateStatus == ToPlayer.EMPTY){
			return '-';
		} else if (this.privateStatus == ToPlayer.SHIP_DESTROYED){
			return 'X';
		} else {
			return 'O';
		}
	}
	
	public Point() {
		publicStatus = ToEnemy.UNTOUCHED;
		privateStatus = ToPlayer.EMPTY;
	}

}
