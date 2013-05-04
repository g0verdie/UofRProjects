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
	
	int maxdepth = 4;
	boolean wasTooLong;
    /**
     * Return the best move for the nextPlayer in the current state,
     * computed using MINIMAX search. See AIMA 5.2.1 and Figure 5.3
     * (page 166) in particular.
     */
    @Override
    protected Move getNextComputerMove() {
    	long turnTime = System.currentTimeMillis();
    	turnCounter++;
    	
    	/*if(state.getBoard().isEmpty(0, 0, 0))
    		return new Move(state.nextPlayer,0,0,0);*/
    	
    	List<Move> moves = state.getPossibleMoves();
    	wasTooLong  = false;
    	if (moves.size() == 0) {
    		throw new NoPossibleMoveException();
    	}
    	// argmax_{a \in actions(s)} minValue(result(s,a))
    	Move bestMove = null;
    	int maxv = Integer.MIN_VALUE;
    	int counter = 0;
    	for (Move move: moves) {
    		int v = minValue(result(state,move),Integer.MIN_VALUE,Integer.MAX_VALUE,counter+1);
    		if (v > maxv) {
    			bestMove = move;
    			maxv = v;
    		}
    	}
    	System.out.println("too long, got bored");
    	System.out.println("Best move is: " + bestMove + ", utility: " + maxv);
    	
    	turnTime = System.currentTimeMillis() - turnTime;
    	timeTot += turnTime;
    	System.out.println("Best move is: " + bestMove + ", utility: " + maxv);
    	System.out.println("average time "+ timeTot/((double)turnCounter));
    	return bestMove;
    }

    protected int maxValue(State s,int alpha,int beta, int counter) {
    	//System.out.println(counter);
    	if(s.isTerminal() || counter > maxdepth)
    		return utility(s);
    	else if(!s.isTerminal())
    	{
    		List<Move> moves = s.getPossibleMoves();
    		
    		int maxv = Integer.MIN_VALUE;
    		//System.out.println(moves.size());
    		for(Move move: moves)
    		{
    		
    			//System.out.println("max");
    			int v = minValue(result(s,move),alpha,beta,counter+1);//utility(s);
    			/*int m = minValue(result(s,move),alpha,beta,counter++);
    			if( m > v)
    				v = m;*/
    			//System.out.println();
	    		if (v > maxv) {
	    	    	maxv = v;
	    	    }
	    		if(maxv >= beta)
	    			return maxv;
	    		alpha = Math.max(alpha, maxv);
    		}
    		return maxv;
    	}
    	else
    		return 0;
    }

    protected int minValue(State s, int alpha, int beta,int counter) {
    	//System.out.println(counter);
    	if(s.isTerminal() || counter > maxdepth)
    		return utility(s);
    	else if(!s.isTerminal())
    	{
    		List<Move> moves = s.getPossibleMoves();
    		
    		int minv = Integer.MAX_VALUE;
    		//System.out.println(moves.size());
    		for(Move move: moves)
    		{
    			//System.out.println("min");
    			int v = maxValue(result(s,move),alpha,beta,counter+1);//utility(s);
    			/*int m = maxValue(result(s,move),alpha,beta,counter++);
    			if( m < v)
    				v = m;*/
	    		if (v < minv) {
	    	    	minv = v;
	    	    }
	    		if(minv <= alpha)
	    			return minv;
	    		beta = Math.min(beta, minv);
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
    	
    	if(true);//s.isTerminal())
    	{
    		//System.out.println("terminal");
    		Player winner = s.getWinner();
    		if (winner == null) {
    			// Draw
    			return 0;
    		} else if (winner.equals(humanPlayer)) {
    			//System.out.println("loss");
    			// Lose
    			return -10;
    		} else {
    			// Win
    			return 10;
    		}
    	}
    	/*else
    	{
    		//System.out.println("not terminal");
    		for(int i = s.board.getSize()-1; i > 1 ; i--)
    		{
    			//int utility = (int) Math.pow(2,i);
    			//2 in a row gains have high priority
    			if(s.board.getNInARow(i) == humanPlayer)
    			{
    				//System.out.println("towards losing");
    				return -1*i;
    			}
    			else if(s.board.getNInARow(i) == null)
    			{int x = 0;}
    			else
    				return i;
    		}
    		return 0;
    	}*/
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
