package chessPlayer;
import chessColor.Color;

/**
 * An abstract class representing the general attributes of a chess player
 * @author Joshua DeOliveira
 */
public abstract class ChessPlayer implements Player {
	
	/**
	 * Name of the player
	 */
	protected final String _name;
	
	/**
	 * Number of wins the player has
	 */
	protected int _wins;
	
	/**
	 * Number of draws the player has
	 */
	protected int _draws;
	
	/**
	 * Number of losses the player has
	 */
	protected int _losses;
	
	/**
	 * The method the player is from (HUMAN or COMPUTER)
	 */
	protected PlayerType _playerType;
	
	/**
	 * Default constructor
	 * @param color The color of the player (BLACK or WHITE)
	 */
	public ChessPlayer(String name) {
		_name = name;
		_wins = 0;
		_draws = 0;
		_losses = 0;
	}
	
	/**
	 * Constructs a player with a previous record of wins-draws-losses
	 * @param color Color of the player
	 * @param wins Number of wins the player already had
	 * @param draws Number of draws the player already had
	 * @param losses Number of losses the player already had
	 */
	public ChessPlayer(String name, int wins, int draws, int losses) {
		_name = name;
		_wins = wins;
		_draws = draws;
		_losses = losses;
	}
	
	/**
	 * Returns the number of wins the player currently has
	 * @return The number of wins the player currently has
	 */
	public int getWins() {
		return _wins;
	}
	
	/**
	 * Returns the number of draws the player currently has
	 * @return The number of draws the player currently has
	 */
	public int getDraws() {
		return _draws;
	}
	
	/**
	 * Returns the number of losses the player currently has
	 * @return The number of losses the player currently has
	 */
	public int getLosses() {
		return _losses;
	}
	
	/**
	 * Returns the name of the player
	 * @return The name of the player
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * Adds a win to the player's win counter
	 */
	public void addWin() {
		_wins++;
	}
	
	/**
	 * Adds a draw to the player's draw counter
	 */
	public void addDraw() {
		_draws++;
	}
	
	/**
	 * Adds a loss to the player's loss counter
	 */
	public void addLoss() {
		_losses++;
	}
	
	/**
	 * Returns the type of the Player
	 * @return The type of the Player
	 */
	public PlayerType getPlayerType() {
		return _playerType;
	}
	
}
