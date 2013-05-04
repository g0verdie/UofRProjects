#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix_types.h"
#include "matrix.h"


/* 173 C week 1:
   here you add matrix functionality (e.g. addition, multiplication...
   see mat_test.c ) */

/*Vectors are columns
 * makes a column of height n */
dvec dvector(int n)
{
	dvec a;
	return  (dvec) malloc(n*sizeof(double));
}

/* frees a column space */
void dvec_free(dvec v)
{
	free((char*) v);
}

/* dvec is double-precision vector data, used below for rows. dmat is
double-precision matrix data, the third field of a matrix struct.*/


/* create matrix by allocating r  c-long vectors for the rows,
   a vector of pointers to rows, and a pointer to the latter vector. */

matrix_t mat_new(int r,int c)  /*r rows, c cols*/
{
	int i;
	dmat m;
	matrix_t a;

	//allocate a number of rows in memory
	m = (dmat) malloc( r*sizeof(double *));

	//if it didn't work return NULL
	if (m == NULL) return NULL;

	//for every row, initialize a spot for each column
	for (i= 0; i<r; i++)
	{
		m[i] = (dvec) malloc( c*sizeof(double));

		//handle exceptions
		if (m[i] == NULL) return NULL;
	}
	//a is a matrix
	a =(matrix_t) malloc ( sizeof(struct matrix));
	if ( a == NULL) return NULL;
	a->r = r;
	a->c = c;
	a->mat = m;
	a-> fev_type = MATRIX;
	return a;
}

//from a read file
matrix_t mat_frd(char* fname)
{
	int c;
	int r;

	int i;
	int j;
	FILE* opened;
	opened = fopen(fname,"r");
	fscanf(opened, "%i %i", &r, &c);

	dmat m;
	matrix_t a;

	//allocate a number of rows in memory
	m = (dmat) malloc( r*sizeof(double *));

	//if it didn't work return NULL
	if (m == NULL) return NULL;

	//for every row, initialize a spot for each column
	for (i= 0; i<r; i++)
	{
		m[i] = (dvec) malloc( c*sizeof(double));
		if (m[i] == NULL) return NULL;
		for(j = 0; j < c; j++)
		{
			double hold;
			fscanf(opened," %lf ",&hold);
			m[i][j] = hold;
		}
	}
	fclose(opened);
	//a is a matrix
	a =(matrix_t) malloc ( sizeof(struct matrix));
	if ( a == NULL) return NULL;
	a->r = r;
	a->c = c;
	a->mat = m;
	a-> fev_type = MATRIX;
	return a;
}
/*  can't just free the pointer to the matrix, there's no garbage collection */

//gotta free all the used rows/cols/data
void mat_free(m)
matrix_t m;
{
	int i,r;
	r =m->r;
	for (i = 0; i<r; i++) free ((char *) m->mat[i]);
	free((char *) m->mat);
	free((char *) m);
}

/* not much of an error function...*/
void cberror (char *msg)
{
	fprintf(stderr, "\n %s \n", msg);
}


/* print out a matrix in a human-readable form */
enum mat_err_no  mat_pr(char *msg, matrix_t m)
{
	int i,j;
	int r,c;
	if (msg == NULL) { cberror("null message arg in mat_pr"); return NILARG1;}
	if (m == NULL) { cberror("null matrix arg in mat_pr"); return NILARG2;}
	r = m->r;
	c = m->c;
	printf("\nMATRIX: %s Type: %d , Dims %d, %d\n", msg,m->fev_type,r,c);
	for (i=0; i<r; i++)
	{
		printf("\n");
		for (j=0; j<c; j++)
			printf("%.3f ",m->mat[i][j]);
	}
	printf("\n");
	return OK;
}

//addition
enum mat_err_no mat_add(matrix_t m1, matrix_t m2, matrix_t m_ans, int sign)
{
	int i,j;
	if (m1 == NULL) { cberror("null message arg in m1"); return NILARG1;}
	if (m2 == NULL) { cberror("null matrix arg in m2"); return NILARG2;}
	if (m_ans == NULL) { cberror("null matrix arg in m_ans"); return NILARG3;}
	if (sign != 1) { cberror("null matrix arg in sign"); return NILARG4;}

	int r1,c1,r2,c2,r3,c3;
	r1 = m1->r;
	r2 = m2->r;
	r3 = m_ans->r;
	c1 = m1->c;
	c2 = m2->c;
	c3 = m_ans->c;

	if(r1 != r2 || c1 != c2){ cberror("matricies didn't match"); return NONCONFORM;}
	if(r1 != r3 || c1 != c3){ cberror("matricies didn't match"); return NONCONFORM;}

	for (i=0; i<r2; i++)
	{
		for (j=0; j<c2; j++)
		{
			m2->mat[i][j] *= sign;
		}
	}

	for (i=0; i<r1; i++)
	{
		for (j=0; j<c1; j++)
		{
			m_ans->mat[i][j] = m1->mat[i][j] + m2->mat[i][j];
		}
	}
	return OK;
}

//multiplier
enum mat_err_no mat_mult(matrix_t m1, matrix_t m2, matrix_t m_ans)
{
	int i,j,k,l;
	if (m1 == NULL) { cberror("null message arg in m1"); return NILARG1;}
	if (m2 == NULL) { cberror("null matrix arg in m2"); return NILARG2;}
	if (m_ans == NULL) { cberror("null matrix arg in m_ans"); return NILARG3;}

	int r1,c1,r2,c2;
	r1 = m1->r;
	r2 = m2->r;
	c1 = m1->c;
	c2 = m2->c;


	if(c1 != r2){ cberror("first two didn't match"); return NONCONFORM;}
	if(r1 != m_ans->r || c2 != m_ans->c){ cberror("last matrix wrong size"); return NONCONFORM;}

	for (i=0; i < m_ans->r; i++)
	{
		for (j=0; j<m_ans->c; j++)
		{
			double total = 0;
			for (k=0; k < c1; k++)
				{
					total += m1->mat[i][k] * m2->mat[k][j];
				}
			m_ans->mat[i][j] = total;
		}
	}
	return OK;
}

//transpose matrix
enum mat_err_no mat_trans(matrix_t m1, matrix_t m_ans)
{
	int i,j;
	if (m1 == NULL) { cberror("null message arg in m1"); return NILARG1;}
	if (m_ans == NULL) { cberror("null matrix arg in m2"); return NILARG2;}
	if (m_ans == NULL) { cberror("null matrix arg in m_ans"); return NILARG3;}

	int r1,c1,r2,c2;
	r1 = m1->r;
	r2 = m_ans->r;
	c1 = m1->c;
	c2 = m_ans->c;

	if(c1 != r2){ cberror("matricies didn't match"); return NONCONFORM;}

	for (i=0; i<r1; i++)
	{
		for (j=0; j<c1; j++)
		{
			m_ans->mat[i][j] = m1->mat[j][i];
		}
	}
	return OK;
}
