package battleships;

import java.util.Scanner;

public class Battleships {

	public static void main(String[] Args){
		Game g = new Game(12);
		g.printState();
		Scanner sc = new Scanner(System.in);
		g.turnSchedule(true,sc);
	}

}