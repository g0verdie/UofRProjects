Dan Scarafoni
dscarafo@u.rochester.edu	
monday/wednesday lab 6:00-7:00

The project sought to create an ai that would solve the game mastermind.
The program used a modified method found online called the 5 step method. This
method is the second most efficient means of cracking the solution, and as the
name suggests is guaranteed to solve in 5 steps or less. The method involves 
creating a list of all possible combinations, and then based on the number of 
right place right color and right color wrong place, weed out all the potential
answers that could not give the expected outcome.The remaining possibilities were
sorted based on how likely they were to produce the answer.
This is repeated until there is only one possibility remaining. 
	My method differed from this method in that I did not weight the answers.
I did not do this becaues this increased the runtime exponentially of the project.
My code can guess every answer in less than a half of a second (using the standard model)
However, the online program takes nearly 30 seconds to make the first guess.
My method is still guarenteed to win all standard model games (needing 8 guesses on 
average). 
	My program also usitlizes a linked list to keep track of all the possible
answers. This was preffered over the standard java array because this list did not
need to be sorted (answers were selected randomly from the list), and it was essential
that removal of specific elements from the list could be done efficiently.
	My program efficiently solves the game for any combinations of 
colors/spaces up to and include 7 colors and 8 spaces.After this point, the program
still solves the problem granted; however, it requires over 30 seconds of time to make
the each guess (7 colors and 8 spaces requires 5 seconds for the first guess). This is
so long that it was observed that the user almost always assumed that the program had
terminated or froze, and stopped playing. This is because this system involved creating
and running through a list of all possibilities for every turn, the maximum size of this
list is (colors)^(places).
	A formal mathematicalanalysis of my programs runtime was not necessary, as the
cpu spends a fraction of a second calculating its next response, whereas the user was shown
to take much, much longer.The user took so much longer that the time the cpu needed for 
computation was trivial.
I included a second program that remains efficient regardless of
the size of the input, but only wins about 50 percent of the time using the standard
model. This program runs through each spot, randomly selecting a color for that spot,
and, based on the number of right place right color markings, figures out through
trial and error which color belongs in each spot. 
	My final project is labeled in the folder MasterMind2, the aformentioned 
guess and check alternative is MasterMind1, and the online solver that uses the
5 step method is the included python module