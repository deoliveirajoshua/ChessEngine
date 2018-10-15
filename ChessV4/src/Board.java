import java.util.ArrayList;

/**
 * An object that mimics a chess board with piece objects
 * @author Joshua DeOliveira
 *
 */
public class Board {
	
	//Data
	String[][] table; // An 8x8 table full of strings corresponding to piece object
	int eval; // The sum of the sub-evaluation scores from each piece 
	ArrayList<Piece> pieces; // A list of 32 piece objects
	
	
	
	
	//Constructors
	
	//Default
	public Board () {
    table = new String[8][8];
	eval = 0;
    pieces = new ArrayList<Piece>();
    setNewGame();
	}
	
	// Board table and list of pieces
	public Board (String[][] otherTable, ArrayList<Piece> otherPieces) {
	    table = otherTable;	
		eval = 0;
	    pieces = otherPieces;
		}
	
	
	
	//Methods
	
	/**
	 * Returns table of strings corresponding to piece color, piece type, and id corresponding to its index on the list of pieces
	 * @return Table of strings corresponding to piece color, piece type, and id corresponding to its index on the list of pieces
	 */
	public String[][] getBoardStr(){
		return table;
	}
	
	/**
	 * Returns the to evaluation score of the board
	 * @return The to evaluation score of the board
	 */
	public int getScore(char color, int[] coefs) {
		char otherColor;
		int pawn = coefs[1];
		int knight = coefs[2];
		int knightPair = coefs[3];
		int bishop = coefs[4];
		int bishopPair = coefs[5];
		int rook = coefs[6];
		int rookPair = coefs[7];
		int queen = coefs[8];
		int isChecked = coefs[9];
		int madeCheckmate = coefs[10];
		int centerControl = coefs[11];
		int hasCastled = coefs[12];
		int wKnightCounter = 0;
		int wBishopCounter = 0;
		int wRookCounter = 0;
		int bKnightCounter = 0;
		int bBishopCounter = 0;
		int bRookCounter = 0;
		int wSum = 0;
		int bSum = 0;
		int difference = 0;
		if (color == 'w') {
			otherColor = 'b';
		}
		else {
			otherColor = 'w';
		}
		int j = 0;
		int i = 0;
		while(i<64) {
			String str = table[i%8][j];
		if (str != null) {
			if (str == "wp") {
				wSum+=pawn;
			}
			else if (str == "wr") {
				wSum+=rook;
				wRookCounter++;
			}
			else if (str == "wb") {
				wSum+=bishop;
				wBishopCounter++;
			}
			else if (str == "wn") {
				wSum+=knight;
				wKnightCounter++;
			}
			else if (str == "wq") {
				wSum+=queen;
			}
			else if (str == "bp") {
				bSum+=pawn;
			}
			else if (str == "br") {
				bSum+=rook;
				bRookCounter++;
			}
			else if (str == "bb") {
				bSum+=bishop;
				bBishopCounter++;
			}
			else if (str == "bn") {
				bSum+=knight;
				bKnightCounter++;
			}
			else if (str == "bq") {
				bSum+=queen;
			}
			
			if (str != null && str.charAt(0) == color && (i%8 == 3 || i%8 == 4) && (j == 3 || j == 4)) {
				eval +=centerControl; 
			}
		}
			if (i%8==7) {
				j++;
			}
			i++;
		}
		
		if(wKnightCounter == 2) {
			eval+= knightPair;
		}
		if(wBishopCounter == 2) {
			eval+= bishopPair;
		}
		if(wRookCounter == 2) {
			eval+= rookPair;
		}
		if(bKnightCounter == 2) {
			eval+= knightPair;
		}
		if(bBishopCounter == 2) {
			eval+= bishopPair;
		}
		if(bRookCounter == 2) {
			eval+= rookPair;
		}
		
		if (color == 'w') {
			if (pieces.get(28).getHasCastled() == true) {
				eval+= hasCastled;
			}
			difference = wSum - bSum;
		}
		else {
			if (pieces.get(4).getHasCastled() == true) {
				eval+= hasCastled;
			}
			difference = bSum - wSum;
		}
		
		if (isInCheck(otherColor)) {
			eval += isChecked;
			if(checkMate(color)) {
				eval+= madeCheckmate;
			}
		}
		else if(isInCheck(color)) {
			eval-= isChecked;
			if(checkMate(otherColor)) {
				eval-= madeCheckmate;
			}
		}
		eval+=difference;
		return eval;
	}
		
	
	
	public void setScore(int newScore) {
		eval = newScore;
	}
	
