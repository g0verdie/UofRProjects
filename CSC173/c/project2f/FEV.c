#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix/matrix_types.h"
#include "matrix/matrix.h"
#include "FEVtypes.h"
#include "FEV.h"



vertex_t new_vert(double x, double y, double z, int serial_no)
{
    vertex_t newvert;

         newvert = (vertex_t) malloc( sizeof(struct vertex));
	 newvert->fev_type = VERTEX;
         newvert->serial = serial_no;
         newvert->vertex_coords = mat_new(3,1);
	 e(newvert->vertex_coords,0,0) = x;
	 e(newvert->vertex_coords,1,0) = y;
	 e(newvert->vertex_coords,2,0) = z;
return(newvert);
}



edge_t new_edge( vertex_t efrom, vertex_t eto, int serial_no)
{
    edge_t newedge;
         newedge = (edge_t) malloc( sizeof(struct edge));
	 newedge->fev_type = EDGE;
         newedge->serial = serial_no;
         newedge->from_vertex = efrom;
         newedge -> next_edge = NULL;  /* for face circular edge list*/
         newedge->to_vertex = eto;
	 return(newedge);
}

face_t new_face (edge_t edges[], int n, int serial_no)  /* a little klunky, could use list*/
{
    face_t newface;
    int i;


         newface = (face_t) malloc( sizeof(struct face));
         newface->fev_type = FACE;
         newface->serial = serial_no;
	 newface->next_edge = edges[0];  /* we're copying a pointer */
	 /*	 printf("\n set face nextedge to %s", edges[0]->name);*/

	 for (i =0; i<(n-1); i++)
	     { edges[i]->next_edge = edges[i+1];
		 /* printf("\n set edge %d nextedge to %s", i, edges[i+1]->name);*/
              }
         edges[n-1]->next_edge = edges[0];
	 newface->next_face = NULL;  /*filled in at higher level*/
 return(newface);
}
