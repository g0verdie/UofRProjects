function [A C] = rowSwap(A,C,R1,R2)
%ERO, swaps 2 rows

hold = A(R1,:);
A(R1,:) = A(R2,:);
A(R2,:) = hold;

hold = C(R1,:);
C(R1,:) = C(R2,:);
C(R2,:) = hold;
end

