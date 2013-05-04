Dan Scarafoni
dscarafo@u.rochester.edu

node on classes(stacks, queues, etc...)

the stack and queue classes are subclasses of a generic LinkedList class.
This class defines a double linked list with methods to call the head and tail (which are 
both private) of
the list, initiates an empty linked list (one with only a head and tail node).

The LinkedList class contains a subclass for generic data nodes. Each nodes contains
a data cell, and a reference to a next and previous node.

LinkedList impements an interface for its functions, such as insert, delete, lookup
etc...

insert creates a node and adds it to the tail of the list
lookup searches the tree and searches for a value.
delete searches the tree for a value. If it is found, the previous and next 
	references of the adjascent nodes are altered to exclude the node from the list
getLength finds the number of elements in the string
toString converts the elements in the linked list (excluding the head and tail)
	into an array of strings
all of the above elements usd a recursive algorithm.


the Stack class was a subclass of LinkedList, and implemented its own interface
for functions unique to this structure (pop, push, and read).

Pop removed the end element from the list and returned it.

push called the insert method from the parent LinkedList class, inserting an element
at the tail.

read read the value at the end of the stack and returned it (this was used for
debugging features).


Queue was also a subclass of LinkedList

the enqueue method called the insert method from the LinkedList class, and dequeue
called the delete method on the head.next node. As with stack, this class was 
generic and implemented its own interface called QueueInterface

It is worth noting that all insertion and removal methods used in this program
took constant time because they simply involved inserting/deleting nodes adjascent 
to the head or tail nodes.

----------------------------------------------------------------------------
step by step of methods:

This program begins by reading the assigned text file as a string.
It converts the program into an array of strings, separated at line breaks

For each line, the tokenizer method is used to convert the string into an array
of strings, each representing either a number or an operator (spaces in the input
line are ignored).
This program works by using a switch statement. If it encounters an operator,
it adds it to the end of d linked list of strings. if it encounters a space, it does nothing.
if it encounters anything else, it creates a holder to hold the variable until
an operator is encountered, at which point the held value and the operator are 
then inserted to the list (in that order). 
The linked List is then converted to an array of strings

it initiates a calculator (Calc) and feeds the line as input.
The calculator initiates a String Stack, a String Queue, and a Double Stack.
The string stack would be used to hold variables during infix to postfix conversion
The string queue held the postfix notation, and the double stack held the total for
the arithmatic answer. strings were chosen for the latter two for consistency reasons.
With all elements of the same type, it would be easier and more safe to execute
comparisons amongst the members of these structures.


The driver program then calls the toPost method on the input, which converts
the array from infix to postfix notation. This method ran through the string
one element at a time. If the value was an operand, it queued it.
If it was a "(" it was pushed onto the stack. 
If it was a ")" everything on the stack was popped off and queued
	until a "(" was found (nether parentheses signs were queued).
if it was an operator, everything on the stack was popped until an operator of lower
precedence was encounted, at which point the operator was pushed.
Once the end of the infix array was reached, the stack popped and the elements
queued repeatedly until it was empty.

The calculation program doCalc was then called. the elements in the postfix
were read one elment at a time from start to finish. If they were operators, 
the corresponding number of operands were read (1 for !, 2 for all others). 
This was then fed into a switch statement and the corresponding operation performed.
The resulting number was pushed onto the stack. At the end of the method, the 
sum was returned and the answer was printed.
it was emptied
