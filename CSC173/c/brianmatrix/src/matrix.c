#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix_types.h"
#include "matrix.h"


/* 173 C week 1:
   here you add matrix functionality (e.g. addition, multiplication...
   see mat_test.c ) */

/*Vectors are columns*/



dvec dvector(int n)
{
dvec a;
return  (dvec) malloc(n*sizeof(double));

}

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
 m = (dmat) malloc( r*sizeof(double *));
 if (m == NULL) return NULL;
 for (i= 0; i<r; i++)
 {
 m[i] = (dvec) malloc( c*sizeof(double));
 if (m[i] == NULL) return NULL;
 }
 a =(matrix_t) malloc ( sizeof(struct matrix));
 if ( a == NULL) return NULL;
 a->r = r;
 a->c = c;
 a->mat = m;
 a-> fev_type = MATRIX;
 return a;
 }

/*  can't just free the pointer to the matrix, there's no garbage collection */

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
  if (m == NULL) { cberror("null matrix arg in mat_pr"); return NILARG2;
}
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
