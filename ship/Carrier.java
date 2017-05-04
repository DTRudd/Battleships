package battleships.ship;

import battleships.player.Player;

public class Carrier extends Ship {

	static int length = 5;
	public Carrier(Player p) {
		super(p);
		intactLength = length;
	}
	
}
