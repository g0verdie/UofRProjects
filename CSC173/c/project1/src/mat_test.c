#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <math.h>
#include "matrix_types.h"
#include "matrix.h"



main()
{

	//read a file
	matrix_t t1, add1, add2, mult1, mult2, trans, sum;
	t1 = mat_frd("test.txt");
	mat_pr("Test Mat_Pr on 2x2", t1);
	mat_free(t1);

	//note, printing the deleted matrix has caused the program to stop on some machines, hence it it commented out here
	//mat_pr("printing deleted",t1);

	//add 2 matricies
	add1 = mat_frd("add1.txt");
	add2 = mat_frd("add2.txt");
	sum = mat_frd("add2.txt");
	mat_add(add1,add2,sum,1);
	mat_pr("test mat_pr on add",sum);

	//multiply 2 matricies
	mult1 = mat_frd("mult1.txt");
	mult2 = mat_frd("mult2.txt");
	sum = mat_new(4,3);
	mat_mult(mult1,mult2,sum);
	mat_pr("test mat_pr on multiply",sum);

	//transpose
	trans = mat_frd("trans.txt");
	sum = mat_new(2,2);
	mat_trans(trans,sum);
	mat_pr("test mat_trans",sum);
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

