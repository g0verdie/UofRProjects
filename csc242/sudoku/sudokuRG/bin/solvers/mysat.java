import java.io.*;
import java.util.*;
public class mysat {
	public static void main(String[] args) {

		String input = readFrom();
		
		//put it into an array
		ArrayList<Boolean> clauses = new ArrayList<Boolean>();
		for(int
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
	
	static String readFrom() {
		//ArrayList<Integer> holdList = new ArrayList<Integer>();
		BufferedReader br;
		Hashtable<Integer,Integer> holdList = new Hashtable<Integer,Integer>();
		String holdFile = "";
		try { 
			br = new BufferedReader(new FileReader("toTranslate.txt"));
			
			String holdLine;
			br.readLine();
			//translate file into a string
			while((holdLine = br.readLine()) != null)
				holdFile += holdLine;
			return holdFile;
		}
		catch(FileNotFoundException e)	{System.out.println("can't find translated");}
		catch(IOException e)			{System.out.println("IO problem");}
		
		return holdFile;
	}
}

