package chessPiece;
import java.util.LinkedList;
import java.util.List;

import chessBoard.ChessBoard;
import chessBoard.Coordinate;
import chessBoard.Move;
import chessBoard.SimpleChessBoard;
import chessColor.Color;

/**
 * Represents a Bishop piece in chess 
 * @author Joshua DeOliveira
 */
public class Bishop extends ChessPiece implements Piece {

	/**
	 * Default constructor
	 * @param color Color of the piece
	 * @param pieceType Type of the piece
	 * @param id The piece's unique identifier
	 */
	public Bishop(Color color, PieceType pieceType, int id) {
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
	public Bishop(Coordinate coord, PieceType pieceType, Color color, int id) {
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
		List<Move> moves = new LinkedList<Move>();
		
		
		boolean hit = false;
		int x = _positionX;
		int y = _positionY;
		while ((--y > -1 && --x > -1) && !hit) {
			if(_table[x][y] == null) {
				available[x][y] = true;
			}
			else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
				hit = true;
				available[x][y] = true;
			}
			else {
				hit = true;
			}
		}
		
		
		hit = false;
		x = _positionX;
		y = _positionY;
		while ((++y < 8 && ++x < 8) && !hit) {
			if(_table[x][y] == null) {
				available[x][y] = true;
			}
			else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
				hit = true;
				available[x][y] = true;
			}
			else {
				hit = true;
			}
		}
		
		
		hit = false;
		x = _positionX;
		y = _positionY;
		while (++y < 8 && --x > -1 && !hit) {
			if(_table[x][y] == null) {
				available[x][y] = true;
			}
			else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
				hit = true;
				available[x][y] = true;
			}
			else {
				hit = true;
			}
		}
		
		
		hit = false;
		x = _positionX;
		y = _positionY;
		while ((--y > -1 && ++x < 8) && !hit) {
			if(_table[x][y] == null) {
				available[x][y] = true;
			}
			else if (!_table[x][y].isSameColor(_table[_positionX][_positionY].getColor())) {
				hit = true;
				available[x][y] = true;
			}
			else {
				hit = true;
			}
		}
		
		return available;
	}

	@Override
	public ChessPiece deepCopy() {
		ChessPiece p = new Bishop(_coord.deepCopy(), TYPE, COLOR, ID);
		p._timesMoved = _timesMoved;
		return p;
	}

}
