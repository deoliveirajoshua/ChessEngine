package chessBoard;
import chessColor.Color;
import chessPiece.ChessPiece;
import chessPiece.PieceType;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class ChessBoard {

	/**
	 * Holds the pieces currently on the board
	 */
	protected Map<Coordinate, ChessPiece> _pieces;
	
	protected ChessPiece _whiteKing;
	
	protected ChessPiece _blackKing;
	
	public ChessBoard() {
		_pieces = new TreeMap<Coordinate, ChessPiece>();
		_whiteKing = null;
		_blackKing = null;
		startNewGame();
	}
	
	public ChessBoard(List<ChessPiece> pieces) {
		_pieces = new TreeMap<Coordinate, ChessPiece>();
		_whiteKing = null;
		_blackKing = null;
		for(ChessPiece piece : pieces) {
			if(piece.getPieceType().equals(PieceType.KING)) {
				if(piece.getColor().equals(Color.WHITE)) {
					_whiteKing = piece;
				}
				else {
					_blackKing = piece;
				}
			}
			_pieces.put(piece.getCoord(), piece);
		}
	}
	
	/**
	 * Initializes the chess pieces onto the board and stores them in 
	 */
	abstract protected void startNewGame();
	
	

}
