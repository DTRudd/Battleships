package battleships.message;

import battleships.Tuple;

public class MissMessage extends Message {

	protected Tuple<Integer,Integer> attackedSquare;
	public MissMessage(Tuple<Integer, Integer> inp) {
		attackedSquare = inp;
	}

	public Tuple<Integer,Integer> getAttackedSquare(){
		return attackedSquare;
	}
	
}
