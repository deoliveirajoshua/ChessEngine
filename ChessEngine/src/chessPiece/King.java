package chessPiece;
import java.util.List;

import chessBoard.ChessBoard;
import chessBoard.Coordinate;
import chessBoard.Move;
import chessBoard.SimpleChessBoard;
import chessColor.Color;

/**
 * Represents a king piece in chess 
 * @author Joshua DeOliveira
 */
public class King extends TurnDependantPiece implements Piece {

	/**
	 * Default constructor 
	 * @param color The color of the piece
	 * @param pieceType The type of piece
	 * @param id The piece's unique identifier
	 */
	public King(Color color, PieceType pieceType, int id) {
		super(color, pieceType, id);
		_hasMovedOnce = false;
	}

	/**
	 * Constructing a piece on the chess board
	 * @param positionX The piece's x-position on the board (0-7)
	 * @param positionY The piece's y-position on the board (0-7)
	 * @param pieceType Type of the piece
	 * @param color Color of the piece
	 * @param id The piece's unique identifier
	 */
	public King(Coordinate coord, PieceType pieceType, Color color, int id) {
		super(coord, pieceType, color, id);
		_hasMovedOnce = false;	
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
		ChessPiece[][] table = board.getTable();
		
		
		if(_positionX-1 > -1) {
			if (table[_positionX-1][_positionY] == null) {
				available[_positionX-1][_positionY] = true;
			}
			else if (!table[_positionX-1][_positionY].isSameColor(table[_positionX][_positionY].getColor())) {
				available[_positionX-1][_positionY] = true;
			}
			
			if (_positionY+1 < 8) {
				if (table[_positionX-1][_positionY+1] == null) {
					available[_positionX-1][_positionY+1] = true;
				}
				else if (!table[_positionX-1][_positionY+1].isSameColor(table[_positionX][_positionY].getColor())) {
					available[_positionX-1][_positionY+1] = true;
				}
			}
			
			if (_positionY-1 > -1) {
				if (table[_positionX-1][_positionY-1] == null) {
					available[_positionX-1][_positionY-1] = true;
				}
				else if (!table[_positionX-1][_positionY-1].isSameColor(table[_positionX][_positionY].getColor())) {
					available[_positionX-1][_positionY-1] = true;
				}
			}
		}
		
		if(_positionX+1 < 8) {
			if (table[_positionX+1][_positionY] == null) {
				available[_positionX+1][_positionY] = true;
			}
			else if (!table[_positionX+1][_positionY].isSameColor(table[_positionX][_positionY].getColor())) {
				available[_positionX+1][_positionY] = true;
			}
			
			if (_positionY+1 < 8) {
				if (table[_positionX+1][_positionY+1] == null) {
					available[_positionX+1][_positionY+1] = true;
				}
				else if (table[_positionX+1][_positionY+1].isSameColor(table[_positionX][_positionY].getColor())) {
					available[_positionX+1][_positionY+1] = true;
				}
			}
			
			if (_positionY-1 > -1) {
				if (table[_positionX+1][_positionY-1] == null) {
					available[_positionX+1][_positionY-1] = true;
				}
				else if (!table[_positionX+1][_positionY-1].isSameColor(table[_positionX][_positionY].getColor())) {
					available[_positionX+1][_positionY-1] = true;
				}
			}
		}
		
		if(_positionY+1 < 8) {
			if (table[_positionX][_positionY+1] == null) {
				available[_positionX][_positionY+1] = true;
			}
			else if (!table[_positionX][_positionY+1].isSameColor(table[_positionX][_positionY].getColor())) {
				available[_positionX][_positionY+1] = true;
			}
			
			if (_positionX+1 < 8) {
				if (table[_positionX+1][_positionY+1] == null) {
					available[_positionX+1][_positionY+1] = true;
				}
				else if (!table[_positionX+1][_positionY+1].isSameColor(table[_positionX][_positionY].getColor())) {
					available[_positionX+1][_positionY+1] = true;
				}
			}
			
			if ((-1+_positionY) > -1) {
				if (table[_positionX-1][_positionY+1] == null) {
					available[_positionX-1][_positionY+1] = true;
				}
				else if (!table[_positionX-1][_positionY+1].isSameColor(table[_positionX][_positionY].getColor())) {
					available[_positionX-1][_positionY+1] = true;
				}
			}
		}
		
		if(_positionY-1 > -1) {
			if (table[_positionX][_positionY-1] == null) {
				available[_positionX][_positionY-1] = true;
			}
			else if (!table[_positionX][_positionY-1].isSameColor(table[_positionX][_positionY].getColor())) {
				available[_positionX][_positionY-1] = true;
			}
			
			if (_positionX+1 < 8) {
				if (table[_positionX+1][_positionY-1] == null) {
					available[_positionX+1][_positionY-1] = true;
				}
				else if (!table[_positionX+1][_positionY-1].isSameColor(table[_positionX][_positionY].getColor())) {
					available[_positionX+1][_positionY-1] = true;
				}
			}
			
			if ((_positionX-1) > 0) {
				if (table[_positionX-1][_positionY-1] == null) {
					available[_positionX-1][_positionY-1] = true;
				}
				else if (!table[_positionX-1][_positionY-1].isSameColor(table[_positionX][_positionY].getColor())) {
					available[_positionX-1][_positionY-1] = true;
				}
			}
		}
		
		if (!_hasMovedOnce) {
			if(table[_positionX+1][_positionY] == null && table[_positionX+2][_positionY] == null && table[_positionX+3][_positionY].getPieceType().equals(PieceType.ROOK) && !((Rook) table[_positionX+3][_positionY]).getMovedOnce())  {
				available[_positionX+2][_positionY] = true;
			}
			if(table[_positionX-1][_positionY] == null && table[_positionX-2][_positionY] == null && table[_positionX-3][_positionY].getPieceType().equals(PieceType.ROOK) && !((Rook) table[_positionX+3][_positionY]).getMovedOnce())  {
				available[_positionX-2][_positionY] = true;
			}
		}
	
		return available;
	}

	@Override
	public ChessPiece deepCopy() {
		King p = new King(_coord.deepCopy(), TYPE, COLOR, ID);
		p._timesMoved = _timesMoved;
		p._hasMovedOnce = _hasMovedOnce; 
		p._movedLastTurn = _movedLastTurn;
		return p;
	}

}
