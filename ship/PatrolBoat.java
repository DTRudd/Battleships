package battleships.ship;

import battleships.player.Player;

public class PatrolBoat extends Ship {

	public PatrolBoat(Player p) {
		super(p);
		intactLength = length = 2;
	}
}
