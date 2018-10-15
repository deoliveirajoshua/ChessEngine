import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class BruteForceTrainer {

	public static void main(String[] args) throws IOException {
		
		System.out.println("\t\t\t\t\t\t Brute Force Trainer");
		
		Scanner scan = new Scanner(System.in);
		System.out.print("\nEnter depth of search for computer ( >= 0) -> ");
		int depth = scan.nextInt();
		System.out.print("\nEnter no of epochs -> ");
		int epochs = scan.nextInt();
		Random rand = new Random();
		Player White = new Player(true, 'w', "White", 0, 0, 0);
		Player Black = new Player(true, 'b', "Black", 0, 0, 0);
		int gameCounter = 0;
 // Credits for file reading to: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
/*	BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Joshua DeOliveira\\Desktop\\BruteForce.csv"));
		String line = null;
		int[] wCoefs = new int[13];
		int[] bCoefs = new int[13];
		while (( line = br.readLine()) != null) {
				String[] evals = line.split(",");
		        for (int q=0; q<13; q++) {
		        	try {
		        	wCoefs[q] = Integer.parseInt(evals[q]);
		        	}
		        	catch(NumberFormatException e) {
		        		continue;
		        	}
		        	bCoefs[q] = wCoefs[q] + rand.nextInt(11)-5;
		        }           
		}
*/
		int[] wCoefs = {0,50,50,50,50,50,50,50,50,50,50,50,50};
		int[] bCoefs = new int[13];
		for (int q=0; q<13; q++) {
        	bCoefs[q] = wCoefs[q] + rand.nextInt(11)-5;
        }       
		System.out.println(Arrays.toString(wCoefs));
		White.setEvals(wCoefs);
		Black.setEvals(bCoefs);
		boolean mate = false;
		boolean draw = false;
		char winner = 'z';
		while (gameCounter < epochs) {
				White.startNewGame();
				System.out.print(White.getBoard().print());

				mate = false;
				draw = false;
				winner = 'z';
				int counter = 0;
				while( !mate && !draw ) {
					if (counter == 0) {
						System.out.print("\n Game No. " + gameCounter);
					}
					else if (counter%2 == 0 ) {
						System.out.print("\nWhite");
						White.setBoard(Black.getBoard());
					}
					else {
						System.out.print("\nBlack");
						Black.setBoard(White.getBoard());
					}

							if (counter%2 == 0) {
								int[] move = White.evaluate(White.getBoard(), depth);
									White.getBoard().move(move[0], move[1], move[2], move[3]);
									System.out.println(White.getBoard().print());
									draw = White.getBoard().checkDraw(White.getColor());
									mate = White.getBoard().checkMate(White.getColor());
									winner = White.getColor();
							}
							if(counter%2 == 1) {
								int[] move = Black.evaluate(Black.getBoard(), depth);
									Black.getBoard().move(move[0], move[1], move[2], move[3]);
									System.out.println(Black.getBoard().print());
									draw = Black.getBoard().checkDraw(Black.getColor());
									mate = Black.getBoard().checkMate(Black.getColor());
									winner = Black.getColor();
							}
							counter++;
					}
					gameCounter++;
					//Credits for file writing to: https://www.tutorialspoint.com/java/java_filewriter_class.htm
				    FileWriter writer = new FileWriter("C:\\Users\\Joshua DeOliveira\\Desktop\\BruteForce.txt", true); 
				if(mate) {
					System.out.println(winner + " won the match via checkmate! ");
					switch (winner) {
					case 'w': White.addWin(); Black.addLoss();  
						writer.write("\n");
						for(int t=0; t<12; t++) {
							 writer.write(wCoefs[t]);
							 if (t<11) {
								 writer.write(",");
							 }
						}
					break;
					case 'b': White.addLoss(); Black.addWin(); 
						writer.write("\n");
						for(int t=0; t<13; t++) {
							if (t == 0) {
								writer.write(gameCounter);
							}
							if (t<12) {
								writer.write(",");
							}
							writer.write(wCoefs[t]);
							
						}
					break;
					default: break;
					}
				}
				else {
					System.out.println("Oops, seems like " + winner + " caused a stalemate....(draw)");
					White.addDraw(); 
					Black.addDraw();
				}
				
				System.out.println("\nCurrent Standings:\nWhite: " + White.getWins() + " " + White.getLosses() + " " + White.getDraws());
				System.out.println("Black: " + Black.getWins() + " " + Black.getLosses() + " " + Black.getDraws());
				writer.flush();
				writer.close();
		}	
		scan.close();
	//	br.close();
		System.out.println("Thanks for playing! Have a great day");

	}

}
