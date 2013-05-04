backwards([],[]).
backwards([H|T],X) :- 
		backwards(T,Y), paste(Y,[H],X).
	
	paste([],L,L).
	paste([H|T],L2,[H|L3]) :- 
		paste(T,L2,L3).


palindrome(P) :- read(X),
		name(X,Y),
		backwards(Y,Y),
		name(X,P).
%i is father's son
parent(father,i).
child(i,father).
%i married widow
married(i,widow).
married(widow,i).
%wDaughter is widow's child
parent(widow,wDaughter).
child(wDaughter,widow).
%father married wDaughter
married(father,wDaughter).
married(wDaughter,father).
%i and widow had a son
parent(i,son).
parent(widow,son).
child(son,i).
child(son,widow).
%father and wDaughter had a son
parent(father,wDSon).
parent(wDaughter,wDSon).
child(wDSon,wDaughter).
child(wDSon,father).

%relationships

pMar(Parent,Child) :- (
			married(Parent,X);
			married(Child,Y)
			),
			(
				%my child
				parent(Parent,Child); 
				%spouse's child
				parent(X,Child);
				%my child's spouse
				parent(Parent,Y);
				%spouse's child's spouse
				parent(X,Y)
			).
grandparent(Gprnt,Gchild) :- pMar(Gprnt,Parent),pMar(Parent,Gchild).
brother(Sibling1,Sibling2) :- pMar(Parent,Sibling),pMar(Parent,Sibling2).
uncleAunt(U,N) :- brother(U,Parent),pMar(Parent,N).

			

