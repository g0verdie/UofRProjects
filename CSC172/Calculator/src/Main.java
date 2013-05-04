import java.io.*;
import java.util.*;

public class Main 
{
	public static void main( String[] args)
	{ 
		//open the file
		File file = new File("input.txt");
		//read the file
		try
		{
			//scan the files, find number of eq
			Scanner scanner = new Scanner(file);
			int length = 0;
			while (scanner.hasNextLine()) 
			{
				length++;
				scanner.nextLine();
			}
			//make an array of equations and break each equation into arrays at spaces
			String[] eqs = new String[length];
			scanner = new Scanner(file);
			
			//run through and solve each equation
			for(int i = 0; i < length;i++)
			{
				eqs[i] = scanner.nextLine();
				String[] splice = tokenize(eqs[i]);
				//set up the calculator
				Calc calc = new Calc(splice);
				//convert to postfix
				calc.postfx = calc.toPost(calc.input,calc.postfx);
				//calculate the answer
				Double answer = calc.doCalc(calc.postfx,calc.sum);
				//print the answer
				print("the answer for line "+(i+1)+" is ");
				print(answer);
			}
		}
		//if not found throw an exception
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
}
	//a simplified printing function
	public static <AnyType> void print(AnyType input)
	{
		System.out.println(input);
	}
	
	public static String[] tokenize(String input)
	{
		//calcualte the size of the return array
		LinkedList<String> list = new LinkedList<String>(); 
		String holder = "";
		for(int i = 0; i < input.length();i++)
		{
			//determine if its an operator
			switch (input.charAt(i)) 
			{
				case '+': 
					list.insert(holder);
					list.insert("+");
					holder = "";
					break;
				case '-': 
					list.insert(holder);
					list.insert("-");
					holder = "";
					break;
				case '*': 
					list.insert(holder);
					list.insert("*");
					holder = "";
					break;
				case '/': 
					list.insert(holder);
					list.insert("/");
					holder = "";
					break;
				case '<': 
					list.insert(holder);
					list.insert("<");
					holder = "";
					break;
				case '>': 
					list.insert(holder);
					list.insert(">");
					holder = "";
					break;
				case '=': 
					list.insert(holder);
					list.insert("=");
					holder = "";
					break;
				case ')': 
					list.insert(holder);
					list.insert(")");
					//print(holder);
					holder = "";
					break;
				case '(': 
					list.insert(holder);
					list.insert("(");
					holder = "";
					break;
				case '!': 
					list.insert(holder);
					list.insert("!");
					holder = "";
					break;
				case '&': 
					list.insert(holder);
					list.insert("&");
					holder = "";
					//print("found");
					break;
				case '|': 
					list.insert(holder);
					list.insert("|");
					holder = "";
					break;
				//its its a space, ignore it
				case ' ':
					break;
				//if its a number, put it into a holder in case the number is  multiple digits
				default:
					holder += Character.toString(input.charAt(i));
					break;
			}
		}
		list.insert(holder);

		//convert the linked list to an array
		int length = list.getLength(list.callHead(),0)-1;
		String[] returned = new String[length];
		return list.toString(list.callHead().next,0,length,returned);
	}
}
