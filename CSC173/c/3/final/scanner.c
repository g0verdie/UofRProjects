/**********************************************************************
    Tokenizer for Java source.

    Allows unicode escapes only within strings and comments.  Otherwise,
    accepts all and only those tokens defined by the Java language
    specification.  (Note that this is significantly more than you were
    required to do for assignment 3; this solution would have received a
    lot of extra credit.)

    Tokens are classified as listed in scanner.h.

 **********************************************************************/

#include <stdlib.h>
#include <stdio.h>
#include "reader.h"
#include "char_classes.h"
#include "keywords.h"
#include "scanner.h"

static void print_location (token_t *tok)
{
    fprintf(stderr, " at line %d, column %d\n",
        tok->location.line->line_num, tok->location.column);
}

/********
    Modify tok to describe next token of input.
    Update loc to refer to location immediately after tok.
 ********/
void scan(location_t * loc, token_t * tok)
{
    enum {
    		//starters
            start,
            got_space,
            got_nl_space,
            got_other,
            done,
            //numbers
            got_dot,
            got_0,
            got_0_89,
            got_oct,
            got_dec,
            got_fp_dot,
            //signs
            got_plus,
            got_minus,
            got_equals,
            got_star,
            got_slash,
    } state = start;

/* Standard way to recognize a token: put back lookahead character that
    isn't part of current token: */
#define ACCEPT_REUSE(t) \
    *loc = loc_save;    \
    tok->length--;      \
    tok->tc = t;        \
    state = done;

/* Shortcut to eliminate final states with no out transitions: go
    ahead and accept token in previous state, but don't put back the
    lookahead: it's actually part of the token: */
#define ACCEPT(t) 												\
    tok->tc = t;  					 							\
    state = done;					 							\

    tok->location = *loc;
    tok->length = 0;

    while (state != done)
    {
        location_t loc_save = *loc;
        int c = get_character(loc);
        tok->length++;
        switch (state) {
            case start:
                switch (char_classes[c])
                {
                    case WHITE:
                        state = got_space;
                        break;
                    case EOLN:
                        state = got_nl_space;
                        break;
                    case DOT:
                        state = got_dot;
                        break;
                    case DIG_0:
                        state = got_0;
                        break;
                    CASE_NZ_DIGIT:
                        state = got_dec;
                        break;
                    case PLUS:
                        state = got_plus;
                        break;
                    case MINUS:
                        state = got_minus;
                        break;
                    case EQUALS:
                        state = got_equals;
                        break;
                    case STAR:
                        state = got_star;
                        break;
                    case SLASH:
                    	state = got_slash;
                    	break;
                    case LPAREN:
                        ACCEPT(T_LPAREN);
                        break;
                    case RPAREN:
                        ACCEPT(T_RPAREN);
                        break;
                    case SEMIC:
                        ACCEPT(T_SEMIC);
                        break;
                    case END:
                        ACCEPT_REUSE(T_EOF);
                        break;
                    case OTHER:
                        /* This will be an error.  Eat as many bogus
                            characters as possible. */
                        state = got_other;
                        break;
                }
                break;

            case got_space:
                switch (char_classes[c])
                {
                    case WHITE:
                        break;  /* stay put */
                    case EOLN:
                        state = got_nl_space;
                        break;
                    default:
                        ACCEPT_REUSE(T_SPACE);
                        break;
                }
                break;

            case got_nl_space:
                switch (char_classes[c])
                {
                    case WHITE:
                    case EOLN:
                        break;  /* stay put */
                    default:
                        ACCEPT_REUSE(T_NL_SPACE);
                        break;
                }
                break;

            case got_other:
                switch (char_classes[c])
                {
                    case OTHER:
                    case WHITE:
                    case EOLN:
                        break;  /* stay put */
                    default:
                        fprintf(stderr, "Invalid token");
                        print_location(tok);
                        ACCEPT_REUSE(T_SPACE);    /* most likely recovery? */
                        break;
                }
                break;
            case done:
                fprintf(stderr, "scan: unexpected done in switch\n");
                exit(-1);
                break;

            /* operators: */
            case got_plus:
            	ACCEPT_REUSE(T_ADD);
                break;
            case got_minus:
            	ACCEPT_REUSE(T_MIN);
            	break;

            case got_star:
            	ACCEPT_REUSE(T_MULT);
                break;

            case got_slash:
                ACCEPT_REUSE(T_DIV);
                break;

            case got_dot:
                switch (char_classes[c])
                {
                    CASE_DEC_DIGIT:
                        state = got_fp_dot;
                        break;
                    default:
                        ACCEPT_REUSE(T_DOT);      /* dot */
                        break;
                }
                break;

            case got_0:
                switch (char_classes[c])
                {
                    case LET_L:
                        ACCEPT(T_LITERAL);        /* 0L */
                        break;
                    case DIG_89:
                        state = got_0_89;
                        break;
                    case DOT:
                        state = got_fp_dot;
                        break;
                    default:
                        ACCEPT_REUSE(T_LITERAL);  /* 0 */
                        break;
                }
                break;

            case got_0_89:
                switch (char_classes[c])
                {
                    CASE_DEC_DIGIT:
                        break;  /* stay put */
                    case DOT:
                        state = got_fp_dot;
                        break;
                    CASE_LET_DF:
                        ACCEPT(T_LITERAL);        /* FP w/ suffix */
                        break;
                    default:
                        fprintf(stderr, "Invalid decimal literal");
                        print_location(tok);
                        ACCEPT_REUSE(T_LITERAL);  /* punt */
                        break;
                }

                break;
            case got_dec:
                switch (char_classes[c])
                {
                    CASE_DEC_DIGIT:
                        break;  /* stay put */
                    case DOT:
                        state = got_fp_dot;
                        break;
                    default:
                        ACCEPT_REUSE(T_LITERAL);  /* decimal integer */
                        break;
                }
                break;

            case got_fp_dot:
                switch (char_classes[c])
                {
                    CASE_DEC_DIGIT:
                        break;  /* stay put */
                    CASE_LET_DF:
                        ACCEPT(T_LITERAL);        /* fp w/ suffix */
                        break;
                    default:
                        ACCEPT_REUSE(T_LITERAL);  /* fp */
                        break;
                }
                break;
        }
    }
}
