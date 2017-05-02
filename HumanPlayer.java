package battleships;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayer extends Player {

	public HumanPlayer(int size) {
		super(size);
	}

	@Override
	public Tuple<Integer, Integer> getAttackVector() {
		int xCoord;
		int yCoord;
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("Enter your attack vector, commander:");
			String input = sc.next();
			Pattern p = Pattern.compile("^([a-zA-Z])([0-9]+)$");
			try{
				Matcher m = p.matcher(input);
				m.matches();
				yCoord = m.group(1).toLowerCase().charAt(0) - 97;
				xCoord = Integer.parseInt(m.group(2))-1;
				break;
			} catch (IllegalStateException ise){
				System.out.println("I'm sorry commander, but I didn't understand that."); 
				continue;
			}
		}
	//	sc.close();
			return new Tuple<Integer,Integer>(xCoord,yCoord);
		}

	public void placeAll(){
		place(fleet.get(0),0,0,Ship.Orientation.UP);
		place(fleet.get(1),1,1,Ship.Orientation.UP);
		place(fleet.get(2),2,2,Ship.Orientation.UP);
		place(fleet.get(3),3,3,Ship.Orientation.UP);
		place(fleet.get(4),4,4,Ship.Orientation.UP);
		place(fleet.get(5),5,5,Ship.Orientation.UP);
		place(fleet.get(6),6,6,Ship.Orientation.UP);
	}
	
}
