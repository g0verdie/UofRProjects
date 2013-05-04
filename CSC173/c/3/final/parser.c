/**********************************************************************
 /**********************************************************************
    Java parser and pretty-printer.

    Uses a recursive-descent parser that accepts a superset of the
    language.

    At the moment it's an unreasonably large superset: basically any
    string of tokens with balanced curvy braces is accepted inside a
    class definition.  You have to fix that by fleshing out the routines
    that parse a compound_stmt.  You also have to
        - find all declared identifiers, so you can print them red
        - indent things appropriately
        - fix inter-token spaces

 **********************************************************************/

#include <stdlib.h>
#include <stdio.h>
#include "reader.h"
#include "scanner.h"
#include "parser.h"

static token_t     tok;
static location_t  loc;

static int indent_level = 0;
#define    INDENT_WIDTH   4

/* forward declarations: */
static float getNum();
static void stmnt_List();
static float stmnt();
static float E();
static float T();
static float F();
static float Ttail(float value);
static float Etail(float value);
static void error_rep();
FILE* FP;
static int at_bol = 1;
static int comment_nl = 0;
float hold;
//print newline
static void newline()
{
    if (comment_nl) {
        comment_nl = 0;
    } else {
        putchar('\n');
        at_bol = 1;
    }
}

//print token
static void put_token()
{
    if (at_bol)
    {
        int i = (indent_level) * INDENT_WIDTH;
        while (i--)
            putchar(' ');
    }
    else
    {
        if (tok.tc == T_NEW_COMMENT || tok.tc == T_OLD_COMMENT)
        {
            int i = INDENT_WIDTH;
            while (i--)
                putchar(' ');
        }
        else
            putchar(' ');
    }
    at_bol = 0;
    comment_nl = 0;      //no longer care if comment printed a newline
}

//gets the next token from the parser
static void get_token()
{
    token_class prev_class = T_SPACE;
    do
    {
    	//get token
        scan(&loc, &tok);
        prev_class = tok.tc;

       if (tok.tc == T_NL_SPACE && prev_class == T_OLD_COMMENT)
       {
             //comment was followed by a newline
            newline();
            comment_nl = 1;      //remember this newline
        }
        if (tok.tc == T_OLD_COMMENT || tok.tc == T_NEW_COMMENT)
        {
            if (prev_class == T_NL_SPACE)
            {
                 //comment was preceded by a newline
                newline();
                comment_nl = 1;      //remember this newline
            }
            if (tok.tc == T_NEW_COMMENT)
            {
                 //newline is not part of comment
                comment_nl = 0;      //we really do want this one
                newline();
                comment_nl = 1;      //remember this newline
            }
        }
    }
    while (tok.tc == T_SPACE       || tok.tc == T_NL_SPACE
          || tok.tc == T_OLD_COMMENT || tok.tc == T_NEW_COMMENT);
}

//parse error
static void parse_error()
{
    fprintf(stderr, "Syntax error");
    fprintf(stderr, " at line %d, column %d\n",
        tok.location.line->line_num, tok.location.column);
    exit(1);
}

/********
    A token of class tc is expected from the scanner.  Verify and print.
 ********/
static void match(token_class tc)
{
    if (tc == T_ID_DEC && tok.tc == T_IDENTIFIER)
    	tok.tc = T_ID_DEC;
    if (tc != tok.tc)
        parse_error();
    put_token();
    get_token();
}

static float getNum()
{
	char *holder;
	holder = malloc(tok.length * sizeof(char));
	int j;
	location_t loc = tok.location;
	for(j = 0; j < tok.length; j++)
		holder[j] = get_character(&loc);

	printf("%f\n",atof(holder));
	match(T_LITERAL);
	return atof(holder);
}

//main parsing program
void parse(FILE* FPH)
{
	FP = FPH;
    set_to_beginning(&loc);
    get_token();
    stmnt_List();
    fclose(FP);
}

static void stmnt_List()
{
	printf("smnt_list\n");
	//add semicolons when not sleepy
	switch(tok.tc)
	{
		case T_LITERAL:
		case T_LPAREN:
			hold = stmnt();
			printf("%f\n",hold);
			fprintf(FP,"%f \n",hold);
			match(T_SEMIC);
			stmnt_List(FP);
			break;
		case T_EOF:
			printf("end\n");
			break;
		default:
			error_rep();
			break;
	}
}

static float stmnt()
{
	printf("smnt\n");
	return E();
}

static float E()
{
	printf("E\n");
	return Etail(T());
}

static float T()
{
	printf("T\n");
	return Ttail(F());
}

static float F()
{
	printf("F\n");
	switch(tok.tc)
	{
		case T_LPAREN:
			printf("(");
			match(T_LPAREN);
			float hold = E();
			if(tok.tc == T_RPAREN)
			{
				match(T_RPAREN);
				printf(")");
				return hold;
			}
			else
				error_rep();
			break;
		case T_LITERAL:
			return getNum();
			break;
		default:
			error_rep();
			break;
	}
}

static float Ttail(float value)
{
	printf("Ttail\n");
	printf("VALUE = %f\n",value);
	switch(tok.tc)
	{
	 case T_MULT:
		 match(T_MULT);
		 printf("mult\n");
		 return value * Ttail(F());
		 break;
	 case T_DIV:
		 match(T_DIV);
		 printf("div\n");
		 return value / Ttail(F());
		 break;
	 default:
		 printf("null\n");
		 return value;
		 break;
	}
}

static float Etail(float value)
{
	printf("Etail\n");
	printf("Value %f\n",value);
	switch(tok.tc)
		{
		 case T_ADD:
			 match(T_ADD);
			 printf("add\n");
			 return Etail(value + T());
			 break;
		 case T_MIN:
			 match(T_MIN);
			 return Etail(value - T());
			 break;
		 default:
			 printf("num\n");
			 return value;
			 break;
		}
}
static void error_rep()
{
	printf("syntax error\n");
	exit(0);
}