	/**
	 * References the pieces located at an file-rank (x-y) position on the board
	 * @param file The file/column on the board
	 * @param rank The rank/row on the board
	 * @return A piece object
	 */
	public Piece pieceAt(int file, int rank) {
		String str = new String();
		str = table[file][rank];
		return pieces.get(Integer.parseInt(str.substring(2)));
	}
	
	/**
	 * Returns an ArrayList of piece objects
	 * @return An ArrayList of piece objects
	 */
	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	
	/**
	 * Overwrites the chess board table with a new table
	 * @param otherTable The new String[][] table of strings corresponding to pieces
	 */
	public void setBoard(String[][] otherTable) {
		table = otherTable;
	}
	
	/**
	 * Orients the table with pieces to start a new game of chess and initializes piece objects in the ArrayList
	 */
	public void setNewGame() {
		
		table[0][7] = "wr24";
		table[1][7] = "wn25";
		table[2][7] = "wb26";
		table[3][7] = "wq27";
		table[4][7] = "wk28";
		table[5][7] = "wb29";
		table[6][7] = "wn30";
		table[7][7] = "wr31";
		table[0][6] = "wp16";	// White Pieces
		table[1][6] = "wp17";
		table[2][6] = "wp18";
		table[3][6] = "wp19";
		table[4][6] = "wp20";
		table[5][6] = "wp21";
		table[6][6] = "wp22";
		table[7][6] = "wp23";
	
		table[0][0] = "br0";
		table[1][0] = "bn1";
		table[2][0] = "bb2";
		table[3][0] = "bq3";
		table[4][0] = "bk4";
		table[5][0] = "bb5";
		table[6][0] = "bn6";
		table[7][0] = "br7";
		table[0][1] = "bp8";	// Black Pieces
		table[1][1] = "bp9";
		table[2][1] = "bp10";
		table[3][1] = "bp11";
		table[4][1] = "bp12";
		table[5][1] = "bp13";
		table[6][1] = "bp14";
		table[7][1] = "bp15";
		
		int i=0;
		for(int j=0; j<64; j++) {
			if(table[j%8][i] != null) {
			pieces.add(new Piece(table[j%8][i].charAt(0), table[j%8][i].charAt(1),j%8, i, j));
			}
			if (j%8 == 7) {
				i++;
			}
		}
	}
	
	/**
	 * Moves a string corresponding to a piece object to a new location on the table and updates the new info to the piece object
	 * @param initialx The piece object's current file/column location 0-7 
	 * @param initialy The piece object's current rank/row location 0-7
	 * @param spotx The file/column (0-7) the piece will move to
	 * @param spoty The rank/row (0-7) the piece will move to
	 */
	public void move(int initialx, int initialy, int spotx, int spoty) {
			for (int i = 0; i < pieces.size(); i++) {
				if (pieces.get(i).getMovedLast()) {
					pieces.get(i).setMovedLast(false);
				}
			}
				
				if( (initialy == 4 || initialy == 3) && table[initialx][initialy].charAt(1) == 'p') {
					if(initialx > 0) {
						if(spotx == initialx-1 && table[spotx][spoty] == null) {
							table[initialx-1][initialy] = null;
						}
					}
					if(initialx < 7) {
						if(spotx == initialx+1 && table[spotx][spoty] == null ) {
							table[initialx+1][initialy] = null;
						}
					}
				}

			if (table[initialx][initialy].contains("wp") && spoty == 0) {
				String str = table[initialx][initialy].substring(2,4);
				table[initialx][initialy] = "wq" + str ;
				pieceAt(initialx, initialy).setType('q');
			}
			else if (table[initialx][initialy].contains("bp") && spoty == 7){
				table[initialx][initialy] = "bq" + pieceAt(initialx, initialy).getId() ;
				pieceAt(initialx, initialy).setType('q');
			}
			pieceAt(initialx, initialy).setPosition(spotx, spoty);
			pieceAt(initialx, initialy).pieceMoved();
			if (table[spotx][spoty] != null) {
				pieceAt(spotx, spoty).setCapture(true);
			}
			table[spotx][spoty] = table[initialx][initialy];
			table[initialx][initialy] = null;
			
			if((spotx-initialx) == 2 && pieceAt(spotx, spoty).getPiece() == 'k') {
				pieceAt(initialx, initialy).setCastled(true);
				move(initialx+3, initialy, initialx+1, initialy);
			}
			if((spotx-initialx) == -2 && pieceAt(spotx, spoty).getPiece() == 'k') {
				pieceAt(initialx, initialy).setCastled(true);
				move(initialx-4, initialy, initialx-1, initialy);
			}

			pieceAt(spotx, spoty).setMovedLast(true);
	}
	
