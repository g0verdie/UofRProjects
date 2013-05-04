Dan Scarafoni
dscarafo@u.rochester.edu
ID- 27435144
csc242
Project 2- Sudoku

File Manifest
	puzzles folder- contains various puzzles to be solved by the problem
	minisat- binary MINISAT logical solver
	ruh.sh- shell file that runs the program, more detail below
	toTranslate.txt- contains logical clauses to be read by minisat
	translated.txt- contains truth value of variables in logical statements given by toTranslate.txt
	
To Run
	1. go to directory in terminal
	2. type run.sh $puzzle $solver
		where:
			$puzzle- the name of the puzzle you wish to solve
			$solver- the solving engine you wish to use 
