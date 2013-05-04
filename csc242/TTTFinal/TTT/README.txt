
File: README.txt
Creator: George Ferguson (used with permission by Dan Scarafoni)
email- dscarafo@u.rochester.edu
StudentID- 27435144

Created: Tue Jan 31 15:21:41 2012
Time-stamp: <Tue Jan 29 16:02:34 EST 2013 ferguson>
Time-stamp: <Tue Feb 11 17:37:33 EST 2013 Scarafoni
Sample code for basic tic-tac-toe.

to run- compile and run MinimaxComputerGame.java for a game agains the minimax ai, HumanGame.java to play two humans against one another
	or, ComputerGame.java to play against an AI which makes random moves
	
to play- the board will first prompt you on which side you wish to play (X or O)- enter the desired side into the command prompt and hit enter. The computer and human players will then take turns, and the current map will be printed out after each move. To make a move, enter the x and y coordinate of the desired move and press enter

Then run the different classes to see what they do. The key classes
for actually playing a game are HumanGame and ComputerGame.

FILE CONTENTS AND PURPOSE

Board: 
	-stores board as 2x2 array of player class
	-adjustable size, default 3
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
	-contains all necessary files for minimax algorithm AI
	-compile and run to play against an AI that uses the minimax Algorithm
	
TTTRunTimeException
TTTexception
IllegalLocationException
NoPossibleMoveException
LocationOccupiedExceptoin
IllegalLocationException
	-all add error catching for illegal moves
	
