#include <stdio.h>
#include <stdlib.h>
#include "scanner.h"
#include "parser.h"

init(parse_tree head)
{
	parse_tree new_node = (parse_tree)malloc(sizeof(struct tree));
	new_node->type = EXPR;
	new_node->left = NULL;
	new_node->right = NULL;
	*head = *new_node;
}

void tree_token(parse_tree head, token_class token, float val)
{
	//make two new nodes
	parse_tree new_node = (parse_tree)malloc(sizeof(struct tree));
	//temp holds the place of the old head node
	parse_tree temp = (parse_tree)malloc(sizeof(struct tree));
	*temp = *head;

	if(token == T_LITERAL)
	{
		//new node is a factor
		new_node->type = FACTOR;
		new_node->value = val;
		//go to the deepest right node
		while(head->right != NULL)
			head = head->right;
		//if the top node was an expression
		if(temp->type == EXPR)
		{
			//new_term is the right side of the depest right term
			parse_tree new_term = (parse_tree)malloc(sizeof(struct tree));
			new_term->type = TERM;
			new_term->left = NULL;
			//new node is the right child of new_term
			new_term->right = new_node;
			head->right = new_term;
		}
		//if it's not, don't sandwich in a new node
		else
		{
			head->right = new_node;
		}
	}

}

void tree_token2(parse_tree head, token_class token, int val)
{
	//new node- node to be added
	parse_tree new_node = (parse_tree)malloc(sizeof(struct tree));
	//temp- holder for head
	parse_tree temp = (parse_tree)malloc(sizeof(struct tree));
	*temp = *head;

	//if it's addition/sub
	if(token == T_ADD)
	{
		//new node is an expression
		new_node->type = EXPR;
		new_node->operation = val;
		//put it at the top, left child is old head
		new_node->left = temp;
		new_node->right = NULL;
		*head = *new_node;
	}
	//mult/div
	else if(token == T_MULT)
	{
		//temp_term is rightchild of head
		parse_tree temp_term = temp->right;
		//new node is a term
		new_node->type = TERM;
		new_node->operation = val;
		//temo term is left child of new_node
		new_node->left = temp_term;
		new_node->right = NULL;
		head->right = new_node;
	}
}
