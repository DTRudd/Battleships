package battleships;

public class improperPlacementException extends Exception {
	public improperPlacementException(String message){
		super(message);
	}
	public improperPlacementException(){
		super();
	}
	public improperPlacementException(Throwable cause){
		super(cause);
	}
	public improperPlacementException(String message, Throwable cause){
		super(message,cause);
	}
}
