package battleships.message;

public class BeenSunkMessage extends Message {

	Class<?> shipClass;
	public BeenSunkMessage(Class<?> inpClass) {
		shipClass = inpClass;
	}

	public Class<?> getShipClass(){
		return shipClass;
	}
}
