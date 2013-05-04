Welcome to DrRacket, version 5.3 [3m].
Language: Pretty Big; memory limit: 128 MB.
2.1

filling out a tree t1 with subtrees s1 and s3
t1- (0 (1 () () ()) () (3 () () ()))
t2- (0 (1 () () ()) () (3 () () ()))
are they equal? #t
are they eq? #f
looking up the number '3'
3
looking up the number '4'
()
part 2.2- precedes iterative loop
(6 5 4 1 ())
part 2.2- precedes recursive
(1 4 5 6)

2.3- making a queue
Q- (0)
adding 5-(0 5)
adding 1-(0 1 5)
adding 1-(0 1 5 6)
first element Q- 0
Q- (1 5 6)
2.4
dirtynumbers badnumbers eqv?
(1 0 0 0 0 6 2 3 0)

dirtyletters badletters eqv?
(a b c)

dirtyletters badletters =
. . =: contract violation
  expected: number?
  given: x
  argument position: 1st
  other arguments...:
   x
> 