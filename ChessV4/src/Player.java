import java.util.ArrayList;
import java.util.Arrays;

/**
 * An object that manipulates the board object to play a chess match against another opponent (human or AI)
 * @author Joshua DeOliveira
 *
 */
public class Player {

	//Data
	boolean playerType; // False for player-controlled, and True for AI-controlled
	int[] evals = {0, 1, 3, 0, 3, 0, 5, 0, 9, 10, 10, 10, 10}; // List of coefficients that the brute-force algorithm will use
	char playerColor ; // 'w' for white, and 'b' for black
	Board board; // The chess board that the player will manipulate
	String name; // The name of the player
	int wins; // The number of times the player has won a match
	int losses; // The number of times the player has lost a match
	int draws; // The number of times the player has drawn a match
	long counter; //Number of searches done by search tree for debugging
	
	//Constructors
	
	//Default constructor
	public Player() {
		playerType = false;
		wins = 0;
		losses = 0;
		draws = 0;
		playerColor = 'w';
		switch (playerColor) {
		case 'w': name = "White"; break;
		case 'b': name = "Black"; break;
		default: break;
		}
	}
	
	//Player type and player color
	public Player(boolean type, char color) {
		playerType = type;
		playerColor = color;
		wins = 0;
		losses = 0;
		draws = 0;
		switch (playerColor) {
		case 'w': name = "White"; break;
		case 'b': name = "Black"; break;
		default: break;
		}
	}
	
	//Player color and player name
	public Player(char color, String name) {
		playerType = false;
		playerColor = color;
		wins = 0;
		losses = 0;
		draws = 0;
		switch (playerColor) {
		case 'w': name = "White"; break;
		case 'b': name = "Black"; break;
		default: break;
		}
	}
	
	//Player type, player color, player name, # of wins, # of losses, # of draws
	public Player(boolean type, char color, String name, int wins, int losses, int draws) {
		playerType = type;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
		playerColor = color;
		this.name = name;
	}
	
	//Player type, player color, player name, list of coefficient the AI will use, # of wins, # of losses, # of draws
	public Player(boolean type, char color, String name, int[] vals, int wins, int losses, int draws) {
		playerType = type;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
		evals = vals;
		playerColor = color;
		this.name = name;
	}
	
	//Methods
	
	/**
	 * Overwrites the color the player can control on the chess board
	 * @param type Desired player color
	 */
	public void setColor(char type) {
		playerColor = type;
	}
	
	/**
	 * Overwrites the name of the player
	 * @param newName Desired new name of the player
	 */
	public void setName (String newName) {
		name = newName;
	}
	
	/**
	 * Overwrites the chess board the player can manipulate
	 * @param b New chess board object
	 */
	public void setBoard(Board b) {
		board = b;
	}
	
	/**
	 * Gives the player a new board with pieces oriented in the correct positions for the beginning of a new game
	 */
	public void startNewGame() {
		board = new Board();
		board.setNewGame();
	}
	
	/**
	 * Increases the number of wins the player has by one
	 */
	public void addWin() {
		wins++;
	}
	
	/**
	 * Increases the number of losses the player has by one
	 */
	public void addLoss() {
		losses++;
	}
	
	/**
	 * Increases the number of draws the player has by one
	 */
	public void addDraw() {
		draws++;
	}
	
	/**
	 * Returns the number of wins the player has
	 * @return The number of wins the player has
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Returns the number of losses the player has
	 * @return The number of losses the player has
	 */
	public int getLosses() {
		return losses;
	}
	
	/**
	 * Returns the number of draws the player has
	 * @return The number of draws the player has
	 */
	public int getDraws() {
		return draws;
	}
	
	/**
	 * Returns the color the player can manipulate
	 * @return The color the player can manipulate
	 */
	public char getColor() {
		return playerColor;
	}
	
	/**
	 * Returns the name of the player
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns whether the player is an algorithm (true) or a human (false)
	 * @return "True" if the move decisions are from an algorithm, and "false" if they are from a human 
	 */
	public boolean getPlayerType() {
		return playerType;
	}
	
	/**
	 * Returns the board object the player currently has
	 * @return The board object the player currently has
	 */
	public Board getBoard() {
		return board;
	}
	
	public void setEvals(int[] coefs) {
		evals = coefs;
	}
	
	public int[] getEvals() {
		return evals;
	}
	/**
	 * Generates a tree of possible moves up to a hard-coded limit, and evaluates the board based on the quality of the move. **Root Node of Mini-Max**
	 * @param board The board that will serve as the root node to the evaluation
	 * @return Two coordinate pairs of the best move from position [file_1,rank_1] to [file_2, rank_2] where file and rank range from 0-7
	 */
	public int[] evaluate(Board board, int depth) {
		System.out.println("\nThinking...");
		counter = 0;
		int[] rootNode = new int[5]; 
		int[] bestMove = new int[4]; 
		System.out.println("Moves");
		ArrayList<Integer> moves = getAllMoves(getBoard(), getColor());
		int max = -10000;
		int i = 0;
		while (i<moves.size()) {
			System.out.println("Cloning");
			Board copy = board.clone();
			try {
			copy.move(moves.get(i), moves.get(++i), moves.get(++i), moves.get(++i));
			}
			catch(NullPointerException e) {
				e.fillInStackTrace();
				throw e;
			}
			i-=3;
			rootNode = MiniMax(depth, copy, moves.get(i), moves.get(++i), moves.get(++i), moves.get(++i), getColor());
			if (max <= rootNode[4]) {
				max = rootNode[4];
				bestMove[0] = rootNode[0];
				bestMove[1] = rootNode[1];
				bestMove[2] = rootNode[2];
				bestMove[3] = rootNode[3];
			}
			++i;
		}
		System.out.println("The search tree looked through " + counter + " possibilites");
		return bestMove;
	}
	
