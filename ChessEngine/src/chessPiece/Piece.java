package chessPiece;
import chessBoard.ChessBoard;
import chessBoard.Move;
import java.util.List;

/**
 * Methods that all ChessPiece objects must have, but vary depending on the Piece's Type
 * @author Joshua DeOliveira
 *
 */
public interface Piece {
	/**
	 * Consumes a chess board, and produces a boolean map of all possible 
	 * spots the piece could move, accounting for en-passant, castling, 
	 * check, stale-mate, and the piece's intrinsic move set.
	 * @param board a given chess board
	 * @return a boolean map of all the possible spots the piece could move on a given chess board.
	 */
	public List<Move> possibleMoves(ChessBoard board); 	
}
