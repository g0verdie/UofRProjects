/**********************************************************************
    Keyword table and lookup routine.

 **********************************************************************/

#include <stdlib.h>
#include <string.h>
#include "keywords.h"

typedef struct {
    char       * name;
    int          len;
    token_class  tc;
} keyword_t;

#define NUM_KEYWORDS   3

static keyword_t keyword_table[NUM_KEYWORDS] = {
    { "false",           5, T_LITERAL },
    { "null",            4, T_LITERAL },
    { "true",            4, T_LITERAL },
};

int compar (const void * key, const void * elem)
{
    keyword_t * kw = (keyword_t *) elem;
    token_t *tok = (token_t *) key;

    int cmp = strncmp(&tok->location.line->data[tok->location.column],
        kw->name, kw->len);
    if (!cmp && tok->length > kw->len) {
        return 1;
    } else {
        return cmp;
    }
}

/********
    Look up token (assumed to be an identifier) in a table of keywords
    and literals.  If found, change token class accordingly.
 ********/
void filter_keywords(token_t * tok)
{
    keyword_t * p = (keyword_t *)
        bsearch((void *) tok, (void *) keyword_table,
            NUM_KEYWORDS, sizeof(keyword_t), compar);

    /* p is now either 0, indicating that tok is really an identifier,
        or a pointer into keyword_table. */

    if (p) tok->tc = p->tc;
}
