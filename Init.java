package battleships;

import java.util.Scanner;

public class Init {

	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		Game g = new Game(12,sc);
		g.printState();
		g.start();
		while(g.isAlive()){}
		sc.close();
	}

}