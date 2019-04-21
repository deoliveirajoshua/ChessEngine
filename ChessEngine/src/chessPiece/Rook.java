package chessPiece;
import java.util.List;

import chessBoard.ChessBoard;
import chessBoard.Coordinate;
import chessBoard.Move;
import chessBoard.SimpleChessBoard;
import chessColor.Color;

/**
 * Represents a pawn piece in chess 
 * @author Joshua DeOliveira
 */
public class Rook extends TurnDependantPiece implements Piece {
	
	/**
	 * Default constructor 
	 * @param color The color of the piece
	 * @param pieceType The type of piece
	 * @param id The piece's unique identifier
	 */
	public Rook(Color color, PieceType pieceType, int id) {
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
	public Rook(Coordinate coord, PieceType pieceType, Color color, int id) {
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
		
		
		boolean hit = false;
		int x = _positionX;
		int y = _positionY;
		while (--x > -1 && !hit) {
			if(_table[x][y] == null) {
				available[x][y] = true;
			}	
			else if (!_table[x][y].isSameColor(_table[_positionX][y].getColor())) {
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
		while (++x < 8 && !hit) {
			if(_table[x][y] == null) {
				available[x][y] = true;
			}	
			else if (!_table[x][y].isSameColor(_table[_positionX][y].getColor())) {
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
		while (++y < 8 && !hit) {
			if(_table[x][y] == null) {
				available[x][y] = true;
			}	
			else if (!_table[x][y].isSameColor(_table[_positionX][y].getColor())) {
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
		while (--y > -1 && !hit) {
			if(_table[x][y] == null) {
				available[x][y] = true;
			}	
			else if (!_table[x][y].isSameColor(_table[_positionX][y].getColor())) {
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
		Rook p = new Rook(_coord.deepCopy(), TYPE, COLOR, ID);
		p._timesMoved = _timesMoved;
		p._hasMovedOnce = _hasMovedOnce; 
		p._movedLastTurn = _movedLastTurn;
		return p;
	}

}
