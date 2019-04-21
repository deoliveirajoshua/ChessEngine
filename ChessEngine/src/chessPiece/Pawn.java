package chessPiece;
import chessBoard.ChessBoard;
import chessBoard.Coordinate;
import chessBoard.Move;
import chessBoard.SimpleChessBoard;
import chessColor.Color;
import java.util.List;

/**
 * Represents a pawn piece in chess 
 * @author Joshua DeOliveira
 */
public class Pawn extends TurnDependantPiece implements Piece {
	
	/**
	 * Default Constructor
	 * @param color Color of the piece
	 * @param pieceType Type of the piece
	 * @param id The piece's unique identifier
	 */
	public Pawn(Color color, PieceType pieceType, int id) {
		super(color, pieceType, id);
	}
	
	/**
	 * Constructing a piece on the chess board
	 * @param positionX The piece's x-position on the board (0-7)
	 * @param positionY The piece's y-position on the board (0-7)
	 * @param pieceType Type of the piece
	 * @param color Color of the piece
	 * @param id The piece's unique identifier
	 */
	public Pawn(Coordinate coord, PieceType pieceType, Color color, int id) {
		super(coord, pieceType, color, id);
	}
	
	
	/**
	 * Consumes a chess board, and produces a boolean map of all possible 
	 * spots the piece could move, accounting for en-passant, castling, 
	 * check, stale-mate, and the piece's intrinsic move set.
	 * @param board a given chess board
	 * @return a boolean map of all the possible spots the piece could move on a given chess board.
	 */
	@Override
	public List<Move> possibleMoves(ChessBoard board) {
		boolean[][] available = new boolean[8][8];
		ChessPiece[][] _table = board.getTable();
		
		if (COLOR == Color.WHITE) {
			if(_positionY > 0 && _table[_positionX][_positionY-1] == null) {
				available[_positionX][_positionY-1] = true;
			}
			if (!hasMovedOnce && _table[_positionX][_positionY-1] == null && _table[_positionX][_positionY-2] == null) {
				available[_positionX][_positionY-2] = true;
			}
			if(_positionY>0 && _positionX>0 && _table[_positionX-1][_positionY-1] != null && _table[_positionX-1][_positionY-1].getColor() == Color.BLACK) {
				available[_positionX-1][_positionY-1] = true;
			}
			if(_positionY>0 && _positionX<7 && _table[_positionX+1][_positionY-1] != null && _table[_positionX+1][_positionY-1].getColor() == Color.BLACK) {
				available[_positionX+1][_positionY-1] = true;
			}
			if(_positionY == 3 && _table[_positionX][_positionY].getPieceType() == PieceType.PAWN) {
				if(_positionX > 0) {
					if(_table[_positionX-1][_positionY] != null && _table[_positionX-1][_positionY].getColor() == Color.BLACK && _table[_positionX-1][_positionY].getPieceType() == PieceType.PAWN && ((Pawn)_table[_positionX-1][_positionY]).getTimesMoved() == 1   && ((Pawn)_table[_positionX-1][_positionY]).movedLastTurn() ) {
						available[_positionX-1][_positionY-1] = true;
					}
				}
				if(_positionX < 7) {
					if(_table[_positionX+1][_positionY] != null && _table[_positionX+1][_positionY].getColor() == Color.BLACK && _table[_positionX+1][_positionY].getPieceType() == PieceType.PAWN && ((Pawn)_table[_positionX+1][_positionY]).getTimesMoved() == 1   && ((Pawn)_table[_positionX+1][_positionY]).movedLastTurn() ) {
						available[_positionX+1][_positionY-1] = true;
					}
				}
			}
		}
		else {
			if(_positionY<7 && _table[_positionX][_positionY+1] == null) {
				available[_positionX][_positionY+1] = true;
			}
			if (!hasMovedOnce && _table[_positionX][_positionY+1] == null && _table[_positionX][_positionY+2] == null) {
				available[_positionX][_positionY+2] = true;
			}
			if(_positionY<7 && _positionX>0 && _table[_positionX-1][_positionY+1] != null && _table[_positionX-1][_positionY+1].getColor() == Color.WHITE) {
				available[_positionX-1][_positionY+1] = true;
			}
			if(_positionY<7 && _positionX<7 && _table[_positionX+1][_positionY+1] != null &&  _table[_positionX+1][_positionY+1].getColor() == Color.WHITE) {
				available[_positionX+1][_positionY+1] = true;
			}
			if(_positionY == 4 && _table[_positionX][_positionY].getPieceType() == PieceType.PAWN) {
				if(_positionX > 0) {
					if(_table[_positionX-1][_positionY] != null && _table[_positionX-1][_positionY].getColor() == Color.WHITE && _table[_positionX-1][_positionY].getPieceType() == PieceType.PAWN && ((Pawn)_table[_positionX-1][_positionY]).getTimesMoved() == 1 && ((Pawn) _table[_positionX-1][_positionY]).movedLastTurn() ) {
						available[_positionX-1][_positionY+1] = true;
					}
				}
				if(_positionX < 7) {
					if(_table[_positionX+1][_positionY] != null && _table[_positionX+1][_positionY].getColor() == Color.WHITE && _table[_positionX+1][_positionY].getPieceType() == PieceType.PAWN && ((Pawn)_table[_positionX+1][_positionY]).getTimesMoved() == 1 && ((Pawn) _table[_positionX+1][_positionY]).movedLastTurn() ) {
						available[_positionX+1][_positionY+1] = true;
					}
				}
			}
		}
		return available;
	}

	@Override
	public ChessPiece deepCopy() {
		Pawn p = new Pawn(_coord.deepCopy(), TYPE, COLOR, ID);
		p._timesMoved = _timesMoved;
		p._hasMovedOnce = _hasMovedOnce; 
		p._movedLastTurn = _movedLastTurn;
		return p;
	}

}
