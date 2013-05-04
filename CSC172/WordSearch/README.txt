dan scarafoni
dscarafo@u.rochester.edu

	This program takes a dictionary of legal words (separated by newLines) and a 
square two dimensional array of words as input. It searches the array for words
in the dictionary. The programs begins by hashing the dictionary to allow for
constant time lookup of words in the dictionary. 
	Seperate chaining was used as a hashing algorithm. This allowed for very
quick access at the cost of using more memory. This was chosed because lookup 
would be a function called very frequently, and the amount of memory needed for this
was still extremely small (in the range of less than 10 megabytes total) because
each data node in the list was only one word. The linked lists were single linked,
as they only needed to be searched from head to tail, not the other way around.
	The actual searching algorithm used for searching the puzzle involved
running through each letter one at a time. For each letter, the letters in all
adjascent directions (up, right up, right, right down, down, left down, left, left up)
were searched one letter at a time until the end of the puzzle was reached. 
if a word was found, it was added to another linked list of found words.
After the entire puzzle was searched, the found words list is printed out, with
a "hhh" as the node. 
	The algorithm is not as efficient as previous project, sporting a big O 
of N^3. Changes were made to try and increase efficiency (in both memory and runtime)
THe program initially used double linked lists, however this was unnecessary and
was scrapped.