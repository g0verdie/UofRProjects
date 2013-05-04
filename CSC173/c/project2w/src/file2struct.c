#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix/matrix_types.h"
#include "matrix/matrix.h"
#include "FEVtypes.h"
#include "FEV.h"


/* the two-pass algorithm to construct a data structure with pointers
   from its printable description*/

/*   reconstructs FEV -style data structures with pointers from a
human-readable file describing p_face_t -style  printable structures
that live in arrays and have int indices instead of pointers.
*/

#define SIZE 16

 vertex_t V[SIZE];
    face_t F[SIZE];
    edge_t E[SIZE];  /* for reconstituting pstructs on disk into pointers*/

/*
This main was used in debugging... give it a file in proper
 archival format.

main ()
{

FILE *FP;
char in_file[64];
face_t new_head_pointer;
 int  i, pstruct_count, pstruct_type;


printf("\n welcome. input pstruct filename:");
scanf("%s", in_file);

       FP = fopen(in_file, "r");

new_head_pointer = file_make_structs(FP);
 rewind(FP);

 file_link_structs(FP);


 short_poly_pr(new_head_pointer);

 edge_ring_pr(E[0]);

 for (i = 0;i<SIZE;i++)
     {  V[i] = NULL;
     E[i] = NULL;
     F[i] = NULL;
     }

 short_poly_pr(new_head_pointer);
}
*/



face_t file_make_structs(FILE *FP)
/* a little annoying we need to know type of head pointer*/


{

    int i,pstruct_type, head_index, pstruct_count;
    vertex_t v1;
    edge_t e1;
    face_t f1;

    /* work thru file creating structs from pstruct information...
   call the read_make_ routines below.
       don't try to fill in pointers yet tho!*/

		return F[head_index];

    }


/* for instance ... */

edge_t read_make_edge(FILE *FP )
{
    int type, ser;
    int fvndx, tvndx, endx;
    edge_t newedge;
    fscanf(FP, "%d ", &type);

    /* ... */
    E[ser] = newedge;
    return newedge;
}

vertex_t read_make_vertex(FILE *FP, vertex_t *V)
{
    int typo, ser;
    int u,v,w;

    /* ... similar */
}



face_t read_make_face(FILE *FP )
{
    /*  ... similar ...*/

}



file_link_structs(FILE *FP)

{

    /*go thru file again, ignore the vertices which dont have
    pointers,  and fill up  pointer fields in the edge and face
      structs we just made */


}


read_link_edge(FILE *FP )
{
    int ser, tvndx;
    /*using routines like this */

    /* .... */

    E[ser] ->to_vertex =V[tvndx];
    /* .... */
}

read_link_face(FILE *FP)
{
    int type, ser;
    int endx, fndx;
    face_t newface;

    /* and this*/
}

