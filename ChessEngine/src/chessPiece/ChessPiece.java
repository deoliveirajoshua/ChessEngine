package chessPiece;
import chessBoard.Coordinate;
import chessColor.Color;

/**
 * An abstract class representing the general attributes of a chess piece
 * @author Joshua DeOliveira
 */
public abstract class ChessPiece implements Piece, Cloneable {
	
	/**
	 * (row, column) position between (0,0) to (7,7)
	 */
	protected Coordinate _coord;
	
	/**
	 * Piece color (BLACK or WHITE)
	 */
	protected final Color COLOR; 
	
	/**
	 * Unique identifier for each piece on the chess board
	 */
	protected final int ID; 
	
	/**
	 * Type of piece (PAWN, ROOK, BISHOP, QUEEN, KING)
	 */
	protected final PieceType TYPE; 
	
	/**
	 * Counts the number of times it has moved
	 */
	protected int _timesMoved;
	
	/**
	 * Default constructor
	 * @param color the piece's color
	 * @param id the piece's unique identifying number
	 */
	public ChessPiece(Color color, PieceType pieceType, int id) {
		COLOR = color;
		ID = id;
		TYPE = pieceType;
		_coord = null;
		_timesMoved = 0;
	}
	
	/**
	 * Constructing a ChessPiece on the SimpleChessBoard
	 * @param positionX The piece's x-position on the board (0-7)
	 * @param positionY The piece's y-position on the board (0-7)
	 * @param pieceType Type of the piece
	 * @param color Color of the piece
	 * @param id The piece's unique identifier
	 */
	public ChessPiece(Coordinate coord, PieceType pieceType, Color color, int id) {
		_coord = coord;
		COLOR = color;
		ID = id;
		TYPE = pieceType;
		_timesMoved = 0;
	}

	/**
	 * Returns the x-position or column of the piece
	 * @return The x-position or column of the piece
	 */
	public int getPosY() {
		return _coord.getPosY();
	}
	
	/**
	 * Returns the y-position or row of the piece
	 * @return The y-position or row of the piece
	 */
	public int getPosX() {
		return _coord.getPosX();
	}

	public Coordinate getCoord() {
		return _coord;
	}
	
	public void setCoord(Coordinate coord) {
		_coord = coord;
	}
	/**
	 * Returns the color of the piece, w for white and b for black
	 * @return 'w' or 'b' for the color of the piece
	 */
	public Color getColor() {
		return COLOR;
	}
	
	public PieceType getPieceType() {
		return TYPE;
	}
	
	/**
	 * Returns the unique, numerical identifier of the piece ranging from 0-40.
	 * @return The unique, numerical identifier of the piece ranging from 0-40.
	 */
	public int getId() {
		return ID;
	}
	
	/**
	 * Overwrites the position of the piece to a new x position
	 * @param x The piece's new column
	 */
	public void setPositionX(int x) {
		_coord.setNewPos(x, _coord.getPosY());
	}
	
	/**
	 * Overwrites the position of the piece to a new y position
	 * @param y The piece's new row
	 */
	public void setPositionY(int y) {
		_coord.setNewPos(_coord.getPosX(), y);
	}
	
	/**
	 * Overwrites the position of the piece to a new x and y position
	 * @param x The piece's new column
	 * @param y The piece's new row
	 */
	public void setPosition(int x, int y) {
		_coord = new Coordinate(x,y);
	}
	
	/**
	 * Returns number of times this piece has moved
	 * @return
	 */
	public int getTimesMoved(){
		return _timesMoved;
	}
	
	/**
	 * Increase times moved by one
	 */
	public void increaseMoveCount() {
		_timesMoved++;
	}
	
	/**
	 * Determines whether the color consumed by the functions is the same as the color of the piece 
	 * @param othercolor The consumed color
	 * @return True if the colors are the same, and false if they are different
	 */
	public boolean isSameColor(Color othercolor) {
		if (COLOR == othercolor) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the consumed piece is equal to this piece object
	 * by comparing their ID's. If the ID's are equal, return true.
	 * Otherwise, return false.
	 * @param ChessPiece the consumed piece to compare.
	 * @return True is the pieces are equal. Otherwise, return false.
	 */
	public boolean isEqual(ChessPiece aPiece) {
		if(ID == aPiece.getId()) {
			return true;
		}
		return false;
	}
	
	public boolean isInSameSpot(ChessPiece aPiece) {
		return _coord.equals(aPiece._coord);
	}
		
	/**
	 * Prints a string representation of the piece object.
	 */
	public String toString() {
		return PieceType.abbrev(TYPE) + Color.abbrev(COLOR);
	}
	
	/**
	 * Prints info about ChessPiece for debugging purposes
	 */
	public String getInfo() {
		return "ID: "+ID+", Type: "+TYPE+", Color: "+COLOR+", Times Moved: "+ _timesMoved+", Coordinates :" + _coord.toString(); 
	}
	
	/**
	 * Creates a deepCopy of the current ChessPiece
	 */
	abstract public ChessPiece deepCopy();
	
}
