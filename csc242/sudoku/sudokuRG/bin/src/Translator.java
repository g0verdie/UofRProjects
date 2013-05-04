import java.io.*;
import java.util.*;
public class Translator {
	BufferedWriter bw;
	BufferedReader br;
	public Translator() {}

	//write to translate.txt
	public void writeTo(ArrayList<String> clauses,Integer numVars){
		
		try{
			Integer numClauses = clauses.size();
			bw = new BufferedWriter(new FileWriter("toTranslate.txt"));
			bw.write("p cnf "+numVars.toString()+" "+numClauses.toString()+"\n");
			for(int i = 0; i < numClauses; i++)
				bw.write(clauses.get(i)+"0\n");
			bw.close();
		}
		catch(FileNotFoundException e)	{System.out.println("can't find toTranslate");}
		catch(IOException e)			{System.out.println("IO problem");}
	}
	
	public Hashtable<Integer,Integer> readFrom() {
		//ArrayList<Integer> holdList = new ArrayList<Integer>();
		Hashtable<Integer,Integer> holdList = new Hashtable<Integer,Integer>();
		try { 
			br = new BufferedReader(new FileReader("translated.txt"));
			
			String holdFile = "";
			String holdLine;
			br.readLine();
			//translate file into a string
			while((holdLine = br.readLine()) != null)
				holdFile += holdLine;
			
			//get the tokens
			StringTokenizer tokens = new StringTokenizer(holdFile);
			while(tokens.hasMoreTokens()) {
				Integer current = Integer.valueOf(tokens.nextToken());
				//holdList.add(Integer.valueOf(tokens.nextToken()));
				if(current > 0)
					holdList.put(current,current);
			}
		}
		catch(FileNotFoundException e)	{System.out.println("can't find translated");}
		catch(IOException e)			{System.out.println("IO problem");}
		
		return holdList;
	}
}
