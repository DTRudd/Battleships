package battleships.ship;

import battleships.player.Player;

public class Submarine extends Ship {

	static int length = 3;
	public Submarine(Player p) {
		super(p);
		intactLength = length;
	}

}
