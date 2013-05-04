Dan scarafoni
CSC173 scheme project week 2
dscarafo@u.rochester.edu


files
	two- the scheme file containing all functions for the project
	interactions- the interactions file for the poject exported to a text file
to run
	1. open drracket
	2. select "pretty big" as language
	3. press run

functions
	2.1
	(makeNode data left mid right)- creates a data node for a tree,
		data- an integer
		left/mid/right- child tree nodes
	(leftNode tree)- the leftnode of tree
	(midNode tree)- the middle node of tree
	(rightNode tree)- the right node of tree
	(copyTree toCopy)- creates an identitcal copy of tree toCopy
	(lookup data tree)- looks up and returns data if it is in tree

	2.2
	(precI x vector)- returns a list of all items that precede incidents of x in vector, uses iteration
	(precR x vector)- same as above, but uses recursion
	
	2.3
	(makeQ)- makes an empty priority queue with only the head node
	(add num qL qR)- adds num into qR, qL is the list afterwards and is left blank when the function is called
	(pop Q)- returns the first element of priority queue Q
	(del Q)- deletes the first element of priority queue Q

	2.4
	(rem-all l1 l2 match)- compares every element of l1 to every element in l2 (using compare function match), if there is a common element, it is removed
		from l1, the trimmed list is returned
	
	
 
	