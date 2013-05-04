/**********************************************************************
    Tokenizer for Java source.
 **********************************************************************/

#ifndef SCANNER_H
#define SCANNER_H

#include "reader.h"

typedef enum {
    T_EOF, T_SPACE, /* intra-line space (no newline characters) */
    T_NL_SPACE,     /* inter-line space (includes >= 1 newlines) */
    T_OLD_COMMENT,  /* shash-star delimited */
    T_NEW_COMMENT,  /* double-slash to end of line */
                    /* NB: Java manual says newline character is part of
                           comment, but we do NOT include it here. */
    T_ID_DEC,       /* identifier in its own declaration */
                    /* (not returned by scanner) */
    T_IDENTIFIER,   /* other identifier */
    T_ADD,
    T_MIN,
    T_MULT,
    T_DIV,
    T_LITERAL,
    T_LPAREN,
    T_RPAREN,
    T_SEMIC,
    T_DOT,
} token_class;

typedef struct {
    token_class tc;
    location_t location;
    int length;     /* length of token in characters (may span lines) */
} token_t;

void scan(location_t * loc, token_t * tok);
    /* Modify tok to describe next token of input.
        Update loc to refer to location immediately after tok. */

#endif
