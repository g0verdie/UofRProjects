#include <stdlib.h>
#include <stdio.h>
#include "reader.h"
#include "scanner.h"
#include "parser.h"
#include "parser.h"

int main ()
{
	FILE* FP;
	FP = fopen("test.txt","r");
    initialize_reader(FP);
    fclose(FP);

    FP = fopen("output.txt","w");
    parse(FP);
    finalize_reader();
    exit(0);
}
