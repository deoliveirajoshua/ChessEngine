package chessPiece;
/**
 * The different varieties of chess pieces that exist
 * @author Joshua DeOliveira
 */
public enum PieceType {
	PAWN, KNIGHT, ROOK, BISHOP, QUEEN, KING;
	
	public static String toString(PieceType type) {
		if(type.equals(PAWN)) {
			return "Pawn";
		}
		else if(type.equals(BISHOP)) {
			return "Bishop";
		}
		else if(type.equals(ROOK)) {
			return "Rook";
		}
		else if(type.equals(KNIGHT)) {
			return "Knight";
		}
		else if(type.equals(QUEEN)) {
			return "Queen";
		}
		else {
			return "King";
		}
	}
	
	public static String abbrev(PieceType type) {
		if(type.equals(PAWN)) {
			return "P";
		}
		else if(type.equals(BISHOP)) {
			return "B";
		}
		else if(type.equals(ROOK)) {
			return "R";
		}
		else if(type.equals(KNIGHT)) {
			return "N";
		}
		else if(type.equals(QUEEN)) {
			return "Q";
		}
		else {
			return "K";
		}
	}
	
}
