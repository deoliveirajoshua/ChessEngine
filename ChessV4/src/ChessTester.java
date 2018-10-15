import java.util.Scanner;
/**
 * Utilizes the Piece, Board, and Player Class to debug the environment my future AI will run in. Used primarily for debugging methods in the Board class for determining whether a move is legal or not, and if a player is in checkmate or stalemate.
 * @author Joshua DeOliveira
 *
 */
public class ChessTester {

	public static void main(String[] args) {
		
		
		Player Josh = new Player(false,'w');
		Player Opponent = new Player(false, 'b');
		Josh.startNewGame();
		System.out.println(Josh.getBoard());
		Scanner scan = new Scanner(System.in);

		
		boolean moved = false;
		boolean mate = false;
		boolean draw = false;
		char winner = 'z';
		int counter = 0;
		while( !mate && !draw ) {
			if (counter == 0) {
				System.out.print("\nIt is white's turn ");
			}
			else if (counter%2 == 0 ) {
				System.out.print("\nIt is white's turn ");
				Josh.setBoard(Opponent.getBoard());
			}
			else {
				System.out.print("\nIt is black's turn ");
				Opponent.setBoard(Josh.getBoard());
			}
			moved = false;
			while (!moved) {
				System.out.print("\nMove a piece from file (0-7): ");
				int num = scan.nextInt();
				System.out.print("Move a piece from rank (0-7): ");
				int num1 = scan.nextInt();
				System.out.print("Move the piece to file (0-7): ");
				int num2 = scan.nextInt();
				System.out.print("Move the piece to rank (0-7): ");
				int num3 = scan.nextInt();
				if (counter%2 == 0) {
					if (Josh.getBoard().canMove(num, num1, num2, num3, Josh.getColor())) {
						Josh.getBoard().move(num, num1, num2, num3);
						System.out.println(Josh.getBoard());
						moved = true;
						draw = Josh.getBoard().checkDraw(Josh.getColor());
						mate = Josh.getBoard().checkMate(Josh.getColor());
						winner = Josh.getColor();
					}
					else {
						System.out.println("That was an invalid move, try another one"); 
					}
				} 
				else {
					if (Opponent.getBoard().canMove(num, num1, num2, num3, Opponent.getColor())) {
						Opponent.getBoard().move(num, num1, num2, num3);
						System.out.println(Opponent.getBoard());
						moved = true;
						draw = Opponent.getBoard().checkDraw(Opponent.getColor());
						mate = Opponent.getBoard().checkMate(Opponent.getColor());
						winner = Opponent.getColor();
					}
					else {
						System.out.println("That was an invalid move, try another one"); 
					}
				}
			}
			counter++;
			
		}
		
		String str = null;
		switch (winner) {
		case 'w': str = "White"; break;
		case 'b': str = "Black"; break;
		
		default: break;
		}
		if(mate) {
			System.out.println(str + " is the winner via checkmate");
		}
		else {
			System.out.println(str + " drew via stalemate");
		}
		
		scan.close();

	}

}
