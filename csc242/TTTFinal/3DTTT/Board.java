/*
 * File: Board.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:16:13 2012
 * Time-stamp: <Tue Jan 29 10:44:30 EST 2013 ferguson>
 */

import java.io.*;
import java.util.*;

/**
 * Representation of a TTT board: square grid containing Players or
 * null (empty).
 */
public class Board {

    // Properties

    protected int size;

    protected Player[][][] grid;

    // Constructors

    /**
     * Construct and return a new 3x3x3 TTT Board.
     */
    public Board() {
	this(4);
    }

    /**
     * Construct and return a new nxn TTT Board.
     */
    public Board(int n) {
	size = n;
	grid = new Player[n][n][n];
    }

    // Printing
    public int getSize()
    {return size;}
    
    public void print(PrintWriter out) {
    	int x,y,z;
    	for (z = size-1; z >= 0; z--) {
			System.out.println("layer "+z);
    		//print borders
			for (y = size - 1; y >= 0; y--) {
				out.print("    +");
				for (x = 0; x < size; x++) {
					out.print("---+");
				}
				out.println();
				//numbers
				out.format("%2d: |", y);
				//x's and o's
				for (x = 0; x < size; x++) {
					out.format(" %s |", isEmpty(x, y,z) ? " " : grid[x][y][z]);
				}
				out.println();
			}
			//bottom border
			out.print("    +");
			for (x = 0; x < size; x++) {
				out.print("---+");
			}
			out.println();
			//numbers on bottom
			out.print("     ");
			for (x = 0; x < size; x++) {
				out.format("%2d  ", x);
			}
			out.println();
		}
    }

    public void print(PrintStream out) {
	print(new PrintWriter(out, true));
    }

    public void print() {
	print(System.out);
    }

    public String toString() {
	StringWriter writer = new StringWriter();
	PrintWriter out = new PrintWriter(writer);
	print(out);
	out.flush();
	return writer.toString();
    }

    // Methods

    /**
     * Return true if the given location on this Board is empty.
     */
    public boolean isEmpty(int x, int y,int z) {
    	return grid[x][y][z] == null;
    }

    /**
     * Apply the given Move to this Board (i.e., mark the Move's
     * location on this Board with the Move's Player).
     */
    public void apply(Move move) throws TTTRuntimeException {
    	int x = move.getX();
    	int y = move.getY();
    	int z = move.getZ();
    	if (x < 0 || x >= size || y < 0 || y >= size || z < 0 || z >= size) {
    		throw new IllegalLocationException();
    	} else if (!isEmpty(x, y,z)) {
    		throw new LocationOccupiedException();
    	} else {
    		grid[x][y][z] = move.getPlayer();
    	}
    }

    /**
     * Return true if this Board represents a finished game.
     * This can happen if one player has won or if there are no empty
     * spaces (that is, a draw).
     */
    public boolean isFinished() {
	if (getWinner() != null) {
	    return true;
	} else {
	    return isFull();
	}
    }

    /**
     * Return the player who has won this game (if any, else null).
     * This happens if one player has n (size) in a row horizontally,
     * vertically, or diagonally.
     */
    public Player getWinner() {
	Player p;
	p = checkHorizontals();
	if (p != null) {
	    return p;
	}
	//System.out.println("checked horiz");
	p = checkVerticals();
	if (p != null) {
	    return p;
	}
	//System.out.println("checked Verts");
	
	p = checkDepths();
	if (p != null) {
	    return p;
	}
	//System.out.println("checked depths");
	
	p = checkDiagonals();
	if (p != null) {
	    return p;
	}
	//System.out.println("checked diag");
	return null;
    }
    
  
    //make it compensate for depth
    //returns first player found in any horiz
    protected Player checkHorizontals() {
    	for (int y = 0; y < size; y++) {
    		for (int z = 0; z < size; z++) {
    			Player p = checkHorizontal(y,z);
    			if (p != null) {
    				return p;
    			}
    		}
    	}
	return null;
    }
    //returns first player found in a horiz
    protected Player checkHorizontal(int y, int z) {
	return checkLine(0,y,z, 1,0,0);
    }
    
    //make it compensate for depth
    //returns first player found in any verticals
    protected Player checkVerticals() {
    	for (int x = 0; x < size; x++) {
    		for (int z = 0; z < size; z++) {
    			Player p = checkVertical(x, z);
    			if (p != null) {
    				return p;
    			}
    		}
    	}
	return null;
    }

