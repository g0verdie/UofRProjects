%facts
noun(apple,s,v).
noun(apples,p,v).
noun(boy,s,c). 
noun(boys,p,c).
noun(girl,s,c).
noun(girls,p,c).
noun(government,s,c).
noun(governments,p,c).
noun(watermelon,s,c).
noun(watermelons,p,c).
noun(person,s,c).
noun(people,p,c).
noun(flavor,s,c).
noun(flavors,p,c).
det(a,c).
det(an,v).
det(the,c).
det(the,v).
verb(conscripts,s).
verb(conscript,p).
verb(contains,s).
verb(contain,p).
verb(likes,s).
verb(like,p).
verb(runs,s).
verb(run,p).
verb(eats,s).
vern(eat,p).
beVerb(iss,s).
beVerb(are,p).
adj(evil).
adj(divine).
adj(pacifist).
rel(that).
rel(whom).
rel(who).
rel(which).
quant(exists).
quant(some,Acc,Acc2) :- append(Acc,['exists'],Acc2).
quant(all,Acc,Acc2)  :- append(Acc,['all'],Acc2).
sign(all,Acc,Acc2) :- append(Acc,[' => '],Acc2).
sign(some,Acc,Acc2) :- append(Acc,[' & '],Acc2).

customPrint([]).
customPrint([H|Tail]) :-
  write(H),customPrint(Tail).



%grammar for parsing sentences
relcl([Rel|NP],Rest,_,Acc,Acc4) :- %nl,write('relcl1'),nl,
	rel(Rel),    append(Acc,[Rel,' '],Acc2),%(write(Rel),write(' '),
	np(NP,[Verb|Rest],SP2,Acc2,Acc3),
	verb(Verb,SP2),append(Acc3,[Verb,' '],Acc4).%write(Verb),write(' ').

relcl([Rel|VP],Rest,SP,Acc,Acc3) :- %nl,write('relcl2'),nl,
	rel(Rel),append(Acc,[Rel,' '],Acc2),%write(Rel),write(' '),
	vp(VP,Rest,SP,Acc2,Acc3).

vp([Verb|Rest],Rest,SP,Acc,Acc2) :- %nl,write(Acc),nl,
	verb(Verb,SP),append(Acc,[Verb,' '],Acc2).%write(Verb),write(' ').

vp([Verb|NP],Rest,SP,Acc,Acc3) :- %nl,write('vp2'),nl,
	verb(Verb,SP),append(Acc,[Verb,' '],Acc2),%write(Verb),write(' '),
	np(NP,Rest,_,Acc2,Acc3).

vp([BeVerb|[Adj|Rest]],Rest,SP,Acc,Acc3) :- %nl,write('vp3'),nl,write(BeVerb),
	beVerb(BeVerb,SP),append(Acc,[BeVerb,' '],Acc2),%write(BeVerb),%write(' '),
	adj(Adj)         ,append(Acc2,[Adj,' '],Acc3).%write(Adj),%write(' ').

np([Noun|Rest],Rest,SP,Acc,Acc2) :- %nl,write('np1'),nl,
	noun(Noun,SP,_),append(Acc,[Noun,' '],Acc2).%write(Noun),write(' ').

np([Det|[Noun|Rest]],Rest,SP,Acc,Acc3) :- %nl,write('np2'),nl,
	det(Det,L),     append(Acc,[Det,' '],Acc2),%write(Det),write(' '),
	noun(Noun,SP,L),append(Acc2,[Noun,' '],Acc3).%write(Noun),write(' ').

np([Det|[Noun|Relcl]],Rest,SP,Acc,Acc4) :- %nl,write('np3'),nl,
	det(Det,L),     append(Acc,[Det,' '],Acc2),%write(Det),write(' '),
	noun(Noun,SP,L),append(Acc2,[Noun,' '],Acc3),%write(Noun),write(' '),
	relcl(Relcl,Rest,SP,Acc3,Acc4).

s(Phrase)  :- np(Phrase,VP,SP,[],Acc2),vp(VP,_,SP,Acc2,Acc3),customPrint(Acc3).

%translate to FOPC
relcl2([Rel|[Quant|NP]],Rest,_,X,Acc,Acc6) :- %write('relcl1'),
	rel(Rel),
	quant(Quant),
	X2 is X + 1,
	quant(Quant,Acc,Acc2),
	append(Acc2,['(X',X2,' '],Acc3),
	np2(NP,[Verb|Rest],SP2,X2,Acc3,Acc4),
	sign(Quant,Acc4,Acc5),
	verb(Verb,SP2),append(Acc5,[Verb,'(X',X2,',X',X,',))'],Acc6).

relcl2([Rel|VP],Rest,SP,X,Acc,Acc3) :- %write('relcl2'),
	rel(Rel),append(Acc,[' & '],Acc2),
	vp2(VP,Rest,SP,X,Acc2,Acc3).

vp2([Verb|Rest],Rest,SP,X,Acc,Acc2) :- %write('vp1'),
	verb(Verb,SP),append(Acc,[Verb,'(X',X,'))'],Acc2).%write('(X'),write(X),write(') ').

vp2([Verb|[Quant|NP]],Rest,SP,X,Acc,Acc6) :- %write('vp2'),
	verb(Verb,SP),append(Acc,[Verb,'(X',X,','],Acc2),
	X2 is X + 1,
	quant(Quant,Acc2,Acc3),
	append(Acc3,['(X',X2,' '],Acc4),
	np2(NP,Rest,_,X2,Acc4,Acc5),
	append(Acc5,['))'],Acc6).

vp2([BeVerb|[Adj|Rest]],Rest,SP,X,Acc,Acc2) :- %write('vp3'),
	adj(Adj)         ,append(Acc,[Adj,'(X',X,')'],Acc2).

np2([Noun|Rest],Rest,SP,X,Acc,Acc2) :- %write('np1'),
	noun(Noun,SP,_),append(Acc,[Noun,'(X',X,')'],Acc2). %write(Noun),write('(X'),write(X),write(') ').

np2([Noun|Relcl],Rest,SP,X,Acc,Acc3) :- %write('np2'),
	noun(Noun,SP,_),append(Acc,['(',Noun,'(X',X,')'],Acc2),%write(Noun),write('(X'),write(X),write(') '),
	relcl2(Relcl,Rest,SP,X,Acc2,Acc3).

np2([Adj|[Noun|Rest]],Rest,SP,X,Acc,Acc3) :- %write('np3'),
	adj(Adj), 	 append(Acc,[Adj,'(X',X,') & '],Acc2),
	noun(Noun,SP,_),append(Acc2,[Noun,'(X',X,')'],Acc3). %write(Noun),write('(X'),write(X),write(') ').

s2([Quant|Phrase])  :- 
	%quant(Quant), write(Quant),X is 1,write('(X'),write(X),write(' '),
	X is 1,
	quant(Quant,[],Acc),
	append(Acc,['(X',X,' '],Acc2),
	np2(Phrase,VP,SP,X,Acc2,Acc3),
	sign(Quant,Acc3,Acc4),
	vp2(VP,_,SP,X,Acc4,Acc5),
	append(Acc5,[')'],Acc6),
	customPrint(Acc6).
