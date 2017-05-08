package battleships;

import java.util.Arrays;
import java.util.Scanner;
public class Init {

	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		int[] bigAvg = new int[625];
		for (int jj = 0; jj < 625; jj++){
			Game[] games = new Game[8];
			for (int ii = 0; ii < 8; ii++){
				games[ii] = new Game(12);
				//g.printState();
				games[ii].start();
			}
			int[] scores = new int[8];
			for(int ii = 0; ii < 8; ii++){
				try{
					games[ii].join();
					scores[ii] = games[ii].getTurns();
				} catch (Exception e){}
			}
			bigAvg[jj] = Arrays.stream(scores).reduce(Integer::sum).getAsInt() / 8;
			if (jj % 10 == 0) System.out.println(jj);
		}
		System.out.println(Arrays.stream(bigAvg).reduce(Integer::sum).getAsInt() / 625);
		sc.close();
	}

}