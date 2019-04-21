package chessBoard;

/**
 * An object representing a chess move
 * @author Joshua DeOliveira
 *
 */
public class Move {

	/**
	 * Starting coordinate of move
	 */
	private Coordinate _start;
	
	/**
	 * Ending coordinate of the move
	 */
	private Coordinate _end;
	
	/**
	 * Default constructor
	 */
	public Move(int startX, int startY, int endX, int endY) {
		_start = new Coordinate(startX, startY);
		_end = new Coordinate(endX, endY);
	}
	
	/**
	 * Constructor with pre-instantiated Coordinates
	 */
	public Move(Coordinate start, Coordinate end) {
		_start = start;
		_end = end;
	}
	
	/**
	 * Returns the current x-pos (0-7)
	 * @return the current x-pos (0-7)
	 */
	public int getStartX() {
		return _start._xPos;
	}
	
	/**
	 * Returns the desired x-coordinate the piece will move to (0-7)
	 * @return the desired x-coordinate the piece will move to (0-7)
	 */
	public int getEndX() {
		return _end._xPos;
	}
	
	/**
	 * Returns the current y-pos (0-7)
	 * @return the current y-pos (0-7)
	 */
	public int getStartY() {
		return _start._yPos;
	}
	
	/**
	 * Returns the desired y-coordinate the piece will move to (0-7)
	 * @return the desired y-coordinate the piece will move to (0-7)
	 */
	public int getEndY() {
		return _end._yPos;
	}
	
	public boolean equals(Move m) {
		return _start.equals(m._start) && _end.equals(m._end);
	}
	
	public Move deepCopy() {
		return new Move(_start.deepCopy(), _end.deepCopy());
	}
}
