package chessPlayer;
import java.util.ArrayList;
import java.util.List;

import chessBoard.SimpleChessBoard;
import chessBoard.Move;
import chessColor.Color;

public class ComputerPlayer extends ChessPlayer implements Player {

	/**
	 * The depth of moves the computer will look ahead to calculate its next move. 
	 */
	private int _searchDepth;
	
	/**
	 * Default constructor of a new Player
	 * @param color The color of the Player
	 */
	public ComputerPlayer(Color color, String name) {
		super(name);
		_searchDepth = 1;
	}

	/**
	 * Default constructor of a new player with a custom search depth
	 * @param color The color of the player
	 * @param name The name of the player
	 * @param depth The depth of the tree the computer uses to calculate its next move
	 */
	public ComputerPlayer(String name, int depth) {
		super(name);
		_searchDepth = depth;
	}
	
	/**
	 * Constructor of an existing player
	 * @param color The color of the player
	 * @param name The name of the player
	 * @param depth The depth of the tree the computer uses to calculate its next move
	 * @param wins The player's current number of wins
	 * @param draws The player's current number of draws
	 * @param losses The player's current number of losses
	 */
	public ComputerPlayer(String name, int depth, int wins, int draws, int losses) {
		super(name, wins, draws, losses);
		_searchDepth = depth;
	}
	
	public void setSearchDepth(int depth) {
		_searchDepth = depth;
	}

	/**
	 * The algorithm will generate a tree that _searchDepth deep of possible 
	 * legal moves to choose from. Then a the benefit of which move immediately
	 * next is most advantageous will be calculated by recursively comparing the 
	 * "scores" given by the board via a mini-max algorithm. 
	 */
	@Override
	public Move startTurn(SimpleChessBoard aBoard) {
		TreeNode root = new TreeNode(aBoard, 0, _searchDepth, _color);
		return root._miniMaxMove;
	}
	
	/**
	 * A static inner class that constructs a tree of all possible moves up to a specified depth,
	 * where the root node is the Chess Board's current position
	 * @author Joshua DeOliveira
	 */
	static class TreeNode{
		
		/**
		 * The board that corresponds to a possible configuration of the board
		 */
		SimpleChessBoard _board;
		
		/**
		 * List of possible Chess Board configuration that can be done
		 * by the other color on the next turn
		 */
		List<TreeNode> _children;
		
		/**
		 * List of possible moves from current Chess Board configurations. The index's in this list 
		 * are the antecedents to the _children in their corresponding indexes
		 */
		List<Move> _moves;
		
		
		/**
		 * Best move for the root node
		 */
		Move _miniMaxMove;
		
		
		/**
		 * _score of the current board configuration
		 */
		double _score;
		
		/**
		 * Color of this node
		 */
		Color _nodeColor;
		
		/**
		 * The current SimpleChessBoard's distance away from the root node. This
		 * can also be thought of as the number of moves abstracted from the 
		 * current SimpleChessBoard's configuration
		 */
		int _depth;
		
		/**
		 * The maximum distance away a TreeNode can be away from the root node.
		 */
		final int _maxDepth;
		
		/**
		 * Constructor of a TreeNode
		 * @param aBoard This node's SimpleChessBoard
		 * @param depth The current SimpleChessBoard's distance away from the root node
		 */
		public TreeNode(SimpleChessBoard aBoard, int depth, int maxDepth, Color color) {
			_board = aBoard;
			_depth = depth;
			_maxDepth = maxDepth;
			_score = 0;
			_nodeColor = color;
			_children = new ArrayList<TreeNode>();
			_moves = new ArrayList<Move>();
			generateChildren();
			evaluate();
		}
		
		/**
		 * Generates all of the children that can be made from this
		 * TreeNode's SimpleChessBoard. If this TreeNode's _depth has reached the 
		 * _maxDepth, then this Node will generate no children.
		 */
		private void generateChildren() {
			if(_depth < _maxDepth) {
				for(Move aMove: _moves) {
					SimpleChessBoard copy = _board.deepCopy();
					copy.requestMove(aMove.getStartX(), aMove.getStartY(), aMove.getEndX(), aMove.getEndY(), _nodeColor);
					_children.add(new TreeNode(copy, ++_depth, _maxDepth, Color.SwitchColor(_nodeColor)));
				}
			}
		}
		
		/**
		 * Evaluates the tree
		 * @return the MinimaxScore
		 */
		public double evaluate() {
			
			if(_depth == _maxDepth) {
				_score = _board.calculateScore();
				return _score;
			}
	
			int currentIndex = 0;
			int bestIndex = -1;
			
			for(TreeNode node : _children) {
				if(node._depth%2 == 0) { //Same color as root
					if(node._score > _score) {
						_score = node._score;
						bestIndex = currentIndex;
					}
				}
				else {
					if(node._score < _score) {
						_score = node._score;
						bestIndex = currentIndex;
					}
				}
				currentIndex++;
			}
			
			if(_depth == 0) {
				_miniMaxMove = _moves.get(bestIndex);
			}
			return _score;
		}
		
	}
	

}
