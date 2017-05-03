package battleships.ship;

import battleships.player.Player;

public class Destroyer extends Ship {

	public Destroyer(Player p) {
		super(p);
		intactLength = length = 4;
	}
}
