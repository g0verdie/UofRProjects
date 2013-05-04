import java.util.LinkedList;
import java.util.Scanner;
public class TheGame implements mm
{
	//---variables---
	
	//the guess
	public String[] guess;
	//the list of all guesses
	LinkedList<String[]> total; 
	//number of turns
	public int turns;
	//color list
	public String[] colors;
	//number of positions
	public int positions;
	//is the problem solved?
	public boolean solved = false;
	//does the user want to play?
	public boolean firstTurn;
	//black and white counter
	public int black;
	public int white;
	//scanners
	Scanner scan = new Scanner(System.in);
	
	//initiate
	public TheGame()
	{
		//start a new game
		newGame();
			while (solved == false) 
			{
				//guess/response cycle
				nextMove();
				response();
			}
	}
	
	//think about response
	public void response()
	{
		turns++;
		//---get input---
		
		//black
		System.out.println("So how many were in the right position and right color?");
		black = scan.nextInt();

		//if they're all right the game is over
		if (black == positions)
		{
			solved = true;
			System.out.print("alright I won! and it only took me "+turns+" turns!");
			TheGame game = new TheGame();
		}
		
		//white	
		System.out.println("Ok, how many were the right color but wrong position?");
		white = scan.nextInt();
	}
	
	//make a next room
	public void nextMove()
	{
		//if not the first term, calculate
		if(!firstTurn)
		{
			checkBlack(guess,black);
			checkWhite(guess,white);
		}
		
		//else, we just guess randomly
		firstTurn = false;
		//print the guess
		System.out.println("Here's my guess");
		guess = total.get((int) (Math.random() * total.size()));
		for(int i= 0; i < guess.length; i++)
		{
			System.out.print(guess[i]);
		}
	}
	
	//start a new game
	public void newGame()
	{
		System.out.println("start a new game?(1 for yes, 0 for no)");
		if (scan.nextInt() == 1) {
			firstTurn = true;
			//get combo length
			System.out.println("how many positions?");
			positions = scan.nextInt();
			//get number of colors
			System.out.println("how many colors do you want to use?");
			int colorNum = scan.nextInt();
			//make a list of colors as long as the number of colors
			colors = new String[colorNum];

			//declare each color
			for (int i = 0; i < colors.length; i++) {
				System.out.println("what do you want color " + (i + 1)
						+ " to be?");
				colors[i] = scan.next();
			}
			
			//initialize guess string
			guess = new String[positions];
			//black count
			black = 0;
			//white count
			white = 0;
			
			//make a blank array of all possible combinations
			int counter = 0;
			String[] str = {"","","",""};
			total = new LinkedList<String[]>();
			int totalSize = (int) Math.pow((int) colors.length, (int) positions);
			while(counter < totalSize)
			{
				str = new String[positions];
				for(int i = 0; i < positions; i++)
				{
					str[i] = colors[(counter/((int)Math.pow(colors.length, positions-i-1))) % colors.length];
				}
				total.add(str);
				counter += 1;
			}
		} 
		
		//end the game if they don't wanna play
		else if (scan.nextInt() == 0) {
			System.out.println("alright then, take care!");
			System.exit(0);
		}	
	}
	//check the black spots
	public void checkBlack(String[] guess,int numBlack)
	{
		for(int i = 0; i < total.size(); i++)
		{
			int bcount = 0;
			for(int j = 0; j < guess.length; j++)
			{
				if(total.get(i)[j] == guess[j])
				{
					bcount ++;
				}
				if(j == (total.get(i).length-1) && bcount != numBlack)
				{
					total.remove(i);
					bcount = 0;
					i--;
				}
			}
		}
	}
	
	//check white
	public void checkWhite(String[] guess, int numWhite)
	{
		String[] guessHolder = guess;
		for (int i = 0; i < total.size(); i++) {
			int wcount = 0;
			guess = guessHolder;
			for (int j = 0; j < total.get(i).length; j++) 
			{
				for (int k = 0; k < guess.length; k++)
				{
					if (total.get(i)[j] == guess[k]) 
					{
						wcount++;
						break;
					}
				}
				if (j == (total.get(i).length - 1))
				{
					if(wcount < numWhite) 
					{
						total.remove(i);
						wcount = 0;
						i--;
					}
					break;
				}
			}
		}
	}
}

