package chessPiece;
import java.util.List;

import chessBoard.ChessBoard;
import chessBoard.Coordinate;
import chessBoard.Move;
import chessBoard.SimpleChessBoard;
import chessColor.Color;

/**
 * Represents a knight piece in chess 
 * @author Joshua DeOliveira
 */
public class Knight extends ChessPiece implements Piece {

	/**
	 * Default Constructor
	 * @param color Color of the piece
	 * @param pieceType Type of the piece
	 * @param id The piece's unique identifier
	 */
	public Knight(Color color, PieceType pieceType, int id) {
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
	public Knight(Coordinate coord, PieceType pieceType, Color color, int id) {
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
		
		int x = _positionX-2;
		int y = _positionY+1;
		if (x > -1) {
			if (y < 8) {
				if (_table[x][y] == null) {
					available[x][y] = true;
				}
				else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
					available[x][y] = true;
				}
			}
			y = _positionY-1;
			if (y > -1) {
				if (_table[x][y] == null) {
					available[x][y] = true;
				}
				else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
					available[x][y] = true;
				}
			}
		}
		
		x = _positionX+2;
		y = _positionY+1;
		if (x < 8) {
			if (y < 8) {
				if (_table[x][y] == null) {
					available[x][y] = true;
				}
				else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())){
					available[x][y] = true;
				}
			}
			y = _positionY-1;
			if (y > -1) {
				if (_table[x][y] == null) {
					available[x][y] = true;
				}
				else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
					available[x][y] = true;
				}
			}
		}
		
		x = _positionX+1;
		y = _positionY+2;
		if (y < 8) {
			if (x < 8) {
				if (_table[x][y] == null) {
					available[x][y] = true;
				}
				else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
					available[x][y] = true;
				}
			}
			x = _positionX-1;
			if (x > -1) {
				if (_table[x][y] == null) {
					available[x][y] = true;
				}
				else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
					available[x][y] = true;
				}
			}
		}
		
		x = _positionX+1;
		y = _positionY-2;
		if (y > -1) {
			if (x < 8) {
				if (_table[x][y] == null) {
					available[x][y] = true;
				}
				else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
					available[x][y] = true;
				}
			}
			x = _positionX-1;
			if (x > -1) {
				if (_table[x][y] == null) {
					available[x][y] = true;
				}
				else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
					available[x][y] = true;
				}
			}
		}
		
		return available;
	}

	@Override
	public ChessPiece deepCopy() {
		ChessPiece p = new Knight(_coord.deepCopy(), TYPE, COLOR, ID);
		p._timesMoved = _timesMoved;
		return p;
	}

}
