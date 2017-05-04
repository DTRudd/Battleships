package battleships.message;

public class SunkMessage extends Message {

	Class<?> shipClass;
	int length;
	
	public SunkMessage(Class<?> inpClass) {
		shipClass = inpClass;
	}

	public Class<?> getShipClass(){
		return shipClass;
	}
	
	public int getLength(){
		return length;
	}
}
