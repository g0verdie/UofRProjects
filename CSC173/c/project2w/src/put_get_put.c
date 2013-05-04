#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix/matrix_types.h"
#include "matrix/matrix.h"
#include "FEVtypes.h"
#include "FEV.h"


#define SIZE 16

vertex_t V[SIZE];
face_t F[SIZE];
edge_t E[SIZE];  /* for reconstituting pstructs on disk into pointers*/


main ()
{

	FILE *FP ;
	face_t FEV_struct_eg;
	//archive
	FP = fopen("tetra.txt","w");
	FEV_struct_eg = new_tetrahedron();
	face_to_file(FP, FEV_struct_eg);

	/*
	//retrieve
	FP = fopen("pgp.data", "r");

	new_head_pointer = file_make_structs(FP);

	rewind(FP);

	//file_link_structs(FP);

	//it's back

	//let's see...
	printf("\nFIRST PRINT\n");
	short_poly_pr(new_head_pointer);

	//zap the pointer arrays
	for (i = 0;i<SIZE;i++)
	{  V[i] = NULL;
	E[i] = NULL;
	F[i] = NULL;
	}
	// should still be linked correctly
	printf("\nSECOND PRINT\n");
	short_poly_pr(new_head_pointer);

	fclose(FP);

	//re-archive
	FP =  fopen("pgp2.data", "w");
	face_to_file(FP, new_head_pointer);
	//now can compare files

	close(FP);
	*/
}
