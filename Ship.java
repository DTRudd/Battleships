package battleships;

import java.util.ArrayList;

public abstract class Ship {

	int length;
	int intactLength;
	Player owner;
	
	public Ship(Player p){
		owner = p;
	}
	
	public void sink(){
		System.out.println("You've sunk this vessel!");
	}
	
	public int getLength(){
		return length;
	}
	
	public int getIntactLength(){
		return intactLength;
	}
	
	public void setIntactLength(int len){
		intactLength = len;
		if (intactLength == 0){
			sink();
			owner.getFleet().remove(this);
		}
	}
	
	public void place(Board board, int x, int y, Orientation dir) throws ImproperPlacementException{
		ArrayList<Tuple<Integer,Integer>> points = new ArrayList<Tuple<Integer,Integer>>();
		switch(dir){
		case UP:	for (int ii = 0; ii < length; ii++){
						points.add(new Tuple<Integer,Integer>(x,y+ii));
					}
					break;
		case DOWN:	for (int ii = 0 ; ii < length; ii++){
						points.add(new Tuple<Integer,Integer>(x,y-ii));
					}
					break;
		case LEFT:	for (int ii = 0; ii < length; ii++){
						points.add(new Tuple<Integer,Integer>(x-ii,y));
					}
					break;
		case RIGHT:	for (int ii = 0; ii < length; ii++){
						points.add(new Tuple<Integer,Integer>(x+ii,y));
					}
					break;
		}
		ArrayList<Tuple<Integer,Integer>> placedPoints = new ArrayList<Tuple<Integer,Integer>>();
		try {
			for(Tuple<Integer,Integer> p : points){
				pointPlace(board,p.second(),p.first());
				placedPoints.add(p);
			}
		} catch (ArrayIndexOutOfBoundsException  | ImproperPlacementException e){
			for(Tuple<Integer,Integer> p : placedPoints){
				board.getPoint(p.second(), p.first()).setOccupyingShip(null);
				board.getPoint(p.second(), p.first()).setPrivateStatus(ToPlayer.EMPTY);
			}
			throw new ImproperPlacementException();
		}
	}
	
	public void pointPlace(Board board, int x, int y) throws ImproperPlacementException, ArrayIndexOutOfBoundsException{
		if (x >= board.getSize() || y >= board.getSize()){
			throw new ImproperPlacementException("Ship leaves board.");
		} else if (board.getPoint(x,y).getPrivateStatus() != ToPlayer.EMPTY){
			throw new ImproperPlacementException("Ship placement obstructed.");
		} else {
			board.getPoint(x, y).setPrivateStatus(ToPlayer.SHIP_INTACT);
			board.getPoint(x, y).setOccupyingShip(this);
		}
	}

}
