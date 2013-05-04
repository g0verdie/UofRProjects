/**********************************************************************
    HTML tags

 **********************************************************************/

#include <stdio.h>
#include "scanner.h"
#include "html.h"

char *file_header = "\
<html>\n\
<head>\n\
<title>Formatted Java output</title>\n\
</head>\n\
<body bgcolor=white>\n\
<pre>\n\
";

char *file_footer = "\
</pre>\n\
</body>\n\
</html>\n\
";

FILE* FP;
void print_token(const token_t * tok)
{
	FP = fopen("tout.txt","a+");
    location_t loc = tok->location;
    int i;
    char c;
    for (i = tok->length; i; i--)
    {
        c = get_character(&loc);
        printf("%c",c);
        fprintf(FP,"%c",c);
    }
    printf("\n");
    fclose(FP);
}
