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
edge_t E[SIZE];


main ()
{

	FILE *FP;
	FILE *FP2;
	face_t FEV_struct_eg, new_head_pointer;
	//archive
	printf("-------------------struct->file----------------------------\n");
	FP = fopen("tetra.txt","w");
	FEV_struct_eg = new_tetrahedron();
	face_to_file(FP, FEV_struct_eg);
	fclose(FP);

	printf("-------------------file->struct----------------------------\n");
	FP = fopen("tetra.txt","r");
	new_head_pointer = file_make_structs(FP);
	fclose(FP);

	printf("-------------------struct->file pt2----------------------------\n");
	FP2 = fopen("tetra2.txt","w");
	printf("roundabout222- %d\n",new_head_pointer->next_face->next_face->next_face->next_face->serial);
	face_to_file(FP2, new_head_pointer);
	fclose(FP2);
}