    //returns first player found in a vertical
    protected Player checkVertical(int x,int z) {
	return checkLine(x,0,z, 0,1,0);
    }

    
    //check depth
    protected Player checkDepths() {
    	for (int x = 0; x < size; x++) {
    		for (int y = 0; y < size; y++) {
    			Player p = checkDepth(x, y);
    			if (p != null) {
    				return p;
    			}
    		}
    	}
	return null;
    }

    //returns first player found in a vertical
    protected Player checkDepth(int x,int y) {
	return checkLine(x,y,0, 0,0,1);
    }
    
    
    //add another 2 for depth
    protected Player checkDiagonals() {

    	//left to right
    	Player p;
    	for(int i = 0; i < size; i++)
    	{
    		p = checkLine(i,0,0, 0,1,1);
    		if (p != null) {
    			return p;
    		}
    		
    		p = checkLine(i,size-1,0, 0,-1,1);
    		if (p != null) {
    			return p;
    		}
    	}
    	
    	//front to back
    	for(int i = 0; i < size; i++)
    	{
    		p = checkLine(0,i,0, 1,0,1);
    		if (p != null) {
    			return p;
    		}
    		
    		p = checkLine(0,i,size-1, 1,0,-1);
    		if (p != null) {
    			return p;
    		}
    	}
    	
    	//down up
    	for(int i = 0; i < size; i++)
    	{
    		p = checkLine(0,0,i, 1,1,0);
    		if (p != null) {
    			return p;
    		}
    		
    		p = checkLine(0,size-1,i, 1,-1,0);
    		if (p != null) {
    			return p;
    		}
    	}

    	//across
    	//lower left front to upper right back
    	p = checkLine(0,0,0, 1,1,1);
    	if (p != null) {
    		return p;
    	}
    	//upper left front to lower right back
    	p = checkLine(0,size-1,0, 1,-1,1);
    	if (p != null) {
    		return p;
    	}
    	
    	//lower left back to upper right front
    	p = checkLine(0,0,size-1, 1,1,-1);
    	if (p != null) {
    		return p;
    	}
    	
    	//upper left back to lower right front
    	p = checkLine(0,size-1,size-1, 1,-1,-1);
    	if (p != null) {
    		return p;
    	}

    	return null;
    }
    
    protected Player checkLine(int x, int y,int z, int dx, int dy, int dz) {
    	//if(x == 0 && y == 3 && dz == 0 && dy == 0 && dx == 1)
    		//System.out.println("checking the one");
    	Player p = grid[x][y][z];
    	//if the original point is empty, return null
    	if (p == null) {
    	    return null;
    	}
    	//to through other spaces, return empty if none of them have a piece
    	while (x >= 0 && x < size && y >= 0 && y < size && z >= 0 && z < size) {
    	    if (grid[x][y][z] != p) {
    		return null;
    	    }
    	    x += dx;
    	    y += dy;
    	    z += dz;
    	}
    	return p;
        }
    

    public Player getNInARow(int n)
    {
    	Player p;
    	p = checkNHorizontals(n);
    	if (p != null) {
    	    return p;
    	}
    	//System.out.println("checked horiz");
    	p = checkNVerticals(n);
    	if (p != null) {
    	    return p;
    	}
    	//System.out.println("checked Verts");
    	
    	p = checkNDepths(n);
    	if (p != null) {
    	    return p;
    	}
    	//System.out.println("checked depths");
    	
    	p = checkNDiagonals(n);
    	if (p != null) {
    	    return p;
    	}
    	//System.out.println("checked diag");
    	return null;
    }
    
  //make it compensate for depth
    //returns first player found in any horiz
    protected Player checkNHorizontals(int n) {
    	for (int y = 0; y < size; y++) {
    		for (int z = 0; z < size; z++) {
    			Player p = checkNHorizontal(n,y,z);
    			if (p != null) {
    				return p;
    			}
    		}
    	}
	return null;
    }
    //returns first player found in a horiz
    protected Player checkNHorizontal(int n,int y, int z) {
    	//if(y == 0 && z == 0)
    		//System.out.println("found it");
	return checkNLine(n,0,y,z, 1,0,0);
    }
    
    //make it compensate for depth
    //returns first player found in any verticals
    protected Player checkNVerticals(int n) {
    	for (int x = 0; x < size; x++) {
    		for (int z = 0; z < size; z++) {
    			Player p = checkNVertical(n,x, z);
    			if (p != null) {
    				return p;
    			}
    		}
    	}
	return null;
    }