	/**
	 * Performs a simplified min-max (recursive parsing) algorithm to evaluate the best branch generated from the tree in the evaluate() method
	 * Inspired and adapted by Sebastian Lague and his video "Algorithms Explained: Mini-max and alpha-beta pruning"
	 * @return An integer ArrayList where the values are the numbers of children nodes at each layer starting at 0 
	 */
	public int[] MiniMax(int depth, Board b, int inX, int inY, int outX, int outY, char player) {
		 counter++;
		 char otherColor;
		 int[] data = {inX, inY,  outX, outY, b.getScore(getColor(), getEvals())};
		 if (depth <= 0  || b.checkMate(player) || b.checkDraw(player)) {
			 return data;
		 }
		 
		 if(player == 'w') {
				otherColor = 'b';
			}
			else {
				otherColor = 'w';
			}
		 
		 if (getColor() == player) {
			 ArrayList<Integer> moves = getAllMoves(b, otherColor);
			 int min = 10000;
			 int i = 0;
			 int[] data1 = new int[5];
				while (i<moves.size()) {
					Board copy = board.clone();
					int deep = depth-1;
					try {
						copy.move(moves.get(i), moves.get(++i), moves.get(++i), moves.get(++i));
						}
						catch(NullPointerException e) {
							e.fillInStackTrace();
							throw e;
						}
					i-=3;
					data1 = MiniMax(deep, copy, moves.get(i), moves.get(++i), moves.get(++i), moves.get(++i), otherColor);
					if ( min > data1[4]) {
						min = data1[4];
						data[4] = min;
					}
					++i;
				}
				return data;
			}
			else {
				 ArrayList<Integer> moves = getAllMoves(b, getColor());
				 int max = -10000;
				 int i = 0;
				 int[] data1 = new int[5];
					while (i<moves.size()) {
						Board copy = board.clone();
						int deep = depth-1;
						copy.move(moves.get(i), moves.get(++i), moves.get(++i), moves.get(++i));
						i-=3;
						data1 = MiniMax(deep, copy, moves.get(i), moves.get(++i), moves.get(++i), moves.get(++i), getColor());
						if ( max < data1[4]) {
							max = data1[4];
							data[4] = max;
						}
						++i;
					}
					return data;
			}
	}
	
	
	/**
	 * Finds all the moves a given color can make
	 * @param b The current board object
	 * @param player The color of the player
	 * @return An array list of integers in packages of four: initialx, intitialy, spotx, and spoty format (all ranging from 0-7)
	 */
	public ArrayList<Integer> getAllMoves (Board b, char player){
		ArrayList<Integer> allMoves = new ArrayList<Integer>();
		boolean[][] moves = new boolean[8][8];
		for (int i=0; i<b.getPieces().size(); i++) {
				if (!b.getPieces().get(i).isCaptured() && b.getPieces().get(i).getColor() == player) {
					
					try {
						char type = b.pieceAt(b.getPieces().get(i).getFile(), b.getPieces().get(i).getRank() ).getPiece();
					switch (type) {
					case 'p': moves = b.pawnAvailable(b.getPieces().get(i).getFile(), b.getPieces().get(i).getRank()); break;
					case 'r': moves = b.rookAvailable(b.getPieces().get(i).getFile(), b.getPieces().get(i).getRank()); break;
					case 'b': moves = b.bishopAvailable(b.getPieces().get(i).getFile(), b.getPieces().get(i).getRank()); break;
					case 'n': moves = b.knightAvailable(b.getPieces().get(i).getFile(), b.getPieces().get(i).getRank()); break;
					case 'k': moves = b.kingAvailable(b.getPieces().get(i).getFile(), b.getPieces().get(i).getRank()); break;
					case 'q': moves = b.queenAvailable(b.getPieces().get(i).getFile(), b.getPieces().get(i).getRank()); break;
					default: break;
					}
					}
					catch(NullPointerException e){
						e.fillInStackTrace();
						throw e;
					}
					int h = 0;
					final int posx = b.getPieces().get(i).getFile();
					final int posy = b.getPieces().get(i).getRank();
					for (int k=0; k<64;k++) {
						if(moves[k%8][h] && b.canMove(posx, posy, k%8, h, player)) {
							allMoves.add(posx);
							allMoves.add(posy);
							allMoves.add(k%8);
							allMoves.add(h);
							// Debugging -> System.out.println(b.getPieces().get(i).getPiece() + " from " + posx + " " + posy + " to " + k%8 + " " + h);
						}
						if(k%8 == 7) {
							h++; 
						}
					}
					board.getPieces().get(i).setPosition(posx, posy);
				}
			}
		return allMoves;
	}
 	
}
