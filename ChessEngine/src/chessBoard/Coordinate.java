package chessBoard;

public class Coordinate implements Comparable {
	
	/**
	 * x position where the piece is currently (0-7)
	*/
	int _xPos;
	
	/**
	 * Y-Pos where the piece is currently (0-7)
	 */
	int _yPos;
	
		
	public Coordinate() {
		_xPos = -1;
		_yPos = -1;
	}
	
	/**
	 * Constructor with given x,y position 
	 */
	public Coordinate (int x, int y) {
		_xPos = x;
		_yPos = y;	
	}
	
	public void setNewPos(int xPos, int yPos) {
		_xPos = xPos;
		_yPos = yPos;
	}
	
	public int getPosX() {
		return _xPos;
	}
	
	public int getPosY() {
		return _yPos;
	}
	
	public Coordinate deepCopy() {
		return new Coordinate(_xPos, _yPos);
	}
	
	public boolean isOnBoard() {
		return _xPos >= 0 && _xPos <= 7 && _yPos >= 0 && _yPos <= 7;
	}
	
	public boolean equals(Coordinate other) {
		return _xPos == other._xPos && _yPos == other._yPos; 
	}
	
	public String toString() {
		if(_xPos == -1 || _yPos == -1) {
			return "null";
		}
		
		return "("+_xPos+","+_yPos+")";
	}

	@Override
	public int compareTo(Object o) {
		if(o == null) {
			throw new NullPointerException("Object was null and could not be compared to Coordinate");
		}
		if(o instanceof Coordinate) {
			int val = 9*_xPos + _yPos;
			int otherVal = 9*((Coordinate) o).getPosX() + ((Coordinate) o).getPosY(); 
			
			if(val == otherVal) {
				return 0;
			}
			else if(val > otherVal) {
				return -1;
			}
			else {
				return 1;
			}
		}
		throw new ClassCastException("Object wasn't a Coordinate and could not be compared");
	}
	
}
