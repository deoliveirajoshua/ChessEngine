package chessPlayer;
import chessBoard.ChessBoard;
import chessColor.Color;
import chessBoard.Move;

/**
 *  Methods that all Player objects must have, but vary depending whether the Player is human or computer controlled 
 * @author Joshua DeOliveira
 */
public interface Player {

	/**
	 * The player begins the process of choosing their move during their turn.
	 * Note, it is the player's job to return a legal move.
	 * @param aBoard The consumed board the player can use to make their move
	 * @return A legal Move object
	 */
	public Move startTurn(ChessBoard aBoard, Color color);
	
}