	/**
	 * Duplicates the current table orientation, moves a piece to its desired location, and checks if the hypothetical move will put its own color into check
	 * @param initialx The piece's current file/column (0-7)
	 * @param initialy The piece's current rank/row (0-7)
	 * @param spotx The desired file/column the piece wants to move to
	 * @param spoty The desired rank/row the piece wants to move to
	 * @param color The color of the player
	 * @return True if the move will not but the player into check, and false if the move will be put into check
	 */
	public boolean hypMove(int initialx, int initialy, int spotx, int spoty, char color){
		String[][] t = new String[8][8];
		int j=0;
		for (int i=0; i<64; i++) {
			final String a = table[i%8][j];
			t[i%8][j] = a;
			if(i%8 == 7) {
				j++;
			}
		}
		
		move(initialx, initialy, spotx, spoty);
		if (!isInCheck(color)) {
			table = t;
			if((spotx-initialx) == 2 && pieceAt(initialx, initialy).getPiece() == 'k') {
				pieceAt(initialx+3, initialy).pieceMovedBack();
			}
			else if((spotx-initialx) == -2 && pieceAt(initialx, initialy).getPiece() == 'k') {
				pieceAt(initialx-4, initialy).pieceMovedBack();
			}
			pieceAt(initialx, initialy).pieceMovedBack();
			if (table[spotx][spoty] != null) {
				pieceAt(spotx, spoty).setCapture(false);
			}
			return true;
		}
		table = t;
		if((spotx-initialx) == 2 && pieceAt(initialx, initialy).getPiece() == 'k') {
			pieceAt(initialx+3, initialy).pieceMovedBack();
		}
		else if((spotx-initialx) == -2 && pieceAt(initialx, initialy).getPiece() == 'k') {
			pieceAt(initialx-4, initialy).pieceMovedBack();
		}
		pieceAt(initialx, initialy).pieceMovedBack();
		if (table[spotx][spoty] != null) {
		pieceAt(spotx, spoty).setCapture(false);
		}
		return false; 
	}
	
	
	/**
	 * Determines whether a player can move a piece from one position to another
	 * @param initialx The current file/column of the piece
	 * @param initialy The current rank/row of the piece 
	 * @param spotx The desired file/column to put the piece
	 * @param spoty The desired rank/row to put the piece
	 * @param color The color of the player
	 * @return True if the move is legal, and false if the move isn't legal from the rules of chess
	 */
	public boolean canMove(int initialx, int initialy, int spotx, int spoty, char color) {
		boolean[][] moves = new boolean[8][8];
		if (table[initialx][initialy] != null && pieceAt(initialx, initialy).isSameColor(color)){
			switch (pieceAt(initialx, initialy).getPiece()) {
			case 'p': moves = pawnAvailable(initialx, initialy); break;
			case 'r': moves = rookAvailable(initialx, initialy); break;
			case 'b': moves = bishopAvailable(initialx, initialy); break;
			case 'n': moves = knightAvailable(initialx, initialy); break;
			case 'k': moves = kingAvailable(initialx, initialy); break;
			case 'q': moves = queenAvailable(initialx, initialy); break;
			default: break;
			}
			if (moves[spotx][spoty] && hypMove(initialx, initialy, spotx, spoty, color)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Generates a boolean map of what spots on the board the pawn can move.
	 * @param initialx The pawn's current file/column
	 * @param initialy The pawn's current rank/row
	 * @return A boolean matrix where "true" squares are possible spots to move to
	 */
	public boolean[][] pawnAvailable(int initialx, int initialy){
		boolean[][] available = new boolean[8][8];
		if (pieceAt(initialx, initialy).color == 'w') {
			if(initialy > 0 && table[initialx][initialy-1] == null) {
				available[initialx][initialy-1] = true;
			}
			if (pieceAt(initialx, initialy).getTimesMoved() == 0 && table[initialx][initialy-1] == null && table[initialx][initialy-2] == null) {
				available[initialx][initialy-2] = true;
			}
			if(initialy>0 && initialx>0 && table[initialx-1][initialy-1] != null && pieceAt(initialx-1, initialy-1).color == 'b') {
				available[initialx-1][initialy-1] = true;
			}
			if(initialy>0 && initialx<7 && table[initialx+1][initialy-1] != null && pieceAt(initialx+1, initialy-1).color == 'b') {
				available[initialx+1][initialy-1] = true;
			}
			if(initialy == 3 && pieceAt(initialx, initialy).getPiece() == 'p') {
				if(initialx > 0) {
					if(table[initialx-1][initialy] != null && pieceAt(initialx-1, initialy).color == 'b' && pieceAt(initialx-1, initialy).getPiece() == 'p' && pieceAt(initialx-1, initialy).getTimesMoved() == 1  && pieceAt(initialx-1, initialy).getMovedLast() ) {
						available[initialx-1][initialy-1] = true;
					}
				}
				if(initialx < 7) {
					if(table[initialx+1][initialy] != null && pieceAt(initialx+1, initialy).color == 'b' && pieceAt(initialx+1, initialy).getPiece() == 'p' && pieceAt(initialx+1, initialy).getTimesMoved() == 1 && pieceAt(initialx+1, initialy).getMovedLast()  ) {
						available[initialx+1][initialy-1] = true;
					}
				}
			}
		}
		else {
			if(initialy<7 && table[initialx][initialy+1] == null) {
				available[initialx][initialy+1] = true;
			}
			if (pieceAt(initialx, initialy).getTimesMoved() == 0 && table[initialx][initialy+1] == null && table[initialx][initialy+2] == null) {
				available[initialx][initialy+2] = true;
			}
			if(initialy<7 && initialx>0 && table[initialx-1][initialy+1] != null && pieceAt(initialx-1, initialy+1).color == 'w') {
				available[initialx-1][initialy+1] = true;
			}
			if(initialy<7 && initialx<7 && table[initialx+1][initialy+1] != null && pieceAt(initialx+1, initialy+1).color == 'w') {
				available[initialx+1][initialy+1] = true;
			}
			if(initialy == 4 && pieceAt(initialx, initialy).getPiece() == 'p') {
				if(initialx > 0) {
					if(table[initialx-1][initialy] != null && pieceAt(initialx-1, initialy).color == 'w' && pieceAt(initialx-1, initialy).getTimesMoved() == 1 && pieceAt(initialx-1, initialy).getPiece() == 'p' && pieceAt(initialx-1, initialy).getMovedLast() ) {
						available[initialx-1][initialy+1] = true;
					}
				}
				if(initialx < 7) {
					if(table[initialx+1][initialy] != null && pieceAt(initialx+1, initialy).color == 'w' && pieceAt(initialx-1, initialy).getTimesMoved() == 1 && pieceAt(initialx+1, initialy).getPiece() == 'p' && pieceAt(initialx+1, initialy).getMovedLast()  ) {
						available[initialx+1][initialy+1] = true;
					}
				}
			}
		}
		return available;
	}

	/**
	 * Generates a boolean map of what spots on the board the rook can move to
	 * @param initialx The rook's current file/column
	 * @param initialy The rook's current rank/row
	 * @return A boolean matrix where "true" squares are possible spots to move to
	 */
	public boolean[][] rookAvailable(int initialx, int initialy){
		boolean[][] available = new boolean[8][8];
		int x=initialx;
		int hits=0;
		while ((--x > -1) && (hits<1)) {
			if(table[x][initialy] == null) {
				available[x][initialy] = true;
			}	
			else if (!pieceAt(x, initialy).isSameColor(pieceAt(initialx, initialy).getColor())) {
				hits++;
				available[x][initialy] = true;
			}
			else {
				hits++;
			}
		}
		x=initialx;
		hits=0;
		while (++x < 8 && hits<1) {
			if(table[x][initialy] == null) {
				available[x][initialy] = true;
			}
			else if (!pieceAt(x, initialy).isSameColor(pieceAt(initialx, initialy).getColor())) {
				hits++;
				available[x][initialy] = true;
			}
			else {
				hits++;
			}
		}
		int y = initialy;
		hits=0;
		while (++y < 8 && hits<1) {
			if(table[initialx][y] == null) {
				available[initialx][y] = true;
			}
			else if (!pieceAt(initialx, y).isSameColor(pieceAt(initialx, initialy).getColor())) {
				hits++;
				available[initialx][y] = true;
			}
			else {
				hits++;
			}
		}
		y = initialy;
		hits=0;
		while (--y > -1 && hits<1) {
			if(table[initialx][y] == null) {
				available[initialx][y] = true;
			}
			else if (!pieceAt(initialx, y).isSameColor(pieceAt(initialx, initialy).getColor())) {
				hits++;
				available[initialx][y] = true;
			}
			else {
				hits++;
			}
		}
		return available;
	}
	
	/**
	 * Generates a boolean map of what spots on the board the bishop can move to
	 * @param initialx The bishop's current file/column
	 * @param initialy The bishop's current rank/row
	 * @return A boolean matrix where "true" squares are possible spots to move to
	 */
	public boolean[][] bishopAvailable(int initialx, int initialy){
		boolean[][] available = new boolean[8][8];
		int hits=0;
		int x = initialx;
		int y = initialy;
		while ((--y > -1 && --x > -1) && hits<1) {
			if(table[x][y] == null) {
				available[x][y] = true;
			}
			else if (!pieceAt(x, y).isSameColor(pieceAt(initialx, initialy).getColor())) {
				hits++;
				available[x][y] = true;
			}
			else {
				hits++;
			}
		}
		hits=0;
		x = initialx;
		y = initialy;
		while ((++y < 8 && ++x < 8) && hits<1) {
			if(table[x][y] == null) {
				available[x][y] = true;
			}
			else if (!pieceAt(x, y).isSameColor(pieceAt(initialx, initialy).getColor())) {
				hits++;
				available[x][y] = true;
			}
			else {
				hits++;
			}
		}
		hits=0;
		x = initialx;
		y = initialy;
		while (++y < 8 && --x > -1 && hits<1) {
			if(table[x][y] == null) {
				available[x][y] = true;
			}
			else if (!pieceAt(x, y).isSameColor(pieceAt(initialx, initialy).getColor())) {
				hits++;
				available[x][y] = true;
			}
			else {
				hits++;
			}
				
		}
		hits=0;
		x = initialx;
		y = initialy;
		while ((--y > -1 && ++x < 8) && hits<1) {
			if(table[x][y] == null) {
				available[x][y] = true;
			}
			else if (!pieceAt(x, y).isSameColor(pieceAt(initialx, initialy).getColor())) {
				hits++;
				available[x][y] = true;
			}
			else {
				hits++;
			}
		}
		return available;
	}
	
	/**
	 * Generates a boolean map of what spots on the board the knight can move to
	 * @param initialx The knight's current file/column
	 * @param initialy The knight's current rank/row
	 * @return A boolean matrix where "true" squares are possible spots to move to
	 */
	public boolean[][] knightAvailable(int initialx, int initialy){
		boolean[][] available = new boolean[8][8];
		
		if (initialx-2 > -1) {
			if (initialy+1 < 8) {
				if (table[initialx-2][initialy+1] == null) {
					available[initialx-2][initialy+1] = true;
				}
				else if (!pieceAt(initialx-2, initialy+1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx-2][initialy+1] = true;
				}
			}
			if (initialy-1 > -1) {
				if (table[initialx-2][initialy-1] == null) {
					available[initialx-2][initialy-1] = true;
				}
				else if (!pieceAt(initialx-2, initialy-1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx-2][initialy-1] = true;
				}
			}
		}
		
		if (initialx+2 < 8) {
			if (initialy+1 < 8) {
				if (table[initialx+2][initialy+1] == null) {
					available[initialx+2][initialy+1] = true;
				}
				else if (!pieceAt(initialx+2, initialy+1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx+2][initialy+1] = true;
				}
			}
			if (initialy-1 > -1) {
				if (table[initialx+2][initialy-1] == null) {
					available[initialx+2][initialy-1] = true;
				}
				else if (!pieceAt(initialx+2, initialy-1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx+2][initialy-1] = true;
				}
			}
		}
		
		if (initialy+2 < 8) {
			if (initialx+1 < 8) {
				if (table[initialx+1][initialy+2] == null) {
					available[initialx+1][initialy+2] = true;
				}
				else if (!pieceAt(initialx+1, initialy+2).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx+1][initialy+2] = true;
				}
			}
			if (initialx-1 > -1) {
				if (table[initialx-1][initialy+2] == null) {
					available[initialx-1][initialy+2] = true;
				}
				else if (!pieceAt(initialx-1, initialy+2).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx-1][initialy+2] = true;
				}
			}
		}
		
		if (initialy-2 > -1) {
			if (initialx+1 < 8) {
				if (table[initialx+1][initialy-2] == null) {
					available[initialx+1][initialy-2] = true;
				}
				else if (!pieceAt(initialx+1, initialy-2).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx+1][initialy-2] = true;
				}
			}
			if (initialx-1 > -1) {
				if (table[initialx-1][initialy-2] == null) {
					available[initialx-1][initialy-2] = true;
				}
				else if (!pieceAt(initialx-1, initialy-2).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx-1][initialy-2] = true;
				}
			}
		}
		
