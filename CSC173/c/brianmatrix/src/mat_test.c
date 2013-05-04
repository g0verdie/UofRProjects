#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix_types.h"
#include "matrix.h"



main()
{

    matrix_t m1, sqmat ;
    enum mat_err_no  merr;
    int i,j;
	printf("test");
    /* make a matrix  */
    m1 = mat_new(3,1);
   
    e(m1,0,0) = 1.0;
    e(m1,1,0) = 2.0;
    e(m1,2,0) = 3.0;

    sqmat = mat_new(2,2);
    for (i = 0; i<2;i++)
	for (j = 0; j<2;j++)
		    sqmat-> mat[i][j] = (i+1)+ (j+1)*10;

    /* print a matrix  */

    merr = mat_pr("Test Mat_Pr on m1", m1);
    merr = mat_pr("Test Mat_Pr on 2x2", sqmat);

    /* more demo code goes here */    

}

/********from the assignment *************/

/*Modify this file, which will form
the 'driver' that demonstrates your matrix
code. 

Your matrix code is four additions to matrix.c, with functions to
* read (from disk and/or from the terminal):
   matrix_t mat_frd(char* fname) -- creates matrix from human-readable file.
    fname is a C string.    
   The file can or should contain "meta-information" about the matrix to
    simplify things.  My file <tt>sample_matrix</tt> implicitly shows the format I use...
    <int no. of rows>,  <int no. of cols>, <rows x cols floats giving matrix
    entries in scan-line (cols w/in rows) order>. fscanf() makes it all easy.


* add   -- enum mat_err_no = mat_add( matrix_t m1, matrix_t m2, 
   matrix_t m_ans, int sign);  
        Multiplies matrix m2 by scalar sign,
        adds result to matrix m2 and puts result in m_ans.  Idea is 
        that sign of 1 and -1 implements matrix addition and subtraction.  
        (return error code).

* multiply  -- something like (see N.B. below)
enum mat_err_no = mat_mult( matrix_t m1, matrix_t m2, matrix_t m_ans);  (return error code)
* transpose -- something like
     enum mat_err_no = mat_trans( matrix_t m_arg, matrix_t m_trans);  (return error code)

You've already got matrix creation and freeing and printing above.

N.B. I recommend the approach of creating matrices you need once, in advance.
This makes you less responsible for storage management. E.g.,

mat_err = mat_mult( matrix_t m1, matrix_t m2, matrix_t m_ans);  (return error code)
rather than
mat_ans = mat_mult( matrix_t m1, matrix_t m2);   (return new matrix).


Your driver program shows off your ability to read, print, multiply, add, transpose,
and delete matrices, and your ability to deal gracefully with errors.
For instance, you can try to  print a deleted matrix,  try to add or multiply matrices
that aren't conformable for those operations (they're incompatible sizes),
etc. Whatever it takes to assure yourself everything works. You'll need to use an editor to make
some files that contain readable matrices. (one or two examples in this directory).

Make sure you can pre-multiply a (column) 4-vector by a square 4 x 4
matrix. And make sure you can post-multiply a a square 4 x 4 matrix  
by a (column) 4-vector.


Make sure you know how matrix addition, multiplication, transposition work!
Make sure you understand the makefile! 
If you don't see what's going on, ask someone!

Submit transcript or evidence it all works.
*/

