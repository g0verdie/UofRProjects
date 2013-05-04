/*
 * File: Move.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:19:31 2012
 * Time-stamp: <Tue Jan 31 11:05:30 EST 2012 ferguson>
 */

/**
 * Representation of a move in TTT: A Player marks a cell on a Board.
 */
public class Move {

    // Properties

    protected Player player;

    public Player getPlayer() {
	return player;
    }

    protected int x;
    protected int y;

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    // Constructors

    public Move(Player player, int x, int y) {
	this.player = player;
	this.x = x;
	this.y = y;
    }

    // Printing

    public String toString() {
	return "Move[" + player + "," + x + "," + y + "]";
    }

    // Testing

    public static void main(String[] argv) {
	Board board = new Board();
	board.print();

	Move move = new Move(Player.X, 1, 1);
	System.out.println("move: " + move);
	board.apply(move);
	board.print();

	move = new Move(Player.O, 0, 0);
	System.out.println("move: " + move);
	board.apply(move);
	board.print();
    }

}