		return available;
	}
	
	
	/**
	 * Generates a boolean map of what spots on the board the king can move to
	 * @param initialx The king's current file/column
	 * @param initialy The king's current rank/row
	 * @return A boolean matrix where "true" squares are possible spots to move to
	 */
	public boolean[][] kingAvailable(int initialx, int initialy){
		boolean[][] available = new boolean[8][8];
		
		if(initialx-1 > -1) {
			if (table[initialx-1][initialy] == null) {
				available[initialx-1][initialy] = true;
			}
			else if (!pieceAt(initialx-1, initialy).isSameColor(pieceAt(initialx, initialy).getColor())) {
				available[initialx-1][initialy] = true;
			}
			
			if (initialy+1 < 8) {
				if (table[initialx-1][initialy+1] == null) {
					available[initialx-1][initialy+1] = true;
				}
				else if (!pieceAt(initialx-1, initialy+1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx-1][initialy+1] = true;
				}
			}
			
			if (initialy-1 > -1) {
				if (table[initialx-1][initialy-1] == null) {
					available[initialx-1][initialy-1] = true;
				}
				else if (!pieceAt(initialx-1, initialy-1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx-1][initialy-1] = true;
				}
			}
		}
		
		if(initialx+1 < 8) {
			if (table[initialx+1][initialy] == null) {
				available[initialx+1][initialy] = true;
			}
			else if (!pieceAt(initialx+1, initialy).isSameColor(pieceAt(initialx, initialy).getColor())) {
				available[initialx+1][initialy] = true;
			}
			
			if (initialy+1 < 8) {
				if (table[initialx+1][initialy+1] == null) {
					available[initialx+1][initialy+1] = true;
				}
				else if (!pieceAt(initialx+1, initialy+1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx+1][initialy+1] = true;
				}
			}
			
			if (initialy-1 > -1) {
				if (table[initialx+1][initialy-1] == null) {
					available[initialx+1][initialy-1] = true;
				}
				else if (!pieceAt(initialx+1, initialy-1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx+1][initialy-1] = true;
				}
			}
		}
		
		if(initialy+1 < 8) {
			if (table[initialx][initialy+1] == null) {
				available[initialx][initialy+1] = true;
			}
			else if (!pieceAt(initialx, initialy+1).isSameColor(pieceAt(initialx, initialy).getColor())) {
				available[initialx][initialy+1] = true;
			}
			
			if (initialx+1 < 8) {
				if (table[initialx+1][initialy+1] == null) {
					available[initialx+1][initialy+1] = true;
				}
				else if (!pieceAt(initialx+1, initialy+1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx+1][initialy+1] = true;
				}
			}
			
			if ((-1+initialy) > -1) {
				if (table[initialx-1][initialy+1] == null) {
					available[initialx-1][initialy+1] = true;
				}
				else if (!pieceAt(initialx-1, initialy+1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx-1][initialy+1] = true;
				}
			}
		}
		
		if(initialy-1 > -1) {
			if (table[initialx][initialy-1] == null) {
				available[initialx][initialy-1] = true;
			}
			else if (!pieceAt(initialx, initialy-1).isSameColor(pieceAt(initialx, initialy).getColor())) {
				available[initialx][initialy-1] = true;
			}
			
			if (initialx+1 < 8) {
				if (table[initialx+1][initialy-1] == null) {
					available[initialx+1][initialy-1] = true;
				}
				else if (!pieceAt(initialx+1, initialy-1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx+1][initialy-1] = true;
				}
			}
			
			if ((initialx-1) > 0) {
				if (table[initialx-1][initialy-1] == null) {
					available[initialx-1][initialy-1] = true;
				}
				else if (!pieceAt(initialx-1, initialy-1).isSameColor(pieceAt(initialx, initialy).getColor())) {
					available[initialx-1][initialy-1] = true;
				}
			}
		}
		
		if (pieceAt(initialx, initialy).getTimesMoved() == 0) {
			if(table[initialx+1][initialy] == null && table[initialx+2][initialy] == null && pieceAt(initialx+3,initialy).getPiece() == 'r' && pieceAt(initialx+3,initialy).getTimesMoved() == 0  &&  hypMove(initialx, initialy, initialx+1, initialy, pieceAt(initialx, initialy).getColor()) && hypMove(initialx, initialy, initialx+2, initialy, pieceAt(initialx, initialy).getColor()) && !isInCheck(pieceAt(initialx, initialy).getColor())    )    {
				available[initialx+2][initialy] = true;
			}
			if(table[initialx-1][initialy] == null && table[initialx-2][initialy] == null && table[initialx-3][initialy] == null && pieceAt(initialx-4,initialy).getPiece() == 'r' && pieceAt(initialx-4,initialy).getTimesMoved() == 0  &&  hypMove(initialx, initialy, initialx-1, initialy, pieceAt(initialx, initialy).getColor()) && hypMove(initialx, initialy, initialx-2, initialy, pieceAt(initialx, initialy).getColor()) && !isInCheck(pieceAt(initialx, initialy).getColor())  )    {
				available[initialx-2][initialy] = true;
			}
		}
		
		return available;
	}

	
	/**
	 * Generates a boolean map of what spots on the board the queen can move to
	 * @param initialx The queen's current file/column
	 * @param initialy The queen's current rank/row
	 * @return A boolean matrix where "true" squares are possible spots to move to
	 */
	public boolean[][] queenAvailable(int initialx, int initialy){
		boolean[][] available = new boolean[8][8];
		boolean[][] available1 = rookAvailable(initialx, initialy);
		boolean[][] available2 = bishopAvailable(initialx, initialy);
		int j=0;
		for (int i=0; i<64;i++) {
			if(available1[i%8][j] || available2[i%8][j]) {
				available[i%8][j] = true;
			}
			if(i%8 == 7) {
				j++;
			}
		}
		return available;
	}

	/**
	 * Determines whether a given color side is in check
	 * @param playerColor The player's color
	 * @return True if the color inputted is in check, and false if the inputted color isn't in check
	 */
	public boolean isInCheck(char playerColor) {
		boolean[][] moves = new boolean[8][8];
		char otherColor;
		int kingIndex;
		int kingx;
		int kingy;
		if (playerColor == 'w') {
			otherColor = 'b';
			kingIndex = 28;
		}
		else {
			otherColor = 'w';
			kingIndex = 4;
		}
		kingx = pieces.get(kingIndex).getFile();
		kingy = pieces.get(kingIndex).getRank();
		for (int i=0; i<pieces.size(); i++) {
			if (!pieces.get(i).isCaptured() && pieces.get(i).getColor() == otherColor) {
				
				try {
					char type = pieceAt(pieces.get(i).getFile(), pieces.get(i).getRank() ).getPiece();
				switch (type) {
				case 'p': moves = pawnAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
				case 'r': moves = rookAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
				case 'b': moves = bishopAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
				case 'n': moves = knightAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
				case 'k': moves = kingAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
				case 'q': moves = queenAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
				default: break;
				}
				}
				catch(NullPointerException e){
					e.fillInStackTrace();
					throw e;
				}
				int j = 0;
				for (int k=0; k<64;k++) {
					if((k%8 == kingx) && (j == kingy) && moves[k%8][j]) {
						return true;
					}
					if(k%8 == 7) {
						j++;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the inputted color put the other color into a draw
	 * @param color The color that may have put the other color into a stalemate
	 * @return True if the inputted color caused a stalemate, and false if the inputted color didn't cause a stalemate
	 */
	public boolean checkDraw(char color) {
		char otherColor;
		
		if (color == 'w') {
			otherColor = 'b';
		}
		else {
			otherColor = 'w';
		}
		
		if ( isInCheck(otherColor)) {
			return false;
		}
		else {
	
			boolean[][] moves = new boolean[8][8];
			for (int i=0; i<pieces.size(); i++) {
				if(!pieces.get(i).isSameColor(color) && !pieces.get(i).isCaptured() ) {
					try {
						char type = pieceAt(pieces.get(i).getFile(), pieces.get(i).getRank() ).getPiece();
					switch (type) {
					case 'p': moves = pawnAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'r': moves = rookAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'b': moves = bishopAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'n': moves = knightAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'k': moves = kingAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'q': moves = queenAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					default: break;
					}
					}
					catch(NullPointerException e){
						e.fillInStackTrace();
						throw e;
					}
					int j = 0;
					for (int k=0; k<64;k++) {
						if( moves[k%8][j] ) {
							return false;
						}
						if(k%8 == 7) {
							j++;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks if the inputted color put the other color into a checkmate
	 * @param color The color that may have put the other color into a checkmate
	 * @return True if the inputted color checkmated the other color, and false if the inputted color didn't cause a checkmate
	 */
	public boolean checkMate(char color) {
		boolean[][] moves = new boolean[8][8];
		char otherColor;
		int kingIndex;
		int kingx;
		int kingy;
		if (color == 'w') {
			otherColor = 'b';
			kingIndex = 4;
		}
		else {
			otherColor = 'w';
			kingIndex = 28;
		}
		
		if (!isInCheck(otherColor)) {
			return false;
		}
		else {
			
		
			kingx = pieces.get(kingIndex).getFile();
			kingy = pieces.get(kingIndex).getRank();
			moves = kingAvailable(pieces.get(kingIndex).getFile(), pieces.get(kingIndex).getRank());
			int j = 0;
			for (int k=0; k<64;k++) {
				if( moves[k%8][j] && hypMove(kingx, kingy, k%8, j, otherColor) ) {
					return false;
				}
				if(k%8 == 7) {
					j++;
				}
			}
			
			for (int i=0; i<pieces.size(); i++) {
				if (!pieces.get(i).isCaptured() && pieces.get(i).getColor() == otherColor) {
					
					try {
						char type = pieceAt(pieces.get(i).getFile(), pieces.get(i).getRank() ).getPiece();
					switch (type) {
					case 'p': moves = pawnAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'r': moves = rookAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'b': moves = bishopAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'n': moves = knightAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'k': moves = kingAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					case 'q': moves = queenAvailable(pieces.get(i).getFile(), pieces.get(i).getRank()); break;
					default: break;
					}
					}
					catch(NullPointerException e){
						e.fillInStackTrace();
						throw e;
					}
					int h = 0;
					for (int k=0; k<64;k++) {
						if(moves[k%8][h] && canMove(pieces.get(i).getFile(), pieces.get(i).getRank(), k%8, h, otherColor)) {
							return false;
						}
						if(k%8 == 7) {
							h++;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * Prints a diagram of the board to the console for debugging purposes
	 */
	public String toString() {
		System.out.println("\n");
		int j=-1;
		for(int i=0; i<72; i++) {
			if(i==0) {
				System.out.print("\t       ");
			}
			if (i<8) {
				System.out.print(i + "     ");
			}
			else {
				if(table[i%8][j] == null) {
					System.out.print("----  ");
				}
				else {
					System.out.print(table[i%8][j] +"  " );
					if (table[i%8][j].length() == 3) {
						System.out.print(" " );
					}
				}
			}
			if (i%8 == 7 && j<7) {
				System.out.println("\n");
				System.out.print("\t " + ++j + "    ");
	
			}
		}
		return "\n\n";
	}
	
	
	/**
	 * Prints a polished version of the toString method with proper file and rank labels and removes piece id's
	 * @return The diagram of the board to the console
	 */
	public String print() {
		System.out.println("\n");
		int j=-1;
		int r=9;
		for(int i=0; i<72; i++) {
			if(i==0) {
				System.out.print("\t       ");
			}
			if (i<8) {
				switch (i) {
				case 0: System.out.print("a     ");  break;
				case 1: System.out.print("b     ");  break;
				case 2: System.out.print("c     ");  break;
				case 3: System.out.print("d     ");  break;
				case 4: System.out.print("e     ");  break;
				case 5: System.out.print("f     ");  break;
				case 6: System.out.print("g     ");  break;
				case 7: System.out.print("h      ");  break;
				default: break;
				}
			}
			else {
				if(table[i%8][j] == null) {
					System.out.print("----  ");
				}
				else {
					System.out.print(" " + table[i%8][j].substring(0, 2) +"   " );
				}
			}
			if (i%8 == 7 && j<7) {
				System.out.println("\n");
				System.out.print("\t " + --r + "    ");
				++j;
			}
		}
		return "\n\n";
	}
	
	
	public Board clone() {
		Board b = new Board();
		String[][] t = new String[8][8];
		int j=0;
		for (int i=0; i<64; i++) {
			final String a = table[i%8][j];
			t[i%8][j] = a;
			if(i%8 == 7) {
				j++;
			}
		}
		int i = 0;
		while (i<32) {
			final int file = pieces.get(i).getFile();
			final int rank = pieces.get(i).getRank();
			final boolean capped = pieces.get(i).isCaptured();
			final int moves = pieces.get(i).getTimesMoved();
			final boolean last = pieces.get(i).getMovedLast();
			final boolean castle = pieces.get(i).getHasCastled();
			b.getPieces().get(i).setPosition(file, rank);
			b.getPieces().get(i).setCapture(capped);
			b.getPieces().get(i).setMovedLast(last);
			b.getPieces().get(i).setTimesMoved(moves);
			b.getPieces().get(i).setCastled(castle);
			++i;
		}
		return b;
	}
}
