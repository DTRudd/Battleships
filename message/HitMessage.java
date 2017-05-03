package battleships.message;

import battleships.Tuple;

public class HitMessage extends Message {

	protected Tuple<Integer,Integer> attackedSquare;
	public HitMessage(Tuple<Integer,Integer> inp) {
		attackedSquare = inp;
	}
	
	public Tuple<Integer,Integer> getAttackedSquare(){
		return attackedSquare;
	}
}
