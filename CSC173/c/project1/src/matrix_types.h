#ifndef _matrixtypes_h
#define _matrixtypes_h



/*********************general matrix types ***************/
typedef double *dvec;
typedef int *ivec;
typedef double **dmat;

//the type for matrix
typedef struct matrix
{
    int fev_type;  /*mysterious for now, wait a week!*/
    //rows
	int r;
	//columns
	int c;
	//actual data in the matrix cell
	dmat mat;
	} *matrix_t;

#endif
