package battleships;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayer extends Player {

	public HumanPlayer(int size) {
		super(size);
	}

	@Override
	public Tuple<Integer, Integer> getAttackVector(Game g, Scanner sc) {
		int xCoord, yCoord;
		while(true){
			System.out.println("Enter your attack vector, commander:");
			String input = sc.next();
			Pattern p = Pattern.compile("^([a-zA-Z])([0-9]+)$");
			Matcher m = p.matcher(input);
			m.matches();
			try{
				yCoord = m.group(1).toLowerCase().charAt(0) - 97;
				xCoord = Integer.parseInt(m.group(2))-1;
				break;
			} catch (IllegalStateException ise){
				System.out.println("I'm sorry commander, but I didn't understand that."); 
				continue;
			}
		}
		return new Tuple<Integer,Integer>(xCoord,yCoord);
	}

	public void placeAll(Game g, Scanner sc){
		for(Ship s : fleet){
			int xCoord, yCoord;
			Orientation dir;
			while(true){
				System.out.println("Commander, we need co-ordinates for our " + s.getClass().getSimpleName().toLowerCase()+"!");
				String input = sc.next();
				Matcher coordMatcher = Pattern.compile("^([a-zA-Z])([0-9]+)$").matcher(input);
				coordMatcher.matches();
				try{
					yCoord = coordMatcher.group(1).toLowerCase().charAt(0) - 97;
					xCoord = Integer.parseInt(coordMatcher.group(2))-1;
					System.out.println("Co-ordinates: " + yCoord + "," + xCoord);
				} catch (IllegalStateException ise){
					System.out.println("I'm sorry commander, but I didn't understand that."); 
					continue;
				}
				System.out.println("And what direction should she face, commander?");
				input = sc.next();
				Matcher dirMatcher = Pattern.compile("^(\\w+)$").matcher(input);
				dirMatcher.matches();
				try{
					switch(dirMatcher.group(1).toUpperCase()){
					case "UP" 	:	dir = Orientation.UP;
									break;
					case "DOWN"	:	dir = Orientation.DOWN;
									break;
					case "LEFT"	:	dir = Orientation.LEFT;
									break;
					case "RIGHT":	dir = Orientation.RIGHT;
									break;
					default:		throw new IllegalStateException();
					}
					place(s,yCoord,xCoord,dir);
					break;
				} catch (IllegalStateException ise){
					System.out.println("I'm sorry commander, but I didn't understand that.");
					continue;
				} catch (ImproperPlacementException | ArrayIndexOutOfBoundsException e){
					 System.out.println("We can't put her there, captain!");
					 continue;
				}
			}
			System.out.println(s.getClass().getSimpleName().toLowerCase() + " in position, commander!");
			g.printState();
			System.out.println();
		}
	}
}
