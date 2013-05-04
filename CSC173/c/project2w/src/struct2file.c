#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix/matrix_types.h"
#include "matrix/matrix.h"
#include "FEVtypes.h"
#include "FEV.h"


/* the recursive depth-first algorithm to follow pointers and
   create the p_struct arrays */


#define SIZE 16

p_face_t F_arr[SIZE];
p_edge_t E_arr[SIZE];
p_vertex_t V_arr[SIZE];

int type_counts[20];    /*indexed by FACE, EDGE, VERTEX codes*/

//top level, writes everything to file
void face_to_file(FILE *FP, face_t head_face)
{
	//need to make the first NULL for a later function
	V_arr[0] = NULL;

    //put the faces into an array
    int head_index = F_to_array(head_face);
    printf("done filing");
    //FILE* tfile;
    //tfile = fopen("test.txt","w");
    //fprintf(tfile, "hello");
    //fprintf(tfile, "part 2");
    //fclose(tfile);
    //write the arrays on a file
    //fprintf(FP,"%d",F_arr[1]->serial);
    int i;
    for(i = 0; i < 4; i++)
    {
    	printf("writing face num- %d \n",E_arr[i]->serial);
    	fprintf(FP,"%d",E_arr[i]->serial);

    }
    fclose(FP);


}




int F_to_array(face_t aface)
{
	printf("beginning face \n");
	int frontf = aface->serial;
	int i = 0;
	int j;
	//go through each face
	F_arr[i] = aface;
	printf(" face num- %d \n",F_arr[i]->serial);
	//printf("edge id- %d",aface->next_edge->serial);
	int e_index = E_to_array(aface->next_edge);
	aface = aface->next_face;
	i++;
	while(aface->serial != frontf)
	{
		F_arr[i] = aface;
		printf(" face num- %d \n",F_arr[i]->serial);
		//put the face's edges into an array
		int e_index = E_to_array(aface->next_edge);
		aface = aface->next_face;
		i++;
		printf("something");
	}
return 0;
}

int E_to_array(edge_t aedge )
{
	//find the serial number of the starter
	int start = aedge->serial;
	int i = 0;

	//first edge
	printf("\tedge id is %d\n",aedge->serial);
	    	int storeVert1 = V_to_array(aedge->from_vertex);
	    	int storeVert2 = V_to_array(aedge->to_vertex);
	    	aedge = aedge->next_edge;
	    	i++;
	//go through each edge
    while(aedge->serial != start)
    {
    	printf("\tedge id is %d\n",aedge->serial);
    	int storeVert1 = V_to_array(aedge->from_vertex);
    	int storeVert2 = V_to_array(aedge->to_vertex);
    	aedge = aedge->next_edge;
    	i++;
    }
    printf("\tending edge\n");
return 1;
}

int V_to_array(vertex_t avertex)
{
	int serial = avertex->serial;
	//go through each vertex in the array
	int i;
	for(i = 0; i < SIZE; i++)
	{
		//if its not, put it in
		if(V_arr[i] == NULL)
		{
			V_arr[i] = avertex;
			V_arr[i+1] = NULL;
			printf("\t\t inserting vertex %d\n",V_arr[i]);
			return 1;
		}

		//if it's already there, stop
		else if(V_arr[i]->serial == serial)
		{
			//printf("stopping vert");
			printf("\t\tending vert\n");
			return 0;
		}
	}
	return 1;
}
