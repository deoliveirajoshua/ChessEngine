package chessMatch;
import chessBoard.SimpleChessBoard;
import chessBoard.GameState;
import chessBoard.Move;
import chessColor.Color;
import chessException.IllegalMoveException;
import chessException.OffOfBoardException;
import chessException.PieceNotFoundException;
import chessPlayer.*;

/**
 * Represents a chess match, consisting of two Players and a SimpleChessBoard
 * @author Joshua DeOliveira
 */
public class SimpleChessMatch {

	
	
	/**
	 * The Player who controls the white Pieces
	 */
	private ChessPlayer _whitePlayer;
	
	/**
	 * The Player who controls the black Pieces
	 */
	private ChessPlayer _blackPlayer;
	
	/**
	 * The chess board the game is played on
	 */
	private SimpleChessBoard _board;
	
	/**
	 * Constructing a ChessMatch between two existing ChessPlayers
	 * @param whitePlayer
	 * @param blackPlayer
	 */
	public SimpleChessMatch(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
		_whitePlayer = whitePlayer;
		_blackPlayer = blackPlayer;
		_board = new SimpleChessBoard();
	}
	
	/**
	 * Returns the ChessPlayer controlling the white pieces
	 * @return The ChessPlayer controlling the white pieces
	 */
	public ChessPlayer getWhitePlayer() {
		return _whitePlayer;
	}
	
	/**
	 * Returns the ChessPlayer controlling the black pieces
	 * @return The ChessPlayer controlling the black pieces
	 */
	public ChessPlayer getBlackPlayer() {
		return _blackPlayer;
	}
	
	/**
	 * Begins the chess match
	 */
	public void startMatch() {

	}
	
	
}
