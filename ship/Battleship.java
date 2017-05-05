package battleships.ship;

import battleships.player.Player;

public class Battleship extends Ship {

	public Battleship(Player p) {
		super(p);
		intactLength = length = 3;
	}

}
