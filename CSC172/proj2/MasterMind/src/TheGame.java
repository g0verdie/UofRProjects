import java.util.Scanner;
import java.util.ArrayList;
public class TheGame implements mm
{
	//variables
	
	//the computer guessing string
	public String[] guess;
	public ArrayList<String> wrong;
	//number of colors
	public int colorNum;
	//color list
	public String[] colors;
	//number of positions
	public int positions;
	//is the problem solved?
	public boolean solved;
	public boolean firstTurn;
	//does the user want to play?
	public boolean wannaPlay;
	//black and white measurers for current and last turn
	public int black1;
	public int white1;
	public int black2;
	public int white2;
	
	//iterate through the indecies
	private int tracker;
	//holds the value in question
	private String holder;
	//scanners
	Scanner scan = new Scanner(System.in);
	
	//initiate
	public TheGame()
	{
		//play until I say otherwise
		while (true) 
		{
			//start a new game
			newGame();
			while (solved == false) 
			{
				//guess/response cycle
				response();
				nextMove();
			}
		}
	}
	
	//think about response
	public void response()
	{
		//print the guess
		System.out.println("Here's my guess");
		for(int i= 0; i < positions; i++)
			System.out.print(guess[i] + " ");
		
		//get input
		System.out.println("So how many were in the right position and right color?");
		black2 = scan.nextInt();

		//if they're all right the game is over
		if (black2 == positions)
		{
			solved = true;
			System.out.print("alright I won!");
			newGame();
		}
		/*System.out.println("Ok, how many were the right color but wrong position?");
		white2 = scan.nextInt();*/
	}
	
	//make a next room
	public void nextMove()
	{
		//if we one more right than before, take out each one one at a time to see which is was
		if(white2 + black2  == guess.length)
		{
			colors = new String[guess.length];
			colors = guess;
		}
		if(black2 > black1 && !firstTurn)
		{
			System.out.println("it worked");
			wrong = new ArrayList<String>();
			tracker += 1;
		}

		else if(black1 == black2)
		{
			wrong.add(guess[tracker]);
			while(true)
			{
				guess[tracker] = colors[(int) (Math.random() * colors.length)];
				if(!(wrong.contains(guess[tracker])));
				{
					System.out.println("it worked");
					break;
				}
			}
		}
		firstTurn = false;
		white1 = white2;
		black1 = black2;
	}
	
	//start a new game
	public void newGame()
	{
		System.out.println("start a new game?(1 for yes, 0 for no)");
		if (scan.nextInt() == 1) {
			solved = false;
			firstTurn = true;
			//get combo length
			System.out.println("how many positions?");
			positions = scan.nextInt();
			//get number of colors
			System.out.println("how many colors do you want to use?");
			colorNum = scan.nextInt();
			//make a list of colors as long as the number of colors
			colors = new String[colorNum];

			//declare each color
			for (int i = 0; i < colors.length; i++) {
				System.out.println("what do you want color " + (i + 1)
						+ " to be?");
				colors[i] = scan.next();
			}

			//make a random intial guess
			guess = new String[positions];
			for (int i = 0; i < guess.length; i++) 
			{
				guess[i] = colors[(int) (Math.random() * colors.length)];
			}

			//tracked variable
			tracker = 0;
			//the holder place
			holder = "";
			//old black count
			black1 = 0;
			//old white count
			white1 = 0;
			//new black count
			black2 = 0;
			//new white count
			white2 = 0;
			//initiate an already guessed array
			wrong = new ArrayList<String>();
		} else if (scan.nextInt() == 0) {
			System.out.println("alright then, take care!");
			wannaPlay = false;
			System.exit(0);
		}	
	}
}