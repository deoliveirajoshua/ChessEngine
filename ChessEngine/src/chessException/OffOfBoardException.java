package chessException;
/**
 * Exception thrown if the an (x,y) coordinate is called that is off of the board,
 * which would be any coordinate where the x and y values do not fall 
 * in to the range (0-7) respectively
 * @author Joshua DeOliveira
 */
@SuppressWarnings("serial")
public class OffOfBoardException extends Exception {

	public OffOfBoardException (String message) {
		super(message);
	}
}
