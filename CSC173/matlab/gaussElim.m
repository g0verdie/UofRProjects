function [B rightAns avgTime avgVar] = gaussElim(size,reps,piv)
%top level function for elimination with partial pivoting
totalTime = 0;
tic;
avgVar = zeros(size,1);
stdDevH = zeros(size,100);
stdDev = zeros(size,1);
%make and test 10 random matricies
for i = 1:reps,
    tStart = tic;
    A = randMat(size,size);
    C = randMat(size,1);

    if checkSizes(A,C) == 0
        B = 'matricies dont match up';
    else 
        if piv == 1
            [A C] = fowardElimPiv(A,C);
        else
            [A C] = fowardElim(A,C);
        end
        [A C] = backElim(A,C);
        B = C;
        L = linsolve(A,C);
        if L <= L+eps | L >= L-eps
            rightAns = 1;
        else 
            rightAns = 0;
        end
    end
    %add time of run to total
    tStop = toc(tStart);
    totalTime = totalTime + tStop;
    %add variable amount to total
    for j= 1:size,
        avgVar(j) = avgVar(j) + C(j);
        stdDevH(j,i) = C(j);
    end
end
%get average time
avgTime = toc/reps;
%get average variable values
  for i= 1:size,
        avgVar(i) = avgVar(i) / reps;
        stdDev(i) = std(stdDevH(i,:));
  end
  display(avgVar);
  display(stdDev);
end
