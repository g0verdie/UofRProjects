function [B rightAns avgTime avgVar] = gaussElimPert(size,reps,piv,pertR)
%top level function for elimination with partial pivoting
total = 0;
avgVar = zeros(size,1);
stdDevH = zeros(1,100);
stdDev = 0;
%make and test 10 random matricies
for i = 1:reps,
    A = randMat(size,size);
    pertA = A+(pertR*rand(size,size));
    C = randMat(size,1);
    pertC = C;

    if checkSizes(A,C) == 0
        B = 'matricies dont match up';
    else 
        if piv == 1
            [A C] = fowardElimPiv(A,C);
            [pertA C] = fowardElimPiv(pertA,C);
        else
            [A C] = fowardElim(A,C);
            [pertA pertC] = fowardElim(pertA,pertC);
        end
        [A C] = backElim(A,C);
        [pertA pertC] = backElim(pertA,pertC);
        B = C;
        L = linsolve(A,C);
        if L <= L+eps | L >= L-eps
            rightAns = 1;
        else 
            rightAns = 0;
        end
    end
    stdDevH(i) = ((C(1)-pertC(1))^2)^(.5);
    %get distances, add to total
    for j= 1:size,
        total = total + ((C(j)-pertC(j))^2)^(.5);
    end
end
display(stdDevH);
stdDev = std(stdDevH);
display(stdDev);
meanDist = total/reps;
display(meanDist);
end
