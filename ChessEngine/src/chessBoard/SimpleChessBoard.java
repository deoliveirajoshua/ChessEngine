package chessBoard;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import chessColor.Color;
import chessException.*;
import chessPiece.*;

/**
 * Represents a chess board that contains thirty two ChessPieces.
 * This object manages all Piece-to-Piece-level interactions 
 * holds the current board position
 * @author Joshua DeOliveira
 */
public class SimpleChessBoard extends ChessBoard {
	
	/**
	 * Default constructor
	 */
	public SimpleChessBoard() {
		super();
	}
	
	/**
	 * Constructor for making a board based off of a list of given pieces
	 * @param pieces
	 */
	public SimpleChessBoard(List<ChessPiece> pieces) {
		super(pieces);
	}
	
	/**
	 * Creates all the piece objects necessary t start a game
	 * of chess and maps them to their correct position 
	 */
	 protected void startNewGame() {
		
		for(int j = 0; j < 8; j++) { //ID's 0-7  -> White Pawns
			Coordinate coord = new Coordinate(j, 1);
			_pieces.put(coord, new Pawn(coord, PieceType.PAWN, Color.WHITE, j));
		}
		for(int i = 0; i <8; i++) { //ID's 8-15 -> Black Pawns
			Coordinate coord = new Coordinate(i, 6);
			_pieces.put(coord, new Pawn(coord, PieceType.PAWN, Color.BLACK, i+7));
		}
		
		//White Back-Line
		_pieces.put(new Coordinate(0,0), new Rook(new Coordinate(0,0), PieceType.ROOK, Color.WHITE, 16)); //Q-Rook
		_pieces.put(new Coordinate(1,0), new Knight(new Coordinate(1,0), PieceType.KNIGHT, Color.WHITE, 17)); //Q-Knight
		_pieces.put(new Coordinate(2,0), new Bishop(new Coordinate(2,0), PieceType.BISHOP, Color.WHITE, 18)); //Q-Bishop
		_pieces.put(new Coordinate(3,0), new Queen(new Coordinate(3,0), PieceType.QUEEN, Color.WHITE, 19)); //Queen
		_whiteKing = new King(new Coordinate(4,0), PieceType.KING, Color.WHITE, 20);
		_pieces.put(new Coordinate(4,0), _whiteKing); //King
		_pieces.put(new Coordinate(5,0), new Bishop(new Coordinate(5,0), PieceType.BISHOP, Color.WHITE, 21)); //K-Bishop
		_pieces.put(new Coordinate(6,0), new Knight(new Coordinate(6,0), PieceType.KNIGHT, Color.WHITE, 22)); //K-Knight
		_pieces.put(new Coordinate(7,0), new Rook(new Coordinate(7,0), PieceType.ROOK, Color.WHITE, 23)); //K-Rook
		
		//Black Back-Line
		_pieces.put(new Coordinate(0,7), new Rook(new Coordinate(0,7), PieceType.ROOK, Color.BLACK, 24)); //Q-Rook
		_pieces.put(new Coordinate(1,7), new Knight(new Coordinate(1,7), PieceType.KNIGHT, Color.BLACK, 25)); //Q-Knight
		_pieces.put(new Coordinate(2,7), new Bishop(new Coordinate(2,7), PieceType.BISHOP, Color.BLACK, 26)); //Q-Bishop
		_pieces.put(new Coordinate(3,7), new Queen(new Coordinate(3,7), PieceType.QUEEN, Color.BLACK, 27)); //Queen
		_blackKing = new King(new Coordinate(4,7), PieceType.KING, Color.BLACK, 28);
		_pieces.put(new Coordinate(4,7), _blackKing); //King
		_pieces.put(new Coordinate(5,7), new Bishop(new Coordinate(5,7), PieceType.BISHOP, Color.BLACK, 29)); //K-Bishop
		_pieces.put(new Coordinate(6,7), new Knight(new Coordinate(6,7), PieceType.KNIGHT, Color.BLACK, 30)); //K-Knight
		_pieces.put(new Coordinate(7,7), new Rook(new Coordinate(7,7), PieceType.ROOK, Color.BLACK, 31)); //K-Rook
	}
	
	/**
	 * Returns a list of all active ChessPiece objects
	 * @return A list of all active ChessPiece objects 
	 */
	public List<ChessPiece> getPieces() {
		List<ChessPiece> listOfPieces = new LinkedList<ChessPiece>();
		for(ChessPiece p : _pieces.values()) {
			listOfPieces.add(p);
		}
		return listOfPieces;
	}
	
	/**
	 * Consumes a given color on the board, black or white, and 
	 * returns true if the given color is currently in check. Otherwise,
	 * return false.
	 * @param color The color to be verified if they are in check.
	 * @return True if the given color is currently in check. Otherwise,
	 * returns false.
	 */
	private boolean isInCheck(Color color) {
		//TODO Implement this
		return false;	
	}
	
	/**
	 * Consumes a given color on the board, black or white, and 
	 * returns true if the given color is currently in check-mate. 
	 * Otherwise, return false.
	 * @param color The color to be verified if they are in check.
	 * @return True if the given color is currently in check-mate. Otherwise,
	 * returns false.
	 */
	private boolean isInCheckMate(Color color) {
		//TODO Implement this
		return false;
	}
	
	/**
	 * Verifies whether the current match is in a stale-mate.
	 * @return True if the match is in a stale-mate, and returns
	 * false otherwise.
	 */
	private boolean isInStaleMate() {
		//TODO Implement this
		return false;
	}
	
