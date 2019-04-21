package chessPiece;
import chessBoard.ChessBoard;
import chessBoard.Coordinate;
import chessBoard.SimpleChessBoard;
import chessBoard.Move;
import chessColor.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a pawn piece in chess 
 * @author Joshua DeOliveira
 */
public class Queen extends ChessPiece implements Piece {

	/**
	 * The rook characteristics of the queen
	 */
	private final ChessPiece _rook; 
	
	/**
	 * The bishop characteristics of the queen
	 */
	private final ChessPiece _bishop; 
	
	/**
	 * Default constructor 
	 * @param color The color of the piece
	 * @param pieceType The type of piece
	 * @param id The piece's unique identifier
	 */
	public Queen(Color color, PieceType pieceType, int id) {
		super(color, pieceType, id);
		_rook = new Rook(color, PieceType.ROOK, id);
		_bishop = new Rook(color, PieceType.BISHOP, id);
	}

	/**
	 * Constructing a piece on the chess board
	 * @param positionX The piece's x-position on the board (0-7)
	 * @param positionY The piece's y-position on the board (0-7)
	 * @param pieceType Type of the piece
	 * @param color Color of the piece
	 * @param id The piece's unique identifier
	 */
	public Queen(Coordinate coord, PieceType pieceType, Color color, int id) {
		super(coord, pieceType, color, id);
		_rook = new Rook(coord, PieceType.ROOK, color, id);
		_bishop = new Rook(coord, PieceType.BISHOP, color, id);
	}

	/**
	 * Overwrites the position of the piece to a new x position
	 * @param x The piece's new column
	 */
	public void setPositionX(int x) {
		_coord.setNewPos(x, _coord.getPosY());
		_rook.setPositionX(x);
		_bishop.setPositionX(x);
	}
	
	/**
	 * Overwrites the position of the piece to a new y position
	 * @param y The piece's new row
	 */
	public void setPositionY(int y) {
		_coord.setNewPos(_coord.getPosX(), y);
		_rook.setPositionY(y);
		_bishop.setPositionY(y);
	}
	
	/**
	 * Overwrites the position of the piece to a new x and y position
	 * @param x The piece's new column
	 * @param y The piece's new row
	 */
	public void setPosition(int x, int y) {
		_coord = new Coordinate(x,y);
		_rook.setPosition(x, y);
		_bishop.setPosition(x,y);
	}
	
	/**
	 * Consumes a chess board, and produces a boolean map of all possible 
	 * spots the piece could move, accounting for en-passant, castling, 
	 * check, stale-mate, and the piece's intrinsic move set.
	 * @param board a given chess board
	 * @return a boolean map of all the possible spots the piece could move on a given chess board.
	 */
	@Override
	public List<Move> possibleMoves(ChessBoard board) {
		List<Move> available = new LinkedList<Move>();
		List<Move> rook = _rook.possibleMoves(board);
		List<Move> bishop = _bishop.possibleMoves(board);
		
		available.addAll(rook);
		available.addAll(bishop);
		
		return available;
	}

	@Override
	public ChessPiece deepCopy() {
		ChessPiece p = new Queen(_coord.deepCopy(), TYPE, COLOR, ID);
		p._timesMoved = _timesMoved;
		return p;
	}

}
