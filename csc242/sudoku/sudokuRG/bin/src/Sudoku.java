/*
 * File: Sudoku.java
 * Creator: George Ferguson
 * Created: Mon Feb 18 18:29:36 2013
 * Time-stamp: <Tue Feb 19 20:13:56 EST 2013 ferguson>
 */
import java.util.*;
import java.io.*;

/**
 * Class representing Sudoku puzzles.
 */
public class Sudoku {

	/**
	 * Size of this Sudoku. This must be a perfect square, and is equal
	 * to the square of the subsize.
	 */
	protected int size;

	/**
	 * Size of each of the <q>sub-grids</q> of this Sudoku (and also
	 * the number of sub-grids in each dimension). This is the square
	 * root of the size.
	 */
	protected int subsize;

	/**
	 * The digits in this Sudoku (0 means empty location).
	 */
	protected int[][] digits;

	/**
	 * Construct and return a new Sudoku with the given size (which
	 * must be a perfect square, although we don't check that).
	 */
	public Sudoku(int size) {
		this.size = size;
		this.subsize = (int)Math.sqrt(size);
		this.digits = new int[size][size];
	}

	/**
	 * Return the size of this Sudoku.
	 */
	public int size() {
		return size;
	}

	/**
	 * Return the subsize (size of the sub-grids) of this Sudoku.
	 */
	public int subsize() {
		return subsize;
	}

	/**
	 * Return the digit at the given location of this Sudoku.
	 */
	public int get(int row, int col) {
		return digits[row][col];
	}
	
	public int[][] getRow(int row) {
		int[][] toReturn = new int[size][3];
		for(int col = 0; col < size; col++) {
			toReturn[col][0] = row;
			toReturn[col][1] = col;
			toReturn[col][2] = digits[row][col];
			//System.out.println(toReturn[col][0]+","+toReturn[col][1]+","+toReturn[col][2]);

		}
		return toReturn;
	}
	
	public int[][] getCol(int col) {
		int[][] toReturn = new int[size][3];
		for(int row = 0; row < size; row++) {
			toReturn[row][0] = row;
			toReturn[row][1] = col;
			toReturn[row][2] = digits[row][col];
		}
		return toReturn;
	}
	
	public int[][] getSub(int sub) {
		int startx = (sub % subsize)*subsize;
		int starty = (sub / subsize)*subsize;
		
		int[][] toReturn = new int[size][3];
		int i = 0;
		for(int x = startx; x < startx + subsize; x++)
			for(int y = starty; y < starty+subsize; y++) {
				toReturn[i][0] = x;
				toReturn[i][1] = y;
				toReturn[i][2] = digits[x][y];
				i++;
			}
		
		return toReturn;
	}

	/**
	 * Set the given location of this Sudoku to the given digit.
	 * Note that this currently does not test that the constraints on
	 * a Sudoku are still preserved. It would not be hard to test that
	 * and throw an exception if not.
	 */
	public void set(int row, int col, int digit) {
		digits[row][col] = digit;
	}

	// Printing

	/**
	 * Print this Sudoku to the given PrintWriter. !!!edited to print in hex for larger sudoku!!!!!
	 */
	public void print(PrintWriter out) {
		out.println(size);
		printHorizontalSeparator(out);
		for (int row=0; row < size; row++) {
			out.print("|");
			for (int col=0; col < size; col++) {
				if (get(row,col) == 0) {
					out.print(".");
				} else {
					out.printf("%x",get(row,col));
				}
				if ((col+1) % subsize == 0) {
					out.print("|");
				}
			}
			out.println();
			if ((row+1) % subsize == 0) {
				printHorizontalSeparator(out);
			}
		}
	}

	/**
	 * Print the horizontal separator used in drawing this Sudoku
	 * to the given PrintWriter.
	 */
	protected void printHorizontalSeparator(PrintWriter out) {
		out.print("+");
		for (int col=0; col < size; col++) {
			out.print('-');
			if ((col+1) % subsize == 0) {
				out.print("+");
			}
		}
		out.println();
	}

	/**
	 * Print this Sudoku to the given PrintStream.
	 */
	public void print(PrintStream out) {
		print(new PrintWriter(out, true));
	}

	/**
	 * Print this Sudoku to System.out (stdout).
	 */
	public void print() {
		print(System.out);
	}

	/**
	 * Return the string representation of this Sudoku.
	 */
	public String toString() {
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		print(out);
		out.flush();
		return writer.toString();
	}
	

	/**
	 * Test driver for Sudoku.
	 */
	public static void main(String[] argv) throws IOException {
		//get input
		String filename = "puzzles/"+argv[0];
		String inOrOut = argv[1];
		
		//read in the sudoku puzzle, initialize solvers/translator
		Sudoku sudoku = SudokuReader.from(filename);
		Solver solver = new Solver(sudoku);
		Translator translator = new Translator();
		
		//if we're in part 1, get constraints 
		if(inOrOut.equals("in")) {
			sudoku.print();
			ArrayList<String> toPrint = new ArrayList<String>();
			toPrint = solver.getConstraints(toPrint);
			int numVars = solver.numVars();
			translator.writeTo(toPrint,numVars);
		}
	
		//if we're on part two, solve it
		else {
			solver.writeAnswers(translator.readFrom());
			sudoku.print();
		}
	}
}
