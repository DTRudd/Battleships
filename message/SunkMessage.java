package battleships.message;

public class SunkMessage extends Message {

	Class<?> shipClass;
	public SunkMessage(Class<?> inpClass) {
		shipClass = inpClass;
	}

	public Class<?> getShipClass(){
		return shipClass;
	}
}
