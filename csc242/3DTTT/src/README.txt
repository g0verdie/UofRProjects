
File: README.txt
Creator: George Ferguson (used and altered with permission by Dan Scarafoni)
email- dscarafo@u.rochester.edu
StudentID- 27435144

Created: Tue Jan 31 15:21:41 2012
Time-stamp: <Tue Jan 29 16:02:34 EST 2013 ferguson>
Time-stamp: <Tue Feb 11 17:37:33 EST 2013 Scarafoni
Sample code for basic tic-tac-toe.

Compile the java files (or just "make").

Then run the different classes to see what they do. The key classes
for actually playing a game are HumanGame and ComputerGame.

FILE CONTENTS AND PURPOSE

Board: 
	-stores board as 4x4x4 array of player class
	-method for printing board
	
Game:
	-common info for all games
	-init, mainloop, gameOver
	-getNextMove (abstract)
	
HumanGame:
	-subclass of game
	-getNextMove (from human)
	-compile and run to play a game with two people
	
computerGame:
	-contains main method
	-extends humanGame
	-gets move from human
	-makes its own move
	-compile and run to play a game against an AI that makes random moves
	
State:
	-keeps track of states and manages all state changes

Player:
	-enum x or o

Move:
	-info for moves
	-x
	-y
	
MinimaxComputerGame:
	-contains all necessary files for minimax algorithm AI, with alpha/beta pruning and a heuristic for limiting time complexity in large state space cases
	-compile and run to play against an AI that uses the minimax Algorithm
	
TTTRunTimeException
TTTexception
IllegalLocationException
NoPossibleMoveException
LocationOccupiedExceptoin
IllegalLocationException
	-all add error catching for illegal moves
	
	
