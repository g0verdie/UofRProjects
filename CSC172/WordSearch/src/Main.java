import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args)
	{
		Hash dictionary;
		//make the dictionary
		File file = new File(args[0]);
		//read the file
		try
		{
			//find the length of the hash table
			Scanner scanner = new Scanner(file);
			int length = 0;
			while (scanner.hasNextLine()) 
			{length++;scanner.nextLine();}
			
			//create a dictionary
			dictionary = new Hash(length);
			
			//hash each word
			scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{dictionary.insert(scanner.nextLine());}
			
			//find the length of the puzzle
			File puzzle = new File(args[1]);
			scanner = new Scanner(puzzle);
			length = 0;
			while (scanner.hasNextLine()) 
			{length++;scanner.nextLine();}
			
			//make an array of appropriate size
			char[][] puz = new char[length][length];
			scanner = new Scanner(puzzle);
			for(int i = 0; i < length; i++)
			{
				String next = scanner.nextLine();
				for(int j = 0; j < length; j++)
				{puz[i][j] = next.charAt(j);}
			}
			Solver solv = new Solver(puz,dictionary);
			solv.solvLoop();
		}
		//if not found throw an exception
		catch (FileNotFoundException e)
		{e.printStackTrace();}
	}
	
	public static <AnyType> void print(AnyType input)
	{System.out.println(input);}
}
