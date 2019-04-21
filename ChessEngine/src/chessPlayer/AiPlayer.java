package chessPlayer;

import chessBoard.SimpleChessBoard;
import chessBoard.Move;
import chessColor.Color;

public class AiPlayer extends ChessPlayer implements Player {

	public AiPlayer(Color color, String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public AiPlayer(Color color, String name, int wins, int draws, int losses) {
		super(name, wins, draws, losses);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move startTurn(SimpleChessBoard aBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}
