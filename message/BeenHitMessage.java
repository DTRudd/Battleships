package battleships.message;

import battleships.Tuple;

public class BeenHitMessage extends Message {

	Tuple<Integer,Integer> attackedSquare;
	public BeenHitMessage(Tuple<Integer,Integer> inp) {
		attackedSquare = inp;
	}

	public Tuple<Integer,Integer> getAttackedSquare(){
		return attackedSquare;
	}
}
