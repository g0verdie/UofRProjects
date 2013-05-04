import java.io.*;
import java.util.*;
public class javaSAT {
	public static void main(String[] args) {

		ArrayList<String> clauses = readFrom();
		
		//put it into an array
		ArrayList<Boolean> symbols= getVars(clauses);

	}
	
	static boolean DPLL(ArrayList<String> clauses, ArrayList<Boolean> symbols, ArrayList<Boolean> model) {
		boolean allTrue = true;
		
	}
	static ArrayList<Boolean> getClauses(ArrayList<String> clauses, ArrayList<Boolean> symbols) {
		ArrayList<Boolean> clauses2 = new ArrayList<Boolean>();

		for(int i = 0; i < clauses.size(); i++) {
			StringTokenizer tokens = new StringTokenizer(clauses.get(i));
			
			ArrayList<Boolean> accumulator = new ArrayList<Boolean>();
			while(tokens.hasMoreTokens()) {
				accumulator.add(symbols.get(Integer.valueOf(tokens.nextToken())));
			}	
			Boolean holder;
			for(int j = 0; j < accumulator.size()-1; j++) {
				holder += (accumulator.get(i) || accumulator.get(i+1));
			}
				
		}
	return clauses2;
	}
	static ArrayList<Boolean> getVars(ArrayList<String> clauses) {
		Hashtable<Integer,Integer> foundVar = new Hashtable<Integer,Integer>();
		ArrayList<Boolean> symbols = new ArrayList<Boolean>();
		for(int i = 0; i < clauses.size(); i++)
		{
			StringTokenizer tokens = new StringTokenizer(clauses.get(i));
			Integer current;
			while((current = Integer.valueOf(tokens.nextToken())) != 0) {
				if(!foundVar.contains(current)) {
					foundVar.put(current,current);
					symbols.add(current,true);
				}		
			}
		}
		return symbols;
	}
	//write to translate.txt
	static void writeTo(ArrayList<String> clauses,Integer numVars){
		BufferedWriter bw;
		try{
			Integer numClauses = clauses.size();
			bw = new BufferedWriter(new FileWriter("solvers/toTranslate.txt"));
			bw.write("p cnf "+numVars.toString()+" "+numClauses.toString()+"\n");
			for(int i = 0; i < numClauses; i++)
				bw.write(clauses.get(i)+"0\n");
			bw.close();
		}
		catch(FileNotFoundException e)	{System.out.println("can't find toTranslate");}
		catch(IOException e)			{System.out.println("IO problem");}
	}
	
	static ArrayList<String> readFrom() {
		//ArrayList<Integer> holdList = new ArrayList<Integer>();
		BufferedReader br;
		ArrayList<String> holdFile = new ArrayList<String>();
		try { 
			br = new BufferedReader(new FileReader("toTranslate.txt"));
			
			String holdLine;
			br.readLine();
			//translate file into a string
			while((holdLine = br.readLine()) != null)
				holdFile.add(holdLine);
			return holdFile;
		}
		catch(FileNotFoundException e)	{System.out.println("can't find translated");}
		catch(IOException e)			{System.out.println("IO problem");}
		
		return holdFile;
	}
}
