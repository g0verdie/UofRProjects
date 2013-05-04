function [B avgTime] = partialPivot(size,reps)
%top level function for elimination with partial pivoting
totalTime = 0;
tic;
%make and test 10 random matricies
for i = 1:reps,
    tStart = tic;
    A = randMat(size,size);
    C = randMat(size,1);

    if checkSizes(A,C) == 0
        B = 'matricies dont match up';
    else 
        [A C] = fowardElim(A,C);
        [A C] = backElim(A,C);
        B = C;
        L = linsolve(A,C);
        if L <= L+eps | L >= L-eps
            true = 1;
        else 
            true = 0;
        end
    end
    tStop = toc(tStart);
    totalTime = totalTime + tStop;
end
avgTime = toc/reps;
end
