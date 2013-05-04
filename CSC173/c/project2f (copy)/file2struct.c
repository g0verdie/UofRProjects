#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix/matrix_types.h"
#include "matrix/matrix.h"
#include "FEVtypes.h"
#include "FEV.h"

#define SIZE 16

p_face_t F_arr2[SIZE];
face_t   F_arr2f[SIZE];
int      fSize2 = 0;

p_edge_t E_arr2[SIZE];
edge_t   E_arr2f[SIZE];
int      eSize2 = 0;

vertex_t V_arr2[SIZE];
int vSize2 = 0;

face_t header;

face_t file_make_structs(FILE *FP)
{
	//add the first head (pointer) node
	fSize2++;
	int typeh;
	int serialh;
	int nextt;
	fscanf(FP,"%d\n%d\n%d\n",&typeh,&serialh,&nextt);
	F_arr2[0] = malloc(sizeof(p_face_t));
	F_arr2[0]->fev_type = typeh;
	F_arr2[0]->serial = serialh;

	//fill in the verts
	printf("making verts\n");
	choose_struct(nextt,FP);
	//edges
	printf("making edges\n");
	fscanf(FP,"%d",&nextt);
	choose_struct(nextt,FP);
	//faces
	printf("making faces\n");
	fscanf(FP,"%d",&nextt);
	choose_struct(nextt,FP);

	//link structs
	printf("linking structs\n");
	file_link_structs();
	header = F_arr2f[0];
	return header;
}

void choose_struct(int nextt, FILE*FP)
{
	if(nextt == FACE)
		read_make_face(FP);
	if(nextt == EDGE)
		read_make_edge(FP);
	if(nextt == VERTEX)
		read_make_vertex(FP);
}

void read_make_vertex(FILE *FP, vertex_t *V)
{
    fscanf(FP,"%d\n",&vSize2);
    int i;
    int fev_type;
    int serial;
    double x, y, z;
    for(i = 0; i < vSize2; i++)
    {
    	fscanf(FP,"%d\n%d\n%lf\n%lf\n%lf\n",
    			&fev_type,
    			&serial,
    			&x, &y, &z);
    	V_arr2[i] = new_vert(x,y,z,serial);

    	printf("new vert: type-%d serial-%d x-%lf y- %lf z- %lf\n",
    	    			V_arr2[i]->fev_type,
    	    			V_arr2[i]->serial,
    	    			V_arr2[i]->vertex_coords->mat[0][0],
    	    			V_arr2[i]->vertex_coords->mat[1][0],
    	    			V_arr2[i]->vertex_coords->mat[2][0]);
    }
}

vertex_t findv(int serial)
{
	int i;
	for(i = 0; i< vSize2; i++)
	{
		if(V_arr2[i]->serial == serial)
			return V_arr2[i];
	}
	return NULL;
}

void read_make_edge(FILE *FP )
{
	fscanf(FP,"%d\n",&eSize2);
	int i;
	int fev_type,serial,from, to,next;
	for(i = 0; i < eSize2; i++)
	{
		fscanf(FP,"%d\n%d\n%d %d\n%d\n",
				&fev_type,
				&serial,
				&from,
				&to,
				&next);
		E_arr2[i] = malloc(sizeof(p_edge_t));
		E_arr2[i]->fev_type = fev_type;
		E_arr2[i]->serial = serial;
		E_arr2[i]->from_vertex_serial = from;
		E_arr2[i]->to_vertex_serial = to;
		E_arr2[i]->next_edge_serial = next;
		printf("edge: type- %d serial- %d from/to- %d %d next- %d\n",
						fev_type,
						serial,
						from,
						to,
						next);
	}

}

edge_t finde(int serial)
{
	int i;
	for(i = 0; i< eSize2; i++)
	{
		if(E_arr2f[i]->serial == serial)
			return E_arr2f[i];
	}
	return NULL;
}

p_edge_t findep(int serial)
{
	int i;
	for(i = 0; i< eSize2; i++)
	{
		if(E_arr2[i]->serial == serial)
			return E_arr2[i];
	}
	return NULL;
}

void read_make_face(FILE *FP )
{
    int fev_type, serial, next_edge, next_face;
    fscanf(FP,"%d",&fSize2);
    fscanf(FP,"%d\n%d\n%d\n%d\n",
    		&fev_type,
    		&serial,
    		&next_edge,
    		&next_face);
    F_arr2[0]->fev_type = fev_type;
    F_arr2[0]->serial = serial;
    F_arr2[0]->next_edge_serial = next_edge;
    F_arr2[0]->next_face_serial = next_face;

    int i;
    for(i = 1; i < fSize2; i++)
    {
    	F_arr2[i] = malloc(sizeof(p_face_t));
    	fscanf(FP,"%d\n%d\n%d\n%d\n",
    			&fev_type,
    			&serial,
    			&next_edge,
    			&next_face);
    	F_arr2[i]->fev_type = fev_type;
    	F_arr2[i]->serial = serial;
    	F_arr2[i]->next_edge_serial = next_edge;
    	F_arr2[i]->next_face_serial = next_face;

    	printf("face: type- %d serial- %d nexte- %d nextf- %d\n",
			F_arr2[i]->fev_type,
			F_arr2[i]->serial = serial,
			F_arr2[i]->next_edge_serial,
			F_arr2[i]->next_face_serial);
    }
}

face_t findf(int serial)
{
	int i;
	for(i = 0; i< fSize2; i++)
	{
		if(F_arr2f[i]->serial == serial)
			return F_arr2f[i];
	}
	return NULL;
}

void file_link_structs(FILE *FP)
{
	int i;
	for(i = 0; i < fSize2; i++)
		{
			//find # edges
			p_edge_t aedge = findep(F_arr2[i]->next_edge_serial);
			printf("%d\n",aedge->serial);
			int fronte = aedge->serial;
			int eSizeLocal = 1;
			aedge = findep(aedge->next_edge_serial);
			while(aedge->serial != fronte)
			{
				eSizeLocal++;
				aedge = findep(aedge->next_edge_serial);
			}printf("\t found eSize- %d \n",eSizeLocal);

			//make the set of edges
			edge_t edges[eSizeLocal];
			int j;
			for(j = 0; j < eSizeLocal; j++)
			{
				edges[j] = new_edge(findv(aedge->from_vertex_serial),findv(aedge->to_vertex_serial),aedge->serial);
				printf("egdge j- %d\n",edges[j]->serial);
				aedge = findep(aedge->next_edge_serial);
			}
			//make a face from the set of edgesF
			F_arr2f[i] = new_face(edges,eSizeLocal,F_arr2[i]->serial);
		}

		//link faces together
		for(i=0; i< fSize2-1; i++)
		{
			F_arr2f[i]->next_face = F_arr2f[i+1];
		} F_arr2f[fSize2-1]->next_face = F_arr2f[0];
		for(i = 0; i < fSize2; i++)
		{
			printf("face %d: nexte- %d nextf- %d\n",
							F_arr2f[i]->serial,
							F_arr2f[i]->next_edge->serial,
							F_arr2f[i]->next_face->serial);
		}
}
