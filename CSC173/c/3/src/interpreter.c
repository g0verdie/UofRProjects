#include <stdio.h>
#include <stdlib.h>
#include "parser.h"

float eval(parse_tree tree)
{
	if(tree->type == EXPR)
	{
		printf("expr\n");
		if(tree->left == NULL)
			return eval(tree->right);
		else
			return (tree->operation == PLUS)? eval(tree->left) + eval(tree->right) : eval(tree->left) - eval(tree->right);
	}
	else if(tree->type == TERM)
	{
		printf("term\n");
		if(tree->left == NULL)
			return eval(tree->right);
		else
			return (tree->operation == MULTIPLY)? eval(tree->left) * eval(tree->right) : eval(tree->left) / eval(tree->right);
	}
	else//if(tree->type == FACTOR)
	{
		printf("factor\n");
		if(tree->right == NULL)
			return tree->value;
		else
			return eval(tree->right);
	}
}

void interpret(parse_tree tree)
{
	printf("The result is %f\n", eval(tree));
}