    //returns first player found in a vertical
    protected Player checkNVertical(int n,int x,int z) {
	return checkNLine(n,x,0,z, 0,1,0);
    }

    
    //check depth
    protected Player checkNDepths(int n) {
    	for (int x = 0; x < size; x++) {
    		for (int y = 0; y < size; y++) {
    			Player p = checkNDepth(n,x, y);
    			if (p != null) {
    				return p;
    			}
    		}
    	}
	return null;
    }

    //returns first player found in a vertical
    protected Player checkNDepth(int n,int x,int y) {
	return checkNLine(n,x,y,0, 0,0,1);
    }
    
    
    //add another 2 for depth
    protected Player checkNDiagonals(int n) {

    	//left to right
    	Player p;
    	for(int i = 0; i < size; i++)
    	{
    		p = checkNLine(n,i,0,0, 0,1,1);
    		if (p != null) {
    			return p;
    		}
    		
    		p = checkNLine(n,i,size-1,0, 0,-1,1);
    		if (p != null) {
    			return p;
    		}
    	}
    	
    	//front to back
    	for(int i = 0; i < size; i++)
    	{
    		p = checkNLine(n,0,i,0, 1,0,1);
    		if (p != null) {
    			return p;
    		}
    		
    		p = checkNLine(n,0,i,size-1, 1,0,-1);
    		if (p != null) {
    			return p;
    		}
    	}
    	
    	//down up
    	for(int i = 0; i < size; i++)
    	{
    		p = checkNLine(n,0,0,i, 1,1,0);
    		if (p != null) {
    			return p;
    		}
    		
    		p = checkNLine(n,0,size-1,i, 1,-1,0);
    		if (p != null) {
    			return p;
    		}
    	}

    	//across
    	//lower left front to upper right back
    	p = checkNLine(n,0,0,0, 1,1,1);
    	if (p != null) {
    		return p;
    	}
    	//upper left front to lower right back
    	p = checkNLine(n,0,size-1,0, 1,-1,1);
    	if (p != null) {
    		return p;
    	}
    	
    	//lower left back to upper right front
    	p = checkNLine(n,0,0,size-1, 1,1,-1);
    	if (p != null) {
    		return p;
    	}
    	
    	//upper left back to lower right front
    	p = checkNLine(n,0,size-1,size-1, 1,-1,-1);
    	if (p != null) {
    		return p;
    	}

    	return null;
    }
    //checks a given line with respect to a point
    protected Player checkNLine(int n,int x, int y,int z, int dx, int dy, int dz) {
    //if(x == 0 && y == 3 && dz == 0 && dy == 0 && dx == 1)
		//System.out.println("checking the one");
  
	Player p1 = Player.X;
	Player p2 = Player.O;
	int inARow1 = 0;
	int inARow2 = 0;
	//to through other spaces, return empty if none of them have a piece
	while (x >= 0 && x < size && y >= 0 && y < size && z >= 0 && z < size) {
	    if (grid[x][y][z] == p1) 
	    	inARow1++;
	    
	    else if(grid[x][y][z] == p2) //&& p2 == null)
	    	inARow2++;
	    
	    x += dx;
	    y += dy;
	    z += dz;
	}
	if(inARow1 == n && inARow2 == 0)// && grid[x0][y0][z0] == null)
		return p1;
	else if(inARow2 == n && inARow1 == 0 )//&& grid[x0][y0][z0] == null)
		return p2;
	else
		return null;
    }

    /**
     * Return true if this Board is full (no empty spaces).
     * This implementation is O(n^2).
     */
    public boolean isFull() {
    	for (int x=0; x < size; x++) {
    		for (int y=0; y < size; y++) {
    			for (int z = 0; z < size; z++) {
    				if (isEmpty(x, y, z)) {
    					return false;
    				}
    			}
    		}
    	}
	return true;
    }

    /**
     * Return List of possible moves for given Player on this Board.
     * For TTT it doesn't matter which player is moving.
     */
    public List<Move> getPossibleMoves(Player player) {
    	List<Move> moves = new ArrayList<Move>();
    	for (int x=0; x < size; x++) {
    		for (int y=0; y < size; y++) {
    			for (int z = 0; z < size; z++) {
    				if (isEmpty(x, y,z)) {
    					moves.add(new Move(player, x, y,z));
    				}
    			}
    		}
    	}
    	return moves;
    }

    /**
     * Return a new Board with a newly-allocated grid initialized
     * to the same values as this Board.
     */
    public Board copy() {
	Board b = new Board(size);
	for (int z = 0; z < size; z++) {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				b.grid[x][y][z] = grid[x][y][z];
			}
		}
	}
	return b;
    }

    // Testing

    public static void main(String[] argv) {
	Board board = new Board();
	board.print();
    }
}
