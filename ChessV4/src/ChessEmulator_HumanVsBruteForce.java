import java.util.Random;
import java.util.Scanner;
/**
 * Utilizes the Piece, Board, and Player Class to create an environment where either two human players, one human and one algorithm, or two algorithms can play a full game of chess. In this version, the algorithm uses a brute force method to generate a recursive tree that evaluates the advantageous-ness of performing each move. For more information on this conventional chess algorithm, see Minimax algorithms and alpha-beta pruning.
 * @author Joshua DeOliveira
 * Note: Each is symbolically represented by the first character being the piece character: white (w) or black (b); and with the second character bing the piece type: rook (r), pawn (p), bishop (b), knight (n), queen (q), and king (k)
 */
public class ChessEmulator_HumanVsBruteForce {

public static void main(String[] args) {
		
		System.out.println("\t\t\t\t\t\t Welcome to Josh DeOliveira's Chess Engine");
		System.out.println("\t\t\t\t\t\t  Human vs. Brute Force Algorithm Edition\n");
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter white's name -> ");
		String nameWhite = scan.nextLine();
		System.out.print("\nEnter white's player (type \"false\" for human and \"true\" for computer) -> ");
		Boolean styleWhite = scan.nextBoolean();
		System.out.print("\nEnter black's name -> ");
		String nameBlack = scan.next();
		System.out.print("\nEnter blacks's player (type \"false\" for human and \"true\" for computer) -> ");
		Boolean styleBlack = scan.nextBoolean();
		System.out.print("\nEnter depth of search for computer ( >= 0) -> ");
		int depth = scan.nextInt();
		
		Player White = new Player(styleWhite, 'w', nameWhite, 0, 0, 0);
		Player Black = new Player(styleBlack, 'b', nameBlack, 0, 0, 0);
		
		boolean playAgain = true;
		while (playAgain) {
			playAgain = false;
				White.startNewGame();
				System.out.print(White.getBoard().print());
			
				boolean moved = false;
				boolean mate = false;
				boolean draw = false;
				char winner = 'z';
				int counter = 0;
				while( !mate && !draw ) {
					if (counter == 0) {
						System.out.print("\n" + White.getName() + " (white) will start off the game");
					}
					else if (counter%2 == 0 ) {
						System.out.print("\nIt is " + White.getName() + "'s turn (white)");
						White.setBoard(Black.getBoard());
					}
					else {
						System.out.print("\nIt is " + Black.getName() + "'s turn (black)");
						Black.setBoard(White.getBoard());
					}
					moved = false;
					while (!moved) {
						if ( (counter%2 == 0 && !White.getPlayerType()) || (counter%2 == 1 && !Black.getPlayerType())) {
							
							System.out.print("\nMove a piece from (ex. e4) ->  ");
							String start = scan.next();
							System.out.print("Move the piece to (ex. g7) -> ");
							String spot = scan.next();
			
							int num = 9;
							int num1 = 9;
							int num2 = 9; 
							int num3 = 9;
							
							switch (start.charAt(0)) {
							case 'a': num = 0;  break;
							case 'b': num = 1;  break;
							case 'c': num = 2;  break;
							case 'd': num = 3;  break;
							case 'e': num = 4;  break;
							case 'f': num = 5;  break;
							case 'g': num = 6;  break;
							case 'h': num = 7;  break;
							default: break;
							}
							
							switch (start.charAt(1)) {
							case '8': num1 = 0;  break;
							case '7': num1 = 1;  break;
							case '6': num1 = 2;  break;
							case '5': num1 = 3;  break;
							case '4': num1 = 4;  break;
							case '3': num1 = 5;  break;
							case '2': num1 = 6;  break;
							case '1': num1 = 7;  break;
							default: break;
							}
							
							switch (spot.charAt(0)) {
							case 'a': num2 = 0;  break;
							case 'b': num2 = 1;  break;
							case 'c': num2 = 2;  break;
							case 'd': num2 = 3;  break;
							case 'e': num2 = 4;  break;
							case 'f': num2 = 5;  break;
							case 'g': num2 = 6;  break;
							case 'h': num2 = 7;  break;
							default: break;
							}
							
							switch (spot.charAt(1)) {
							case '8': num3 = 0;  break;
							case '7': num3 = 1;  break;
							case '6': num3 = 2;  break;
							case '5': num3 = 3;  break;
							case '4': num3 = 4;  break;
							case '3': num3 = 5;  break;
							case '2': num3 = 6;  break;
							case '1': num3 = 7;  break;
							default: break;
							}
							
							if (counter%2 == 0) {
								if (num != 9 && num1 != 9 && num2 != 9 && num3 != 9 && White.getBoard().canMove(num, num1, num2, num3, White.getColor())) {
									White.getBoard().move(num, num1, num2, num3);
									System.out.println(White.getBoard().print());
									moved = true;
									draw = White.getBoard().checkDraw(White.getColor());
									mate = White.getBoard().checkMate(White.getColor());
									winner = White.getColor();
								}
								else {
									Random randy = new Random();
									System.out.println("");
									switch (randy.nextInt(5)) {
									case 0: System.out.println("That was an invalid move, try another one");  break;
									case 1: System.out.println("Zoinks! Looks like that isn't a valid move");  break;
									case 2: System.out.println("Try again, but with a more valid move pls");  break;
									case 3: System.out.println("That's no legal move I've ever heard of...");  break;
									case 4: System.out.println("As stated in the official chess rule book in chapter " + randy.nextInt(100)+1 +", article " + randy.nextInt(500)+1 + ", section " + randy.nextInt(1000)+1 + ", you can't move there"); break;
									default: break;
									}
									
								}
							} 
							else {
								if (num != 9 && num1 != 9 && num2 != 9 && num3 != 9 && Black.getBoard().canMove(num, num1, num2, num3, Black.getColor())) {
									Black.getBoard().move(num, num1, num2, num3);
									System.out.println(Black.getBoard().print());
									moved = true;
									draw = Black.getBoard().checkDraw(Black.getColor());
									mate = Black.getBoard().checkMate(Black.getColor());
									winner = Black.getColor();
								}
								else {
									Random randy = new Random();
									System.out.println("");
									switch (randy.nextInt(5)) {
									case 0: System.out.println("That was an invalid move, try another one");  break;
									case 1: System.out.println("Zoinks! Looks like that isn't a valid move");  break;
									case 2: System.out.println("Try again, but with a more valid move pls");  break;
									case 3: System.out.println("That's no legal move I've ever heard of...");  break;
									case 4: System.out.println("As stated in the official chess rule book in chapter " + randy.nextInt(100)+1 +", article " + randy.nextInt(500)+1 + ", section " + randy.nextInt(1000)+1 + ", you can't move there"); break;
									default: break;
									}
								}
							}
						}
						else {
							if (counter%2 == 0) {
								int[] move = White.evaluate(White.getBoard(), depth);
									White.getBoard().move(move[0], move[1], move[2], move[3]);
									System.out.println(White.getBoard().print());
									moved = true;
									draw = White.getBoard().checkDraw(White.getColor());
									mate = White.getBoard().checkMate(White.getColor());
									winner = White.getColor();
							}
							else if(counter%2 == 1) {
								int[] move = Black.evaluate(Black.getBoard(), depth);
								
									Black.getBoard().move(move[0], move[1], move[2], move[3]);
									System.out.println(Black.getBoard().print());
									moved = true;
									draw = Black.getBoard().checkDraw(Black.getColor());
									mate = Black.getBoard().checkMate(Black.getColor());
									winner = Black.getColor();
							}
							else {
								System.out.println("That was an invalid move, try another one");
							}
						}
					}
					counter++;
					
				}
				
				String name = null;
				switch (winner) {
				case 'w': name = White.getName(); break;
				case 'b': name = Black.getName(); break;
				default: break;
				}
				if(mate) {
					System.out.println(name + " won the match via checkmate! ");
					switch (winner) {
					case 'w': White.addWin(); Black.addLoss(); break;
					case 'b': White.addLoss(); Black.addWin(); break;
					default: break;
					}
				}
				else {
					System.out.println("Oops, seems like " + name + " caused a stalemate....(draw)");
					White.addDraw(); 
					Black.addDraw();
				}
				
				
				String str1;
				String str2;
				String str3;
				String str4;
				String str5;
				String str6;
				if(White.getWins() == 1) {
					str1 = "win, ";
				}
				else {
					str1 = "wins, ";
				}
				if(White.getLosses() == 1) {
					str2 = "loss, ";
				}
				else {
					str2 = "losses, ";
				}
				if(White.getDraws() == 1) {
					str3 = "draw, ";
				}
				else {
					str3 = "draws, ";
				}
				if(Black.getWins() == 1) {
					str4 = "win, ";
				}
				else {
					str4 = "wins, ";
				}
				if(Black.getLosses() == 1) {
					str5 = "loss, ";
				}
				else {
					str5 = "losses, ";
				}
				if(Black.getDraws() == 1) {
					str6 = "draw.";
				}
				else {
					str6 = "draws.";
				}
				
				System.out.println("\nCurrent Standings:\nWhite has " + White.getWins() + " " + str1 + White.getLosses() + " " + str2 + "and " + White.getDraws() + " " + str3);
				System.out.println("Black has " + Black.getWins() + " " + str4 + Black.getLosses() + " " + str5 + "and " + Black.getDraws() + " " + str6);
				
				System.out.print("\nDo you want to play again? (yes/no) -> ");
				String answer = scan.next();
				if (answer.equals("yes")) {
					playAgain = true;
				}
		}
		
		scan.close();
		System.out.println("Thanks for playing! Have a great day");

	}

}

