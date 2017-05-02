package battleships;

public class Point {

	private ToEnemy publicStatus;
	private ToPlayer privateStatus;
	private Ship occupyingShip;
	
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
	
	public Ship getOccupyingShip(){
		return occupyingShip;
	}
	
	public void setOccupyingShip(Ship inp){
		occupyingShip = inp;
	}
	
	public ToEnemy defend() throws AttackNotPermittedException {
		if (this.publicStatus != ToEnemy.UNTOUCHED){
			throw new AttackNotPermittedException("Already attacked there.");
		} else {
			if (privateStatus == ToPlayer.EMPTY){
				publicStatus = ToEnemy.MISS;
				return ToEnemy.MISS;
			} else {
				privateStatus = ToPlayer.SHIP_DESTROYED;
				publicStatus = ToEnemy.HIT;
				occupyingShip.setIntactLength(occupyingShip.getIntactLength()-1);
				return ToEnemy.HIT;
			}
		}
	}
	
	public Point() {
		publicStatus = ToEnemy.UNTOUCHED;
		privateStatus = ToPlayer.EMPTY;
	}

}