	/**
	 * Consumes a starting (x,y) coordinate ranging from (0,0) to (7,7) that a piece is 
	 * supposedly located. If the start or end (x,y) coordinates are not on the board, 
	 * a OffOfBoard exception is thrown. If a piece isn't in the start coordinates, a 
	 * PieceNotFound exception is thrown. If the coordinates are both within range and 
	 * a piece is present in the starting location, the method checks whether the piece 
	 * moving from the start location to the desired location is a valid move in reference
	 * to the rules of chess. If the move is valid, the piece will be moved and any other 
	 * modifications to the board (captures, castling, etc.) will be handled. If the move 
	 * isn't valid, a IllegalMoveException will be thrown.
	 * @throws IllegalMoveException 
	 * @throws OffOfBoardException 
	 */
	public void requestMove(Move request, Color color) throws IllegalMoveException, OffOfBoardException {
		GameState state = isLegalMove(request, color);
		if(!state.equals(GameState.ILLEGAL)) {
			move(request, state);
		}
	}
	
	/**
	 * Verifies whether the piece at start (x,y) can legally move to end (x,y) without violating
	 * any of the rules of chess. Returns true if the piece can legally move to end (x,y) and 
	 * returns false otherwise.
	 * @return True if the piece can legally move to end (x,y) and returns false otherwise.
	 */
	public GameState isLegalMove(Move moveToVal, Color color)  {
		return GameState.ACTIVE; 
	}
	
	public GameState getState(Color color) {
		if(isInCheck(color)) {
			return GameState.CHECK;
		}
		if(isInCheckMate(color)) {
			return GameState.CHECKMATE;
		}
		if(isInStaleMate()) {
			return GameState.STALEMATE;
		}
		return GameState.ACTIVE;
	}
	
	/**
	 * Moves the piece at the start (x,y) to the end (x,y), and checks for any other
	 * modifications to the board (captures, castling, etc.) as necessary. 
	 */
	private void move(Move move, GameState state) {
		if(state.equals(GameState.CASTLE)) { //A castling move
			castleHandler(move);
		}
		else if(state.equals(GameState.ENPASSENT)) { //An en-passent
			enPassentHandler(move);
		}
		else if(state.equals(GameState.PROMOTION)) { //A pawn promotion 
			promotionHandler(move);
		}
		else { //State is ACTIVE
			normalMoveHandler(move);
		}
	}
	
	/**
	 * Checks whether the move is also a castle. If it is a castle, return true; 
	 * otherwise, return false.
	 * @return True if it is a castle, false otherwise.
	 */
	private boolean isACastle(Move move) {
		//TODO Implement this.
		return false;
	}
	
	private void castleHandler(Move move) {
		//TODO Implement this
	}
	
	/**
	 * Checks whether the move is also an en-passent. If it is an en-passent, return true; 
	 * otherwise, return false.
	 * @return True if it is an en-passent, false otherwise.
	 */
	private boolean isEnPassent(Move move) {
		//TODO Implement this.
		return false;
	}
	
	private void enPassentHandler(Move move) {
		//TODO Implement this
	}
	
	/**
	 * Checks whether the move is also an en-passent. If it is an en-passent, return true; 
	 * otherwise, return false.
	 * @return True if it is an en-passent, false otherwise.
	 */
	private boolean isPawnPromotion(Move move) {
		//TODO Implement this.
		return false;
	}
	
	private boolean promotionHandler(Move move) {
		//TODO Implement this.
		return false;
	}
	
	private boolean normalMoveHandler(Move move) {
		//TODO Implement this.
		return false;
	}
	
	/**
	 * Generates a deep copy of the current SimpleChessBoard
	 * @return A deep copy of the current SimpleChessBoard
	 */
	public SimpleChessBoard deepCopy() {
		List<ChessPiece> list = new LinkedList<ChessPiece>();
		
		for(ChessPiece p : _pieces.values()) {
			list.add(p.deepCopy());
		}
		
		return new SimpleChessBoard(list);
	}
	
	/**
	 * Produces a string representation of the board in a 
	 * traditional 8x8 chess board with labeled pieces.
	 * @return A string representation of the current chess board.
	 */
	public String toString() {
		String str = new String();
		Coordinate coor =  new Coordinate(0,7);
		
		for(int i = 81; i > 0; i--) {
			
			if(i%9 == 0 && i > 9) { //Rows 1-8
				Integer k = (i/9)-1;
				str = str.concat(k.toString()+ "  ");
			}
			else if(i/9 == 0 || i == 9) { //Letters A-H
				str = str.concat(switchLetter(i)+"   "); 
			}
			else {
				try {
					str = str.concat("  " +_pieces.get(coor).toString() +"   ");
				}
				catch(NullPointerException  e) {
					str = str.concat(" ----  ");
				}
				if(coor._xPos < 7) {
					coor.setNewPos(coor.getPosX()+1, coor.getPosY());
				}
				else {
					coor.setNewPos(0, coor.getPosY()-1);
					str = str.concat("\n\n");
					if(!coor.equals(new Coordinate(7,0))){
						str = str.concat("\n");
					}
				}
			}
		}
		
		return str;
	}
	
	private String switchLetter(int num) {
		if(num == 9) {
			return "  ";
		}
		else if(num == 8) {
			return "A";
		}
		else if(num == 7) {
			return "   B";
		}
		else if(num == 6) {
			return "   C";
		}
		else if(num == 5) {
			return "   D";
		}
		else if(num == 4) {
			return "   E";
		}
		else if(num == 3) {
			return "   F";
		}
		else if(num == 2) {
			return "   G";
		}
		else {
			return "   H";
		}
	}
	
	public static void main(String[] args) {
		ChessBoard b = new SimpleChessBoard();
		System.out.println(b);
	}
}
