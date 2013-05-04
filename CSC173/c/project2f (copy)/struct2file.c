#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix/matrix_types.h"
#include "matrix/matrix.h"
#include "FEVtypes.h"
#include "FEV.h"


#define SIZE 16

p_face_t   F_arr[SIZE];
int        fSize;
p_edge_t   E_arr[SIZE];
int        eSize;
p_vertex_t V_arr[SIZE];
int        vSize;

//top level, writes everything to file
void face_to_file(FILE *FP, face_t head_face)
{
	eSize = 0;
	fSize = 0;
	vSize = 0;
	V_arr[0] = NULL;
	//pass to the actual function
    F_to_array(head_face);

    //print the head pointer type and index
    fprintf(FP,"%d\n%d\n",
    		F_arr[0]->fev_type,
    		F_arr[0]->serial);

    //alert to vertecies
    fprintf(FP,"%d\n%d\n",
    		V_arr[0]->fev_type,
    		vSize);

    //print off all verts
    int i;
    for(i = 0; i < vSize; i++)
    {
    	fprintf(FP,"%d\n%d\n%lf\n%lf\n%lf\n",
    			V_arr[i]->fev_type,
    			V_arr[i]->serial,
    			V_arr[i]->vertex_coords->mat[0][0],
    			V_arr[i]->vertex_coords->mat[1][0],
    			V_arr[i]->vertex_coords->mat[2][0]);
    }

    //alert to edges
    fprintf(FP,"%d\n%d\n",
    		E_arr[0]->fev_type,
    		eSize);

    //print off all edges
    for(i = 0; i < eSize; i++)
    {
    	fprintf(FP,"%d\n%d\n%d %d\n%d\n",
    			E_arr[i]->fev_type,
    			E_arr[i]->serial,
    			E_arr[i]->from_vertex_serial,
    			E_arr[i]->to_vertex_serial,
    			E_arr[i]->next_edge_serial);
    }

    //alert to faces
    fprintf(FP,"%d\n%d\n",
    		F_arr[0]->
    		fev_type,fSize);

    //print off all faces
    for(i = 0; i < fSize; i++)
    {
    	fprintf(FP,"%d\n%d\n%d\n%d\n",
    			F_arr[i]->fev_type,
    			F_arr[i]->serial,
    			F_arr[i]->next_edge_serial,
    			F_arr[i]->next_face_serial);
    }
    /*//clear memory
    for(i = 0; i < vSize; i++)free(V_arr[i]);
    printf("success");
   for(i = 0; i < eSize; i++)
    {
	    E_arr[i]->from_vertex_serial = 10;
	    E_arr[i]->to_vertex_serial = 10;
    	E_arr[i]->next_edge_serial = 10;
    	free(E_arr[i]);
    }
    printf("\n double success");
    for(i = 0; i < fSize; i++)
    {
    	F_arr[i]->next_face_serial = 10;
    	free(F_arr[i]);
    }
    printf("\n after clear\n");*/

}




void F_to_array(face_t aface)
{
	//find number of faces
	int frontf = aface->serial;
	aface = aface->next_face;
	fSize++;
	while(aface->serial != frontf)
	{
		fSize++;
		aface = aface->next_face;
	}printf("calculated fSize to be- %d\n",fSize);

	//enter in the faces
	printf("entering faces now\n");
	int i;
	for(i=0; i < fSize; i++)
	{
		printf("on face- %d\n",aface->serial);
		F_arr[i]                   = malloc(sizeof(p_face_t));
		F_arr[i]->fev_type         = aface->fev_type;
		F_arr[i]->serial           = aface->serial;
		F_arr[i]->next_edge_serial = aface->next_edge->serial;
		F_arr[i]->next_face_serial = aface->next_face->serial;


		//enter edges of face
		printf("entering face #%d's edges\n",aface->serial);
		E_to_array(aface->next_edge);

		//go to next face
		aface = aface->next_face;
	}
}

int E_to_array(edge_t aedge )
{
	//find # edges
	int fronte = aedge->serial;
	aedge = aedge->next_edge;
	int eSizeLocal = 1;
	while(aedge->serial != fronte)
	{
		eSizeLocal++;
		aedge = aedge->next_edge;
	}printf("\t found eSize- %d \n",eSize);

	//go through each edge
	printf("\t entering edges \n");
	int i;
    for(i=eSize; i < eSize+eSizeLocal; i++)
    {
    	printf("\t entering edge #%d \n",aedge->serial);
    	E_arr[i]                     = malloc(sizeof(p_edge_t));
    	E_arr[i]->fev_type           = aedge->fev_type;
    	E_arr[i]->serial             = aedge->serial;
    	E_arr[i]->from_vertex_serial = aedge->from_vertex->serial;
    	E_arr[i]->to_vertex_serial   = aedge->to_vertex->serial;
    	E_arr[i]->next_edge_serial   = aedge->next_edge->serial;

    	//enter in its verts
    	printf("\t entering edge #%d's vertecies \n",aedge->serial);
    	V_to_array(aedge->from_vertex);
    	V_to_array(aedge->to_vertex);

    	//onto the next edge
    	aedge = aedge->next_edge;
    }
    eSize += eSizeLocal;
    return 1;
}

int V_to_array(vertex_t avertex)
{
	printf("\t\t checking if vertex %d is already entered\n",avertex->serial);
	int i;
	for(i=0;i<vSize+1; i++)
	{
		printf("\t\t checking vert index %d\n",i);
		if(V_arr[i] == NULL)
		{
			printf("\t\t inserting vert %d \n",avertex->serial);
			V_arr[i]                = malloc(sizeof(p_vertex_t));
			V_arr[i]->fev_type      = avertex->fev_type;
			V_arr[i]->serial        = avertex->serial;
			V_arr[i]->vertex_coords = avertex->vertex_coords;
			vSize++;
			V_arr[i+1] = NULL;
			return 1;
		}
		else if(V_arr[i]->serial == avertex->serial)
		{
			printf("\t\t vertex %d is already entered\n",avertex->serial);
			return 0;
		}
	}
	return 1;
}
