import java.util.ArrayList;
import java.util.Random;

public class Debugger {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Player Josh = new Player(false, 'w');
		Josh.startNewGame();
		System.out.println(Josh.getBoard()+ "\n\n");
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		Josh.getBoard().move(4, 6, 4, 4);
		
		moves = Josh.getAllMoves(Josh.getBoard(), 'b');
		
		
		System.out.println(Josh.getBoard()+ "\n\n");
		
		
		int i = 0;
		while (i < moves.size()) {
			System.out.println(moves.get(i) + " " +  moves.get(++i)  + " " +  moves.get(++i)  + " " + moves.get(++i) );
			i++;
			System.out.println(rand.nextInt(11)-5);
		}
	}

}
