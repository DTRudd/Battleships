package battleships.ship;

import battleships.player.Player;

public class TestShip extends Ship {

	static int length = 2;
	public TestShip(Player p) {
		super(p);
		intactLength = length;
	}
}
