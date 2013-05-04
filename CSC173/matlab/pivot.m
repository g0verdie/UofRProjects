function [A C] = pivot(A,C,C1,R1,R2)
%finds a suitable replacement column for the row
if A(R2,C) ~= 0
    [A C] = rowSwap(A,C,R1,R2);
else 
    [A C] = pivot(A,C,C1,R1,R2+1);
end
end

