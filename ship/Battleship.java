package battleships.ship;

import battleships.player.Player;

public class Battleship extends Ship {

	static int length = 3;
	public Battleship(Player p) {
		super(p);
		intactLength = length;
	}

}
