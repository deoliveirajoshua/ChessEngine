package chessPlayer;
import java.util.Scanner;

import chessBoard.SimpleChessBoard;
import chessBoard.Move;
import chessColor.Color;

/**
 * Represents a human-controlled ChessPlayer
 * @author Joshua DeOliveira
 */
public class HumanPlayer extends ChessPlayer implements Player {

	/**
	 * Default constructor of a new player
	 * @param color The color of the player
	 * @param name The name of the player
	 */
	public HumanPlayer(String name) {
		super(name);
		_playerType = PlayerType.HUMAN;
	}

	/**
	 * Constructor of an existing player
	 * @param color The color of the player
	 * @param name The name of the player
	 * @param wins The player's current number of wins
	 * @param draws The player's current number of draws
	 * @param losses the player's current number of losses
	 */
	public HumanPlayer(String name, int wins, int draws, int losses) {
		super(name, wins, draws, losses);
	}

	/**
	 * Asks the player via scanner to choose a move to perform. If the move is 
	 * valid in reference to the rules of chess, the move will be performed on
	 * the board. If the move is invalid, an alert will be sent to the player 
	 * notifying them the move is invalid and the reason why. The player will
	 * then have as many attempts to move one of their pieces until one of their
	 * moves is finally legal.
	 */
	@Override
	public Move startTurn(SimpleChessBoard aBoard) {
	
		int startX = 9;
		int startY = 9; 
		int endX = 9;
		int endY = 9;
		
		Scanner scan = new Scanner(System.in);
		boolean validMove = false;
		while (!validMove) {
			System.out.print("\nMove a piece from (ex. e4) ->  ");
			String start = scan.next();
			System.out.print("Move the piece to (ex. g7) -> ");
			String spot = scan.next();

			startX = swithFile(start);
			startY = swithFile(start);  
			endX = swithRank(start); 
			endY = swithRank(start); 
			
			if( !(startX == 9 || startY == 9 || endX == 9 || endY == 9) ) {
				validMove = true;
			}
		
		}
		return new Move(startX, startY, endX, endY);
	}
	
	private int swithRank(String str) {
		
		int num = 9;
		switch (str.charAt(1)) {
		case '8': num = 0; break;
		case '7': num = 1; break;
		case '6': num = 2; break;
		case '5': num = 3; break;
		case '4': num = 4; break;
		case '3': num = 5; break;
		case '2': num = 6; break;
		case '1': num = 7; break;
		}
		
		return num;
	}
	
	private int swithFile(String str) {
		
		int num = 9;
		switch (str.charAt(0)) {
		case 'a': num = 0;  break;
		case 'b': num = 1;  break;
		case 'c': num = 2;  break;
		case 'd': num = 3;  break;
		case 'e': num = 4;  break;
		case 'f': num = 5;  break;
		case 'g': num = 6;  break;
		case 'h': num = 7;  break;
		}
	
		return num;
	}
}
