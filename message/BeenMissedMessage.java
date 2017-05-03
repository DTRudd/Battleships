package battleships.message;

import battleships.Tuple;

public class BeenMissedMessage extends Message {

	protected Tuple<Integer,Integer> attackedSquare;
	
	public BeenMissedMessage(Tuple<Integer,Integer> inp) {
		attackedSquare = inp;
	}
	
	public Tuple<Integer,Integer> getAttackedSquare(){
		return attackedSquare;
	}
}