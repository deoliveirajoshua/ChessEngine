package chessPiece;
import chessBoard.Coordinate;
import chessColor.Color;

public abstract class TurnDependantPiece extends ChessPiece {
	
	/**
	 * Whether this piece moved last turn
	 */
	protected boolean _movedLastTurn;
	
	/**
	 * Whether this piece has moved once;
	 */
	protected boolean _hasMovedOnce;

	public TurnDependantPiece(Color color, PieceType pieceType, int id) {
		super(color, pieceType, id);
		_movedLastTurn = false;
		_hasMovedOnce = false;
	}
	
	public TurnDependantPiece (Coordinate coord, PieceType pieceType, Color color, int id) {
		super(coord, pieceType, color, id);
		_movedLastTurn = false;
		_hasMovedOnce = false;
	}
	
	public void setMovedOnce() {
		_hasMovedOnce = true;
	}
	
	public boolean hasMovedOnce() {
		return _hasMovedOnce;
	}
	
	public boolean hasmovedLastTurn(){
		return _movedLastTurn;
	}

}
