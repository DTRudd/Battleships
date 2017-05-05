package battleships.ship;

import battleships.player.Player;

public class Submarine extends Ship {

	public Submarine(Player p) {
		super(p);
		intactLength = length = 3;
	}

}
