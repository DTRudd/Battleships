package battleships;

public class ImproperPlacementException extends Exception {
	static final long serialVersionUID = 132125L;
	public ImproperPlacementException(String message){
		super(message);
	}
	public ImproperPlacementException(){
		super();
	}
	public ImproperPlacementException(Throwable cause){
		super(cause);
	}
	public ImproperPlacementException(String message, Throwable cause){
		super(message,cause);
	}
}
