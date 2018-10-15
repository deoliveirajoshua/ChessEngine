/**
 * An object that holds information about a given chess piece on a chess board, and is primarily used for the board class.
 * @author Joshua DeOliveira
 *
 */
public class Piece {

	//Data
	int posx; // Column Position
	int posy; // Row Position
	char color;	 // Piece color (black or white)
	char type; // Type of piece (king, queen, knight, rook, bishop, or pawn)
	int timesMoved; // The number of times the piece has moved
	int value; // The value of the piece based on its position, type, and how it is being used along with other pieces. Will be utilized in the brute force algorithm
	int id; // Unique identifier for each piece on the chess board
	boolean capture; // Whether the piece is still on the board or not
	boolean movedLast; // Whether this pieces was the last piece to move or not
	boolean hasCastled; // // Whether the king has castled or not 
	//Constructor
	
	//Default Constructor
	public Piece () {
	color = 'w';
	type = 'p';
	}
	
	//Starting Color and Type
	public Piece (char color, char type) {
		this.color = color;
		this.type = type;
		posx = 0;
		posy = 0;
		timesMoved = 0;
		id = 50;
		capture = false;
		movedLast = false;
		hasCastled = false;
		}
	
	//Starting Color and Type and Position
	public Piece (char color, char type, int x, int y, int identifier) {
		posx=x;
		posy=y;
		this.color = color;
		this.type = type;
		timesMoved = 0;
		id = identifier;
		capture = false;
		movedLast = false;
		hasCastled = false;
		}
		
	//Methods
	
	/**
	 * Returns the x-y position of the piece
	 * @return The x-y position of the piece
	 */
	public int[] getPos() {
		int[] list = new int[1];
		list[0]=posx;
		list[1]=posy;
		return list;
	}
	
	/**
	 * Returns the x-position or column of the piece
	 * @return The x-position or column of the piece
	 */
	public int getFile() {
		return posx;
	}
	
	/**
	 * Returns the y-position or row of the piece
	 * @return The y-position or row of the piece
	 */
	public int getRank() {
		return posy;
	}
	
	/**
	 * Returns the color of the piece, w for white and b for black
	 * @return 'w' or 'b' for the color of the piece
	 */
	public char getColor() {
		return color;
	}
	
	/**
	 * Returns the type of piece: 'p' for pawn, 'r' for rook, 'b' for bishop, 'n' for knight, 'k' for king, or 'q' for queen
	 * @return 'p' for pawn, 'r' for rook, 'b' for bishop, 'n' for knight, 'k' for king, or 'q' for queen
	 */
	public char getPiece() {
		return type;
	}
	
	/**
	 * Returns the amount of times the piece has moved on the board
	 * @return The amount of times the piece has moved on the board
	 */
	public int getTimesMoved() {
		return timesMoved;
	}
	
	/**
	 * Returns the unique, numerical identifier of the piece ranging from 0-40.
	 * @return The unique, numerical identifier of the piece ranging from 0-40.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns whether this piece was the piece that moved last.
	 * @return True if it moved last, and false if it didn't
	 */
	public boolean getMovedLast() {
		return movedLast;
	}
	
	/**
	 * Returns whether the king has castled or not
	 * @return Whether the king has castled or not
	 */
	public boolean getHasCastled() {
		return hasCastled;
	}
	
	/**
	 * Increased the amount of times the pieces has moved by 1
	 */
	public void pieceMoved() {
		timesMoved++;
	}
	
	/**
	 * Decreases the amount of time the piece has moved by 1
	 */
	public void pieceMovedBack() {
		timesMoved--;
	}
	
	/**
	 * Overwrites the position of the piece to a new x and y position
	 * @param x The piece's new column/file
	 * @param y The piece's new row/rank
	 */
	public void setPosition(int x, int y) {
		posx= x;
		posy= y;
	}
	
	/**
	 * Overwrites the new state of the piece
	 * @param state True if the piece is captures, and false if the piece is not captured
	 */
	public void setCapture(boolean state ) {
		capture = state;
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	/**
	 * Sets whether the piece moved last or not
	 * @param moved True if the piece moved last and false if the piece didn't move last
	 */
	public void setMovedLast(boolean moved) {
		movedLast = moved;
	}
	
	public void setTimesMoved(int moves) {
		timesMoved = moves;
	}
	
	public void setCastled(boolean castled) {
		hasCastled = castled;
	}
	
	/**
	 * Returns the current state of the piece
	 * @return True if the piece is captured, and false if the piece isn't captured
	 */
	public boolean isCaptured() {
		return capture;
	}
	
	/**
	 * Determines whether the color inputted to the functions is the same as the color of the piece 
	 * @param othercolor The inputted color
	 * @return True if the colors are the same, and false if they are different
	 */
	public boolean isSameColor(char othercolor) {
		if (color == othercolor) {
			return true;
		}
		return false;
	}
	
	/**
	 * Prints information about a given piece for debugging purposes
	 */
	public String toString() {
		
		return "*************\nPiece: " + getPiece() +"\nColor: " + getColor() +"\nFile: " + getFile() 
		+"\nRank: " + getRank() + "\nTimes Moved: " + getTimesMoved(); 
	}
	
}
