/*
 * File: MinimaxComputerGame.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 12:12:05 2012
 * Time-stamp: <Tue Jan 29 15:55:53 EST 2013 ferguson>
 */

import java.util.List;

/**
 * This class extends ComputerGame in order to pick the next
 * move more intelligently. I have coded up the top-level part
 * of AIMA Figure 5.3, since it might not be obvious how to do it.
 * But the rest is up to you...
 */
public class MinimaxComputerGame extends ComputerGame {
long timeTot = 0;
int turnCounter = 0;
    /**
     * Return the best move for the nextPlayer in the current state,
     * computed using MINIMAX search. See AIMA 5.2.1 and Figure 5.3
     * (page 166) in particular.
     */
    @Override
    protected Move getNextComputerMove() {
    long turnTime = System.currentTimeMillis();
    turnCounter++;
    
	List<Move> moves = state.getPossibleMoves();
	if (moves.size() == 0) {
	    throw new NoPossibleMoveException();
	}
	// argmax_{a \in actions(s)} minValue(result(s,a))
	Move bestMove = null;
	int maxv = Integer.MIN_VALUE;
	for (Move move: moves) {
	    int v = minValue(result(state,move));
	    if (v > maxv) {
	    	bestMove = move;
	    	maxv = v;
	    }
	}   
	turnTime = System.currentTimeMillis() - turnTime;
	timeTot += turnTime;
	System.out.println("Best move is: " + bestMove + ", utility: " + maxv);
	System.out.println("average time "+ timeTot/((double)turnCounter));
	return bestMove;
    }

    protected int maxValue(State s) {
    	if(s.isTerminal())
    		return utility(s);
    	else if(!s.isTerminal())
    	{
    		List<Move> moves = s.getPossibleMoves();
    		int maxv = Integer.MIN_VALUE;
    		for(Move move: moves)
    		{
    			int v = (minValue(result(s,move)));
	    		if (v > maxv) {
	    	    	maxv = v;
	    	    }
    		}
    		return maxv;
    	}
    	else
    		return 0;
    }

    protected int minValue(State s) {
    	if(s.isTerminal())
    		return utility(s);
    	else if(!s.isTerminal())
    	{
    		List<Move> moves = s.getPossibleMoves();
    		int minv = Integer.MAX_VALUE;
    		for(Move move: moves)
    		{
    			int v = (maxValue(result(s,move)));
	    		if (v < minv) {
	    	    	minv = v;
	    	    }
    		}
    		return minv;
    	}
    	else
        	return 0;
    }

    //find the max value child of a state
    /**
     * Returns the utility of the given State (assumed terminal) for the
     * computer player.
     * <p>
     * You could move this method into the State class, but note that the
     * utility depends on which player is ``us.'' There are other ways you
     * could handle that. For TTT, doing it here is ok and also makes the
     * code look more like the book's pseudocode.
     */
    protected int utility(State s) {
	Player winner = s.getWinner();
	if (winner == null) {
	    // Draw
	    return 0;
	} else if (winner.equals(humanPlayer)) {
	    // Lose
	    return -1;
	} else {
	    // Win
	    return 1;
	}
    }

    /**
     * Return a new State which is the result of applying Move m in
     * State s.
     * <p>
     * It is crucial that you realize that you have to copy the State
     * before applying the Move. And that this is the source of the
     * memory overhead for state-space search. There are ways of trading
     * space for time, but this is simplest.
     */
    protected State result(State s, Move m) {
	State newState = s.copy();
	newState.apply(m);
	return newState;
    }

    public static void main(String[] argv) {
	new MinimaxComputerGame().play();
    }

}
