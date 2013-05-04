Dan Scarafoni
CSC 173
project 2

put_get_put.c- this is the driver program, it creates a tetrahedron, 	converts it into a .txt file (tetra.txt), writes this file, then 	reads the file in a reconstructs the tetrahedron, and then 	    	converts it into a .txt file again (tetra.txt)

FEV.c- contains numerous functions for initializing faces, vertecies, 	and edges

FEVtypes.h- defines the structs for vertecies/edges/faces and their 	printable versions. Printable versions replace all struct 	    	poitners with the serial numbers of the pointed to struct. Since 
    vertecies have no pointers, they are inherently "printable"

tetra.c- creates a tetrahedron structure and returns the pointer to 	the head face. It is first called by the driver to create a     	tetrahedron to be written to a file

struct2file.c- this takes a structure of faces, edges, and vertecies 
	converts it into a file format. It reads in the face list
	one at a time and places the information in an array of 
	printable faces. 
	When it encounters a "next edge" it takes this edge and enters
	it (and the other edges of the face) into an array of 		printable edges.
	As it's adding edges, it encounters vertecies, and enters 
	these into an array also. The program will make sure that 
	a vertex has not already been entered to avoid repition.
	
	The file then takes the information gathered and writes it to 		a file. The format used for the file is identical to Brown's
	sample code, but with one exception- all'next_edge' and 
	'next_face' structs are represented by their serial number,
	not array index.

file2struct.c- this takes a file of structure information and converts
	it into a struct. It opens the file, and then reads the 
	information in for the various structs, placing them into
	arrays of printable faces, vertecies, and edges (using 
	read_make_face, rad_make_vertex, and read_make_edge). Then, 
	using file_link_structs, it assembles this information into 
	the structure in the same manner as tetra.c (creating a face, 		filling it with its edges, then linking all faces together).

 
