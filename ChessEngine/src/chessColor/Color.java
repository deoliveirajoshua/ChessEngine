package chessColor;

/**
 * Represents the two colors a piece can have or a player can control in a chess match
 * @author Joshua DeOliveira
 */
public enum Color {
	BLACK, WHITE;

	public static Color SwitchColor(Color color) {
		if(color == Color.WHITE) {
			return Color.BLACK;
		}
			return Color.WHITE;
	}
	
	public static String toString(Color color) {
		if(color.equals(Color.BLACK)) {
			return "Black";
		}
		
		return "White";
	}
	
	/**
	 * Returns 1-Character abbreviation
	 */
	public static String abbrev(Color color) {
		if(color.equals(Color.BLACK)) {
			return "B";
		}
		
		return "W";
	}
	
}