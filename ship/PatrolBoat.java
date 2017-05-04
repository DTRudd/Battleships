package battleships.ship;

import battleships.player.Player;

public class PatrolBoat extends Ship {

	static int length = 2;
	public PatrolBoat(Player p) {
		super(p);
		intactLength = length;
	}
}
