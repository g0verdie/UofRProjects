public class Solver implements SolverInterface
{
	//x, y location in the puzzle
	int xCo;
	int yCo;
	//list of found words
	LinkedList found;
	//the puzzle
	char[][] puzzle;
	//the dictionary
	Hash dictionary;
	
	public Solver(char[][] p, Hash dic)
	{
		xCo = 0;
		yCo = 0;
		found = new LinkedList();
		puzzle = p;
		dictionary = dic;
	}
	
	public void solvLoop()
	{
		//run through each letter
		for(xCo = 0; xCo < puzzle.length; xCo++)
			for(yCo = 0; yCo < puzzle.length; yCo++)
				//go through each of 8 directions
				for(int k = -1; k <= 1; k++ )
					for(int l = -1; l <= 1; l++ )
					{
						if(k == 0 && l == 0)
							l++;
						checkLine(k,l);
					}
		//print the results
		found.printList(found.callHead());
	}
	
	//for checking each of 8 directions
	public void checkLine(int x, int y)
	{
		//start at index
		int xCheck = xCo;
		int yCheck = yCo;
		String holder = "";
		//until you reach the end of the line
		while(xCheck<puzzle.length&&xCheck>-1 && yCheck<puzzle.length&&yCheck>-1)
		{
			//add the current letter to the holder
			holder += puzzle[yCheck][xCheck];
			
			//if its in the dictionary
			if(dictionary.lookup(holder) != -1)
				//if greater than 3 words
				if(!found.lookUp(holder,found.callHead()) && holder.length() >= 3)
				{
					//insert it
					holder += " "+(xCo)+","+yCo+","+xCheck+","+yCheck;
					found.insert(holder);
				}
			
			yCheck += y;
			xCheck += x;
		}
	}
}
