OBJS = put_get_put.o file2struct.o tetra.o struct2file.o matrix.o FEV.o
INCS = FEVtypes.h FEV.h matrix/matrix.h matrix/matrix_types.h
FLAGS =  -g

runtest:	$(OBJS)
		gcc -o runtest $(FLAGS) $(OBJS)


put_get_put.o:	put_get_put.c $(INCS)
		gcc -c $(FLAGS) put_get_put.c


file_to_struct.o:       file2struct.c $(INCS)
		gcc -c $(FLAGS) file2struct.c

tetra.o:	tetra.c $(INCS)
		gcc -c $(FLAGS) tetra.c

FEV.o:		FEV.c  $(INCS)
		gcc -c $(FLAGS) FEV.c

matrix.o:	matrix/matrix.c   $(INCS)
		gcc -c $(FLAGS) matrix/matrix.c

# your matrix code may be elsewhere of course
 
clean:		
		rm *.o
