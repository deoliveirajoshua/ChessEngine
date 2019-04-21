package chessException;
/**
 * An exception that is thrown if the user attempts to perform an illegal move
 * by violating a one of the rules of chess
 * @author Joshua DeOliveira
 */
@SuppressWarnings("serial")
public class IllegalMoveException extends Exception{

	public IllegalMoveException (String message) {
		super(message);
	}
}
