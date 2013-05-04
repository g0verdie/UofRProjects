/*
 * File: HumanGame.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:55:32 2012
 * Time-stamp: <Tue Jan 29 15:56:22 EST 2013 ferguson>
 */

import java.io.*;
import java.util.StringTokenizer;

/**
 * A Game played between human players.
 */
public class HumanGame extends Game {

    /**
     * Return the next move in this Game.
     * For a HumanGame, prompt the user for a move, read it from stdin,
     * and return a new Move.
     */
    @Override
    protected Move getNextMove() {
	int x,y,z;
	while (true) {
	    System.out.print("Enter move (X Y Z): ");
	    try {
		String line = stdin.readLine();
		StringTokenizer tokens = new StringTokenizer(line);
		x = Integer.valueOf(tokens.nextToken());
		y = Integer.valueOf(tokens.nextToken());
		z = Integer.valueOf(tokens.nextToken());
		break;
	    } catch (Exception ex) {
		System.out.println(ex);
	    }
	}
	return new Move(state.getNextPlayer(), x, y,z);
    }

    protected BufferedReader stdin =
	new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] argv) {
	new HumanGame().play();
    }

}
