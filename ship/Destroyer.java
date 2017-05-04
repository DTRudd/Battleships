package battleships.ship;

import battleships.player.Player;

public class Destroyer extends Ship {

	static int length = 4;
	public Destroyer(Player p) {
		super(p);
		intactLength = length;
	}
}
