package battleships;

public abstract class Ship {

	int length;
	public enum Orientation {UP, DOWN, LEFT, RIGHT};
	public void sink(){
		System.out.println("You've sunk this vessel!");
	}
	
	public int getLength(){
		return length;
	}
	
	public void place(Board board, int x, int y, Orientation dir) throws ImproperPlacementException{
		switch(dir){
		case UP:	for (int ii = 0; ii < length; ii++){
						pointPlace(board,y+ii,x);
					}
					break;
		case DOWN:	for (int ii = 0 ; ii < length; ii++){
						pointPlace(board,y-ii,x);
					}
					break;
		case LEFT:	for (int ii = 0; ii < length; ii++){
						pointPlace(board,y,x-ii);
					}
					break;
		case RIGHT:	for (int ii = 0; ii < length; ii++){
						pointPlace(board,y,x+ii);
					}
					break;
		}
	}
	
	public void pointPlace(Board board, int x, int y) throws ImproperPlacementException{
		if (x >= board.getSize() || y >= board.getSize()){
			throw new ImproperPlacementException("Ship leaves board.");
		} else if (!(board.getPoint(x,y).getPrivateStatus() == ToPlayer.EMPTY)){
			throw new ImproperPlacementException("Ship placement obstructed.");
		} else {
			board.getPoint(x, y).setPrivateStatus(ToPlayer.SHIP_INTACT);
		}
	}

}
