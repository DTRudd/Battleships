package battleships.ship;

import battleships.player.Player;

public class Carrier extends Ship {

	public Carrier(Player p) {
		super(p);
		intactLength = length = 5;
	}

}
