import java.util.*;
public class Solver {
	private Sudoku board;
	private String[][][] vars;
	private int numVars;
	
	public Solver(Sudoku board){
		this.board = board;
		setUpVars(board);
	}
	//fill in the blanks with shiny new answers!
	protected void writeAnswers(Hashtable<Integer,Integer> answers){
		int index = 1;
		for(int x = 0; x < board.size(); x++) {
			for(int y = 0; y < board.size(); y++) {
				if(board.get(x,y) == 0) {
					for(int z = 0; z < board.size(); z++){
						if(answers.get(index) != null){
							board.set(x,y,z+1);
						}
						index++;
					}
				}
			}
		}
	}

	//create enough variables for the whole board
	private void setUpVars(Sudoku board){
		vars = new String[board.size()][board.size()][board.size()];
		numVars = (int) Math.pow(board.size,3);
		Integer place = 1;
		for(int x = 0; x < board.size(); x++)
			for(int y = 0; y < board.size(); y++)
				for(int z = 0; z < board.size(); z++){
					vars[x][y][z] = place.toString();
					place++;
				}
}
	public ArrayList<String> getConstraints(ArrayList<String> constraints){
		
		//all spots on board have one value
		constraints = AssignValues(board,vars,constraints);
		
		//assign row/col/sub constraints
		for(int i = 0; i < board.size(); i++){
			//rows
			constraints = allDiffArcs(board.getRow(i),constraints);
			//cols
			constraints = allDiffArcs(board.getCol(i),constraints);
			//subsections
			constraints = allDiffArcs(board.getSub(i),constraints);
		}
		return constraints;
	}
	
	//turns arc consistency list into a series of binary consistencies
	public ArrayList<String> allDiffArcs(int[][] list,ArrayList<String> constraints){
		//for each spot in the row/col/sub
		String[] varVals = new String[list.length];
		//match each one value with every other value
		for(int i = 0; i < list.length; i++) {
			for(int j = 0; j < list.length; j++) 
				varVals[j] = vars[list[j][0]][list[j][1]][i];
			constraints = onlyOne(varVals,constraints);
		}
		return constraints;
	}
	
	//initialize the array of all possible boolean variables for the board
	public ArrayList<String> AssignValues(Sudoku board, String[][][] vars, ArrayList<String> constraints) {
		//row's
		for(int x = 0; x < board.size(); x++) {
			//cols
			for(int y = 0; y < board.size(); y++) {
				//each space can only have one number
				constraints = onlyOne(vars[x][y],constraints);
				//fill in any values already given
				for(int z = 0; z <vars[x][y].length; z++) {
					if(board.get(x,y)-1 == z)
						constraints.add(vars[x][y][z].toString()+" ");
				}
			}
		}
		return constraints;
	}
	
	//of all of the array of booleans that i'm giving, only one variable can at most be true
	public ArrayList<String> onlyOne(String[] input, ArrayList<String> constraints){
		String toAdd = "";
		for(int i = 0; i < input.length; i++) {
			toAdd += input[i].toString() + " ";
		}
		constraints.add(toAdd);
		for(int i = 0; i < input.length; i++)
			for(int j = i+1; j < input.length; j++){
				constraints.add("-"+input[i].toString()+" -"+input[j].toString()+" ");
			}
		return constraints;
	}
	
	public int numVars() {
		return numVars;
	}
}
