package battleships.message;

public class SunkMessage extends Message {

	Class<?> shipClass;
	int length;
	
	public SunkMessage(Class<?> inpClass, int inpLength) {
		shipClass = inpClass;
		length = inpLength;
	}

	public Class<?> getShipClass(){
		return shipClass;
	}
	
	public int getLength(){
		return length;
	}
}
