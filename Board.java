package battleships;

public class Board {
	private int size;
	private Point[][] grid;
	public Board(int size) {
		this.size = size;
		grid = new Point[size][size];
		for(int ii = 0; ii < size; ii++){
			for (int jj = 0; jj < size; jj++){
				grid[ii][jj] = new Point();
			}
		}
	}
	public Point getPoint(int x, int y) throws ArrayIndexOutOfBoundsException{
		return grid[x][y];
	}
	
	public int getSize(){
		return size;
	}
	
	public String getCoOrds(int x, int y){
		//Assume board never exceeds 26x26
		char xChar = (char) (x + 65);
		StringBuilder sb = new StringBuilder();
		sb.append(xChar);
		sb.append(new Integer(y+1).toString());
		return sb.toString();
	}
	
	public Point getIndices(String humanCoOrds){
		int xCoOrd = (int) humanCoOrds.charAt(0) - 65;
		int yCoOrd = Integer.parseInt(humanCoOrds.substring(1));
		try{
			return grid[xCoOrd][yCoOrd];
		} catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Point not found on game board.  Please try again.");
			return null;
		}
	}
}
