package battleships;

import java.util.Scanner;
public class Init {

	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		int[] bigAvgTotal = new int[62500];
		for(int ii = 0; ii < 100; ii++){
			Game[] games = new Game[16];
			for(int jj = 0; jj < 16; jj++){
				games[jj] = new Game(12);
				//g.printState();
				games[jj].start();
			}
			int[] turnTotal = new int[16];
			for(int jj = 0; jj < 16; jj++){
				try{
					games[jj].join();
				} catch (Exception e){}
				turnTotal[jj] = games[jj].getTurns();
			}
			int avgTotal = 0;
			for (int jj = 0; jj < 16; jj++){
				avgTotal += turnTotal[jj];
			}
			avgTotal /= 32;
			bigAvgTotal[ii] = avgTotal;
		}
		int finTotal = 0;
		for(int ii = 0; ii < 100; ii++){
			finTotal += bigAvgTotal[ii];
		}
		finTotal /= 100;
		System.out.println("AVERAGE: " + finTotal);
		sc.close();
	}

}